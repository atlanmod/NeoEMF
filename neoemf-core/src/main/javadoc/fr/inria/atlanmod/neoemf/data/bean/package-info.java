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
 * Provides simple representations of objects used to translate model-level operations in persistence calls.
 * <p>
 * This package define a set of abstraction that can be used by {@link fr.inria.atlanmod.neoemf.data.store.Store}
 * subclasses as an intermediate layer between the backend level and the model level. For example, the {@link
 * fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean} class can be used to represent a containment link
 * between two elements. These abstract structures are useful to manipulate the model at a low-level without knowing the
 * concrete model-to-backend mapping used.
 */

package fr.inria.atlanmod.neoemf.data.bean;