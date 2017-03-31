package fr.inria.atlanmod.neoemf.demo.notification;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Model;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.demo.importer.BlueprintsImporter;
import fr.inria.atlanmod.neoemf.demo.importer.EfficientBlueprintsImporter;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

/**
 * This class shows how to load a NeoEMF/Graph resource and add an
 * {@link Adapter} to its top-level element.
 * <p>
 * This class needs to load an existing NeoEMF/Graph model to work, run
 * {@link BlueprintsImporter} and {@link EfficientBlueprintsImporter} to create
 * the instance of the model.
 *
 */
public class LoadedResourceNotifier {

	public static void main(String[] args) throws IOException {
		JavaPackage.eINSTANCE.eClass();

		PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
				BlueprintsPersistenceBackendFactory.getInstance());

		ResourceSet rSet = new ResourceSetImpl();
		rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME,
				PersistentResourceFactory.getInstance());

		try (PersistentResource resource = (PersistentResource) rSet
				.createResource(BlueprintsURI.createFileURI(new File("models/sample.graphdb")))) {
			resource.load(Collections.emptyMap());
			checkModel(resource);
			Model model = (Model) resource.getContents().get(0);
			
			// Add an Adapter to the top-level element that outputs a simple log when an event is received
			model.eAdapters().add(new AdapterImpl() {
				@Override
				public void notifyChanged(Notification msg) {
					NeoLogger.info("Notification (type {0}) received from {1}", msg.getEventType(),
							msg.getNotifier().getClass().getName());
				}
			});
			String oldName = model.getName(); 	// doesn't generate a log
			model.setName("New Name"); 			// generates a log
			model.setName(oldName); 			// generates a log

		}
	}

	private static void checkModel(Resource resource) {
		if (resource.getContents().size() == 0) {
			throw new IllegalStateException(
					"The content of the resource is empty, make sure you have created it using (Efficient)BlueprintsImporter");
		}
	}

}
