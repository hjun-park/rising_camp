package com.example.demo.src.client.model;

import com.example.demo.src.location.model.District;
import com.example.demo.src.order.model.Address;
import com.example.demo.src.order.model.Orders;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

	@Column(length = 35, nullable = false)
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
	private Double longitude;	// 13

//	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private LocalDateTime createdAt;

//	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Enumerated(EnumType.STRING)
	private MemberStatus status;

//	private String addressBuildingNum;
//	private String districtCode;

	// 양방향 연관관계
	// 외래키 존재
	@OneToOne
	@JoinColumn(name = "addressBuildingNum", referencedColumnName = "buildingNum")	// foreign key이름
	private Address address;

	// 양방향 연관관계
	// 외래키 존재
	@ManyToOne
	@JoinColumn(name = "districtCode", referencedColumnName = "code")
	private District district;

	// mappedBy는 외래키가 없는 쪽에서 사용
	@OneToMany(mappedBy = "client")
	private List<Orders> orders = new ArrayList<>();

//	// DB랑 상관 없는 값
//	@Transient
//	private String temp;

	public void setName(String name) {
		this.name = name;
	}

//	@PrePersist
//	public void prePersist() {
//		this.
//		this.address.setBuildingNum(this.address.getBuildingNum() == null ? "0" : this.address.getBuildingNum());
//		this.district.setCode(this.district.getCode() == null ? "0" : this.district.getCode());
//		this.districtCode =
//			this.districtCode == null ? "0" : this.addressBuildingNum;
//	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		Client client = (Client) o;
//		return getAddressBuildingNum().equals(client.getAddressBuildingNum()) && getDistrictCode().equals(client.getDistrictCode());
//	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(getAddressBuildingNum(), getDistrictCode());
//	}


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
//		this.addressBuildingNum = addressBuildingNum;
//		this.districtCode = districtCode;
		this.addressDetail = addressDetail;
		this.latitude = latitude;
		this.longitude = longitude;
		this.grade = grade;
	}

	public Client() {
	}
}
