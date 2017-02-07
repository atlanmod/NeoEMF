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

package fr.inria.atlanmod.neoemf.data.berkeleydb.option;

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

import java.util.Map;

import javax.annotation.Nonnull;

/**
 * ???
 * <p>
 * All features are all optional: options can be created using all or none of them.
 */
@Experimental
public class BerkeleyDbOptionsBuilder extends AbstractPersistenceOptionsBuilder<BerkeleyDbOptionsBuilder, BerkeleyDbOptions> {

    /**
     * Constructs a new {@code BerkeleyDbOptionsBuilder}.
     *
     * @note This constructor is protected for API consistency purpose, to create a new builder use {@link
     * #newBuilder()}
     */
    protected BerkeleyDbOptionsBuilder() {
    }

    /**
     * Returns an immutable empty {@link Map}.
     *
     * @return an immutable {@link Map}
     */
    @Nonnull
    public static Map<String, Object> noOption() {
        return BerkeleyDbOptionsBuilder.newBuilder().asMap();
    }

    /**
     * Constructs a new {@code BerkeleyDbOptionsBuilder} instance.
     *
     * @return a new builder
     */
    @Nonnull
    public static BerkeleyDbOptionsBuilder newBuilder() {
        return new BerkeleyDbOptionsBuilder();
    }

    /**
     * ???
     *
     * @return this builder (for chaining)
     */
    @Nonnull
    @Deprecated
    public BerkeleyDbOptionsBuilder directWrite() {
        return storeOption(BerkeleyDbStoreOptions.DIRECT_WRITE);
    }

    /**
     * ???
     *
     * @return this builder (for chaining)
     */
    @Nonnull
    @Deprecated
    public BerkeleyDbOptionsBuilder directWriteLists() {
        return storeOption(BerkeleyDbStoreOptions.DIRECT_WRITE_LISTS);
    }

    /**
     * ???
     *
     * @return this builder (for chaining)
     */
    @Nonnull
    @Deprecated
    public BerkeleyDbOptionsBuilder directWriteArrays() {
        return storeOption(BerkeleyDbStoreOptions.DIRECT_WRITE_ARRAYS);
    }

    /**
     * ?
     *
     * @return this builder (for chaining)
     */
    @Nonnull
    @Deprecated
    public BerkeleyDbOptionsBuilder directWriteCacheMany() {
        return storeOption(BerkeleyDbStoreOptions.CACHE_MANY);
    }
}
