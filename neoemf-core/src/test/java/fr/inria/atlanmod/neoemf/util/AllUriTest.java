package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.AllUnitTest;

import org.assertj.core.api.Assertions;
import org.eclipse.emf.common.util.URI;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public abstract class AllUriTest extends AllUnitTest {

    protected static final String SCHEME_INVALID = "invalid";

    protected abstract URI createURI(URI uri);

    protected abstract URI createFileURI(File file);

    @Test
    public void testCreateUriFromStandardUri() {
        URI validURI = URI.createURI(uriScheme() + ":/test");
        URI neoURI = createURI(validURI);
        assertThat(neoURI.scheme()).isEqualTo(uriScheme());
    }

    @Test
    public void testCreateUriFromFileUri() {
        URI fileURI = URI.createFileURI(file().getAbsolutePath());
        URI neoURI = createURI(fileURI);
        assertThat(neoURI.scheme()).isEqualTo(uriScheme());
    }

    @Test
    public void testCreateFileUriFromFile() {
        URI neoURI = createFileURI(file());
        assertThat(neoURI.scheme()).isEqualTo(uriScheme());
    }

    @Test
    public void testCreateUriFromStandardUriInvalidScheme() {
        URI invalidURI = URI.createURI(SCHEME_INVALID + ":/test");

        Throwable thrown = catchThrowable(() -> createURI(invalidURI));
        Assertions.assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
