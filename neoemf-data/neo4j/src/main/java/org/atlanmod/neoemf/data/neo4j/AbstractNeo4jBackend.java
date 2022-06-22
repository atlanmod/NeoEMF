package org.atlanmod.neoemf.data.neo4j;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.atlanmod.commons.Throwables;
import org.neo4j.configuration.GraphDatabaseSettings;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.schema.IndexDefinition;
import org.neo4j.graphdb.schema.Schema;

import static org.atlanmod.commons.Guards.checkNotNull;

/**
 * An abstract {@link Neo4jBackend} that provides overall behavior for the management of a Neo4j
 * database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractNeo4jBackend extends AbstractBackend implements Neo4jBackend {

    /**
     * The property key used to identify {@link Node} instances.
     *
     * <b>NOTE:</b> using "__id" instead of "_id" avoids collision with Rexster's "_id".
     */
    @Nonnull
    protected static final String ID = "__id";

    private GraphDatabaseService databaseService;
    private DatabaseManagementService managementService;
    private Transaction transaction;
    private IndexDefinition IdIndex;

    /**
     * Constructs a new {@code AbstractNeo4jBackend}.
     */
    protected AbstractNeo4jBackend(DatabaseManagementService service) {
        // TODO Implement this constructor
        managementService = service;
        databaseService = service.database(GraphDatabaseSettings.DEFAULT_DATABASE_NAME);
        this.registerShutdownHook();
        this.createIndex();
        transaction = databaseService.beginTx();
    }

    private void createIndex() {
        try (Transaction tx = databaseService.beginTx()) {
            Schema schema = tx.schema();
            IdIndex = schema.indexFor(Labels.EObject)
                    .on(ID)
                    .withName("ids")
                    .create();
            tx.commit();
        }
    }

    @Override
    protected void internalSave() throws IOException {
        // TODO Implement this method
        transaction.commit();
    }

    @Override
    protected void internalClose() throws IOException {
        // TODO Implement this method
        managementService.shutdown();
    }

    @Override
    protected void internalCopyTo(DataMapper target) {
        // TODO Implement this method
        throw Throwables.notImplementedYet("innerCopyTo");
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

        Node node = getOrCreateNode(id);
        Node owner = getOrCreateNode(container.owner());

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

        // TODO Implement this method
        throw Throwables.notImplementedYet("metaClassOf");
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        // TODO Implement this method
        //throw Throwables.notImplementedYet("metaClassFor");


        return false;
    }

    private Node getOrCreateNode(Id id) {
        ResourceIterator<Node> it = transaction.findNodes(Labels.EObject, ID, id);
        if (it.hasNext()) {
            return it.next();
        }

        Node newNode = transaction.createNode();
        newNode.setProperty(ID, id);
        return newNode;
    }

    protected Node getNode(Id id) {
        // TODO
        return null;
    }

    @Nonnull
    @Override
    public Stream<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        // TODO Implement this method
        throw Throwables.notImplementedYet("allInstancesOf");
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(managementService::shutdown));
    }

}
