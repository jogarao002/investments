package com.intellect.investmentsms.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.intellect.investmentsms.domain.CardRates;

@SpringBootTest(classes = {CardRatesRepositoryTest.class})

class CardRatesRepositoryTest {
	
	@Autowired
	private CardRatesRepository cardRatesRepository;

	@BeforeEach
	void setUp() throws Exception {
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
		
		cardRatesRepository.save(cardRates);
		
		List<CardRates> pacsIdAndStatusList = cardRatesRepository.findByPacsIdAndStatus(cardRates.getPacsId(), cardRates.getStatus());
		assertEquals(1, pacsIdAndStatusList.size());
		
	}

}
