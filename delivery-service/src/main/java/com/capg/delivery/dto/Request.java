package com.capg.delivery.dto;

import com.capg.delivery.entity.Address;
import com.capg.delivery.entity.PackageEntity;

import jakarta.validation.constraints.NotNull;

public class Request {

	@NotNull
	 private Address sender;

	 @NotNull
	 private Address receiver;

	 @NotNull
	 private PackageEntity packageDetails;

    public Address getSender() { return sender; }
    public void setSender(Address sender) { this.sender = sender; }

    public Address getReceiver() { return receiver; }
    public void setReceiver(Address receiver) { this.receiver = receiver; }

    public PackageEntity getPackageDetails() { return packageDetails; }
    public void setPackageDetails(PackageEntity packageDetails) { this.packageDetails = packageDetails; }
}