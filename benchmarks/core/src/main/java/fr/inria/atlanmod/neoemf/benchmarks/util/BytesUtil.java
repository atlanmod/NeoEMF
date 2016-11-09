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

package fr.inria.atlanmod.neoemf.benchmarks.util;

import java.text.MessageFormat;

public class BytesUtil {

    private static final long KB = 1024L;
    private static final long MB = KB * KB;
    private static final long GB = KB * KB * KB;

    private BytesUtil() {
    }

    public static String toKilobytes(long bytes) {
        return MessageFormat.format("{0} {1}", bytes / KB, "KB");
    }

    public static String toMegabytes(long bytes) {
        return MessageFormat.format("{0} {1}", bytes / MB, "MB");
    }

    public static String toGigabytes(long bytes) {
        return MessageFormat.format("{0} {1}", bytes / GB, "GB");
    }
}
