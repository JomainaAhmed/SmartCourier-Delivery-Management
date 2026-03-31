package com.capg.admin.dto;

public class DashboardDto {

    private int total;
    private int booked;
    private int inTransit;
    private int delivered;
    private int delayed;
    private double successRate;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getBooked() {
		return booked;
	}
	public void setBooked(int booked) {
		this.booked = booked;
	}
	public int getInTransit() {
		return inTransit;
	}
	public void setInTransit(int inTransit) {
		this.inTransit = inTransit;
	}
	public int getDelivered() {
		return delivered;
	}
	public void setDelivered(int delivered) {
		this.delivered = delivered;
	}
	public int getDelayed() {
		return delayed;
	}
	public void setDelayed(int delayed) {
		this.delayed = delayed;
	}
	public double getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}

}