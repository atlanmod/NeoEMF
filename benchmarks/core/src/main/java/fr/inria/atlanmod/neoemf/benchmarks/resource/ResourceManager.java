package fr.inria.atlanmod.neoemf.benchmarks.resource;

import fr.inria.atlanmod.common.collect.MoreIterables;
import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.Workspace;
import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

        return Migrator.migrate(sourceFile, adapter);
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

    /**
     * A class that provides static methods for {@link Resource} migration.
     */
    @ParametersAreNonnullByDefault
    static final class Migrator {

        /**
         * The map holding the link between the original {@link EObject} and its adaptation.
         */
        private final Map<EObject, EObject> correspondences = new HashMap<>();

        /**
         * Constructs a new {@code Migrator}.
         */
        private Migrator() {
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
        public static File migrate(File resourceFile, Adapter.Internal adapter) throws IOException {
            Log.info("Adapting resource to URI {0}", adapter.initAndGetEPackage().getNsURI());

            String targetFileName = getNameWithoutExtension(resourceFile.getName()) + "." + adapter.getResourceExtension() + ".zxmi";
            File targetFile = Workspace.getResourcesDirectory().resolve(targetFileName).toFile();

            if (targetFile.exists()) {
                Log.info("Already existing resource: {0}", targetFile);
                return targetFile;
            }

            ResourceSet resourceSet = ResourceCreator.loadResourceSet();

            Log.info("Loading resource from: {0}", resourceFile);

            URI sourceUri = URI.createFileURI(resourceFile.getAbsolutePath());
            Resource sourceResource = resourceSet.getResource(sourceUri, true);

            Log.info("Copying resource content...");

            EObject targetRoot = new Migrator().migrate(sourceResource.getContents().get(0), adapter.initAndGetEPackage());
            sourceResource.unload();

            Log.info("Migrating resource content...");

            URI targetUri = URI.createFileURI(targetFile.getAbsolutePath());
            Resource targetResource = resourceSet.createResource(targetUri);

            targetResource.getContents().add(targetRoot);

            Log.info("Saving resource to: {0}", targetResource.getURI());

            Map<String, Object> saveOpts = new HashMap<>();
            saveOpts.put(XMIResource.OPTION_ZIP, true);
            saveOpts.put(XMIResource.OPTION_ENCODING, StandardCharsets.UTF_8.name());
            targetResource.save(saveOpts);

            targetResource.unload();

            return targetFile;
        }

        /**
         * Adapts the given {@code sourceRoot} in a particular implementation, specified by the {@code targetPackage}.
         *
         * @param sourceRoot    the sourceRoot {@link EObject} to adapt
         * @param targetPackage the {@link EPackage}
         *
         * @return the adapted {@code sourceRoot}
         */
        @Nonnull
        private EObject migrate(EObject sourceRoot, EPackage targetPackage) {
            final EObject targetRoot = correspondentOf(sourceRoot, targetPackage);
            copy(sourceRoot, targetRoot);

            MoreIterables.stream(() -> EcoreUtil.<EObject>getAllContents(sourceRoot, true))
                    .forEach(sourceObject -> copy(sourceObject, correspondentOf(sourceObject, targetPackage)));

            return targetRoot;
        }

        /**
         * Copies the {@code src} to the {@code tgt}.
         *
         * @param src the src {@link EObject}
         * @param tgt the corresponding {@link EObject}
         *
         * @see #correspondentOf(EObject, EPackage)
         */
        @SuppressWarnings("unchecked")
        private void copy(EObject src, EObject tgt) {
            final EClass tgtClass = tgt.eClass();
            final EPackage tgtPackage = tgtClass.getEPackage();

            src.eClass().getEAllStructuralFeatures().stream()
                    .filter(src::eIsSet)
                    .forEach(srcFeature -> {
                        final Object srcValue = src.eGet(srcFeature);

                        Object tgtValue;
                        if (EAttribute.class.isInstance(srcFeature)) {
                            tgtValue = srcValue;
                        }
                        else {
                            if (!srcFeature.isMany()) {
                                tgtValue = correspondentOf((EObject) srcValue, tgtPackage);
                            }
                            else {
                                tgtValue = MoreIterables.stream((Iterable<EObject>) srcValue)
                                        .map(v -> correspondentOf(v, tgtPackage))
                                        .collect(Collectors.toCollection(BasicEList::new));
                            }
                        }

                        tgt.eSet(tgtClass.getEStructuralFeature(srcFeature.getName()), tgtValue);
                    });
        }

        /**
         * Adapts the given {@code object} in a particular implementation, specified by the {@code targetPackage}, and
         * stores the correspondence in the given {@code correspondences} {@link Map}.
         *
         * @param object        the {@link EObject} to adapt
         * @param targetPackage the {@link EPackage} used to retrieve the corresponding {@link EObject}
         *
         * @return the corresponding {@link EObject}
         */
        @Nonnull
        private EObject correspondentOf(EObject object, EPackage targetPackage) {
            return correspondences.computeIfAbsent(object, o -> EcoreUtil.create(EClass.class.cast(targetPackage.getEClassifier(object.eClass().getName()))));
        }
    }
}
