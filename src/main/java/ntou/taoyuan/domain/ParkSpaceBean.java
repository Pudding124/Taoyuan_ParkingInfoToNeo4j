package ntou.taoyuan.domain;

import java.util.ArrayList;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import ntou.taoyuan.domain.relationship.Remainder_Of;

@NodeEntity
public class ParkSpaceBean {
	
	@GraphId
	private Long id;
	
	private int totalSpace;
	
	private String surplusSpace;
	
	public ParkSpaceBean() {}
	
	public ParkSpaceBean(int totalSpace, String surplusSpace) {
		this.totalSpace = totalSpace;
		this.surplusSpace = surplusSpace;
	}
	
	@Relationship(type="REMAINDER_OF")
	ArrayList<Remainder_Of> remainder_Ofs = new ArrayList<Remainder_Of>();

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

	public void addRemainderOfRelationship(ParkSpaceBean parkSpaceBean, ParkInfoBean parkInfoBean) {
		Remainder_Of remainder_Of = new Remainder_Of(parkSpaceBean,parkInfoBean);
		remainder_Ofs.add(remainder_Of);
	}
	
}