package com.intellect.investmentsms.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InvestedBankDetails;
import com.intellect.investmentsms.domain.InvestmentsWorkFlow;
import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.domain.ProductAssociatedBankDetails;
import com.intellect.investmentsms.domain.TermDepositInvestmentAccounts;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.InvestedBankDetailsRepository;
import com.intellect.investmentsms.repository.InvestmentsWorkFlowRepository;
import com.intellect.investmentsms.repository.ProductAssociatedBankDetailsRepository;
import com.intellect.investmentsms.repository.ProductRepository;
import com.intellect.investmentsms.repository.SharesInvestmentAccountsRepository;
import com.intellect.investmentsms.repository.TermDepositInvestmentAccountsRepository;
import com.intellect.investmentsms.service.InterestPaymentsService;
import com.intellect.investmentsms.service.InvestmentAccountDocumentsService;
import com.intellect.investmentsms.service.InvestmentAccountInstallmentsService;
import com.intellect.investmentsms.service.InvestmentAccountsTransactionService;
import com.intellect.investmentsms.service.TermDepositInvestmentAccountsService;
import com.intellect.investmentsms.service.dto.DepositTypesEnum;
import com.intellect.investmentsms.service.dto.InterestPaymentsDTO;
import com.intellect.investmentsms.service.dto.InvestmentAccountDocumentsDTO;
import com.intellect.investmentsms.service.dto.InvestmentAccountInstallmentsDTO;
import com.intellect.investmentsms.service.dto.InvestmentAccountsTransactionDTO;
import com.intellect.investmentsms.service.dto.ModuleTypeEnum;
import com.intellect.investmentsms.service.dto.ProductDTO;
import com.intellect.investmentsms.service.dto.SharesInvestmentAccountsDTO;
import com.intellect.investmentsms.service.dto.TermDepositInvestmentAccountsDTO;
import com.intellect.investmentsms.service.mapper.SharesInvestmentAccountsMapper;
import com.intellect.investmentsms.service.mapper.TermDepositInvestmentAccountsMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing
 * {@link com.intellect.investmentsms.domain.TermDepositInvestmentAccounts}.
 */
@Service
@Transactional
public class TermDepositInvestmentAccountsServiceImpl implements TermDepositInvestmentAccountsService {

	private final TermDepositInvestmentAccountsRepository termDepositInvestmentAccountsRepository;

	private final TermDepositInvestmentAccountsMapper termDepositInvestmentAccountsMapper;

	@Autowired
	private InvestmentsWorkFlowRepository investmentsWorkFlowRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private InvestmentAccountDocumentsService investmentAccountDocumentsService;

	@Autowired
	private InvestmentAccountInstallmentsService investmentAccountInstallmentsService;

	@Autowired
	private InterestPaymentsService interestPaymentsService;

	@Autowired
	private InvestmentAccountsTransactionService investmentAccountsTransactionService;
	
	@Autowired
	private CommonStatusRepository commonStatusRepository;

	@Autowired
	private TermInvestmentsCalculationServiceImpl termInvestmentsCalculationServiceImpl;
	
	@Autowired
	private  SharesInvestmentAccountsMapper sharesInvestmentAccountsMapper;
	
	@Autowired
	private SharesInvestmentAccountsRepository sharesInvestmentAccountsRepository;
	
    @Autowired
    private ProductAssociatedBankDetailsRepository productAssociatedBankDetailsRepository;
    
    @Autowired
    private InvestedBankDetailsRepository investedBankDetailsRepository;
	
	public TermDepositInvestmentAccountsServiceImpl(
			TermDepositInvestmentAccountsRepository termDepositInvestmentAccountsRepository,
			TermDepositInvestmentAccountsMapper termDepositInvestmentAccountsMapper) {
		this.termDepositInvestmentAccountsRepository = termDepositInvestmentAccountsRepository;
		this.termDepositInvestmentAccountsMapper = termDepositInvestmentAccountsMapper;
	}

	
	/**
	 * @implNote: save the response object by "termDepositInvestmentAccountsDTO" or Updates an termDepositInvestmentAccountsDTO.
	 * @param termDepositInvestmentAccountsDTO.
	 * @return TermDepositInvestmentAccountsDTO.
	 * @throws InvestmentsBusinessException, IOException.
	 * @author jogarao
	 */
	@Override
	public TermDepositInvestmentAccountsDTO save(TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO)
			throws InvestmentsBusinessException, IOException {
		Integer previousStatus = null;
		if (termDepositInvestmentAccountsDTO != null) {
			duplicateAccountCheck(termDepositInvestmentAccountsDTO);
			if (termDepositInvestmentAccountsDTO.getStatus() != null
					&& !termDepositInvestmentAccountsDTO.getStatus().equals(ApplicationConstants.ACTIVE)
					&& termDepositInvestmentAccountsDTO.getId() != null) {
				duplicateAccountCheck(termDepositInvestmentAccountsDTO);
			}

			if (termDepositInvestmentAccountsDTO.getId() != null) {
				Optional<TermDepositInvestmentAccounts> termDepositInvestmentAccounts = termDepositInvestmentAccountsRepository
						.findById(termDepositInvestmentAccountsDTO.getId());
				if (null != termDepositInvestmentAccounts && termDepositInvestmentAccounts.isPresent()
						&& null != termDepositInvestmentAccounts.get()) {
					termDepositInvestmentAccountsDTO.setCreatedOn(termDepositInvestmentAccounts.get().getCreatedOn());
					termDepositInvestmentAccountsDTO.setCreatedBy(termDepositInvestmentAccounts.get().getCreatedBy());
				}
			}
		}
		TermDepositInvestmentAccounts termDepositInvestmentAccounts = termDepositInvestmentAccountsMapper
				.toEntity(termDepositInvestmentAccountsDTO);
		termDepositInvestmentAccounts = termDepositInvestmentAccountsRepository.save(termDepositInvestmentAccounts);
		termDepositInvestmentAccountsDTO = termDepositInvestmentAccountsMapper.toDto(termDepositInvestmentAccounts);
		
		if(null != termDepositInvestmentAccountsDTO.getDepositType()) {
			if(termDepositInvestmentAccountsDTO.getDepositType().equals(DepositTypesEnum.FD_CUMMULATIVE.getValue())) {
				termInvestmentsCalculationServiceImpl.getMaturityAmountOnForeclosure(termDepositInvestmentAccountsDTO);
			} else if(termDepositInvestmentAccountsDTO.getDepositType().equals(DepositTypesEnum.FD_NON_CUMMULATIVE.getValue())) {
				termInvestmentsCalculationServiceImpl.saveFdNonCummulativeInterestPaymentsOnAccountApproval(termDepositInvestmentAccountsDTO, ApplicationConstants.FALSE);
			} else {
				termInvestmentsCalculationServiceImpl.getRDMaturityAmountOnMaturityDate(termDepositInvestmentAccountsDTO);
			}
		}
		
		if (previousStatus == null || !previousStatus.equals(termDepositInvestmentAccountsDTO.getStatus())) {
			saveTermDepositInvestmentAccountWorkFlow(termDepositInvestmentAccountsDTO);
		}
		return termDepositInvestmentAccountsDTO;
	}

	private void saveTermDepositInvestmentAccountWorkFlow(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) {
		if (termDepositInvestmentAccountsDTO != null) {
			InvestmentsWorkFlow investmentsWorkFlow = new InvestmentsWorkFlow();
			if (termDepositInvestmentAccountsDTO.getPacsCode() != null) {
				investmentsWorkFlow.setPacsCode(termDepositInvestmentAccountsDTO.getPacsCode());
			}
			if (termDepositInvestmentAccountsDTO.getPacsId() != null) {
				investmentsWorkFlow.setPacsId(termDepositInvestmentAccountsDTO.getPacsId());
			}
			if (termDepositInvestmentAccountsDTO.getBranchId() != null) {
				investmentsWorkFlow.setBranchId(termDepositInvestmentAccountsDTO.getBranchId());
			}
			if (termDepositInvestmentAccountsDTO.getId() != null) {
				investmentsWorkFlow.setModuleId(termDepositInvestmentAccountsDTO.getId());
				investmentsWorkFlow.setModuleType(ModuleTypeEnum.TERM_DEPOSIT_INVESTMENT_ACCOUNTS.getKey());
			}
			if (termDepositInvestmentAccountsDTO.getModifiedBy() != null) {
				investmentsWorkFlow.setUpdatedBy(termDepositInvestmentAccountsDTO.getModifiedBy());
			}
			if (termDepositInvestmentAccountsDTO.getModifiedOn() != null) {
				investmentsWorkFlow.setUpdatedOn(termDepositInvestmentAccountsDTO.getModifiedOn());
			}
			if (termDepositInvestmentAccountsDTO.getRemarks() != null) {
				investmentsWorkFlow.setRemarks(termDepositInvestmentAccountsDTO.getRemarks());
			}
			if (termDepositInvestmentAccountsDTO.getStatus() != null) {
				investmentsWorkFlow.setStatus(termDepositInvestmentAccountsDTO.getStatus());
			}
			investmentsWorkFlowRepository.save(investmentsWorkFlow);
		}
	}
	 /**
	 *@implNote: duplicate check for the bank account number
	 * @param termDepositInvestmentAccountsDTO
	 * @return void
	 * @modification duplicate check for account number
	 * @modified by LaxmiPrasannaKumar.S
	 */
	private void duplicateAccountCheck(TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO)
			throws InvestmentsBusinessException {
		List<TermDepositInvestmentAccounts> termDepositInvestmentAccounts = termDepositInvestmentAccountsRepository
				.findByAccountNumber(termDepositInvestmentAccountsDTO.getAccountNumber());
		if (termDepositInvestmentAccounts != null && !termDepositInvestmentAccounts.isEmpty()) {
			for (TermDepositInvestmentAccounts termDepositInvestmentAccount : termDepositInvestmentAccounts) {
				if (termDepositInvestmentAccount != null && null != termDepositInvestmentAccount.getId() && null != termDepositInvestmentAccountsDTO.getAccountNumber() 
						&& !termDepositInvestmentAccount.getId().equals(termDepositInvestmentAccountsDTO.getId())
						&& termDepositInvestmentAccount.getAccountNumber().equals(termDepositInvestmentAccountsDTO.getAccountNumber()))
					throw new InvestmentsBusinessException(ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNT);
			}
		}
	}

	/**
	 * @implNote: get all termDepositInvestmentAccountsDTO.
	 * @param null.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 * @modification displaying bank name in find all with the help of product Id
	 * @modifier LaxmiPrasannaKumar.S
	 */
	@Override
	@Transactional(readOnly = true)
	public List<TermDepositInvestmentAccountsDTO> findAll() {
		 LinkedList<TermDepositInvestmentAccountsDTO> termDepositInvestmentAccountsDTOList = termDepositInvestmentAccountsRepository.findAll().stream()
				.map(termDepositInvestmentAccountsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		 if (termDepositInvestmentAccountsDTOList != null && !termDepositInvestmentAccountsDTOList.isEmpty()) {
				for (TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO : termDepositInvestmentAccountsDTOList) {
					if (null != termDepositInvestmentAccountsDTO) {
						if (null != termDepositInvestmentAccountsDTO.getStatus()) {
							Optional<CommonStatus> optCommonStatus = commonStatusRepository
									.findById(termDepositInvestmentAccountsDTO.getStatus().longValue());
							if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
								termDepositInvestmentAccountsDTO.setStatusName(optCommonStatus.get().getName());
							}
						}
						if (null != termDepositInvestmentAccountsDTO.getProductId()) {
							Optional<Product> optProduct = productRepository
									.findById(termDepositInvestmentAccountsDTO.getProductId());
							if (optProduct.isPresent() && null != optProduct.get().getName()) {
								termDepositInvestmentAccountsDTO.setProductName(optProduct.get().getName());
							}
						}
						if(termDepositInvestmentAccountsDTO.getProductId()!= null) {
							termDepositInvestmentAccountsDTO = productAssociatedBankName(termDepositInvestmentAccountsDTO);
						}
					}
				}
			}
			return termDepositInvestmentAccountsDTOList;
	}

	/**
	 * @implNote: get termDepositInvestmentAccountsDTO by "id".
	 * @param id.
	 * @return TermDepositInvestmentAccountsDTO.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 * @modification displaying bank name in find one with the help of product Id
	 * @modifier LaxmiPrasannaKumar.S
	 */
	@Override
	@Transactional(readOnly = true)
	public TermDepositInvestmentAccountsDTO findOne(Long id) {
		TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO = null;
		Optional<TermDepositInvestmentAccounts> optTermDepositInvestmentAccounts = termDepositInvestmentAccountsRepository
				.findById(id);
		if (optTermDepositInvestmentAccounts.isPresent()) {
			termDepositInvestmentAccountsDTO = termDepositInvestmentAccountsMapper.toDto(optTermDepositInvestmentAccounts.get());
			if (null != termDepositInvestmentAccountsDTO) {
				if (null != termDepositInvestmentAccountsDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(termDepositInvestmentAccountsDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						termDepositInvestmentAccountsDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
				if (null != termDepositInvestmentAccountsDTO.getProductId()) {
					Optional<Product> optProduct = productRepository.findById(termDepositInvestmentAccountsDTO.getProductId());
					if (optProduct.isPresent() && null != optProduct.get().getName()) {
						termDepositInvestmentAccountsDTO.setProductName(optProduct.get().getName());
					}
				}
				if(termDepositInvestmentAccountsDTO.getProductId()!= null) {
					termDepositInvestmentAccountsDTO = productAssociatedBankName(termDepositInvestmentAccountsDTO);
				}
			}
		}
		return termDepositInvestmentAccountsDTO;
	}

	/**
	 * @implNote: delete termDepositInvestmentAccounts by "id".
	 * @param id.
	 * @return void.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	@Override
	public void delete(Long id) {
		termDepositInvestmentAccountsRepository.deleteById(id);
	}

	/**
	 * @implNote: get termDepositInvestmentAccountsDTO by "productId".
	 * @param productId.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	@Override
	public List<TermDepositInvestmentAccountsDTO> findByProductId(Long productId) {
		List<TermDepositInvestmentAccountsDTO> termDepositInvestmentAccountsDTOList = null;
		if (productId != null) {
			termDepositInvestmentAccountsDTOList = termDepositInvestmentAccountsMapper
					.toDto(termDepositInvestmentAccountsRepository.findByProductId(productId));
			if (termDepositInvestmentAccountsDTOList != null && !termDepositInvestmentAccountsDTOList.isEmpty()) {
				for (TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO : termDepositInvestmentAccountsDTOList) {
					if (null != termDepositInvestmentAccountsDTO) {
						if (null != termDepositInvestmentAccountsDTO.getStatus()) {
							Optional<CommonStatus> optCommonStatus = commonStatusRepository
									.findById(termDepositInvestmentAccountsDTO.getStatus().longValue());
							if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
								termDepositInvestmentAccountsDTO.setStatusName(optCommonStatus.get().getName());
							}
						}
						if (null != termDepositInvestmentAccountsDTO.getProductId()) {
							Optional<Product> optProduct = productRepository
									.findById(termDepositInvestmentAccountsDTO.getProductId());
							if (optProduct.isPresent() && null != optProduct.get().getName()) {
								termDepositInvestmentAccountsDTO.setProductName(optProduct.get().getName());
							}
						}

					}
				}
			}
		}
		return termDepositInvestmentAccountsDTOList;
	}

	/**
	 * @implNote: get termDepositInvestmentAccountsDTO by "pacId" and "branchId".
	 * @param pacId, branchId.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	@Override
	public List<TermDepositInvestmentAccountsDTO> findByPacIdAndBranchId(Long pacId, Long branchId)
			throws InvestmentsBusinessException {
		List<TermDepositInvestmentAccountsDTO> termDepositInvestmentAccountsDTOList = null;
		if (pacId != null && branchId != null) {
			termDepositInvestmentAccountsDTOList = termDepositInvestmentAccountsMapper
					.toDto(termDepositInvestmentAccountsRepository.getByPacsIdAndBranchId(pacId, branchId));
			if (termDepositInvestmentAccountsDTOList != null && !termDepositInvestmentAccountsDTOList.isEmpty()) {
				for (TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO : termDepositInvestmentAccountsDTOList) {
					if (null != termDepositInvestmentAccountsDTO) {
						if (null != termDepositInvestmentAccountsDTO.getStatus()) {
							Optional<CommonStatus> optCommonStatus = commonStatusRepository
									.findById(termDepositInvestmentAccountsDTO.getStatus().longValue());
							if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
								termDepositInvestmentAccountsDTO.setStatusName(optCommonStatus.get().getName());
							}
						}
						if (null != termDepositInvestmentAccountsDTO.getProductId()) {
							Optional<Product> optProduct = productRepository
									.findById(termDepositInvestmentAccountsDTO.getProductId());
							if (optProduct.isPresent() && null != optProduct.get().getName()) {
								termDepositInvestmentAccountsDTO.setProductName(optProduct.get().getName());
							}
						}

					}
				}
			}
		}
		return termDepositInvestmentAccountsDTOList;
	}

	/**
	 * @implNote: get termDepositInvestmentAccountsDTO by "termAccountId".
	 * @param termAccountId.
	 * @return TermDepositInvestmentAccountsDTO.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	@Override
	public TermDepositInvestmentAccountsDTO getPreviewByTermAccountId(Long termAccountId)
			throws InvestmentsBusinessException {
		TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO = null;
		if(null != termAccountId) {
			Optional<TermDepositInvestmentAccounts> termDepositInvestmentAccounts = termDepositInvestmentAccountsRepository.findById(termAccountId);
			if(termDepositInvestmentAccounts.isPresent() && !termDepositInvestmentAccounts.isEmpty()) {
				termDepositInvestmentAccountsDTO = termDepositInvestmentAccountsMapper.toDto(termDepositInvestmentAccounts.get());
			}
			if(null != termDepositInvestmentAccountsDTO) {
				List<InvestmentAccountDocumentsDTO> investmentAccountDocumentsDTOList = investmentAccountDocumentsService.getAllByTermAccountId(termAccountId);
				termDepositInvestmentAccountsDTO.setInvestmentAccountDocumentsDTO(investmentAccountDocumentsDTOList);
				
				List<InvestmentAccountInstallmentsDTO> investmentAccountInstallmentsDTOList = investmentAccountInstallmentsService.getByTermAccId(termAccountId);
				termDepositInvestmentAccountsDTO.setInvestmentAccountInstallmentsDTO(investmentAccountInstallmentsDTOList);
				
				List<InterestPaymentsDTO> interestPaymentsDTOList = interestPaymentsService.getInterestPaymentsListByTermAccId(termAccountId);
				termDepositInvestmentAccountsDTO.setInterestPaymentsDTO(interestPaymentsDTOList);
				
				List<InvestmentAccountsTransactionDTO> investmentAccountsTransactionDTOList = investmentAccountsTransactionService.findByTermAccId(termAccountId);
				termDepositInvestmentAccountsDTO.setInvestmentAccountsTransactionDTO(investmentAccountsTransactionDTOList);
			}
		}
		return termDepositInvestmentAccountsDTO;
	}

	/**
	 * @implNote: update the response object by "termDepositInvestmentAccountsDTO".
	 * @param termDepositInvestmentAccountsDTO.
	 * @return TermDepositInvestmentAccountsDTO
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	@Override
	public TermDepositInvestmentAccountsDTO getTermDepositInvestmentAccountStatus(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) throws InvestmentsBusinessException {
		if(null != termDepositInvestmentAccountsDTO && null != termDepositInvestmentAccountsDTO.getStatusName()) {
			CommonStatus commonStatus = commonStatusRepository.findByName(termDepositInvestmentAccountsDTO.getStatusName());
			if(null != commonStatus && null != commonStatus.getId()) {
				termDepositInvestmentAccountsDTO.setStatus(commonStatus.getId().intValue());
				termDepositInvestmentAccountsDTO = termDepositInvestmentAccountsMapper.toDto(termDepositInvestmentAccountsRepository.save(termDepositInvestmentAccountsMapper.toEntity(termDepositInvestmentAccountsDTO)));
				if(null != commonStatus.getName()) 
					termDepositInvestmentAccountsDTO.setStatusName(commonStatus.getName());
				if(null != termDepositInvestmentAccountsDTO)
					saveTermDepositInvestmentAccountWorkFlow(termDepositInvestmentAccountsDTO);
			}
		}
		
		return termDepositInvestmentAccountsDTO;
	}

	@Override
	public TermDepositInvestmentAccountsDTO getMaturityAmountOnForeclosure(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) throws InvestmentsBusinessException, IOException {
		if(null != termDepositInvestmentAccountsDTO)
			termDepositInvestmentAccountsDTO = termInvestmentsCalculationServiceImpl.getMaturityAmountOnForeclosure(termDepositInvestmentAccountsDTO);
		
		return termDepositInvestmentAccountsDTO;
	}
	
	@Override
	public TermDepositInvestmentAccountsDTO saveForeClosureDetails(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) throws InvestmentsBusinessException {
		if(null != termDepositInvestmentAccountsDTO)
			termDepositInvestmentAccountsDTO = termInvestmentsCalculationServiceImpl.saveForeClosureDetails(termDepositInvestmentAccountsDTO, ApplicationConstants.FALSE);
		
		return termDepositInvestmentAccountsDTO;
	}

	@Override
	public TermDepositInvestmentAccountsDTO getRDMaturityAmountOnForeclosure(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) throws InvestmentsBusinessException {
		if(null != termDepositInvestmentAccountsDTO)
			termDepositInvestmentAccountsDTO = termInvestmentsCalculationServiceImpl.getRDMaturityAmountOnForeclosure(termDepositInvestmentAccountsDTO);
		
		return termDepositInvestmentAccountsDTO;
	}
	/**
	 *@implNote: get all grid data  based on "pacsId and BranchId"
	 * @param pacsId,branchId
	 * @return public List<TermDepositInvestmentAccountsDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public List<TermDepositInvestmentAccountsDTO> getAllGridData(Long pacsId, Long branchId)
			throws InvestmentsBusinessException {
	    List<TermDepositInvestmentAccountsDTO> termDepositInvestmentAccountsDTOList = termDepositInvestmentAccountsMapper.toDto(termDepositInvestmentAccountsRepository
	            .findByPacsIdAndBranchId(pacsId, branchId));
	    List<SharesInvestmentAccountsDTO> sharesInvestmentAccountsDTOList =sharesInvestmentAccountsMapper.toDto(sharesInvestmentAccountsRepository
	            .findByPacsIdAndBranchId(pacsId, branchId)); 
	  
	List<TermDepositInvestmentAccountsDTO> resultList = new ArrayList<>();

	if (!termDepositInvestmentAccountsDTOList.isEmpty()) {
		termDepositInvestmentAccountsDTOList = termDepositInvestmentAccountsDTOList.stream().map(termDepoInvestAcnt -> {
					termDepoInvestAcnt = productAssociatedBankName(termDepoInvestAcnt);
		
	        return termDepoInvestAcnt;
	    }).collect(Collectors.toList());

	    resultList.addAll(termDepositInvestmentAccountsDTOList);
	}

	if (!sharesInvestmentAccountsDTOList.isEmpty()) {
		sharesInvestmentAccountsDTOList.forEach(shareInvestAcnt -> {
			TermDepositInvestmentAccountsDTO termDepoInvestAcnt = new TermDepositInvestmentAccountsDTO();
			shareInvestAcnt = productAssociatedBankName(shareInvestAcnt);
	        if(shareInvestAcnt.getShareCertificateNumber() != null)
	        	termDepoInvestAcnt.setAccountNumber(shareInvestAcnt.getShareCertificateNumber());
	        if(shareInvestAcnt.getEachShareAmount() != null)
	        	termDepoInvestAcnt.setDepositAmount(shareInvestAcnt.getEachShareAmount());
	        if(shareInvestAcnt.getSharesPurchasedDate() != null)
	        	termDepoInvestAcnt.setDepositDate(shareInvestAcnt.getSharesPurchasedDate().longValue());
	        try {
	            BeanUtils.copyProperties(termDepoInvestAcnt, shareInvestAcnt);
	            
	            resultList.add(termDepoInvestAcnt);
	        } catch (IllegalAccessException | InvocationTargetException e) {
	            e.printStackTrace();
	        }
	    });
	}

	return resultList;
	}
	/**
	 *@implNote: find the product bank  name based on "id"
	 * @param SharesInvestmentAccountsDTO
	 * @return SharesInvestmentAccountsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	private SharesInvestmentAccountsDTO productAssociatedBankName(SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO) {
		if (null != sharesInvestmentAccountsDTO.getProductId()) {
			List<ProductAssociatedBankDetails> productAssociatedBankDetails = productAssociatedBankDetailsRepository
					.findByProductId(sharesInvestmentAccountsDTO.getProductId().longValue());
			for (ProductAssociatedBankDetails productAssociatedBankDetailsDTO : productAssociatedBankDetails) {
				if(productAssociatedBankDetailsDTO.getInvestedBankDetailsId() != null) {
					sharesInvestmentAccountsDTO.setInvestedBankDetailsId(productAssociatedBankDetailsDTO.getInvestedBankDetailsId());
				}	
			}
			if(sharesInvestmentAccountsDTO.getInvestedBankDetailsId()!= null) {
				Optional<InvestedBankDetails> investedBankDetails = investedBankDetailsRepository.findById(sharesInvestmentAccountsDTO.getInvestedBankDetailsId().longValue());
				if(investedBankDetails.isPresent() && investedBankDetails.get().getBankName() != null) {
					sharesInvestmentAccountsDTO.setBankName(investedBankDetails.get().getBankName());
				}
			}	
						Optional<Product> optProduct = productRepository
								.findById(sharesInvestmentAccountsDTO.getProductId());
						if (optProduct.isPresent() && null != optProduct.get().getName()) {
							sharesInvestmentAccountsDTO.setProductName(optProduct.get().getName());
				}
						if (sharesInvestmentAccountsDTO.getStatus() != null) {
							Optional<CommonStatus> commonStatus = commonStatusRepository
									.findById(sharesInvestmentAccountsDTO.getStatus().longValue());
							if (commonStatus != null && commonStatus.isPresent() && commonStatus.get().getName() != null) {
								sharesInvestmentAccountsDTO.setStatusName(commonStatus.get().getName());
							}
						}
		}
		sharesInvestmentAccountsDTO.setDepositName(DepositTypesEnum.SHARES.getKey());

		return sharesInvestmentAccountsDTO;
	}


	/**
	 *@implNote: find the product bank  name based on "id"
	 * @param TermDepositInvestmentAccountsDTO
	 * @return TermDepositInvestmentAccountsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	private TermDepositInvestmentAccountsDTO productAssociatedBankName(TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) {
			
						if (null != termDepositInvestmentAccountsDTO.getProductId()) {
							Optional<Product> optProduct = productRepository
									.findById(termDepositInvestmentAccountsDTO.getProductId());
							if (optProduct.isPresent() && null != optProduct.get().getName()) {
								termDepositInvestmentAccountsDTO.setProductName(optProduct.get().getName());
				
							}
							List<ProductAssociatedBankDetails> productAssociatedBankDetails = productAssociatedBankDetailsRepository
									.findByProductId(termDepositInvestmentAccountsDTO.getProductId().longValue());
							for (ProductAssociatedBankDetails productAssociatedBankDetailsDTO : productAssociatedBankDetails) {
								if(productAssociatedBankDetailsDTO.getInvestedBankDetailsId() != null) {
									termDepositInvestmentAccountsDTO.setInvestedBankDetailsId(productAssociatedBankDetailsDTO.getInvestedBankDetailsId());
								}	
							}
							if(termDepositInvestmentAccountsDTO.getInvestedBankDetailsId()!= null) {
								Optional<InvestedBankDetails> investedBankDetails = investedBankDetailsRepository.findById(termDepositInvestmentAccountsDTO.getInvestedBankDetailsId().longValue());
								if(investedBankDetails.isPresent() && investedBankDetails.get().getBankName() != null) {
									termDepositInvestmentAccountsDTO.setBankName(investedBankDetails.get().getBankName());
								}
							}
						}

				        if (termDepositInvestmentAccountsDTO.getStatus() != null) {
							Optional<CommonStatus> commonStatus = commonStatusRepository
									.findById(termDepositInvestmentAccountsDTO.getStatus().longValue());
							if (commonStatus != null && commonStatus.isPresent() && commonStatus.get().getName() != null) {
								termDepositInvestmentAccountsDTO.setStatusName(commonStatus.get().getName());
							}
						}
						if(termDepositInvestmentAccountsDTO.getDepositType() != null) {
							 if (termDepositInvestmentAccountsDTO.getDepositType().equals(DepositTypesEnum.FD_NON_CUMMULATIVE.getValue())) {
								 termDepositInvestmentAccountsDTO.setDepositName(DepositTypesEnum.FD_NON_CUMMULATIVE.getKey());
				                } else if (termDepositInvestmentAccountsDTO.getDepositType().equals(DepositTypesEnum.FD_CUMMULATIVE.getValue())) {
									 termDepositInvestmentAccountsDTO.setDepositName(DepositTypesEnum.FD_CUMMULATIVE.getKey());
				                } else if (termDepositInvestmentAccountsDTO.getDepositType().equals(DepositTypesEnum.RD.getValue())) {
									 termDepositInvestmentAccountsDTO.setDepositName(DepositTypesEnum.RD.getKey());
				                }
						}

		      return termDepositInvestmentAccountsDTO;
		
	}
}
