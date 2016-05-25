/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.datastore;

import java.util.HashMap;
import java.util.Map;

public class PersistenceBackendFactoryRegistry {

	private PersistenceBackendFactoryRegistry() {}
	
	private static final Map<String,AbstractPersistenceBackendFactory> FACTORIES = new HashMap<>();
	
	public static Map<String,AbstractPersistenceBackendFactory> getFactories() {
		return FACTORIES;
	}
	
	public static AbstractPersistenceBackendFactory getFactoryProvider(String factoryId) {
		AbstractPersistenceBackendFactory factory = FACTORIES.get(factoryId);
		if(factory == null) {
			throw new IllegalArgumentException("Can not find a factory for the given type " + factoryId);
		}
		return factory;
	}
	
}
