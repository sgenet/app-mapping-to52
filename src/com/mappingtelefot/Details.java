package com.mappingtelefot;

import java.io.Serializable;

public class Details implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	private String deviceId;
	private String deviceModel;
	private String deviceBrand;
	private String phoneNumber;
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserId() {
		return userId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setDeviceBrand(String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}
	public String getDeviceBrand() {
		return deviceBrand;
	}

}
