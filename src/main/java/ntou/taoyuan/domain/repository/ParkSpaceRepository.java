package ntou.taoyuan.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import ntou.taoyuan.domain.ParkSpaceBean;

public interface ParkSpaceRepository extends GraphRepository<ParkSpaceBean>{

	@Query("MATCH (x:ParkInfoBean {address:{address}})-[y]-(z:ParkSpaceBean) RETURN z")
	 public ParkSpaceBean findByAddress(@Param("address") String address);
	
}
