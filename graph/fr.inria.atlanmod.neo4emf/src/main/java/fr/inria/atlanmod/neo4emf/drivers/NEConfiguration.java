/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Sunye
 */
package fr.inria.atlanmod.neo4emf.drivers;

import java.io.File;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

import fr.inria.atlanmod.neo4emf.PersistentPackage;

public class NEConfiguration {
	
	private final PersistentPackage ePackage;
	
	private final URI uri;
	
	private final Map<String,String> options;
	
	private final File path;
	
	
	public NEConfiguration(PersistentPackage ep, URI uri, Map<String,String> map) {
		this.ePackage = ep;
		this.uri = uri;
		this.options = map;
		
		String name = uri.isHierarchical() ? uri.path() : uri.opaquePart();
		path = new File(name);
	}
	
	public PersistentPackage ePackage() {
		return ePackage;
	}
	
	public URI uri() {
		return uri;
	}
	
	public Map<String,String> options() {
		return options;
	}
	
	public File path() {
		return path;
	}
}
