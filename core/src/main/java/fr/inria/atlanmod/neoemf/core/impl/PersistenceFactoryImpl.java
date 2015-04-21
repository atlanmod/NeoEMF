package fr.inria.atlanmod.neoemf.core.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

import fr.inria.atlanmod.neoemf.core.PersistenceFactory;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;

public class PersistenceFactoryImpl extends EFactoryImpl implements PersistenceFactory {
	
	@Override
	public InternalPersistentEObject create(EClass eClass) {
		PersistentEObjectImpl eObject = new PersistentEObjectImpl();
		eObject.eSetClass(eClass);
		return eObject;
	}
	
}
