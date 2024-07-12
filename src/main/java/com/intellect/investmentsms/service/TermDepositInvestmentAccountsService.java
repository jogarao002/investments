package com.intellect.investmentsms.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.TermDepositInvestmentAccountsDTO;

/**
 * Service Interface for managing
 * {@link com.intellect.investmentsms.domain.TermDepositInvestmentAccounts}.
 */
public interface TermDepositInvestmentAccountsService {
	/**
	 * @implNote: save the response object by "termDepositInvestmentAccountsDTO" or Updates an termDepositInvestmentAccountsDTO.
	 * @param termDepositInvestmentAccountsDTO.
	 * @return TermDepositInvestmentAccountsDTO.
	 * @throws InvestmentsBusinessException, IOException.
	 * @author jogarao
	 */
	TermDepositInvestmentAccountsDTO save(TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO)
			throws InvestmentsBusinessException, IOException;

	/**
	 * @implNote: get all termDepositInvestmentAccountsDTO.
	 * @param null.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	List<TermDepositInvestmentAccountsDTO> findAll() throws InvestmentsBusinessException;

	/**
	 * @implNote: get termDepositInvestmentAccountsDTO by "id".
	 * @param id.
	 * @return TermDepositInvestmentAccountsDTO.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	TermDepositInvestmentAccountsDTO findOne(Long id) throws InvestmentsBusinessException;

	/**
	 * @implNote: delete termDepositInvestmentAccounts by "id".
	 * @param id.
	 * @return void.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	void delete(Long id) throws InvestmentsBusinessException;

	/**
	 * @implNote: get termDepositInvestmentAccountsDTO by "productId".
	 * @param productId.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	List<TermDepositInvestmentAccountsDTO> findByProductId(Long productId) throws InvestmentsBusinessException;

	/**
	 * @implNote: get termDepositInvestmentAccountsDTO by "pacId" and "branchId".
	 * @param pacId, branchId.
	 * @return List<TermDepositInvestmentAccountsDTO>.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	List<TermDepositInvestmentAccountsDTO> findByPacIdAndBranchId(Long pacId, Long branchId)
			throws InvestmentsBusinessException;

	/**
	 * @implNote: get termDepositInvestmentAccountsDTO by "termAccountId".
	 * @param termAccountId.
	 * @return TermDepositInvestmentAccountsDTO.
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	TermDepositInvestmentAccountsDTO getPreviewByTermAccountId(Long termAccountId) throws InvestmentsBusinessException;

	/**
	 * @implNote: update the response object by "termDepositInvestmentAccountsDTO".
	 * @param termDepositInvestmentAccountsDTO.
	 * @return TermDepositInvestmentAccountsDTO
	 * @throws InvestmentsBusinessException.
	 * @author jogarao
	 */
	TermDepositInvestmentAccountsDTO getTermDepositInvestmentAccountStatus(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) throws InvestmentsBusinessException;

	TermDepositInvestmentAccountsDTO getMaturityAmountOnForeclosure(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO)
			throws InvestmentsBusinessException, IOException;

	TermDepositInvestmentAccountsDTO saveForeClosureDetails(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) throws InvestmentsBusinessException;

	TermDepositInvestmentAccountsDTO getRDMaturityAmountOnForeclosure(
			TermDepositInvestmentAccountsDTO termDepositInvestmentAccountsDTO) throws InvestmentsBusinessException;
	/**
	 *@implNote: get the Grid Data based on "pacsId,branchId"
	 * @param pacsId, branchId
	 * @return List<TermDepositInvestmentAccountsDTO>
	 * @author LaxmiPrasannaKumar.S
	 */

	List<TermDepositInvestmentAccountsDTO> getAllGridData(Long pacsId, Long branchId)throws InvestmentsBusinessException;

}
