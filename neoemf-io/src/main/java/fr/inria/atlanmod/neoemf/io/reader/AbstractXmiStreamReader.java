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
import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicElement;
import fr.inria.atlanmod.neoemf.io.structure.BasicFeature;
import fr.inria.atlanmod.neoemf.io.structure.BasicId;
import fr.inria.atlanmod.neoemf.io.structure.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.structure.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;
import fr.inria.atlanmod.neoemf.io.util.XmiConstants;
import fr.inria.atlanmod.neoemf.io.util.XmlConstants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
    private static final String REGEX_PREFIXED_VALUE = "(\\w+):(\\w+)";

    /**
     * Pattern of a prefixed value.
     * <p>
     * Example of recognized strings : {@code "&lt;prefix&gt;:&lt;name&gt;"}
     */
    private static final Pattern PATTERN_PREFIXED_VALUE = Pattern.compile(REGEX_PREFIXED_VALUE);

    /**
     * Whether the current element has to be ignored.
     * <p>
     * Used when a special or unmanaged feature is encountered.
     */
    private boolean ignoreElement = false;

    /**
     * The current element.
     */
    private BasicElement currentElement;

    /**
     * A collection that holds all attributes of the {@link #currentElement}.
     */
    private Collection<BasicFeature> currentAttributes;

    /**
     * A collection that holds all references of the {@link #currentElement}.
     */
    private Collection<BasicFeature> currentReferences;

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
        currentAttributes = new ArrayList<>();
        currentReferences = new ArrayList<>();
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
            List<BasicFeature> features = processFeatures(prefix, name, value);

            if (ignoreElement) {
                // No need to go further
                return;
            }

            if (!features.isEmpty()) {
                if (features.get(0).isAttribute()) {
                    currentAttributes.addAll(features);
                }
                else {
                    currentReferences.addAll(features);
                }
            }
        }
    }

    /**
     * Finalizes the current element and sends notifications to handlers.
     */
    protected void flushStartElement() {
        if (!ignoreElement) {
            // Notifies the current element
            notifyStartElement(currentElement);

            // Notifies the features
            currentAttributes.stream()
                    .map(BasicAttribute.class::cast)
                    .forEach(this::notifyAttribute);

            currentReferences.stream()
                    .map(BasicReference.class::cast)
                    .forEach(this::notifyReference);

            // Reset the current element/features
            currentElement = null;
            currentAttributes = null;
            currentReferences = null;
        }
    }

    /**
     * Processes the end of an element.
     */
    protected void readEndElement() {
        if (!ignoreElement) {
            notifyEndElement();
        }
        else {
            ignoreElement = false;
        }
    }

    /**
     * Processes a feature, which can be an attribute or a reference.
     *
     * @param prefix the prefix of the feature
     * @param name   the name of the feature
     * @param value  the value of the feature
     *
     * @return a list of {@link BasicFeature} that can be empty.
     *
     * @see #processAttributes(String, String)
     */
    @Nonnull
    private List<BasicFeature> processFeatures(@Nullable String prefix, String name, String value) {
        return !processSpecialFeature(prefix, name, value)
                ? processAttributes(name, value)
                : Collections.emptyList();
    }

    /**
     * Processes a special feature as 'xsi:type', 'xmi:id' or 'xmi:idref'.
     *
     * @param prefix the prefix of the feature
     * @param name   the name of the feature
     * @param value  the value of the feature
     *
     * @return {@code true} if the given feature is a special feature
     */
    private boolean processSpecialFeature(@Nullable String prefix, String name, String value) {
        boolean isSpecialFeature = false;

        // A special feature always has a prefix
        if (nonNull(prefix) && !prefix.isEmpty()) {
            final String prefixedValue = XmlConstants.format(prefix, name);

            if (prefixedValue.matches(XmiConstants.XMI_XSI_TYPE)) {
                processMetaClass(value);
                isSpecialFeature = true;
            }
            else if (Objects.equals(XmiConstants.XMI_ID, prefixedValue)) {
                currentElement.id(BasicId.original(value));
                isSpecialFeature = true;
            }
            else if (Objects.equals(XmiConstants.XMI_IDREF, prefixedValue)) {
                // It's not a feature of the current element, but a reference of the previous
                BasicReference reference = new BasicReference(currentElement.name());
                reference.idReference(BasicId.original(value));
                notifyReference(reference);
                ignoreElement = true;
                isSpecialFeature = true;
            }
            else if (Objects.equals(XmiConstants.XMI_VERSION_ATTR, prefixedValue)) {
                Log.debug("XMI version : " + value);
                isSpecialFeature = true;
            }
        }
        else if (Objects.equals(XmiConstants.HREF, name)) {
            Log.warn("{0} is an external reference to {1}. This feature is not supported yet.", currentElement.name(), value);
            ignoreElement = true;
        }
        else if (Objects.equals(XmiConstants.NAME, name)) {
            currentElement.className(value);
            isSpecialFeature = true;
        }

        return isSpecialFeature;
    }

    /**
     * Processes an attribute.
     *
     * @param name  the name of the attribute
     * @param value the value of the attribute
     *
     * @return a singleton list of {@link BasicFeature} containing the processed attribute.
     */
    @Nonnull
    private List<BasicFeature> processAttributes(String name, String value) {
        List<BasicFeature> features = new ArrayList<>();

        BasicAttribute attribute = new BasicAttribute(name);
        attribute.index(0);
        attribute.value(value);

        features.add(attribute);

        return features;
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

            BasicMetaclass metaClass = new BasicMetaclass(ns, name);
            currentElement.metaclass(metaClass);
        }
        else {
            throw new IllegalArgumentException("Malformed metaclass " + prefixedValue);
        }
    }
}
