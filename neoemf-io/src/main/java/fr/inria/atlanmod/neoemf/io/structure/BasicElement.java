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

import java.util.Objects;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A identifiable {@link BasicMetaclass} which can be typed.
 */
@ParametersAreNonnullByDefault
public class BasicElement extends BasicMetaclass {

    /**
     * The identifier of this classifier.
     */
    private BasicId id;

    /**
     * The name of the class of this classifier.
     */
    private String className;

    /**
     * The metaclassifier of this classifier.
     */
    private BasicMetaclass metaclass;

    /**
     * Whether this classifier is the root element of a structure.
     */
    private boolean isRoot;

    /**
     * Constructs a new {@code BasicElement} with the given {@code ns} and {@code name}.
     *
     * @param ns   the ns of this classifier
     * @param name the name of this classifier
     */
    public BasicElement(BasicNamespace ns, String name) {
        super(ns, name);
        this.isRoot = false;
    }

    /**
     * Returns the identifier of this classifier.
     *
     * @return the identifier
     */
    public BasicId id() {
        return id;
    }

    /**
     * Defines the identifier of this classifier.
     *
     * @param id the identifier
     */
    public void id(BasicId id) {
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
     * Returns the metaclass of this classifier.
     *
     * @return the metaclass
     */
    public BasicMetaclass metaclass() {
        return metaclass;
    }

    /**
     * Defines the metaclassifier of this classifier.
     *
     * @param metaClass the metaclassifier
     */
    public void metaclass(BasicMetaclass metaClass) {
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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (!BasicElement.class.isInstance(o)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        BasicElement that = BasicElement.class.cast(o);
        return Objects.equals(id, that.id);
    }
}
