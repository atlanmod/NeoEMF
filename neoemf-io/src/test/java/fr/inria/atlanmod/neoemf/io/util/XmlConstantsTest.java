package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.commons.AbstractTest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlConstantsTest extends AbstractTest {

    @Test
    public void formatWithPrefix() {
        assertThat(XmlConstants.format("prefix", "value")).isEqualTo("prefix:value");
    }

    @Test
    public void formatWithoutPrefix() {
        assertThat(XmlConstants.format(null, "value")).isEqualTo("value");
    }
}