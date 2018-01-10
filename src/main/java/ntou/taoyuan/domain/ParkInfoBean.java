package ntou.taoyuan.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class ParkInfoBean {
	
	@GraphId
	private Long id;
	
	private String areaId;
	
	private String areaName;
	
	private String parkId;
	
	private String parkName;
	
	private String introduction;
	
	private String address;
	
	public ParkInfoBean(){}
	
	public ParkInfoBean(String areaId, String areaName, String parkId, String parkName, String introduction, String address){
		
		this.areaId = areaId;
		this.areaName = areaName;
		this.parkId = parkId;
		this.parkName = parkName;
		this.introduction = introduction;
		this.address = address;
		
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getParkId() {
		return parkId;
	}

	public void setParkId(String parkId) {
		this.parkId = parkId;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
