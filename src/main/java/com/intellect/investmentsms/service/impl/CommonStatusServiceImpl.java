package com.intellect.investmentsms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonCategories;
import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonCategoriesRepository;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.service.CommonStatusService;
import com.intellect.investmentsms.service.dto.CommonStatusDTO;
import com.intellect.investmentsms.service.mapper.CommonStatusMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing {@link CommonStatus}.
 */
@Service
@Transactional
public class CommonStatusServiceImpl implements CommonStatusService {

	private final CommonStatusRepository commonStatusRepository;

	private final CommonStatusMapper commonStatusMapper;

	@Autowired
	private CommonCategoriesRepository commonCategoriesRepository;

	public CommonStatusServiceImpl(CommonStatusRepository commonStatusRepository,
			CommonStatusMapper commonStatusMapper) {
		this.commonStatusRepository = commonStatusRepository;
		this.commonStatusMapper = commonStatusMapper;
	}

	@Override
	public CommonStatusDTO save(CommonStatusDTO commonStatusDTO) throws InvestmentsBusinessException {
		if (commonStatusDTO != null && commonStatusDTO.getName() != null && commonStatusDTO.getCategoryId() != null) {
			duplicateCheckcommonStatus(commonStatusDTO);
		}
		if (null != commonStatusDTO && commonStatusDTO.getId() != null) {
			CommonStatus commonStatus = null;
			Optional<CommonStatus> commonStatusOptional = commonStatusRepository.findById(commonStatusDTO.getId());
			if (commonStatusOptional.isPresent())
				commonStatus = commonStatusOptional.get();
			commonStatusDTO.setCreatedBy(commonStatus.getCreatedBy());
			commonStatusDTO.setCreatedOn(commonStatus.getCreatedOn());
		}
		CommonStatus commonStatus = commonStatusMapper.toEntity(commonStatusDTO);
		commonStatus = commonStatusRepository.save(commonStatus);
		CommonStatusDTO result = commonStatusMapper.toDto(commonStatus);
		commonStatusRepository.save(commonStatus);
		return result;
	}

	public void duplicateCheckcommonStatus(CommonStatusDTO commonStatusDTO) throws InvestmentsBusinessException {
		List<CommonStatus> commonStatusList = commonStatusRepository.findByNameAndCategoryId(commonStatusDTO.getName(),
				commonStatusDTO.getCategoryId());
		if (commonStatusList != null && !commonStatusList.isEmpty()) {
			for (CommonStatus commonStatus : commonStatusList) {
				if (null != commonStatus && commonStatus.getId() != commonStatusDTO.getId())
					throw new InvestmentsBusinessException(ApplicationConstants.COMMON_STATUS_DUPLICATE);
			}
		}
	}
	/**
	 * @implNote: get all common status based 
	 * @param 
	 * @return List<CommonStatusDTO>
	 * @modification set the common category name
	 * @modified by LaxmiPrasannaKumar.S
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CommonStatusDTO> findAll() {
		List<CommonStatusDTO> commonStatusDTOList = commonStatusMapper.toDto(commonStatusRepository.findAll());
		for (CommonStatusDTO commonStatusDTO : commonStatusDTOList) {
			if (null != commonStatusDTO) {
				if (null != commonStatusDTO.getCategoryId()) {
					Optional<CommonCategories> optCommonCategories = commonCategoriesRepository
							.findById(commonStatusDTO.getCategoryId());
					if (optCommonCategories.isPresent()) {
						if (null != optCommonCategories.get() && null != optCommonCategories.get().getName())
							commonStatusDTO.setCommonCategoryName(optCommonCategories.get().getName());
					}
				}
				if(commonStatusDTO.getStatus()!=null) {
					if (commonStatusDTO.getStatus() == ApplicationConstants.ACTIVE) {
						commonStatusDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
					}else {
						commonStatusDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
					}
				}
			}
		}
		return commonStatusDTOList;
	}

	@Override
	@Transactional(readOnly = true)
	public CommonStatusDTO findOne(Long id) {
		CommonStatusDTO commonStatusDTO = null;
		Optional<CommonStatus> optCommonStatus = commonStatusRepository.findById(id);
		if (optCommonStatus.isPresent()) {
			commonStatusDTO = commonStatusMapper.toDto(optCommonStatus.get());
			if (null != commonStatusDTO.getCategoryId()) {
				Optional<CommonCategories> optCommonCategories = commonCategoriesRepository
						.findById(commonStatusDTO.getCategoryId());
				if (optCommonCategories.isPresent()) {
					if (null != optCommonCategories.get() && null != optCommonCategories.get().getName()) {
						commonStatusDTO.setCommonCategoryName(optCommonCategories.get().getName());
						if (null != commonStatusDTO && null != commonStatusDTO.getStatus()
								&& commonStatusDTO.getStatus() == ApplicationConstants.ACTIVE) {
							commonStatusDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
						} else {
							commonStatusDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
						}
					}
				}
			}
		}
		return commonStatusDTO;
	}

	@Override
	public void delete(Long id) {
		CommonStatus commonStatus = null;
		Optional<CommonStatus> commonStatusOptional = commonStatusRepository.findById(id);
		if (commonStatusOptional.isPresent())
			commonStatus = commonStatusOptional.get();
		commonStatus.setStatus(ApplicationConstants.IN_ACTIVE);
		commonStatusRepository.save(commonStatus);
	}

}
