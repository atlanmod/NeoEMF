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
