/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.proxy;

import org.atlanmod.commons.LazyReference;
import org.atlanmod.commons.annotation.Singleton;
import org.atlanmod.commons.annotation.Static;
import org.atlanmod.commons.annotation.VisibleForTesting;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;


import static org.atlanmod.commons.Preconditions.checkNotNull;


/**
 * A simple representation of a {@link org.eclipse.emf.ecore.EPackage}.
 */
@ParametersAreNonnullByDefault
public class ProxyPackage implements Proxy<ProxyPackage, EPackage> {

    /**
     * The instance of the default namespace: {@code ecore @ http://www.eclipse.org/emf/2002/Ecore}.
     */
    @Nonnull
    public static final ProxyPackage DEFAULT = Registry.getInstance().register(EcorePackage.eINSTANCE);
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
     * The {@link EPackage} represented by this object.
     */
    @Nonnull
    private final LazyReference<EPackage> ePackage = LazyReference.soft(() -> {
        final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
        // Until here we don't have a null object
        //EPackage p = EPackage.Registry.INSTANCE.getEPackage(getUri());
        EPackage p = EPackage.Registry.INSTANCE.getEPackage(getUri());
       // EPackage p2 = new EPackageImpl("name", );
        logger.info("the ePackage :: {}", p);
        if(p == null){
            // create an EPackage object
            //p = EPackage.Registry.INSTANCE.getEPackage("http://www.omg.org/XMI");
            throw new NullPointerException("The ePackage is null");
        }
        logger.info("Epackage name :::::: {}",getUri() );
        checkNotNull(p, "EPackage %s is not registered.", getUri());
        return p;
    });

    /**
     * Constructs a new {@code ProxyPackage} with the given {@code prefix} and {@code uri}.
     *
     * @param prefix the prefix of this namespace
     * @param uri    the literal representation of the URI of this namespace
     */
    private ProxyPackage(String prefix, String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }

    @Override
    public EPackage getOrigin() {
        return ePackage.get();
        //return (ePackage != null) ? ePackage.get(): new EPackageImpl("", )
    }

    @Nonnull
    @Override
    public ProxyPackage setOrigin(EPackage ePackage) {
        checkNotNull(ePackage, "ePackage");

        this.ePackage.update(ePackage);
        return this;
    }

    /**
     * Returns the prefix of this namespace.
     *
     * @return the prefix
     */
    @Nonnull
    public String getPrefix() {
        return prefix;
    }

    /**
     * Returns the literal representation of the URI of this namespace.
     *
     * @return the literal representation of the URI
     */
    @Nonnull
    public String getUri() {
        return uri;
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

        ProxyPackage that = (ProxyPackage) o;
        return Objects.equals(uri, that.uri);
    }

    @Override
    public String toString() {
        return prefix + '@' + uri;
    }

    /**
     * Registry of all declared {@link ProxyPackage}.
     */
    @Singleton
    public static class Registry {

        /**
         * A map that holds registered {@link ProxyPackage}, identified by their prefix.
         */
        private final Map<String, ProxyPackage> namespacesByPrefix = new HashMap<>();

        /**
         * A map that holds registered {@link ProxyPackage}, identified by their URI.
         */
        private final Map<String, ProxyPackage> namespacesByUri = new HashMap<>();

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
        Iterable<String> getPrefixes() {
            return Collections.unmodifiableSet(namespacesByPrefix.keySet());
        }

        /**
         * Returns a {@link ProxyPackage} from the specified {@code ePackage}.
         *
         * @param ePackage the {@link EPackage} associated with the namespace
         *
         * @return the namespace
         */
        @Nonnull
        public ProxyPackage get(EPackage ePackage) {
            checkNotNull(ePackage, "ePackage");

            final ProxyPackage ns = getByUri(ePackage.getNsURI());
            return Optional.ofNullable(ns).map(p -> p.setOrigin(ePackage)).orElseGet(() -> register(ePackage));
        }

        /**
         * Returns a {@link ProxyPackage} identified by the given {@code prefix}, or {@code null} if no namespace is
         * registered with this {@code prefix}.
         *
         * @param prefix the prefix of the desired namespace
         *
         * @return a namespace identified by the given {@code prefix}, or {@code null} if no namespace is registered
         * with this {@code prefix}
         */
        public ProxyPackage getByPrefix(@Nullable String prefix) {
            return Optional.ofNullable(prefix).map(namespacesByPrefix::get).orElse(null);
        }

        /**
         * Returns a {@link ProxyPackage} identified by the given {@code uri}, or {@code null} if no namespace is
         * registered with this {@code uri}.
         *
         * @param uri the URI of the desired namespace
         *
         * @return a namespace identified by the given {@code uri}, or {@code null} if no namespace is registered with
         * this {@code uri}.
         */
        public ProxyPackage getByUri(@Nullable String uri) {
            return Optional.ofNullable(uri).map(v -> namespacesByUri.get(uri)).orElse(null);
        }

        /**
         * Registers a new {@link ProxyPackage} from the given {@code ePackage}.
         *
         * @param ePackage the {@link EPackage} associated with the namespace
         *
         * @return the new namespace
         */
        @Nonnull
        public ProxyPackage register(EPackage ePackage) {
            checkNotNull(ePackage, "ePackage");

            return register(ePackage.getNsPrefix(), ePackage.getNsURI()).setOrigin(ePackage);
        }

        /**
         * Registers a new {@link ProxyPackage} with the given {@code prefix} and {@code uri}.
         *
         * @param prefix the prefix of the new namespace
         * @param uri    the URI associated with the prefix
         *
         * @return the new namespace
         */
        @Nonnull
        public ProxyPackage register(String prefix, String uri) {
            if (namespacesByPrefix.containsKey(prefix)) {
                ProxyPackage ns = namespacesByPrefix.get(prefix);

                if (Objects.equals(ns.getUri(), uri)) {
                    return ns;
                }
            }

            ProxyPackage ns = new ProxyPackage(prefix, uri);
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
