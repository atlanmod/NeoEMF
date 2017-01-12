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

import static java.util.Objects.isNull;

/**
 * A namespace identified by a prefix and a URI.
 */
public class Namespace {

    private static final Namespace DEFAULT = new Namespace("ecore", "http://www.eclipse.org/emf/2002/Ecore");
    private final String prefix;
    private final String uri;

    private Namespace(String prefix, String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }

    public static Namespace getDefault() {
        return DEFAULT;
    }

    public String getPrefix() {
        return prefix;
    }

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

        private Registry() {
            nsByPrefixCache = Caffeine.newBuilder().build();
            nsByUriCache = Caffeine.newBuilder().build();
        }

        public static Registry getInstance() {
            return Holder.INSTANCE;
        }

        public Iterable<String> getPrefixes() {
            return nsByPrefixCache.asMap().keySet();
        }

        /**
         * Returns a {@link Namespace} identified by the given {@code prefix}, or {@code null} if no
         * namespace is registered with this {@code prefix}.
         *
         * @param prefix the prefix of the desired namespace
         *
         * @return a {@link Namespace} identified by the given {@code prefix}, or {@code null} if no namespace is
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
         * @return a {@link Namespace} identified by the given {@code uri}, or {@code null} if no namespace is
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

        private static class Holder {

            private static final Registry INSTANCE = new Registry();
        }
    }
}
