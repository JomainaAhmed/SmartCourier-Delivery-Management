package com.capg.admin.dto;

public class ReportDto {

    private int total;
    private int delivered;
    private int failed;
    private int delayed;
    private double successRate;
    
    private double failureRate;
    private double delayRate; 
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getDelivered() {
		return delivered;
	}
	public void setDelivered(int delivered) {
		this.delivered = delivered;
	}
	public int getFailed() {
		return failed;
	}
	public void setFailed(int failed) {
		this.failed = failed;
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
	public double getFailureRate() {
		return failureRate;
	}
	public void setFailureRate(double failureRate) {
		this.failureRate = failureRate;
	}
	public double getDelayRate() {
		return delayRate;
	}
	public void setDelayRate(double delayRate) {
		this.delayRate = delayRate;
	}

}