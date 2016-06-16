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
package fr.inria.atlanmod.neoemf.hbase.datastore;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;

/**
 * Dummy backend implementation for HBase to fit core architecture
 * The real access to the HBase Table is done in @see{DirectWriteHbaseResourceEStoreImpl}
 * and @see{ReadOnlyHbaseResourceEStoreImpl}
 */
public class HBasePersistenceBackend implements PersistenceBackend {

    @Override
    public void start(Map<?, ?> options) throws InvalidDataStoreException {
        
    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public void stop() {
        
    }

    @Override
    public void save() {
        
    }

    @Override
    public Object getAllInstances(EClass eClass, boolean strict)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

}
