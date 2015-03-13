package fr.inria.atlanmod.neo4emf;

import org.eclipse.emf.ecore.EFactory;

public interface INeoFactory extends EFactory {
	
	 INeoFactory eINSTANCE =  fr.inria.atlanmod.neo4emf.impl.NeoFactory.init();

}
