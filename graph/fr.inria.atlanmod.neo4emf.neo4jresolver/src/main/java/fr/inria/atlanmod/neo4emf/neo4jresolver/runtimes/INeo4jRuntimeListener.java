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
package fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes;

/**
 * @author abelgomez
 *
 */
public interface INeo4jRuntimeListener {

	public class Event {
		private AbstractNeo4jRuntimeInstaller source;
		
		public Event(AbstractNeo4jRuntimeInstaller source) {
			this.source = source;
		}
		
		/**
		 * @return the source
		 */
		public AbstractNeo4jRuntimeInstaller getSource() {
			return source;
		}
	}
	
	public void notifyChange(Event event);
	
}
