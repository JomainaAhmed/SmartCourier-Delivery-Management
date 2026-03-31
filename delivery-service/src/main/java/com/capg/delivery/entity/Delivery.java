package com.capg.delivery.entity;

import com.capg.delivery.enums.DeliveryStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Address sender;

    @OneToOne(cascade = CascadeType.ALL)
    private Address receiver;

    @OneToOne(cascade = CascadeType.ALL)
    private PackageEntity packageEntity;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Delivery() {}

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getSender() {
		return sender;
	}

	public void setSender(Address sender) {
		this.sender = sender;
	}

	public Address getReceiver() {
		return receiver;
	}

	public void setReceiver(Address receiver) {
		this.receiver = receiver;
	}

	public PackageEntity getPackageEntity() {
		return packageEntity;
	}

	public void setPackageEntity(PackageEntity packageEntity) {
		this.packageEntity = packageEntity;
	}

	public DeliveryStatus getStatus() {
		return status;
	}

	public void setStatus(DeliveryStatus status) {
		this.status = status;
	}

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