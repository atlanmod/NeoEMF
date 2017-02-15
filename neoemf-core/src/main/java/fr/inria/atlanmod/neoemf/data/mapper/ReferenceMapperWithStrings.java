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

package fr.inria.atlanmod.neoemf.data.mapper;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import java.util.Optional;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public interface ReferenceMapperWithStrings extends ReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(FeatureKey key) {
        return this.<String>valueOf(key)
                .map(this::parse);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(FeatureKey key, Id id) {
        return this.valueFor(key, format(id))
                .map(this::parse);
    }

    /**
     * ???
     *
     * @param value ???
     *
     * @return ???
     */
    default String format(Id value) {
        return checkNotNull(value).toString();
    }

    /**
     * ???
     *
     * @param value ???
     *
     * @return ???
     */
    default Id parse(String value) {
        return StringId.of(checkNotNull(value));
    }
}
