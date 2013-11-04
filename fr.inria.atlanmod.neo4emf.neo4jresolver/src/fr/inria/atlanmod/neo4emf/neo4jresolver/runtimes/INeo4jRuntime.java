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
package fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes;

import java.io.Serializable;

import org.eclipse.core.runtime.IPath;

/**
 * @author abelgomez
 *
 */
public interface INeo4jRuntime extends Serializable {

	/**
	 * @return the version
	 */
	public String getVersion();
	
	/**
	 * @return the runtime ID
	 */
	public String getId();
	
	/**
	 * @return the directory path
	 */
	public IPath getPath();
	
}
