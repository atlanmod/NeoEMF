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

package fr.inria.atlanmod.neoemf.io.mock;

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
public class DummyWriter implements Writer {

    /**
     * A map that holds the identifier of all elements.
     */
    private final Map<Id, DummyElement> identifiers;

    /**
     * A LIFO that holds the current path, from the root to the current element.
     */
    private final Deque<DummyElement> previousElements;

    /**
     * The root element.
     */
    private DummyElement root;

    /**
     * Constructs a new {@code DummyWriter}.
     */
    public DummyWriter() {
        this.identifiers = new HashMap<>();
        this.previousElements = new ArrayDeque<>();
    }

    /**
     * Returns the root element.
     *
     * @return the root element
     */
    public DummyElement getRoot() {
        return root;
    }

    @Override
    public void onInitialize() {
        // Do nothing
    }

    @Override
    public void onStartElement(BasicElement element) {
        DummyElement mock = new DummyElement(element);

        if (previousElements.isEmpty()) {
            root = mock;
        }
        else {
            previousElements.getLast().children().add(mock);
        }

        Optional.ofNullable(element.id()).ifPresent(v -> identifiers.put(element.id(), mock));

        previousElements.addLast(mock);
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        if (isNull(attribute.owner()) || attribute.owner().equals(previousElements.getLast().id())) {
            previousElements.getLast().attributes().add(attribute);
        }
        else {
            DummyElement mock = identifiers.get(attribute.owner());
            if (nonNull(mock) && Objects.equals(mock.id(), attribute.owner())) {
                mock.attributes().add(attribute);
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
            DummyElement mock = identifiers.get(reference.owner());
            if (nonNull(mock) && Objects.equals(mock.id(), reference.owner())) {
                mock.references().add(reference);
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
