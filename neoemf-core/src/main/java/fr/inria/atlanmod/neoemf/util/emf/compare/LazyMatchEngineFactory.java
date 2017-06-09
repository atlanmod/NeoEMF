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

package fr.inria.atlanmod.neoemf.util.emf.compare;

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
 * A {@link MatchEngineFactoryImpl} that creates instances of {@link IComparisonFactory} for comparing
 * {@link PersistentEObject}s.
 *
 * @see LazyEqualityHelperFactory
 */
public class LazyMatchEngineFactory extends MatchEngineFactoryImpl {

    /**
     * Constructs a {@link LazyMatchEngineFactory}.
     * <p>
     * This match engine will use the standalone weight provider registry {@link WeightProviderDescriptorRegistryImpl#createStandaloneInstance()}.
     */
    public LazyMatchEngineFactory() {
        this(UseIdentifiers.WHEN_AVAILABLE);
    }

    /**
     * Constructs a new {@code LazyMatchEngineFactory} that will use identifiers as specified by the given {@code
     * useIDs} enumeration.
     * <p>
     * This match engine will use a the standalone weight provider registry {@link WeightProviderDescriptorRegistryImpl#createStandaloneInstance()}.
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
     * @param matcher           the matcher that will be in charge of pairing EObjects together for this comparison
     *                          process.
     * @param comparisonFactory factory that will be use to instantiate Comparison as return by match() methods.
     */
    public LazyMatchEngineFactory(IEObjectMatcher matcher, IComparisonFactory comparisonFactory) {
        super(matcher, comparisonFactory);
    }
}
