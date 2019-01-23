/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.ClientSessionOptions;
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.QueryOperators;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.session.ClientSession;

import org.atlanmod.commons.collect.MoreIterables;
import org.atlanmod.commons.function.Converter;
import org.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mongodb.document.ClassDocument;
import fr.inria.atlanmod.neoemf.data.mongodb.document.ContainerDocument;
import fr.inria.atlanmod.neoemf.data.mongodb.document.ModelDocument;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.mongodb.client.model.Aggregates.limit;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.project;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Projections.computed;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.setOnInsert;
import static com.mongodb.client.model.Updates.unset;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * An abstract {@link MongoDbBackend} that provides overall behavior for the management of a MongoDb database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractMongoDbBackend extends AbstractBackend implements MongoDbBackend {

    /**
     * The {@link Converter} to store the hexadecimal representation of {@link Id}s.
     */
    @Nonnull
    protected final Converter<Id, String> idConverter = IdConverters.withHexString();

    /**
     * The MongoDB client.
     */
    @Nonnull
    private final MongoClient client;

    /**
     * The MongoDB database.
     */
    @Nonnull
    private final MongoDatabase database;

    /**
     * The MongoDB collection where to store the model.
     */
    @Nonnull
    private final MongoCollection<ModelDocument> collection;

    /**
     * The current client session, {@code null} if the {@link #client} does not support sessions.
     */
    @Nullable
    private ClientSession clientSession;

    /**
     * Constructs a new {@code AbstractMongoDbBackend}.
     */
    protected AbstractMongoDbBackend(MongoClient client, MongoDatabase database) {
        this.client = client;
        this.database = database;

        this.collection = getOrCreateCollection("instances", ModelDocument.class);

        // Causally-consistent session
        try {
            this.clientSession = client.startSession(ClientSessionOptions.builder().causallyConsistent(true).build());
        }
        catch (Exception ignored) {
            this.clientSession = null;
            Log.debug("Sessions are not supported by the MongoDB cluster to which this client is connected");
        }
    }

    @Nonnull
    protected static String fieldWithSuffix(String fieldName, String suffix) {
        return fieldName + '.' + suffix;
    }

    /**
     * Gets a collection and creates it if needed.
     *
     * @param name          the name of the collection
     * @param documentClass the class of the model to use for this collection
     *
     * @return the corresponding {@link MongoCollection} instance
     */
    @Nonnull
    @SuppressWarnings("SameParameterValue")
    private <D> MongoCollection<D> getOrCreateCollection(String name, Class<D> documentClass) {
        if (MoreIterables.stream(database.listCollectionNames()).noneMatch(name::equals)) {
            database.createCollection(name);
        }

        return database.getCollection(name, documentClass);
    }

    @Override
    protected void internalClose() {
        if (nonNull(clientSession)) {
            clientSession.close();
        }

        client.close();
    }

    @Override
    protected void internalSave() {
        // Do nothing
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        final String ownerId = idConverter.convert(id);

        final Bson query = and(
                eq(ModelDocument.F_ID, ownerId),
                exists(ModelDocument.F_CONTAINER)
        );

        final Bson projection = include(ModelDocument.F_CONTAINER);

        final ModelDocument instance = find(query, ModelDocument.class)
                .projection(projection)
                .first();

        return Optional.ofNullable(instance)
                .map(ModelDocument::getContainer)
                .map(ContainerDocument::toBean);
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        final String ownerId = idConverter.convert(id);
        final ContainerDocument newContainer = ContainerDocument.fromBean(container);

        final Bson filter = eq(ModelDocument.F_ID, ownerId);

        final Bson update = combine(
                set(fieldWithSuffix(ModelDocument.F_CONTAINER, ContainerDocument.F_OWNER), newContainer.getOwner()),
                set(fieldWithSuffix(ModelDocument.F_CONTAINER, ContainerDocument.F_ID), newContainer.getId())
        );

        updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        final String ownerId = idConverter.convert(id);

        final Bson filter = eq(ModelDocument.F_ID, ownerId);

        final Bson update = unset(ModelDocument.F_CONTAINER);

        updateOne(filter, update);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        final String ownerId = idConverter.convert(id);

        final Bson query = and(
                eq(ModelDocument.F_ID, ownerId),
                exists(ModelDocument.F_METACLASS)
        );

        final Bson projection = include(ModelDocument.F_METACLASS);

        final ModelDocument instance = find(query, ModelDocument.class)
                .projection(projection)
                .first();

        return Optional.ofNullable(instance)
                .map(ModelDocument::getMetaClass)
                .map(ClassDocument::toBean);
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        final String ownerId = idConverter.convert(id);
        final ClassDocument newMetaClass = ClassDocument.fromBean(metaClass);

        final Bson existsQuery = and(
                eq(ModelDocument.F_ID, ownerId),
                exists(ModelDocument.F_METACLASS)
        );

        final boolean notExists = !Optional.ofNullable(find(existsQuery, ModelDocument.class).first()).isPresent();

        if (notExists) {
            final Bson filter = eq(ModelDocument.F_ID, ownerId);

            final Bson update = combine(
                    setOnInsert(fieldWithSuffix(ModelDocument.F_METACLASS, ClassDocument.F_NAME), newMetaClass.getName()),
                    setOnInsert(fieldWithSuffix(ModelDocument.F_METACLASS, ClassDocument.F_URI), newMetaClass.getUri())
            );

            updateOne(filter, update, new UpdateOptions().upsert(true));
        }

        return notExists;
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        final Iterable<Bson> andFilters = metaClasses.stream()
                .map(c -> and(
                        eq(fieldWithSuffix(ModelDocument.F_METACLASS, ClassDocument.F_NAME), c.name()),
                        eq(fieldWithSuffix(ModelDocument.F_METACLASS, ClassDocument.F_URI), c.uri()))
                )
                .collect(Collectors.toList());

        final Bson query = or(andFilters);

        final Bson projection = include(ModelDocument.F_ID);

        final FindIterable<ModelDocument> find = find(query, ModelDocument.class)
                .projection(projection);

        return MoreIterables.stream(find)
                .map(ModelDocument::getId)
                .map(idConverter::revert)
                .collect(Collectors.toList());
    }

    // region MongoDB

    /**
     * Finds all documents in the collection that match the specified {@code filter}.
     *
     * @param filter a document describing the query filter
     * @param type   the class to decode each document into
     *
     * @return an iterable of all matching documents
     */
    @Nonnull
    protected <T> FindIterable<T> find(Bson filter, Class<T> type) {
        if (nonNull(clientSession)) {
            return collection.find(clientSession, filter, type);
        }
        else {
            return collection.find(filter, type);
        }
    }

    /**
     * Aggregates documents according to the specified aggregation {@code pipeline}.
     *
     * @param pipeline the aggregation pipeline
     * @param type     the class to decode each document into
     *
     * @return an iterable containing the result of the aggregation operation
     */
    @Nonnull
    protected <T> AggregateIterable<T> aggregate(List<? extends Bson> pipeline, Class<T> type) {
        if (nonNull(clientSession)) {
            return collection.aggregate(clientSession, pipeline, type);
        }
        else {
            return collection.aggregate(pipeline, type);
        }
    }

    /**
     * Update a single document in the collection according to the specified arguments.
     *
     * @param filter a document describing the query filter
     * @param update a document describing the update
     */
    protected void updateOne(Bson filter, Bson update) {
        updateOne(filter, update, new UpdateOptions());
    }

    /**
     * Update a single document in the collection according to the specified arguments.
     *
     * @param filter  a document describing the query filter
     * @param update  a document describing the update
     * @param options the options to apply to the update operation
     */
    protected void updateOne(Bson filter, Bson update, UpdateOptions options) {
        if (nonNull(clientSession)) {
            collection.updateOne(clientSession, filter, update, options);
        }
        else {
            collection.updateOne(filter, update, options);
        }
    }

    /**
     * Inserts the provided {@code document} in the collection.
     *
     * @param document the instance to store
     */
    protected void insertOne(ModelDocument document) {
        if (nonNull(clientSession)) {
            collection.insertOne(clientSession, document);
        }
        else {
            collection.insertOne(document);
        }
    }

    @Nonnegative
    protected int sizeOf(String id, String name, int index) {
        final String sizeField = "size";

        final Bson filter = and(
                eq(ModelDocument.F_ID, id),
                exists(name)
        );

        final Bson projection = computed(sizeField, new Document(QueryOperators.SIZE, fieldWithSuffix('$' + name, Integer.toString(index))));

        final List<Bson> pipeline = Arrays.asList(
                match(filter),
                limit(1),
                project(projection)
        );

        try {
            final AggregateIterable<BasicDBObject> aggregate = aggregate(pipeline, BasicDBObject.class);
            return MoreIterables.onlyElement(aggregate).map(o -> o.getInt(sizeField)).orElse(0);
        }
        catch (MongoCommandException e) {
            // FIXME Don't use an exception to determine the presence of an index
            if (e.getErrorCode() != 17124) { // "the $size operator requires an list" when index does not exist in collection
                throw e;
            }
            return 0;
        }
    }

    // endregion
}
