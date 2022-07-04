package co.com.mercadolibre.mutants.controlleradvice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import co.com.mercadolibre.mutants.exception.AccesDeniedException;
import co.com.mercadolibre.mutants.exception.BusinessException;
import co.com.mercadolibre.mutants.exception.DataBaseIntegrityException;



@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {BusinessException.class})
	protected ResponseEntity<Object> businessExceptionHandler(RuntimeException exception, WebRequest request) {
				return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.INSUFFICIENT_STORAGE, request);
	}
	
	@ExceptionHandler(value = {DataBaseIntegrityException.class})
	protected ResponseEntity<Object> dataBaseIntegrityExceptionHandler(RuntimeException exception, WebRequest request) {
				return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = {RuntimeException.class})
	protected ResponseEntity<Object> genericExceptionHandler(RuntimeException exception, WebRequest request) {
				return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(value = {AccesDeniedException.class})
	protected ResponseEntity<Object> accessExceptionHandler(RuntimeException exception, WebRequest request) {
				return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}

}

