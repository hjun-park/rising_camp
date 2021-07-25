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
	public void createUser(Client client) {
		em.persist();
	}

	@Transactional
	





}
