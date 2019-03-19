/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides the base classes that extends EMF API to enable lazy-loading of model elements.
 * <p>
 * The core component defines the {@link fr.inria.atlanmod.neoemf.core.PersistentEObject} interface that extends EMF
 * {@link org.eclipse.emf.ecore.EObject} by delegating its attribute and reference accesses to the underlying backend.
 * {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}s contain a unique {@link fr.inria.atlanmod.neoemf.core.Id}
 * that is used by the underlying backend to retrieve, update, and delete model elements.
 * <p>
 * {@link fr.inria.atlanmod.neoemf.core.PersistentEObject} extends the {@link org.eclipse.emf.ecore.EObject} interface,
 * and can be accessed using the standard EMF API by client applications, easing the integration of NeoEMF.
 */

package fr.inria.atlanmod.neoemf.core;