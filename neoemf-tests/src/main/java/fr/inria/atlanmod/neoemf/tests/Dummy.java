/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.tests;

import org.atlanmod.commons.Throwables;

/**
 * This is a dummy class : <b>DON'T USE IT</b> !
 * <p>
 * This class is here to ensure a valid deployment in Maven Central.
 */
final class Dummy {

    private Dummy() {
        throw Throwables.notInstantiableClass(getClass());
    }
}
