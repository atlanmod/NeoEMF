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

import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicNamespace;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

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
    private final Deque<Id> previousIds = new ArrayDeque<>();

    /**
     * The current element.
     *
     * @see #readStartElement(String, String)
     * @see #flushCurrentElement()
     */
    private BasicElement currentElement;

    /**
     * A collection that holds all attributes of the {@link #currentElement}.
     */
    private Deque<BasicAttribute> currentAttributes;

    /**
     * Whether the current element has to be ignored.
     * <p>
     * Used when a special or unmanaged feature is encountered.
     */
    private boolean ignoreElement;

    /**
     * Constructs a new {@code AbstractStreamReader} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public AbstractStreamReader(Handler handler) {
        super(handler);
    }

    @Override
    public final void read(InputStream source) throws IOException {
        checkNotNull(source);

        try {
            run(new BufferedInputStream(source));
        }
        catch (Exception e) {
            throw new IOException(e);
        }
        finally {
            BasicNamespace.Registry.getInstance().clean();
        }
    }

    /**
     * Runs the reading on the {@code stream}.
     *
     * @param stream the stream to read
     *
     * @throws Exception if an error occurred during the I/O process
     */
    public abstract void run(InputStream stream) throws Exception;

    /**
     * Returns the identifier of the previous element.
     *
     * @return the identifier of the previous element
     */
    @Nonnull
    public Id getPreviousId() {
        return previousIds.getLast();
    }

    /**
     * Returns the current element.
     *
     * @return the current element (mutable)
     */
    @Nonnull
    public BasicElement getCurrentElement() {
        return currentElement;
    }

    /**
     * Asks to ignore the current element. If an element is ignored, the element and its content will no be notified to
     * next handlers.
     */
    public void ignoreCurrentElement() {
        this.ignoreElement = true;
    }

    /**
     * Reads the start of the document.
     */
    protected final void readStartDocument() {
        notifyInitialize();
    }

    /**
     * Reads a new element.
     * <p>
     * <b>Note:</b> An element must be flushed with the {@link #flushCurrentElement()} method after analysing all its
     * attributes.
     *
     * @param uri  the URI of the element
     * @param name the name of the element
     */
    protected final void readStartElement(@Nullable String uri, String name) {
        currentElement = new BasicElement();
        currentElement.name(name);

        BasicNamespace ns = BasicNamespace.Registry.getInstance().getFromUri(Strings.emptyToNull(uri));
        if (nonNull(ns)) {
            currentElement.metaClass(new BasicMetaclass(ns));
        }

        currentAttributes = new ArrayDeque<>();
    }

    /**
     * Reads a {@link BasicNamespace} declaration.
     *
     * @param prefix the prefix
     * @param uri    the URI associated with the {@code prefix}
     *
     * @see BasicNamespace.Registry#register(String, String)
     */
    protected final void readNamespace(String prefix, String uri) {
        BasicNamespace.Registry.getInstance().register(prefix, uri);
    }

    /**
     * Finalizes the current element and sends all delayed notifications to handlers.
     */
    protected final void flushCurrentElement() {
        if (!ignoreElement) {
            // Notifies the current element
            notifyStartElement(currentElement);
            previousIds.addLast(currentElement.id());

            // Notifies the attributes
            currentAttributes.forEach(this::notifyAttribute);
        }
    }

    /**
     * Reads a new attribute of the current element.
     *
     * @param prefix the prefix of the attribute
     * @param name   the name of the attribute
     * @param value  the value of the attribute
     */
    protected final void readAttribute(@Nullable String prefix, String name, String value) {
        if (!ignoreElement && !isSpecialAttribute(prefix, name, value)) {
            BasicAttribute attribute = new BasicAttribute();
            attribute.name(name);
            attribute.value(value);

            currentAttributes.add(attribute);
        }
    }

    /**
     * Defines if the attribute represented with the parameters is a special attribute as 'xsi:type', 'xmi:id' or
     * 'xmi:idref'.
     *
     * @param prefix the prefix of the attribute
     * @param name   the name of the attribute
     * @param value  the value of the attribute
     *
     * @return {@code true} if the given feature is a special feature
     */
    protected abstract boolean isSpecialAttribute(@Nullable String prefix, String name, String value);

    /**
     * Reads characters.
     *
     * @param characters a set of characters, as {@link String}
     */
    protected final void readCharacters(String characters) {
        notifyCharacters(characters);
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
        if (m.find()) {
            BasicNamespace ns = BasicNamespace.Registry.getInstance().getFromPrefix(m.group(1));
            String name = m.group(2);

            currentElement.metaClass(new BasicMetaclass(ns, name));
        }
        else {
            throw new IllegalArgumentException("Malformed meta-class " + prefixedValue);
        }
    }

    /**
     * Reads the end of an element.
     */
    protected final void readEndElement() {
        if (!ignoreElement) {
            notifyEndElement();
        }

        ignoreElement = false;
        previousIds.removeLast();
    }

    /**
     * Reads the end of the current document.
     */
    protected final void readEndDocument() {
        notifyComplete();
    }
}
