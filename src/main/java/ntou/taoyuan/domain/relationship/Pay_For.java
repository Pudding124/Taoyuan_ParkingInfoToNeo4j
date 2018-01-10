package ntou.taoyuan.domain.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import ntou.taoyuan.domain.ParkInfoBean;
import ntou.taoyuan.domain.ParkPriceBean;

@RelationshipEntity(type = "PAY_FOR")
public class Pay_For {

	@GraphId 
	private Long relationshipId;
	
	@StartNode
	ParkPriceBean parkPriceBean;
	
	@EndNode
	ParkInfoBean parkInfoBean;
	
	public Pay_For() {}
	
	public Pay_For(ParkPriceBean parkPriceBean, ParkInfoBean parkInfoBean) {
		this.parkPriceBean = parkPriceBean;
		this.parkInfoBean = parkInfoBean;
	}
	
}
