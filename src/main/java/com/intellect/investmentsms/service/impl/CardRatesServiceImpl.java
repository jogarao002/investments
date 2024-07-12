package com.intellect.investmentsms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intellect.investmentsms.domain.CardRates;
import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.domain.Product;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CardRatesRepository;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.repository.ProductRepository;
import com.intellect.investmentsms.service.CardRatesService;
import com.intellect.investmentsms.service.dto.CardRatesDTO;
import com.intellect.investmentsms.service.mapper.CardRatesMapper;
import com.intellect.investmentsms.util.ApplicationConstants;

/**
 * Service Implementation for managing
 * {@link com.intellect.termdepositsms.domain.CardRates}.
 */
@Service
@Transactional
public class CardRatesServiceImpl implements CardRatesService {

	private final CardRatesRepository cardRatesRepository;

	private final CardRatesMapper cardRatesMapper;

	@Autowired
	private ProductRepository productRepository;

	private final CommonStatusRepository commonStatusRepository;

	public CardRatesServiceImpl(CardRatesRepository cardRatesRepository, CardRatesMapper cardRatesMapper, CommonStatusRepository commonStatusRepository) {
		this.cardRatesRepository = cardRatesRepository;
		this.cardRatesMapper = cardRatesMapper;
		this.commonStatusRepository = commonStatusRepository;
	}

	@Override
	public CardRatesDTO save(CardRatesDTO cardRatesDTO) throws InvestmentsBusinessException {
		if (cardRatesDTO != null) {
			duplicateCheckCardRates(cardRatesDTO);
			if (cardRatesDTO.getId() != null) {
				Optional<CardRates> optCardRates = cardRatesRepository.findById(cardRatesDTO.getId());
				CardRates cardRate = null;
				if (optCardRates.isPresent()) {
					cardRate = optCardRates.get();
				}
				if (optCardRates != null) {
					if (cardRate.getCreatedBy() != null)
						cardRatesDTO.setCreatedBy(cardRate.getCreatedBy());
					if (cardRate.getCreatedOn() != null)
						cardRatesDTO.setCreatedOn(cardRate.getCreatedOn());
				}
			}

		}
		CardRates optCardRates = cardRatesMapper.toEntity(cardRatesDTO);
		optCardRates = cardRatesRepository.save(optCardRates);
		CardRatesDTO result = cardRatesMapper.toDto(optCardRates);
		return result;

	}

	private void duplicateCheckCardRates(CardRatesDTO cardRatesDTO) throws InvestmentsBusinessException {
		List<CardRates> cardRatesList = cardRatesRepository
				.findByPacsIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndStatus(
						cardRatesDTO.getPacsId(), cardRatesDTO.getEffectiveStartDate(), cardRatesDTO.getMinTenure(),
						cardRatesDTO.getMinTenure(), cardRatesDTO.getStatus());
		if (cardRatesList == null || (cardRatesList != null && cardRatesList.isEmpty())) {
			cardRatesList = cardRatesRepository
					.findByPacsIdAndEffectiveStartDateLessThanEqualAndEffectiveEndDateIsNullAndMinTenureLessThanEqualAndMaxTenureGreaterThanEqualAndStatus(
							cardRatesDTO.getPacsId(), cardRatesDTO.getEffectiveStartDate(), cardRatesDTO.getMaxTenure(),
							cardRatesDTO.getMaxTenure(), cardRatesDTO.getStatus());
		}
		if (cardRatesList != null && !cardRatesList.isEmpty()) {
			for (CardRates cardRate : cardRatesList) {
				if (cardRate != null && null != cardRate.getId() && !cardRate.getId().equals(cardRate.getId()))
					throw new InvestmentsBusinessException(ApplicationConstants.MIN_OR_MAX_TENURE_ALREADY_EXIST);
			}

		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<CardRatesDTO> findAll() {
		List<CardRatesDTO> cardRatesDTOList = cardRatesRepository.findAll().stream().map(cardRatesMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
		if (cardRatesDTOList != null && !cardRatesDTOList.isEmpty()) {
			for (CardRatesDTO cardRatesDTO : cardRatesDTOList) {
				if (null != cardRatesDTO) {
					if (null != cardRatesDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(cardRatesDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							cardRatesDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
				}
			}
		}
		return cardRatesDTOList;
	}

	@Override
	public CardRatesDTO findOne(Long id) {
		CardRatesDTO cardRatesDTO = null;
		Optional<CardRates> optCardRates = cardRatesRepository.findById(id);
		if (optCardRates.isPresent()) {
			cardRatesDTO = cardRatesMapper.toDto(optCardRates.get());
			if (null != cardRatesDTO) {
				if (null != cardRatesDTO.getStatus()) {
					Optional<CommonStatus> optCommonStatus = commonStatusRepository
							.findById(cardRatesDTO.getStatus().longValue());
					if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
						cardRatesDTO.setStatusName(optCommonStatus.get().getName());
					}
				}
			}
		}
		return cardRatesDTO;
	}

	@Override
	public List<CardRatesDTO> getCardRatesByPacsId(Long pacsId) throws InvestmentsBusinessException {
		List<CardRatesDTO> cardRatesDTOList = cardRatesMapper
				.toDto(cardRatesRepository.findByPacsIdAndStatus(pacsId, ApplicationConstants.ACTIVE));
		if(null != cardRatesDTOList && !cardRatesDTOList.isEmpty()) {
			for (CardRatesDTO cardRatesDTO : cardRatesDTOList) {
				if (null != cardRatesDTO) {
					if (null != cardRatesDTO.getStatus()) {
						Optional<CommonStatus> optCommonStatus = commonStatusRepository
								.findById(cardRatesDTO.getStatus().longValue());
						if (optCommonStatus.isPresent() && null != optCommonStatus.get().getName()) {
							cardRatesDTO.setStatusName(optCommonStatus.get().getName());
						}
					}
				}
			}
		}
		
		return cardRatesDTOList;
	}

	@Override
	public void delete(Long id) throws InvestmentsBusinessException {
		CardRatesDTO cardRatesDTO = null;
		Optional<CardRates> optCardRates = cardRatesRepository.findById(id);
		if (optCardRates.isPresent()) {
			cardRatesDTO = cardRatesMapper.toDto(optCardRates.get());
			if (null != cardRatesDTO) {
				cardRatesDTO.setStatus(ApplicationConstants.IN_ACTIVE);
				save(cardRatesDTO);
			}
		}
	}

}
