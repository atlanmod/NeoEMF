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
 * A representation of the literals of the enumeration '<em><b>Assignment Operator Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see DOM.DOMPackage#getAssignmentOperatorKind()
 * @model
 * @generated
 */
public enum AssignmentOperatorKind implements Enumerator {
	/**
	 * The '<em><b>Right shift signed assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_SIGNED_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT_SHIFT_SIGNED_ASSIGN(1, "right_shift_signed_assign", ">>="),

	/**
	 * The '<em><b>Bit xor assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BIT_XOR_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	BIT_XOR_ASSIGN(2, "bit_xor_assign", "^="),

	/**
	 * The '<em><b>Times assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIMES_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	TIMES_ASSIGN(3, "times_assign", "*="),

	/**
	 * The '<em><b>Divide assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DIVIDE_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	DIVIDE_ASSIGN(4, "divide_assign", "/="),

	/**
	 * The '<em><b>Minus assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MINUS_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	MINUS_ASSIGN(5, "minus_assign", "-="),

	/**
	 * The '<em><b>Bit or assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BIT_OR_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	BIT_OR_ASSIGN(6, "bit_or_assign", "|="),

	/**
	 * The '<em><b>Plus assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PLUS_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	PLUS_ASSIGN(7, "plus_assign", "+="),

	/**
	 * The '<em><b>Assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	ASSIGN(8, "assign", "="),

	/**
	 * The '<em><b>Right shift unsigned assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_UNSIGNED_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	RIGHT_SHIFT_UNSIGNED_ASSIGN(9, "right_shift_unsigned_assign", ">>>="),

	/**
	 * The '<em><b>Remainder assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMAINDER_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	REMAINDER_ASSIGN(10, "remainder_assign", "%="),

	/**
	 * The '<em><b>Bit and assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BIT_AND_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	BIT_AND_ASSIGN(11, "bit_and_assign", "&="),

	/**
	 * The '<em><b>Left shift assign</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LEFT_SHIFT_ASSIGN_VALUE
	 * @generated
	 * @ordered
	 */
	LEFT_SHIFT_ASSIGN(12, "left_shift_assign", "<<=");

	/**
	 * The '<em><b>Right shift signed assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Right shift signed assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_SIGNED_ASSIGN
	 * @model name="right_shift_signed_assign" literal=">>="
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_SHIFT_SIGNED_ASSIGN_VALUE = 1;

	/**
	 * The '<em><b>Bit xor assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Bit xor assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BIT_XOR_ASSIGN
	 * @model name="bit_xor_assign" literal="^="
	 * @generated
	 * @ordered
	 */
	public static final int BIT_XOR_ASSIGN_VALUE = 2;

	/**
	 * The '<em><b>Times assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Times assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIMES_ASSIGN
	 * @model name="times_assign" literal="*="
	 * @generated
	 * @ordered
	 */
	public static final int TIMES_ASSIGN_VALUE = 3;

	/**
	 * The '<em><b>Divide assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Divide assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DIVIDE_ASSIGN
	 * @model name="divide_assign" literal="/="
	 * @generated
	 * @ordered
	 */
	public static final int DIVIDE_ASSIGN_VALUE = 4;

	/**
	 * The '<em><b>Minus assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Minus assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MINUS_ASSIGN
	 * @model name="minus_assign" literal="-="
	 * @generated
	 * @ordered
	 */
	public static final int MINUS_ASSIGN_VALUE = 5;

	/**
	 * The '<em><b>Bit or assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Bit or assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BIT_OR_ASSIGN
	 * @model name="bit_or_assign" literal="|="
	 * @generated
	 * @ordered
	 */
	public static final int BIT_OR_ASSIGN_VALUE = 6;

	/**
	 * The '<em><b>Plus assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Plus assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PLUS_ASSIGN
	 * @model name="plus_assign" literal="+="
	 * @generated
	 * @ordered
	 */
	public static final int PLUS_ASSIGN_VALUE = 7;

	/**
	 * The '<em><b>Assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ASSIGN
	 * @model name="assign" literal="="
	 * @generated
	 * @ordered
	 */
	public static final int ASSIGN_VALUE = 8;

	/**
	 * The '<em><b>Right shift unsigned assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Right shift unsigned assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RIGHT_SHIFT_UNSIGNED_ASSIGN
	 * @model name="right_shift_unsigned_assign" literal=">>>="
	 * @generated
	 * @ordered
	 */
	public static final int RIGHT_SHIFT_UNSIGNED_ASSIGN_VALUE = 9;

	/**
	 * The '<em><b>Remainder assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Remainder assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REMAINDER_ASSIGN
	 * @model name="remainder_assign" literal="%="
	 * @generated
	 * @ordered
	 */
	public static final int REMAINDER_ASSIGN_VALUE = 10;

	/**
	 * The '<em><b>Bit and assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Bit and assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BIT_AND_ASSIGN
	 * @model name="bit_and_assign" literal="&="
	 * @generated
	 * @ordered
	 */
	public static final int BIT_AND_ASSIGN_VALUE = 11;

	/**
	 * The '<em><b>Left shift assign</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Left shift assign</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LEFT_SHIFT_ASSIGN
	 * @model name="left_shift_assign" literal="<<="
	 * @generated
	 * @ordered
	 */
	public static final int LEFT_SHIFT_ASSIGN_VALUE = 12;

	/**
	 * An array of all the '<em><b>Assignment Operator Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AssignmentOperatorKind[] VALUES_ARRAY =
		new AssignmentOperatorKind[] {
			RIGHT_SHIFT_SIGNED_ASSIGN,
			BIT_XOR_ASSIGN,
			TIMES_ASSIGN,
			DIVIDE_ASSIGN,
			MINUS_ASSIGN,
			BIT_OR_ASSIGN,
			PLUS_ASSIGN,
			ASSIGN,
			RIGHT_SHIFT_UNSIGNED_ASSIGN,
			REMAINDER_ASSIGN,
			BIT_AND_ASSIGN,
			LEFT_SHIFT_ASSIGN,
		};

	/**
	 * A public read-only list of all the '<em><b>Assignment Operator Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AssignmentOperatorKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Assignment Operator Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AssignmentOperatorKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AssignmentOperatorKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Assignment Operator Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AssignmentOperatorKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AssignmentOperatorKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Assignment Operator Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AssignmentOperatorKind get(int value) {
		switch (value) {
			case RIGHT_SHIFT_SIGNED_ASSIGN_VALUE: return RIGHT_SHIFT_SIGNED_ASSIGN;
			case BIT_XOR_ASSIGN_VALUE: return BIT_XOR_ASSIGN;
			case TIMES_ASSIGN_VALUE: return TIMES_ASSIGN;
			case DIVIDE_ASSIGN_VALUE: return DIVIDE_ASSIGN;
			case MINUS_ASSIGN_VALUE: return MINUS_ASSIGN;
			case BIT_OR_ASSIGN_VALUE: return BIT_OR_ASSIGN;
			case PLUS_ASSIGN_VALUE: return PLUS_ASSIGN;
			case ASSIGN_VALUE: return ASSIGN;
			case RIGHT_SHIFT_UNSIGNED_ASSIGN_VALUE: return RIGHT_SHIFT_UNSIGNED_ASSIGN;
			case REMAINDER_ASSIGN_VALUE: return REMAINDER_ASSIGN;
			case BIT_AND_ASSIGN_VALUE: return BIT_AND_ASSIGN;
			case LEFT_SHIFT_ASSIGN_VALUE: return LEFT_SHIFT_ASSIGN;
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
	private AssignmentOperatorKind(int value, String name, String literal) {
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
	
} //AssignmentOperatorKind
