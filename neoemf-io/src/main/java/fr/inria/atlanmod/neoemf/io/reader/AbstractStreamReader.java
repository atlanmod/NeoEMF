/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyClass;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyPackage;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyValue;

import org.atlanmod.commons.primitive.Strings;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Preconditions.checkArgument;
import static org.atlanmod.commons.Preconditions.checkNotNull;
import static org.atlanmod.commons.Preconditions.checkState;

/**
 * An abstract {@link Reader} that reads data from an {@link InputStream}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractStreamReader extends AbstractReader<InputStream> {

    /**
     * Pattern of a prefixed value.
     * <p>
     * Example of recognized strings : {@code "&lt;prefix&gt;:&lt;name&gt;"}
     */
    @Nonnull
    private static final Pattern PATTERN_PREFIXED_VALUE = Pattern.compile("(\\w+):(\\w+)");

    /**
     * A LIFO that holds the current {@link Id} chain. It contains the current identifier and the previous.
     */
    @Nonnull
    private final Deque<Id> identifiers = new ArrayDeque<>();

    /**
     * A collection that holds all attributes of the {@link #currentElement}.
     */
    @Nonnull
    private final Deque<ProxyAttribute> currentAttributes = new ArrayDeque<>();

    /**
     * The current element.
     *
     * @see #readStartElement(String, String)
     * @see #flushCurrentElement()
     */
    private ProxyElement currentElement;

    /**
     * Whether the current element has to be ignored.
     * <p>
     * Used when a special or unmanaged feature is encountered.
     */
    private boolean ignoredElement;

    @Override
    public final void read(InputStream source) throws IOException {
        checkNotNull(source, "source");

        try (InputStream in = new BufferedInputStream(source)) {
            parse(in);
        }
        finally {
            ProxyPackage.Registry.getInstance().clean();
        }
    }

    /**
     * Parses the content of the {@code stream}.
     *
     * @param stream the stream to read
     *
     * @throws IOException if an error occurred during the I/O process
     */
    public abstract void parse(InputStream stream) throws IOException;

    /**
     * Returns the identifier of the previous element.
     *
     * @return the identifier of the previous element
     */
    @Nonnull
    public Id getPreviousId() {
        return identifiers.getLast();
    }

    /**
     * Returns the current element.
     *
     * @return the current element (mutable)
     */
    @Nonnull
    public ProxyElement getCurrentElement() {
        return currentElement;
    }

    /**
     * Asks to ignore the current element. If an element is ignored, the element and its content will no be notified to
     * next handlers.
     */
    public void ignoreCurrentElement() {
        this.ignoredElement = true;
    }

    /**
     * Reads the start of the document.
     */
    protected final void readStartDocument() throws IOException {
        notifyInitialize();
    }

    /**
     * Reads the start of a new element.
     * <p>
     * <b>Note:</b> An element must be flushed with the {@link #flushCurrentElement()} method after analysing all its
     * attributes.
     *
     * @param uri  the namespace's URI of the element
     * @param name the name of the element
     */
    protected final void readStartElement(@Nullable String uri, String name) {
        currentElement = new ProxyElement().setName(name);

        Optional.ofNullable(ProxyPackage.Registry.getInstance().getByUri(Strings.emptyToNull(uri)))
                .map(ProxyClass::new)
                .ifPresent(c -> currentElement.setMetaClass(c));

        currentAttributes.clear();
    }

    /**
     * Reads a new simple element, as {@code <name>value</name>}.
     *
     * @param uri   the namespace's URI of the element
     * @param name  the name of the element
     * @param value the literal value of the element
     */
    protected final void readSimpleElement(@Nullable String uri, String name, String value) throws IOException {
        checkState(!identifiers.isEmpty(), "Trying to process an empty root element");

        notifyAttribute(new ProxyAttribute().setName(name).setValue(ProxyValue.raw(value)));
    }

    /**
     * Reads a {@link ProxyPackage} declaration.
     *
     * @param prefix the prefix of the namespace
     * @param uri    the URI of the namespace
     *
     * @see ProxyPackage.Registry#register(String, String)
     */
    protected final void readNamespace(String prefix, String uri) {
        ProxyPackage.Registry.getInstance().register(prefix, uri);
    }

    /**
     * Finalizes the current element and sends all delayed notifications to handlers.
     */
    protected final void flushCurrentElement() throws IOException {
        if (ignoredElement) {
            return;
        }

        notifyStartElement(currentElement);
        identifiers.addLast(currentElement.getId().getResolved());

        for (ProxyAttribute a : currentAttributes) {
            notifyAttribute(a);
        }
    }

    /**
     * Reads a new attribute of the current element.
     *
     * @param prefix the namespace's prefix of the attribute
     * @param name   the name of the attribute
     * @param value  the value of the attribute
     */
    protected final void readAttribute(@Nullable String prefix, String name, String value) throws IOException {
        if (ignoredElement || isSpecialAttribute(prefix, name, value)) {
            return;
        }

        currentAttributes.add(new ProxyAttribute().setName(name).setValue(ProxyValue.raw(value)));
    }

    /**
     * Reads a meta-class attribute from the {@code prefixedValue}, and defines is as the meta-class of the given {@code
     * element}.
     *
     * @param prefixedValue the value representing the meta-class
     *
     * @see #PATTERN_PREFIXED_VALUE
     */
    protected final void readMetaClass(String prefixedValue) {
        Matcher m = PATTERN_PREFIXED_VALUE.matcher(prefixedValue);
        checkArgument(m.find(), "Malformed meta-class %s", prefixedValue);

        ProxyPackage ns = ProxyPackage.Registry.getInstance().getByPrefix(m.group(1));
        String name = m.group(2);

        currentElement.setMetaClass(new ProxyClass(ns, name));
    }

    /**
     * Reads the end of an element.
     */
    protected final void readEndElement() throws IOException {
        if (ignoredElement) {
            ignoredElement = false;
            return;
        }

        notifyEndElement();
        identifiers.removeLast();
    }

    /**
     * Reads the end of the current document.
     */
    protected final void readEndDocument() throws IOException {
        notifyComplete();

        checkState(identifiers.isEmpty(), "Stream analysis is complete, but some elements are not complete: %s", identifiers);
    }

    /**
     * Defines if the attribute represented with the parameters is a special attribute as 'xsi:type', 'xmi:id' or
     * 'xmi:idref'.
     *
     * @param prefix the namespace's prefix of the attribute
     * @param name   the name of the attribute
     * @param value  the value of the attribute
     *
     * @return {@code true} if the given feature is a special feature
     */
    protected abstract boolean isSpecialAttribute(@Nullable String prefix, String name, String value) throws IOException;
}
