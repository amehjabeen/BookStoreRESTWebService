package com.bookstore.service.resources;

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;

public class CustomWebException extends WebApplicationException{
	
	private String message;

	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 4421476552727274607L;

	public CustomWebException(String message){
		super (Response.status(Response.Status.BAD_REQUEST).entity(message).type(MediaType.TEXT_PLAIN).build());
		this.setMessage(message);
	}

	public CustomWebException(List<String> messageList){
		super (Response.status(Response.Status.BAD_REQUEST).entity(StringUtils.join(messageList, ", ")).type(MediaType.TEXT_PLAIN).build());
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
