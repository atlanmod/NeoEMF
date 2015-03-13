package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.impl.Serializer;

/**
 * 
 * @author sunye
 */
public class UnidirectionalRemoveLink extends RemoveLink {

	public UnidirectionalRemoveLink(INeo4emfObject from, EReference eRef, INeo4emfObject to) {
		super(from,eRef,to);
		assert eRef.getEOpposite() == null : "Try to create a UnidirectionalRemoveLink with a bidirectional EReference";
	}

	@Override
	public void process(Serializer serializer, boolean isTmp) {
		serializer.removeExistingLink(eObject, eReference, referencedEObject, isTmp);
		super.release();
	}

}
