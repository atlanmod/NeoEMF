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
