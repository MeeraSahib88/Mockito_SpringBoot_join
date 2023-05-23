package com.example.demo.entity;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "country")
public class Country {

	@Id
	@GenericGenerator(name = "uuid-gen",strategy = "uuid2")
	@GeneratedValue(generator = "uuid-gen")
	private String co_id;
	
	private String countryName;
	
	private String countryCapital;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Userdetails userdetails;
}
