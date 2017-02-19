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

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.io.reader.ReaderFactory;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;

import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 *
 */
public abstract class AbstractIOTest extends AbstractBackendTest {

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
    protected static EObject getChildFrom(EObject root, int... indexes) {
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
        return new File(AbstractIOTest.class.getResource(path).getFile());
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
     * Checks that the {@code expected} {@link EObject} is the same as the {@code actual}.
     *
     * @param actual   the NeoEMF object
     * @param expected the EMF object
     */
    protected void assertEqualEObject(EObject actual, EObject expected) {
        NeoLogger.debug("Actual object     : {0}", actual);
        NeoLogger.debug("Expected object   : {0}", expected);

        if (!testedObjects.contains(expected)) {
            testedObjects.add(expected);

            Optional.ofNullable(expected).ifPresent(e -> assertThat(actual).isNotNull());

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
    protected void assertValidElement(EObject obj, String metaclassName, int size, String name) {
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
    protected void assertValidReference(EObject obj, String name, int index, String referenceClassName, String referenceName, boolean many, boolean containment) {
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
    protected void assertValidAttribute(EObject obj, String name, Object value) {
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
    protected EObject loadWithEMF(File file) throws IOException {
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
    protected EObject loadWithNeoEMF(File file) throws IOException {
        PersistenceBackendFactoryRegistry.register(context().uriScheme(), context().persistenceBackendFactory());

        try (PersistenceBackend backend = context().createBackend(file())) {
            ReaderFactory.fromXmi(file, WriterFactory.toNaiveBackend(backend));
        }

        PersistentResource resource = context().loadResource(null, file());
        return resource.getContents().get(0);
    }
}
