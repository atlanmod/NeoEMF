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

package fr.inria.atlanmod.neoemf.datastore.structure;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EReference;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class ContainerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Id id;

    private final String name;

    protected ContainerInfo(Id id, String name) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
    }

    public static ContainerInfo from(PersistentEObject eObject, EReference eReference) {
        return new ContainerInfo(eObject.id(), eReference.getName());
    }

    public Id id() {
        return id;
    }

    public String name() {
        return name;
    }
}
