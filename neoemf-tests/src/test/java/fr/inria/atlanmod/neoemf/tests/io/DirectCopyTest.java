package fr.inria.atlanmod.neoemf.tests.io;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;
import fr.inria.atlanmod.neoemf.io.util.IOTestUtils;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;

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
public class DirectCopyTest extends AbstractMigrationTest {

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
        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

        // Comparing with EMF
        expected = IOTestUtils.loadWithEMF(expectedUri);
        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);
    }
}
