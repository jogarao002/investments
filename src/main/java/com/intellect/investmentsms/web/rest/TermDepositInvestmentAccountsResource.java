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
import com.intellect.investmentsms.service.TermDepositInvestmentAccountsService;
import com.intellect.investmentsms.service.dto.TermDepositInvestmentAccountsDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing
 * {@link com.intellect.investmentsms.domain.TermDepositInvestmentAccounts}.
 */
@RestController
@RequestMapping("/term_deposit_investment_accounts")
public class TermDepositInvestmentAccountsResource {

	private final TermDepositInvestmentAccountsService termDepositInvestmentAccountsService;

	public TermDepositInvestmentAccountsResource(
			TermDepositInvestmentAccountsService termDepositInvestmentAccountsService) {
		this.termDepositInvestmentAccountsService = termDepositInvestmentAccountsService;
	}

	/**
	 * {@code POST  /add} : Create a new
	 * @implNote: save the response object by "termDepositInvestmentAccountsDTO".
	 * @param userid, authToken, termDepositInvestmentAccountsDTO.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 * @author jogarao
	 */
	@PostMapping("/add")
	public ResponseObject createTermDepositInvestmentAccounts(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) throws URISyntaxException {
		List<TermDepositInvestmentAccountsDTO> termDepositInvestmentAccountsDTOs = null;
		TermDepositInvestmentAccountsDTO result = null;
		try {
			if (termDepositInvestmentAccountsDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = termDepositInvestmentAccountsService.save(termDepositInvestmentAccountsDTO);
			termDepositInvestmentAccountsDTOs = new ArrayList<>();
			termDepositInvestmentAccountsDTOs.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(
					ApplicationConstants.RES_STATUS_ERROR, ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS
							+ ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(),
					termDepositInvestmentAccountsDTOs);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.CREATE_RECORD_SUCESS,
				termDepositInvestmentAccountsDTOs);
	}

	/**
	 * {@code PUT  /update} : Updates an termDepositInvestmentAccountsDTO.
	 * @implNote: update the response object by "termDepositInvestmentAccountsDTO".
	 * @param userid, authToken, termDepositInvestmentAccountsDTO.
	 * @return List<TermDepositInvestmentAccountsDTO>
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 * @author jogarao
	 */
	@PutMapping("/update")
	public ResponseObject updateTermDepositInvestmentAccounts(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) throws URISyntaxException {
		List<TermDepositInvestmentAccountsDTO> data = null;
		TermDepositInvestmentAccountsDTO result = null;
		try {
			if (termDepositInvestmentAccountsDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = termDepositInvestmentAccountsService.save(termDepositInvestmentAccountsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.UPDATE_RECORD_FAILED
							+ "," + e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.UPDATE_RECORD_SUCESS,
				data);
	}

	/**
	 * {@code GET  /get_all} : getAll an termDepositInvestmentAccountsDTO.
	 * @implNote: get all termDepositInvestmentAccountsDTO.
	 * @param userid, authToken.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @author jogarao
	 */
	@GetMapping("/get_all")
	public ResponseObject getAllTermDepositInvestmentAccounts(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {
		List<TermDepositInvestmentAccountsDTO> result = null;
		try {
			result = termDepositInvestmentAccountsService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_FAILED,
					result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_SUCESS,
				result);
	}

	/**
	 * {@code GET  /get} : get the "id" termDepositInvestmentAccountsDTO.
	 * @implNote: get termDepositInvestmentAccountsDTO by "id".
	 * @param userid, authToken, id.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @author jogarao
	 */
	@GetMapping("/get")
	public ResponseObject getTermDepositInvestmentAccounts(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		List<TermDepositInvestmentAccountsDTO> data = null;
		try {
			TermDepositInvestmentAccountsDTO result = termDepositInvestmentAccountsService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_FAILED,
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

	/**
	 * {@code DELETE  /remove} : delete the "id" termDepositInvestmentAccounts.
	 * @implNote: delete termDepositInvestmentAccounts by "id".
	 * @param id.
	 * @return ResponseObject Message.
	 * @author jogarao
	 */
	@DeleteMapping("/remove")
	public ResponseObject deleteTermDepositInvestmentAccounts(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			termDepositInvestmentAccountsService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.DELETE_RECORD_FAILED
							+ "," + e.getMessage(),
					null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.DELETE_RECORD_SUCESS,
				null);
	}

	/**
	 * {@code GET  /get_by_product_id} : get the "productId" termDepositInvestmentAccountsDTO.
	 * @implNote: get termDepositInvestmentAccountsDTO by "productId".
	 * @param userid, authToken, productId.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @author jogarao
	 */
	@GetMapping("/get_by_product_id")
	public ResponseObject getByProductId(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long productId) {
		List<TermDepositInvestmentAccountsDTO> data = null;
		try {
			List<TermDepositInvestmentAccountsDTO> result = termDepositInvestmentAccountsService
					.findByProductId(productId);
			data = new ArrayList<>();
			data.addAll(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_FAILED,
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

	/**
	 * {@code GET  /getByPacIdAndBranchId} : get the "pacId" and "branchId" termDepositInvestmentAccountsDTO.
	 * @implNote: get termDepositInvestmentAccountsDTO by "pacId" and "branchId".
	 * @param userid, authToken, pacId, branchId.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @author jogarao
	 */
	@GetMapping("/get_by_pacId_and_branchId")
	public ResponseObject getByPacIdAndBranchId(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long pacId,
			@RequestHeader(required = true) Long branchId) {
		List<TermDepositInvestmentAccountsDTO> data = null;
		try {
			List<TermDepositInvestmentAccountsDTO> result = termDepositInvestmentAccountsService
					.findByPacIdAndBranchId(pacId, branchId);
			data = new ArrayList<>();
			data.addAll(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_FAILED,
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

	/**
	 * {@code GET  /get_preview_by_termAccountId}.
	 * @implNote: get termDepositInvestmentAccountsDTO by "termAccountId".
	 * @param userid, authToken, termAccountId.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @author jogarao
	 */
	@GetMapping("/get_preview_by_termAccountId")
	public ResponseObject getPreviewByTermAccountId(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long termAccountId) {
		List<TermDepositInvestmentAccountsDTO> data = null;
		try {
			TermDepositInvestmentAccountsDTO result = termDepositInvestmentAccountsService
					.getPreviewByTermAccountId(termAccountId);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
	
	/**
	 * {@code PUT  /term_deposit_investment_account_status} : Updates an termDepositInvestmentAccountsDTO.
	 * @implNote: update the response object by "termDepositInvestmentAccountsDTO".
	 * @param userid, authToken, termDepositInvestmentAccountsDTO.
	 * @return List<TermDepositInvestmentAccountsDTO>
	 * @author jogarao
	 */
	@PutMapping("/term_deposit_investment_account_status")
	public ResponseObject termDepositInvestmentAccountStatus(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) {
		List<TermDepositInvestmentAccountsDTO> data = null;
		try {
			TermDepositInvestmentAccountsDTO result = termDepositInvestmentAccountsService
					.getTermDepositInvestmentAccountStatus(termDepositInvestmentAccountsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
	
	@PostMapping("/get_maturity_amount_on_foreclosure")
    public ResponseObject getMaturityAmountOnForeclosure(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) {
    	List<TermDepositInvestmentAccountsDTO> data = null;
		try {
			TermDepositInvestmentAccountsDTO result = termDepositInvestmentAccountsService.getMaturityAmountOnForeclosure(termDepositInvestmentAccountsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS +ApplicationConstants.FETCH_RECORD_SUCESS, data);
    }
	
	@PostMapping("/save_fore_closure_details")
    public ResponseObject saveForeClosureDetails(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO,
			@RequestHeader(required = true) Boolean isApproved) throws URISyntaxException {
    	 List<TermDepositInvestmentAccountsDTO> data = null;
    	 
    	 TermDepositInvestmentAccountsDTO result = null;
 		try {
 			result = termDepositInvestmentAccountsService.saveForeClosureDetails(termDepositInvestmentAccountsDTO);
 			data = new ArrayList<>();
 			data.add(result);
 		} catch (InvestmentsBusinessException e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
 		} catch (Exception e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SERVER_ERROR, null);
 		}
 		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
 				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.CREATE_RECORD_SUCESS, data);
    }
	
	@PostMapping("/get_rd_maturity_amount_on_foreclosure")
    public ResponseObject getRDMaturityAmountOnForeclosure(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody(required = true) TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) {
    	List<TermDepositInvestmentAccountsDTO> data = null;
		try {
			TermDepositInvestmentAccountsDTO result = termDepositInvestmentAccountsService.getRDMaturityAmountOnForeclosure(termDepositInvestmentAccountsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.TERM_DEPOSIT_INVESTMENT_ACCOUNTS +ApplicationConstants.FETCH_RECORD_SUCESS, data);
    }
	
	 /**
   	 * {@code GET /get_all_grid} :
   	 *@implNote: get All Grid Data based on pacs Id and Branch Id 
   	 * @param userid,authToken,branchId,pacsId
   	 * @return ResponseObject
   	 * @author LaxmiPrasannaKumar.S
   	 */
	
	@GetMapping("/get_all_grid")
	public ResponseObject getAllGridData(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestHeader(required = true) Long pacsId,@RequestHeader(required = true) Long branchId) {
		List<TermDepositInvestmentAccountsDTO> result = null;
		try {
			result = termDepositInvestmentAccountsService.getAllGridData(pacsId,branchId);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.DEPOSIT_AND_SHARES_INVESTMENT_ACCOUNTS + ApplicationConstants.FETCH_RECORD_SUCESS, result);
	}
	
}
