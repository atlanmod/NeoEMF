/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.util.compare.LazyMatchEngineFactory;

import org.atlanmod.commons.Throwables;
import org.atlanmod.commons.annotation.Static;
import org.atlanmod.commons.log.Log;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.EObject;

import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A static utility class for model comparison.
 */
@Static
@ParametersAreNonnullByDefault
public final class ModelComparisonUtils {

    private ModelComparisonUtils() {
        throw Throwables.notInstantiableClass(getClass());
    }

    /**
     * Checks that the {@code actual} object is the same as the {@code expected}.
     *
     * @param actual   the object to check
     * @param expected the expected object
     */
    public static void assertEObjectAreEqual(EObject actual, EObject expected) {
        Log.info("Comparing models...");

        IMatchEngine.Factory factory = new LazyMatchEngineFactory(UseIdentifiers.NEVER);

        IMatchEngine.Factory.Registry registry = new MatchEngineFactoryRegistryImpl();
        registry.add(factory);

        IComparisonScope scope = new DefaultComparisonScope(expected, actual, null);

        Comparison comparison = EMFCompare.builder()
                .setMatchEngineFactoryRegistry(registry)
                .build()
                .compare(scope);

        final List<Diff> differences = comparison.getDifferences();

        // Don't display all differences
        assertThat(differences.size()).isEqualTo(0).withFailMessage("Models have %d differences", differences.size());
    }
}
