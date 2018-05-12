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
import com.mongodb.session.ClientSession;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mongodb.model.Container;
import fr.inria.atlanmod.neoemf.data.mongodb.model.MetaClass;
import fr.inria.atlanmod.neoemf.data.mongodb.model.StoredInstance;

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
import static com.mongodb.client.model.Updates.unset;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * An abstract {@link MongoDbBackend} that provides overall behavior for the management of a MongoDb database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractMongoDbBackend extends AbstractBackend implements MongoDbBackend {

    // region Fields
    // TODO Move fields in their respectives classes (StoredInstance, MetaClass, Container)

    /**
     *
     */
    @Nonnull
    protected static final String FIELD_ID = "_id";

    /**
     *
     */
    @Nonnull
    private static final String FIELD_CONTAINER = "container";

    /**
     *
     */
    @Nonnull
    private static final String FIELD_CONTAINER_OWNER = combineField(FIELD_CONTAINER, "owner");

    /**
     *
     */
    @Nonnull
    private static final String FIELD_CONTAINER_ID = combineField(FIELD_CONTAINER, "id");

    /**
     *
     */
    @Nonnull
    private static final String FIELD_METACLASS = "metaClass";

    /**
     *
     */
    @Nonnull
    private static final String FIELD_METACLASS_NAME = combineField(FIELD_METACLASS, "name");

    /**
     *
     */
    @Nonnull
    private static final String FIELD_METACLASS_URI = combineField(FIELD_METACLASS, "uri");

    // endregion

    /**
     * The name of the collection containing the instances
     */
    @Nonnull
    private static final String COLLECTION_NAME = "instances";

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
    private final MongoCollection<StoredInstance> collection;

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

        this.collection = getOrCreateCollection(COLLECTION_NAME, StoredInstance.class);

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
    protected static String combineField(String prefix, String suffix) {
        return prefix + '.' + suffix;
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
        final StoredInstance instance = find(and(eq(FIELD_ID, ownerId), exists(FIELD_CONTAINER))).first();

        if (nonNull(instance) && nonNull(instance.getContainer())) {
            return Optional.of(instance.getContainer().toBean());
        }

        return Optional.empty();
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        final String ownerId = idConverter.convert(id);
        final Container newContainer = Container.fromBean(container);

        StoredInstance instance = find(eq(FIELD_ID, ownerId)).first();

        if (isNull(instance)) {
            instance = new StoredInstance();
            instance.setId(ownerId);
            instance.setContainer(newContainer);

            insertOne(instance);
        }
        else {
            updateOne(
                    eq(FIELD_ID, ownerId),
                    combine(
                            set(FIELD_CONTAINER_OWNER, newContainer.getOwner()),
                            set(FIELD_CONTAINER_ID, newContainer.getId())
                    )
            );
        }
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        final String ownerId = idConverter.convert(id);
        final StoredInstance instance = find(eq(FIELD_ID, ownerId)).first();

        if (nonNull(instance) && nonNull(instance.getContainer())) {
            updateOne(eq(FIELD_ID, ownerId), unset(FIELD_CONTAINER));
        }
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        final String ownerId = idConverter.convert(id);
        final StoredInstance instance = find(and(eq(FIELD_ID, ownerId), exists(FIELD_METACLASS))).first();

        if (nonNull(instance) && nonNull(instance.getMetaClass())) {
            return Optional.of(instance.getMetaClass().toBean());
        }

        return Optional.empty();
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        final String ownerId = idConverter.convert(id);
        final MetaClass newMetaClass = MetaClass.fromBean(metaClass);

        StoredInstance instance = find(eq(FIELD_ID, ownerId)).first();

        if (isNull(instance)) {
            instance = new StoredInstance();
            instance.setId(ownerId);
            instance.setMetaClass(newMetaClass);

            insertOne(instance);

            return true;
        }

        return false;
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        final Iterable<Bson> andFilters = metaClasses.stream()
                .map(c -> and(eq(FIELD_METACLASS_NAME, c.name()), eq(FIELD_METACLASS_URI, c.uri())))
                .collect(Collectors.toList());

        final Bson query = or(andFilters);

        final FindIterable<StoredInstance> find = find(query)
                .projection(include(FIELD_ID));

        return MoreIterables.stream(find)
                .map(StoredInstance::getId)
                .map(idConverter::revert)
                .collect(Collectors.toList());
    }

    // region MongoDB

    /**
     * Finds all documents in the collection that match the specified {@code filter}.
     *
     * @param filter a document describing the query filter
     *
     * @return an iterable of all matching documents
     */
    @Nonnull
    protected FindIterable<StoredInstance> find(Bson filter) {
        if (nonNull(clientSession)) {
            return collection.find(clientSession, filter);
        }
        else {
            return collection.find(filter);
        }
    }

    /**
     * Update a single document in the collection according to the specified arguments.
     *
     * @param filter a document describing the query filter
     * @param update a document describing the update
     */
    protected void updateOne(Bson filter, Bson update) {
        if (nonNull(clientSession)) {
            collection.updateOne(clientSession, filter, update);
        }
        else {
            collection.updateOne(filter, update);
        }
    }

    /**
     * Inserts the provided {@code document} in the collection.
     *
     * @param document the instance to store
     */
    protected void insertOne(StoredInstance document) {
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

        final List<Bson> pipeline = Arrays.asList(
                match(and(eq(FIELD_ID, id), exists(name))),
                limit(1),
                project(computed(sizeField, new Document(QueryOperators.SIZE, String.format("$%s.%d", name, index))))
        );

        try {
            @SuppressWarnings("unchecked") final AggregateIterable<BasicDBObject> aggregate = collection.aggregate(pipeline, BasicDBObject.class);
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
