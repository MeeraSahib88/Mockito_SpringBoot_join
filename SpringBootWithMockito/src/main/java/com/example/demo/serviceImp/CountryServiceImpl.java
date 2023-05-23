package com.example.demo.serviceImp;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CaptiaCity;
import com.example.demo.entity.CaptialInter;
import com.example.demo.entity.ContryInter;
import com.example.demo.entity.Country;
import com.example.demo.entity.Userdetails;
import com.example.demo.payload.request.CaptialReq;
import com.example.demo.payload.request.CountryRequest;
import com.example.demo.payload.request.UserRequest;
import com.example.demo.payload.response.CaptilResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.CaptialCityRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.userDetailsRepos;
import com.example.demo.service.CountryServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CountryServiceImpl implements CountryServices {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	userDetailsRepos detailsRepos;

	@Autowired
	CaptialCityRepository captialCityRepository;

	@Override
	public ResponseEntity<MessageResponse> saveCountry(CountryRequest countryRequest) throws JsonProcessingException {

		Optional<Userdetails> op = detailsRepos.findById(countryRequest.getId());
		Country country = Country.builder().countryCapital(countryRequest.getCountryCapital())
				.countryName(countryRequest.getCountryName()).build();
		country.setUserdetails(op.get());
		countryRepository.save(country);
		
		return ResponseEntity.ok(MessageResponse.builder().status(HttpStatus.OK.value()).message("Saved success")
				.response(country).build());
	}

	@Override
	public ResponseEntity<MessageResponse> findAllcountry() {
		List<Country> countries = countryRepository.findAll();

		return ResponseEntity.ok(MessageResponse.builder().status(HttpStatus.OK.value()).message("Retifed success")
				.response(countries).build());
	}

	@Override
	public ResponseEntity<MessageResponse> findACountryById(String id) {
		Optional<Country> country = countryRepository.findById(id);
		Country country2 = country.get();
		return ResponseEntity.ok(MessageResponse.builder().status(HttpStatus.OK.value()).message("Retifed success")
				.response(country2).build());
	}

	@Override
	public ResponseEntity<MessageResponse> findACountryByName(String countryName) {
		Optional<Country> country = countryRepository.findByCountryName(countryName);
		Country country2 = country.get();
		return ResponseEntity.ok(MessageResponse.builder().status(HttpStatus.OK.value()).message("Retifed success")
				.response(country2).build());
	}

	@Override
	public ResponseEntity<MessageResponse> updateCountry(String id, CountryRequest countryRequest) {

		Optional<Country> country = countryRepository.findById(id);

		Country country2 = country.get();

		Country country3 = Country.builder().co_id(country2.getCo_id())
				.countryCapital(countryRequest.getCountryCapital()).countryName(countryRequest.getCountryName())
				.build();

		countryRepository.save(country3);
		return ResponseEntity.ok(MessageResponse.builder().status(HttpStatus.OK.value()).message("Retifed success")
				.response(country3).build());
	}

	@Override
	public ResponseEntity<MessageResponse> deleteCountryById(String id) {
		String message;
		JSONObject jsonObject = new JSONObject();
		countryRepository.deleteById(id);

		jsonObject.put(id, "Deleted suscces");
		message = jsonObject.toString();
		return ResponseEntity.ok(MessageResponse.builder().status(HttpStatus.OK.value()).message("Retifed success")
				.response(message).build());
	}

	@Override
	public ResponseEntity<MessageResponse> findByInter(String id) {
		ContryInter country = countryRepository.findPlayerNameById(id);
		ModelMapper mapper = new ModelMapper();

		CountryRequest map = mapper.map(country, CountryRequest.class);

		return ResponseEntity.ok(MessageResponse.builder().status(HttpStatus.OK.value()).message("Retifed success")
				.response(map).build());

	}

	@Override
	public ResponseEntity<MessageResponse> saveUser(UserRequest countryRequest) {

		ModelMapper mapper = new ModelMapper();

		Userdetails map = mapper.map(countryRequest, Userdetails.class);
		map = detailsRepos.save(map);
		return ResponseEntity.ok(MessageResponse.builder().status(HttpStatus.OK.value()).message("Retifed success")
				.response(map).build());
	}

	@Override
	public ResponseEntity<MessageResponse> saveCaptial(CaptialReq countryRequest) {
		Optional<Userdetails> op = detailsRepos.findById(countryRequest.getId());
		ModelMapper mapper = new ModelMapper();

		CaptiaCity map = mapper.map(countryRequest, CaptiaCity.class);
		map.setUserdetails(op.get());
		map = captialCityRepository.save(map);
		return ResponseEntity.ok(MessageResponse.builder().status(HttpStatus.OK.value()).message("Retifed success")
				.response(map).build());
	}

	@Override
	public ResponseEntity<MessageResponse> join(String id) {
		CaptilResponse country = captialCityRepository.findByUser(id);

//		System.out.println(country.getCityPinCode());
		ModelMapper mapper = new ModelMapper();

		CaptilResponse map = mapper.map(country, CaptilResponse.class);
//		CaptilResponse res = CaptilResponse.builder().cityPinCode(map.getCityPinCode()).countryCapital(map.getCountryCapital())
//				.famousPoint(map.getFamousPoint()).countryName(map.getCountryName()).build();
		return ResponseEntity.ok(MessageResponse.builder().status(HttpStatus.OK.value()).message("Retifed success")
				.response(map).build());
	}

}
