/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind.internal;

import fr.inria.atlanmod.commons.Lazy;
import fr.inria.atlanmod.neoemf.util.Activator;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.namespace.BundleNamespace;
import org.osgi.framework.wiring.BundleWiring;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An {@link URLCollector} that analyzes a {@link org.osgi.framework.BundleContext} to retrieve all {@link
 * org.osgi.framework.Bundle}s related to this one. The {@link org.osgi.framework.Bundle}s are located with their {@link
 * URL} by using {@code org.eclipse.core.runtime.FileLocator#getBundleFile()}.
 */
@ParametersAreNonnullByDefault
public class BundleContextCollector implements URLCollector {

    /**
     * The {@code FileLocator#getBundleFile(Bundle)} method.
     */
    @Nonnull
    private static final Lazy<Method> BUNDLE_LOCATOR = Lazy.with(() -> {
        try {
            return Activator.class.getClassLoader()
                    .loadClass("org.eclipse.core.runtime.FileLocator")
                    .getMethod("getBundleFile", Bundle.class);
        }
        catch (NoSuchMethodException | ClassNotFoundException e) {
            throw new IllegalStateException("Unable to find Eclipse runtime", e);
        }
    });

    /**
     * The OSGi context to explore.
     */
    @Nonnull
    private final BundleContext context;

    /**
     * Constructs a new {@code BundleContextCollector} for the specified {@code context}.
     *
     * @param context the OSGi context to explore
     */
    public BundleContextCollector(BundleContext context) {
        this.context = checkNotNull(context, "context");
    }

    /**
     * Retrieves the {@link URL} of the {@code bundle}.
     * <p>
     * If an error occurs, it will be ignored.
     *
     * @param bundle the bundle to locate
     *
     * @return an {@link Optional} containing the {@link URL} of the {@code bundle}, or {@link Optional#empty()} if the
     * {@code bundle} cannot be located
     */
    @Nonnull
    private static Optional<URL> urlOf(Bundle bundle) {
        try {
            return Optional.of(File.class.cast(BUNDLE_LOCATOR.get().invoke(null, bundle)).toURI().toURL());
        }
        catch (MalformedURLException e) {
            return Optional.empty();
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException("Unable to find Eclipse runtime", e);
        }
    }

    @Nonnull
    @Override
    public Set<URL> get() {
        Bundle coreBundle = context.getBundle();
        return collectDependentBundles(coreBundle);
    }

    /**
     * Collects the {@link URL} of all bundles, within this context, that are dependant from the {@code bundle}.
     *
     * @param bundle the bundle
     *
     * @return the {@link URL}s of all bundles that are dependant
     */
    @Nonnull
    private Set<URL> collectDependentBundles(Bundle bundle) {
        long id = bundle.getBundleId();

        // Filter all bundles that are dependant to the `neoemf-core` bundle and collect their URL
        return Arrays.stream(context.getBundles())
                .filter(b -> isDependentOn(id, b))
                .map(BundleContextCollector::urlOf)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    /**
     * Checks that the {@code bundle} is wired to the bundle identified by the specified {@code bundleId}.
     *
     * @param bundleId the identifier of the bundle
     * @param bundle   the bundle to check
     *
     * @return {@code true} if the {@code bundle} is dependent
     */
    private boolean isDependentOn(long bundleId, Bundle bundle) {
        return bundle.adapt(BundleWiring.class)
                .getRequiredWires(BundleNamespace.BUNDLE_NAMESPACE)
                .stream()
                .map(r -> r.getProviderWiring().getBundle().getBundleId())
                .anyMatch(id -> id == bundleId);
    }
}
