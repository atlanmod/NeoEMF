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

package fr.inria.atlanmod.neoemf.datastore.store.info;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import java.io.Serializable;

/**
 * Memento class for storing metaclass/EClass information.
 */
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String uri;

    private final String name;

    public ClassInfo(PersistentEObject object) {
        this.uri = object.eClass().getEPackage().getNsURI();
        this.name = object.eClass().getName();
    }

    public String uri() {
        return uri;
    }

    public String name() {
        return name;
    }

    /**
     * Retrieves the EClass corresponding to this memento.
     */
    public EClass eClass() {
        return (EClass) EPackage.Registry.INSTANCE.getEPackage(uri).getEClassifier(name);
    }
}

