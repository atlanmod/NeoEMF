/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.core.impl;

import fr.inria.atlanmod.neoemf.core.Id;

import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Objects;

import static java.util.Objects.isNull;

public class StringId implements Id {

	private static final long serialVersionUID = 1L;

	private final String literalId;

	public static Id generate() {
		return new StringId(EcoreUtil.generateUUID());
	}
	
	public StringId(String literalId) {
		this.literalId = literalId;
	}
	
	@Override
	public int compareTo(Id o) {
		return o.toString().compareTo(toString());
	}
	
	@Override
	public String toString() {
		return literalId;
	}

	@Override
	public long toLong() {
		throw new UnsupportedOperationException("Cannot create a Long ID");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this) {
			return true;
		}
		if(isNull(obj) || obj.getClass() != getClass()) {
			return false;
		}

		StringId other = (StringId) obj;
		return Objects.equals(literalId, other.literalId);
	}
	
	@Override
	public int hashCode() {
	    return literalId.hashCode();
	}
}
