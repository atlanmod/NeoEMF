/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner.state;

import fr.inria.atlanmod.neoemf.benchmarks.io.LocalWorkspace;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.TearDown;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link BaseState} that provides capabilities for simple queries.
 */
@ParametersAreNonnullByDefault
public class AtomicState extends BaseState {

    /**
     * The default {@link ImmutableConfig} for saving the current resource.
     */
    @Nonnull
    private static final ImmutableConfig DEFAULT_CONFIG = new BaseConfig<>();

    /**
     * The {@link EFactory} related to the current adapter.
     */
    private EFactory factory;

    /**
     * The current {@link Resource}.
     */
    private Resource resource;

    /**
     * The root {@link EObject} of the current datastore.
     */
    private Package root;

    /**
     * Returns the current resource loaded from the datastore.
     */
    @Nonnull
    public Resource resource() {
        return resource;
    }

    /**
     * Returns the root {@link EObject} of the current datastore.
     *
     * @return the root {@link EObject}
     */
    @Nonnull
    public Package root() {
        return root;
    }

    /**
     * Creates a new {@link EObject} that is an instance of the {@link org.eclipse.emf.ecore.EClass} identified by the
     * specified {@code eClassId}.
     *
     * @param eClassId the identifier of the {@link org.eclipse.emf.ecore.EClass}
     * @param <T>      the type of the desired {@link EObject}
     *
     * @return a new instance
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public <T extends EObject> T create(int eClassId) {
        EClassImpl eClass = (EClassImpl) EcoreFactory.eINSTANCE.createEClass();
        eClass.setClassifierID(eClassId);
        return (T) factory.create(eClass);
    }

    /**
     * Creates a new temporary datastore.
     */
    @Setup(Level.Iteration)
    public void loadResource() throws IOException {
        factory = adapter().initAndGetEPackage().getEFactoryInstance();

        final String fileName = "atomic-" + UUID.randomUUID().toString().replaceAll("-", "");
        final URI uri = adapter().createUri(LocalWorkspace.newTempDirectory(), fileName);

        // Create the resource
        final Resource newResource = adapter().create(uri);
        adapter().save(newResource, DEFAULT_CONFIG);
        adapter().unload(newResource);

        resource = adapter().load(uri, DEFAULT_CONFIG);
    }

    @Setup(Level.Invocation)
    public void loadResourceContent() throws IOException {
        root = create(JavaPackage.PACKAGE);

        if (resource.getContents().isEmpty()) {
            resource.getContents().add(root);
        }
        else {
            // Distributed databases may have latency (see #unloadResourceContent)
            resource.getContents().set(0, root);
        }

        initResource(root);

        adapter().save(resource, DEFAULT_CONFIG);
    }

    @TearDown(Level.Invocation)
    public void unloadResourceContent() throws IOException {
        resource.getContents().clear();
        root = null;

        adapter().save(resource, DEFAULT_CONFIG);
    }

    /**
     * Unloads the current datastore.
     */
    @TearDown(Level.Iteration)
    public void unloadResource() {
        if (nonNull(resource)) {
            adapter().unload(resource);
            resource = null;
        }
    }

    /**
     * Cleans the workspace if necessary.
     */
    @TearDown(Level.Trial)
    public void cleanWorkspace() {
        LocalWorkspace.cleanTempDirectory();
    }

    /**
     * Initializes the content of the resource.
     *
     * @param root the root of the current resource to initialize
     */
    @OverridingMethodsMustInvokeSuper
    protected void initResource(Package root) {
        // By default, do nothing
    }
}
