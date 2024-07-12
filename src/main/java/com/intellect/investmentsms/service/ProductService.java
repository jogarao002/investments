package com.intellect.investmentsms.service;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.ProductDTO;
import java.util.List;

/**
 * Service Interface for managing {@link com.intellect.investmentsms.domain.Product}.
 */
public interface ProductService {
	/**
	 *@implNote: save the product Details based on "productDTO"
	 * @param productDTO 
	 * @return ProductDTO
	 * @author LaxmiPrasannaKumar.S
	 */
    ProductDTO save(ProductDTO productDTO) throws InvestmentsBusinessException;
	/**
	 *@implNote: find all  the product Details
	 * @param 
	 * @return List<ProductDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
    List<ProductDTO> findAll() throws InvestmentsBusinessException;
	/**
	 *@implNote: find the product Details based on id
	 * @param id
	 * @return ProductDTO
	 * @author LaxmiPrasannaKumar.S
	 */
    ProductDTO findOne(Long id) throws InvestmentsBusinessException;
	/**
	 *@implNote: delete the product Details based on id
	 * @param id
	 * @return void
	 * @author LaxmiPrasannaKumar.S
	 */
    void delete(Long id) throws InvestmentsBusinessException;
	/**
	 *@implNote: get  the preview of product Details based on id
	 * @param id
	 * @return ProductDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	ProductDTO getPreviewByProductId(Long productId)throws InvestmentsBusinessException;
	/**
	 *@implNote: update  the status of product Details based on productDTO
	 * @param productDTO
	 * @return ProductDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	ProductDTO updateProductStatus(ProductDTO productDTO)throws InvestmentsBusinessException;
	/**
	 *@implNote: get all active the product Details based on pacs Id
	 * @param pacsId
	 * @return List<ProductDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<ProductDTO> getAllActiveProductsBasedOnPacsId(Long pacsId)throws InvestmentsBusinessException;


}
