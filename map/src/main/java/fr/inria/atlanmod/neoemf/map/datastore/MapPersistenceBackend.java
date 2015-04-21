package fr.inria.atlanmod.neoemf.map.datastore;

import java.util.Map;

import org.mapdb.DB;
import org.mapdb.Engine;

import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;

public class MapPersistenceBackend extends DB implements PersistenceBackend {
	
	public MapPersistenceBackend(Engine mapEngine) {
		super(mapEngine);
	}
	
	@Override
	public void start(Map<?, ?> options) throws InvalidDataStoreException {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}
	
}
