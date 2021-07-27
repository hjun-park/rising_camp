package com.example.demo.src.client;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.client.model.Client;
import com.example.demo.src.client.model.ClientReq;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Repository
@Slf4j
@Transactional(readOnly = true)
public class ClientDAO {

	@PersistenceContext
	private EntityManager em;

//	EntityTransaction tx = em.getTransaction();

	@Transactional
	public Long createClient(Client client) throws BaseException {
		log.info(String.valueOf(client.getId()));
		log.info(client.getEmail());
		log.info("======================");

		try {
			em.persist(client);
			em.flush();
			return client.getId();
		} catch (Exception exception)  {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}

	}

	// 하나만 조회
	public Client findClientById(Long clientId) {
		return em.find(Client.class, clientId);
	}

	// 리스트 조회
	public List<Client> findAll() {
		return em.createQuery("select c from Client c WHERE c.status = 'Joined'", Client.class)
			.getResultList();
	}

	// 이메일로 조회
	public List<Client> findByEmail(String email) {
		return em.createQuery("select c from Client c " +
			"where c.email = :email and c.status = 'Joined'", Client.class)
			.getResultList();
	}

	//이름 수정
	@Transactional
	public Long updateClient(Client client) {
		Client findClient = em.find(Client.class, client.getId());
		findClient.setName(client.getName());

		return client.getId();
	}


	//삭제
	@Transactional
	public Long deleteClient(Long clientId) throws BaseException {
		Client findClient = em.find(Client.class, clientId);
		em.remove(findClient);

		return clientId;

	}


}
