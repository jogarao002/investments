package com.intellect.investmentsms.web.rest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.RequiredDocumentsRepository;
import com.intellect.investmentsms.service.RequiredDocumentsService;
import com.intellect.investmentsms.service.dto.RequiredDocumentsDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing
 * {@link com.intellect.investmentsms.domain.RequiredDocuments}.
 */
@RestController
@RequestMapping("/required_documents")
public class RequiredDocumentsResource {

	private final RequiredDocumentsService requiredDocumentsService;

	private final RequiredDocumentsRepository requiredDocumentsRepository;

	public RequiredDocumentsResource(RequiredDocumentsService requiredDocumentsService,
			RequiredDocumentsRepository requiredDocumentsRepository) {
		this.requiredDocumentsService = requiredDocumentsService;
		this.requiredDocumentsRepository = requiredDocumentsRepository;
	}

	/**
	 * {@code POST  /add} :
	 * @implNote save the RequiredDocuments
	 * @param userid, authToken, requiredDocumentsDTO.
	 * @return ResponseObject.
	 * @author Dileep_kumar.Gedela
	 */
	@PostMapping("/add")
	public ResponseObject createRequiredDocuments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody RequiredDocumentsDTO requiredDocumentsDTO)
			throws InvestmentsBusinessException {
		List<RequiredDocumentsDTO> data = null;
		RequiredDocumentsDTO result = null;
		try {
			if (requiredDocumentsDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = requiredDocumentsService.save(requiredDocumentsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.CREATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.CREATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code PUT  /update} :
	 * @implNote update the RequiredDocuments
	 * @param userid, authToken, requiredDocumentsDTO.
	 * @return ResponseObject.
	 * @author Dileep_kumar.Gedela
	 */
	@PutMapping("/update")
	public ResponseObject updateRequiredDocuments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody RequiredDocumentsDTO requiredDocumentsDTO)
			throws URISyntaxException {
		List<RequiredDocumentsDTO> data = null;
		RequiredDocumentsDTO result = null;
		try {
			if (requiredDocumentsDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = requiredDocumentsService.save(requiredDocumentsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.UPDATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.UPDATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code GET  /get_all} :
	 * @implNote get all the RequiredDocuments
	 * @param userid, authToken.
	 * @return ResponseObject.
	 * @author Dileep_kumar.Gedela
	 */
	@GetMapping("/get_all")
	public ResponseObject getAllRequiredDocuments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {

		List<RequiredDocumentsDTO> result = null;
		try {
			result = requiredDocumentsService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.FETCH_RECORD_SUCESS, result);

	}

	/**
	 * {@code GET  /get} :
	 * @implNote get the RequiredDocuments
	 * @param userid, authToken, id.
	 * @return ResponseObject.
	 * @author Dileep_kumar.Gedela
	 */
	@GetMapping("/get")
	public ResponseObject getRequiredDocuments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		List<RequiredDocumentsDTO> data = null;
		try {
			RequiredDocumentsDTO result = requiredDocumentsService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.FETCH_RECORD_FAILED + ", "
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

	/**
	 * {@code DELETE  /remove} :
	 * 
	 * @implNote: remove the RequiredDocuments based on "id".
	 * @param userid,authToken,id
	 * @return ResponseObject
	 * @author Dileep_kumar.Gedela
	 */
	@DeleteMapping("/remove")
	public ResponseObject deleteRequiredDocuments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id)
			throws InvestmentsBusinessException {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			requiredDocumentsService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.DELETE_RECORD_FAILED + ", "
							+ e.getMessage(),
					null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}

	/**
	 * {@code GET  /get_by_produc_id} :
	 * @implNote: get the RequiredDocuments based on "productId"
	 * @param userid, authToken, productId.
	 * @return ResponseObject.
	 * @author Dileep_kumar.Gedela
	 */
	@GetMapping("/get_by_produc_id")
	public ResponseObject getByProductId(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long productId) {

		List<RequiredDocumentsDTO> result = null;
		try {
			result = requiredDocumentsService.findByProductId(productId);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.FETCH_RECORD_SUCESS, result);

	}

	/**
	 * {@code GET  /get_by_product_id_and_document_type_id} :
	 * @implNote: get the RequiredDocuments based on "productId" and "documentTypeId".
	 * @param userid, authToken, productId,documentTypeId.
	 * @return ResponseObject.
	 * @author Dileep_kumar.Gedela
	 */
	@GetMapping("/get_by_product_id_and_document_type_id")
	public ResponseObject getByProductIdAndDocumentTypeId(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long productId,
			@RequestHeader(required = true) Long documentTypeId) {

		List<RequiredDocumentsDTO> result = null;
		try {
			result = requiredDocumentsService.findByProductIdAndDocumentTypeId(productId, documentTypeId);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.REQUIRED_DOCUMENTS + ApplicationConstants.FETCH_RECORD_SUCESS, result);

	}
}
