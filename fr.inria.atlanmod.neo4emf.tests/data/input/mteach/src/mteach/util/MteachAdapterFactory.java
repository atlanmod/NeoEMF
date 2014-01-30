/**
 *
 * $Id$
 */
package mteach.util;


import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import fr.inria.atlanmod.neo4emf.change.impl.*;

import mteach.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see mteach.MteachPackage
 * @generated
 */
public class MteachAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MteachPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MteachAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = MteachPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MteachSwitch<Adapter> modelSwitch =
		new MteachSwitch<Adapter>() {
			@Override
			public Adapter caseProfessor(Professor object) {
				return createProfessorAdapter();
			}
			@Override
			public Adapter caseCourse(Course object) {
				return createCourseAdapter();
			}
			@Override
			public Adapter caseTopic(Topic object) {
				return createTopicAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link mteach.Professor <em>Professor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see mteach.Professor
	 * @generated
	 */
	public Adapter createProfessorAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link mteach.Course <em>Course</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see mteach.Course
	 * @generated
	 */
	public Adapter createCourseAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link mteach.Topic <em>Topic</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see mteach.Topic
	 * @generated
	 */
	public Adapter createTopicAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}
 	
}	
//MteachAdapterFactory