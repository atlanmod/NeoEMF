/*******************************************************************************
 * Copyright (c) 2014 Abel Gómez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gómez - initial API and implementation
 ******************************************************************************/
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
	
