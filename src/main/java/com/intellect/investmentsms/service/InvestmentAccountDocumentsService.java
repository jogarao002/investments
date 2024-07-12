package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.InvestmentAccountDocumentsDTO;

/**
 * Service Interface for managing {@link com.intellect.investmentsms.domain.InvestmentAccountDocuments}.
 */
public interface InvestmentAccountDocumentsService {
	/**
	 * @implNote: save the InvestmentAccountDocumentsDTO.
	 * @param investmentAccountDocumentsDTO.
	 * @return InvestmentAccountDocumentsDTO.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    InvestmentAccountDocumentsDTO save(InvestmentAccountDocumentsDTO investmentAccountDocumentsDTO) throws InvestmentsBusinessException;

    
    /**
	 * @implNote: get all InvestmentAccountDocumentsDTO.
	 * @return the List<InvestmentAccountDocumentsDTO>.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    List<InvestmentAccountDocumentsDTO> findAll() throws InvestmentsBusinessException;

    /**
	 * @implNote: get InvestmentAccountDocumentsDTO by id.
	 * @param id.
	 * @return InvestmentAccountDocumentsDTO.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    InvestmentAccountDocumentsDTO findOne(Long id) throws InvestmentsBusinessException;

    /**
	 * @implNote: delete InvestmentAccountDocumentsDTO by id.
	 * @param id.
	 * @return void.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    void delete(Long id) throws InvestmentsBusinessException;
    
    /**
	 * @implNote: get the list of InvestmentAccountDocumentsDTO by productId.
	 * @param productId.
	 * @return the List<InvestmentAccountDocumentsDTO>.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    List<InvestmentAccountDocumentsDTO> getAllByProductId(Long productId) throws InvestmentsBusinessException;
    
    /**
	 * @implNote: get the list of InvestmentAccountDocumentsDTO by termAccountId.
	 * @param termAccountId.
	 * @return the List<InvestmentAccountDocumentsDTO>.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    List<InvestmentAccountDocumentsDTO> getAllByTermAccountId(Long termAccountId) throws InvestmentsBusinessException;
}
