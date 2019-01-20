/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind;

import fr.inria.atlanmod.neoemf.data.BackendFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Nonnull;

/**
 * Annotates an element that is bound to a {@link fr.inria.atlanmod.neoemf.data.BackendFactory}.
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FactoryBinding {

    /**
     * The default variant.
     */
    @Nonnull
    String DEFAULT_VARIANT = "default";

    /**
     * The type of the {@link BackendFactory} implied by this binding.
     *
     * @return the type
     */
    @Nonnull
    Class<? extends BackendFactory> factory();

    /**
     * The variant identifier of this binding; it should be unique for a given {@link BackendFactory}.
     *
     * @return the variant identifier
     */
    @Nonnull
    String variant() default DEFAULT_VARIANT;

    /**
     * {@code true} if the annotated class represents a concrete implementation, {@code false} if it's an abstract or a
     * base class.
     *
     * @return {@code true} if the annotated class represents a reel instance
     */
    boolean concrete() default true;
}
