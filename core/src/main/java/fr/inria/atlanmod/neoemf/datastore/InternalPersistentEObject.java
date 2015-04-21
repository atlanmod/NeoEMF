package fr.inria.atlanmod.neoemf.datastore;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

public interface InternalPersistentEObject extends PersistentEObject,
		InternalEObject {

	public void id(Id id);
	
	public Resource.Internal resource();
	
	public void resource(Resource.Internal resource);
	
}
