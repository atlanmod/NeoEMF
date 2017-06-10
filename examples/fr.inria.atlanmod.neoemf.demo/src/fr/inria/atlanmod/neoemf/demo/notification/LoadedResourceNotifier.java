package fr.inria.atlanmod.neoemf.demo.notification;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.demo.importer.BlueprintsImporter;
import fr.inria.atlanmod.neoemf.demo.importer.EfficientBlueprintsImporter;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Model;

import java.io.File;
import java.io.IOException;

/**
 * This class shows how to load a NeoEMF/Graph resource and add an
 * {@link Adapter} to its top-level element.
 * <p>
 * This class needs to load an existing NeoEMF/Graph model to work, run
 * {@link BlueprintsImporter} and {@link EfficientBlueprintsImporter} to create
 * the instance of the model.
 */
public class LoadedResourceNotifier {

    public static void main(String[] args) throws IOException {
        JavaPackage.eINSTANCE.eClass();

        BackendFactoryRegistry.register(BlueprintsURI.SCHEME, BlueprintsBackendFactory.getInstance());

        ResourceSet rSet = new ResourceSetImpl();
        rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());

        try (PersistentResource resource = (PersistentResource) rSet.createResource(BlueprintsURI.newBuilder().fromFile(new File("models/sample.graphdb")))) {
            resource.load(BlueprintsOptions.noOption());

            checkModel(resource);

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

    private static void checkModel(Resource resource) {
        if (resource.getContents().isEmpty()) {
            throw new IllegalStateException("The content of the resource is empty, make sure you have created it using (Efficient)BlueprintsImporter");
        }
    }
}
