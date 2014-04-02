package fr.inria.atlanmod.kyanos.core.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.kyanos.core.KyanosResourceFactory;

public class KyanosResourceFactoryImpl implements KyanosResourceFactory {

	@Override
	public Resource createResource(URI uri) {
		return new KyanosResourceImpl(uri);
	}

}
