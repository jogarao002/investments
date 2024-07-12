package com.intellect.investmentsms.web.rest;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.ProductRepository;
import com.intellect.investmentsms.service.ProductService;
import com.intellect.investmentsms.service.dto.ProductDTO;
import com.intellect.investmentsms.util.ApplicationConstants;
import com.intellect.investmentsms.util.InvestmentsResponseUtil;
import com.intellect.investmentsms.util.ResponseObject;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.intellect.investmentsms.domain.Product}.
 */
@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService productService;

    private final ProductRepository productRepository;

    public ProductResource(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

   	/**
	 * {@code POST /add} :
	 *@implNote: Create Product based on productDTO
	 * @param userid,authToken,productDTO
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */

    @PostMapping("/add")
    public ResponseObject createProduct(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody ProductDTO productDTO) throws URISyntaxException {
    	 List<ProductDTO> data = null;
    	 ProductDTO result = null;
 		try {
 			if (productDTO.getId() != null) {
 				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 						ApplicationConstants.BAD_REQUEST, null);
 			}
 			result =productService.save(productDTO);
 			data = new ArrayList<>();
 			data.add(result);
 		} catch (InvestmentsBusinessException e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.PRODUCT + ApplicationConstants.CREATE_RECORD_FAILED + "," + e.getMessage(), data);
 		} catch (Exception e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SERVER_ERROR, null);
 		}
 		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
 				 ApplicationConstants.PRODUCT + ApplicationConstants.CREATE_RECORD_SUCESS, data);
    }
   	/**
	 * {@code PUT /update} :
	 *@implNote: Update Product based on productDTO
	 * @param userid,authToken,productDTO
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    @PutMapping("/update")
    public ResponseObject updateProduct(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody ProductDTO productDTO) throws URISyntaxException {
        
    	 List<ProductDTO> data = null;
    	 ProductDTO result = null;
 		try {
 			if (productDTO.getId() == null) {
 				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 						ApplicationConstants.BAD_REQUEST, null);
 			}
 			result = productService.save(productDTO);
 			data = new ArrayList<>();
 			data.add(result);
 		} catch (InvestmentsBusinessException e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.PRODUCT + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
 		} catch (Exception e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SERVER_ERROR, null);
 		}
 		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
 				 ApplicationConstants.PRODUCT +  ApplicationConstants.UPDATE_RECORD_SUCESS , data);
    }
   	/**
	 * {@code GET /get_all} :
	 *@implNote: get all Product details
	 * @param userid,authToken
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    @GetMapping("/get_all")
    public ResponseObject getAllProducts(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken) {
    	  List<ProductDTO> result = null;
  		try {
  			result = productService.findAll();
  		} catch (InvestmentsBusinessException e) {
  			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
  					ApplicationConstants.PRODUCT + ApplicationConstants.FETCH_RECORD_FAILED, result);
  		} catch (Exception e) {
  			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
  					ApplicationConstants.SERVER_ERROR, null);
  		}
  		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
  				 ApplicationConstants.PRODUCT + ApplicationConstants.FETCH_RECORD_SUCESS, result);
    }
   	/**
	 * {@code GET /get} :
	 *@implNote: get Product details based on id
	 * @param userid,authToken,id
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    @GetMapping("/get")
    public ResponseObject getProduct(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
    	
    	List<ProductDTO> data = null;
		try {
			ProductDTO result = productService.findOne(id);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PRODUCT + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.PRODUCT + ApplicationConstants.FETCH_RECORD_SUCESS, data);
    }
   	/**
	 * {@code DELETE /remove} :
	 *@implNote: remove Product details based on id
	 * @param userid,authToken,id
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    @DeleteMapping("/remove")
    public ResponseObject deleteProduct(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,
			@RequestHeader(required = true) Long id) {
    	try {
			if (id == null) {
				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
						ApplicationConstants.BAD_REQUEST, null);
			}
			productService.delete(id);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PRODUCT + ApplicationConstants.DELETE_RECORD_FAILED+ "," + e.getMessage(), null);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				 ApplicationConstants.PRODUCT + ApplicationConstants.DELETE_RECORD_SUCESS, null);
    }
  	/**
	 * {@code GET /get_preview_by_productId} :
	 *@implNote: get preview Product details based on productId
	 * @param userid,authToken,productId
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    @GetMapping("/get_preview_by_productId")
	public ResponseObject getPreviewByProductId(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken, @RequestHeader(required = true) Long productId) {
		List<ProductDTO> data = null;
		try {
			ProductDTO result = productService.getPreviewByProductId(productId);
			data = new ArrayList<>();
			data.add(result);
		} catch (InvestmentsBusinessException e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.PRODUCT + ApplicationConstants.FETCH_RECORD_FAILED, data);
		} catch (Exception e) {
			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
					ApplicationConstants.SERVER_ERROR, null);
		}
		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
				ApplicationConstants.PRODUCT + ApplicationConstants.FETCH_RECORD_SUCESS, data);
	}
  	/**
	 * {@code PUT /update_product_status} :
	 *@implNote: update Product status based on productDTO
	 * @param userid,authToken,productDTO
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    @PutMapping("/update_product_status")
    public ResponseObject updateProductStatus(@RequestHeader(required = true) Long userid,
			@RequestHeader(required = true) String authToken,@RequestBody ProductDTO productDTO) throws URISyntaxException {
        
    	 List<ProductDTO> data = null;
    	 ProductDTO result = null;
 		try {
 			if (productDTO.getId() == null) {
 				return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 						ApplicationConstants.BAD_REQUEST, null);
 			}
 			result = productService.updateProductStatus(productDTO);
 			data = new ArrayList<>();
 			data.add(result);
 		} catch (InvestmentsBusinessException e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.PRODUCT + ApplicationConstants.UPDATE_RECORD_FAILED + "," + e.getMessage(), data);
 		} catch (Exception e) {
 			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
 					ApplicationConstants.SERVER_ERROR, null);
 		}
 		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
 				 ApplicationConstants.PRODUCT +  ApplicationConstants.UPDATE_RECORD_SUCESS , data);
    }
   	/**
	 * {@code GET /get_all_active_products_based_on_pacsId} :
	 *@implNote: get all active Product details based on Pacs Id
	 * @param userid,authToken,pacsId
	 * @return ResponseObject
	 * @author LaxmiPrasannaKumar.S
	 */
    
    @GetMapping("/get_all_active_products_based_on_pacsId")
   	public ResponseObject getAllActiveProductsBasedOnPacsId(@RequestHeader(required = true) Long userid,
   			@RequestHeader(required = true) String authToken,@RequestHeader(required = true) Long pacsId) {
   		List<ProductDTO> result = null;
   		try {
   			result = productService.getAllActiveProductsBasedOnPacsId(pacsId);
   		} catch (InvestmentsBusinessException e) {
   			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
   					ApplicationConstants.FETCH_RECORD_FAILED, result);
   		} catch (Exception e) {
   			return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_ERROR,
   					ApplicationConstants.SERVER_ERROR, null);
   		}
   		return InvestmentsResponseUtil.buildResponse(ApplicationConstants.RES_STATUS_SUCCESS,
   				ApplicationConstants.PRODUCT + ApplicationConstants.FETCH_RECORD_SUCESS, result);
   	}
   
}
