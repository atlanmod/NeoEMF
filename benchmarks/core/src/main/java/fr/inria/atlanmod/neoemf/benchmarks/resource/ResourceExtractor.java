package fr.inria.atlanmod.neoemf.benchmarks.resource;

import fr.inria.atlanmod.commons.log.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.annotation.Nonnull;

import static java.util.Objects.nonNull;

/**
 * An object that extract a {@link org.eclipse.emf.ecore.resource.Resource} from a ZIP file.
 */
final class ResourceExtractor {

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
    public File extract(String filename, Path outputDir) throws IOException {
        File outputFile = null;
        boolean fileFound = false;
        try (ZipInputStream inputStream = new ZipInputStream(Stores.class.getResourceAsStream("/" + Resources.RESOURCES_ZIP))) {
            ZipEntry entry = inputStream.getNextEntry();
            while (nonNull(entry) || !fileFound) {
                if (!entry.isDirectory() && Objects.equals(new File(entry.getName()).getName(), filename)) {
                    outputFile = extractEntry(inputStream, entry, outputDir);
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
    private File extractEntry(ZipInputStream input, ZipEntry entry, Path outputDir) throws IOException {
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
}
