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

package fr.inria.atlanmod.neoemf.datastore.estores.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link org.eclipse.emf.ecore.InternalEObject.EStore} implementation that uses synchronized collections to
 * store the data in memory, using {@link Collections#synchronizedMap(java.util.Map)}.
 */
public class SynchronizedTransientEStoreImpl extends TransientEStoreImpl {

	public SynchronizedTransientEStoreImpl() {
		super();
		singleMap = Collections.synchronizedMap(singleMap);
		manyMap = Collections.synchronizedMap(manyMap);
	}

	@Override
	protected List<Object> createValue() {
		return Collections.synchronizedList(new ArrayList<>());
	}
}
