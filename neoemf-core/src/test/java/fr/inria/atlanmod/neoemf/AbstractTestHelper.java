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

package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * An abstract {@link TestHelper} that manages the assembly and the construction of {@link PersistentResource}.
 *
 * @param <B> the "self"-type of this {@link TestHelper}
 */
public abstract class AbstractTestHelper<B extends AbstractTestHelper<B>> implements TestHelper {

    /**
     * The {@link EPackage} associated to the built resource.
     */
    private final EPackage ePackage;

    /**
     * The {@link ResourceSet} used to create the resource.
     */
    protected ResourceSet resourceSet;

    /**
     * The {@link URI} of the resource.
     */
    protected URI uri;

    /**
     * Map of options used to define the behavior of the resource.
     */
    protected Map<String, Object> resourceOptions;

    /**
     * Whether the {@link PersistentResource} is persistent.
     */
    private boolean isPersistent;

    /**
     * Constructs a new {@code AbstractTestHelper} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link org.eclipse.emf.ecore.resource.Resource}
     *
     * @see EPackage.Registry
     */
    public AbstractTestHelper(EPackage ePackage) {
        this.ePackage = ePackage;
        initBuilder();
    }

    /**
     * Returns this instance, casted as a {@code <B>}.
     *
     * @return this instance
     */
    @SuppressWarnings("unchecked")
    protected B me() {
        return (B) this;
    }

    /**
     * Initializes this builder, and registers the {@link EPackage} in the
     * {@link org.eclipse.emf.ecore.EPackage.Registry}.
     */
    protected final void initBuilder() {
        isPersistent = false;

        BackendFactoryRegistry.register(uriScheme(), factory());

        resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(uriScheme(), PersistentResourceFactory.getInstance());

        Optional.ofNullable(ePackage).ifPresent(p -> EPackage.Registry.INSTANCE.put(p.getNsURI(), p));
    }

    /**
     * Returns the {@link BackendFactory} of this helper.
     *
     * @return a factory
     */
    protected abstract BackendFactory factory();

    /**
     * Returns the {@link fr.inria.atlanmod.neoemf.option.PersistenceOptionsBuilder} of this helper.
     *
     * @return a builder
     */
    protected abstract AbstractPersistenceOptionsBuilder<?, ?> optionsBuilder();

    /**
     * Returns the URI scheme of this helper.
     *
     * @return the URI scheme
     */
    protected abstract String uriScheme();

    /**
     * Defines the {@link URI} of the created resource from another.
     *
     * @param uri the base {@link URI}
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.util.PersistenceURI#createURI(URI)
     */
    public abstract B uri(URI uri);

    /**
     * Defines the {@link URI} of the created resource from a file.
     *
     * @param file the {@link File} associated to the created resource
     *
     * @return this builder (for chaining)
     *
     * @see fr.inria.atlanmod.neoemf.util.PersistenceURI#createFileURI(File, String)
     */
    public abstract B file(File file);

    /**
     * Defines the created resource as persistent.
     *
     * @return this builder (for chaining)
     */
    public final B persistent() {
        isPersistent = true;
        return me();
    }

    /**
     * Returns the default options of this helper.
     *
     * @return a map of options
     */
    private Map<String, Object> defaultOptions() {
        return optionsBuilder()
                .log()
                .autocommit()
                .cacheIsSet()
                .cacheSizes()
                .cacheFeatures()
                .asMap();
    }

    @Override
    public final PersistentResource createResource() throws IOException {
        PersistentResource resource = (PersistentResource) resourceSet.createResource(uri);
        if (isPersistent) {
            resource.save(defaultOptions());
        }
        initBuilder();
        return resource;
    }

    @Override
    public final PersistentResource loadResource() throws IOException {
        PersistentResource resource = (PersistentResource) resourceSet.createResource(uri);
        resource.load(defaultOptions());
        initBuilder();
        return resource;
    }

    @Override
    public final Backend createPersistentBackend() {
        return factory().createPersistentBackend(uri, defaultOptions());
    }
}
