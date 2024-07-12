package com.intellect.investmentsms.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonCategories;
import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonCategoriesRepository;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.service.CommonCategoriesService;
import com.intellect.investmentsms.service.dto.CommonCategoriesDTO;
import com.intellect.investmentsms.service.dto.CommonStatusDTO;
import com.intellect.investmentsms.service.mapper.CommonCategoriesMapper;
import com.intellect.investmentsms.service.mapper.CommonStatusMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing {@link CommonCategories}.
 */
@Service
@Transactional
public class CommonCategoriesServiceImpl implements CommonCategoriesService {

	private final CommonCategoriesRepository commonCategoriesRepository;

	private final CommonCategoriesMapper commonCategoriesMapper;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	@Autowired
	private CommonStatusMapper commonStatusMapper;

	public CommonCategoriesServiceImpl(CommonCategoriesRepository commonCategoriesRepository,
			CommonCategoriesMapper commonCategoriesMapper) {
		this.commonCategoriesRepository = commonCategoriesRepository;
		this.commonCategoriesMapper = commonCategoriesMapper;
	}

	@Override
	public CommonCategoriesDTO save(CommonCategoriesDTO commonCategoriesDTO) throws InvestmentsBusinessException {
		if (commonCategoriesDTO != null) {
			if (commonCategoriesDTO != null && commonCategoriesDTO.getName() != null) {
				duplicateCheckcommonCategory(commonCategoriesDTO);
			}
			if (null != commonCategoriesDTO && commonCategoriesDTO.getId() != null) {
				CommonCategories commonCategories = null;
				Optional<CommonCategories> commonCategoriesOptional = commonCategoriesRepository
						.findById(commonCategoriesDTO.getId());

				if (commonCategoriesOptional.isPresent())
					commonCategories = commonCategoriesOptional.get();
				if (commonCategoriesDTO.getCreatedBy() != null) {
					commonCategoriesDTO.setCreatedBy(commonCategories.getCreatedBy());
				}
				if (commonCategoriesDTO.getCreatedOn() != null) {
					commonCategoriesDTO.setCreatedOn(commonCategories.getCreatedOn());
				}
				if (commonCategoriesDTO.getStatus() == ApplicationConstants.IN_ACTIVE
						&& commonCategoriesDTO.getId() != null) {
					List<CommonStatus> commonStatusList = commonStatusRepository
							.findByCategoryIdAndStatus(commonCategories.getId(), commonCategories.getStatus());
					if (commonStatusList != null && !commonStatusList.isEmpty()) {
						throw new InvestmentsBusinessException(
								ApplicationConstants.COMMON_CATEGORY_ACTIVE_IN_COMMON_STATUS);
					}
				}
				if (null == commonCategoriesDTO.getStatus()) {
					commonCategoriesDTO.setStatus(ApplicationConstants.ACTIVE);
				}

			}
		}

		CommonCategories commonCategories = commonCategoriesMapper.toEntity(commonCategoriesDTO);
		commonCategories = commonCategoriesRepository.save(commonCategories);
		CommonCategoriesDTO result = commonCategoriesMapper.toDto(commonCategories);
		if (commonCategoriesDTO.getCommonStatusDTOList() != null
				&& !commonCategoriesDTO.getCommonStatusDTOList().isEmpty()) {
			for (CommonStatusDTO commonStatusDTO : commonCategoriesDTO.getCommonStatusDTOList()) {
				duplicateCheckcommonStatus(commonStatusDTO);

				commonStatusDTO.setCategoryId(commonCategories.getId());
				commonStatusRepository.save(commonStatusMapper.toEntity(commonStatusDTO));
			}
		}
		commonCategoriesRepository.save(commonCategories);
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

	public void duplicateCheckcommonCategory(CommonCategoriesDTO commonCategoriesDTO)
			throws InvestmentsBusinessException {
		CommonCategories commonCategories = commonCategoriesRepository.findByName(commonCategoriesDTO.getName());

		if (null != commonCategories && commonCategories.getId() != commonCategoriesDTO.getId())
			throw new InvestmentsBusinessException(ApplicationConstants.COMMON_CATEGORIES_DUPLICATE);

	}
	/**
	 * @implNote: get all common categories
	 * @param 
	 * @return List<CommonCategoriesDTO>
	 * @modification get all sub categories from common status
	 * @modified by LaxmiPrasannaKumar.S
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CommonCategoriesDTO> findAll() {
		List<CommonCategoriesDTO> commonCategoriesDTOList = commonCategoriesRepository.findAll().stream()
				.map(commonCategoriesMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
		if (commonCategoriesDTOList != null && !commonCategoriesDTOList.isEmpty()) {
			for (CommonCategoriesDTO commonCategoriesDTO : commonCategoriesDTOList) {
				if (commonCategoriesDTO.getId() != null) {
					if (commonCategoriesDTO.getStatus() == ApplicationConstants.ACTIVE) {
						commonCategoriesDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
					} else {
						commonCategoriesDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
					}
				}
				if (commonCategoriesDTO.getId() != null) {
					List<CommonStatusDTO> commonStatusDTOList = getAllSubCategories(commonCategoriesDTO.getId());
					if (commonStatusDTOList != null && !commonStatusDTOList.isEmpty()) {
						commonCategoriesDTO.setCommonStatusDTOList(commonStatusDTOList);

					}
				}
			}
		}
		return commonCategoriesDTOList;
	}
	/**
	 * @implNote: get the common categories based On "id"
	 * @param id
	 * @return CommonCategoriesDTO
	 * @modification get all sub categories from common status
	 * @modified by LaxmiPrasannaKumar.S
	 */
	@Override
	@Transactional(readOnly = true)
	public CommonCategoriesDTO findOne(Long id) {

		CommonCategoriesDTO commonCategoriesDTO = null;
		Optional<CommonCategories> optCommonCategories = commonCategoriesRepository.findById(id);
		if (optCommonCategories.isPresent()) {
			commonCategoriesDTO = commonCategoriesMapper.toDto(optCommonCategories.get());
			if(null != commonCategoriesDTO && null != commonCategoriesDTO.getId()) {
				if(commonCategoriesDTO.getStatus() == ApplicationConstants.ACTIVE) {
					commonCategoriesDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
				}else {
					commonCategoriesDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
				}
			}
			if (commonCategoriesDTO.getId() != null) {
				List<CommonStatusDTO> commonStatusDTOList = getAllSubCategories(commonCategoriesDTO.getId());
				if (commonStatusDTOList != null && !commonStatusDTOList.isEmpty()) {
					commonCategoriesDTO.setCommonStatusDTOList(commonStatusDTOList);

				}
			}
		}
		return commonCategoriesDTO;

	}

	/**
	 * Delete the AppraisalSchedule by id.
	 *
	 * @param id the id of the entity.
	 * @throws DataCapturingToolBusinessException
	 */

	@Override
	public void delete(Long id) {
		CommonCategories commonCategories = null;
		Optional<CommonCategories> commonCategoriesOptional = commonCategoriesRepository.findById(Long.valueOf(id));
		if (commonCategoriesOptional.isPresent())
			commonCategories = commonCategoriesOptional.get();
		commonCategories.setStatus(ApplicationConstants.IN_ACTIVE);
		commonCategoriesRepository.save(commonCategories);
	}

	public List<CommonStatusDTO> getAllSubCategories(Long commonCategoryId) {
		List<CommonStatusDTO> result = null;
		List<CommonStatus> commonStatusList = new ArrayList<>();
		commonStatusList = commonStatusRepository.findByCategoryId(commonCategoryId);
		result = commonStatusMapper.toDto(commonStatusList);
		for (CommonStatusDTO commonStatusDTO : result) {
			if (commonStatusDTO.getCategoryId() != null) {
				CommonCategories commonCategories = null;
				Optional<CommonCategories> commonCategoriesOptional = commonCategoriesRepository
						.findById(Long.valueOf(commonStatusDTO.getCategoryId()));
				if (commonCategoriesOptional.isPresent())
					commonCategories = commonCategoriesOptional.get();
				if (commonCategories != null) {
					commonStatusDTO.setCommonCategoryName(commonCategories.getName());
				}
				if (commonStatusDTO.getStatus() == ApplicationConstants.ACTIVE)
					commonStatusDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
				else
					commonStatusDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
			}
		}
		return result;
	}

}
