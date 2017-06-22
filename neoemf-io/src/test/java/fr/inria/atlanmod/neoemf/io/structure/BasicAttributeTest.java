package fr.inria.atlanmod.neoemf.io.structure;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of {@link BasicAttribute}.
 */
public class BasicAttributeTest {

    @Test
    public void testName() {
        String name0 = "attribute0";
        String name1 = "attribute1";

        BasicAttribute attr0 = new BasicAttribute(name0);
        assertThat(attr0.name()).isEqualTo(name0);

        BasicAttribute attr1 = new BasicAttribute(name1);
        assertThat(attr1.name()).isEqualTo(name1);

        assertThat(attr0.name()).isNotEqualTo(attr1.name());
    }

    @Test
    public void testId() {
        BasicAttribute attr0 = new BasicAttribute("attribute0");
        assertThat(attr0.id()).isNull();

        BasicId id0 = BasicId.original("id0");
        BasicId id1 = BasicId.generated("id1");

        attr0.id(id0);
        assertThat(attr0.id()).isEqualTo(id0);

        attr0.id(id1);
        assertThat(attr0.id()).isNotEqualTo(id0).isEqualTo(id1);
    }

    @Test
    public void testIndex() {
        BasicAttribute attr0 = new BasicAttribute("attribute0");
        assertThat(attr0.index()).isEqualTo(-1);

        int index0 = 42;
        int index1 = 17;

        attr0.index(index0);
        assertThat(attr0.index()).isEqualTo(index0);

        attr0.index(index1);
        assertThat(attr0.index()).isNotEqualTo(index0).isEqualTo(index1);
    }

    @Test
    public void testMany() {
        BasicAttribute attr0 = new BasicAttribute("attribute0");
        assertThat(attr0.isMany()).isFalse();

        attr0.isMany(true);
        assertThat(attr0.isMany()).isTrue();

        attr0.isMany(false);
        assertThat(attr0.isMany()).isFalse();
    }

    @Test
    public void testValue() throws Exception {
        BasicAttribute attr0 = new BasicAttribute("attribute0");
        assertThat(attr0.value()).isNull();

        String value0 = "value0";
        String value1 = "value1";

        attr0.value(value0);
        assertThat(attr0.value()).isEqualTo(value0);

        attr0.value(value1);
        assertThat(attr0.value()).isNotEqualTo(value0).isEqualTo(value1);
    }

    @Test
    public void testIsReference() {
        BasicAttribute attr0 = new BasicAttribute("attribute0");

        assertThat(attr0.isReference()).isFalse();
    }

    @Test
    public void testIsAttribute() {
        BasicAttribute attr0 = new BasicAttribute("attribute0");

        assertThat(attr0.isAttribute()).isTrue();
    }

    @Test
    public void testHashCode() {
        BasicAttribute attr0 = new BasicAttribute("attribute0");
        BasicAttribute attr0Bis = new BasicAttribute("attribute0");
        BasicAttribute attr1 = new BasicAttribute("attribute1");

        assertThat(attr0.hashCode()).isEqualTo(attr0Bis.hashCode());
        assertThat(attr0.hashCode()).isNotEqualTo(attr1.hashCode());
        assertThat(attr1.hashCode()).isNotEqualTo(attr0Bis.hashCode());
    }

    @Test
    public void testEquals() {
        BasicAttribute attr0 = new BasicAttribute("attribute0");
        BasicAttribute attr0Bis = new BasicAttribute("attribute0");
        BasicAttribute attr1 = new BasicAttribute("attribute1");

        assertThat(attr0).isEqualTo(attr0Bis);
        assertThat(attr0).isNotEqualTo(attr1);
        assertThat(attr1).isNotEqualTo(attr0Bis);
    }

    @Test
    public void testFrom() {
        BasicId id0 = BasicId.original("id0");
        int index0 = 42;
        String value0 = "value0";

        BasicReference ref0 = new BasicReference("feature0");
        ref0.id(id0);
        ref0.index(index0);
        ref0.idReference(BasicId.original(value0));

        BasicAttribute attr0 = BasicAttribute.from(ref0);
        assertThat(attr0.id()).isEqualTo(id0);
        assertThat(attr0.index()).isEqualTo(index0);
        assertThat(attr0.value()).isEqualTo(value0);
    }
}