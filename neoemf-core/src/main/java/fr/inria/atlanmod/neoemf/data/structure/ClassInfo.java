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

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Memento class for storing metaclass/{@link EClass} information.
 */
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;

    private final String uri;

    protected ClassInfo(String name, String uri) {
        this.name = checkNotNull(name);
        this.uri = checkNotNull(uri);
    }

    public static ClassInfo from(PersistentEObject object) {
        final EClass eClass = object.eClass();
        return new ClassInfo(eClass.getName(), eClass.getEPackage().getNsURI());
    }

    public String name() {
        return name;
    }

    public String uri() {
        return uri;
    }

    /**
     * Retrieves the EClass corresponding to this memento.
     */
    public EClass eClass() {
        return (EClass) EPackage.Registry.INSTANCE.getEPackage(uri).getEClassifier(name);
    }
}

