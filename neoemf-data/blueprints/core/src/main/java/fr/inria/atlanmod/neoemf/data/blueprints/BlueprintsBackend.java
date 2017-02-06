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

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;

import org.eclipse.emf.ecore.EcorePackage;

/**
 *
 */
public interface BlueprintsBackend extends PersistenceBackend {

    /**
     * The literal description of this back-end.
     */
    String NAME = "blueprints";

    /**
     * @deprecated Don't use this field
     */
    @Deprecated
    String KEY_ECLASS_NAME = EcorePackage.eINSTANCE.getENamedElement_Name().getName();

    /**
     * @deprecated Don't use this field
     */
    @Deprecated
    String KEY_EPACKAGE_NSURI = EcorePackage.eINSTANCE.getEPackage_NsURI().getName();

    /**
     * @deprecated Don't use this field
     */
    @Deprecated
    String KEY_INSTANCE_OF = "kyanosInstanceOf";

    /**
     * @deprecated Don't use this method
     */
    @Deprecated
    Vertex getVertex(Id id);

    /**
     * @deprecated Don't use this method
     */
    @Deprecated
    Vertex addVertex(Id id);
}
