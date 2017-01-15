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

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.io.BlueprintsHandlerFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder;
import fr.inria.atlanmod.neoemf.io.Importer;
import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmt.modisco.java.JavaPackage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class EfficientBlueprintsImporter {

    public static void main(String[] args) throws Exception {
        EPackage.Registry.INSTANCE.put(JavaPackage.eNS_URI, JavaPackage.eINSTANCE);

        Map<String, Object> options = BlueprintsNeo4jOptionsBuilder.newBuilder().asMap();

        PersistenceBackendFactory factory = BlueprintsPersistenceBackendFactory.getInstance();
        PersistenceBackend backend = factory.createPersistentBackend(new File("models/sample2.graphdb"), options);

        PersistenceHandler handler = BlueprintsHandlerFactory.createPersistenceHandler((BlueprintsPersistenceBackend) backend, false);

        long begin = System.currentTimeMillis();

        Importer.fromXmi(new FileInputStream(new File("models/sample.xmi")), handler);

        long end = System.currentTimeMillis();
        NeoLogger.info("Import done in {0} seconds", (end - begin) / 1000);
    }
}
