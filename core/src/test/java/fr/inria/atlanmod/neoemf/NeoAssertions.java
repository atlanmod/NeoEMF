package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.assertions.URIAssert;

import org.assertj.core.api.Assertions;
import org.eclipse.emf.common.util.URI;

public class NeoAssertions extends Assertions {

    public static URIAssert assertThat(URI uri) {
        return new URIAssert(uri);
    }

    // TODO Fill with our custom assertion builders
}
