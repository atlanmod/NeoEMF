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

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.io.util.PersistenceConstants;
import fr.inria.atlanmod.neoemf.io.util.XmiConstants;
import fr.inria.atlanmod.neoemf.io.util.XmlConstants;

import org.codehaus.stax2.XMLOutputFactory2;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import static java.util.Objects.nonNull;

/**
 * A {@link StreamWriter} that uses a StAX implementation with cursors for writing XMI files.
 */
@Experimental
@ParametersAreNonnullByDefault
public class XmiStAXCursorStreamWriter extends AbstractXmiStreamWriter {

    /**
     * The XML writer.
     */
    private final XMLStreamWriter writer;

    /**
     * Constructs a new {@code XmiStAXCursorStreamWriter} on the given {@code stream}.
     *
     * @param stream the output stream to write
     */
    public XmiStAXCursorStreamWriter(OutputStream stream) {
        XMLOutputFactory factory = XMLOutputFactory2.newInstance();
        factory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);

        try {
            writer = factory.createXMLStreamWriter(new BufferedOutputStream(stream), XmlConstants.ENCODING);
        }
        catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onInitialize() {
        try {
            writer.writeStartDocument(XmlConstants.ENCODING, XmlConstants.VERSION);
        }
        catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onStartElement(RawElement element) {
        try {
            if (element.root()) {
                writer.writeStartElement(element.ns().prefix() + ":" + element.name());

                // TODO Write all used namespaces

                writer.writeAttribute(XmiConstants.XMI_VERSION_ATTR, XmiConstants.XMI_VERSION);
            }
            else {
                writer.writeStartElement(element.name());
            }

            writer.writeAttribute(XmiConstants.XMI_TYPE, element.metaClass().ns().prefix() + ":" + element.metaClass().name());
            writer.writeAttribute(XmiConstants.XMI_ID, element.id().value());

            if (nonNull(element.className())) {
                writer.writeAttribute(PersistenceConstants.FEATURE_NAME, element.className());
            }
        }
        catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onAttribute(RawAttribute attribute) {
        try {
            writer.writeAttribute(attribute.name(), String.valueOf(attribute.value()));
        }
        catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onReference(RawReference reference) {
        // TODO Handle references
    }

    @Override
    public void onCharacters(String characters) {
        // Do nothing
    }

    @Override
    public void onEndElement() {
        try {
            writer.writeEndElement();
        }
        catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onComplete() {
        try {
            writer.writeEndDocument();
            writer.close();
        }
        catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
