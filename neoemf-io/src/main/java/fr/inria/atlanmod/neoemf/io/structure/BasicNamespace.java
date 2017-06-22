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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple representation of a namespace with a prefix and an URI.
 */
@ParametersAreNonnullByDefault
public class BasicNamespace {

    /**
     * The instance of the default {@code BasicNamespace}.
     */
    private static final BasicNamespace DEFAULT = new BasicNamespace("ecore", "http://www.eclipse.org/emf/2002/Ecore");

    /**
     * The prefix of this namespace.
     */
    private final String prefix;

    /**
     * The literal representation of the URI of this namespace.
     */
    private final String uri;

    /**
     * Constructs a new {@code BasicNamespace} with the given {@code prefix} and {@code uri}.
     *
     * @param prefix the prefix of this namespace
     * @param uri    the literal representation of the URI of this namespace
     */
    private BasicNamespace(String prefix, String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }

    /**
     * Returns the default namespace.
     *
     * @return the namespace representing "ecore @ http://www.eclipse.org/emf/2002/Ecore"
     */
    public static BasicNamespace getDefault() {
        return DEFAULT;
    }

    /**
     * Returns the prefix of this namespace.
     *
     * @return the prefix
     */
    public String prefix() {
        return prefix;
    }

    /**
     * Returns the literal representation of the URI of this namespace.
     *
     * @return the literal representation of the URI
     */
    public String uri() {
        return uri;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, uri);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!BasicNamespace.class.isInstance(o)) {
            return false;
        }

        BasicNamespace that = BasicNamespace.class.cast(o);
        return Objects.equals(prefix, that.prefix) &&
                Objects.equals(uri, that.uri);
    }

    @Override
    public String toString() {
        return prefix + "@" + uri;
    }

    /**
     * Registry of all declared {@link BasicNamespace}.
     */
    public static class Registry {

        /**
         * A map that holds registered {@link BasicNamespace}, identified by their prefix.
         */
        private final Map<String, BasicNamespace> namespacesByPrefix;

        /**
         * A map that holds registered {@link BasicNamespace}, identified by their URI.
         */
        private final Map<String, BasicNamespace> namespacesByUri;

        /**
         * Constructs a new {@code Registry}.
         */
        private Registry() {
            namespacesByPrefix = new HashMap<>();
            namespacesByUri = new HashMap<>();
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
        public Iterable<String> getPrefixes() {
            return Collections.unmodifiableSet(namespacesByPrefix.keySet());
        }

        /**
         * Returns a {@link BasicNamespace} identified by the given {@code prefix}, or {@code null} if no
         * namespace is registered with this {@code prefix}.
         *
         * @param prefix the prefix of the desired namespace
         *
         * @return a {@code BasicNamespace} identified by the given {@code prefix}, or {@code null} if no namespace is
         * registered with this {@code prefix}
         */
        public BasicNamespace getFromPrefix(@Nullable String prefix) {
            return Optional.ofNullable(prefix)
                    .map(namespacesByPrefix::get)
                    .orElse(null);
        }

        /**
         * Returns a {@link BasicNamespace} identified by the given {@code uri}, or {@code null} if no
         * namespace is registered with this {@code uri}.
         *
         * @param uri the URI of the desired namespace
         *
         * @return a {@code BasicNamespace} identified by the given {@code uri}, or {@code null} if no namespace is
         * registered with this {@code uri}.
         */
        public BasicNamespace getFromUri(@Nullable String uri) {
            return Optional.ofNullable(uri)
                    .map(v -> namespacesByUri.get(uri))
                    .orElse(null);
        }

        /**
         * Registers a new {@link BasicNamespace} with the given {@code prefix} and {@code uri}.
         *
         * @param prefix the prefix of the new namespace
         * @param uri    the URI associated with the prefix
         *
         * @return the new {@link BasicNamespace}
         */
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
        private static final class Holder {

            /**
             * The instance of the outer class.
             */
            private static final Registry INSTANCE = new Registry();
        }
    }
}
