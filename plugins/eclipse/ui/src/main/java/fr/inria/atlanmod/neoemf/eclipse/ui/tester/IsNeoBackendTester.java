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

package fr.inria.atlanmod.neoemf.eclipse.ui.tester;

import fr.inria.atlanmod.neoemf.data.BackendFactory;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.Path;

import java.util.Objects;

public class IsNeoBackendTester extends PropertyTester {

    private static final String IS_NEOEMF_DB = "isNeoEMFDB";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        return Objects.equals(IS_NEOEMF_DB, property)
                && IFolder.class.isInstance(receiver)
                && IFolder.class.cast(receiver).exists(new Path(BackendFactory.CONFIG_FILE)) == Boolean.class.cast(expectedValue);
    }
}
