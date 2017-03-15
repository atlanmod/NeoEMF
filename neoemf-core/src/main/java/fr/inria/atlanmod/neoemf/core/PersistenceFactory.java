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
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The factory that creates {@link PersistentEObject} instances from {@link EClass}es.
 */
@ParametersAreNonnullByDefault
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
        return create(eClass, StringId.generate());
    }

    /**
     * Creates a new instance of the class and returns it.
     *
     * @param eClass the class of the new instance
     * @param id     the identifier of the new instance
     *
     * @return a new instance of the class
     */
    public PersistentEObject create(EClass eClass, Id id) {
        PersistentEObject newObject;

        if (Objects.equals(eClass.getEPackage().getClass(), EPackageImpl.class)) {
            newObject = new DefaultPersistentEObject(id);
            newObject.eSetClass(eClass);
        }
        else {
            newObject = PersistentEObject.from(EcoreUtil.create(eClass));
            newObject.id(id);
        }

        return newObject;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final PersistenceFactory INSTANCE = new PersistenceFactory();
    }
}
