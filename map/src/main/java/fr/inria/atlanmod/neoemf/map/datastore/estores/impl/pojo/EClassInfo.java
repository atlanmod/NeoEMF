/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import java.io.Serializable;

/**
 * Memento class for storing metaclass/EClass information.
 */
public class EClassInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nsURI;
	
	private String className;
	
	public EClassInfo(PersistentEObject object) {
		this.nsURI = object.eClass().getEPackage().getNsURI();
		this.className = object.eClass().getName();
	}

	/**
	 * Retrieves the EClass corresponding to this memento.
	 * @return
	 */
	public EClass eClass() {
		return (EClass) EPackage.Registry.INSTANCE.getEPackage(nsURI).getEClassifier(className);
	}

}

