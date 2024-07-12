package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.InvestmentsWorkFlowDTO;


public interface InvestmentsWorkFlowService {
   
	/**
	 *@implNote: Save the InvestmentsWorkFlow by "investmentsWorkFlowDTO".
	 * @param investmentsWorkFlowDTO. 
	 * @return the DTO Object InvestmentsWorkFlowDTO.
	 * @author Dilip Kumar.G
	 */
	
    InvestmentsWorkFlowDTO save(InvestmentsWorkFlowDTO investmentsWorkFlowDTO) throws InvestmentsBusinessException;
    
    /**
	 *@implNote: Get all the InvestmentsWorkFlow.
	 * @param. 
	 * @return the List List<InvestmentsWorkFlowDTO>.
	 * @author Dilip Kumar.G
	 */
    
    List<InvestmentsWorkFlowDTO> findAll()throws InvestmentsBusinessException;

    /**
	 *@implNote: Get the InvestmentsWorkFlow by "id".
	 * @param id. 
	 * @return the DTO Object InvestmentsWorkFlowDTO.
	 * @author Dilip Kumar.G
	 */
    
    InvestmentsWorkFlowDTO findOne(Long id) throws InvestmentsBusinessException;

    /**
	 *@implNote: Delete the InvestmentsWorkFlow by "id".
	 * @param id. 
	 * @return void.
	 * @author Dilip Kumar.G
	 */
    
    void delete(Long id)throws InvestmentsBusinessException;
}
