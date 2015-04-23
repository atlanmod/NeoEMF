package fr.inria.atlanmod.neoemf.datastore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;

public class PersistenceBackendFactoryRegistryTest {

	AbstractPersistenceBackendFactory persistenceBackendFactory1 = Mockito.mock(AbstractPersistenceBackendFactory.class);
	AbstractPersistenceBackendFactory persistenceBackendFactory2 = Mockito.mock(AbstractPersistenceBackendFactory.class);
	
	@Before
	public void setUp() {
		PersistenceBackendFactoryRegistry.getFactories().clear();
	}
	
	@Test
	public void testSingleAdd() {
		PersistenceBackendFactoryRegistry.getFactories().put("mock1", persistenceBackendFactory1);
		assert PersistenceBackendFactoryRegistry.getFactories().size() == 1;
		AbstractPersistenceBackendFactory registeredFactory = PersistenceBackendFactoryRegistry.getFactoryProvider("mock1");
		assert registeredFactory != null;
		assert registeredFactory == persistenceBackendFactory1;
	}
	
	@Test
	public void testMulltipleAdd() {
		PersistenceBackendFactoryRegistry.getFactories().put("mock1", persistenceBackendFactory1);
		PersistenceBackendFactoryRegistry.getFactories().put("mock2", persistenceBackendFactory2);
		assert PersistenceBackendFactoryRegistry.getFactories().size() == 2;
		AbstractPersistenceBackendFactory registeredFactory1 = PersistenceBackendFactoryRegistry.getFactoryProvider("mock1");
		AbstractPersistenceBackendFactory registeredFactory2 = PersistenceBackendFactoryRegistry.getFactoryProvider("mock2");
		assert registeredFactory1 != null;
		assert registeredFactory1 == persistenceBackendFactory1;
		assert registeredFactory2 != null;
		assert registeredFactory2 == persistenceBackendFactory2;
	}

}
