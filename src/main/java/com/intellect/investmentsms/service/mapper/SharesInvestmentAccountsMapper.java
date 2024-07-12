package com.intellect.investmentsms.service.mapper;

import com.intellect.investmentsms.domain.SharesInvestmentAccounts;
import com.intellect.investmentsms.service.dto.SharesInvestmentAccountsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SharesInvestmentAccounts} and its DTO {@link SharesInvestmentAccountsDTO}.
 */
@Mapper(componentModel = "spring")
public interface SharesInvestmentAccountsMapper extends EntityMapper<SharesInvestmentAccountsDTO, SharesInvestmentAccounts> {}
