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

package fr.inria.atlanmod.neoemf.io.bean;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link AbstractBasicFeature} representing an attribute, with a value.
 */
@ParametersAreNonnullByDefault
public class BasicAttribute extends AbstractBasicFeature {

    /**
     * The value of this attribute.
     */
    private String value;

    /**
     * Constructs a new {@code BasicAttribute} with the given {@code name}.
     *
     * @param name the name of this attribute
     */
    public BasicAttribute(String name) {
        super(name);
    }

    /**
     * Converts a {@link BasicReference} to an {@code BasicAttribute}.
     *
     * @param reference the reference to convert
     *
     * @return a new attribute
     */
    public static BasicAttribute from(BasicReference reference) {
        BasicAttribute attribute = new BasicAttribute(reference.name());
        attribute.id(reference.id());
        attribute.index(reference.index());
        attribute.value(reference.idReference().value());
        return attribute;
    }

    /**
     * Returns the value of this attribute.
     *
     * @return the value
     */
    public String value() {
        return value;
    }

    /**
     * Defines the value of this attribute.
     *
     * @param value the value
     */
    public void value(String value) {
        this.value = value;
    }

    @Override
    public boolean isAttribute() {
        return true;
    }
}
