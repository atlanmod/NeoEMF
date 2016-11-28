/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.hbase.option;

import fr.inria.atlanmod.neoemf.option.AbstractPersistenceOptionsBuilder;

public class HBaseOptionsBuilder extends AbstractPersistenceOptionsBuilder<HBaseOptionsBuilder> {

    protected HBaseOptionsBuilder() {
    }

    public static HBaseOptionsBuilder newBuilder() {
        return new HBaseOptionsBuilder();
    }

    public HBaseOptionsBuilder readOnly() {
        return option(HBaseResourceOptions.READ_ONLY, true);
    }
}
