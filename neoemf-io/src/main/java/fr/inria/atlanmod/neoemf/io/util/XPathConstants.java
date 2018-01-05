/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.commons.annotation.Static;

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

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private XPathConstants() {
        throw new IllegalStateException("This class should not be instantiated");
    }
}
