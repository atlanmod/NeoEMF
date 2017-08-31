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

package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.commons.annotation.Singleton;
import fr.inria.atlanmod.commons.annotation.Static;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmt.modisco.java.impl.JavaPackageImpl;

import java.net.URL;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A utility class that manages external resources for I/O tests.
 */
@Static
@ParametersAreNonnullByDefault
public final class IOResourceManager {

    @SuppressWarnings("JavaDoc")
    private IOResourceManager() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Returns the XMI file that uses XPath references.
     *
     * @return the XMI file
     */
    @Nonnull
    public static URI xmiStandard() {
        return ResourceLoader.getInstance().getResourceUri("/xmi/sampleStandard.xmi");
    }

    /**
     * Returns the XMI file that uses {@code xmi:id} references.
     *
     * @return the XMI file
     */
    @Nonnull
    public static URI xmiWithId() {
        return ResourceLoader.getInstance().getResourceUri("/xmi/sampleWithId.xmi");
    }

    /**
     * Returns the compressed XMI file that uses XPath references.
     *
     * @return the XMI file
     */
    @Nonnull
    public static URI zxmiStandard() {
        return ResourceLoader.getInstance().getResourceUri("/xmi/sampleStandard.zxmi");
    }

    /**
     * Returns the compressed XMI file that uses {@code xmi:id} references.
     *
     * @return the XMI file
     */
    @Nonnull
    public static URI zxmiWithId() {
        return ResourceLoader.getInstance().getResourceUri("/xmi/sampleWithId.zxmi");
    }

    /**
     * Registers all {@link EPackage}s used in test-cases.
     */
    public static void registerAllPackages() {
        JavaPackageImpl.init();
    }

    /**
     * A resource loader that allows to retrieve a resource from this module, even if the call comes from another one.
     *
     * @see Class#getResource(String)
     */
    @Singleton
    private static final class ResourceLoader {

        @SuppressWarnings("JavaDoc")
        public static ResourceLoader getInstance() {
            return Holder.INSTANCE;
        }

        /**
         * Retrieves the resource with the given {@code name}.
         *
         * @param name the name of the resource
         *
         * @return a URI of the resource
         *
         * @throws NullPointerException if the resource cannot be found
         */
        @Nonnull
        private URI getResourceUri(String name) {
            URL url = checkNotNull(getClass().getResource(name), "Unable to find the resource %s", name);
            return URI.createURI(url.toString());
        }

        /**
         * The initialization-on-demand holder of the singleton of this class.
         */
        @Static
        private static final class Holder {

            /**
             * The instance of the outer class.
             */
            private static final ResourceLoader INSTANCE = new ResourceLoader();
        }
    }
}
