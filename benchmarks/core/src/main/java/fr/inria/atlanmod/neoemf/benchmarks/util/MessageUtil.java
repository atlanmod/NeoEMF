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

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

public class MessageUtil {

    private MessageUtil() {
    }

    /**
     * 'HH:mm:ss' format for time.
     */
    private static final String TIME_FORMAT = "%02d:%02d:%02d";

    /**
     * 10^6 factor for bytes.
     */
    private static final int MB = 1024 * 1024;

    public static String formatMillis(long millis) {
        return String.format(TIME_FORMAT,
                MILLISECONDS.toHours(millis),
                MILLISECONDS.toMinutes(millis) - HOURS.toMinutes(MILLISECONDS.toHours(millis)),
                MILLISECONDS.toSeconds(millis) - MINUTES.toSeconds(MILLISECONDS.toMinutes(millis)));
    }

    public static String byteCountToDisplaySize(long size) {
        return MessageFormat.format("{0} MB", size / MB);
    }
}
