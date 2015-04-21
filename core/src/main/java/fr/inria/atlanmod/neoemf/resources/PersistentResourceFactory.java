package fr.inria.atlanmod.neoemf.resources;


import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceFactoryImpl;


/**
 * Created by sunye on 17/03/15.
 */
public interface PersistentResourceFactory extends Resource.Factory {

	public static PersistentResourceFactory eINSTANCE = new PersistentResourceFactoryImpl();

}
