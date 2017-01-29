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

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.evt.XMLEventFactory2;

import java.io.InputStream;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;

/**
 * A {@link Reader} that uses a StAX implementation with cursors for reading and parsing XMI files.
 */
public class XmiStAXCursorReader extends AbstractXmiReader {

    @Override
    public void run(InputStream stream) throws Exception {
        XMLInputFactory factory = XMLInputFactory2.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);

        read(factory.createXMLStreamReader(stream));
    }

    /**
     * Reads the XMI file with a cursor parser using cursors.
     *
     * @param reader the reader to browse
     *
     * @throws XMLStreamException if there is an error with the underlying XML
     */
    private void read(XMLStreamReader reader) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory2.newFactory();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamReader.START_DOCUMENT) {
                readStartDocument();
            }
            else if (event == XMLStreamReader.START_ELEMENT) {
                int namespaceCount = reader.getNamespaceCount();
                if (namespaceCount > 0) {
                    IntStream.range(0, namespaceCount).forEach(i ->
                            readNamespace(reader.getNamespacePrefix(i), reader.getNamespaceURI(i)));
                }

                Iterable<Attribute> attributes;
                int attributeCount = reader.getAttributeCount();
                if (attributeCount > 0) {
                    attributes = IntStream.range(0, attributeCount)
                            .mapToObj(i -> eventFactory.createAttribute(
                                    reader.getAttributePrefix(i),
                                    reader.getAttributeNamespace(i),
                                    reader.getAttributeLocalName(i),
                                    reader.getAttributeValue(i)))
                            .collect(Collectors.toList());
                }
                else {
                    attributes = Collections.emptyList();
                }

                readStartElement(reader.getNamespaceURI(), reader.getLocalName(), attributes);
            }
            else if (event == XMLStreamReader.END_ELEMENT) {
                readEndElement();
            }
            else if (event == XMLStreamReader.END_DOCUMENT) {
                readEndDocument();
            }
            else if (event == XMLStreamReader.CHARACTERS) {
                if (reader.getTextLength() > 0 && !reader.isWhiteSpace()) {
                    processCharacters(reader.getText());
                }
            }
        }
    }
}
