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

package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.io.writer.Writer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
    public void onStartElement(BasicElement element) {
        InMemoryElement e = new InMemoryElement(element);

        if (previousElements.isEmpty()) {
            root = e;
        }
        else {
            previousElements.getLast().children().add(e);
        }

        Optional.ofNullable(element.id()).ifPresent(v -> identifiers.put(element.id(), e));

        previousElements.addLast(e);
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        if (isNull(attribute.owner()) || attribute.owner().equals(previousElements.getLast().id())) {
            previousElements.getLast().attributes().add(attribute);
        }
        else {
            InMemoryElement e = identifiers.get(attribute.owner());
            if (nonNull(e) && Objects.equals(e.id(), attribute.owner())) {
                e.attributes().add(attribute);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void onReference(BasicReference reference) {
        if (isNull(reference.owner()) || reference.owner().equals(previousElements.getLast().id())) {
            previousElements.getLast().references().add(reference);
        }
        else {
            InMemoryElement e = identifiers.get(reference.owner());
            if (nonNull(e) && Objects.equals(e.id(), reference.owner())) {
                e.references().add(reference);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void onCharacters(String characters) {
        // Do nothing
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
