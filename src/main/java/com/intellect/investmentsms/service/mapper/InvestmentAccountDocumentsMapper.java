package com.intellect.investmentsms.service.mapper;

import com.intellect.investmentsms.domain.InvestmentAccountDocuments;
import com.intellect.investmentsms.service.dto.InvestmentAccountDocumentsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvestmentAccountDocuments} and its DTO {@link InvestmentAccountDocumentsDTO}.
 */
@Mapper(componentModel = "spring")
public interface InvestmentAccountDocumentsMapper extends EntityMapper<InvestmentAccountDocumentsDTO, InvestmentAccountDocuments> {}
