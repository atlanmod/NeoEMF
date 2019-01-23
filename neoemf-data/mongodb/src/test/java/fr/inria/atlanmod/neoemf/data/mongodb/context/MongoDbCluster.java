package fr.inria.atlanmod.neoemf.data.mongodb.context;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;

import org.atlanmod.commons.concurrent.MoreThreads;
import org.atlanmod.commons.log.Log;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Preconditions.checkState;
import static java.util.Objects.nonNull;

/**
 * An object that holds the MongoDB in-memory-cluster instance.
 */
@ParametersAreNonnullByDefault
class MongoDbCluster {

    /**
     * Facility for testing MongoDB.
     */
    private static MongodExecutable mongo;

    /**
     * The server host.
     */
    private static String host;

    /**
     * The server port.
     */
    private static int port;

    /**
     * Whether the cluster has been initialized.
     */
    private static boolean initialized;

    /**
     * Checks whether the cluster has been successfully initialized.
     *
     * @return {@code true} if the cluster has been successfully initialized
     */
    public static boolean isInitialized() {
        return initialized && nonNull(mongo);
    }

    /**
     * Initializes the cluster.
     * <p>
     * If the cluster is already initialized, then calling this method does nothing.
     */
    // TODO Disable MongoDb logging
    public static void init() {
        // If already initialized, then do nothing
        if (initialized) {
            return;
        }

        initialized = true;

        try {
            Log.info("Initializing the MongoDB cluster... (This may take several minutes)");

            final MongodStarter starter = MongodStarter.getDefaultInstance();

            final IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.DEVELOPMENT).build();

            mongo = starter.prepare(mongodConfig);
            mongo.start();

            host = "localhost"; // FIXME Find host dynamically (failure with Linux - 127.0.1.1)
            port = mongodConfig.net().getPort();

            Log.info("MongoDB cluster running at {0}:{1,number,#}", host, port);

            MoreThreads.executeAtExit(MongoDbCluster::close);
        }
        catch (Throwable e) {
            reset();
            Log.error(e, "Unable to create the MongoDB cluster");
        }
    }

    /**
     * Closes the cluster and cleans all resources related to it.
     */
    private static void close() {
        try {
            Log.info("Shutting down the MongoDB cluster...");
            mongo.stop();

            reset();
        }
        catch (Throwable ignored) {
        }
    }

    /**
     * Resets the current configuration.
     */
    private static void reset() {
        mongo = null;
        host = null;
        port = 0;
    }

    /**
     * Returns the host of the MongoDB server.
     *
     * @return the host
     */
    @Nonnull
    public static String host() {
        checkState(isInitialized(), "The cluster has not been initialized");

        return host;
    }

    /**
     * Returns the port of the MongoDB server.
     *
     * @return the port
     */
    @Nonnegative
    public static int port() {
        checkState(isInitialized(), "The cluster has not been initialized");

        return port;
    }
}
