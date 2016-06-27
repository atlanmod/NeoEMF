/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.beans;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * A namespace identified by a prefix and a URI.
 */
public class Namespace {

    private final String prefix;
    private final String uri;

    private Namespace(String prefix, String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getUri() {
        return uri;
    }

    /**
     * Registry of all declared {@link Namespace namespaces}.
     */
    public static class Registry {

        private static Registry INSTANCE;

        public static Registry getInstance() {
            if (INSTANCE == null) {
                INSTANCE = new Registry();
            }
            return INSTANCE;
        }

        /**
         * Registered {@link Namespace namespaces} identified by their prefix.
         */
        private Cache<String, Namespace> namespacesByPrefix;

        /**
         * Registered {@link Namespace namespaces} identified by their URI.
         */
        private Cache<String, Namespace> namespacesByUri;

        private Registry() {
            namespacesByPrefix = CacheBuilder.newBuilder().build();
            namespacesByUri = CacheBuilder.newBuilder().build();
        }

        /**
         * Returns a {@link Namespace namespace} identified by the given {@code prefix}, or {@code null} if no
         * namespace is registered with this {@code prefix}.
         *
         * @param prefix the prefix of the desired namespace
         *
         * @return a {@link Namespace namespace} identified by the given {@code prefix}, or {@code null} if no
         * namespace is registered with this {@code prefix}
         */
        public Namespace getFromPrefix(String prefix) {
            if (prefix == null) {
                return null;
            }
            return namespacesByPrefix.getIfPresent(prefix);
        }

        /**
         * Returns a {@link Namespace namespace} identified by the given {@code uri}, or {@code null} if no
         * namespace is registered with this {@code uri}.
         *
         * @param uri the URI of the desired namespace
         *
         * @return a {@link Namespace namespace} identified by the given {@code uri}, or {@code null} if no
         * namespace is registered with this {@code uri}.
         */
        public Namespace getFromUri(String uri) {
            if (uri == null) {
                return null;
            }
            return namespacesByUri.getIfPresent(uri);
        }

        /**
         * Registers a new {@link Namespace namespace} with the given {@code prefix} and {@code uri}.
         * @param prefix the prefix of the new namespace
         * @param uri the uri associated with the prefix
         */
        public void register(String prefix, String uri) {
            Namespace ns = new Namespace(prefix, uri);
            namespacesByPrefix.put(prefix, ns);
            namespacesByUri.put(uri, ns);
        }

        /**
         * Cleans the registry.
         */
        public void clean() {
            namespacesByPrefix.invalidateAll();
            namespacesByUri.invalidateAll();
        }
    }
}
