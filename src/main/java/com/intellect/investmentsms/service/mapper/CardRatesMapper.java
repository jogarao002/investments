package com.intellect.investmentsms.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.investmentsms.domain.CardRates;
import com.intellect.investmentsms.service.dto.CardRatesDTO;

/**
 * Mapper for the entity {@link CardRates} and its DTO {@link CardRatesDTO}.
 */
@Mapper(componentModel = "spring")
public interface CardRatesMapper extends EntityMapper<CardRatesDTO, CardRates> {
}
