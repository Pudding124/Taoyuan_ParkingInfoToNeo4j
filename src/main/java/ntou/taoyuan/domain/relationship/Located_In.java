package ntou.taoyuan.domain.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import ntou.taoyuan.domain.ParkAbsolutePositionBean;
import ntou.taoyuan.domain.ParkInfoBean;

@RelationshipEntity(type = "LOCATED_IN")
public class Located_In {
	
	 @GraphId 
	 private Long relationshipId;
	 
	 @StartNode
	 ParkAbsolutePositionBean parkAbsolutePositionBean;
	 
	 @EndNode
	 ParkInfoBean parkInfoBean;
	 
	 public Located_In() {}
	 
	 public Located_In(ParkAbsolutePositionBean parkAbsolutePositionBean, ParkInfoBean parkInfoBean) {
		 this.parkAbsolutePositionBean = parkAbsolutePositionBean;
		 this.parkInfoBean = parkInfoBean;
	 }
}
