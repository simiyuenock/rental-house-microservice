/**
 * 
 */
package com.simiyu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author enocksimiyu
 *
 */
@RestControllerAdvice
public class HouseNotFoundController {

	@ExceptionHandler(value = HouseNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void handleNonExistingHouses() {
		
	}
}
