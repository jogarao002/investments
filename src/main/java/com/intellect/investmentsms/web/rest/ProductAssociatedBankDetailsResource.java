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
import com.intellect.investmentsms.service.ProductAssociatedBankDetailsService;
import com.intellect.investmentsms.service.dto.ProductAssociatedBankDetailsDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;

@RestController
@RequestMapping("/product_associated_bank_details")
public class ProductAssociatedBankDetailsResource {

	private final ProductAssociatedBankDetailsService productAssociatedBankDetailsService;

	public ProductAssociatedBankDetailsResource(
			ProductAssociatedBankDetailsService productAssociatedBankDetailsService) {
		this.productAssociatedBankDetailsService = productAssociatedBankDetailsService;
	}

	/**
	 * {@code POST  /add} : Save the ProductAssociatedBankDetails by "productAssociatedBankDetailsDTO".
	 *@implNote: save the ProductAssociatedBankDetails by "productAssociatedBankDetailsDTO".
	 * @param userid, authToken, productAssociatedBankDetailsDTO. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@PostMapping("/add")
	public ResponseObject createProductAssociatedBankDetails(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody ProductAssociatedBankDetailsDTO productAssociatedBankDetailsDTO)
			throws URISyntaxException{
		List<ProductAssociatedBankDetailsDTO> data = null;
		ProductAssociatedBankDetailsDTO result = null;
		try {
			if (productAssociatedBankDetailsDTO.getId() != null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = productAssociatedBankDetailsService.save(productAssociatedBankDetailsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.CREATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.CREATE_RECORD_SUCESS, data);
	}

	/**
	 * {@code PUT  /add} : Update the ProductAssociatedBankDetails by "productAssociatedBankDetailsDTO".
	 *@implNote: Update the ProductAssociatedBankDetails by "productAssociatedBankDetailsDTO".
	 * @param userid, authToken, productAssociatedBankDetailsDTO. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@PutMapping("/update")
	public ResponseObject updateProductAssociatedBankDetails(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestBody ProductAssociatedBankDetailsDTO productAssociatedBankDetailsDTO)
			throws URISyntaxException {
		List<ProductAssociatedBankDetailsDTO> data = null;
		ProductAssociatedBankDetailsDTO result = null;
		try {
			if (productAssociatedBankDetailsDTO.getId() == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			result = productAssociatedBankDetailsService.save(productAssociatedBankDetailsDTO);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.UPDATE_RECORD_FAILED + ","
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.UPDATE_RECORD_SUCESS, data);
	}

	/**
	 * {@code GET  /get_all} : Get All the ProductAssociatedBankDetails.
	 *@implNote: Get All the ProductAssociatedBankDetails.
	 * @param userid, authToken. 
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@GetMapping("/get_all")
	public ResponseObject getAllProductAssociatedBankDetails(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {
		List<ProductAssociatedBankDetailsDTO> result = null;
		try {
			result = productAssociatedBankDetailsService.findAll();
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.FETCH_RECORD_FAILED, result);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.FETCH_RECORD_SUCESS, result);
	}

	/**
	 * {@code GET  /get} : Get the ProductAssociatedBankDetails by "id".
	 *@implNote: Get the ProductAssociatedBankDetails by "id".
	 * @param userid, authToken, id.
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@GetMapping("/get")
	public ResponseObject getProductAssociatedBankDetails(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		List<ProductAssociatedBankDetailsDTO> data = null;
		try {
			ProductAssociatedBankDetailsDTO result = productAssociatedBankDetailsService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.FETCH_RECORD_SUCESS, data);

	}

	/**
	 * {@code DELETE  /remove} : Delete the ProductAssociatedBankDetails by "id".
	 *@implNote: Delete the ProductAssociatedBankDetails by "id".
	 * @param userid, authToken, id.
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@DeleteMapping("/remove")
	public ResponseObject deleteProductAssociatedBankDetails(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long id) {
		try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			productAssociatedBankDetailsService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.DELETE_RECORD_FAILED + ","
							+ e.getMessage(),
					null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.DELETE_RECORD_SUCESS, null);
	}
	
	/**
	 * {@code GET  /product_associated_bank_details_by_product_id} : Get the ProductAssociatedBankDetails by "productid".
	 *@implNote: Get the ProductAssociatedBankDetails by "productid".
	 * @param userid, authToken, productid.
	 * @return the ResponseObject.
	 * @author Dilip Kumar.G
	 */
	
	@GetMapping("/product_associated_bank_details_by_product_id")
	public ResponseObject getInterestPolicyConfigByProductId(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long productid) {
		List<ProductAssociatedBankDetailsDTO> data = null;
		try {
			data = productAssociatedBankDetailsService.getProductAssociatedBankDetailsByProductId(productid);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.FETCH_RECORD_FAILED + ", "
							+ e.getMessage(),
					data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PRODUCT_ASSOCIATED_BANK_DETAILS + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
}
