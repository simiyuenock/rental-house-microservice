/**
 * 
 */
package com.simiyu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simiyu.model.LandLoad;
import com.simiyu.repository.LandloadRepository;

/**
 * @author enocksimiyu
 *
 */
@Configuration
public class LoadDatabase {

	private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);
	
	@Bean
	public CommandLineRunner dbData(LandloadRepository landloadRepository) {
		
		return args ->{
			logger.info("Landload no 1" + landloadRepository.save(new LandLoad(12345,"Simiyu Enock")));
			logger.info("Landload no 1" + landloadRepository.save(new LandLoad(12346,"Bella Mukolo")));
			//logger.info("Landload no 1" + landloadRepository.save(new LandLoad(12347,"Derick Musa")));
			logger.info("Landload no 1" + landloadRepository.save(new LandLoad(12348,"Augustine Toleta")));
			logger.info("Landload no 1" + landloadRepository.save(new LandLoad(12349,"Palmer Simiyu")));
		};
	}
}
