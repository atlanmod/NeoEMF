/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store.listener;

import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.data.store.Store;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Statistics about the usage of a {@link Store} chain.
 */
@ParametersAreNonnullByDefault
public final class StoreStats {

    /**
     * A map of the method calls made in a {@link Store} chain with their number of invocation.
     */
    @Nonnull
    private final Map<String, AtomicLong> methodInvocations = new HashMap<>();

    /**
     * TODO
     *
     * @param map
     *
     * @return
     */
    @Nonnull
    private static Map<String, Long> atomicToPrimitive(Map<String, AtomicLong> map) {
        return map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().get()));
    }

    /**
     * Sort the {@code map} in descending order of values.
     *
     * @param map the map to sort
     *
     * @return a new sorted map
     *
     * @implNote The returned map is an instance of {@link LinkedHashMap} to keep the order, as described in the
     * official Javadoc: "This linked list defines the iteration ordering, which is normally the order in which keys
     * were inserted into the map (insertion-order)."
     */
    @Nonnull
    private static Map<String, Long> sort(Map<String, Long> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> { throw new IllegalStateException(String.format("Duplicate key %s", e1)); },
                        LinkedHashMap::new)); // Use LinkedHashMap to keep the order
    }

    /**
     * Increments the invocation number for the specified {@code method}.
     *
     * @param method the invoked method
     */
    void hasBeenInvoked(String method) {
        methodInvocations.computeIfAbsent(method, s -> new AtomicLong()).incrementAndGet();
    }

    /**
     * Returns a map of the different calls made in a {@link Store} chain with their number of invocations.
     *
     * @return an immutable map view
     */
    @Nonnull
    public Map<String, Long> methodInvocations() {
        return Collections.unmodifiableMap(atomicToPrimitive(methodInvocations));
    }

    @Override
    public String toString() {
        return sort(methodInvocations()).entrySet().stream()
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(Strings.LR));
    }
}
