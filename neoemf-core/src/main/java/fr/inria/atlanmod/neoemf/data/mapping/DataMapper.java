/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.data.Saveable;

import org.atlanmod.commons.Copiable;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An object capable of mapping features, containers and meta-classes represented as a set of key/value pair.
 *
 * @see ContainerMapper
 * @see ClassMapper
 * @see ValueMapper
 * @see ReferenceMapper
 * @see ManyValueMapper
 * @see ManyReferenceMapper
 */
@ParametersAreNonnullByDefault
public interface DataMapper extends Saveable, Copiable<DataMapper>, ContainerMapper, ClassMapper, ValueMapper, ManyValueMapper, ReferenceMapper, ManyReferenceMapper {
}
