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

package fr.inria.atlanmod.neoemf.benchmarks.query;

import java.text.MessageFormat;

class QueryHelper {

    private static final long MB = 1024L * 1024L;

    private QueryHelper() {
    }

    public static String toMegabytes(long bytes) {
        return MessageFormat.format("{0} {1}", bytes / MB, "MB");
    }
}
