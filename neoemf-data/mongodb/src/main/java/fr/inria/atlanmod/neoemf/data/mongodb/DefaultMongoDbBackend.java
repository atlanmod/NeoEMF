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

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.io.serializer.BinarySerializerFactory;
import fr.inria.atlanmod.commons.io.serializer.StringSerializer;
import fr.inria.atlanmod.commons.io.serializer.StringSerializerFactory;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mongodb.model.StoredInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.pushEach;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.unset;
import static fr.inria.atlanmod.commons.Preconditions.checkNotContainsNull;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * The default {@link MongoDbBackend} mapping.
 *
 * @see MongoDbBackendFactory
 */
@ParametersAreNonnullByDefault
class DefaultMongoDbBackend extends AbstractMongoDbBackend {

    // region Fields
    // TODO Move fields in their respectives classes (StoredInstance)

    /**
     *
     */
    @Nonnull
    private static final String FIELD_SINGLE_VALUE = "singlevaluedValues";

    /**
     *
     */
    @Nonnull
    private static final String FIELD_SINGLE_REF = "singlevaluedReferences";

    /**
     *
     */
    @Nonnull
    private static final String FIELD_MANY_VALUE = "multivaluedValues";

    /**
     *
     */
    @Nonnull
    private static final String FIELD_MANY_REF = "multivaluedReferences";

    // endregion

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

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_SINGLE_VALUE, featureId)))
                .first();

        if (nonNull(instance) && nonNull(instance.getSinglevaluedValues()) && instance.getSinglevaluedValues().containsKey(featureId)) {
            return Optional.of(deserializeValue(instance.getSinglevaluedValues().get(featureId)));
        }

        return Optional.empty();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_SINGLE_VALUE, featureId)))
                .first();

        if (isNull(instance)) {
            instance = new StoredInstance();
            instance.setId(ownerId);
            instance.getSinglevaluedValues().put(featureId, serializeValue(value));

            insertOne(instance);
        }
        else {
            updateOne(
                    eq(FIELD_ID, ownerId),
                    set(combineField(FIELD_SINGLE_VALUE, featureId), serializeValue(value))
            );

            if (instance.getSinglevaluedValues().containsKey(featureId)) {
                return Optional.of(deserializeValue(instance.getSinglevaluedValues().get(featureId)));
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
                eq(FIELD_ID, ownerId),
                unset(combineField(FIELD_SINGLE_VALUE, featureId))
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

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_SINGLE_REF, featureId)))
                .first();

        if (nonNull(instance) && nonNull(instance.getSinglevaluedReferences()) && instance.getSinglevaluedReferences().containsKey(featureId)) {
            return Optional.of(idConverter.revert(instance.getSinglevaluedReferences().get(featureId)));
        }

        return Optional.empty();
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_SINGLE_REF, featureId)))
                .first();

        if (isNull(instance)) {
            instance = new StoredInstance();
            instance.setId(ownerId);
            instance.getSinglevaluedReferences().put(featureId, idConverter.convert(reference));

            insertOne(instance);
        }
        else {
            updateOne(
                    eq(FIELD_ID, ownerId),
                    set(combineField(FIELD_SINGLE_REF, featureId), idConverter.convert(reference))
            );

            if (instance.getSinglevaluedReferences().containsKey(featureId)) {
                return Optional.of(idConverter.revert(instance.getSinglevaluedReferences().get(featureId)));
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
                eq(FIELD_ID, ownerId),
                unset(combineField(FIELD_SINGLE_REF, featureId))
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

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_VALUE, featureId)))
                .first();

        if (nonNull(instance) && nonNull(instance.getMultivaluedValues()) && instance.getMultivaluedValues().containsKey(featureId) && instance.getMultivaluedValues().get(featureId).size() > feature.position()) {
            return Optional.of(deserializeValue(instance.getMultivaluedValues().get(featureId).get(feature.position())));
        }

        return Optional.empty();
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_VALUE, featureId)))
                .first();

        if (nonNull(instance) && !instance.getMultivaluedValues().isEmpty() && instance.getMultivaluedValues().containsKey(featureId)) {
            List<String> values = instance.getMultivaluedValues().get(featureId);
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

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_VALUE, featureId)))
                .first();

        if (isNull(instance)) {
            return Optional.empty();
        }

        if (!instance.getMultivaluedValues().containsKey(featureId) || feature.position() >= instance.getMultivaluedValues().get(featureId).size()) {
            throw new NoSuchElementException();
        }

        List<String> values = instance.getMultivaluedValues().get(featureId);

        Optional<V> previousValue = Optional.of(deserializeValue(instance.getMultivaluedValues().get(featureId).get(feature.position())));
        values.set(feature.position(), serializeValue(value));

        updateOne(
                eq(FIELD_ID, ownerId),
                set(combineField(FIELD_MANY_VALUE, featureId), values)
        );

        return previousValue;
    }

    @Override
    public <V> void addValue(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_VALUE, featureId)))
                .first();

        if (isNull(instance)) {
            return;
        }

        final int size = sizeOf(ownerId, FIELD_MANY_VALUE, featureId);

        if (feature.position() > size) {
            throw new IndexOutOfBoundsException();
        }

        List<String> newValues = Stream.of(value).map(this::serializeValue).collect(Collectors.toList());

        if (size > 0) {
            updateOne(
                    eq(FIELD_ID, ownerId),
                    pushEach(combineField(FIELD_MANY_VALUE, featureId), newValues, new PushOptions().position(feature.position()))
            );
        }
        else {
            updateOne(
                    eq(FIELD_ID, ownerId),
                    set(combineField(FIELD_MANY_VALUE, featureId), newValues)
            );
        }
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

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_VALUE, featureId)))
                .first();

        if (isNull(instance)) {
            return;
        }

        final int size = sizeOf(ownerId, FIELD_MANY_VALUE, featureId);

        if (feature.position() > size) {
            throw new IndexOutOfBoundsException();
        }

        List<String> newValues = collection.stream().map(this::serializeValue).collect(Collectors.toList());

        if (size > 0) {
            updateOne(
                    eq(FIELD_ID, ownerId),
                    pushEach(combineField(FIELD_MANY_VALUE, featureId), newValues, new PushOptions().position(feature.position()))
            );
        }
        else {
            updateOne(
                    eq(FIELD_ID, ownerId),
                    set(combineField(FIELD_MANY_VALUE, featureId), newValues)
            );
        }
    }

    @Override
    public <V> int appendValue(SingleFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_VALUE, featureId)))
                .first();

        final List<String> values = Optional.ofNullable(instance.getMultivaluedValues().get(featureId)).orElseGet(ArrayList::new);
        final int position = values.size();

        values.add(serializeValue(value));

        updateOne(
                eq(FIELD_ID, ownerId),
                set(combineField(FIELD_MANY_VALUE, featureId), values)
        );

        return position;
    }

    @Override
    public <V> int appendAllValues(SingleFeatureBean feature, List<? extends V> collection) {
        checkNotNull(feature, "feature");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection, "collection");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_VALUE, featureId)))
                .first();

        final List<String> values = Optional.ofNullable(instance.getMultivaluedValues().get(featureId)).orElseGet(ArrayList::new);
        final int firstPosition = values.size();

        collection.stream().map(this::serializeValue).collect(Collectors.toCollection(() -> values));

        updateOne(
                eq(FIELD_ID, ownerId),
                set(combineField(FIELD_MANY_VALUE, featureId), values)
        );

        return firstPosition;
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_VALUE, featureId)))
                .first();

        if (isNull(instance) || !instance.getMultivaluedValues().containsKey(featureId) || instance.getMultivaluedValues().get(featureId).size() < feature.position()) {
            return Optional.empty();
        }

        List<String> values = instance.getMultivaluedValues().get(featureId);
        Optional<V> previousValue = Optional.of(deserializeValue(values.get(feature.position())));

        values.remove(feature.position());

        updateOne(
                eq(FIELD_ID, ownerId),
                set(combineField(FIELD_MANY_VALUE, featureId), values)
        );

        return previousValue;
    }

    @Override
    public void removeAllValues(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());

        updateOne(
                eq(FIELD_ID, ownerId),
                unset(FIELD_MANY_VALUE)
        );
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_VALUE, featureId)))
                .first();

        return Optional.ofNullable(instance)
                .map(StoredInstance::getMultivaluedValues)
                .filter(m -> m.containsKey(featureId))
                .map(m -> m.get(featureId))
                .map(List::size)
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

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_REF, featureId)))
                .first();

        if (nonNull(instance) && nonNull(instance.getMultivaluedReferences()) && instance.getMultivaluedReferences().containsKey(featureId) && instance.getMultivaluedReferences().get(featureId).size() > feature.position()) {
            return Optional.of(idConverter.revert(instance.getMultivaluedReferences().get(featureId).get(feature.position())));
        }

        return Optional.empty();
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_REF, featureId)))
                .first();

        if (nonNull(instance) && !instance.getMultivaluedReferences().isEmpty() && instance.getMultivaluedReferences().containsKey(featureId)) {
            List<String> references = instance.getMultivaluedReferences().get(featureId);
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

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_REF, featureId)))
                .first();

        if (isNull(instance)) {
            return Optional.empty();
        }

        if (!instance.getMultivaluedReferences().containsKey(featureId) || feature.position() >= instance.getMultivaluedReferences().get(featureId).size()) {
            throw new NoSuchElementException();
        }

        List<String> rs = instance.getMultivaluedReferences().get(featureId);

        Optional<Id> previousId = Optional.of(idConverter.revert(instance.getMultivaluedReferences().get(featureId).get(feature.position())));
        rs.set(feature.position(), idConverter.convert(reference));

        updateOne(
                eq(FIELD_ID, ownerId),
                set(combineField(FIELD_MANY_REF, featureId), rs)
        );

        return previousId;
    }

    @Override
    public void addReference(ManyFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_REF, featureId)))
                .first();

        if (isNull(instance)) {
            return;
        }

        final int size = sizeOf(ownerId, FIELD_MANY_REF, featureId);

        if (feature.position() > size) {
            throw new IndexOutOfBoundsException();
        }

        List<String> newReferences = Stream.of(reference).map(idConverter::convert).collect(Collectors.toList());

        if (size > 0) {
            updateOne(
                    eq(FIELD_ID, ownerId),
                    pushEach(combineField(FIELD_MANY_REF, featureId), newReferences, new PushOptions().position(feature.position()))
            );
        }
        else {
            updateOne(
                    eq(FIELD_ID, ownerId),
                    set(combineField(FIELD_MANY_REF, featureId), newReferences)
            );
        }
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

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_REF, featureId)))
                .first();

        if (isNull(instance)) {
            return;
        }

        final int size = sizeOf(ownerId, FIELD_MANY_REF, featureId);

        if (feature.position() > size) {
            throw new IndexOutOfBoundsException();
        }

        List<String> newReferences = collection.stream().map(idConverter::convert).collect(Collectors.toList());

        if (size > 0) {
            updateOne(
                    eq(FIELD_ID, ownerId),
                    pushEach(combineField(FIELD_MANY_REF, featureId), newReferences, new PushOptions().position(feature.position()))
            );
        }
        else {
            updateOne(
                    eq(FIELD_ID, ownerId),
                    set(combineField(FIELD_MANY_REF, featureId), newReferences)
            );
        }
    }

    @Override
    public int appendReference(SingleFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_REF, featureId)))
                .first();

        final List<String> references = Optional.ofNullable(instance.getMultivaluedReferences().get(featureId)).orElseGet(ArrayList::new);
        final int position = references.size();

        references.add(idConverter.convert(reference));

        updateOne(
                eq(FIELD_ID, ownerId),
                set(combineField(FIELD_MANY_REF, featureId), references)
        );

        return position;
    }

    @Override
    public int appendAllReferences(SingleFeatureBean feature, List<Id> collection) {
        checkNotNull(feature, "feature");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection, "collection");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_REF, featureId)))
                .first();

        final List<String> references = Optional.ofNullable(instance.getMultivaluedReferences().get(featureId)).orElseGet(ArrayList::new);
        final int firstPosition = references.size();

        collection.stream().map(idConverter::convert).collect(Collectors.toCollection(() -> references));

        updateOne(
                eq(FIELD_ID, ownerId),
                set(combineField(FIELD_MANY_REF, featureId), references)
        );

        return firstPosition;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_REF, featureId)))
                .first();

        if (isNull(instance) || !instance.getMultivaluedReferences().containsKey(featureId) || instance.getMultivaluedReferences().get(featureId).size() < feature.position()) {
            return Optional.empty();
        }

        List<String> references = instance.getMultivaluedReferences().get(featureId);
        Optional<Id> previousId = Optional.of(idConverter.revert(references.get(feature.position())));

        references.remove(feature.position());

        updateOne(
                eq(FIELD_ID, ownerId),
                set(combineField(FIELD_MANY_REF, featureId), references)
        );

        return previousId;
    }

    @Override
    public void removeAllReferences(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());

        updateOne(
                eq(FIELD_ID, ownerId),
                unset(FIELD_MANY_REF)
        );
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final StoredInstance instance = find(eq(FIELD_ID, ownerId))
                .projection(include(combineField(FIELD_MANY_REF, featureId)))
                .first();

        return Optional.ofNullable(instance)
                .map(StoredInstance::getMultivaluedReferences)
                .filter(m -> m.containsKey(featureId))
                .map(m -> m.get(featureId))
                .map(List::size)
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
