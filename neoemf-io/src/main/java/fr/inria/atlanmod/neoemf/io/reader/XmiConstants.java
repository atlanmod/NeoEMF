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

package fr.inria.atlanmod.neoemf.io.reader;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A utility class that contains all the constants used in an XMI file.
 */
public interface XmiConstants {

    /**
     * The namespace prefix of XSI.
     */
    String XSI_NS = "xsi";

    /**
     * The namespace prefix of XMI.
     */
    String XMI_NS = "xmi";

    /**
     * The attribute key representing the identifier of an element.
     */
    String XMI_ID = format(XMI_NS, "id");

    /**
     * The attribute key representing a reference to an identified element.
     *
     * @see #XMI_ID
     */
    String XMI_IDREF = format(XMI_NS, "idref");

    /**
     * The attribute key representing the metaclass of an element.
     */
    String XMI_XSI_TYPE = format("(" + XMI_NS + "|" + XSI_NS + ")", "type");

    /**
     * The attribute key representing the version of the parsed XMI file.
     */
    String XMI_VERSION_ATTR = format(XMI_NS, "version");

    /**
     * The attribute key representing a link to another document.
     */
    String PROXY = "href";

    /**
     * The attribute key representing a name of an element.
     */
    String NAME = "name";

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
    static String format(@Nullable String prefix, @Nonnull String value) {
        checkNotNull(value);

        return (isNull(prefix) ? "" : prefix + ':') + value;
    }
}
