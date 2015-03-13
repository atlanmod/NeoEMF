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
package fr.inria.atlanmod.kyanos.core.impl;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.eclipse.emf.ecore.InternalEObject;

public class KyanosEObjectProxyHandlerImpl implements MethodInterceptor {

	protected InternalEObject internalEObject;
	
	public KyanosEObjectProxyHandlerImpl(InternalEObject internalEObject) {
		super();
		this.internalEObject = internalEObject;
	}

	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		throw new UnsupportedOperationException();
	}
}
