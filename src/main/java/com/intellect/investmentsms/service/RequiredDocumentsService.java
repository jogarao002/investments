package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.RequiredDocumentsDTO;

/**
 * Service Interface for managing
 * {@link com.intellect.investmentsms.domain.RequiredDocuments}.
 */
public interface RequiredDocumentsService {

	/**
	 * @implNote: Save a RequiredDocuments.
	 * @param requiredDocumentsDTO.
	 * @return RequiredDocumentsDTO.
	 * @author Dileep_Kumar.Gedela.
	 */
	RequiredDocumentsDTO save(RequiredDocumentsDTO requiredDocumentsDTO) throws InvestmentsBusinessException;

	/**
	 * @implNote: get all the RequiredDocuments.
	 * @param
	 * @return List<RequiredDocumentsDTO>.
	 * @author Dileep_Kumar.Gedela.
	 */
	List<RequiredDocumentsDTO> findAll() throws InvestmentsBusinessException;

	/**
	 * @implNote: get the RequiredDocuments based on id.
	 * @param id.
	 * @return RequiredDocumentsDTO.
	 * @author Dileep_Kumar.Gedela.
	 */
	RequiredDocumentsDTO findOne(Long id) throws InvestmentsBusinessException;

	/**
	 * @implNote: remove the RequiredDocuments based on id.
	 * @param id.
	 * @return
	 * @author Dileep_Kumar.Gedela
	 */
	void delete(Long id) throws InvestmentsBusinessException;

	/**
	 * @implNote: get all the RequiredDocuments based on productId.
	 * @param productId.
	 * @return List<RequiredDocumentsDTO>.
	 * @author Dileep_Kumar.Gedela.
	 */
	List<RequiredDocumentsDTO> findByProductId(Long productId) throws InvestmentsBusinessException;

	/**
	 * @implNote: get all the RequiredDocuments based on productId and
	 *            documentTypeId.
	 * @param productId,documentTypeId.
	 * @return List<RequiredDocumentsDTO>.
	 * @author Dileep_Kumar.Gedela.
	 */
	List<RequiredDocumentsDTO> findByProductIdAndDocumentTypeId(Long productId, Long documentTypeId)
			throws InvestmentsBusinessException;
}
