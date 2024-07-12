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
import com.intellect.investmentsms.service.CommonStatusService;
import com.intellect.investmentsms.service.dto.CommonStatusDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing
 * {@link com.intellect.appraisal.domain.CommonStatus}.
 */
@RestController
@RequestMapping("/common_status")
public class CommonStatusResource {

	private final CommonStatusService commonStatusService;

	public CommonStatusResource(CommonStatusService commonStatusService) {
		this.commonStatusService = commonStatusService;
	}

	/**
	 * {@code 	POST /save} :
	 *@implNote: get the CommonStatus based on commonStatusDTO
	 * @param userid,authToken,commonStatusDTO.
	 * @return ResponseObject
	 */
	@PostMapping("/add")
	public ResponseObject createCommonStatus(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody CommonStatusDTO commonStatusDTO) throws URISyntaxException {
		List<CommonStatusDTO> data = null;
		CommonStatusDTO result = null;
		try {
			if (commonStatusDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = commonStatusService.save(commonStatusDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.COMMON_STATUS + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.COMMON_STATUS + ApplicationConstants.CREATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code 	PUT /update} :
	 *@implNote: get the CommonStatus based on commonStatusDTO
	 * @param userid,authToken,commonStatusDTO.
	 * @return ResponseObject
	 */
	@PutMapping("/update")
	public ResponseObject updateCommonStatus(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody CommonStatusDTO commonStatusDTO) throws URISyntaxException {
		List<CommonStatusDTO> data = null;
		CommonStatusDTO result = null;
		try {
			if (commonStatusDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = commonStatusService.save(commonStatusDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.COMMON_STATUS + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.COMMON_STATUS + ApplicationConstants.UPDATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code 	GET /get} :
	 *@implNote: get the CommonStatus
	 * @param userid,authToken
	 * @return ResponseObject
	 */
	@GetMapping("/get_all")
	public ResponseObject getAllCommonStatus(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {

		List<CommonStatusDTO> result = null;
		try {
			result = commonStatusService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.COMMON_STATUS + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.COMMON_STATUS + ApplicationConstants.FETCH_RECORD_SUCESS, result);

	}

	/**
	 * {@code 	GET /get} :
	 *@implNote: get the CommonStatus based on "id"
	 * @param userid,authToken,id
	 * @return ResponseObject
	 */
	@GetMapping("/get")
	public ResponseObject getCommonStatus(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
		List<CommonStatusDTO> data = null;
		try {
			CommonStatusDTO result = commonStatusService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.COMMON_STATUS + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.COMMON_STATUS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

	/**
	 * {@code 	DELETE /get} :
	 *@implNote: delete the CommonStatus based on "id"
	 * @param userid,authToken,id
	 * @return ResponseObject
	 */
	@DeleteMapping("/remove")
	public ResponseObject deleteCommonStatus(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) throws InvestmentsBusinessException {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			commonStatusService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.COMMON_STATUS + ApplicationConstants.DELETE_RECORD_FAILED, null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.COMMON_STATUS + ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}
	
}
