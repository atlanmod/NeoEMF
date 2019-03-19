/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.demo.notification;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.config.BlueprintsNeo4jConfig;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsUriFactory;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.atlanmod.commons.log.Log;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.JavaFactory;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Model;

import java.io.IOException;
import java.util.Collections;

/**
 * This class shows how to create a new NeoEMF/Graph resource and add an {@link org.eclipse.emf.common.notify.Adapter}
 * to its top-level element.
 * <p>
 * In this example the resource is first created, and an element is added to its direct content. An adapter is set to
 * the created object and update operations are performed to generate new notifications. Saving the resource does not
 * have an impact on the notification framework that works even when the elements have been persisted in the database.
 */
public class NewResourceNotifier {

    public static void main(String[] args) throws IOException {
        JavaPackage.eINSTANCE.eClass();
        JavaFactory factory = JavaFactory.eINSTANCE;

        ResourceSet resourceSet = new ResourceSetImpl();

        URI uri = new BlueprintsUriFactory().createLocalUri("databases/notifier_example.graphdb");
        ImmutableConfig options = new BlueprintsNeo4jConfig();

        try (PersistentResource resource = (PersistentResource) resourceSet.createResource(uri)) {
            resource.save(options.toMap());

            // Create a new Model element with and set its name
            Model newModel = factory.createModel();
            newModel.setName("myModel");

            // Add the created element to the resource
            resource.getContents().add(newModel);

            // Add an Adapter to the top-level element that outputs a simple log when an event is received
            newModel.eAdapters().add(new AdapterImpl() {
                @Override
                public void notifyChanged(Notification msg) {
                    Log.info("Notification (type {0}) received from {1}", msg.getEventType(), msg.getNotifier().getClass().getName());
                }
            });

            // Update the Model
            String oldName = newModel.getName();                                   // Doesn't generate a log
            newModel.setName("New Name");                                          // Generates a log
            newModel.getCompilationUnits().add(factory.createCompilationUnit());   // Generates a log

            // Save the resource and update it again
            resource.save(Collections.emptyMap());
            newModel.setName(oldName);                                             // Generates a log
        }
    }
}
