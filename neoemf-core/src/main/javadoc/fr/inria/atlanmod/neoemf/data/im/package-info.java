/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides classes related to data management specific to the default in-memory implementation.
 * <p>
 * This implementation is used by default, as a transient database, when a persistent {@link
 * fr.inria.atlanmod.neoemf.data.Backend} has not been saved.
 * <p>
 * The default in-memory database is built on top of ChronicleMap, a in-memory key-value store.
 * <p>
 * <b>NOTE:</b> Data processed with this implementation are not persistent.
 */

package fr.inria.atlanmod.neoemf.data.im;