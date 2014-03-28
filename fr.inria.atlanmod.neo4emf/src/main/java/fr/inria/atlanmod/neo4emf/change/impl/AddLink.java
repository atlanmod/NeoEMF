package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.impl.Serializer;

/**
 * 
 * @author sunye
 */
public class AddLink extends Entry {

	private final EReference eReference;
	private final Object oldValue;
	private final Object newValue;

	public AddLink(INeo4emfObject object, EReference eRef, Object oldV, Object newV) {
		super(object);
		eReference = eRef;
		oldValue = oldV;
		newValue = newV;
	}

	@Override
	public void process(Serializer serializer, boolean isTmp) {
		serializer.addNewLink(eObject, eReference, newValue,isTmp);
		super.release();
	}
	
	@Deprecated
	public EReference geteReference() {
		return eReference;
	}
}
