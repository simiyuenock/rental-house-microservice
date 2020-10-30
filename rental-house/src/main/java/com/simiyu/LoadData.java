/**
 * 
 */
package com.simiyu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simiyu.model.House;
import com.simiyu.model.House_Type;
import com.simiyu.repository.HouseRepository;

/**
 * @author enocksimiyu
 *
 */
@Configuration
public class LoadData {

	private static final Logger logger = LoggerFactory.getLogger(LoadData.class); 
	
	@Bean
	public CommandLineRunner db(HouseRepository houseRepository) {
		return args ->{
		logger.info("House Number : " + houseRepository.save(new House(100, House_Type.NORMAL, false, 12345)));
		logger.info("House Number : " + houseRepository.save(new House(101, House_Type.NORMAL, false, 12346)));
		logger.info("House Number : " + houseRepository.save(new House(102, House_Type.STORY, false, 12345)));
		logger.info("House Number : " + houseRepository.save(new House(103, House_Type.NORMAL, true, 12346)));
		logger.info("House Number : " + houseRepository.save(new House(104, House_Type.NORMAL, false, 12349)));
		};
	}
}
