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

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.eclipse.emf.ecore.InternalEObject;

import java.lang.reflect.Method;

public class NeoEObjectProxyHandlerImpl implements MethodInterceptor {

	private InternalEObject internalEObject;

	public NeoEObjectProxyHandlerImpl(InternalEObject internalEObject) {
		super();
		this.internalEObject = internalEObject;
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {
		throw new UnsupportedOperationException();
	}
}
