package com.intellect.investmentsms.repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.WorkFlowSteps;

/**
 * Spring Data  repository for the WorkFlowSteps entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkFlowStepsRepository extends JpaRepository<WorkFlowSteps, Long> {

	List<WorkFlowSteps> findByWorkFlowIdAndIsActive(int intValue, Integer active);

	List<WorkFlowSteps> findByCategoryIdAndCommonStatusIdAndWorkFlowIdAndIsActive(Long categoryId, Integer cmnStatusId,
			Integer workFlowId, Integer active);

	List<WorkFlowSteps> findByCategoryIdAndWorkFlowIdAndIsActive(Long categoryId, Integer workFlowId, Integer isActive);

	List<WorkFlowSteps> findByPreviousStepAndWorkFlowIdAndIsActive(int intValue, Integer workFlowId, Integer isActive);
	/**
	 *@implNote: get the work flow  by work flow id 
	 * @param intValue 
	 * @return List<WorkFlowSteps>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowSteps> findByWorkFlowId(int intValue);
	/**
	 *@implNote: get the work flow  by work flow id and category id
	 * @param categoryId, workFlowId
	 * @return List<WorkFlowSteps>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowSteps> findByCategoryIdAndWorkFlowId(Long categoryId, Integer workFlowId);
	/**
	 *@implNote: get the work flow  by  category id,previous step and isExceptional
	 * @param id, i,isException
	 * @return WorkFlowSteps
	 * @author LaxmiPrasannaKumar.S
	 */
	WorkFlowSteps findByCategoryIdAndPreviousStepAndIsExceptional(Long id, int i, Integer isException);
	/**
	 *@implNote: get the work flow  by name
	 * @param name
	 * @return List<WorkFlowSteps>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowSteps> findByName(String name);
/**
 *@implNote: get the work flow  by workflow id
 * @param workFlowId
 * @return List<WorkFlowSteps>
 * @author LaxmiPrasannaKumar.S
 */
	List<WorkFlowSteps> findByWorkFlowId(Integer workFlowId);
	/**
	 *@implNote: get the work flow  by category id ,previous step and is exceptional
	 * @param categoryId,previousStepId,isException
	 * @return WorkFlowSteps
	 * @author LaxmiPrasannaKumar.S
	 */
	WorkFlowSteps findByCategoryIdAndPreviousStepAndIsExceptional(Long categoryId, Integer previousStepId , Integer isException);
	/**
	 *@implNote: get the work flow  by previous step 
	 * @param id
	 * @return List<WorkFlowSteps>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowSteps> findByPreviousStep(Integer id);
	/**
	 *@implNote: get the work flow  by previous step and relationship type
	 * @param previousStepId ,level
	 * @return List<WorkFlowSteps>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowSteps> findByPreviousStepAndRelationshipType(Integer previousStepId, String level);

//	WorkFlowSteps findByCmnStatusId(Integer id);
	
//	List<WorkFlowSteps> findByPreviousStepAndRelationshipTypeAndIsExceptional(Integer step, String relation,
//		Integer isExceptional);
	/**
	 *@implNote: get the work flow  by categoryId
	 * @param categoryId
	 * @return List<WorkFlowSteps>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowSteps> findByCategoryId(Long categoryId);
	/**
	 *@implNote: get the work flow  by previous step ,work flow id and isActive
	 * @param intValue ,workFlowId,isActive
	 * @return List<WorkFlowSteps>
	 * @author LaxmiPrasannaKumar.S
	 */
	
	List<WorkFlowSteps> findByPreviousStepAndWorkFlowIdAndIsActive(Integer intValue, Integer workFlowId, Integer isActive);

	/**
	 *@implNote: get the work flow by previous step ,relation ship type ,isExceptional and isActive
	 * @param intValue ,relation,isExceptional,isActive
	 * @return List<WorkFlowSteps>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowSteps> findByPreviousStepAndRelationshipTypeAndIsExceptionalAndIsActive(Integer intValue, String relation,
		Integer isExceptional, Integer active);
//for orgchat parent
	/**
	 *@implNote: get the work flow by previous step and isActive
	 * @param id ,active
	 * @return List<WorkFlowSteps>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowSteps> findByPreviousStepAndIsActive(Integer id, Integer active);
	/**
	 *@implNote: get the work flow by previous step,workflow id,category id  and isActive
	 * @param id,workFlowId ,active,categoryId
	 * @return List<WorkFlowSteps>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<WorkFlowSteps> findByPreviousStepAndWorkFlowIdAndIsActiveAndCategoryId(Integer id, Integer workFlowId,
			Integer active, Long categoryId);
	/**
	 *@implNote: get the work flow by category status  and isActive
	 * @param id,active
	 * @return WorkFlowSteps
	 * @author LaxmiPrasannaKumar.S
	 */
	WorkFlowSteps findByCommonStatusIdAndIsActive(Integer id, Integer active);
}
