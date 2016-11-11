package fr.inria.atlanmod.neoemf.benchmarks.datastore;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.util.Collections;
import java.util.Map;

public interface InternalBackend extends Backend {

    String getName();

    String getResourceExtension();

    String getStoreExtension();

    EPackage initAndGetEPackage() throws Exception;

    Resource createResource(File file, ResourceSet resourceSet) throws Exception;

    default Map<Object, Object> getOptions() {
        return Collections.emptyMap();
    }
}
