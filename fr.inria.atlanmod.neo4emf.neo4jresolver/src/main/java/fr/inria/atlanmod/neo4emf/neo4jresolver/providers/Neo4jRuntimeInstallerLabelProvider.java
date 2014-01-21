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
package fr.inria.atlanmod.neo4emf.neo4jresolver.providers;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import fr.inria.atlanmod.neo4emf.neo4jresolver.Neo4jResolverPlugin;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.AbstractNeo4jRuntimeInstaller;

/**
 * @author abelgomez
 *
 */
public class Neo4jRuntimeInstallerLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		AbstractNeo4jRuntimeInstaller installer = (AbstractNeo4jRuntimeInstaller) element;
		return installer.getName();
	}
	@Override
	public Image getImage(Object element) {
		return Neo4jResolverPlugin.getDefault().getImageRegistry().get(Neo4jResolverPlugin.OBJ16_LIBRARY_OBJ);
	}
}