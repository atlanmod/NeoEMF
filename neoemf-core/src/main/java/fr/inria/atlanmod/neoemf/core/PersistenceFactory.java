/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Guards.checkNotNull;

/**
 * The factory that creates {@link PersistentEObject} instances from {@link org.eclipse.emf.ecore.EClass}es.
 */
@ParametersAreNonnullByDefault
public class PersistenceFactory extends EFactoryImpl implements EFactory {

    /**
     * Constructs a new {@code PersistenceFactory}.
     */
    public PersistenceFactory() {
    }

    /**
     * Constructs a new {@code PersistenceFactory} for the given {@code ePackage}.
     *
     * @param ePackage the package associated to this factory
     */
    public PersistenceFactory(EPackage ePackage) {
        this.ePackage = ePackage;
    }

    /**
     * Creates a new instance of the {@code eClass}.
     *
     * @param eClass the class to instantiate
     *
     * @return a new instance of the class
     *
     * @see EcoreUtil#create(EClass)
     */
    @Nonnull
    public static PersistentEObject newInstance(EClass eClass) {
        return newInstance(eClass, Id.UNDEFINED);
    }

    /**
     * Creates a new instance of the {@code eClass}.
     *
     * @param eClass the class to instantiate
     * @param id     the identifier of the new object
     *
     * @return a new instance of the class
     *
     * @see EcoreUtil#create(EClass)
     */
    @Nonnull
    public static PersistentEObject newInstance(EClass eClass, Id id) {
        updateIfDynamic(eClass.getEPackage());

        final EObject base = EcoreUtil.create(eClass);

        final PersistentEObject object = PersistentEObject.from(base);
        object.id(id);

        return object;
    }

    /**
     * Updates the {@link EFactory} of the specified {@code ePackage} if it's a {@link EPackageImpl}.
     *
     * @param ePackage the package to update
     *
     * @return {@code true} if the package has been updated
     */
    public static boolean updateIfDynamic(EPackage ePackage) {
        checkNotNull(ePackage, "ePackage");

        boolean updated = false;

        if (ePackage.getClass() == EPackageImpl.class && !(ePackage.getEFactoryInstance() instanceof PersistenceFactory)) {
            ePackage.setEFactoryInstance(new PersistenceFactory(ePackage));
            updated = true;
        }

        return updated;
    }

    @Override
    protected PersistentEObject basicCreate(EClass eClass) {
        return Objects.equals(eClass.getInstanceClassName(), java.util.Map.Entry.class.getName())
                ? new DynamicPersistentEObject.MapEntry<String, String>(eClass)
                : new DynamicPersistentEObject(eClass);
    }
}
