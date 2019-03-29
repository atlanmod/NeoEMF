/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.KeyIndexableGraph;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.ElementEdge;
import fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.ElementVertex;

import org.atlanmod.commons.collect.MoreIterables;
import org.atlanmod.commons.collect.MoreStreams;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Preconditions.checkNotContainsNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;
import static org.atlanmod.commons.Preconditions.checkPositionIndex;

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
     * @param baseGraph the base {@link com.tinkerpop.blueprints.KeyIndexableGraph} used to access the database
     *
     * @see BlueprintsBackendFactory
     */
    protected DefaultBlueprintsBackend(KeyIndexableGraph baseGraph) {
        super(baseGraph);
    }

    //region Single-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        return graph.getVertex(feature.owner())
                .flatMap(v -> v.getValue(feature));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        return graph.getOrCreateVertex(feature.owner())
                .setValue(feature, value);
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        graph.getVertex(feature.owner())
                .ifPresent(v -> v.removeValue(feature));
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        return graph.getVertex(feature.owner())
                .flatMap(v -> v.getReference(feature))
                .map(ElementVertex::getElementId);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        return graph.getOrCreateVertex(feature.owner())
                .setReference(feature, reference)
                .map(ElementVertex::getElementId);
    }

    @Override
    public void removeReference(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        graph.getVertex(feature.owner())
                .ifPresent(v -> v.removeReferences(feature));
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        return graph.getVertex(feature.owner())
                .flatMap(v -> v.getValue(feature, feature.position()));
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        return graph.getVertex(feature.owner())
                .map(v -> v.<V>getValues(feature))
                .map(MoreIterables::stream)
                .orElseGet(Stream::empty);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        Optional<V> previousValue = graph.getVertex(feature.owner())
                .flatMap(v -> v.replaceValue(feature, feature.position(), value));

        if (!previousValue.isPresent()) {
            throw new IndexOutOfBoundsException();
        }

        return previousValue;
    }

    @Override
    public <V> void addValue(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        ElementVertex vertex = graph.getOrCreateVertex(feature.owner());

        final int firstPosition = feature.position();
        final int size = vertex.getSize(feature);
        checkPositionIndex(firstPosition, size);

        IntStream.range(firstPosition, size)
                .map(MoreStreams.reverseOrder(firstPosition, size))
                .forEachOrdered(i -> vertex.moveValue(feature, i, i + 1));

        vertex.setValue(feature, firstPosition, value);
        vertex.setSize(feature, size + 1);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean feature, List<? extends V> collection) {
        checkNotNull(feature, "feature");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection, "collection");

        if (collection.isEmpty()) {
            return;
        }

        ElementVertex vertex = graph.getOrCreateVertex(feature.owner());

        final int firstPosition = feature.position();
        final int size = vertex.getSize(feature);
        checkPositionIndex(firstPosition, size);

        final int additionCount = collection.size();

        IntStream.range(firstPosition, size)
                .map(MoreStreams.reverseOrder(firstPosition, size))
                .forEachOrdered(i -> vertex.moveValue(feature, i, i + additionCount));

        IntStream.range(0, additionCount)
                .forEachOrdered(i -> vertex.setValue(feature, firstPosition + i, collection.get(i)));

        vertex.setSize(feature, size + additionCount);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        Optional<ElementVertex> optVertex = graph.getVertex(feature.owner());
        if (!optVertex.isPresent()) {
            return Optional.empty();
        }

        final ElementVertex vertex = optVertex.get();

        final int firstPosition = feature.position();
        final int size = vertex.getSize(feature);
        if (size == 0) {
            return Optional.empty();
        }

        Optional<V> previousValue = vertex.getValue(feature, firstPosition);

        IntStream.range(firstPosition, size - 1)
                .forEachOrdered(i -> vertex.moveValue(feature, i + 1, i));

        vertex.removeValue(feature, size - 1);
        vertex.setSize(feature, size - 1);

        return previousValue;
    }

    @Override
    public void removeAllValues(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        graph.getVertex(feature.owner()).ifPresent(v -> {
            final int size = v.getSize(feature);

            IntStream.range(0, size)
                    .forEachOrdered(i -> v.removeValue(feature, i));

            v.setSize(feature, 0);
        });
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        return graph.getVertex(feature.owner())
                .map(v -> v.getSize(feature))
                .filter(s -> s > 0);
    }

    //endregion

    //region Multi-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        return graph.getVertex(feature.owner())
                .flatMap(v -> v.getReference(feature, feature.position()))
                .map(ElementVertex::getElementId);
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        return graph.getVertex(feature.owner())
                .map(v -> MoreIterables.stream(v.getReferenceEdges(feature))
                        .sorted(Comparator.comparingInt(ElementEdge::getPosition))
                        .map(ElementEdge::getReferencedVertex)
                        .map(ElementVertex::getElementId))
                .orElseGet(Stream::empty);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        Optional<Id> previousId = graph.getVertex(feature.owner())
                .flatMap(v -> v.replaceReference(feature, feature.position(), reference))
                .map(ElementVertex::getElementId);

        if (!previousId.isPresent()) {
            throw new IndexOutOfBoundsException();
        }

        return previousId;
    }

    @Override
    public void addReference(ManyFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        ElementVertex vertex = graph.getOrCreateVertex(feature.owner());

        final int firstPosition = feature.position();
        final int size = vertex.getSize(feature);
        checkPositionIndex(firstPosition, size);

        if (firstPosition < size) {
            vertex.getReferenceEdges(feature, firstPosition, size + 1)
                    .forEach(e -> e.updatePosition(p -> p + 1));
        }

        vertex.setReference(feature, firstPosition, reference);
        vertex.setSize(feature, size + 1);
    }

    @Override
    public void addAllReferences(ManyFeatureBean feature, List<Id> collection) {
        checkNotNull(feature, "feature");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection, "collection");

        if (collection.isEmpty()) {
            return;
        }

        ElementVertex vertex = graph.getOrCreateVertex(feature.owner());

        final int firstPosition = feature.position();
        final int size = vertex.getSize(feature);
        checkPositionIndex(firstPosition, size);

        final int additionCount = collection.size();

        if (firstPosition < size) {
            vertex.getReferenceEdges(feature, firstPosition, size + additionCount)
                    .forEach(e -> e.updatePosition(p -> p + additionCount));
        }

        IntStream.range(0, additionCount)
                .forEachOrdered(i -> vertex.setReference(feature, firstPosition + i, collection.get(i)));

        vertex.setSize(feature, size + additionCount);
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        Optional<ElementVertex> optVertex = graph.getVertex(feature.owner());
        if (!optVertex.isPresent()) {
            return Optional.empty();
        }

        final ElementVertex vertex = optVertex.get();

        final int firstPosition = feature.position();
        final int size = vertex.getSize(feature);
        if (size == 0) {
            return Optional.empty();
        }

        Optional<Id> previousId = Optional.empty();

        for (ElementEdge edge : vertex.getReferenceEdges(feature, firstPosition, size)) {
            int position = edge.getPosition();
            if (position != firstPosition) {
                edge.updatePosition(p -> p - 1);
            }
            else {
                previousId = Optional.of(edge.getReferencedVertex().getElementId());
                edge.remove();
            }
        }

        vertex.setSize(feature, size - 1);

        return previousId;
    }

    @Override
    public void removeAllReferences(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        graph.getVertex(feature.owner()).ifPresent(v -> {
            v.removeReferences(feature);
            v.setSize(feature, 0);
        });
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean feature) {
        return sizeOfValue(feature);
    }

    //endregion
}
