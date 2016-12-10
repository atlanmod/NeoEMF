package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;

import org.assertj.core.api.Assertions;
import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;
import static fr.inria.atlanmod.neoemf.NeoAssertions.catchThrowable;

public abstract class AllUriTest extends AllTest {

    protected static final String SCHEME_INVALID = "invalid";

    private File file;

    protected File file() {
        return file;
    }

    protected abstract String name();

    protected abstract String uriScheme();

    protected abstract PersistenceBackendFactory persistenceBackendFactory();

    protected abstract URI createURI(URI uri);

    protected abstract URI createFileURI(File file);

    @Before
    public void setUp() {
        PersistenceBackendFactoryRegistry.register(uriScheme(), persistenceBackendFactory());
        file = tempFile(name());
    }

    @After
    public void tearDown() {
        PersistenceBackendFactoryRegistry.unregisterAll();
    }

    @Test
    public void testCreateUriFromStandardUri() {
        URI validURI = URI.createURI(uriScheme() + ":/test");
        URI neoURI = createURI(validURI);
        assertThat(neoURI).hasScheme(uriScheme());
    }

    @Test
    public void testCreateUriFromFileUri() {
        URI fileURI = URI.createFileURI(file().getAbsolutePath());
        URI neoURI = createURI(fileURI);
        assertThat(neoURI).hasScheme(uriScheme());
    }

    @Test
    public void testCreateFileUriFromFile() {
        URI neoURI = createFileURI(file());
        assertThat(neoURI).hasScheme(uriScheme());
    }

    @Test
    public void testCreateUriFromStandardUriInvalidScheme() {
        URI invalidURI = URI.createURI(SCHEME_INVALID + ":/test");

        Throwable thrown = catchThrowable(() -> createURI(invalidURI));
        Assertions.assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
