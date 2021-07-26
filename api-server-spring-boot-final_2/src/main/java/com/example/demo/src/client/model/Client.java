package com.example.demo.src.client.model;

import com.example.demo.src.order.model.Address;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "MEMBER")
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
//	@Lob // BINARY
	private String password;

	@Column(length = 35, nullable = false)
	private String email;

	@Column(length = 35, nullable = false, unique = true)
	private String name;

	@Column(length = 25, nullable = false)
	private String phoneNumber;

	private String profileImageUrl;

	@Column(nullable = false)
	private String mailAccept;

	@Column(nullable = false)
	private String smsAccept;

	@Column(nullable = false)
	private String addressBuildingNum;

	@Column(nullable = false)
	private String districtCode;

	private String addressDetail;

	@Column(nullable = false)
	private String grade;

	@Column(nullable = false)
	private String birthDate;

	private Double latitude;
	private Double longitude;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	@Enumerated(EnumType.STRING)
	private MemberStatus status;

	@OneToOne
	@JoinColumn(name = "buildingNum")
	private Address buildingNum;

	// DB랑 신경 없는 값
	@Transient
	private String temp;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return getAddressBuildingNum().equals(client.getAddressBuildingNum()) && getDistrictCode().equals(client.getDistrictCode());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getAddressBuildingNum(), getDistrictCode());
	}

	public Client() {
	}
}
