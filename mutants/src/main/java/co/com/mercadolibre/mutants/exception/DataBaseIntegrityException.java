package co.com.mercadolibre.mutants.exception;

@SuppressWarnings("serial")
public class DataBaseIntegrityException extends RuntimeException {

	public DataBaseIntegrityException(String message) {
		super(message);
	}
	
}