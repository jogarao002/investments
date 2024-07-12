package com.intellect.investmentsms.service.mapper;

import com.intellect.investmentsms.domain.InvestmentAccountInstallments;
import com.intellect.investmentsms.service.dto.InvestmentAccountInstallmentsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvestmentAccountInstallments} and its DTO {@link InvestmentAccountInstallmentsDTO}.
 */
@Mapper(componentModel = "spring")
public interface InvestmentAccountInstallmentsMapper
    extends EntityMapper<InvestmentAccountInstallmentsDTO, InvestmentAccountInstallments> {}
