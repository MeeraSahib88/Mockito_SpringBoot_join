package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.ContryInter;
import com.example.demo.entity.Country;

public interface CountryRepository extends JpaRepository<Country, String> {

	Optional<Country> findByCountryName(String countryName);

	@Query(value = "SELECT a.country_name as CountryName,a.country_capital as CountryCapital FROM country a WHERE a.country_capital= :id", nativeQuery = true)
	ContryInter findPlayerNameById(String id);

}
