package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.CommonCategoriesDTO;

/**
 * Service Interface for managing
 * {@link com.intellect.appraisal.domain.CommonCategories}.
 */
public interface CommonCategoriesService {

	/**
	 * Save a commonCategories.
	 *
	 * @param commonCategoriesDTO the entity to save.
	 * @return the persisted entity.
	 * @throws AppraisalBusinessException
	 */
	CommonCategoriesDTO save(CommonCategoriesDTO commonCategoriesDTO) throws InvestmentsBusinessException;

	/**
	 * Get all the commonCategories.
	 *
	 * @return the list of entities.
	 */
	List<CommonCategoriesDTO> findAll() throws InvestmentsBusinessException;

	/**
	 * Get the "id" commonCategories.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	CommonCategoriesDTO findOne(Long id) throws InvestmentsBusinessException;

	/**
	 * Delete the "id" commonCategories.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id) throws InvestmentsBusinessException;

}
