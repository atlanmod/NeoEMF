package fr.inria.atlanmod.neo4emf.change;


import org.eclipse.emf.ecore.EReference;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.change.impl.ChangeLogFactory;
import fr.inria.atlanmod.neo4emf.change.impl.Entry;

public interface IChangeLogFactory {
	
	IChangeLogFactory eINSTANCE = ChangeLogFactory.init();
	
	public IChangeLog<Entry> createChangeLog(INeo4emfResource resource);
	
	/**
	 * Return an Unidirectional or Bidirectional AddLink entry
	 * according to eRef
	 * @param from
	 * @param eRef
	 * @param to
	 * @return
	 */
	public Entry createAddLink(INeo4emfObject from, EReference eRef, INeo4emfObject to);
	
	/**
	 * Return an Unidirectional or Bidirectional RemoveLink entry
	 * according to eRef
	 * @param from
	 * @param eRef
	 * @param to
	 * @return
	 */
	public Entry createRemoveLink(INeo4emfObject from, EReference eRef, INeo4emfObject to);
		
}
