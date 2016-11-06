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

package fr.inria.atlanmod.neoemf.benchmarks;

/**
 *
 */
public interface QueryRunner {

    /*
     * Commons queries
     */

    void classDeclarationAttributes();

    void grabats();

    void invisibleMethodDeclarations();

    void orphanNonPrimitiveTypes();

    void renameAllMethods();

    void thrownExceptionsPerPackage();

    void unusedMethodsWithList();

    void unusedMethodsWithLoop();

    /*
     * ASE 2015 queries
     */

    void branchStatementsAse2015();

    void grabatsAse2015();

    void invisibleMethodDeclarationsAse2015();

    void specificInvisibleMethodDeclarationsAse2015();

    void thrownExceptionsAse2015();
}
