package fr.inria.atlanmod.neoemf.tests.io;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

/**
 * A test case about the direct copy, using {@link fr.inria.atlanmod.neoemf.io.reader.Reader} and {@link
 * fr.inria.atlanmod.neoemf.io.writer.Writer}.
 */
// TODO Move file test-cases in another class: no need for backends
public class DirectCopyTest extends AbstractIOTest {

    /**
     * Checks the copy from a {@link Backend} to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopyBackend() throws IOException {
        final File sourceBackend = file();
        final File targetBackend = Paths.get(file() + "-copy").toFile();

        URI expectedUri = IOResourceManager.xmiStandard();

        try (DataMapper sourceMapper = context().createMapper(sourceBackend); InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toMapper(sourceMapper)
                    .migrate();

            try (DataMapper targetMapper = context().createMapper(targetBackend)) {
                Migrator.fromMapper(sourceMapper)
                        .toMapper(targetMapper)
                        .migrate();
            }
        }

        final EObject actual = closeAtExit(context().loadResource(targetBackend)).getContents().get(0);

        // Comparing PersistentBackend
        EObject expected = closeAtExit(context().loadResource(sourceBackend)).getContents().get(0);
        assertEObjectAreEqual(actual, expected);

        // Comparing with EMF
        expected = loadWithEMF(expectedUri);
        assertEObjectAreEqual(actual, expected);
    }

    /**
     * Checks the copy from a XMI file to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopyStandard() throws IOException {
        final File targetFile = new File(file() + ".xmi");
        Log.info("Exporting to {0}", targetFile);

        URI expectedUri = IOResourceManager.xmiStandard();

        try (InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toXmi(targetFile)
                    .migrate();
        }

        final EObject actual = loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = loadWithEMF(expectedUri);

        assertEObjectAreEqual(actual, expected);
    }

    /**
     * Checks the copy from a compressed XMI file to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopyStandardCompressed() throws IOException {
        final File targetFile = new File(file() + ".zxmi");
        Log.info("Exporting to {0}", targetFile);

        URI expectedUri = IOResourceManager.xmiStandard();

        try (InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toZXmi(targetFile)
                    .migrate();
        }

        final EObject actual = loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = loadWithEMF(expectedUri);

        assertEObjectAreEqual(actual, expected);
    }

    /**
     * Checks the copy from a XMI file to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopyWithId() throws IOException {
        final File targetFile = new File(file() + ".xmi");
        Log.info("Exporting to {0}", targetFile);

        URI expectedUri = IOResourceManager.xmiWithId();

        try (InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toXmi(targetFile)
                    .migrate();
        }

        final EObject actual = loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = loadWithEMF(expectedUri);

        assertEObjectAreEqual(actual, expected);
    }

    /**
     * Checks the copy from a compressed XMI file to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopyWithIdCompressed() throws IOException {
        final File targetFile = new File(file() + ".zxmi");
        Log.info("Exporting to {0}", targetFile);

        URI expectedUri = IOResourceManager.zxmiWithId();

        try (InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toZXmi(targetFile)
                    .migrate();
        }

        final EObject actual = loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = loadWithEMF(expectedUri);

        assertEObjectAreEqual(actual, expected);
    }
}
