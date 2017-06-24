package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.InvalidStoreException;
import fr.inria.atlanmod.neoemf.data.mapper.AbstractMapperFactory;
import fr.inria.atlanmod.neoemf.option.PersistentResourceOptions;
import fr.inria.atlanmod.neoemf.option.PersistentStoreOptions;

import org.eclipse.emf.ecore.resource.Resource;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * A factory that creates instances of {@link Store}.
 */
@ParametersAreNonnullByDefault
public final class StoreFactory extends AbstractMapperFactory {

    /**
     * Constructs a new {@code StoreFactory}.
     */
    private StoreFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    public static StoreFactory getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Retrieves the stores to use from the {@code options}.
     *
     * @param options the options containing the stores declaration
     *
     * @return a collection of stores declaration
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    private static Collection<PersistentStoreOptions> getStores(Map<String, Object> options) {
        try {
            if (options.containsKey(PersistentResourceOptions.STORES)) {
                return Collection.class.cast(options.get(PersistentResourceOptions.STORES));
            }
        }
        catch (ClassCastException ignored) {
        }

        return Collections.emptySet();
    }

    /**
     * Checks that the {@code storeOption} is defined in the {@code options}.
     *
     * @param options     the options where to look for
     * @param storeOption the option to look for
     *
     * @return {@code true} if the {@code storeOption} is defined
     */
    public static boolean isDefined(Map<String, Object> options, PersistentStoreOptions storeOption) {
        return getStores(options).contains(storeOption);
    }

    /**
     * Creates a new {@link Store} on top of the {@code backend} according to the specified {@code options}. The
     * newly created store will be detached from any resource.
     * <p>
     * The returned {@link Store} may be a succession of several {@link Store}.
     *
     * @param backend  the back-end where to store data
     * @param options  the options that defines the behaviour of the store
     *
     * @return a new store
     *
     * @throws NullPointerException  if the {@code store} or the {@code options} are {@code null}
     * @throws InvalidStoreException if an error occurs during the creation of the store
     *
     * @see #createStore(Backend, Resource.Internal, Map)
     */
    @Nonnull
    public Store createStore(Backend backend, Map<String, Object> options) {
        return createStore(backend, null, options);
    }

    /**
     * Creates a new {@link Store} between the {@code resource} and the {@code backend} according to the specified
     * {@code options}. If the {@code resource} is {@code null}, then the newly created store will be detached from
     * any resource.
     * <p>
     * The returned {@link Store} may be a succession of several {@link Store}.
     *
     * @param backend  the back-end where to store data
     * @param resource the resource to store and access
     * @param options  the options that defines the behaviour of the store
     *
     * @return a new store
     *
     * @throws NullPointerException  if the {@code store} or the {@code options} are {@code null}
     * @throws InvalidStoreException if an error occurs during the creation of the store
     */
    @Nonnull
    public Store createStore(Backend backend, @Nullable Resource.Internal resource, Map<String, Object> options) {
        checkNotNull(backend);
        checkNotNull(options);

        // The tail of the store chain
        Store store = new DirectWriteStore(backend, resource);

        try {
            for (PersistentStoreOptions opt : getStores(options).stream().sorted().collect(Collectors.toList())) {
                List<ConstructorParameter> parameters = opt.parameters().stream()
                        .filter(options::containsKey)
                        .map(options::get)
                        .map(ConstructorParameter::new)
                        .collect(Collectors.toList());

                // Each store has a store as first argument
                parameters.add(0, new ConstructorParameter(store, Store.class));

                ConstructorParameter[] parametersArray = parameters.toArray(new ConstructorParameter[parameters.size()]);
                store = newInstanceOf(opt.className(), parametersArray);
            }
        }
        catch (Exception e) {
            throw new InvalidStoreException(e);
        }

        return store;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final StoreFactory INSTANCE = new StoreFactory();
    }
}
