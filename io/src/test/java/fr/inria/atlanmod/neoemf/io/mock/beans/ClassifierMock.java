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

package fr.inria.atlanmod.neoemf.io.mock.beans;

import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.Identifier;
import fr.inria.atlanmod.neoemf.io.beans.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.beans.Namespace;
import fr.inria.atlanmod.neoemf.io.beans.Reference;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ClassifierMock {

    private final Classifier classifier;

    private final List<Attribute> attributes;
    private final List<Reference> references;

    private final List<ClassifierMock> elements;

    public ClassifierMock(Classifier classifier) {
        this.classifier = classifier;

        this.attributes = new ArrayList<>();
        this.references = new ArrayList<>();
        this.elements = new ArrayList<>();
    }

    public String getLocalName() {
        return classifier.getLocalName();
    }

    public Namespace getNamespace() {
        return classifier.getNamespace();
    }

    public boolean isRoot() {
        return classifier.isRoot();
    }

    public String getClassName() {
        return classifier.getClassName();
    }

    public Identifier getId() {
        return classifier.getId();
    }

    public MetaClassifier getMetaClassifier() {
        return classifier.getMetaClassifier();
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public List<ClassifierMock> getElements() {
        return elements;
    }

    public static ClassifierMock getChildFrom(ClassifierMock root, int ... indexes) {
        if (indexes.length == 0) {
            throw new IllegalArgumentException("You must define at least one index");
        }

        ClassifierMock child = root;

        for (int index : indexes) {
            child = child.getElements().get(index);
        }

        return child;
    }
}
