package fr.inria.atlanmod.neoemf.demo.notification;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.JavaFactory;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Model;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

/**
 * This class shows how to create a new NeoEMF/Graph resource and add an
 * {@link Adapter} to its top-level element.
 * <p>
 * In this example the resource is first created, and an element is added to its
 * direct content. An adapter is set to the created object and update operations
 * are performed to generate new notifications. Saving the resource does not
 * have an impact on the notification framework that works even when the
 * elements have been persisted in the database.
 *
 */
public class NewResourceNotifier {

	public static void main(String[] args) throws IOException {
		JavaPackage.eINSTANCE.eClass();
		JavaFactory factory = JavaFactory.eINSTANCE;

		PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
				BlueprintsPersistenceBackendFactory.getInstance());

		ResourceSet rSet = new ResourceSetImpl();
		rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME,
				PersistentResourceFactory.getInstance());

		try (PersistentResource resource = (PersistentResource) rSet
				.createResource(BlueprintsURI.createFileURI(new File("models/notifier_example.graphdb")))) {
			resource.save(Collections.emptyMap());
			// Create a new Model element with and set its name
			Model newModel = factory.createModel();
			newModel.setName("myModel");
			// Add the created element to the resource
			resource.getContents().add(newModel);

			// Add an Adapter to the top-level element that outputs a simple log
			// when an event is received
			newModel.eAdapters().add(new AdapterImpl() {
				@Override
				public void notifyChanged(Notification msg) {
					NeoLogger.info("Notification (type {0}) received from {1}", msg.getEventType(),
							msg.getNotifier().getClass().getName());
				}
			});

			// Update the Model
			String oldName = newModel.getName(); 									// doesn't generate a log
			newModel.setName("New Name"); 											// generates a log
			newModel.getCompilationUnits().add(factory.createCompilationUnit()); 	// generates a log

			// Save the resource and update it again
			resource.save(Collections.emptyMap());
			newModel.setName(oldName); 												// generates a log

		}
	}

}
