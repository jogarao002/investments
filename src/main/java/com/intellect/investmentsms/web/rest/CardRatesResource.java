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
import com.intellect.investmentsms.repository.CardRatesRepository;
import com.intellect.investmentsms.service.CardRatesService;
import com.intellect.investmentsms.service.dto.CardRatesDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

/**
 * REST controller for managing
 * {@link com.intellect.termdepositsms.domain.CardRates}.
 */
@RestController
@RequestMapping("/card_rates")
public class CardRatesResource {

	private final CardRatesService cardRatesService;


	public CardRatesResource(CardRatesService cardRatesService) {
		this.cardRatesService = cardRatesService;
	}

	@PostMapping("/add")
	public ResponseObject createCardRates(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody CardRatesDTO cardRatesDTO)
			throws URISyntaxException {
		List<CardRatesDTO> data = null;
		CardRatesDTO result = null;
		try {
			if (cardRatesDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = cardRatesService.save(cardRatesDTO);
			data = new ArrayList<>();
			data.add(result); 
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.CARD_RATES + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), 
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.CARD_RATES + ApplicationConstants.CREATE_RECORD_SUCESS, data);
	}

	@PutMapping("/update")
	public ResponseObject updateCardRates(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody CardRatesDTO cardRatesDTO)
			throws URISyntaxException {
		List<CardRatesDTO> data = null; 
		CardRatesDTO result = null;
		try {
			if (cardRatesDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = cardRatesService.save(cardRatesDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.CARD_RATES + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.CARD_RATES + ApplicationConstants.UPDATE_RECORD_SUCESS, data);
	}

	@GetMapping("/get_all")
	public ResponseObject getAllCardRates(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {
		List<CardRatesDTO> result = null;
		try {
			result = cardRatesService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.CARD_RATES + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.CARD_RATES + ApplicationConstants.FETCH_RECORD_SUCESS, result);
	}

	@GetMapping("/get")
	public ResponseObject getCardRate(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		List<CardRatesDTO> data = null;
		try {
			CardRatesDTO result = cardRatesService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.CARD_RATES + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null); 
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.CARD_RATES + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
 
	@DeleteMapping("/remove")
	public ResponseObject deleteCardRate(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			cardRatesService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.CARD_RATES + ApplicationConstants.DELETE_RECORD_FAILED + "," + e.getMessage(),
					null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.CARD_RATES + ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}

	@GetMapping("/get_card_rates_by_pacs_id")
	public ResponseObject getCardRatesByPacsId(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long pacsId) {
		List<CardRatesDTO> data = null;
		try {
			data = cardRatesService.getCardRatesByPacsId(pacsId);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.CARD_RATES + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.CARD_RATES + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}

}
