package fr.inria.atlanmod.kyanos.core;

import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.kyanos.core.impl.KyanosResourceFactoryImpl;

public interface KyanosResourceFactory extends Resource.Factory {

	public static KyanosResourceFactory eINSTANCE = new KyanosResourceFactoryImpl();
	
}
