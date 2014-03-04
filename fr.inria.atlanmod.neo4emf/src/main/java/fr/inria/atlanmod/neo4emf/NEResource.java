package fr.inria.atlanmod.neo4emf;

import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neo4emf.drivers.NEConnection;

public interface NEResource extends Resource {
	
	public void setConnection(NEConnection nec);

}
