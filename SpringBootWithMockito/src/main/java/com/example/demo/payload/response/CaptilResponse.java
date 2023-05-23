package com.example.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaptilResponse {

	
	public String countryName;

	public String countryCapital;
	
	public String cityPinCode;
	
	public String famousPoint;
}
