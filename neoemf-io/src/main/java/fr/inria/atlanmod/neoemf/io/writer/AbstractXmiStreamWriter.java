/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.processor.ValueConverter;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyClass;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyPackage;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import fr.inria.atlanmod.neoemf.io.util.XmiConstants;

import org.atlanmod.commons.annotation.Beta;
import org.atlanmod.commons.primitive.Strings;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.io.util.XmiConstants.XMI_ID;
import static fr.inria.atlanmod.neoemf.io.util.XmiConstants.XMI_NS;
import static fr.inria.atlanmod.neoemf.io.util.XmiConstants.XMI_TYPE;
import static fr.inria.atlanmod.neoemf.io.util.XmiConstants.XMI_URI;
import static fr.inria.atlanmod.neoemf.io.util.XmiConstants.XMI_VERSION;
import static fr.inria.atlanmod.neoemf.io.util.XmiConstants.XMI_VERSION_ATTR;

/**
 * An {@link AbstractStreamWriter} that writes data into an XMI file.
 */
@Beta
@ParametersAreNonnullByDefault
public abstract class AbstractXmiStreamWriter extends AbstractStreamWriter {

    /**
     * A LIFO that holds the current {@link EClass} chain. It contains the current element and the previous.
     */
    @Nonnull
    private final Deque<EClass> classes = new ArrayDeque<>();

    /**
     * Constructs a new {@code AbstractXmiStreamWriter} with the given {@code stream}.
     *
     * @param stream the stream where to write data
     */
    protected AbstractXmiStreamWriter(OutputStream stream) {
        super(stream);
    }

    @Override
    public final void onInitialize() throws IOException {
        writeStartDocument();
    }

    @Override
    public final void onComplete() throws IOException {
        writeEndDocument();
    }

    @Override
    public final void onStartElement(ProxyElement element) throws IOException {
        super.onStartElement(element);

        final Id id = element.getId().getResolved();
        final ProxyClass metaClass = element.getMetaClass();
        final ProxyPackage ns = metaClass.getNamespace();

        if (element.isRoot()) {
            writeStartElement(XmiConstants.format(ns.getPrefix(), element.getName()));

            // Namespaces
            writeNamespace(ns.getPrefix(), ns.getUri());
            writeNamespace(XMI_NS, XMI_URI);

            // XMI version
            writeAttribute(XMI_VERSION_ATTR, XMI_VERSION);
        }
        else {
            writeStartElement(element.getName());
        }

        if (requiresExplicitType(element)) {
            writeAttribute(XMI_TYPE, XmiConstants.format(ns.getPrefix(), metaClass.getName()));
        }

        writeAttribute(XMI_ID, id.toHexString());

        classes.add(metaClass.getOrigin());
    }

    @Override
    public final void onEndElement() throws IOException {
        super.onEndElement();

        writeEndElement();

        classes.removeLast();
    }

    @Override
    public final void onAttribute(ProxyAttribute attribute, List<Object> values) throws IOException {
        final ValueConverter converter = ValueConverter.INSTANCE;

        final EAttribute eAttribute = attribute.getOrigin();
        if (!attribute.isMany()) {
            writeAttribute(attribute.getName(), converter.revert(values.get(0), eAttribute));
        }
        else {
            for (Object v : values) {
                writeStartElement(attribute.getName());
                writeCharacters(converter.revert(v, eAttribute));
                writeEndElement();
            }
        }
    }

    @Override
    public final void onReference(ProxyReference reference, List<Id> values) throws IOException {
        if (reference.isContainment()) {
            return;
        }

        if (!reference.isMany()) {
            writeAttribute(reference.getName(), values.get(0).toHexString());
        }
        else {
            writeAttribute(reference.getName(), values.stream().map(Id::toHexString).collect(Collectors.joining(Strings.SPACE)));
        }
    }

    /**
     * Checks that the element inherit from type of the referencing feature type, from its container. If the type of the
     * element is the same as the type of the referencing feature, then the explicit declaration can be skipped.
     * <p>
     * This method validates this predicate: {@code e.type != e.container.reference(e).type}.
     *
     * @param element the element to test
     *
     * @return {@code true} if the element requires an explicit declaration of its type
     */
    private boolean requiresExplicitType(ProxyElement element) {
        if (element.isRoot()) {
            return false;
        }

        final EClass containerClass = classes.getLast();
        final EStructuralFeature referencingFeature = containerClass.getEStructuralFeature(element.getName());

        final EClass baseType = (EClass) referencingFeature.getEType();
        final EClass specificType = element.getMetaClass().getOrigin();

        return specificType != baseType;
    }

    /**
     * Writes the start of a document, including the general header.
     *
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeStartDocument() throws IOException;

    /**
     * Writes the start of an element {@code name}
     *
     * @param name the name of the element
     *
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeStartElement(String name) throws IOException;

    /**
     * Writes a namespace.
     *
     * @param prefix the prefix of the namespace
     * @param uri    the URI of the namespace
     *
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeNamespace(String prefix, String uri) throws IOException;

    /**
     * Writes an attribute of the current element.
     *
     * @param name  the name of the attribute
     * @param value the value of the attribute
     *
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeAttribute(String name, String value) throws IOException;

    /**
     * Writes characters.
     *
     * @param characters the characters
     *
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeCharacters(String characters) throws IOException;

    /**
     * Writes the end of the current element.
     *
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeEndElement() throws IOException;

    /**
     * Writes the end of the document and finalizes the migration.
     *
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeEndDocument() throws IOException;
}
