package com.intellect.investmentsms.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.investmentsms.domain.InterestPayments;
import com.intellect.investmentsms.service.dto.InterestPaymentsDTO;

/**
 * Mapper for the entity {@link InterestPayments} and its DTO {@link InterestPaymentsDTO}.
 */
@Mapper(componentModel = "spring")
public interface InterestPaymentsMapper extends EntityMapper<InterestPaymentsDTO, InterestPayments> {}
