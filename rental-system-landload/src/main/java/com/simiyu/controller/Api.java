/**
 * 
 */
package com.simiyu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.simiyu.client.HouseClient;
import com.simiyu.model.LandLoad;
import com.simiyu.repository.LandloadRepository;

/**
 * @author enocksimiyu
 *
 */
@RestController
public class Api {

	private HouseClient houseClient;
	private LandloadRepository landloadRepository;
	
	public Api(HouseClient houseClient, LandloadRepository landloadRepository) {
		this.houseClient = houseClient;
		this.landloadRepository = landloadRepository;
	}
	
	@GetMapping("/landloads")
	public ResponseEntity<List<LandLoad>> getLandloads(){
		List<LandLoad> allLandLoads = landloadRepository.findAll();
		
		return new ResponseEntity<>(allLandLoads,HttpStatus.OK);
	}
	
	@GetMapping(value = "/landloads/landload_id/{landload_id}")
	public ResponseEntity<LandLoad> findbyLandload_id(@PathVariable long landload_id){
		try {
		LandLoad getLandload = landloadRepository.findByLandload_id(landload_id);
		if(getLandload != null) {
			getLandload.setHouses(houseClient.findHouses(landload_id));
			return new ResponseEntity<LandLoad>(getLandload,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/landloads/{landload_id}")
	public ResponseEntity<LandLoad> getLandload(@PathVariable long landload_id){
		LandLoad singleLandLoad = landloadRepository.findByLandload_id(landload_id);
		if(singleLandLoad == null) {
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(singleLandLoad,HttpStatus.OK);
	}
	
}
