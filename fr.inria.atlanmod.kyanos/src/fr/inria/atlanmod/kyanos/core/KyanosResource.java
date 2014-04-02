package fr.inria.atlanmod.kyanos.core;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

public interface KyanosResource extends Resource, Resource.Internal {

	public final static String OPTIONS_GRAPH_TYPE = "blueprints.graph";
	public final static String OPTIONS_GRAPH_TYPE_DEFAULT = "com.tinkerpop.blueprints.impls.tg.TinkerGraph";

	
	public abstract InternalEObject.EStore eStore();
	
}
