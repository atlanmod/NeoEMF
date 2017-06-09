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

import org.eclipse.emf.compare.match.eobject.WeightProvider;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.utils.UseIdentifiers;

/**
 * @deprecated This class is no longer needed since the removing of Guava dependencies.
 * Use the standard {@link MatchEngineFactoryImpl} instead.
 */
@Deprecated
public class LazyMatchEngineFactory extends MatchEngineFactoryImpl {

    @SuppressWarnings("JavaDoc")
    public LazyMatchEngineFactory() {
        super();
    }

    @SuppressWarnings("JavaDoc")
    public LazyMatchEngineFactory(UseIdentifiers useIDs) {
        super(useIDs);
    }

    @SuppressWarnings("JavaDoc")
    public LazyMatchEngineFactory(UseIdentifiers useIDs, WeightProvider.Descriptor.Registry registry) {
        super(useIDs, registry);
    }
}
