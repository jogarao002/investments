package com.intellect.investmentsms.service.mapper;

import com.intellect.investmentsms.domain.InvestedBankDetails;
import com.intellect.investmentsms.service.dto.InvestedBankDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvestedBankDetails} and its DTO {@link InvestedBankDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface InvestedBankDetailsMapper extends EntityMapper<InvestedBankDetailsDTO, InvestedBankDetails> {}
