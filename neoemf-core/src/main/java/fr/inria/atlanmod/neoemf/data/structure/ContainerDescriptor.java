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
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A simple representation of a containment between a {@link PersistentEObject} and another. The referenced
 * {@link PersistentEObject} is identified by the name the reference used to retrieve it.
 */
@Immutable
@ParametersAreNonnullByDefault
public final class ContainerDescriptor implements Serializable {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = -3357337595522803972L;

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
     * Constructs a new {@code ContainerDescriptor} with the given {@code id} and the given {@code name}.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name the reference used to retrieve the container of the {@link PersistentEObject}
     */
    protected ContainerDescriptor(Id id, String name) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
    }

    /**
     * Creates a new {@code ContainerDescriptor} from the given {@code object} and {@code reference}.
     * <p>
     * This method behaves like: {@code of(object.id(), reference.getName())}.
     *
     * @param object    the {@link PersistentEObject}
     * @param reference the reference used to retrieve the container of the {@link PersistentEObject}
     *
     * @return a new {@code ContainerDescriptor}
     *
     * @see #of(Id, String)
     */
    @Nonnull
    public static ContainerDescriptor from(PersistentEObject object, EReference reference) {
        return of(object.id(), reference.getName());
    }

    /**
     * Creates a new {@code ContainerDescriptor} with the given {@code id} and {@code name}.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name the reference used to retrieve the container of the {@link PersistentEObject}
     *
     * @return a new {@code ContainerDescriptor}
     */
    @Nonnull
    public static ContainerDescriptor of(Id id, String name) {
        return new ContainerDescriptor(id, name);
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

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContainerDescriptor that = (ContainerDescriptor) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return String.format("%s {%s # %s}", getClass().getSimpleName(), id, name);
    }
}
