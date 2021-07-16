package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchOrderDetailReq {
	private int userId;
	private int orderId;
	private String orderStatus;
}
