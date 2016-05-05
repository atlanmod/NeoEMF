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
package fr.inria.atlanmod.neoemf.resources.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;

public class PersistentResourceFactoryImpl implements PersistentResourceFactory {

	@Override
	public Resource createResource(URI uri) {
		if (PersistenceBackendFactoryRegistry.getFactories().containsKey(uri.scheme())) {
			return new PersistentResourceImpl(uri);
		} else {
			return null;
		}
	}

}
