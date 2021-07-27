package com.example.demo.src.order.model;

import com.example.demo.src.client.model.Client;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ORDERS")
public class Orders {
	public enum Status {
		Order, Delivery, Cancel, Complete;
	}

	@Id
	private int id;
	private int storeId;
	private int memberId;
	private String addressBuildingNum;
	private String addressDetail;
	private int tips;
	private Status status;
	private String storeRequest;
	private String riderRequest;
	private int riderId;
	private LocalDateTime orderTime;

	// 연관관계 주인
	@ManyToOne
	@JoinColumn(name = "client_id") // 클라이언트 엔티티의 id를 참조한다는 뜻
	private Client client;


	@Builder
	public Orders(int id, int storeId, int memberId, String addressBuildingNum, String addressDetail, int tips, Status status, String storeRequest, String riderRequest, int riderId, LocalDateTime orderTime) {
		this.id = id;
		this.storeId = storeId;
		this.memberId = memberId;
		this.addressBuildingNum = addressBuildingNum;
		this.addressDetail = addressDetail;
		this.tips = tips;
		this.status = status;
		this.storeRequest = storeRequest;
		this.riderRequest = riderRequest;
		this.riderId = riderId;
		this.orderTime = orderTime;
	}
}
