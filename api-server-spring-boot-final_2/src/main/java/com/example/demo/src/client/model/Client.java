package com.example.demo.src.client.model;

import com.example.demo.src.location.model.District;
import com.example.demo.src.order.model.Address;
import com.example.demo.src.order.model.Order;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lombok.Lombok.checkNotNull;

@Entity
@Table(name = "MEMBER")
@Getter
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	private String mailAccept;

	private String smsAccept;

	private String addressDetail;

	private String grade;

	@Column(nullable = false)
	private String birthDate;

	private Double latitude;
	private Double longitude;

//	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

//	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;

	@Enumerated(EnumType.STRING)
	private MemberStatus status;

	private String addressBuildingNum;
	private String districtCode;

	// 양방향 연관관계
	@OneToOne
	@JoinColumn(name = "buildingNum")
	private Address address;

	// 양방향 연관관계
	@ManyToOne
	@JoinColumn(name = "code")
	private District district;

	@OneToMany(mappedBy = "order")
	private List<Order> orders = new ArrayList<>();

	// DB랑 신경 없는 값
	@Transient
	private String temp;

	public void setName(String name) {
		this.name = name;
	}

	@PrePersist
	public void prePersist() {
		this.addressBuildingNum =
			this.addressBuildingNum == null ? "0" : this.addressBuildingNum;
		this.districtCode =
			this.districtCode == null ? "0" : this.addressBuildingNum;
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


	@Builder
	public Client(String email, String password, String name, String phoneNumber, String profileImageUrl, String mailAccept, String smsAccept, String birthDate, String addressBuildingNum, String districtCode, String addressDetail, double latitude, double longitude, String grade) {
		this.email = checkNotNull(email, "email");
		this.password = checkNotNull(password, "password");
		this.name = checkNotNull(name, "name");
		this.phoneNumber = checkNotNull(phoneNumber, "phoneNumber");
		this.profileImageUrl = profileImageUrl;
		this.mailAccept = mailAccept;
		this.smsAccept = smsAccept;
		this.birthDate = checkNotNull(birthDate, "birthDate");
		this.addressBuildingNum = addressBuildingNum;
		this.districtCode = districtCode;
		this.addressDetail = addressDetail;
		this.latitude = latitude;
		this.longitude = longitude;
		this.grade = grade;
	}

	public Client() {
	}
}
