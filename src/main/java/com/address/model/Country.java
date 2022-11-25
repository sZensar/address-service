package com.address.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

	@JsonProperty("code")
	private String code;
	
	@JsonProperty("name")
	private String name;
	
}
