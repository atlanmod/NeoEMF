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

import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.io.reader.ReaderFactory;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;
import fr.inria.atlanmod.neoemf.io.writer.WriterFactory;
import fr.inria.atlanmod.neoemf.tests.AbstractBackendTest;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
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
     * Retrieves a child element from the {@code root} following the given {@code indices}.
     *
     * @param root    the element from which to start the search
     * @param indices the indices of contained elements. The first index represents the index of the element from the
     *                root element, the second represents the index of the element from the previous,...
     *
     * @return the object
     */
    @Nonnull
    protected static EObject childFrom(EObject root, int... indices) {
        if (indices.length == 0) {
            throw new IllegalArgumentException("You must define at least one index");
        }

        EObject element = root;
        for (int index : indices) {
            element = element.eContents().get(index);
        }

        return checkNotNull(element);
    }

    /**
     * Initializes the test case by registering the packages.
     */
    @Before
    public void init() {
        IOResourceManager.registerPackage("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        IOResourceManager.registerPackage("uml", "http://schema.omg.org/spec/UML/2.1");

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
        Log.debug("Actual object     : {0}", actual);
        Log.debug("Expected object   : {0}", expected);

        if (!testedObjects.contains(expected)) {
            testedObjects.add(expected);

            Optional.ofNullable(expected).ifPresent(e -> assertThat(actual).isNotNull());

            assertThat(actual.eClass().getName()).isEqualTo(expected.eClass().getName());
            assertThat(actual.eContents()).hasSameSizeAs(expected.eContents());

            expected.eClass().getEAttributes()
                    .forEach(a -> assertEqualFeature(actual, expected, a.getFeatureID()));

            expected.eClass().getEReferences()
                    .forEach(r -> assertEqualFeature(actual, expected, r.getFeatureID()));

            IntStream.range(0, expected.eContents().size())
                    .forEach(i -> assertEqualEObject(actual.eContents().get(i), expected.eContents().get(i)));
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

            Log.debug("Actual feature    : {0}", actualValue);
            Log.debug("Expected feature  : {0}", expectedValue);

            if (EObject.class.isInstance(expectedValue)) {
                assertEqualEObject(EObject.class.cast(actualValue), EObject.class.cast(expectedValue));
            }
            else if (List.class.isInstance(expectedValue)) {
                List<EObject> expectedList = (List<EObject>) expectedValue;
                List<EObject> actualList = (List<EObject>) actualValue;

                assertThat(actualList).hasSameSizeAs(expectedList);

                for (int i = 0; i < expectedList.size(); i++) {
                    assertEqualEObject(actualList.get(i), expectedList.get(i));
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
            assertThat(
                    catchThrowable(() -> obj.eGet(obj.eClass().getEStructuralFeature("name")))
            ).isInstanceOf(NullPointerException.class);
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
        EReference reference = EReference.class.cast(obj.eClass().getEStructuralFeature(name));

        Object objectReference = obj.eGet(reference);
        EObject eObjectReference;

        if (many) {
            EList<EObject> eObjectList = (EList<EObject>) objectReference;
            eObjectReference = eObjectList.get(index);
        }
        else {
            eObjectReference = EObject.class.cast(objectReference);
        }

        assertThat(eObjectReference.eClass().getName()).isEqualTo(referenceClassName);

        if (isNull(referenceName)) {
            try {
                EAttribute attribute = checkNotNull(EAttribute.class.cast(eObjectReference.eClass().getEStructuralFeature("name")));
                assertThat(eObjectReference.eGet(attribute)).isEqualTo(attribute.getDefaultValue());
            }
            catch (NullPointerException ignored) {
                // It's not a problem if this happens
            }
        }
        else {
            EAttribute attribute = EAttribute.class.cast(eObjectReference.eClass().getEStructuralFeature("name"));
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
        EAttribute attribute = checkNotNull(EAttribute.class.cast(obj.eClass().getEStructuralFeature(name)));

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
     * @param stream the file to load
     *
     * @return the first element of the loaded content
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    protected EObject loadWithEMF(InputStream stream) throws IOException {
        Resource resource = new XMIResourceImpl();
        resource.load(stream, Collections.emptyMap());

        return resource
                .getContents()
                .get(0);
    }

    /**
     * Loads the {@code file} with NeoEMF according to the current {@link #context() Context}.
     *
     * @param stream the file to load
     *
     * @return the first element of the loaded content
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    protected EObject loadWithNeoEMF(InputStream stream) throws IOException {
        BackendFactoryRegistry.register(context().uriScheme(), context().factory());

        try (DataMapper mapper = context().createMapper(file())) {
            ReaderFactory.fromXmi(stream, WriterFactory.toMapper(mapper));
        }

        return closeAtExit(context().loadResource(file()))
                .getContents()
                .get(0);
    }
}
