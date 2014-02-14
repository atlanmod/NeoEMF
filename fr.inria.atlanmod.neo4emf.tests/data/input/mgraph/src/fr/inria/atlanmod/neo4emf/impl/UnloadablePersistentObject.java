package fr.inria.atlanmod.neo4emf.impl;

import java.lang.ref.ReferenceQueue;

public class UnloadablePersistentObject extends Neo4emfObject {
	protected ReferenceQueue<Object> garbagedData;
}
