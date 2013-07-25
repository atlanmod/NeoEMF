package fr.inria.atlanmod.neo4emf.util;
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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.neo4j.graphdb.Node;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

public interface ILoader {
	
	public static final String DUPLICATION_TOLERANT = "duplication_tolerant";
	
	public static final boolean DUPLICATION_TOLERANT_DEFAULT_VALUE = true;
	
	
	/**
	 * load Root elements from the Backend
	 * 
	 * @param options {@link Map}
	 */
	public void load(Map<?, ?> options);
	/**
	 * maps the node properties to the eObject attributes
	 * 
	 * @param object {@link EObject}
	 * @param node {@link Node}
	 */
	public void fetchAttributes(EObject object, Node node);
	/**
	 * gets the eReference of an eObject on demand
	 * 
	 * @param obj {@link EObject}
	 * @param featureId {@link Integer}
	 * @param node {@link Node}
	 * @param nodes {@link List}
	 */
	public void getObjectsOnDemand(EObject obj, int featureId, Node node, List<Node> nodes);
	/**
	 * build a list of NeoObjects of type <b>eClass</b>
	 * from a list of nodes representing these elements  
	 * @param eClass {@link EClass}
	 * @param nodeList {@link List}
	 * @return {@link List}
	 */
	public EList<INeo4emfObject> getAllInstances(EClass eClass,
			List<Node> nodeList);
	/**
	 * Get Container on demand 
	 * @param eObject {@link EObject}
	 * @param featureId {@link Integer}
	 * @return {@link EObject}
	 */
	public EObject getContainerOnDemand(EObject eObject, int featureId,Node node, Node containerNode);
	
}
