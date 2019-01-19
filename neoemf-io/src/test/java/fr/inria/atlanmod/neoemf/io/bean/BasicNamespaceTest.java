/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.bean;

import org.atlanmod.commons.AbstractTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BasicNamespace}.
 */
@ParametersAreNonnullByDefault
class BasicNamespaceTest extends AbstractTest {

    @BeforeEach
    void setUp() {
        BasicNamespace.Registry.getInstance().clean();
    }

    @Test
    void testGetDefault() {
        BasicNamespace ns0 = BasicNamespace.DEFAULT;
        assertThat(ns0).isNotNull();
        assertThat(ns0.getPrefix()).isEqualTo("ecore");
    }

    @Test
    void testPrefixAndUri() {
        String prefix0 = "prefix0";
        String prefix1 = "prefix1";

        String uri0 = "uri0";
        String uri1 = "uri1";

        BasicNamespace ns0 = BasicNamespace.Registry.getInstance().register(prefix0, uri0);
        assertThat(ns0.getPrefix()).isEqualTo(prefix0);
        assertThat(ns0.getUri()).isEqualTo(uri0);

        BasicNamespace ns1 = BasicNamespace.Registry.getInstance().register(prefix1, uri1);
        assertThat(ns1.getPrefix()).isNotEqualTo(prefix0).isEqualTo(prefix1);
        assertThat(ns1.getUri()).isNotEqualTo(uri0).isEqualTo(uri1);
    }

    @Test
    void testHashCode() {
        String prefix0 = "prefix0";
        String prefix1 = "prefix1";

        String uri0 = "uri0";
        String uri1 = "uri1";

        BasicNamespace ns0 = BasicNamespace.Registry.getInstance().register(prefix0, uri0);
        BasicNamespace ns0Bis = BasicNamespace.Registry.getInstance().register(prefix0, uri0);
        BasicNamespace ns1 = BasicNamespace.Registry.getInstance().register(prefix1, uri1);

        assertThat(ns0.hashCode()).isEqualTo(ns0Bis.hashCode());
        assertThat(ns0.hashCode()).isNotEqualTo(ns1.hashCode());
        assertThat(ns1.hashCode()).isNotEqualTo(ns0Bis.hashCode());
    }

    @Test
    void testEquals() {
        String prefix0 = "prefix0";
        String prefix1 = "prefix1";

        String uri0 = "uri0";
        String uri1 = "uri1";

        BasicNamespace ns0 = BasicNamespace.Registry.getInstance().register(prefix0, uri0);
        BasicNamespace ns0Bis = BasicNamespace.Registry.getInstance().register(prefix0, uri0);
        BasicNamespace ns1 = BasicNamespace.Registry.getInstance().register(prefix1, uri1);

        assertThat(ns0).isSameAs(ns0Bis);
        assertThat(ns0).isNotSameAs(ns1).isNotEqualTo(ns1);
        assertThat(ns1).isNotSameAs(ns0Bis).isNotEqualTo(ns0Bis);

        assertThat(ns0).isEqualTo(ns0);
        assertThat(ns0).isNotEqualTo(null);
        assertThat(ns0).isNotEqualTo(0);
    }

    @Test
    void testToString() {
        String prefix0 = "prefix0";
        String uri0 = "uri0";

        BasicNamespace ns0 = BasicNamespace.Registry.getInstance().register(prefix0, uri0);
        assertThat(ns0).hasToString("prefix0@uri0");
    }

    @Test
    void testRegistry() {
        BasicNamespace.Registry registry = BasicNamespace.Registry.getInstance();

        String prefix0 = "prefix0";
        String uri0 = "uri0";

        String prefix1 = "prefix1";
        String uri1 = "uri1";

        BasicNamespace ns0 = registry.register(prefix0, uri0);
        BasicNamespace ns1 = registry.register(prefix1, uri1);

        BasicNamespace ns0Bis = registry.getByPrefix(prefix0);
        assertThat(ns0Bis).isEqualTo(ns0);

        BasicNamespace ns1Bis = registry.getByUri(uri1);
        assertThat(ns1Bis).isEqualTo(ns1);

        Iterable<String> prefixes = registry.getPrefixes();
        assertThat(prefixes).containsExactlyInAnyOrder(prefix0, prefix1);

        registry.clean();
        assertThat(prefixes).isEmpty();

        assertThat(registry.getByPrefix(prefix0)).isNull();
        assertThat(registry.getByUri(uri1)).isNull();
    }
}