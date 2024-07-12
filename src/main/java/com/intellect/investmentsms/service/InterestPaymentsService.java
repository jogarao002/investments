package com.intellect.investmentsms.service;

import java.util.List;
import java.util.Optional;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.InterestPaymentsDTO;

/**
 * Service Interface for managing {@link com.intellect.investmentsms.domain.InterestPayments}.
 */
public interface InterestPaymentsService {
	/**
	 *@implNote: Save the InterestPayments by "interestPaymentsDTO".
	 * @param interestPaymentsDTO. 
	 * @return the DTO Object InterestPaymentsDTO.
	 * @author K.Ramu
	 */
    InterestPaymentsDTO save(InterestPaymentsDTO interestPaymentsDTO) throws InvestmentsBusinessException;

    /**
	 *@implNote: Get all the InterestPayments Details.
	 * @param . 
	 * @return the List List<InterestPaymentsDTO>.
	 * @author K.Ramu
	 */
    List<InterestPaymentsDTO> findAll() throws InvestmentsBusinessException;

    /**
	 *@implNote: Get the InterestPayments by "id".
	 * @param id. 
	 * @return the DTO Object InterestPaymentsDTO.
	 * @author K.Ramu
	 */
    InterestPaymentsDTO findOne(Long id) throws InvestmentsBusinessException;

    /**
	 *@implNote: Delete the InterestPayments by "id".
	 * @param id. 
	 * @return void.
	 * @author K.Ramu
	 */
    void delete(Long id) throws InvestmentsBusinessException;

    /**
	 *@implNote: Get the InterestPayments by "products".
	 * @param products. 
	 * @return the list List<InterestPaymentsDTO>.
	 * @author K.Ramu
	 */
	List<InterestPaymentsDTO> getInterestPaymentsListByProducts(Long products) throws InvestmentsBusinessException;

	/**
	 *@implNote: Get the InterestPayments by "termAccId".
	 * @param termAccId. 
	 * @return the list List<InterestPaymentsDTO>.
	 * @author K.Ramu
	 */
	List<InterestPaymentsDTO> getInterestPaymentsListByTermAccId(Long termAccId) throws InvestmentsBusinessException;
}
