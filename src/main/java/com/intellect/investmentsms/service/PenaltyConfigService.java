package com.intellect.investmentsms.service;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.PenaltyConfigDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.intellect.investmentsms.domain.PenaltyConfig}.
 */
public interface PenaltyConfigService {
	
	/**
	 *@implNote: Save a penaltyConfig.
	 * @param penaltyConfigDTO.
	 * @return PenaltyConfigDTO.
	 * @author Dileep_Kumar.Gedela.
	 */
    PenaltyConfigDTO save(PenaltyConfigDTO penaltyConfigDTO) throws InvestmentsBusinessException;
    
    /**
   	 *@implNote: get all the penaltyConfigs. 
   	 * @param 
   	 * @return List<PenaltyConfigDTO>.
   	 * @author Dileep_Kumar.Gedela.
   	 */
    List<PenaltyConfigDTO> findAll()throws InvestmentsBusinessException;

    /**
   	 *@implNote: get the penaltyConfig based on id.
   	 * @param  id.
   	 * @return PenaltyConfigDTO.
   	 * @author Dileep_Kumar.Gedela.
   	 */
    PenaltyConfigDTO findOne(Long id)throws InvestmentsBusinessException;

    /**
   	 *@implNote: remove penaltyConfig based on id.
   	 * @param  id.
   	 * @return 
   	 * @author Dileep_Kumar.Gedela
   	 */
    void delete(Long id)throws InvestmentsBusinessException;
    
	/**
   	 *@implNote: get all the penaltyConfigs based on productId.
   	 * @param productId.
   	 * @return List<PenaltyConfigDTO>.
   	 * @author Dileep_Kumar.Gedela.
   	 */
	List<PenaltyConfigDTO> findByProductId(Long productId)throws InvestmentsBusinessException;
}
