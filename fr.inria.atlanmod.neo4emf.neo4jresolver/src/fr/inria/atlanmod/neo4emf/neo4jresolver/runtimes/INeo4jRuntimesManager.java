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

import java.io.IOException;

/**
 * @author abelgomez
 *
 */
public interface INeo4jRuntimesManager {

	public abstract AbstractNeo4jRuntimeInstaller getInstaller(String id);

	public abstract INeo4jRuntime getRuntime(String id);

	public abstract boolean checkRuntimes(boolean blockOnOpen);

	public abstract void launchInstallRuntimesWizard();
	
	public abstract void uninstall(String id) throws IOException;


}