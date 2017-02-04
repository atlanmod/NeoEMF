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
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.compare.match.DefaultComparisonFactory;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.eobject.WeightProvider;
import org.eclipse.emf.compare.match.eobject.WeightProviderDescriptorRegistryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.utils.UseIdentifiers;

/**
 * Overrides {@link DefaultEqualityHelperFactory} methods to create
 * {@link LazyEqualityHelper} instances instead of default
 * {@link EqualityHelper}.
 * 
 * @see LazyEqualityHelper
 */

/**
 * Overrides {@link MatchEngineFactoryImpl} methods to create
 * {@link LazyMatchEngine} instances instead of {@link DefaultMatchEngine} ones.
 * This class uses {@link LazyEqualityHelperFactory} to create the helper used
 * to compare {@link PersistentEObject}.
 * 
 * @see LazyMatchEngine
 * @see LazyEqualityHelperFactory
 */
public class LazyMatchEngineFactory extends MatchEngineFactoryImpl {

    /**
     * Constructs a default {@link LazyMatchEngine}. This match engine will use
     * the standalone weight provider registry
     * {@link WeightProviderDescriptorRegistryImpl#createStandaloneInstance()}.
     */
    public LazyMatchEngineFactory() {
        this(UseIdentifiers.WHEN_AVAILABLE, WeightProviderDescriptorRegistryImpl
                .createStandaloneInstance());
    }

    /**
     * Constructor that instantiate a {@link LazyMatchEngineFactory} that will
     * use identifiers as specified by the given {@code useIDs} enumeration.
     * This match engine will use a the standalone weight provider registry
     * {@link WeightProviderDescriptorRegistryImpl#createStandaloneInstance()}.
     * 
     * @param useIDs
     *            the kinds of matcher to use.
     */
    public LazyMatchEngineFactory(UseIdentifiers useIDs) {
        this(useIDs, WeightProviderDescriptorRegistryImpl.createStandaloneInstance());
    }

    /**
     * Constructor that instantiate a {@link LazyMatchEngine} that will use
     * identifiers as specified by the given {@code useIDs} enumeration.
     * 
     * @param useIDs
     *            the kinds of matcher to use.
     * @param registry
     *            A match engine needs a WeightProvider in case of this match
     *            engine do not use identifiers.
     */
    public LazyMatchEngineFactory(UseIdentifiers useIDs, WeightProvider.Descriptor.Registry registry) {
        final IComparisonFactory comparisonFactory = new DefaultComparisonFactory(
                new LazyEqualityHelperFactory());
        final IEObjectMatcher matcher = DefaultMatchEngine.createDefaultEObjectMatcher(useIDs,
                registry);
        NeoLogger.info("LazyMatchEngine with LazyEqualityHelper created");
        matchEngine = new LazyMatchEngine(matcher, comparisonFactory);
    }

    /**
     * Constructor that instantiate a {@link LazyMatchEngine} with the given
     * parameters.
     * 
     * @param matcher
     *            The matcher that will be in charge of pairing EObjects
     *            together for this comparison process.
     * @param comparisonFactory
     *            factory that will be use to instantiate Comparison as return
     *            by match() methods.
     */
    public LazyMatchEngineFactory(IEObjectMatcher matcher, IComparisonFactory comparisonFactory) {
        NeoLogger.info("LazyMatchEngine with LazyEqualityHelper created");
        matchEngine = new LazyMatchEngine(matcher, comparisonFactory);
    }

}
