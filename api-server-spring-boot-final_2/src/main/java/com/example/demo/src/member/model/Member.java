package com.example.demo.src.member.model;

import lombok.*;

import java.time.LocalDateTime;
import static lombok.Lombok.checkNotNull;


@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	public enum Status {
		Joined, Deleted, Suspend;
	}

	private int id;

	private String email;
	private String password;
	private String name;
	private String phoneNumber;
	private String profileImageUrl;
	private String mailAccept;
	private String smsAccept;
	private String birthDate;

	private String addressBuildingNum;
	private String districtCode;
	private String addressDetail;
	private double latitude;
	private double longitude;

	private String grade;
	private Status status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@Builder
	public Member(int id, String email, String password, String name, String phoneNumber, String profileImageUrl, String mailAccept, String smsAccept, String birthDate, String addressBuildingNum, String districtCode, String addressDetail, double latitude, double longitude, String grade) {
		this.id = id;
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

	public void setPassword(String password) {
		this.password = checkNotNull(password, "password");
	}

	public static boolean hasNullDataWhenJoin(Member m) {
		return m.getEmail() == null || m.getPassword() == null ||
			m.getName() == null || m.getPhoneNumber() == null;
	}
}

