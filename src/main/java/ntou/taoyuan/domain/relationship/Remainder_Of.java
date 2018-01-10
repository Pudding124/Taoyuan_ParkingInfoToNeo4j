package ntou.taoyuan.domain.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import ntou.taoyuan.domain.ParkInfoBean;
import ntou.taoyuan.domain.ParkSpaceBean;

@RelationshipEntity(type = "REMAINDER_OF")
public class Remainder_Of {

	@GraphId 
	private Long relationshipId;
	
	@StartNode
	ParkSpaceBean parkSpaceBean;
	
	@EndNode
	ParkInfoBean parkInfoBean;
	
	public Remainder_Of() {}
	
	public Remainder_Of(ParkSpaceBean parkSpaceBean, ParkInfoBean parkInfoBean) {
		this.parkSpaceBean = parkSpaceBean;
		this.parkInfoBean = parkInfoBean;
	}
	
}
