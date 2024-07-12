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
import com.intellect.investmentsms.domain.DocumentTypes;
import com.intellect.investmentsms.domain.WorkFlow;
import com.intellect.investmentsms.domain.WorkFlowSteps;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonCategoriesRepository;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.WorkFlowRepository;
import com.intellect.investmentsms.repository.WorkFlowStepsRepository;
import com.intellect.investmentsms.service.WorkFlowService;
import com.intellect.investmentsms.service.dto.WorkFlowDTO;
import com.intellect.investmentsms.service.dto.WorkFlowDTO;
import com.intellect.investmentsms.service.dto.WorkFlowStepsDTO;
import com.intellect.investmentsms.service.mapper.WorkFlowMapper;
import com.intellect.investmentsms.service.mapper.WorkFlowStepsMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing {@link WorkFlow}.
 */
@Service
@Transactional
public class WorkFlowServiceImpl implements WorkFlowService {

	private final WorkFlowRepository workFlowRepository;

	private final WorkFlowMapper workFlowMapper;

	@Autowired
	private WorkFlowStepsRepository workFlowStepsRepository;

	@Autowired
	private WorkFlowStepsMapper workFlowStepsMapper;

	@Autowired
	private CommonCategoriesRepository commonCategoriesRepository;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	public WorkFlowServiceImpl(WorkFlowRepository workFlowRepository, WorkFlowMapper workFlowMapper) {
		this.workFlowRepository = workFlowRepository;
		this.workFlowMapper = workFlowMapper;
	}

	@Override
	public WorkFlowDTO save(WorkFlowDTO workFlowDTO) throws InvestmentsBusinessException {
		if (workFlowDTO != null) {
			duplicateCheckForWorkFlow(workFlowDTO);
		}
		if (null != workFlowDTO && workFlowDTO.getId() != null) {
			WorkFlow workFlow = null;
			Optional<WorkFlow> workFlowOptional = workFlowRepository.findById(workFlowDTO.getId());
			if (workFlowOptional.isPresent()) {
				workFlow = workFlowOptional.get();
				if (workFlow.getCreatedBy() != null) {
					workFlowDTO.setCreatedBy(workFlow.getCreatedBy());
				}

				if (workFlow.getCreatedOn() != null) {
					workFlowDTO.setCreatedOn(workFlow.getCreatedOn());
				}
			}

		}
		if (null == workFlowDTO.getIsActive())
			workFlowDTO.setIsActive(ApplicationConstants.ACTIVE);
		WorkFlow workFlow = workFlowMapper.toEntity(workFlowDTO);
		workFlow = workFlowRepository.save(workFlow);
		WorkFlowDTO result = workFlowMapper.toDto(workFlow);

		return result;
	}

	public void duplicateCheckForWorkFlow(WorkFlowDTO workFlowDTO) throws InvestmentsBusinessException {
		if (workFlowDTO.getCategoryId() != null) {
			List<WorkFlow> workFlowList = workFlowRepository
					.findByCategoryIdAndIsExceptional(workFlowDTO.getCategoryId(), workFlowDTO.getIsExceptional());
			if (workFlowList != null && !workFlowList.isEmpty()) {
				for (WorkFlow workFlow : workFlowList) {
					if (null != workFlow && workFlow.getId() != workFlowDTO.getId())
						throw new InvestmentsBusinessException(ApplicationConstants.WORK_FLOW_DUPLICATE);

				}
			}
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<WorkFlowDTO> findAll() {

		List<WorkFlowDTO> workFlowDTOList = workFlowRepository.findAll().stream().map(workFlowMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));

		if (workFlowDTOList != null && !workFlowDTOList.isEmpty()) {
			for (WorkFlowDTO workFlowDTO : workFlowDTOList) {
				List<WorkFlowStepsDTO> workFlowStepsDTOList = new ArrayList<>();
				if (workFlowDTO.getId() != null) {
					if (workFlowDTO.getIsActive() == ApplicationConstants.ACTIVE) {
						workFlowDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
					} else {
						workFlowDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
					}
					if (workFlowDTO.getIsExceptional() != null
							&& workFlowDTO.getIsExceptional() == ApplicationConstants.ACTIVE) {
						workFlowDTO.setIsExceptionalName(ApplicationConstants.YES);
					} else {
						workFlowDTO.setIsExceptionalName(ApplicationConstants.NO);
					}
					CommonCategories commonCategories = null;
					if (workFlowDTO.getCategoryId() != null) {
						Optional<CommonCategories> commonCategoriesOptional = commonCategoriesRepository
								.findById(workFlowDTO.getCategoryId());
						if (commonCategoriesOptional.isPresent() && commonCategoriesOptional.get() != null)
							commonCategories = commonCategoriesOptional.get();
						if (commonCategories != null && commonCategories.getName() != null)
							workFlowDTO.setCategoryName(commonCategories.getName());
					}
				}
				List<WorkFlowSteps> workFlowStepsList = workFlowStepsRepository
						.findByWorkFlowIdAndIsActive(workFlowDTO.getId().intValue(), ApplicationConstants.ACTIVE);
				if (workFlowStepsList != null && !workFlowStepsList.isEmpty()) {
					for (WorkFlowSteps workFlowSteps : workFlowStepsList) {
						if (workFlowSteps != null) {
							WorkFlowStepsDTO workFlowStepsDTO = new WorkFlowStepsDTO();
							if (workFlowSteps.getName() != null) {
								workFlowStepsDTO.setName(workFlowSteps.getName());
							}
							if (workFlowSteps.getLevel() != null) {
								workFlowStepsDTO.setLevel(workFlowSteps.getLevel());
							}
							if (workFlowSteps.getCommonStatusId() != null) {
								workFlowStepsDTO.setCommonStatusId(workFlowSteps.getCommonStatusId());
							}
							if (workFlowSteps.getCategoryId() != null) {
								workFlowStepsDTO.setCategoryId(workFlowSteps.getCategoryId());
							}
							if (workFlowSteps.getPreviousStep() != null) {
								workFlowStepsDTO.setPreviousStep(workFlowSteps.getPreviousStep());
							}
							CommonStatus commonStatus = null;
							if (workFlowSteps.getCommonStatusId() != null) {
								Optional<CommonStatus> commonStatusOptional = commonStatusRepository
										.findById(workFlowSteps.getCommonStatusId().longValue());
								if (commonStatusOptional.isPresent())
									commonStatus = commonStatusOptional.get();
							}

							if (commonStatus != null && commonStatus.getName() != null) {
								workFlowStepsDTO.setCommonStatusName(commonStatus.getName());
							}
							WorkFlowSteps workFlowStepPrevious = null;
							Optional<WorkFlowSteps> workFlowStepsOptional = workFlowStepsRepository
									.findById(workFlowSteps.getPreviousStep().longValue());
							if (workFlowStepsOptional.isPresent())
								workFlowStepPrevious = workFlowStepsOptional.get();
							if (workFlowStepPrevious != null && workFlowStepPrevious.getName() != null) {
								workFlowStepsDTO.setPrevStepName(workFlowStepPrevious.getName());
							}

							CommonCategories commonCategories = null;
							Optional<CommonCategories> commonCategoriesOptional = commonCategoriesRepository
									.findById(workFlowStepsDTO.getCategoryId());
							if (commonCategoriesOptional.isPresent())
								commonCategories = commonCategoriesOptional.get();
							if (commonCategories != null && commonCategories.getName() != null)
								workFlowStepsDTO.setCategoryName(commonCategories.getName());
							if (workFlowSteps.getCode() != null)
								workFlowStepsDTO.setCode(workFlowSteps.getCode());
							workFlowStepsDTOList.add(workFlowStepsDTO);
						}
					}
					workFlowDTO.setWorkFlowStepsDTOList(workFlowStepsDTOList);
				}
			}
		}

		return workFlowDTOList;
	}

	@Override
	public void delete(Long id) {

		WorkFlow workFlow = null;
		Optional<WorkFlow> workFlowOptional = workFlowRepository.findById(id);
		if (workFlowOptional.isPresent()) {
			workFlow = workFlowOptional.get();
			workFlow.setIsActive(ApplicationConstants.IN_ACTIVE);
		}
		workFlowRepository.save(workFlow);
	}

	@Override
	@Transactional(readOnly = true)
	public WorkFlowDTO findOne(Long id) {
		WorkFlow workFlow = null;
		Optional<WorkFlow> workFlowOptional = workFlowRepository.findById(id);
		if (workFlowOptional.isPresent())
			workFlow = workFlowOptional.get();
		List<WorkFlowSteps> workFlowStepsList = workFlowStepsRepository.findByWorkFlowId(workFlow.getId().intValue());
		List<WorkFlowStepsDTO> WorkFlowStepsDTOList = workFlowStepsMapper.toDto(workFlowStepsList);
		for (WorkFlowStepsDTO workFlowStepsDTO : WorkFlowStepsDTOList) {
			
			if (workFlowStepsDTO.getCommonStatusId() != null) {
				CommonStatus commonStatus = null;
				Optional<CommonStatus> commonStatusOptional = commonStatusRepository
						.findById(workFlowStepsDTO.getCommonStatusId().longValue());
				if (commonStatusOptional.isPresent())
					commonStatus = commonStatusOptional.get();
				if (commonStatus != null) {
					workFlowStepsDTO.setCommonStatusName(commonStatus.getName());
				}
			}
			if (workFlowStepsDTO.getCategoryId() != null) {
				CommonCategories commonCategories = null;
				Optional<CommonCategories> commonCategoriesOptional = commonCategoriesRepository
						.findById(workFlowStepsDTO.getCategoryId());
				if (commonCategoriesOptional.isPresent())
					commonCategories = commonCategoriesOptional.get();
				if (commonCategories != null && commonCategories.getName() != null) {
					workFlowStepsDTO.setCategoryName(commonCategories.getName());
				}
			}
			if (workFlowStepsDTO.getPreviousStep() != null) {
				WorkFlowSteps workFlowSteps = null;
				Optional<WorkFlowSteps> workFlowStepsOptional = workFlowStepsRepository
						.findById(workFlowStepsDTO.getPreviousStep().longValue());
				if (workFlowStepsOptional.isPresent())
					workFlowSteps = workFlowStepsOptional.get();
				if (workFlowSteps != null && workFlowSteps.getName() != null) {
					workFlowStepsDTO.setPrevStepName(workFlowSteps.getName());
				}
			}
			if (workFlowStepsDTO.getIsExceptional() != null
					&& workFlowStepsDTO.getIsExceptional() == ApplicationConstants.ACTIVE) {
				workFlowStepsDTO.setIsExceptionalName(ApplicationConstants.YES);
			} else {
				workFlowStepsDTO.setIsExceptionalName(ApplicationConstants.NO);
			}
		}
		WorkFlowDTO workFlowDTO = workFlowMapper.toDto(workFlow);
		workFlowDTO.setWorkFlowStepsDTOList(WorkFlowStepsDTOList);
		return workFlowDTO;

	}
}
