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

/**
 * Provides Blueprints' specific readers and writers used in the {@code io} module to save and persist models from
 * external sources.
 * <p>
 * Providing efficient model import and export is tightly coupled to the database and the data representation. In order
 * to provide this feature, a back-end module should contain an I/O specific package that overrides {@link
 * fr.inria.atlanmod.neoemf.io.persistence.AbstractPersistenceHandler}.
 *
 * @see fr.inria.atlanmod.neoemf.io.persistence.AbstractPersistenceHandler
 */

package fr.inria.atlanmod.neoemf.data.blueprints.io;