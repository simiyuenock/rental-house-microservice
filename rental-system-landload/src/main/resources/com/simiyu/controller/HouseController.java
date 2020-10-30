/**
 * 
 */
package com.simiyu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simiyu.model.House;
import com.simiyu.repository.HouseRepository;

/**
 * @author enocksimiyu
 *
 */
@RestController
public class HouseController {

	private HouseRepository houseRepository;
	
	public HouseController(HouseRepository houseRepository) {
		this.houseRepository = houseRepository;
	}
	
	// retrieve all houses
	@GetMapping(value = "/houses")
	public ResponseEntity<List<House>> findAll(){
		try {
			List<House> all_houses = houseRepository.findAll();
			if(all_houses != null) {
				return new ResponseEntity<List<House>>(all_houses, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	// find single house by id
	@GetMapping(value = "/houses/id/{id}")
	public ResponseEntity<House> getHouseById(@PathVariable long id){
		House single_house = houseRepository.getOne(id);
		if(single_house != null) {
			return new ResponseEntity<House>(single_house, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
	
	//find occupied houses 
	@GetMapping(value= "/houses/occupied/{occupied}")
	public ResponseEntity<List<House>> occupied(@PathVariable boolean occupied){
		List<House> occupied_houses = houseRepository.occupied_houses(occupied);
		if(occupied_houses.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(occupied_houses, HttpStatus.OK);
		}
	}
	
	//add a house
	@PostMapping(value = "/houses")
	public ResponseEntity<House> create_House(@RequestBody House house){
		try {
		House newHouse = houseRepository.save(house);
		long newHouseId = newHouse.getId();
		if(newHouseId > 0) {
		return new ResponseEntity<>(newHouse, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//update house
	@PutMapping(value = "/houses/{id}")
	public ResponseEntity<House> updateHouse(@PathVariable long id, @RequestBody House updated_house){
		try {
			House house_found = houseRepository.getOne(id);
			if(house_found != null) {
				House house_update = new House();
				house_update.setId(house_found.getId());
				house_update.setHouse_number(updated_house.getHouse_number());
				house_update.setHouse_type(updated_house.getHouse_type());
				house_update.setOccupied(updated_house.isOccupied());
				return new ResponseEntity<>(houseRepository.save(house_update),HttpStatus.OK);
			}else {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
	
	//delete House
	@DeleteMapping(value = "/houses/{id}")
	public ResponseEntity<House> delete_house(@PathVariable long id){
		House get_house_to_delete = houseRepository.getOne(id);
		if(get_house_to_delete != null) {
			houseRepository.delete(get_house_to_delete);
			return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
		}else
		{
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
	}
}
