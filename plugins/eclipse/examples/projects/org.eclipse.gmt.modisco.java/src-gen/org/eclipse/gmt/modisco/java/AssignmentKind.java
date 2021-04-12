/**
 */
package org.eclipse.gmt.modisco.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Assignment Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getAssignmentKind()
 * @model
 * @generated
 */
public enum AssignmentKind implements Enumerator {
	/**
	 * The '<em><b>ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	ASSIGN(1, "ASSIGN", "="),

	/**
	 * The '<em><b>PLUS ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLUS_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	PLUS_ASSIGN(2, "PLUS_ASSIGN", "+="),

	/**
	 * The '<em><b>MINUS ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MINUS_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	MINUS_ASSIGN(3, "MINUS_ASSIGN", "-="),

	/**
	 * The '<em><b>TIMES ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIMES_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	TIMES_ASSIGN(4, "TIMES_ASSIGN", "*="),

	/**
	 * The '<em><b>DIVIDE ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIVIDE_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	DIVIDE_ASSIGN(5, "DIVIDE_ASSIGN", "/="),

	/**
	 * The '<em><b>BIT AND ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BIT_AND_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	BIT_AND_ASSIGN(6, "BIT_AND_ASSIGN", "&="),

	/**
	 * The '<em><b>BIT OR ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BIT_OR_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	BIT_OR_ASSIGN(7, "BIT_OR_ASSIGN", "|="),

	/**
	 * The '<em><b>BIT XOR ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BIT_XOR_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	BIT_XOR_ASSIGN(8, "BIT_XOR_ASSIGN", "^="),

	/**
	 * The '<em><b>REMAINDER ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMAINDER_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	REMAINDER_ASSIGN(9, "REMAINDER_ASSIGN", "%="),

	/**
	 * The '<em><b>LEFT SHIFT ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LEFT_SHIFT_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	LEFT_SHIFT_ASSIGN(10, "LEFT_SHIFT_ASSIGN", "<<="),

	/**
	 * The '<em><b>RIGHT SHIFT SIGNED ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_SIGNED_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT_SHIFT_SIGNED_ASSIGN(11, "RIGHT_SHIFT_SIGNED_ASSIGN", ">>="),

	/**
	 * The '<em><b>RIGHT SHIFT UNSIGNED ASSIGN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_UNSIGNED_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT_SHIFT_UNSIGNED_ASSIGN(12, "RIGHT_SHIFT_UNSIGNED_ASSIGN", ">>>=");

	/**
	 * The '<em><b>ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ASSIGN
	 * @model literal="="
	 * @generated
	 * @ordered
	 */
	public static final int ASSIGN_VALUE = 1;

	/**
	 * The '<em><b>PLUS ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>PLUS ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PLUS_ASSIGN
	 * @model literal="+="
	 * @generated
	 * @ordered
	 */
	public static final int PLUS_ASSIGN_VALUE = 2;

	/**
	 * The '<em><b>MINUS ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MINUS ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MINUS_ASSIGN
	 * @model literal="-="
	 * @generated
	 * @ordered
	 */
	public static final int MINUS_ASSIGN_VALUE = 3;

	/**
	 * The '<em><b>TIMES ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TIMES ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIMES_ASSIGN
	 * @model literal="*="
	 * @generated
	 * @ordered
	 */
	public static final int TIMES_ASSIGN_VALUE = 4;

	/**
	 * The '<em><b>DIVIDE ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIVIDE ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIVIDE_ASSIGN
	 * @model literal="/="
	 * @generated
	 * @ordered
	 */
	public static final int DIVIDE_ASSIGN_VALUE = 5;

	/**
	 * The '<em><b>BIT AND ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BIT AND ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BIT_AND_ASSIGN
	 * @model literal="&amp;="
	 * @generated
	 * @ordered
	 */
	public static final int BIT_AND_ASSIGN_VALUE = 6;

	/**
	 * The '<em><b>BIT OR ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BIT OR ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BIT_OR_ASSIGN
	 * @model literal="|="
	 * @generated
	 * @ordered
	 */
	public static final int BIT_OR_ASSIGN_VALUE = 7;

	/**
	 * The '<em><b>BIT XOR ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BIT XOR ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BIT_XOR_ASSIGN
	 * @model literal="^="
	 * @generated
	 * @ordered
	 */
	public static final int BIT_XOR_ASSIGN_VALUE = 8;

	/**
	 * The '<em><b>REMAINDER ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REMAINDER ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REMAINDER_ASSIGN
	 * @model literal="%="
	 * @generated
	 * @ordered
	 */
	public static final int REMAINDER_ASSIGN_VALUE = 9;

	/**
	 * The '<em><b>LEFT SHIFT ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LEFT SHIFT ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LEFT_SHIFT_ASSIGN
	 * @model literal="&lt;&lt;="
	 * @generated
	 * @ordered
	 */
	public static final int LEFT_SHIFT_ASSIGN_VALUE = 10;

	/**
	 * The '<em><b>RIGHT SHIFT SIGNED ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RIGHT SHIFT SIGNED ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_SIGNED_ASSIGN
	 * @model literal="&gt;&gt;="
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_SHIFT_SIGNED_ASSIGN_VALUE = 11;

	/**
	 * The '<em><b>RIGHT SHIFT UNSIGNED ASSIGN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RIGHT SHIFT UNSIGNED ASSIGN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_UNSIGNED_ASSIGN
	 * @model literal="&gt;&gt;&gt;="
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_SHIFT_UNSIGNED_ASSIGN_VALUE = 12;

	/**
	 * An array of all the '<em><b>Assignment Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AssignmentKind[] VALUES_ARRAY =
		new AssignmentKind[] {
			ASSIGN,
			PLUS_ASSIGN,
			MINUS_ASSIGN,
			TIMES_ASSIGN,
			DIVIDE_ASSIGN,
			BIT_AND_ASSIGN,
			BIT_OR_ASSIGN,
			BIT_XOR_ASSIGN,
			REMAINDER_ASSIGN,
			LEFT_SHIFT_ASSIGN,
			RIGHT_SHIFT_SIGNED_ASSIGN,
			RIGHT_SHIFT_UNSIGNED_ASSIGN,
		};

	/**
	 * A public read-only list of all the '<em><b>Assignment Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AssignmentKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Assignment Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AssignmentKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AssignmentKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Assignment Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AssignmentKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AssignmentKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Assignment Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AssignmentKind get(int value) {
		switch (value) {
			case ASSIGN_VALUE: return ASSIGN;
			case PLUS_ASSIGN_VALUE: return PLUS_ASSIGN;
			case MINUS_ASSIGN_VALUE: return MINUS_ASSIGN;
			case TIMES_ASSIGN_VALUE: return TIMES_ASSIGN;
			case DIVIDE_ASSIGN_VALUE: return DIVIDE_ASSIGN;
			case BIT_AND_ASSIGN_VALUE: return BIT_AND_ASSIGN;
			case BIT_OR_ASSIGN_VALUE: return BIT_OR_ASSIGN;
			case BIT_XOR_ASSIGN_VALUE: return BIT_XOR_ASSIGN;
			case REMAINDER_ASSIGN_VALUE: return REMAINDER_ASSIGN;
			case LEFT_SHIFT_ASSIGN_VALUE: return LEFT_SHIFT_ASSIGN;
			case RIGHT_SHIFT_SIGNED_ASSIGN_VALUE: return RIGHT_SHIFT_SIGNED_ASSIGN;
			case RIGHT_SHIFT_UNSIGNED_ASSIGN_VALUE: return RIGHT_SHIFT_UNSIGNED_ASSIGN;
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
	private AssignmentKind(int value, String name, String literal) {
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
	
} //AssignmentKind
