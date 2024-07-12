package com.intellect.investmentsms.web.rest;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.InvestedBankDetailsRepository;
import com.intellect.investmentsms.service.InvestedBankDetailsService;
import com.intellect.investmentsms.service.dto.InvestedBankDetailsDTO;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import org.springframework.http.ResponseEntity;
import com.intellect.investmentsms.util.ResponseObject;
import com.intellect.investmentsms.util.ApplicationConstants;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.intellect.investmentsms.domain.InvestedBankDetails}.
 */
@RestController
@RequestMapping("/invested_bank_details")
public class InvestedBankDetailsResource {

    private final InvestedBankDetailsService investedBankDetailsService;

    private final InvestedBankDetailsRepository investedBankDetailsRepository;

    public InvestedBankDetailsResource(
        InvestedBankDetailsService investedBankDetailsService,
        InvestedBankDetailsRepository investedBankDetailsRepository
    ) {
        this.investedBankDetailsService = investedBankDetailsService;
        this.investedBankDetailsRepository = investedBankDetailsRepository;
    }

   	/**
	 * {@code POST /add} :
	 *@implNote: create Invested Bank Details based on investedBankDetailsDTO
	 * @param userid,authToken,investedBankDetailsDTO
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    
    @PostMapping("/add")
    public ResponseObject createInvestedBankDetails(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody InvestedBankDetailsDTO investedBankDetailsDTO) throws URISyntaxException {
    	 List<InvestedBankDetailsDTO> data = null;
    	 InvestedBankDetailsDTO result = null;
 		try {
 			if (investedBankDetailsDTO.getId() != null) {
 				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 						ApplicationConstants.BAD_REQUEST, null);
 			}
 			result =investedBankDetailsService.save(investedBankDetailsDTO);
 			data = new ArrayList<>();
 			data.add(result);
 		} catch (InvestmentsBusinessException e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.INVESTED_BANK_DETAILS + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
 		} catch (Exception e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SERVER_ERROR, null);
 		}
 		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
 				 ApplicationConstants.INVESTED_BANK_DETAILS + ApplicationConstants.CREATE_RECORD_SUCESS, data);
    }
    
   	/**
	 * {@code PUT /update} :
	 *@implNote: update Invested Bank Details based on investedBankDetailsDTO
	 * @param userid,authToken,investedBankDetailsDTO
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */

    @PutMapping("/update")
    public ResponseObject updateInvestedBankDetails(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody InvestedBankDetailsDTO investedBankDetailsDTO) throws URISyntaxException {
        
    	 List<InvestedBankDetailsDTO> data = null;
    	 InvestedBankDetailsDTO result = null;
 		try {
 			if (investedBankDetailsDTO.getId() == null) {
 				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 						ApplicationConstants.BAD_REQUEST, null);
 			}
 			result = investedBankDetailsService.save(investedBankDetailsDTO);
 			data = new ArrayList<>();
 			data.add(result);
 		} catch (InvestmentsBusinessException e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.INVESTED_BANK_DETAILS + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
 		} catch (Exception e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SERVER_ERROR, null);
 		}
 		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
 				 ApplicationConstants.INVESTED_BANK_DETAILS +  ApplicationConstants.UPDATE_RECORD_SUCESS , data);
    }

   	/**
	 * {@code GET /get_all} :
	 *@implNote: get all Invested Bank Details
	 * @param userid,authToken
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    @GetMapping("/get_all")
    public ResponseObject getAllInvestedBankDetails(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {
    	  List<InvestedBankDetailsDTO> result = null;
  		try {
  			result = investedBankDetailsService.findAll();
  		} catch (InvestmentsBusinessException e) {
  			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
  					ApplicationConstants.INVESTED_BANK_DETAILS + ApplicationConstants.FETCH_RECORD_FAILED, result);
  		} catch (Exception e) {
  			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
  					ApplicationConstants.SERVER_ERROR, null);
  		}
  		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
  				 ApplicationConstants.INVESTED_BANK_DETAILS + ApplicationConstants.FETCH_RECORD_SUCESS, result);
    }

   	/**
	 * {@code GET /get} :
	 *@implNote: get all Invested Bank Details based on Id
	 * @param userid,authToken,id
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    @GetMapping("/get")
    public ResponseObject getInvestedBankDetails(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
    	
    	List<InvestedBankDetailsDTO> data = null;
		try {
			InvestedBankDetailsDTO result = investedBankDetailsService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTED_BANK_DETAILS + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.INVESTED_BANK_DETAILS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
    }
    
   	/**
	 * {@code PUT /update_invested_bank_details_status} :
	 *@implNote: update Invested Bank Details status based on investedBankDetailsDTO
	 * @param userid,authToken,investedBankDetailsDTO
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    @PutMapping("/update_invested_bank_details_status")
    public ResponseObject updateInvestedBankDetailsStatus(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody InvestedBankDetailsDTO investedBankDetailsDTO) throws URISyntaxException {
        
    	 List<InvestedBankDetailsDTO> data = null;
    	 InvestedBankDetailsDTO result = null;
 		try {
 			if (investedBankDetailsDTO.getId() == null) {
 				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 						ApplicationConstants.BAD_REQUEST, null);
 			}
 			result = investedBankDetailsService.investedBankDetailsStatus(investedBankDetailsDTO);
 			data = new ArrayList<>();
 			data.add(result);
 		} catch (InvestmentsBusinessException e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.INVESTED_BANK_DETAILS + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
 		} catch (Exception e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SERVER_ERROR, null);
 		}
 		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
 				 ApplicationConstants.INVESTED_BANK_DETAILS +  ApplicationConstants.UPDATE_RECORD_SUCESS , data);
    }
   	/**
	 * {@code DELETE /remove} :
	 *@implNote: remove Invested Bank Details based on id
	 * @param userid,authToken,id
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    @DeleteMapping("/remove")
    public ResponseObject deleteInvestedBankDetails(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
    	try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			investedBankDetailsService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTED_BANK_DETAILS + ApplicationConstants.DELETE_RECORD_FAILED+ "," + e.getMessage(), null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.INVESTED_BANK_DETAILS + ApplicationConstants.DELETE_RECORD_SUCESS, null);
    }
}
