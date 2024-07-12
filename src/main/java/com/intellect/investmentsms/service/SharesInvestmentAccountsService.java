package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.SharesInvestmentAccountsDTO;

/**
 * Service Interface for managing {@link com.intellect.investmentsms.domain.SharesInvestmentAccounts}.
 */
public interface SharesInvestmentAccountsService {
	/**
	 * @implNote: save the sharesInvestmentAccountsDTO.
	 * @param sharesInvestmentAccountsDTO.
	 * @return sharesInvestmentAccountsDTO.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    SharesInvestmentAccountsDTO save(SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO) throws InvestmentsBusinessException;

    /**
	 * @implNote: get all sharesInvestmentAccountsDTO.
	 * @return the List<SharesInvestmentAccountsDTO>.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    List<SharesInvestmentAccountsDTO> findAll() throws InvestmentsBusinessException;

    /**
	 * @implNote: get sharesInvestmentAccountsDTO by id.
	 * @param id.
	 * @return sharesInvestmentAccountsDTO.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    SharesInvestmentAccountsDTO findOne(Long id) throws InvestmentsBusinessException;
    
    /**
	 * @implNote: delete sharesInvestmentAccountsDTO by id.
	 * @param id.
	 * @return void.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    void delete(Long id) throws InvestmentsBusinessException;
    
    /**
	 * @implNote: get the list of sharesInvestmentAccountsDTO by pacsId and branchId.
	 * @param pacsId and branchId.
	 * @return the List<SharesInvestmentAccountsDTO>.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    List<SharesInvestmentAccountsDTO> getAllSharesInvestmentAccountsByPacsIdAndBranchId(Long pacsId, Long branchId) throws InvestmentsBusinessException;
    
    /**
	 * @implNote: get the list of sharesInvestmentAccountsDTO by productId.
	 * @param productId.
	 * @return the List<SharesInvestmentAccountsDTO>.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    List<SharesInvestmentAccountsDTO> getAllByProductId(Long id) throws InvestmentsBusinessException;
    
    /**
	 * @implNote: update sharesInvestmentAccountsDTO status.
	 * @param sharesInvestmentAccountsDTO.
	 * @return sharesInvestmentAccountsDTO.
	 * @throws InvestmentsBusinessException when exception occurs.
	 * @author M.Bhavana
	 */
    SharesInvestmentAccountsDTO sharesInvestmentAccountsApproval(SharesInvestmentAccountsDTO sharesInvestmentAccountsDTO) throws InvestmentsBusinessException;;
}
