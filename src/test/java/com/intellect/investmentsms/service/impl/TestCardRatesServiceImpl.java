package com.intellect.investmentsms.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.intellect.investmentsms.domain.CardRates;
import com.intellect.investmentsms.domain.CommonStatus;
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
		// Mock repository methods
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
}
