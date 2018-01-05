/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * Statistics about the usage of a {@link Store} chain.
 */
@Immutable
@ParametersAreNonnullByDefault
public final class StoreStats {

    /**
     * A map of the method calls made in a {@link Store} chain with their number of invocation.
     */
    @Nonnull
    private final Map<String, Long> methodCalls;

    /**
     * Constructs a new {@code StoreStats} with the given arguments.
     *
     * @param methodCalls the map of method calls with their number of invocation; the values ​​will remain unchanged
     */
    public StoreStats(Map<String, AtomicLong> methodCalls) {
        checkNotNull(methodCalls, "methodCalls");

        this.methodCalls = Collections.unmodifiableMap(sortMethodCalls(methodCalls));
    }

    /**
     * Sort the {@code methodCalls} in descending order of values.
     *
     * @param methodCalls the map to sort
     *
     * @return a new sorted map
     *
     * @implNote The returned map is an instance of {@link LinkedHashMap} to keep the order, as described in the
     * official Javadoc: "This linked list defines the iteration ordering, which is normally the order in which keys
     * were inserted into the map (insertion-order)."
     */
    @Nonnull
    private static Map<String, Long> sortMethodCalls(Map<String, AtomicLong> methodCalls) {
        return methodCalls.entrySet()
                .stream()
                .map(e -> new AbstractMap.SimpleImmutableEntry<>(e.getKey(), e.getValue().get()))
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> { throw new IllegalStateException(String.format("Duplicate key %s", e1)); },
                        LinkedHashMap::new)); // Use LinkedHashMap to keep the order
    }

    /**
     * Returns a map of the different calls made in a {@link Store} chain with their number of calls.
     *
     * @return an immutable map
     */
    @Nonnull
    public Map<String, Long> methodCalls() {
        return methodCalls;
    }

    @Override
    public String toString() {
        return methodCalls().entrySet().stream()
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining("\n"));
    }
}
