package com.luxoft.techtest.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table (name = "NACE_DATA")
public class NaceData {
	
	
	@Id
	private String orderNumber;
	
	private String level;
	
	private String code;
	
	private String parent;
		
	private String description;
	
	@Lob
	private String thisItemIncludes;
		
	@Lob
	private String thisItemAlsoIncludes;
		
	@Lob
	private String rulings;
	
	@Lob
	private String thisItemExcludes;
			
	private String reference;	
}
