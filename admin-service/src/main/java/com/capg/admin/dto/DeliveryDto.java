package com.capg.admin.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryDto {

    private Long id;
    private String status;
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