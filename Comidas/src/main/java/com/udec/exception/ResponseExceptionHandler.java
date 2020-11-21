package com.udec.exception;

import java.io.IOException;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.udec.dto.ErrorDto;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ModelNotFoundException.class)
	public final ResponseEntity<ErrorDto> ModelNotFoundExceptionHandler(ModelNotFoundException ex, WebRequest request) {
		ErrorDto error = new ErrorDto(404, HttpStatus.NOT_FOUND.toString(), ex.getMessage(), request.getContextPath());
		return new ResponseEntity<ErrorDto>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ErrorDto> NullPointerExceptionHandler(NullPointerException ex, WebRequest request) {
		ex.printStackTrace();
		ErrorDto error = new ErrorDto(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), ex.getMessage(),
				request.getContextPath());
		return new ResponseEntity<ErrorDto>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<ErrorDto> IllegalArgumentExceptionHandler(IllegalArgumentException ex,
			WebRequest request) {
		ex.printStackTrace();
		ErrorDto error = new ErrorDto(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				ex.getMessage(), request.getContextPath());
		return new ResponseEntity<ErrorDto>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IOException.class)
	public final ResponseEntity<ErrorDto> IOExceptionHandler(IOException ex, WebRequest request) {
		ex.printStackTrace();
		ErrorDto error = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getContextPath());
		return new ResponseEntity<ErrorDto>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(NotAcceptableException.class)
	public final ResponseEntity<ErrorDto> IOExceptionHandler(NotAcceptableException ex, WebRequest request) {
		ex.printStackTrace();
		ErrorDto error = new ErrorDto(HttpStatus.NOT_ACCEPTABLE.value(),
				HttpStatus.NOT_ACCEPTABLE.toString(), ex.getMessage(), request.getContextPath());
		return new ResponseEntity<ErrorDto>(error, HttpStatus.NOT_ACCEPTABLE);
	}	
		
	@ExceptionHandler(ArgumentRequiredException.class)
	public final ResponseEntity<ErrorDto> ArgumentHandler(ArgumentRequiredException ex, WebRequest request) {
		ex.printStackTrace();
		ErrorDto error = new ErrorDto(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
				ex.getMessage(), request.getContextPath());
		return new ResponseEntity<ErrorDto>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDto> ExceptionHandler(Exception ex, WebRequest request) {
		ex.printStackTrace();
		ErrorDto error = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getContextPath());
		return new ResponseEntity<ErrorDto>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ErrorDto error = new ErrorDto(status.value(), HttpStatus.BAD_REQUEST.toString(),
				"Argumento(s) no válidos en el JSON", request.getContextPath());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ErrorDto error = new ErrorDto(status.value(), status.METHOD_NOT_ALLOWED.toString(), ex.getMessage(),
				request.getContextPath());
		return new ResponseEntity<Object>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		String mensajes = "";
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			mensajes = mensajes + error.getDefaultMessage() + ". ";
		}
		ErrorDto error = new ErrorDto(status.value(), HttpStatus.BAD_REQUEST.toString(), mensajes,
				request.getContextPath());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ErrorDto error = new ErrorDto(status.value(), HttpStatus.BAD_REQUEST.toString(),
				"Parametro no válido en la url", request.getContextPath());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(status.value(), HttpStatus.BAD_REQUEST.toString(),
				"Parametro no válido en la url", request.getContextPath());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(status.value(), HttpStatus.BAD_REQUEST.toString(),
				"Parametro no válido en la url", request.getContextPath());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDto error = new ErrorDto(status.value(), HttpStatus.BAD_REQUEST.toString(),
				"Parametro no válido en la url", request.getContextPath());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		ErrorDto error = new ErrorDto(status.value(), status.UNSUPPORTED_MEDIA_TYPE.toString(),
				"El servicio solo acepta objetos tipo JSON", request.getContextPath());
		return new ResponseEntity<Object>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
}
