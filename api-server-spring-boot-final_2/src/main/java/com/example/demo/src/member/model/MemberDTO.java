package com.example.demo.src.member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@ToString
public class MemberDTO {
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

	private int grade;
	private Status status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public MemberDTO(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public MemberDTO() {
	}

	public static boolean hasNullDataWhenJoin(MemberDTO m) {
		return m.getEmail() == null || m.getPassword() == null ||
			m.getName() == null || m.getPhoneNumber() == null;
	}
}

