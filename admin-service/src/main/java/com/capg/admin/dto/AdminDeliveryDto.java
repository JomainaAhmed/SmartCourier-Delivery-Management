package com.capg.admin.dto;

public class AdminDeliveryDto {

    private Long id;
    private String status;
    private double price;

    private AddressDto sender;
    private AddressDto receiver;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public AddressDto getSender() {
		return sender;
	}
	public void setSender(AddressDto sender) {
		this.sender = sender;
	}
	public AddressDto getReceiver() {
		return receiver;
	}
	public void setReceiver(AddressDto receiver) {
		this.receiver = receiver;
	}

}
