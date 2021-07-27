package com.example.demo.src.client;

import com.example.demo.src.client.model.Client;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) // Junit4의 RunWith와 같음
@SpringBootTest
@AutoConfigureMockMvc // unused
public class ClientDAOTest {

	@Autowired
	ClientDAO clientDAO;

	@Test
	@Transactional
	@Rollback(false)	// 내 눈을 의심하고 롤백 노노 원할 때
	public void testClient() throws Exception {
		//given
		Client client = Client.builder()
		.email("ph123@gmail.com")
		.password("pwd1231233123")
		.name("testAB")
		.phoneNumber("012313412412")
		.profileImageUrl(null)
		.birthDate("19961022")
		.build();

		//when
		Long clientId = clientDAO.createClient(client);
		Client findClient = clientDAO.findClientById(clientId);

	    //then
		Assertions.assertThat(findClient.getId()).isEqualTo(client.getId());
		Assertions.assertThat(findClient.getName()).isEqualTo(client.getName());


	}




}
