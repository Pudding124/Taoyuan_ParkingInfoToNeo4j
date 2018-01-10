package ntou.taoyuan.domain.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import ntou.taoyuan.domain.ParkInfoBean;

public interface ParkInfoRepository extends GraphRepository<ParkInfoBean> {

	@Query("MATCH (x:ParkInfoBean {areaName:{areaName}}) RETURN x")
	List<ParkInfoBean> findByAreaName(@Param("areaName") String areaName);
}
