package com.address.utility;

import java.util.regex.Pattern;

import org.springframework.util.ObjectUtils;

import com.address.model.AddressModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddressUtility {

	private static final String COLON = ":";
	private static final String SPACE = " ";
	private static final String DASH = "-";
	private static final String BLANK = "";
	private static final String POST_CODE_VALIDATION_REGEX = "\\d+$";
	private static final String POST_CODE_VALIDATION_MESSAGE = "Postcode is missing or not in vallid format. Postcode can be only numaric value.";
	private static final String COUNTRY_VALIDATION_MESSAGE = "Country details are missing in address. Please provide valid country details.";
	private static final String ADDRESS_LINE_DETAILS_VALIDATION_MESSAGE = "Address line details is missing in address. Please provide address line details.";
	private static final String PROVINCE_VALIDATION_MESSAGE_FOR_ZA = "Please provide the province details. For ZA country province is required.";	
	
	private AddressUtility() {

	}

	/**
	 * @param address
	 * @return
	 */
	public static String prettyPrintAddress(AddressModel address) {
		StringBuilder fullAddress = new StringBuilder();

		if (address.getAddressType() != null && address.getAddressType().getName() != null) {
			fullAddress.append(address.getAddressType().getName()).append(SPACE).append(COLON).append(SPACE);
		}

		if (address.getAddressLineDetail() != null) {
			if (address.getAddressLineDetail().getLine1() != null) {
				fullAddress.append(address.getAddressLineDetail().getLine1()).append(SPACE).append(DASH).append(SPACE);
			}

			if (address.getAddressLineDetail().getLine2() != null) {
				fullAddress.append(address.getAddressLineDetail().getLine2()).append(SPACE).append(DASH).append(SPACE);
			}
		}

		if (address.getCityOrTown() != null) {
			fullAddress.append(address.getCityOrTown()).append(SPACE).append(DASH).append(SPACE);
		}

		if (address.getProvinceOrState() != null && address.getProvinceOrState().getName() != null) {
			fullAddress.append(address.getProvinceOrState().getName()).append(SPACE).append(DASH).append(SPACE);
		}

		if (address.getPostalCode() != null) {
			fullAddress.append(address.getPostalCode()).append(SPACE).append(DASH).append(SPACE);
		}

		if (address.getCountry() != null && address.getCountry().getName() != null) {
			fullAddress.append(address.getCountry().getName());
		}

		return fullAddress.toString();
	}

	
	public static void addressValidator(AddressModel address) {
		StringBuilder errorMessage = new StringBuilder();

		if (address.getPostalCode() != null
				&& AddressUtility.validate(POST_CODE_VALIDATION_REGEX, address.getPostalCode())) {
			errorMessage.append(POST_CODE_VALIDATION_MESSAGE).append(" ,");
		}

		if (address.getCountry() == null || address.getCountry().getName() == null
				|| BLANK.equalsIgnoreCase(address.getCountry().getName().strip())) {
			errorMessage.append(COUNTRY_VALIDATION_MESSAGE).append(" ,");
			;
		} else if ("ZA".equalsIgnoreCase(address.getCountry().getCode()) && address.getProvinceOrState() == null) {
			errorMessage.append(PROVINCE_VALIDATION_MESSAGE_FOR_ZA).append(" ,");
		}

		if (address.getAddressLineDetail() == null
				|| (ObjectUtils.isEmpty(address.getAddressLineDetail().getLine1())
						&& ObjectUtils.isEmpty(address.getAddressLineDetail().getLine2()))) {
			errorMessage.append(ADDRESS_LINE_DETAILS_VALIDATION_MESSAGE);
		}

		if (!errorMessage.isEmpty()) {
			log.error(address.toString() + " error message is : " + errorMessage.toString());				
		} else {
			log.info(prettyPrintAddress(address));
		}
	}
	
	/**
	 * @param address
	 * @param addressType
	 * @return
	 */
	public static boolean filterAddressBasedOnAddressType(AddressModel address, String addressType) {
		return (address.getAddressType() != null && address.getAddressType().getName() != null
				&& addressType.equalsIgnoreCase(address.getAddressType().getName()));
	}
	
	/**
	 * @param regex
	 * @param details
	 * @return
	 */
	public static boolean validate(String regex, String details) {
		return !Pattern.compile(regex).matcher(details).matches();
	}

}
