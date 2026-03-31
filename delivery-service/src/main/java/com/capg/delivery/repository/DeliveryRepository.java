package com.capg.delivery.repository;

import com.capg.delivery.entity.Delivery;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
	
	
}