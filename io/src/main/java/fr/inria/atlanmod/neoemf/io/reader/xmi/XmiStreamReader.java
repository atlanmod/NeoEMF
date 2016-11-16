/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.reader.xmi;

import fr.inria.atlanmod.neoemf.io.internal.InternalHandler;
import fr.inria.atlanmod.neoemf.io.internal.impl.DefaultInternalHandler;
import fr.inria.atlanmod.neoemf.io.internal.impl.EcoreDelegatedInternalHandler;
import fr.inria.atlanmod.neoemf.io.internal.impl.XPathDelegatedInternalHandler;
import fr.inria.atlanmod.neoemf.io.reader.Reader;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.SAXParserFactory;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A XMI {@link Reader reader} that uses stream for reading.
 * <p/>
 * It uses a SAX implementation of XML parsing.
 */
public class XmiStreamReader extends AbstractXmiReader {

    private boolean showProgress = true;

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    @Override
    public InternalHandler newDefaultHandler() {
        InternalHandler defaultHandler;

        defaultHandler = new DefaultInternalHandler();
        defaultHandler = new XPathDelegatedInternalHandler(defaultHandler);
        defaultHandler = new EcoreDelegatedInternalHandler(defaultHandler);

        addHandler(defaultHandler);
        return defaultHandler;
    }

    public void read(InputStream stream) throws Exception {
        if (!hasHandler()) {
            throw new IllegalStateException("This notifier hasn't any handler");
        }

        checkNotNull(stream);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(false);

        Timer logProgressTimer = new Timer(true);

        try {
            // Log progress timer
            logProgressTimer.schedule(new LogProgressTimer(stream, stream.available()), 10000, 30000);

            factory.newSAXParser().parse(stream, new XmiSaxHandler());
        }
        catch (SAXException e) {
            if (!isNull(e.getException())) {
                throw e.getException();
            }
            else {
                throw e;
            }
        }
        finally {
            logProgressTimer.cancel();
        }
    }

    private void logProgress(double percent) {
        if (showProgress) {
            NeoLogger.debug("Progress : {0}", String.format("%5s", String.format("%,.0f %%", percent)));
        }
    }

    private class XmiSaxHandler extends DefaultHandler {

        private String xmiUri;

        @Override
        public void startDocument() throws SAXException {
            try {
                processStartDocument();
            }
            catch (Exception e) {
                throw new SAXException(e);
            }

            logProgress(0);
        }

        @Override
        public void endDocument() throws SAXException {
            logProgress(100);

            try {
                processEndDocument();
            }
            catch (Exception e) {
                throw new SAXException(e);
            }
        }

        @Override
        public void startPrefixMapping(String prefix, String uri) throws SAXException {
            if (prefix.equals(XMI_NS)) {
                xmiUri = uri;
            }

            processNamespace(prefix, uri);
        }

        @Override
        public void startElement(String uri, String name, String qName, Attributes attributes) throws SAXException
        {
            // Ignore XMI elements
            if (uri.equals(xmiUri)) {
                return;
            }

            try {
                processStartElement(uri, name, attributes);
            }
            catch (Exception e) {
                throw new SAXException(e);
            }
        }

        @Override
        public void endElement(String uri, String name, String qName) throws SAXException {
            // Ignore XMI elements
            if (uri.equals(xmiUri)) {
                return;
            }

            try {
                processEndElement(uri, name);
            }
            catch (Exception e) {
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
                throw new SAXException(e);
            }
        }
    }

    private class LogProgressTimer extends TimerTask {

        private final InputStream stream;
        private final long total;

        private LogProgressTimer(InputStream stream, long total) {
            this.stream = stream;
            this.total = total;
        }

        @Override
        public void run() {
            try {
                logProgress((double) (total - stream.available()) / (double) total * 100d);
            }
            catch (Exception ignore) {
            }
        }
    }
}
