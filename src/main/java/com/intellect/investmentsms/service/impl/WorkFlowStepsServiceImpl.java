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
import com.intellect.investmentsms.domain.WorkFlowSteps;
import com.intellect.investmentsms.domain.WorkFlow;
import com.intellect.investmentsms.domain.WorkFlowSteps;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonCategoriesRepository;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.WorkFlowRepository;
import com.intellect.investmentsms.repository.WorkFlowStepsRepository;
import com.intellect.investmentsms.service.WorkFlowStepsService;
import com.intellect.investmentsms.service.dto.WorkFlowStepsDTO;
import com.intellect.investmentsms.service.dto.WorkFlowStepsDTO;
import com.intellect.investmentsms.service.mapper.WorkFlowStepsMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing {@link WorkFlowSteps}.
 */
@Service
@Transactional
public class WorkFlowStepsServiceImpl implements WorkFlowStepsService {

	@Autowired
	private WorkFlowRepository workFlowRepository;

	@Autowired
	private CommonCategoriesRepository commonCategoriesRepository;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	private final WorkFlowStepsRepository workFlowStepsRepository;

	private final WorkFlowStepsMapper workFlowStepsMapper;

	public WorkFlowStepsServiceImpl(WorkFlowStepsRepository workFlowStepsRepository,
			WorkFlowStepsMapper workFlowStepsMapper) {
		this.workFlowStepsRepository = workFlowStepsRepository;
		this.workFlowStepsMapper = workFlowStepsMapper;
	}

	@Override
	public WorkFlowStepsDTO save(WorkFlowStepsDTO workFlowStepsDTO) throws InvestmentsBusinessException {
		if (workFlowStepsDTO != null && workFlowStepsDTO.getName() != null) {
			duplicateCheckWorkFlowSteps(workFlowStepsDTO);
		}
		if (null != workFlowStepsDTO && workFlowStepsDTO.getId() != null) {
			WorkFlowSteps workFlowSteps = null;
			Optional<WorkFlowSteps> workFlowStepsOptional = workFlowStepsRepository.findById(workFlowStepsDTO.getId());
			if (workFlowStepsOptional.isPresent())
				workFlowSteps = workFlowStepsOptional.get();
			if (workFlowSteps.getCreatedBy() != null)
				workFlowStepsDTO.setCreatedBy(workFlowSteps.getCreatedBy());
			if (workFlowSteps.getCreatedOn() != null)
				workFlowStepsDTO.setCreatedOn(workFlowSteps.getCreatedOn());
		}
		WorkFlowSteps workFlowSteps = workFlowStepsMapper.toEntity(workFlowStepsDTO);
		workFlowSteps = workFlowStepsRepository.save(workFlowSteps);
		WorkFlowStepsDTO workFlowDto = workFlowStepsMapper.toDto(workFlowSteps);
		workFlowStepsRepository.save(workFlowSteps);
		WorkFlow workFlow = null;
		if (workFlowDto.getWorkFlowId() != null) {
			Optional<WorkFlow> workFlowOptional = workFlowRepository.findById(workFlowDto.getWorkFlowId().longValue());
			if (workFlowOptional.isPresent())
				workFlow = workFlowOptional.get();
			if (workFlowDto.getCategoryId() != null && workFlowDto.getWorkFlowId() != null) {
				List<WorkFlowStepsDTO> workFlowStepsDTOList = workFlowStepsMapper.toDto(
						workFlowStepsRepository.findByCategoryIdAndWorkFlowIdAndIsActive(workFlowDto.getCategoryId(),
								workFlowDto.getWorkFlowId(), ApplicationConstants.ACTIVE));
				if (workFlowStepsDTOList != null && !workFlowStepsDTOList.isEmpty()) {
					workFlow.setNoOfSteps(workFlowStepsDTOList.size());
				} else {
					workFlow.setNoOfSteps(ApplicationConstants.ZERO);
				}
			}
		}
		workFlowRepository.save(workFlow);
		return workFlowDto;
	}

	public void duplicateCheckWorkFlowSteps(WorkFlowStepsDTO workFlowStepsDTO) throws InvestmentsBusinessException {
		if (workFlowStepsDTO.getCategoryId() != null && workFlowStepsDTO.getCommonStatusId() != null
				&& workFlowStepsDTO.getWorkFlowId() != null) {
			List<WorkFlowSteps> workFlowStepsList = workFlowStepsRepository
					.findByCategoryIdAndCommonStatusIdAndWorkFlowIdAndIsActive(workFlowStepsDTO.getCategoryId(),
							workFlowStepsDTO.getCommonStatusId(), workFlowStepsDTO.getWorkFlowId(),
							ApplicationConstants.ACTIVE);
			if (workFlowStepsList != null && !workFlowStepsList.isEmpty()) {
				for (WorkFlowSteps workFlowSteps : workFlowStepsList) {
					if (null != workFlowSteps && workFlowSteps.getId() != workFlowStepsDTO.getId()) {
						throw new InvestmentsBusinessException(ApplicationConstants.WORKFLOW_STEPS_DUPLICATE);
					}
				}
			}
		}
	}

	/**
	 * Get all the WorkFlowSteps.
	 *
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<WorkFlowStepsDTO> findAll() {
		List<WorkFlowStepsDTO> workFlowStepsDTOList = workFlowStepsRepository.findAll().stream()
				.map(workFlowStepsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));

		if (workFlowStepsDTOList != null && !workFlowStepsDTOList.isEmpty()) {

			for (WorkFlowStepsDTO workFlowStepsDTO : workFlowStepsDTOList) {
				if (workFlowStepsDTO.getId() != null) {
					if (workFlowStepsDTO.getIsActive() == ApplicationConstants.ACTIVE) {
						workFlowStepsDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
					} else {
						workFlowStepsDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
					}
				}
				if (workFlowStepsDTO.getCommonStatusId() != null) {
					CommonStatus commonStatus = null;
					Optional<CommonStatus> commonStatusOptional = commonStatusRepository
							.findById(workFlowStepsDTO.getCommonStatusId().longValue());
					if (commonStatusOptional.isPresent())
						commonStatus = commonStatusOptional.get();
					if (commonStatus != null && commonStatus.getName() != null) {
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
		}
		return workFlowStepsDTOList;
	}

	/**
	 * Get one WorkFlowSteps by id. b
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public WorkFlowStepsDTO findOne(Long id) {
		WorkFlowStepsDTO workFlowStepsDTO = null;
		Optional<WorkFlowSteps> optWorkFlowSteps = workFlowStepsRepository.findById(id);
		if (optWorkFlowSteps.isPresent())
			workFlowStepsDTO = workFlowStepsMapper.toDto(optWorkFlowSteps.get());
			if(null != workFlowStepsDTO && null != workFlowStepsDTO.getId()) {
				if(workFlowStepsDTO.getIsActive() == ApplicationConstants.ACTIVE) {
					workFlowStepsDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
				}else {
					workFlowStepsDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
				}
			}

		return workFlowStepsDTO;
	}

	/**
	 * Delete the WorkFlow by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) throws InvestmentsBusinessException {
		WorkFlowSteps workFlowSteps = null;
		Optional<WorkFlowSteps> workFlowStepsOptional = workFlowStepsRepository.findById(id);
		if (workFlowStepsOptional.isPresent())
			workFlowSteps = workFlowStepsOptional.get();
		if (null != workFlowSteps && workFlowSteps.getId() != null) {
			List<WorkFlowSteps> workFlowStepsList = workFlowStepsRepository.findByPreviousStepAndWorkFlowIdAndIsActive(
					workFlowSteps.getId().intValue(), workFlowSteps.getWorkFlowId(), workFlowSteps.getIsActive());
			if (null != workFlowStepsList && !workFlowStepsList.isEmpty()) {
				throw new InvestmentsBusinessException(ApplicationConstants.STEP_ACTIVE_IN_ANOTHER);
			} else {
				workFlowSteps.setIsActive(ApplicationConstants.IN_ACTIVE);
				workFlowStepsRepository.save(workFlowSteps);
			}
		}
		WorkFlow workFlow = null;
		Optional<WorkFlow> workFlowOptional = workFlowRepository.findById(workFlowSteps.getWorkFlowId().longValue());
		if (workFlowOptional.isPresent())
			workFlow = workFlowOptional.get();
		List<WorkFlowStepsDTO> workFlowStepsDTOList = workFlowStepsMapper
				.toDto(workFlowStepsRepository.findByCategoryIdAndWorkFlowIdAndIsActive(workFlowSteps.getCategoryId(),
						workFlowSteps.getWorkFlowId(), ApplicationConstants.ACTIVE));
		if (workFlowStepsDTOList != null && !workFlowStepsDTOList.isEmpty()) {
			workFlow.setNoOfSteps(workFlowStepsDTOList.size());
		} else {
			workFlow.setNoOfSteps(ApplicationConstants.ZERO);
		}
		workFlowRepository.save(workFlow);

	}
	 /**
	 *@implNote: get  work flow steps
	 * @param id,isException
	 * @return WorkFlowStepsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public WorkFlowStepsDTO getInitWorkFlowSteps(Long id, Integer isException)
			throws InvestmentsBusinessException {
		return workFlowStepsMapper
				.toDto(workFlowStepsRepository.findByCategoryIdAndPreviousStepAndIsExceptional(id, 0, isException));
	}
	 /**
	 *@implNote: get  work flow steps by id
	 * @param id
	 * @return List<WorkFlowStepsDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public List<WorkFlowStepsDTO> getNextWorkFlowSteps(Integer id) throws InvestmentsBusinessException {
		WorkFlowStepsDTO dto = workFlowStepsMapper
				.toDto(workFlowStepsRepository.findByCommonStatusIdAndIsActive(id, ApplicationConstants.ACTIVE));
		return workFlowStepsMapper.toDto(workFlowStepsRepository.findByPreviousStep(dto.getId().intValue()));
	}
	
	
	 /**
	 *@implNote: get next level work flow steps
	 * @param previousStepId,level
	 * @return List<WorkFlowStepsDTO>
	 * @author LaxmiPrasannaKumar.S
	 */

	
	@Override
	public List<WorkFlowStepsDTO> getNextLevelWorkFlowSteps(Integer previousStepId, String level) {
		WorkFlowStepsDTO dto = workFlowStepsMapper.toDto(
				workFlowStepsRepository.findByCommonStatusIdAndIsActive(previousStepId, ApplicationConstants.ACTIVE));

		List<WorkFlowStepsDTO> returnDtoList = workFlowStepsMapper
				.toDto(workFlowStepsRepository.findByPreviousStepAndRelationshipType(previousStepId, level));
		for (WorkFlowStepsDTO workFlowStepsDTO : returnDtoList) {
			if (workFlowStepsDTO != null && workFlowStepsDTO.getCommonStatusId() != null) {
				CommonStatus commonStatus = null;
				Optional<CommonStatus> commonStatusOptional = commonStatusRepository
						.findById(workFlowStepsDTO.getCommonStatusId().longValue());
				if (commonStatusOptional.isPresent())
					commonStatus = commonStatusOptional.get();
				if (commonStatus != null) {
					workFlowStepsDTO.setStatusName(commonStatus.getName());
				}
			}
		}
		return returnDtoList;
	}
	
	
	 /**
	 *@implNote: get all work flow steps by status
	 * @param status,relation,isExceptional
	 * @return List<WorkFlowStepsDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
	
	
	@Override
	public List<WorkFlowStepsDTO> getNextWorkFlowStepsByStatus(Long status, String relation, Integer isExceptional)
			throws InvestmentsBusinessException {
		WorkFlowSteps workFlowSteps = workFlowStepsRepository.findByCommonStatusIdAndIsActive(status.intValue(),
				ApplicationConstants.ACTIVE);
		List<WorkFlowStepsDTO> workFlowStepsList = null;
		if (workFlowSteps != null) {
			workFlowStepsList = workFlowStepsMapper.toDto(workFlowStepsRepository
					.findByPreviousStepAndRelationshipTypeAndIsExceptionalAndIsActive(workFlowSteps.getId().intValue(),
							relation, workFlowSteps.getIsExceptional(), ApplicationConstants.ACTIVE));
			if (workFlowStepsList != null && !workFlowStepsList.isEmpty())
				for (WorkFlowStepsDTO workFlowStepsDTO : workFlowStepsList) {
					CommonStatus commonStatus = null;
					Optional<CommonStatus> commonStatusOptional = commonStatusRepository
							.findById(workFlowStepsDTO.getCommonStatusId().longValue());
					if (commonStatusOptional.isPresent())
						commonStatus = commonStatusOptional.get();
					if (commonStatus != null) {
						workFlowStepsDTO.setStatusName(commonStatus.getName());
					}
				}
		}
		return workFlowStepsList;
	}
	
	
	 /**
	 *@implNote: get all work flow steps by category and work flow
	 * @param categoryId,workFlowId
	 * @return List<WorkFlowStepsDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public List<WorkFlowStepsDTO> getWorkFlowStepsByCategoryAndWorkFlow(Long categoryId, Integer workFlowId) {

		List<WorkFlowStepsDTO> workFlowStepsDTOs = workFlowStepsMapper
				.toDto(workFlowStepsRepository.findByCategoryIdAndWorkFlowId(categoryId, workFlowId));
		return workFlowStepsDTOs;
	}
	 /**
	 *@implNote: get all work flow steps 
	 * @param categoryId,workFlowId,isActive
	 * @return List<WorkFlowStepsDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public List<WorkFlowStepsDTO> findAllWorkFlowSteps(Long categoryId, Integer workFlowId, Integer isActive)
			throws InvestmentsBusinessException {
		List<WorkFlowStepsDTO> workFlowStepsDTOList = workFlowStepsMapper.toDto(
				workFlowStepsRepository.findByCategoryIdAndWorkFlowIdAndIsActive(categoryId, workFlowId, isActive));
		if (workFlowStepsDTOList != null && !workFlowStepsDTOList.isEmpty()) {
			for (WorkFlowStepsDTO workFlowStepsDTO : workFlowStepsDTOList) {
				if (workFlowStepsDTO.getCommonStatusId() != null) {
					CommonStatus commonStatus = null;
					Optional<CommonStatus> commonStatusOptional = commonStatusRepository
							.findById(workFlowStepsDTO.getCommonStatusId().longValue());
					if (commonStatusOptional.isPresent())
						commonStatus = commonStatusOptional.get();
					if (commonStatus != null && commonStatus.getName() != null) {
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
					} else {
						workFlowStepsDTO.setPrevStepName(ApplicationConstants.NA);
					}
				} else {
					workFlowStepsDTO.setPrevStepName(ApplicationConstants.NA);
				}
				if (workFlowStepsDTO.getIsActive() == ApplicationConstants.ACTIVE) {
					workFlowStepsDTO.setStatusName(ApplicationConstants.ACTIVE_STATUS);
				} else {
					workFlowStepsDTO.setStatusName(ApplicationConstants.IN_ACTIVE_STATUS);
				}
				if (workFlowStepsDTO.getIsExceptional() != null
						&& workFlowStepsDTO.getIsExceptional() == ApplicationConstants.ACTIVE) {
					workFlowStepsDTO.setIsExceptionalName(ApplicationConstants.YES);
				} else {
					workFlowStepsDTO.setIsExceptionalName(ApplicationConstants.NO);
				}
				
			}
		}

		return workFlowStepsDTOList;
	}
	 /**
	 *@implNote: get work flow steps by work flow
	 * @param workFlowId
	 * @return List<WorkFlowStepsDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public List<WorkFlowStepsDTO> getWorkFlowStepsByWorkFlow(Integer workFlowId) {
		List<WorkFlowStepsDTO> workFlowStepsDTOs = workFlowStepsMapper
				.toDto(workFlowStepsRepository.findByWorkFlowId(workFlowId));
		if (workFlowStepsDTOs != null && !workFlowStepsDTOs.isEmpty()) {
			for (WorkFlowStepsDTO workFlowStepsDTO : workFlowStepsDTOs) {
				if (workFlowStepsDTO.getCommonStatusId() != null) {
					CommonStatus commonStatus = null;
					Optional<CommonStatus> commonStatusOptional = commonStatusRepository
							.findById(workFlowStepsDTO.getCommonStatusId().longValue());
					if (commonStatusOptional.isPresent())
						commonStatus = commonStatusOptional.get();
					if (commonStatus != null && commonStatus.getName() != null) {
						workFlowStepsDTO.setCommonStatusName(commonStatus.getName());
					}
				}
			}
		}
		return workFlowStepsDTOs;
	}
	 /**
	 *@implNote: this method for getting parent for orgchart
	 * @param id,workFlowId,categoryId
	 * @return WorkFlowSteps
	 * @author LaxmiPrasannaKumar.S
	 */
	// this method for getting parent for orgchart
	private WorkFlowSteps getParent(Integer id, Integer workFlowId, Long categoryId) {
		List<WorkFlowSteps> workFlowSteps = workFlowStepsRepository
				.findByPreviousStepAndWorkFlowIdAndIsActiveAndCategoryId(id, workFlowId, ApplicationConstants.ACTIVE,
						categoryId);
		if (workFlowSteps != null && !workFlowSteps.isEmpty()) {
			return workFlowSteps.get(0);
		}
		return null;
	}
	 /**
	 *@implNote: this method for getting orgchart
	 * @param workFlowId,categoryId
	 * @return WorkFlowStepsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	// this methos getting orgchart
	@Override
	public WorkFlowStepsDTO getWorkFlowOrgHeirarchy(Integer workFlowId, Long categoryId)
			throws InvestmentsBusinessException {
		WorkFlowSteps workFlowSteps = getParent(ApplicationConstants.ZERO, workFlowId, categoryId);
		WorkFlowStepsDTO dtoObj = workFlowStepsMapper.toDto(workFlowSteps);
		if (workFlowSteps == null) {
			throw new InvestmentsBusinessException(ApplicationConstants.NO_PARENT_RECORD);
		}
		getRecursiveTest(dtoObj);
		return dtoObj;

	}
	 /**
	 *@implNote: this method is for setting childerns for the parent
	 * @param obj
	 * @return void
	 * @author LaxmiPrasannaKumar.S
	 */
	// this method is for setting childerns for the parent
	private void getRecursiveTest(WorkFlowStepsDTO obj) {
		final List<WorkFlowSteps> children1 = this.workFlowStepsRepository
				.findByPreviousStepAndIsActive(obj.getId().intValue(), 1);
		final List<WorkFlowStepsDTO> childElements = new ArrayList<>();
		List<WorkFlowStepsDTO> children = workFlowStepsMapper.toDto(children1);
		for (final WorkFlowStepsDTO childtBranch : children) {
			childElements.add(childtBranch);
			getRecursiveTest(childtBranch);
		}
		obj.setChildren(childElements);
	}
	 /**
	 *@implNote: get the Work flow steps by status name
	 * @param statusName,relation,isExceptional
	 * @return List<WorkFlowStepsDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
	
	@Override
	public List<WorkFlowStepsDTO> getNextWorkFlowStepsByStatusName(String statusName, String relation,
			Integer isExceptional) throws InvestmentsBusinessException {
		List<WorkFlowStepsDTO> workFlowStepsList = null;
		if (statusName != null) {
			CommonStatus status = commonStatusRepository.findByName(statusName);
			if (status != null && status.getId() != null) {
				WorkFlowSteps workFlowSteps = workFlowStepsRepository
						.findByCommonStatusIdAndIsActive(status.getId().intValue(), ApplicationConstants.ACTIVE);
				workFlowStepsList = new ArrayList<WorkFlowStepsDTO>();
				if (workFlowSteps != null) {
					workFlowStepsList = workFlowStepsMapper.toDto(
							workFlowStepsRepository.findByPreviousStepAndRelationshipTypeAndIsExceptionalAndIsActive(
									workFlowSteps.getId().intValue(), relation, workFlowSteps.getIsExceptional(),
									ApplicationConstants.ACTIVE));
					if (workFlowStepsList != null && !workFlowStepsList.isEmpty())
						for (WorkFlowStepsDTO workFlowStepsDTO : workFlowStepsList) {
							CommonStatus commonStatus = null;
							Optional<CommonStatus> commonStatusOptional = commonStatusRepository
									.findById(workFlowStepsDTO.getCommonStatusId().longValue());
							if (commonStatusOptional.isPresent())
								commonStatus = commonStatusOptional.get();
							if (commonStatus != null) {
								workFlowStepsDTO.setStatusName(commonStatus.getName());
							}
						}
				}

			}
		}
		return workFlowStepsList;
	}
	 /**
	 *@implNote: get the Work flow steps by category name
	 * @param categoryName,isException
	 * @return WorkFlowStepsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	@Override
	public WorkFlowStepsDTO getInitWorkFlowStepsBycategoryName(String categoryName, Integer isException)
			throws InvestmentsBusinessException {
		WorkFlowStepsDTO dto = new WorkFlowStepsDTO();
		CommonCategories category = commonCategoriesRepository.findByName(categoryName);
		if (category != null && category.getId() != null) {
			dto = workFlowStepsMapper.toDto(workFlowStepsRepository
					.findByCategoryIdAndPreviousStepAndIsExceptional(category.getId(), 0, isException));
		}
		return dto;

	}


	
}
