/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.annotation.Beta;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.io.processor.ValueConverter;
import fr.inria.atlanmod.neoemf.io.util.XmiConstants;

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
                writeStartElement(XmiConstants.format(ns.prefix(), element.name()));

                // Namespaces
                writeNamespace(ns.prefix(), ns.uri());
                writeNamespace(XMI_NS, XMI_URI);

                // XMI version
                writeAttribute(XMI_VERSION_ATTR, XMI_VERSION);
            }
            else {
                writeStartElement(element.name());
            }

            if (requiresExplicitType(element)) {
                writeAttribute(XMI_TYPE, XmiConstants.format(ns.prefix(), metaClass.name()));
            }

            writeAttribute(XMI_ID, element.id().toHexString());
        }
        catch (IOException e) {
            handleException(e);
        }

        classes.add(metaClass.eClass());
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

        classes.removeLast();
    }

    @Override
    public final void onAttribute(BasicAttribute attribute, List<Object> values) {
        try {
            EAttribute eAttribute = attribute.eFeature();
            if (!attribute.isMany()) {
                writeAttribute(attribute.name(), ValueConverter.INSTANCE.revert(values.get(0), eAttribute));
            }
            else {
                for (Object v : values) {
                    writeStartElement(attribute.name());
                    writeCharacters(ValueConverter.INSTANCE.revert(v, eAttribute));
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
                writeAttribute(reference.name(), values.get(0).toHexString());
            }
            else {
                writeAttribute(reference.name(), values.stream().map(Id::toHexString).collect(Collectors.joining(Strings.SPACE)));
            }
        }
        catch (IOException e) {
            handleException(e);
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
    private boolean requiresExplicitType(BasicElement element) {
        if (element.isRoot()) {
            return false;
        }

        final EClass containerClass = classes.getLast();
        final EStructuralFeature referencingFeature = containerClass.getEStructuralFeature(element.name());

        EClass baseType = EClass.class.cast(referencingFeature.getEType());
        EClass specificType = element.metaClass().eClass();

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

    /**
     * TODO
     *
     * @param e the exception to handle
     */
    private void handleException(IOException e) {
        throw Throwables.wrap(e, RuntimeException.class);
    }
}
