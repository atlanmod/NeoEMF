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


/**
 * @author abelgomez
 *
 */
public interface INeo4jRuntimesManager {

//	public abstract AbstractNeo4jRuntimeInstaller getInstaller(String id);

	/**
	 * Returns the runtime with the given id
	 * @param id
	 * @return
	 * The {@link INeo4jRuntime} or <code>null</code> if not found
	 */
	public abstract INeo4jRuntime getRuntime(String id);

	/**
	 * Checks the installed runtimes and launches the installation wizard if no
	 * runtimes are found. Implementors should consider threading issues (i.e.
	 * implement the method as <code>synchronized</code>).
	 * 
	 * @return
	 */
	public abstract void checkRuntimes();

//	public abstract void launchInstallRuntimesWizard();
	
//	public abstract void uninstall(String id) throws IOException;


}