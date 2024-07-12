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
import com.intellect.investmentsms.service.InvestmentsWorkFlowService;
import com.intellect.investmentsms.service.dto.InvestmentsWorkFlowDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

@RestController
@RequestMapping("/investments_work_flow_service")
public class InvestmentsWorkFlowResource {

	private final InvestmentsWorkFlowService investmentsWorkFlowService;

	public InvestmentsWorkFlowResource(InvestmentsWorkFlowService investmentWorkFlowService) {
		this.investmentsWorkFlowService = investmentWorkFlowService;
	}

	/**
	 * {@code POST  /add} : Save the InvestmentsWorkFlow by "investmentsWorkFlowDTO".
	 *@implNote: save the InvestmentsWorkFlow by "investmentsWorkFlowDTO".
	 * @param userid, authToken, investmentsWorkFlowDTO. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@PostMapping("/add")
	public synchronized ResponseObject createInvestmentsWorkFlow(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody InvestmentsWorkFlowDTO investmentsWorkFlowDTO) throws URISyntaxException {
		List<InvestmentsWorkFlowDTO> data = null;
		InvestmentsWorkFlowDTO result = null;
		try {
			if (investmentsWorkFlowDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = investmentsWorkFlowService.save(investmentsWorkFlowDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENTS_WORK_FLOW + ApplicationConstants.CREATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INVESTMENTS_WORK_FLOW + ApplicationConstants.CREATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code PUT  /update} : Update the InvestmentsWorkFlow by "investmentsWorkFlowDTO".
	 *@implNote: Update the InvestmentsWorkFlow by "investmentsWorkFlowDTO".
	 * @param userid, authToken, investmentsWorkFlowDTO. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@PutMapping("/update")
	public ResponseObject updateInvestmentsWorkFlow(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody InvestmentsWorkFlowDTO transationWorkFlowDTO)
			throws URISyntaxException {

		List<InvestmentsWorkFlowDTO> data = null;
		InvestmentsWorkFlowDTO result = null;
		try {
			if (transationWorkFlowDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = investmentsWorkFlowService.save(transationWorkFlowDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENTS_WORK_FLOW + ApplicationConstants.UPDATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INVESTMENTS_WORK_FLOW + ApplicationConstants.UPDATE_RECORD_SUCESS, data);
	}

	/**
	 * {@code Get  /get_all} : Get All the InvestmentsWorkFlows.
	 *@implNote: Get All the InvestmentsWorkFlows.
	 * @param userid, authToken. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@GetMapping("/get_all")
	public ResponseObject getAllInvestmentsWorkFlow(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {
		List<InvestmentsWorkFlowDTO> result = null;
		try {
			result = investmentsWorkFlowService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENTS_WORK_FLOW + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INVESTMENTS_WORK_FLOW + ApplicationConstants.FETCH_RECORD_SUCESS, result);
	}

	/**
	 * {@code Get  /get} : Get the InvestmentsWorkFlow by "id".
	 *@implNote: Get the InvestmentsWorkFlow by "id".
	 * @param userid, authToken, id. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@GetMapping("/get")
	public ResponseObject getInvestmentsWorkFlow(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {

		List<InvestmentsWorkFlowDTO> data = null;
		try {
			InvestmentsWorkFlowDTO result = investmentsWorkFlowService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENTS_WORK_FLOW + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INVESTMENTS_WORK_FLOW + ApplicationConstants.FETCH_RECORD_SUCESS, data);

	}

	/**
	 * {@code DELETE  /remove} : Delete the InvestmentsWorkFlow by "id".
	 *@implNote: Delete the InvestmentsWorkFlow by "id".
	 * @param userid, authToken, id. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@DeleteMapping("/remove")
	public ResponseObject deleteInvestmentsWorkFlow(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			investmentsWorkFlowService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENTS_WORK_FLOW + ApplicationConstants.DELETE_RECORD_FAILED + ","
							+ e.getMessage(),
					null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INVESTMENTS_WORK_FLOW + ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}
}
