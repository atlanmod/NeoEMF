/**
 *
 * $Id$
 */
package DOM;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Infix Expression Operator Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see DOM.DOMPackage#getInfixExpressionOperatorKind()
 * @model
 * @generated
 */
public enum InfixExpressionOperatorKind implements Enumerator {
	/**
	 * The '<em><b>Greater equals</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	GREATER_EQUALS(1, "greater_equals", ">="),

	/**
	 * The '<em><b>Or</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OR_VALUE
	 * @generated
	 * @ordered
	 */
	OR(2, "or", "|"),

	/**
	 * The '<em><b>Right shift signed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_SIGNED_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT_SHIFT_SIGNED(3, "right_shift_signed", ">>"),

	/**
	 * The '<em><b>Minus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MINUS_VALUE
	 * @generated
	 * @ordered
	 */
	MINUS(4, "minus", "-"),

	/**
	 * The '<em><b>Xor</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XOR_VALUE
	 * @generated
	 * @ordered
	 */
	XOR(5, "xor", "^"),

	/**
	 * The '<em><b>Less equals</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	LESS_EQUALS(6, "less_equals", "<="),

	/**
	 * The '<em><b>Equals</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	EQUALS(7, "equals", "=="),

	/**
	 * The '<em><b>Not equals</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_EQUALS_VALUE
	 * @generated
	 * @ordered
	 */
	NOT_EQUALS(8, "not_equals", "!="),

	/**
	 * The '<em><b>And</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AND_VALUE
	 * @generated
	 * @ordered
	 */
	AND(9, "and", "&"),

	/**
	 * The '<em><b>Plus</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLUS_VALUE
	 * @generated
	 * @ordered
	 */
	PLUS(10, "plus", "+"),

	/**
	 * The '<em><b>Greater</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GREATER_VALUE
	 * @generated
	 * @ordered
	 */
	GREATER(11, "greater", ">"),

	/**
	 * The '<em><b>Conditional or</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL_OR_VALUE
	 * @generated
	 * @ordered
	 */
	CONDITIONAL_OR(12, "conditional_or", "||"),

	/**
	 * The '<em><b>Remainder</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMAINDER_VALUE
	 * @generated
	 * @ordered
	 */
	REMAINDER(13, "remainder", "%"),

	/**
	 * The '<em><b>Less</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LESS_VALUE
	 * @generated
	 * @ordered
	 */
	LESS(14, "less", "<"),

	/**
	 * The '<em><b>Left shift</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LEFT_SHIFT_VALUE
	 * @generated
	 * @ordered
	 */
	LEFT_SHIFT(15, "left_shift", "<<"),

	/**
	 * The '<em><b>Right shift unsigned</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_UNSIGNED_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT_SHIFT_UNSIGNED(16, "right_shift_unsigned", ">>>"),

	/**
	 * The '<em><b>Conditional and</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL_AND_VALUE
	 * @generated
	 * @ordered
	 */
	CONDITIONAL_AND(17, "conditional_and", "&&"),

	/**
	 * The '<em><b>Times</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIMES_VALUE
	 * @generated
	 * @ordered
	 */
	TIMES(18, "times", "*"),

	/**
	 * The '<em><b>Divide</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIVIDE_VALUE
	 * @generated
	 * @ordered
	 */
	DIVIDE(19, "divide", "/");

	/**
	 * The '<em><b>Greater equals</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Greater equals</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GREATER_EQUALS
	 * @model name="greater_equals" literal=">="
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_EQUALS_VALUE = 1;

	/**
	 * The '<em><b>Or</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Or</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OR
	 * @model name="or" literal="|"
	 * @generated
	 * @ordered
	 */
	public static final int OR_VALUE = 2;

	/**
	 * The '<em><b>Right shift signed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Right shift signed</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_SIGNED
	 * @model name="right_shift_signed" literal=">>"
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_SHIFT_SIGNED_VALUE = 3;

	/**
	 * The '<em><b>Minus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Minus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MINUS
	 * @model name="minus" literal="-"
	 * @generated
	 * @ordered
	 */
	public static final int MINUS_VALUE = 4;

	/**
	 * The '<em><b>Xor</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xor</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XOR
	 * @model name="xor" literal="^"
	 * @generated
	 * @ordered
	 */
	public static final int XOR_VALUE = 5;

	/**
	 * The '<em><b>Less equals</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Less equals</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LESS_EQUALS
	 * @model name="less_equals" literal="<="
	 * @generated
	 * @ordered
	 */
	public static final int LESS_EQUALS_VALUE = 6;

	/**
	 * The '<em><b>Equals</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Equals</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EQUALS
	 * @model name="equals" literal="=="
	 * @generated
	 * @ordered
	 */
	public static final int EQUALS_VALUE = 7;

	/**
	 * The '<em><b>Not equals</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Not equals</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_EQUALS
	 * @model name="not_equals" literal="!="
	 * @generated
	 * @ordered
	 */
	public static final int NOT_EQUALS_VALUE = 8;

	/**
	 * The '<em><b>And</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>And</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AND
	 * @model name="and" literal="&"
	 * @generated
	 * @ordered
	 */
	public static final int AND_VALUE = 9;

	/**
	 * The '<em><b>Plus</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Plus</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PLUS
	 * @model name="plus" literal="+"
	 * @generated
	 * @ordered
	 */
	public static final int PLUS_VALUE = 10;

	/**
	 * The '<em><b>Greater</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Greater</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #GREATER
	 * @model name="greater" literal=">"
	 * @generated
	 * @ordered
	 */
	public static final int GREATER_VALUE = 11;

	/**
	 * The '<em><b>Conditional or</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Conditional or</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL_OR
	 * @model name="conditional_or" literal="||"
	 * @generated
	 * @ordered
	 */
	public static final int CONDITIONAL_OR_VALUE = 12;

	/**
	 * The '<em><b>Remainder</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Remainder</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REMAINDER
	 * @model name="remainder" literal="%"
	 * @generated
	 * @ordered
	 */
	public static final int REMAINDER_VALUE = 13;

	/**
	 * The '<em><b>Less</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Less</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LESS
	 * @model name="less" literal="<"
	 * @generated
	 * @ordered
	 */
	public static final int LESS_VALUE = 14;

	/**
	 * The '<em><b>Left shift</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Left shift</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LEFT_SHIFT
	 * @model name="left_shift" literal="<<"
	 * @generated
	 * @ordered
	 */
	public static final int LEFT_SHIFT_VALUE = 15;

	/**
	 * The '<em><b>Right shift unsigned</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Right shift unsigned</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_UNSIGNED
	 * @model name="right_shift_unsigned" literal=">>>"
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_SHIFT_UNSIGNED_VALUE = 16;

	/**
	 * The '<em><b>Conditional and</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Conditional and</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONDITIONAL_AND
	 * @model name="conditional_and" literal="&&"
	 * @generated
	 * @ordered
	 */
	public static final int CONDITIONAL_AND_VALUE = 17;

	/**
	 * The '<em><b>Times</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Times</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIMES
	 * @model name="times" literal="*"
	 * @generated
	 * @ordered
	 */
	public static final int TIMES_VALUE = 18;

	/**
	 * The '<em><b>Divide</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Divide</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIVIDE
	 * @model name="divide" literal="/"
	 * @generated
	 * @ordered
	 */
	public static final int DIVIDE_VALUE = 19;

	/**
	 * An array of all the '<em><b>Infix Expression Operator Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final InfixExpressionOperatorKind[] VALUES_ARRAY =
		new InfixExpressionOperatorKind[] {
			GREATER_EQUALS,
			OR,
			RIGHT_SHIFT_SIGNED,
			MINUS,
			XOR,
			LESS_EQUALS,
			EQUALS,
			NOT_EQUALS,
			AND,
			PLUS,
			GREATER,
			CONDITIONAL_OR,
			REMAINDER,
			LESS,
			LEFT_SHIFT,
			RIGHT_SHIFT_UNSIGNED,
			CONDITIONAL_AND,
			TIMES,
			DIVIDE,
		};

	/**
	 * A public read-only list of all the '<em><b>Infix Expression Operator Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<InfixExpressionOperatorKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Infix Expression Operator Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static InfixExpressionOperatorKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			InfixExpressionOperatorKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Infix Expression Operator Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static InfixExpressionOperatorKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			InfixExpressionOperatorKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Infix Expression Operator Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static InfixExpressionOperatorKind get(int value) {
		switch (value) {
			case GREATER_EQUALS_VALUE: return GREATER_EQUALS;
			case OR_VALUE: return OR;
			case RIGHT_SHIFT_SIGNED_VALUE: return RIGHT_SHIFT_SIGNED;
			case MINUS_VALUE: return MINUS;
			case XOR_VALUE: return XOR;
			case LESS_EQUALS_VALUE: return LESS_EQUALS;
			case EQUALS_VALUE: return EQUALS;
			case NOT_EQUALS_VALUE: return NOT_EQUALS;
			case AND_VALUE: return AND;
			case PLUS_VALUE: return PLUS;
			case GREATER_VALUE: return GREATER;
			case CONDITIONAL_OR_VALUE: return CONDITIONAL_OR;
			case REMAINDER_VALUE: return REMAINDER;
			case LESS_VALUE: return LESS;
			case LEFT_SHIFT_VALUE: return LEFT_SHIFT;
			case RIGHT_SHIFT_UNSIGNED_VALUE: return RIGHT_SHIFT_UNSIGNED;
			case CONDITIONAL_AND_VALUE: return CONDITIONAL_AND;
			case TIMES_VALUE: return TIMES;
			case DIVIDE_VALUE: return DIVIDE;
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
	private InfixExpressionOperatorKind(int value, String name, String literal) {
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
	
} //InfixExpressionOperatorKind
