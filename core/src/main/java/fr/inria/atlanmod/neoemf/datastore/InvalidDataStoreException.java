/*******************************************************************************
 * Copyright (c) 2014 Abel G�mez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel G�mez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.neoemf.datastore;

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
	
