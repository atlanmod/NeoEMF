/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.reader;

import com.ctc.wstx.api.WstxInputProperties;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.processor.EcoreProcessor;
import fr.inria.atlanmod.neoemf.io.processor.XPathResolver;
import fr.inria.atlanmod.neoemf.io.util.XmiConstants;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.stream.IntStream;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * An {@link AbstractXmiStreamReader} that uses a StAX implementation with cursors for reading and parsing XMI files.
 */
@ParametersAreNonnullByDefault
public class XmiStreamReader extends AbstractXmiStreamReader {

    /**
     * Constructs a new {@code XmiStreamReader} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public XmiStreamReader(Handler handler) {
        super(new EcoreProcessor(new XPathResolver(handler)));
    }

    @Override
    public void run(InputStream stream) throws IOException {
        XMLInputFactory factory = XMLInputFactory2.newInstance();
        configure(factory);

        try (InputStream in = new BufferedInputStream(stream)) {
            read((XMLStreamReader2) factory.createXMLStreamReader(in, XmiConstants.ENCODING));
        }
        catch (Exception e) {
            throw Throwables.wrap(e, IOException.class);
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
                IntStream.range(0, reader.getNamespaceCount()).forEachOrdered(i ->
                        readNamespace(reader.getNamespacePrefix(i), reader.getNamespaceURI(i)));

                readStartElement(reader.getNamespaceURI(), reader.getLocalName());

                IntStream.range(0, reader.getAttributeCount()).forEachOrdered(i ->
                        readAttribute(reader.getAttributePrefix(i), reader.getAttributeLocalName(i), reader.getAttributeValue(i)));

                flushCurrentElement();
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
