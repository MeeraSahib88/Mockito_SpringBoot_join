package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.CaptiaCity;
import com.example.demo.entity.CaptialInter;
import com.example.demo.payload.response.CaptilResponse;

public interface CaptialCityRepository extends JpaRepository<CaptiaCity, String> {

	@Query(value = "select u.city_pin_code as CityPinCode , u.famous_point as FamousPoint, sd.country_capital as CountryCapital,sd.country_name as CountryName from captia_city u inner join country sd on u.user_id=sd.user_id \r\n"
			+ "inner join user_details ud  on sd.user_id=ud.user_id where ud.user_id=:id", nativeQuery = true)
	CaptialInter findByuserId(String id);
	

	//@Query(value = "select u.city_pin_code as CityPinCode , u.famous_point as FamousPoint, sd.country_capital as CountryCapital,sd.country_name as CountryName from captia_city u inner join country sd on u.user_id=sd.user_id \r\n"
	//		+ "inner join user_details ud  on sd.user_id=ud.user_id where ud.user_id=:id", nativeQuery = true)
	@Query(nativeQuery = true, name = "CaptiaCity.findByUser")
	CaptilResponse findByUser(String id);
	
	@Query(value = "select u.city_pin_code as CityPinCode , u.famous_point as FamousPoint, sd.country_capital as CountryCapital,sd.country_name as CountryName from captia_city u inner join country sd on u.user_id=sd.user_id \r\n"
			+ "inner join user_details ud  on sd.user_id=ud.user_id where ud.user_id=:id", nativeQuery = true)
	CaptiaCity findByEnUser(String id);
	
}
