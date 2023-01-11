package com.Api.Exceptionhandler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Api.entity.Response;

@RestControllerAdvice
public class exceptionHandler extends RuntimeException{
	//or you can also use response entity exception handler

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Response> dataHandler(EmptyResultDataAccessException ex)
	{
		String text="User id Not Exist in database";
		Response rp = new Response();
		return new ResponseEntity(new Response(text,false),HttpStatus.BAD_REQUEST);
	}
//	
	@ExceptionHandler(resourceNotFound.class)
	public ResponseEntity<Response> resoucenotfound(resourceNotFound e)
	{
		String text="Position Not Found";
		Response r = new Response(text,false);
		return new ResponseEntity<Response>(r,HttpStatus.BAD_REQUEST);
	}
}
