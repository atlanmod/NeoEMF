package fr.inria.atlanmod.neo4emf.change.impl;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.impl.Serializer;

/**
 * 
 * @author sunye
 */
public class DeleteObject extends Entry {

	public DeleteObject(INeo4emfObject value) {
		super(value);
	}

	@Override
	public void process(Serializer serializer, boolean isTmp) {
		serializer.deleteExistingObject(eObject, isTmp);
		super.release();
	}

}
