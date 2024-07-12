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
import com.intellect.investmentsms.repository.InvestmentAccountInstallmentsRepository;
import com.intellect.investmentsms.service.InvestmentAccountInstallmentsService;
import com.intellect.investmentsms.service.dto.InvestmentAccountInstallmentsDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing {@link com.intellect.investmentsms.domain.InvestmentAccountInstallments}.
 */
@RestController
@RequestMapping("investment_account_installments")
public class InvestmentAccountInstallmentsResource {

    private final InvestmentAccountInstallmentsService investmentAccountInstallmentsService;

    private final InvestmentAccountInstallmentsRepository investmentAccountInstallmentsRepository;

    public InvestmentAccountInstallmentsResource(
        InvestmentAccountInstallmentsService investmentAccountInstallmentsService,
        InvestmentAccountInstallmentsRepository investmentAccountInstallmentsRepository
    ) {
        this.investmentAccountInstallmentsService = investmentAccountInstallmentsService;
        this.investmentAccountInstallmentsRepository = investmentAccountInstallmentsRepository;
    }

    @PostMapping("/add")
    public ResponseObject saveInvestmentAccountInstallments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody InvestmentAccountInstallmentsDTO investmentAccountInstallmentsDTO) throws URISyntaxException {
    	 List<InvestmentAccountInstallmentsDTO> data = null;
    	 InvestmentAccountInstallmentsDTO result = null;
 		try {
 			if (investmentAccountInstallmentsDTO.getId() != null) {
 				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 						ApplicationConstants.BAD_REQUEST, null);
 			}
 			result =investmentAccountInstallmentsService.save(investmentAccountInstallmentsDTO);
 			data = new ArrayList<>();
 			data.add(result);
 		} catch (InvestmentsBusinessException e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
 		} catch (Exception e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SERVER_ERROR, null);
 		}
 		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
 				 ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS + ApplicationConstants.CREATE_RECORD_SUCESS, data);
    }

    @PutMapping("/update")
    public ResponseObject updateInvestmentAccountInstallments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody InvestmentAccountInstallmentsDTO investmentAccountInstallmentsDTO) throws URISyntaxException {
        
    	 List<InvestmentAccountInstallmentsDTO> data = null;
    	 InvestmentAccountInstallmentsDTO result = null;
 		try {
 			if (investmentAccountInstallmentsDTO.getId() == null) {
 				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 						ApplicationConstants.BAD_REQUEST, null);
 			}
 			result = investmentAccountInstallmentsService.save(investmentAccountInstallmentsDTO);
 			data = new ArrayList<>();
 			data.add(result);
 		} catch (InvestmentsBusinessException e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
 		} catch (Exception e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SERVER_ERROR, null);
 		}
 		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
 				 ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS +  ApplicationConstants.UPDATE_RECORD_SUCESS , data);
    }

    @GetMapping("/get_all")
    public ResponseObject getAllInvestmentAccountInstallments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {
    	  List<InvestmentAccountInstallmentsDTO> result = null;
  		try {
  			result = investmentAccountInstallmentsService.findAll();
  		} catch (InvestmentsBusinessException e) {
  			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
  					ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS + ApplicationConstants.FETCH_RECORD_FAILED, result);
  		} catch (Exception e) {
  			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
  					ApplicationConstants.SERVER_ERROR, null);
  		}
  		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
  				 ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS + ApplicationConstants.FETCH_RECORD_SUCESS, result);
    }
    
    @GetMapping("/get")
    public ResponseObject getInvestmentAccountInstallments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
    	
    	List<InvestmentAccountInstallmentsDTO> data = null;
		try {
			InvestmentAccountInstallmentsDTO result = investmentAccountInstallmentsService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
       
    }

    @DeleteMapping("/remove")
    public ResponseObject deleteInvestmentAccountInstallments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
    	try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			investmentAccountInstallmentsService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS + ApplicationConstants.DELETE_RECORD_FAILED+ "," + e.getMessage(), null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS + ApplicationConstants.DELETE_RECORD_SUCESS, null);
    }
    
    @GetMapping("/get_by_term_account_id")
    public ResponseObject getByTermAccId(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long termAccId) {
    	
    	List<InvestmentAccountInstallmentsDTO> data = null;
		try {
			data = investmentAccountInstallmentsService.getByTermAccId(termAccId);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.INVESTMENT_ACCOUNT_INSTALLMENTS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
       
    }
}
