package fr.inria.atlanmod.neoemf.datastore;


import java.io.File;
import java.util.Map;

import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;

public abstract class AbstractPersistenceBackendFactory {
	
	public abstract PersistenceBackend createTransientBackend();
	public abstract SearcheableResourceEStore createTransientEStore(PersistentResource resource, PersistenceBackend backend);
	
	public abstract PersistenceBackend createPersistentBackend(File file, Map<?,?> options);
	public abstract SearcheableResourceEStore createPersistentEStore(PersistentResource resource, PersistenceBackend backend);
	
	public abstract void copyBackend(PersistenceBackend from, PersistenceBackend to);
	
	
}
