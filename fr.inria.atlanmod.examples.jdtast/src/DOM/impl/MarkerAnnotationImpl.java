/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.DOMPackage;
import DOM.MarkerAnnotation;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Marker Annotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class MarkerAnnotationImpl extends AnnotationImpl implements MarkerAnnotation {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//AnnotationImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MarkerAnnotationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.MARKER_ANNOTATION;
	}




// data Class generation 
protected static  class DataMarkerAnnotation extends DataAnnotation {


	/**
	 *Constructor of DataMarkerAnnotation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataMarkerAnnotation() {
		super();
	}
	
		
	/**
	 *Constructor of DataMarkerAnnotation
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link Annotation }
	 * @generated
	 */
	public DataMarkerAnnotation(DataAnnotation data) {
		super();		
		
		typeName = data.typeName;
				
				
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString(){	
		StringBuffer result = new StringBuffer(super.toString());		
		result.append(')');
		return result.toString();
	}
		
}
} //MarkerAnnotationImpl
