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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;

import javax.xml.parsers.SAXParserFactory;

/**
 *
 */
public class XmiSaxReader extends AbstractInternalNotifier implements Reader {

    @Override
    public Class<? extends AbstractInternalHandler> getHandlerClass() {
        return XmiHandler.class;
    }

    public void read(File file) throws Exception {
        if (!hasHandler()) {
            NeoLogger.error("This notifier hasn't any handler.");
            return;
        }

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        factory.newSAXParser().parse(file, new XmiSaxHandler());
    }

    private class XmiSaxHandler extends DefaultHandler {

        @Override
        public void startDocument() throws SAXException {
            try {
                XmiSaxReader.this.notifyStartDocument();
            }
            catch (Exception e) {
                NeoLogger.error(e);
            }
        }

        @Override
        public void endDocument() throws SAXException {
            try {
                XmiSaxReader.this.notifyEndDocument();
            }
            catch (Exception e) {
                NeoLogger.error(e);
            }
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
        {
            try {
                notifyStartElement(qName.split(":")[0], uri, localName);

                if (attributes.getLength() > 0) {
                    for (int i = 0; i < attributes.getLength(); i++) {
                        String namespace = attributes.getURI(i);
                        String value = attributes.getValue(i);

                        // Check if value is an XPath reference
                        if (value.startsWith("//@")) {
                            for (String ref : value.split(" ")) {
                                notifyReference(namespace, attributes.getLocalName(i), ref.trim());
                            }
                        } else {
                            notifyAttribute(namespace, attributes.getLocalName(i), value);
                        }
                    }
                }
            }
            catch (Exception e) {
                NeoLogger.error(e);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            try {
                XmiSaxReader.this.notifyEndElement();
            }
            catch (Exception e) {
                NeoLogger.error(e);
            }
        }
    }
}
