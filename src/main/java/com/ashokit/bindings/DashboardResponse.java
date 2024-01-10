package com.ashokit.bindings;

import org.springframework.stereotype.Component;

@Component
public class DashboardResponse {
	
	private Integer totalEnq;
	private Integer enrollerEnq;
	private Integer lostEnq;
	public Integer getTotalEnq() {
		return totalEnq;
	}
	public void setTotalEnq(Integer totalEnq) {
		this.totalEnq = totalEnq;
	}
	public Integer getEnrollerEnq() {
		return enrollerEnq;
	}
	public void setEnrollerEnq(Integer enrollerEnq) {
		this.enrollerEnq = enrollerEnq;
	}
	public Integer getLostEnq() {
		return lostEnq;
	}
	public void setLostEnq(Integer lostEnq) {
		this.lostEnq = lostEnq;
	}
	
	

}
