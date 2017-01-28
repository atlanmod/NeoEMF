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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;

/**
 * A {@link Reader} that uses a StAX implementation with cursors for reading and parsing XMI files.
 */
public class XmiStAXCursorReader extends AbstractXmiReader {

    @Override
    public void read(InputStream stream) throws IOException {
        super.read(stream);

        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);

        try {
            read(factory.createXMLStreamReader(stream));
        }
        catch (Exception e) {
            throw new IOException(e);
        }
        finally {
            progress(100);
        }
    }

    /**
     * Reads the XMI file with a cursor parser using cursors.
     *
     * @param reader the reader to browse
     *
     * @throws XMLStreamException if there is an error with the underlying XML
     */
    private void read(XMLStreamReader reader) throws XMLStreamException {

        XMLEventFactory factory = XMLEventFactory.newFactory();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_DOCUMENT) {
                processStartDocument();
            }
            else if (event == XMLStreamConstants.START_ELEMENT) {
                int namespaceCount = reader.getNamespaceCount();
                if (namespaceCount > 0) {
                    IntStream.range(0, namespaceCount).forEach(i ->
                            processNamespace(reader.getNamespacePrefix(i), reader.getNamespaceURI(i)));
                }

                Iterable<Attribute> attributes;
                int attributeCount = reader.getAttributeCount();
                if (attributeCount > 0) {
                    attributes = IntStream.range(0, attributeCount)
                            .mapToObj(i -> factory.createAttribute(
                                    reader.getAttributePrefix(i),
                                    reader.getAttributeNamespace(i),
                                    reader.getAttributeLocalName(i),
                                    reader.getAttributeValue(i)))
                            .collect(Collectors.toList());
                }
                else {
                    attributes = Collections.emptyList();
                }
                processStartElement(reader.getNamespaceURI(), reader.getLocalName(), attributes);
            }
            else if (event == XMLStreamConstants.END_ELEMENT) {
                processEndElement(reader.getNamespaceURI(), reader.getLocalName());
            }
            else if (event == XMLStreamConstants.END_DOCUMENT) {
                progress(100);
                processEndDocument();
            }
            else if (event == XMLStreamConstants.CHARACTERS) {
                if (reader.getTextLength() > 0 && !reader.isWhiteSpace()) {
                    processCharacters(reader.getText());
                }
            }
        }
    }
}
