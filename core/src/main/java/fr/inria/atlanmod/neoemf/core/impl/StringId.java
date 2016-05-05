/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p>
 * Contributors:
 * Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.core.impl;

import fr.inria.atlanmod.neoemf.core.Id;

public class StringId implements Id {

	private static final long serialVersionUID = 1L;
	
	String literalId;
	
	public StringId(String literalId) {
		this.literalId = literalId;
	}
	
	@Override
	public int compareTo(Id o) {
		return o.toString().compareTo(this.toString());
	}
	
	@Override
	public String toString() {
		return literalId;
	}

	@Override
	public long toLong() {
		throw new UnsupportedOperationException("Can not create a Long ID");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		else if(obj == null) {
			return false;
		}
		else if(obj.getClass() != this.getClass()) {
			return false;
		}
		else {
			return ((Id)obj).toString().equals(literalId);
		}
		
	}
	
	@Override
	public int hashCode() {
	    return literalId.hashCode();
	}

}
