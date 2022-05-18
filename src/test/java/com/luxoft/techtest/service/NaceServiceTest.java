package com.luxoft.techtest.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.luxoft.techtest.model.NaceData;
import com.luxoft.techtest.repository.NaceRepository;

@ExtendWith(MockitoExtension.class)
public class NaceServiceTest {

	private NaceService service;
	
	private NaceData testData;
	
	@Mock
	private NaceRepository repository;
		
	@BeforeEach
	public void setup() {
	 service = new NaceServiceImpl(repository);	
	 testData = new NaceData();
	}	
	
	@Test
	public void insertNaceDataSuccessfully() {		
		Mockito.when(repository.save(ArgumentMatchers.any())).then(AdditionalAnswers.returnsFirstArg());		
		service.addNaceData(testData);		
		Mockito.verify(repository).save(testData);
	}
	
	@Test
	public void findNaceDetailsSuccessfully() {
		Mockito.when(repository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(testData));
		String orderId = "Order1";
		Optional<NaceData> foundData = service.getNaceData(orderId);
		assertEquals(testData, foundData.get());
		Mockito.verify(repository).findById(orderId);
	}
}
