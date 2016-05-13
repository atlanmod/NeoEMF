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

package fr.inria.atlanmod.neoemf.map.util;

import fr.inria.atlanmod.neoemf.util.NeoURI;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.text.MessageFormat;

public class NeoMapURI extends NeoURI {

	public static final String NEO_MAP_SCHEME = "neo-map";
	
	protected NeoMapURI(int hashCode, URI internalURI) {
		super(hashCode,internalURI);
	}
	
	public static URI createNeoMapURI(URI uri) {
		URI returnValue;
		if(NeoURI.FILE_SCHEME.equals(uri.scheme())) {
			returnValue = createNeoMapURI(FileUtils.getFile(uri.toFileString()));
		}
		else if(NEO_MAP_SCHEME.equals(uri.scheme())) {
			returnValue = NeoURI.createNeoURI(uri);
		}
		else {
			throw new IllegalArgumentException(MessageFormat.format("Can not create NeoMapURI from the URI scheme {0}",uri.scheme()));
		}
		return returnValue;
	}
	
	public static URI createNeoMapURI(File file) {
		return NeoURI.createNeoURI(file, NEO_MAP_SCHEME);
	}
	
}
