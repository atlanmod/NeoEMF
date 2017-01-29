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
 * A identifiable {@link RawMetaClassifier} which can be typed.
 */
public class RawClassifier extends RawMetaClassifier {

    /**
     * The identifier of this classifier.
     */
    private RawIdentifier id;

    /**
     * The name of the class of this classifier.
     */
    private String className;

    /**
     * The metaclassifier of this classifier.
     */
    private RawMetaClassifier metaClassifier;

    /**
     * Whether this classifier is the root element of a structure.
     */
    private boolean root;

    /**
     * Constructs a new {@code RawClassifier} with the given {@code namespace} and {@code localName}.
     *
     * @param namespace the namespace of this classifier
     * @param localName the name of this classifier
     */
    public RawClassifier(RawNamespace namespace, String localName) {
        super(namespace, localName);
        this.root = false;
    }

    /**
     * Returns the identifier of this classifier.
     *
     * @return the identifier
     */
    public RawIdentifier id() {
        return id;
    }

    /**
     * Defines the identifier of this classifier.
     *
     * @param id the identifier
     */
    public void id(RawIdentifier id) {
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
    public RawMetaClassifier metaClassifier() {
        return metaClassifier;
    }

    /**
     * Defines the metaclassifier of this classifier.
     *
     * @param metaClassifier the metaclassifier
     */
    public void metaClassifier(RawMetaClassifier metaClassifier) {
        this.metaClassifier = metaClassifier;
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
