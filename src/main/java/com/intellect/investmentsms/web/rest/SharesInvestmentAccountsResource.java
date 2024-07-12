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
import com.intellect.investmentsms.service.SharesInvestmentAccountsService;
import com.intellect.investmentsms.service.dto.SharesInvestmentAccountsDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing {@link com.intellect.investmentsms.domain.SharesInvestmentAccounts}.
 */
@RestController
@RequestMapping("/shares_investment_accounts")
public class SharesInvestmentAccountsResource {

    private final SharesInvestmentAccountsService sharesInvestmentAccountsService;
    

    public SharesInvestmentAccountsResource(
        SharesInvestmentAccountsService sharesInvestmentAccountsService) {
        this.sharesInvestmentAccountsService = sharesInvestmentAccountsService;
    }

    /**
     * {@code POST  /add} : Create a new sharesInvestmentAccounts.
	 * @implNote: save the InvestmentAccountDocumentsDTO.
	 * @param sharesInvestmentAccountsDTO.
	 * @return sharesInvestmentAccountsDTO.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 * @author M.Bhavana
	 */
    @PostMapping("/add")
    public ResponseObject createSharesInvestmentAccounts(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO) throws URISyntaxException {
   	 List<SharesInvestmentAccountsDTO> data = null;
   	 SharesInvestmentAccountsDTO result = null;
		try {
			if (sharesInvestmentAccountsDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result =sharesInvestmentAccountsService.save(sharesInvestmentAccountsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + ApplicationConstants.CREATE_RECORD_SUCESS, data);
   }

    /**
     * {@code PUT  /update} : update the sharesInvestmentAccounts
     * @implNote: update the sharesInvestmentAccountsDTO
     * @param userid, authToken and sharesInvestmentAccountsDTO.
     * @return the List<SharesInvestmentAccountsDTO> as ResponseObject object.
     * @author M.Bhavana
     */
    @PutMapping("/update")
    public ResponseObject updateSharesInvestmentAccounts(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO) throws URISyntaxException {
        
    	 List<SharesInvestmentAccountsDTO> data = null;
    	 SharesInvestmentAccountsDTO result = null;
 		try {
 			if (sharesInvestmentAccountsDTO.getId() == null) {
 				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 						ApplicationConstants.BAD_REQUEST, null);
 			}
 			result = sharesInvestmentAccountsService.save(sharesInvestmentAccountsDTO);
 			data = new ArrayList<>();
 			data.add(result);
 		} catch (InvestmentsBusinessException e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
 		} catch (Exception e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SERVER_ERROR, null);
 		}
 		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
 				 ApplicationConstants.SHARES_INVESTMENT_ACCOUNT +  ApplicationConstants.UPDATE_RECORD_SUCESS , data);
    }
    
    /**
     * {@code GET  /get_all} : get all the sharesInvestmentAccounts.
     * @implNote: get all sharesInvestmentAccountsDTO
     * @param userid and authToken.
     * @return the List<SharesInvestmentAccountsDTO> as ResponseObject object.
     * @author M.Bhavana
     */
    @GetMapping("/get_all")
    public ResponseObject getAllSharesInvestmentAccounts(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {
  	  List<SharesInvestmentAccountsDTO> result = null;
		try {
			result = sharesInvestmentAccountsService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + ApplicationConstants.FETCH_RECORD_SUCESS, result);
  }

    /**
     * {@code GET  /get} : get the sharesInvestmentAccounts based on id.
     * @implNote: get sharesInvestmentAccountsDTO by id
     * @param userid, authToken and id.
     * @return the List<SharesInvestmentAccountsDTO> as ResponseObject object.
     * @author M.Bhavana
     */
    @GetMapping("/get")
    public ResponseObject getSharesInvestmentAccounts(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
    	
    	List<SharesInvestmentAccountsDTO> data = null;
		try {
			SharesInvestmentAccountsDTO result = sharesInvestmentAccountsService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + ApplicationConstants.FETCH_RECORD_SUCESS, data);
       
    }

    /**
     * {@code DELETE  /remove} : delete the sharesInvestmentAccounts based on id.
     * @implNote: delete SharesInvestmentAccountsDTO by id
     * @param userid, authToken and id.
     * @return the ResponseObject object.
     * @author M.Bhavana
     */
    @DeleteMapping("/remove")
    public ResponseObject deleteSharesInvestmentAccounts(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
    	try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			sharesInvestmentAccountsService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + ApplicationConstants.DELETE_RECORD_FAILED+ "," + e.getMessage(), null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + ApplicationConstants.DELETE_RECORD_SUCESS, null);
    }
    
    /**
     * {@code GET  /get_all_by_productId} : get the list of sharesInvestmentAccounts based on pacsId and branchId.
     * @implNote: get the list of SharesInvestmentAccountsDTO by productId.
     * @param userid, authToken, pacsId and productId.
     * @return the List<SharesInvestmentAccountsDTO> as ResponseObject object.
     * @author M.Bhavana
     */
    @GetMapping("/get_all_by_productId")
    public ResponseObject getByProductId(@RequestHeader(required = true) Long userid, @RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) { 
    	List<SharesInvestmentAccountsDTO> result = null;
		try {
			result = sharesInvestmentAccountsService.getAllByProductId(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + " " + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + " " + ApplicationConstants.FETCH_RECORD_SUCESS, result);

    }
    
    /**
     * {@code GET  /get_all_by_termAccountId} : get the list of investmentAccountDocumentsDTO based on termAccountId.
     * @implNote: get the list of InvestmentAccountDocumentsDTO by termAccountId.
     * @param userid, authToken and termAccountId.
     * @return the List<InvestmentAccountDocumentsDTO> as ResponseObject object.
     * @author M.Bhavana
     */
    @GetMapping("/get_all_sharesInvestmentAccounts_by_pacsId_and_branchId")
    public ResponseObject getSharesInvestmentAccountsByPacsIdAndBranchId(@RequestHeader(required = true) Long userid, @RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long pacsId, @RequestHeader(required = true) Long branchId) { 
    	List<SharesInvestmentAccountsDTO> result = null;
		try {
			result = sharesInvestmentAccountsService.getAllSharesInvestmentAccountsByPacsIdAndBranchId(pacsId,branchId);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + " " + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + " " + ApplicationConstants.FETCH_RECORD_SUCESS, result);

    }
    
    /**
     * {@code PUT  /shares_investment_account_status_approval} : update the sharesInvestmentAccounts
     * @implNote: update the sharesInvestmentAccountsDTO status.
     * @param userid, authToken and sharesInvestmentAccountsDTO.
     * @return the List<SharesInvestmentAccountsDTO> as ResponseObject object.
     * @author M.Bhavana
     */
    @PutMapping("/shares_investment_account_status_approval")
    public ResponseObject addStatus(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO) throws URISyntaxException {
    	List<SharesInvestmentAccountsDTO> data = null;
    	SharesInvestmentAccountsDTO result = null;
    	try {
    		if(null == sharesInvestmentAccountsDTO) {
    			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
    		}
    		  result = sharesInvestmentAccountsService.sharesInvestmentAccountsApproval(sharesInvestmentAccountsDTO);
              data = new ArrayList<>();
              data.add(result);
	    } catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + " " + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
    	return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
                ApplicationConstants.SHARES_INVESTMENT_ACCOUNT + ApplicationConstants.UPDATE_RECORD_SUCESS, data);
    }
		
}
