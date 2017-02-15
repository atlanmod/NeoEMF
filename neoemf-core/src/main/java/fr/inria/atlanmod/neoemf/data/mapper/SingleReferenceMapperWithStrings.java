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
public interface SingleReferenceMapperWithStrings extends SingleValueMapper, SingleReferenceMapper {

    /**
     * ???
     *
     * @param value ???
     *
     * @return ???
     */
    static String format(Id value) {
        checkNotNull(value);

        return value.toString();
    }

    /**
     * ???
     *
     * @param value ???
     *
     * @return ???
     */
    static Id parse(String value) {
        checkNotNull(value);

        return StringId.of(value);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceOf(FeatureKey key) {
        return this.<String>valueOf(key)
                .map(SingleReferenceMapperWithStrings::parse);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(FeatureKey key, Id id) {
        return this.valueFor(key, format(id))
                .map(SingleReferenceMapperWithStrings::parse);
    }
}
