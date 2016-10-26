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

package fr.inria.atlanmod.neoemf.io.beans;

/**
 * The identifier of a {@link Classifier classifier}.
 */
public class Identifier {

    public static Identifier original(String value) {
        return new Identifier(value, false);
    }

    public static Identifier generated(String value) {
        return new Identifier(value, true);
    }

    private String value;
    private boolean generated;

    private Identifier(String value, boolean generated) {
        this.value = value;
        this.generated = generated;
    }

    public String getValue() {
        return value;
    }

    public boolean isGenerated() {
        return generated;
    }

    @Override
    public String toString() {
        return value;
    }
}
