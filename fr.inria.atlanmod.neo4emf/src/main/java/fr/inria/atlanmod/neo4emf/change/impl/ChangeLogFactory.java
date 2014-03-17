package fr.inria.atlanmod.neo4emf.change.impl;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.change.IChangeLog;
import fr.inria.atlanmod.neo4emf.change.IChangeLogFactory;

public class ChangeLogFactory implements IChangeLogFactory {

	public static int CHANGELOG_SIZE = 10;
	
	public static void setChangeLogSize(int size) {
		CHANGELOG_SIZE = size;
	}
	
	public static IChangeLogFactory init() {
		if (eINSTANCE == null) {
			return new ChangeLogFactory();
		} else {
			return eINSTANCE;
		}
	}

	@Override
	public IChangeLog<Entry> createChangeLog(INeo4emfResource resource) {
		return new ChangeLog(CHANGELOG_SIZE,resource);
	}
}
