/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Modifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Body Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.BodyDeclarationImpl#getAbstractTypeDeclaration <em>Abstract Type Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.BodyDeclarationImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.BodyDeclarationImpl#getAnonymousClassDeclarationOwner <em>Anonymous Class Declaration Owner</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.BodyDeclarationImpl#getModifier <em>Modifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class BodyDeclarationImpl extends NamedElementImpl implements BodyDeclaration {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BodyDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getBodyDeclaration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbstractTypeDeclaration getAbstractTypeDeclaration() {
		return (AbstractTypeDeclaration)eGet(JavaPackage.eINSTANCE.getBodyDeclaration_AbstractTypeDeclaration(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbstractTypeDeclaration(AbstractTypeDeclaration newAbstractTypeDeclaration) {
		eSet(JavaPackage.eINSTANCE.getBodyDeclaration_AbstractTypeDeclaration(), newAbstractTypeDeclaration);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<Annotation> getAnnotations() {
		return (EList<Annotation>)eGet(JavaPackage.eINSTANCE.getBodyDeclaration_Annotations(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnonymousClassDeclaration getAnonymousClassDeclarationOwner() {
		return (AnonymousClassDeclaration)eGet(JavaPackage.eINSTANCE.getBodyDeclaration_AnonymousClassDeclarationOwner(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnonymousClassDeclarationOwner(AnonymousClassDeclaration newAnonymousClassDeclarationOwner) {
		eSet(JavaPackage.eINSTANCE.getBodyDeclaration_AnonymousClassDeclarationOwner(), newAnonymousClassDeclarationOwner);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Modifier getModifier() {
		return (Modifier)eGet(JavaPackage.eINSTANCE.getBodyDeclaration_Modifier(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifier(Modifier newModifier) {
		eSet(JavaPackage.eINSTANCE.getBodyDeclaration_Modifier(), newModifier);
	}

} //BodyDeclarationImpl
