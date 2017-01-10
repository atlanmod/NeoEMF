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

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.AbstractActivator;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseURI;

public class Activator extends AbstractActivator {

    @Override
    protected String name() {
        return "HBase";
    }

    @Override
    protected String scheme() {
        return HBaseURI.SCHEME;
    }

    @Override
    protected PersistenceBackendFactory factory() {
        return HBasePersistenceBackendFactory.getInstance();
    }
}
