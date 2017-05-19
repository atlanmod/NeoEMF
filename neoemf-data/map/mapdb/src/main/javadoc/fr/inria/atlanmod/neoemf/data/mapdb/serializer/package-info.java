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
 * Provides serializers that allows to persist model-level elements into MapDB collections.
 * <p>
 * Serializers are used internally by the framework to efficiently convert modeling elements into MapDB records. Note that every resource stored in 
 * MapDB uses the same serializers. Providing custom serializer options will be possible in a future release of the tool.
 */

package fr.inria.atlanmod.neoemf.data.mapdb.serializer;