/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime;

/**
 * @author abelgomez
 *
 */
public class Neo4jRuntime implements INeo4jRuntime {
	
	private static final long serialVersionUID = 1L;
	private String version;
	private String id;
	private IPath path;
	
	/**
	 * Creates a new Runtime directory description for the given {@link Path}.
	 * If <code>path</code> denotes an existing directory with contents they are
	 * preserved.
	 * 
	 * @param version
	 * @param path
	 * 
	 */

	public Neo4jRuntime(String version, String id, IPath path)  {
		this.version = version;
		this.id = id;
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime#getVersion()
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/* (non-Javadoc)
	 * @see fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime#getId()
	 */
	@Override
	public String getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime#getPath()
	 */
	@Override
	public IPath getPath() {
		return path;
	}
}
