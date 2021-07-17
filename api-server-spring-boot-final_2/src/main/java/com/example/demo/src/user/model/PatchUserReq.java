package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.sound.midi.Patch;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PatchUserReq {
	private int id;
	private String name;
	private String password;
	private String phoneNumber;
	private String profileImageUrl;
	private int mailAccept;
	private int smsAccept;
	private Date birthDate;

	public PatchUserReq(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public PatchUserReq() {

	}
}
