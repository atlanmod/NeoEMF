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

import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;
import fr.inria.atlanmod.neoemf.io.internal.impl.EcoreProcessor;
import fr.inria.atlanmod.neoemf.io.internal.impl.InternalHandlerImpl;
import fr.inria.atlanmod.neoemf.io.internal.impl.XPathProcessor;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.eclipse.emf.ecore.EPackage;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

/**
 *
 */
public class XmiStreamReader extends AbstractXmiReader {

    public XmiStreamReader(Map<String, EPackage> ePackages) {
        super(ePackages);
    }

    @Override
    public InternalHandler newHandler() {
        return new EcoreProcessor(ePackages, new XPathProcessor(new InternalHandlerImpl()));
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
                XmiStreamReader.this.notifyStartDocument();
            }
            catch (Exception e) {
                NeoLogger.error(e);
                throw new SAXException(e);
            }
        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            processNamespace(prefix, uri);
        }

        @Override
        public void startElement(String uri, String name, String qName, Attributes attributes) throws SAXException
        {
            try {
                processElement(qName, uri, name, attributes);
            }
            catch (Exception e) {
                NeoLogger.error(e);
                throw new SAXException(e);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String characters = String.valueOf(ch, start, length).trim();
            try {
                if (!characters.isEmpty()) {
                    processCharacters(characters);
                }
            }
            catch (Exception e) {
                NeoLogger.error(e);
                throw new SAXException(e);
            }
        }

        @Override
        public void endElement(String uri, String name, String qName) throws SAXException {
            try {
                XmiStreamReader.this.notifyEndElement();
            }
            catch (Exception e) {
                NeoLogger.error(e);
                throw new SAXException(e);
            }
        }

        @Override
        public void endDocument() throws SAXException {
            try {
                XmiStreamReader.this.notifyEndDocument();
            }
            catch (Exception e) {
                NeoLogger.error(e);
                throw new SAXException(e);
            }
        }
    }
}
