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
 * Provides specific classes to map model-level operations into Blueprints-level operations.
 * <p>
 * This package provides implementations of the {@link fr.inria.atlanmod.neoemf.data.store.PersistentStore} interface that use a Blueprints database
 * to store the result of modeling operations and retrieve model elements.
 * <p>
 * NeoEMF Blueprints implementation does not provide additional <i>store decorators</i>. Note that generic decorators defined at the core level (see {@link fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator} subclasses)
 * can be combined on top of Blueprints stores to provide additional behavior, such as autocommit or feature caching.
 */

package fr.inria.atlanmod.neoemf.data.blueprints.store;