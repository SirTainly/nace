package com.luxoft.techtest.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.luxoft.techtest.model.NaceData;

@SpringBootTest
public class NaceE2ETest {

	private static final String FREE_PEN = "Free Pen";

	private static final String KRAMER_VS_KRAMER = "Kramer vs Kramer";

	private static final String EXCELLENT_HIRE = "Excellent Hire";

	private static final String MOTHER = "mother";

	private static final String CROSSING = "crossing";

	private static final String TEST_DESCRIPTION = "Test Description";

	private static final String GREEN_CROSS = "GreenCross";

	private static final String THIS_IS_A_TEST = "ThisIsATest";

	private static final String AN_ITEM = "An item";

	private static final String TOUPEES = "Toupees";

	private String SERVICE_URL = "http://localhost:8080/nace/";

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void findsRecord() throws Exception {

		ResponseEntity<NaceData> retrievedNaceDataResponse = restTemplate.getForEntity(SERVICE_URL + "orders/398481",
				NaceData.class);
		NaceData retrievedNaceData = retrievedNaceDataResponse.getBody();
		assertEquals("398481", retrievedNaceData.getOrderNumber());
		assertEquals(
				"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.",
				retrievedNaceData.getThisItemIncludes());

	}

	@Test
	public void failsToFindRecord() throws Exception {

		HttpClientErrorException thrown = Assertions.assertThrows(HttpClientErrorException.class, () -> {
			restTemplate.getForEntity(SERVICE_URL + "orders/Fred", NaceData.class);
		});

		Assertions.assertEquals(404, thrown.getRawStatusCode());
	}
	
	@Test
	public void addsNewNaceData() {
		NaceData naceData = new NaceData();
		naceData.setOrderNumber(THIS_IS_A_TEST);
		naceData.setCode(GREEN_CROSS);
		naceData.setDescription(TEST_DESCRIPTION);
		naceData.setLevel(CROSSING);
		naceData.setParent(MOTHER);
		naceData.setReference(EXCELLENT_HIRE);
		naceData.setRulings(KRAMER_VS_KRAMER);
		naceData.setThisItemAlsoIncludes(FREE_PEN);
		naceData.setThisItemIncludes(AN_ITEM);
		naceData.setThisItemExcludes(TOUPEES);
		
		ResponseEntity<NaceData> createdNaceDataResponse = restTemplate.postForEntity(SERVICE_URL + "order/", naceData, NaceData.class);
		assertEquals(HttpStatus.CREATED, createdNaceDataResponse.getStatusCode());
		
		NaceData createdNaceData = createdNaceDataResponse.getBody();
		
		assertEquals(naceData.getOrderNumber(), createdNaceData.getOrderNumber());
		assertEquals(naceData.getDescription(), createdNaceData.getDescription());
		assertEquals(naceData.getLevel(), createdNaceData.getLevel());
		assertEquals(naceData.getCode(), createdNaceData.getCode());
		assertEquals(naceData.getParent(), createdNaceData.getParent());
		assertEquals(naceData.getReference(), createdNaceData.getReference());
		assertEquals(naceData.getRulings(), createdNaceData.getRulings());
		assertEquals(naceData.getThisItemAlsoIncludes(), createdNaceData.getThisItemAlsoIncludes());
		assertEquals(naceData.getThisItemIncludes(), createdNaceData.getThisItemIncludes());
		assertEquals(naceData.getThisItemExcludes(), createdNaceData.getThisItemExcludes());
		
		ResponseEntity<NaceData> retrievedNaceDataResponse = restTemplate.getForEntity(SERVICE_URL + "orders/" + THIS_IS_A_TEST,
				NaceData.class);
		
		NaceData retrievedNaceData = retrievedNaceDataResponse.getBody();
		
		assertEquals(naceData.getOrderNumber(), retrievedNaceData.getOrderNumber());
		assertEquals(naceData.getDescription(), retrievedNaceData.getDescription());
		assertEquals(naceData.getLevel(), retrievedNaceData.getLevel());
		assertEquals(naceData.getCode(), retrievedNaceData.getCode());
		assertEquals(naceData.getParent(), retrievedNaceData.getParent());
		assertEquals(naceData.getReference(), retrievedNaceData.getReference());
		assertEquals(naceData.getRulings(), retrievedNaceData.getRulings());
		assertEquals(naceData.getThisItemAlsoIncludes(), retrievedNaceData.getThisItemAlsoIncludes());
		assertEquals(naceData.getThisItemIncludes(), retrievedNaceData.getThisItemIncludes());
		assertEquals(naceData.getThisItemExcludes(), retrievedNaceData.getThisItemExcludes());
		
		
	}

}
