/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.reader;

import com.ctc.wstx.api.WstxInputProperties;

import fr.inria.atlanmod.neoemf.io.util.XmiConstants;

import org.atlanmod.commons.primitive.Strings;
import org.codehaus.stax2.XMLInputFactory2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;
import static org.atlanmod.commons.Preconditions.checkState;

/**
 * An {@link AbstractXmiStreamReader} that uses a StAX implementation with cursors for reading and parsing XMI files.
 */
@ParametersAreNonnullByDefault
public class XmiStreamReader extends AbstractXmiStreamReader {

    /**
     * The current XML reader.
     */
    private XMLStreamReader reader;

    /**
     * The current event.
     *
     * @see javax.xml.stream.XMLStreamConstants
     */
    @Nonnegative
    private int event;

    @Override
    public void parse(InputStream stream) throws IOException {
        XMLInputFactory factory = XMLInputFactory2.newInstance();
        configure(factory);

        try (InputStream in = new BufferedInputStream(stream)) {
            reader = factory.createXMLStreamReader(in, XmiConstants.ENCODING);

            readStartDocument();
            while (reader.hasNext()) {
                readNextEvent();
                if (event == START_ELEMENT) {
                    readStartElement();
                }
                else if (event == END_ELEMENT) {
                    readEndElement();
                }
            }
            readEndDocument();

            event = 0;
            reader.close();
        }
        catch (XMLStreamException e) {
            throw new IOException(e);
        }
    }

    /**
     * Reads the next event.
     *
     * @return the next event
     *
     * @throws IOException if there is an error processing the XML source
     */
    @Nonnegative
    private int readNextEvent() throws IOException {
        try {
            event = reader.next();
            return event;
        }
        catch (XMLStreamException e) {
            throw new IOException(e);
        }
    }

    /**
     * Reads the start of an element.
     *
     * @throws IOException if there is an error processing the XML source
     */
    private void readStartElement() throws IOException {
        for (int i = 0, size = reader.getNamespaceCount(); i < size; i++) {
            readNamespace(reader.getNamespacePrefix(i), reader.getNamespaceURI(i));
        }

        @Nullable final String uri = reader.getNamespaceURI();
        final String name = reader.getLocalName();

        @Nonnegative final int attributeCount = reader.getAttributeCount();

        if (attributeCount > 0) {
            readComplexElement(uri, name, attributeCount);
        }
        else {
            readSimpleElement(uri, name);
        }
    }

    /**
     * Reads the start of a complex element with its attributes.
     * <p>
     * A complex element is an element that may contain attributes, and other elements.
     *
     * @param uri            the namespace's URI of the element
     * @param name           the name of the element
     * @param attributeCount the number of attributes of the element
     *
     * @throws IOException if there is an error processing the XML source
     */
    private void readComplexElement(@Nullable String uri, String name, @Nonnegative int attributeCount) throws IOException {
        readStartElement(uri, name);

        for (int i = 0; i < attributeCount; i++) {
            readAttribute(reader.getAttributePrefix(i), reader.getAttributeLocalName(i), reader.getAttributeValue(i));
        }

        flushCurrentElement();
    }

    /**
     * Reads a simple element.
     * <p>
     * A simple element is an element without any attribute that may contain characters, but no element.
     *
     * @param uri  the namespace's URI of the element
     * @param name the name of the element
     *
     * @throws IOException if there is an error processing the XML source
     */
    private void readSimpleElement(@Nullable String uri, String name) throws IOException {
        readNextEvent();

        if (event == CHARACTERS) {
            final String characters = reader.getText();

            if (characters.trim().isEmpty()) {
                // Formatting spaces: redirecting
                readComplexElement(uri, name, 0);
            }
            else {
                checkState(readNextEvent() == END_ELEMENT, "Expected event END_ELEMENT but was %s", name, getEventName(event));
                readSimpleElement(uri, name, characters);
            }
        }
        else if (event == END_ELEMENT) {
            readSimpleElement(uri, name, Strings.EMPTY);
        }
    }

    /**
     * Returns the name of the {@code event}.
     *
     * @param event the event
     *
     * @return the name of the event
     */
    @Nonnull
    private String getEventName(@Nonnegative int event) {
        switch (event) {
            case XMLStreamConstants.START_ELEMENT:
                return "START_ELEMENT";
            case XMLStreamConstants.END_ELEMENT:
                return "END_ELEMENT";
            case XMLStreamConstants.PROCESSING_INSTRUCTION:
                return "PROCESSING_INSTRUCTION";
            case XMLStreamConstants.CHARACTERS:
                return "CHARACTERS";
            case XMLStreamConstants.COMMENT:
                return "COMMENT";
            case XMLStreamConstants.SPACE:
                return "SPACE";
            case XMLStreamConstants.START_DOCUMENT:
                return "START_DOCUMENT";
            case XMLStreamConstants.END_DOCUMENT:
                return "END_DOCUMENT";
            case XMLStreamConstants.ENTITY_REFERENCE:
                return "ENTITY_REFERENCE";
            case XMLStreamConstants.ATTRIBUTE:
                return "ATTRIBUTE";
            case XMLStreamConstants.DTD:
                return "DTD";
            case XMLStreamConstants.CDATA:
                return "CDATA";
            case XMLStreamConstants.NAMESPACE:
                return "NAMESPACE";
            case XMLStreamConstants.NOTATION_DECLARATION:
                return "NOTATION_DECLARATION";
            case XMLStreamConstants.ENTITY_DECLARATION:
                return "ENTITY_DECLARATION";
            default:
                return "UNKNOWN (" + event + ')';
        }
    }

    /**
     * Configures the specified {@code factory} with default options.
     *
     * @param factory the XML factory to configure
     */
    private void configure(XMLInputFactory factory) {
        // Use namespace support
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        // Remove constraints
        factory.setProperty(WstxInputProperties.P_MAX_CHARACTERS, Integer.MAX_VALUE);

        factory.setProperty(WstxInputProperties.P_MAX_ELEMENT_COUNT, Integer.MAX_VALUE);
        factory.setProperty(WstxInputProperties.P_MAX_ELEMENT_DEPTH, Integer.MAX_VALUE);
        factory.setProperty(WstxInputProperties.P_MAX_CHILDREN_PER_ELEMENT, Integer.MAX_VALUE);

        factory.setProperty(WstxInputProperties.P_MAX_ATTRIBUTE_SIZE, Integer.MAX_VALUE);
        factory.setProperty(WstxInputProperties.P_MAX_ATTRIBUTES_PER_ELEMENT, Integer.MAX_VALUE);

        factory.setProperty(WstxInputProperties.P_MAX_TEXT_LENGTH, Integer.MAX_VALUE);
    }
}
