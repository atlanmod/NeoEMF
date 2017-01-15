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

import javax.annotation.Nonnull;

/**
 * The factory that creates {@link PersistentEObject}s from {@link EClass}es.
 */
public class PersistenceFactory extends EFactoryImpl implements EFactory {

    /**
     * Constructs a new {@code PersistenceFactory}.
     */
    private PersistenceFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static PersistenceFactory getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public PersistentEObject create(EClass eClass) {
        DefaultPersistentEObject object = new DefaultPersistentEObject();
        object.eSetClass(eClass);
        return object;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static class Holder {

        /**
         * The instance of the outer class.
         */
        private static final PersistenceFactory INSTANCE = new PersistenceFactory();
    }
}
