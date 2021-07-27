package com.example.demo.src.client;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.client.model.Client;
import com.example.demo.src.client.model.ClientReq;
import com.example.demo.src.client.model.PostClientReq;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/clients")
public class ClientController {

	private ClientService clientService;
	private JwtService jwtService;

	public ClientController(ClientService clientService, JwtService jwtService) {
		this.clientService = clientService;
		this.jwtService = jwtService;
	}

	@PostMapping("/join")
	public BaseResponse<Long> postJoinClient(@RequestBody PostClientReq postClientReq) {
		log.info("email: {}", postClientReq.getEmail());

		try {
			Long clientId = clientService.join(postClientReq);
			return new BaseResponse<>(clientId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PatchMapping("/{clientId}/status")
	public BaseResponse<Long> deleteClient(@PathVariable Long clientId) {
		try {
			Long resultId = clientService.removeClient(clientId);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}


}
