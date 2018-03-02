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
import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;
import fr.inria.atlanmod.neoemf.data.mongodb.model.StoredInstance;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;
import static com.mongodb.client.model.Updates.unset;
import static fr.inria.atlanmod.commons.Preconditions.checkNotContainsNull;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The default {@link MongoDbBackend} mapping.
 *
 * @see MongoDbBackendFactory
 */
@ParametersAreNonnullByDefault
class DefaultMongoDbBackend extends AbstractMongoDbBackend {

    /**
     * Constructs a new {@code DefaultMongoDbBackend}.
     *
     * @see MongoDbBackendFactory
     */
    protected DefaultMongoDbBackend(MongoDbConfig config, MongoClient client, MongoDatabase database) {
        super(config, client, database);
    }

    //region Single-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueOf");
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueFor");
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeValue");
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = (StoredInstance) instancesCollection.find(eq("_id", hexId)).projection(include("references")).first();

        if (instance == null || instance.getReferences() == null)
        {
            return Optional.empty();
        }
        else
        {
            if (instance.getReferences().containsKey(stringKeyId))
            {
                return Optional.of(IdConverters.withHexString().revert(instance.getReferences().get(stringKeyId)));
            }
            else
            {

                return Optional.empty();
            }
        }
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = (StoredInstance) instancesCollection.find(eq("_id", hexId)).projection(include("references")).first();
        if (instance == null)
        {
            instance = new StoredInstance();
            instance.setId(hexId);

            instance.getReferences().put(stringKeyId, reference.toHexString());

            instancesCollection.insertOne(instance);

            return Optional.empty();
        }
        else
        {
            instancesCollection.updateOne(
                    eq("_id", hexId),
                    set("references." + stringKeyId, reference.toHexString()));

            if (instance.getReferences().containsKey(stringKeyId))
            {
                return Optional.of(IdConverters.withHexString().revert(instance.getReferences().get(stringKeyId)));
            }
            else
            {
                return Optional.empty();
            }

        }
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = (StoredInstance) instancesCollection.find(eq("_id", hexId)).projection(include("references")).first();


        if (!(instance == null || instance.getReferences() == null) && instance.getReferences().containsKey(stringKeyId))
        {
            instancesCollection.updateOne(
                    eq("_id", hexId),
                    unset("references." + stringKeyId));
        }
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueOf");
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("allValuesOf");
    }

    @Nonnull
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueFor");
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        // TODO Implement this method
        throw Throwables.notImplementedYet("addValue");
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        // TODO Implement this method
        throw Throwables.notImplementedYet("addAllValues");
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeValue");
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeAllValues");
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("sizeOfValue");
    }

    //endregion

    //region Multi-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("referenceOf");
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("allReferencesOf");
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        // TODO Implement this method
        throw Throwables.notImplementedYet("referenceFor");
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        // TODO Implement this method
        throw Throwables.notImplementedYet("addReference");
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        // TODO Implement this method
        throw Throwables.notImplementedYet("addAllReferences");
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        checkNotNull(key, "key");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeReference");
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = (StoredInstance) instancesCollection.find(eq("_id", hexId)).projection(include("references")).first();

        if (!(instance == null || instance.getReferences() == null))
        {
            instancesCollection.updateOne(
                    eq("_id", hexId),
                    unset("references"));
        }
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = (StoredInstance) instancesCollection.find(eq("_id", hexId)).projection(include("references")).first();

        if (instance == null || instance.getReferences() == null)
        {
            return Optional.empty();
        }
        else
        {
            return Optional.of(instance.getReferences().size());
        }
    }

    //endregion
}
