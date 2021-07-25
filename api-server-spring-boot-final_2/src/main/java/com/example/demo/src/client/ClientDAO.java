package com.example.demo.src.client;


import com.example.demo.src.client.model.Client;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = false)
@Slf4j
public class ClientDAO {

	private JwtService jwtService;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void createClient(Client client) {
		em.persist();
	}

	public void findClientById(int clientId) {

	}

	@Transactional
	public void updateClient(Client client) {

	}

	@Transactional
	public void deleteClient(int clientId) {

	}







}
