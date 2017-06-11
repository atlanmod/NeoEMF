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
 * Provides miscellaneous utility classes, such as URIs management or models comparison.
 * <p>
 * This package defines the {@link fr.inria.atlanmod.neoemf.util.UriBuilder} class, that extends the standard EMF
 * {@link org.eclipse.emf.common.util.URI} to enable NeoEMF specific URI creation. Backend-specific implementations
 * provide their own implementation that allows to create a pre-formatted {@link org.eclipse.emf.common.util.URI}
 * for the corresponding backend.
 */

package fr.inria.atlanmod.neoemf.util;