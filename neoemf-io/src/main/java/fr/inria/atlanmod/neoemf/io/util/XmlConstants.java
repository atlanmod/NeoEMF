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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A utility class that contains all the constants used in an XML file.
 */
public interface XmlConstants {

    /**
     * The default encoding of XML files.
     */
    String ENCODING = "utf-8";

    /**
     * The default version of XML files.
     */
    String VERSION = "1.0";

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

    /**
     * Formats a prefixed value as {@code "prefix:value"}. If the {@code prefix} is {@code null}, the returned value
     * only contains the {@code value}.
     *
     * @param prefix the prefix of the value
     * @param value  the value
     *
     * @return the formatted value as {@code "prefix:value"}
     */
    @Nonnull
    static String format(@Nullable String prefix, String value) {
        checkNotNull(value);

        return (isNull(prefix) ? "" : prefix + ':') + value;
    }
}
