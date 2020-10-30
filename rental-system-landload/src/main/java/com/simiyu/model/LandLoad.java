/**
 * 
 */
package com.simiyu.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author enocksimiyu
 *
 */
@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@Table(name = "landload")
public class LandLoad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long landload_id;
	private String name;
	
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "landload_house", referencedColumnName = "id")
	private List<House> houses;
	
	public LandLoad() {
		
	}
	
	public LandLoad(long landload_id, String name) {
		this.landload_id = landload_id;
		this.name = name;
	}

	
	public LandLoad(long landload_id, String name, List<House> houses) {
		this.landload_id = landload_id;
		this.name = name;
		this.houses = houses;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getLandload_id() {
		return landload_id;
	}

	public void setLandload_id(long landload_id) {
		this.landload_id = landload_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<House> getHouses() {
		return houses;
	}

	public void setHouses(List<House> houses) {
		this.houses = houses;
	}

	@Override
	public String toString() {
		return "LandLoad [id=" + id + ", landload_id=" + landload_id + ", name=" + name + ", houses=" + houses + "]";
	}
	
	
	
}
