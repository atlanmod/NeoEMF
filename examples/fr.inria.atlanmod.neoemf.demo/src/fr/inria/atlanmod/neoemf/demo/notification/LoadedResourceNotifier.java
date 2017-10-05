/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.demo.notification;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config.BlueprintsNeo4jConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUri;
import fr.inria.atlanmod.neoemf.demo.importer.DirectBlueprintsImporter;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Model;

import java.io.IOException;

/**
 * This class shows how to load a NeoEMF/Graph resource and add an {@link org.eclipse.emf.common.notify.Adapter} to its
 * top-level element.
 * <p>
 * This class needs to load an existing NeoEMF/Graph model to work, run {@link fr.inria.atlanmod.neoemf.demo.importer.BlueprintsImporter}
 * and {@link DirectBlueprintsImporter} to create the instance of the model.
 */
public class LoadedResourceNotifier {

    public static void main(String[] args) throws IOException {
        JavaPackage.eINSTANCE.eClass();

        ResourceSet resourceSet = new ResourceSetImpl();

        URI uri = BlueprintsUri.builder().fromFile("models/sample.graphdb");
        Config options = BlueprintsNeo4jConfig.newConfig();

        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(uri)) {
            resource.load(options.toMap());

            if (resource.getContents().isEmpty()) {
                throw new IllegalStateException("The content of the resource is empty, make sure you have created it using (Efficient)BlueprintsImporter");
            }

            Model model = (Model) resource.getContents().get(0);

            // Add an Adapter to the top-level element that outputs a simple log when an event is received
            model.eAdapters().add(new AdapterImpl() {
                @Override
                public void notifyChanged(Notification msg) {
                    Log.info("Notification (type {0}) received from {1}", msg.getEventType(), msg.getNotifier().getClass().getName());
                }
            });

            String oldName = model.getName();   // Doesn't generate a log
            model.setName("New Name");          // Generates a log
            model.setName(oldName);             // Generates a log
        }
    }
}
