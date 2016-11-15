/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.core;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.lang3.ClassUtils;
import org.eclipse.emf.ecore.InternalEObject;

import java.lang.reflect.Method;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A factory able to adapt an {@link Object object} in a specific {@link Class type}.
 */
class PersistentEObjectAdapter {

    /**
     * {@link Cache} that stores the {@link InternalEObject objects} that have been already adapted to avoid
     * duplication of {@link PersistentEObject persistent objects}s.
     * <p/>
     * We use a soft value cache since the adaptor is no longer needed when the original {@link InternalEObject} has
     * been garbage collected.
     */
    private static final Cache<InternalEObject, PersistentEObject> ADAPTED_OBJECTS_CACHE =
            Caffeine.newBuilder().weakKeys().build();

    private PersistentEObjectAdapter() {
    }

    /**
     * Returns the given {@code object} adapted in a specific {@code type}.
     *
     * @param adaptableObject the object to adapt
     * @param adapterType     the class in which the object must be adapted
     *
     * @return an adapted object in the given {@code type}, or {@code null} if the {@code object} cannot be assigned as
     *         a {@code type}
     *
     * @throws NullPointerException if the {@code type} is {@code null}
     */
    public static <T extends PersistentEObject> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (isNull(adaptableObject)) {
            return null;
        }
        checkNotNull(adapterType);

        Object adapter = null;
        if (adapterType.isInstance(adaptableObject)) {
            adapter = adaptableObject;
        }
        else if (adaptableObject instanceof InternalEObject) {
            adapter = ADAPTED_OBJECTS_CACHE.getIfPresent(adaptableObject);
            if (isNull(adapter) || !adapterType.isAssignableFrom(adapter.getClass())) {
                adapter = createAdapter(adaptableObject, adapterType);
                ADAPTED_OBJECTS_CACHE.put((InternalEObject) adaptableObject, (PersistentEObject) adapter);
            }
        }

        if (isNull(adapter)) {
            NeoLogger.warn("Unable to create a {0} adapter for this object of type {1}", adapterType.getSimpleName(), adaptableObject.getClass().getSimpleName());
        }
        return adapterType.cast(adapter);
    }

    /**
     * Returns the given {@code object} as a {@link PersistentEObject}.
     *
     * @param adaptableObject the object to adapt
     *
     * @return an adapted object as a {@link PersistentEObject}, or {@code null} if the {@code object} cannot be
     *         assigned as a {@link PersistentEObject}
     *
     * @see #getAdapter(Object, Class)
     */
    public static PersistentEObject getAdapter(Object adaptableObject) {
        return getAdapter(adaptableObject, PersistentEObject.class);
    }

    /**
     * Create an adapter for the given {@code object} in a specific {@code type}.
     *
     * @param adaptableObject the object to adapt
     * @param adapterType     the class in which the object must be adapted
     *
     * @return an adapted object in the given {@code type}
     */
    private static Object createAdapter(Object adaptableObject, Class<?> adapterType) {
        /*
         * Compute the interfaces that the proxy has to implement
		 * These are the current interfaces + PersistentEObject
		 */
        List<Class<?>> interfaces = ClassUtils.getAllInterfaces(adaptableObject.getClass());
        interfaces.add(PersistentEObject.class);

        // Create the proxy
        Enhancer proxy = new Enhancer();

		/*
         * Use the ClassLoader of the type, otherwise it will cause OSGI troubles (like project trying to
		 * create an PersistentEObject while it does not have a dependency to NeoEMF core)
		 */
        proxy.setClassLoader(adapterType.getClassLoader());
        proxy.setSuperclass(adaptableObject.getClass());
        proxy.setInterfaces(interfaces.toArray(new Class[interfaces.size()]));
        proxy.setCallback(new PersistentEObjectProxyHandler());
        return proxy.create();
    }

    private static class PersistentEObjectProxyHandler implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {
            throw new UnsupportedOperationException();
        }
    }
}
