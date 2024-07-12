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
import com.intellect.investmentsms.repository.PenaltyConfigRepository;
import com.intellect.investmentsms.service.PenaltyConfigService;
import com.intellect.investmentsms.service.dto.PenaltyConfigDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing
 * {@link com.intellect.investmentsms.domain.PenaltyConfig}.
 */
@RestController
@RequestMapping("/penalty_configs")
public class PenaltyConfigResource {

	private final PenaltyConfigService penaltyConfigService;

	private final PenaltyConfigRepository penaltyConfigRepository;

	public PenaltyConfigResource(PenaltyConfigService penaltyConfigService,
			PenaltyConfigRepository penaltyConfigRepository) {
		this.penaltyConfigService = penaltyConfigService;
		this.penaltyConfigRepository = penaltyConfigRepository;
	}

	/**
	 * {@code POST  /add} :
	 * @implNote save the PenaltyConfig
	 * @param userid, authToken, penaltyConfigDTO.
	 * @return ResponseObject.
	 * @author Dileep_kumar.Gedela
	 */
	@PostMapping("/add")
	public ResponseObject createPenaltyConfig(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody PenaltyConfigDTO penaltyConfigDTO)
			throws InvestmentsBusinessException {
		List<PenaltyConfigDTO> data = null;
		PenaltyConfigDTO result = null;
		try {
			if (penaltyConfigDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = penaltyConfigService.save(penaltyConfigDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.CREATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.CREATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code PUT  /update} :
	 * @implNote update the penaltyConfig
	 * @param userid, authToken, penaltyConfigDTO.
	 * @return ResponseObject.
	 * @author Dileep_kumar.Gedela
	 */
	@PutMapping("/update")
	public ResponseObject updatePenaltyConfig(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody PenaltyConfigDTO penaltyConfigDTO)
			throws URISyntaxException {
		List<PenaltyConfigDTO> data = null;
		PenaltyConfigDTO result = null;
		try {
			if (penaltyConfigDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = penaltyConfigService.save(penaltyConfigDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.UPDATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.UPDATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code GET  /get_all} :
	 * @implNote get all the penaltyConfigs
	 * @param userid, authToken.
	 * @return ResponseObject.
	 * @author Dileep_kumar.Gedela
	 */
	@GetMapping("/get_all")
	public ResponseObject getAllPenaltyConfig(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {

		List<PenaltyConfigDTO> result = null;
		try {
			result = penaltyConfigService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.FETCH_RECORD_SUCESS, result);

	}

	/**
	 * {@code GET  /get} :
	 * @implNote get the penaltyConfig
	 * @param userid, authToken, id.
	 * @return ResponseObject.
	 * @author Dileep_kumar.Gedela
	 */
	@GetMapping("/get")
	public ResponseObject getPenaltyConfig(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		List<PenaltyConfigDTO> data = null;
		try {
			PenaltyConfigDTO result = penaltyConfigService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.FETCH_RECORD_FAILED + ", "
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

	/**
   	 * {@code DELETE  /remove} :
   	 *@implNote: remove the PenaltyConfig based on "id".
   	 * @param userid,authToken,id
   	 * @return ResponseObject
   	 * @author Dileep_kumar.Gedela
   	 */
	@DeleteMapping("/remove")
	public ResponseObject deletePenaltyConfig(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id)
			throws InvestmentsBusinessException {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			penaltyConfigService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.DELETE_RECORD_FAILED + ", "
							+ e.getMessage(),
					null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}
	
	/**
	 * {@code GET  /get_by_produc_id} :
	 * @implNote: get the PenaltyConfig by "productId"
	 * @param userid, authToken, productId.
	 * @return ResponseObject.
	 * @author Dileep_kumar.Gedela
	 */
	@GetMapping("/get_by_produc_id")
	public ResponseObject getByProductId(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long productId){

		List<PenaltyConfigDTO> result = null;
		try {
			result = penaltyConfigService.findByProductId(productId);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PENALTY_CONFIG + ApplicationConstants.FETCH_RECORD_SUCESS, result);

	}
}
