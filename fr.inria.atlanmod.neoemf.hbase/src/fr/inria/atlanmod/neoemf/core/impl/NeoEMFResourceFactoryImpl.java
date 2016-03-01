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
package fr.inria.atlanmod.neoemf.core.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neoemf.core.NeoEMFResourceFactory;
import fr.inria.atlanmod.neoemf.util.NeoEMFURI;

public class NeoEMFResourceFactoryImpl implements NeoEMFResourceFactory {

	@Override
	public Resource createResource(URI uri) {
		if (StringUtils.equals(NeoEMFURI.NEOEMF_HBASE_SCHEME, uri.scheme())) {
			return new NeoEMFHbaseResourceImpl(uri);
		} else {
			return null;
		}
	}

}
