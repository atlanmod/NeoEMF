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
 * A identifiable {@link MetaClassifier} which can be typed.
 */
public class Classifier extends MetaClassifier {

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
    private MetaClassifier metaClassifier;

    /**
     * Whether this classifier is the root element of a structure.
     */
    private boolean root;

    /**
     * Constructs a new {@code Classifier} with the given {@code namespace} and {@code localName}.
     *
     * @param namespace the namespace of this classifier
     * @param localName the name of this classifier
     */
    public Classifier(Namespace namespace, String localName) {
        super(namespace, localName);
        this.root = false;
    }

    /**
     * Returns the identifier of this classifier.
     *
     * @return the identifier
     */
    public Identifier getId() {
        return id;
    }

    /**
     * Defines the identifier of this classifier.
     *
     * @param id the identifier
     */
    public void setId(Identifier id) {
        this.id = id;
    }

    /**
     * Returns the name of the class of this classifier.
     *
     * @return the name of the class
     */
    public String getClassName() {
        return className;
    }

    /**
     * Defines the name of the class of this classifier.
     *
     * @param className the name of the class
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Returns the metaclassifier of this classifier.
     *
     * @return the metaclassifier
     */
    public MetaClassifier getMetaClassifier() {
        return metaClassifier;
    }

    /**
     * Defines the metaclassifier of this classifier.
     *
     * @param metaClassifier the metaclassifier
     */
    public void setMetaClassifier(MetaClassifier metaClassifier) {
        this.metaClassifier = metaClassifier;
    }

    /**
     * Returns whether this classifier is the root element of a structure.
     *
     * @return {@code true} if this classifier is a root element
     */
    public boolean isRoot() {
        return root;
    }

    /**
     * Defines whether this classifier is the root element of a structure.
     *
     * @param root {@code true} if this classifier is a root element
     */
    public void setRoot(boolean root) {
        this.root = root;
    }
}
