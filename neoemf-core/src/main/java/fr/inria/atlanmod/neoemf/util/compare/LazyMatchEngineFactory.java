/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util.compare;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.compare.match.DefaultComparisonFactory;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.eobject.WeightProvider;
import org.eclipse.emf.compare.match.eobject.WeightProviderDescriptorRegistryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.utils.UseIdentifiers;

/**
 * A {@link org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl} that creates instances of {@link
 * org.eclipse.emf.compare.match.IComparisonFactory} for comparing {@link PersistentEObject}s.
 *
 * @see LazyEqualityHelperFactory
 */
public class LazyMatchEngineFactory extends MatchEngineFactoryImpl {

    /**
     * Constructs a new {@code LazyMatchEngineFactory}.
     * <p>
     * This match engine will use the standalone weight provider registry {@link org.eclipse.emf.compare.match.eobject.WeightProviderDescriptorRegistryImpl#createStandaloneInstance()}.
     */
    public LazyMatchEngineFactory() {
        this(UseIdentifiers.WHEN_AVAILABLE);
    }

    /**
     * Constructs a new {@code LazyMatchEngineFactory} that will use identifiers as specified by the given {@code
     * useIDs} enumeration.
     * <p>
     * This match engine will use a the standalone weight provider registry {@link org.eclipse.emf.compare.match.eobject.WeightProviderDescriptorRegistryImpl#createStandaloneInstance()}.
     *
     * @param useIDs the kinds of matcher to use.
     */
    public LazyMatchEngineFactory(UseIdentifiers useIDs) {
        this(useIDs, WeightProviderDescriptorRegistryImpl.createStandaloneInstance());
    }

    /**
     * Constructs a new {@code LazyMatchEngineFactory} that will use identifiers as specified by the given {@code
     * useIDs} enumeration.
     *
     * @param useIDs   the kinds of matcher to use.
     * @param registry a match engine needs a WeightProvider in case of this match engine do not use identifiers.
     */
    public LazyMatchEngineFactory(UseIdentifiers useIDs, WeightProvider.Descriptor.Registry registry) {
        this(DefaultMatchEngine.createDefaultEObjectMatcher(useIDs, registry), new DefaultComparisonFactory(new LazyEqualityHelperFactory()));
    }

    /**
     * Constructs a new {@code LazyMatchEngineFactory} with the given parameters.
     *
     * @param matcher           the matcher that will be in charge of pairing {@link org.eclipse.emf.ecore.EObject}s
     *                          together for this comparison process.
     * @param comparisonFactory factory that will be use to instantiate Comparison as return by match() methods.
     */
    public LazyMatchEngineFactory(IEObjectMatcher matcher, IComparisonFactory comparisonFactory) {
        super(matcher, comparisonFactory);
    }
}
