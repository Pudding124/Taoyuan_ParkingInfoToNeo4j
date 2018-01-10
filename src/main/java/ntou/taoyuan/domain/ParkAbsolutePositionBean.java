package ntou.taoyuan.domain;

import java.util.ArrayList;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import ntou.taoyuan.domain.relationship.Located_In;

@NodeEntity
public class ParkAbsolutePositionBean {

	@GraphId
	private Long id;
	
	private Double wgsX;
	
	private Double wgsY;
	
	public ParkAbsolutePositionBean() {}
	
	public ParkAbsolutePositionBean(Double wgsX,Double wgsY) {
		this.wgsX = wgsX;
		this.wgsY = wgsY;
	}
	
	@Relationship(type="LOCATED_IN")
	ArrayList<Located_In> located_Ins = new ArrayList<Located_In>();
	

	public Double getWgsX() {
		return wgsX;
	}

	public void setWgsX(Double wgsX) {
		this.wgsX = wgsX;
	}

	public Double getWgsY() {
		return wgsY;
	}

	public void setWgsY(Double wgsY) {
		this.wgsY = wgsY;
	}
	
	public void addLocatedInRelationship(ParkAbsolutePositionBean parkAbsolutePositionBean, ParkInfoBean parkInfoBean) {
		Located_In located_In = new Located_In(parkAbsolutePositionBean, parkInfoBean);
		located_Ins.add(located_In);
	}
	
	
}
