/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind;

import fr.inria.atlanmod.neoemf.util.Activator;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.namespace.BundleNamespace;
import org.osgi.framework.wiring.BundleWiring;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * ???
 */
@ParametersAreNonnullByDefault
final class BundleContextAnalyzer {

    /**
     * The {@code FileLocator#getBundleFile(Bundle)} method.
     */
    private static Method bundleLocatorMethod;

    /**
     * The OSGi context to explore.
     */
    @Nonnull
    private final BundleContext context;

    /**
     * Constructs a new {@code BundleContextAnalyzer} for the specified {@code baseContext}.
     *
     * @param baseContext the OSGi context to explore
     */
    public BundleContextAnalyzer(BundleContext baseContext) {
        this.context = checkNotNull(baseContext);
    }

    /**
     * Checks that the {@code bundle} is wired to the bundle identified by the specified {@code bundleId}.
     *
     * @param bundleId the identifier of the bundle
     * @param bundle   the bundle to check
     *
     * @return {@code true} if the {@code bundle} is dependent
     */
    private static boolean isDependentOn(long bundleId, Bundle bundle) {
        return bundle.adapt(BundleWiring.class)
                .getRequiredWires(BundleNamespace.BUNDLE_NAMESPACE)
                .stream()
                .map(r -> r.getProviderWiring().getBundle().getBundleId())
                .anyMatch(id -> id == bundleId);
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
        URL url = null;

        try {
            url = File.class.cast(getBundleLocatorMethod().invoke(null, bundle)).toURI().toURL();
        }
        catch (IOException ignored) {
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(url);
    }

    /**
     * Retrieves the {@code FileLocator#getBundleFile(Bundle)} method, using reflexion.
     *
     * @return the static method
     */
    @Nonnull
    private static Method getBundleLocatorMethod() {
        if (isNull(bundleLocatorMethod)) {
            try {
                bundleLocatorMethod = Activator.class.getClassLoader()
                        .loadClass("org.eclipse.core.runtime.FileLocator")
                        .getMethod("getBundleFile", Bundle.class);
            }
            catch (NoSuchMethodException | ClassNotFoundException e) {
                throw new RuntimeException("Unable to find org.eclipse.core.runtime.FileLocator#getBundleFile(Bundle) method", e);
            }
        }

        return bundleLocatorMethod;
    }

    /**
     * Retrieves the {@link URL} of all bundles, within this context, that are dependant from the {@code bundle}.
     *
     * @param bundle the bundle
     *
     * @return the {@link URL}s of all bundles that are dependant
     */
    @Nonnull
    public Set<URL> getDependentBundles(Bundle bundle) {
        long coreId = bundle.getBundleId();

        // Filter all bundles that are dependant to the `neoemf-core` bundle and collect their URL
        return Arrays.stream(context.getBundles())
                .filter(b -> isDependentOn(coreId, b))
                .map(BundleContextAnalyzer::urlOf)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}
