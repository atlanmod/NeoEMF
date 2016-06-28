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
 * A {@link NamespacedElement} which can be typed.
 */
public class Classifier extends NamespacedElement {

    private String id;
    private String className;
    private NamespacedElement metaclass;

    private boolean root;

    public Classifier(Namespace namespace, String localName) {
        super(namespace, localName);
        this.root = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public NamespacedElement getMetaclass() {
        return metaclass;
    }

    public void setMetaclass(NamespacedElement metaclass) {
        this.metaclass = metaclass;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }
}
