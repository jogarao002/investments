package com.intellect.investmentsms.service;

import java.util.List;

import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.service.dto.InvestmentAccountInstallmentsDTO;

public interface InvestmentAccountInstallmentsService {
    
	InvestmentAccountInstallmentsDTO save(InvestmentAccountInstallmentsDTO investmentAccountInstallmentsDTO) throws InvestmentsBusinessException;

    
	List<InvestmentAccountInstallmentsDTO> findAll() throws InvestmentsBusinessException;

    
    InvestmentAccountInstallmentsDTO findOne(Long id) throws InvestmentsBusinessException;

    
    void delete(Long id) throws InvestmentsBusinessException;
    
    List<InvestmentAccountInstallmentsDTO> getByTermAccId(Long termAccId) throws InvestmentsBusinessException;
}
