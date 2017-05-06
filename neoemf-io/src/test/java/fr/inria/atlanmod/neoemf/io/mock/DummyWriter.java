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

import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicElement;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;
import fr.inria.atlanmod.neoemf.io.writer.MapperWriter;

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
 * A {@link MapperWriter} that stores all elements in {@link java.util.Collection}s.
 */
@ParametersAreNonnullByDefault
public class DummyWriter implements MapperWriter {

    /**
     * A map that holds the identifier of all elements.
     */
    private final Map<String, DummyElement> identifiers;

    /**
     * A stack that holds the current path, from the root to the current element.
     */
    private final Deque<DummyElement> stack;

    /**
     * The root element.
     */
    private DummyElement root;

    /**
     * Constructs a new {@code DummyWriter}.
     */
    public DummyWriter() {
        this.identifiers = new HashMap<>();
        this.stack = new ArrayDeque<>();
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

        if (stack.isEmpty()) {
            root = mock;
        }
        else {
            stack.getLast().children().add(mock);
        }

        Optional.ofNullable(element.id()).ifPresent(v -> identifiers.put(element.id().value(), mock));

        stack.addLast(mock);
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        if (isNull(attribute.id()) || attribute.id().equals(stack.getLast().id())) {
            stack.getLast().attributes().add(attribute);
        }
        else {
            DummyElement mock = identifiers.get(attribute.id().value());
            if (nonNull(mock) && Objects.equals(mock.id(), attribute.id())) {
                mock.attributes().add(attribute);
            }
            else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public void onReference(BasicReference reference) {
        if (isNull(reference.id()) || reference.id().equals(stack.getLast().id())) {
            stack.getLast().references().add(reference);
        }
        else {
            DummyElement mock = identifiers.get(reference.id().value());
            if (nonNull(mock) && Objects.equals(mock.id(), reference.id())) {
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
        stack.removeLast();
    }

    @Override
    public void onComplete() {
        stack.clear();
        identifiers.clear();
    }
}
