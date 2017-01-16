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

package fr.inria.atlanmod.neoemf.data.structure;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EReference;

import java.io.Serializable;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple representation of a containment between a {@link PersistentEObject} and another. The referenced
 * {@link PersistentEObject} is identified by the name the reference used to retrieve it.
 */
public class ContainerInfo implements Serializable {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 1L;

    /**
     * The identifier of the object.
     */
    @Nonnull
    private final Id id;

    /**
     * The name of the reference used to retrieve the container of the object.
     */
    @Nonnull
    private final String name;

    /**
     * Constructs a new {@code ContainerInfo} with the given {@code id} and the given {@code name}.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name the reference used to retrieve the container of the {@link PersistentEObject}
     */
    protected ContainerInfo(@Nonnull Id id, @Nonnull String name) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
    }

    /**
     * Creates a new {@code ContainerInfo} from the given {@code object} and {@code reference}.
     * <p>
     * This method behaves like: {@code of(object.id(), reference.getName())}.
     *
     * @param object    the {@link PersistentEObject}
     * @param reference the reference used to retrieve the container of the {@link PersistentEObject}
     *
     * @return a new {@code ContainerInfo}
     *
     * @see #of(Id, String)
     */
    @Nonnull
    public static ContainerInfo from(@Nonnull PersistentEObject object, @Nonnull EReference reference) {
        return of(object.id(), reference.getName());
    }

    /**
     * Creates a new {@code ContainerInfo} with the given {@code id} and {@code name}.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name the reference used to retrieve the container of the {@link PersistentEObject}
     *
     * @return a new {@code ContainerInfo}
     */
    @Nonnull
    public static ContainerInfo of(@Nonnull Id id, @Nonnull String name) {
        return new ContainerInfo(id, name);
    }

    /**
     * Returns the identifier of the {@link PersistentEObject}.
     *
     * @return the identifier of the object
     */
    @Nonnull
    public Id id() {
        return id;
    }

    /**
     * Returns the name the {@link EReference} used to retrieve the container of the {@link PersistentEObject}.
     *
     * @return the name of the reference
     */
    @Nonnull
    public String name() {
        return name;
    }
}
