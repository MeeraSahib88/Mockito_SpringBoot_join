package com.example.demo.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.payload.response.CaptilResponse;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "captia_city")
@SqlResultSetMapping(name = "CaptiaCityMapping", classes = {
		@ConstructorResult(targetClass = CaptilResponse.class, columns = { @ColumnResult(name = "countryName"),
				@ColumnResult(name = "countryCapital"), @ColumnResult(name = "cityPinCode"),
				@ColumnResult(name = "famousPoint") }) })
@NamedNativeQuery(name = "CaptiaCity.findByUser", query = "select u.city_pin_code as CityPinCode , u.famous_point as FamousPoint, sd.country_capital as CountryCapital,sd.country_name as CountryName from captia_city u inner join country sd on u.user_id=sd.user_id \r\n"
		+ "inner join user_details ud  on sd.user_id=ud.user_id where ud.user_id=:id", resultSetMapping = "CaptiaCityMapping")
public class CaptiaCity {

	@Id
	@GenericGenerator(name = "uuid-gen", strategy = "uuid2")
	@GeneratedValue(generator = "uuid-gen")
	private String cpa_id;

	private String cityPinCode;

	private String famousPoint;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Userdetails userdetails;

	@Transient
	private String countryName;

	@Transient
	private String countryCapital;

}
