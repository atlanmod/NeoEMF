package fr.inria.atlanmod.kyanos.datastore.exceptions;

import java.io.IOException;

public class InvalidDataStoreException extends IOException {

	private static final long serialVersionUID = 1L;

	public InvalidDataStoreException() {
	}
	
	public InvalidDataStoreException(String message) {
		super(message);
	}
	
	public InvalidDataStoreException(Throwable t) {
		super(t);
	}

	public InvalidDataStoreException(String message, Throwable t) {
		super(message, t);
	}
	
}
	