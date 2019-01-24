/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.QueryOperators;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.PushOptions;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.AllReferenceAs;
import fr.inria.atlanmod.neoemf.data.mongodb.document.ModelDocument;

import org.atlanmod.commons.collect.MoreIterables;
import org.atlanmod.commons.function.Converter;
import org.atlanmod.commons.io.serializer.BinarySerializerFactory;
import org.atlanmod.commons.io.serializer.StringSerializer;
import org.atlanmod.commons.io.serializer.StringSerializerFactory;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.mongodb.client.model.Aggregates.limit;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Projections.computed;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Projections.slice;
import static com.mongodb.client.model.Updates.pushEach;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.unset;
import static java.util.Objects.isNull;
import static org.atlanmod.commons.Preconditions.checkNotContainsNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The default {@link MongoDbBackend} mapping.
 *
 * @see MongoDbBackendFactory
 */
@ParametersAreNonnullByDefault
class DefaultMongoDbBackend extends AbstractMongoDbBackend implements AllReferenceAs<String> {

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

    @Nonnull
    @Override
    public Converter<Id, String> referenceConverter() {
        return idConverter;
    }

    //region Single-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final String fieldName = fieldWithSuffix(ModelDocument.F_SINGLE_FEATURE, featureId);

        final Bson filter = and(eq(ModelDocument.F_ID, ownerId), exists(fieldName));
        final Bson projection = include(fieldName);

        final ModelDocument instance = documents.find(filter).projection(projection).first();

        return Optional.ofNullable(instance)
                .map(ModelDocument::getSingleFeatures)
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

        final String fieldName = fieldWithSuffix(ModelDocument.F_SINGLE_FEATURE, featureId);

        final Bson filter = eq(ModelDocument.F_ID, ownerId);
        final Bson projection = include(fieldName);
        final Bson update = set(fieldName, serializeValue(value));

        final ModelDocument instance = documents.findOneAndUpdate(filter, update, new FindOneAndUpdateOptions().upsert(true).projection(projection));

        return Optional.ofNullable(instance)
                .map(ModelDocument::getSingleFeatures)
                .map(l -> l.get(featureId))
                .map(this::deserializeValue);
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final String fieldName = fieldWithSuffix(ModelDocument.F_SINGLE_FEATURE, featureId);

        final Bson filter = and(eq(ModelDocument.F_ID, ownerId), exists(fieldName));
        final Bson update = unset(fieldName);

        documents.updateOne(filter, update);
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final String fieldName = fieldWithSuffix(ModelDocument.F_MANY_FEATURE, featureId);

        final Bson filter = and(eq(ModelDocument.F_ID, ownerId), exists(fieldName));
        final Bson projection = slice(fieldName, feature.position(), 1);

        final ModelDocument instance = documents.find(filter).projection(projection).first();

        return Optional.ofNullable(instance)
                .map(ModelDocument::getManyFeatures)
                .map(m -> m.get(featureId))
                .filter(l -> !l.isEmpty())
                .map(l -> l.get(0))
                .map(this::deserializeValue);
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final String fieldName = fieldWithSuffix(ModelDocument.F_MANY_FEATURE, featureId);

        final Bson filter = and(eq(ModelDocument.F_ID, ownerId), exists(fieldName));
        final Bson projection = include(fieldName);

        final ModelDocument instance = documents.find(filter).projection(projection).first();

        return Optional.ofNullable(instance)
                .map(ModelDocument::getManyFeatures)
                .map(m -> m.get(featureId))
                .map(Collection::stream)
                .map(s -> s.map(this::<V>deserializeValue))
                .orElseGet(Stream::empty);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final String fieldName = fieldWithSuffix(ModelDocument.F_MANY_FEATURE, featureId);
        final String fieldNameWithPos = fieldWithSuffix(fieldName, Integer.toString(feature.position()));

        final Bson filter = and(eq(ModelDocument.F_ID, ownerId), exists(fieldNameWithPos));
        final Bson projection = slice(fieldName, feature.position(), 1);
        final Bson update = set(fieldNameWithPos, serializeValue(value));

        final ModelDocument instance = documents.findOneAndUpdate(filter, update, new FindOneAndUpdateOptions().projection(projection));

        final Optional<V> previousValue = Optional.ofNullable(instance)
                .map(ModelDocument::getManyFeatures)
                .map(m -> m.get(featureId))
                .filter(l -> !l.isEmpty())
                .map(l -> l.get(0))
                .map(this::deserializeValue);

        if (!previousValue.isPresent()) {
            throw new IndexOutOfBoundsException();
        }

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

        final String fieldName = fieldWithSuffix(ModelDocument.F_MANY_FEATURE, featureId);

        final Bson filter = eq(ModelDocument.F_ID, ownerId);
        final Bson update = pushEach(fieldName, newValues, new PushOptions().position(feature.position()));

        documents.updateOne(filter, update);
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());
        final String featureId = Integer.toString(feature.id());

        final String fieldName = fieldWithSuffix(ModelDocument.F_MANY_FEATURE, featureId);
        final String fieldNameWithPos = fieldWithSuffix(fieldName, Integer.toString(feature.position()));

        final Bson baseFilter = eq(ModelDocument.F_ID, ownerId);
        final Bson getFilter = and(baseFilter, exists(fieldNameWithPos));
        final Bson projection = include(fieldName);

        final ModelDocument instance = documents.find(getFilter).projection(projection).first();

        if (isNull(instance)) {
            return Optional.empty();
        }

        final List<String> values = instance.getManyFeatures().get(featureId);
        final Optional<V> previousValue = Optional.of(values.remove(feature.position())).map(this::deserializeValue);

        final Bson updateFilter = and(baseFilter, exists(fieldName));
        final Bson update = set(fieldName, values);

        documents.updateOne(updateFilter, update);

        return previousValue;
    }

    @Override
    public void removeAllValues(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());

        final String fieldName = ModelDocument.F_MANY_FEATURE;

        final Bson filter = and(eq(ModelDocument.F_ID, ownerId), exists(fieldName));
        final Bson update = unset(fieldName);

        documents.updateOne(filter, update);
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        final String ownerId = idConverter.convert(feature.owner());

        final String fieldName = ModelDocument.F_MANY_FEATURE;
        final String fieldSize = "size";

        final Bson filter = and(eq(ModelDocument.F_ID, ownerId), exists(fieldName));
        final Bson projection = computed(fieldSize, new Document(QueryOperators.SIZE, fieldWithSuffix('$' + fieldName, Integer.toString(feature.id()))));

        final List<Bson> pipeline = Arrays.asList(
                match(filter),
                limit(1),
                project(projection)
        );

        try {
            final AggregateIterable<BasicDBObject> aggregate = documents.aggregate(pipeline, BasicDBObject.class);
            return MoreIterables.onlyElement(aggregate).map(o -> o.getInt(fieldSize));
        }
        catch (MongoCommandException e) {
            // FIXME Don't use an exception to determine the presence of an index
            if (e.getErrorCode() != 17124) { // "the $size operator requires an list" when index does not exist in collection
                throw e;
            }
            return Optional.empty();
        }
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
