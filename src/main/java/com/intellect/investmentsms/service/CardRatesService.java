package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.CardRatesDTO;

public interface CardRatesService {

	CardRatesDTO save(CardRatesDTO cardRatesDTO) throws InvestmentsBusinessException;

	List<CardRatesDTO> findAll() throws InvestmentsBusinessException;

	CardRatesDTO findOne(Long id) throws InvestmentsBusinessException;

	void delete(Long id) throws InvestmentsBusinessException;

	List<CardRatesDTO> getCardRatesByPacsId(Long pacsId) throws InvestmentsBusinessException;

}
