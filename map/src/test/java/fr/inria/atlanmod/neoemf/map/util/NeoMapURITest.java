package fr.inria.atlanmod.neoemf.map.util;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackendFactory;

public class NeoMapURITest {

	private AbstractPersistenceBackendFactory persistenceBackendFactory = new MapPersistenceBackendFactory();
	private File testFile = null;
	
	@Before
	public void setUp() {
		PersistenceBackendFactoryRegistry.getFactories().clear();
		PersistenceBackendFactoryRegistry.getFactories().put(NeoMapURI.NEO_MAP_SCHEME, persistenceBackendFactory);
		testFile = new File("src/test/resource/neoMapURITestFile");
	}
	
	@After
	public void tearDown() {
		if(testFile != null) {
			testFile.delete();
			testFile = null;
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateNeoGraphURIFromStandardURIInvalidScheme() {
		URI invalidURI = URI.createURI("invalid:/test");
		NeoMapURI.createNeoMapURI(invalidURI);
	}
	
	@Test
	public void testCreateNeoGraphURIFromStandardURIValidScheme() {
		URI validURI = URI.createURI(NeoMapURI.NEO_MAP_SCHEME+":/test");
		URI neoURI = NeoMapURI.createNeoMapURI(validURI);
		assert neoURI.scheme().equals(NeoMapURI.NEO_MAP_SCHEME);
	}
	
	@Test
	public void testCreateNeoGraphURIFromFileURI() {
		URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
		URI neoURI = NeoMapURI.createNeoMapURI(fileURI);
		assert neoURI.scheme().equals(NeoMapURI.NEO_MAP_SCHEME);
	}
	
	@Test
	public void testCreateNeoURIFromFile() {
		URI neoURI = NeoMapURI.createNeoMapURI(testFile);
		assert neoURI.scheme().equals(NeoMapURI.NEO_MAP_SCHEME);
	}
	
}
