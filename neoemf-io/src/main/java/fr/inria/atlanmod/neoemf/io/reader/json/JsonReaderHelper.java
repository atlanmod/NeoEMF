package fr.inria.atlanmod.neoemf.io.reader.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.InputStream;


public class JsonReaderHelper {

    public static final ObjectMapper mapper = new ObjectMapper();
    public static final ResourceSet resourceSet = new ResourceSetImpl();
    public static final String resourceData = "/Users/macbook/eclipse-workspace/NeoEMF/neoemf-io/src/main/resources/data.json";


}
