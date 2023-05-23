
package com.example.demo.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CaptialReq {

	private String id;
	private String cityPinCode;

	private String famousPoint;

}
