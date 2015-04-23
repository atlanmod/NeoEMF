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

import java.util.HashMap;
import java.util.Map;

public class PersistenceBackendFactoryProvider {
	
	private static Map<String,AbstractPersistenceBackendFactory> factories = new HashMap<String,AbstractPersistenceBackendFactory>();
	
	public static void init() {
		// read from properties file and initialize the mapping
	}
	
	public static Map<String,AbstractPersistenceBackendFactory> getFactories() {
		return factories;
	}
	
	public static AbstractPersistenceBackendFactory getProvider(String kind) {
		if(factories.isEmpty()) {
			init();
		}
		AbstractPersistenceBackendFactory factory = factories.get(kind);
		if(factory == null) {
			throw new RuntimeException("Can not find a factory for the given type " + kind);
		}
		return factory;
	}
	
}
