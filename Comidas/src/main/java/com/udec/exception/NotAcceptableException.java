package com.udec.exception;

public class NotAcceptableException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public NotAcceptableException(String mensaje) {		
		super(mensaje);
	}

}
