package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.ProductAssociatedBankDetailsDTO;


public interface ProductAssociatedBankDetailsService {
    
	/**
	 *@implNote: Save the ProductAssociatedBankDetails by "productAssociatedBankDetailsDTO".
	 * @param productAssociatedBankDetailsDTO. 
	 * @return the DTO Object ProductAssociatedBankDetailsDTO.
	 * @author Dilip Kumar.G
	 */
	
    ProductAssociatedBankDetailsDTO save(ProductAssociatedBankDetailsDTO productAssociatedBankDetailsDTO)throws InvestmentsBusinessException;
    
    /**
	 *@implNote: Update the ProductAssociatedBankDetails by "productAssociatedBankDetailsDTO".
	 * @param productAssociatedBankDetailsDTO. 
	 * @return the DTO Object ProductAssociatedBankDetailsDTO.
	 * @author Dilip Kumar.G
	 */
    
    ProductAssociatedBankDetailsDTO update(ProductAssociatedBankDetailsDTO productAssociatedBankDetailsDTO)throws InvestmentsBusinessException;

    /**
	 *@implNote: Get All the ProductAssociatedBankDetails.
	 * @param . 
	 * @return the List List<ProductAssociatedBankDetailsDTO>.
	 * @author Dilip Kumar.G
	 */
    
    List<ProductAssociatedBankDetailsDTO> findAll()throws InvestmentsBusinessException;
    
    /**
	 *@implNote: Get the ProductAssociatedBankDetails by "id".
	 * @param id . 
	 * @return the Object ProductAssociatedBankDetailsDTO.
	 * @author Dilip Kumar.G
	 */

    ProductAssociatedBankDetailsDTO findOne(Long id)throws InvestmentsBusinessException;
    
    /**
	 *@implNote: Delete the ProductAssociatedBankDetails by "id".
	 * @param id . 
	 * @return void.
	 * @author Dilip Kumar.G
	 */

    void delete(Long id)throws InvestmentsBusinessException;
    
    /**
	 *@implNote: Get the ProductAssociatedBankDetails by "productid".
	 * @param productid . 
	 * @return the List List<ProductAssociatedBankDetailsDTO>.
	 * @author Dilip Kumar.G
	 */

	List<ProductAssociatedBankDetailsDTO> getProductAssociatedBankDetailsByProductId(Long productid) throws InvestmentsBusinessException;

	
}
