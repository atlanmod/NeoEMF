/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides classes to map model-level operations into persistence-level operations.
 * <p>
 * NeoEMF relies on the {@link org.eclipse.emf.ecore.InternalEObject.EStore} interface to extend the default
 * serialization mechanism of EMF. The {@link fr.inria.atlanmod.neoemf.data.store.Store} interface extends the {@link
 * org.eclipse.emf.ecore.InternalEObject.EStore} one and provides additional methods such as {@code allInstance()}
 * computation. This interface is implemented by backend-specific classes to serialize models into the corresponding
 * backend.
 * <p>
 * In addition, this package defines a set of <i>store decorators</i> that can be combined to provide additional
 * behavior. For example, this package defines specific store decorators to cache model elements, log backend
 * operations, or automatically commit the pending transaction. These decorators are generic and can be plugged on top
 * of any backend-specific {@link fr.inria.atlanmod.neoemf.data.store.Store} implementation.
 */

package fr.inria.atlanmod.neoemf.data.store;