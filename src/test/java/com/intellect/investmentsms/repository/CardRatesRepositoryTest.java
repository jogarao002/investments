package com.intellect.investmentsms.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.intellect.investmentsms.domain.CardRates;
import com.intellect.investmentsms.exception.InvestmentsBusinessException;

@SpringBootTest(classes = {CardRatesRepositoryTest.class})
@ExtendWith(MockitoExtension.class)
class CardRatesRepositoryTest {
	
	@Mock
	private CardRatesRepository cardRatesRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindByPacsIdAndStatus() {
		
		CardRates cardRates = new CardRates();
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
		
		when(cardRatesRepository.findByPacsIdAndStatus(cardRates.getPacsId(), cardRates.getStatus()))
			.thenReturn(List.of(cardRates));
		
		List<CardRates> pacsIdAndStatusList = cardRatesRepository.findByPacsIdAndStatus(cardRates.getPacsId(), cardRates.getStatus());
		assertEquals(1, pacsIdAndStatusList.size());
		
	}
	
	
}