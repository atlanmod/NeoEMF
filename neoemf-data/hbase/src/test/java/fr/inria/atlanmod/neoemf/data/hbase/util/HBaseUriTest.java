/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.hbase.util;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.HBasePersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.util.AllUriTest;

import org.eclipse.emf.common.util.URI;

import java.io.File;

public class HBaseUriTest extends AllUriTest {

    @Override
    protected String name() {
        return "HBase";
    }

    @Override
    protected String uriScheme() {
        return HBaseURI.SCHEME;
    }

    @Override
    protected PersistenceBackendFactory persistenceBackendFactory() {
        return HBasePersistenceBackendFactory.getInstance();
    }

    @Override
    protected URI createURI(URI uri) {
        return HBaseURI.createURI(uri);
    }

    @Override
    protected URI createFileURI(File file) {
        return HBaseURI.createFileURI(file);
    }
}
