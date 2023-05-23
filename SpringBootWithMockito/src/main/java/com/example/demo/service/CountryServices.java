package com.example.demo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.payload.request.CaptialReq;
import com.example.demo.payload.request.CountryRequest;
import com.example.demo.payload.request.UserRequest;
import com.example.demo.payload.response.MessageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public interface CountryServices {

	ResponseEntity<MessageResponse> saveCountry(CountryRequest countryRequest) throws JsonProcessingException;

	ResponseEntity<MessageResponse> findAllcountry();

	ResponseEntity<MessageResponse> findACountryById(String id);

	ResponseEntity<MessageResponse> findACountryByName(String countryName);

	ResponseEntity<MessageResponse> updateCountry(String id, CountryRequest countryRequest);

	ResponseEntity<MessageResponse> deleteCountryById(String id);

	ResponseEntity<MessageResponse> findByInter(String id);

	ResponseEntity<MessageResponse> saveUser(UserRequest countryRequest);

	ResponseEntity<MessageResponse> saveCaptial(CaptialReq countryRequest);

	ResponseEntity<MessageResponse> join(String id);

}
