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
import com.intellect.investmentsms.service.WorkFlowStepsService;
import com.intellect.investmentsms.service.dto.WorkFlowStepsDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;
/**
 * REST controller for managing
 * {@link com.intellect.appraisal.domain.WorkFlowSteps}.
 */
@RestController
@RequestMapping("/work_flow_steps")
public class WorkFlowStepsResource {

	private final WorkFlowStepsService workFlowStepsService;

	public WorkFlowStepsResource(WorkFlowStepsService workFlowStepsService) {
		this.workFlowStepsService = workFlowStepsService;
	}

	/**
	 * {@code POST  /work-flow-steps} : Create a new workFlowSteps.
	 *
	 * @param workFlowStepsDTO the workFlowStepsDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new workFlowStepsDTO, or with status
	 *         {@code 400 (Bad Request)} if the workFlowSteps has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/add")
	public ResponseObject createWorkFlowSteps(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody WorkFlowStepsDTO workFlowStepsDTO) throws URISyntaxException {
		List<WorkFlowStepsDTO> data = null;
		WorkFlowStepsDTO result = null;
		try {
			if (workFlowStepsDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = workFlowStepsService.save(workFlowStepsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.WORK_FLOW_STEPS + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.CREATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code PUT  /work-flow-steps} : Updates an existing workFlowSteps.
	 *
	 * @param workFlowStepsDTO the workFlowStepsDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated workFlowStepsDTO, or with status
	 *         {@code 400 (Bad Request)} if the workFlowStepsDTO is not valid, or
	 *         with status {@code 500 (Internal Server Error)} if the
	 *         workFlowStepsDTO couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/update")
	public ResponseObject updateWorkFlowSteps(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody WorkFlowStepsDTO workFlowStepsDTO) throws URISyntaxException {
		List<WorkFlowStepsDTO> data = null;
		WorkFlowStepsDTO result = null;
		try {
			if (workFlowStepsDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = workFlowStepsService.save(workFlowStepsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.WORK_FLOW_STEPS + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.UPDATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code GET  /work-flow-steps} : get all the workFlowSteps.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of workFlowSteps in body.
	 */
	@GetMapping("/get_all")
	public ResponseObject getAllWorkFlowSteps(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {

		List<WorkFlowStepsDTO> result = null;
		try {
			result = workFlowStepsService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.WORK_FLOW_STEPS + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.FETCH_RECORD_SUCESS, result);

	}

	/**
	 * {@code GET  /work-flow-steps/:id} : get the "id" workFlowSteps.
	 *
	 * @param id the id of the workFlowStepsDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the workFlowStepsDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/get")
	public ResponseObject getworkFlowSteps(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
		List<WorkFlowStepsDTO> data = null;
		try {
			WorkFlowStepsDTO result = workFlowStepsService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.WORK_FLOW_STEPS + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

	/**
	 * {@code DELETE  /work-flow-steps/:id} : delete the "id" workFlowSteps.
	 *
	 * @param id the id of the workFlowStepsDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/remove")
	public ResponseObject deleteWorkFlowSteps(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) throws InvestmentsBusinessException {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			workFlowStepsService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.WORK_FLOW_STEPS + ApplicationConstants.DELETE_RECORD_FAILED, null);
		}catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}
	
	 /**
   	 * {@code GET /get_init_status} :
   	 *@implNote: get work flow steps by "id and isExceptional"
   	 * @param userid,authToken,id,isExceptional
   	 * @return ResponseObject
   	 * @author LaxmiPrasannaKumar.S
   	 */
	@GetMapping("/get_init_status")
	public ResponseObject getInitWorkFlowSteps(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id,
			@RequestHeader(required = true) Integer isExceptional) {
		List<WorkFlowStepsDTO> data = null;
		try {
			WorkFlowStepsDTO result = workFlowStepsService.getInitWorkFlowSteps(id, isExceptional);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
	/**
   	 * {@code GET /get_next_status} :
   	 *@implNote: get next work flow steps by "id "
   	 * @param userid,authToken,id
   	 * @return ResponseObject
   	 * @author LaxmiPrasannaKumar.S
   	 */
	@GetMapping("/get_next_status")
	public ResponseObject getNextWorkFlowSteps(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Integer id) {
		List<WorkFlowStepsDTO> data = new ArrayList<>();
		try {
			data = workFlowStepsService.getNextWorkFlowSteps(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
	/**
   	 * {@code GET /get_next_status_level} :
   	 *@implNote: get next level work flow steps by "previousStepId and  level"
   	 * @param userid,authToken,previousStepId,level
   	 * @return ResponseObject
   	 * @author LaxmiPrasannaKumar.S
   	 */
	@GetMapping("/get_next_status_level")
	public ResponseObject getNextLevelWorkFlowSteps(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Integer previousStepId,
			@RequestHeader(required = true) String level) {
		List<WorkFlowStepsDTO> data = new ArrayList<>();
		try {
			data = workFlowStepsService.getNextLevelWorkFlowSteps(previousStepId, level);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
	/**
   	 * {@code GET /get_next_status_by_status} :
   	 *@implNote: get next work flow steps by status "id,relationType and  isExceptional"
   	 * @param userid,authToken,id,relationType,isExceptional
   	 * @return ResponseObject
   	 * @author LaxmiPrasannaKumar.S
   	 */
	@GetMapping("/get_next_status_by_status")
	public ResponseObject getNextWorkFlowStepsByStatus(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id,
			@RequestHeader(required = true) String relationType,
			@RequestHeader(required = true) Integer isExceptional) {
		List<WorkFlowStepsDTO> data = new ArrayList<>();
		try {
			data = workFlowStepsService.getNextWorkFlowStepsByStatus(id, relationType, isExceptional);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
	/**
   	 * {@code GET /get_work_flow_steps_by_category_and_workflow} :
   	 *@implNote: getwork flow steps by category "categoryId, workFlowId"
   	 * @param userid,authToken,categoryId,workFlowId
   	 * @return ResponseObject
   	 * @author LaxmiPrasannaKumar.S
   	 */
	@GetMapping("/get_work_flow_steps_by_category_and_workflow")
	public ResponseObject getWorkFlowStepsByCategory(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long categoryId,
			@RequestHeader(required = true) Integer workFlowId) {
		List<WorkFlowStepsDTO> data = new ArrayList<>();
		try {
			data = workFlowStepsService.getWorkFlowStepsByCategoryAndWorkFlow(categoryId, workFlowId);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
	/**
   	 * {@code GET /get_all_workflow_steps} :
   	 *@implNote: get all work flow steps by "categoryId, workFlowId,isActive"
   	 * @param userid,authToken,categoryId,workFlowId,isActive
   	 * @return ResponseObject
   	 * @author LaxmiPrasannaKumar.S
   	 */
	@GetMapping("/get_all_workflow_steps")
	public ResponseObject getAllWorkFlowStepsGrid(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long categoryId,
			@RequestHeader(required = true) Integer workFlowId,
			@RequestHeader(required = true) Integer isActive) {

		List<WorkFlowStepsDTO> result = null;
		try {
			result = workFlowStepsService.findAllWorkFlowSteps(categoryId, workFlowId, isActive);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.CREATE_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,

				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.CREATE_RECORD_SUCESS, result);

	}
	/**
   	 * {@code GET /get_work_flow_steps_by_workflow} :
   	 *@implNote: get all work flow steps by  workFlowId
   	 * @param userid,authToken,workFlowId
   	 * @return ResponseObject
   	 * @author LaxmiPrasannaKumar.S
   	 */
	@GetMapping("/get_work_flow_steps_by_workflow")
	public ResponseObject getWorkFlowStepsByWorkFLow(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Integer workFlowId) {
		List<WorkFlowStepsDTO> data = new ArrayList<>();
		try {
			data = workFlowStepsService.getWorkFlowStepsByWorkFlow(workFlowId);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.CREATE_RECORD_FAILED, data);

		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,

				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.CREATE_RECORD_SUCESS, data);

	}
	/**
   	 * {@code GET /org_heirarchy} :
   	 *@implNote: for workflow testing heirarchy
   	 * @param userid,authToken,workFlowId,categoryId
   	 * @return ResponseObject
   	 * @author LaxmiPrasannaKumar.S
   	 */
	// for workflow testing heirarchy
	@GetMapping("/org_heirarchy")
	public ResponseObject getWorkFlowStepsOrgHeirarchy(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Integer workFlowId,
			@RequestHeader(required = true) Long categoryId) {
		List<WorkFlowStepsDTO> result = new ArrayList<>();
		try {
			WorkFlowStepsDTO obj = workFlowStepsService.getWorkFlowOrgHeirarchy(workFlowId, categoryId);
			result.add(obj);
		} catch (InvestmentsBusinessException hax) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR, hax.getMessage(), null);
		} catch (Exception e) {

			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.FETCH_RECORD_SUCESS, result);
	}
	
	/**
   	 * {@code GET /get_next_status_by_status_name} :
   	 *@implNote: get next work flow steps by status name 
   	 * @param userid,authToken,statusName,relationType,isExceptional
   	 * @return ResponseObject
   	 * @author LaxmiPrasannaKumar.S
   	 */
	@GetMapping("/get_next_status_by_status_name")
	public ResponseObject getNextWorkFlowStepsByStatusName(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) String statusName,
			@RequestHeader(required = true) String relationType,
			@RequestHeader(required = true) Integer isExceptional) {
		List<WorkFlowStepsDTO> data = new ArrayList<>();
		try {
			data = workFlowStepsService.getNextWorkFlowStepsByStatusName(statusName, relationType, isExceptional);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
	
	/**
   	 * {@code GET /get_init_status_by_category_name} :
   	 *@implNote: get next work flow steps by category name 
   	 * @param userid,authToken,categoryName,isExceptional
   	 * @return ResponseObject
   	 * @author LaxmiPrasannaKumar.S
   	 */
	
	@GetMapping("/get_init_status_by_category_name")
	public ResponseObject getInitWorkFlowStepsByCategoryName(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) String categoryName,
			@RequestHeader(required = true) Integer isExceptional) {
		List<WorkFlowStepsDTO> data = null;
		try {
			WorkFlowStepsDTO result = workFlowStepsService.getInitWorkFlowStepsBycategoryName(categoryName, isExceptional);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.WORK_FLOW_STEPS +ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

}
