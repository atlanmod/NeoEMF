/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.tester;

import fr.inria.atlanmod.neoemf.config.Config;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFolder;

import java.nio.file.Path;
import java.util.Objects;

/**
 * A {@link PropertyTester} that checks if a directory represents a {@link fr.inria.atlanmod.neoemf.data.Backend}.
 */
public class IsBackendTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (!Objects.equals("isNeoEMFDB", property) || !IFolder.class.isInstance(receiver)) {
            return false;
        }

        Path directory = IFolder.class.cast(receiver)
                .getLocation()
                .toFile()
                .toPath();

        return Config.exists(directory) == Boolean.class.cast(expectedValue);
    }
}
