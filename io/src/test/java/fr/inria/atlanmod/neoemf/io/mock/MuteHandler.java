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

package fr.inria.atlanmod.neoemf.io.mock;

import fr.inria.atlanmod.neoemf.io.PersistenceHandler;

/**
 * A persistence handler that does nothing.
 * <p/>
 * Using for basic tests.
 */
public class MuteHandler implements PersistenceHandler {

    @Override
    public void handleStartElement(String nsUri, String name, String id) throws Exception {

    }

    @Override
    public void handleMetaClass(String nsUri, String name) throws Exception {

    }

    @Override
    public void handleAttribute(String nsUri, String name, int index, String value) throws Exception {

    }

    @Override
    public void handleReference(String nsUri, String name, int index, String idReference) throws Exception {

    }

    @Override
    public void handleEndElement() throws Exception {

    }

    @Override
    public void handleStartDocument() throws Exception {

    }

    @Override
    public void handleEndDocument() throws Exception {

    }
}
