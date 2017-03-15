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

import fr.inria.atlanmod.neoemf.annotations.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A simple representation of a {@link EClass}.
 */
@Immutable
@ParametersAreNonnullByDefault
public final class ClassDescriptor implements Externalizable {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 3630220484508625215L;

    /**
     * The name of the metaclass.
     */
    @Nonnull
    private String name;

    /**
     * The literal representation of the {@link URI} of the metaclass.
     */
    @Nonnull
    private String uri;

    /**
     * The represented {@link EClass}.
     */
    private transient EClass eClass;

    /**
     * Constructs a new {@code ClassDescriptor}.
     */
    @VisibleForReflection
    public ClassDescriptor() {
        this("", "");
    }

    /**
     * Constructs a new {@code ClassDescriptor} with the given {@code name} and {@code uri}, which are used as a
     * simple representation of a an {@link EClass}.
     *
     * @param name the name of the {@link EClass}
     * @param uri  the literal representation of the {@link URI} of the {@link EClass}
     */
    private ClassDescriptor(String name, String uri) {
        this.name = checkNotNull(name);
        this.uri = checkNotNull(uri);
    }

    /**
     * Constructs a new {@code ClassDescriptor} for the represented {@code eClass}.
     *
     * @param eClass the represented {@link EClass}
     */
    private ClassDescriptor(EClass eClass) {
        this(eClass.getName(), eClass.getEPackage().getNsURI());
        this.eClass = eClass;
    }

    /**
     * Creates a new {@code ClassDescriptor} from the given {@code object}. The {@link EClass} will be found by
     * calling the {@link PersistentEObject#eClass()} method.
     * <p>
     * This method behaves like: {@code of(eClass.getName(), eClass.getEPackage().getNsURI())}.
     *
     * @param object the object from which the {@link EClass} has to be retrieve with the {@link
     *               PersistentEObject#eClass()} method
     *
     * @return a new {@code ClassDescriptor}
     *
     * @see #from(EClass)
     * @see #of(String, String)
     */
    @Nonnull
    public static ClassDescriptor from(PersistentEObject object) {
        return from(object.eClass());
    }

    /**
     * Creates a new {@code ClassDescriptor} from the given {@code eClass}.
     * <p>
     * This method behaves like: {@code of(eClass.getName(), eClass.getEPackage().getNsURI())}.
     *
     * @param eClass the {@link EClass}
     *
     * @return a new {@code ClassDescriptor}
     *
     * @see #of(String, String)
     */
    @Nonnull
    public static ClassDescriptor from(EClass eClass) {
        return new ClassDescriptor(eClass);
    }

    /**
     * Creates a new {@code ClassDescriptor} with the given {@code name} and {@code uri}, which are used as a simple
     * representation of a an {@link EClass}.
     *
     * @param name the name of the {@link EClass}
     * @param uri  the literal representation of the {@link URI} of the {@link EClass}
     *
     * @return a new {@code ClassDescriptor}
     */
    @Nonnull
    public static ClassDescriptor of(String name, String uri) {
        return new ClassDescriptor(name, uri);
    }

    /**
     * Returns the name of this {@code ClassDescriptor}.
     *
     * @return the name
     */
    @Nonnull
    public String name() {
        return name;
    }

    /**
     * Returns the literal representation of the {@link URI} of this {@code ClassDescriptor}.
     *
     * @return the URI
     */
    @Nonnull
    public String uri() {
        return uri;
    }

    /**
     * Returns whether this {@code ClassDescriptor} represents an abstract class.
     *
     * @return {@code true} if this {@code ClassDescriptor} represents an abstract class, {@code false} otherwise
     */
    public boolean isAbstract() {
        return get().isAbstract();
    }

    /**
     * Returns whether this {@code ClassDescriptor} represents an interface.
     *
     * @return {@code true} if this {@code ClassDescriptor} represents an interface, {@code false} otherwise
     */
    public boolean isInterface() {
        return get().isInterface();
    }

    /**
     * Retrieves the superclass of this {@code ClassDescriptor}.
     *
     * @return an {@link Optional} containing the representation of the direct superclass, or {@link Optional#empty()}
     * if the class has no superclass
     */
    @Nonnull
    public Optional<ClassDescriptor> inheritFrom() {
        return get().getESuperTypes()
                .stream()
                .filter(c -> !c.isInterface())
                .map(ClassDescriptor::from)
                .findAny();
    }

    /**
     * Retrieves all subclasses of this {@code ClassDescriptor}.
     *
     * @return a {@link Set} containing the representation of all non-abstract subclasses that inherit, directly and
     * indirectly, from this {@code ClassDescriptor}
     */
    @Nonnull
    public Set<ClassDescriptor> inheritedBy() {
        return get().getEPackage().getEClassifiers()
                .stream()
                .filter(EClass.class::isInstance)
                .map(EClass.class::cast)
                .filter(c -> get().isSuperTypeOf(c))
                .filter(c -> !c.isAbstract())
                .filter(c -> !c.isInterface())
                .map(ClassDescriptor::from)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves the {@link EClass} corresponding to this {@code ClassDescriptor}.
     *
     * @return a class, or {@code null} if it can not be found
     */
    public EClass get() {
        if (isNull(eClass)) {
            EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(uri);
            if (nonNull(ePackage)) {
                eClass = (EClass) ePackage.getEClassifier(name);
            }
            else {
                Log.warn("Unable to find EPackage for URI: {0}", uri);
            }
        }
        return eClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uri);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClassDescriptor that = (ClassDescriptor) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(uri, that.uri);
    }

    @Override
    public String toString() {
        return String.format("%s {%s @ %s}", getClass().getSimpleName(), name, uri);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeUTF(uri);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        uri = in.readUTF();
    }
}

