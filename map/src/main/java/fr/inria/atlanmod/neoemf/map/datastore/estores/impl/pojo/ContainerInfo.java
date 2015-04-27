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
package fr.inria.atlanmod.neoemf.map.datastore.estores.impl.pojo;

import java.io.Serializable;

import fr.inria.atlanmod.neoemf.core.Id;

public class ContainerInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Id containerId;
	
	public String containingFeatureName;
	
	public ContainerInfo(Id containerId, String containingFeatureName) {
		this.containerId = containerId;
		this.containingFeatureName = containingFeatureName;
	}
	
}
