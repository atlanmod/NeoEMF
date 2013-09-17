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

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.change.impl.ChangeLog;





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
	protected NeoObjectData getObjectData(){	
		return null;
	}
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
	
	public Neo4emfObject(EClass eClass) {
		super();
		eSetClass(eClass);
		id="";
		isProxy=false;
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
	
	@Override 
	public Object eGet( EStructuralFeature eFeature ){
		return eGet(eFeature, true);
	}
	@Override 
	public Object eGet(EStructuralFeature str, boolean resolve){
		return eGet(str, true, true);
	}
	
	@Override
	public Object eGet(EStructuralFeature eFeature, boolean resolve, boolean coreType){ 
		 int featureID = eDerivedStructuralFeatureID(eFeature);
		 Assert.isTrue(featureID >= 0, "Invalid feature : "+ eFeature.getName()); 
		 return eGet(featureID, resolve, coreType);
	 }
	 

	@Override 
	public Object eGet(int featureID, boolean resolve, boolean coreType){
		 EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
		 Assert.isNotNull(eFeature, "Invalid feature : "+ eFeature);
		 Object result = simple_eGet(featureID, resolve, coreType);	
		 if (id == "")
			 return result;
		 if (! eFeature.isMany()){		
			 if (result != null )
				 return result;
		 } else {
			 if (!((List<?>)result).isEmpty())
				 return result;
		 }
		 
		 Assert.isTrue(eResource() != null && eResource() instanceof INeo4emfResource, "The resource is either null or not of type INeo4emfResource");
		 INeo4emfResource resource = (INeo4emfResource) eResource();
		 if (eFeature instanceof EAttribute)
			 resource.fetchAttributes(this);
		 else
		 	 resource.getOnDemand(this, featureID);
		 return simple_eGet(featureID, resolve, coreType);
	
	}
	
	protected Object simple_eGet(int featureID, boolean resolve, boolean coreType){
		int dynamicFeatureID = featureID - eStaticFeatureCount();
		 EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
		 Assert.isTrue(eFeature != null , "Invalid featureID: " + featureID);
		 Object result = eSettingDelegate(eFeature).dynamicGet(this, eSettings(), dynamicFeatureID, resolve, coreType);
		 if (result != null){
			 Notification msg = new ENotificationImpl(this, INeo4emfNotification.GET, eFeature, null, null);
			 ChangeLog.getInstance().addNewEntry(msg);
		 }
		return result;
	}
	
	@Override 
	public void eSet (EStructuralFeature eFeature, Object newValue){
		int featureID = eDerivedStructuralFeatureID(eFeature); 
		Assert.isTrue(featureID >= 0 , "Invalid Feature : "+ eFeature.getName());
		eSet(featureID, newValue);	
	}	
	
	@Override
	public void eSet(int featureID,  Object newValue){
		super.eSet(featureID, newValue);
		EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID); 
		Object oldValue = simple_eGet(featureID, true, true);
		int dynamicFeatureID = featureID - eStaticFeatureCount();
		Assert.isTrue(eFeature.isChangeable()," illegal argument feature Cannot be changed : " + eFeature);
		eDynamicSet(dynamicFeatureID, eFeature, newValue);
		Notification msg = new ENotificationImpl(this, Notification.SET, eFeature, oldValue, newValue);
		ChangeLog.getInstance().addNewEntry(msg);
	}
	
	protected boolean isLoaded() {
		return getNodeId()>-1 & eResource() instanceof INeo4emfResource;
	}
	
	public static class NeoObjectData{
		
	}



	
}
