package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.WorkFlowStepsDTO;



/**
 * Service Interface for managing
 * {@link com.intellect.appraisal.domain.WorkFlowSteps}.
 */
public interface WorkFlowStepsService {

	/**
	 * Save a workFlowSteps.
	 *
	 * @param workFlowStepsDTO the entity to save.
	 * @return the persisted entity.
	 * @throws AppraisalBusinessException
	 */
	WorkFlowStepsDTO save(WorkFlowStepsDTO workFlowStepsDTO) throws InvestmentsBusinessException;

	/**
	 * Get all the workFlowSteps.
	 *
	 * @return the list of entities.
	 */
	List<WorkFlowStepsDTO> findAll() throws InvestmentsBusinessException;

	/**
	 * Get the "id" workFlowSteps.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	WorkFlowStepsDTO findOne(Long id) throws InvestmentsBusinessException;

	/**
	 * Delete the "id" workFlowSteps.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id) throws InvestmentsBusinessException;

	/**
	 *@implNote: get the In It work flow steps
	 * @param id, isExceptional
	 * @return WorkFlowStepsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	WorkFlowStepsDTO getInitWorkFlowSteps(Long id, Integer isExceptional) throws InvestmentsBusinessException;
	/**
	 *@implNote: get the next work flow steps
	 * @param id
	 * @return List<WorkFlowStepsDTO> 
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowStepsDTO> getNextWorkFlowSteps(Integer id) throws InvestmentsBusinessException;
	/**
	 *@implNote: get the next level work flow steps
	 * @param previousStepId,level
	 * @return List<WorkFlowStepsDTO> 
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowStepsDTO> getNextLevelWorkFlowSteps(Integer previousStepId, String level) throws InvestmentsBusinessException;
	/**
	 *@implNote: get the next work flow steps by status
	 * @param status,relationType,isExceptional
	 * @return List<WorkFlowStepsDTO> 
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowStepsDTO> getNextWorkFlowStepsByStatus(Long status, String relationType, Integer isExceptional)
			throws InvestmentsBusinessException;
	/**
	 *@implNote: get the work flow steps by category and work flow
	 * @param categoryId,workFLowId
	 * @return List<WorkFlowStepsDTO> 
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowStepsDTO> getWorkFlowStepsByCategoryAndWorkFlow(Long categoryId, Integer workFLowId)
			throws InvestmentsBusinessException;
	/**
	 *@implNote: get all work flow steps 
	 * @param categoryId,workFLowId,isActive
	 * @return List<WorkFlowStepsDTO> 
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowStepsDTO> findAllWorkFlowSteps(Long categoryId, Integer workFlowId, Integer isActive)
			throws InvestmentsBusinessException;
	/**
	 *@implNote: get all work flow steps by work flow 
	 * @param workFLowId
	 * @return List<WorkFlowStepsDTO> 
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowStepsDTO> getWorkFlowStepsByWorkFlow(Integer workFlowId) throws InvestmentsBusinessException;

//this method for orgchart
	/**
	 *@implNote: get  work flow heirarchy
	 * @param workFLowId,categoryId
	 * @return WorkFlowStepsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	WorkFlowStepsDTO getWorkFlowOrgHeirarchy(Integer workFlowId, Long categoryId) throws InvestmentsBusinessException;
	/**
	 *@implNote: get  work flow steps by status name
	 * @param statusName,relationType,isExceptional
	 * @return List<WorkFlowStepsDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowStepsDTO> getNextWorkFlowStepsByStatusName(String statusName, String relationType,
			Integer isExceptional) throws InvestmentsBusinessException;
	/**
	 *@implNote: get  work flow steps by category name
	 * @param categoryName,isExceptional
	 * @return WorkFlowStepsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	WorkFlowStepsDTO getInitWorkFlowStepsBycategoryName(String categoryName, Integer isException)
			throws InvestmentsBusinessException;

	
}
