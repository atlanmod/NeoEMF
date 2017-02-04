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

package fr.inria.atlanmod.neoemf.context;

import fr.inria.atlanmod.neoemf.option.CommonOptionsBuilder;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * An abstract {@link ResourceBuilder} that manages the assembly and the construction of {@link PersistentResource}.
 *
 * @param <B> the "self"-type of this {@link ResourceBuilder}
 */
public abstract class AbstractResourceBuilder<B extends AbstractResourceBuilder<B>> implements ResourceBuilder {

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
     * Constructs a new {@code AbstractResourceBuilder} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link org.eclipse.emf.ecore.resource.Resource}
     *
     * @see EPackage.Registry
     */
    public AbstractResourceBuilder(EPackage ePackage) {
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
    protected void initBuilder() {
        isPersistent = false;
        EPackage.Registry.INSTANCE.put(ePackage.getNsURI(), ePackage);
        resourceSet = new ResourceSetImpl();
        resourceOptions = CommonOptionsBuilder.noOption();
    }

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
    public B persistent() {
        isPersistent = true;
        return me();
    }

    @Override
    public PersistentResource build() throws IOException {
        PersistentResource resource = (PersistentResource) resourceSet.createResource(uri);
        if (isPersistent) {
            resource.save(resourceOptions);
        }
        initBuilder();
        return resource;
    }
}
