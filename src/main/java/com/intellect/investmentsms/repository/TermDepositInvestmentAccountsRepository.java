package com.intellect.investmentsms.repository;

import com.intellect.investmentsms.domain.TermDepositInvestmentAccounts;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TermDepositInvestmentAccounts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TermDepositInvestmentAccountsRepository extends JpaRepository<TermDepositInvestmentAccounts, Long> {
	
	/**
	 * @implNote get the termDepositInvestmentAccounts by "accountNumber"
	 * @param accountNumber
	 * @return List<TermDepositInvestmentAccounts>
	 * @author jogarao
	 */
	List<TermDepositInvestmentAccounts> findByAccountNumber(String accountNumber);
	
	/**
	 * @implNote get the termDepositInvestmentAccounts by "productId"
	 * @param productId
	 * @return List<TermDepositInvestmentAccounts>
	 * @author jogarao
	 */
	List<TermDepositInvestmentAccounts> findByProductId(Long productId);
	
	/**
	 * @implNote get the termDepositInvestmentAccounts by "pacsId" and "branchId"
	 * @param pacsId
	 * @param branchId
	 * @return List<TermDepositInvestmentAccounts>
	 * @author jogarao
	 */
	List<TermDepositInvestmentAccounts> getByPacsIdAndBranchId(Long pacsId, Long branchId);
	/**
	 *@implNote: get the Term Deposit Investment Accounts  based on"pacsId and branchId"
	 * @param pacsId ,branchId
	 * @return List<TermDepositInvestmentAccounts>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<TermDepositInvestmentAccounts> findByPacsIdAndBranchId(Long pacsId, Long branchId);
	
}
