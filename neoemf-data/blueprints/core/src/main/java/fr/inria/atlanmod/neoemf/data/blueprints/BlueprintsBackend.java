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

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.data.LocalPersistentBackend;
import fr.inria.atlanmod.neoemf.data.PersistentBackend;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistentBackend} that is responsible of low-level access to a Blueprints database.
 * <p>
 * It wraps an existing Blueprints database and provides facilities to create and retrieve elements, map {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}s to {@code Vertex} elements in order to speed up attribute access,
 * and manage a set of lightweight caches to improve access time of {@code Vertex} from their corresponding {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}.
 */
@ParametersAreNonnullByDefault
public interface BlueprintsBackend extends LocalPersistentBackend {
}
