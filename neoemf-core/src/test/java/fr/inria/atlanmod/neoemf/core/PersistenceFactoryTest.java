/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.AbstractTest;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link PersistenceFactory}.
 */
@ParametersAreNonnullByDefault
class PersistenceFactoryTest extends AbstractTest {

    private static EClass eClass;

    @BeforeAll
    static void initMocks() {
        EcoreFactory coreFactory = EcoreFactory.eINSTANCE;

        EPackage ePackage = coreFactory.createEPackage();
        ePackage.setName("MyPackage");
        ePackage.setNsPrefix("my");
        ePackage.setNsURI("http://example.org/my");

        eClass = coreFactory.createEClass();
        eClass.setName("MyClass");

        ePackage.getEClassifiers().add(eClass);

        assertThat(eClass.getEPackage()).isEqualTo(ePackage);

        PersistenceFactory.updateIfDynamic(ePackage);
    }

    @Test
    void createWithClassOnly() {
        PersistentEObject object = PersistenceFactory.newInstance(eClass, Id.UNDEFINED);

        assertThat(object.eClass()).isEqualTo(eClass);
    }

    @Test
    void createWithClassAndId() {
        Id id = Id.getProvider().fromLong(17L);

        PersistentEObject object = PersistenceFactory.newInstance(eClass, id);

        assertThat(object.eClass()).isEqualTo(eClass);
        assertThat(object.id()).isEqualTo(id);
    }

    @Test
    void createStandard() {
        EObject object = EcoreUtil.create(eClass);

        assertThat(object).isInstanceOf(PersistentEObject.class);
        assertThat(object.eClass()).isEqualTo(eClass);
    }
}