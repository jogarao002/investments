package com.intellect.investmentsms.service;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.InvestedBankDetailsDTO;
import java.util.List;
/**
 * Service Interface for managing {@link com.intellect.investmentsms.domain.InvestedBankDetails}.
 */
public interface InvestedBankDetailsService {
	/**
	 *@implNote: save the invested bank Details based on "investedBankDetailsDTO"
	 * @param investedBankDetailsDTO 
	 * @return InvestedBankDetailsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
    InvestedBankDetailsDTO save(InvestedBankDetailsDTO investedBankDetailsDTO) throws InvestmentsBusinessException;
	/**
	 *@implNote: find all invested bank Details 
	 * @param 
	 * @return List<InvestedBankDetailsDTO>
	 * @author LaxmiPrasannaKumar.S
	 */
    List<InvestedBankDetailsDTO> findAll()throws InvestmentsBusinessException;
	/**
	 *@implNote: get the invested bank Details based on id
	 * @param id
	 * @return InvestedBankDetailsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
    InvestedBankDetailsDTO findOne(Long id)throws InvestmentsBusinessException;

	/**
	 *@implNote: delete the invested bank Details based on id
	 * @param id
	 * @return void
	 * @author LaxmiPrasannaKumar.S
	 */
    void delete(Long id)throws InvestmentsBusinessException;
	/**
	 *@implNote: update the invested bank Details status based on investedBankDetailsDTO
	 * @param investedBankDetailsDTO
	 * @return InvestedBankDetailsDTO
	 * @author LaxmiPrasannaKumar.S
	 */
	InvestedBankDetailsDTO investedBankDetailsStatus(InvestedBankDetailsDTO investedBankDetailsDTO) throws InvestmentsBusinessException;


}
