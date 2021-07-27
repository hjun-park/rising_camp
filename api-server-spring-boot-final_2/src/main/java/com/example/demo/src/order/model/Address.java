package com.example.demo.src.order.model;

import com.example.demo.src.client.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@Getter @Setter @ToString
@Entity
@Table(name = "ADDRESS")
public class Address {

	@Id
	private String buildingNum;

	private Double longitude;
	private Double latitude;
	private String addressName;
	private int zipcode;

	@OneToOne(mappedBy = "address")
	private Client client;

	public Address() {
	}
}
