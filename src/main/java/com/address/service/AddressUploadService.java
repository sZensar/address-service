package com.address.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.BiPredicate;

import com.address.model.AddressModel;
import com.address.utility.AddressUtility;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddressUploadService {

	public static void readAddressFromFile(String filePath) throws StreamReadException, DatabindException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		//File addressFile = ResourceUtils.getFile("classpath:addresses.json");
		
		File addressFile = new File(filePath);
		List<AddressModel> addresses = mapper.readValue(addressFile, new TypeReference<List<AddressModel>>() {
		});

		// Code to print all the address.
		addresses.stream().map(AddressUtility::prettyPrintAddress).forEach(fullAddress -> {
			// log.info(fullAddress);
		});

		// Print the address based on type.
		log.info("--------> Printing the adress based on address type <--------------");
		printAddressBasedOnType(addresses, "Physical Address");
		// printAddressBasedOnType(addresses, "Postal Address");
		// printAddressBasedOnType(addresses, "Business Address");

		// Address validation messages.
		log.info("--------> Printing the adress with validation message. <--------------");
		addresses.stream().forEach(AddressUtility::addressValidator);

	}

	public static void printAddressBasedOnType(List<AddressModel> addresses, String addressType) {
		BiPredicate<AddressModel, String> filterAddressBasedOnAddressType = AddressUtility::filterAddressBasedOnAddressType;
		addresses.stream().filter(address -> filterAddressBasedOnAddressType.test(address, addressType))
				.map(AddressUtility::prettyPrintAddress).forEach(fullAddress -> {
					log.info(fullAddress);
				});
	}
}
