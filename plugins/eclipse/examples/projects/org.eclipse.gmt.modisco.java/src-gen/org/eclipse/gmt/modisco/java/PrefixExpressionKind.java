/**
 */
package org.eclipse.gmt.modisco.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Prefix Expression Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getPrefixExpressionKind()
 * @model
 * @generated
 */
public enum PrefixExpressionKind implements Enumerator {
	/**
	 * The '<em><b>INCREMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INCREMENT_VALUE
	 * @generated
	 * @ordered
	 */
	INCREMENT(1, "INCREMENT", "++"),

	/**
	 * The '<em><b>DECREMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DECREMENT_VALUE
	 * @generated
	 * @ordered
	 */
	DECREMENT(2, "DECREMENT", "--"),

	/**
	 * The '<em><b>PLUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLUS_VALUE
	 * @generated
	 * @ordered
	 */
	PLUS(3, "PLUS", "+"),

	/**
	 * The '<em><b>MINUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MINUS_VALUE
	 * @generated
	 * @ordered
	 */
	MINUS(4, "MINUS", "-"),

	/**
	 * The '<em><b>COMPLEMENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPLEMENT_VALUE
	 * @generated
	 * @ordered
	 */
	COMPLEMENT(5, "COMPLEMENT", "~"),

	/**
	 * The '<em><b>NOT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_VALUE
	 * @generated
	 * @ordered
	 */
	NOT(6, "NOT", "!");

	/**
	 * The '<em><b>INCREMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INCREMENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INCREMENT
	 * @model literal="++"
	 * @generated
	 * @ordered
	 */
	public static final int INCREMENT_VALUE = 1;

	/**
	 * The '<em><b>DECREMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DECREMENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DECREMENT
	 * @model literal="--"
	 * @generated
	 * @ordered
	 */
	public static final int DECREMENT_VALUE = 2;

	/**
	 * The '<em><b>PLUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PLUS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PLUS
	 * @model literal="+"
	 * @generated
	 * @ordered
	 */
	public static final int PLUS_VALUE = 3;

	/**
	 * The '<em><b>MINUS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MINUS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MINUS
	 * @model literal="-"
	 * @generated
	 * @ordered
	 */
	public static final int MINUS_VALUE = 4;

	/**
	 * The '<em><b>COMPLEMENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMPLEMENT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMPLEMENT
	 * @model literal="~"
	 * @generated
	 * @ordered
	 */
	public static final int COMPLEMENT_VALUE = 5;

	/**
	 * The '<em><b>NOT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT
	 * @model literal="!"
	 * @generated
	 * @ordered
	 */
	public static final int NOT_VALUE = 6;

	/**
	 * An array of all the '<em><b>Prefix Expression Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PrefixExpressionKind[] VALUES_ARRAY =
		new PrefixExpressionKind[] {
			INCREMENT,
			DECREMENT,
			PLUS,
			MINUS,
			COMPLEMENT,
			NOT,
		};

	/**
	 * A public read-only list of all the '<em><b>Prefix Expression Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PrefixExpressionKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Prefix Expression Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PrefixExpressionKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PrefixExpressionKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Prefix Expression Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PrefixExpressionKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PrefixExpressionKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Prefix Expression Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static PrefixExpressionKind get(int value) {
		switch (value) {
			case INCREMENT_VALUE: return INCREMENT;
			case DECREMENT_VALUE: return DECREMENT;
			case PLUS_VALUE: return PLUS;
			case MINUS_VALUE: return MINUS;
			case COMPLEMENT_VALUE: return COMPLEMENT;
			case NOT_VALUE: return NOT;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private PrefixExpressionKind(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //PrefixExpressionKind
