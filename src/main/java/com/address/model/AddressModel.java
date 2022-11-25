package com.address.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AddressModel {

	@JsonProperty("id")
	private int id;

	@JsonProperty("type")
	private AddressType addressType;

	@JsonProperty("addressLineDetail")
	private AddressLineDetail addressLineDetail;

	@JsonProperty("provinceOrState")
	private ProvinceOrState provinceOrState;

	@JsonProperty("cityOrTown")
	private String cityOrTown;

	@JsonProperty("country")
	private Country country;

	@JsonProperty("postalCode")
	private String postalCode;

	@JsonProperty("lastUpdated")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date lastUpdated;

	@JsonProperty("suburbOrDistrict")
	private String suburbOrDistrict;
}
