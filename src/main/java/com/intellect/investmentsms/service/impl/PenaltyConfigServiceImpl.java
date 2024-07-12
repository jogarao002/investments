package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.PenaltyConfig;
import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.PenaltyConfigRepository;
import com.intellect.investmentsms.repository.ProductRepository;
import com.intellect.investmentsms.service.PenaltyConfigService;
import com.intellect.investmentsms.service.dto.PenaltyConfigDTO;
import com.intellect.investmentsms.service.mapper.PenaltyConfigMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing
 * {@link com.intellect.investmentsms.domain.PenaltyConfig}.
 */
@Service
@Transactional
public class PenaltyConfigServiceImpl implements PenaltyConfigService {

	private final PenaltyConfigRepository penaltyConfigRepository;

	private final PenaltyConfigMapper penaltyConfigMapper;
	
	@Autowired
	private CommonStatusRepository commonStatusRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public PenaltyConfigServiceImpl(PenaltyConfigRepository penaltyConfigRepository,
			PenaltyConfigMapper penaltyConfigMapper) {
		this.penaltyConfigRepository = penaltyConfigRepository;
		this.penaltyConfigMapper = penaltyConfigMapper;
	}

	/**
	 *@implNote: save the PenaltyConfig based on "penaltyConfigDTO".
	 * @param penaltyConfigDTO.
	 * @return PenaltyConfigDTO.
	 * @author Dileep_Kumar.Gedela
	 */
	@Override
	public PenaltyConfigDTO save(PenaltyConfigDTO penaltyConfigDTO) throws InvestmentsBusinessException {
		if (penaltyConfigDTO != null) {
			duplicateCheck(penaltyConfigDTO);
			if (penaltyConfigDTO.getId() != null) {
				Optional<PenaltyConfig> penaltyConfigOpt = penaltyConfigRepository.findById(penaltyConfigDTO.getId());
				PenaltyConfig penaltyConfig = null;
				if (penaltyConfigOpt.isPresent() && penaltyConfigOpt.get() != null)
					penaltyConfig = penaltyConfigOpt.get();
				if (penaltyConfig != null) {
					if (penaltyConfig.getCreatedBy() != null)
						penaltyConfigDTO.setCreatedBy(penaltyConfig.getCreatedBy());
					if (penaltyConfig.getCreatedOn() != null)
						penaltyConfigDTO.setCreatedOn(penaltyConfig.getCreatedOn());
				}
			}
		}
		PenaltyConfig PenaltyConfig = penaltyConfigMapper.toEntity(penaltyConfigDTO);
		PenaltyConfig = penaltyConfigRepository.save(PenaltyConfig);
		PenaltyConfigDTO result = penaltyConfigMapper.toDto(PenaltyConfig);
		return result;
	}

	/**
   	 *@implNote: get All the PenaltyConfigs.
   	 * @param 
   	 * @return List<PenaltyConfigDTO>.
   	 * @author Dileep_Kumar.Gedela
   	 */
	@Override
	@Transactional(readOnly = true)
	public List<PenaltyConfigDTO> findAll() {
		List<PenaltyConfigDTO> penaltyConfigList = penaltyConfigRepository.findAll().stream()
				.map(penaltyConfigMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		if (penaltyConfigList != null && !penaltyConfigList.isEmpty()) {
			for (PenaltyConfigDTO penaltyConfigDTO : penaltyConfigList) {
				if (null != penaltyConfigDTO) {
					if (null != penaltyConfigDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(penaltyConfigDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							penaltyConfigDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if (null != penaltyConfigDTO.getProductId()) {
						Optional<Product> optProduct = productRepository
								.findById(penaltyConfigDTO.getProductId());
						if (optProduct.isPresent() && null != optProduct.get().getName()) {
							penaltyConfigDTO.setProductName(optProduct.get().getName());
						}
					}

				}
			}
		}
		return penaltyConfigList;

	}

	/**
   	 *@implNote: get the PenaltyConfig based on "id".
   	 * @param  id.
   	 * @return PenaltyConfigDTO.
   	 * @author Dileep_Kumar.Gedela
   	 */
	@Override
	public PenaltyConfigDTO findOne(Long id) {
		PenaltyConfigDTO penaltyConfigDTO = null;
		Optional<PenaltyConfig> optPenaltyConfig = penaltyConfigRepository.findById(id);
		if (optPenaltyConfig.isPresent()) {
			penaltyConfigDTO = penaltyConfigMapper.toDto(optPenaltyConfig.get());
			if (null != penaltyConfigDTO) {
				if (null != penaltyConfigDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(penaltyConfigDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						penaltyConfigDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
				if (null != penaltyConfigDTO.getProductId()) {
					Optional<Product> optProduct = productRepository
							.findById(penaltyConfigDTO.getProductId());
					if (optProduct.isPresent() && null != optProduct.get().getName()) {
						penaltyConfigDTO.setProductName(optProduct.get().getName());
					}
				}

			}
		}
		return penaltyConfigDTO;
	}
	
	/**
   	 *@implNote: remove the penaltyConfig based on "id".
   	 * @param  id.
   	 * @return void.
   	 * @author Dileep_Kumar.Gedela
   	 */
	@Override
	public void delete(Long id) {
		penaltyConfigRepository.deleteById(id);
	}

	/**
   	 *@implNote: get all the PenaltyConfigs based on "productId".
   	 * @param productId.
   	 * @return List<PenaltyConfigDTO>.
   	 * @author Dileep_Kumar.Gedela
   	 */
	@Override
	public List<PenaltyConfigDTO> findByProductId(Long productId) {
		List<PenaltyConfigDTO> penaltyConfigDTOList = null;
		penaltyConfigDTOList = penaltyConfigMapper.toDto(penaltyConfigRepository.findByProductId(productId));

		if (penaltyConfigDTOList != null && !penaltyConfigDTOList.isEmpty()) {
			for (PenaltyConfigDTO penaltyConfigDTO : penaltyConfigDTOList) {
				if (null != penaltyConfigDTO) {
					if (null != penaltyConfigDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(penaltyConfigDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							penaltyConfigDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
					if (null != penaltyConfigDTO.getProductId()) {
						Optional<Product> optProduct = productRepository
								.findById(penaltyConfigDTO.getProductId());
						if (optProduct.isPresent() && null != optProduct.get().getName()) {
							penaltyConfigDTO.setProductName(optProduct.get().getName());
						}
					}
				}
			}
		}
		return penaltyConfigDTOList;
	}
	
	/**
	 *@implNote: duplicateCheck the PenaltyConfig based on "penaltyConfigDTO".
	 * @param penaltyConfigDTO.
	 * @return void
	 * @author Dileep_Kumar.Gedela
	 */
	private void duplicateCheck(PenaltyConfigDTO penaltyConfigDTO) throws InvestmentsBusinessException {
		List<PenaltyConfig> interestPolicyConfigList = penaltyConfigRepository
				.findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndStatus(
						penaltyConfigDTO.getProductId(), penaltyConfigDTO.getEffectiveStartDate(),
						penaltyConfigDTO.getStatus());
		if (interestPolicyConfigList.isEmpty() || interestPolicyConfigList == null) {
			interestPolicyConfigList = penaltyConfigRepository
					.findByProductIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateGreaterThanEqualAndStatus(
							penaltyConfigDTO.getProductId(), penaltyConfigDTO.getEffectiveStartDate(),
							penaltyConfigDTO.getEffectiveEndDate(), penaltyConfigDTO.getStatus());
		}
		if (interestPolicyConfigList != null && !interestPolicyConfigList.isEmpty()) {
			for (PenaltyConfig penaltyConfig : interestPolicyConfigList) {
				if (penaltyConfig != null && null != penaltyConfig.getId()
						&& !penaltyConfig.getId().equals(penaltyConfigDTO.getId()))
					throw new InvestmentsBusinessException(ApplicationConstants.PENALTY_CONFIG_ALREADY_EXIST);
			}
		}
	}
}
