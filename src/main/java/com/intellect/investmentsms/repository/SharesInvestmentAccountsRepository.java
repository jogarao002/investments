package com.intellect.investmentsms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intellect.investmentsms.domain.SharesInvestmentAccounts;

/**
 * Spring Data JPA repository for the SharesInvestmentAccounts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SharesInvestmentAccountsRepository extends JpaRepository<SharesInvestmentAccounts, Long> {
	
	/**
	 * @implNote: get the list of sharesInvestmentAccounts by productId.
	 * @param productId.
	 * @return the List<SharesInvestmentAccounts>.
	 * @author M.Bhavana
	 */
	List<SharesInvestmentAccounts> findAllByProductId(Long productId);
	
	/**
	 * @implNote: get the list of sharesInvestmentAccounts by shareCertificateNumber.
	 * @param shareCertificateNumber.
	 * @return the List<SharesInvestmentAccounts>.
	 * @author M.Bhavana
	 */
	List<SharesInvestmentAccounts> findByShareCertificateNumber(String shareCertificateNumber);
	
	/**
	 * @implNote: get the list of sharesInvestmentAccountsDTO by pacsId and branchId.
	 * @param pacsId and branchId.
	 * @return the List<SharesInvestmentAccountsDTO>.
	 * @author M.Bhavana
	 */
	List<SharesInvestmentAccounts> findAllByPacsIdAndBranchId(Long pacsId, Long branchId);
	/**
	 *@implNote: get the shares Investment Accounts  based on"pacsId and branchId"
	 * @param pacsId ,branchId
	 * @return List<SharesInvestmentAccounts>
	 * @author LaxmiPrasannaKumar.S
	 */
	List<SharesInvestmentAccounts> findByPacsIdAndBranchId(Long pacsId, Long branchId);
}
