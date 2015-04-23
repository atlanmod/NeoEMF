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
package fr.inria.atlanmod.neoemf.resources.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.NeoURI;

public class PersistentResourceFactoryImpl implements PersistentResourceFactory {

	@Override
	public Resource createResource(URI uri) {
		// TODO handle it in a properties file
		if (StringUtils.equals(NeoURI.KYANOS_SCHEME, uri.scheme())) {
			return new PersistentResourceImpl(uri);
		} else if (StringUtils.equals(NeoURI.KYANOS_MAP_SCHEME, uri.scheme())) {
			return new PersistentResourceImpl(uri);
		} else {
			return null;
		}
	}

}
