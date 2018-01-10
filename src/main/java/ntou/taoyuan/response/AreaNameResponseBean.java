package ntou.taoyuan.response;

import org.springframework.stereotype.Component;

@Component
public class AreaNameResponseBean {

	private String areaName;
	
	private String address;
	
	private String parkName;
	
	private String introduction;
	
	private int totalSpace;
	
	private String surplusSpace;
	
	private String payGuide;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public int getTotalSpace() {
		return totalSpace;
	}

	public void setTotalSpace(int totalSpace) {
		this.totalSpace = totalSpace;
	}

	public String getSurplusSpace() {
		return surplusSpace;
	}

	public void setSurplusSpace(String surplusSpace) {
		this.surplusSpace = surplusSpace;
	}

	public String getPayGuide() {
		return payGuide;
	}

	public void setPayGuide(String payGuide) {
		this.payGuide = payGuide;
	}
	
}
