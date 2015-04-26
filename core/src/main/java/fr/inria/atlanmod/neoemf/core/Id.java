/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.core;

import java.io.Serializable;

public interface Id extends Comparable<Id>, Serializable {

    /**
     * 
     * @return The String representation of the Id
     */
    String toString();
    
    /**
     * 
     * @return the long number of the Id
     */
    long toLong();

}
