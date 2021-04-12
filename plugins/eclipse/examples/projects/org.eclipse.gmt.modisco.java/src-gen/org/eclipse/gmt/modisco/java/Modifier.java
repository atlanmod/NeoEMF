/**
 */
package org.eclipse.gmt.modisco.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Modifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#getInheritance <em>Inheritance</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#isStatic <em>Static</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#isTransient <em>Transient</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#isVolatile <em>Volatile</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#isNative <em>Native</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#isStrictfp <em>Strictfp</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#isSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#getBodyDeclaration <em>Body Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#getSingleVariableDeclaration <em>Single Variable Declaration</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#getVariableDeclarationStatement <em>Variable Declaration Statement</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.Modifier#getVariableDeclarationExpression <em>Variable Declaration Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier()
 * @model
 * @generated
 */
public interface Modifier extends ASTNode {
	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.gmt.modisco.java.VisibilityKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' attribute.
	 * @see org.eclipse.gmt.modisco.java.VisibilityKind
	 * @see #setVisibility(VisibilityKind)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_Visibility()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	VisibilityKind getVisibility();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#getVisibility <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' attribute.
	 * @see org.eclipse.gmt.modisco.java.VisibilityKind
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(VisibilityKind value);

	/**
	 * Returns the value of the '<em><b>Inheritance</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.gmt.modisco.java.InheritanceKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inheritance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inheritance</em>' attribute.
	 * @see org.eclipse.gmt.modisco.java.InheritanceKind
	 * @see #setInheritance(InheritanceKind)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_Inheritance()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	InheritanceKind getInheritance();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#getInheritance <em>Inheritance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inheritance</em>' attribute.
	 * @see org.eclipse.gmt.modisco.java.InheritanceKind
	 * @see #getInheritance()
	 * @generated
	 */
	void setInheritance(InheritanceKind value);

	/**
	 * Returns the value of the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Static</em>' attribute.
	 * @see #setStatic(boolean)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_Static()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isStatic();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#isStatic <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Static</em>' attribute.
	 * @see #isStatic()
	 * @generated
	 */
	void setStatic(boolean value);

	/**
	 * Returns the value of the '<em><b>Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transient</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transient</em>' attribute.
	 * @see #setTransient(boolean)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_Transient()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isTransient();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#isTransient <em>Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transient</em>' attribute.
	 * @see #isTransient()
	 * @generated
	 */
	void setTransient(boolean value);

	/**
	 * Returns the value of the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Volatile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Volatile</em>' attribute.
	 * @see #setVolatile(boolean)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_Volatile()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isVolatile();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#isVolatile <em>Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Volatile</em>' attribute.
	 * @see #isVolatile()
	 * @generated
	 */
	void setVolatile(boolean value);

	/**
	 * Returns the value of the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Native</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Native</em>' attribute.
	 * @see #setNative(boolean)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_Native()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isNative();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#isNative <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Native</em>' attribute.
	 * @see #isNative()
	 * @generated
	 */
	void setNative(boolean value);

	/**
	 * Returns the value of the '<em><b>Strictfp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strictfp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strictfp</em>' attribute.
	 * @see #setStrictfp(boolean)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_Strictfp()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isStrictfp();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#isStrictfp <em>Strictfp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strictfp</em>' attribute.
	 * @see #isStrictfp()
	 * @generated
	 */
	void setStrictfp(boolean value);

	/**
	 * Returns the value of the '<em><b>Synchronized</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Synchronized</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Synchronized</em>' attribute.
	 * @see #setSynchronized(boolean)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_Synchronized()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isSynchronized();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#isSynchronized <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synchronized</em>' attribute.
	 * @see #isSynchronized()
	 * @generated
	 */
	void setSynchronized(boolean value);

	/**
	 * Returns the value of the '<em><b>Body Declaration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.BodyDeclaration#getModifier <em>Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body Declaration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body Declaration</em>' container reference.
	 * @see #setBodyDeclaration(BodyDeclaration)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_BodyDeclaration()
	 * @see org.eclipse.gmt.modisco.java.BodyDeclaration#getModifier
	 * @model opposite="modifier" transient="false" ordered="false"
	 * @generated
	 */
	BodyDeclaration getBodyDeclaration();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#getBodyDeclaration <em>Body Declaration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body Declaration</em>' container reference.
	 * @see #getBodyDeclaration()
	 * @generated
	 */
	void setBodyDeclaration(BodyDeclaration value);

	/**
	 * Returns the value of the '<em><b>Single Variable Declaration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.SingleVariableDeclaration#getModifier <em>Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Single Variable Declaration</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Single Variable Declaration</em>' container reference.
	 * @see #setSingleVariableDeclaration(SingleVariableDeclaration)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_SingleVariableDeclaration()
	 * @see org.eclipse.gmt.modisco.java.SingleVariableDeclaration#getModifier
	 * @model opposite="modifier" transient="false" ordered="false"
	 * @generated
	 */
	SingleVariableDeclaration getSingleVariableDeclaration();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#getSingleVariableDeclaration <em>Single Variable Declaration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Single Variable Declaration</em>' container reference.
	 * @see #getSingleVariableDeclaration()
	 * @generated
	 */
	void setSingleVariableDeclaration(SingleVariableDeclaration value);

	/**
	 * Returns the value of the '<em><b>Variable Declaration Statement</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.VariableDeclarationStatement#getModifier <em>Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Declaration Statement</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Declaration Statement</em>' container reference.
	 * @see #setVariableDeclarationStatement(VariableDeclarationStatement)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_VariableDeclarationStatement()
	 * @see org.eclipse.gmt.modisco.java.VariableDeclarationStatement#getModifier
	 * @model opposite="modifier" transient="false" ordered="false"
	 * @generated
	 */
	VariableDeclarationStatement getVariableDeclarationStatement();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#getVariableDeclarationStatement <em>Variable Declaration Statement</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Declaration Statement</em>' container reference.
	 * @see #getVariableDeclarationStatement()
	 * @generated
	 */
	void setVariableDeclarationStatement(VariableDeclarationStatement value);

	/**
	 * Returns the value of the '<em><b>Variable Declaration Expression</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.gmt.modisco.java.VariableDeclarationExpression#getModifier <em>Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Declaration Expression</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Declaration Expression</em>' container reference.
	 * @see #setVariableDeclarationExpression(VariableDeclarationExpression)
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#getModifier_VariableDeclarationExpression()
	 * @see org.eclipse.gmt.modisco.java.VariableDeclarationExpression#getModifier
	 * @model opposite="modifier" transient="false" ordered="false"
	 * @generated
	 */
	VariableDeclarationExpression getVariableDeclarationExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.gmt.modisco.java.Modifier#getVariableDeclarationExpression <em>Variable Declaration Expression</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Declaration Expression</em>' container reference.
	 * @see #getVariableDeclarationExpression()
	 * @generated
	 */
	void setVariableDeclarationExpression(VariableDeclarationExpression value);

} // Modifier
