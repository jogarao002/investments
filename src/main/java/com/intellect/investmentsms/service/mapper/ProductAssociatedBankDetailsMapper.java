package com.intellect.investmentsms.service.mapper;

import org.mapstruct.Mapper;

import com.intellect.investmentsms.domain.ProductAssociatedBankDetails;
import com.intellect.investmentsms.service.dto.ProductAssociatedBankDetailsDTO;

/**
 * Mapper for the entity {@link ProductAssociatedBankDetails} and its DTO {@link ProductAssociatedBankDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductAssociatedBankDetailsMapper extends EntityMapper<ProductAssociatedBankDetailsDTO, ProductAssociatedBankDetails> {}
