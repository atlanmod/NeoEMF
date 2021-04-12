/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.ArrayType;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ArrayTypeImpl#getDimensions <em>Dimensions</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.impl.ArrayTypeImpl#getElementType <em>Element Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArrayTypeImpl extends TypeImpl implements ArrayType {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getArrayType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDimensions() {
		return (Integer)eGet(JavaPackage.eINSTANCE.getArrayType_Dimensions(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDimensions(int newDimensions) {
		eSet(JavaPackage.eINSTANCE.getArrayType_Dimensions(), newDimensions);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getElementType() {
		return (TypeAccess)eGet(JavaPackage.eINSTANCE.getArrayType_ElementType(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementType(TypeAccess newElementType) {
		eSet(JavaPackage.eINSTANCE.getArrayType_ElementType(), newElementType);
	}

} //ArrayTypeImpl
