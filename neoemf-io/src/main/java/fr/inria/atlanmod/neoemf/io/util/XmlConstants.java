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

package fr.inria.atlanmod.neoemf.io.util;

/**
 * A utility class that contains all the constants used in an XML file.
 */
public interface XmlConstants {

    /**
     * The namespace prefix of XML.
     */
    String XML_NS = "xmlns";

    /**
     * The namespace URI of XML Schema.
     */
    String XML_URI = "http://www.w3.org/2001/XMLSchema";

    /**
     * The namespace prefix of XSI.
     */
    String XSI_NS = "xsi";

    /**
     * The namespace URI of XSI.
     */
    String XSI_URI = "http://www.w3.org/2001/XMLSchema-instance";

    /**
     * The attribute key representing a {@code null} element.
     */
    String TYPE = "type";

    /**
     * The attribute key representing a {@code null} element.
     */
    String NIL = "nil";

    /**
     * The attribute key representing a link to another document.
     */
    String HREF = "href";
}
