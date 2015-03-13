package fr.inria.atlanmod.neo4emf.change.impl;

import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

public abstract class RemoveLink extends Entry {

	protected final EReference eReference;
	protected final INeo4emfObject referencedEObject;
	
	public RemoveLink(INeo4emfObject from, EReference eRef, INeo4emfObject to) {
		super(from);
		eReference = eRef;
		referencedEObject = to;
	}

}
