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

package fr.inria.atlanmod.neoemf.io.mock.beans;

import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawIdentifier;
import fr.inria.atlanmod.neoemf.io.structure.RawMetaClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawNamespace;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ClassifierMock {

    private final RawClassifier classifier;

    private final List<RawAttribute> attributes;
    private final List<RawReference> references;

    private final List<ClassifierMock> elements;

    public ClassifierMock(RawClassifier classifier) {
        this.classifier = classifier;

        this.attributes = new ArrayList<>();
        this.references = new ArrayList<>();
        this.elements = new ArrayList<>();
    }

    public static ClassifierMock getChildFrom(ClassifierMock root, int... indexes) {
        if (indexes.length == 0) {
            throw new IllegalArgumentException("You must define at least one index");
        }

        ClassifierMock child = root;

        for (int index : indexes) {
            child = child.getElements().get(index);
        }

        return child;
    }

    public String getLocalName() {
        return classifier.localName();
    }

    public RawNamespace getNamespace() {
        return classifier.namespace();
    }

    public boolean isRoot() {
        return classifier.root();
    }

    public String getClassName() {
        return classifier.className();
    }

    public RawIdentifier getId() {
        return classifier.id();
    }

    public RawMetaClassifier getMetaClassifier() {
        return classifier.metaClassifier();
    }

    public List<RawAttribute> getAttributes() {
        return attributes;
    }

    public List<RawReference> getReferences() {
        return references;
    }

    public List<ClassifierMock> getElements() {
        return elements;
    }
}
