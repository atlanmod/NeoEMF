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

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * Memento class for storing metaclass/{@link EClass} information.
 */
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Nonnull
    private final String name;

    @Nonnull
    private final String uri;

    protected ClassInfo(@Nonnull String name, @Nonnull String uri) {
        this.name = checkNotNull(name);
        this.uri = checkNotNull(uri);
    }

    @Nonnull
    public static ClassInfo from(@Nonnull PersistentEObject object) {
        final EClass eClass = object.eClass();
        return of(eClass.getName(), eClass.getEPackage().getNsURI());
    }

    @Nonnull
    public static ClassInfo of(@Nonnull String name, @Nonnull String uri) {
        return new ClassInfo(name, uri);
    }

    @Nonnull
    public String name() {
        return name;
    }

    @Nonnull
    public String uri() {
        return uri;
    }

    /**
     * Retrieves the {@link EClass} corresponding to this memento.
     *
     * @return an {@link EClass}, or {@code null} if it can not be found
     */
    @Nullable
    public EClass eClass() {
        EClass eClass = null;
        EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(uri);
        if (isNull(ePackage)) {
            NeoLogger.warn("Unable to find EPackage for URI: {0}", uri);
        } else {
            eClass = (EClass) ePackage.getEClassifier(name);
        }
        return eClass;
    }
}

