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
