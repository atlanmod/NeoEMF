/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.data.resource;

import fr.inria.atlanmod.commons.Lazy;
import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.annotation.Static;
import fr.inria.atlanmod.commons.io.MoreFiles;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.benchmarks.io.LocalWorkspace;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A class that provides static methods for {@link Resource} management.
 */
@Static
@ParametersAreNonnullByDefault
public final class Resources {

    /**
     * The name of the default ZIP file.
     */
    @Nonnull
    static final String RESOURCES_ZIP = "resources.zip";

    /**
     * The name of the default properties file.
     */
    @Nonnull
    private static final String RESOURCES_PROPERTIES = "resources.properties";

    /**
     * A {@link Map} that holds all available resources in {@link #RESOURCES_ZIP}.
     */
    private static List<String> availableResources;

    /**
     * A {@link Map} that holds all registered resources.
     */
    @Nonnull
    private static Lazy<Map<String, String>> registeredResources = Lazy.with(Resources::getRegisteredResources);

    private Resources() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Creates a new {@link Resource} from the given {@code resourceFileName}, and adapts it for the given {@code
     * targetAdapter}. The resource file can be placed in the resource ZIP, or in the file system.
     *
     * @param resourceFileName the name of the resource file
     * @param adapter          the adapter where to store the resource
     *
     * @return the created file
     *
     * @throws IOException if a error occurs during the creation of the resource
     */
    @Nonnull
    public static File getOrCreateResource(String resourceFileName, Adapter.Internal adapter) throws IOException {
        Log.info("Initializing the resource {0} ...", resourceFileName);

        checkNotNull(resourceFileName);
        checkArgument(resourceFileName.length() >= 4);

        // Check of the resource has to be converted
        boolean withIds = false;
        if (resourceFileName.endsWith(":id")) {
            resourceFileName = resourceFileName.substring(0, resourceFileName.length() - 3);
            withIds = true;
        }

        // Retrieve the original file name from a shortcut
        if (registeredResources.get().containsKey(resourceFileName.toLowerCase())) {
            resourceFileName = registeredResources.get().get(resourceFileName.toLowerCase());
        }

        // Retrieve the resource from the 'resources/resource.zip' or from the file system
        File file = getZipResources().contains(resourceFileName)
                ? new ResourceExtractor().extract(resourceFileName, LocalWorkspace.getResourcesDirectory())
                : new File(resourceFileName);

        // Post-process the resource
        if (withIds) {
            file = new ResourceConverter().apply(file, adapter);
        }

        return new ResourceMigrator().apply(file, adapter);
    }

    /**
     * Checks that the resource, identified by its {@code fileName}, is valid, i.e. if its extension is recognized.
     *
     * @param fileName the name of the resource file
     *
     * @throws IllegalArgumentException if the resource is not valid
     */
    public static void checkValid(String fileName) {
        checkNotNull(fileName);
        String fileExtension = MoreFiles.fileExtension(fileName);
        checkArgument(fileExtension.equals("xmi") || fileExtension.equals("zxmi"),
                "'%s' is an invalid resource file. Only *.%s and *.%s files are allowed.", fileName, "xmi", "zxmi");
    }

    // region ZIP

    /**
     * Returns all resources contained in the default ZIP file.
     *
     * @return a {@link List} of the file name of the resources
     *
     * @throws IOException if the ZIP file cannot be found
     */
    @Nonnull
    private static List<String> getZipResources() throws IOException {
        if (isNull(availableResources)) {
            availableResources = new ArrayList<>();

            try (ZipInputStream inputStream = new ZipInputStream(ResourceExtractor.class.getResourceAsStream("/" + RESOURCES_ZIP))) {
                ZipEntry entry = inputStream.getNextEntry();
                while (nonNull(entry)) {
                    if (!entry.isDirectory()) {
                        checkValid(entry.getName());
                        availableResources.add(new File(entry.getName()).getName());
                    }
                    inputStream.closeEntry();
                    entry = inputStream.getNextEntry();
                }
            }
        }
        return availableResources;
    }

    /**
     * Returns all registered resources.
     *
     * @return a {@link Map} containing all registered resources identified by their name
     */
    @Nonnull
    private static Map<String, String> getRegisteredResources() {
        try (InputStream in = Resources.class.getResourceAsStream('/' + RESOURCES_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(in);
            return properties.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
        }
        catch (IOException e) {
            throw Throwables.shouldNeverHappen(e);
        }
    }

    // endregion

    /**
     * Adapts the file name of the {@code file} according to this adapter.
     *
     * @param file the original resource file
     *
     * @return the adapted file name
     */
    @Nonnull
    public static String getFileName(File file, Adapter.Internal adapter, boolean isTemporary) {
        Resources.checkValid(file.getName());
        checkArgument(file.exists(), "Resource '%s' does not exist", file);

        String targetFileName = MoreFiles.nameWithoutExtension(file.getAbsolutePath());

        // Has been converted ?
        if (targetFileName.contains("-id." + adapter.getResourceExtension() + '.')) {
            targetFileName = targetFileName.replaceFirst("-id", Strings.EMPTY);
        }

        if (isTemporary) {
            targetFileName = '_' + Instant.now().getEpochSecond() + '.' + targetFileName;
        }

        final String additionalExtension = adapter.getStoreExtension();
        return targetFileName + (additionalExtension.isEmpty() ? Strings.EMPTY : '.' + additionalExtension);
    }
}
