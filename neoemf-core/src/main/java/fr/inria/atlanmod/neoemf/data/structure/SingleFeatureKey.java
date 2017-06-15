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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

/**
 * A simple representation of a single-valued {@link EStructuralFeature} of a {@link PersistentEObject}.
 */
@Immutable
@ParametersAreNonnullByDefault
public class SingleFeatureKey extends FeatureKey {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = -2197099155190693261L;

    /**
     * Constructs a new {@code SingleFeatureKey} with the given {@code id} and the given {@code name}, which are used as
     * a simple representation of a feature of an object.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     */
    protected SingleFeatureKey(Id id, String name) {
        super(id, name);
    }

    /**
     * Creates a new {@code SingleFeatureKey} from the given {@code object} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code from(PersistentEObject.from(object), feature)}.
     *
     * @param object  the {@link InternalEObject} that will be adapted as {@link PersistentEObject}
     * @param feature the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return a new {@code SingleFeatureKey}
     *
     * @see #from(PersistentEObject, EStructuralFeature)
     * @see PersistentEObject#from(Object)
     * @see EStructuralFeature#getName()
     */
    @Nonnull
    public static SingleFeatureKey from(InternalEObject object, EStructuralFeature feature) {
        return from(PersistentEObject.from(object), feature);
    }

    /**
     * Creates a new {@code SingleFeatureKey} from the given {@code object} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code of(object.id(), feature.getName())}.
     *
     * @param object  the {@link PersistentEObject}
     * @param feature the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return a new {@code SingleFeatureKey}
     *
     * @see #of(Id, String)
     * @see PersistentEObject#id()
     * @see EStructuralFeature#getName()
     */
    @Nonnull
    public static SingleFeatureKey from(PersistentEObject object, EStructuralFeature feature) {
        return of(object.id(), feature.getName());
    }

    /**
     * Creates a new {@code SingleFeatureKey} with the given {@code id} and the given {@code name}, which are used as a
     * simple representation of a feature of an object.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return a new {@code SingleFeatureKey}
     */
    @Nonnull
    public static SingleFeatureKey of(Id id, String name) {
        return new SingleFeatureKey(id, name);
    }

    @Override
    public String toString() {
        return String.format("SingleFeatureKey {%s # %s}", id, name);
    }
}
