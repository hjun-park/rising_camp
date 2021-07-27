package com.example.demo.src.client;

import com.example.demo.config.BaseException;
import com.example.demo.src.client.model.Client;
import com.example.demo.src.client.model.ClientReq;
import com.example.demo.src.client.model.PostClientReq;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ClientService {

	private final ClientDAO clientDAO;
	private final JwtService jwtService;

	public ClientService(ClientDAO clientDAO, JwtService jwtService) {
		this.clientDAO = clientDAO;
		this.jwtService = jwtService;
	}

	// 유저 생성
	public Long join(PostClientReq postClientReq) throws BaseException {
		/*
			private String email;
			private String password;
			private String name;
			private String phoneNumber;
			private String profileImageUrl;
			private String birthDate;
		 */
		// 검증 필요한 방법
		Client client = Client.builder()
			.email(postClientReq.getEmail())
			.password(postClientReq.getPassword())
			.name(postClientReq.getName())
			.phoneNumber(postClientReq.getPhoneNumber())
			.profileImageUrl(postClientReq.getProfileImageUrl())
			.birthDate(postClientReq.getBirthDate())
			.build();


		return clientDAO.createClient(client);

	}

	// 유저 조회
	public Client findClient(Long clientId) throws BaseException {

		return clientDAO.findClientById(clientId);
	}

	// 유저 전체 조회
	public List<Client> findClients() throws BaseException {

		return clientDAO.findAll();
	}

	// 유저 정보 수정
	public Long modifyClient(ClientReq clientReq) throws BaseException {

		Client client = new Client();

		// 삽입작업

		return clientDAO.updateClient(client);
	}

	// 유저 삭제
	public Long removeClient(Long clientId) throws BaseException {

		return clientDAO.deleteClient(clientId);
	}





}
