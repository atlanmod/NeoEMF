/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.map.core;

import fr.inria.atlanmod.neoemf.annotations.VisibleForTesting;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;
import fr.inria.atlanmod.neoemf.data.map.core.store.MapStore;
import org.eclipse.emf.ecore.EClass;

import java.util.Collection;
import java.util.Map;

/**
 * An adapter on top of a map-based database that provides specific methods for communicating with the database that it uses.
 * Each {@code MapBackend} manage one single instance of a database.
 * <p>
 * It does not provide model-level translation; these functions are handled by {@link MapStore}s.
 *
 * @see MapStore
 */
public interface MapBackend extends PersistenceBackend {
    
    /**
     * Returns all the {@link Collection}s contained in the database.
     *
     * @return a {@link Map} containing all the {@link Collection}s contained in the database and their associated names
     */
    @VisibleForTesting
    Map<String, Object> getAll();

    /**
     * ???
     *
     * @param name ???
     * @param <E>  ???
     *
     * @return ???
     */
    @VisibleForTesting
    <E> E get(String name);

    /**
     * Retrieves container for a given object {@link Id}.
     *
     * @param id the {@link Id} of the contained object
     *
     * @return a {@link ContainerInfo} descriptor that contains element's container information
     */
    ContainerInfo containerFor(Id id);

    /**
     * Stores container information for a given id in the Container Map.
     *
     * @param id        the {@link Id} of the contained element
     * @param container the {@link ContainerInfo} descriptor containing element's container information to store
     */
    void storeContainer(Id id, ContainerInfo container);

    /**
     * Retrieves the metaclass ({@link EClass}) of the element with the given {@link Id}.
     *
     * @param id the {@link Id} of the element
     *
     * @return a {@link ClassInfo} descriptor containing element's metaclass information ({@link EClass}, meta-model
     * name, and {@code nsURI})
     */
    ClassInfo metaclassFor(Id id);

    /**
     * Stores metaclass ({@link EClass}) information for the element with the given {@link Id}.
     *
     * @param id        the {@link Id} of the element
     * @param metaclass the {@link ClassInfo} descriptor containing element's metaclass information ({@link EClass},
     *                  meta-model name, and {@code nsURI})
     */
    void storeMetaclass(Id id, ClassInfo metaclass);

    /**
     * Stores the value of a given {@link FeatureKey}.
     *
     * @param key   the {@link FeatureKey} to set the value of
     * @param value an {@link Object} representing the value associated to the given {@code key}
     *
     * @return ???
     */
    Object storeValue(FeatureKey key, Object value);

    /**
     * Retrieves the value of a given {@link FeatureKey}.
     *
     * @param key the {@link FeatureKey} to look for
     *
     * @return an {@link Object} representing the value associated to the given {@code key}, {@code null} if it is not
     * in the database
     */
    Object valueOf(FeatureKey key);

    /**
     * Removes the value of a given {@link FeatureKey} from the database, and unset it ({@link
     * #isFeatureSet(FeatureKey)}).
     *
     * @param key the {@link FeatureKey} to remove
     *
     * @return an {@link Object} representing the removed value, {@code null} if it hasn't been found
     */
    Object removeFeature(FeatureKey key);

    /**
     * Checks if the given {@link FeatureKey} is set.
     *
     * @param key the {@link FeatureKey} to check
     *
     * @return {@code true} if the feature is set, {@code false} otherwise
     */
    boolean isFeatureSet(FeatureKey key);

    /**
     * Stores the value of a given {@link MultivaluedFeatureKey}.
     * <p>
     * This method is similar to {@link #storeValue(FeatureKey, Object)} but it uses the multi-valued {@link Map} that
     * stores indices explicitly.
     *
     * @param key   the {@link MultivaluedFeatureKey} to set the value of
     * @param value an {@link Object} representing the value associated to the given {@code key}
     *
     * @return ???
     *
     */
    Object storeValueAtIndex(MultivaluedFeatureKey key, Object value);

    /**
     * Retrieves the value of a given {@link MultivaluedFeatureKey}.
     * <p>
     * This method is similar to {@link #valueOf(FeatureKey)} but it uses multi-valued {@link Map} to retrieve the
     * element at the given index directly instead of returning the entire {@link Collection}.
     *
     * @param key the {@link MultivaluedFeatureKey} to get the value from
     *
     * @return an {@link Object} representing the value associated to the given {@code key}
     *
     */
    Object valueAtIndex(MultivaluedFeatureKey key);

    /**
     * Copies all the contents of this this back-end to the target one.
     *
     * @param target the {@code MapDbPersistenceBackend} to copy the database contents to
     *
     * @throws UnsupportedOperationException if the current {@link DB} contains {@link Collection}s which are not {@link
     *                                       Map}s
     */
    //@SuppressWarnings({"unchecked", "rawtypes"}) // Unchecked cast: 'Map' to 'Map<...>'
    //void copyTo(MapBackend target);
}
