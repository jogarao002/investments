package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.InvestmentAccountInstallments;
import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.domain.TermDepositInvestmentAccounts;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.InvestmentAccountInstallmentsRepository;
import com.intellect.investmentsms.repository.TermDepositInvestmentAccountsRepository;
import com.intellect.investmentsms.service.InvestmentAccountInstallmentsService;
import com.intellect.investmentsms.service.dto.InvestmentAccountInstallmentsDTO;
import com.intellect.investmentsms.service.mapper.InvestmentAccountInstallmentsMapper;
import com.intellect.investmentsms.service.mapper.TermDepositInvestmentAccountsMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing
 * {@link com.intellect.investmentsms.domain.InvestmentAccountInstallments}.
 */
@Service
@Transactional
public class InvestmentAccountInstallmentsServiceImpl implements InvestmentAccountInstallmentsService {

	private final InvestmentAccountInstallmentsRepository investmentAccountInstallmentsRepository;

	private final InvestmentAccountInstallmentsMapper investmentAccountInstallmentsMapper;

	@Autowired
	private TermDepositInvestmentAccountsRepository termDepositInvestmentAccountsRepository;

	@Autowired
	private TermDepositInvestmentAccountsMapper termDepositInvestmentAccountsMapper;

	@Autowired
	private CommonStatusRepository commonStatusRepository;

	public InvestmentAccountInstallmentsServiceImpl(
			InvestmentAccountInstallmentsRepository investmentAccountInstallmentsRepository,
			InvestmentAccountInstallmentsMapper investmentAccountInstallmentsMapper) {
		this.investmentAccountInstallmentsRepository = investmentAccountInstallmentsRepository;
		this.investmentAccountInstallmentsMapper = investmentAccountInstallmentsMapper;
	}

	@Override
	public InvestmentAccountInstallmentsDTO save(InvestmentAccountInstallmentsDTO investmentAccountInstallmentsDTO)
			throws InvestmentsBusinessException {

		if (investmentAccountInstallmentsDTO != null) {
			if (investmentAccountInstallmentsDTO.getId() != null) {
				Optional<InvestmentAccountInstallments> investmentAccountInstallmentsOptional = investmentAccountInstallmentsRepository
						.findById(investmentAccountInstallmentsDTO.getId());
				if (null != investmentAccountInstallmentsOptional && investmentAccountInstallmentsOptional.isPresent()
						&& null != investmentAccountInstallmentsOptional.get()) {
					investmentAccountInstallmentsDTO
							.setCreatedOn(investmentAccountInstallmentsOptional.get().getCreatedOn());
					investmentAccountInstallmentsDTO
							.setCreatedBy(investmentAccountInstallmentsOptional.get().getCreatedBy());
				}
			}
		}
		InvestmentAccountInstallments investmentAccountInstallments = investmentAccountInstallmentsMapper
				.toEntity(investmentAccountInstallmentsDTO);
		investmentAccountInstallments = investmentAccountInstallmentsRepository.save(investmentAccountInstallments);
		return investmentAccountInstallmentsMapper.toDto(investmentAccountInstallments);
	}

	@Override
	@Transactional(readOnly = true)
	public List<InvestmentAccountInstallmentsDTO> findAll() {
		List<InvestmentAccountInstallmentsDTO> investmentAccountInstallmentsDTOList = investmentAccountInstallmentsRepository
				.findAll().stream().map(investmentAccountInstallmentsMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
		if (investmentAccountInstallmentsDTOList != null && !investmentAccountInstallmentsDTOList.isEmpty()) {
			for (InvestmentAccountInstallmentsDTO investmentAccountInstallmentsDTO : investmentAccountInstallmentsDTOList) {
				if (null != investmentAccountInstallmentsDTO) {
					if (null != investmentAccountInstallmentsDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(investmentAccountInstallmentsDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							investmentAccountInstallmentsDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
				}
			}
		}
		return investmentAccountInstallmentsDTOList;
	}

	@Override
	@Transactional(readOnly = true)
	public InvestmentAccountInstallmentsDTO findOne(Long id) {

		InvestmentAccountInstallmentsDTO investmentAccountInstallmentsDTO = null;
		Optional<InvestmentAccountInstallments> optInvestmentAccountInstallments = investmentAccountInstallmentsRepository
				.findById(id);
		if (optInvestmentAccountInstallments.isPresent()) {
			investmentAccountInstallmentsDTO = investmentAccountInstallmentsMapper
					.toDto(optInvestmentAccountInstallments.get());
			if (null != investmentAccountInstallmentsDTO) {
				if (null != investmentAccountInstallmentsDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(investmentAccountInstallmentsDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						investmentAccountInstallmentsDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
			}
		}
		return investmentAccountInstallmentsDTO;
	}

	@Override
	public void delete(Long id) throws InvestmentsBusinessException {
		InvestmentAccountInstallments investmentAccountInstallments = null;

		Optional<InvestmentAccountInstallments> investmentAccountInstallmentsOptional = investmentAccountInstallmentsRepository
				.findById(id);
		if (investmentAccountInstallmentsOptional.isPresent()) {
			investmentAccountInstallments = investmentAccountInstallmentsOptional.get();
			investmentAccountInstallments.setStatus(ApplicationConstants.IN_ACTIVE);
			investmentAccountInstallments = investmentAccountInstallmentsRepository.save(investmentAccountInstallments);
		}
	}

	@Override
	public List<InvestmentAccountInstallmentsDTO> getByTermAccId(Long termAccId) throws InvestmentsBusinessException {
		List<InvestmentAccountInstallmentsDTO> investmentAccountInstallmentsDTOList = null;
		if (termAccId != null) {
			investmentAccountInstallmentsDTOList = investmentAccountInstallmentsMapper
					.toDto(investmentAccountInstallmentsRepository.findByTermAccId(termAccId));
			if (investmentAccountInstallmentsDTOList != null && !investmentAccountInstallmentsDTOList.isEmpty()) {
				for (InvestmentAccountInstallmentsDTO investmentAccountInstallmentsDTO : investmentAccountInstallmentsDTOList) {
					if (null != investmentAccountInstallmentsDTO) {
						if (null != investmentAccountInstallmentsDTO.getStatus()) {
							Optional<CommonStatus> optCommonStatus = commonStatusRepository
									.findById(investmentAccountInstallmentsDTO.getStatus().longValue());
							if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
								investmentAccountInstallmentsDTO.setStatusName(optCommonStatus.get().getName());
							}
						}
					}
				}
			}
		}
		return investmentAccountInstallmentsDTOList;
	}

}
