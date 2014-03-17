package fr.inria.atlanmod.neo4emf.change;


import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.change.impl.ChangeLogFactory;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;

public interface IChangeLogFactory {
	
	IChangeLogFactory eINSTANCE = ChangeLogFactory.init();
	
	public IChangeLog<Entry> createChangeLog(INeo4emfResource resource);
		
}
