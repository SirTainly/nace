package com.luxoft.techtest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.techtest.model.NaceData;
import com.luxoft.techtest.repository.NaceRepository;

@Service
public class NaceServiceImpl implements NaceService {

	private NaceRepository repository;
	
	@Autowired
	public NaceServiceImpl(NaceRepository repository) {
		this.repository = repository;
	}
	
	public NaceData addNaceData(NaceData naceData) {
		return repository.save(naceData);
	}
	
	public Optional<NaceData> getNaceData(String orderId) {
		return repository.findById(orderId);
	}
}
