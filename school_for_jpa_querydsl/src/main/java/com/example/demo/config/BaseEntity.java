package com.example.demo.config;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
    공통적인 속성(컬럼)들을 모아놓고 상속받아서 쓰기 위한 추상 클래스
 */
@MappedSuperclass   // 명시해주면 해당 클래스는 엔티티 속성 상속만을 위한 목적으로 사용하겠단 얘기
public abstract class BaseEntity {

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
