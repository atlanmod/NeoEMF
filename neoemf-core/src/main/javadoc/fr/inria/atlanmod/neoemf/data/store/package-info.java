/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides classes that adds preprocessing and postprocessing functionalities during persistence operations.
 * <p>
 * This package defines a set of <i>store decorators</i> that can be combined to provide additional behavior. For
 * example, this package defines specific store decorators to cache model elements, log backend operations, or
 * automatically commit the pending transaction. These stores are generic and can be plugged on top of any {@link
 * fr.inria.atlanmod.neoemf.data.Backend} implementation.
 */

package fr.inria.atlanmod.neoemf.data.store;