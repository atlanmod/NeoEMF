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

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.util.Collections;
import java.util.Map;

/**
 * An {@link Adapter} used to create {@link Resource}s.
 */
public interface InternalAdapter extends Adapter {

    /**
     * Returns the name of this adapter.
     *
     * @return the name
     */
    String getName();

    /**
     * Returns the extension of the adapted {@link Resource}, used to create the stores.
     *
     * @return the extension
     */
    String getResourceExtension();

    /**
     * Returns the extension of the {@link Resource}, used for benchmarks.
     *
     * @return the extension
     */
    String getStoreExtension();

    /**
     * Retrieves and initializes the {@link EPackage} used by this adapter.
     *
     * @return the package
     */
    EPackage initAndGetEPackage();

    /**
     * Creates a new {@link Resource} in the given {@code file}, by using the given {@code resourceSet}.
     *
     * @param file        the file to create the resource
     * @param resourceSet the resource set used to created the resource
     *
     * @return a new resource
     */
    Resource createResource(File file, ResourceSet resourceSet);

    /**
     * Returns the default {@link Map} options of this adapter
     *
     * @return the {@link Map} options
     */
    default Map<String, Object> getOptions() {
        return Collections.emptyMap();
    }
}
