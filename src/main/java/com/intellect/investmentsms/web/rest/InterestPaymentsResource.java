package com.intellect.investmentsms.web.rest;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import com.intellect.investmentsms.repository.InterestPaymentsRepository;
import com.intellect.investmentsms.service.InterestPaymentsService;
import com.intellect.investmentsms.service.dto.InterestPaymentsDTO;
import com.intellect.investmentsms.service.dto.PenaltyConfigDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing {@link com.intellect.investmentsms.domain.InterestPayments}.
 */
@RestController
@RequestMapping("/interest_payments")
public class InterestPaymentsResource {

    private final InterestPaymentsService interestPaymentsService;

    public InterestPaymentsResource(InterestPaymentsService interestPaymentsService) {
        this.interestPaymentsService = interestPaymentsService;
    }

    /**
     * {@code POST  /add} : Create a new InterestPayments.
     * @implNote: save the response object by "interestPaymentsDTO".
     * @param userid, authToken, interestPaymentsDTO.
     * @return the ResponseObject
     * @author K.Ramu
     */
	@PostMapping("/add")
	public ResponseObject createInterestPayments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestBody InterestPaymentsDTO interestPaymentsDTO) throws URISyntaxException {
		List<InterestPaymentsDTO> data = null;
		InterestPaymentsDTO result = null;
		try {
			if (interestPaymentsDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = interestPaymentsService.save(interestPaymentsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.CREATE_RECORD_SUCESS, data);

	}
	
	/**
     * {@code PUT  /update} : Updates an interestPaymentsDTO.
     * @implNote: update the response object by "InterestPaymentsDTO".
     * @param userid, authToken, interestPaymentsDTO.
     * @return the ResponseObject
     * @author K.Ramu
     */
	@PutMapping("/update")
	public ResponseObject updateInterestPayments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody InterestPaymentsDTO interestPaymentsDTO)
			throws URISyntaxException {
		List<InterestPaymentsDTO> data = null;
		InterestPaymentsDTO result = null;
		try {
			if (interestPaymentsDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = interestPaymentsService.save(interestPaymentsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.UPDATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.UPDATE_RECORD_SUCESS, data);

	}

	/**
	 * {@code GET  /get_all} : getAll an interestPaymentsDTO.
     * @implNote: get all InterestPaymentsDTO.
	 * @param userid, authToken.
	 * @return the ResponseObject
	 * @author K.Ramu
	 */
	@GetMapping("/get_all")
	public ResponseObject getAllInterestPayments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {

		List<InterestPaymentsDTO> result = null;
		try {
			result = interestPaymentsService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.FETCH_RECORD_SUCESS, result);

	}

	/**
     * {@code GET  /get} : get the "id" InterestPayments.
	 * @implNote: get InterestPaymentsDTO by "id".
	 * @param userid, authToken, id.
	 * @return the ResponseObject
	 * @author K.Ramu
	 */
	@GetMapping("/get")
	public ResponseObject getInterestPayments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
		List<InterestPaymentsDTO> data = null;
		try {
			InterestPaymentsDTO result = interestPaymentsService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

	/**
     * {@code DELETE  /remove} : delete the "id" InterestPayments.
	 * @implNote: delete InterestPaymentsDTO by "id".
	 * @param userid,authToken,id.
	 * @return ResponseObject Message.
	 * @author K.Ramu
	 */
	@DeleteMapping("/remove")
	public ResponseObject deleteInterestPayments(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) throws InvestmentsBusinessException {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			interestPaymentsService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.DELETE_RECORD_FAILED, null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.DELETE_RECORD_SUCESS, null);
	} 

	/**
     * {@code GET  /get_interest_payments_list_by_products} : get all InterestPayments by products an interestPaymentsDTO.
 	 * @implNote: get all by products for InterestPaymentsDTO.
 	 * @param userid, authToken,products
 	 * @return the ResponseObject
 	 * @author K.Ramu
 	 */
	@GetMapping("/get_interest_payments_list_by_products")
    public ResponseObject getInterestPaymentsListByProducts(@RequestHeader(required = true) Long userid,
            @RequestHeader(required = true) String authToken,
            @RequestHeader(required = true) Long products) {

          List<InterestPaymentsDTO> result = null;
          try {
              result = interestPaymentsService.getInterestPaymentsListByProducts(products);
          } catch (InvestmentsBusinessException e) {
              return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
                      ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.FETCH_RECORD_FAILED, result);
          } catch (Exception e) {
              return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
                      ApplicationConstants.SERVER_ERROR, null);
          }
          return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
                   ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.FETCH_RECORD_SUCESS, result);
    }
	
	/**
     * {@code GET  /get_interest_payments_list_by_term_acc_id} : get all InterestPayments by termAccId an interestPaymentsDTO.
 	 * @implNote: get all by termAccId for InterestPaymentsDTO.
 	 * @param userid, authToken,termAccId
 	 * @return the ResponseObject
 	 * @author K.Ramu
 	 */
	@GetMapping("/get_interest_payments_list_by_term_acc_id")
    public ResponseObject getInterestPaymentsListByTermAccId(@RequestHeader(required = true) Long userid,
            @RequestHeader(required = true) String authToken,
            @RequestHeader(required = true) Long termAccId) {

          List<InterestPaymentsDTO> result = null;
          try {
              result = interestPaymentsService.getInterestPaymentsListByTermAccId(termAccId);
          } catch (InvestmentsBusinessException e) {
              return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
                      ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.FETCH_RECORD_FAILED, result);
          } catch (Exception e) {
              return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
                      ApplicationConstants.SERVER_ERROR, null);
          }
          return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
                   ApplicationConstants.INTEREST_PAYMENTS + ApplicationConstants.FETCH_RECORD_SUCESS, result);
    }

}
