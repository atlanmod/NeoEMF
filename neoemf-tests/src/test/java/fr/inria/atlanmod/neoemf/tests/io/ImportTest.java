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

package fr.inria.atlanmod.neoemf.tests.io;

import fr.inria.atlanmod.neoemf.Tags;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.io.Importer;
import fr.inria.atlanmod.neoemf.io.writer.PersistenceWriter;
import fr.inria.atlanmod.neoemf.io.writer.WriterFactory;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.AbstractBackendTest;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Nonnull;

import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test case about the import from XMI to {@link PersistenceBackend}s.
 */
public class ImportTest extends AbstractBackendTest {

    /**
     * A {@link java.util.Set} holding all tested {@link EObject}s, to avoid testing the same {@link Object} twice.
     */
    private HashSet<EObject> testedObjects;

    /**
     * A {@link java.util.Set} holding tested {@link EStructuralFeature}s, to avoid testing the same {@link Object}
     * twice.
     */
    private HashSet<EStructuralFeature> testedFeatures;

    /**
     * Retrieves a child element from the {@code root} following the given {@code indexes}.
     *
     * @param root    the element from which to start the search
     * @param indexes the indexes of contained elements. The first index represents the index of the element from the
     *                root element, the second represents the index of the element from the previous,...
     *
     * @return the object
     */
    @Nonnull
    private static EObject getChildFrom(EObject root, int... indexes) {
        if (indexes.length == 0) {
            throw new IllegalArgumentException("You must define at least one index");
        }

        EObject element = root;
        for (int index : indexes) {
            element = element.eContents().get(index);
        }
        assertThat(element).isNotNull();
        return element;
    }

    /**
     * Returns the test XMI file that uses XPath as references.
     *
     * @return the test file
     */
    protected static File getXmiStandard() {
        return getResourceFile("/io/xmi/sampleStandard.xmi");
    }

    /**
     * Returns the test XMI file that uses {@code xmi:id} as references.
     *
     * @return the test file
     */
    protected static File getXmiWithId() {
        return getResourceFile("/io/xmi/sampleWithId.xmi");
    }

    /**
     * Returns a test file according to the given {@code path}.
     *
     * @param path the resource path
     *
     * @return the test file
     */
    protected static File getResourceFile(String path) {
        return new File(ImportTest.class.getResource(path).getFile());
    }

    /**
     * Registers a EPackage in {@link EPackage.Registry} according to its {@code prefix} and {@code uri}, from an Ecore
     * file.
     * <p>
     * The targeted Ecore file must be present in {@code /resources/ecore}.
     *
     * @param prefix the prefix of the URI. It is used to retrieve the {@code ecore} file
     * @param uri    the URI of the {@link EPackage}
     */
    protected static void registerEPackageFromEcore(String prefix, String uri) {
        File file = getResourceFile("/io/ecore/{name}.ecore".replaceAll("\\{name\\}", prefix));

        EPackage ePackage = null;

        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

        ResourceSet rs = new ResourceSetImpl();

        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
        rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        Resource r = rs.getResource(URI.createFileURI(file.toString()), true);
        EObject eObject = r.getContents().get(0);
        if (eObject instanceof EPackage) {
            ePackage = (EPackage) eObject;
            rs.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
        }

        assertThat(ePackage).isNotNull(); // "EPackage does not exist"

        EPackage.Registry.INSTANCE.put(uri, ePackage);
    }

    /**
     * Initializes the test case by registering the packages.
     */
    @Before
    public void init() {
        registerEPackageFromEcore("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        registerEPackageFromEcore("uml", "http://schema.omg.org/spec/UML/2.1");

        testedObjects = new HashSet<>();
        testedFeatures = new HashSet<>();
    }

    /**
     * Check that the elements are properly processed.
     * <p>
     * All elements must have an id and a class name.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testElementsAndChildren() throws IOException {
        EObject eObject;
        EObject eObjectChild;

        EObject root = loadWithNeoEMF(getXmiStandard());
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
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testAttributes() throws IOException {
        EObject eObject;

        EObject root = loadWithNeoEMF(getXmiStandard());
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
     * Check that the XPath references/id are properly processed.
     * <p>
     * Containment and inverse reference must have been created. References previously detected as attributes, are now
     * well placed.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testReferences() throws IOException {
        EObject eObject;
        EObject eObjectChild;

        EObject root = loadWithNeoEMF(getXmiStandard());
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

    /**
     * Compares a model read with standard EMF and another read with NeoEMF.
     * <p>
     * The models use XPath as references.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testCompare() throws IOException {
        File file = getXmiStandard();

        EObject emfObject = loadWithEMF(file);
        EObject neoObject = loadWithNeoEMF(file);

        assertEqualEObject(neoObject, emfObject);
    }

    /**
     * Compares a model read with standard EMF and another read with NeoEMF.
     * <p>
     * The models use {@code xmi:id} as references.
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Test
    @Ignore // FIXME Inverse references don't exist in EMF
    @Category({Tags.PersistentTests.class, Tags.IOTests.class})
    public void testCompareWithId() throws IOException {
        File file = getXmiWithId();

        EObject emfObject = loadWithEMF(file);
        EObject neoObject = loadWithNeoEMF(file);

        assertEqualEObject(neoObject, emfObject);
    }

    /**
     * Checks that the {@code expected} {@link EObject} is the same as the {@code actual}.
     *
     * @param actual   the NeoEMF object
     * @param expected the EMF object
     */
    private void assertEqualEObject(EObject actual, EObject expected) {
        NeoLogger.debug("Actual object     : {0}", actual);
        NeoLogger.debug("Expected object   : {0}", expected);

        if (!testedObjects.contains(expected)) {
            testedObjects.add(expected);

            assertThat(actual.eClass().getName()).isEqualTo(expected.eClass().getName());
            assertThat(actual.eContents()).hasSameSizeAs(expected.eContents());

            for (EAttribute attribute : expected.eClass().getEAttributes()) {
                assertEqualFeature(actual, expected, attribute.getFeatureID());
            }

            for (EReference reference : expected.eClass().getEReferences()) {
                assertEqualFeature(actual, expected, reference.getFeatureID());
            }

            for (int i = 0; i < expected.eContents().size(); i++) {
                assertEqualEObject(actual.eContents().get(i), expected.eContents().get(i));
            }
        }
    }

    /**
     * Asserts that the {@link EStructuralFeature} identified by {@code featureId} is the same between the
     * {@code expected} and the {@code actual}.
     *
     * @param actual    the NeoEMF object
     * @param expected  the EMF object
     * @param featureId the identifier of the {@link EStructuralFeature} to compare
     */
    @SuppressWarnings("unchecked") // Unchecked method 'hasSameSizeAs(Iterable<?>)' invocation
    private void assertEqualFeature(EObject actual, EObject expected, int featureId) {
        EStructuralFeature feature = expected.eClass().getEStructuralFeature(featureId);

        if (!testedFeatures.contains(feature)) {
            testedFeatures.add(feature);

            Object expectedValue = expected.eGet(feature);
            Object actualValue = actual.eGet(actual.eClass().getEStructuralFeature(featureId));

            NeoLogger.debug("Actual feature    : {0}", actualValue);
            NeoLogger.debug("Expected feature  : {0}", expectedValue);

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

    /**
     * Asserts that the given {@code object} the given {@code name}, {@code size}, and inherit from an
     * {@link org.eclipse.emf.ecore.EClass} named as {@code metaclassName}.
     *
     * @param obj           the {@link EObject} to tests
     * @param metaclassName the expected name of the metaclass of the {@code obj}
     * @param size          the expected size of the {@code obj}
     * @param name          the expected name of the {@code obj}
     */
    private void assertValidElement(EObject obj, String metaclassName, int size, String name) {
        assertThat(obj.eClass().getName()).isEqualTo(metaclassName);
        assertThat(obj.eContents()).hasSize(size);
        if (isNull(name)) {
            Throwable thrown = catchThrowable(() -> obj.eGet(obj.eClass().getEStructuralFeature("name")));
            assertThat(thrown).isInstanceOf(NullPointerException.class);
        }
        else {
            assertThat(obj.eGet(obj.eClass().getEStructuralFeature("name"))).isEqualTo(name);
        }
    }

    /**
     * Asserts that the {@link EReference} of the {@code obj}, identified by its {@code name}, has the given parameters.
     *
     * @param obj                the {@link EObject} to retrieve to {@link EReference}
     * @param name               the name of the {@link EReference} to retrieve
     * @param index              the index concerned by the test (if {@code many == true})
     * @param referenceClassName the expected name of the metaclass of the referenced {@link EObject}
     * @param referenceName      the expected name of the referenced {@link EObject}
     * @param many               if the reference must be a multi-valued feature
     * @param containment        if the reference must be a containment
     */
    @SuppressWarnings("unchecked") // Unchecked cast: 'Object' to 'EList<...>'
    private void assertValidReference(EObject obj, String name, int index, String referenceClassName, String referenceName, boolean many, boolean containment) {
        EReference reference = (EReference) obj.eClass().getEStructuralFeature(name);

        Object objectReference = obj.eGet(reference);
        EObject eObjectReference;

        if (many) {
            EList<EObject> eObjectList = (EList<EObject>) objectReference;
            eObjectReference = eObjectList.get(index);
        }
        else {
            eObjectReference = (EObject) objectReference;
        }

        assertThat(eObjectReference.eClass().getName()).isEqualTo(referenceClassName);

        if (isNull(referenceName)) {
            try {
                EAttribute attribute = (EAttribute) eObjectReference.eClass().getEStructuralFeature("name");
                assertThat(eObjectReference.eGet(attribute)).isEqualTo(attribute.getDefaultValue());
            }
            catch (NullPointerException ignore) {
                // It's not a problem if this happens
            }
        }
        else {
            EAttribute attribute = (EAttribute) eObjectReference.eClass().getEStructuralFeature("name");
            assertThat(eObjectReference.eGet(attribute).toString()).isEqualTo(referenceName);
        }

        assertThat(reference.isContainment()).isEqualTo(containment);
        assertThat(reference.isMany()).isEqualTo(many);
    }

    /**
     * Asserts that the {@link EAttribute} of the {@code obj}, identified by its {@code name}, has the given {@code
     * value}.
     *
     * @param obj   the {@link EObject} to retrieve to {@link EAttribute}
     * @param name  the name of the {@link EAttribute} to retrieve
     * @param value the expected value of the attribute
     */
    private void assertValidAttribute(EObject obj, String name, Object value) {
        EAttribute attribute = (EAttribute) obj.eClass().getEStructuralFeature(name);

        if (isNull(value)) {
            assertThat(obj.eGet(attribute)).isEqualTo(attribute.getDefaultValue());
        }
        else {
            assertThat(obj.eGet(attribute).toString()).isEqualTo(value);
        }
    }

    /**
     * Loads the {@code file} with standard EMF.
     *
     * @param file the file to load
     *
     * @return the first element of the loaded content
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    private EObject loadWithEMF(File file) throws IOException {
        Resource resource = new XMIResourceImpl();
        resource.load(new FileInputStream(file), Collections.emptyMap());
        return resource.getContents().get(0);
    }

    /**
     * Loads the {@code file} with NeoEMF according to the current {@link #context() Context}.
     *
     * @param file the file to load
     *
     * @return the first element of the loaded content
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    private EObject loadWithNeoEMF(File file) throws IOException {
        PersistenceBackendFactoryRegistry.register(context().uriScheme(), context().persistenceBackendFactory());

        try (PersistenceBackend backend = context().createBackend(file())) {
            PersistenceWriter handler = WriterFactory.newNaiveWriter(backend);
            Importer.fromXmi(new FileInputStream(file), handler);
        }

        PersistentResource resource = context().loadResource(null, file());
        return resource.getContents().get(0);
    }
}
