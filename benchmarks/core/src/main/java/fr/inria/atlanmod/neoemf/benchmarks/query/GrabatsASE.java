/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;

import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 *
 */
@ParametersAreNonnullByDefault
class GrabatsASE extends Grabats {

    @Override
    protected boolean isStaticAndReturns(ClassDeclaration type, MethodDeclaration method) {
        Modifier modifier = method.getModifier();
        TypeAccess returnType = method.getReturnType();

        return nonNull(modifier)
                && nonNull(returnType)
                && modifier.isStatic()
                && returnType.getType() == type;
    }
}
