/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.util;

/**
 * A utility class that contains all the constants used in an XPath.
 */
public interface XPathConstants {

    /**
     * The string representing the start of a XPath expression.
     */
    String START_EXPR = "//@";

    /**
     * The string representing the start of a XPath segment.
     */
    String START_ELT = "/@";

    /**
     * The string representing the end of a XPath segment.
     */
    String END_EXPR = "/";

    /**
     * The character used as separator between the name and the index of a XPath segment.
     */
    String INDEX_SEPARATOR = ".";
}
