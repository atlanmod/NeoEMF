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

package fr.inria.atlanmod.neoemf.core;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

/**
 * A factory able to create a {@link PersistentEObject} from an {@link EClass}.
 */
public class PersistenceFactory extends EFactoryImpl implements EFactory {

    private PersistenceFactory() {
    }

    public static PersistenceFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public PersistentEObject create(EClass eClass) {
        DefaultPersistentEObject object = new DefaultPersistentEObject();
        object.eSetClass(eClass);
        return object;
    }

    private static class Holder {

        private static final PersistenceFactory INSTANCE = new PersistenceFactory();
    }
}
