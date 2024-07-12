package com.intellect.investmentsms.service.mapper;

import com.intellect.investmentsms.domain.TermDepositInvestmentAccounts;
import com.intellect.investmentsms.service.dto.TermDepositInvestmentAccountsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TermDepositInvestmentAccounts} and its DTO {@link TermDepositInvestmentAccountsDTO}.
 */
@Mapper(componentModel = "spring")
public interface TermDepositInvestmentAccountsMapper
    extends EntityMapper<TermDepositInvestmentAccountsDTO, TermDepositInvestmentAccounts> {}
