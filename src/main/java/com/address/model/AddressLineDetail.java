package com.address.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressLineDetail {

	@JsonProperty("line1")
	private String line1;
	
	@JsonProperty("line2")
	private String line2;
	
}
