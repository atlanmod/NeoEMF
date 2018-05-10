/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
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
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mongodb.model.MetaClass;
import fr.inria.atlanmod.neoemf.data.mongodb.model.SingleFeature;
import fr.inria.atlanmod.neoemf.data.mongodb.model.StoredInstance;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import static com.mongodb.client.model.Projections.computed;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.unset;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * An abstract {@link MongoDbBackend} that provides overall behavior for the management of a MongoDb database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractMongoDbBackend extends AbstractBackend implements MongoDbBackend {

    /**
     * The name of the collection containing the instances
     */
    private static final String COLLECTION_NAME = "instances";

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

        String hexId = id.toHexString();
        StoredInstance instance = (StoredInstance) find(eq("_id", hexId)).first();

        if (instance == null || instance.getContainer() == null) {
            return Optional.empty();
        } else {
            return Optional.of(instance.getContainer().toSingleFeatureBean());
        }
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        String hexId = id.toHexString();
        SingleFeature newContainer = SingleFeature.fromSingleFeatureBean(container);

        StoredInstance instance = (StoredInstance) find(eq("_id", hexId)).first();
        if (instance == null) {
            instance = new StoredInstance();
            instance.setId(hexId);

            instance.setContainer(newContainer);

            insertOne(instance);
        } else {
            updateOne(
                    eq("_id", hexId),
                    combine(
                            set("container.owner", newContainer.getOwner()),
                            set("container.id", newContainer.getId())));
        }
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        String hexId = id.toHexString();
        StoredInstance instance = (StoredInstance) find(eq("_id", hexId)).first();

        if (instance != null && instance.getContainer() != null) {
            updateOne(eq("_id", hexId), unset("container"));
        }
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        String hexId = id.toHexString();
        StoredInstance instance = (StoredInstance) find(eq("_id", hexId)).first();

        if (instance == null || instance.getMetaClass() == null) {
            return Optional.empty();
        } else {
            return Optional.of(instance.getMetaClass().toClassBean());
        }

    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        String hexId = id.toHexString();
        MetaClass newMetaClass = MetaClass.fromClassBean(metaClass);

        StoredInstance instance = (StoredInstance) find(eq("_id", hexId)).first();
        if (instance == null) {
            instance = new StoredInstance();
            instance.setId(hexId);

            instance.setMetaClass(newMetaClass);

            insertOne(instance);
            return true;
        } else {

            if (instance.getMetaClass() != null) {
                updateOne(
                        eq("_id", hexId),
                        combine(
                                set("metaClass.name", metaClass.name()),
                                set("metaClass.uri", metaClass.uri())));
                return true;
            }
            else{
                return false;
            }
        }


    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        List<Id> list = new ArrayList<>();

        for (ClassBean bean : metaClasses) {
            find(
                    and(
                            eq("metaClass.name", bean.name()),
                            eq("metaClass.uri", bean.uri())
                    )
            ).forEach((Block<StoredInstance>) storedInstance -> {
                Id id = IdConverters.withHexString().revert(storedInstance.getId());
                list.add(id);
            });
        }

        return list;
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
    protected int getArraySize(String id, String name, String index) {
        final String sizeField = "size";

        final List<Bson> pipeline = Arrays.asList(
                match(and(eq("_id", id), exists(name))),
                limit(1),
                project(computed(sizeField, new Document(QueryOperators.SIZE, String.format("$%s.%s", name, index))))
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
