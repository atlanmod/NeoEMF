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
package fr.inria.atlanmod.kyanos.core.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.kyanos.core.KyanosResourceFactory;
import fr.inria.atlanmod.kyanos.util.KyanosURI;

public class KyanosResourceFactoryImpl implements KyanosResourceFactory {

	@Override
	public Resource createResource(URI uri) {
		if (StringUtils.equals(KyanosURI.KYANOS_SCHEME, uri.scheme())) {
			return new KyanosResourceImpl(uri);
		} else if (StringUtils.equals(KyanosURI.KYANOS_MAP_SCHEME, uri.scheme())) {
			return new KyanosMapResourceImpl(uri);
		} else {
			return null;
		}
	}

}
