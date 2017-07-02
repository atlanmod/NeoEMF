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

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicId;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.io.util.XmiConstants;
import fr.inria.atlanmod.neoemf.io.util.XmlConstants;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.RegEx;

import static java.util.Objects.nonNull;

/**
 * An abstract {@link StreamReader} that processes the raw structure of XMI files.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractXmiStreamReader extends AbstractStreamReader {

    /**
     * Regular expression of a prefixed value.
     */
    @RegEx
    @Nonnull
    private static final String REGEX_PREFIXED_VALUE = "(\\w+):(\\w+)";

    /**
     * Pattern of a prefixed value.
     * <p>
     * Example of recognized strings : {@code "&lt;prefix&gt;:&lt;name&gt;"}
     */
    @Nonnull
    private static final Pattern PATTERN_PREFIXED_VALUE = Pattern.compile(REGEX_PREFIXED_VALUE);

    /**
     * Whether the current element has to be ignored.
     * <p>
     * Used when a special or unmanaged feature is encountered.
     */
    private boolean ignoreElement = false;

    /**
     * A LIFO that holds the current {@link BasicId} chain. It contains the current identifier and the previous.
     */
    private Deque<BasicId> previousIds = new ArrayDeque<>();

    /**
     * The current element.
     */
    private BasicElement currentElement;

    /**
     * A collection that holds all attributes of the {@link #currentElement}.
     */
    @Nonnull
    private Collection<BasicAttribute> currentAttributes = new ArrayList<>();

    /**
     * Constructs a new {@code AbstractXmiStreamReader} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public AbstractXmiStreamReader(Handler handler) {
        super(handler);
    }

    /**
     * Starts a new element.
     * <p>
     * <b>Note:</b> An element must be flushed with the {@link #flushStartElement()} method after analysing all its
     * attributes.
     *
     * @param uri  the URI of the element
     * @param name the name of the element
     */
    protected void readStartElement(String uri, String name) {
        currentElement = new BasicElement(BasicNamespace.Registry.getInstance().getFromUri(uri), name);
    }

    /**
     * Reads a new attribute of the current element.
     *
     * @param prefix the prefix of the attribute
     * @param name   the name of the attribute
     * @param value  the value of the attribute
     */
    protected void readAttribute(@Nullable String prefix, String name, String value) {
        if (!ignoreElement) {
            processAttribute(prefix, name, value);
        }
    }

    /**
     * Finalizes the current element and sends notifications to handlers.
     */
    protected void flushStartElement() {
        if (!ignoreElement) {
            // Notifies the current element
            notifyStartElement(currentElement);
            previousIds.addLast(currentElement.id());

            // Notifies the attributes
            currentAttributes.forEach(this::notifyAttribute);
        }

        // Reset the current element and its attribute
        currentElement = null;
        currentAttributes.clear();
    }

    /**
     * Processes the end of an element.
     */
    protected void readEndElement() {
        if (!ignoreElement) {
            notifyEndElement();
        }

        ignoreElement = false;
        previousIds.removeLast();
    }

    /**
     * Processes an attribute.
     *
     * @param prefix the prefix of the attribute
     * @param name   the name of the attribute
     * @param value  the value of the attribute
     *
     * @see #isSpecialAttribute(String, String, String)
     */
    private void processAttribute(@Nullable String prefix, String name, String value) {
        if (!isSpecialAttribute(prefix, name, value)) {
            BasicAttribute attribute = new BasicAttribute(name);
            attribute.index(0);
            attribute.value(value);

            currentAttributes.add(attribute);
        }
    }

    /**
     * Processes a special attribute as 'xsi:type', 'xmi:id' or 'xmi:idref'.
     *
     * @param prefix the prefix of the feature
     * @param name   the name of the feature
     * @param value  the value of the feature
     *
     * @return {@code true} if the given feature is a special feature
     */
    private boolean isSpecialAttribute(@Nullable String prefix, String name, String value) {
        if (Objects.equals(XmiConstants.NAME, name)) { // The instance of the current element
            currentElement.className(value);

            return true;
        }

        if (Objects.equals(XmiConstants.HREF, name)) { // A link to an external resource
            Log.warn("{0} is an external reference to {1}. This feature is not supported yet.", currentElement.name(), value);

            ignoreElement = true;
            return true;
        }

        if (nonNull(prefix) && !prefix.isEmpty()) {
            final String prefixedValue = XmlConstants.format(prefix, name);

            if (Objects.equals(XmiConstants.XMI_IDREF, prefixedValue)) { // A reference of the previous element
                BasicReference reference = new BasicReference(currentElement.name());
                reference.id(previousIds.getLast());
                reference.idReference(BasicId.original(value));
                notifyReference(reference);

                ignoreElement = true;
                return true;
            }

            if (Objects.equals(XmiConstants.XMI_ID, prefixedValue)) { // The identifier of the current element
                currentElement.id(BasicId.original(value));

                return true;
            }

            if (Objects.equals(XmiConstants.XMI_VERSION_ATTR, prefixedValue)) { // The version of the read XMI file
                return true;
            }

            if (prefixedValue.matches(XmiConstants.XMI_XSI_TYPE)) { // The metaclass of the current element
                processMetaClass(value);

                return true;
            }
        }

        return false;
    }

    /**
     * Processes a metaclass attribute from the {@code prefixedValue}, and defines is as the metaclass of the given
     * {@code element}.
     *
     * @param prefixedValue the value representing the metaclass
     *
     * @see #PATTERN_PREFIXED_VALUE
     */
    private void processMetaClass(String prefixedValue) {
        Matcher m = PATTERN_PREFIXED_VALUE.matcher(prefixedValue);
        if (m.find()) {
            BasicNamespace ns = BasicNamespace.Registry.getInstance().getFromPrefix(m.group(1));
            String name = m.group(2);

            currentElement.metaclass(new BasicMetaclass(ns, name));
        }
        else {
            throw new IllegalArgumentException("Malformed metaclass " + prefixedValue);
        }
    }
}
