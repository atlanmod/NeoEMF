package fr.inria.atlanmod.kyanos.core;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

public interface KyanosInternalEObject extends KyanosEObject, InternalEObject {

	public abstract void kyanosSetId(String id);
	
	public Resource.Internal kyanosResource();

	public void kyanosSetResource(Resource.Internal resource);
	
	
}
