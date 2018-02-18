/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.log.Log;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The factory that creates {@link PersistentEObject} instances from {@link org.eclipse.emf.ecore.EClass}es.
 */
@Singleton
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

    @Nonnull
    @Override
    public PersistentEObject create(EClass eClass) {
        return create(eClass, Id.UNDEFINED);
    }

    /**
     * Creates a new instance of the class and returns it.
     *
     * @param eClass the class of the new instance
     * @param id     the identifier of the new instance
     *
     * @return a new instance of the class
     */
    @Nonnull
    public PersistentEObject create(EClass eClass, Id id) {
        PersistentEObject newObject;

        if (eClass.getEPackage().getClass() == EPackageImpl.class) {
            newObject = new DefaultPersistentEObject(id);
        }
        else {
            newObject = PersistentEObject.from(newInstance(eClass));
            newObject.id(id);
        }

        if (!Objects.equals(newObject.eClass(), eClass)) {
            newObject.eSetClass(eClass);
        }

        return newObject;
    }

    /**
     * Creates a new instance of the {@code eClass}.
     *
     * @param eClass the class to instantiate
     *
     * @return a new instance of the class
     */
    @Nonnull
    private EObject newInstance(EClass eClass) {
        final EFactory factory = eClass.getEPackage().getEFactoryInstance();
        checkFactory(factory);

        return factory.create(eClass);
    }

    /**
     * Ensures that the factory is the real instance to avoid creating a {@link org.eclipse.emf.ecore.impl.DynamicEObjectImpl} instance.
     *
     * @param eFactory the factory to test
     */
    private void checkFactory(EFactory eFactory) {
        // EFactoryImpl can creates DynamicEObjectImpl instances
        if (eFactory.getClass() == EFactoryImpl.class) {
            Log.warn("Creating a new instance using {0}; This could lead to a dynamic instance that is not supported", eFactory);
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final PersistenceFactory INSTANCE = new PersistenceFactory();
    }
}
