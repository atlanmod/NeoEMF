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

package fr.inria.atlanmod.neoemf.bind.annotation;

import fr.inria.atlanmod.neoemf.data.BackendFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Nonnull;

/**
 * Annotates an element that is bound to a {@link BackendFactory}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FactoryBinding {

    /**
     * The type of the bound {@link BackendFactory}.
     *
     * @return the type
     */
    @Nonnull
    Class<? extends BackendFactory> value();
}
