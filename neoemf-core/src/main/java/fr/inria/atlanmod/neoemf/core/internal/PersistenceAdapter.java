/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.atlanmod.commons.annotation.Singleton;
import org.atlanmod.commons.annotation.Static;
import org.atlanmod.commons.cache.Cache;
import org.atlanmod.commons.cache.CacheBuilder;
import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.reflect.MoreReflection;
import org.eclipse.emf.ecore.InternalEObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkArgument;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The factory that adapts {@link Object}s in a specific {@link PersistentEObject}.
 */
@Singleton
@ParametersAreNonnullByDefault
public final class PersistenceAdapter {

    /**
     * In-memory cache that stores the {@link InternalEObject} that have been already adapted to avoid duplication of
     * {@link PersistentEObject}s.
     * <p>
     * We use a weak-references cache since the adaptor is no longer needed when the original {@link InternalEObject}
     * has been garbage collected.
     */
    @Nonnull
    private final Cache<Object, PersistentEObject> cache = CacheBuilder.builder()
            .weakKeys()
            .build();

    /**
     * Constructs a new {@code PersistenceAdapter}.
     */
    private PersistenceAdapter() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static PersistenceAdapter getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Adapts the provided object as a {@code type} instance.
     *
     * @param <T>    the type of the adapted object
     * @param object the object to adapt
     * @param type   the class in which the object must be adapted
     *
     * @return an adapted object in the given {@code type}, or {@code null} if the {@code object} cannot be assigned as
     * a {@code type}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @Nonnull
    private <T extends PersistentEObject> T adapt(Object object, Class<T> type) {
        checkNotNull(object, "object");
        checkNotNull(type, "type");

        Object adaptedInstance = null;

        if (type.isInstance(object)) {
            adaptedInstance = object;
        }
        else if (object instanceof InternalEObject) {
            adaptedInstance = cache.get(object, o -> createProxy((InternalEObject) o, type));
        }

        // Check the instance has been resolved
        checkArgument(nonNull(adaptedInstance),
                "Unable to create a %s adapter for this object of type %s",
                type.getSimpleName(), object.getClass().getSimpleName());

        // adaptedInstance != null
        return type.cast(adaptedInstance);
    }

    /**
     * Adapts the provided object as a {@link PersistentEObject} instance.
     *
     * @param object the object to adapt
     *
     * @return an adapted object as a {@link PersistentEObject}, or {@code null} if the {@code object} cannot be
     * assigned as a {@link PersistentEObject}
     *
     * @see #adapt(Object, Class)
     */
    @Nonnull
    public PersistentEObject adapt(Object object) {
        return adapt(object, PersistentEObject.class);
    }

    /**
     * Create an adapter for the given {@code object} in a specific {@code type}.
     *
     * @param object the object to adapt
     * @param type   the class in which the object must be adapted
     *
     * @return an adapted object in the given {@code type}
     */
    @Nonnull
    private <T extends PersistentEObject> T createProxy(InternalEObject object, Class<T> type) {
        Log.warn("Creating a proxy for {0}", object);

        final Set<Class<?>> interfaces = MoreReflection.getAllInterfaces(object.getClass());
        interfaces.add(type);

        // Create the proxy
        Enhancer proxy = new Enhancer();
        proxy.setClassLoader(type.getClassLoader());
        proxy.setSuperclass(object.getClass());
        proxy.setInterfaces(interfaces.toArray(new Class[0]));
        proxy.setCallback(new PersistenceInterceptor());

        return type.cast(proxy.create());
    }

    /**
     * A proxy that handles method calls from an {@link Object}. It dynamically transforms {@link Object}s as {@link
     * PersistentEObject}s.
     */
    @ParametersAreNonnullByDefault
    private static class PersistenceInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(@Nullable Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            try {
                // Note: 'invokeSuper()' calls the method itself, not the super method
                return methodProxy.invokeSuper(proxy, args);
            }
            catch (Throwable e) {
                return invoke(proxy, method, args);
            }
        }

        /**
         * Resolves the {@code object} and invokes the {@code method} with its {@code args} on it.
         *
         * @param proxy  the instance related to this invocation; {@code null} if the {@code method} is static
         * @param method the method to invoke
         * @param args   the arguments of the {@code method}
         *
         * @return the result of the invocation
         *
         * @throws InvocationTargetException if the underlying method throws an exception
         * @throws IllegalAccessException    if the specified object is null and the method is an instance method
         */
        @SuppressWarnings("ConstantConditions")
        private Object invoke(@Nullable Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
            logInvocation(proxy, method);

            final Object resolvedInstance = nonNull(proxy)
                    ? resolve((InternalEObject) proxy)
                    : null;

            return method.invoke(resolvedInstance, args);
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
            throw new UnsupportedOperationException(String.format("Dynamic EMF is not supported yet: Unable to resolve %s", proxy));
        }

        /**
         * Logs the {@code method} invocation.
         *
         * @param proxy  the instance related to this invocation; {@code null} if the {@code method} is static
         * @param method the method to invoke
         */
        private void logInvocation(@Nullable Object proxy, Method method) {
            final String args = Arrays.stream(method.getParameterTypes())
                    .map(Object::getClass)
                    .map(Class::getSimpleName)
                    .collect(Collectors.joining(", "));

            Log.info("Invoking {0}#{1}({2}) on {3}", method.getDeclaringClass().getSimpleName(), method.getName(), args, proxy);
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        static final PersistenceAdapter INSTANCE = new PersistenceAdapter();
    }
}
