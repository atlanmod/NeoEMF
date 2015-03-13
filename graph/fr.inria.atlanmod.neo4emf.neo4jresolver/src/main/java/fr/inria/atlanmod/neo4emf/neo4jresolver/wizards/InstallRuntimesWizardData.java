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
package fr.inria.atlanmod.neo4emf.neo4jresolver.wizards;

import java.util.ArrayList;
import java.util.List;

import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.AbstractNeo4jRuntimeInstaller;

/**
 * @author abelgomez
 *
 */
public class InstallRuntimesWizardData {

	private List<AbstractNeo4jRuntimeInstaller> installersToInstall = new ArrayList<AbstractNeo4jRuntimeInstaller>();
	
	/**
	 * @return the installersToInstall
	 */
	public List<AbstractNeo4jRuntimeInstaller> getInstallersToInstall() {
		return installersToInstall;
	}
}
