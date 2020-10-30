/**
 * 
 */
package com.simiyu.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

import com.simiyu.exception.HouseNotFoundException;
import com.simiyu.model.House;
import com.simiyu.repository.HouseRepository;

/**
 * @author enocksimiyu
 *
 */
@RestController
@RequestMapping("/houses")
public class HouseController {

	private HouseRepository houseRepository;
	
	public HouseController(HouseRepository houseRepository) {
		this.houseRepository = houseRepository;
	}
	
	@ApiOperation(value = "Retrieve list of all available houses")	
	@GetMapping(value = "/")
	public Iterable<?> findAllHouses() {
		Iterable<?> houses = houseRepository.findAll();
		return houses;		
	}
	
	@ApiOperation(value = "Retrieve house by its id")	
	@GetMapping(value = "/house_by_id/{id}")
	public ResponseEntity<House> findHouseById(@PathVariable long id) {		
		House house_found = houseRepository.getOne(id);
		if(house_found == null)throw new HouseNotFoundException();
					
			return new ResponseEntity<House>(house_found, HttpStatus.OK);		
				
	}
	
	@ApiOperation(value = "Create a new house")	
	@PostMapping(value = "/add_house")
	public ResponseEntity<House> create_house(@RequestBody House newHouse){
		try {
		House new_house = new House();
		new_house.setHouse_number(newHouse.getHouse_number());
		new_house.setHouse_type(newHouse.getHouse_type());
		new_house.setOccupied(newHouse.isOccupied());
		new_house.setLandload_id(newHouse.getLandload_id());
		return new ResponseEntity<House>(houseRepository.save(new_house), HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Update house details")	
	@PutMapping(value = "/update_house/{id}")
	public ResponseEntity<House> updateHouse(@PathVariable long id, @RequestBody House house){
		try {
		House update_house = houseRepository.getOne(id);
		if(update_house == null) throw new HouseNotFoundException();
		
		update_house.setHouse_number(house.getHouse_number());
		update_house.setHouse_type(house.getHouse_type());
		update_house.setOccupied(house.isOccupied());
		update_house.setLandload_id(house.getLandload_id());
		return new ResponseEntity<House>(houseRepository.save(update_house), HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	}
	@ApiOperation(value = "Retrieve houses by the landload id")	
	@GetMapping(value = "/by_landload_id/{landload_id}")
	public ResponseEntity<List<House>> findByLandload_id(@PathVariable long landload_id){
		
		List<House> houses_by_landload = houseRepository.findAllByLandload_id(landload_id);
		if(houses_by_landload.isEmpty()) throw new HouseNotFoundException();
	
			return new ResponseEntity<List<House>>(houses_by_landload, HttpStatus.OK);
		
	}
}
