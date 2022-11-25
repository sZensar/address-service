package com.address;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.address.service.AddressUploadService;

@SpringBootApplication
public class AddressServiceApplication implements CommandLineRunner {

	@Value("${file.path}")
	private String filePath;

	public static void main(String[] args) {
		SpringApplication.run(AddressServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AddressUploadService.readAddressFromFile(filePath);

	}

}
