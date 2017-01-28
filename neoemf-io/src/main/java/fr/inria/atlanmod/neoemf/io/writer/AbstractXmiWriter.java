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

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

/**
 *
 */
@Experimental
public abstract class AbstractXmiWriter extends AbstractWriter {

    @Override
    public void processStartDocument() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void processStartElement(Classifier classifier) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void processAttribute(Attribute attribute) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void processReference(Reference reference) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void processEndElement() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void processEndDocument() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void processCharacters(String characters) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
