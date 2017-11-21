/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind;

import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.concurrent.MoreExecutors;
import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.bind.annotation.FactoryBinding;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
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
     * The {@link Converter} used to manipulate {@link URI}s instead of {@link URL}s.
     * <p>
     * Prefer to store {@link URI}s because of the {@link URL#equals(Object)} method.
     */
    @Nonnull
    private static final Converter<URL, URI> URL_CONVERTER = Converter.from(
            url -> {
                try {
                    return url.toURI();
                }
                catch (URISyntaxException e) {
                    throw new IllegalStateException(e);
                }
            },
            uri -> {
                try {
                    return uri.toURL();
                }
                catch (MalformedURLException e) {
                    throw new IllegalStateException(e);
                }
            });

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
     * The concurrent pool for managing classpath analysis tasks.
     */
    @Nonnull
    private final ExecutorService analysisPool = MoreExecutors.newFixedThreadPool();

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
    private final Set<URI> registeredUrls = Collections.synchronizedSet(new HashSet<>());

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
        // Waiting for classpath analysis
        phaser.awaitAdvance(READY);

        return registeredUrls.stream()
                .map(URL_CONVERTER::revert)
                .collect(Collectors.toSet());
    }

    /**
     * Scans all URLs provided by the given {@code callable} and retain only those that can be related to NeoEMF.
     *
     * @param callable a supplier of a set of {@link URL}
     *
     * @throws NullPointerException if {@code callable} is {@code null}
     */
    public void register(Callable<Collection<URL>> callable) {
        checkNotNull(callable, "callable");

        phaser.register();

        analysisPool.submit(() -> {
            try {
                // One configuration for one task
                ConfigurationBuilder baseConfig = new ConfigurationBuilder()
                        .setExecutorService(Bindings.getBindingPool())
                        .setScanners(new TypeAnnotationsScanner(), new SubTypesScanner());

                // Filter URLs, and remove any that cannot be related to NeoEMF (Java, EMF,...)
                checkNotNull(callable.call(), "callable.call")
                        .stream()
                        .filter(url -> isRelated(url, baseConfig))
                        .map(URL_CONVERTER::convert)
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
