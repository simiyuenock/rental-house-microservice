/**
 * 
 */
package com.simiyu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.simiyu.model.House;

/**
 * @author enocksimiyu
 *
 */
public interface HouseRepository extends JpaRepository<House, Long>{

	@Query(value = "SELECT * FROM house h WHERE h.landload_id = :landload_id", nativeQuery = true)
	public List<House> findAllByLandload_id(@Param("landload_id") long landload_id);
	
	
	@Query(value = "SELECT * FROM house h WHERE h.occupied = :occupied", nativeQuery = true)
	public List<House> occupied_houses(@Param("occupied") boolean occupied);
}
