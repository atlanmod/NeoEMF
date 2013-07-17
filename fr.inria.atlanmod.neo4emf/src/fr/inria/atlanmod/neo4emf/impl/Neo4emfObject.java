package fr.inria.atlanmod.neo4emf.impl;
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

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import fr.inria.atlanmod.neo4emf.INeo4emfObject;




public class Neo4emfObject  extends MinimalEObjectImpl implements INeo4emfObject {

	/**
	 * eObject ID 
	 */
	protected String id;
	/**
	 * isProxy flag
	 */
	protected boolean isProxy;
	/**
	 * hasProxy flag
	 */
	//protected boolean hasProxy;
	
//	@Override
//	public boolean isHasProxy() {
//		return hasProxy;
//	}
//	@Override
//	public void setHasProxy(boolean hasProxy) {
//		this.hasProxy = hasProxy;
//	}
//	
	/**
	 * @see INeo4emfObject#getNodeId()
	 */
	@Override
	public long getNodeId() {
		if (id=="") return -1;	
		return Long.parseLong(id.substring(id.lastIndexOf("/")+1));
	}
	/**
	 * @see INeo4emfObject#setNodeId()
	 */
	@Override
	public void setNodeId(long nodeId){
		if (id=="")
			id= "/"+nodeId;
		else 
			this.id = id.substring(0,id.lastIndexOf("/"))+nodeId;
	}
	@Override
	public int getPartitionId() {
		if (id=="" || id.lastIndexOf("/")==0) return -1;
		return Integer.parseInt(id.substring(0,id.lastIndexOf("/")));
	}
	@Override
	public void setPartitionId(int partId) {
		id = partId+id.substring(id.lastIndexOf("/"));
	}
	/**
	 * Constructor  
	 */
	public Neo4emfObject (){
		super();
		id="";
		isProxy= false;
	}
	/**
	 * Clear the field <b>ID</b>
	 */
	public void clearId(){
		id="";
	}
	/**
	 * compare two objects of type {@link INeo4emfObject}
	 *@param {@link INeo4emfObject}
	 *@return {@link int }
	 */
	@Override
	public int compareTo(INeo4emfObject o) {
		// TODO Auto-generated method stub
		return this.equals(o)? 0 : this.eContainer().eClass().getName().compareTo(o.eContainer().eClass().getName());
	}
	
	@Override
	public boolean eIsProxy(){
		// TODO redefine the eIsproxy method 
		return isProxy;
	}
	@Override
	public void setProxy(boolean isProxy) {
		this.isProxy = isProxy;
	}
}
