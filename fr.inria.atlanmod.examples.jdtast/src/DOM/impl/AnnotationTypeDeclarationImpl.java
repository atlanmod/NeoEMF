/**
 *
 * $Id$
 */
package DOM.impl;

import DOM.AnnotationTypeDeclaration;
import DOM.DOMPackage;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotation Type Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class AnnotationTypeDeclarationImpl extends AbstractTypeDeclarationImpl implements AnnotationTypeDeclaration {

	 
	//fr.inria.atlanmod.neo4emf.impl.Neo4emfObject	 
	//AbstractTypeDeclarationImpl
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnnotationTypeDeclarationImpl() {
		super();
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DOMPackage.Literals.ANNOTATION_TYPE_DECLARATION;
	}




// data Class generation 
protected static  class DataAnnotationTypeDeclaration extends DataAbstractTypeDeclaration {


	/**
	 *Constructor of DataAnnotationTypeDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataAnnotationTypeDeclaration() {
		super();
	}
	
		
	/**
	 *Constructor of DataAnnotationTypeDeclaration
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param {@link AbstractTypeDeclaration }
	 * @generated
	 */
	public DataAnnotationTypeDeclaration(DataAbstractTypeDeclaration data) {
		super();		
		
		bodyDeclarations = data.bodyDeclarations;
				
		name = data.name;
				
		localTypeDeclaration = data.localTypeDeclaration;
				
		memberTypeDeclaration = data.memberTypeDeclaration;
				
		packageMemberTypeDeclaration = data.packageMemberTypeDeclaration;
				
				
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
} //AnnotationTypeDeclarationImpl
