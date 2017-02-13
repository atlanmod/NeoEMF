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

import fr.inria.atlanmod.neoemf.io.Handler;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.evt.XMLEventFactory2;

import java.io.InputStream;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * A {@link StreamReader} that uses a StAX implementation with cursors for reading and parsing XMI files.
 */
@ParametersAreNonnullByDefault
public class XmiStAXCursorStreamReader extends AbstractXmiStreamReader {

    /**
     * Constructs a new {@code XmiStAXCursorStreamReader} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public XmiStAXCursorStreamReader(Handler handler) {
        super(handler);
    }

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

        readStartDocument();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamReader.START_ELEMENT) {
                int namespaceCount = reader.getNamespaceCount();
                for (int i = 0; i < namespaceCount; i++) {
                    readNamespace(reader.getNamespacePrefix(i), reader.getNamespaceURI(i));
                }

                readStartElement(reader.getNamespaceURI(), reader.getLocalName());

                int attributeCount = reader.getAttributeCount();
                for (int i = 0; i < attributeCount; i++) {
                    readAttribute(
                            reader.getAttributePrefix(i),
                            reader.getAttributeLocalName(i),
                            reader.getAttributeValue(i));
                }

                flushStartElement();
            }
            else if (event == XMLStreamReader.END_ELEMENT) {
                readEndElement();
            }
            else if (event == XMLStreamReader.CHARACTERS) {
                if (reader.getTextLength() > 0 && !reader.isWhiteSpace()) {
                    readCharacters(reader.getText());
                }
            }
        }

        readEndDocument();
    }
}
