/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.UpdateOptions;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mongodb.document.ClassDocument;
import fr.inria.atlanmod.neoemf.data.mongodb.document.ContainerDocument;
import fr.inria.atlanmod.neoemf.data.mongodb.document.ModelDocument;

import org.atlanmod.commons.collect.MoreIterables;
import org.atlanmod.commons.function.Converter;
import org.bson.conversions.Bson;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.exists;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.setOnInsert;
import static com.mongodb.client.model.Updates.unset;
import static org.atlanmod.commons.Preconditions.checkNotNull;

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
     * The MongoDB collection where to store the model.
     */
    @Nonnull
    protected final MongoCollection<ModelDocument> documents;

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
     * Constructs a new {@code AbstractMongoDbBackend}.
     */
    protected AbstractMongoDbBackend(MongoClient client, MongoDatabase database) {
        this.client = client;
        this.database = database;

        this.documents = getOrCreateCollection("instances", ModelDocument.class);
    }

    @Nonnull
    protected static String concat(String... fieldNames) {
        return String.join(".", fieldNames);
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

        final Bson filter = and(eq(ModelDocument.F_ID, ownerId), exists(ModelDocument.F_CONTAINER));
        final Bson projection = include(ModelDocument.F_CONTAINER);

        final ModelDocument instance = documents.find(filter).projection(projection).first();

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
                set(concat(ModelDocument.F_CONTAINER, ContainerDocument.F_OWNER), newContainer.getOwner()),
                set(concat(ModelDocument.F_CONTAINER, ContainerDocument.F_ID), newContainer.getId())
        );

        documents.updateOne(filter, update, new UpdateOptions().upsert(true));
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        final String ownerId = idConverter.convert(id);

        final Bson filter = and(eq(ModelDocument.F_ID, ownerId), exists(ModelDocument.F_CONTAINER));
        final Bson update = unset(ModelDocument.F_CONTAINER);

        documents.updateOne(filter, update);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        final String ownerId = idConverter.convert(id);

        final Bson filter = and(eq(ModelDocument.F_ID, ownerId), exists(ModelDocument.F_METACLASS));
        final Bson projection = include(ModelDocument.F_METACLASS);

        final ModelDocument instance = documents.find(filter).projection(projection).first();

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

        final Bson filter = eq(ModelDocument.F_ID, ownerId);

        final Bson existsFilter = and(filter, exists(ModelDocument.F_METACLASS));
        final boolean notExists = documents.countDocuments(existsFilter, new CountOptions().limit(1)) == 0;

        if (notExists) {
            final Bson update = combine(
                    setOnInsert(concat(ModelDocument.F_METACLASS, ClassDocument.F_NAME), newMetaClass.getName()),
                    setOnInsert(concat(ModelDocument.F_METACLASS, ClassDocument.F_URI), newMetaClass.getUri())
            );

            documents.updateOne(filter, update, new UpdateOptions().upsert(true));
        }

        return notExists;
    }

    @Nonnull
    @Override
    public Stream<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        final Iterable<Bson> andFilters = metaClasses.stream()
                .map(c -> and(
                        eq(concat(ModelDocument.F_METACLASS, ClassDocument.F_NAME), c.name()),
                        eq(concat(ModelDocument.F_METACLASS, ClassDocument.F_URI), c.uri()))
                )
                .collect(Collectors.toList());

        final Bson filter = or(andFilters);
        final Bson projection = include(ModelDocument.F_ID);

        final FindIterable<ModelDocument> find = documents.find(filter).projection(projection);

        return MoreIterables.stream(find)
                .map(ModelDocument::getId)
                .map(idConverter::revert)
                .distinct();
    }
}
