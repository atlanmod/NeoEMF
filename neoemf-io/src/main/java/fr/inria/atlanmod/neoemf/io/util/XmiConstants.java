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
import org.atlanmod.commons.primitive.Strings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A utility class that contains all the constants used in an XMI file.
 */
@Static
@ParametersAreNonnullByDefault
public final class XmiConstants {

    /**
     * The default encoding of XML files.
     */
    public static final String ENCODING = "utf-8";

    /**
     * The default version of XML files.
     */
    public static final String VERSION = "1.0";

    /**
     * The namespace prefix of XSI.
     */
    public static final String XSI_NS = "xsi";

    /**
     * The attribute key representing a {@code null} element.
     */
    public static final String TYPE = "type";

    /**
     * The attribute key representing a link to another document.
     */
    public static final String HREF = "href";

    /**
     * The namespace prefix of XMI.
     */
    public static final String XMI_NS = "xmi";

    /**
     * The namespace URI of XMI.
     */
    public static final String XMI_URI = "http://www.omg.org/XMI";

    /**
     * The attribute key representing the identifier of an element.
     */
    public static final String XMI_ID = format(XMI_NS, "id");

    /**
     * The attribute key representing a reference to an identified element.
     *
     * @see #XMI_ID
     */
    public static final String XMI_IDREF = format(XMI_NS, "idref");

    /**
     * The default attribute key representing the meta-class of an element.
     */
    public static final String XMI_TYPE = format(XMI_NS, TYPE);

    /**
     * The attribute key representing the meta-class of an element.
     */
    public static final String XMI_XSI_TYPE = format('(' + XMI_NS + '|' + XSI_NS + ')', TYPE);

    /**
     * The attribute key representing the version of the parsed XMI file.
     */
    public static final String XMI_VERSION_ATTR = format(XMI_NS, "version");

    /**
     * The default XMI version.
     */
    public static final String XMI_VERSION = "2.0";

    private XmiConstants() {
        throw Throwables.notInstantiableClass(getClass());
    }

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
    public static String format(@Nullable String prefix, String value) {
        checkNotNull(value, "value");

        return (isNull(prefix) ? Strings.EMPTY : prefix + ':') + value;
    }
}
