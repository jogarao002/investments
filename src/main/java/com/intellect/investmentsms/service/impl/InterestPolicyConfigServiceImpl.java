package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InterestPolicyConfig;
import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.InterestPolicyConfigRepository;
import com.intellect.investmentsms.repository.ProductRepository;
import com.intellect.investmentsms.service.InterestPolicyConfigService;
import com.intellect.investmentsms.service.dto.InterestPolicyConfigDTO;
import com.intellect.investmentsms.service.mapper.InterestPolicyConfigMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

@Service
@Transactional
public class InterestPolicyConfigServiceImpl implements InterestPolicyConfigService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	private final InterestPolicyConfigRepository interestPolicyConfigRepository;
	private final InterestPolicyConfigMapper interestPolicyConfigMapper;

	public InterestPolicyConfigServiceImpl(InterestPolicyConfigRepository interestPolicyConfigRepository,
			InterestPolicyConfigMapper interestPolicyConfigMapper) {
		this.interestPolicyConfigRepository = interestPolicyConfigRepository;
		this.interestPolicyConfigMapper = interestPolicyConfigMapper;
	}

	/**
	 *@implNote: Save the InterestPolicyConfig by "interestPolicyConfigDTO".
	 * @param interestPolicyConfigDTO. 
	 * @return the DTO Object InterestPolicyConfigDTO.
	 * @author Dilip Kumar.G
	 */
	
	@Override
	public InterestPolicyConfigDTO save(InterestPolicyConfigDTO interestPolicyConfigDTO)
			throws InvestmentsBusinessException {
		if (interestPolicyConfigDTO != null) {
			duplicateCheck(interestPolicyConfigDTO);
			if (interestPolicyConfigDTO.getId() != null) {
				InterestPolicyConfig interestPolicyConfig = null;
				Optional<InterestPolicyConfig> kYCDocument = interestPolicyConfigRepository
						.findById(interestPolicyConfigDTO.getId());
				if (kYCDocument.isPresent()) {
					interestPolicyConfig = kYCDocument.get();
					interestPolicyConfigDTO.setCreatedOn(interestPolicyConfig.getCreatedOn());
					interestPolicyConfigDTO.setCreatedBy(interestPolicyConfig.getCreatedBy());
				}
			}
		}
		InterestPolicyConfig interestPolicyConfigs = interestPolicyConfigMapper.toEntity(interestPolicyConfigDTO);
		interestPolicyConfigs = interestPolicyConfigRepository.save(interestPolicyConfigs);
		return interestPolicyConfigMapper.toDto(interestPolicyConfigs);
	}

	/**
	 *@implNote: Update the InterestPolicyConfig by "interestPolicyConfigDTO".
	 * @param interestPolicyConfigDTO. 
	 * @return the DTO Object InterestPolicyConfigDTO.
	 * @author Dilip Kumar.G
	 */
	
	@Override
	public InterestPolicyConfigDTO update(InterestPolicyConfigDTO interestPolicyConfigDTO) {
		InterestPolicyConfig interestPolicyConfig = interestPolicyConfigMapper.toEntity(interestPolicyConfigDTO);
		interestPolicyConfig = interestPolicyConfigRepository.save(interestPolicyConfig);
		return interestPolicyConfigMapper.toDto(interestPolicyConfig);
	}

	 /**
		 *@implNote: Get all the InterestPolicyConfig.
		 * @param . 
		 * @return the List List<InterestPolicyConfigDTO>.
		 * @author Dilip Kumar.G
		 */
	
	@Override
	@Transactional(readOnly = true)
	public List<InterestPolicyConfigDTO> findAll() {
		List<InterestPolicyConfigDTO> interestPolicyConfigDTOList = interestPolicyConfigRepository.findAll().stream()
				.map(interestPolicyConfigMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		if (interestPolicyConfigDTOList != null && !interestPolicyConfigDTOList.isEmpty()) {
			for (InterestPolicyConfigDTO interestPolicyConfigDTO : interestPolicyConfigDTOList) {
				if (null != interestPolicyConfigDTO) {
					if (null != interestPolicyConfigDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(interestPolicyConfigDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							interestPolicyConfigDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if (null != interestPolicyConfigDTO.getProductId()) {
						Optional<Product> optProduct = productRepository
								.findById(interestPolicyConfigDTO.getProductId());
						if (optProduct.isPresent() && null != optProduct.get().getName()) {
							interestPolicyConfigDTO.setProductName(optProduct.get().getName());
						}
					}

				}
			}
		}
		return interestPolicyConfigDTOList;
	}

	/**
	 *@implNote: Get the InterestPolicyConfig by "id".
	 * @param id. 
	 * @return the DTO Object InterestPolicyConfigDTO.
	 * @author Dilip Kumar.G
	 */
	
	@Override
	@Transactional(readOnly = true)
	public InterestPolicyConfigDTO findOne(Long id) {
		InterestPolicyConfigDTO interestPolicyConfigDTO = null;
		Optional<InterestPolicyConfig> optInterestPolicyConfig = interestPolicyConfigRepository.findById(id);
		if (optInterestPolicyConfig.isPresent()) {
			interestPolicyConfigDTO = interestPolicyConfigMapper.toDto(optInterestPolicyConfig.get());
			if (null != interestPolicyConfigDTO) {
				if (null != interestPolicyConfigDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(interestPolicyConfigDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						interestPolicyConfigDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
				if (null != interestPolicyConfigDTO.getProductId()) {
					Optional<Product> optProduct = productRepository.findById(interestPolicyConfigDTO.getProductId());
					if (optProduct.isPresent() && null != optProduct.get().getName()) {
						interestPolicyConfigDTO.setProductName(optProduct.get().getName());
					}
				}

			}
		}
		return interestPolicyConfigDTO;
	}

	/**
   	 *@implNote: Delete the InterestPolicyConfig by "id".
   	 * @param id. 
   	 * @return void.
   	 * @author Dilip Kumar.G
   	 */
	
	@Override
	public void delete(Long id) {
		interestPolicyConfigRepository.deleteById(id);
	}

	/**
   	 *@implNote: Get the InterestPolicyConfig by "productid".
   	 * @param productid. 
   	 * @return the List List<InterestPolicyConfigDTO>.
   	 * @author Dilip Kumar.G
   	 */

	@Override
	public List<InterestPolicyConfigDTO> getInterestPolicyConfigByProductId(Long productid)
			throws InvestmentsBusinessException {
		List<InterestPolicyConfigDTO> interestPolicyConfigDTOList = null;
		if (productid != null) {
			interestPolicyConfigDTOList = interestPolicyConfigMapper
					.toDto(interestPolicyConfigRepository.findByProductId(productid));
			for (InterestPolicyConfigDTO interestPolicyConfigDTO : interestPolicyConfigDTOList) {
				if (interestPolicyConfigDTO.getProductId() != null) {
					Optional<Product> product = productRepository.findById(productid);
					if (product.isPresent() && product.get().getName() != null) {
						interestPolicyConfigDTO.setProductName(product.get().getName());
					}
				}
				if (null != interestPolicyConfigDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(interestPolicyConfigDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						interestPolicyConfigDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
				if (null != interestPolicyConfigDTO.getProductId()) {
					Optional<Product> optProduct = productRepository.findById(interestPolicyConfigDTO.getProductId());
					if (optProduct.isPresent() && null != optProduct.get().getName()) {
						interestPolicyConfigDTO.setProductName(optProduct.get().getName());
					}
				}
			}
		}
		return interestPolicyConfigDTOList;
	}

	/**
   	 *@implNote: Duplicate check for effectiveStartDate and effectiveEndDate by "interestPolicyConfigDTO".
   	 * @param interestPolicyConfigDTO. 
   	 * @return void.
   	 * @author Dilip Kumar.G
   	 */
	
	private void duplicateCheck(InterestPolicyConfigDTO interestPolicyConfigDTO) throws InvestmentsBusinessException {
		List<InterestPolicyConfig> interestPolicyConfigList = interestPolicyConfigRepository
				.findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndStatus(
						interestPolicyConfigDTO.getProductId(), interestPolicyConfigDTO.getEffectiveStartDate(),
						interestPolicyConfigDTO.getStatus());
		if (interestPolicyConfigList.isEmpty() || interestPolicyConfigList == null) {
			interestPolicyConfigList = interestPolicyConfigRepository
					.findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndStatus(
							interestPolicyConfigDTO.getProductId(), interestPolicyConfigDTO.getEffectiveStartDate(),
							interestPolicyConfigDTO.getEffectiveEndDate(), interestPolicyConfigDTO.getStatus());
		}

		if (interestPolicyConfigList != null && !interestPolicyConfigList.isEmpty()) {
			for (InterestPolicyConfig interestPolicyConfig : interestPolicyConfigList) {
				if (interestPolicyConfig != null && null != interestPolicyConfig.getId()
						&& !interestPolicyConfig.getId().equals(interestPolicyConfigDTO.getId()))
					throw new InvestmentsBusinessException(ApplicationConstants.INTEREST_POLICY_ALREADY_EXIST);
			}
		}
	}
}
