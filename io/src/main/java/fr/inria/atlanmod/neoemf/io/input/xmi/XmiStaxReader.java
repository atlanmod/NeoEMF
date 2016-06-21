/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.input.xmi;

import fr.inria.atlanmod.neoemf.io.impl.AbstractInternalHandler;
import fr.inria.atlanmod.neoemf.io.impl.AbstractInternalNotifier;
import fr.inria.atlanmod.neoemf.io.input.Reader;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

import java.io.File;
import java.io.IOException;

import javax.xml.stream.events.XMLEvent;

/**
 *
 */
public class XmiStaxReader extends AbstractInternalNotifier implements Reader {

    private final XMLInputFactory2 factory;

    private String defaultPrefix;

    public XmiStaxReader() {
        super();
        this.factory = (XMLInputFactory2) XMLInputFactory2.newFactory();
        this.factory.configureForLowMemUsage();
        this.defaultPrefix = "xmi";
    }

    /**
     * Defines the default prefix of the document.
     * <p/>
     * An element that has no information on its namespace, will have {@code prefix}. If the document hasn't the
     * specified prefix, an exception will be thrown.
     *
     * @param prefix the default prefix of this XML parser
     */
    public void setDefaultPrefix(String prefix) {
        this.defaultPrefix = prefix;
    }

    @Override
    public Class<? extends AbstractInternalHandler> getHandlerClass() {
        return XmiHandler.class;
    }

    public void read(File file) throws Exception {
        if (!hasHandler()) {
            NeoLogger.error("This notifier hasn't any handler.");
            return;
        }

        XMLStreamReader2 streamReader = factory.createXMLStreamReader(file);

        notifyStartDocument();

        while (streamReader.hasNext()) {
            int eventType = streamReader.next();
            if (eventType == XMLEvent.START_ELEMENT) {
                startElement(streamReader);
            } else if (eventType == XMLEvent.END_ELEMENT) {
                notifyEndElement();
            }
        }

        notifyEndDocument();
    }

    private void startElement(XMLStreamReader2 stream) throws Exception {
        if (stream.getNamespaceCount() > 0) {
            checkXmi(stream);
        }

        String prefix = getPrefixElement(stream);
        notifyStartElement(stream.getNamespaceURI(prefix), stream.getLocalName(), null);
        attribute(stream);
    }

    private void attribute(XMLStreamReader2 stream) throws Exception {
        if (stream.getAttributeCount() > 0) {
            for (int i = 0; i < stream.getAttributeCount(); i++) {
                String namespace = getNamespaceAttribute(stream, stream.getAttributeNamespace(i));
                String value = stream.getAttributeValue(i);

                // Check if value is an XPath reference
                if (value.startsWith("//@")) {
                    for (String ref : value.split(" ")) {
                        notifyReference(namespace, stream.getAttributeLocalName(i), ref.trim());
                    }
                } else {
                    notifyAttribute(namespace, stream.getAttributeLocalName(i), value);
                }
            }
        }
    }

    private String getPrefixElement(XMLStreamReader2 stream) {
        return stream.getPrefix().isEmpty() ? defaultPrefix : stream.getPrefix();
    }

    private String getNamespaceAttribute(XMLStreamReader2 stream, String namespace) {
        return namespace.trim().isEmpty() ? stream.getNamespaceURI(defaultPrefix) : namespace;
    }

    private void checkXmi(XMLStreamReader2 stream) throws IOException {
        if (stream.getNamespaceContext().getNamespaceURI(defaultPrefix) == null) {
            throw new IOException("The given XML file is not an XMI file");
        }
    }
}
