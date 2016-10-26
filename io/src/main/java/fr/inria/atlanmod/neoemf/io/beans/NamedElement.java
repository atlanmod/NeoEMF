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

package fr.inria.atlanmod.neoemf.io.beans;

/**
 * An element which has a name.
 */
public class NamedElement {

    private String localName;

    public NamedElement(String localName) {
        this.localName = localName;
    }

    public String getLocalName() {
        return localName;
    }

    @Override
    public String toString() {
        return localName;
    }
}
