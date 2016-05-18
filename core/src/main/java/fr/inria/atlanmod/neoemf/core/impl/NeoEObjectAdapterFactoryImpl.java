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

package fr.inria.atlanmod.neoemf.core.impl;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;

import net.sf.cglib.proxy.Enhancer;

import org.apache.commons.lang3.ClassUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class NeoEObjectAdapterFactoryImpl {

	/**
	 * {@link WeakHashMap} that stores the EObjects that have been already
	 * adapted to avoid duplication of {@link PersistentEObject}s. We use a
	 * {@link WeakHashMap} since the adaptor is no longer needed when the
	 * original {@link EObject} has been garbage collected
	 */
	protected static final Map<InternalEObject, InternalPersistentEObject> ADAPTED_OBJECTS = new WeakHashMap<>();

	public static <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		T returnValue = null;
		if (adapterType.isInstance(adaptableObject)) {
			returnValue = adapterType.cast(adaptableObject);
		} else if (adapterType.isAssignableFrom(InternalPersistentEObject.class) 
				&& adaptableObject instanceof InternalEObject) {

			EObject eObjectAdapter = ADAPTED_OBJECTS.get(adaptableObject);
			if (eObjectAdapter != null && adapterType.isAssignableFrom(eObjectAdapter.getClass())) {
				returnValue = adapterType.cast(eObjectAdapter);
			} else {
				// Compute the interfaces that the proxy has to implement
				// These are the current interfaces + PersistentEObject
				List<Class<?>> interfaces = new ArrayList<>(ClassUtils.getAllInterfaces(adaptableObject.getClass()));
				interfaces.add(InternalPersistentEObject.class);
				// Create the proxy
				Enhancer enhancer = new Enhancer();
				/*
				 * Use the ClassLoader of the type, otherwise it will cause OSGI troubles (like project trying to
				 * create an InternalPersistentEObject while it does not have a dependency to NeoEMF core)
				 */
				enhancer.setClassLoader(adapterType.getClassLoader());
				enhancer.setSuperclass(adaptableObject.getClass());
				enhancer.setInterfaces(interfaces.toArray(new Class[interfaces.size()]));
				enhancer.setCallback(new NeoEObjectProxyHandlerImpl((InternalEObject) adaptableObject));
				returnValue = adapterType.cast(enhancer.create());
				ADAPTED_OBJECTS.put((InternalEObject) adaptableObject, (InternalPersistentEObject)  returnValue);
			}
		}
		return returnValue;
	}

}
