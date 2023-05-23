package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payload.request.CaptialReq;
import com.example.demo.payload.request.CountryRequest;
import com.example.demo.payload.request.UserRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.service.CountryServices;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/country")
public class CountryController {

	@Autowired
	CountryServices countryServices;

	@PostMapping("/saveCountry")
	public ResponseEntity<MessageResponse> saveCountry(@RequestBody CountryRequest countryRequest) throws JsonProcessingException {
		return countryServices.saveCountry(countryRequest);

	}

	@GetMapping("/findAllCountry")
	public ResponseEntity<MessageResponse> findAllcountry() {
		return countryServices.findAllcountry();

	}

	@GetMapping("/findCountryById/{id}")
	public ResponseEntity<MessageResponse> findACountryById(@PathVariable(value = "id") String id) {
		return countryServices.findACountryById(id);

	}

	@GetMapping("/findCountryByName/CountryName")
	public ResponseEntity<MessageResponse> findACountryByName(@RequestParam(value = "CountryName") String CountryName) {
		return countryServices.findACountryByName(CountryName);

	}

	@PutMapping("/updateCountry/{id}")
	public ResponseEntity<MessageResponse> updateCountry(@PathVariable("id") String id,
			@RequestBody CountryRequest countryRequest) {
		return countryServices.updateCountry(id, countryRequest);

	}

	@DeleteMapping("/deleteCountryById/{id}")
	public ResponseEntity<MessageResponse> deleteCountryById(@PathVariable("id") String id) {
		return countryServices.deleteCountryById(id);

	}

	
	@GetMapping("/findCountry/By/Id/{id}")
	public ResponseEntity<MessageResponse> findACountryByIds(@PathVariable(value = "id") String id) {
		return countryServices.findByInter
				(id);

	}
	
	@PostMapping("/saveUser")
	public ResponseEntity<MessageResponse> saveUser(@RequestBody UserRequest countryRequest) {
		return countryServices.saveUser(countryRequest);

	}
	
	@PostMapping("/saveCaptial")
	public ResponseEntity<MessageResponse> saveCaptial(@RequestBody CaptialReq countryRequest) {
		return countryServices.saveCaptial(countryRequest);

	}
	
	@GetMapping("/join/{id}")
	public ResponseEntity<MessageResponse> join(@PathVariable("id") String id) {
		return countryServices.join(id);

	}
}
