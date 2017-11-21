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
 * serialization mechanism of EMF. The {@link fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter} interface
 * extends the {@link org.eclipse.emf.ecore.InternalEObject.EStore} one.
 */

package fr.inria.atlanmod.neoemf.data.store.adapter;