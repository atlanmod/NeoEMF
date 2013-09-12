package fr.inria.atlanmod.neo4emf;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EcoreFactory;

import fr.inria.atlanmod.neo4emf.impl.NeoFactory;

public interface INeoFactory extends EFactory {
	
	 INeoFactory eINSTANCE =  fr.inria.atlanmod.neo4emf.impl.NeoFactory.init();

}
