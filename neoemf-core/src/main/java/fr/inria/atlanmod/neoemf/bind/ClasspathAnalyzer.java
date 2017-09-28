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

package fr.inria.atlanmod.neoemf.bind;

import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.bind.annotation.FactoryBinding;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Phaser;
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * ???
 */
@Singleton
@ParametersAreNonnullByDefault
final class ClasspathAnalyzer {

    /**
     * The annotation used to determine the relation with NeoEMF.
     */
    @Nonnull
    private static final Class<? extends Annotation> BASE_ANNOTATION = FactoryBinding.class;

    /**
     * The identifier of the phase 'ready-to-use' of the {@link #phaser}. In this state, no analysis is in progress.
     */
    @Nonnegative
    private static final int READY = 0;

    /**
     * A phaser representing the number of classpath analysis in progress. When {@code phaser.getPhase() > 0}, at least
     * one analysis is in progress, and all calls to {@link #registeredUrls()} will wait for all tasks to complete.
     */
    @Nonnull
    private final Phaser phaser = new Phaser(READY);

    /**
     * A set that holds the registered {@link URL}s.
     */
    @Nonnull
    private final Set<URL> registeredUrls = Collections.synchronizedSet(new HashSet<>());

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static ClasspathAnalyzer getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Returns the set of registered {@link URL}s.
     *
     * @return a immutable set of {@link URL}s
     */
    @Nonnull
    public Collection<URL> registeredUrls() {
        // Waiting for the end of initialization
        phaser.awaitAdvance(READY);

        return Collections.unmodifiableSet(registeredUrls);
    }

    /**
     * Scans all URLs provided by the given {@code callable} and retain only those that can be related to NeoEMF.
     *
     * @param callable a supplier of a set of {@link URL}
     *
     * @throws NullPointerException if {@code callable} is {@code null}
     */
    public void register(Callable<Collection<URL>> callable) {
        checkNotNull(callable);

        phaser.register();

        Bindings.getSharedPool().submit(() -> {
            try {
                // One configuration for one task
                ConfigurationBuilder baseConfig = new ConfigurationBuilder()
                        .setExecutorService(Bindings.getSharedPool())
                        .setScanners(new TypeAnnotationsScanner(), new SubTypesScanner());

                // Filter URLs, and remove any that cannot be related to NeoEMF (Java, EMF,...)
                checkNotNull(callable.call())
                        .stream()
                        .filter(url -> isRelated(url, baseConfig))
                        .collect(Collectors.toCollection(() -> registeredUrls));
            }
            catch (Exception e) {
                Log.warn(e);
            }
            finally {
                phaser.arriveAndDeregister();
            }
        });
    }

    /**
     * Returns {@code true} if the {@code url} contains any element related to NeoEMF.
     *
     * @param url        the url to scan
     * @param baseConfig the base configuration of the classpath scanning
     *
     * @return {@code true} if the {@code url} contains any element related to NeoEMF
     */
    private boolean isRelated(URL url, ConfigurationBuilder baseConfig) {
        try {
            return !new Reflections(baseConfig.setUrls(url))
                    .getTypesAnnotatedWith(BASE_ANNOTATION)
                    .isEmpty();
        }
        catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final ClasspathAnalyzer INSTANCE = new ClasspathAnalyzer();
    }
}
