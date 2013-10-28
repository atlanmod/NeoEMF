/**
 * Copyright (c) 2008, 2013 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 *    Abel Gómez - Adapted to Neo4EMF models
 */
package fr.inria.atlanmod.neo4emf.ui.migrator;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * @author abelgomez
 */
public abstract class Neo4emfMigratorUtil {

	private static final String ROOT_EXTENDS_CLASS = "fr.inria.atlanmod.neo4emf.impl.Neo4emfObject";

	private static final String ROOT_EXTENDS_INTERFACE = "fr.inria.atlanmod.neo4emf.INeo4emfObject";

	private static final Boolean DYNAMIC_TEMPLATES = true;

	private static final String TEMPLATE_DIRECTORY = "platform:/plugin/fr.inria.atlanmod.neo4emf.codegen/templates";

	public static final String PLUGIN_VARIABLE = "NEO4EMF=fr.inria.atlanmod.neo4emf";
	
	public static final String TEMPLATE_PLUGIN_VARIABLE = "NEO4EMF_GENERATOR=fr.inria.atlanmod.neo4emf.codegen";

	private Neo4emfMigratorUtil() {
	}

	public static String adjustGenModel(GenModel genModel) {
		StringBuilder builder = new StringBuilder();

		if (!ROOT_EXTENDS_CLASS.equals(genModel.getRootExtendsClass())) {
			genModel.setRootExtendsClass(ROOT_EXTENDS_CLASS);
			builder.append("Set Root Extends Class = ");
			builder.append(ROOT_EXTENDS_CLASS);
			builder.append("\n");
		}

		if (!ROOT_EXTENDS_INTERFACE.equals(genModel.getRootExtendsInterface())) {
			genModel.setRootExtendsInterface(ROOT_EXTENDS_INTERFACE);
			builder.append("Set Root Extends Interface = ");
			builder.append(ROOT_EXTENDS_INTERFACE);
			builder.append("\n");
		}

		if (!DYNAMIC_TEMPLATES.equals(genModel.isDynamicTemplates())) {
			genModel.setDynamicTemplates(DYNAMIC_TEMPLATES);
			builder.append("Set Dynamic Templates = ");
			builder.append(DYNAMIC_TEMPLATES);
			builder.append("\n");
		}
		
		if (!TEMPLATE_DIRECTORY.equals(genModel.getTemplateDirectory())) {
			genModel.setTemplateDirectory(TEMPLATE_DIRECTORY);;
			builder.append("Set Template Directory = ");
			builder.append(TEMPLATE_DIRECTORY);
			builder.append("\n");
		}
		
		EList<String> pluginVariables = genModel.getModelPluginVariables();
		if (!pluginVariables.contains(PLUGIN_VARIABLE)) {
			pluginVariables.add(PLUGIN_VARIABLE);
			builder.append("Added Model Plugin Variables = ");
			builder.append(PLUGIN_VARIABLE);
			builder.append("\n");
		}

		EList<String> templatePluginVariables = genModel.getTemplatePluginVariables();
		if (!templatePluginVariables.contains(TEMPLATE_PLUGIN_VARIABLE)) {
			templatePluginVariables.add(TEMPLATE_PLUGIN_VARIABLE);
			builder.append("Added Template Plugin Variables = ");
			builder.append(TEMPLATE_PLUGIN_VARIABLE);
			builder.append("\n");
		}

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFolder modelFolder = root.getFolder(new Path(genModel.getModelDirectory()));
		IProject modelProject = modelFolder.getProject();
		if (!modelProject.exists()) {
			try {
				modelProject.create(new NullProgressMonitor());
				builder.append("Created target model project" + "\n"); //$NON-NLS-2$
			} catch (CoreException ex) {
				throw new WrappedException(ex);
			}
		}

		if (!modelProject.isOpen()) {
			try {
				modelProject.open(new NullProgressMonitor());
				builder.append("Opened target model project" + "\n"); //$NON-NLS-2$
			} catch (CoreException ex) {
				throw new WrappedException(ex);
			}
		}

		return builder.length() == 0 ? null : builder.toString();
	}
}
