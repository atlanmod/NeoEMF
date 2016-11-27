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

package fr.inria.atlanmod.neoemf.map.datastore.store.info;

import fr.inria.atlanmod.neoemf.core.Id;

import java.io.Serializable;

public class ContainerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Id id;

    private final String name;

    public ContainerInfo(Id id, String name) {
        this.id = id;
        this.name = name;
    }

    public Id id() {
        return id;
    }

    public String name() {
        return name;
    }
}
