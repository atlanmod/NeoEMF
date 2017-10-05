/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.util;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A utility class that contains all the constants used in an XMI file.
 */
@ParametersAreNonnullByDefault
public interface XmiConstants extends XmlConstants {

    /**
     * The namespace prefix of XMI.
     */
    String XMI_NS = "xmi";

    /**
     * The namespace URI of XMI.
     */
    String XMI_URI = "http://www.omg.org/XMI";

    /**
     * The attribute key representing the identifier of an element.
     */
    String XMI_ID = XmlConstants.format(XMI_NS, "id");

    /**
     * The attribute key representing a reference to an identified element.
     *
     * @see #XMI_ID
     */
    String XMI_IDREF = XmlConstants.format(XMI_NS, "idref");

    /**
     * The default attribute key representing the meta-class of an element.
     */
    String XMI_TYPE = XmlConstants.format(XMI_NS, TYPE);

    /**
     * The attribute key representing the meta-class of an element.
     */
    String XMI_XSI_TYPE = XmlConstants.format('(' + XMI_NS + '|' + XSI_NS + ')', TYPE);

    /**
     * The attribute key representing the version of the parsed XMI file.
     */
    String XMI_VERSION_ATTR = XmlConstants.format(XMI_NS, "version");

    /**
     * The default XMI version.
     */
    String XMI_VERSION = "2.0";
}
