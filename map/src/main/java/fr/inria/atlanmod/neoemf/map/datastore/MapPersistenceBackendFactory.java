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
package fr.inria.atlanmod.neoemf.map.datastore;

import java.io.File;
import java.util.Map;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Engine;

import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.datastores.estores.impl.IsSetCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastores.estores.impl.SizeCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.map.datastore.estores.impl.DirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;

public class MapPersistenceBackendFactory extends
		AbstractPersistenceBackendFactory {

	@Override
	public PersistenceBackend createTransientBackend() {
		Engine mapEngine = DBMaker.newMemoryDB().closeOnJvmShutdown().makeEngine();
		return new MapPersistenceBackend(mapEngine);
	}

	@Override
	public SearcheableResourceEStore createTransientEStore(
			PersistentResource resource, PersistenceBackend backend) {
		assert backend instanceof DB : "Trying to create a Map-based EStore with an invalid backend";
		return new DirectWriteMapResourceEStoreImpl(resource, (DB)backend);
	}

	@Override
	public PersistenceBackend createPersistentBackend(File file,
			Map<?, ?> options) {
		Engine mapEngine = DBMaker.newFileDB(file).cacheLRUEnable().closeOnJvmShutdown().mmapFileEnableIfSupported().asyncWriteEnable().makeEngine();
		return new MapPersistenceBackend(mapEngine);
	}

	@Override
	public SearcheableResourceEStore createPersistentEStore(
			PersistentResource resource, PersistenceBackend backend) {
		assert backend instanceof DB : "Trying to create a Map-based EStore with an invalid backend";
		return new IsSetCachingDelegatedEStoreImpl(new SizeCachingDelegatedEStoreImpl(new DirectWriteMapResourceEStoreImpl(resource, (DB)backend)));
	}

	@Override
	public void copyBackend(PersistenceBackend from, PersistenceBackend to) {
		// TODO Auto-generated method stub
	}

}
