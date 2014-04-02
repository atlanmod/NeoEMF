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