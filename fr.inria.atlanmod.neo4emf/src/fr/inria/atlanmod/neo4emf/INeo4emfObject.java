package fr.inria.atlanmod.neo4emf;
/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Amine BENELALLAM
 * */
import org.eclipse.emf.ecore.EObject;

public interface INeo4emfObject extends EObject, Comparable <INeo4emfObject>{
	
	
		/**
		 * returns the <b>ID</b> of the node that represents the 
		 * 		element in the backend and <b>-1</b> if the element 
		 * 		have not been persisted yet
		 * @return ID {@link long }			
		 */
		public long getNodeId();
		
		/**
		 * set the <b>ID</b> of the eObject once created from the 
		 * 	backend node
		 * @param id {@link long}
		 */
		void setNodeId(long id);
		/**
		 * @return ID {@link int}
		 */
		public int getPartitionId();
		
		/**
		 * set the partition Id 
		 * @param id
		 */
		public void setPartitionId(int id);

		void setProxy(boolean isProxy);

//		boolean isHasProxy();
//
//		void setHasProxy(boolean hasProxy);
	
		

}
