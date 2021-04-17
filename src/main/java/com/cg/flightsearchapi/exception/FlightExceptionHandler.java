package com.cg.flightsearchapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
	public class FlightExceptionHandler extends ResponseEntityExceptionHandler{ 
		private long currentTimeMillis = System.currentTimeMillis();
		private String errorMsg = "Some thing went wrong!";
		
		@ExceptionHandler(Exception.class)
		public final ResponseEntity<ErrorMessage> somethingWentWrong(Exception ex){
			
			ErrorMessage exceptionResponse =
					new ErrorMessage(ex.getMessage(), 
							errorMsg,currentTimeMillis);
			return new ResponseEntity<ErrorMessage>(exceptionResponse,
					new HttpHeaders(),HttpStatus.BAD_REQUEST);
			
		}
		
		@ExceptionHandler(NullValueException.class)
		public final ResponseEntity<ErrorMessage> nullParameter(NullValueException ex){

			ErrorMessage exceptionResponse =
					new ErrorMessage(ex.getMessage(), 
							errorMsg,currentTimeMillis);
			return new ResponseEntity<ErrorMessage>(exceptionResponse,
					new HttpHeaders(),HttpStatus.NOT_ACCEPTABLE);
		}
		
		

		
		@ExceptionHandler(FlightNotFoundException.class)
		public final ResponseEntity<ErrorMessage> flightNotFound(FlightNotFoundException ex){

			ErrorMessage exceptionResponse =
					new ErrorMessage(ex.getMessage(), 
							errorMsg,currentTimeMillis);
			return new ResponseEntity<ErrorMessage>(exceptionResponse,
					new HttpHeaders(),HttpStatus.NOT_FOUND);
		}

}

