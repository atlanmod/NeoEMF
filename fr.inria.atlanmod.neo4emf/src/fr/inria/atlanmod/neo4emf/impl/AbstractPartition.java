package fr.inria.atlanmod.neo4emf.impl;

import java.util.List;


import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfPartition;

public abstract class AbstractPartition implements INeo4emfPartition {


	@Override
	public abstract boolean unloadable();

	public abstract boolean containsKey(long nodeId);
	
	public abstract INeo4emfObject get(long nodeId);

	public abstract List<INeo4emfObject> flattened();

	public abstract void remove(long nodeId);

}
