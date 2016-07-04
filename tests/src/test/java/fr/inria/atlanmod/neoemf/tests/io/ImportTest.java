/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.tests.io;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.io.input.BlueprintsPersistenceHandlerFactory;
import fr.inria.atlanmod.neoemf.graph.blueprints.neo4j.resources.BlueprintsNeo4jResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoBlueprintsURI;
import fr.inria.atlanmod.neoemf.io.AllInputTest;
import fr.inria.atlanmod.neoemf.io.IOFactory;
import fr.inria.atlanmod.neoemf.io.PersistenceHandler;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 *
 */
public class ImportTest extends AllInputTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private File neo4jFile;

    private HashSet<Object> testedObjects;
    private HashSet<EStructuralFeature> testedFeatures;

    @Before
    public void setUp() throws Exception {
        String timestamp = String.valueOf(new Date().getTime());
        neo4jFile = temporaryFolder.getRoot().toPath().resolve("import-Neo4j" + timestamp).toFile();

        testedObjects = new HashSet<>();
        testedFeatures = new HashSet<>();
    }

    @Test
    public void testImportWithoutIdNeo4j() throws Exception {
        URL url = ImportTest.class.getResource("/xmi/sampleStd.xmi");

        EObject emfObject = readWithEMF(url.openStream());
        EObject neoObject = readWithNeo(url.openStream());

        assertEqualEObject(neoObject, emfObject);
    }

    @Test
    @Ignore
    // FIXME Inverse references don't exist in EMF... It's a problem, or not ?
    public void testImportWithIdNeo4j() throws Exception {
        URL url = ImportTest.class.getResource("/xmi/sampleWithId.xmi");

        EObject emfObject = readWithEMF(url.openStream());
        EObject neoObject = readWithNeo(url.openStream());

        assertEqualEObject(neoObject, emfObject);
    }

    @Test
    public void testImportSet1Neo4j() throws Exception {
        URL url = ImportTest.class.getResource("/xmi/bench/fr.inria.atlanmod.kyanos.tests.xmi");

        EObject emfObject = readWithEMF(url.openStream());
        EObject neoObject = readWithNeo(url.openStream());

        assertEqualEObject(neoObject, emfObject);
    }

    @Test
    public void testImportSet2Neo4j() throws Exception {
        URL url = ImportTest.class.getResource("/xmi/bench/fr.inria.atlanmod.neo4emf.neo4jresolver.xmi");

        EObject emfObject = readWithEMF(url.openStream());
        EObject neoObject = readWithNeo(url.openStream());

        assertEqualEObject(neoObject, emfObject);
    }

    @Test
    public void testImportSet3Neo4j() throws Exception {
        URL url = ImportTest.class.getResource("/xmi/bench/fr.inria.atlanmod.kyanos.tests.xmi");

        EObject emfObject = readWithEMF(url.openStream());
        EObject neoObject = readWithNeo(url.openStream());

        assertEqualEObject(neoObject, emfObject);
    }

    private EObject readWithEMF(InputStream stream) throws Exception {
        registerEPackageFromEcore("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        registerEPackageFromEcore("uml", "http://schema.omg.org/spec/UML/2.1");

        Resource resource = new XMIResourceImpl();
        resource.load(stream, Collections.emptyMap());
        return resource.getContents().get(0);
    }

    private EObject readWithNeo(InputStream stream) throws Exception {
        registerEPackageFromEcore("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        registerEPackageFromEcore("uml", "http://schema.omg.org/spec/UML/2.1");

        BlueprintsPersistenceBackend persistenceBackend = createNeo4jPersistenceBackend();
        PersistenceHandler persistenceHandler = BlueprintsPersistenceHandlerFactory.createPersistenceHandler(persistenceBackend, false);

        IOFactory.importXmi(stream, persistenceHandler);

        persistenceBackend.stop();

        return load();
    }

    private EObject load() throws IOException {
        PersistenceBackendFactoryRegistry.register(
                NeoBlueprintsURI.NEO_GRAPH_SCHEME,
                BlueprintsPersistenceBackendFactory.getInstance());

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(
                NeoBlueprintsURI.NEO_GRAPH_SCHEME,
                PersistentResourceFactory.eINSTANCE);

        Resource resource = resourceSet.createResource(NeoBlueprintsURI.createNeoGraphURI(neo4jFile));
        resource.load(Collections.emptyMap());

        return resource.getContents().get(0);
    }

    private BlueprintsPersistenceBackend createNeo4jPersistenceBackend() throws InvalidDataStoreException {
        Map<String, Object> options = new HashMap<>();

        List<PersistentResourceOptions.StoreOption> storeOptions = new ArrayList<>();
        storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.DIRECT_WRITE);

        options.put(
                BlueprintsResourceOptions.OPTIONS_BLUEPRINTS_GRAPH_TYPE,
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_TYPE_NEO4J);

        options.put(
                PersistentResourceOptions.STORE_OPTIONS,
                storeOptions);

        options.put(
                BlueprintsNeo4jResourceOptions.OPTIONS_BLUEPRINTS_NEO4J_CACHE_TYPE,
                BlueprintsNeo4jResourceOptions.CacheType.NONE);

        return (BlueprintsPersistenceBackend) BlueprintsPersistenceBackendFactory.getInstance().createPersistentBackend(neo4jFile, options);
    }

    private void assertEqualEObject(EObject actual, EObject expected) {
        if (!testedObjects.contains(expected)) {
            testedObjects.add(expected);

            assertThat(actual.eClass().getName(), equalTo(expected.eClass().getName()));
            assertThat(actual.eContents().size(), is(expected.eContents().size()));

            for (EAttribute eAttribute : expected.eClass().getEAttributes()) {
                assertEqualFeature(actual, expected, eAttribute.getFeatureID());
            }

            for (EReference eReference : expected.eClass().getEReferences()) {
                assertEqualFeature(actual, expected, eReference.getFeatureID());
            }

            for (int i = 0; i < expected.eContents().size(); i++) {
                assertEqualEObject(actual.eContents().get(i), expected.eContents().get(i));
            }
        }
    }

    private void assertEqualFeature(EObject actual, EObject expected, int featureId) {
        EStructuralFeature eStructuralFeature = expected.eClass().getEStructuralFeature(featureId);

        if (!testedFeatures.contains(eStructuralFeature)) {
            testedFeatures.add(eStructuralFeature);

            Object expectedValue = expected.eGet(eStructuralFeature);
            Object actualValue = actual.eGet(actual.eClass().getEStructuralFeature(featureId));

            if (expectedValue instanceof EObject) {
                assertEqualEObject((EObject) actualValue, (EObject) expectedValue);
            }
            else if (expectedValue instanceof List<?>) {
                List<?> expectedList = (List<?>) expectedValue;
                List<?> actualList = (List<?>) actualValue;

                assertThat(actualList.size(), is(expectedList.size()));

                for (int i = 0; i < expectedList.size(); i++) {
                    assertEqualEObject((EObject) actualList.get(i), (EObject) expectedList.get(i));
                }
            }
            else {
                assertThat(actualValue, equalTo(expectedValue));
            }
        }
    }
}
