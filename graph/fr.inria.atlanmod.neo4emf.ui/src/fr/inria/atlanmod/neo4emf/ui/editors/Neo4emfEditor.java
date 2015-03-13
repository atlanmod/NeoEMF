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
package fr.inria.atlanmod.neo4emf.ui.editors;

import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.resource.Resource;

import fr.inria.atlanmod.neo4emf.impl.Neo4emfResource;

/**
 * 
 * @author abelgomez
 *
 */
public class Neo4emfEditor extends EcoreEditor {

	public static final String EDITOR_ID = "fr.inria.atlanmod.neo4emf.ui.Neo4emfEditor";
	
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
        for (Resource resource : editingDomain.getResourceSet().getResources()) {
        	if (resource instanceof Neo4emfResource) {
        		for (EObject eObject : resource.getContents()) {
        			Iterator<EObject> iterator = eObject.eAllContents();
        			while (iterator.hasNext()) {
						iterator.next();
					}
        		}
        	}
        }
		super.doSave(progressMonitor);
	}
	
	@Override
	public void dispose() {
        for (Resource resource : editingDomain.getResourceSet().getResources()) {
        	if (resource instanceof Neo4emfResource) {
        		((Neo4emfResource) resource).shutdown();
        	}
        }
		super.dispose();
	}
}
