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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import java.net.URL;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * A utility class that manages external resources for I/O tests.
 */
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
        return ResourceLoader.Holder.INSTANCE.getUri("/xmi/sampleStandard.xmi");
    }

    /**
     * Returns the XMI file that uses {@code xmi:id} references.
     *
     * @return the XMI file
     */
    @Nonnull
    public static URI xmiWithId() {
        return ResourceLoader.Holder.INSTANCE.getUri("/xmi/sampleWithId.xmi");
    }

    /**
     * Returns the compressed XMI file that uses XPath references.
     *
     * @return the XMI file
     */
    @Nonnull
    public static URI zxmiStandard() {
        return ResourceLoader.Holder.INSTANCE.getUri("/xmi/sampleStandard.zxmi");
    }

    /**
     * Returns the compressed XMI file that uses {@code xmi:id} references.
     *
     * @return the XMI file
     */
    @Nonnull
    public static URI zxmiWithId() {
        return ResourceLoader.Holder.INSTANCE.getUri("/xmi/sampleWithId.zxmi");
    }

    /**
     * Registers a EPackage in {@link EPackage.Registry} according to its {@code prefix} and {@code uri}, from an
     * Ecore file.
     * <p>
     * The targeted Ecore file must be present in the {@code /resources/ecore} directory of this modules.
     */
    public static void registerPackage(String prefix) {
        Resource.Factory.Registry.INSTANCE
                .getExtensionToFactoryMap()
                .put("ecore", new EcoreResourceFactoryImpl());

        ResourceSet rs = new ResourceSetImpl();

        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
        rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        Resource r = rs.getResource(ResourceLoader.Holder.INSTANCE.getUri("/ecore/" + prefix + ".ecore"), true);

        EObject eObject = r.getContents().get(0);
        if (EPackage.class.isInstance(eObject)) {
            EPackage pkg = checkNotNull(EPackage.class.cast(eObject));
            EPackage.Registry.INSTANCE.put(pkg.getNsURI(), pkg);
        }
    }

    /**
     * A resource loader that allows to retrieve a resource from this module, even if the call comes from another one.
     *
     * @see Class#getResource(String)
     */
    private static final class ResourceLoader {

        /**
         * Retrieves the resource with the given {@code name}.
         *
         * @param name the name of the resource
         *
         * @return the URL of the resource
         *
         * @throws NullPointerException if the resource cannot be found
         */
        @Nonnull
        private URL getUrl(String name) {
            return checkNotNull(getClass().getResource(name), "Unable to find the resource %s", name);
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
        private URI getUri(String name) {
            return URI.createURI(getUrl(name).toString());
        }

        /**
         * The initialization-on-demand holder of the singleton of this class.
         */
        private static final class Holder {

            /**
             * The instance of the outer class.
             */
            private static final ResourceLoader INSTANCE = new ResourceLoader();
        }
    }
}
