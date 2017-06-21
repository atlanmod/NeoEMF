package fr.inria.atlanmod.neoemf.core;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StringIdTest {

    @Test
    public void testFrom() throws Exception {
        String value = "thisIsAnId";

        Object o = mock(Object.class);
        when(o.toString()).thenReturn(value);

        Id id = StringId.from(o);

        assertThat(id.toString()).isEqualTo(value);
    }

    @Test
    public void testOf() throws Exception {
        String value = "thisIsAnId";

        Id id = StringId.of(value);

        assertThat(id.toString()).isEqualTo(value);
    }

    @Test
    public void testGenerate() throws Exception {
        Id id0 = StringId.generate();
        Id id1 = StringId.generate();

        assertThat(id0.toString().trim()).isNotEmpty();
        assertThat(id1.toString().trim()).isNotEmpty();

        assertThat(id0).isNotEqualTo(id1);
    }

    @Test
    public void testCompareTo() throws Exception {
        Id id0 = StringId.of("aaa");
        Id id0Bis = StringId.of("aaa");
        Id id1 = StringId.of("ZZZ");

        assertThat(id0).isEqualByComparingTo(id0Bis);
        assertThat(id0).isLessThan(id1);
        assertThat(id1).isGreaterThan(id0Bis);
    }

    @Test
    public void testToLong() throws Exception {
        assertThat(catchThrowable(() ->
            StringId.generate().toLong()
        )).isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testHashCode() throws Exception {
        Id id0 = StringId.of("aaa");
        Id id0Bis = StringId.of("aaa");
        Id id1 = StringId.of("ZZZ");

        assertThat(id0.hashCode()).isEqualTo(id0Bis.hashCode());
        assertThat(id1.hashCode()).isNotEqualTo(id0Bis.hashCode());
        assertThat(id0.hashCode()).isNotEqualTo(id1.hashCode());
    }

    @Test
    public void testEquals() throws Exception {
        Id id0 = StringId.of("aaa");
        Id id0Bis = StringId.of("aaa");
        Id id1 = StringId.of("ZZZ");

        assertThat(id0).isEqualTo(id0Bis);
        assertThat(id1).isNotEqualTo(id0Bis);
        assertThat(id0).isNotEqualTo(id1);
    }

    @Test
    public void testToString() throws Exception {
        String value = "thisIsAnId";

        Id id = StringId.of(value);

        assertThat(id).hasToString(value);
    }
}