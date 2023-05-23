package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.controller.CountryController;
import com.example.demo.entity.Country;
import com.example.demo.entity.Userdetails;
import com.example.demo.payload.request.CountryRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.userDetailsRepos;
import com.example.demo.serviceImp.CountryServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.net.ObjectWriter;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
class ServiceMockito {

	@InjectMocks
	CountryServiceImpl countryServiceImpl;

	@MockBean
	CountryRepository countryRepository;

	@MockBean
	userDetailsRepos userdetailsRepos;

	@Autowired
	CountryController countrycontroller;

	List<Country> country;

	@Test
	@Order(1)
	void test_saveCountry() throws JsonProcessingException {
		Userdetails userdetails = Userdetails.builder().userName("meera").password("meera@123").user_id("1234")
				.details("user").build();
		when(userdetailsRepos.save(userdetails)).thenReturn(userdetails);
		Optional<Userdetails> op = Optional.of(userdetails);
		when(userdetailsRepos.findById("1234")).thenReturn(op);

		CountryRequest countryRequest = CountryRequest.builder().countryName("uae").countryCapital("dubai").id("1234")
				.build();

		// countryServiceImpl.saveCountry(countryRequest);

		/*
		 * ModelMapper mapper = new ModelMapper();
		 * 
		 * Country map = mapper.map(countryRequest, Country.class);
		 * 
		 * when(countryRepository.save(map)).thenReturn(map);
		 */
		Country object = Country.builder().co_id(null).countryCapital("dubai").countryName("uae")
				.userdetails(userdetails).build();
		ObjectMapper ow = new ObjectMapper();
		String json = ow.writeValueAsString(object);

		ResponseEntity<MessageResponse> responseEntity = countrycontroller.saveCountry(countryRequest);
		System.out.println(responseEntity.getStatusCode());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		System.out.println(responseEntity.getBody().getResponse());
		System.out.println(json);
		assertEquals(object, responseEntity.getBody().getResponse());

	}

	@Test
	void test_findCountry() {

		List<Country> country = new ArrayList<>();
		Userdetails userdetails = Userdetails.builder().userName("meera").password("meera@123").user_id("1234")
				.details("user").build();
		Country object = Country.builder().co_id("123").countryCapital("dubai").countryName("uae")
				.userdetails(userdetails).build();
		Country object1 = Country.builder().co_id("124").countryCapital("doho").countryName("qatar")
				.userdetails(userdetails).build();
		country.add(object);
		country.add(object1);

		when(countryRepository.findAll()).thenReturn(country);

		ResponseEntity<MessageResponse> responseEntity = countrycontroller.findAllcountry();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		assertEquals(2, countryRepository.findAll().size());
	}

	@Test

	@Order(2)
	void test_findByCountry() {
		List<Country> country = new ArrayList<>();
		Userdetails userdetails = Userdetails.builder().userName("meera").password("meera@123").user_id("1234")
				.details("user").build();
		Country object = Country.builder().co_id("123").countryCapital("dubai").countryName("uae")
				.userdetails(userdetails).build();
		Country object1 = Country.builder().co_id("124").countryCapital("doho").countryName("qatar")
				.userdetails(userdetails).build();
		country.add(object);
		country.add(object1); //
		when(countryRepository.saveAll(country)).thenReturn(country);
		String find = "uae";
		when(countryRepository.findAll()).thenReturn(country); //
		Optional<Country> op1 = Optional.of(object);
		when(countryRepository.findByCountryName(find)).thenReturn(op1);
		String test = countryRepository.findByCountryName(find).get().getCountryName(); //
		assertEquals(find, test);
		Optional<Country> op = Optional.of(object);
		when(countryRepository.findByCountryName(find)).thenReturn(op);
		assertEquals(find, countryRepository.findByCountryName(find).get().getCountryName());
	}

	@Test
	void test_findById() {
		List<Country> country = new ArrayList<>();
		Userdetails userdetails = Userdetails.builder().userName("meera").password("meera@123").user_id("1234")
				.details("user").build();
		Country object = Country.builder().co_id("1234").countryCapital("dubai").countryName("uae")
				.userdetails(userdetails).build();
		Country object1 = Country.builder().co_id("124").countryCapital("doho").countryName("qatar")
				.userdetails(userdetails).build();
		country.add(object);
		country.add(object1); //
		when(countryRepository.saveAll(country)).thenReturn(country);
		String find = "1234";
		Optional<Country> op = Optional.of(object);
		when(countryRepository.findById(find)).thenReturn(op);
		assertEquals(find, countryRepository.findById(find).get().getCo_id());
	}

	@Test
	void test_findByIdUpDate() throws JSONException {
		List<Country> country = new ArrayList<>();
		Userdetails userdetails = Userdetails.builder().userName("meera").password("meera@123").user_id("1234")
				.details("user").build();
		Country object = Country.builder().co_id("1234").countryCapital("dubai").countryName("uae")
				.userdetails(userdetails).build();
		Country object1 = Country.builder().co_id("124").countryCapital("doho").countryName("qatar")
				.userdetails(userdetails).build();
		country.add(object);
		country.add(object1); //
		Optional<Country> op = Optional.of(object);
		when(countryRepository.findById("1234")).thenReturn(op);
		ResponseEntity<MessageResponse> responseEntity = countrycontroller.updateCountry("1234",
				new CountryRequest("null","qatar", "doho"));
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Object objec = responseEntity.getBody().getResponse();
		object.toString();
		String exp = "qatar";
		ModelMapper mapper = new ModelMapper();

		Country map = mapper.map(objec, Country.class);

		// String act = json.getString("countryName");
		System.out.println(objec.toString());

		assertEquals(exp, map.getCountryName());
	}

	@Test
	void test_DeleteByIdUpDate() {
		List<Country> country = new ArrayList<>();
		Userdetails userdetails = Userdetails.builder().userName("meera").password("meera@123").user_id("1234")
				.details("user").build();
		Country object = Country.builder().co_id("1234").countryCapital("dubai").countryName("uae")
				.userdetails(userdetails).build();
		Country object1 = Country.builder().co_id("124").countryCapital("doho").countryName("qatar")
				.userdetails(userdetails).build();
		country.add(object);
		country.add(object1); //
 
		String id = "1234";
		ResponseEntity<MessageResponse> responseEntity = countrycontroller.deleteCountryById(id);
		verify(countryRepository).deleteById(id);
	}

}
