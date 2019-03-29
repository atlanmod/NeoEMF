/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides classes extending EMF resource management to support lazy-loading and database delegation.
 * <p>
 * The resource component defines the {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} interface, that
 * extends EMF {@link org.eclipse.emf.ecore.resource.Resource} and provides high-level modeling operations such as
 * efficient {@code allInstances()} computation. {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} instances
 * can be accessed as standard EMF {@link org.eclipse.emf.ecore.resource.Resource}, easing NeoEMF integration in client
 * applications.
 * <p>
 * The {@link fr.inria.atlanmod.neoemf.resource.DefaultPersistentResource} class implements the {@link
 * fr.inria.atlanmod.neoemf.resource.PersistentResource} interface and extends the standard EMF {@link
 * org.eclipse.emf.ecore.resource.Resource} with efficient lazy-loading capabilities.
 */

package fr.inria.atlanmod.neoemf.resource;