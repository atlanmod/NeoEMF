/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

/**
 * Provides classes related to the binding engine and classpath analysis.
 * <p>
 * The binding engine is centered on the {@link fr.inria.atlanmod.neoemf.bind.BindingEngine} class that allows to
 * dynamically bind {@link fr.inria.atlanmod.neoemf.data.BackendFactory}, {@link fr.inria.atlanmod.neoemf.util.UriFactory}
 * and {@link fr.inria.atlanmod.neoemf.config.Config} instances at runtime, by analyzing elements annotated with the
 * {@link fr.inria.atlanmod.neoemf.bind.FactoryBinding} annotation on a limited view of a classpath.
 * <p>
 * This package is only intended to be used internally.
 */

package fr.inria.atlanmod.neoemf.bind;