package fr.inria.atlanmod.neo4emf;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;

public class NeoEObjectContainmentEList<E> extends EObjectContainmentEList<E> {
	
	private static final long serialVersionUID = 1L;
	
	public NeoEObjectContainmentEList(Class<?> dataClass, InternalEObject owner, int featureID) {
		super(dataClass,owner,featureID);
	}

}
