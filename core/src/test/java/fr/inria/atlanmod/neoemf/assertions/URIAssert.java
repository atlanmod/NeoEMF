package fr.inria.atlanmod.neoemf.assertions;

import org.assertj.core.api.AbstractObjectAssert;
import org.eclipse.emf.common.util.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Assertion class for {@link URI}s.
 *
 * @see URI
 */
public class URIAssert extends AbstractObjectAssert<URIAssert, URI> {

    public URIAssert(URI actual) {
        super(actual, URIAssert.class);
    }

    /**
     * Verifies that the actual {@code URI} has the expected scheme.
     *
     * @param expected the expected scheme of the actual {@code URI}.
     *
     * @return {@code this} assertion object.
     *
     * @throws AssertionError if the actual scheme is not equal to the expected scheme.
     */
    public URIAssert hasScheme(String expected) {
        assertThat(actual.scheme()).isEqualTo(expected);
        return this;
    }
}
