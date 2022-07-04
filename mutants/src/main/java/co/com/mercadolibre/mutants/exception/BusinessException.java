package co.com.mercadolibre.mutants.exception;

@SuppressWarnings("serial")
public class BusinessException extends RuntimeException {
	
	public BusinessException(String message) {
		super(message);
	}

}
