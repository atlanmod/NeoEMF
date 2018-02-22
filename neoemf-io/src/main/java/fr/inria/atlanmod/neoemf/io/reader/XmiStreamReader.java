/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.reader;

import com.ctc.wstx.api.WstxInputProperties;

import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.io.util.XmiConstants;

import org.codehaus.stax2.XMLInputFactory2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import static fr.inria.atlanmod.commons.Preconditions.checkState;
import static javax.xml.stream.XMLStreamConstants.CHARACTERS;
import static javax.xml.stream.XMLStreamConstants.END_ELEMENT;
import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

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
    private int event;

    /**
     * {@code true} if the next call to {@link XMLStreamReader#next()} must be skipped.
     */
    private boolean skipNext;

    @Override
    public void parse(InputStream stream) throws IOException {
        XMLInputFactory factory = XMLInputFactory2.newInstance();
        configure(factory);

        try (InputStream in = new BufferedInputStream(stream)) {
            reader = factory.createXMLStreamReader(in, XmiConstants.ENCODING);

            readStartDocument();
            while (reader.hasNext()) {
                event = readNextEvent();
                if (event == START_ELEMENT) {
                    readStartElement();
                }
                else if (event == END_ELEMENT) {
                    readEndElement();
                }
            }
            readEndDocument();

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
     * @throws XMLStreamException if there is an error with the underlying XML
     */
    private int readNextEvent() throws XMLStreamException {
        if (skipNext) {
            skipNext = false;
            return event;
        }
        else {
            return reader.next();
        }
    }

    /**
     * Reads the start of an element from the current reader.
     *
     * @throws XMLStreamException if there is an error with the underlying XML
     */
    private void readStartElement() throws IOException, XMLStreamException {
        for (int i = 0, size = reader.getNamespaceCount(); i < size; i++) {
            readNamespace(reader.getNamespacePrefix(i), reader.getNamespaceURI(i));
        }

        final String uri = reader.getNamespaceURI();
        final String name = reader.getLocalName();

        int attributeCount = reader.getAttributeCount();

        if (attributeCount > 0) {
            // The element has at least one attribute: it's a complex element
            readStartElement(uri, name);

            for (int i = 0; i < attributeCount; i++) {
                readAttribute(reader.getAttributePrefix(i), reader.getAttributeLocalName(i), reader.getAttributeValue(i));
            }

            flushCurrentElement();
        }
        else {
            event = reader.next();
            skipNext = true;

            if (event == CHARACTERS) {
                final String characters = reader.getText();

                checkState(reader.next() == END_ELEMENT, "The next event should have been END_ELEMENT");
                skipNext = false;

                readSimpleElement(uri, name, characters);
            }
            else if (event == END_ELEMENT) {
                skipNext = false;

                readSimpleElement(uri, name, Strings.EMPTY);
            }
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
