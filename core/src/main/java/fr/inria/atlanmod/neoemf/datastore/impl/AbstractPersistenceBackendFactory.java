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

package fr.inria.atlanmod.neoemf.datastore.impl;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.EStructuralFeatureCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.IsSetCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.LoadedObjectCounterLoggingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.LoggingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.impl.SizeCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

import java.util.List;
import java.util.Map;

public abstract class AbstractPersistenceBackendFactory implements PersistenceBackendFactory {

	@Override
	public SearcheableResourceEStore createPersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?,?> options) throws InvalidDataStoreException {
	    SearcheableResourceEStore eStore = internalCreatePersistentEStore(resource, backend, options);
	    @SuppressWarnings("unchecked")
		List<PersistentResourceOptions.StoreOption> storeOptions = (List<PersistentResourceOptions.StoreOption>)options.get(PersistentResourceOptions.STORE_OPTIONS);
	    if(storeOptions != null && !storeOptions.isEmpty()) {
	        if(storeOptions.contains(PersistentResourceOptions.EStoreOption.IS_SET_CACHING)) {
	            eStore = new IsSetCachingDelegatedEStoreImpl(eStore);
	        }
	        if(storeOptions.contains(PersistentResourceOptions.EStoreOption.ESTRUCUTRALFEATURE_CACHING)) {
	            eStore = new EStructuralFeatureCachingDelegatedEStoreImpl(eStore);
	        }
	        if(storeOptions.contains(PersistentResourceOptions.EStoreOption.SIZE_CACHING)) {
	            eStore = new SizeCachingDelegatedEStoreImpl(eStore);
	        }
	        if(storeOptions.contains(PersistentResourceOptions.EStoreOption.LOGGING)) {
	            eStore = new LoggingDelegatedEStoreImpl(eStore);
	        }
	        if(storeOptions.contains(PersistentResourceOptions.EStoreOption.LOADED_OBJECT_COUNTER_LOGGING)) {
	            eStore = new LoadedObjectCounterLoggingDelegatedEStoreImpl(eStore);
	        }
	    }
	    return eStore;
	}

	protected abstract SearcheableResourceEStore internalCreatePersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?,?> options) throws InvalidDataStoreException;
}
