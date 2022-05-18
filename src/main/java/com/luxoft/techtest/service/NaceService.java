package com.luxoft.techtest.service;

import java.util.Optional;

import com.luxoft.techtest.model.NaceData;

public interface NaceService {

	NaceData addNaceData(NaceData naceData);
	
	 Optional<NaceData> getNaceData(String orderId);
}
