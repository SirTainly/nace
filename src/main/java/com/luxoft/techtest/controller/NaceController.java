package com.luxoft.techtest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.techtest.model.NaceData;
import com.luxoft.techtest.service.NaceService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@RequestMapping("/nace")
public class NaceController {
	
	private NaceService naceService;
	
	@Autowired
	public NaceController(NaceService naceService) {
		this.naceService = naceService;
	}

	@PostMapping("/order")
	public ResponseEntity<NaceData> addNaceData(@RequestBody NaceData naceData) {
		return  new ResponseEntity<NaceData>(naceService.addNaceData(naceData), HttpStatus.CREATED);
	}
	
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<NaceData> getNaceData(@PathVariable  String orderId) {
		Optional<NaceData> data = naceService.getNaceData(orderId);
		if (data.isEmpty()) {
			return new ResponseEntity<NaceData>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<NaceData>(data.get(), HttpStatus.OK);
		}
	}
}
