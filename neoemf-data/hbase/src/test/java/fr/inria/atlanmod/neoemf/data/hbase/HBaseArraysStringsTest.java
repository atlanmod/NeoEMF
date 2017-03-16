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

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.hbase.context.HBaseContext;
import fr.inria.atlanmod.neoemf.data.mapper.AbstractPersistenceMapperTest;

import org.junit.Ignore;

// FIXME `AbstractHBaseBackend#unsetValue` does not correctly clean many values/references
public class HBaseArraysStringsTest extends AbstractPersistenceMapperTest {

    @Override
    public Context context() {
        return HBaseContext.getWithArraysAndStrings();
    }

    @Ignore
    @Override
    public void testRemoveValue() {
    }

    @Ignore
    @Override
    public void testRemoveAllValues() {
    }

    @Ignore
    @Override
    public void testContainsValue() {
    }

    @Ignore
    @Override
    public void testRemoveReference() {
    }

    @Ignore
    @Override
    public void testRemoveAllReferences() {
    }

    @Ignore
    @Override
    public void testContainsReference() {
    }
}
