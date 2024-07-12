package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.InterestPolicyConfigDTO;


public interface InterestPolicyConfigService {
   
	/**
	 *@implNote: Save the InterestPolicyConfig by "interestPolicyConfigDTO".
	 * @param interestPolicyConfigDTO. 
	 * @return the DTO Object InterestPolicyConfigDTO.
	 * @author Dilip Kumar.G
	 */
	
    InterestPolicyConfigDTO save(InterestPolicyConfigDTO interestPolicyConfigDTO) throws InvestmentsBusinessException;
    
    /**
	 *@implNote: Update the InterestPolicyConfig by "interestPolicyConfigDTO".
	 * @param interestPolicyConfigDTO. 
	 * @return the DTO Object InterestPolicyConfigDTO.
	 * @author Dilip Kumar.G
	 */
    
    InterestPolicyConfigDTO update(InterestPolicyConfigDTO interestPolicyConfigDTO) throws InvestmentsBusinessException;

    /**
	 *@implNote: Get all the InterestPolicyConfig.
	 * @param . 
	 * @return the List List<InterestPolicyConfigDTO>.
	 * @author Dilip Kumar.G
	 */
    
    List<InterestPolicyConfigDTO> findAll() throws InvestmentsBusinessException;

    /**
	 *@implNote: Get the InterestPolicyConfig by "id".
	 * @param id. 
	 * @return the DTO Object InterestPolicyConfigDTO.
	 * @author Dilip Kumar.G
	 */
    
    InterestPolicyConfigDTO findOne(Long id) throws InvestmentsBusinessException;
    
    /**
   	 *@implNote: Delete the InterestPolicyConfig by "id".
   	 * @param id. 
   	 * @return void.
   	 * @author Dilip Kumar.G
   	 */

    void delete(Long id) throws InvestmentsBusinessException;
    
    /**
   	 *@implNote: Get the InterestPolicyConfig by "productid".
   	 * @param productid. 
   	 * @return the List List<InterestPolicyConfigDTO>.
   	 * @author Dilip Kumar.G
   	 */

	List<InterestPolicyConfigDTO> getInterestPolicyConfigByProductId(Long productid)throws InvestmentsBusinessException;
}
