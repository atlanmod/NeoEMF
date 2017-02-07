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
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.eclipse.emf.ecore.EClass;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * An object capable of mapping features represented as a set of key/value pair.
 */
public interface PersistenceMapper {

    /**
     * Retrieves container for a given object {@link Id}.
     *
     * @param id the {@link Id} of the contained object
     *
     * @return a {@link ContainerValue} descriptor that contains element's container information
     */
    Optional<ContainerValue> containerOf(Id id);

    /**
     * Stores container information for a given id in the Container Map.
     *
     * @param id        the {@link Id} of the contained element
     * @param container the {@link ContainerValue} descriptor containing element's container information to store
     */
    void containerFor(Id id, ContainerValue container);

    /**
     * Retrieves the metaclass ({@link EClass}) of the element with the given {@link Id}.
     *
     * @param id the {@link Id} of the element
     *
     * @return a {@link MetaclassValue} descriptor containing element's metaclass information ({@link EClass},
     * meta-model name, and {@code nsURI})
     */
    Optional<MetaclassValue> metaclassOf(Id id);

    /**
     * Stores metaclass ({@link EClass}) information for the element with the given {@link Id}.
     *
     * @param id        the {@link Id} of the element
     * @param metaclass the {@link MetaclassValue} descriptor containing element's metaclass information ({@link
     *                  EClass}, meta-model name, and {@code nsURI})
     */
    void metaclassFor(Id id, MetaclassValue metaclass);

    <V> Optional<V> valueOf(FeatureKey key);

    Optional<Id> referenceOf(FeatureKey key);

    <V> Optional<V> valueOf(MultivaluedFeatureKey key);

    Optional<Id> referenceOf(MultivaluedFeatureKey key);

    <V> Optional<V> valueFor(FeatureKey key, V value);

    Optional<Id> referenceFor(FeatureKey key, Id id);

    <V> Optional<V> valueFor(MultivaluedFeatureKey key, V value);

    Optional<Id> referenceFor(MultivaluedFeatureKey key, Id id);

    <V> void unsetValue(FeatureKey key);

    void unsetReference(FeatureKey key);

    <V> void unsetAllValues(FeatureKey key);

    void unsetAllReferences(FeatureKey key);

    <V> boolean hasValue(FeatureKey key);

    boolean hasReference(FeatureKey key);

    <V> boolean hasAnyValue(FeatureKey key);

    boolean hasAnyReference(FeatureKey key);

    <V> void addValue(MultivaluedFeatureKey key, V value);

    void addReference(MultivaluedFeatureKey key, Id id);

    <V> Optional<V> removeValue(MultivaluedFeatureKey key);

    Optional<Id> removeReference(MultivaluedFeatureKey key);

    <V> void cleanValues(FeatureKey key);

    void cleanReferences(FeatureKey key);

    <V> boolean containsValue(FeatureKey key, V value);

    boolean containsReference(FeatureKey key, Id id);

    <V> OptionalInt indexOfValue(FeatureKey key, V value);

    OptionalInt indexOfReference(FeatureKey key, Id id);

    <V> OptionalInt lastIndexOfValue(FeatureKey key, V value);

    OptionalInt lastIndexOfReference(FeatureKey key, Id id);

    <V> Iterable<V> valuesAsList(FeatureKey key);

    Iterable<Id> referencesAsList(FeatureKey key);

    <V> OptionalInt sizeOf(FeatureKey key);
}
