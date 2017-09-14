package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.io.util.IOResourceManager;
import fr.inria.atlanmod.neoemf.io.util.IOTestUtils;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * A test-case about the copy from a file to another.
 */
public class CopyTest extends AbstractTest {

    @BeforeClass
    public static void registerPackages() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

        IOResourceManager.registerAllPackages();
    }

    /**
     * Checks the copy from a XMI file to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopyStandard() throws IOException {
        final File targetFile = new File(workspace.newFile("io") + ".xmi");
        Log.info("Exporting to {0}", targetFile);

        URI expectedUri = IOResourceManager.xmiStandard();

        try (InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toXmi(targetFile)
                    .migrate();
        }

        final EObject actual = IOTestUtils.loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = IOTestUtils.loadWithEMF(expectedUri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);
    }

    /**
     * Checks the copy from a compressed XMI file to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopyStandardCompressed() throws IOException {
        final File targetFile = new File(workspace.newFile("io") + ".zxmi");
        Log.info("Exporting to {0}", targetFile);

        URI expectedUri = IOResourceManager.xmiStandard();

        try (InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toZXmi(targetFile)
                    .migrate();
        }

        final EObject actual = IOTestUtils.loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = IOTestUtils.loadWithEMF(expectedUri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);
    }

    /**
     * Checks the copy from a XMI file to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopyWithId() throws IOException {
        final File targetFile = new File(workspace.newFile("io") + ".xmi");
        Log.info("Exporting to {0}", targetFile);

        URI expectedUri = IOResourceManager.xmiWithId();

        try (InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toXmi(targetFile)
                    .migrate();
        }

        final EObject actual = IOTestUtils.loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = IOTestUtils.loadWithEMF(expectedUri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);
    }

    /**
     * Checks the copy from a compressed XMI file to another.
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void testCopyWithIdCompressed() throws IOException {
        final File targetFile = new File(workspace.newFile("io") + ".zxmi");
        Log.info("Exporting to {0}", targetFile);

        URI expectedUri = IOResourceManager.zxmiWithId();

        try (InputStream in = new URL(expectedUri.toString()).openStream()) {
            Migrator.fromXmi(in)
                    .toZXmi(targetFile)
                    .migrate();
        }

        final EObject actual = IOTestUtils.loadWithEMF(URI.createFileURI(targetFile.toString()));
        EObject expected = IOTestUtils.loadWithEMF(expectedUri);

        ModelComparisonUtils.assertEObjectAreEqual(actual, expected);
    }
}
