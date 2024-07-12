package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.CommonStatusDTO;

/**
 * Service Interface for managing
 * {@link com.intellect.appraisal.domain.CommonStatus}.
 */
public interface CommonStatusService {

	/**
	 * Save a commonStatus.
	 *
	 * @param commonStatusDTO the entity to save.
	 * @return the persisted entity.
	 * @throws AppraisalBusinessException
	 */
	CommonStatusDTO save(CommonStatusDTO commonStatusDTO) throws InvestmentsBusinessException;

	/**
	 * Get all the commonStatuses.
	 *
	 * @return the list of entities.
	 */
	List<CommonStatusDTO> findAll() throws InvestmentsBusinessException;

	/**
	 * Get the "id" commonStatus.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	CommonStatusDTO findOne(Long id) throws InvestmentsBusinessException;

	/**
	 * Delete the "id" commonStatus.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id) throws InvestmentsBusinessException;
	
}
