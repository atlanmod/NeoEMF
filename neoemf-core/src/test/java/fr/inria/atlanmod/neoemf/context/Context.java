/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;

import java.io.File;
import java.io.IOException;

public interface Context {

    /**
     * Returns the name of this {@code Context}.
     */
    String name();

    String uriScheme();

    URI createURI(URI uri);

    URI createFileURI(File file);

    PersistentResource createPersistentResource(EPackage ePackage, File file) throws IOException;

    PersistentResource createTransientResource(EPackage ePackage, File file) throws IOException;

    PersistenceBackendFactory persistenceBackendFactory();

    Class<? extends DirectWriteStore> directWriteClass();
}
