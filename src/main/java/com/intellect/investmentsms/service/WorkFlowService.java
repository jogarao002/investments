package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.WorkFlowDTO;




/**
 * Service Interface for managing
 * {@link com.intellect.appraisal.domain.WorkFlow}.
 */
public interface WorkFlowService {

	/**
	 * Save a workFlow.
	 *
	 * @param workFlowDTO the entity to save.
	 * @return the persisted entity.
	 * @throws AppraisalBusinessException
	 */
	WorkFlowDTO save(WorkFlowDTO workFlowDTO) throws InvestmentsBusinessException;

	/**
	 * Get all the workFlows.
	 *
	 * @return the list of entities.
	 */
	List<WorkFlowDTO> findAll() throws InvestmentsBusinessException;

	/**
	 * Get the "id" workFlow.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	WorkFlowDTO findOne(Long id) throws InvestmentsBusinessException;

	/**
	 * Delete the "id" workFlow.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id) throws InvestmentsBusinessException;
}
