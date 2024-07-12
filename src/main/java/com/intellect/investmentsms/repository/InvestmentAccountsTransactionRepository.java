package com.intellect.investmentsms.repository;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InvestmentAccountsTransaction;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InvestmentAccountsTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestmentAccountsTransactionRepository extends JpaRepository<InvestmentAccountsTransaction, Long> {
	
	/**
   	 *@implNote: get all InvestmentAccountsTransaction based on "termAccId".
   	 * @param  termAccId.
   	 * @return List<InvestmentAccountsTransactionDTO>.
   	 * @author k.Saikumar
   	 */
	List<InvestmentAccountsTransaction> findByTermAccId(Long termAccId);
	
	/**
   	 *@implNote: get all InvestmentAccountsTransaction based on "termAccId & transactionType ".
   	 * @param  termAccId,transactionType.
   	 * @return List<InvestmentAccountsTransactionDTO>.
   	 * @author k.Saikumar
   	 */
	List<InvestmentAccountsTransaction> findByTermAccIdAndTransactionType(Long termAccId, Integer transactionType);
}
