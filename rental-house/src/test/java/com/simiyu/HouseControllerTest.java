/**
 * 
 */
package com.simiyu;

import org.aspectj.lang.annotation.Before;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simiyu.api.HouseController;
import com.simiyu.exception.HouseNotFoundController;
import com.simiyu.exception.HouseNotFoundException;
import com.simiyu.model.House;
import com.simiyu.model.House_Type;
import com.simiyu.repository.HouseRepository;

/**
 * @author enocksimiyu
 *
 */
@ExtendWith(MockitoExtension.class)
public class HouseControllerTest {

	private MockMvc mvc;
	
	@Mock
	private HouseRepository houseRepository;
	
	@InjectMocks
	private HouseController houseController;
	
	private JacksonTester<House> jsonHouse;
	
	
	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		
		mvc = MockMvcBuilders.standaloneSetup(houseController)
				.setControllerAdvice(new HouseNotFoundController())				
				.build();
	}
	@Test
	public void retrieveHouseByIdIfExists() throws Exception{
		given(houseRepository.getOne((long) 1))
			.willReturn(new House(1,100, House_Type.NORMAL, false, 12345));
		MockHttpServletResponse response = mvc.perform(
				get("/houses/house_by_id/1")
					.accept(MediaType.APPLICATION_JSON))
					.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonHouse.write(new House(1,100, House_Type.NORMAL, false, 12345)).getJson()
		);
	}
	
	@Test
	public void retriveHouseByIdWhenDoesNOtExist() throws Exception{
		given(houseRepository.getOne((long) 10)).willThrow(new HouseNotFoundException());
		
		MockHttpServletResponse response = mvc.perform(
				get("/houses/house_by_id/10")
					.accept(MediaType.APPLICATION_JSON))
					.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getContentAsString()).isEmpty();	
	}
	
	@Test
	public void createHouseTest() throws Exception{
		MockHttpServletResponse response = mvc.perform(post("/houses/add_house").contentType(MediaType.APPLICATION_JSON).content(jsonHouse.write(new House(150, House_Type.STORY, false, 12340)).getJson())).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
	}
	
	@Test
	public void updateHouseDetailsIfExists() throws Exception{
		given(houseRepository.getOne((long) 1))
		.willReturn(new House(1,100, House_Type.NORMAL, false, 12345));
		
		House uphouse = new House();
		uphouse.setHouse_number(400);
		uphouse.setHouse_type(House_Type.STORY);
		uphouse.setOccupied(true);
		MockHttpServletResponse response = mvc.perform(put("/houses/update_house/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonHouse.write(uphouse)
				.getJson())).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
	}
	
	@Test
	public void findByLandload_id_Test() throws Exception{
		List<House> house_list = new ArrayList<>();
		House house1 = new House(100, House_Type.NORMAL, false, 12345);
		House house2 = new House(101, House_Type.NORMAL, false, 12346);
		House house3 = new House(102, House_Type.STORY, false, 12345);
		House house4 = new House(103, House_Type.NORMAL, true, 12346);
		House house5 = new House(104, House_Type.NORMAL, false, 12349);
		house_list.add(house1);
		house_list.add(house2);
		house_list.add(house3);
		house_list.add(house4);
		house_list.add(house5);
		given(houseRepository.findAllByLandload_id(12345)).willReturn(house_list);
		
		MockHttpServletResponse response = mvc.perform(get("/houses/by_landload_id/12345")
				.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
	}
}
