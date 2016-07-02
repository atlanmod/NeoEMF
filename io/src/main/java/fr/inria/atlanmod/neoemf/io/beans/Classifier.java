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

package fr.inria.atlanmod.neoemf.io.beans;

/**
 * A {@link MetaClassifier} which can be typed.
 */
public class Classifier extends MetaClassifier {

    private Identifier id;
    private String className;
    private MetaClassifier metaClassifier;

    private boolean root;

    public Classifier(Namespace namespace, String localName) {
        super(namespace, localName);
        this.root = false;
    }

    public Identifier getId() {
        return id;
    }

    public void setId(Identifier id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public MetaClassifier getMetaClassifier() {
        return metaClassifier;
    }

    public void setMetaClassifier(MetaClassifier metaClassifier) {
        this.metaClassifier = metaClassifier;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }
}
