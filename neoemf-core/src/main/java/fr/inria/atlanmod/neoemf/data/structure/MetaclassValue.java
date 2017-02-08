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
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A simple representation of a (meta){@link EClass}.
 */
@ParametersAreNonnullByDefault
public class MetaclassValue implements Serializable {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 1L;

    /**
     * The name of the class.
     */
    @Nonnull
    private final String name;

    /**
     * The literal representation of the {@link URI} of the class.
     */
    @Nonnull
    private final String uri;

    /**
     * Constructs a new {@code MetaclassValue} with the given {@code name} and {@code uri}, which are used as a simple
     * representation of a an {@link EClass}.
     *
     * @param name the name of the {@link EClass}
     * @param uri  the literal representation of the {@link URI} of the {@link EClass}
     */
    protected MetaclassValue(String name, String uri) {
        this.name = checkNotNull(name);
        this.uri = checkNotNull(uri);
    }

    /**
     * Creates a new {@code MetaclassValue} from the given {@code object}. The {@link EClass} will be found by calling
     * the {@link PersistentEObject#eClass()} method.
     * <p>
     * This method behaves like: {@code of(eClass.getName(), eClass.getEPackage().getNsURI())}.
     *
     * @param object the object from which the {@link EClass} has to be retrieve with the {@link
     *               PersistentEObject#eClass()} method
     *
     * @return a new {@code MetaclassValue}
     *
     * @see #of(String, String)
     */
    @Nonnull
    public static MetaclassValue from(PersistentEObject object) {
        final EClass eClass = object.eClass();
        return of(eClass.getName(), eClass.getEPackage().getNsURI());
    }

    /**
     * Creates a new {@code MetaclassValue} with the given {@code name} and {@code uri}, which are used as a simple
     * representation of a an {@link EClass}.
     *
     * @param name the name of the {@link EClass}
     * @param uri  the literal representation of the {@link URI} of the {@link EClass}
     *
     * @return a new {@code MetaclassValue}
     */
    @Nonnull
    public static MetaclassValue of(String name, String uri) {
        return new MetaclassValue(name, uri);
    }

    /**
     * Returns the name of the {@link EClass}.
     *
     * @return the name of the class
     */
    @Nonnull
    public String name() {
        return name;
    }

    /**
     * Returns the literal representation of the {@link URI} of the {@link EClass}.
     *
     * @return the literal representation of the {@link URI}
     */
    @Nonnull
    public String uri() {
        return uri;
    }

    /**
     * Retrieves the {@link EClass} corresponding to this {@code MetaclassValue}.
     *
     * @return a class, or {@code null} if it can not be found
     */
    public EClass eClass() {
        EClass eClass = null;
        EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(uri);
        if (isNull(ePackage)) {
            NeoLogger.warn("Unable to find EPackage for URI: {0}", uri);
        }
        else {
            eClass = (EClass) ePackage.getEClassifier(name);
        }
        return eClass;
    }
}

