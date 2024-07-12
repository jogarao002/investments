package com.intellect.investmentsms.service.impl;


import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InvestedBankDetails;
import com.intellect.investmentsms.domain.InvestmentsWorkFlow;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.domain.ProductAssociatedBankDetails;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.InvestedBankDetailsRepository;
import com.intellect.investmentsms.repository.InvestmentsWorkFlowRepository;
import com.intellect.investmentsms.repository.ProductAssociatedBankDetailsRepository;
import com.intellect.investmentsms.repository.ProductRepository;
import com.intellect.investmentsms.service.ProductService;
import com.intellect.investmentsms.service.dto.InterestPaymentsDTO;
import com.intellect.investmentsms.service.dto.InterestPolicyConfigDTO;
import com.intellect.investmentsms.service.dto.InvestmentAccountDocumentsDTO;
import com.intellect.investmentsms.service.dto.ModuleTypeEnum;
import com.intellect.investmentsms.service.dto.PenaltyConfigDTO;
import com.intellect.investmentsms.service.dto.ProductAssociatedBankDetailsDTO;
import com.intellect.investmentsms.service.dto.ProductDTO;
import com.intellect.investmentsms.service.dto.RequiredDocumentsDTO;
import com.intellect.investmentsms.service.dto.SharesInvestmentAccountsDTO;
import com.intellect.investmentsms.service.dto.TermDepositInvestmentAccountsDTO;
import com.intellect.investmentsms.service.mapper.ProductMapper;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.CommonFunctionUtil;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * Service Implementation for managing {@link com.intellect.investmentsms.domain.Product}.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;
    
    private final CommonStatusRepository commonStatusRepository;
    
    private final InvestmentsWorkFlowRepository investmentsWorkFlowRepository;
    
    @Autowired
    private  ProductAssociatedBankDetailsServiceImpl productAssociatedBankDetailsServiceImpl;
    
    @Autowired
    private InterestPolicyConfigServiceImpl interestPolicyConfigServiceImpl;
    
    @Autowired
    private PenaltyConfigServiceImpl penaltyConfigServiceImpl;
    
    @Autowired
    private RequiredDocumentsServiceImpl requiredDocumentsServiceImpl;
    
    @Autowired
    private InvestmentAccountDocumentsServiceImpl investmentAccountDocumentsServiceImpl;
    
    @Autowired
    private InterestPaymentsServiceImpl interestPaymentsServiceImpl;
    
    @Autowired
    private TermDepositInvestmentAccountsServiceImpl termDepositInvestmentAccountsServiceImpl;
    
    @Autowired
    private SharesInvestmentAccountsServiceImpl sharesInvestmentAccountsServiceImpl;
    
    @Autowired
    private ProductAssociatedBankDetailsRepository productAssociatedBankDetailsRepository;
    
    @Autowired
    private InvestedBankDetailsRepository investedBankDetailsRepository;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,CommonStatusRepository commonStatusRepository,
            InvestmentsWorkFlowRepository investmentsWorkFlowRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.commonStatusRepository = commonStatusRepository;
        this.investmentsWorkFlowRepository = investmentsWorkFlowRepository;
    }
	/**
	 *@implNote: save the product based on "productDTO"
	 * @param productDTO .
	 * @return ProductDTO
	 * @author LaxmiPrasannaKumar.S
	 */
    @Override
    public ProductDTO save(ProductDTO productDTO) throws InvestmentsBusinessException{
		Integer previousStatus = null;
    	if(productDTO != null) {
    		duplicateProductCheck(productDTO);
    		if(productDTO.getId() != null) {
    			Optional<Product> ProductList = productRepository.findById(productDTO.getId());
    			Product products = null;
    			if(ProductList.isPresent()) {
    				products = ProductList.get();
    			}
    			if(products != null) {
    				if(products.getCreatedBy() != null) 
    					productDTO.setCreatedBy(products.getCreatedBy());
    				if(products.getCreatedOn() != null)
    					productDTO.setCreatedOn(products.getCreatedOn());
    				previousStatus = products.getStatus().intValue();
    			}
    		}
    	}
    	if (productDTO.getStatusName() != null) {
			CommonStatus commonStatus = commonStatusRepository.findByName(productDTO.getStatusName());
			if (commonStatus != null && commonStatus.getId() != null)
				productDTO.setStatus(commonStatus.getId().intValue());
		}
    	Product productDetails = productMapper.toEntity(productDTO);
    	productDetails = productRepository.save(productDetails);
    	ProductDTO productDetailsDto =  productMapper.toDto(productDetails);
		if (previousStatus == null || (productDetailsDto != null && previousStatus != null && !previousStatus.equals(productDTO.getStatus()))) {
			productWorkFlowSave(productDetailsDto);
		}
    	return productDetailsDto;
	}
	/**
	 *@implNote: saving work flow for the product based on productDetailsDto
	 * @param productDetailsDto
	 * @return void
	 * @author LaxmiPrasannaKumar.S
	 */
	private void productWorkFlowSave(ProductDTO productDetailsDto) {
		if (productDetailsDto != null) {
			InvestmentsWorkFlow investmentsWorkFlow = new InvestmentsWorkFlow();
			if(productDetailsDto.getId() != null) {
				investmentsWorkFlow.setModuleId(productDetailsDto.getId());
			}
			if (productDetailsDto.getPacsCode() != null) {
				investmentsWorkFlow.setPacsCode(productDetailsDto.getPacsCode());
			}
			if (productDetailsDto.getPacsId() != null) {
				investmentsWorkFlow.setPacsId(productDetailsDto.getPacsId());
			}
			if (productDetailsDto.getBranchId() != null) {
				investmentsWorkFlow.setBranchId(productDetailsDto.getBranchId());
			}
			if (productDetailsDto.getId() != null) {
				investmentsWorkFlow.setModuleType(ModuleTypeEnum.PRODUCT_DEFINITION.getKey());
			}
			if (productDetailsDto.getModifiedBy() != null) {
				investmentsWorkFlow.setUpdatedBy(productDetailsDto.getModifiedBy());
			}
			if (productDetailsDto.getModifiedOn() != null) {
				investmentsWorkFlow.setUpdatedOn(productDetailsDto.getModifiedOn());
			}
			if (productDetailsDto.getRemarks() != null) {
				investmentsWorkFlow.setRemarks(productDetailsDto.getRemarks());
			}
			if (productDetailsDto.getStatus() != null) {
				investmentsWorkFlow.setStatus(productDetailsDto.getStatus());
			}
			investmentsWorkFlowRepository.save(investmentsWorkFlow);
		}
		
	}
	/**
	 *@implNote: duplicate check for the product  based on productDetailsDto
	 * @param productDTO
	 * @return void
	 * @author LaxmiPrasannaKumar.S
	 */
	private void duplicateProductCheck(ProductDTO productDTO) throws InvestmentsBusinessException {
		 List<Product> productList = productRepository
	                .findByEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndStatus(productDTO.getEffectiveStartDate(),productDTO.getStatus());
	        if (productList.isEmpty() || productList == null ) {
	        	productList = productRepository.findByEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndStatus(
	        			productDTO.getEffectiveStartDate(),productDTO.getEffectiveEndDate(),productDTO.getStatus());
	        }
	        if (productList != null && !productList.isEmpty()) {
	            for (Product products : productList) {
	                if (products != null && null != products.getId()
	                        && !products.getId().equals(products.getId())) {
	                    throw new InvestmentsBusinessException(ApplicationConstants.PRODUCT_ALREADY_EXIST);
	                }
	            }
	        }
		
	}
	/**
	 *@implNote: find all product details
	 * @param 
	 * @return List<ProductDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() throws InvestmentsBusinessException{
		List<ProductDTO> productDTOList = productRepository.findAll().stream().map(productMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
		if (productDTOList != null && !productDTOList.isEmpty()) {
			for (ProductDTO productDTO : productDTOList) {
				if (productDTO != null) {
					if (null != productDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(productDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							productDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if(productDTO.getId()!= null) {
						productDTO = productAssociatedBankName(productDTO);
					}
				}
			}
		}
		return productDTOList;
	}
	/**
	 *@implNote: find the product details based on "id"
	 * @param id
	 * @return ProductDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
    @Transactional(readOnly = true)
    public ProductDTO findOne(Long id) throws InvestmentsBusinessException{
      Optional<ProductDTO> optProductDTO =  productRepository.findById(id).map(productMapper::toDto);
      ProductDTO productDTO = null;
		if (optProductDTO.isPresent()) {
			productDTO = optProductDTO.get();
			if (productDTO != null) {
				if (null != productDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(productDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						productDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
				if(productDTO.getId()!= null) {
					productDTO = productAssociatedBankName(productDTO);

				}
			}
		}
       return productDTO;
    }
	/**
	 *@implNote: find the product bank  name based on "id"
	 * @param productDTO
	 * @return ProductDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	private ProductDTO productAssociatedBankName(ProductDTO productDTO) {
			
						if (null != productDTO.getId()) {
							List<ProductAssociatedBankDetails> productAssociatedBankDetails = productAssociatedBankDetailsRepository
									.findByProductId(productDTO.getId().longValue());
							for (ProductAssociatedBankDetails productAssociatedBankDetailsDTO : productAssociatedBankDetails) {
								if(productAssociatedBankDetailsDTO.getInvestedBankDetailsId() != null) {
									productDTO.setInvestedBankDetailsId(productAssociatedBankDetailsDTO.getInvestedBankDetailsId());
								}	
							}
							if(productDTO.getInvestedBankDetailsId()!= null) {
								Optional<InvestedBankDetails> investedBankDetails = investedBankDetailsRepository.findById(productDTO.getInvestedBankDetailsId().longValue());
								if(investedBankDetails.isPresent() && investedBankDetails.get().getBankName() != null && investedBankDetails.get().getBranchName() != null ) {
									productDTO.setBankName(investedBankDetails.get().getBankName());
									productDTO.setBranchName(investedBankDetails.get().getBranchName());
								}
							}
						}

		      return productDTO;
		
	}
	/**
	 *@implNote: delete the product details based on "id"
	 * @param id
	 * @return void
	 * @author LaxmiPrasannaKumar.S
	 */
    @Override
    public void delete(Long id) throws InvestmentsBusinessException{
        productRepository.deleteById(id);
    }
	/**
	 *@implNote: preview the product details based on "productId"
	 * @param productId
	 * @return ProductDTO
	 * @author LaxmiPrasannaKumar.S
	 */
    @Override
	public ProductDTO getPreviewByProductId(Long productId) throws InvestmentsBusinessException {
    	ProductDTO productDTO = null;
		if (productId != null) {
			Optional<Product> productsOptional = productRepository.findById(productId);
			if (productsOptional.isPresent())
				productDTO = productMapper.toDto(productsOptional.get());
			if (productDTO != null) {
				List<InterestPolicyConfigDTO> interestPolicyConfigDTOList = interestPolicyConfigServiceImpl
						.getInterestPolicyConfigByProductId(productId);
				if (interestPolicyConfigDTOList != null && !interestPolicyConfigDTOList.isEmpty())
					productDTO.setInterestPolicyConfigDTO(interestPolicyConfigDTOList);

				List<ProductAssociatedBankDetailsDTO> productAssociatedBankDetailsDTOList = (productAssociatedBankDetailsServiceImpl
						.getProductAssociatedBankDetailsByProductId(productId));
				if (productAssociatedBankDetailsDTOList != null
						&& !productAssociatedBankDetailsDTOList.isEmpty())
					productDTO.setProductAssociatedBankDetailsDTO(productAssociatedBankDetailsDTOList);

				List<PenaltyConfigDTO> penaltyConfigDTOList = (penaltyConfigServiceImpl
						.findByProductId(productId));
				if (penaltyConfigDTOList != null && !penaltyConfigDTOList.isEmpty())
					productDTO.setPenaltyConfigDTO(penaltyConfigDTOList);

				List<RequiredDocumentsDTO> requiredDocumentsDTOList = (requiredDocumentsServiceImpl
						.findByProductId(productId));
				if (requiredDocumentsDTOList != null && !requiredDocumentsDTOList.isEmpty())
					productDTO.setRequiredDocumentsDTO(requiredDocumentsDTOList);

				List<InvestmentAccountDocumentsDTO> investmentAccountDocumentsDTOList = (investmentAccountDocumentsServiceImpl
						.getAllByProductId(productId));
				if (investmentAccountDocumentsDTOList != null && !investmentAccountDocumentsDTOList.isEmpty())
					productDTO.setInvestmentAccountDocumentsDTO(investmentAccountDocumentsDTOList);

				List<InterestPaymentsDTO> interestPaymentsDTOList = (interestPaymentsServiceImpl
						.getInterestPaymentsListByProducts(productId));
				if (interestPaymentsDTOList != null && !interestPaymentsDTOList.isEmpty())
					productDTO.setInterestPaymentsDTO(interestPaymentsDTOList);

				List<TermDepositInvestmentAccountsDTO> termDepositInvestmentAccountsDTOList = (termDepositInvestmentAccountsServiceImpl
						.findByProductId(productId));
				if (termDepositInvestmentAccountsDTOList != null && !termDepositInvestmentAccountsDTOList.isEmpty())
					productDTO.setTermDepositInvestmentAccountsDTO(termDepositInvestmentAccountsDTOList);

				List<SharesInvestmentAccountsDTO> sharesInvestmentAccountsDTOList = (sharesInvestmentAccountsServiceImpl
						.getAllByProductId(productId));
				if (sharesInvestmentAccountsDTOList != null && !sharesInvestmentAccountsDTOList.isEmpty())
					productDTO.setSharesInvestmentAccountsDTO(sharesInvestmentAccountsDTOList);

			}

		}
		return productDTO;
    }
	/**
	 *@implNote: update the product details status based on "productDTO"
	 * @param productDTO
	 * @return ProductDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public ProductDTO updateProductStatus(ProductDTO productDTO) throws InvestmentsBusinessException {
		 if (productDTO != null) {
	            if (productDTO.getStatusName() != null) {
	                CommonStatus approveStatus = commonStatusRepository.findByName(productDTO.getStatusName());
	                if (approveStatus != null)
	                	productDTO.setStatus(approveStatus.getId().intValue());
	                Product product = productMapper.toEntity(productDTO);
	                productDTO = productMapper.toDto(productRepository.save(product));
	                productWorkFlowSave(productDTO);
	            }
	            if (null != productDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(productDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						productDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
	            if (null != productDTO.getId()) {
					List<ProductAssociatedBankDetails> productAssociatedBankDetails = productAssociatedBankDetailsRepository
							.findByProductId(productDTO.getId().longValue());
						if(productAssociatedBankDetails.get(0) != null) {
							productDTO.setInvestedBankDetailsId( productAssociatedBankDetails.get(0).getInvestedBankDetailsId());
						}	
					if(productDTO.getInvestedBankDetailsId()!= null) {
						Optional<InvestedBankDetails> investedBankDetails = investedBankDetailsRepository.findById(productDTO.getInvestedBankDetailsId().longValue());
						if(investedBankDetails.isPresent() && investedBankDetails.get().getBankName() != null) {
							productDTO.setBankName(investedBankDetails.get().getBankName());
						}
					}
				}
	        }
	        return productDTO;
	}
	/**
	 *@implNote: find all active product details based on pacsId
	 * @param pacsId
	 * @return List<ProductDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public List<ProductDTO> getAllActiveProductsBasedOnPacsId(Long pacsId) throws InvestmentsBusinessException {
		List<ProductDTO> productDefinitionDTOList = null;
		if (pacsId != null) {
			productDefinitionDTOList = productMapper.toDto(productRepository
					.findByEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndPacsId(
							CommonFunctionUtil.getCurrentDateWithoutTime(),
							CommonFunctionUtil.getCurrentDateWithoutTime(), pacsId));
			List<ProductDTO> productDefinitionList = productMapper.toDto(productRepository.findByEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndPacsId(
							CommonFunctionUtil.getCurrentDateWithoutTime(), pacsId));
			
			productDefinitionDTOList.addAll(productDefinitionList);
			if (productDefinitionDTOList != null && !productDefinitionDTOList.isEmpty()) {
				for (ProductDTO productDTO : productDefinitionDTOList) {
					if (productDTO != null) {
						Optional<CommonStatus> commonStatus = commonStatusRepository
								.findById(productDTO.getStatus().longValue());
						if (commonStatus.isPresent() && commonStatus.get().getName() != null) {
							productDTO.setStatusName(commonStatus.get().getName());
						}
					}
				}
			}
		}
		return productDefinitionDTOList;
	}


}
