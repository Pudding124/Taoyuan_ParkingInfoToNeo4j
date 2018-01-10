package ntou.taoyuan.domain;

import java.util.ArrayList;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import ntou.taoyuan.domain.relationship.Pay_For;


@NodeEntity
public class ParkPriceBean {

	@GraphId
	private Long id;
	
	private String payGuide;
	
	public ParkPriceBean() {}
	
	public ParkPriceBean(String payGuide) {
		this.payGuide = payGuide;
	}
	
	@Relationship(type="PAY_FOR")
	ArrayList<Pay_For> pay_Fors = new ArrayList<Pay_For>();

	public String getPayGuide() {
		return payGuide;
	}

	public void setPayGuide(String payGuide) {
		this.payGuide = payGuide;
	}
	
	public void addPayForRelationship(ParkPriceBean parkPriceBean,ParkInfoBean parkInfoBean) {
		Pay_For pay_For = new Pay_For(parkPriceBean, parkInfoBean);
		pay_Fors.add(pay_For);
	}
	
}
