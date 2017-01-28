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

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * A {@link Reader} that uses a StAX implementation with iterators for reading and parsing XMI files.
 */
public class XmiStAXIteratorReader extends AbstractXmiReader {

    @Override
    public void read(InputStream stream) throws IOException {
        super.read(stream);

        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);

        try {
            read(factory.createXMLEventReader(factory.createXMLStreamReader(stream)));
        }
        catch (Exception e) {
            throw new IOException(e);
        }
        finally {
            progress(100);
        }
    }

    /**
     * Reads the XMI file by using iterator-style.
     *
     * @param reader the reader to browse
     *
     * @throws XMLStreamException if there is an error with the underlying XML
     */
    private void read(XMLEventReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isStartDocument()) {
                processStartDocument();
            }
            else if (event.isStartElement()) {
                StartElement element = event.asStartElement();

                Iterable<Namespace> namespaces = element::getNamespaces;
                for (Namespace ns : namespaces) {
                    processNamespace(ns.getPrefix(), ns.getNamespaceURI());
                }

                QName qName = element.getName();
                processStartElement(qName.getNamespaceURI(), qName.getLocalPart(), element::getAttributes);
            }
            else if (event.isEndElement()) {
                EndElement element = event.asEndElement();
                QName qName = element.getName();
                processEndElement(qName.getNamespaceURI(), qName.getLocalPart());
            }
            else if (event.isEndDocument()) {
                processEndDocument();
            }
            else if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (!characters.isWhiteSpace() && !characters.isIgnorableWhiteSpace()) {
                    processCharacters(characters.getData());
                }
            }
        }
    }
}
