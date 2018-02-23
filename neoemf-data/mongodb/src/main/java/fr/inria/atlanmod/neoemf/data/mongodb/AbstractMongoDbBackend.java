/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;
import fr.inria.atlanmod.neoemf.data.mongodb.model.MetaClass;
import fr.inria.atlanmod.neoemf.data.mongodb.model.StoredInstance;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link MongoDbBackend} that provides overall behavior for the management of a MongoDb
 * database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractMongoDbBackend extends AbstractBackend implements MongoDbBackend {

    /**
     * The name of the collection containing the instances
     */
    private static final String INSTANCES_COLLECTION_NAME = "instances";

    /**
     * If set to true, the test databases will be deleted once the tests are done
     */
    private static final boolean SHOULD_DELETE_TEST_DATABASES = false;


    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;
    private MongoDbConfig mongoDbConfig;

    private MongoCollection instancesCollection;

    @Override
    protected void internalSave() throws IOException {
        //Nothing to do, save is automatic
    }

    /**
     * Constructs a new {@code AbstractMongoDbBackend}.
     */
    protected AbstractMongoDbBackend(MongoDbConfig config, MongoClient client, MongoDatabase database) {
        this.mongoDbConfig = config;
        this.mongoClient = client;
        this.mongoDatabase = database;

        //Create and get the needed collections
        this.instancesCollection = getOrCreateCollection(INSTANCES_COLLECTION_NAME, StoredInstance.class);
    }

    /**
     * Gets a collection and creates it if needed
     * @param name the name of the collection
     * @param modelClass the class of the model to use for this collection
     * @return the corresponding {@link MongoCollection} instance
     */
    private MongoCollection getOrCreateCollection(String name, Class modelClass)
    {
        if (!hasCollection(name))
            this.mongoDatabase.createCollection(name);

        return this.mongoDatabase.getCollection(name, modelClass);
    }


    /**
     * Checks if the database has a certain collection
     * @param collection the collection name
     * @return true if the database contains the collection, false otherwise
     */
    private boolean hasCollection(String collection)
    {
        for (String c : this.mongoDatabase.listCollectionNames())
        {
            if (c.equals(collection))
                return true;
        }

        return false;
    }

    @Override
    protected void internalClose() throws IOException {
        if (SHOULD_DELETE_TEST_DATABASES && this.mongoDatabase.getName().contains("test"))
            this.mongoDatabase.drop();
        
        this.mongoClient.close();
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        // TODO Implement this method
        throw Throwables.notImplementedYet("containerOf");
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        // TODO Implement this method
        throw Throwables.notImplementedYet("containerFor");
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeContainer");
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        String hexId = id.toHexString();
        StoredInstance instance = (StoredInstance) instancesCollection.find(eq("_id", hexId)).first();

        if (instance == null || instance.getMetaClass() == null)
        {
            return Optional.empty();
        }
        else
        {
            return Optional.of(ClassBean.of(instance.getMetaClass().getName(), instance.getMetaClass().getUri()));
        }

    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        String hexId = id.toHexString();
        MetaClass newMetaClass = new MetaClass(metaClass.name(), metaClass.uri());

        StoredInstance instance = (StoredInstance) instancesCollection.find(eq("_id", hexId)).first();
        if (instance == null)
        {
            instance = new StoredInstance();
            instance.setId(hexId);

            instance.setMetaClass(newMetaClass);

            instancesCollection.insertOne(instance);
        }
        else
        {
            instancesCollection.updateOne(
                    eq("_id", hexId),
                    combine(
                            set("metaClass.name", metaClass.name()),
                            set("metaClass.uri", metaClass.uri())));
        }

        return true;
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        // TODO Implement this method
        throw Throwables.notImplementedYet("allInstancesOf");
    }
}
