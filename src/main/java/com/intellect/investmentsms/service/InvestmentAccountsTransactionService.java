package com.intellect.investmentsms.service;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.InvestmentAccountsTransactionDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.intellect.investmentsms.domain.InvestmentAccountsTransaction}.
 */
public interface InvestmentAccountsTransactionService {
	/**
	 *@implNote: save the InvestmentAccountsTransaction based on "investmentAccountsTransactionDTO"
	 * @param investmentAccountsTransactionDTO.
	 * @return InvestmentAccountsTransactionDTO
	 * @author k.Saikumar
	 */
    InvestmentAccountsTransactionDTO save(InvestmentAccountsTransactionDTO investmentAccountsTransactionDTO) throws InvestmentsBusinessException;

    /**
   	 *@implNote: getAll InvestmentAccountsTransactions
   	 * @param 
   	 * @return List<InvestmentAccountsTransactionDTO>
   	 * @author k.Saikumar
   	 */
    List<InvestmentAccountsTransactionDTO> findAll() throws InvestmentsBusinessException;

    /**
   	 *@implNote: get InvestmentAccountsTransaction based on "id"
   	 * @param : id
   	 * @return InvestmentAccountsTransactionDTO
   	 * @author k.Saikumar
   	 */
   InvestmentAccountsTransactionDTO findOne(Long id) throws InvestmentsBusinessException;
   /**
  	 *@implNote: remove the InvestmentAccountsTransaction based on "id".
  	 * @param : id
  	 * @return void
  	 * @author k.Saikumar
  	 */
    void delete(Long id) throws InvestmentsBusinessException;

    /**
   	 *@implNote: get all InvestmentAccountsTransaction based on "termAccId".
   	 * @param  termAccId.
   	 * @return List<InvestmentAccountsTransactionDTO>.
   	 * @author k.Saikumar
   	 */
	List<InvestmentAccountsTransactionDTO> findByTermAccId(Long termAccId)throws InvestmentsBusinessException;

	/**
   	 *@implNote: get all InvestmentAccountsTransaction based on "termAccId & transactionType ".
   	 * @param  termAccId,transactionType.
   	 * @return List<InvestmentAccountsTransactionDTO>.
   	 * @author k.Saikumar
   	 */
	List<InvestmentAccountsTransactionDTO> findByTermAccIdAndTransactionType(Long termAccId, Integer transactionType)throws InvestmentsBusinessException;
}
