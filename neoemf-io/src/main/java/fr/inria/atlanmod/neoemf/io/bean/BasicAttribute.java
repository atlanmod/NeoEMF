/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.bean;

import org.eclipse.emf.ecore.EAttribute;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A simple representation of a {@link EAttribute}
 */
@ParametersAreNonnullByDefault
public class BasicAttribute extends AbstractBasicFeature<BasicAttribute, EAttribute, Object> {
}
