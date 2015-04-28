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


import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.datastores.estores.impl.EStructuralFeatureCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastores.estores.impl.IsSetCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.datastores.estores.impl.LoggingDelegatedResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.datastores.estores.impl.SizeCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

public abstract class AbstractPersistenceBackendFactory {
	
	public abstract PersistenceBackend createTransientBackend();
	
	public abstract SearcheableResourceEStore createTransientEStore(PersistentResource resource, PersistenceBackend backend);
	
	public abstract PersistenceBackend createPersistentBackend(File file, Map<?,?> options);
	
	public SearcheableResourceEStore createPersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?,?> options) throws InvalidDataStoreException {
	    SearcheableResourceEStore eStore = this.internalCreatePersistentEStore(resource, backend, options);
	    @SuppressWarnings("unchecked")
        ArrayList<PersistentResourceOptions.StoreOption> storeOptions = (ArrayList<PersistentResourceOptions.StoreOption>)options.get(PersistentResourceOptions.STORE_OPTIONS);
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
	            eStore = new LoggingDelegatedResourceEStoreImpl(eStore);
	        }
	    }
	    return eStore;
	}
	
	protected abstract SearcheableResourceEStore internalCreatePersistentEStore(PersistentResource resource, PersistenceBackend backend, Map<?,?> options) throws InvalidDataStoreException;
	
	public abstract void copyBackend(PersistenceBackend from, PersistenceBackend to);
	
	
}
