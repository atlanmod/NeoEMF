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
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * A test-case about {@link PersistenceFactory}.
 */
@ParametersAreNonnullByDefault
class PersistenceFactoryTest extends AbstractTest {

    private static EClass eClass;

    @BeforeAll
    static void initMocks() {
        EFactory eFactory = mock(EFactory.class);
        when(eFactory.create(any(EClass.class))).thenAnswer((Answer<PersistentEObject>) i -> {
            PersistentEObject result = new DefaultPersistentEObject();
            result.eSetClass(i.getArgument(0));
            return result;
        });

        EPackage ePackage = mock(EPackage.class);
        when(ePackage.getEFactoryInstance()).thenReturn(eFactory);

        eClass = mock(EClass.class);
        when(eClass.getEPackage()).thenReturn(ePackage);
        when(eClass.getName()).thenReturn("MyClass");
    }

    @Test
    void createWithClassOnly() {
        PersistentEObject object = PersistenceFactory.getInstance().create(eClass);

        assertThat(object.eClass()).isEqualTo(eClass);
    }

    @Test
    void createWithClassAndId() {
        Id id = Id.getProvider().fromLong(17L);

        PersistentEObject object = PersistenceFactory.getInstance().create(eClass, id);

        assertThat(object.eClass()).isEqualTo(eClass);
        assertThat(object.id()).isEqualTo(id);
    }
}