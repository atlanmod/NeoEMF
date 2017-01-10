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

import com.sleepycat.je.EnvironmentConfig;

import fr.inria.atlanmod.neoemf.AbstractTest;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BerkeleyDbPersistenceBackendTest extends AbstractTest {

    private static BerkeleyDbPersistenceBackend backend;

    public BerkeleyDbPersistenceBackendTest() {}

    @BeforeClass
    public static void initialize() throws IOException {
        File file = workspace.newFile("BerkeleyDB");
        file = Files.createDirectory(file.toPath()).toFile();
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        backend = new BerkeleyDbPersistenceBackend(file, envConfig);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        // Temp directories are automatically cleaned
//        Files.walk(new File(pathname).toPath(), FileVisitOption.FOLLOW_LINKS)
//                .sorted(Comparator.reverseOrder())
//                .map(Path::toFile)
//                .peek(System.out::println)
//                .forEach(File::delete);
    }

    @Before
    public void before() {
        backend.open();
    }

    @After
    public void after() {
        backend.close();
    }

    @Test
    public void testStoreFeature() {
        final int TIMES = 1000;

        IntStream.range(0, TIMES).forEach(i -> {
            FeatureKey key = FeatureKey.of(new StringId("object"+i), "name"+i);
            assertThat(backend.storeValue(key, "value"+i)).isNotNull();
        });

//        backend.save();
        backend.close();
        backend.open();

        IntStream.range(0, TIMES).forEach(i -> assertThat(backend.valueOf(FeatureKey.of(new StringId("object" + i), "name" + i))).isEqualTo("value" + i));
    }

    @Test
    public void testStoreMultivaluedFeature() {
        final int TIMES = 10;

        MultivaluedFeatureKey[] keys = new MultivaluedFeatureKey[TIMES];
        FeatureKey featureKey = FeatureKey.of(new StringId("object"), "name");

        IntStream.range(0, TIMES).forEach(i -> {
            keys[i] = featureKey.withPosition(i);
            backend.storeValueAtIndex(keys[i], i);
        });

        IntStream.range(0, TIMES).forEach(i -> assertThat(i).isEqualTo(backend.valueAtIndex(keys[i])));
    }

    @Test
    public void testIsFeatureSet() {
        FeatureKey fk1 = FeatureKey.of(new StringId("objectId"), "isSet");
        assertThat(backend.isFeatureSet(fk1)).isFalse();

        backend.storeValue(fk1, "yes");
        assertThat(backend.isFeatureSet(fk1)).isTrue();
    }

    @Test
    public void testRemoveFeature() {
        FeatureKey fk1 = FeatureKey.of(new StringId("objectId"), "unSet");
        assertThat(backend.isFeatureSet(fk1)).isFalse();

        backend.storeValue(fk1, "yes");
        assertThat(backend.isFeatureSet(fk1)).isTrue();

        backend.removeFeature(fk1);
        assertThat(backend.isFeatureSet(fk1)).isFalse();
    }

    @Test
    public void testStoreContainer() {
        Id id1 = StringId.generate();
        Id id2 = StringId.generate();

        PersistentEObject po = mock(PersistentEObject.class);
        when(po.id()).thenReturn(id2);
        EReference eref = mock(EReference.class);
        when(eref.getName()).thenReturn("ref-name");

        ContainerInfo ci = ContainerInfo.from(po, eref);
        backend.storeContainer(id1, ci);

        ContainerInfo containerInfo = backend.containerFor(id1);

        assertThat(containerInfo.id()).isEqualTo(id2);
        assertThat(containerInfo.name()).isEqualTo("ref-name");
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

        backend.storeMetaclass(id2, ClassInfo.from(po));

        ClassInfo classInfo = backend.metaclassFor(id2);
        assertThat(classInfo).isNotNull();
        assertThat(classInfo.name()).isEqualTo("eClassTest");
        assertThat(classInfo.uri()).isEqualTo("URI://my.uri/");
    }
}

