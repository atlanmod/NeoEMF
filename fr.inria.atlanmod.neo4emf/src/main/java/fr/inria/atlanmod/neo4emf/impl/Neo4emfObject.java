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

import java.lang.ref.ReferenceQueue;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.change.impl.ChangeLog;





public class Neo4emfObject  extends MinimalEObjectImpl implements INeo4emfObject {

	/**
	 * eObject ID 
	 */
	protected String id;
	
	protected String tmpId;
	/**
	 * isProxy flag
	 */
	protected boolean isProxy;
	
	/**
	 * Local reference queue
	 */
	protected ReferenceQueue<Object> garbagedData;
	
	/**
	 * hasProxy flag
	 */
	protected NeoObjectData getObjectData() {	
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
		if (this.id == "") {
			return -1;
		}	
		return Long.parseLong(this.id.substring(this.id.lastIndexOf("/") + 1));
	}
	
	@Override
	public long getTmpNodeId() {
		if(this.tmpId == "") {
			return -1;
		}
		return Long.parseLong(this.tmpId.substring(this.tmpId.lastIndexOf("/") + 1));
	}
	
	/**
	 * @see INeo4emfObject#setNodeId()
	 */
	@Override
	public void setNodeId(final long nodeId){
		if (this.id == "") {
			this.id = "/" + nodeId;
		} else {
			this.id = this.id.substring(0, this.id.lastIndexOf("/")) + nodeId;
		}
	}
	
	@Override
	public void setTmpNodeId(final long nodeId) {
		if(this.tmpId == "") {
			this.tmpId = "/" + nodeId;
		}
		else {
			this.tmpId = this.tmpId.substring(0, this.tmpId.lastIndexOf("/")) + nodeId;
		}
	}
	
	@Override
	public void resetTmpNodeId() {
		this.tmpId = "";
	}
	
	@Override
	public int getPartitionId() {
		if (this.id == "" || this.id.lastIndexOf("/") == 0) {
			return -1;
		}
		return Integer.parseInt(this.id.substring(0, this.id.lastIndexOf("/")));
	}
	@Override
	public void setPartitionId(final int partId) {
		this.id = partId + this.id.substring(this.id.lastIndexOf("/"));
	}
	
	/**
	 * Constructor  
	 */
	public Neo4emfObject() {
		super();
		this.id = "";
		this.tmpId = "";
		this.isProxy = false;
		this.garbagedData = new ReferenceQueue<Object>();
	}
	
	public Neo4emfObject(final EClass eClass) {
		super();
		eSetClass(eClass);
		this.id = "";
		this.isProxy = false;
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
	public int compareTo(final INeo4emfObject o) {
		return this.equals(o)? 0 : this.eContainer().eClass().getName().compareTo(o.eContainer().eClass().getName());
	}
	
	@Override
	public boolean eIsProxy() {
		// TODO redefine the eIsproxy method 
		return this.isProxy;
	}
	@Override
	public void setProxy(final boolean isProxy) {
		this.isProxy = isProxy;
	}
	
	@Override 
	public Object eGet(final EStructuralFeature eFeature ) {
		return eGet(eFeature, true);
	}
	@Override 
	public Object eGet(final EStructuralFeature str, boolean resolve ){
		return eGet(str, true, true);
	}
	
	@Override
	public Object eGet(final EStructuralFeature eFeature, final boolean resolve, final boolean coreType){ 
		 int featureID = eDerivedStructuralFeatureID(eFeature);
		 Assert.isTrue(featureID >= 0, "Invalid feature : "+ eFeature.getName()); 
		 return eGet(featureID, resolve, coreType);
	 }
	 
	
	@Override 
	public Object eGet(final int featureID, final boolean resolve, final boolean coreType){
		 EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
		 Assert.isNotNull(eFeature, "Invalid feature : " + eFeature);
		 Object result = simpleGet(featureID, resolve, coreType, true);	
		 if (this.id == "") {
			return result;
		}
		 if (!eFeature.isMany()) {		
			 if (result != null) {
				return result;
			}
		 } else {
			 if (!((List<?>) result).isEmpty()) {
				return result;
			}
		 }
		 
		 Assert.isTrue(eResource() != null && eResource() instanceof INeo4emfResource, "The resource is either null or not of type INeo4emfResource");
		 INeo4emfResource resource = (INeo4emfResource) eResource();
		 if (eFeature instanceof EAttribute) {
			resource.fetchAttributes(this);
		} else {
			resource.getOnDemand(this, featureID);
		}
		 return simpleGet(featureID, resolve, coreType, false);
	
	}
	 protected Object eDynamicGet(final int dynamicFeatureID, final EStructuralFeature eFeature, final boolean resolve, final boolean coreType){
		Assert.isTrue(dynamicFeatureID >= 0, "invalid Feature with " + eFeature);
		Object result = eSettingDelegate(eFeature).dynamicGet(this, eSettings(), dynamicFeatureID, resolve, coreType);
		if (result == null) {
			INeo4emfResource resource = (INeo4emfResource) eResource();	 	
			if (eFeature instanceof EAttribute) {
				resource.fetchAttributes(this);
			} else { 
				 resource.getOnDemand(this, dynamicFeatureID + eStaticFeatureCount()); 
			 	 }
		}
		return simpleGet(dynamicFeatureID + eStaticFeatureCount(), resolve, coreType, true);
	 	
	 }
		 
		 
		 protected Object simpleGet(final int featureID, final boolean resolve, final boolean coreType, final boolean notificationRequired){
		int dynamicFeatureID = featureID - eStaticFeatureCount();
		 EStructuralFeature eFeature = eClass().getEStructuralFeature(featureID);
		 Assert.isTrue(eFeature != null , "Invalid featureID: " + featureID);
		 Object result = eSettingDelegate(eFeature).dynamicGet(this, eSettings(), dynamicFeatureID, resolve, coreType);
		 if (result != null && notificationRequired) {
			 Notification msg = new ENotificationImpl(this, INeo4emfNotification.GET, eFeature, null, null);
			 ChangeLog.getInstance().addNewEntry(msg);
		 }
		return result;
	}
	
	@Override
	protected void eDynamicSet(final int dynamicFeatureID, final EStructuralFeature eFeature, Object newValue){
		Object oldValue = simpleGet(dynamicFeatureID + eStaticFeatureCount(), true, true, false);
		eSettingDelegate(eFeature).dynamicSet(this, eSettings(), dynamicFeatureID, newValue);
		Notification msg = new ENotificationImpl(this, Notification.SET, eFeature, oldValue, newValue);
		ChangeLog.getInstance().addNewEntry(msg);
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
		int dynamicFeatureID = featureID - eStaticFeatureCount();
		Assert.isTrue(eFeature.isChangeable()," illegal argument feature Cannot be changed : " + eFeature);
		eDynamicSet(dynamicFeatureID, eFeature, newValue);
	}
	
	@Override 
	protected Object eVirtualValue(int index){
		// handle virtual delegation in the eResource
		//		Object result = eVirtualValues()[index];
		//		if (result != null )
		//			return result;
		//		else {
		//			
		//		}
		throw new UnsupportedOperationException();		
	}
	protected boolean isLoaded() {
		return getNodeId() > -1 & eResource() instanceof INeo4emfResource;
	}
	
	public static class NeoObjectData {
		
	}



	
}
