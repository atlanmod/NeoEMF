/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.neoemf.util.cache.Cache;
import fr.inria.atlanmod.neoemf.util.cache.CacheBuilder;
import fr.inria.atlanmod.neoemf.util.log.Log;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.lang3.ClassUtils;
import org.eclipse.emf.ecore.InternalEObject;

import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * The factory that adapts {@link Object}s in a specific {@link Class}.
 */
@ParametersAreNonnullByDefault
final class PersistentEObjectAdapter {

    /**
     * In-memory cache that stores the {@link InternalEObject} that have been already adapted to avoid duplication of
     * {@link PersistentEObject}s.
     * <p>
     * We use a weak-references cache since the adaptor is no longer needed when the original {@link InternalEObject}
     * has been garbage collected.
     */
    @Nonnull
    private static final Cache<Object, PersistentEObject> CACHE = CacheBuilder.newBuilder()
            .weakKeys()
            .build();

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private PersistentEObjectAdapter() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Adapts the provided object as a {@code type} instance.
     *
     * @param <T>  the type of the adapted object
     * @param o    the object to adapt
     * @param type the class in which the object must be adapted
     *
     * @return an adapted object in the given {@code type}, or {@code null} if the {@code object} cannot be assigned as
     * a {@code type}
     *
     * @throws NullPointerException if the {@code type} is {@code null}
     */
    @Nullable
    private static <T extends PersistentEObject> T getAdapter(@Nullable Object o, Class<T> type) {
        if (isNull(o)) {
            return null;
        }
        checkNotNull(type);

        Object adapter = null;

        if (type.isInstance(o)) {
            adapter = o;
        }
        else if (o instanceof InternalEObject) {
            adapter = CACHE.get(o);
            if (isNull(adapter) || !type.isAssignableFrom(adapter.getClass())) {
                adapter = createAdapter(o, type);
                CACHE.put(o, (PersistentEObject) adapter);
            }
        }

        if (isNull(adapter)) {
            Log.warn("Unable to create a {0} adapter for this object of type {1}", type.getSimpleName(), o.getClass().getSimpleName());
            return null;
        }

        return type.cast(adapter);
    }

    /**
     * Adapts the provided object as a {@link PersistentEObject} instance.
     *
     * @param o the object to adapt
     *
     * @return an adapted object as a {@link PersistentEObject}, or {@code null} if the {@code object} cannot be
     * assigned as a {@link PersistentEObject}
     *
     * @see #getAdapter(Object, Class)
     */
    @Nullable
    public static PersistentEObject getAdapter(@Nullable Object o) {
        return getAdapter(o, PersistentEObject.class);
    }

    /**
     * Create an adapter for the given {@code object} in a specific {@code type}.
     *
     * @param o    the object to adapt
     * @param type the class in which the object must be adapted
     *
     * @return an adapted object in the given {@code type}
     */
    @Nonnull
    private static <T> Object createAdapter(Object o, Class<T> type) {
        // Compute the interfaces that the proxy has to implement
        List<Class<?>> interfaces = ClassUtils.getAllInterfaces(o.getClass());
        interfaces.add(type);

        // Create the proxy
        Enhancer proxy = new Enhancer();

		/*
         * Use the ClassLoader of the type, otherwise it will cause OSGi troubles (like project trying to
		 * create an PersistentEObject while it does not have a dependency to NeoEMF core)
		 */
        proxy.setClassLoader(type.getClassLoader());
        proxy.setSuperclass(o.getClass());
        proxy.setInterfaces(interfaces.toArray(new Class[interfaces.size()]));
        proxy.setCallback(new PersistentEObjectInterceptor());

        return proxy.create();
    }

    /**
     * ???
     */
    private static class PersistentEObjectInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            /*
             * TODO Dynamically transform 'obj' as a PersistentEObject or its implementation.
             * For now, it only works if the given 'obj' is natively a PersistentEObject.
             */
            // 'invokeSuper()' calls the method itself, not the super method
            return methodProxy.invokeSuper(obj, args);
        }
    }
}
