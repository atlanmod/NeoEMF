/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.util;

import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.annotation.Static;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A utility class that contains all the constants used in an XPath.
 */
@Static
@ParametersAreNonnullByDefault
public final class XPathConstants {

    /**
     * The string representing the start of a XPath expression.
     */
    public static final String START_EXPR = "//@";

    /**
     * The string representing the start of a XPath segment.
     */
    public static final String START_ELT = "/@";

    /**
     * The string representing the end of a XPath segment.
     */
    public static final String END_EXPR = "/";

    /**
     * The character used as separator between the name and the index of a XPath segment.
     */
    public static final String INDEX_SEPARATOR = ".";

    private XPathConstants() {
        throw Throwables.notInstantiableClass(getClass());
    }
}
