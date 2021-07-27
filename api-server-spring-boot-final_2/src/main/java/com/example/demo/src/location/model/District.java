package com.example.demo.src.location.model;

import com.example.demo.src.client.model.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor @Entity
@Table(name = "DISTRICT")
public class District {

	@Id
	private String code;

	// mapped 붙여진 건 연관관계 주인이 아니기 때문에
	// 읽기만 된다, 외래키 수정은 client domain에서 가능
	// 연관관계 주인 := 외래키가 있는 곳
	@OneToMany(mappedBy = "district")
	private List<Client> clients = new ArrayList<>();

	private String region1Depth;
	private String region2Depth;
	private String region3Depth;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;


	public District() {

	}
}
