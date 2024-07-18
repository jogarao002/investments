package com.intellect.investmentsms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.intellect.investmentsms.domain.CardRates;
import com.intellect.investmentsms.domain.CommonStatus;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;
import com.intellect.investmentsms.repository.CardRatesRepository;
import com.intellect.investmentsms.repository.CommonStatusRepository;
import com.intellect.investmentsms.service.dto.CardRatesDTO;
import com.intellect.investmentsms.service.mapper.CardRatesMapper;

@SpringBootTest(classes = { TestCardRatesServiceImpl.class })
@ExtendWith(MockitoExtension.class)
public class TestCardRatesServiceImpl {

	@Mock
	private CardRatesRepository cardRatesRepository;

	@Mock
	private CardRatesMapper cardRatesMapper;

	@Mock
	private CommonStatusRepository commonStatusRepository;

	@InjectMocks
	CardRatesServiceImpl cardRatesServiceImpl;

	private CardRates cardRates;
	private CardRatesDTO cardRatesDTO;
	private CommonStatus commonStatus;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

	    cardRatesServiceImpl = new CardRatesServiceImpl(cardRatesRepository, cardRatesMapper, commonStatusRepository);
		
		// Initialize test data
		cardRates = new CardRates();
		cardRates.setCreatedBy(75548514L);
        cardRates.setCreatedOn(758158212L);
		cardRates.setId(1L);
		cardRates.setPacsId(123L);
		cardRates.setTenureType(1);
		cardRates.setMinTenure(6);
		cardRates.setMaxTenure(12);
		cardRates.setGeneralRoi(5.5f);
		cardRates.setStaffRoi(6.0f);
		cardRates.setSeniorcitizenRoi(5.0f);
		cardRates.setPenalRoi(7.5f);
		cardRates.setEffectiveStartDate(System.currentTimeMillis());
		cardRates.setEffectiveEndDate(System.currentTimeMillis() + 86400000);
		cardRates.setStatus(1);

		cardRatesDTO = new CardRatesDTO();
		cardRatesDTO.setCreatedBy(75548514L);
        cardRatesDTO.setCreatedOn(758158212L);
		cardRatesDTO.setId(1L);
		cardRatesDTO.setPacsId(123L);
		cardRatesDTO.setTenureType(1);
		cardRatesDTO.setMinTenure(6);
		cardRatesDTO.setMaxTenure(12);
		cardRatesDTO.setGeneralRoi(5.5f);
		cardRatesDTO.setStaffRoi(6.0f);
		cardRatesDTO.setSeniorcitizenRoi(5.0f);
		cardRatesDTO.setPenalRoi(7.5f);
		cardRatesDTO.setEffectiveStartDate(cardRates.getEffectiveStartDate());
		cardRatesDTO.setEffectiveEndDate(cardRates.getEffectiveEndDate());
		cardRatesDTO.setStatus(1);

		commonStatus = new CommonStatus();
		commonStatus.setId(1L);
		commonStatus.setName("Active");
	}

	@Test
	public void test_findOne() {
		when(cardRatesRepository.findById(anyLong())).thenReturn(Optional.of(cardRates));
		when(cardRatesMapper.toDto(cardRates)).thenReturn(cardRatesDTO);
		when(commonStatusRepository.findById(anyLong())).thenReturn(Optional.of(commonStatus));

		CardRatesDTO resultDTO = cardRatesServiceImpl.findOne(1L);

		assertNotNull(resultDTO);
		assertEquals(cardRatesDTO.getId(), resultDTO.getId());
		assertEquals(cardRatesDTO.getPacsId(), resultDTO.getPacsId());
		assertEquals(cardRatesDTO.getTenureType(), resultDTO.getTenureType());
		assertEquals(cardRatesDTO.getMinTenure(), resultDTO.getMinTenure());
		assertEquals(cardRatesDTO.getMaxTenure(), resultDTO.getMaxTenure());
		assertEquals(cardRatesDTO.getGeneralRoi(), resultDTO.getGeneralRoi());
		assertEquals(cardRatesDTO.getStaffRoi(), resultDTO.getStaffRoi());
		assertEquals(cardRatesDTO.getSeniorcitizenRoi(), resultDTO.getSeniorcitizenRoi());
		assertEquals(cardRatesDTO.getPenalRoi(), resultDTO.getPenalRoi());
		assertEquals(cardRatesDTO.getEffectiveStartDate(), resultDTO.getEffectiveStartDate());
		assertEquals(cardRatesDTO.getEffectiveEndDate(), resultDTO.getEffectiveEndDate());
		assertEquals(cardRatesDTO.getStatus(), resultDTO.getStatus());
		assertEquals(cardRatesDTO.getStatusName(), resultDTO.getStatusName());

		verify(cardRatesRepository).findById(1L);
		verify(commonStatusRepository).findById(1L);
	}
	
	@Test
	public void test_findAll() {
		List<CardRates> cardRatesList = new ArrayList<>();
		CardRates cardRates = new CardRates(); cardRates.setId(1L); cardRates.setPacsId(123L); cardRates.setTenureType(1); cardRates.setMinTenure(6); cardRates.setMaxTenure(12); cardRates.setGeneralRoi(5.5f); cardRates.setStaffRoi(6.0f); cardRates.setSeniorcitizenRoi(5.0f); cardRates.setPenalRoi(7.5f); cardRates.setEffectiveStartDate(System.currentTimeMillis()); cardRates.setEffectiveEndDate(System.currentTimeMillis() + 86400000); cardRates.setStatus(1);
		CardRates cardRate1 = new CardRates(); cardRate1.setId(2L); cardRate1.setPacsId(456L); cardRate1.setTenureType(2); cardRate1.setMinTenure(3); cardRate1.setMaxTenure(18); cardRate1.setGeneralRoi(6.0f); cardRate1.setStaffRoi(6.5f); cardRate1.setSeniorcitizenRoi(4.5f); cardRate1.setPenalRoi(8.0f); cardRate1.setEffectiveStartDate(System.currentTimeMillis()); cardRate1.setEffectiveEndDate(System.currentTimeMillis() + 172800000); cardRate1.setStatus(2);
		cardRatesList.add(cardRates);
        cardRatesList.add(cardRate1);
        
        List<CardRatesDTO> cardRatesDTOList = new ArrayList<>();
        CardRatesDTO cardRatesDTO = new CardRatesDTO(); cardRatesDTO.setId(1L); cardRatesDTO.setPacsId(123L); cardRatesDTO.setTenureType(1); cardRatesDTO.setMinTenure(6); cardRatesDTO.setMaxTenure(12); cardRatesDTO.setGeneralRoi(5.5f); cardRatesDTO.setStaffRoi(6.0f); cardRatesDTO.setSeniorcitizenRoi(5.0f); cardRatesDTO.setPenalRoi(7.5f); cardRatesDTO.setEffectiveStartDate(System.currentTimeMillis()); cardRatesDTO.setEffectiveEndDate(System.currentTimeMillis() + 86400000); cardRatesDTO.setStatus(1);
        CardRatesDTO cardRate1DTO = new CardRatesDTO(); cardRate1DTO.setId(2L); cardRate1DTO.setPacsId(456L); cardRate1DTO.setTenureType(2); cardRate1DTO.setMinTenure(3); cardRate1DTO.setMaxTenure(18); cardRate1DTO.setGeneralRoi(6.0f); cardRate1DTO.setStaffRoi(6.5f); cardRate1DTO.setSeniorcitizenRoi(4.5f); cardRate1DTO.setPenalRoi(8.0f); cardRate1DTO.setEffectiveStartDate(System.currentTimeMillis()); cardRate1DTO.setEffectiveEndDate(System.currentTimeMillis() + 172800000); cardRate1DTO.setStatus(2);
        cardRatesDTOList.add(cardRatesDTO);
        cardRatesDTOList.add(cardRate1DTO);
        
        
        
		when(cardRatesRepository.findAll()).thenReturn(cardRatesList);
		when(cardRatesMapper.toDto(any(CardRates.class))).thenAnswer(invocation -> {
	        CardRates entity = invocation.getArgument(0);
	        // Find matching DTO from the list based on entity id (assuming id uniquely identifies DTO)
	        return cardRatesDTOList.stream()
	        					   .filter(dto -> dto.getId().equals(entity.getId()))
	                               .findFirst()
	                               .orElse(null); // Return null if no matching DTO found
	    });
		//when(cardRatesMapper.toDto(cardRatesList)).thenReturn(cardRatesDTOList);
		for (int i = 0; i < cardRatesList.size(); i++) {

            CardRates cardRatesIndexList = cardRatesList.get(i);
            CardRatesDTO cardRatesDTOIndexList = cardRatesDTOList.get(i);
            assertEquals(cardRatesIndexList.getId(), cardRatesDTOIndexList.getId());
            assertEquals(cardRatesIndexList.getPacsId(), cardRatesDTOIndexList.getPacsId());
            assertEquals(cardRatesIndexList.getTenureType(), cardRatesDTOIndexList.getTenureType());
            assertEquals(cardRatesIndexList.getMinTenure(), cardRatesDTOIndexList.getMinTenure());
            assertEquals(cardRatesIndexList.getMaxTenure(), cardRatesDTOIndexList.getMaxTenure());
            assertEquals(cardRatesIndexList.getGeneralRoi(), cardRatesDTOIndexList.getGeneralRoi());
            assertEquals(cardRatesIndexList.getStaffRoi(), cardRatesDTOIndexList.getStaffRoi());
            assertEquals(cardRatesIndexList.getSeniorcitizenRoi(), cardRatesDTOIndexList.getSeniorcitizenRoi());
            assertEquals(cardRatesIndexList.getPenalRoi(), cardRatesDTOIndexList.getPenalRoi());
            assertEquals(cardRatesIndexList.getEffectiveStartDate(), cardRatesDTOIndexList.getEffectiveStartDate());
            assertEquals(cardRatesIndexList.getEffectiveEndDate(), cardRatesDTOIndexList.getEffectiveEndDate());
            assertEquals(cardRatesIndexList.getStatus(), cardRatesDTOIndexList.getStatus());

        }
		when(commonStatusRepository.findById(anyLong())).thenReturn(Optional.of(commonStatus));

		List<CardRatesDTO> result = cardRatesServiceImpl.findAll();
		assertEquals(cardRatesDTOList.size(), result.size());
		
	}
	
	
	@Test
	public void testSave() throws InvestmentsBusinessException {
		when(cardRatesRepository.findById(anyLong())).thenReturn(Optional.of(cardRates));
		when(cardRatesMapper.toEntity(cardRatesDTO)).thenReturn(cardRates);
		when(cardRatesRepository.save(cardRates)).thenReturn(cardRates);
		when(cardRatesMapper.toDto(cardRates)).thenReturn(cardRatesDTO);
		
		CardRatesDTO resultDTO = cardRatesServiceImpl.save(cardRatesDTO);
		assertNotNull(resultDTO);
		assertEquals(cardRatesDTO.getId(), resultDTO.getId());
		assertEquals(cardRatesDTO.getPacsId(), resultDTO.getPacsId());
		assertEquals(cardRatesDTO.getTenureType(), resultDTO.getTenureType());
		assertEquals(cardRatesDTO.getMinTenure(), resultDTO.getMinTenure());
		assertEquals(cardRatesDTO.getMaxTenure(), resultDTO.getMaxTenure());
		assertEquals(cardRatesDTO.getGeneralRoi(), resultDTO.getGeneralRoi());
		assertEquals(cardRatesDTO.getStaffRoi(), resultDTO.getStaffRoi());
		assertEquals(cardRatesDTO.getSeniorcitizenRoi(), resultDTO.getSeniorcitizenRoi());
		assertEquals(cardRatesDTO.getPenalRoi(), resultDTO.getPenalRoi());
		assertEquals(cardRatesDTO.getEffectiveStartDate(), resultDTO.getEffectiveStartDate());
		assertEquals(cardRatesDTO.getEffectiveEndDate(), resultDTO.getEffectiveEndDate());
		assertEquals(cardRatesDTO.getStatus(), resultDTO.getStatus());
		assertEquals(cardRatesDTO.getStatusName(), resultDTO.getStatusName());
		
	}
	
}
