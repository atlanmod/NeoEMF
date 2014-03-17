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
		eObject.setMemoryLock();
	}

	public abstract void process(Serializer serializer, boolean isTmp);
	
	protected final void release() {
		eObject.unsetMemoryLock();
	}
	
	@Deprecated
	public INeo4emfObject geteObject() {
		return eObject;
	}
}
