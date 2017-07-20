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

import fr.inria.atlanmod.common.annotation.Static;
import fr.inria.atlanmod.common.cache.Cache;
import fr.inria.atlanmod.common.cache.CacheBuilder;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.eclipse.emf.ecore.InternalEObject;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * The factory that adapts {@link Object}s in a specific {@link Class}.
 */
@Static
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
    private static final Cache<Object, PersistentEObject> CACHE = CacheBuilder.builder()
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
    @Nonnull
    private static <T extends PersistentEObject> T adapt(Object o, Class<T> type) {
        checkNotNull(o);
        checkNotNull(type);

        Object adapter = null;

        if (type.isInstance(o)) {
            adapter = o;
        }
        else if (InternalEObject.class.isInstance(o)) {
            adapter = CACHE.get(o);
            if (isNull(adapter) || !type.isAssignableFrom(adapter.getClass())) {
                adapter = createAdapter(o, type);
                CACHE.put(o, PersistentEObject.class.cast(adapter));
            }
        }

        if (isNull(adapter)) {
            throw new IllegalArgumentException(
                    String.format("Unable to create a %s adapter for this object of type %s",
                            type.getSimpleName(),
                            o.getClass().getSimpleName()));
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
     * @see #adapt(Object, Class)
     */
    @Nonnull
    public static PersistentEObject adapt(Object o) {
        return adapt(o, PersistentEObject.class);
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
        Set<Class<?>> interfaces = getAllInterfaces(o.getClass());
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
     * Retrieves all interfaces implemented by the given class and its superclasses.
     * <p>
     * The order is determined by looking through each interface in turn as declared in the source file and following
     * its hierarchy up. Then each superclass is considered in the same way. Later duplicates are ignored, so the order
     * is maintained.
     *
     * @param type the class to look up
     *
     * @return a {@link Set} of interfaces in order
     */
    @Nonnull
    private static Set<Class<?>> getAllInterfaces(Class<?> type) {
        Set<Class<?>> interfaces = new LinkedHashSet<>();
        getAllInterfaces(type, interfaces);
        return interfaces;
    }

    /**
     * Retrieves recursively the interfaces for the specified class.
     *
     * @param cls        the class to look up
     * @param interfaces the {@link Set} of interfaces for the class
     */
    private static void getAllInterfaces(Class<?> cls, Set<Class<?>> interfaces) {
        while (cls != null) {
            for (Class<?> i : cls.getInterfaces()) {
                if (interfaces.add(i)) {
                    getAllInterfaces(i, interfaces);
                }
            }
            cls = cls.getSuperclass();
        }
    }

    /**
     * A proxy that handles method calls from an {@link Object}. It dynamically transforms {@link Object}s as
     * {@link PersistentEObject}s.
     */
    @ParametersAreNonnullByDefault
    private static class PersistentEObjectInterceptor implements MethodInterceptor {

        /**
         * The {@link PersistentEObject} associated to this proxy.
         */
        private PersistentEObject persistentObject;

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            try {
                // Note: 'invokeSuper()' calls the method itself, not the super method
                return methodProxy.invokeSuper(obj, args);
            }
            catch (Throwable e) {
                // Resolve object for a call to a non-static method
                if (isNull(persistentObject) && nonNull(obj)) {
                    persistentObject = resolve(InternalEObject.class.cast(obj));
                }

                return method.invoke(persistentObject, args);
            }
        }

        /**
         * Resolves the {@link PersistentEObject} represented by the {@code proxy}.
         *
         * @param proxy the proxy object to resolve
         *
         * @return the resolved object
         *
         * @throws UnsupportedOperationException if the proxy cannot be resolved
         */
        @Nonnull
        private PersistentEObject resolve(InternalEObject proxy) {
            throw new UnsupportedOperationException("Dynamic EMF is not supported yet");
        }
    }
}
