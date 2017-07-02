package fr.inria.atlanmod.neoemf.benchmarks.resource;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.Workspace;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A class that provides static methods for {@link Resource} management.
 */
@ParametersAreNonnullByDefault
public final class ResourceManager {

    /**
     * The name of the default ZIP file.
     */
    private static final String RESOURCES_ZIP = "resources.zip";

    /**
     * The name of the default properties file.
     */
    private static final String RESOURCES_PROPERTIES = "resources.properties";

    /**
     * A {@link Map} that holds all available resources in {@link #RESOURCES_ZIP}.
     */
    private static List<String> availableResources;

    /**
     * A {@link Map} that holds all registered resources.
     */
    private static Map<String, String> registeredResources;

    /**
     * Creates a new {@link Resource} from the given {@code resourceFileName}, and adapts it for the given
     * {@code targetAdapter}. The resource file can be placed in the resource ZIP, or in the file system.
     *
     * @param resourceFileName the name of the resource file
     * @param adapter          the adapter where to store the resource
     *
     * @return the created file
     *
     * @throws IOException if a error occurs during the creation of the resource
     */
    @Nonnull
    public static File createResource(String resourceFileName, Adapter.Internal adapter) throws IOException {
        if (getRegisteredResources().containsKey(resourceFileName.toLowerCase())) {
            resourceFileName = getRegisteredResources().get(resourceFileName.toLowerCase());
        }

        File sourceFile;
        if (getZipResources().contains(resourceFileName)) {
            // Get file from the resources/resource.zip
            sourceFile = extractFromZip(resourceFileName, Workspace.getResourcesDirectory());
        }
        else {
            // Get the file from the file system
            sourceFile = new File(resourceFileName);
        }

        checkValidResource(sourceFile.getName());
        checkArgument(sourceFile.exists(), "Resource '%s' does not exist", sourceFile);

        return migrate(sourceFile, adapter);
    }

    /**
     * Creates a new {@link Resource} from the given {@code file}, and adapts it for the given {@code
     * targetAdapter}.
     *
     * @param resourceFile the resource file
     * @param adapter      the adapter where to store the resource
     *
     * @return the created file
     *
     * @throws IOException if a error occurs during the creation of the resource
     */
    @Nonnull
    private static File migrate(File resourceFile, Adapter.Internal adapter) throws IOException {
        Log.info("Adapting resource to URI {0}", adapter.initAndGetEPackage().getNsURI());

        String targetFileName = getNameWithoutExtension(resourceFile.getName()) + "." + adapter.getResourceExtension() + ".zxmi";
        File targetFile = Workspace.getResourcesDirectory().resolve(targetFileName).toFile();

        if (targetFile.exists()) {
            Log.info("Already existing resource: {0}", targetFile);
            return targetFile;
        }

        // Replace the 'xmlns:java' value
        Charset charset = StandardCharsets.UTF_8;

        try (ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(targetFile.toPath()))) {
            out.putNextEntry(new ZipEntry("ResourceContents"));

            try (BufferedReader reader = Files.newBufferedReader(resourceFile.toPath(), charset); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, charset))) {
                boolean migrated = false;

                String line;
                while (nonNull(line = reader.readLine())) {
                    if (!migrated && line.contains("xmlns:java")) {
                        writer.write(line.replaceFirst("(xmlns:java=\")[^\"]*(\")", "$1" + adapter.initAndGetEPackage().getNsURI() + "$2"));
                        migrated = true; // Only one occurence
                    }
                    else {
                        writer.write(line);
                    }
                    writer.newLine();
                }
            }
        }

        return targetFile;
    }

    /**
     * Checks that the resource, identified by its {@code filename}, is valid, i.e. if its extension is recognized.
     *
     * @param filename the name of the resource file
     *
     * @throws IllegalArgumentException if the resource is not valid
     */
    static void checkValidResource(String filename) {
        checkNotNull(filename);

        checkArgument(filename.endsWith(".xmi") || filename.endsWith(".zxmi"),
                "'%s' is an invalid resource file. Only *.%s and *.%s files are allowed.", filename, "xmi", "zxmi");
    }

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

            try (ZipInputStream inputStream = new ZipInputStream(ResourceCreator.class.getResourceAsStream("/" + RESOURCES_ZIP))) {
                ZipEntry entry = inputStream.getNextEntry();
                while (nonNull(entry)) {
                    if (!entry.isDirectory()) {
                        checkValidResource(entry.getName());
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
     *
     * @throws IOException if the properties file cannot be found
     */
    @Nonnull
    private static Map<String, String> getRegisteredResources() throws IOException {
        if (isNull(registeredResources)) {
            Properties properties = new Properties();
            properties.load(ResourceCreator.class.getResourceAsStream("/" + RESOURCES_PROPERTIES));
            registeredResources = properties.entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
        }
        return registeredResources;
    }

    /**
     * Extracts a {@link ZipEntry}, named {@code filename}, from the default ZIP file to the {@code outputDir}.
     *
     * @param filename  the file name of the {@link ZipEntry} to extract
     * @param outputDir the directory where to extract the file
     *
     * @return the extracted file
     *
     * @throws IOException if an I/O error occurs during the extraction
     */
    @Nonnull
    private static File extractFromZip(String filename, Path outputDir) throws IOException {
        File outputFile = null;
        boolean fileFound = false;
        try (ZipInputStream inputStream = new ZipInputStream(ResourceCreator.class.getResourceAsStream("/" + RESOURCES_ZIP))) {
            ZipEntry entry = inputStream.getNextEntry();
            while (nonNull(entry) || !fileFound) {
                if (!entry.isDirectory() && Objects.equals(new File(entry.getName()).getName(), filename)) {
                    outputFile = extractEntryFromZip(inputStream, entry, outputDir);
                    fileFound = true;
                }
                inputStream.closeEntry();
                entry = inputStream.getNextEntry();
            }
        }
        return outputFile;
    }

    /**
     * Extracts a {@link ZipEntry} from the given {@code input} to the {@code outputDir}.
     *
     * @param input     the input stream of the ZIP file
     * @param entry     the entry in the ZIP file
     * @param outputDir the directory where to extract the file
     *
     * @return the extracted file
     *
     * @throws IOException if an I/O error occurs during the extraction
     */
    @Nonnull
    private static File extractEntryFromZip(ZipInputStream input, ZipEntry entry, Path outputDir) throws IOException {
        File outputFile = outputDir.resolve(new File(entry.getName()).getName()).toFile();
        if (outputFile.exists()) {
            Log.info("Already extracted resource: {0}", outputFile);
            return outputFile;
        }

        try (OutputStream output = new FileOutputStream(outputFile)) {
            final byte[] buffer = new byte[4096];
            int count;
            while (-1 != (count = input.read(buffer))) {
                output.write(buffer, 0, count);
            }
        }

        return outputFile;
    }

    /**
     * Retrieves the file name without its extension of {@code file}.
     *
     * @param file the file name
     *
     * @return the filename without its extension
     */
    @Nonnull
    protected static String getNameWithoutExtension(String file) {
        checkNotNull(file);

        String fileName = new File(file).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }
}
