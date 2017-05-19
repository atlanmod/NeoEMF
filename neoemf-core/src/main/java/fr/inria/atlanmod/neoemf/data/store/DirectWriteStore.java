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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;

/**
 * A {@link PersistentStore} that translates model-level operations into datastore calls.
 * <p>
 * <b>Future:</b> An abstraction of {@link DirectWriteStore}s will be implemented to define a global behaviour. For now,
 * it requires to be re-implemented for handle a specific implementation of a database, but later, these functions will
 * be handled by {@link PersistenceBackend}.
 */
public interface DirectWriteStore extends PersistentStore {
}
