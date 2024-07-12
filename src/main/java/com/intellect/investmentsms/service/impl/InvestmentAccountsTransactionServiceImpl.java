package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InvestmentAccountsTransaction;
import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.InvestmentAccountsTransactionRepository;
import com.intellect.investmentsms.service.InvestmentAccountsTransactionService;
import com.intellect.investmentsms.service.dto.InvestmentAccountsTransactionDTO;
import com.intellect.investmentsms.service.mapper.InvestmentAccountsTransactionMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing
 * {@link com.intellect.investmentsms.domain.InvestmentAccountsTransaction}.
 */
@Service
@Transactional
public class InvestmentAccountsTransactionServiceImpl implements InvestmentAccountsTransactionService {

	private final InvestmentAccountsTransactionRepository investmentAccountsTransactionRepository;

	private final InvestmentAccountsTransactionMapper investmentAccountsTransactionMapper;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	public InvestmentAccountsTransactionServiceImpl(
			InvestmentAccountsTransactionRepository investmentAccountsTransactionRepository,
			InvestmentAccountsTransactionMapper investmentAccountsTransactionMapper) {
		this.investmentAccountsTransactionRepository = investmentAccountsTransactionRepository;
		this.investmentAccountsTransactionMapper = investmentAccountsTransactionMapper;
	}

	/**
	 *@implNote: save the InvestmentAccountsTransaction based on "investmentAccountsTransactionDTO"
	 * @param investmentAccountsTransactionDTO.
	 * @return InvestmentAccountsTransactionDTO
	 * @author k.Saikumar
	 */
	@Override
	public InvestmentAccountsTransactionDTO save(InvestmentAccountsTransactionDTO investmentAccountsTransactionDTO) {
		if (investmentAccountsTransactionDTO != null) {
			if (investmentAccountsTransactionDTO.getId() != null) {
				Optional<InvestmentAccountsTransaction> investmentAccountsTransaction = investmentAccountsTransactionRepository
						.findById(investmentAccountsTransactionDTO.getId());
				if (null != investmentAccountsTransaction && investmentAccountsTransaction.isPresent()
						&& null != investmentAccountsTransaction.get()) {
					investmentAccountsTransactionDTO.setCreatedOn(investmentAccountsTransaction.get().getCreatedOn());
					investmentAccountsTransactionDTO.setCreatedBy(investmentAccountsTransaction.get().getCreatedBy());
				}
			}
		}
		InvestmentAccountsTransaction investmentAccountsTransaction = investmentAccountsTransactionMapper
				.toEntity(investmentAccountsTransactionDTO);
		investmentAccountsTransaction = investmentAccountsTransactionRepository.save(investmentAccountsTransaction);
		investmentAccountsTransactionDTO = investmentAccountsTransactionMapper.toDto(investmentAccountsTransaction);
		return investmentAccountsTransactionDTO;

	}
	/**
   	 *@implNote: getAll InvestmentAccountsTransactions
   	 * @param 
   	 * @return List<InvestmentAccountsTransactionDTO>
   	 * @author k.Saikumar
   	 */
	@Override
	@Transactional(readOnly = true)
	public List<InvestmentAccountsTransactionDTO> findAll() {
		List<InvestmentAccountsTransactionDTO> investmentAccountsTransactionDTOList = investmentAccountsTransactionRepository
				.findAll().stream().map(investmentAccountsTransactionMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
		if (investmentAccountsTransactionDTOList != null && !investmentAccountsTransactionDTOList.isEmpty()) {
			for (InvestmentAccountsTransactionDTO investmentAccountsTransactionDTO : investmentAccountsTransactionDTOList) {
				if (null != investmentAccountsTransactionDTO) {
					if (null != investmentAccountsTransactionDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(investmentAccountsTransactionDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							investmentAccountsTransactionDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
				}
			}
		}
		return investmentAccountsTransactionDTOList;
	}
	/**
   	 *@implNote: get InvestmentAccountsTransaction based on "id"
   	 * @param : id
   	 * @return InvestmentAccountsTransactionDTO
   	 * @author k.Saikumar
   	 */
	@Override
	@Transactional(readOnly = true)
	public InvestmentAccountsTransactionDTO findOne(Long id) {
		InvestmentAccountsTransactionDTO investmentAccountsTransactionDTO = null;
		Optional<InvestmentAccountsTransaction> optInvestmentAccountsTransaction = investmentAccountsTransactionRepository
				.findById(id);
		if (optInvestmentAccountsTransaction.isPresent()) {
			investmentAccountsTransactionDTO = investmentAccountsTransactionMapper
					.toDto(optInvestmentAccountsTransaction.get());
			if (null != investmentAccountsTransactionDTO) {
				if (null != investmentAccountsTransactionDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(investmentAccountsTransactionDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						investmentAccountsTransactionDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
			}
		}
		return investmentAccountsTransactionDTO;
	}
	 /**
   	 *@implNote: remove the InvestmentAccountsTransaction based on "id".
   	 * @param : id
   	 * @return void
   	 * @author k.Saikumar
   	 */
	@Override
	public void delete(Long id) {
		investmentAccountsTransactionRepository.deleteById(id);
	}
	/**
   	 *@implNote: get all InvestmentAccountsTransaction based on "termAccId".
   	 * @param  termAccId.
   	 * @return List<InvestmentAccountsTransactionDTO>.
   	 * @author k.Saikumar
   	 */
	@Override
	public List<InvestmentAccountsTransactionDTO> findByTermAccId(Long termAccId) throws InvestmentsBusinessException {

		List<InvestmentAccountsTransactionDTO> investmentAccountsTransactionDTOList = investmentAccountsTransactionMapper
				.toDto(investmentAccountsTransactionRepository.findByTermAccId(termAccId));
		if (investmentAccountsTransactionDTOList != null && !investmentAccountsTransactionDTOList.isEmpty()) {
			for (InvestmentAccountsTransactionDTO investmentAccountsTransactionDTO : investmentAccountsTransactionDTOList) {
				if (investmentAccountsTransactionDTO != null) {
					if (null != investmentAccountsTransactionDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(investmentAccountsTransactionDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							investmentAccountsTransactionDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
				}
			}
		}

		return investmentAccountsTransactionDTOList;
	}
	/**
   	 *@implNote: get all InvestmentAccountsTransaction based on "termAccId & transactionType ".
   	 * @param  termAccId,transactionType.
   	 * @return List<InvestmentAccountsTransactionDTO>.
   	 * @author k.Saikumar
   	 */
	@Override
	public List<InvestmentAccountsTransactionDTO> findByTermAccIdAndTransactionType(Long termAccId,
			Integer transactionType) throws InvestmentsBusinessException {

		List<InvestmentAccountsTransactionDTO> investmentAccountsTransactionDTOList = investmentAccountsTransactionMapper
				.toDto(investmentAccountsTransactionRepository.findByTermAccIdAndTransactionType(termAccId,
						transactionType));
		if (investmentAccountsTransactionDTOList != null && !investmentAccountsTransactionDTOList.isEmpty()) {
			for (InvestmentAccountsTransactionDTO investmentAccountsTransactionDTO : investmentAccountsTransactionDTOList) {
				if (investmentAccountsTransactionDTO != null) {
					if (null != investmentAccountsTransactionDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(investmentAccountsTransactionDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							investmentAccountsTransactionDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
				}
			}

		}
		return investmentAccountsTransactionDTOList;
	}
}
