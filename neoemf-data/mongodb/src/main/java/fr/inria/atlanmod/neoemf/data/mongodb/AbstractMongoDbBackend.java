/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.session.ClientSession;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;
import fr.inria.atlanmod.neoemf.data.mongodb.model.MetaClass;
import fr.inria.atlanmod.neoemf.data.mongodb.model.SingleFeature;
import fr.inria.atlanmod.neoemf.data.mongodb.model.StoredInstance;
import org.bson.BSON;
import org.bson.BasicBSONDecoder;
import org.bson.BasicBSONEncoder;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;
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
    private static final boolean SHOULD_DELETE_TEST_DATABASES = true;

    private boolean isTestDatabase;

    private ClientSession clientSession;

    protected boolean isTestDatabase() {
        return isTestDatabase;
    }


    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;

    private MongoCollection instancesCollection;

    @Override
    protected void internalSave() throws IOException {
        //Nothing to do, save is automatic... or is it ?
    }

    /**
     * If in a test database, will wait for completion of the update operation
     * before returning, otherwise does nothing
     *
     * @param result the pending update operation
     */
    protected void waitForUpdateCompletion(UpdateResult result) {
        if (!isTestDatabase())
            return;

        while (!result.wasAcknowledged()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Performs an updateOne on the instances collection
     * Provides causal consistency and acknowledgment waiting
     *
     * @param var1 the first update one parameter
     * @param var2 the second update one parameter
     */
    protected void updateOne(Bson var1, Bson var2) {
        waitForUpdateCompletion(
                clientSession == null ?
                        instancesCollection.updateOne(var1, var2) :
                        instancesCollection.updateOne(clientSession, var1, var2)
        );
    }

    protected FindIterable<StoredInstance> find(Bson var1) {
        if (clientSession == null)
            return instancesCollection.find(var1);
        else
            return instancesCollection.find(this.clientSession, var1);

    }

    /**
     * Insert a new stored instance in the collection
     * Provides causal consistency and acknowledgment waiting
     *
     * @param var the instance to store
     */
    protected void insertOne(StoredInstance var) {
        if (clientSession == null)
            instancesCollection.insertOne(var);
        else
            instancesCollection.insertOne(clientSession, var);
    }

    /**
     * Constructs a new {@code AbstractMongoDbBackend}.
     */
    protected AbstractMongoDbBackend(MongoClient client, MongoDatabase database) {
        this.mongoClient = client;
        this.mongoDatabase = database;

        //Are we in a test database ?
        this.isTestDatabase = this.mongoDatabase.getName().contains("test");

        //Create and get the needed collections
        this.instancesCollection = getOrCreateCollection(INSTANCES_COLLECTION_NAME, StoredInstance.class);


        if(!this.isTestDatabase){
            //Causally Consistent Client Session
            try {
                this.clientSession = mongoClient.startSession(ClientSessionOptions.builder().causallyConsistent(true).build());
                Log.info("Sessions support enabled");
            } catch (MongoClientException ex) {
                Log.info("MongoDB server does not support sessions, disabling sessions support");
            }

        }
        else{
            this.clientSession = null;
        }

    }

    /**
     * Gets a collection and creates it if needed
     *
     * @param name       the name of the collection
     * @param modelClass the class of the model to use for this collection
     * @return the corresponding {@link MongoCollection} instance
     */
    private MongoCollection getOrCreateCollection(String name, Class modelClass) {
        if (!hasCollection(name))
            this.mongoDatabase.createCollection(name);

        return this.mongoDatabase.getCollection(name, modelClass);
    }


    /**
     * Checks if the database has a certain collection
     *
     * @param collection the collection name
     * @return true if the database contains the collection, false otherwise
     */
    private boolean hasCollection(String collection) {
        for (String c : this.mongoDatabase.listCollectionNames()) {
            if (c.equals(collection))
                return true;
        }

        return false;
    }

    @Override
    protected void internalClose() throws IOException {
        if (SHOULD_DELETE_TEST_DATABASES && isTestDatabase()) {
            Log.info("Deleting test database " + this.mongoDatabase.getName());
            if (this.clientSession == null)
                this.mongoDatabase.drop();
            else
                this.mongoDatabase.drop(this.clientSession);
        }

        if (clientSession != null)
            this.clientSession.close();

        this.mongoClient.close();
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


    Document runCommand(String query){
        Bson command = new Document("eval", "db."+ INSTANCES_COLLECTION_NAME + "." + query + ";");
        System.out.println("[EXECUTING] " + command);
        return mongoDatabase.runCommand(command);
    }

    int getArraySize(String hexId, String fieldName, String fieldKey){

        String query = "aggregate(" +
            "[" +
                "{ $project: " +
                    "{\""+fieldName+"\" : 1, items: {" +
                        "$filter : {" +
                            "input: \"$items\", as: \"items\", "+
                            "cond: {$eq: [\"$_id\", \"" + hexId + "\"]}"+
                        "}," +
                    "}}" +
                "}, " +
                "{ $project: " +
                    "{ items: {" +
                        "$size: \"$" + fieldName+"."+fieldKey +"\"" +
                    "}}" +
                "}, " +
            "]" +
        ")";

        try{
            Document doc = (Document)((Document)runCommand(query)).get("retval");
            List<Document> res = (ArrayList<Document>)doc.get("_batch");
            return (Integer) res.get(0).get("items");
        } catch (MongoCommandException e){
            return 0;
        }
    }
}
