/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.function.Converter;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Converter} of {@link Id} instances.
 *
 * @param <T> the type of the result of the converter
 */
@ParametersAreNonnullByDefault
public interface IdConverter<T> extends Converter<Id, T> {
}
