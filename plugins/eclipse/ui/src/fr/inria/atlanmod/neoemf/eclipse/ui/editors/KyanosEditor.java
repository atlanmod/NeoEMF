/*******************************************************************************
 * Copyright (c) 2014 Abel Gómez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gómez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.neoemf.eclipse.ui.editors;

import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * 
 * @author abelgomez
 *
 */
public class KyanosEditor extends EcoreEditor {

	public static final String EDITOR_ID = KyanosEditor.class.getName();
	
	@Override
	public void dispose() {
        for (Resource resource : editingDomain.getResourceSet().getResources()) {
        	resource.unload();
        }
		super.dispose();
	}
}
