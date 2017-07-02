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

package fr.inria.atlanmod.neoemf.data.bean;

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
public class SingleFeatureBean extends AbstractFeatureBean {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = -2197099155190693261L;

    /**
     * Constructs a new {@code SingleFeatureBean} with the given {@code id} and the given {@code name}, which are used
     * as a simple representation of a feature of an object.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     */
    protected SingleFeatureBean(Id id, String name) {
        super(id, name);
    }

    /**
     * Creates a new {@code SingleFeatureBean} from the given {@code object} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code from(PersistentEObject.from(object), feature)}.
     *
     * @param object  the {@link InternalEObject} that will be adapted as {@link PersistentEObject}
     * @param feature the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return a new {@code SingleFeatureBean}
     *
     * @throws NullPointerException if any argument is {@code null}
     * @see #from(PersistentEObject, EStructuralFeature)
     * @see PersistentEObject#from(Object)
     * @see EStructuralFeature#getName()
     */
    @Nonnull
    public static SingleFeatureBean from(InternalEObject object, EStructuralFeature feature) {
        return from(PersistentEObject.from(object), feature);
    }

    /**
     * Creates a new {@code SingleFeatureBean} from the given {@code object} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code of(object.id(), feature.getName())}.
     *
     * @param object  the {@link PersistentEObject}
     * @param feature the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return a new {@code SingleFeatureBean}
     *
     * @throws NullPointerException if any argument is {@code null}
     * @see #of(Id, String)
     * @see PersistentEObject#id()
     * @see EStructuralFeature#getName()
     */
    @Nonnull
    public static SingleFeatureBean from(PersistentEObject object, EStructuralFeature feature) {
        return of(object.id(), feature.getName());
    }

    /**
     * Creates a new {@code SingleFeatureBean} with the given {@code id} and the given {@code name}, which are used as a
     * simple representation of a feature of an object.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     *
     * @return a new {@code SingleFeatureBean}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @Nonnull
    public static SingleFeatureBean of(Id id, String name) {
        return new SingleFeatureBean(id, name);
    }

    @Override
    public boolean isMany() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("SingleFeatureBean {%s # %s}", id, name);
    }
}
