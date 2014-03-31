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
**/
package fr.inria.atlanmod.neo4emf.testdata.impl;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import fr.inria.atlanmod.neo4emf.testdata.ColoredVertex;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.neo4j.graphdb.Node;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Colored Vertex</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.impl.ColoredVertexImpl#getANatural <em>ANatural</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColoredVertexImpl extends VertexImpl implements ColoredVertex {

	 
	
	 
	 
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColoredVertexImpl() {
		super();
		
	}

	
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestPackage.Literals.COLORED_VERTEX;
	}

/** genFeaure.override.javajetinc **/
	/**
	 * <!-- begin-user-doc -->
	 *XX7
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getANatural() {
		try {
			setLoadingOnDemand();	
	  		
			return getData().aNatural;
		
		} finally {
			unsetLoadingOnDemand();
		}
	}
 /**
 * <!-- begin-user-doc -->
 *YY2
 * <!-- end-user-doc -->
 * @generated
 */
	public void setANatural(BigInteger newANatural) {
	
		
	
		BigInteger oldANatural = getData().aNatural;
		getData().aNatural = newANatural;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(
			this, Notification.SET,
			TestPackage.COLORED_VERTEX__ANATURAL,
			oldANatural, getData().aNatural));
        }  
		this.addChangelogEntry(newANatural, TestPackage.COLORED_VERTEX__ANATURAL);
	} 

	/**
	 * <!-- begin-user-doc -->
	 *YY15
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestPackage.COLORED_VERTEX__ANATURAL:
				return getANatural();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY16
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestPackage.COLORED_VERTEX__ANATURAL:
				setANatural((BigInteger)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY17-Bis
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TestPackage.COLORED_VERTEX__ANATURAL:
				setANatural(DataColoredVertex.ANATURAL_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 *YY18
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TestPackage.COLORED_VERTEX__ANATURAL:
				return DataColoredVertex.ANATURAL_EDEFAULT == null ? getANatural() != null : !DataColoredVertex.ANATURAL_EDEFAULT.equals(getANatural());
		}
		return super.eIsSet(featureID);
	}
	/**
	 * <!-- begin-user-doc -->
	 *YY27
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		if (data != null) result.append(data.toString());
		
		return result.toString();
		}

	/**
	 * @generated
	 */
	public void setDataStrongReferences() {
		if(data != null) {
			
				
			
		}
	}

	/**
	 * @generated
	 */
	public void releaseDataStrongReferences() {
		
			
		
	}
/*
* Neo4EMF inserted code -- begin
*/
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	 @Override
	protected DataColoredVertex getData() {
		if ( data == null || !(data instanceof DataColoredVertex)){
			// TODO check that
			setLoadingOnDemand();
			data = new DataColoredVertex();
			if (isLoaded()) {
				((INeo4emfResource) this.eResource()).fetchAttributes(this);
			}
			unsetLoadingOnDemand();
			}
		return (DataColoredVertex) data;
	}

	/**
	*
	* @generated
	**/
	public void loadAllAttributesFrom(Node n) {
		this.data = new DataColoredVertex(this);
		data.loadAllAttributesFrom(n);
	}
	
	/**
	*
	* @generated
	**/
	public void saveAllAttributesTo(Node n) {
		if (data != null) {
			data.saveAllAttributesTo(n);
		}
	}
	
	/**
	*
	* @generated
	**/
	public void saveAttributeTo(int featureID, Node n) {
		if (data != null) {
			data.saveAttributeTo(featureID, n);
		}
	}
	
/**
*   extends VertexImpl
*  1
*
*/
protected static class DataColoredVertex extends DataVertex{


	/**
	 *Constructor of DataColoredVertex
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataColoredVertex() {
	}


	/**
	 * Constructor of DataColoredVertex
	 * Initializes multi-valued fields, if any.
	 *
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataColoredVertex(ColoredVertexImpl outer) {
		 super(outer); 
	}


    
	/**
	 * The default value of the '{@link #getANatural() <em>ANatural</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getANatural()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger ANATURAL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getANatural() <em>ANatural</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getANatural()
	 * @generated
	 * @ordered
	 */
	protected BigInteger aNatural = ANATURAL_EDEFAULT;


	
		
	/**
	 *Constructor of DataColoredVertex%>
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Vertex }
	 * @generated
	 */
	
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(" (aNatural: ");
		result.append(aNatural);
		result.append(')');
		return result.toString();
	}
		

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void loadAllAttributesFrom(Node n) {
		super.loadAllAttributesFrom(n);
		Object aux;
		aux = n.getProperty("aNatural", null);
		if (aux != null) {
			aNatural = BigInteger.valueOf((long) aux);
		} 
	}


	/**
 	* <!-- begin-user-doc -->
 	* <!-- end-user-doc -->
 	* @generated
 	*/
	public void saveAllAttributesTo(Node n) {
		
		super.saveAllAttributesTo(n);
		
		
		this.saveaNaturalTo(n);
	} // saveTo()
	
	/**
 	* <!-- begin-user-doc -->
 	* <!-- end-user-doc -->
 	* @generated
 	*/
	public void saveAttributeTo(int featureID, Node n) {
		switch (featureID) {
			
			case TestPackage.COLORED_VERTEX__ANATURAL:
				this.saveaNaturalTo(n);
				return;
		} // switch
	} // saveAttributeTo()
	

	/*
	*
	*/
	private void saveaNaturalTo(Node n) {
	
		if (aNatural != null) n.setProperty("aNatural", aNatural.longValue());
	} 
	



}//end attribute class
	
protected static class ColoredVertexReferences  extends DataVertex {
}
/*
* Neo4EMF inserted code -- end
*/




} //ColoredVertexImpl
