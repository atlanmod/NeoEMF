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

import fr.inria.atlanmod.neoemf.util.log.Log;
import fr.inria.atlanmod.neoemf.util.log.Logger;

import org.junit.AssumptionViolatedException;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link org.junit.Rule} that logs each test-case call.
 */
public class Watcher extends org.junit.rules.TestWatcher {

    /**
     * The special logger without timestamp.
     */
    private static final Logger LOG_TEST = Log.customLogger("test");

    /**
     * The special logger without timestamp and level.
     */
    private static final Logger LOG_VOID = Log.customLogger("void");

    /**
     * An empty message.
     */
    private static final String NO_MESSAGE = "";

    /**
     * The message to display a running test.
     */
    private static final String MESSAGE_RUNNING = "--- Running";

    /**
     * The message to display a successful test.
     */
    private static final String MESSAGE_SUCCESS = "--- Succeeded";

    /**
     * The message to display a failed test.
     */
    private static final String MESSAGE_FAILURE = "--- Failed";

    /**
     * The message to display a failed test with errors.
     */
    private static final String MESSAGE_FAILURE_ERRORS = MESSAGE_FAILURE + " with error(s):";

    /**
     * The message to display a skipped test.
     */
    private static final String MESSAGE_SKIP = "--- Skipped";

    @Override
    protected void succeeded(Description description) {
        LOG_TEST.info(MESSAGE_SUCCESS);
    }

    @Override
    protected void failed(Throwable e, Description description) {
        if (isNull(e)) {
            LOG_TEST.warn(MESSAGE_FAILURE);
        }
        else if (AssertionError.class.isInstance(e) && nonNull(e.getMessage())) {
            Arrays.stream(e.getMessage().split("\n"))
                    .filter(s -> nonNull(s) && !s.isEmpty())
                    .forEach(LOG_TEST::warn);

            LOG_TEST.warn(NO_MESSAGE);
            LOG_TEST.warn(MESSAGE_FAILURE);
        }
        else {
            LOG_TEST.error(MESSAGE_FAILURE_ERRORS);

            (MultipleFailureException.class.isInstance(e)
                    ? MultipleFailureException.class.cast(e).getFailures()
                    : Collections.singletonList(e))
                    .forEach(LOG_VOID::error);

            LOG_VOID.error(NO_MESSAGE);
        }
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        if (nonNull(e) && nonNull(e.getMessage())) {
            LOG_TEST.warn(MESSAGE_SKIP + ": {0}", e.getMessage());
        }
        else {
            LOG_TEST.warn(MESSAGE_SKIP);
        }
    }

    @Override
    protected void starting(Description description) {
        LOG_VOID.info(NO_MESSAGE);
        LOG_TEST.info(MESSAGE_RUNNING + ": {0}", description.getMethodName());
    }

    @Override
    protected void finished(Description description) {
        LOG_VOID.info(NO_MESSAGE);
    }
}
