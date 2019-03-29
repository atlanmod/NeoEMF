/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb.context;

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.config.io.ProcessOutput;
import de.flapdoodle.embed.process.io.IStreamProcessor;

import org.atlanmod.commons.concurrent.MoreThreads;
import org.atlanmod.commons.log.Level;
import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.log.Logger;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkState;

/**
 * An object that holds the MongoDB in-memory-cluster instance.
 */
@ParametersAreNonnullByDefault
class MongoDbCluster {

    /**
     * The global logger for MongoDB clusters.
     */
    private static final Logger LOG = Log.forName("org.mongodb.driver");

    /**
     * Facility for testing MongoDB.
     */
    private static MongodExecutable mongoExecutable;

    /**
     * MongoDB process.
     */
    private static MongodProcess mongoProcess;

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
        return initialized && nonNull(mongoExecutable);
    }

    /**
     * Initializes the cluster.
     * <p>
     * If the cluster is already initialized, then calling this method does nothing.
     */
    public static void init() {
        // If already initialized, then do nothing
        if (initialized) {
            return;
        }

        initialized = true;

        try {
            Log.info("Initializing the MongoDB cluster... (This may take several minutes)");

            final ProcessOutput processOutput = new ProcessOutput(
                    new CommonsStreamProcessor(LOG, Level.INFO),
                    new CommonsStreamProcessor(LOG, Level.ERROR),
                    new CommonsStreamProcessor(LOG, Level.DEBUG)
            );

            final IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
                    .defaults(Command.MongoD)
                    .processOutput(processOutput)
                    .build();

            final MongodStarter starter = MongodStarter.getInstance(runtimeConfig);

            final IMongodConfig mongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.DEVELOPMENT)
                    .build();

            mongoExecutable = starter.prepare(mongodConfig);
            mongoProcess = mongoExecutable.start();

            host = mongodConfig.net().getServerAddress().getHostAddress();
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
            mongoProcess.stop();
            mongoExecutable.stop();

            reset();
        }
        catch (Throwable ignored) {
        }
    }

    /**
     * Resets the current configuration.
     */
    private static void reset() {
        mongoProcess = null;
        mongoExecutable = null;
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

    /**
     * An {@link IStreamProcessor} that logs events on a {@link Logger}.
     */
    @ParametersAreNonnullByDefault
    private static class CommonsStreamProcessor implements IStreamProcessor {

        /**
         * The logger where to log events.
         */
        private final Logger logger;

        /**
         * The logging level.
         */
        private final Level level;

        /**
         * Constructs a new {@code CommonsStreamProcessor}.
         *
         * @param logger the logger where to log events
         * @param level  the logging level
         */
        public CommonsStreamProcessor(Logger logger, Level level) {
            this.logger = logger;
            this.level = level;
        }

        @Override
        public void process(String s) {
            logger.log(level, s);
        }

        @Override
        public void onProcessed() {
            // Do nothing
        }
    }
}
