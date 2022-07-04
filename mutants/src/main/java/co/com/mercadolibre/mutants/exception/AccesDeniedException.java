package co.com.mercadolibre.mutants.exception;

@SuppressWarnings("serial")
public class AccesDeniedException extends RuntimeException {

	public AccesDeniedException(String message) {
		super(message);
	}
	
}