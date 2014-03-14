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
package mteach.impl;
 


import fr.inria.atlanmod.neo4emf.change.impl.NewObject;

import mteach.*;

import mteach.util.MteachAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MteachFactoryImpl extends EFactoryImpl implements MteachFactory {

	
	/**
	 * AdapterFactory instance
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	protected MteachAdapterFactory adapterFactory = new MteachAdapterFactory();
	
	
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MteachFactory init() {
		try {
			MteachFactory theMteachFactory = (MteachFactory)EPackage.Registry.INSTANCE.getEFactory("http://mteach/1.0"); 
			if (theMteachFactory != null) {
				return theMteachFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MteachFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MteachFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MteachPackage.PROFESSOR: return (EObject)createProfessor();
			case MteachPackage.COURSE: return (EObject)createCourse();
			case MteachPackage.TOPIC: return (EObject)createTopic();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Professor createProfessor() {
		ProfessorImpl professor = new ProfessorImpl();
		Adapter adapter = adapterFactory.createProfessorAdapter();
		if (adapter != null) {
			professor.eAdapters().add(adapter);
		}
		return professor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Course createCourse() {
		CourseImpl course = new CourseImpl();
		Adapter adapter = adapterFactory.createCourseAdapter();
		if (adapter != null) {
			course.eAdapters().add(adapter);
		}
		return course;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Topic createTopic() {
		TopicImpl topic = new TopicImpl();
		Adapter adapter = adapterFactory.createTopicAdapter();
		if (adapter != null) {
			topic.eAdapters().add(adapter);
		}
		return topic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MteachPackage getMteachPackage() {
		return (MteachPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MteachPackage getPackage() {
		return MteachPackage.eINSTANCE;
	}

} //MteachFactoryImpl
