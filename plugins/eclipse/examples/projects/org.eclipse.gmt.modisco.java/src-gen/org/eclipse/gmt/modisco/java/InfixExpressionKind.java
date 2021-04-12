/**
 */
package org.eclipse.gmt.modisco.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Infix Expression Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.gmt.modisco.java.JavaPackage#getInfixExpressionKind()
 * @model
 * @generated
 */
public enum InfixExpressionKind implements Enumerator {
	/**
	 * The '<em><b>TIMES</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIMES_VALUE
	 * @generated
	 * @ordered
	 */
	TIMES(1, "TIMES", "*"),

	/**
	 * The '<em><b>DIVIDE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIVIDE_VALUE
	 * @generated
	 * @ordered
	 */
	DIVIDE(2, "DIVIDE", "/"),

	/**
	 * The '<em><b>REMAINDER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMAINDER_VALUE
	 * @generated
	 * @ordered
	 */
	REMAINDER(3, "REMAINDER", "%"),

	/**
	 * The '<em><b>PLUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLUS_VALUE
	 * @generated
	 * @ordered
	 */
	PLUS(4, "PLUS", "+"),

	/**
	 * The '<em><b>MINUS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MINUS_VALUE
	 * @generated
	 * @ordered
	 */
	MINUS(5, "MINUS", "-"),

	/**
	 * The '<em><b>LEFT SHIFT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LEFT_SHIFT_VALUE
	 * @generated
	 * @ordered
	 */
	LEFT_SHIFT(6, "LEFT_SHIFT", "<<"),

	/**
	 * The '<em><b>RIGHT SHIFT SIGNED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_SIGNED_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT_SHIFT_SIGNED(7, "RIGHT_SHIFT_SIGNED", ">>"),

	/**
	 * The '<em><b>RIGHT SHIFT UNSIGNED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_UNSIGNED_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT_SHIFT_UNSIGNED(8, "RIGHT_SHIFT_UNSIGNED", ">>>"),

	/**
	 * The '<em><b>LESS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_VALUE
	 * @generated
	 * @ordered
	 */
	LESS(9, "LESS", "<"),

	/**
	 * The '<em><b>GREATER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_VALUE
	 * @generated
	 * @ordered
	 */
	GREATER(10, "GREATER", ">"),

	/**
	 * The '<em><b>LESS EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	LESS_EQUALS(11, "LESS_EQUALS", "<="),

	/**
	 * The '<em><b>GREATER EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	GREATER_EQUALS(12, "GREATER_EQUALS", ">="),

	/**
	 * The '<em><b>EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	EQUALS(13, "EQUALS", "=="),

	/**
	 * The '<em><b>NOT EQUALS</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	NOT_EQUALS(14, "NOT_EQUALS", "!="),

	/**
	 * The '<em><b>XOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XOR_VALUE
	 * @generated
	 * @ordered
	 */
	XOR(15, "XOR", "^"),

	/**
	 * The '<em><b>AND</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AND_VALUE
	 * @generated
	 * @ordered
	 */
	AND(16, "AND", "&"),

	/**
	 * The '<em><b>OR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OR_VALUE
	 * @generated
	 * @ordered
	 */
	OR(17, "OR", "|"),

	/**
	 * The '<em><b>CONDITIONAL AND</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL_AND_VALUE
	 * @generated
	 * @ordered
	 */
	CONDITIONAL_AND(18, "CONDITIONAL_AND", "&&"),

	/**
	 * The '<em><b>CONDITIONAL OR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL_OR_VALUE
	 * @generated
	 * @ordered
	 */
	CONDITIONAL_OR(19, "CONDITIONAL_OR", "||");

	/**
	 * The '<em><b>TIMES</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TIMES</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIMES
	 * @model literal="*"
	 * @generated
	 * @ordered
	 */
	public static final int TIMES_VALUE = 1;

	/**
	 * The '<em><b>DIVIDE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DIVIDE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIVIDE
	 * @model literal="/"
	 * @generated
	 * @ordered
	 */
	public static final int DIVIDE_VALUE = 2;

	/**
	 * The '<em><b>REMAINDER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>REMAINDER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REMAINDER
	 * @model literal="%"
	 * @generated
	 * @ordered
	 */
	public static final int REMAINDER_VALUE = 3;

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
	public static final int PLUS_VALUE = 4;

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
	public static final int MINUS_VALUE = 5;

	/**
	 * The '<em><b>LEFT SHIFT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LEFT SHIFT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LEFT_SHIFT
	 * @model literal="&lt;&lt;"
	 * @generated
	 * @ordered
	 */
	public static final int LEFT_SHIFT_VALUE = 6;

	/**
	 * The '<em><b>RIGHT SHIFT SIGNED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RIGHT SHIFT SIGNED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_SIGNED
	 * @model literal="&gt;&gt;"
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_SHIFT_SIGNED_VALUE = 7;

	/**
	 * The '<em><b>RIGHT SHIFT UNSIGNED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RIGHT SHIFT UNSIGNED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_UNSIGNED
	 * @model literal="&gt;&gt;&gt;"
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_SHIFT_UNSIGNED_VALUE = 8;

	/**
	 * The '<em><b>LESS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LESS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LESS
	 * @model literal="&lt;"
	 * @generated
	 * @ordered
	 */
	public static final int LESS_VALUE = 9;

	/**
	 * The '<em><b>GREATER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GREATER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GREATER
	 * @model literal="&gt;"
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_VALUE = 10;

	/**
	 * The '<em><b>LESS EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LESS EQUALS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LESS_EQUALS
	 * @model literal="&lt;="
	 * @generated
	 * @ordered
	 */
	public static final int LESS_EQUALS_VALUE = 11;

	/**
	 * The '<em><b>GREATER EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GREATER EQUALS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GREATER_EQUALS
	 * @model literal="&gt;="
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_EQUALS_VALUE = 12;

	/**
	 * The '<em><b>EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>EQUALS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EQUALS
	 * @model literal="=="
	 * @generated
	 * @ordered
	 */
	public static final int EQUALS_VALUE = 13;

	/**
	 * The '<em><b>NOT EQUALS</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NOT EQUALS</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_EQUALS
	 * @model literal="!="
	 * @generated
	 * @ordered
	 */
	public static final int NOT_EQUALS_VALUE = 14;

	/**
	 * The '<em><b>XOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>XOR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XOR
	 * @model literal="^"
	 * @generated
	 * @ordered
	 */
	public static final int XOR_VALUE = 15;

	/**
	 * The '<em><b>AND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AND</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AND
	 * @model literal="&amp;"
	 * @generated
	 * @ordered
	 */
	public static final int AND_VALUE = 16;

	/**
	 * The '<em><b>OR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OR
	 * @model literal="|"
	 * @generated
	 * @ordered
	 */
	public static final int OR_VALUE = 17;

	/**
	 * The '<em><b>CONDITIONAL AND</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONDITIONAL AND</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL_AND
	 * @model literal="&amp;&amp;"
	 * @generated
	 * @ordered
	 */
	public static final int CONDITIONAL_AND_VALUE = 18;

	/**
	 * The '<em><b>CONDITIONAL OR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONDITIONAL OR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL_OR
	 * @model literal="||"
	 * @generated
	 * @ordered
	 */
	public static final int CONDITIONAL_OR_VALUE = 19;

	/**
	 * An array of all the '<em><b>Infix Expression Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final InfixExpressionKind[] VALUES_ARRAY =
		new InfixExpressionKind[] {
			TIMES,
			DIVIDE,
			REMAINDER,
			PLUS,
			MINUS,
			LEFT_SHIFT,
			RIGHT_SHIFT_SIGNED,
			RIGHT_SHIFT_UNSIGNED,
			LESS,
			GREATER,
			LESS_EQUALS,
			GREATER_EQUALS,
			EQUALS,
			NOT_EQUALS,
			XOR,
			AND,
			OR,
			CONDITIONAL_AND,
			CONDITIONAL_OR,
		};

	/**
	 * A public read-only list of all the '<em><b>Infix Expression Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<InfixExpressionKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Infix Expression Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static InfixExpressionKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			InfixExpressionKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Infix Expression Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static InfixExpressionKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			InfixExpressionKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Infix Expression Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static InfixExpressionKind get(int value) {
		switch (value) {
			case TIMES_VALUE: return TIMES;
			case DIVIDE_VALUE: return DIVIDE;
			case REMAINDER_VALUE: return REMAINDER;
			case PLUS_VALUE: return PLUS;
			case MINUS_VALUE: return MINUS;
			case LEFT_SHIFT_VALUE: return LEFT_SHIFT;
			case RIGHT_SHIFT_SIGNED_VALUE: return RIGHT_SHIFT_SIGNED;
			case RIGHT_SHIFT_UNSIGNED_VALUE: return RIGHT_SHIFT_UNSIGNED;
			case LESS_VALUE: return LESS;
			case GREATER_VALUE: return GREATER;
			case LESS_EQUALS_VALUE: return LESS_EQUALS;
			case GREATER_EQUALS_VALUE: return GREATER_EQUALS;
			case EQUALS_VALUE: return EQUALS;
			case NOT_EQUALS_VALUE: return NOT_EQUALS;
			case XOR_VALUE: return XOR;
			case AND_VALUE: return AND;
			case OR_VALUE: return OR;
			case CONDITIONAL_AND_VALUE: return CONDITIONAL_AND;
			case CONDITIONAL_OR_VALUE: return CONDITIONAL_OR;
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
	private InfixExpressionKind(int value, String name, String literal) {
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
	
} //InfixExpressionKind
