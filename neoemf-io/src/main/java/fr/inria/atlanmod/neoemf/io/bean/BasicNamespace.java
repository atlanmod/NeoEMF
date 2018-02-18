/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.bean;

import fr.inria.atlanmod.commons.LazyReference;
import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.annotation.VisibleForTesting;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A simple representation of a {@link org.eclipse.emf.ecore.EPackage}.
 */
@ParametersAreNonnullByDefault
public class BasicNamespace {

    /**
     * The instance of the default {@code BasicNamespace}.
     */
    @Nonnull
    private static final BasicNamespace DEFAULT = Registry.getInstance().register(EcorePackage.eINSTANCE);

    /**
     * The prefix of this namespace.
     */
    @Nonnull
    private final String prefix;

    /**
     * The literal representation of the URI of this namespace.
     */
    @Nonnull
    private final String uri;

    /**
     * The {@link EPackage} associated to this namespace.
     */
    @Nonnull
    private LazyReference<EPackage> ePackage;

    /**
     * Constructs a new {@code BasicNamespace} with the given {@code prefix} and {@code uri}.
     *
     * @param prefix the prefix of this namespace
     * @param uri    the literal representation of the URI of this namespace
     */
    private BasicNamespace(String prefix, String uri) {
        this.prefix = prefix;
        this.uri = uri;

        this.ePackage = LazyReference.soft(() -> {
            EPackage p = EPackage.Registry.INSTANCE.getEPackage(uri());
            checkNotNull(p, "EPackage %s is not registered.", uri());
            return p;
        });
    }

    /**
     * Returns the default namespace.
     *
     * @return the namespace representing "ecore @ http://www.eclipse.org/emf/2002/Ecore"
     */
    @Nonnull
    public static BasicNamespace getDefault() {
        return DEFAULT;
    }

    /**
     * Returns the prefix of this namespace.
     *
     * @return the prefix
     */
    @Nonnull
    public String prefix() {
        return prefix;
    }

    /**
     * Returns the literal representation of the URI of this namespace.
     *
     * @return the literal representation of the URI
     */
    @Nonnull
    public String uri() {
        return uri;
    }

    /**
     * Returns the {@link org.eclipse.emf.ecore.EPackage} associated to this namespace.
     *
     * @return the {@link org.eclipse.emf.ecore.EPackage}
     */
    @Nonnull
    public EPackage ePackage() {
        return ePackage.get();
    }

    /**
     * Defines the {@link org.eclipse.emf.ecore.EPackage} associated to this namespace.
     *
     * @param ePackage the {@link org.eclipse.emf.ecore.EPackage}
     *
     * @return this instance (for chaining)
     */
    public BasicNamespace ePackage(EPackage ePackage) {
        checkNotNull(ePackage, "ePackage");

        this.ePackage.update(ePackage);
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BasicNamespace that = BasicNamespace.class.cast(o);
        return Objects.equals(uri, that.uri);
    }

    @Override
    public String toString() {
        return prefix + '@' + uri;
    }

    /**
     * Registry of all declared {@link BasicNamespace}.
     */
    @Singleton
    public static class Registry {

        /**
         * A map that holds registered {@link BasicNamespace}, identified by their prefix.
         */
        private final Map<String, BasicNamespace> namespacesByPrefix = new HashMap<>();

        /**
         * A map that holds registered {@link BasicNamespace}, identified by their URI.
         */
        private final Map<String, BasicNamespace> namespacesByUri = new HashMap<>();

        /**
         * Constructs a new {@code Registry}.
         */
        private Registry() {
        }

        /**
         * Returns the instance of this class.
         *
         * @return the instance of this class
         */
        @Nonnull
        public static Registry getInstance() {
            return Holder.INSTANCE;
        }

        /**
         * Returns all registered namespace prefixes.
         *
         * @return a immutable collection
         */
        @Nonnull
        @VisibleForTesting
        public Iterable<String> getPrefixes() {
            return Collections.unmodifiableSet(namespacesByPrefix.keySet());
        }

        /**
         * Returns a {@link BasicNamespace} from the specified {@code ePackage}.
         *
         * @param ePackage the {@link EPackage} associated with the namespace
         *
         * @return the namespace
         */
        @Nonnull
        public BasicNamespace get(EPackage ePackage) {
            return Optional.ofNullable(getByUri(ePackage.getNsURI())).orElseGet(() -> register(ePackage));
        }

        /**
         * Returns a {@link BasicNamespace} identified by the given {@code prefix}, or {@code null} if no namespace is
         * registered with this {@code prefix}.
         *
         * @param prefix the prefix of the desired namespace
         *
         * @return a namespace identified by the given {@code prefix}, or {@code null} if no namespace is registered
         * with this {@code prefix}
         */
        public BasicNamespace getByPrefix(@Nullable String prefix) {
            return Optional.ofNullable(prefix).map(namespacesByPrefix::get).orElse(null);
        }

        /**
         * Returns a {@link BasicNamespace} identified by the given {@code uri}, or {@code null} if no namespace is
         * registered with this {@code uri}.
         *
         * @param uri the URI of the desired namespace
         *
         * @return a namespace identified by the given {@code uri}, or {@code null} if no namespace is registered with
         * this {@code uri}.
         */
        public BasicNamespace getByUri(@Nullable String uri) {
            return Optional.ofNullable(uri).map(v -> namespacesByUri.get(uri)).orElse(null);
        }

        /**
         * Registers a new {@link BasicNamespace} from the given {@code ePackage}.
         *
         * @param ePackage the {@link EPackage} associated with the namespace
         *
         * @return the new namespace
         */
        @Nonnull
        public BasicNamespace register(EPackage ePackage) {
            checkNotNull(ePackage, "ePackage");

            return register(ePackage.getNsPrefix(), ePackage.getNsURI()).ePackage(ePackage);
        }

        /**
         * Registers a new {@link BasicNamespace} with the given {@code prefix} and {@code uri}.
         *
         * @param prefix the prefix of the new namespace
         * @param uri    the URI associated with the prefix
         *
         * @return the new namespace
         */
        @Nonnull
        public BasicNamespace register(String prefix, String uri) {
            if (namespacesByPrefix.containsKey(prefix)) {
                BasicNamespace ns = namespacesByPrefix.get(prefix);

                if (Objects.equals(ns.uri(), uri)) {
                    return ns;
                }
            }

            BasicNamespace ns = new BasicNamespace(prefix, uri);
            namespacesByPrefix.put(prefix, ns);
            namespacesByUri.put(uri, ns);
            return ns;
        }

        /**
         * Cleans the registry.
         */
        public void clean() {
            namespacesByPrefix.clear();
            namespacesByUri.clear();
        }

        /**
         * The initialization-on-demand holder of the singleton of this class.
         */
        @Static
        private static final class Holder {

            /**
             * The instance of the outer class.
             */
            static final Registry INSTANCE = new Registry();
        }
    }
}
