/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb;

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
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BerkeleyDBPersistenceBackendTest extends AbstractTest {

    private static BerkeleyDBPersistenceBackend backend;
    private static String pathname = "/tmp/test/";


    public BerkeleyDBPersistenceBackendTest() {}

    @BeforeClass
    public static void initialize() throws IOException {
        File file = new File(pathname);
        file.mkdir();
        backend = new BerkeleyDBPersistenceBackend(file);
        backend.open();
    }

    @AfterClass
    public static void tearDown() throws IOException {
        backend.close();

        Files.walk(new File(pathname).toPath(), FileVisitOption.FOLLOW_LINKS)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .peek(System.out::println)
                .forEach(File::delete);
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

        for (int i = 0; i < 1000; i++) {
            FeatureKey key = FeatureKey.of(new StringId("object"+i), "name"+i);
            assertThat(backend.storeValue(key, "value"+i)).isNotNull();
        }

        //backend.save();
        backend.close();backend.open();

        for (int i = 0; i < 1000; i++) {
            assertThat(backend.valueOf(FeatureKey.of(new StringId("object" + i), "name" + i))).isEqualTo("value" + i);
        }

    }

    @Test
    public void testStoreMultivaluedFeature() {
        final int TIMES = 10;

        MultivaluedFeatureKey[] keys = new MultivaluedFeatureKey[TIMES];
        FeatureKey featureKey = FeatureKey.of(new StringId("object"), "name");

        for (int i = 0; i < 10; i++) {
            keys[i] = featureKey.withPosition(i);
            backend.storeValueAtIndex(keys[i], i);
        }

        for (int i = 0; i < TIMES; i++) {
            assertThat(i).isEqualTo(backend.valueAtIndex(keys[i]));
        }
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

        ContainerInfo result = backend.containerFor(id1);

        assertThat(result.id()).isEqualTo(id2);
        assertThat(result.name()).isEqualTo("ref-name");
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

        ClassInfo ci = ClassInfo.from(po);
        backend.storeMetaclass(id2, ci);

        ClassInfo result = backend.metaclassFor(id2);
        assertThat(result).isNotNull();
        assertThat(result.name()).isEqualTo("eClassTest");
        assertThat(result.uri()).isEqualTo("URI://my.uri/");

    }

}

