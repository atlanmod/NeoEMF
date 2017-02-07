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
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * An object capable of mapping features represented as a set of key/value pair.
 */
public interface PersistenceMapper {

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
