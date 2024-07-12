package com.intellect.investmentsms.service.mapper;

import com.intellect.investmentsms.domain.InvestmentAccountsTransaction;
import com.intellect.investmentsms.service.dto.InvestmentAccountsTransactionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvestmentAccountsTransaction} and its DTO {@link InvestmentAccountsTransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface InvestmentAccountsTransactionMapper
    extends EntityMapper<InvestmentAccountsTransactionDTO, InvestmentAccountsTransaction> {}
