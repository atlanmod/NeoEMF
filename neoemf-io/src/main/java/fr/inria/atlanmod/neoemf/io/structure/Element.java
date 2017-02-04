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

package fr.inria.atlanmod.neoemf.io.structure;

/**
 * A identifiable {@link MetaClass} which can be typed.
 */
public class Element extends MetaClass {

    /**
     * The identifier of this classifier.
     */
    private Identifier id;

    /**
     * The name of the class of this classifier.
     */
    private String className;

    /**
     * The metaclassifier of this classifier.
     */
    private MetaClass metaClass;

    /**
     * Whether this classifier is the root element of a structure.
     */
    private boolean root;

    /**
     * Constructs a new {@code Element} with the given {@code ns} and {@code name}.
     *
     * @param ns   the ns of this classifier
     * @param name the name of this classifier
     */
    public Element(Namespace ns, String name) {
        super(ns, name);
        this.root = false;
    }

    /**
     * Returns the identifier of this classifier.
     *
     * @return the identifier
     */
    public Identifier id() {
        return id;
    }

    /**
     * Defines the identifier of this classifier.
     *
     * @param id the identifier
     */
    public void id(Identifier id) {
        this.id = id;
    }

    /**
     * Returns the name of the class of this classifier.
     *
     * @return the name of the class
     */
    public String className() {
        return className;
    }

    /**
     * Defines the name of the class of this classifier.
     *
     * @param className the name of the class
     */
    public void className(String className) {
        this.className = className;
    }

    /**
     * Returns the metaclassifier of this classifier.
     *
     * @return the metaclassifier
     */
    public MetaClass metaClass() {
        return metaClass;
    }

    /**
     * Defines the metaclassifier of this classifier.
     *
     * @param metaClass the metaclassifier
     */
    public void metaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    /**
     * Returns whether this classifier is the root element of a structure.
     *
     * @return {@code true} if this classifier is a root element
     */
    public boolean root() {
        return root;
    }

    /**
     * Defines whether this classifier is the root element of a structure.
     *
     * @param root {@code true} if this classifier is a root element
     */
    public void root(boolean root) {
        this.root = root;
    }
}
