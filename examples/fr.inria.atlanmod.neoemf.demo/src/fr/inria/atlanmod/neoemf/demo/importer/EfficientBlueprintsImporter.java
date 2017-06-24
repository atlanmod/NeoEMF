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

package fr.inria.atlanmod.neoemf.demo.importer;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUri;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.io.Migrator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmt.modisco.java.JavaPackage;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;

/**
 * Imports an existing model stored in a XMI files into a Blueprints-based {@link fr.inria.atlanmod.neoemf.resource.PersistentResource}
 * using a scalable XMI parser that bypasses the EMF API to improve performances and enable large XMI imports.
 */
public class EfficientBlueprintsImporter {

    public static void main(String[] args) throws Exception {
        EPackage.Registry.INSTANCE.put(JavaPackage.eNS_URI, JavaPackage.eINSTANCE);

        Map<String, Object> options = BlueprintsNeo4jOptions.builder()
                .autoSave()
                .noCache() // Can slow down the import that is write-only
                .asMap();

        BackendFactory factory = BlueprintsBackendFactory.getInstance();

        File sourceFile = new File("models/sample.xmi");
        URI targetUri = BlueprintsUri.builder().fromFile("models/sample2.graphdb");

        try (Backend backend = factory.createPersistentBackend(targetUri, options); DataMapper mapper = StoreFactory.getInstance().createStore(backend, options)) {
            Instant start = Instant.now();

            Migrator.fromXmi(sourceFile)
                    .toMapper(mapper)
                    .migrate();

            Instant end = Instant.now();
            Log.info("Import done in {0} seconds", Duration.between(start, end).getSeconds());
        }
    }
}
