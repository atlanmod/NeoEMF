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
 * A simple representation of a element with a {@link Namespace} and a name.
 */
public class MetaClassifier extends NamedElement {

    /**
     * The instance of the default {@code MetaClassifier}.
     */
    private static final MetaClassifier DEFAULT = new MetaClassifier(Namespace.getDefault(), "EObject");

    /**
     * The namespace of this metaclassifier.
     */
    private Namespace namespace;

    /**
     * Constructs a new {@code MetaClassifier} with the given {@code namespace} and {@code localName}.
     *
     * @param namespace the namespace of this metaclassifier
     * @param localName the name of this metaclassifier
     */
    public MetaClassifier(Namespace namespace, String localName) {
        super(localName);
        this.namespace = namespace;
    }

    /**
     * Returns the default metaclassifier.
     *
     * @return the metaclassifier representing "ecore:EObject"
     */
    public static MetaClassifier getDefault() {
        return DEFAULT;
    }

    /**
     * Returns the namespace of this metaclassifier.
     *
     * @return the namespace
     */
    public Namespace getNamespace() {
        return namespace;
    }

    /**
     * Defines the namespace of this metaclassifier.
     *
     * @param namespace the namespace
     */
    public void setNamespace(Namespace namespace) {
        this.namespace = namespace;
    }

    @Override
    public String toString() {
        return namespace.getPrefix() + ':' + getLocalName();
    }
}
