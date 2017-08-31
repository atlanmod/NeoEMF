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

package fr.inria.atlanmod.commons;

import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object that converts an object of type {@link A} to another of type {@link B}.
 * <p>
 * The reverse operation <b>may</b> be strict <i>inverse</i>, meaning that {@code converter.doBackward(converter.doForward(a)).equals(a)}
 * always {@code true}.
 *
 * @param <A> the type of the input instance
 * @param <B> the type of the output instance
 */
@ParametersAreNonnullByDefault
public interface Converter<A, B> {

    /**
     * Creates a converter that always converts or reverses an object to itself.
     *
     * @param <T> the type of the converted instance
     *
     * @return a new converter
     */
    static <T> Converter<T, T> identity() {
        return new Converter<T, T>() {
            @Nonnull
            @Override
            public T doForward(T t) {
                return t;
            }

            @Nonnull
            @Override
            public T doBackward(T t) {
                return t;
            }
        };
    }

    /**
     * Creates a converter based on separate forward and backward functions.
     *
     * @param forwardFunc  the function used for {@link #doForward(Object)}
     * @param backwardFunc the function used for {@link #doBackward(Object)}
     * @param <A>          the type of the input instance
     * @param <B>          the type of the output instance
     *
     * @return a new converter
     */
    static <A, B> Converter<A, B> from(Function<? super A, ? extends B> forwardFunc, Function<? super B, ? extends A> backwardFunc) {
        return new Converter<A, B>() {
            @Nonnull
            @Override
            public B doForward(A a) {
                return forwardFunc.apply(a);
            }

            @Nonnull
            @Override
            public A doBackward(B b) {
                return backwardFunc.apply(b);
            }
        };
    }

    /**
     * Returns a representation of {@code a} as an instance of type {@link B}.
     *
     * @param a the instance to convert
     *
     * @return the converted instance
     */
    @Nonnull
    B doForward(A a);

    /**
     * Returns a representation of {@code b} as an instance of type {@link A}.
     *
     * @param b the instance to convert
     *
     * @return the converted instance
     */
    @Nonnull
    A doBackward(B b);
}
