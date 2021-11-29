package fr.inria.atlanmod.neoemf.io.json;

import fr.inria.atlanmod.neoemf.data.im.DefaultInMemoryBackend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.tests.sample.*;
import fr.inria.atlanmod.neoemf.tests.sample.impl.TypeMapImpl;
import fr.inria.atlanmod.neoemf.tests.sample.impl.ValueImpl;
import org.atlanmod.commons.log.Log;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emfjson.jackson.resource.JsonResourceFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class JsonWriterTest {
	// Get the factory
	private static final SampleFactory factory = SampleFactory.eINSTANCE;

	static void populateResource(Resource resource) {
		Value val = factory.createValue();
		val.setValue(10);
		Value val2 = factory.createValue();
		val.setValue(12);

		resource.getContents().addAll(Arrays.asList(
			val
		));
	}

	static void testWrite(String targetPath) throws IOException {
		// java to emfjson-jackson (for testing/comparison purpose)
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("json", new JsonResourceFactory());
		Log.info("Exporting to file... [{0}]", targetPath + "test-jackson-write.json");
		Resource resource = resourceSet.createResource(URI.createFileURI(targetPath + "test-jackson-write.json"));
		populateResource(resource);
		resource.save(null);

		// java to xmi
		ResourceSet resourceSet2 = new ResourceSetImpl();
		resourceSet2.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
		Log.info("Exporting to file... [{0}]", targetPath + "test-write.xmi");
		Resource resource2 = resourceSet2.createResource(URI.createFileURI(targetPath + "test-write.xmi"));
		populateResource(resource2);
		resource2.save(null);

		try (DataMapper mapper = new DefaultInMemoryBackend(); InputStream in = new FileInputStream(targetPath + "test-write.xmi")) {
			// java to json
			Migrator.fromXmi(in)
					.toMapper(mapper)
					.migrate();

			Log.info("Exporting to file... [{0}]", targetPath + "test-write.json");
			File targetFileJSON = new File(targetPath + "test-write.json");
			Migrator.fromMapper(mapper)
					.toJson(targetFileJSON)
					.migrate();
		}
	}

	public static void main(String[] args) throws IOException {
		testWrite("neoemf-io/src/test/resources/test-output/simplestClass/");

	}
}
