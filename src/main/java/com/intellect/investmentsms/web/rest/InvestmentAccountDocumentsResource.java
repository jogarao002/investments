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
import com.intellect.investmentsms.service.InvestmentAccountDocumentsService;
import com.intellect.investmentsms.service.dto.InvestmentAccountDocumentsDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing {@link com.intellect.investmentsms.domain.InvestmentAccountDocuments}.
 */
@RestController
@RequestMapping("/investment_account_documents")
public class InvestmentAccountDocumentsResource {

    private final InvestmentAccountDocumentsService investmentAccountDocumentsService;

    public InvestmentAccountDocumentsResource(
        InvestmentAccountDocumentsService investmentAccountDocumentsService) {
        this.investmentAccountDocumentsService = investmentAccountDocumentsService;
    }
    /**
     * {@code POST  /add} : Create a new investmentAccountDocuments.
	 * @implNote: save the InvestmentAccountDocumentsDTO.
	 * @param investmentAccountDocumentsDTO.
	 * @return investmentAccountDocumentsDTO.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 * @author M.Bhavana
	 */
    @PostMapping("/add")
    public ResponseObject createInvestmentAccountDocuments(@RequestHeader(required = true) Long userid,@RequestHeader(required = true) String authToken,@RequestBody InvestmentAccountDocumentsDTO investmentAccountDocumentsDTO) throws URISyntaxException {
   	 List<InvestmentAccountDocumentsDTO> data = null;
   	 InvestmentAccountDocumentsDTO result = null;
		try {
			if (investmentAccountDocumentsDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result =investmentAccountDocumentsService.save(investmentAccountDocumentsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNT_DOCUMENT + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.INVESTMENT_ACCOUNT_DOCUMENT + ApplicationConstants.CREATE_RECORD_SUCESS, data);
   }

    /**
     * {@code PUT  /update} : update the investmentAccountDocuments
     * @implNote: update the investmentAccountDocumentsDTO
     * @param userid, authToken and investmentAccountDocumentsDTO.
     * @return the List<InvestmentAccountDocumentsDTO> as ResponseObject object.
     * @author M.Bhavana
     */
    @PutMapping("/update")
    public ResponseObject updateInvestmentAccountDocuments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody InvestmentAccountDocumentsDTO investmentAccountDocumentsDTO) throws URISyntaxException {
        
    	 List<InvestmentAccountDocumentsDTO> data = null;
    	 InvestmentAccountDocumentsDTO result = null;
 		try {
 			if (investmentAccountDocumentsDTO.getId() == null) {
 				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 						ApplicationConstants.BAD_REQUEST, null);
 			}
 			result = investmentAccountDocumentsService.save(investmentAccountDocumentsDTO);
 			data = new ArrayList<>();
 			data.add(result);
 		} catch (InvestmentsBusinessException e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.INVESTMENT_ACCOUNT_DOCUMENT + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
 		} catch (Exception e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SERVER_ERROR, null);
 		}
 		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
 				 ApplicationConstants.INVESTMENT_ACCOUNT_DOCUMENT +  ApplicationConstants.UPDATE_RECORD_SUCESS , data);
    }

    /**
     * {@code GET  /get_all} : get all the investmentAccountDocuments.
     * @implNote: get all InvestmentAccountDocumentsDTO
     * @param userid and authToken.
     * @return the List<InvestmentAccountDocumentsDTO> as ResponseObject object.
     * @author M.Bhavana
     */
    @GetMapping("/get_all")
    public ResponseObject getAllInvestmentAccountDocuments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {
  	  List<InvestmentAccountDocumentsDTO> result = null;
		try {
			result = investmentAccountDocumentsService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNT_DOCUMENT + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.INVESTMENT_ACCOUNT_DOCUMENT + ApplicationConstants.FETCH_RECORD_SUCESS, result);
  }

    /**
     * {@code GET  /get} : get the investmentAccountDocuments based on id.
     * @implNote: get InvestmentAccountDocumentsDTO by id
     * @param userid, authToken and id.
     * @return the List<InvestmentAccountDocumentsDTO> as ResponseObject object.
     * @author M.Bhavana
     */
    @GetMapping("/get")
    public ResponseObject getInvestmentAccountDocuments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
    	
    	List<InvestmentAccountDocumentsDTO> data = null;
		try {
			InvestmentAccountDocumentsDTO result = investmentAccountDocumentsService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNT_DOCUMENT + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.INVESTMENT_ACCOUNT_DOCUMENT + ApplicationConstants.FETCH_RECORD_SUCESS, data);
       
    }

    /**
     * {@code DELETE  /remove} : delete the investmentAccountDocuments based on id.
     * @implNote: delete InvestmentAccountDocumentsDTO by id
     * @param userid, authToken and id.
     * @return the ResponseObject object.
     * @author M.Bhavana
     */
    @DeleteMapping("/remove")
    public ResponseObject deleteInvestmentAccountDocuments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
    	try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			investmentAccountDocumentsService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNT_DOCUMENT + ApplicationConstants.DELETE_RECORD_FAILED+ "," + e.getMessage(), null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.INVESTMENT_ACCOUNT_DOCUMENT + ApplicationConstants.DELETE_RECORD_SUCESS, null);
    }
    
    /**
     * {@code GET  /get_all_by_productId} : get the list of investmentAccountDocumentsDTO based on pacsId and branchId.
     * @implNote: get the list of InvestmentAccountDocumentsDTO by productId.
     * @param userid, authToken, pacsId and productId.
     * @return the List<InvestmentAccountDocumentsDTO> as ResponseObject object.
     * @author M.Bhavana
     */
    @GetMapping("/get_all_by_productId")
    public ResponseObject getByProductId(@RequestHeader(required = true) Long userid, @RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long productId) { 
    	List<InvestmentAccountDocumentsDTO> result = null;
		try {
			result = investmentAccountDocumentsService.getAllByProductId(productId);
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
	@GetMapping("/get_all_by_termAccountId")
	public ResponseObject getByTermAccountId(@RequestHeader(required = true) Long userid, @RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) { 
		List<InvestmentAccountDocumentsDTO> result = null;
		try {
			result = investmentAccountDocumentsService.getAllByTermAccountId(id);
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

    
}
