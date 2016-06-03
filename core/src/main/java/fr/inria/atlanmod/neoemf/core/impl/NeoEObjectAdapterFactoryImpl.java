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

package fr.inria.atlanmod.neoemf.core.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;

import net.sf.cglib.proxy.Enhancer;

import org.apache.commons.lang3.ClassUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.List;

public class NeoEObjectAdapterFactoryImpl {

	private NeoEObjectAdapterFactoryImpl() {}

	/**
	 * {@link Cache} that stores the EObjects that have been already
	 * adapted to avoid duplication of {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s.
	 * <p/>
	 * We use a soft value cache since the adaptor is no longer needed when the
	 * original {@link EObject} has been garbage collected.
	 */
	private static final Cache<InternalEObject, InternalPersistentEObject> ADAPTED_OBJECTS_CACHE = CacheBuilder.newBuilder().weakKeys().build();

	public static <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		Object adapter = null;
		if (adapterType.isInstance(adaptableObject)) {
			adapter = adaptableObject;
		} else if (adapterType.isAssignableFrom(InternalPersistentEObject.class)
				&& adaptableObject instanceof InternalEObject) {

			EObject eObjectAdapter = ADAPTED_OBJECTS_CACHE.getIfPresent(adaptableObject);
			if (eObjectAdapter != null && adapterType.isAssignableFrom(eObjectAdapter.getClass())) {
				adapter = eObjectAdapter;
			} else {
				adapter = createAdapter(adaptableObject, adapterType);
				ADAPTED_OBJECTS_CACHE.put((InternalEObject) adaptableObject, (InternalPersistentEObject)  adapter);
			}
		}
		return adapterType.cast(adapter);
	}

	private static Object createAdapter(Object adaptableObject, Class<?> adapterType) {
		/*
		 * Compute the interfaces that the proxy has to implement
		 * These are the current interfaces + PersistentEObject
		 */
		List<Class<?>> interfaces = ClassUtils.getAllInterfaces(adaptableObject.getClass());
		interfaces.add(InternalPersistentEObject.class);

		// Create the proxy
		Enhancer proxy = new Enhancer();

		/*
		 * Use the ClassLoader of the type, otherwise it will cause OSGI troubles (like project trying to
		 * create an InternalPersistentEObject while it does not have a dependency to NeoEMF core)
		 */
		proxy.setClassLoader(adapterType.getClassLoader());
		proxy.setSuperclass(adaptableObject.getClass());
		proxy.setInterfaces(interfaces.toArray(new Class[interfaces.size()]));
		proxy.setCallback(new NeoEObjectProxyHandlerImpl((InternalEObject) adaptableObject));
		return proxy.create();
	}
}
