package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;

import javax.annotation.Nonnull;

/**
 * An object that can be stored in a {@link Store}.
 */
public interface Storable {

    /**
     * Returns the store used to store the model.
     *
     * @return the store
     */
    @Nonnull
    StoreAdapter eStore();
}
