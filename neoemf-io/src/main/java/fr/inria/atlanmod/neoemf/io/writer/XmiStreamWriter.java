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

import fr.inria.atlanmod.common.annotation.Beta;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.io.util.MapperConstants;
import fr.inria.atlanmod.neoemf.io.util.XmiConstants;
import fr.inria.atlanmod.neoemf.io.util.XmlConstants;

import org.codehaus.stax2.XMLOutputFactory2;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * A {@link StreamWriter} that uses a StAX implementation with cursors for writing XMI files.
 */
@Beta
@ParametersAreNonnullByDefault
public class XmiStreamWriter extends AbstractXmiStreamWriter {

    /**
     * The XML writer.
     */
    private final XMLStreamWriter writer;

    /**
     * Constructs a new {@code XmiStreamWriter} on the given {@code stream}.
     *
     * @param stream the output stream to write
     */
    public XmiStreamWriter(OutputStream stream) {
        XMLOutputFactory factory = XMLOutputFactory2.newInstance();

        factory.setProperty("javax.xml.stream.isRepairingNamespaces", true);
        factory.setProperty("javax.xml.stream.isNamespaceAware", true);

        factory.setProperty("org.codehaus.stax2.automaticEmptyElements", true);

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
    public void onStartElement(BasicElement element) {
        try {
            if (element.isRoot()) {
                writer.writeStartElement(XmlConstants.format(element.ns().prefix(), element.name()));

                // Namespaces
                writer.writeNamespace(element.ns().prefix(), element.ns().uri());
                writer.writeNamespace(XmiConstants.XMI_NS, XmiConstants.XMI_URI);

                // XMI version
                writer.writeAttribute(XmiConstants.XMI_VERSION_ATTR, XmiConstants.XMI_VERSION);
            }
            else {
                writer.writeStartElement(element.name());
            }

            writer.writeAttribute(XmiConstants.XMI_TYPE, XmlConstants.format(element.metaclass().ns().prefix(), element.metaclass().name()));
            writer.writeAttribute(XmiConstants.XMI_ID, element.id().value());

            Optional<String> name = Optional.ofNullable(element.className());
            if (name.isPresent()) {
                writer.writeAttribute(MapperConstants.FEATURE_NAME, name.get());
            }
        }
        catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        try {
            if (!attribute.isMany()) {
                writer.writeAttribute(attribute.name(), String.valueOf(attribute.value()));
            }
            else {
                writer.writeStartElement(attribute.name());
                writer.writeCharacters(String.valueOf(attribute.value()));
                writer.writeEndElement();
            }
        }
        catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onReference(BasicReference reference) {
        try {
            if (reference.isContainment()) {
                return;
            }

            if (!reference.isMany()) {
                writer.writeAttribute(reference.name(), reference.idReference().value());
            }
            else {
                writer.writeStartElement(reference.name());

                Optional<BasicMetaclass> metaclass = Optional.ofNullable(reference.metaclassReference());
                if (metaclass.isPresent()) {
                    writer.writeAttribute(XmiConstants.XMI_TYPE, XmlConstants.format(metaclass.get().ns().prefix(), metaclass.get().name()));
                }

                writer.writeAttribute(XmiConstants.XMI_IDREF, reference.idReference().value());
                writer.writeEndElement();
            }
        }
        catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
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
