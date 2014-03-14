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
package fr.inria.atlanmod.neo4emf.testdata;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vertex</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getVname <em>Vname</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getATransientInteger <em>ATransient Integer</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getAVolatileDate <em>AVolatile Date</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#isABoolean <em>ABoolean</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getAStringArray <em>AString Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getANonUniqueArray <em>ANon Unique Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getANonOrderedArray <em>ANon Ordered Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getATenStringArray <em>ATen String Array</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getTemperature <em>Temperature</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getVcontainer <em>Vcontainer</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getFrom <em>From</em>}</li>
 *   <li>{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex()
 * @model
 * @extends INeo4emfObject
 * @generated
 */
public interface Vertex extends INeo4emfObject {

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Vname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Vname</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vname</em>' attribute.
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_Vname()
	 * @model required="true" changeable="false"
	 * @generated
	 */
	String getVname(); 

/**
	 * Returns the value of the '<em><b>ATransient Integer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>ATransient Integer</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ATransient Integer</em>' attribute.
	 * @see #setATransientInteger(BigInteger)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_ATransientInteger()
	 * @model required="true" transient="true"
	 * @generated
	 */
	BigInteger getATransientInteger();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getATransientInteger <em>ATransient Integer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ATransient Integer</em>' attribute.
	 * @see #getATransientInteger()
	 * @generated
	 */
	void setATransientInteger(BigInteger value);

	/**
	 * Returns the value of the '<em><b>AVolatile Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>AVolatile Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AVolatile Date</em>' attribute.
	 * @see #setAVolatileDate(Date)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_AVolatileDate()
	 * @model required="true" volatile="true"
	 * @generated
	 */
	Date getAVolatileDate();

	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getAVolatileDate <em>AVolatile Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>AVolatile Date</em>' attribute.
	 * @see #getAVolatileDate()
	 * @generated
	 */
	void setAVolatileDate(Date value);

	/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>ABoolean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>ABoolean</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ABoolean</em>' attribute.
	 * @see #setABoolean(boolean)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_ABoolean()
	 * @model required="true"
	 * @generated
	 */
	boolean isABoolean();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#isABoolean <em>ABoolean</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ABoolean</em>' attribute.
	 * @see #isABoolean()
	 * @generated
	 */
	void setABoolean(boolean value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>AString Array</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>AString Array</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AString Array</em>' attribute list.
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_AStringArray()
	 * @model ordered="false"
	 * @generated
	 */
	EList<String> getAStringArray(); 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>ANon Unique Array</b></em>' attribute list.
	 * The list contents are of type {@link java.math.BigDecimal}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>ANon Unique Array</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ANon Unique Array</em>' attribute list.
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_ANonUniqueArray()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	EList<BigDecimal> getANonUniqueArray(); 

/**
	 * Returns the value of the '<em><b>ANon Ordered Array</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Boolean}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>ANon Ordered Array</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ANon Ordered Array</em>' attribute list.
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_ANonOrderedArray()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Boolean> getANonOrderedArray();

	/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>ATen String Array</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>ATen String Array</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ATen String Array</em>' attribute list.
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_ATenStringArray()
	 * @model lower="10" upper="10" ordered="false"
	 * @generated
	 */
	EList<String> getATenStringArray(); 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Temperature</b></em>' attribute.
	 * The literals are from the enumeration {@link fr.inria.atlanmod.neo4emf.testdata.Temperature}.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Temperature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Temperature</em>' attribute.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Temperature
	 * @see #setTemperature(Temperature)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_Temperature()
	 * @model required="true" transient="true"
	 * @generated
	 */
	Temperature getTemperature();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getTemperature <em>Temperature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Temperature</em>' attribute.
	 * @see fr.inria.atlanmod.neo4emf.testdata.Temperature
	 * @see #getTemperature()
	 * @generated
	 */
	void setTemperature(Temperature value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>Vcontainer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neo4emf.testdata.Container#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>Vcontainer</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vcontainer</em>' container reference.
	 * @see #setVcontainer(Container)
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_Vcontainer()
	 * @see fr.inria.atlanmod.neo4emf.testdata.Container#getNodes
	 * @model opposite="nodes" required="true" transient="false"
	 * @generated
	 */
	Container getVcontainer();
	/**
	 * Sets the value of the '{@link fr.inria.atlanmod.neo4emf.testdata.Vertex#getVcontainer <em>Vcontainer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 *YY1-BIS
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vcontainer</em>' container reference.
	 * @see #getVcontainer()
	 * @generated
	 */
	void setVcontainer(Container value);
 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.neo4emf.testdata.Link}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getOutGoing <em>Out Going</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>From</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference list.
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_From()
	 * @see fr.inria.atlanmod.neo4emf.testdata.Link#getOutGoing
	 * @model opposite="outGoing"
	 * @generated
	 */
	EList<Link> getFrom(); 

/** genFeaure.override.javajetinc **/
	/**
	 * Returns the value of the '<em><b>To</b></em>' reference list.
	 * The list contents are of type {@link fr.inria.atlanmod.neo4emf.testdata.Link}.
	 * It is bidirectional and its opposite is '{@link fr.inria.atlanmod.neo4emf.testdata.Link#getInComing <em>In Coming</em>}'.
	 * <!-- begin-user-doc -->
	 *XX6a
	 * <p>
	 * If the meaning of the '<em>To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference list.
	 * @see fr.inria.atlanmod.neo4emf.testdata.TestPackage#getVertex_To()
	 * @see fr.inria.atlanmod.neo4emf.testdata.Link#getInComing
	 * @model opposite="inComing"
	 * @generated
	 */
	EList<Link> getTo(); 


/*
* Neo4EMF inserted code -- begin
*/



/*
* Neo4EMF inserted code -- end
*/




} // Vertex
