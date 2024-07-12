package com.intellect.investmentsms.web.rest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.DocumentTypesService;
import com.intellect.investmentsms.service.dto.DocumentTypesDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing
 * {@link com.intellect.DocumentTypes.erp.domain.KycDocTypes}.
 */
@RestController
@RequestMapping("/document_types")
public class DocumentTypesResource {

	private final DocumentTypesService documentTypesService;

	public DocumentTypesResource(DocumentTypesService documentTypesService) {
		this.documentTypesService = documentTypesService;
	}

	/**
	 * {@code POST  /kyc-doc-types} : Create a new kycDocTypes.
	 *
	 * @param documentTypesDTO the kycDocTypesDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new kycDocTypesDTO, or with status {@code 400 (Bad Request)}
	 *         if the kycDocTypes has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/add")
	public synchronized ResponseObject createDocumentTypes(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody DocumentTypesDTO documentTypesDTO)
			throws URISyntaxException {
		List<DocumentTypesDTO> data = null;
		DocumentTypesDTO result = null;
		try {
			if (documentTypesDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = documentTypesService.save(documentTypesDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.DOCUMENT_TYPES + ApplicationConstants.CREATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.DOCUMENT_TYPES + ApplicationConstants.CREATE_RECORD_SUCESS, data);
	}

	/**
	 * {@code PUT  /kyc-documents-types/:id} : Updates an existing
	 * kYCDocumentsTypes.
	 *
	 * @param id                   the id of the kYCDocumentsTypesDTO to save.
	 * @param kYCDocumentsTypesDTO the kYCDocumentsTypesDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated kYCDocumentsTypesDTO, or with status
	 *         {@code 400 (Bad Request)} if the kYCDocumentsTypesDTO is not valid,
	 *         or with status {@code 500 (Internal Server Error)} if the
	 *         kYCDocumentsTypesDTO couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/update")
	public synchronized ResponseObject updateDocumentTypes(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody DocumentTypesDTO documentTypesDTO)
			throws URISyntaxException {

		List<DocumentTypesDTO> data = null;
		DocumentTypesDTO result = null;
		try {
			if (documentTypesDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = documentTypesService.save(documentTypesDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.DOCUMENT_TYPES + ApplicationConstants.UPDATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.DOCUMENT_TYPES + ApplicationConstants.UPDATE_RECORD_SUCESS, data);
	}

	/**
	 * {@code GET  /kyc-documents-types} : get all the kYCDocumentsTypes.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of kYCDocumentsTypes in body.
	 */
	@GetMapping("/get_all")
	public ResponseObject getAllDocumentTypes(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {
		List<DocumentTypesDTO> result = null;
		try {
			result = documentTypesService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.DOCUMENT_TYPES + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.DOCUMENT_TYPES + ApplicationConstants.FETCH_RECORD_SUCESS, result);
	}

	/**
	 * {@code GET  /kyc-documents-types/:id} : get the "id" kYCDocumentsTypes.
	 *
	 * @param id the id of the kYCDocumentsTypesDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the kYCDocumentsTypesDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("get")
	public ResponseObject getDocumentTypes(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {

		List<DocumentTypesDTO> data = null;
		try {
			DocumentTypesDTO result = documentTypesService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.DOCUMENT_TYPES + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.DOCUMENT_TYPES + ApplicationConstants.FETCH_RECORD_SUCESS, data);

	}

	/**
	 * {@code DELETE  /kyc-documents-types/:id} : delete the "id" kYCDocumentsTypes.
	 *
	 * @param id the id of the kYCDocumentsTypesDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/remove")
	public ResponseObject deleteDocumentTypes(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			documentTypesService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.DOCUMENT_TYPES + ApplicationConstants.DELETE_RECORD_FAILED + ","
							+ e.getMessage(),
					null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.DOCUMENT_TYPES + ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}

}
