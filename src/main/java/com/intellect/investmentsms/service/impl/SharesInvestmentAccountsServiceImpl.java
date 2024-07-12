package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InvestedBankDetails;
import com.intellect.investmentsms.domain.InvestmentsWorkFlow;
import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.domain.ProductAssociatedBankDetails;
import com.intellect.investmentsms.domain.RequiredDocuments;
import com.intellect.investmentsms.domain.SharesInvestmentAccounts;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.InvestedBankDetailsRepository;
import com.intellect.investmentsms.repository.InvestmentsWorkFlowRepository;
import com.intellect.investmentsms.repository.ProductAssociatedBankDetailsRepository;
import com.intellect.investmentsms.repository.ProductRepository;
import com.intellect.investmentsms.repository.SharesInvestmentAccountsRepository;
import com.intellect.investmentsms.service.SharesInvestmentAccountsService;
import com.intellect.investmentsms.service.dto.ModuleTypeEnum;
import com.intellect.investmentsms.service.dto.ProductDTO;
import com.intellect.investmentsms.service.dto.SharesInvestmentAccountsDTO;
import com.intellect.investmentsms.service.dto.TermDepositInvestmentAccountsDTO;
import com.intellect.investmentsms.service.dto.SharesInvestmentAccountsDTO;
import com.intellect.investmentsms.service.dto.SharesInvestmentAccountsDTO;
import com.intellect.investmentsms.service.mapper.ProductMapper;
import com.intellect.investmentsms.service.mapper.SharesInvestmentAccountsMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing
 * {@link com.intellect.investmentsms.domain.sharesInvestmentAccounts}.
 */
@SuppressWarnings("unused")
@Service
@Transactional
public class SharesInvestmentAccountsServiceImpl implements SharesInvestmentAccountsService {

	private final SharesInvestmentAccountsRepository sharesInvestmentAccountsRepository;

	private final SharesInvestmentAccountsMapper sharesInvestmentAccountsMapper;

	private final ProductRepository productRepository;

	private final ProductMapper productMapper;

	private final InvestmentsWorkFlowRepository investmentsWorkFlowRepository;

	private final CommonStatusRepository commonStatusRepository;
	
    @Autowired
    private ProductAssociatedBankDetailsRepository productAssociatedBankDetailsRepository;
    
    @Autowired
    private InvestedBankDetailsRepository investedBankDetailsRepository;

	public SharesInvestmentAccountsServiceImpl(SharesInvestmentAccountsRepository sharesInvestmentAccountsRepository,
			SharesInvestmentAccountsMapper sharesInvestmentAccountsMapper, ProductRepository productRepository,
			ProductMapper productMapper, InvestmentsWorkFlowRepository investmentsWorkFlowRepository,
			CommonStatusRepository commonStatusRepository) {
		this.sharesInvestmentAccountsRepository = sharesInvestmentAccountsRepository;
		this.sharesInvestmentAccountsMapper = sharesInvestmentAccountsMapper;
		this.productMapper = productMapper;
		this.productRepository = productRepository;
		this.investmentsWorkFlowRepository = investmentsWorkFlowRepository;
		this.commonStatusRepository = commonStatusRepository;
	}

	/**
	 * @implNote: save the sharesInvestmentAccountsDTO.
	 * @param sharesInvestmentAccountsDTO.
	 * @return sharesInvestmentAccountsDTO.
	 * @author M.Bhavana
	 */
	@Override
	public SharesInvestmentAccountsDTO save(SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO)
			throws InvestmentsBusinessException {
		Integer previousStatus = null;
		if (sharesInvestmentAccountsDTO != null) {
			duplicateCheck(sharesInvestmentAccountsDTO);
			if (sharesInvestmentAccountsDTO.getId() != null) {
				Optional<SharesInvestmentAccounts> sharesInvestmentAccounts = sharesInvestmentAccountsRepository
						.findById(sharesInvestmentAccountsDTO.getId());
				if (null != sharesInvestmentAccounts && sharesInvestmentAccounts.isPresent()
						&& null != sharesInvestmentAccounts.get()) {
					sharesInvestmentAccountsDTO.setCreatedOn(sharesInvestmentAccounts.get().getCreatedOn());
					sharesInvestmentAccountsDTO.setCreatedBy(sharesInvestmentAccounts.get().getCreatedBy());
					previousStatus = sharesInvestmentAccounts.get().getStatus();
				}
			}
		}
		if (sharesInvestmentAccountsDTO.getStatusName() != null) {
			CommonStatus commonStatus = commonStatusRepository.findByName(sharesInvestmentAccountsDTO.getStatusName());
			if (commonStatus != null && commonStatus.getId() != null)
				sharesInvestmentAccountsDTO.setStatus(commonStatus.getId().intValue());
		}
		SharesInvestmentAccounts sharesInvestmentAccounts = sharesInvestmentAccountsMapper
				.toEntity(sharesInvestmentAccountsDTO);
		sharesInvestmentAccounts = sharesInvestmentAccountsRepository.save(sharesInvestmentAccounts);
		sharesInvestmentAccountsDTO = sharesInvestmentAccountsMapper.toDto(sharesInvestmentAccounts);
		if (previousStatus == null || !previousStatus.equals(sharesInvestmentAccountsDTO.getStatus())) {
			saveSharesInvestmentAccountsWorkFlow(sharesInvestmentAccountsDTO);
		}
		return sharesInvestmentAccountsDTO;
	}

	/**
	 * @implNote: get all sharesInvestmentAccountsDTO.
	 * @return the List<SharesInvestmentAccountsDTO>.
	 * @author M.Bhavana
	 * @modification displaying bank name in find all with the help of product Id
	 * @modifier LaxmiPrasannaKumar.S
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SharesInvestmentAccountsDTO> findAll() {
		List<SharesInvestmentAccountsDTO> sharesInvestmentAccountsDTOList = sharesInvestmentAccountsRepository.findAll()
				.stream().map(sharesInvestmentAccountsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		if (sharesInvestmentAccountsDTOList != null && !sharesInvestmentAccountsDTOList.isEmpty()) {
			for (SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO : sharesInvestmentAccountsDTOList) {
				if (null != sharesInvestmentAccountsDTO) {
					if (null != sharesInvestmentAccountsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(sharesInvestmentAccountsDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							sharesInvestmentAccountsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if (null != sharesInvestmentAccountsDTO.getProductId()) {
						Optional<Product> optProduct = productRepository
								.findById(sharesInvestmentAccountsDTO.getProductId());
						if (optProduct.isPresent() && null != optProduct.get().getName()) {
							sharesInvestmentAccountsDTO.setProductName(optProduct.get().getName());
						}
					}
					if(sharesInvestmentAccountsDTO.getProductId()!= null) {
						sharesInvestmentAccountsDTO = productAssociatedBankName(sharesInvestmentAccountsDTO);
					}
				}
			}
		}
		return sharesInvestmentAccountsDTOList;
	}

	/**
	 * @implNote: get sharesInvestmentAccountsDTO by id.
	 * @param id.
	 * @return sharesInvestmentAccountsDTO.
	 * @author M.Bhavana
	 * @modification displaying bank name in find one with the help of product Id
	 * @modifier LaxmiPrasannaKumar.S
	 */
	@Override
	@Transactional(readOnly = true)
	public SharesInvestmentAccountsDTO findOne(Long id) {

		SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO = null;
		Optional<SharesInvestmentAccounts> sharesInvestmentAccounts = sharesInvestmentAccountsRepository.findById(id);
		if (sharesInvestmentAccounts.isPresent())
			sharesInvestmentAccountsDTO = sharesInvestmentAccountsMapper.toDto(sharesInvestmentAccounts.get());
		if (sharesInvestmentAccountsDTO != null && sharesInvestmentAccountsDTO.getProductId() != null) {
			Optional<Product> product = productRepository.findById(sharesInvestmentAccountsDTO.getProductId());
			if (product.isPresent()) {
				ProductDTO productDTO = productMapper.toDto(product.get());
				if (productDTO != null && productDTO.getName() != null)
					sharesInvestmentAccountsDTO.setProductName(productDTO.getName());
			}
			if(sharesInvestmentAccountsDTO.getProductId()!= null) {
				sharesInvestmentAccountsDTO = productAssociatedBankName(sharesInvestmentAccountsDTO);
			}
		}
		return sharesInvestmentAccountsDTO;
	}

	
	/**
	 * @implNote: get the list of sharesInvestmentAccountsDTO by productId.
	 * @param productId.
	 * @return the List<SharesInvestmentAccountsDTO>.
	 * @author M.Bhavana
	 */
	@Override
    @Transactional(readOnly = true)
	public List<SharesInvestmentAccountsDTO> getAllByProductId(Long id) {
			List<SharesInvestmentAccounts> sharesInvestmentAccountsList = sharesInvestmentAccountsRepository.findAllByProductId(id);
			List<SharesInvestmentAccountsDTO> sharesInvestmentAccountsDTOList = null;
			if(sharesInvestmentAccountsList != null && !sharesInvestmentAccountsList.isEmpty())
			{
				sharesInvestmentAccountsDTOList = sharesInvestmentAccountsMapper.toDto(sharesInvestmentAccountsList);
				if(sharesInvestmentAccountsDTOList != null && !sharesInvestmentAccountsDTOList.isEmpty())
				{
					for(SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO : sharesInvestmentAccountsDTOList)
					{
						if (null != sharesInvestmentAccountsDTO) {
							if (null != sharesInvestmentAccountsDTO.getStatus()) {
								Optional<CommonStatus> optCommonStatus = commonStatusRepository
										.findById(sharesInvestmentAccountsDTO.getStatus().longValue());
								if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
									sharesInvestmentAccountsDTO.setStatusName(optCommonStatus.get().getName());
								}
							}
							if (null != sharesInvestmentAccountsDTO.getProductId()) {
								Optional<Product> optProduct = productRepository
										.findById(sharesInvestmentAccountsDTO.getProductId());
								if (optProduct.isPresent() && null != optProduct.get().getName()) {
									sharesInvestmentAccountsDTO.setProductName(optProduct.get().getName());
								}
							}

						}
					}
				}
			}
		
		return sharesInvestmentAccountsDTOList;
	}

	/**
	 * @implNote: delete sharesInvestmentAccountsDTO by id.
	 * @param id.
	 * @return void.
	 * @author M.Bhavana
	 */
	@Override
	public void delete(Long id) {

		sharesInvestmentAccountsRepository.deleteById(id);
	}

	/**
	 * @implNote: check whether sharesInvestmentAccountsDTO is already present or not.
	 * @param sharesInvestmentAccountsDTO.
	 * @return void.
	 * @author M.Bhavana
	 * @modification added duplicate check with id 
	 * @modifier LaxmiPrasannaKumar.S
	 */
	private void duplicateCheck(SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO)
			throws InvestmentsBusinessException {
		List<SharesInvestmentAccounts> sharesInvestmentAccountsList = sharesInvestmentAccountsRepository
				.findByShareCertificateNumber(sharesInvestmentAccountsDTO.getShareCertificateNumber());
		if (sharesInvestmentAccountsList != null && !sharesInvestmentAccountsList.isEmpty()) {
			for (SharesInvestmentAccounts sharesInvestmentAccount : sharesInvestmentAccountsList) {
				if (null != sharesInvestmentAccount && null != sharesInvestmentAccount.getShareCertificateNumber() && null != sharesInvestmentAccount.getId()
						&&  sharesInvestmentAccount.getShareCertificateNumber()
						.equals(sharesInvestmentAccountsDTO.getShareCertificateNumber())
						&& !sharesInvestmentAccount.getId()
								.equals(sharesInvestmentAccountsDTO.getId())) {
					throw new InvestmentsBusinessException(ApplicationConstants.CERTIFICATE_NUMBER_ALREADY_EXIST);
				}
			}
		}
	}

	/**
	 * @implNote: get the list of sharesInvestmentAccountsDTO by pacsId and branchId.
	 * @param pacsId and branchId.
	 * @return the List<SharesInvestmentAccountsDTO>.
	 * @author M.Bhavana
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SharesInvestmentAccountsDTO> getAllSharesInvestmentAccountsByPacsIdAndBranchId(Long pacsId,
			Long branchId) throws InvestmentsBusinessException {

		List<SharesInvestmentAccountsDTO> sharesInvestmentAccountsDTOList = sharesInvestmentAccountsMapper
				.toDto(sharesInvestmentAccountsRepository.findAllByPacsIdAndBranchId(pacsId, branchId));
		if (sharesInvestmentAccountsDTOList != null && !sharesInvestmentAccountsDTOList.isEmpty()) {
			for (SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO : sharesInvestmentAccountsDTOList) {
				if (sharesInvestmentAccountsDTO != null) {
					if (sharesInvestmentAccountsDTO.getProductId() != null) {
						Optional<Product> product = productRepository
								.findById(sharesInvestmentAccountsDTO.getProductId());
						if (product.isPresent()) {
							if (null != product.get() && null != product.get().getName())
								sharesInvestmentAccountsDTO.setProductName(product.get().getName());
						}
					}
					if (null != sharesInvestmentAccountsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(sharesInvestmentAccountsDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							sharesInvestmentAccountsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					
				}
			}

		}

		return sharesInvestmentAccountsDTOList;
	}

	/**
	 * @implNote: save sharesInvestmentAccountsDTO data to investmentsWorkFlow.
	 * @param sharesInvestmentAccountsDTO.
	 * @return void.
	 * @author M.Bhavana
	 */
	private void saveSharesInvestmentAccountsWorkFlow(SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO) {
		if (sharesInvestmentAccountsDTO != null) {
			InvestmentsWorkFlow investmentsWorkFlow = new InvestmentsWorkFlow();
			if (sharesInvestmentAccountsDTO.getPacsCode() != null)
				investmentsWorkFlow.setPacsCode(sharesInvestmentAccountsDTO.getPacsCode());
			if (sharesInvestmentAccountsDTO.getPacsId() != null)
				investmentsWorkFlow.setPacsId(sharesInvestmentAccountsDTO.getPacsId());
			if (sharesInvestmentAccountsDTO.getBranchId() != null)
				investmentsWorkFlow.setBranchId(sharesInvestmentAccountsDTO.getBranchId());
			if (sharesInvestmentAccountsDTO.getId() != null) {
				investmentsWorkFlow.setModuleId(sharesInvestmentAccountsDTO.getId());
				investmentsWorkFlow.setModuleType(ModuleTypeEnum.SHARES_INVESTMENT_ACCOUNTS.getKey());
			}
			if (sharesInvestmentAccountsDTO.getModifiedOn() != null)
				investmentsWorkFlow.setUpdatedOn(sharesInvestmentAccountsDTO.getModifiedOn());
			if (sharesInvestmentAccountsDTO.getModifiedBy() != null)
				investmentsWorkFlow.setUpdatedBy(sharesInvestmentAccountsDTO.getModifiedBy());
			if (sharesInvestmentAccountsDTO.getRemarks() != null)
				investmentsWorkFlow.setRemarks(sharesInvestmentAccountsDTO.getRemarks());
			if (sharesInvestmentAccountsDTO.getStatus() != null)
				investmentsWorkFlow.setStatus(sharesInvestmentAccountsDTO.getStatus());
			investmentsWorkFlowRepository.save(investmentsWorkFlow);
		}
	}

	/**
	 * @implNote: update sharesInvestmentAccountsDTO status.
	 * @param sharesInvestmentAccountsDTO.
	 * @return sharesInvestmentAccountsDTO.
	 * @author M.Bhavana
	 */
	@Override
	public SharesInvestmentAccountsDTO sharesInvestmentAccountsApproval(
			SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO) throws InvestmentsBusinessException {
		if (sharesInvestmentAccountsDTO != null) {
			if (sharesInvestmentAccountsDTO.getStatusName() != null) {
				CommonStatus approveStatus = commonStatusRepository
						.findByName(sharesInvestmentAccountsDTO.getStatusName());
				if (approveStatus != null)
					sharesInvestmentAccountsDTO.setStatus(approveStatus.getId().intValue());
				sharesInvestmentAccountsDTO = sharesInvestmentAccountsMapper.toDto(sharesInvestmentAccountsRepository
						.save(sharesInvestmentAccountsMapper.toEntity(sharesInvestmentAccountsDTO)));
				if (null != approveStatus.getName())
					sharesInvestmentAccountsDTO.setStatusName(approveStatus.getName());
				if (null != sharesInvestmentAccountsDTO)
					saveSharesInvestmentAccountsWorkFlow(sharesInvestmentAccountsDTO);
			}
		}
		return sharesInvestmentAccountsDTO;

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
								if(investedBankDetails.isPresent() && investedBankDetails.get().getBankName() != null && investedBankDetails.get().getBranchName() != null) {
									sharesInvestmentAccountsDTO.setBankName(investedBankDetails.get().getBankName());
									sharesInvestmentAccountsDTO.setBranchName(investedBankDetails.get().getBranchName());
								}
							}
						}

		      return sharesInvestmentAccountsDTO;
		
	}

}
