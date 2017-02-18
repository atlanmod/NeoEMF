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
 * A identifiable {@link RawMetaclass} which can be typed.
 */
public class RawElement extends RawMetaclass {

    /**
     * The identifier of this classifier.
     */
    private RawId id;

    /**
     * The name of the class of this classifier.
     */
    private String className;

    /**
     * The metaclassifier of this classifier.
     */
    private RawMetaclass metaclass;

    /**
     * Whether this classifier is the root element of a structure.
     */
    private boolean isRoot;

    /**
     * Constructs a new {@code RawElement} with the given {@code ns} and {@code name}.
     *
     * @param ns   the ns of this classifier
     * @param name the name of this classifier
     */
    public RawElement(Namespace ns, String name) {
        super(ns, name);
        this.isRoot = false;
    }

    /**
     * Returns the identifier of this classifier.
     *
     * @return the identifier
     */
    public RawId id() {
        return id;
    }

    /**
     * Defines the identifier of this classifier.
     *
     * @param id the identifier
     */
    public void id(RawId id) {
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
    public RawMetaclass metaclass() {
        return metaclass;
    }

    /**
     * Defines the metaclassifier of this classifier.
     *
     * @param metaClass the metaclassifier
     */
    public void metaclass(RawMetaclass metaClass) {
        this.metaclass = metaClass;
    }

    /**
     * Returns whether this classifier is the root element of a structure.
     *
     * @return {@code true} if this classifier is a root element
     */
    public boolean isRoot() {
        return isRoot;
    }

    /**
     * Defines whether this classifier is the root element of a structure.
     *
     * @param isRoot {@code true} if this classifier is a root element
     */
    public void isRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }
}
