package fr.inria.atlanmod.neo4emf.change.impl;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.drivers.impl.Serializer;

/**
 * 
 * @author sunye
 */
abstract public class Entry {
	protected final INeo4emfObject eObject;

	public Entry(INeo4emfObject value) {
		eObject = value;
	}

	public abstract void process(Serializer serializer, boolean isTmp);
	
	@Deprecated
	public INeo4emfObject geteObject() {
		return eObject;
	}
}
