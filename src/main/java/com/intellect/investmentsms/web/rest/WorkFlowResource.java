package com.intellect.investmentsms.web.rest;

import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.intellect.investmentsms.service.WorkFlowService;
import com.intellect.investmentsms.service.WorkFlowStepsService;
import com.intellect.investmentsms.service.dto.WorkFlowDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;


/**
 * REST controller for managing {@link com.intellect.appraisal.domain.WorkFlow}.
 */
@RestController
@RequestMapping("/work_flow")
public class WorkFlowResource {

	private final WorkFlowService workFlowService;

	@Autowired
	private WorkFlowStepsService workFlowStepsService;

	public WorkFlowResource(WorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}

	/**
	 * {@code POST  /work-flows} : Create a new workFlow.
	 *
	 * @param workFlowDTO the workFlowDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new workFlowDTO, or with status {@code 400 (Bad Request)} if
	 *         the workFlow has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/add")
	public ResponseObject createWorkFlow(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody WorkFlowDTO workFlowDTO)
			throws URISyntaxException {
		List<WorkFlowDTO> data = null;
		WorkFlowDTO result = null;
		try {
			if (workFlowDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = workFlowService.save(workFlowDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.WORK_FLOW + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW +ApplicationConstants.CREATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code PUT  /work-flows} : Updates an existing workFlow.
	 *
	 * @param workFlowDTO the workFlowDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated workFlowDTO, or with status {@code 400 (Bad Request)} if
	 *         the workFlowDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the workFlowDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/update")
	public ResponseObject updateWorkFlow(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody WorkFlowDTO workFlowDTO)
			throws URISyntaxException {
		List<WorkFlowDTO> data = null;
		WorkFlowDTO result = null;
		try {
			if (workFlowDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = workFlowService.save(workFlowDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.WORK_FLOW + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW +ApplicationConstants.UPDATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code GET  /work-flows} : get all the workFlows.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of workFlows in body.
	 */
	@GetMapping("/get_all")
	public ResponseObject getAllWorkFlow(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {

		List<WorkFlowDTO> result = null;
		try {
			result = workFlowService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.WORK_FLOW + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW +ApplicationConstants.FETCH_RECORD_SUCESS, result);

	}

	/**
	 * {@code GET  /work-flows/:id} : get the "id" workFlow.
	 *
	 * @param id the id of the workFlowDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the workFlowDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/get")
	public ResponseObject getworkFlow(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
		List<WorkFlowDTO> data = null;
		try {
			WorkFlowDTO result = workFlowService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.WORK_FLOW + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW +ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

	/**
	 * {@code DELETE  /work-flows/:id} : delete the "id" workFlow.
	 *
	 * @param id the id of the workFlowDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/remove")
	public ResponseObject deleteWorkFlow(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) throws InvestmentsBusinessException {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			workFlowService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.WORK_FLOW + ApplicationConstants.DELETE_RECORD_FAILED, null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW +ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}
}
