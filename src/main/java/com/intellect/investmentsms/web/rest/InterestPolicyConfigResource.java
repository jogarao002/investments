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
import com.intellect.investmentsms.service.InterestPolicyConfigService;
import com.intellect.investmentsms.service.dto.InterestPolicyConfigDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

@RestController
@RequestMapping("interest_policy_configs")
public class InterestPolicyConfigResource {

	private final InterestPolicyConfigService interestPolicyConfigService;

	public InterestPolicyConfigResource(InterestPolicyConfigService interestPolicyConfigService) {
		this.interestPolicyConfigService = interestPolicyConfigService;
	}

	/**
	 * {@code POST  /add} : Save the InterestPolicyConfig by "InterestPolicyConfigDTO".
	 *@implNote: save the InterestPolicyConfig by "InterestPolicyConfigDTO".
	 * @param userid, authToken, InterestPolicyConfigDTO. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@PostMapping("/add")
	public ResponseObject createInterestPolicyConfig(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody InterestPolicyConfigDTO interestPolicyConfigDTO)
			throws URISyntaxException {
		List<InterestPolicyConfigDTO> data = null;
		InterestPolicyConfigDTO result = null;
		try {
			if (interestPolicyConfigDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = interestPolicyConfigService.save(interestPolicyConfigDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.CREATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.CREATE_RECORD_SUCESS, data);
	}

	/**
	 * {@code PUT  /update} : Update the InterestPolicyConfig by "InterestPolicyConfigDTO".
	 *@implNote: Update the InterestPolicyConfig by "InterestPolicyConfigDTO".
	 * @param userid, authToken, InterestPolicyConfigDTO. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@PutMapping("/update")
	public ResponseObject updateInterestPolicyConfig(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody InterestPolicyConfigDTO interestPolicyConfigDTO)
			throws URISyntaxException {
		List<InterestPolicyConfigDTO> data = null;
		InterestPolicyConfigDTO result = null;
		try {
			if (interestPolicyConfigDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = interestPolicyConfigService.save(interestPolicyConfigDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.UPDATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.UPDATE_RECORD_SUCESS, data);
	}

	/**
	 * {@code GET  /get_all} : Get all the InterestPolicyConfig.
	 *@implNote: Get all the InterestPolicyConfig.
	 * @param userid, authToken.
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@GetMapping("/get_all")
	public ResponseObject getAllInterestPolicyConfigs(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {
		List<InterestPolicyConfigDTO> result = null;
		try {
			result = interestPolicyConfigService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.FETCH_RECORD_SUCESS, result);
	}

	/**
	 * {@code GET  /get} : Get the InterestPolicyConfig by "id".
	 *@implNote: Get the InterestPolicyConfig by "id".
	 * @param userid, authToken, id. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@GetMapping("/get")
	public ResponseObject getInterestPolicyConfig(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		List<InterestPolicyConfigDTO> data = null;
		try {
			InterestPolicyConfigDTO result = interestPolicyConfigService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.FETCH_RECORD_SUCESS, data);

	}

	/**
	 * {@code DELETE  /remove} : Delete the InterestPolicyConfig by "id".
	 *@implNote: Delete the InterestPolicyConfig by "id".
	 * @param userid, authToken, id. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@DeleteMapping("/remove")
	public ResponseObject deleteInterestPolicyConfig(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			interestPolicyConfigService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.DELETE_RECORD_FAILED + ","
							+ e.getMessage(),
					null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}
	
	/**
	 * {@code GET  /get_interest_policy_config_by_product_id} : Get the InterestPolicyConfig by "productid".
	 *@implNote: Get the InterestPolicyConfig by "productid".
	 * @param userid, authToken, productid. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@GetMapping("/get_interest_policy_config_by_product_id")
	public ResponseObject getInterestPolicyConfigByProductId(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long productid) {
		List<InterestPolicyConfigDTO> data = null;
		try {
			data = interestPolicyConfigService.getInterestPolicyConfigByProductId(productid);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.FETCH_RECORD_FAILED + ", "
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INTEREST_POLICY_CONFIG + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
}
