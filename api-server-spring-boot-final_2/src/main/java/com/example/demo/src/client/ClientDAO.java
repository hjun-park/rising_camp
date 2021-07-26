package com.example.demo.src.client;


import com.example.demo.src.client.model.Client;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Slf4j
public class ClientDAO {

	@PersistenceContext
	private EntityManager em;

//	EntityTransaction tx = em.getTransaction();

	public void createClient(Client client) { ;
		if (client.getId() != null) {
			em.persist(client);
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
	public void updateClient(Client client) {
		Client findClient = em.find(Client.class, client.getId());
		findClient.setName(client.getName());
	}


	//삭제
	public void deleteClient(int clientId) {
		Client findClient = em.find(Client.class, clientId);
		em.remove(findClient);
	}


}
