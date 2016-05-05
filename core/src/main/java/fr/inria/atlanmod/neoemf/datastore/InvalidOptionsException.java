/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/

package fr.inria.atlanmod.neoemf.datastore;


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
	
