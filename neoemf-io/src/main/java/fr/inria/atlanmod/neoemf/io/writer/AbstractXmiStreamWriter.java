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

import fr.inria.atlanmod.commons.annotation.Beta;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.io.util.XmiConstants;
import fr.inria.atlanmod.neoemf.io.util.XmlConstants;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link AbstractStreamWriter} that writes data into an XMI file.
 */
@Beta
@ParametersAreNonnullByDefault
public abstract class AbstractXmiStreamWriter extends AbstractStreamWriter {

    /**
     * Constructs a new {@code AbstractXmiStreamWriter} with the given {@code stream}.
     *
     * @param stream the stream where to write data
     */
    protected AbstractXmiStreamWriter(OutputStream stream) {
        super(stream);
    }

    @Override
    public final void onInitialize() {
        try {
            writeStartDocument();
        }
        catch (IOException e) {
            handleException(e);
        }
    }

    @Override
    public final void onComplete() {
        try {
            writeEndDocument();
        }
        catch (IOException e) {
            handleException(e);
        }
    }

    @Override
    public final void onStartElement(BasicElement element) {
        super.onStartElement(element);

        BasicMetaclass metaClass = element.metaClass();
        BasicNamespace ns = metaClass.ns();

        try {
            if (element.isRoot()) {
                writeStartElement(XmlConstants.format(ns.prefix(), element.name()));

                // Namespaces
                writeNamespace(ns.prefix(), ns.uri());
                writeNamespace(XmiConstants.XMI_NS, XmiConstants.XMI_URI);

                // XMI version
                writeAttribute(XmiConstants.XMI_VERSION_ATTR, XmiConstants.XMI_VERSION);
            }
            else {
                writeStartElement(element.name());
            }

            // TODO Write the meta-class only if EReference#getEType() != EClass
            writeAttribute(XmiConstants.XMI_TYPE, XmlConstants.format(metaClass.ns().prefix(), metaClass.name()));

            writeAttribute(XmiConstants.XMI_ID, element.id().toString());
        }
        catch (IOException e) {
            handleException(e);
        }
    }

    @Override
    public final void onEndElement() {
        super.onEndElement();

        try {
            writeEndElement();
        }
        catch (IOException e) {
            handleException(e);
        }
    }

    @Override
    public final void onAttribute(BasicAttribute attribute, List<String> values) {
        try {
            if (!attribute.isMany()) {
                writeAttribute(attribute.name(), values.get(0));
            }
            else {
                // TODO Check the behavior of multi-valued attributes
                for (String v : values) {
                    writeStartElement(attribute.name());
                    writeCharacters(v);
                    writeEndElement();
                }
            }
        }
        catch (IOException e) {
            handleException(e);
        }
    }

    @Override
    public final void onReference(BasicReference reference, List<Id> values) {
        if (reference.isContainment()) {
            return;
        }

        try {
            if (!reference.isMany()) {
                writeAttribute(reference.name(), values.get(0).toString());
            }
            else {
                writeAttribute(reference.name(), values.stream().map(Id::toString).collect(Collectors.joining(Strings.SPACE)));
            }
        }
        catch (IOException e) {
            handleException(e);
        }
    }

    /**
     * Writes the start of a document, including the general header.
     */
    protected abstract void writeStartDocument() throws IOException;

    /**
     * Writes the start of an element {@code name}
     *
     * @param name the name of the element
     */
    protected abstract void writeStartElement(String name) throws IOException;

    /**
     * Writes a namespace.
     *
     * @param prefix the prefix of the namespace
     * @param uri    the URI of the namespace
     */
    protected abstract void writeNamespace(String prefix, String uri) throws IOException;

    /**
     * Writes an attribute of the current element.
     *
     * @param name  the name of the attribute
     * @param value the value of the attribute
     */
    protected abstract void writeAttribute(String name, String value) throws IOException;

    /**
     * Writes characters.
     *
     * @param characters the characters
     */
    protected abstract void writeCharacters(String characters) throws IOException;

    /**
     * Writes the end of the current element.
     */
    protected abstract void writeEndElement() throws IOException;

    /**
     * Writes the end of the document and finalizes the migration.
     */
    protected abstract void writeEndDocument() throws IOException;

    private void handleException(IOException e) {
        throw new RuntimeException(e);
    }
}
