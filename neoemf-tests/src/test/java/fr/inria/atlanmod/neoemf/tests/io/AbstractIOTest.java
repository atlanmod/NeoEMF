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
import fr.inria.atlanmod.neoemf.util.emf.compare.LazyMatchEngineFactory;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;

import java.io.IOException;
import java.net.URL;

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

    @BeforeClass
    public static void registerPackages() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());

        IOResourceManager.registerPackage("java", "http://www.eclipse.org/MoDisco/Java/0.2.incubation/java");
        IOResourceManager.registerPackage("uml", "http://schema.omg.org/spec/UML/2.1");
    }

    /**
     * Checks that the {@code expected} notifier is the same as the {@code actual}.
     *
     * @param actual   the notifier to check
     * @param expected the source notifier
     */
    protected void assertNotifierAreEqual(Notifier actual, Notifier expected) {
        IMatchEngine.Factory.Registry matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
        matchEngineRegistry.add(new LazyMatchEngineFactory());

        IComparisonScope scope = new DefaultComparisonScope(expected, actual, null);

        Comparison comparison = EMFCompare.builder()
                .setMatchEngineFactoryRegistry(matchEngineRegistry)
                .build()
                .compare(scope);

        assertThat(comparison.getConflicts()).hasSize(0);
        assertThat(comparison.getDifferences()).hasSize(0);
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
    protected void assertObjectHas(EObject obj, String metaclassName, int size, String name) {
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
    protected void assertReferenceHas(EObject obj, String name, int index, String referenceClassName, String referenceName, boolean many, boolean containment) {
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
    protected void assertAttributeHas(EObject obj, String name, Object value) {
        EAttribute attribute = checkNotNull(EAttribute.class.cast(obj.eClass().getEStructuralFeature(name)));

        if (isNull(value)) {
            assertThat(obj.eGet(attribute)).isEqualTo(attribute.getDefaultValue());
        }
        else {
            assertThat(obj.eGet(attribute).toString()).isEqualTo(value);
        }
    }

    /**
     * Loads the {@code uri} with standard EMF.
     *
     * @param uri the URI to load
     *
     * @return the the loaded content
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    protected EObject loadWithEMF(URI uri) throws IOException {
        return new ResourceSetImpl().getResource(uri, true).getContents().get(0);
    }

    /**
     * Loads the {@code uri} with NeoEMF according to the current {@link #context() Context}.
     *
     * @param uri the URI to load
     *
     * @return the loaded content
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    protected EObject loadWithNeoEMF(URI uri) throws IOException {
        BackendFactoryRegistry.register(context().uriScheme(), context().factory());

        try (DataMapper mapper = context().createMapper(file())) {
            ReaderFactory.fromXmi(new URL(uri.toString()).openStream(), WriterFactory.toMapper(mapper));
        }

        return closeAtExit(context().loadResource(file())).getContents().get(0);
    }
}
