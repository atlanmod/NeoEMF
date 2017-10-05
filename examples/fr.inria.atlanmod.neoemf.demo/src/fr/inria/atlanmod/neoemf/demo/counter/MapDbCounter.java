/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.demo.counter;

import fr.inria.atlanmod.commons.Stopwatch;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.data.mapdb.config.MapDbConfig;
import fr.inria.atlanmod.neoemf.data.mapdb.util.MapDbUri;
import fr.inria.atlanmod.neoemf.demo.util.Helpers;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;

import java.io.IOException;

/**
 * A simple example showing how to access an existing MapDB-based {@link PersistentResource} and traverse its content to
 * count the number of elements it contains.
 */
public class MapDbCounter {

    public static void main(String[] args) throws IOException {
        JavaPackage.eINSTANCE.eClass();

        ResourceSet resourceSet = new ResourceSetImpl();

        URI uri = MapDbUri.builder().fromFile("models/sample.mapdb");

        Config config = MapDbConfig.newConfig()
                .withIndices();

        Stopwatch stopwatch = Stopwatch.createStarted();

        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(uri)) {
            resource.load(config.toMap());

            long size = Helpers.countElements(resource);
            Log.info("Resource {0} contains {1} elements", resource.toString(), size);
        }

        stopwatch.stop();
        Log.info("Query computed in {0} ms", stopwatch.elapsed().toMillis());
    }
}
