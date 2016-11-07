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

import fr.inria.atlanmod.neoemf.benchmarks.Runner;

/**
 * A {@link Runner} able to execute {@link fr.inria.atlanmod.neoemf.benchmarks.query.Query queries}.
 *
 * @see fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory
 */
public interface QueryRunner extends Runner {

    /*
     * Commons queries
     */

    void classDeclarationAttributes() throws Exception;

    void grabats() throws Exception;

    void invisibleMethodDeclarations() throws Exception;

    void orphanNonPrimitiveTypes() throws Exception;

    void renameAllMethods() throws Exception;

    void thrownExceptionsPerPackage() throws Exception;

    void unusedMethodsWithList() throws Exception;

    void unusedMethodsWithLoop() throws Exception;

    /*
     * ASE 2015 queries
     */

    void branchStatementsAse2015() throws Exception;

    void grabatsAse2015() throws Exception;

    void invisibleMethodDeclarationsAse2015() throws Exception;

    void specificInvisibleMethodDeclarationsAse2015() throws Exception;

    void thrownExceptionsAse2015() throws Exception;
}
