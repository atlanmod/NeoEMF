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

import fr.inria.atlanmod.neoemf.benchmarks.QueryExecutor;

/**
 *
 */
public interface QueryExecutorASE2015 extends QueryExecutor {

    void queryASE2015GetBranchStatements();

    void queryASE2015Grabats09();

    void queryASE2015InvisibleMethodDeclarations();

    void queryASE2015SpecificInvisibleMethodDeclarations();

    void queryASE2015ThrownExceptions();
}
