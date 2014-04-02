package fr.inria.atlanmod.kyanos.datastore.exceptions;


public class InvalidOptionsException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidOptionsException() {
	}
	
	public InvalidOptionsException(String message) {
		super(message);
	}
	
	public InvalidOptionsException(Throwable t) {
		super(t);
	}

	public InvalidOptionsException(String message, Throwable t) {
		super(message, t);
	}
	
}
	