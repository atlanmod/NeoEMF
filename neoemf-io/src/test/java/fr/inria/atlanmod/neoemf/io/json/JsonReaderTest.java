package fr.inria.atlanmod.neoemf.io.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.utils.Obj;
import fr.inria.atlanmod.neoemf.io.reader.json.AbstractJsonStreamReader;
import fr.inria.atlanmod.neoemf.io.reader.json.JsonReaderHelper;

import java.io.*;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

import fr.inria.atlanmod.neoemf.io.reader.json.User;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emfjson.jackson.resource.JsonResourceFactory;
import org.osgi.framework.UnfilteredServiceListener;

public class JsonReaderTest {

    public static void main(String [] args) throws IOException {
        final Logger logger = Logger.getLogger(JsonReaderTest.class);
        final ObjectMapper mapper = new ObjectMapper();
        AbstractJsonStreamReader reader = new AbstractJsonStreamReader();
        InputStream stream = new FileInputStream(JsonReaderHelper.resourceData);
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry()
                .getExtensionToFactoryMap()
                .put("json", new JsonResourceFactory());
        final String jsonData = "/Users/macbook/eclipse-workspace/NeoEMF/neoemf-io/src/main/resources/data.json";
       Resource resource = resourceSet.createResource(URI.createFileURI(jsonData));
        //reader.parse(stream);
        File file = new File(jsonData);

        User user = mapper.readValue(file, User.class);
       logger.info(user);
    }


}
