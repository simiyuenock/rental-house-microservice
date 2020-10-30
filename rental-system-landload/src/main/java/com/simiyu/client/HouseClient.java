/**
 * 
 */
package com.simiyu.client;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simiyu.model.House;

/**
 * @author enocksimiyu
 *
 */
@FeignClient(name = "house-service")
@RibbonClient(name = "house-service")
public interface HouseClient {


	@RequestMapping(value = "/houses/by_landload_id/{landload_id}")
	public List<House> findHouses(@PathVariable long landload_id);
}
