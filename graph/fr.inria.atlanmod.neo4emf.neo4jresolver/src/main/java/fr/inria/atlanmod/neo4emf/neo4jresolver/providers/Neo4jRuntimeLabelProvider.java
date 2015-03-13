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
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime;

/**
 * @author abelgomez
 *
 */
public final class Neo4jRuntimeLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		StringBuilder builder = new StringBuilder();
		if (element instanceof INeo4jRuntime) {
			INeo4jRuntime runtime = (INeo4jRuntime) element;
			builder.append("Neo4J runtime v.");
			builder.append(runtime.getVersion());
		} else {
			builder.append(super.getText(element));
		}
		return builder.toString();
	}
	@Override
	public Image getImage(Object element) {
		return Neo4jResolverPlugin.getDefault().getImageRegistry().get(Neo4jResolverPlugin.OBJ16_LIBRARY_OBJ);
	}
}