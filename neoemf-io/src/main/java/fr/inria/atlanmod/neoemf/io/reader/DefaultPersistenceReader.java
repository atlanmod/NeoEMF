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

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.util.PersistenceConstants;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
public class DefaultPersistenceReader extends AbstractReader<PersistenceBackend> implements PersistenceReader {

    /**
     * Constructs a new {@code DefaultPersistenceReader} with the given {@code handlers}.
     *
     * @param handlers the handlers to notify
     */
    public DefaultPersistenceReader(Handler... handlers) {
        super(handlers);
    }

    @Override
    public void read(PersistenceBackend source) {
        FeatureKey key = FeatureKey.of(PersistenceConstants.ROOT_ID, PersistenceConstants.ROOT_FEATURE_NAME);

        NeoLogger.info("References...");
        source.allReferencesOf(key).forEach(o -> NeoLogger.info("[R] {0}", o));
    }
}
