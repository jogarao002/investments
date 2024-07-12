package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.DocumentTypesDTO;

/**
 * Service Interface for managing
 * {@link com.intellect.DocumentTypes.erp.domain.KycDocTypes}.
 */
public interface DocumentTypesService {
	/**
	 * Save a kycDocTypes.
	 *
	 * @param documentTypesDTO the entity to save.
	 * @return the persisted entity.
	 */
	DocumentTypesDTO save(DocumentTypesDTO documentTypesDTO) throws InvestmentsBusinessException;

	/**
	 * Get all the kycDocTypes.
	 *
	 * @return the list of entities.
	 */
	List<DocumentTypesDTO> findAll() throws InvestmentsBusinessException;

	/**
	 * Get the "id" kycDocTypes.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	DocumentTypesDTO findOne(Long id) throws InvestmentsBusinessException;

	/**
	 * Delete the "id" kycDocTypes.
	 *
	 * @param id the id of the entity.
	 */
	void delete(Long id) throws InvestmentsBusinessException;
}
