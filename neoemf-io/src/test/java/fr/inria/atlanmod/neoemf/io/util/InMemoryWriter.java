/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import fr.inria.atlanmod.neoemf.io.writer.Writer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static org.atlanmod.commons.Preconditions.checkEqualTo;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A {@link Writer} that stores all elements in {@link java.util.Collection}s.
 */
@ParametersAreNonnullByDefault
public final class InMemoryWriter implements Writer {

    /**
     * A map that holds the identifier of all elements.
     */
    private final Map<Id, InMemoryElement> identifiers;

    /**
     * A LIFO that holds the current path, from the root to the current element.
     */
    private final Deque<InMemoryElement> previousElements;

    /**
     * The root element.
     */
    private InMemoryElement root;

    /**
     * Constructs a new {@code InMemoryWriter}.
     */
    public InMemoryWriter() {
        this.identifiers = new HashMap<>();
        this.previousElements = new ArrayDeque<>();
    }

    /**
     * Returns the root element.
     *
     * @return the root element
     */
    public InMemoryElement getRoot() {
        return root;
    }

    @Override
    public void onInitialize() {
        // Do nothing
    }

    @Override
    public void onStartElement(ProxyElement element) {
        InMemoryElement e = new InMemoryElement(element);

        if (previousElements.isEmpty()) {
            root = e;
        }
        else {
            previousElements.getLast().children().add(e);
        }

        Optional.ofNullable(element.getId().getResolved()).ifPresent(v -> identifiers.put(element.getId().getResolved(), e));

        previousElements.addLast(e);
    }

    @Override
    public void onAttribute(ProxyAttribute attribute) {
        if (isNull(attribute.getOwner()) || attribute.getOwner().equals(previousElements.getLast().id())) {
            previousElements.getLast().attributes().add(attribute);
        }
        else {
            InMemoryElement e = identifiers.get(attribute.getOwner());
            checkNotNull(e, "identifiers.get(%s)", attribute.getOwner());
            checkEqualTo(e.id(), attribute.getOwner(), "%s is not owner of this attribute (%s)", e.id(), attribute.getOwner());

            e.attributes().add(attribute);
        }
    }

    @Override
    public void onReference(ProxyReference reference) {
        if (isNull(reference.getOwner()) || reference.getOwner().equals(previousElements.getLast().id())) {
            previousElements.getLast().references().add(reference);
        }
        else {
            InMemoryElement e = identifiers.get(reference.getOwner());
            checkNotNull(e, "identifiers.get(%s)", reference.getOwner());
            checkEqualTo(e.id(), reference.getOwner(), "%s is not the owner of this reference (%s)", e.id(), reference.getOwner());

            e.references().add(reference);
        }
    }

    @Override
    public void onEndElement() {
        previousElements.removeLast();
    }

    @Override
    public void onComplete() {
        previousElements.clear();
        identifiers.clear();
    }
}
