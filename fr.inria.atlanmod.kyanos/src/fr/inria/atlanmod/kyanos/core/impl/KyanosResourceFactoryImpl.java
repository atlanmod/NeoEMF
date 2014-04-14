package fr.inria.atlanmod.kyanos.core.impl;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.kyanos.core.KyanosResourceFactory;
import fr.inria.atlanmod.kyanos.util.KyanosURI;

public class KyanosResourceFactoryImpl implements KyanosResourceFactory {

	@Override
	public Resource createResource(URI uri) {
		if (StringUtils.equals(KyanosURI.KYANOS_SCHEME, uri.scheme())) {
			return new KyanosResourceImpl(uri);
		} else if (StringUtils.equals(KyanosURI.KYANOS_MAP_SCHEME, uri.scheme())) {
			return new KyanosMapResourceImpl(uri);
		} else {
			return null;
		}
	}

}
