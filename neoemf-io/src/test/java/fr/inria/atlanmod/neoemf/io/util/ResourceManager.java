/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.neoemf.tests.sample.impl.SamplePackageImpl;

import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.annotation.Singleton;
import org.atlanmod.commons.annotation.Static;
import org.atlanmod.neoemf.tests.iceage.impl.IceagePackageImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.impl.JavaPackageImpl;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Guards.checkNotNull;

/**
 * A static utility class that manages external resources for I/O tests.
 */
@Static
@ParametersAreNonnullByDefault
public final class ResourceManager {

    static {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());
    }

    private ResourceManager() {
        throw Throwables.notInstantiableClass(getClass());
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
     * Returns a XMI file with some specific cases: BigDecimal, BigInteger, etc.
     *
     * @return the XMI file
     */
    @Nonnull
    public static URI xmiIceAge() {
        return ResourceLoader.getInstance().getResourceUri("/xmi/iceage.xmi");
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


    @Nonnull
    public static URI proxyContainer() {
        return ResourceManager.ResourceLoader.getInstance().getResourceUri("/xmi/proxy-container.xmi");
    }

    @Nonnull
    public static URI proxyContainment() {
        return ResourceManager.ResourceLoader.getInstance().getResourceUri("/xmi/proxy-containment.xmi");
    }

    /**
     * Registers all {@link EPackage}s used in test-cases.
     */
    public static void registerAllPackages() {
        JavaPackageImpl.init();
        SamplePackageImpl.init();
        IceagePackageImpl.init();
    }

    /**
     * Loads the {@code uri} with standard EMF.
     *
     * @param uri the URI to load
     * @return the the loaded content
     */
    @Nonnull
    public static EObject load(URI uri) throws IOException {
        Map<String, Object> options = new HashMap<>();
        if (uri.fileExtension().endsWith("zxmi")) {
            options.put(XMIResource.OPTION_ZIP, true);
        }

        Resource resource = new ResourceSetImpl().createResource(uri);
        resource.load(options);

        return resource.getContents().get(0);
    }

    /**
     * A resource loader that allows to retrieve a resource from this module, even if the call comes from another one.
     *
     * @see Class#getResource(String)
     */
    @Singleton
    private static final class ResourceLoader {

        @Nonnull
        public static ResourceLoader getInstance() {
            return Holder.INSTANCE;
        }

        /**
         * Retrieves the resource with the given {@code name}.
         *
         * @param name the name of the resource
         * @return a URI of the resource
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
            static final ResourceLoader INSTANCE = new ResourceLoader();
        }
    }
}
