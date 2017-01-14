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

package fr.inria.atlanmod.neoemf.io.structure;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Collections;

import javax.annotation.Nonnull;

import static java.util.Objects.isNull;

/**
 * A simple representation of a namespace with a prefix and an URI.
 */
public class Namespace {

    /**
     * The instance of the default {@code Namespace}.
     */
    private static final Namespace DEFAULT = new Namespace("ecore", "http://www.eclipse.org/emf/2002/Ecore");

    /**
     * The prefix of this namespace.
     */
    private final String prefix;

    /**
     * The literal representation of the URI of this namespace.
     */
    private final String uri;

    /**
     * Constructs a new {@code Namespace} with the given {@code prefix} and {@code uri}.
     *
     * @param prefix the prefix of this namespace
     * @param uri    the literal representation of the URI of this namespace
     */
    private Namespace(String prefix, String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }

    /**
     * Returns the default namespace.
     *
     * @return the namespace representing "ecore @ http://www.eclipse.org/emf/2002/Ecore"
     */
    public static Namespace getDefault() {
        return DEFAULT;
    }

    /**
     * Returns the prefix of this namespace.
     *
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Returns the literal representation of the URI of this namespace
     *
     * @return the literal representation of the URI
     */
    public String getUri() {
        return uri;
    }

    /**
     * Registry of all declared {@link Namespace}.
     */
    public static class Registry {

        /**
         * Registered {@link Namespace} identified by their prefix.
         */
        private final Cache<String, Namespace> nsByPrefixCache;

        /**
         * Registered {@link Namespace} identified by their URI.
         */
        private final Cache<String, Namespace> nsByUriCache;

        /**
         * Constructs a new {@code Registry}.
         */
        private Registry() {
            nsByPrefixCache = Caffeine.newBuilder().build();
            nsByUriCache = Caffeine.newBuilder().build();
        }

        /**
         * Returns the instance of this class.
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
        public Iterable<String> getPrefixes() {
            return Collections.unmodifiableSet(nsByPrefixCache.asMap().keySet());
        }

        /**
         * Returns a {@link Namespace} identified by the given {@code prefix}, or {@code null} if no
         * namespace is registered with this {@code prefix}.
         *
         * @param prefix the prefix of the desired namespace
         *
         * @return a {@code Namespace} identified by the given {@code prefix}, or {@code null} if no namespace is
         * registered with this {@code prefix}
         */
        public Namespace getFromPrefix(String prefix) {
            if (isNull(prefix)) {
                return null;
            }
            return nsByPrefixCache.getIfPresent(prefix);
        }

        /**
         * Returns a {@link Namespace} identified by the given {@code uri}, or {@code null} if no
         * namespace is registered with this {@code uri}.
         *
         * @param uri the URI of the desired namespace
         *
         * @return a {@code Namespace} identified by the given {@code uri}, or {@code null} if no namespace is
         * registered with this {@code uri}.
         */
        public Namespace getFromUri(String uri) {
            if (isNull(uri)) {
                return null;
            }
            return nsByUriCache.getIfPresent(uri);
        }

        /**
         * Registers a new {@link Namespace} with the given {@code prefix} and {@code uri}.
         *
         * @param prefix the prefix of the new namespace
         * @param uri    the URI associated with the prefix
         */
        public void register(String prefix, String uri) {
            Namespace ns = new Namespace(prefix, uri);
            nsByPrefixCache.put(prefix, ns);
            nsByUriCache.put(uri, ns);
        }

        /**
         * Cleans the registry.
         */
        public void clean() {
            nsByPrefixCache.invalidateAll();
            nsByUriCache.invalidateAll();
        }

        /**
         * The initialization-on-demand holder of the singleton of this class.
         */
        private static class Holder {

            private static final Registry INSTANCE = new Registry();
        }
    }
}
