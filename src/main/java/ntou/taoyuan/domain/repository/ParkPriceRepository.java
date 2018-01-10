package ntou.taoyuan.domain.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import ntou.taoyuan.domain.ParkPriceBean;

public interface ParkPriceRepository extends GraphRepository<ParkPriceBean>{

	@Query("MATCH (x:ParkInfoBean {address:{address}})-[y]-(z:ParkPriceBean) RETURN z")
	 public ParkPriceBean findByAddress(@Param("address") String address);
	
}
