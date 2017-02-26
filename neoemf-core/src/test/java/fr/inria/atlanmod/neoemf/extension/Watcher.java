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

package fr.inria.atlanmod.neoemf.extension;

import com.google.common.base.Splitter;

import fr.inria.atlanmod.neoemf.util.logging.Log;
import fr.inria.atlanmod.neoemf.util.logging.Logger;

import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link org.junit.Rule} that logs each test-case call.
 */
public class Watcher extends org.junit.rules.TestWatcher {

    /**
     * The special logger where messages will be sent.
     */
    private static final Logger LOG = Log.customLogger("test");

    @Override
    protected void succeeded(Description description) {
        LOG.info("[INFO] --- Succeeded");
    }

    @Override
    protected void failed(Throwable e, Description description) {
        if (isNull(e)) {
            LOG.warn("[WARN] --- Failed");
        }
        else if (AssertionError.class.isInstance(e) && nonNull(e.getMessage())) {
            Splitter.on('\n').omitEmptyStrings().splitToList(e.getMessage())
                    .forEach(m -> LOG.warn("[WARN] {0}", m));

            LOG.warn("[WARN]");
            LOG.warn("[WARN] --- Failed");
        }
        else {
            // Several exceptions
            if (MultipleFailureException.class.isInstance(e)) {
                LOG.error(e, "[ERROR] --- Several exceptions have been thrown during the test:");
                ((MultipleFailureException) e).getFailures().forEach(t -> LOG.error(t, ""));
                LOG.error("");
            }
            // One exception
            else {
                LOG.error(e, "[ERROR] --- An exception has been thrown during the test:");
            }
        }
    }

    @Override
    protected void starting(Description description) {
        LOG.info("");
        LOG.info("[INFO] --- Running " + description.getMethodName());
    }

    @Override
    protected void finished(Description description) {
        LOG.info("");
    }
}
