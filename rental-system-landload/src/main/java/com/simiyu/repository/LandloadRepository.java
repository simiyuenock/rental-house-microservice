/**
 * 
 */
package com.simiyu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.simiyu.model.House;
import com.simiyu.model.LandLoad;

/**
 * @author enocksimiyu
 *
 */
public interface LandloadRepository extends JpaRepository<LandLoad, Long>{

	@Query(value = "SELECT * FROM landload l WHERE l.landload_id = :landload_id", nativeQuery = true)
	public LandLoad findByLandload_id(@Param("landload_id") long landload_id);
}
