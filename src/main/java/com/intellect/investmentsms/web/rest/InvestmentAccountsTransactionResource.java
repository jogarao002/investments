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
import com.intellect.investmentsms.repository.InvestmentAccountsTransactionRepository;
import com.intellect.investmentsms.service.InvestmentAccountsTransactionService;
import com.intellect.investmentsms.service.dto.InvestmentAccountsTransactionDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing {@link com.intellect.investmentsms.domain.InvestmentAccountsTransaction}.
 */
@RestController
@RequestMapping("/investment_accounts_transactions")
public class InvestmentAccountsTransactionResource {

    private final InvestmentAccountsTransactionService investmentAccountsTransactionService;

    private final InvestmentAccountsTransactionRepository investmentAccountsTransactionRepository;

    public InvestmentAccountsTransactionResource(
        InvestmentAccountsTransactionService investmentAccountsTransactionService,
        InvestmentAccountsTransactionRepository investmentAccountsTransactionRepository
    ) {
        this.investmentAccountsTransactionService = investmentAccountsTransactionService;
        this.investmentAccountsTransactionRepository = investmentAccountsTransactionRepository;
    }

    /**
	 * {@code POST  /add} :
	 *@implNote: save the InvestmentAccountsTransaction based on "investmentAccountsTransactionDTO"
	 * @param userid,authToken,investmentAccountsTransactionDTO.
	 * @return ResponseObject
	 * @author k.Saikumar
	 */
    @PostMapping("/add")
	public ResponseObject createInvestmentAccountsTransaction(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody InvestmentAccountsTransactionDTO investmentAccountsTransactionDTO) throws URISyntaxException {
		List<InvestmentAccountsTransactionDTO> data = null;
		InvestmentAccountsTransactionDTO result = null;
		try {
			if (investmentAccountsTransactionDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = investmentAccountsTransactionService.save(investmentAccountsTransactionDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.CREATE_RECORD_SUCESS, data);

	}

    /**
   	 * {@code PUT  /update} :
   	 *@implNote: update  the InvestmentAccountsTransaction based on "investmentAccountsTransactionDTO"
   	 * @param userid,authToken,investmentAccountsTransactionDTO
   	 * @return ResponseObject
   	 * @author k.Saikumar
   	 */
    @PutMapping("/update")
	public ResponseObject updateInvestmentAccountsTransaction(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody InvestmentAccountsTransactionDTO investmentAccountsTransactionDTO) throws URISyntaxException {
		List<InvestmentAccountsTransactionDTO> data = null;
		InvestmentAccountsTransactionDTO result = null;
		try {
			if (investmentAccountsTransactionDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = investmentAccountsTransactionService.save(investmentAccountsTransactionDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.UPDATE_RECORD_SUCESS, data);

	}


	/**
   	 * {@code GET  /get_all} :
   	 *@implNote: getAll InvestmentAccountsTransactions
   	 * @param userid,authToken
   	 * @return ResponseObject
   	 * @author k.Saikumar
   	 */
    @GetMapping("/get_all")
	public ResponseObject getAllInvestmentAccountsTransactions(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {

		List<InvestmentAccountsTransactionDTO> result = null;
		try {
			result = investmentAccountsTransactionService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.FETCH_RECORD_SUCESS, result);

	}

    /**
   	 * {@code GET  /get} :
   	 *@implNote: get InvestmentAccountsTransaction based on "id"
   	 * @param userid,authToken,id
   	 * @return ResponseObject
   	 * @author k.Saikumar
   	 */
    @GetMapping("/get")
	public ResponseObject getInvestmentAccountsTransaction(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
		List<InvestmentAccountsTransactionDTO> data = null;
		try {
			InvestmentAccountsTransactionDTO result = investmentAccountsTransactionService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
    /**
   	 * {@code DELETE  /remove} :
   	 *@implNote: remove the InvestmentAccountsTransaction based on "id".
   	 * @param userid,authToken,id
   	 * @return ResponseObject
   	 * @author k.Saikumar
   	 */

    @DeleteMapping("/remove")
	public ResponseObject deleteInvestmentAccountsTransaction(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) throws InvestmentsBusinessException {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			investmentAccountsTransactionService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.DELETE_RECORD_FAILED, null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}
    /**
   	 * {@code GET  /get_by_term_accId} :
   	 *@implNote: get all InvestmentAccountsTransaction based on "termAccId".
   	 * @param userid,authToken,termAccId
   	 * @return ResponseObject
   	 * @author k.Saikumar
   	 */
    @GetMapping("/get_by_term_accId")
   	public ResponseObject getByTermAccId(@RequestHeader(required = true) Long userid,
   			@RequestHeader(required = true) String authToken,
   			@RequestHeader(required = true) Long termAccId) {

   		List<InvestmentAccountsTransactionDTO> result = null;
   		try {
   			result = investmentAccountsTransactionService.findByTermAccId(termAccId);
   		} catch (InvestmentsBusinessException e) {
   			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
   					ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.FETCH_RECORD_FAILED, result);
   		} catch (Exception e) {
   			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
   					ApplicationConstants.SERVER_ERROR, null);
   		}
   		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
   				ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.FETCH_RECORD_SUCESS, result);

   	}
    /**
   	 * {@code GET  /get_term_accId_and_transaction_Type} :
   	 *@implNote: get all InvestmentAccountsTransaction based on "termAccId" & "transactionType".
   	 * @param userid,authToken,termAccId,transactionType.
   	 * @return ResponseObject
   	 * @author k.Saikumar
   	 */
    
    @GetMapping("/get_term_accId_and_transaction_Type")
   	public ResponseObject getByTermAccIdAndTransactionType (@RequestHeader(required = true) Long userid,
   			@RequestHeader(required = true) String authToken,
   			@RequestHeader(required = true) Long termAccId,
   			@RequestHeader(required = true) Integer transactionType) {

   		List<InvestmentAccountsTransactionDTO> result = null;
   		try {
   			result = investmentAccountsTransactionService.findByTermAccIdAndTransactionType(termAccId,transactionType);
   		} catch (InvestmentsBusinessException e) {
   			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
   					ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.FETCH_RECORD_FAILED, result);
   		} catch (Exception e) {
   			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
   					ApplicationConstants.SERVER_ERROR, null);
   		}
   		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
   				ApplicationConstants.INVESTMENT_ACCOUNTS_TRANSACTION + ApplicationConstants.FETCH_RECORD_SUCESS, result);

   	}


}
