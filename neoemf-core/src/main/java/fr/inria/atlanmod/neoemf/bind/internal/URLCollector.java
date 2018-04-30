/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.bind.internal;

import java.net.URL;
import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object that analyzes, collects and supplies {@link URL}s.
 */
@ParametersAreNonnullByDefault
@FunctionalInterface
public interface URLCollector extends Supplier<Set<URL>> {
}
