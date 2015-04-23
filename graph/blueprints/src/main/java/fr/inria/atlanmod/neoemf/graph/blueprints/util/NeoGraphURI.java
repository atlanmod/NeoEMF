package fr.inria.atlanmod.neoemf.graph.blueprints.util;

import java.io.File;
import java.text.MessageFormat;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;

import fr.inria.atlanmod.neoemf.util.NeoURI;

public class NeoGraphURI extends NeoURI {
	
	public static final String NEO_GRAPH_SCHEME = "neo-graph";
	
	protected NeoGraphURI(int hashCode, URI internalURI) {
		super(hashCode,internalURI);
	}
	
	public static URI createNeoGraphURI(URI uri) {
		if(NeoURI.FILE_SCHEME.equals(uri.scheme())) {
			return createNeoGraphURI(FileUtils.getFile(uri.toFileString()));
		}
		else if(NEO_GRAPH_SCHEME.equals(uri.scheme())) {
			return NeoURI.createNeoURI(uri);
		}
		else {
			throw new IllegalArgumentException(MessageFormat.format("Can not create NeoGraphURI from the URI scheme {0}",uri.scheme()));
		}
	}
	
	public static URI createNeoGraphURI(File file) {
		return NeoURI.createNeoURI(file, NEO_GRAPH_SCHEME);
	}
	
}
