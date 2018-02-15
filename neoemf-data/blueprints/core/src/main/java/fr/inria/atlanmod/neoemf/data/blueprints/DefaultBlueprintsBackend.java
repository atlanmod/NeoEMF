/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.KeyIndexableGraph;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.collect.MoreStreams;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.blueprints.graph.ElementEdge;
import fr.inria.atlanmod.neoemf.data.blueprints.graph.ElementVertex;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotContainsNull;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;

/**
 * The default {@link BlueprintsBackend} mapping.
 *
 * @see BlueprintsBackendFactory
 */
@ParametersAreNonnullByDefault
class DefaultBlueprintsBackend extends AbstractBlueprintsBackend {

    /**
     * Constructs a new {@code DefaultBlueprintsBackend} wrapping the provided {@code baseGraph}.
     *
     * @param baseGraph the base {@link KeyIndexableGraph} used to access the database
     *
     * @see BlueprintsBackendFactory
     */
    protected DefaultBlueprintsBackend(KeyIndexableGraph baseGraph) {
        super(baseGraph);
    }

    //region Single-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        return graph.getVertex(key.owner())
                .flatMap(v -> v.getValue(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        return graph.getOrCreateVertex(key.owner())
                .setValue(key, value);
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        graph.getVertex(key.owner())
                .ifPresent(v -> v.removeValue(key));
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        return graph.getVertex(key.owner())
                .flatMap(v -> v.getReference(key))
                .map(ElementVertex::getElementId);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        return graph.getOrCreateVertex(key.owner())
                .setReference(key, reference)
                .map(ElementVertex::getElementId);
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        checkNotNull(key, "key");

        graph.getVertex(key.owner())
                .ifPresent(v -> v.removeReferences(key));
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        return graph.getVertex(key.owner())
                .flatMap(v -> v.getValue(key, key.position()));
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        return graph.getVertex(key.owner())
                .map(v -> v.<V>getValues(key))
                .map(MoreIterables::stream)
                .orElseGet(Stream::empty);
    }

    @Nonnull
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        V previousValue = graph.getVertex(key.owner())
                .flatMap(v -> v.replaceValue(key, key.position(), value))
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        return Optional.of(previousValue);
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        ElementVertex vertex = graph.getOrCreateVertex(key.owner());

        final int firstPosition = key.position();
        final int size = vertex.getSize(key);
        checkPositionIndex(firstPosition, size);

        IntStream.range(firstPosition, size)
                .map(MoreStreams.reverseOrder(firstPosition, size))
                .forEachOrdered(i -> vertex.moveValue(key, i, i + 1));

        vertex.setValue(key, firstPosition, value);
        vertex.setSize(key, size + 1);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        ElementVertex vertex = graph.getOrCreateVertex(key.owner());

        final int firstPosition = key.position();
        final int size = vertex.getSize(key);
        checkPositionIndex(firstPosition, size);

        final int additionCount = collection.size();

        IntStream.range(firstPosition, size)
                .map(MoreStreams.reverseOrder(firstPosition, size))
                .forEachOrdered(i -> vertex.moveValue(key, i, i + additionCount));

        IntStream.range(0, additionCount)
                .forEachOrdered(i -> vertex.setValue(key, firstPosition + i, collection.get(i)));

        vertex.setSize(key, size + additionCount);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Optional<ElementVertex> optVertex = graph.getVertex(key.owner());
        if (!optVertex.isPresent()) {
            return Optional.empty();
        }

        final ElementVertex vertex = optVertex.get();

        final int firstPosition = key.position();
        final int size = vertex.getSize(key);
        if (size == 0) {
            return Optional.empty();
        }

        Optional<V> previousValue = vertex.getValue(key, firstPosition);

        IntStream.range(firstPosition, size - 1)
                .forEachOrdered(i -> vertex.moveValue(key, i + 1, i));

        vertex.removeValue(key, size - 1);
        vertex.setSize(key, size - 1);

        return previousValue;
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        checkNotNull(key, "key");

        graph.getVertex(key.owner()).ifPresent(v -> {
            final int size = v.getSize(key);

            IntStream.range(0, size)
                    .forEachOrdered(i -> v.removeValue(key, i));

            v.setSize(key, 0);
        });
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        return graph.getVertex(key.owner())
                .map(v -> v.getSize(key))
                .filter(s -> s > 0);
    }

    //endregion

    //region Multi-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        return graph.getVertex(key.owner())
                .flatMap(v -> v.getReference(key, key.position()))
                .map(ElementVertex::getElementId);
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        return graph.getVertex(key.owner())
                .map(v -> MoreIterables.stream(v.getReferenceEdges(key))
                        .sorted(Comparator.comparingInt(ElementEdge::getPosition))
                        .map(ElementEdge::getReferencedVertex)
                        .map(ElementVertex::getElementId))
                .orElseGet(Stream::empty);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Id previousId = graph.getVertex(key.owner())
                .flatMap(v -> v.replaceReference(key, key.position(), reference))
                .map(ElementVertex::getElementId)
                .<NoSuchElementException>orElseThrow(NoSuchElementException::new);

        return Optional.of(previousId);
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        ElementVertex vertex = graph.getOrCreateVertex(key.owner());

        final int firstPosition = key.position();
        final int size = vertex.getSize(key);
        checkPositionIndex(firstPosition, size);

        if (firstPosition < size) {
            vertex.getReferenceEdges(key, firstPosition, size + 1)
                    .forEach(e -> e.updatePosition(p -> p + 1));
        }

        vertex.setReference(key, firstPosition, reference);
        vertex.setSize(key, size + 1);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        ElementVertex vertex = graph.getOrCreateVertex(key.owner());

        final int firstPosition = key.position();
        final int size = vertex.getSize(key);
        checkPositionIndex(firstPosition, size);

        final int additionCount = collection.size();

        if (firstPosition < size) {
            vertex.getReferenceEdges(key, firstPosition, size + additionCount)
                    .forEach(e -> e.updatePosition(p -> p + additionCount));
        }

        IntStream.range(0, additionCount)
                .forEachOrdered(i -> vertex.setReference(key, firstPosition + i, collection.get(i)));

        vertex.setSize(key, size + additionCount);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Optional<ElementVertex> optVertex = graph.getVertex(key.owner());
        if (!optVertex.isPresent()) {
            return Optional.empty();
        }

        final ElementVertex vertex = optVertex.get();

        final int firstPosition = key.position();
        final int size = vertex.getSize(key);
        if (size == 0) {
            return Optional.empty();
        }

        Optional<Id> previousId = Optional.empty();

        for (ElementEdge edge : vertex.getReferenceEdges(key, firstPosition, size)) {
            int position = edge.getPosition();
            if (position != firstPosition) {
                edge.updatePosition(p -> p - 1);
            }
            else {
                previousId = Optional.of(edge.getReferencedVertex().getElementId());
                edge.remove();
            }
        }

        vertex.setSize(key, size - 1);

        return previousId;
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        checkNotNull(key, "key");

        graph.getVertex(key.owner()).ifPresent(v -> {
            v.removeReferences(key);
            v.setSize(key, 0);
        });
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return sizeOfValue(key);
    }

    //endregion
}
