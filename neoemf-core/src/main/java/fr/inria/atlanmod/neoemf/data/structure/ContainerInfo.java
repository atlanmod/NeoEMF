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

package fr.inria.atlanmod.neoemf.data.structure;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EReference;

import java.io.Serializable;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class ContainerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Nonnull
    private final Id id;

    @Nonnull
    private final String name;

    protected ContainerInfo(@Nonnull Id id, @Nonnull String name) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
    }

    @Nonnull
    public static ContainerInfo from(@Nonnull PersistentEObject object, @Nonnull EReference reference) {
        return new ContainerInfo(object.id(), reference.getName());
    }

    @Nonnull
    public static ContainerInfo of(@Nonnull Id id, @Nonnull String name) {
        return new ContainerInfo(id, name);
    }

    @Nonnull
    public Id id() {
        return id;
    }

    @Nonnull
    public String name() {
        return name;
    }
}
