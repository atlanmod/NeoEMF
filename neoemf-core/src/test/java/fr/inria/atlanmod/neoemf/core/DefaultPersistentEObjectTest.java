package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.neoemf.data.store.adapter.TransientStoreAdapter;

import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link DefaultPersistentEObject}.
 */
@ParametersAreNonnullByDefault
public class DefaultPersistentEObjectTest {

    @Test
    public void testIdGeneration() {
        // Created without an undefined Id
        PersistentEObject object = new DefaultPersistentEObject();

        // Id generated on first call
        assertThat(object.id()).isNotSameAs(Id.UNDEFINED);
    }

    @Test
    public void testStoreGeneration() {
        // Created without any store
        PersistentEObject object = new DefaultPersistentEObject();

        // Store created on first call
        assertThat(object.eStore()).isNotNull().isInstanceOf(TransientStoreAdapter.class);
        object.eStore().close();
    }
}
