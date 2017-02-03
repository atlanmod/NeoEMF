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

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.store.DirectWriteStore;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.Closeable;
import java.util.Collection;
import java.util.Map;

/**
 * An adapter on top of a database that provides specific methods for communicating with the database that it uses.
 * Each {@code PersistenceBackend} manage one single instance of a database.
 * <p>
 * It does not provide model-level translation; these functions are handled by {@link DirectWriteStore}s.
 *
 * @future an abstraction of {@code PersistenceBackend}s will be implemented to define a global behaviour. For now, it
 * provides only basic methods for closing or saving a model, but later, it will provide generic methods to add, delete
 * or get a value.
 * @see DirectWriteStore
 */
public interface PersistenceBackend extends Closeable {

    /**
     * Saves the modifications of the owned {@link EObject}s in the underlying database.
     */
    void save();

    /**
     * {@inheritDoc}
     * <p>
     * In our case, it cleanly stops the underlying database.
     */
    @Override
    void close();

    /**
     * Returns whether the underlying database is closed.
     *
     * @return {@code true} if the database is closed, otherwise {@code false}
     */
    boolean isClosed();

    /**
     * Returns {@code true} if this {@code PersistenceBackend} is distributed.
     *
     * @return {@code true} if the back-end is distributed, {@code false} otherwise.
     */
    boolean isDistributed();

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
     * Retrieves the value of a given {@link FeatureKey}.
     *
     * @param key the {@link FeatureKey} to look for
     *
     * @return an {@link Object} representing the value associated to the given {@code key}, {@code null} if it is not
     * in the database
     */
    Object getValue(FeatureKey key);

    /**
     * Stores the value of a given {@link FeatureKey}.
     *
     * @param key   the {@link FeatureKey} to set the value of
     * @param value an {@link Object} representing the value associated to the given {@code key}
     *
     * @return the old value
     */
    Object setValue(FeatureKey key, Object value);

    /**
     * Removes the value of a given {@link FeatureKey} from the database, and unset it ({@link
     * #hasValue(FeatureKey)}).
     *
     * @param key the {@link FeatureKey} to remove
     *
     * @return an {@link Object} representing the removed value, {@code null} if it hasn't been found
     */
    Object unsetValue(FeatureKey key);

    /**
     * Checks if the given {@link FeatureKey} is set.
     *
     * @param key the {@link FeatureKey} to check
     *
     * @return {@code true} if the feature is set, {@code false} otherwise
     */
    boolean hasValue(FeatureKey key);

    Id getReference(FeatureKey key);

    Id setReference(FeatureKey key, Id id);

    Id unsetReference(FeatureKey key);

    boolean hasReference(FeatureKey key);

    /**
     * Retrieves the value of a given {@link MultivaluedFeatureKey}.
     * <p>
     * This method is similar to {@link #getValue(FeatureKey)} but it uses multi-valued {@link Map} to retrieve the
     * element at the given index directly instead of returning the entire {@link Collection}.
     *
     * @param key the {@link MultivaluedFeatureKey} to get the value from
     *
     * @return an {@link Object} representing the value associated to the given {@code key}
     */
    Object getValueAtIndex(MultivaluedFeatureKey key);

    /**
     * Stores the value of a given {@link MultivaluedFeatureKey}.
     * <p>
     * This method is similar to {@link #setValue(FeatureKey, Object)} but it uses the multi-valued {@link Map} that
     * stores indices explicitly.
     *
     * @param key   the {@link MultivaluedFeatureKey} to set the value of
     * @param value an {@link Object} representing the value associated to the given {@code key}
     *
     * @return the old value
     */
    Object setValueAtIndex(MultivaluedFeatureKey key, Object value);

    /**
     * Removes the value of a given {@link FeatureKey} from the database, and unset it ({@link
     * #hasValue(FeatureKey)}).
     *
     * @param key the {@link FeatureKey} to remove
     *
     * @return an {@link Object} representing the removed value, {@code null} if it hasn't been found
     */
    Object unsetValueAtIndex(FeatureKey key);

    /**
     * Checks if the given {@link FeatureKey} is set.
     *
     * @param key the {@link FeatureKey} to check
     *
     * @return {@code true} if the feature is set, {@code false} otherwise
     */
    boolean hasValueAtIndex(FeatureKey key);

    Id getReferenceAtIndex(MultivaluedFeatureKey key);

    Id setReferenceAtIndex(MultivaluedFeatureKey key, Id id);

    Id unsetReferenceAtIndex(FeatureKey key);

    boolean hasReferenceAtIndex(FeatureKey key);

    int sizeOf(FeatureKey key);

    /**
     * Back-end specific computation of {@link Resource#getAllContents()}.
     *
     * @param eClass the class to compute the instances of
     * @param strict {@code true} if the lookup searches for strict instances
     *
     * @return an {@link Object} containing the back-end specific objects corresponding to the instances of the {@link
     * EClass}
     *
     * @throws UnsupportedOperationException if the back-end does not support all instances lookup
     */
    default Object getAllInstances(EClass eClass, boolean strict) {
        throw new UnsupportedOperationException("This back-end does not support custom all instances computation");
    }
}

