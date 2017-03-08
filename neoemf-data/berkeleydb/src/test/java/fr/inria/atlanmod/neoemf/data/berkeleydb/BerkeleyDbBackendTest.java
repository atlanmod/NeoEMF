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

package fr.inria.atlanmod.neoemf.data.berkeleydb;

import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.neoemf.AbstractTest;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BerkeleyDbBackendTest extends AbstractTest {

    private static BerkeleyDbBackend backend;

    @Before
    public void initialize() throws IOException {
        File file = workspace.newFile("BerkeleyDB");
        file = Files.createDirectory(file.toPath()).toFile();

        EnvironmentConfig envConfig = new EnvironmentConfig()
                .setAllowCreate(true);

        DatabaseConfig dbConfig = new DatabaseConfig()
                .setAllowCreate(true)
                .setSortedDuplicates(false)
                .setDeferredWrite(true);

        backend = new BerkeleyDbBackendIndices(file, envConfig, dbConfig);
    }

    @After
    public void after() {
        backend.close();
    }

    @Test
    public void testStoreFeature() {
        final int TIMES = 1000;

        IntStream.range(0, TIMES).forEach(i -> {
            FeatureKey key = FeatureKey.of(StringId.of("object" + i), "name" + i);
            assertThat(backend.valueFor(key, "value" + i)).isNotPresent();
        });

        backend.save();

        IntStream.range(0, TIMES).forEach(i -> assertThat(backend.valueOf(FeatureKey.of(StringId.of("object" + i), "name" + i)).orElse(null)).isEqualTo("value" + i));
    }

    @Test
    public void testStoreMultivaluedFeature() {
        final int TIMES = 10;

        ManyFeatureKey[] keys = new ManyFeatureKey[TIMES];
        FeatureKey key = FeatureKey.of(StringId.of("object"), "name");

        IntStream.range(0, TIMES).forEach(i -> {
            keys[i] = key.withPosition(i);
            backend.addValue(keys[i], i);
        });

        IntStream.range(0, TIMES).forEach(i -> assertThat(i).isEqualTo(backend.valueOf(keys[i]).orElse(null)));
    }

    @Test
    public void testIsFeatureSet() {
        FeatureKey fk1 = FeatureKey.of(StringId.of("objectId"), "isSet");
        assertThat(backend.hasValue(fk1)).isFalse();

        backend.valueFor(fk1, "yes");
        assertThat(backend.hasValue(fk1)).isTrue();
    }

    @Test
    public void testRemoveFeature() {
        FeatureKey fk1 = FeatureKey.of(StringId.of("objectId"), "unSet");
        assertThat(backend.hasValue(fk1)).isFalse();

        backend.valueFor(fk1, "yes");
        assertThat(backend.hasValue(fk1)).isTrue();

        backend.unsetValue(fk1);
        assertThat(backend.hasValue(fk1)).isFalse();
    }

    @Test
    public void testStoreContainer() {
        Id id1 = StringId.generate();
        Id id2 = StringId.generate();

        PersistentEObject po = mock(PersistentEObject.class);
        when(po.id()).thenReturn(id2);
        EReference eref = mock(EReference.class);
        when(eref.getName()).thenReturn("ref-name");

        ContainerDescriptor originalContainer = ContainerDescriptor.from(po, eref);
        backend.containerFor(id1, originalContainer);

        ContainerDescriptor retrievedContainer = backend.containerOf(id1).orElse(null);

        assertThat(retrievedContainer.id()).isEqualTo(id2);
        assertThat(retrievedContainer.name()).isEqualTo("ref-name");
    }

    @Test
    public void testStoreMetaclass() {
        Id id1 = StringId.generate();
        Id id2 = StringId.generate();

        EPackage ePackage = mock(EPackage.class);
        when(ePackage.getNsURI()).thenReturn("URI://my.uri/");

        EClass eClass = mock(EClass.class);
        when(eClass.getName()).thenReturn("eClassTest");
        when(eClass.getEPackage()).thenReturn(ePackage);

        PersistentEObject po = mock(PersistentEObject.class);
        when(po.eClass()).thenReturn(eClass);

        backend.metaclassFor(id2, ClassDescriptor.from(po));

        ClassDescriptor metaclass = backend.metaclassOf(id2).orElse(null);
        assertThat(metaclass).isNotNull();
        assertThat(metaclass.name()).isEqualTo("eClassTest");
        assertThat(metaclass.uri()).isEqualTo("URI://my.uri/");
    }
}

