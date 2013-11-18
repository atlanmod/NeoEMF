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
package fr.inria.atlanmod.neo4emf.codegen;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import fr.inria.atlanmod.neo4emf.neo4jresolver.cp.container.INeo4jClasspathContainer;

public class GenReltypesGeneratorAdapter extends GenBaseGeneratorAdapter {
	/**
	 * 
	 */
	private static final String PREFERRED_NEO4J_VERSION = "1.9.4";
	protected static final int REL_TYPES_ID = 0;
	protected static final int MAP_REL_TYPES_ID = 1;

	protected static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS = {
			new JETEmitterDescriptor("model/ReltypesClass.javajet", "fr.inria.atlanmod.neo4emf.codegen.templates.model.ReltypesClass"),
			new JETEmitterDescriptor("model/MappingReltypesClass.javajet", "fr.inria.atlanmod.neo4emf.codegen.templates.model.MappingReltypesClass") };

	protected JETEmitterDescriptor[] getJETEmitterDescriptors() {
		return JET_EMITTER_DESCRIPTORS;
	}

	public GenReltypesGeneratorAdapter() {
		super();
	}

	public GenReltypesGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	@Override
	public boolean canGenerate(Object object, Object projectType) {
		// return MODEL_PROJECT_TYPE.equals(projectType)
		// ? ( hasReferences((GenPackage) object) ? super.canGenerate(object,
		// projectType) : false ): false;
		return MODEL_PROJECT_TYPE.equals(projectType) ? super.canGenerate(object, projectType) : false;
	}

	//	private boolean hasReferences(GenPackage object) {
	//		for (GenClass genClass : object.getOrderedGenClasses()) {
	//			for (GenFeature feat : genClass.getAllGenFeatures())
	//				if (feat.isReferenceType())
	//					return true;
	//		}
	//		return false;
	//	}

	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor) {
		GenPackage genPackage = (GenPackage) object;
		GenModel genModel = genPackage.getGenModel();

		// if (hasReferences(genPackage)){
		monitor.beginTask("", 2);
		monitor.subTask(message);
		ensureProjectExists(genModel.getModelDirectory(), genModel, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

		generateJava(genModel.getModelDirectory(),
				genModel.getModelPluginID() + ".reltypes", "Reltypes",
				getJETEmitter(getJETEmitterDescriptors(), REL_TYPES_ID),
				null,
				createMonitor(monitor, 1));

		generateJava(genModel.getModelDirectory(),
				genModel.getModelPluginID() + ".reltypes", "ReltypesMappings",
				getJETEmitter(getJETEmitterDescriptors(), MAP_REL_TYPES_ID),
				null,
				createMonitor(monitor, 1));
		// }
		
		fixClasspath(genModel, createMonitor(monitor, 1));
		return Diagnostic.OK_INSTANCE;
	}

	protected void fixClasspath(GenModel genModel, Monitor monitor) {
        try {
        	monitor.beginTask("Updating project's classpath", 1);
        	IPath neo4jPath = INeo4jClasspathContainer.ID.append(PREFERRED_NEO4J_VERSION);
        	IWorkspace workspace = ResourcesPlugin.getWorkspace();
        	IPath path = new Path(genModel.getModelDirectory());
        	IProject project = workspace.getRoot().getProject(path.segment(0));
        	IJavaProject javaProject = JavaCore.create(project);
			IClasspathEntry[] entries = javaProject.getRawClasspath();
			for (IClasspathEntry entry : entries) {
				if (entry.getPath().equals(neo4jPath)) { 
					return;
				}
			}
			IClasspathEntry[] newEntries = new IClasspathEntry[entries.length + 1];
			System.arraycopy(entries, 0, newEntries, 0, entries.length);
			newEntries[newEntries.length - 1] = JavaCore.newContainerEntry(neo4jPath);
			javaProject.setRawClasspath(newEntries, BasicMonitor.toIProgressMonitor(createMonitor(monitor, 1)));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			monitor.done();
		}
	}
	@Override
	protected void addBaseTemplatePathEntries(List<String> templatePath) {
		templatePath.add(Neo4emfGeneratorPlugin.INSTANCE.getBaseURL().toString() + "templates");
		super.addBaseTemplatePathEntries(templatePath);
	}
}