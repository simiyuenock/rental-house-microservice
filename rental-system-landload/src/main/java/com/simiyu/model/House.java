/**
 * 
 */
package com.simiyu.model;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author enocksimiyu
 *
 */
@Entity
public class House {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long house_number;
	private House_Type house_type;
	private boolean occupied;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getHouse_number() {
		return house_number;
	}
	public void setHouse_number(long house_number) {
		this.house_number = house_number;
	}
	public House_Type getHouse_type() {
		return house_type;
	}
	public void setHouse_type(House_Type house_type) {
		this.house_type = house_type;
	}
	public boolean isOccupied() {
		return occupied;
	}
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	
	
	
}
