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
import fr.inria.atlanmod.neoemf.io.processor.EcoreProcessor;
import fr.inria.atlanmod.neoemf.io.processor.XPathProcessor;
import fr.inria.atlanmod.neoemf.io.util.XmlConstants;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.stream.IntStream;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * A {@link StreamReader} that uses a StAX implementation with cursors for reading and parsing XMI files.
 */
@ParametersAreNonnullByDefault
public class XmiStreamReader extends AbstractXmiStreamReader {

    /**
     * Constructs a new {@code XmiStreamReader} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public XmiStreamReader(Handler handler) {
        super(new EcoreProcessor(new XPathProcessor(handler)));
    }

    @Override
    public void run(InputStream stream) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory2.newInstance();
        factory.setProperty("javax.xml.stream.isNamespaceAware", true);
        factory.setProperty("com.ctc.wstx.lazyParsing", true);

        // Remove contraints
        factory.setProperty("com.ctc.wstx.maxAttributesPerElement", Integer.MAX_VALUE);
        factory.setProperty("com.ctc.wstx.maxAttributeSize", Integer.MAX_VALUE);
        factory.setProperty("com.ctc.wstx.maxChildrenPerElement", Integer.MAX_VALUE);
        factory.setProperty("com.ctc.wstx.maxElementCount", Integer.MAX_VALUE);
        factory.setProperty("com.ctc.wstx.maxElementDepth", Integer.MAX_VALUE);
        factory.setProperty("com.ctc.wstx.maxCharacters", Integer.MAX_VALUE);
        factory.setProperty("com.ctc.wstx.maxTextLength", Integer.MAX_VALUE);

        read((XMLStreamReader2) factory.createXMLStreamReader(new BufferedInputStream(stream), XmlConstants.ENCODING));
    }

    /**
     * Reads the XMI file with a cursor parser using cursors.
     *
     * @param reader the reader to browse
     *
     * @throws XMLStreamException if there is an error with the underlying XML
     */
    private void read(XMLStreamReader2 reader) throws XMLStreamException {
        readStartDocument();

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamReader.START_ELEMENT) {
                IntStream.range(0, reader.getNamespaceCount()).forEach(i ->
                        readNamespace(reader.getNamespacePrefix(i), reader.getNamespaceURI(i)));

                readStartElement(reader.getNamespaceURI(), reader.getLocalName());

                IntStream.range(0, reader.getAttributeCount()).forEach(i ->
                        readAttribute(reader.getAttributePrefix(i), reader.getAttributeLocalName(i), reader.getAttributeValue(i)));

                flushStartElement();
            }
            else if (event == XMLStreamReader.END_ELEMENT) {
                readEndElement();
            }
            else if (event == XMLStreamReader.CHARACTERS) {
                String text = reader.getText().trim();
                if (!text.isEmpty()) {
                    readCharacters(text);
                }
            }
        }

        readEndDocument();
    }
}
