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

import org.eclipse.emf.common.util.EList;
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
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

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
    public void testCompareWithEMFNeo4j() throws Exception {
        File file = getXmiStandard();

        EObject emfObject = loadWithEMF(file);
        EObject neoObject = loadWithNeoBlueprints(file);

        assertEqualEObject(neoObject, emfObject);
    }

    /**
     * Check that the elements are properly processed.
     * <p/>
     * All elements must have an id and a class name.
     */
    @Test
    public void testElementsAndChildrenNeo4j() throws Exception {
        EObject eObject;
        EObject eObjectChild;

        EObject root = loadWithNeoBlueprints(getXmiStandard());
        assertValidElement(root, "Model", 19, "fr.inria.atlanmod.kyanos.tests");
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            eObject = getChildFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidElement(eObject, "ClassDeclaration", 7, "TestCreateResource");
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
                eObjectChild = getChildFrom(eObject, 0);
                assertValidElement(eObjectChild, "Modifier", 0, null);

                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                eObjectChild = getChildFrom(eObject, 3);
                assertValidElement(eObjectChild, "MethodDeclaration", 5, "tearDownAfterClass");
            }

            //@Model/@ownedElements.1
            eObject = getChildFrom(root, 1);
            assertValidElement(eObject, "Package", 5, "java");

            //@Model/@orphanTypes.5
            eObject = getChildFrom(root, 8);
            assertValidElement(eObject, "PrimitiveTypeVoid", 0, "void");

            //@Model/@compilationUnits.1
            eObject = getChildFrom(root, 17);
            assertValidElement(eObject, "CompilationUnit", 16, "TestXmi.java");
            {
                //@Model/@compilationUnits.1/@imports.2
                eObjectChild = getChildFrom(eObject, 2);
                assertValidElement(eObjectChild, "ImportDeclaration", 0, null);
            }
        }
    }

    /**
     * Check that the attributes are properly processed.
     */
    @Test
    public void testAttributesNeo4j() throws Exception {
        EObject eObject;

        EObject root = loadWithNeoBlueprints(getXmiStandard());
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@modifier
            eObject = getChildFrom(root, 0, 0, 0, 0, 0, 0, 0);
            assertValidAttribute(eObject, "visibility", "public");

            //@Model/@ownedElements.1
            eObject = getChildFrom(root, 1);
            assertValidAttribute(eObject, "proxy", "true");

            //@Model/@compilationUnits.1
            eObject = getChildFrom(root, 17);
            assertValidAttribute(eObject, "originalFilePath", "C:\\Eclipse\\eclipse-SDK-4.3.1-win32-x86_64-Blue\\eclipse\\workspace\\fr.inria.atlanmod.kyanos.tests\\src\\fr\\inria\\atlanmod\\kyanos\\tests\\TestXmi.java");
        }
    }

    /**
     * Check that the {@code XPath} references/id are properly processed.
     * <p/>
     * Containment and inverse reference must have been created.
     * References previously detected as attributes, are now well placed.
     */
    @Test
    public void testReferencesNeo4j() throws Exception {
        EObject eObject;
        EObject eObjectChild;

        EObject root = loadWithNeoBlueprints(getXmiStandard());
        assertValidReference(root, "ownedElements", 0, "Package", "fr", true, true);
        assertValidReference(root, "orphanTypes", 5, "PrimitiveTypeVoid", "void", true, true);
        {
            //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0
            eObject = getChildFrom(root, 0, 0, 0, 0, 0, 0);
            assertValidReference(eObject, "originalCompilationUnit", 0, "CompilationUnit", "TestCreateResource.java", false, false);
            assertValidReference(eObject, "bodyDeclarations", 0, "FieldDeclaration", null, true, true);
            {
                //@Model/@ownedElements.0/@ownedPackages[4]/@ownedElements.0/@bodyDeclarations.2
                eObjectChild = getChildFrom(eObject, 3);
                assertValidReference(eObjectChild, "originalCompilationUnit", 0, "CompilationUnit", "TestCreateResource.java", false, false);
                assertValidReference(eObjectChild, "modifier", 0, "Modifier", null, false, true);
            }

            //@Model/@ownedElements.1
            eObject = getChildFrom(root, 1);
            assertValidReference(eObject, "ownedPackages", 0, "Package", "io", true, true);

            //@Model/@orphanTypes.5
            eObject = getChildFrom(root, 8);
            assertValidReference(eObject, "usagesInTypeAccess", 0, "TypeAccess", null, true, false);
            assertValidReference(eObject, "usagesInTypeAccess", 9, "TypeAccess", null, true, false);

            //@Model/@compilationUnits.1
            eObject = getChildFrom(root, 17);
            assertValidReference(eObject, "package", 0, "Package", "tests", false, false);
            assertValidReference(eObject, "imports", 0, "ImportDeclaration", null, true, true);
            {
                //@Model/@compilationUnits.1/@imports.2
                eObjectChild = getChildFrom(eObject, 2);
                assertValidReference(eObjectChild, "originalCompilationUnit", 0, "CompilationUnit", "TestXmi.java", false, false);
                assertValidReference(eObjectChild, "importedElement", 2, "ClassDeclaration", "URI", false, false);
            }
        }
    }

    @Test
    @Ignore
    // FIXME Inverse references don't exist in EMF... It's a problem, or not ?
    public void testImportWithIdNeo4j() throws Exception {
        File file = getXmiWithId();

        EObject emfObject = loadWithEMF(file);
        EObject neoObject = loadWithNeoBlueprints(file);

        assertEqualEObject(neoObject, emfObject);
    }

    private void assertEqualEObject(EObject actual, EObject expected) {
        if (!testedObjects.contains(expected)) {
            testedObjects.add(expected);

            assertThat(actual.eClass().getName()).isEqualTo(expected.eClass().getName());
            assertThat(actual.eContents()).hasSameSizeAs(expected.eContents());

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

                assertThat(actualList).hasSameSizeAs(expectedList);

                for (int i = 0; i < expectedList.size(); i++) {
                    assertEqualEObject((EObject) actualList.get(i), (EObject) expectedList.get(i));
                }
            }
            else {
                assertThat(actualValue).isEqualTo(expectedValue);
            }
        }
    }

    private void assertValidElement(final EObject eObject, final String className, final int size, final Object name) {
        assertThat(eObject.eClass().getName()).isEqualTo(className);
        assertThat(eObject.eContents()).hasSize(size);
        if (name != null) {
            assertThat(eObject.eGet(eObject.eClass().getEStructuralFeature("name"))).isEqualTo(name);
        } else {
            try {
                eObject.eGet(eObject.eClass().getEStructuralFeature("name"));
                fail();
            } catch (NullPointerException ignore) {
                // It's good !
            }
        }
    }

    private void assertValidReference(final EObject eObject, final String name, final int index, final String referenceClassName, final String referenceName, final boolean many, final boolean containment) {
        EReference eReference = (EReference) eObject.eClass().getEStructuralFeature(name);

        Object objectReference = eObject.eGet(eReference);
        EObject eObjectReference;

        if (many) {
            @SuppressWarnings("unchecked") // Unchecked cast: 'Object' to 'EList<...>'
            EList<EObject> eObjectList = (EList<EObject>) objectReference;
            eObjectReference = eObjectList.get(index);
        } else {
            eObjectReference = (EObject) objectReference;
        }

        assertThat(eObjectReference.eClass().getName()).isEqualTo(referenceClassName);

        if (referenceName != null) {
            EAttribute eAttribute = (EAttribute) eObjectReference.eClass().getEStructuralFeature("name");
            assertThat(eObjectReference.eGet(eAttribute).toString()).isEqualTo(referenceName);
        } else {
            try {
                EAttribute eAttribute = (EAttribute) eObjectReference.eClass().getEStructuralFeature("name");
                assertThat(eObjectReference.eGet(eAttribute)).isEqualTo(eAttribute.getDefaultValue());
            } catch (NullPointerException ignore) {
                // It's good
            }
        }

        assertThat(eReference.isContainment()).isEqualTo(containment);
        assertThat(eReference.isMany()).isEqualTo(many);
    }

    private void assertValidAttribute(final EObject eObject, final String name, final Object value) {
        EAttribute eAttribute = (EAttribute) eObject.eClass().getEStructuralFeature(name);

        if (value == null) {
            assertThat(eObject.eGet(eAttribute)).isEqualTo(eAttribute.getDefaultValue());
        } else {
            assertThat(eObject.eGet(eAttribute).toString()).isEqualTo(value);
        }
    }

    private static EObject getChildFrom(EObject root, int ... indexes) {
        if (indexes.length == 0) {
            throw new IllegalArgumentException("You must define at least one index");
        }

        EObject child = root;

        for (int index : indexes) {
            child = child.eContents().get(index);
        }

        return child;
    }

    private EObject loadWithEMF(File file) throws Exception {
        registerEPackageFromEcore("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        registerEPackageFromEcore("uml", "http://schema.omg.org/spec/UML/2.1");

        Resource resource = new XMIResourceImpl();
        resource.load(new FileInputStream(file), Collections.emptyMap());
        return resource.getContents().get(0);
    }

    private void loadWithNeo(File file, PersistenceHandler persistenceHandler) throws Exception {
        registerEPackageFromEcore("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        registerEPackageFromEcore("uml", "http://schema.omg.org/spec/UML/2.1");

        IOFactory.importXmi(new FileInputStream(file), persistenceHandler);
    }

    private EObject loadWithNeoBlueprints(File file) throws Exception {
        BlueprintsPersistenceBackend persistenceBackend = createNeo4jPersistenceBackend();
        PersistenceHandler persistenceHandler = BlueprintsPersistenceHandlerFactory.createPersistenceHandler(persistenceBackend, false);

        loadWithNeo(file, persistenceHandler);

        persistenceBackend.stop();

        PersistenceBackendFactoryRegistry.register(
                NeoBlueprintsURI.NEO_GRAPH_SCHEME,
                BlueprintsPersistenceBackendFactory.getInstance());

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(
                NeoBlueprintsURI.NEO_GRAPH_SCHEME,
                PersistentResourceFactory.getInstance());

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
}
