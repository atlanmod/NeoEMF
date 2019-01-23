/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.PushOptions;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mongodb.document.ModelDocument;

import org.atlanmod.commons.collect.MoreIterables;
import org.atlanmod.commons.io.serializer.BinarySerializerFactory;
import org.atlanmod.commons.io.serializer.StringSerializer;
import org.atlanmod.commons.io.serializer.StringSerializerFactory;
import org.bson.conversions.Bson;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.pushEach;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.unset;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkNotContainsNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The default {@link MongoDbBackend} mapping.
 *
 * @see MongoDbBackendFactory
 */
@ParametersAreNonnullByDefault
class DefaultMongoDbBackend extends AbstractMongoDbBackend {

    /**
     *
     */
    @Nonnull
    private final StringSerializer<Object> serializer = StringSerializerFactory.base64(BinarySerializerFactory.getInstance().forAny());

    /**
     * Constructs a new {@code DefaultMongoDbBackend}.
     *
     * @see MongoDbBackendFactory
     */
    protected DefaultMongoDbBackend(MongoClient client, MongoDatabase database) {
        super(client, database);
    }

    //region Single-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final Bson query = and(
                eq(ModelDocument.F_ID, ownerId),
                exists(ModelDocument.F_SINGLE_VALUE)
        );

        final Bson projection = include(fieldWithSuffix(ModelDocument.F_SINGLE_VALUE, featureId));

        final ModelDocument instance = find(query, ModelDocument.class)
                .projection(projection)
                .first();

        return Optional.ofNullable(instance)
                .map(ModelDocument::getSingleValues)
                .filter(m -> m.containsKey(featureId))
                .map(m -> m.get(featureId))
                .map(this::deserializeValue);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        ModelDocument instance = find(eq(ModelDocument.F_ID, ownerId), ModelDocument.class)
                .projection(include(fieldWithSuffix(ModelDocument.F_SINGLE_VALUE, featureId)))
                .first();

        if (isNull(instance)) {
            instance = new ModelDocument();
            instance.setId(ownerId);
            instance.getSingleValues().put(featureId, serializeValue(value));

            insertOne(instance);
        }
        else {
            updateOne(
                    eq(ModelDocument.F_ID, ownerId),
                    set(fieldWithSuffix(ModelDocument.F_SINGLE_VALUE, featureId), serializeValue(value))
            );

            if (instance.getSingleValues().containsKey(featureId)) {
                return Optional.of(deserializeValue(instance.getSingleValues().get(featureId)));
            }
        }

        return Optional.empty();
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        updateOne(
                eq(ModelDocument.F_ID, ownerId),
                unset(fieldWithSuffix(ModelDocument.F_SINGLE_VALUE, featureId))
        );
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final Bson query = and(
                eq(ModelDocument.F_ID, ownerId),
                exists(ModelDocument.F_SINGLE_REFERENCE)
        );

        final Bson projection = include(fieldWithSuffix(ModelDocument.F_SINGLE_REFERENCE, featureId));

        final ModelDocument instance = find(query, ModelDocument.class)
                .projection(projection)
                .first();

        return Optional.ofNullable(instance)
                .map(ModelDocument::getSingleReferences)
                .filter(m -> m.containsKey(featureId))
                .map(m -> m.get(featureId))
                .map(idConverter::revert);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        ModelDocument instance = find(eq(ModelDocument.F_ID, ownerId), ModelDocument.class)
                .projection(include(fieldWithSuffix(ModelDocument.F_SINGLE_REFERENCE, featureId)))
                .first();

        if (isNull(instance)) {
            instance = new ModelDocument();
            instance.setId(ownerId);
            instance.getSingleReferences().put(featureId, idConverter.convert(reference));

            insertOne(instance);
        }
        else {
            updateOne(
                    eq(ModelDocument.F_ID, ownerId),
                    set(fieldWithSuffix(ModelDocument.F_SINGLE_REFERENCE, featureId), idConverter.convert(reference))
            );

            if (instance.getSingleReferences().containsKey(featureId)) {
                return Optional.of(idConverter.revert(instance.getSingleReferences().get(featureId)));
            }
        }

        return Optional.empty();
    }

    @Override
    public void removeReference(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        updateOne(
                eq(ModelDocument.F_ID, ownerId),
                unset(fieldWithSuffix(ModelDocument.F_SINGLE_REFERENCE, featureId))
        );
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final ModelDocument instance = find(eq(ModelDocument.F_ID, ownerId), ModelDocument.class)
                .projection(include(fieldWithSuffix(ModelDocument.F_MANY_VALUE, featureId)))
                .first();

        if (nonNull(instance) && nonNull(instance.getManyValues()) && instance.getManyValues().containsKey(featureId) && instance.getManyValues().get(featureId).size() > feature.position()) {
            return Optional.of(deserializeValue(instance.getManyValues().get(featureId).get(feature.position())));
        }

        return Optional.empty();
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final ModelDocument instance = find(eq(ModelDocument.F_ID, ownerId), ModelDocument.class)
                .projection(include(fieldWithSuffix(ModelDocument.F_MANY_VALUE, featureId)))
                .first();

        if (nonNull(instance) && !instance.getManyValues().isEmpty() && instance.getManyValues().containsKey(featureId)) {
            List<String> values = instance.getManyValues().get(featureId);
            return MoreIterables.stream(values).map(this::<V>deserializeValue);
        }

        return Stream.empty();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final ModelDocument instance = find(eq(ModelDocument.F_ID, ownerId), ModelDocument.class)
                .projection(include(fieldWithSuffix(ModelDocument.F_MANY_VALUE, featureId)))
                .first();

        if (isNull(instance)) {
            return Optional.empty();
        }

        if (!instance.getManyValues().containsKey(featureId) || feature.position() >= instance.getManyValues().get(featureId).size()) {
            throw new NoSuchElementException();
        }

        List<String> values = instance.getManyValues().get(featureId);

        Optional<V> previousValue = Optional.of(deserializeValue(instance.getManyValues().get(featureId).get(feature.position())));
        values.set(feature.position(), serializeValue(value));

        updateOne(
                eq(ModelDocument.F_ID, ownerId),
                set(fieldWithSuffix(ModelDocument.F_MANY_VALUE, featureId), values)
        );

        return previousValue;
    }

    @Override
    public <V> void addValue(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        addAllValues(feature, Collections.singletonList(value));
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean feature, List<? extends V> collection) {
        checkNotNull(feature, "feature");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection, "collection");

        if (collection.isEmpty()) {
            return;
        }

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final int size = sizeOfValue(feature.withoutPosition()).orElse(0);

        if (feature.position() > size) {
            throw new IndexOutOfBoundsException();
        }

        List<String> newValues = collection.stream().map(this::serializeValue).collect(Collectors.toList());

        final String fieldName = fieldWithSuffix(ModelDocument.F_MANY_VALUE, featureId);

        final Bson update = size > 0
                ? pushEach(fieldName, newValues, new PushOptions().position(feature.position()))
                : set(fieldName, newValues);

        updateOne(
                eq(ModelDocument.F_ID, ownerId),
                update
        );
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final ModelDocument instance = find(eq(ModelDocument.F_ID, ownerId), ModelDocument.class)
                .projection(include(fieldWithSuffix(ModelDocument.F_MANY_VALUE, featureId)))
                .first();

        if (isNull(instance) || !instance.getManyValues().containsKey(featureId) || instance.getManyValues().get(featureId).size() < feature.position()) {
            return Optional.empty();
        }

        List<String> values = instance.getManyValues().get(featureId);
        Optional<V> previousValue = Optional.of(deserializeValue(values.get(feature.position())));

        values.remove(feature.position());

        updateOne(
                eq(ModelDocument.F_ID, ownerId),
                set(fieldWithSuffix(ModelDocument.F_MANY_VALUE, featureId), values)
        );

        return previousValue;
    }

    @Override
    public void removeAllValues(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());

        updateOne(
                eq(ModelDocument.F_ID, ownerId),
                unset(ModelDocument.F_MANY_VALUE)
        );
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());

        return Optional.of(sizeOf(ownerId, ModelDocument.F_MANY_VALUE, feature.id()))
                .filter(s -> s != 0);
    }

    //endregion

    //region Multi-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final ModelDocument instance = find(eq(ModelDocument.F_ID, ownerId), ModelDocument.class)
                .projection(include(fieldWithSuffix(ModelDocument.F_MANY_REFERENCE, featureId)))
                .first();

        if (nonNull(instance) && nonNull(instance.getManyReferences()) && instance.getManyReferences().containsKey(featureId) && instance.getManyReferences().get(featureId).size() > feature.position()) {
            return Optional.of(idConverter.revert(instance.getManyReferences().get(featureId).get(feature.position())));
        }

        return Optional.empty();
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final ModelDocument instance = find(eq(ModelDocument.F_ID, ownerId), ModelDocument.class)
                .projection(include(fieldWithSuffix(ModelDocument.F_MANY_REFERENCE, featureId)))
                .first();

        if (nonNull(instance) && !instance.getManyReferences().isEmpty() && instance.getManyReferences().containsKey(featureId)) {
            List<String> references = instance.getManyReferences().get(featureId);
            return MoreIterables.stream(references).map(idConverter::revert);
        }

        return Stream.empty();
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final ModelDocument instance = find(eq(ModelDocument.F_ID, ownerId), ModelDocument.class)
                .projection(include(fieldWithSuffix(ModelDocument.F_MANY_REFERENCE, featureId)))
                .first();

        if (isNull(instance)) {
            return Optional.empty();
        }

        if (!instance.getManyReferences().containsKey(featureId) || feature.position() >= instance.getManyReferences().get(featureId).size()) {
            throw new NoSuchElementException();
        }

        List<String> rs = instance.getManyReferences().get(featureId);

        Optional<Id> previousId = Optional.of(idConverter.revert(instance.getManyReferences().get(featureId).get(feature.position())));
        rs.set(feature.position(), idConverter.convert(reference));

        updateOne(
                eq(ModelDocument.F_ID, ownerId),
                set(fieldWithSuffix(ModelDocument.F_MANY_REFERENCE, featureId), rs)
        );

        return previousId;
    }

    @Override
    public void addReference(ManyFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        addAllReferences(feature, Collections.singletonList(reference));
    }

    @Override
    public void addAllReferences(ManyFeatureBean feature, List<Id> collection) {
        checkNotNull(feature, "feature");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection, "collection");

        if (collection.isEmpty()) {
            return;
        }

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final int size = sizeOfReference(feature.withoutPosition()).orElse(0);

        if (feature.position() > size) {
            throw new IndexOutOfBoundsException();
        }

        List<String> newReferences = collection.stream().map(idConverter::convert).collect(Collectors.toList());

        final String fieldName = fieldWithSuffix(ModelDocument.F_MANY_REFERENCE, featureId);

        final Bson update = size > 0
                ? pushEach(fieldName, newReferences, new PushOptions().position(feature.position()))
                : set(fieldName, newReferences);

        updateOne(
                eq(ModelDocument.F_ID, ownerId),
                update
        );
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final ModelDocument instance = find(eq(ModelDocument.F_ID, ownerId), ModelDocument.class)
                .projection(include(fieldWithSuffix(ModelDocument.F_MANY_REFERENCE, featureId)))
                .first();

        if (isNull(instance) || !instance.getManyReferences().containsKey(featureId) || instance.getManyReferences().get(featureId).size() < feature.position()) {
            return Optional.empty();
        }

        List<String> references = instance.getManyReferences().get(featureId);
        Optional<Id> previousId = Optional.of(idConverter.revert(references.get(feature.position())));

        references.remove(feature.position());

        updateOne(
                eq(ModelDocument.F_ID, ownerId),
                set(fieldWithSuffix(ModelDocument.F_MANY_REFERENCE, featureId), references)
        );

        return previousId;
    }

    @Override
    public void removeAllReferences(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());

        updateOne(
                eq(ModelDocument.F_ID, ownerId),
                unset(ModelDocument.F_MANY_REFERENCE)
        );
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());

        return Optional.of(sizeOf(ownerId, ModelDocument.F_MANY_REFERENCE, feature.id()))
                .filter(s -> s != 0);
    }

    //endregion

    // region MongoDB

    @Nonnull
    private <T> String serializeValue(T value) {
        return serializer.convert(value);
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    private <T> T deserializeValue(String value) {
        return (T) serializer.revert(value);
    }

    // endregion
}
