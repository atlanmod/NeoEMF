package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.impl.Serializer;

public class BidirectionalAddLink extends AddLink {

	private final boolean eObjectWasSaved;
	private final boolean referencedEObjectWasSaved;
	
	public BidirectionalAddLink(INeo4emfObject from, EReference eRef, INeo4emfObject to) {
		super(from,eRef,to);
		assert eRef.getEOpposite() != null : "Try to create a BidirectionalAddLink with a uniderctional EReference";
		eObjectWasSaved = from.isLoaded();
		referencedEObjectWasSaved = to.isLoaded();
	}

	@Override
	public void process(Serializer serializer, boolean isTmp) {
		//if(eObjectWasSaved && referencedEObjectWasSaved) {
			serializer.addNewLink(eObject, eReference, referencedEObject, isTmp);
		//}
		//if(referencedEObjectWasSaved && eObjectWasSaved) {
			serializer.addNewLink(referencedEObject, eReference.getEOpposite(), eObject, isTmp);
		//}
		super.release();
	}

}
