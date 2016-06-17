/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;

/**
 *
 */
public class ConsoleHandler implements InternalHandler {

    private String name;

    public ConsoleHandler(String name) {
        this.name = name;
    }

    @Override
    public void handleStartDocument() {
        //System.out.println("[" + name +  "] Start the reading of a document");
    }

    @Override
    public void handleStartElement(String namespace, String localName) {
        System.out.println("[" + name +  "] Element : " + namespace + ":" + localName);
    }

    @Override
    public void handleAttribute(String namespace, String localName, String value) {
        System.out.println("[" + name +  "]  - Attribute : " + namespace + ":" + localName + " = " + value);
    }

    @Override
    public void handleReference(String namespace, String localName, String reference) {
        System.out.println("[" + name +  "]  - Reference : " + namespace + ":" + localName + " = " + reference);
    }

    @Override
    public void handleEndElement() {
        //System.out.println("[" + name +  "] End of element");
    }

    @Override
    public void handleEndDocument() {
        //System.out.println("[" + name +  "] Document reading done");
    }
}
