/**
 *
 * $Id$
 */
package DOM;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see DOM.DOMFactory
 * @model kind="package"
 * @generated
 */
public interface DOMPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "DOM";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.amma.dsl.jdt.dom";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dom";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DOMPackage eINSTANCE = DOM.impl.DOMPackageImpl.init();

	/**
	 * The meta object id for the '{@link DOM.impl.ASTImpl <em>AST</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ASTImpl
	 * @see DOM.impl.DOMPackageImpl#getAST()
	 * @generated
	 */
	int AST = 0;

	/**
	 * The feature id for the '<em><b>Compilation Units</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST__COMPILATION_UNITS = 0;

	/**
	 * The number of structural features of the '<em>AST</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>AST</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ASTNodeImpl <em>AST Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ASTNodeImpl
	 * @see DOM.impl.DOMPackageImpl#getASTNode()
	 * @generated
	 */
	int AST_NODE = 1;

	/**
	 * The number of structural features of the '<em>AST Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST_NODE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>AST Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST_NODE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link DOM.impl.AnonymousClassDeclarationImpl <em>Anonymous Class Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.AnonymousClassDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getAnonymousClassDeclaration()
	 * @generated
	 */
	int ANONYMOUS_CLASS_DECLARATION = 2;

	/**
	 * The feature id for the '<em><b>Body Declarations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Anonymous Class Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANONYMOUS_CLASS_DECLARATION_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Anonymous Class Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANONYMOUS_CLASS_DECLARATION_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.BodyDeclarationImpl <em>Body Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.BodyDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getBodyDeclaration()
	 * @generated
	 */
	int BODY_DECLARATION = 3;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BODY_DECLARATION__MODIFIERS = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BODY_DECLARATION__JAVADOC = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Body Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BODY_DECLARATION_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Body Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BODY_DECLARATION_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.CatchClauseImpl <em>Catch Clause</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.CatchClauseImpl
	 * @see DOM.impl.DOMPackageImpl#getCatchClause()
	 * @generated
	 */
	int CATCH_CLAUSE = 4;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATCH_CLAUSE__BODY = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Exception</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATCH_CLAUSE__EXCEPTION = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Catch Clause</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATCH_CLAUSE_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Catch Clause</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATCH_CLAUSE_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.CommentImpl <em>Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.CommentImpl
	 * @see DOM.impl.DOMPackageImpl#getComment()
	 * @generated
	 */
	int COMMENT = 5;

	/**
	 * The feature id for the '<em><b>Alternate Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__ALTERNATE_ROOT = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.CompilationUnitImpl <em>Compilation Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.CompilationUnitImpl
	 * @see DOM.impl.DOMPackageImpl#getCompilationUnit()
	 * @generated
	 */
	int COMPILATION_UNIT = 6;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT__COMMENTS = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Package</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT__PACKAGE = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT__IMPORTS = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT__TYPES = AST_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Compilation Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Compilation Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPILATION_UNIT_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ExpressionImpl
	 * @see DOM.impl.DOMPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 7;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__RESOLVE_BOXING = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__RESOLVE_UNBOXING = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__TYPE_BINDING = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ImportDeclarationImpl <em>Import Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ImportDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getImportDeclaration()
	 * @generated
	 */
	int IMPORT_DECLARATION = 8;

	/**
	 * The feature id for the '<em><b>On Demand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION__ON_DEMAND = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION__STATIC = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION__NAME = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Import Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Import Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_DECLARATION_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.MemberRefImpl <em>Member Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.MemberRefImpl
	 * @see DOM.impl.DOMPackageImpl#getMemberRef()
	 * @generated
	 */
	int MEMBER_REF = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_REF__NAME = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_REF__QUALIFIER = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Member Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_REF_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Member Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_REF_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.MemberValuePairImpl <em>Member Value Pair</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.MemberValuePairImpl
	 * @see DOM.impl.DOMPackageImpl#getMemberValuePair()
	 * @generated
	 */
	int MEMBER_VALUE_PAIR = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_VALUE_PAIR__NAME = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_VALUE_PAIR__VALUE = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Member Value Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_VALUE_PAIR_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Member Value Pair</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_VALUE_PAIR_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.MethodRefImpl <em>Method Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.MethodRefImpl
	 * @see DOM.impl.DOMPackageImpl#getMethodRef()
	 * @generated
	 */
	int METHOD_REF = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_REF__NAME = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_REF__QUALIFIER = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_REF__PARAMETERS = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Method Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_REF_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Method Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_REF_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.MethodRefParameterImpl <em>Method Ref Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.MethodRefParameterImpl
	 * @see DOM.impl.DOMPackageImpl#getMethodRefParameter()
	 * @generated
	 */
	int METHOD_REF_PARAMETER = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_REF_PARAMETER__NAME = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_REF_PARAMETER__TYPE = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Varargs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_REF_PARAMETER__VARARGS = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Method Ref Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_REF_PARAMETER_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Method Ref Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_REF_PARAMETER_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ExtendedModifierImpl <em>Extended Modifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ExtendedModifierImpl
	 * @see DOM.impl.DOMPackageImpl#getExtendedModifier()
	 * @generated
	 */
	int EXTENDED_MODIFIER = 13;

	/**
	 * The number of structural features of the '<em>Extended Modifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_MODIFIER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Extended Modifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENDED_MODIFIER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ModifierImpl <em>Modifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ModifierImpl
	 * @see DOM.impl.DOMPackageImpl#getModifier()
	 * @generated
	 */
	int MODIFIER = 14;

	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__ABSTRACT = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__FINAL = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__NATIVE = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>None</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__NONE = AST_NODE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Private</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__PRIVATE = AST_NODE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Protected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__PROTECTED = AST_NODE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Public</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__PUBLIC = AST_NODE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__STATIC = AST_NODE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Strictfp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__STRICTFP = AST_NODE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Synchronized</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__SYNCHRONIZED = AST_NODE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__TRANSIENT = AST_NODE_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER__VOLATILE = AST_NODE_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Modifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 12;

	/**
	 * The number of operations of the '<em>Modifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODIFIER_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.PackageDeclarationImpl <em>Package Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.PackageDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getPackageDeclaration()
	 * @generated
	 */
	int PACKAGE_DECLARATION = 15;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_DECLARATION__ANNOTATIONS = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_DECLARATION__JAVADOC = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_DECLARATION__NAME = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_DECLARATION__BINDING = AST_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Package Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_DECLARATION_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Package Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_DECLARATION_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.StatementImpl <em>Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.StatementImpl
	 * @see DOM.impl.DOMPackageImpl#getStatement()
	 * @generated
	 */
	int STATEMENT = 16;

	/**
	 * The number of structural features of the '<em>Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATEMENT_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.TagElementImpl <em>Tag Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.TagElementImpl
	 * @see DOM.impl.DOMPackageImpl#getTagElement()
	 * @generated
	 */
	int TAG_ELEMENT = 17;

	/**
	 * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_ELEMENT__FRAGMENTS = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tag Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_ELEMENT__TAG_NAME = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Nested</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_ELEMENT__NESTED = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Tag Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_ELEMENT_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Tag Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_ELEMENT_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.TextElementImpl <em>Text Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.TextElementImpl
	 * @see DOM.impl.DOMPackageImpl#getTextElement()
	 * @generated
	 */
	int TEXT_ELEMENT = 18;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT__TEXT = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Text Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Text Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_ELEMENT_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.TypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.TypeImpl
	 * @see DOM.impl.DOMPackageImpl#getType()
	 * @generated
	 */
	int TYPE = 19;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.TypeParameterImpl <em>Type Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.TypeParameterImpl
	 * @see DOM.impl.DOMPackageImpl#getTypeParameter()
	 * @generated
	 */
	int TYPE_PARAMETER = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_PARAMETER__NAME = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type Bounds</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_PARAMETER__TYPE_BOUNDS = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Type Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_PARAMETER_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Type Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_PARAMETER_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.VariableDeclarationImpl <em>Variable Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.VariableDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getVariableDeclaration()
	 * @generated
	 */
	int VARIABLE_DECLARATION = 21;

	/**
	 * The feature id for the '<em><b>Extra Dimensions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__EXTRA_DIMENSIONS = AST_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Initializer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__INITIALIZER = AST_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION__NAME = AST_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_FEATURE_COUNT = AST_NODE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_OPERATION_COUNT = AST_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.AbstractTypeDeclarationImpl <em>Abstract Type Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.AbstractTypeDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getAbstractTypeDeclaration()
	 * @generated
	 */
	int ABSTRACT_TYPE_DECLARATION = 22;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_DECLARATION__MODIFIERS = BODY_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_DECLARATION__JAVADOC = BODY_DECLARATION__JAVADOC;

	/**
	 * The feature id for the '<em><b>Body Declarations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS = BODY_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_DECLARATION__NAME = BODY_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Local Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION = BODY_DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION = BODY_DECLARATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Package Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION = BODY_DECLARATION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Abstract Type Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_DECLARATION_FEATURE_COUNT = BODY_DECLARATION_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Abstract Type Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_TYPE_DECLARATION_OPERATION_COUNT = BODY_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.AnnotationTypeMemberDeclarationImpl <em>Annotation Type Member Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.AnnotationTypeMemberDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getAnnotationTypeMemberDeclaration()
	 * @generated
	 */
	int ANNOTATION_TYPE_MEMBER_DECLARATION = 23;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_MEMBER_DECLARATION__MODIFIERS = BODY_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_MEMBER_DECLARATION__JAVADOC = BODY_DECLARATION__JAVADOC;

	/**
	 * The feature id for the '<em><b>Default</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT = BODY_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_MEMBER_DECLARATION__NAME = BODY_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE = BODY_DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Annotation Type Member Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_MEMBER_DECLARATION_FEATURE_COUNT = BODY_DECLARATION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Annotation Type Member Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_MEMBER_DECLARATION_OPERATION_COUNT = BODY_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.EnumConstantDeclarationImpl <em>Enum Constant Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.EnumConstantDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getEnumConstantDeclaration()
	 * @generated
	 */
	int ENUM_CONSTANT_DECLARATION = 24;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CONSTANT_DECLARATION__MODIFIERS = BODY_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CONSTANT_DECLARATION__JAVADOC = BODY_DECLARATION__JAVADOC;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CONSTANT_DECLARATION__ARGUMENTS = BODY_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Anonymous Class Declaration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION = BODY_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CONSTANT_DECLARATION__NAME = BODY_DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Enum Constant Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CONSTANT_DECLARATION_FEATURE_COUNT = BODY_DECLARATION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Enum Constant Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CONSTANT_DECLARATION_OPERATION_COUNT = BODY_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.FieldDeclarationImpl <em>Field Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.FieldDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getFieldDeclaration()
	 * @generated
	 */
	int FIELD_DECLARATION = 25;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_DECLARATION__MODIFIERS = BODY_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_DECLARATION__JAVADOC = BODY_DECLARATION__JAVADOC;

	/**
	 * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_DECLARATION__FRAGMENTS = BODY_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_DECLARATION__TYPE = BODY_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Field Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_DECLARATION_FEATURE_COUNT = BODY_DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Field Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_DECLARATION_OPERATION_COUNT = BODY_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.InitializerImpl <em>Initializer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.InitializerImpl
	 * @see DOM.impl.DOMPackageImpl#getInitializer()
	 * @generated
	 */
	int INITIALIZER = 26;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZER__MODIFIERS = BODY_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZER__JAVADOC = BODY_DECLARATION__JAVADOC;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZER__BODY = BODY_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Initializer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZER_FEATURE_COUNT = BODY_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Initializer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZER_OPERATION_COUNT = BODY_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.MethodDeclarationImpl <em>Method Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.MethodDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getMethodDeclaration()
	 * @generated
	 */
	int METHOD_DECLARATION = 27;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__MODIFIERS = BODY_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__JAVADOC = BODY_DECLARATION__JAVADOC;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__BODY = BODY_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Extra Dimensions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__EXTRA_DIMENSIONS = BODY_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__NAME = BODY_DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__RETURN_TYPE = BODY_DECLARATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Constructor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__CONSTRUCTOR = BODY_DECLARATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Varargs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__VARARGS = BODY_DECLARATION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__PARAMETERS = BODY_DECLARATION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Thrown Exceptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__THROWN_EXCEPTIONS = BODY_DECLARATION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__TYPE_PARAMETERS = BODY_DECLARATION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION__BINDING = BODY_DECLARATION_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Method Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_FEATURE_COUNT = BODY_DECLARATION_FEATURE_COUNT + 10;

	/**
	 * The number of operations of the '<em>Method Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_DECLARATION_OPERATION_COUNT = BODY_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.AnnotationTypeDeclarationImpl <em>Annotation Type Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.AnnotationTypeDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getAnnotationTypeDeclaration()
	 * @generated
	 */
	int ANNOTATION_TYPE_DECLARATION = 28;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_DECLARATION__MODIFIERS = ABSTRACT_TYPE_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_DECLARATION__JAVADOC = ABSTRACT_TYPE_DECLARATION__JAVADOC;

	/**
	 * The feature id for the '<em><b>Body Declarations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_DECLARATION__BODY_DECLARATIONS = ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_DECLARATION__NAME = ABSTRACT_TYPE_DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Local Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION = ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION = ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Package Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION = ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION;

	/**
	 * The number of structural features of the '<em>Annotation Type Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_DECLARATION_FEATURE_COUNT = ABSTRACT_TYPE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Annotation Type Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_DECLARATION_OPERATION_COUNT = ABSTRACT_TYPE_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.EnumDeclarationImpl <em>Enum Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.EnumDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getEnumDeclaration()
	 * @generated
	 */
	int ENUM_DECLARATION = 29;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION__MODIFIERS = ABSTRACT_TYPE_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION__JAVADOC = ABSTRACT_TYPE_DECLARATION__JAVADOC;

	/**
	 * The feature id for the '<em><b>Body Declarations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION__BODY_DECLARATIONS = ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION__NAME = ABSTRACT_TYPE_DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Local Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION__LOCAL_TYPE_DECLARATION = ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION__MEMBER_TYPE_DECLARATION = ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Package Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION = ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Super Interface Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION__SUPER_INTERFACE_TYPES = ABSTRACT_TYPE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enum Constants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION__ENUM_CONSTANTS = ABSTRACT_TYPE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Enum Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION_FEATURE_COUNT = ABSTRACT_TYPE_DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Enum Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_DECLARATION_OPERATION_COUNT = ABSTRACT_TYPE_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.TypeDeclarationImpl <em>Type Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.TypeDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getTypeDeclaration()
	 * @generated
	 */
	int TYPE_DECLARATION = 30;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__MODIFIERS = ABSTRACT_TYPE_DECLARATION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Javadoc</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__JAVADOC = ABSTRACT_TYPE_DECLARATION__JAVADOC;

	/**
	 * The feature id for the '<em><b>Body Declarations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__BODY_DECLARATIONS = ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__NAME = ABSTRACT_TYPE_DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Local Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__LOCAL_TYPE_DECLARATION = ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__MEMBER_TYPE_DECLARATION = ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Package Member Type Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION = ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION;

	/**
	 * The feature id for the '<em><b>Superclass Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__SUPERCLASS_TYPE = ABSTRACT_TYPE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__INTERFACE = ABSTRACT_TYPE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Super Interface Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__SUPER_INTERFACE_TYPES = ABSTRACT_TYPE_DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION__TYPE_PARAMETERS = ABSTRACT_TYPE_DECLARATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Type Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_FEATURE_COUNT = ABSTRACT_TYPE_DECLARATION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Type Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_OPERATION_COUNT = ABSTRACT_TYPE_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.BlockCommentImpl <em>Block Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.BlockCommentImpl
	 * @see DOM.impl.DOMPackageImpl#getBlockComment()
	 * @generated
	 */
	int BLOCK_COMMENT = 31;

	/**
	 * The feature id for the '<em><b>Alternate Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_COMMENT__ALTERNATE_ROOT = COMMENT__ALTERNATE_ROOT;

	/**
	 * The number of structural features of the '<em>Block Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_COMMENT_FEATURE_COUNT = COMMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Block Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_COMMENT_OPERATION_COUNT = COMMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.JavadocImpl <em>Javadoc</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.JavadocImpl
	 * @see DOM.impl.DOMPackageImpl#getJavadoc()
	 * @generated
	 */
	int JAVADOC = 32;

	/**
	 * The feature id for the '<em><b>Alternate Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC__ALTERNATE_ROOT = COMMENT__ALTERNATE_ROOT;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC__TAGS = COMMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Javadoc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_FEATURE_COUNT = COMMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Javadoc</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVADOC_OPERATION_COUNT = COMMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.LineCommentImpl <em>Line Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.LineCommentImpl
	 * @see DOM.impl.DOMPackageImpl#getLineComment()
	 * @generated
	 */
	int LINE_COMMENT = 33;

	/**
	 * The feature id for the '<em><b>Alternate Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_COMMENT__ALTERNATE_ROOT = COMMENT__ALTERNATE_ROOT;

	/**
	 * The number of structural features of the '<em>Line Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_COMMENT_FEATURE_COUNT = COMMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Line Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_COMMENT_OPERATION_COUNT = COMMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.AnnotationImpl <em>Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.AnnotationImpl
	 * @see DOM.impl.DOMPackageImpl#getAnnotation()
	 * @generated
	 */
	int ANNOTATION = 34;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__TYPE_NAME = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ArrayAccessImpl <em>Array Access</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ArrayAccessImpl
	 * @see DOM.impl.DOMPackageImpl#getArrayAccess()
	 * @generated
	 */
	int ARRAY_ACCESS = 35;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_ACCESS__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_ACCESS__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_ACCESS__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Array</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_ACCESS__ARRAY = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Index</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_ACCESS__INDEX = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Array Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_ACCESS_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Array Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_ACCESS_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ArrayCreationImpl <em>Array Creation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ArrayCreationImpl
	 * @see DOM.impl.DOMPackageImpl#getArrayCreation()
	 * @generated
	 */
	int ARRAY_CREATION = 36;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Dimensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION__DIMENSIONS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Initializer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION__INITIALIZER = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION__TYPE = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Array Creation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Array Creation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ArrayInitializerImpl <em>Array Initializer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ArrayInitializerImpl
	 * @see DOM.impl.DOMPackageImpl#getArrayInitializer()
	 * @generated
	 */
	int ARRAY_INITIALIZER = 37;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INITIALIZER__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INITIALIZER__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INITIALIZER__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INITIALIZER__EXPRESSIONS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Array Initializer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INITIALIZER_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Array Initializer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_INITIALIZER_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.AssignmentImpl <em>Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.AssignmentImpl
	 * @see DOM.impl.DOMPackageImpl#getAssignment()
	 * @generated
	 */
	int ASSIGNMENT = 38;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Left Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__LEFT_HAND_SIDE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__OPERATOR = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Right Hand Side</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__RIGHT_HAND_SIDE = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.BooleanLiteralImpl
	 * @see DOM.impl.DOMPackageImpl#getBooleanLiteral()
	 * @generated
	 */
	int BOOLEAN_LITERAL = 39;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Boolean Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__BOOLEAN_VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.CastExpressionImpl <em>Cast Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.CastExpressionImpl
	 * @see DOM.impl.DOMPackageImpl#getCastExpression()
	 * @generated
	 */
	int CAST_EXPRESSION = 40;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAST_EXPRESSION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAST_EXPRESSION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAST_EXPRESSION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAST_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAST_EXPRESSION__TYPE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Cast Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAST_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Cast Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CAST_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.CharacterLiteralImpl <em>Character Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.CharacterLiteralImpl
	 * @see DOM.impl.DOMPackageImpl#getCharacterLiteral()
	 * @generated
	 */
	int CHARACTER_LITERAL = 41;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Char Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__CHAR_VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Escaped Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__ESCAPED_VALUE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Character Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Character Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ClassInstanceCreationImpl <em>Class Instance Creation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ClassInstanceCreationImpl
	 * @see DOM.impl.DOMPackageImpl#getClassInstanceCreation()
	 * @generated
	 */
	int CLASS_INSTANCE_CREATION = 42;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION__ARGUMENTS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Anonymous Class Declaration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION__TYPE = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Type Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Class Instance Creation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Class Instance Creation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASS_INSTANCE_CREATION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ConditionalExpressionImpl <em>Conditional Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ConditionalExpressionImpl
	 * @see DOM.impl.DOMPackageImpl#getConditionalExpression()
	 * @generated
	 */
	int CONDITIONAL_EXPRESSION = 43;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Else Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION__ELSE_EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Then Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION__THEN_EXPRESSION = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Conditional Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Conditional Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.FieldAccessImpl <em>Field Access</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.FieldAccessImpl
	 * @see DOM.impl.DOMPackageImpl#getFieldAccess()
	 * @generated
	 */
	int FIELD_ACCESS = 44;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ACCESS__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ACCESS__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ACCESS__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ACCESS__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ACCESS__NAME = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Field Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ACCESS_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Field Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_ACCESS_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.InfixExpressionImpl <em>Infix Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.InfixExpressionImpl
	 * @see DOM.impl.DOMPackageImpl#getInfixExpression()
	 * @generated
	 */
	int INFIX_EXPRESSION = 45;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Extended Operands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION__EXTENDED_OPERANDS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION__LEFT_OPERAND = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION__OPERATOR = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION__RIGHT_OPERAND = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Infix Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Infix Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.InstanceofExpressionImpl <em>Instanceof Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.InstanceofExpressionImpl
	 * @see DOM.impl.DOMPackageImpl#getInstanceofExpression()
	 * @generated
	 */
	int INSTANCEOF_EXPRESSION = 46;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCEOF_EXPRESSION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCEOF_EXPRESSION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCEOF_EXPRESSION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Left Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCEOF_EXPRESSION__LEFT_OPERAND = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCEOF_EXPRESSION__RIGHT_OPERAND = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Instanceof Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCEOF_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Instanceof Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCEOF_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.MethodInvocationImpl <em>Method Invocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.MethodInvocationImpl
	 * @see DOM.impl.DOMPackageImpl#getMethodInvocation()
	 * @generated
	 */
	int METHOD_INVOCATION = 47;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION__ARGUMENTS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION__NAME = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION__TYPE_ARGUMENTS = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Method Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION__METHOD_BINDING = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Method Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Method Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_INVOCATION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.NameImpl <em>Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.NameImpl
	 * @see DOM.impl.DOMPackageImpl#getName_()
	 * @generated
	 */
	int NAME = 48;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME__FULLY_QUALIFIED_NAME = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAME_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.NullLiteralImpl <em>Null Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.NullLiteralImpl
	 * @see DOM.impl.DOMPackageImpl#getNullLiteral()
	 * @generated
	 */
	int NULL_LITERAL = 49;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The number of structural features of the '<em>Null Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Null Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_LITERAL_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.NumberLiteralImpl <em>Number Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.NumberLiteralImpl
	 * @see DOM.impl.DOMPackageImpl#getNumberLiteral()
	 * @generated
	 */
	int NUMBER_LITERAL = 50;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Token</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL__TOKEN = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Number Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Number Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_LITERAL_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ParenthesizedExpressionImpl <em>Parenthesized Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ParenthesizedExpressionImpl
	 * @see DOM.impl.DOMPackageImpl#getParenthesizedExpression()
	 * @generated
	 */
	int PARENTHESIZED_EXPRESSION = 51;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIZED_EXPRESSION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIZED_EXPRESSION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIZED_EXPRESSION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIZED_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Parenthesized Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIZED_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Parenthesized Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENTHESIZED_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.PostfixExpressionImpl <em>Postfix Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.PostfixExpressionImpl
	 * @see DOM.impl.DOMPackageImpl#getPostfixExpression()
	 * @generated
	 */
	int POSTFIX_EXPRESSION = 52;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_EXPRESSION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_EXPRESSION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_EXPRESSION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_EXPRESSION__OPERAND = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_EXPRESSION__OPERATOR = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Postfix Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Postfix Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.PrefixExpressionImpl <em>Prefix Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.PrefixExpressionImpl
	 * @see DOM.impl.DOMPackageImpl#getPrefixExpression()
	 * @generated
	 */
	int PREFIX_EXPRESSION = 53;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_EXPRESSION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_EXPRESSION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_EXPRESSION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Operand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_EXPRESSION__OPERAND = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_EXPRESSION__OPERATOR = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Prefix Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Prefix Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.StringLiteralImpl <em>String Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.StringLiteralImpl
	 * @see DOM.impl.DOMPackageImpl#getStringLiteral()
	 * @generated
	 */
	int STRING_LITERAL = 54;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Escaped Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__ESCAPED_VALUE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Literal Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__LITERAL_VALUE = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.SuperFieldAccessImpl <em>Super Field Access</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.SuperFieldAccessImpl
	 * @see DOM.impl.DOMPackageImpl#getSuperFieldAccess()
	 * @generated
	 */
	int SUPER_FIELD_ACCESS = 55;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_FIELD_ACCESS__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_FIELD_ACCESS__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_FIELD_ACCESS__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_FIELD_ACCESS__NAME = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_FIELD_ACCESS__QUALIFIER = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Super Field Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_FIELD_ACCESS_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Super Field Access</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_FIELD_ACCESS_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.SuperMethodInvocationImpl <em>Super Method Invocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.SuperMethodInvocationImpl
	 * @see DOM.impl.DOMPackageImpl#getSuperMethodInvocation()
	 * @generated
	 */
	int SUPER_METHOD_INVOCATION = 56;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_METHOD_INVOCATION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_METHOD_INVOCATION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_METHOD_INVOCATION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_METHOD_INVOCATION__ARGUMENTS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_METHOD_INVOCATION__NAME = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_METHOD_INVOCATION__QUALIFIER = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_METHOD_INVOCATION__TYPE_ARGUMENTS = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Super Method Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_METHOD_INVOCATION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Super Method Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_METHOD_INVOCATION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ThisExpressionImpl <em>This Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ThisExpressionImpl
	 * @see DOM.impl.DOMPackageImpl#getThisExpression()
	 * @generated
	 */
	int THIS_EXPRESSION = 57;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_EXPRESSION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_EXPRESSION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_EXPRESSION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_EXPRESSION__QUALIFIER = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>This Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>This Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THIS_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.TypeLiteralImpl <em>Type Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.TypeLiteralImpl
	 * @see DOM.impl.DOMPackageImpl#getTypeLiteral()
	 * @generated
	 */
	int TYPE_LITERAL = 58;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL__TYPE = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Type Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_LITERAL_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.VariableDeclarationExpressionImpl <em>Variable Declaration Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.VariableDeclarationExpressionImpl
	 * @see DOM.impl.DOMPackageImpl#getVariableDeclarationExpression()
	 * @generated
	 */
	int VARIABLE_DECLARATION_EXPRESSION = 59;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_EXPRESSION__RESOLVE_BOXING = EXPRESSION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_EXPRESSION__RESOLVE_UNBOXING = EXPRESSION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_EXPRESSION__TYPE_BINDING = EXPRESSION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS = EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_EXPRESSION__MODIFIERS = EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_EXPRESSION__TYPE = EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Variable Declaration Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Variable Declaration Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_EXPRESSION_OPERATION_COUNT = EXPRESSION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.AssertStatementImpl <em>Assert Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.AssertStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getAssertStatement()
	 * @generated
	 */
	int ASSERT_STATEMENT = 60;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERT_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Message</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERT_STATEMENT__MESSAGE = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Assert Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERT_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Assert Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSERT_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.BlockImpl <em>Block</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.BlockImpl
	 * @see DOM.impl.DOMPackageImpl#getBlock()
	 * @generated
	 */
	int BLOCK = 61;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__STATEMENTS = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.BreakStatementImpl <em>Break Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.BreakStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getBreakStatement()
	 * @generated
	 */
	int BREAK_STATEMENT = 62;

	/**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK_STATEMENT__LABEL = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Break Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Break Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ConstructorInvocationImpl <em>Constructor Invocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ConstructorInvocationImpl
	 * @see DOM.impl.DOMPackageImpl#getConstructorInvocation()
	 * @generated
	 */
	int CONSTRUCTOR_INVOCATION = 63;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR_INVOCATION__ARGUMENTS = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Constructor Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR_INVOCATION_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Constructor Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRUCTOR_INVOCATION_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ContinueStatementImpl <em>Continue Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ContinueStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getContinueStatement()
	 * @generated
	 */
	int CONTINUE_STATEMENT = 64;

	/**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTINUE_STATEMENT__LABEL = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Continue Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTINUE_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Continue Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTINUE_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.DoStatementImpl <em>Do Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.DoStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getDoStatement()
	 * @generated
	 */
	int DO_STATEMENT = 65;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DO_STATEMENT__BODY = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DO_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Do Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DO_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Do Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DO_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.EmptyStatementImpl <em>Empty Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.EmptyStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getEmptyStatement()
	 * @generated
	 */
	int EMPTY_STATEMENT = 66;

	/**
	 * The number of structural features of the '<em>Empty Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Empty Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMPTY_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.EnhancedForStatementImpl <em>Enhanced For Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.EnhancedForStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getEnhancedForStatement()
	 * @generated
	 */
	int ENHANCED_FOR_STATEMENT = 67;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENHANCED_FOR_STATEMENT__BODY = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENHANCED_FOR_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENHANCED_FOR_STATEMENT__PARAMETER = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Enhanced For Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENHANCED_FOR_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Enhanced For Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENHANCED_FOR_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ExpressionStatementImpl <em>Expression Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ExpressionStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getExpressionStatement()
	 * @generated
	 */
	int EXPRESSION_STATEMENT = 68;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Expression Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Expression Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ForStatementImpl <em>For Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ForStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getForStatement()
	 * @generated
	 */
	int FOR_STATEMENT = 69;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STATEMENT__BODY = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Initializers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STATEMENT__INITIALIZERS = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Updaters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STATEMENT__UPDATERS = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>For Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>For Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.IfStatementImpl <em>If Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.IfStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getIfStatement()
	 * @generated
	 */
	int IF_STATEMENT = 70;

	/**
	 * The feature id for the '<em><b>Else Statement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT__ELSE_STATEMENT = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Then Statement</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT__THEN_STATEMENT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>If Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>If Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IF_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.LabeledStatementImpl <em>Labeled Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.LabeledStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getLabeledStatement()
	 * @generated
	 */
	int LABELED_STATEMENT = 71;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELED_STATEMENT__BODY = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELED_STATEMENT__LABEL = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Labeled Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELED_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Labeled Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABELED_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ReturnStatementImpl <em>Return Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ReturnStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getReturnStatement()
	 * @generated
	 */
	int RETURN_STATEMENT = 72;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Return Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Return Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETURN_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.SuperConstructorInvocationImpl <em>Super Constructor Invocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.SuperConstructorInvocationImpl
	 * @see DOM.impl.DOMPackageImpl#getSuperConstructorInvocation()
	 * @generated
	 */
	int SUPER_CONSTRUCTOR_INVOCATION = 73;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Super Constructor Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_CONSTRUCTOR_INVOCATION_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Super Constructor Invocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPER_CONSTRUCTOR_INVOCATION_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.SwitchCaseImpl <em>Switch Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.SwitchCaseImpl
	 * @see DOM.impl.DOMPackageImpl#getSwitchCase()
	 * @generated
	 */
	int SWITCH_CASE = 74;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_CASE__EXPRESSION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_CASE__DEFAULT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Switch Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_CASE_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Switch Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_CASE_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.SwitchStatementImpl <em>Switch Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.SwitchStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getSwitchStatement()
	 * @generated
	 */
	int SWITCH_STATEMENT = 75;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Statements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_STATEMENT__STATEMENTS = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Switch Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Switch Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.SynchronizedStatementImpl <em>Synchronized Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.SynchronizedStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getSynchronizedStatement()
	 * @generated
	 */
	int SYNCHRONIZED_STATEMENT = 76;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONIZED_STATEMENT__BODY = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONIZED_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Synchronized Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONIZED_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Synchronized Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONIZED_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ThrowStatementImpl <em>Throw Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ThrowStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getThrowStatement()
	 * @generated
	 */
	int THROW_STATEMENT = 77;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THROW_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Throw Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THROW_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Throw Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THROW_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.TryStatementImpl <em>Try Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.TryStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getTryStatement()
	 * @generated
	 */
	int TRY_STATEMENT = 78;

	/**
	 * The feature id for the '<em><b>Catch Clauses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRY_STATEMENT__CATCH_CLAUSES = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRY_STATEMENT__BODY = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Finally</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRY_STATEMENT__FINALLY = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Try Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRY_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Try Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRY_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.TypeDeclarationStatementImpl <em>Type Declaration Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.TypeDeclarationStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getTypeDeclarationStatement()
	 * @generated
	 */
	int TYPE_DECLARATION_STATEMENT = 79;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_STATEMENT__DECLARATION = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Declaration Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Type Declaration Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DECLARATION_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.VariableDeclarationStatementImpl <em>Variable Declaration Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.VariableDeclarationStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getVariableDeclarationStatement()
	 * @generated
	 */
	int VARIABLE_DECLARATION_STATEMENT = 80;

	/**
	 * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_STATEMENT__FRAGMENTS = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_STATEMENT__MODIFIERS = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_STATEMENT__TYPE = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Variable Declaration Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Variable Declaration Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.WhileStatementImpl <em>While Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.WhileStatementImpl
	 * @see DOM.impl.DOMPackageImpl#getWhileStatement()
	 * @generated
	 */
	int WHILE_STATEMENT = 81;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_STATEMENT__BODY = STATEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_STATEMENT__EXPRESSION = STATEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>While Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_STATEMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>While Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WHILE_STATEMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ArrayTypeImpl <em>Array Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ArrayTypeImpl
	 * @see DOM.impl.DOMPackageImpl#getArrayType()
	 * @generated
	 */
	int ARRAY_TYPE = 82;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__COMPONENT_TYPE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dimensions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__DIMENSIONS = TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE__ELEMENT_TYPE = TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Array Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Array Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.ParameterizedTypeImpl <em>Parameterized Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.ParameterizedTypeImpl
	 * @see DOM.impl.DOMPackageImpl#getParameterizedType()
	 * @generated
	 */
	int PARAMETERIZED_TYPE = 83;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETERIZED_TYPE__TYPE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETERIZED_TYPE__TYPE_ARGUMENTS = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Parameterized Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETERIZED_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Parameterized Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETERIZED_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.PrimitiveTypeImpl
	 * @see DOM.impl.DOMPackageImpl#getPrimitiveType()
	 * @generated
	 */
	int PRIMITIVE_TYPE = 84;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE__CODE = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Primitive Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Primitive Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.QualifiedTypeImpl <em>Qualified Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.QualifiedTypeImpl
	 * @see DOM.impl.DOMPackageImpl#getQualifiedType()
	 * @generated
	 */
	int QUALIFIED_TYPE = 85;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_TYPE__NAME = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_TYPE__QUALIFIER = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Qualified Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Qualified Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.SimpleTypeImpl <em>Simple Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.SimpleTypeImpl
	 * @see DOM.impl.DOMPackageImpl#getSimpleType()
	 * @generated
	 */
	int SIMPLE_TYPE = 86;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_TYPE__NAME = TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Simple Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Simple Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.WildcardTypeImpl <em>Wildcard Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.WildcardTypeImpl
	 * @see DOM.impl.DOMPackageImpl#getWildcardType()
	 * @generated
	 */
	int WILDCARD_TYPE = 87;

	/**
	 * The feature id for the '<em><b>Bound</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WILDCARD_TYPE__BOUND = TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WILDCARD_TYPE__UPPER_BOUND = TYPE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Wildcard Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WILDCARD_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Wildcard Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WILDCARD_TYPE_OPERATION_COUNT = TYPE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.SingleVariableDeclarationImpl <em>Single Variable Declaration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.SingleVariableDeclarationImpl
	 * @see DOM.impl.DOMPackageImpl#getSingleVariableDeclaration()
	 * @generated
	 */
	int SINGLE_VARIABLE_DECLARATION = 88;

	/**
	 * The feature id for the '<em><b>Extra Dimensions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VARIABLE_DECLARATION__EXTRA_DIMENSIONS = VARIABLE_DECLARATION__EXTRA_DIMENSIONS;

	/**
	 * The feature id for the '<em><b>Initializer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VARIABLE_DECLARATION__INITIALIZER = VARIABLE_DECLARATION__INITIALIZER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VARIABLE_DECLARATION__NAME = VARIABLE_DECLARATION__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VARIABLE_DECLARATION__TYPE = VARIABLE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Varargs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VARIABLE_DECLARATION__VARARGS = VARIABLE_DECLARATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VARIABLE_DECLARATION__MODIFIERS = VARIABLE_DECLARATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Single Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VARIABLE_DECLARATION_FEATURE_COUNT = VARIABLE_DECLARATION_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Single Variable Declaration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_VARIABLE_DECLARATION_OPERATION_COUNT = VARIABLE_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.VariableDeclarationFragmentImpl <em>Variable Declaration Fragment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.VariableDeclarationFragmentImpl
	 * @see DOM.impl.DOMPackageImpl#getVariableDeclarationFragment()
	 * @generated
	 */
	int VARIABLE_DECLARATION_FRAGMENT = 89;

	/**
	 * The feature id for the '<em><b>Extra Dimensions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_FRAGMENT__EXTRA_DIMENSIONS = VARIABLE_DECLARATION__EXTRA_DIMENSIONS;

	/**
	 * The feature id for the '<em><b>Initializer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_FRAGMENT__INITIALIZER = VARIABLE_DECLARATION__INITIALIZER;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_FRAGMENT__NAME = VARIABLE_DECLARATION__NAME;

	/**
	 * The number of structural features of the '<em>Variable Declaration Fragment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_FRAGMENT_FEATURE_COUNT = VARIABLE_DECLARATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Variable Declaration Fragment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_DECLARATION_FRAGMENT_OPERATION_COUNT = VARIABLE_DECLARATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.QualifiedNameImpl <em>Qualified Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.QualifiedNameImpl
	 * @see DOM.impl.DOMPackageImpl#getQualifiedName()
	 * @generated
	 */
	int QUALIFIED_NAME = 90;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_NAME__RESOLVE_BOXING = NAME__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_NAME__RESOLVE_UNBOXING = NAME__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_NAME__TYPE_BINDING = NAME__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_NAME__FULLY_QUALIFIED_NAME = NAME__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_NAME__NAME = NAME_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualifier</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_NAME__QUALIFIER = NAME_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Qualified Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_NAME_FEATURE_COUNT = NAME_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Qualified Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALIFIED_NAME_OPERATION_COUNT = NAME_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.SimpleNameImpl <em>Simple Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.SimpleNameImpl
	 * @see DOM.impl.DOMPackageImpl#getSimpleName()
	 * @generated
	 */
	int SIMPLE_NAME = 91;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_NAME__RESOLVE_BOXING = NAME__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_NAME__RESOLVE_UNBOXING = NAME__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_NAME__TYPE_BINDING = NAME__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Fully Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_NAME__FULLY_QUALIFIED_NAME = NAME__FULLY_QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_NAME__IDENTIFIER = NAME_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_NAME__DECLARATION = NAME_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Simple Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_NAME_FEATURE_COUNT = NAME_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Simple Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMPLE_NAME_OPERATION_COUNT = NAME_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.MarkerAnnotationImpl <em>Marker Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.MarkerAnnotationImpl
	 * @see DOM.impl.DOMPackageImpl#getMarkerAnnotation()
	 * @generated
	 */
	int MARKER_ANNOTATION = 92;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ANNOTATION__RESOLVE_BOXING = ANNOTATION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ANNOTATION__RESOLVE_UNBOXING = ANNOTATION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ANNOTATION__TYPE_BINDING = ANNOTATION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ANNOTATION__TYPE_NAME = ANNOTATION__TYPE_NAME;

	/**
	 * The number of structural features of the '<em>Marker Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ANNOTATION_FEATURE_COUNT = ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Marker Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ANNOTATION_OPERATION_COUNT = ANNOTATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.NormalAnnotationImpl <em>Normal Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.NormalAnnotationImpl
	 * @see DOM.impl.DOMPackageImpl#getNormalAnnotation()
	 * @generated
	 */
	int NORMAL_ANNOTATION = 93;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_ANNOTATION__RESOLVE_BOXING = ANNOTATION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_ANNOTATION__RESOLVE_UNBOXING = ANNOTATION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_ANNOTATION__TYPE_BINDING = ANNOTATION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_ANNOTATION__TYPE_NAME = ANNOTATION__TYPE_NAME;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_ANNOTATION__VALUES = ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Normal Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_ANNOTATION_FEATURE_COUNT = ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Normal Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_ANNOTATION_OPERATION_COUNT = ANNOTATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.impl.SingleMemberAnnotationImpl <em>Single Member Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.impl.SingleMemberAnnotationImpl
	 * @see DOM.impl.DOMPackageImpl#getSingleMemberAnnotation()
	 * @generated
	 */
	int SINGLE_MEMBER_ANNOTATION = 94;

	/**
	 * The feature id for the '<em><b>Resolve Boxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEMBER_ANNOTATION__RESOLVE_BOXING = ANNOTATION__RESOLVE_BOXING;

	/**
	 * The feature id for the '<em><b>Resolve Unboxing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEMBER_ANNOTATION__RESOLVE_UNBOXING = ANNOTATION__RESOLVE_UNBOXING;

	/**
	 * The feature id for the '<em><b>Type Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEMBER_ANNOTATION__TYPE_BINDING = ANNOTATION__TYPE_BINDING;

	/**
	 * The feature id for the '<em><b>Type Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEMBER_ANNOTATION__TYPE_NAME = ANNOTATION__TYPE_NAME;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEMBER_ANNOTATION__VALUE = ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Single Member Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEMBER_ANNOTATION_FEATURE_COUNT = ANNOTATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Single Member Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_MEMBER_ANNOTATION_OPERATION_COUNT = ANNOTATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link DOM.AssignmentOperatorKind <em>Assignment Operator Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.AssignmentOperatorKind
	 * @see DOM.impl.DOMPackageImpl#getAssignmentOperatorKind()
	 * @generated
	 */
	int ASSIGNMENT_OPERATOR_KIND = 95;

	/**
	 * The meta object id for the '{@link DOM.InfixExpressionOperatorKind <em>Infix Expression Operator Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.InfixExpressionOperatorKind
	 * @see DOM.impl.DOMPackageImpl#getInfixExpressionOperatorKind()
	 * @generated
	 */
	int INFIX_EXPRESSION_OPERATOR_KIND = 96;

	/**
	 * The meta object id for the '{@link DOM.PostfixExpressionOperatorKind <em>Postfix Expression Operator Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.PostfixExpressionOperatorKind
	 * @see DOM.impl.DOMPackageImpl#getPostfixExpressionOperatorKind()
	 * @generated
	 */
	int POSTFIX_EXPRESSION_OPERATOR_KIND = 97;

	/**
	 * The meta object id for the '{@link DOM.PrefixExpressionOperatorKind <em>Prefix Expression Operator Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see DOM.PrefixExpressionOperatorKind
	 * @see DOM.impl.DOMPackageImpl#getPrefixExpressionOperatorKind()
	 * @generated
	 */
	int PREFIX_EXPRESSION_OPERATOR_KIND = 98;


	/**
	 * Returns the meta object for class '{@link DOM.AST <em>AST</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AST</em>'.
	 * @see DOM.AST
	 * @generated
	 */
	EClass getAST();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.AST#getCompilationUnits <em>Compilation Units</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Compilation Units</em>'.
	 * @see DOM.AST#getCompilationUnits()
	 * @see #getAST()
	 * @generated
	 */
	EReference getAST_CompilationUnits();

	/**
	 * Returns the meta object for class '{@link DOM.ASTNode <em>AST Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AST Node</em>'.
	 * @see DOM.ASTNode
	 * @generated
	 */
	EClass getASTNode();

	/**
	 * Returns the meta object for class '{@link DOM.AnonymousClassDeclaration <em>Anonymous Class Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Anonymous Class Declaration</em>'.
	 * @see DOM.AnonymousClassDeclaration
	 * @generated
	 */
	EClass getAnonymousClassDeclaration();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.AnonymousClassDeclaration#getBodyDeclarations <em>Body Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Body Declarations</em>'.
	 * @see DOM.AnonymousClassDeclaration#getBodyDeclarations()
	 * @see #getAnonymousClassDeclaration()
	 * @generated
	 */
	EReference getAnonymousClassDeclaration_BodyDeclarations();

	/**
	 * Returns the meta object for class '{@link DOM.BodyDeclaration <em>Body Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Body Declaration</em>'.
	 * @see DOM.BodyDeclaration
	 * @generated
	 */
	EClass getBodyDeclaration();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.BodyDeclaration#getModifiers <em>Modifiers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Modifiers</em>'.
	 * @see DOM.BodyDeclaration#getModifiers()
	 * @see #getBodyDeclaration()
	 * @generated
	 */
	EReference getBodyDeclaration_Modifiers();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.BodyDeclaration#getJavadoc <em>Javadoc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Javadoc</em>'.
	 * @see DOM.BodyDeclaration#getJavadoc()
	 * @see #getBodyDeclaration()
	 * @generated
	 */
	EReference getBodyDeclaration_Javadoc();

	/**
	 * Returns the meta object for class '{@link DOM.CatchClause <em>Catch Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Catch Clause</em>'.
	 * @see DOM.CatchClause
	 * @generated
	 */
	EClass getCatchClause();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.CatchClause#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see DOM.CatchClause#getBody()
	 * @see #getCatchClause()
	 * @generated
	 */
	EReference getCatchClause_Body();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.CatchClause#getException <em>Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exception</em>'.
	 * @see DOM.CatchClause#getException()
	 * @see #getCatchClause()
	 * @generated
	 */
	EReference getCatchClause_Exception();

	/**
	 * Returns the meta object for class '{@link DOM.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see DOM.Comment
	 * @generated
	 */
	EClass getComment();

	/**
	 * Returns the meta object for the reference '{@link DOM.Comment#getAlternateRoot <em>Alternate Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Alternate Root</em>'.
	 * @see DOM.Comment#getAlternateRoot()
	 * @see #getComment()
	 * @generated
	 */
	EReference getComment_AlternateRoot();

	/**
	 * Returns the meta object for class '{@link DOM.CompilationUnit <em>Compilation Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compilation Unit</em>'.
	 * @see DOM.CompilationUnit
	 * @generated
	 */
	EClass getCompilationUnit();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.CompilationUnit#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Comments</em>'.
	 * @see DOM.CompilationUnit#getComments()
	 * @see #getCompilationUnit()
	 * @generated
	 */
	EReference getCompilationUnit_Comments();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.CompilationUnit#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Package</em>'.
	 * @see DOM.CompilationUnit#getPackage()
	 * @see #getCompilationUnit()
	 * @generated
	 */
	EReference getCompilationUnit_Package();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.CompilationUnit#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Imports</em>'.
	 * @see DOM.CompilationUnit#getImports()
	 * @see #getCompilationUnit()
	 * @generated
	 */
	EReference getCompilationUnit_Imports();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.CompilationUnit#getTypes <em>Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Types</em>'.
	 * @see DOM.CompilationUnit#getTypes()
	 * @see #getCompilationUnit()
	 * @generated
	 */
	EReference getCompilationUnit_Types();

	/**
	 * Returns the meta object for class '{@link DOM.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see DOM.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Expression#getResolveBoxing <em>Resolve Boxing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resolve Boxing</em>'.
	 * @see DOM.Expression#getResolveBoxing()
	 * @see #getExpression()
	 * @generated
	 */
	EAttribute getExpression_ResolveBoxing();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Expression#getResolveUnboxing <em>Resolve Unboxing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resolve Unboxing</em>'.
	 * @see DOM.Expression#getResolveUnboxing()
	 * @see #getExpression()
	 * @generated
	 */
	EAttribute getExpression_ResolveUnboxing();

	/**
	 * Returns the meta object for the reference '{@link DOM.Expression#getTypeBinding <em>Type Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type Binding</em>'.
	 * @see DOM.Expression#getTypeBinding()
	 * @see #getExpression()
	 * @generated
	 */
	EReference getExpression_TypeBinding();

	/**
	 * Returns the meta object for class '{@link DOM.ImportDeclaration <em>Import Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import Declaration</em>'.
	 * @see DOM.ImportDeclaration
	 * @generated
	 */
	EClass getImportDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link DOM.ImportDeclaration#getOnDemand <em>On Demand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>On Demand</em>'.
	 * @see DOM.ImportDeclaration#getOnDemand()
	 * @see #getImportDeclaration()
	 * @generated
	 */
	EAttribute getImportDeclaration_OnDemand();

	/**
	 * Returns the meta object for the attribute '{@link DOM.ImportDeclaration#getStatic <em>Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Static</em>'.
	 * @see DOM.ImportDeclaration#getStatic()
	 * @see #getImportDeclaration()
	 * @generated
	 */
	EAttribute getImportDeclaration_Static();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ImportDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.ImportDeclaration#getName()
	 * @see #getImportDeclaration()
	 * @generated
	 */
	EReference getImportDeclaration_Name();

	/**
	 * Returns the meta object for class '{@link DOM.MemberRef <em>Member Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Member Ref</em>'.
	 * @see DOM.MemberRef
	 * @generated
	 */
	EClass getMemberRef();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MemberRef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.MemberRef#getName()
	 * @see #getMemberRef()
	 * @generated
	 */
	EReference getMemberRef_Name();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MemberRef#getQualifier <em>Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Qualifier</em>'.
	 * @see DOM.MemberRef#getQualifier()
	 * @see #getMemberRef()
	 * @generated
	 */
	EReference getMemberRef_Qualifier();

	/**
	 * Returns the meta object for class '{@link DOM.MemberValuePair <em>Member Value Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Member Value Pair</em>'.
	 * @see DOM.MemberValuePair
	 * @generated
	 */
	EClass getMemberValuePair();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MemberValuePair#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.MemberValuePair#getName()
	 * @see #getMemberValuePair()
	 * @generated
	 */
	EReference getMemberValuePair_Name();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MemberValuePair#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see DOM.MemberValuePair#getValue()
	 * @see #getMemberValuePair()
	 * @generated
	 */
	EReference getMemberValuePair_Value();

	/**
	 * Returns the meta object for class '{@link DOM.MethodRef <em>Method Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Ref</em>'.
	 * @see DOM.MethodRef
	 * @generated
	 */
	EClass getMethodRef();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MethodRef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.MethodRef#getName()
	 * @see #getMethodRef()
	 * @generated
	 */
	EReference getMethodRef_Name();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MethodRef#getQualifier <em>Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Qualifier</em>'.
	 * @see DOM.MethodRef#getQualifier()
	 * @see #getMethodRef()
	 * @generated
	 */
	EReference getMethodRef_Qualifier();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.MethodRef#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see DOM.MethodRef#getParameters()
	 * @see #getMethodRef()
	 * @generated
	 */
	EReference getMethodRef_Parameters();

	/**
	 * Returns the meta object for class '{@link DOM.MethodRefParameter <em>Method Ref Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Ref Parameter</em>'.
	 * @see DOM.MethodRefParameter
	 * @generated
	 */
	EClass getMethodRefParameter();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MethodRefParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.MethodRefParameter#getName()
	 * @see #getMethodRefParameter()
	 * @generated
	 */
	EReference getMethodRefParameter_Name();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MethodRefParameter#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see DOM.MethodRefParameter#getType()
	 * @see #getMethodRefParameter()
	 * @generated
	 */
	EReference getMethodRefParameter_Type();

	/**
	 * Returns the meta object for the attribute '{@link DOM.MethodRefParameter#getVarargs <em>Varargs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Varargs</em>'.
	 * @see DOM.MethodRefParameter#getVarargs()
	 * @see #getMethodRefParameter()
	 * @generated
	 */
	EAttribute getMethodRefParameter_Varargs();

	/**
	 * Returns the meta object for class '{@link DOM.ExtendedModifier <em>Extended Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extended Modifier</em>'.
	 * @see DOM.ExtendedModifier
	 * @generated
	 */
	EClass getExtendedModifier();

	/**
	 * Returns the meta object for class '{@link DOM.Modifier <em>Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Modifier</em>'.
	 * @see DOM.Modifier
	 * @generated
	 */
	EClass getModifier();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see DOM.Modifier#getAbstract()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getFinal <em>Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final</em>'.
	 * @see DOM.Modifier#getFinal()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_Final();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getNative <em>Native</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Native</em>'.
	 * @see DOM.Modifier#getNative()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_Native();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getNone <em>None</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>None</em>'.
	 * @see DOM.Modifier#getNone()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_None();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getPrivate <em>Private</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Private</em>'.
	 * @see DOM.Modifier#getPrivate()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_Private();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getProtected <em>Protected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Protected</em>'.
	 * @see DOM.Modifier#getProtected()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_Protected();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getPublic <em>Public</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Public</em>'.
	 * @see DOM.Modifier#getPublic()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_Public();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getStatic <em>Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Static</em>'.
	 * @see DOM.Modifier#getStatic()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_Static();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getStrictfp <em>Strictfp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Strictfp</em>'.
	 * @see DOM.Modifier#getStrictfp()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_Strictfp();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getSynchronized <em>Synchronized</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synchronized</em>'.
	 * @see DOM.Modifier#getSynchronized()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_Synchronized();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getTransient <em>Transient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transient</em>'.
	 * @see DOM.Modifier#getTransient()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_Transient();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Modifier#getVolatile <em>Volatile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Volatile</em>'.
	 * @see DOM.Modifier#getVolatile()
	 * @see #getModifier()
	 * @generated
	 */
	EAttribute getModifier_Volatile();

	/**
	 * Returns the meta object for class '{@link DOM.PackageDeclaration <em>Package Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Declaration</em>'.
	 * @see DOM.PackageDeclaration
	 * @generated
	 */
	EClass getPackageDeclaration();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.PackageDeclaration#getAnnotations <em>Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Annotations</em>'.
	 * @see DOM.PackageDeclaration#getAnnotations()
	 * @see #getPackageDeclaration()
	 * @generated
	 */
	EReference getPackageDeclaration_Annotations();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.PackageDeclaration#getJavadoc <em>Javadoc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Javadoc</em>'.
	 * @see DOM.PackageDeclaration#getJavadoc()
	 * @see #getPackageDeclaration()
	 * @generated
	 */
	EReference getPackageDeclaration_Javadoc();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.PackageDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.PackageDeclaration#getName()
	 * @see #getPackageDeclaration()
	 * @generated
	 */
	EReference getPackageDeclaration_Name();

	/**
	 * Returns the meta object for the reference '{@link DOM.PackageDeclaration#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Binding</em>'.
	 * @see DOM.PackageDeclaration#getBinding()
	 * @see #getPackageDeclaration()
	 * @generated
	 */
	EReference getPackageDeclaration_Binding();

	/**
	 * Returns the meta object for class '{@link DOM.Statement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Statement</em>'.
	 * @see DOM.Statement
	 * @generated
	 */
	EClass getStatement();

	/**
	 * Returns the meta object for class '{@link DOM.TagElement <em>Tag Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Element</em>'.
	 * @see DOM.TagElement
	 * @generated
	 */
	EClass getTagElement();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.TagElement#getFragments <em>Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fragments</em>'.
	 * @see DOM.TagElement#getFragments()
	 * @see #getTagElement()
	 * @generated
	 */
	EReference getTagElement_Fragments();

	/**
	 * Returns the meta object for the attribute '{@link DOM.TagElement#getTagName <em>Tag Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag Name</em>'.
	 * @see DOM.TagElement#getTagName()
	 * @see #getTagElement()
	 * @generated
	 */
	EAttribute getTagElement_TagName();

	/**
	 * Returns the meta object for the attribute '{@link DOM.TagElement#getNested <em>Nested</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nested</em>'.
	 * @see DOM.TagElement#getNested()
	 * @see #getTagElement()
	 * @generated
	 */
	EAttribute getTagElement_Nested();

	/**
	 * Returns the meta object for class '{@link DOM.TextElement <em>Text Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Element</em>'.
	 * @see DOM.TextElement
	 * @generated
	 */
	EClass getTextElement();

	/**
	 * Returns the meta object for the attribute '{@link DOM.TextElement#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see DOM.TextElement#getText()
	 * @see #getTextElement()
	 * @generated
	 */
	EAttribute getTextElement_Text();

	/**
	 * Returns the meta object for class '{@link DOM.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see DOM.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for class '{@link DOM.TypeParameter <em>Type Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Parameter</em>'.
	 * @see DOM.TypeParameter
	 * @generated
	 */
	EClass getTypeParameter();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.TypeParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.TypeParameter#getName()
	 * @see #getTypeParameter()
	 * @generated
	 */
	EReference getTypeParameter_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.TypeParameter#getTypeBounds <em>Type Bounds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type Bounds</em>'.
	 * @see DOM.TypeParameter#getTypeBounds()
	 * @see #getTypeParameter()
	 * @generated
	 */
	EReference getTypeParameter_TypeBounds();

	/**
	 * Returns the meta object for class '{@link DOM.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Declaration</em>'.
	 * @see DOM.VariableDeclaration
	 * @generated
	 */
	EClass getVariableDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link DOM.VariableDeclaration#getExtraDimensions <em>Extra Dimensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extra Dimensions</em>'.
	 * @see DOM.VariableDeclaration#getExtraDimensions()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EAttribute getVariableDeclaration_ExtraDimensions();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.VariableDeclaration#getInitializer <em>Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Initializer</em>'.
	 * @see DOM.VariableDeclaration#getInitializer()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EReference getVariableDeclaration_Initializer();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.VariableDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.VariableDeclaration#getName()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EReference getVariableDeclaration_Name();

	/**
	 * Returns the meta object for class '{@link DOM.AbstractTypeDeclaration <em>Abstract Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Type Declaration</em>'.
	 * @see DOM.AbstractTypeDeclaration
	 * @generated
	 */
	EClass getAbstractTypeDeclaration();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.AbstractTypeDeclaration#getBodyDeclarations <em>Body Declarations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Body Declarations</em>'.
	 * @see DOM.AbstractTypeDeclaration#getBodyDeclarations()
	 * @see #getAbstractTypeDeclaration()
	 * @generated
	 */
	EReference getAbstractTypeDeclaration_BodyDeclarations();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.AbstractTypeDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.AbstractTypeDeclaration#getName()
	 * @see #getAbstractTypeDeclaration()
	 * @generated
	 */
	EReference getAbstractTypeDeclaration_Name();

	/**
	 * Returns the meta object for the attribute '{@link DOM.AbstractTypeDeclaration#getLocalTypeDeclaration <em>Local Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local Type Declaration</em>'.
	 * @see DOM.AbstractTypeDeclaration#getLocalTypeDeclaration()
	 * @see #getAbstractTypeDeclaration()
	 * @generated
	 */
	EAttribute getAbstractTypeDeclaration_LocalTypeDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link DOM.AbstractTypeDeclaration#getMemberTypeDeclaration <em>Member Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Member Type Declaration</em>'.
	 * @see DOM.AbstractTypeDeclaration#getMemberTypeDeclaration()
	 * @see #getAbstractTypeDeclaration()
	 * @generated
	 */
	EAttribute getAbstractTypeDeclaration_MemberTypeDeclaration();

	/**
	 * Returns the meta object for the attribute '{@link DOM.AbstractTypeDeclaration#getPackageMemberTypeDeclaration <em>Package Member Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Member Type Declaration</em>'.
	 * @see DOM.AbstractTypeDeclaration#getPackageMemberTypeDeclaration()
	 * @see #getAbstractTypeDeclaration()
	 * @generated
	 */
	EAttribute getAbstractTypeDeclaration_PackageMemberTypeDeclaration();

	/**
	 * Returns the meta object for class '{@link DOM.AnnotationTypeMemberDeclaration <em>Annotation Type Member Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation Type Member Declaration</em>'.
	 * @see DOM.AnnotationTypeMemberDeclaration
	 * @generated
	 */
	EClass getAnnotationTypeMemberDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.AnnotationTypeMemberDeclaration#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Default</em>'.
	 * @see DOM.AnnotationTypeMemberDeclaration#getDefault()
	 * @see #getAnnotationTypeMemberDeclaration()
	 * @generated
	 */
	EReference getAnnotationTypeMemberDeclaration_Default();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.AnnotationTypeMemberDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.AnnotationTypeMemberDeclaration#getName()
	 * @see #getAnnotationTypeMemberDeclaration()
	 * @generated
	 */
	EReference getAnnotationTypeMemberDeclaration_Name();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.AnnotationTypeMemberDeclaration#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see DOM.AnnotationTypeMemberDeclaration#getType()
	 * @see #getAnnotationTypeMemberDeclaration()
	 * @generated
	 */
	EReference getAnnotationTypeMemberDeclaration_Type();

	/**
	 * Returns the meta object for class '{@link DOM.EnumConstantDeclaration <em>Enum Constant Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Constant Declaration</em>'.
	 * @see DOM.EnumConstantDeclaration
	 * @generated
	 */
	EClass getEnumConstantDeclaration();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.EnumConstantDeclaration#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see DOM.EnumConstantDeclaration#getArguments()
	 * @see #getEnumConstantDeclaration()
	 * @generated
	 */
	EReference getEnumConstantDeclaration_Arguments();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.EnumConstantDeclaration#getAnonymousClassDeclaration <em>Anonymous Class Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Anonymous Class Declaration</em>'.
	 * @see DOM.EnumConstantDeclaration#getAnonymousClassDeclaration()
	 * @see #getEnumConstantDeclaration()
	 * @generated
	 */
	EReference getEnumConstantDeclaration_AnonymousClassDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.EnumConstantDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.EnumConstantDeclaration#getName()
	 * @see #getEnumConstantDeclaration()
	 * @generated
	 */
	EReference getEnumConstantDeclaration_Name();

	/**
	 * Returns the meta object for class '{@link DOM.FieldDeclaration <em>Field Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Declaration</em>'.
	 * @see DOM.FieldDeclaration
	 * @generated
	 */
	EClass getFieldDeclaration();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.FieldDeclaration#getFragments <em>Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fragments</em>'.
	 * @see DOM.FieldDeclaration#getFragments()
	 * @see #getFieldDeclaration()
	 * @generated
	 */
	EReference getFieldDeclaration_Fragments();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.FieldDeclaration#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see DOM.FieldDeclaration#getType()
	 * @see #getFieldDeclaration()
	 * @generated
	 */
	EReference getFieldDeclaration_Type();

	/**
	 * Returns the meta object for class '{@link DOM.Initializer <em>Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Initializer</em>'.
	 * @see DOM.Initializer
	 * @generated
	 */
	EClass getInitializer();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.Initializer#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see DOM.Initializer#getBody()
	 * @see #getInitializer()
	 * @generated
	 */
	EReference getInitializer_Body();

	/**
	 * Returns the meta object for class '{@link DOM.MethodDeclaration <em>Method Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Declaration</em>'.
	 * @see DOM.MethodDeclaration
	 * @generated
	 */
	EClass getMethodDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MethodDeclaration#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see DOM.MethodDeclaration#getBody()
	 * @see #getMethodDeclaration()
	 * @generated
	 */
	EReference getMethodDeclaration_Body();

	/**
	 * Returns the meta object for the attribute '{@link DOM.MethodDeclaration#getExtraDimensions <em>Extra Dimensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extra Dimensions</em>'.
	 * @see DOM.MethodDeclaration#getExtraDimensions()
	 * @see #getMethodDeclaration()
	 * @generated
	 */
	EAttribute getMethodDeclaration_ExtraDimensions();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MethodDeclaration#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.MethodDeclaration#getName()
	 * @see #getMethodDeclaration()
	 * @generated
	 */
	EReference getMethodDeclaration_Name();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MethodDeclaration#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Return Type</em>'.
	 * @see DOM.MethodDeclaration#getReturnType()
	 * @see #getMethodDeclaration()
	 * @generated
	 */
	EReference getMethodDeclaration_ReturnType();

	/**
	 * Returns the meta object for the attribute '{@link DOM.MethodDeclaration#getConstructor <em>Constructor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constructor</em>'.
	 * @see DOM.MethodDeclaration#getConstructor()
	 * @see #getMethodDeclaration()
	 * @generated
	 */
	EAttribute getMethodDeclaration_Constructor();

	/**
	 * Returns the meta object for the attribute '{@link DOM.MethodDeclaration#getVarargs <em>Varargs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Varargs</em>'.
	 * @see DOM.MethodDeclaration#getVarargs()
	 * @see #getMethodDeclaration()
	 * @generated
	 */
	EAttribute getMethodDeclaration_Varargs();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.MethodDeclaration#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see DOM.MethodDeclaration#getParameters()
	 * @see #getMethodDeclaration()
	 * @generated
	 */
	EReference getMethodDeclaration_Parameters();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.MethodDeclaration#getThrownExceptions <em>Thrown Exceptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Thrown Exceptions</em>'.
	 * @see DOM.MethodDeclaration#getThrownExceptions()
	 * @see #getMethodDeclaration()
	 * @generated
	 */
	EReference getMethodDeclaration_ThrownExceptions();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.MethodDeclaration#getTypeParameters <em>Type Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type Parameters</em>'.
	 * @see DOM.MethodDeclaration#getTypeParameters()
	 * @see #getMethodDeclaration()
	 * @generated
	 */
	EReference getMethodDeclaration_TypeParameters();

	/**
	 * Returns the meta object for the reference '{@link DOM.MethodDeclaration#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Binding</em>'.
	 * @see DOM.MethodDeclaration#getBinding()
	 * @see #getMethodDeclaration()
	 * @generated
	 */
	EReference getMethodDeclaration_Binding();

	/**
	 * Returns the meta object for class '{@link DOM.AnnotationTypeDeclaration <em>Annotation Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation Type Declaration</em>'.
	 * @see DOM.AnnotationTypeDeclaration
	 * @generated
	 */
	EClass getAnnotationTypeDeclaration();

	/**
	 * Returns the meta object for class '{@link DOM.EnumDeclaration <em>Enum Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Declaration</em>'.
	 * @see DOM.EnumDeclaration
	 * @generated
	 */
	EClass getEnumDeclaration();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.EnumDeclaration#getSuperInterfaceTypes <em>Super Interface Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Super Interface Types</em>'.
	 * @see DOM.EnumDeclaration#getSuperInterfaceTypes()
	 * @see #getEnumDeclaration()
	 * @generated
	 */
	EReference getEnumDeclaration_SuperInterfaceTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.EnumDeclaration#getEnumConstants <em>Enum Constants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Enum Constants</em>'.
	 * @see DOM.EnumDeclaration#getEnumConstants()
	 * @see #getEnumDeclaration()
	 * @generated
	 */
	EReference getEnumDeclaration_EnumConstants();

	/**
	 * Returns the meta object for class '{@link DOM.TypeDeclaration <em>Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Declaration</em>'.
	 * @see DOM.TypeDeclaration
	 * @generated
	 */
	EClass getTypeDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.TypeDeclaration#getSuperclassType <em>Superclass Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Superclass Type</em>'.
	 * @see DOM.TypeDeclaration#getSuperclassType()
	 * @see #getTypeDeclaration()
	 * @generated
	 */
	EReference getTypeDeclaration_SuperclassType();

	/**
	 * Returns the meta object for the attribute '{@link DOM.TypeDeclaration#getInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interface</em>'.
	 * @see DOM.TypeDeclaration#getInterface()
	 * @see #getTypeDeclaration()
	 * @generated
	 */
	EAttribute getTypeDeclaration_Interface();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.TypeDeclaration#getSuperInterfaceTypes <em>Super Interface Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Super Interface Types</em>'.
	 * @see DOM.TypeDeclaration#getSuperInterfaceTypes()
	 * @see #getTypeDeclaration()
	 * @generated
	 */
	EReference getTypeDeclaration_SuperInterfaceTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.TypeDeclaration#getTypeParameters <em>Type Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type Parameters</em>'.
	 * @see DOM.TypeDeclaration#getTypeParameters()
	 * @see #getTypeDeclaration()
	 * @generated
	 */
	EReference getTypeDeclaration_TypeParameters();

	/**
	 * Returns the meta object for class '{@link DOM.BlockComment <em>Block Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block Comment</em>'.
	 * @see DOM.BlockComment
	 * @generated
	 */
	EClass getBlockComment();

	/**
	 * Returns the meta object for class '{@link DOM.Javadoc <em>Javadoc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Javadoc</em>'.
	 * @see DOM.Javadoc
	 * @generated
	 */
	EClass getJavadoc();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.Javadoc#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see DOM.Javadoc#getTags()
	 * @see #getJavadoc()
	 * @generated
	 */
	EReference getJavadoc_Tags();

	/**
	 * Returns the meta object for class '{@link DOM.LineComment <em>Line Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Line Comment</em>'.
	 * @see DOM.LineComment
	 * @generated
	 */
	EClass getLineComment();

	/**
	 * Returns the meta object for class '{@link DOM.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation</em>'.
	 * @see DOM.Annotation
	 * @generated
	 */
	EClass getAnnotation();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.Annotation#getTypeName <em>Type Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type Name</em>'.
	 * @see DOM.Annotation#getTypeName()
	 * @see #getAnnotation()
	 * @generated
	 */
	EReference getAnnotation_TypeName();

	/**
	 * Returns the meta object for class '{@link DOM.ArrayAccess <em>Array Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Access</em>'.
	 * @see DOM.ArrayAccess
	 * @generated
	 */
	EClass getArrayAccess();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ArrayAccess#getArray <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Array</em>'.
	 * @see DOM.ArrayAccess#getArray()
	 * @see #getArrayAccess()
	 * @generated
	 */
	EReference getArrayAccess_Array();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ArrayAccess#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Index</em>'.
	 * @see DOM.ArrayAccess#getIndex()
	 * @see #getArrayAccess()
	 * @generated
	 */
	EReference getArrayAccess_Index();

	/**
	 * Returns the meta object for class '{@link DOM.ArrayCreation <em>Array Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Creation</em>'.
	 * @see DOM.ArrayCreation
	 * @generated
	 */
	EClass getArrayCreation();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.ArrayCreation#getDimensions <em>Dimensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dimensions</em>'.
	 * @see DOM.ArrayCreation#getDimensions()
	 * @see #getArrayCreation()
	 * @generated
	 */
	EReference getArrayCreation_Dimensions();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ArrayCreation#getInitializer <em>Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Initializer</em>'.
	 * @see DOM.ArrayCreation#getInitializer()
	 * @see #getArrayCreation()
	 * @generated
	 */
	EReference getArrayCreation_Initializer();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ArrayCreation#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see DOM.ArrayCreation#getType()
	 * @see #getArrayCreation()
	 * @generated
	 */
	EReference getArrayCreation_Type();

	/**
	 * Returns the meta object for class '{@link DOM.ArrayInitializer <em>Array Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Initializer</em>'.
	 * @see DOM.ArrayInitializer
	 * @generated
	 */
	EClass getArrayInitializer();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.ArrayInitializer#getExpressions <em>Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Expressions</em>'.
	 * @see DOM.ArrayInitializer#getExpressions()
	 * @see #getArrayInitializer()
	 * @generated
	 */
	EReference getArrayInitializer_Expressions();

	/**
	 * Returns the meta object for class '{@link DOM.Assignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assignment</em>'.
	 * @see DOM.Assignment
	 * @generated
	 */
	EClass getAssignment();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.Assignment#getLeftHandSide <em>Left Hand Side</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Hand Side</em>'.
	 * @see DOM.Assignment#getLeftHandSide()
	 * @see #getAssignment()
	 * @generated
	 */
	EReference getAssignment_LeftHandSide();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Assignment#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see DOM.Assignment#getOperator()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.Assignment#getRightHandSide <em>Right Hand Side</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Hand Side</em>'.
	 * @see DOM.Assignment#getRightHandSide()
	 * @see #getAssignment()
	 * @generated
	 */
	EReference getAssignment_RightHandSide();

	/**
	 * Returns the meta object for class '{@link DOM.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Literal</em>'.
	 * @see DOM.BooleanLiteral
	 * @generated
	 */
	EClass getBooleanLiteral();

	/**
	 * Returns the meta object for the attribute '{@link DOM.BooleanLiteral#getBooleanValue <em>Boolean Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Value</em>'.
	 * @see DOM.BooleanLiteral#getBooleanValue()
	 * @see #getBooleanLiteral()
	 * @generated
	 */
	EAttribute getBooleanLiteral_BooleanValue();

	/**
	 * Returns the meta object for class '{@link DOM.CastExpression <em>Cast Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cast Expression</em>'.
	 * @see DOM.CastExpression
	 * @generated
	 */
	EClass getCastExpression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.CastExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.CastExpression#getExpression()
	 * @see #getCastExpression()
	 * @generated
	 */
	EReference getCastExpression_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.CastExpression#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see DOM.CastExpression#getType()
	 * @see #getCastExpression()
	 * @generated
	 */
	EReference getCastExpression_Type();

	/**
	 * Returns the meta object for class '{@link DOM.CharacterLiteral <em>Character Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Character Literal</em>'.
	 * @see DOM.CharacterLiteral
	 * @generated
	 */
	EClass getCharacterLiteral();

	/**
	 * Returns the meta object for the attribute '{@link DOM.CharacterLiteral#getCharValue <em>Char Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Char Value</em>'.
	 * @see DOM.CharacterLiteral#getCharValue()
	 * @see #getCharacterLiteral()
	 * @generated
	 */
	EAttribute getCharacterLiteral_CharValue();

	/**
	 * Returns the meta object for the attribute '{@link DOM.CharacterLiteral#getEscapedValue <em>Escaped Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Escaped Value</em>'.
	 * @see DOM.CharacterLiteral#getEscapedValue()
	 * @see #getCharacterLiteral()
	 * @generated
	 */
	EAttribute getCharacterLiteral_EscapedValue();

	/**
	 * Returns the meta object for class '{@link DOM.ClassInstanceCreation <em>Class Instance Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class Instance Creation</em>'.
	 * @see DOM.ClassInstanceCreation
	 * @generated
	 */
	EClass getClassInstanceCreation();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.ClassInstanceCreation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see DOM.ClassInstanceCreation#getArguments()
	 * @see #getClassInstanceCreation()
	 * @generated
	 */
	EReference getClassInstanceCreation_Arguments();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ClassInstanceCreation#getAnonymousClassDeclaration <em>Anonymous Class Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Anonymous Class Declaration</em>'.
	 * @see DOM.ClassInstanceCreation#getAnonymousClassDeclaration()
	 * @see #getClassInstanceCreation()
	 * @generated
	 */
	EReference getClassInstanceCreation_AnonymousClassDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ClassInstanceCreation#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.ClassInstanceCreation#getExpression()
	 * @see #getClassInstanceCreation()
	 * @generated
	 */
	EReference getClassInstanceCreation_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ClassInstanceCreation#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see DOM.ClassInstanceCreation#getType()
	 * @see #getClassInstanceCreation()
	 * @generated
	 */
	EReference getClassInstanceCreation_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.ClassInstanceCreation#getTypeArguments <em>Type Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type Arguments</em>'.
	 * @see DOM.ClassInstanceCreation#getTypeArguments()
	 * @see #getClassInstanceCreation()
	 * @generated
	 */
	EReference getClassInstanceCreation_TypeArguments();

	/**
	 * Returns the meta object for class '{@link DOM.ConditionalExpression <em>Conditional Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conditional Expression</em>'.
	 * @see DOM.ConditionalExpression
	 * @generated
	 */
	EClass getConditionalExpression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ConditionalExpression#getElseExpression <em>Else Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Else Expression</em>'.
	 * @see DOM.ConditionalExpression#getElseExpression()
	 * @see #getConditionalExpression()
	 * @generated
	 */
	EReference getConditionalExpression_ElseExpression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ConditionalExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.ConditionalExpression#getExpression()
	 * @see #getConditionalExpression()
	 * @generated
	 */
	EReference getConditionalExpression_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ConditionalExpression#getThenExpression <em>Then Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Then Expression</em>'.
	 * @see DOM.ConditionalExpression#getThenExpression()
	 * @see #getConditionalExpression()
	 * @generated
	 */
	EReference getConditionalExpression_ThenExpression();

	/**
	 * Returns the meta object for class '{@link DOM.FieldAccess <em>Field Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field Access</em>'.
	 * @see DOM.FieldAccess
	 * @generated
	 */
	EClass getFieldAccess();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.FieldAccess#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.FieldAccess#getExpression()
	 * @see #getFieldAccess()
	 * @generated
	 */
	EReference getFieldAccess_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.FieldAccess#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.FieldAccess#getName()
	 * @see #getFieldAccess()
	 * @generated
	 */
	EReference getFieldAccess_Name();

	/**
	 * Returns the meta object for class '{@link DOM.InfixExpression <em>Infix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Infix Expression</em>'.
	 * @see DOM.InfixExpression
	 * @generated
	 */
	EClass getInfixExpression();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.InfixExpression#getExtendedOperands <em>Extended Operands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extended Operands</em>'.
	 * @see DOM.InfixExpression#getExtendedOperands()
	 * @see #getInfixExpression()
	 * @generated
	 */
	EReference getInfixExpression_ExtendedOperands();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.InfixExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Operand</em>'.
	 * @see DOM.InfixExpression#getLeftOperand()
	 * @see #getInfixExpression()
	 * @generated
	 */
	EReference getInfixExpression_LeftOperand();

	/**
	 * Returns the meta object for the attribute '{@link DOM.InfixExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see DOM.InfixExpression#getOperator()
	 * @see #getInfixExpression()
	 * @generated
	 */
	EAttribute getInfixExpression_Operator();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.InfixExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Operand</em>'.
	 * @see DOM.InfixExpression#getRightOperand()
	 * @see #getInfixExpression()
	 * @generated
	 */
	EReference getInfixExpression_RightOperand();

	/**
	 * Returns the meta object for class '{@link DOM.InstanceofExpression <em>Instanceof Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instanceof Expression</em>'.
	 * @see DOM.InstanceofExpression
	 * @generated
	 */
	EClass getInstanceofExpression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.InstanceofExpression#getLeftOperand <em>Left Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Operand</em>'.
	 * @see DOM.InstanceofExpression#getLeftOperand()
	 * @see #getInstanceofExpression()
	 * @generated
	 */
	EReference getInstanceofExpression_LeftOperand();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.InstanceofExpression#getRightOperand <em>Right Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Operand</em>'.
	 * @see DOM.InstanceofExpression#getRightOperand()
	 * @see #getInstanceofExpression()
	 * @generated
	 */
	EReference getInstanceofExpression_RightOperand();

	/**
	 * Returns the meta object for class '{@link DOM.MethodInvocation <em>Method Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Invocation</em>'.
	 * @see DOM.MethodInvocation
	 * @generated
	 */
	EClass getMethodInvocation();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.MethodInvocation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see DOM.MethodInvocation#getArguments()
	 * @see #getMethodInvocation()
	 * @generated
	 */
	EReference getMethodInvocation_Arguments();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MethodInvocation#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.MethodInvocation#getExpression()
	 * @see #getMethodInvocation()
	 * @generated
	 */
	EReference getMethodInvocation_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.MethodInvocation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.MethodInvocation#getName()
	 * @see #getMethodInvocation()
	 * @generated
	 */
	EReference getMethodInvocation_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.MethodInvocation#getTypeArguments <em>Type Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type Arguments</em>'.
	 * @see DOM.MethodInvocation#getTypeArguments()
	 * @see #getMethodInvocation()
	 * @generated
	 */
	EReference getMethodInvocation_TypeArguments();

	/**
	 * Returns the meta object for the reference '{@link DOM.MethodInvocation#getMethodBinding <em>Method Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Method Binding</em>'.
	 * @see DOM.MethodInvocation#getMethodBinding()
	 * @see #getMethodInvocation()
	 * @generated
	 */
	EReference getMethodInvocation_MethodBinding();

	/**
	 * Returns the meta object for class '{@link DOM.Name <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Name</em>'.
	 * @see DOM.Name
	 * @generated
	 */
	EClass getName_();

	/**
	 * Returns the meta object for the attribute '{@link DOM.Name#getFullyQualifiedName <em>Fully Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fully Qualified Name</em>'.
	 * @see DOM.Name#getFullyQualifiedName()
	 * @see #getName_()
	 * @generated
	 */
	EAttribute getName_FullyQualifiedName();

	/**
	 * Returns the meta object for class '{@link DOM.NullLiteral <em>Null Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Null Literal</em>'.
	 * @see DOM.NullLiteral
	 * @generated
	 */
	EClass getNullLiteral();

	/**
	 * Returns the meta object for class '{@link DOM.NumberLiteral <em>Number Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Number Literal</em>'.
	 * @see DOM.NumberLiteral
	 * @generated
	 */
	EClass getNumberLiteral();

	/**
	 * Returns the meta object for the attribute '{@link DOM.NumberLiteral#getToken <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Token</em>'.
	 * @see DOM.NumberLiteral#getToken()
	 * @see #getNumberLiteral()
	 * @generated
	 */
	EAttribute getNumberLiteral_Token();

	/**
	 * Returns the meta object for class '{@link DOM.ParenthesizedExpression <em>Parenthesized Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parenthesized Expression</em>'.
	 * @see DOM.ParenthesizedExpression
	 * @generated
	 */
	EClass getParenthesizedExpression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ParenthesizedExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.ParenthesizedExpression#getExpression()
	 * @see #getParenthesizedExpression()
	 * @generated
	 */
	EReference getParenthesizedExpression_Expression();

	/**
	 * Returns the meta object for class '{@link DOM.PostfixExpression <em>Postfix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Postfix Expression</em>'.
	 * @see DOM.PostfixExpression
	 * @generated
	 */
	EClass getPostfixExpression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.PostfixExpression#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operand</em>'.
	 * @see DOM.PostfixExpression#getOperand()
	 * @see #getPostfixExpression()
	 * @generated
	 */
	EReference getPostfixExpression_Operand();

	/**
	 * Returns the meta object for the attribute '{@link DOM.PostfixExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see DOM.PostfixExpression#getOperator()
	 * @see #getPostfixExpression()
	 * @generated
	 */
	EAttribute getPostfixExpression_Operator();

	/**
	 * Returns the meta object for class '{@link DOM.PrefixExpression <em>Prefix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Prefix Expression</em>'.
	 * @see DOM.PrefixExpression
	 * @generated
	 */
	EClass getPrefixExpression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.PrefixExpression#getOperand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Operand</em>'.
	 * @see DOM.PrefixExpression#getOperand()
	 * @see #getPrefixExpression()
	 * @generated
	 */
	EReference getPrefixExpression_Operand();

	/**
	 * Returns the meta object for the attribute '{@link DOM.PrefixExpression#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see DOM.PrefixExpression#getOperator()
	 * @see #getPrefixExpression()
	 * @generated
	 */
	EAttribute getPrefixExpression_Operator();

	/**
	 * Returns the meta object for class '{@link DOM.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Literal</em>'.
	 * @see DOM.StringLiteral
	 * @generated
	 */
	EClass getStringLiteral();

	/**
	 * Returns the meta object for the attribute '{@link DOM.StringLiteral#getEscapedValue <em>Escaped Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Escaped Value</em>'.
	 * @see DOM.StringLiteral#getEscapedValue()
	 * @see #getStringLiteral()
	 * @generated
	 */
	EAttribute getStringLiteral_EscapedValue();

	/**
	 * Returns the meta object for the attribute '{@link DOM.StringLiteral#getLiteralValue <em>Literal Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Literal Value</em>'.
	 * @see DOM.StringLiteral#getLiteralValue()
	 * @see #getStringLiteral()
	 * @generated
	 */
	EAttribute getStringLiteral_LiteralValue();

	/**
	 * Returns the meta object for class '{@link DOM.SuperFieldAccess <em>Super Field Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Super Field Access</em>'.
	 * @see DOM.SuperFieldAccess
	 * @generated
	 */
	EClass getSuperFieldAccess();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SuperFieldAccess#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.SuperFieldAccess#getName()
	 * @see #getSuperFieldAccess()
	 * @generated
	 */
	EReference getSuperFieldAccess_Name();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SuperFieldAccess#getQualifier <em>Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Qualifier</em>'.
	 * @see DOM.SuperFieldAccess#getQualifier()
	 * @see #getSuperFieldAccess()
	 * @generated
	 */
	EReference getSuperFieldAccess_Qualifier();

	/**
	 * Returns the meta object for class '{@link DOM.SuperMethodInvocation <em>Super Method Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Super Method Invocation</em>'.
	 * @see DOM.SuperMethodInvocation
	 * @generated
	 */
	EClass getSuperMethodInvocation();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.SuperMethodInvocation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see DOM.SuperMethodInvocation#getArguments()
	 * @see #getSuperMethodInvocation()
	 * @generated
	 */
	EReference getSuperMethodInvocation_Arguments();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SuperMethodInvocation#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.SuperMethodInvocation#getName()
	 * @see #getSuperMethodInvocation()
	 * @generated
	 */
	EReference getSuperMethodInvocation_Name();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SuperMethodInvocation#getQualifier <em>Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Qualifier</em>'.
	 * @see DOM.SuperMethodInvocation#getQualifier()
	 * @see #getSuperMethodInvocation()
	 * @generated
	 */
	EReference getSuperMethodInvocation_Qualifier();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.SuperMethodInvocation#getTypeArguments <em>Type Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type Arguments</em>'.
	 * @see DOM.SuperMethodInvocation#getTypeArguments()
	 * @see #getSuperMethodInvocation()
	 * @generated
	 */
	EReference getSuperMethodInvocation_TypeArguments();

	/**
	 * Returns the meta object for class '{@link DOM.ThisExpression <em>This Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>This Expression</em>'.
	 * @see DOM.ThisExpression
	 * @generated
	 */
	EClass getThisExpression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ThisExpression#getQualifier <em>Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Qualifier</em>'.
	 * @see DOM.ThisExpression#getQualifier()
	 * @see #getThisExpression()
	 * @generated
	 */
	EReference getThisExpression_Qualifier();

	/**
	 * Returns the meta object for class '{@link DOM.TypeLiteral <em>Type Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Literal</em>'.
	 * @see DOM.TypeLiteral
	 * @generated
	 */
	EClass getTypeLiteral();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.TypeLiteral#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see DOM.TypeLiteral#getType()
	 * @see #getTypeLiteral()
	 * @generated
	 */
	EReference getTypeLiteral_Type();

	/**
	 * Returns the meta object for class '{@link DOM.VariableDeclarationExpression <em>Variable Declaration Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Declaration Expression</em>'.
	 * @see DOM.VariableDeclarationExpression
	 * @generated
	 */
	EClass getVariableDeclarationExpression();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.VariableDeclarationExpression#getFragments <em>Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fragments</em>'.
	 * @see DOM.VariableDeclarationExpression#getFragments()
	 * @see #getVariableDeclarationExpression()
	 * @generated
	 */
	EReference getVariableDeclarationExpression_Fragments();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.VariableDeclarationExpression#getModifiers <em>Modifiers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Modifiers</em>'.
	 * @see DOM.VariableDeclarationExpression#getModifiers()
	 * @see #getVariableDeclarationExpression()
	 * @generated
	 */
	EReference getVariableDeclarationExpression_Modifiers();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.VariableDeclarationExpression#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see DOM.VariableDeclarationExpression#getType()
	 * @see #getVariableDeclarationExpression()
	 * @generated
	 */
	EReference getVariableDeclarationExpression_Type();

	/**
	 * Returns the meta object for class '{@link DOM.AssertStatement <em>Assert Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assert Statement</em>'.
	 * @see DOM.AssertStatement
	 * @generated
	 */
	EClass getAssertStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.AssertStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.AssertStatement#getExpression()
	 * @see #getAssertStatement()
	 * @generated
	 */
	EReference getAssertStatement_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.AssertStatement#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Message</em>'.
	 * @see DOM.AssertStatement#getMessage()
	 * @see #getAssertStatement()
	 * @generated
	 */
	EReference getAssertStatement_Message();

	/**
	 * Returns the meta object for class '{@link DOM.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block</em>'.
	 * @see DOM.Block
	 * @generated
	 */
	EClass getBlock();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.Block#getStatements <em>Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Statements</em>'.
	 * @see DOM.Block#getStatements()
	 * @see #getBlock()
	 * @generated
	 */
	EReference getBlock_Statements();

	/**
	 * Returns the meta object for class '{@link DOM.BreakStatement <em>Break Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Break Statement</em>'.
	 * @see DOM.BreakStatement
	 * @generated
	 */
	EClass getBreakStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.BreakStatement#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label</em>'.
	 * @see DOM.BreakStatement#getLabel()
	 * @see #getBreakStatement()
	 * @generated
	 */
	EReference getBreakStatement_Label();

	/**
	 * Returns the meta object for class '{@link DOM.ConstructorInvocation <em>Constructor Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constructor Invocation</em>'.
	 * @see DOM.ConstructorInvocation
	 * @generated
	 */
	EClass getConstructorInvocation();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.ConstructorInvocation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see DOM.ConstructorInvocation#getArguments()
	 * @see #getConstructorInvocation()
	 * @generated
	 */
	EReference getConstructorInvocation_Arguments();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.ConstructorInvocation#getTypeArguments <em>Type Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type Arguments</em>'.
	 * @see DOM.ConstructorInvocation#getTypeArguments()
	 * @see #getConstructorInvocation()
	 * @generated
	 */
	EReference getConstructorInvocation_TypeArguments();

	/**
	 * Returns the meta object for class '{@link DOM.ContinueStatement <em>Continue Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Continue Statement</em>'.
	 * @see DOM.ContinueStatement
	 * @generated
	 */
	EClass getContinueStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ContinueStatement#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label</em>'.
	 * @see DOM.ContinueStatement#getLabel()
	 * @see #getContinueStatement()
	 * @generated
	 */
	EReference getContinueStatement_Label();

	/**
	 * Returns the meta object for class '{@link DOM.DoStatement <em>Do Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Do Statement</em>'.
	 * @see DOM.DoStatement
	 * @generated
	 */
	EClass getDoStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.DoStatement#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see DOM.DoStatement#getBody()
	 * @see #getDoStatement()
	 * @generated
	 */
	EReference getDoStatement_Body();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.DoStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.DoStatement#getExpression()
	 * @see #getDoStatement()
	 * @generated
	 */
	EReference getDoStatement_Expression();

	/**
	 * Returns the meta object for class '{@link DOM.EmptyStatement <em>Empty Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Empty Statement</em>'.
	 * @see DOM.EmptyStatement
	 * @generated
	 */
	EClass getEmptyStatement();

	/**
	 * Returns the meta object for class '{@link DOM.EnhancedForStatement <em>Enhanced For Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enhanced For Statement</em>'.
	 * @see DOM.EnhancedForStatement
	 * @generated
	 */
	EClass getEnhancedForStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.EnhancedForStatement#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see DOM.EnhancedForStatement#getBody()
	 * @see #getEnhancedForStatement()
	 * @generated
	 */
	EReference getEnhancedForStatement_Body();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.EnhancedForStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.EnhancedForStatement#getExpression()
	 * @see #getEnhancedForStatement()
	 * @generated
	 */
	EReference getEnhancedForStatement_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.EnhancedForStatement#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Parameter</em>'.
	 * @see DOM.EnhancedForStatement#getParameter()
	 * @see #getEnhancedForStatement()
	 * @generated
	 */
	EReference getEnhancedForStatement_Parameter();

	/**
	 * Returns the meta object for class '{@link DOM.ExpressionStatement <em>Expression Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression Statement</em>'.
	 * @see DOM.ExpressionStatement
	 * @generated
	 */
	EClass getExpressionStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ExpressionStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.ExpressionStatement#getExpression()
	 * @see #getExpressionStatement()
	 * @generated
	 */
	EReference getExpressionStatement_Expression();

	/**
	 * Returns the meta object for class '{@link DOM.ForStatement <em>For Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>For Statement</em>'.
	 * @see DOM.ForStatement
	 * @generated
	 */
	EClass getForStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ForStatement#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see DOM.ForStatement#getBody()
	 * @see #getForStatement()
	 * @generated
	 */
	EReference getForStatement_Body();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ForStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.ForStatement#getExpression()
	 * @see #getForStatement()
	 * @generated
	 */
	EReference getForStatement_Expression();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.ForStatement#getInitializers <em>Initializers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Initializers</em>'.
	 * @see DOM.ForStatement#getInitializers()
	 * @see #getForStatement()
	 * @generated
	 */
	EReference getForStatement_Initializers();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.ForStatement#getUpdaters <em>Updaters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Updaters</em>'.
	 * @see DOM.ForStatement#getUpdaters()
	 * @see #getForStatement()
	 * @generated
	 */
	EReference getForStatement_Updaters();

	/**
	 * Returns the meta object for class '{@link DOM.IfStatement <em>If Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Statement</em>'.
	 * @see DOM.IfStatement
	 * @generated
	 */
	EClass getIfStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.IfStatement#getElseStatement <em>Else Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Else Statement</em>'.
	 * @see DOM.IfStatement#getElseStatement()
	 * @see #getIfStatement()
	 * @generated
	 */
	EReference getIfStatement_ElseStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.IfStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.IfStatement#getExpression()
	 * @see #getIfStatement()
	 * @generated
	 */
	EReference getIfStatement_Expression();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.IfStatement#getThenStatement <em>Then Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Then Statement</em>'.
	 * @see DOM.IfStatement#getThenStatement()
	 * @see #getIfStatement()
	 * @generated
	 */
	EReference getIfStatement_ThenStatement();

	/**
	 * Returns the meta object for class '{@link DOM.LabeledStatement <em>Labeled Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Labeled Statement</em>'.
	 * @see DOM.LabeledStatement
	 * @generated
	 */
	EClass getLabeledStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.LabeledStatement#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see DOM.LabeledStatement#getBody()
	 * @see #getLabeledStatement()
	 * @generated
	 */
	EReference getLabeledStatement_Body();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.LabeledStatement#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label</em>'.
	 * @see DOM.LabeledStatement#getLabel()
	 * @see #getLabeledStatement()
	 * @generated
	 */
	EReference getLabeledStatement_Label();

	/**
	 * Returns the meta object for class '{@link DOM.ReturnStatement <em>Return Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Return Statement</em>'.
	 * @see DOM.ReturnStatement
	 * @generated
	 */
	EClass getReturnStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ReturnStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.ReturnStatement#getExpression()
	 * @see #getReturnStatement()
	 * @generated
	 */
	EReference getReturnStatement_Expression();

	/**
	 * Returns the meta object for class '{@link DOM.SuperConstructorInvocation <em>Super Constructor Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Super Constructor Invocation</em>'.
	 * @see DOM.SuperConstructorInvocation
	 * @generated
	 */
	EClass getSuperConstructorInvocation();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.SuperConstructorInvocation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see DOM.SuperConstructorInvocation#getArguments()
	 * @see #getSuperConstructorInvocation()
	 * @generated
	 */
	EReference getSuperConstructorInvocation_Arguments();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SuperConstructorInvocation#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.SuperConstructorInvocation#getExpression()
	 * @see #getSuperConstructorInvocation()
	 * @generated
	 */
	EReference getSuperConstructorInvocation_Expression();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.SuperConstructorInvocation#getTypeArguments <em>Type Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type Arguments</em>'.
	 * @see DOM.SuperConstructorInvocation#getTypeArguments()
	 * @see #getSuperConstructorInvocation()
	 * @generated
	 */
	EReference getSuperConstructorInvocation_TypeArguments();

	/**
	 * Returns the meta object for class '{@link DOM.SwitchCase <em>Switch Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Switch Case</em>'.
	 * @see DOM.SwitchCase
	 * @generated
	 */
	EClass getSwitchCase();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SwitchCase#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.SwitchCase#getExpression()
	 * @see #getSwitchCase()
	 * @generated
	 */
	EReference getSwitchCase_Expression();

	/**
	 * Returns the meta object for the attribute '{@link DOM.SwitchCase#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see DOM.SwitchCase#getDefault()
	 * @see #getSwitchCase()
	 * @generated
	 */
	EAttribute getSwitchCase_Default();

	/**
	 * Returns the meta object for class '{@link DOM.SwitchStatement <em>Switch Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Switch Statement</em>'.
	 * @see DOM.SwitchStatement
	 * @generated
	 */
	EClass getSwitchStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SwitchStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.SwitchStatement#getExpression()
	 * @see #getSwitchStatement()
	 * @generated
	 */
	EReference getSwitchStatement_Expression();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.SwitchStatement#getStatements <em>Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Statements</em>'.
	 * @see DOM.SwitchStatement#getStatements()
	 * @see #getSwitchStatement()
	 * @generated
	 */
	EReference getSwitchStatement_Statements();

	/**
	 * Returns the meta object for class '{@link DOM.SynchronizedStatement <em>Synchronized Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Synchronized Statement</em>'.
	 * @see DOM.SynchronizedStatement
	 * @generated
	 */
	EClass getSynchronizedStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SynchronizedStatement#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see DOM.SynchronizedStatement#getBody()
	 * @see #getSynchronizedStatement()
	 * @generated
	 */
	EReference getSynchronizedStatement_Body();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SynchronizedStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.SynchronizedStatement#getExpression()
	 * @see #getSynchronizedStatement()
	 * @generated
	 */
	EReference getSynchronizedStatement_Expression();

	/**
	 * Returns the meta object for class '{@link DOM.ThrowStatement <em>Throw Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Throw Statement</em>'.
	 * @see DOM.ThrowStatement
	 * @generated
	 */
	EClass getThrowStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ThrowStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.ThrowStatement#getExpression()
	 * @see #getThrowStatement()
	 * @generated
	 */
	EReference getThrowStatement_Expression();

	/**
	 * Returns the meta object for class '{@link DOM.TryStatement <em>Try Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Try Statement</em>'.
	 * @see DOM.TryStatement
	 * @generated
	 */
	EClass getTryStatement();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.TryStatement#getCatchClauses <em>Catch Clauses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Catch Clauses</em>'.
	 * @see DOM.TryStatement#getCatchClauses()
	 * @see #getTryStatement()
	 * @generated
	 */
	EReference getTryStatement_CatchClauses();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.TryStatement#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see DOM.TryStatement#getBody()
	 * @see #getTryStatement()
	 * @generated
	 */
	EReference getTryStatement_Body();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.TryStatement#getFinally <em>Finally</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Finally</em>'.
	 * @see DOM.TryStatement#getFinally()
	 * @see #getTryStatement()
	 * @generated
	 */
	EReference getTryStatement_Finally();

	/**
	 * Returns the meta object for class '{@link DOM.TypeDeclarationStatement <em>Type Declaration Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Declaration Statement</em>'.
	 * @see DOM.TypeDeclarationStatement
	 * @generated
	 */
	EClass getTypeDeclarationStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.TypeDeclarationStatement#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Declaration</em>'.
	 * @see DOM.TypeDeclarationStatement#getDeclaration()
	 * @see #getTypeDeclarationStatement()
	 * @generated
	 */
	EReference getTypeDeclarationStatement_Declaration();

	/**
	 * Returns the meta object for class '{@link DOM.VariableDeclarationStatement <em>Variable Declaration Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Declaration Statement</em>'.
	 * @see DOM.VariableDeclarationStatement
	 * @generated
	 */
	EClass getVariableDeclarationStatement();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.VariableDeclarationStatement#getFragments <em>Fragments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fragments</em>'.
	 * @see DOM.VariableDeclarationStatement#getFragments()
	 * @see #getVariableDeclarationStatement()
	 * @generated
	 */
	EReference getVariableDeclarationStatement_Fragments();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.VariableDeclarationStatement#getModifiers <em>Modifiers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Modifiers</em>'.
	 * @see DOM.VariableDeclarationStatement#getModifiers()
	 * @see #getVariableDeclarationStatement()
	 * @generated
	 */
	EReference getVariableDeclarationStatement_Modifiers();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.VariableDeclarationStatement#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see DOM.VariableDeclarationStatement#getType()
	 * @see #getVariableDeclarationStatement()
	 * @generated
	 */
	EReference getVariableDeclarationStatement_Type();

	/**
	 * Returns the meta object for class '{@link DOM.WhileStatement <em>While Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>While Statement</em>'.
	 * @see DOM.WhileStatement
	 * @generated
	 */
	EClass getWhileStatement();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.WhileStatement#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see DOM.WhileStatement#getBody()
	 * @see #getWhileStatement()
	 * @generated
	 */
	EReference getWhileStatement_Body();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.WhileStatement#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see DOM.WhileStatement#getExpression()
	 * @see #getWhileStatement()
	 * @generated
	 */
	EReference getWhileStatement_Expression();

	/**
	 * Returns the meta object for class '{@link DOM.ArrayType <em>Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Type</em>'.
	 * @see DOM.ArrayType
	 * @generated
	 */
	EClass getArrayType();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ArrayType#getComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Component Type</em>'.
	 * @see DOM.ArrayType#getComponentType()
	 * @see #getArrayType()
	 * @generated
	 */
	EReference getArrayType_ComponentType();

	/**
	 * Returns the meta object for the attribute '{@link DOM.ArrayType#getDimensions <em>Dimensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dimensions</em>'.
	 * @see DOM.ArrayType#getDimensions()
	 * @see #getArrayType()
	 * @generated
	 */
	EAttribute getArrayType_Dimensions();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ArrayType#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Element Type</em>'.
	 * @see DOM.ArrayType#getElementType()
	 * @see #getArrayType()
	 * @generated
	 */
	EReference getArrayType_ElementType();

	/**
	 * Returns the meta object for class '{@link DOM.ParameterizedType <em>Parameterized Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameterized Type</em>'.
	 * @see DOM.ParameterizedType
	 * @generated
	 */
	EClass getParameterizedType();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.ParameterizedType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see DOM.ParameterizedType#getType()
	 * @see #getParameterizedType()
	 * @generated
	 */
	EReference getParameterizedType_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.ParameterizedType#getTypeArguments <em>Type Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type Arguments</em>'.
	 * @see DOM.ParameterizedType#getTypeArguments()
	 * @see #getParameterizedType()
	 * @generated
	 */
	EReference getParameterizedType_TypeArguments();

	/**
	 * Returns the meta object for class '{@link DOM.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Type</em>'.
	 * @see DOM.PrimitiveType
	 * @generated
	 */
	EClass getPrimitiveType();

	/**
	 * Returns the meta object for the attribute '{@link DOM.PrimitiveType#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see DOM.PrimitiveType#getCode()
	 * @see #getPrimitiveType()
	 * @generated
	 */
	EAttribute getPrimitiveType_Code();

	/**
	 * Returns the meta object for class '{@link DOM.QualifiedType <em>Qualified Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Qualified Type</em>'.
	 * @see DOM.QualifiedType
	 * @generated
	 */
	EClass getQualifiedType();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.QualifiedType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.QualifiedType#getName()
	 * @see #getQualifiedType()
	 * @generated
	 */
	EReference getQualifiedType_Name();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.QualifiedType#getQualifier <em>Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Qualifier</em>'.
	 * @see DOM.QualifiedType#getQualifier()
	 * @see #getQualifiedType()
	 * @generated
	 */
	EReference getQualifiedType_Qualifier();

	/**
	 * Returns the meta object for class '{@link DOM.SimpleType <em>Simple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simple Type</em>'.
	 * @see DOM.SimpleType
	 * @generated
	 */
	EClass getSimpleType();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SimpleType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.SimpleType#getName()
	 * @see #getSimpleType()
	 * @generated
	 */
	EReference getSimpleType_Name();

	/**
	 * Returns the meta object for class '{@link DOM.WildcardType <em>Wildcard Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wildcard Type</em>'.
	 * @see DOM.WildcardType
	 * @generated
	 */
	EClass getWildcardType();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.WildcardType#getBound <em>Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Bound</em>'.
	 * @see DOM.WildcardType#getBound()
	 * @see #getWildcardType()
	 * @generated
	 */
	EReference getWildcardType_Bound();

	/**
	 * Returns the meta object for the attribute '{@link DOM.WildcardType#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see DOM.WildcardType#getUpperBound()
	 * @see #getWildcardType()
	 * @generated
	 */
	EAttribute getWildcardType_UpperBound();

	/**
	 * Returns the meta object for class '{@link DOM.SingleVariableDeclaration <em>Single Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Variable Declaration</em>'.
	 * @see DOM.SingleVariableDeclaration
	 * @generated
	 */
	EClass getSingleVariableDeclaration();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SingleVariableDeclaration#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see DOM.SingleVariableDeclaration#getType()
	 * @see #getSingleVariableDeclaration()
	 * @generated
	 */
	EReference getSingleVariableDeclaration_Type();

	/**
	 * Returns the meta object for the attribute '{@link DOM.SingleVariableDeclaration#getVarargs <em>Varargs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Varargs</em>'.
	 * @see DOM.SingleVariableDeclaration#getVarargs()
	 * @see #getSingleVariableDeclaration()
	 * @generated
	 */
	EAttribute getSingleVariableDeclaration_Varargs();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.SingleVariableDeclaration#getModifiers <em>Modifiers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Modifiers</em>'.
	 * @see DOM.SingleVariableDeclaration#getModifiers()
	 * @see #getSingleVariableDeclaration()
	 * @generated
	 */
	EReference getSingleVariableDeclaration_Modifiers();

	/**
	 * Returns the meta object for class '{@link DOM.VariableDeclarationFragment <em>Variable Declaration Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Declaration Fragment</em>'.
	 * @see DOM.VariableDeclarationFragment
	 * @generated
	 */
	EClass getVariableDeclarationFragment();

	/**
	 * Returns the meta object for class '{@link DOM.QualifiedName <em>Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Qualified Name</em>'.
	 * @see DOM.QualifiedName
	 * @generated
	 */
	EClass getQualifiedName();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.QualifiedName#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see DOM.QualifiedName#getName()
	 * @see #getQualifiedName()
	 * @generated
	 */
	EReference getQualifiedName_Name();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.QualifiedName#getQualifier <em>Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Qualifier</em>'.
	 * @see DOM.QualifiedName#getQualifier()
	 * @see #getQualifiedName()
	 * @generated
	 */
	EReference getQualifiedName_Qualifier();

	/**
	 * Returns the meta object for class '{@link DOM.SimpleName <em>Simple Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simple Name</em>'.
	 * @see DOM.SimpleName
	 * @generated
	 */
	EClass getSimpleName();

	/**
	 * Returns the meta object for the attribute '{@link DOM.SimpleName#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see DOM.SimpleName#getIdentifier()
	 * @see #getSimpleName()
	 * @generated
	 */
	EAttribute getSimpleName_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link DOM.SimpleName#getDeclaration <em>Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Declaration</em>'.
	 * @see DOM.SimpleName#getDeclaration()
	 * @see #getSimpleName()
	 * @generated
	 */
	EAttribute getSimpleName_Declaration();

	/**
	 * Returns the meta object for class '{@link DOM.MarkerAnnotation <em>Marker Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Marker Annotation</em>'.
	 * @see DOM.MarkerAnnotation
	 * @generated
	 */
	EClass getMarkerAnnotation();

	/**
	 * Returns the meta object for class '{@link DOM.NormalAnnotation <em>Normal Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Normal Annotation</em>'.
	 * @see DOM.NormalAnnotation
	 * @generated
	 */
	EClass getNormalAnnotation();

	/**
	 * Returns the meta object for the containment reference list '{@link DOM.NormalAnnotation#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see DOM.NormalAnnotation#getValues()
	 * @see #getNormalAnnotation()
	 * @generated
	 */
	EReference getNormalAnnotation_Values();

	/**
	 * Returns the meta object for class '{@link DOM.SingleMemberAnnotation <em>Single Member Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Member Annotation</em>'.
	 * @see DOM.SingleMemberAnnotation
	 * @generated
	 */
	EClass getSingleMemberAnnotation();

	/**
	 * Returns the meta object for the containment reference '{@link DOM.SingleMemberAnnotation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see DOM.SingleMemberAnnotation#getValue()
	 * @see #getSingleMemberAnnotation()
	 * @generated
	 */
	EReference getSingleMemberAnnotation_Value();

	/**
	 * Returns the meta object for enum '{@link DOM.AssignmentOperatorKind <em>Assignment Operator Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Assignment Operator Kind</em>'.
	 * @see DOM.AssignmentOperatorKind
	 * @generated
	 */
	EEnum getAssignmentOperatorKind();

	/**
	 * Returns the meta object for enum '{@link DOM.InfixExpressionOperatorKind <em>Infix Expression Operator Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Infix Expression Operator Kind</em>'.
	 * @see DOM.InfixExpressionOperatorKind
	 * @generated
	 */
	EEnum getInfixExpressionOperatorKind();

	/**
	 * Returns the meta object for enum '{@link DOM.PostfixExpressionOperatorKind <em>Postfix Expression Operator Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Postfix Expression Operator Kind</em>'.
	 * @see DOM.PostfixExpressionOperatorKind
	 * @generated
	 */
	EEnum getPostfixExpressionOperatorKind();

	/**
	 * Returns the meta object for enum '{@link DOM.PrefixExpressionOperatorKind <em>Prefix Expression Operator Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Prefix Expression Operator Kind</em>'.
	 * @see DOM.PrefixExpressionOperatorKind
	 * @generated
	 */
	EEnum getPrefixExpressionOperatorKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DOMFactory getDOMFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link DOM.impl.ASTImpl <em>AST</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ASTImpl
		 * @see DOM.impl.DOMPackageImpl#getAST()
		 * @generated
		 */
		EClass AST = eINSTANCE.getAST();

		/**
		 * The meta object literal for the '<em><b>Compilation Units</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AST__COMPILATION_UNITS = eINSTANCE.getAST_CompilationUnits();

		/**
		 * The meta object literal for the '{@link DOM.impl.ASTNodeImpl <em>AST Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ASTNodeImpl
		 * @see DOM.impl.DOMPackageImpl#getASTNode()
		 * @generated
		 */
		EClass AST_NODE = eINSTANCE.getASTNode();

		/**
		 * The meta object literal for the '{@link DOM.impl.AnonymousClassDeclarationImpl <em>Anonymous Class Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.AnonymousClassDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getAnonymousClassDeclaration()
		 * @generated
		 */
		EClass ANONYMOUS_CLASS_DECLARATION = eINSTANCE.getAnonymousClassDeclaration();

		/**
		 * The meta object literal for the '<em><b>Body Declarations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS = eINSTANCE.getAnonymousClassDeclaration_BodyDeclarations();

		/**
		 * The meta object literal for the '{@link DOM.impl.BodyDeclarationImpl <em>Body Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.BodyDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getBodyDeclaration()
		 * @generated
		 */
		EClass BODY_DECLARATION = eINSTANCE.getBodyDeclaration();

		/**
		 * The meta object literal for the '<em><b>Modifiers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BODY_DECLARATION__MODIFIERS = eINSTANCE.getBodyDeclaration_Modifiers();

		/**
		 * The meta object literal for the '<em><b>Javadoc</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BODY_DECLARATION__JAVADOC = eINSTANCE.getBodyDeclaration_Javadoc();

		/**
		 * The meta object literal for the '{@link DOM.impl.CatchClauseImpl <em>Catch Clause</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.CatchClauseImpl
		 * @see DOM.impl.DOMPackageImpl#getCatchClause()
		 * @generated
		 */
		EClass CATCH_CLAUSE = eINSTANCE.getCatchClause();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATCH_CLAUSE__BODY = eINSTANCE.getCatchClause_Body();

		/**
		 * The meta object literal for the '<em><b>Exception</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CATCH_CLAUSE__EXCEPTION = eINSTANCE.getCatchClause_Exception();

		/**
		 * The meta object literal for the '{@link DOM.impl.CommentImpl <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.CommentImpl
		 * @see DOM.impl.DOMPackageImpl#getComment()
		 * @generated
		 */
		EClass COMMENT = eINSTANCE.getComment();

		/**
		 * The meta object literal for the '<em><b>Alternate Root</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMENT__ALTERNATE_ROOT = eINSTANCE.getComment_AlternateRoot();

		/**
		 * The meta object literal for the '{@link DOM.impl.CompilationUnitImpl <em>Compilation Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.CompilationUnitImpl
		 * @see DOM.impl.DOMPackageImpl#getCompilationUnit()
		 * @generated
		 */
		EClass COMPILATION_UNIT = eINSTANCE.getCompilationUnit();

		/**
		 * The meta object literal for the '<em><b>Comments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPILATION_UNIT__COMMENTS = eINSTANCE.getCompilationUnit_Comments();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPILATION_UNIT__PACKAGE = eINSTANCE.getCompilationUnit_Package();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPILATION_UNIT__IMPORTS = eINSTANCE.getCompilationUnit_Imports();

		/**
		 * The meta object literal for the '<em><b>Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPILATION_UNIT__TYPES = eINSTANCE.getCompilationUnit_Types();

		/**
		 * The meta object literal for the '{@link DOM.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ExpressionImpl
		 * @see DOM.impl.DOMPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();

		/**
		 * The meta object literal for the '<em><b>Resolve Boxing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPRESSION__RESOLVE_BOXING = eINSTANCE.getExpression_ResolveBoxing();

		/**
		 * The meta object literal for the '<em><b>Resolve Unboxing</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPRESSION__RESOLVE_UNBOXING = eINSTANCE.getExpression_ResolveUnboxing();

		/**
		 * The meta object literal for the '<em><b>Type Binding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION__TYPE_BINDING = eINSTANCE.getExpression_TypeBinding();

		/**
		 * The meta object literal for the '{@link DOM.impl.ImportDeclarationImpl <em>Import Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ImportDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getImportDeclaration()
		 * @generated
		 */
		EClass IMPORT_DECLARATION = eINSTANCE.getImportDeclaration();

		/**
		 * The meta object literal for the '<em><b>On Demand</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORT_DECLARATION__ON_DEMAND = eINSTANCE.getImportDeclaration_OnDemand();

		/**
		 * The meta object literal for the '<em><b>Static</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORT_DECLARATION__STATIC = eINSTANCE.getImportDeclaration_Static();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORT_DECLARATION__NAME = eINSTANCE.getImportDeclaration_Name();

		/**
		 * The meta object literal for the '{@link DOM.impl.MemberRefImpl <em>Member Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.MemberRefImpl
		 * @see DOM.impl.DOMPackageImpl#getMemberRef()
		 * @generated
		 */
		EClass MEMBER_REF = eINSTANCE.getMemberRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_REF__NAME = eINSTANCE.getMemberRef_Name();

		/**
		 * The meta object literal for the '<em><b>Qualifier</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_REF__QUALIFIER = eINSTANCE.getMemberRef_Qualifier();

		/**
		 * The meta object literal for the '{@link DOM.impl.MemberValuePairImpl <em>Member Value Pair</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.MemberValuePairImpl
		 * @see DOM.impl.DOMPackageImpl#getMemberValuePair()
		 * @generated
		 */
		EClass MEMBER_VALUE_PAIR = eINSTANCE.getMemberValuePair();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_VALUE_PAIR__NAME = eINSTANCE.getMemberValuePair_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_VALUE_PAIR__VALUE = eINSTANCE.getMemberValuePair_Value();

		/**
		 * The meta object literal for the '{@link DOM.impl.MethodRefImpl <em>Method Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.MethodRefImpl
		 * @see DOM.impl.DOMPackageImpl#getMethodRef()
		 * @generated
		 */
		EClass METHOD_REF = eINSTANCE.getMethodRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_REF__NAME = eINSTANCE.getMethodRef_Name();

		/**
		 * The meta object literal for the '<em><b>Qualifier</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_REF__QUALIFIER = eINSTANCE.getMethodRef_Qualifier();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_REF__PARAMETERS = eINSTANCE.getMethodRef_Parameters();

		/**
		 * The meta object literal for the '{@link DOM.impl.MethodRefParameterImpl <em>Method Ref Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.MethodRefParameterImpl
		 * @see DOM.impl.DOMPackageImpl#getMethodRefParameter()
		 * @generated
		 */
		EClass METHOD_REF_PARAMETER = eINSTANCE.getMethodRefParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_REF_PARAMETER__NAME = eINSTANCE.getMethodRefParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_REF_PARAMETER__TYPE = eINSTANCE.getMethodRefParameter_Type();

		/**
		 * The meta object literal for the '<em><b>Varargs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_REF_PARAMETER__VARARGS = eINSTANCE.getMethodRefParameter_Varargs();

		/**
		 * The meta object literal for the '{@link DOM.impl.ExtendedModifierImpl <em>Extended Modifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ExtendedModifierImpl
		 * @see DOM.impl.DOMPackageImpl#getExtendedModifier()
		 * @generated
		 */
		EClass EXTENDED_MODIFIER = eINSTANCE.getExtendedModifier();

		/**
		 * The meta object literal for the '{@link DOM.impl.ModifierImpl <em>Modifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ModifierImpl
		 * @see DOM.impl.DOMPackageImpl#getModifier()
		 * @generated
		 */
		EClass MODIFIER = eINSTANCE.getModifier();

		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__ABSTRACT = eINSTANCE.getModifier_Abstract();

		/**
		 * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__FINAL = eINSTANCE.getModifier_Final();

		/**
		 * The meta object literal for the '<em><b>Native</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__NATIVE = eINSTANCE.getModifier_Native();

		/**
		 * The meta object literal for the '<em><b>None</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__NONE = eINSTANCE.getModifier_None();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__PRIVATE = eINSTANCE.getModifier_Private();

		/**
		 * The meta object literal for the '<em><b>Protected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__PROTECTED = eINSTANCE.getModifier_Protected();

		/**
		 * The meta object literal for the '<em><b>Public</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__PUBLIC = eINSTANCE.getModifier_Public();

		/**
		 * The meta object literal for the '<em><b>Static</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__STATIC = eINSTANCE.getModifier_Static();

		/**
		 * The meta object literal for the '<em><b>Strictfp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__STRICTFP = eINSTANCE.getModifier_Strictfp();

		/**
		 * The meta object literal for the '<em><b>Synchronized</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__SYNCHRONIZED = eINSTANCE.getModifier_Synchronized();

		/**
		 * The meta object literal for the '<em><b>Transient</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__TRANSIENT = eINSTANCE.getModifier_Transient();

		/**
		 * The meta object literal for the '<em><b>Volatile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODIFIER__VOLATILE = eINSTANCE.getModifier_Volatile();

		/**
		 * The meta object literal for the '{@link DOM.impl.PackageDeclarationImpl <em>Package Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.PackageDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getPackageDeclaration()
		 * @generated
		 */
		EClass PACKAGE_DECLARATION = eINSTANCE.getPackageDeclaration();

		/**
		 * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_DECLARATION__ANNOTATIONS = eINSTANCE.getPackageDeclaration_Annotations();

		/**
		 * The meta object literal for the '<em><b>Javadoc</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_DECLARATION__JAVADOC = eINSTANCE.getPackageDeclaration_Javadoc();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_DECLARATION__NAME = eINSTANCE.getPackageDeclaration_Name();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_DECLARATION__BINDING = eINSTANCE.getPackageDeclaration_Binding();

		/**
		 * The meta object literal for the '{@link DOM.impl.StatementImpl <em>Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.StatementImpl
		 * @see DOM.impl.DOMPackageImpl#getStatement()
		 * @generated
		 */
		EClass STATEMENT = eINSTANCE.getStatement();

		/**
		 * The meta object literal for the '{@link DOM.impl.TagElementImpl <em>Tag Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.TagElementImpl
		 * @see DOM.impl.DOMPackageImpl#getTagElement()
		 * @generated
		 */
		EClass TAG_ELEMENT = eINSTANCE.getTagElement();

		/**
		 * The meta object literal for the '<em><b>Fragments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_ELEMENT__FRAGMENTS = eINSTANCE.getTagElement_Fragments();

		/**
		 * The meta object literal for the '<em><b>Tag Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_ELEMENT__TAG_NAME = eINSTANCE.getTagElement_TagName();

		/**
		 * The meta object literal for the '<em><b>Nested</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_ELEMENT__NESTED = eINSTANCE.getTagElement_Nested();

		/**
		 * The meta object literal for the '{@link DOM.impl.TextElementImpl <em>Text Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.TextElementImpl
		 * @see DOM.impl.DOMPackageImpl#getTextElement()
		 * @generated
		 */
		EClass TEXT_ELEMENT = eINSTANCE.getTextElement();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_ELEMENT__TEXT = eINSTANCE.getTextElement_Text();

		/**
		 * The meta object literal for the '{@link DOM.impl.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.TypeImpl
		 * @see DOM.impl.DOMPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '{@link DOM.impl.TypeParameterImpl <em>Type Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.TypeParameterImpl
		 * @see DOM.impl.DOMPackageImpl#getTypeParameter()
		 * @generated
		 */
		EClass TYPE_PARAMETER = eINSTANCE.getTypeParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_PARAMETER__NAME = eINSTANCE.getTypeParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Type Bounds</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_PARAMETER__TYPE_BOUNDS = eINSTANCE.getTypeParameter_TypeBounds();

		/**
		 * The meta object literal for the '{@link DOM.impl.VariableDeclarationImpl <em>Variable Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.VariableDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getVariableDeclaration()
		 * @generated
		 */
		EClass VARIABLE_DECLARATION = eINSTANCE.getVariableDeclaration();

		/**
		 * The meta object literal for the '<em><b>Extra Dimensions</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_DECLARATION__EXTRA_DIMENSIONS = eINSTANCE.getVariableDeclaration_ExtraDimensions();

		/**
		 * The meta object literal for the '<em><b>Initializer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION__INITIALIZER = eINSTANCE.getVariableDeclaration_Initializer();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION__NAME = eINSTANCE.getVariableDeclaration_Name();

		/**
		 * The meta object literal for the '{@link DOM.impl.AbstractTypeDeclarationImpl <em>Abstract Type Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.AbstractTypeDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getAbstractTypeDeclaration()
		 * @generated
		 */
		EClass ABSTRACT_TYPE_DECLARATION = eINSTANCE.getAbstractTypeDeclaration();

		/**
		 * The meta object literal for the '<em><b>Body Declarations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS = eINSTANCE.getAbstractTypeDeclaration_BodyDeclarations();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_TYPE_DECLARATION__NAME = eINSTANCE.getAbstractTypeDeclaration_Name();

		/**
		 * The meta object literal for the '<em><b>Local Type Declaration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_TYPE_DECLARATION__LOCAL_TYPE_DECLARATION = eINSTANCE.getAbstractTypeDeclaration_LocalTypeDeclaration();

		/**
		 * The meta object literal for the '<em><b>Member Type Declaration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_TYPE_DECLARATION__MEMBER_TYPE_DECLARATION = eINSTANCE.getAbstractTypeDeclaration_MemberTypeDeclaration();

		/**
		 * The meta object literal for the '<em><b>Package Member Type Declaration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_TYPE_DECLARATION__PACKAGE_MEMBER_TYPE_DECLARATION = eINSTANCE.getAbstractTypeDeclaration_PackageMemberTypeDeclaration();

		/**
		 * The meta object literal for the '{@link DOM.impl.AnnotationTypeMemberDeclarationImpl <em>Annotation Type Member Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.AnnotationTypeMemberDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getAnnotationTypeMemberDeclaration()
		 * @generated
		 */
		EClass ANNOTATION_TYPE_MEMBER_DECLARATION = eINSTANCE.getAnnotationTypeMemberDeclaration();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT = eINSTANCE.getAnnotationTypeMemberDeclaration_Default();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION_TYPE_MEMBER_DECLARATION__NAME = eINSTANCE.getAnnotationTypeMemberDeclaration_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE = eINSTANCE.getAnnotationTypeMemberDeclaration_Type();

		/**
		 * The meta object literal for the '{@link DOM.impl.EnumConstantDeclarationImpl <em>Enum Constant Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.EnumConstantDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getEnumConstantDeclaration()
		 * @generated
		 */
		EClass ENUM_CONSTANT_DECLARATION = eINSTANCE.getEnumConstantDeclaration();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUM_CONSTANT_DECLARATION__ARGUMENTS = eINSTANCE.getEnumConstantDeclaration_Arguments();

		/**
		 * The meta object literal for the '<em><b>Anonymous Class Declaration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION = eINSTANCE.getEnumConstantDeclaration_AnonymousClassDeclaration();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUM_CONSTANT_DECLARATION__NAME = eINSTANCE.getEnumConstantDeclaration_Name();

		/**
		 * The meta object literal for the '{@link DOM.impl.FieldDeclarationImpl <em>Field Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.FieldDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getFieldDeclaration()
		 * @generated
		 */
		EClass FIELD_DECLARATION = eINSTANCE.getFieldDeclaration();

		/**
		 * The meta object literal for the '<em><b>Fragments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD_DECLARATION__FRAGMENTS = eINSTANCE.getFieldDeclaration_Fragments();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD_DECLARATION__TYPE = eINSTANCE.getFieldDeclaration_Type();

		/**
		 * The meta object literal for the '{@link DOM.impl.InitializerImpl <em>Initializer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.InitializerImpl
		 * @see DOM.impl.DOMPackageImpl#getInitializer()
		 * @generated
		 */
		EClass INITIALIZER = eINSTANCE.getInitializer();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INITIALIZER__BODY = eINSTANCE.getInitializer_Body();

		/**
		 * The meta object literal for the '{@link DOM.impl.MethodDeclarationImpl <em>Method Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.MethodDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getMethodDeclaration()
		 * @generated
		 */
		EClass METHOD_DECLARATION = eINSTANCE.getMethodDeclaration();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_DECLARATION__BODY = eINSTANCE.getMethodDeclaration_Body();

		/**
		 * The meta object literal for the '<em><b>Extra Dimensions</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_DECLARATION__EXTRA_DIMENSIONS = eINSTANCE.getMethodDeclaration_ExtraDimensions();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_DECLARATION__NAME = eINSTANCE.getMethodDeclaration_Name();

		/**
		 * The meta object literal for the '<em><b>Return Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_DECLARATION__RETURN_TYPE = eINSTANCE.getMethodDeclaration_ReturnType();

		/**
		 * The meta object literal for the '<em><b>Constructor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_DECLARATION__CONSTRUCTOR = eINSTANCE.getMethodDeclaration_Constructor();

		/**
		 * The meta object literal for the '<em><b>Varargs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_DECLARATION__VARARGS = eINSTANCE.getMethodDeclaration_Varargs();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_DECLARATION__PARAMETERS = eINSTANCE.getMethodDeclaration_Parameters();

		/**
		 * The meta object literal for the '<em><b>Thrown Exceptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_DECLARATION__THROWN_EXCEPTIONS = eINSTANCE.getMethodDeclaration_ThrownExceptions();

		/**
		 * The meta object literal for the '<em><b>Type Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_DECLARATION__TYPE_PARAMETERS = eINSTANCE.getMethodDeclaration_TypeParameters();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_DECLARATION__BINDING = eINSTANCE.getMethodDeclaration_Binding();

		/**
		 * The meta object literal for the '{@link DOM.impl.AnnotationTypeDeclarationImpl <em>Annotation Type Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.AnnotationTypeDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getAnnotationTypeDeclaration()
		 * @generated
		 */
		EClass ANNOTATION_TYPE_DECLARATION = eINSTANCE.getAnnotationTypeDeclaration();

		/**
		 * The meta object literal for the '{@link DOM.impl.EnumDeclarationImpl <em>Enum Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.EnumDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getEnumDeclaration()
		 * @generated
		 */
		EClass ENUM_DECLARATION = eINSTANCE.getEnumDeclaration();

		/**
		 * The meta object literal for the '<em><b>Super Interface Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUM_DECLARATION__SUPER_INTERFACE_TYPES = eINSTANCE.getEnumDeclaration_SuperInterfaceTypes();

		/**
		 * The meta object literal for the '<em><b>Enum Constants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUM_DECLARATION__ENUM_CONSTANTS = eINSTANCE.getEnumDeclaration_EnumConstants();

		/**
		 * The meta object literal for the '{@link DOM.impl.TypeDeclarationImpl <em>Type Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.TypeDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getTypeDeclaration()
		 * @generated
		 */
		EClass TYPE_DECLARATION = eINSTANCE.getTypeDeclaration();

		/**
		 * The meta object literal for the '<em><b>Superclass Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION__SUPERCLASS_TYPE = eINSTANCE.getTypeDeclaration_SuperclassType();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_DECLARATION__INTERFACE = eINSTANCE.getTypeDeclaration_Interface();

		/**
		 * The meta object literal for the '<em><b>Super Interface Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION__SUPER_INTERFACE_TYPES = eINSTANCE.getTypeDeclaration_SuperInterfaceTypes();

		/**
		 * The meta object literal for the '<em><b>Type Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION__TYPE_PARAMETERS = eINSTANCE.getTypeDeclaration_TypeParameters();

		/**
		 * The meta object literal for the '{@link DOM.impl.BlockCommentImpl <em>Block Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.BlockCommentImpl
		 * @see DOM.impl.DOMPackageImpl#getBlockComment()
		 * @generated
		 */
		EClass BLOCK_COMMENT = eINSTANCE.getBlockComment();

		/**
		 * The meta object literal for the '{@link DOM.impl.JavadocImpl <em>Javadoc</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.JavadocImpl
		 * @see DOM.impl.DOMPackageImpl#getJavadoc()
		 * @generated
		 */
		EClass JAVADOC = eINSTANCE.getJavadoc();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JAVADOC__TAGS = eINSTANCE.getJavadoc_Tags();

		/**
		 * The meta object literal for the '{@link DOM.impl.LineCommentImpl <em>Line Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.LineCommentImpl
		 * @see DOM.impl.DOMPackageImpl#getLineComment()
		 * @generated
		 */
		EClass LINE_COMMENT = eINSTANCE.getLineComment();

		/**
		 * The meta object literal for the '{@link DOM.impl.AnnotationImpl <em>Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.AnnotationImpl
		 * @see DOM.impl.DOMPackageImpl#getAnnotation()
		 * @generated
		 */
		EClass ANNOTATION = eINSTANCE.getAnnotation();

		/**
		 * The meta object literal for the '<em><b>Type Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION__TYPE_NAME = eINSTANCE.getAnnotation_TypeName();

		/**
		 * The meta object literal for the '{@link DOM.impl.ArrayAccessImpl <em>Array Access</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ArrayAccessImpl
		 * @see DOM.impl.DOMPackageImpl#getArrayAccess()
		 * @generated
		 */
		EClass ARRAY_ACCESS = eINSTANCE.getArrayAccess();

		/**
		 * The meta object literal for the '<em><b>Array</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_ACCESS__ARRAY = eINSTANCE.getArrayAccess_Array();

		/**
		 * The meta object literal for the '<em><b>Index</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_ACCESS__INDEX = eINSTANCE.getArrayAccess_Index();

		/**
		 * The meta object literal for the '{@link DOM.impl.ArrayCreationImpl <em>Array Creation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ArrayCreationImpl
		 * @see DOM.impl.DOMPackageImpl#getArrayCreation()
		 * @generated
		 */
		EClass ARRAY_CREATION = eINSTANCE.getArrayCreation();

		/**
		 * The meta object literal for the '<em><b>Dimensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_CREATION__DIMENSIONS = eINSTANCE.getArrayCreation_Dimensions();

		/**
		 * The meta object literal for the '<em><b>Initializer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_CREATION__INITIALIZER = eINSTANCE.getArrayCreation_Initializer();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_CREATION__TYPE = eINSTANCE.getArrayCreation_Type();

		/**
		 * The meta object literal for the '{@link DOM.impl.ArrayInitializerImpl <em>Array Initializer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ArrayInitializerImpl
		 * @see DOM.impl.DOMPackageImpl#getArrayInitializer()
		 * @generated
		 */
		EClass ARRAY_INITIALIZER = eINSTANCE.getArrayInitializer();

		/**
		 * The meta object literal for the '<em><b>Expressions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_INITIALIZER__EXPRESSIONS = eINSTANCE.getArrayInitializer_Expressions();

		/**
		 * The meta object literal for the '{@link DOM.impl.AssignmentImpl <em>Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.AssignmentImpl
		 * @see DOM.impl.DOMPackageImpl#getAssignment()
		 * @generated
		 */
		EClass ASSIGNMENT = eINSTANCE.getAssignment();

		/**
		 * The meta object literal for the '<em><b>Left Hand Side</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGNMENT__LEFT_HAND_SIDE = eINSTANCE.getAssignment_LeftHandSide();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSIGNMENT__OPERATOR = eINSTANCE.getAssignment_Operator();

		/**
		 * The meta object literal for the '<em><b>Right Hand Side</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGNMENT__RIGHT_HAND_SIDE = eINSTANCE.getAssignment_RightHandSide();

		/**
		 * The meta object literal for the '{@link DOM.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.BooleanLiteralImpl
		 * @see DOM.impl.DOMPackageImpl#getBooleanLiteral()
		 * @generated
		 */
		EClass BOOLEAN_LITERAL = eINSTANCE.getBooleanLiteral();

		/**
		 * The meta object literal for the '<em><b>Boolean Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_LITERAL__BOOLEAN_VALUE = eINSTANCE.getBooleanLiteral_BooleanValue();

		/**
		 * The meta object literal for the '{@link DOM.impl.CastExpressionImpl <em>Cast Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.CastExpressionImpl
		 * @see DOM.impl.DOMPackageImpl#getCastExpression()
		 * @generated
		 */
		EClass CAST_EXPRESSION = eINSTANCE.getCastExpression();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAST_EXPRESSION__EXPRESSION = eINSTANCE.getCastExpression_Expression();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CAST_EXPRESSION__TYPE = eINSTANCE.getCastExpression_Type();

		/**
		 * The meta object literal for the '{@link DOM.impl.CharacterLiteralImpl <em>Character Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.CharacterLiteralImpl
		 * @see DOM.impl.DOMPackageImpl#getCharacterLiteral()
		 * @generated
		 */
		EClass CHARACTER_LITERAL = eINSTANCE.getCharacterLiteral();

		/**
		 * The meta object literal for the '<em><b>Char Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_LITERAL__CHAR_VALUE = eINSTANCE.getCharacterLiteral_CharValue();

		/**
		 * The meta object literal for the '<em><b>Escaped Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_LITERAL__ESCAPED_VALUE = eINSTANCE.getCharacterLiteral_EscapedValue();

		/**
		 * The meta object literal for the '{@link DOM.impl.ClassInstanceCreationImpl <em>Class Instance Creation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ClassInstanceCreationImpl
		 * @see DOM.impl.DOMPackageImpl#getClassInstanceCreation()
		 * @generated
		 */
		EClass CLASS_INSTANCE_CREATION = eINSTANCE.getClassInstanceCreation();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_INSTANCE_CREATION__ARGUMENTS = eINSTANCE.getClassInstanceCreation_Arguments();

		/**
		 * The meta object literal for the '<em><b>Anonymous Class Declaration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION = eINSTANCE.getClassInstanceCreation_AnonymousClassDeclaration();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_INSTANCE_CREATION__EXPRESSION = eINSTANCE.getClassInstanceCreation_Expression();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_INSTANCE_CREATION__TYPE = eINSTANCE.getClassInstanceCreation_Type();

		/**
		 * The meta object literal for the '<em><b>Type Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS = eINSTANCE.getClassInstanceCreation_TypeArguments();

		/**
		 * The meta object literal for the '{@link DOM.impl.ConditionalExpressionImpl <em>Conditional Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ConditionalExpressionImpl
		 * @see DOM.impl.DOMPackageImpl#getConditionalExpression()
		 * @generated
		 */
		EClass CONDITIONAL_EXPRESSION = eINSTANCE.getConditionalExpression();

		/**
		 * The meta object literal for the '<em><b>Else Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITIONAL_EXPRESSION__ELSE_EXPRESSION = eINSTANCE.getConditionalExpression_ElseExpression();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITIONAL_EXPRESSION__EXPRESSION = eINSTANCE.getConditionalExpression_Expression();

		/**
		 * The meta object literal for the '<em><b>Then Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITIONAL_EXPRESSION__THEN_EXPRESSION = eINSTANCE.getConditionalExpression_ThenExpression();

		/**
		 * The meta object literal for the '{@link DOM.impl.FieldAccessImpl <em>Field Access</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.FieldAccessImpl
		 * @see DOM.impl.DOMPackageImpl#getFieldAccess()
		 * @generated
		 */
		EClass FIELD_ACCESS = eINSTANCE.getFieldAccess();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD_ACCESS__EXPRESSION = eINSTANCE.getFieldAccess_Expression();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD_ACCESS__NAME = eINSTANCE.getFieldAccess_Name();

		/**
		 * The meta object literal for the '{@link DOM.impl.InfixExpressionImpl <em>Infix Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.InfixExpressionImpl
		 * @see DOM.impl.DOMPackageImpl#getInfixExpression()
		 * @generated
		 */
		EClass INFIX_EXPRESSION = eINSTANCE.getInfixExpression();

		/**
		 * The meta object literal for the '<em><b>Extended Operands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFIX_EXPRESSION__EXTENDED_OPERANDS = eINSTANCE.getInfixExpression_ExtendedOperands();

		/**
		 * The meta object literal for the '<em><b>Left Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFIX_EXPRESSION__LEFT_OPERAND = eINSTANCE.getInfixExpression_LeftOperand();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INFIX_EXPRESSION__OPERATOR = eINSTANCE.getInfixExpression_Operator();

		/**
		 * The meta object literal for the '<em><b>Right Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INFIX_EXPRESSION__RIGHT_OPERAND = eINSTANCE.getInfixExpression_RightOperand();

		/**
		 * The meta object literal for the '{@link DOM.impl.InstanceofExpressionImpl <em>Instanceof Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.InstanceofExpressionImpl
		 * @see DOM.impl.DOMPackageImpl#getInstanceofExpression()
		 * @generated
		 */
		EClass INSTANCEOF_EXPRESSION = eINSTANCE.getInstanceofExpression();

		/**
		 * The meta object literal for the '<em><b>Left Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCEOF_EXPRESSION__LEFT_OPERAND = eINSTANCE.getInstanceofExpression_LeftOperand();

		/**
		 * The meta object literal for the '<em><b>Right Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCEOF_EXPRESSION__RIGHT_OPERAND = eINSTANCE.getInstanceofExpression_RightOperand();

		/**
		 * The meta object literal for the '{@link DOM.impl.MethodInvocationImpl <em>Method Invocation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.MethodInvocationImpl
		 * @see DOM.impl.DOMPackageImpl#getMethodInvocation()
		 * @generated
		 */
		EClass METHOD_INVOCATION = eINSTANCE.getMethodInvocation();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_INVOCATION__ARGUMENTS = eINSTANCE.getMethodInvocation_Arguments();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_INVOCATION__EXPRESSION = eINSTANCE.getMethodInvocation_Expression();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_INVOCATION__NAME = eINSTANCE.getMethodInvocation_Name();

		/**
		 * The meta object literal for the '<em><b>Type Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_INVOCATION__TYPE_ARGUMENTS = eINSTANCE.getMethodInvocation_TypeArguments();

		/**
		 * The meta object literal for the '<em><b>Method Binding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_INVOCATION__METHOD_BINDING = eINSTANCE.getMethodInvocation_MethodBinding();

		/**
		 * The meta object literal for the '{@link DOM.impl.NameImpl <em>Name</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.NameImpl
		 * @see DOM.impl.DOMPackageImpl#getName_()
		 * @generated
		 */
		EClass NAME = eINSTANCE.getName_();

		/**
		 * The meta object literal for the '<em><b>Fully Qualified Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAME__FULLY_QUALIFIED_NAME = eINSTANCE.getName_FullyQualifiedName();

		/**
		 * The meta object literal for the '{@link DOM.impl.NullLiteralImpl <em>Null Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.NullLiteralImpl
		 * @see DOM.impl.DOMPackageImpl#getNullLiteral()
		 * @generated
		 */
		EClass NULL_LITERAL = eINSTANCE.getNullLiteral();

		/**
		 * The meta object literal for the '{@link DOM.impl.NumberLiteralImpl <em>Number Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.NumberLiteralImpl
		 * @see DOM.impl.DOMPackageImpl#getNumberLiteral()
		 * @generated
		 */
		EClass NUMBER_LITERAL = eINSTANCE.getNumberLiteral();

		/**
		 * The meta object literal for the '<em><b>Token</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMBER_LITERAL__TOKEN = eINSTANCE.getNumberLiteral_Token();

		/**
		 * The meta object literal for the '{@link DOM.impl.ParenthesizedExpressionImpl <em>Parenthesized Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ParenthesizedExpressionImpl
		 * @see DOM.impl.DOMPackageImpl#getParenthesizedExpression()
		 * @generated
		 */
		EClass PARENTHESIZED_EXPRESSION = eINSTANCE.getParenthesizedExpression();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARENTHESIZED_EXPRESSION__EXPRESSION = eINSTANCE.getParenthesizedExpression_Expression();

		/**
		 * The meta object literal for the '{@link DOM.impl.PostfixExpressionImpl <em>Postfix Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.PostfixExpressionImpl
		 * @see DOM.impl.DOMPackageImpl#getPostfixExpression()
		 * @generated
		 */
		EClass POSTFIX_EXPRESSION = eINSTANCE.getPostfixExpression();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POSTFIX_EXPRESSION__OPERAND = eINSTANCE.getPostfixExpression_Operand();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTFIX_EXPRESSION__OPERATOR = eINSTANCE.getPostfixExpression_Operator();

		/**
		 * The meta object literal for the '{@link DOM.impl.PrefixExpressionImpl <em>Prefix Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.PrefixExpressionImpl
		 * @see DOM.impl.DOMPackageImpl#getPrefixExpression()
		 * @generated
		 */
		EClass PREFIX_EXPRESSION = eINSTANCE.getPrefixExpression();

		/**
		 * The meta object literal for the '<em><b>Operand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PREFIX_EXPRESSION__OPERAND = eINSTANCE.getPrefixExpression_Operand();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREFIX_EXPRESSION__OPERATOR = eINSTANCE.getPrefixExpression_Operator();

		/**
		 * The meta object literal for the '{@link DOM.impl.StringLiteralImpl <em>String Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.StringLiteralImpl
		 * @see DOM.impl.DOMPackageImpl#getStringLiteral()
		 * @generated
		 */
		EClass STRING_LITERAL = eINSTANCE.getStringLiteral();

		/**
		 * The meta object literal for the '<em><b>Escaped Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_LITERAL__ESCAPED_VALUE = eINSTANCE.getStringLiteral_EscapedValue();

		/**
		 * The meta object literal for the '<em><b>Literal Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_LITERAL__LITERAL_VALUE = eINSTANCE.getStringLiteral_LiteralValue();

		/**
		 * The meta object literal for the '{@link DOM.impl.SuperFieldAccessImpl <em>Super Field Access</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.SuperFieldAccessImpl
		 * @see DOM.impl.DOMPackageImpl#getSuperFieldAccess()
		 * @generated
		 */
		EClass SUPER_FIELD_ACCESS = eINSTANCE.getSuperFieldAccess();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_FIELD_ACCESS__NAME = eINSTANCE.getSuperFieldAccess_Name();

		/**
		 * The meta object literal for the '<em><b>Qualifier</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_FIELD_ACCESS__QUALIFIER = eINSTANCE.getSuperFieldAccess_Qualifier();

		/**
		 * The meta object literal for the '{@link DOM.impl.SuperMethodInvocationImpl <em>Super Method Invocation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.SuperMethodInvocationImpl
		 * @see DOM.impl.DOMPackageImpl#getSuperMethodInvocation()
		 * @generated
		 */
		EClass SUPER_METHOD_INVOCATION = eINSTANCE.getSuperMethodInvocation();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_METHOD_INVOCATION__ARGUMENTS = eINSTANCE.getSuperMethodInvocation_Arguments();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_METHOD_INVOCATION__NAME = eINSTANCE.getSuperMethodInvocation_Name();

		/**
		 * The meta object literal for the '<em><b>Qualifier</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_METHOD_INVOCATION__QUALIFIER = eINSTANCE.getSuperMethodInvocation_Qualifier();

		/**
		 * The meta object literal for the '<em><b>Type Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_METHOD_INVOCATION__TYPE_ARGUMENTS = eINSTANCE.getSuperMethodInvocation_TypeArguments();

		/**
		 * The meta object literal for the '{@link DOM.impl.ThisExpressionImpl <em>This Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ThisExpressionImpl
		 * @see DOM.impl.DOMPackageImpl#getThisExpression()
		 * @generated
		 */
		EClass THIS_EXPRESSION = eINSTANCE.getThisExpression();

		/**
		 * The meta object literal for the '<em><b>Qualifier</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference THIS_EXPRESSION__QUALIFIER = eINSTANCE.getThisExpression_Qualifier();

		/**
		 * The meta object literal for the '{@link DOM.impl.TypeLiteralImpl <em>Type Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.TypeLiteralImpl
		 * @see DOM.impl.DOMPackageImpl#getTypeLiteral()
		 * @generated
		 */
		EClass TYPE_LITERAL = eINSTANCE.getTypeLiteral();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_LITERAL__TYPE = eINSTANCE.getTypeLiteral_Type();

		/**
		 * The meta object literal for the '{@link DOM.impl.VariableDeclarationExpressionImpl <em>Variable Declaration Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.VariableDeclarationExpressionImpl
		 * @see DOM.impl.DOMPackageImpl#getVariableDeclarationExpression()
		 * @generated
		 */
		EClass VARIABLE_DECLARATION_EXPRESSION = eINSTANCE.getVariableDeclarationExpression();

		/**
		 * The meta object literal for the '<em><b>Fragments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS = eINSTANCE.getVariableDeclarationExpression_Fragments();

		/**
		 * The meta object literal for the '<em><b>Modifiers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION_EXPRESSION__MODIFIERS = eINSTANCE.getVariableDeclarationExpression_Modifiers();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION_EXPRESSION__TYPE = eINSTANCE.getVariableDeclarationExpression_Type();

		/**
		 * The meta object literal for the '{@link DOM.impl.AssertStatementImpl <em>Assert Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.AssertStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getAssertStatement()
		 * @generated
		 */
		EClass ASSERT_STATEMENT = eINSTANCE.getAssertStatement();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSERT_STATEMENT__EXPRESSION = eINSTANCE.getAssertStatement_Expression();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSERT_STATEMENT__MESSAGE = eINSTANCE.getAssertStatement_Message();

		/**
		 * The meta object literal for the '{@link DOM.impl.BlockImpl <em>Block</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.BlockImpl
		 * @see DOM.impl.DOMPackageImpl#getBlock()
		 * @generated
		 */
		EClass BLOCK = eINSTANCE.getBlock();

		/**
		 * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK__STATEMENTS = eINSTANCE.getBlock_Statements();

		/**
		 * The meta object literal for the '{@link DOM.impl.BreakStatementImpl <em>Break Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.BreakStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getBreakStatement()
		 * @generated
		 */
		EClass BREAK_STATEMENT = eINSTANCE.getBreakStatement();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREAK_STATEMENT__LABEL = eINSTANCE.getBreakStatement_Label();

		/**
		 * The meta object literal for the '{@link DOM.impl.ConstructorInvocationImpl <em>Constructor Invocation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ConstructorInvocationImpl
		 * @see DOM.impl.DOMPackageImpl#getConstructorInvocation()
		 * @generated
		 */
		EClass CONSTRUCTOR_INVOCATION = eINSTANCE.getConstructorInvocation();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRUCTOR_INVOCATION__ARGUMENTS = eINSTANCE.getConstructorInvocation_Arguments();

		/**
		 * The meta object literal for the '<em><b>Type Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS = eINSTANCE.getConstructorInvocation_TypeArguments();

		/**
		 * The meta object literal for the '{@link DOM.impl.ContinueStatementImpl <em>Continue Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ContinueStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getContinueStatement()
		 * @generated
		 */
		EClass CONTINUE_STATEMENT = eINSTANCE.getContinueStatement();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTINUE_STATEMENT__LABEL = eINSTANCE.getContinueStatement_Label();

		/**
		 * The meta object literal for the '{@link DOM.impl.DoStatementImpl <em>Do Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.DoStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getDoStatement()
		 * @generated
		 */
		EClass DO_STATEMENT = eINSTANCE.getDoStatement();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DO_STATEMENT__BODY = eINSTANCE.getDoStatement_Body();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DO_STATEMENT__EXPRESSION = eINSTANCE.getDoStatement_Expression();

		/**
		 * The meta object literal for the '{@link DOM.impl.EmptyStatementImpl <em>Empty Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.EmptyStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getEmptyStatement()
		 * @generated
		 */
		EClass EMPTY_STATEMENT = eINSTANCE.getEmptyStatement();

		/**
		 * The meta object literal for the '{@link DOM.impl.EnhancedForStatementImpl <em>Enhanced For Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.EnhancedForStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getEnhancedForStatement()
		 * @generated
		 */
		EClass ENHANCED_FOR_STATEMENT = eINSTANCE.getEnhancedForStatement();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENHANCED_FOR_STATEMENT__BODY = eINSTANCE.getEnhancedForStatement_Body();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENHANCED_FOR_STATEMENT__EXPRESSION = eINSTANCE.getEnhancedForStatement_Expression();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENHANCED_FOR_STATEMENT__PARAMETER = eINSTANCE.getEnhancedForStatement_Parameter();

		/**
		 * The meta object literal for the '{@link DOM.impl.ExpressionStatementImpl <em>Expression Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ExpressionStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getExpressionStatement()
		 * @generated
		 */
		EClass EXPRESSION_STATEMENT = eINSTANCE.getExpressionStatement();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_STATEMENT__EXPRESSION = eINSTANCE.getExpressionStatement_Expression();

		/**
		 * The meta object literal for the '{@link DOM.impl.ForStatementImpl <em>For Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ForStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getForStatement()
		 * @generated
		 */
		EClass FOR_STATEMENT = eINSTANCE.getForStatement();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_STATEMENT__BODY = eINSTANCE.getForStatement_Body();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_STATEMENT__EXPRESSION = eINSTANCE.getForStatement_Expression();

		/**
		 * The meta object literal for the '<em><b>Initializers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_STATEMENT__INITIALIZERS = eINSTANCE.getForStatement_Initializers();

		/**
		 * The meta object literal for the '<em><b>Updaters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_STATEMENT__UPDATERS = eINSTANCE.getForStatement_Updaters();

		/**
		 * The meta object literal for the '{@link DOM.impl.IfStatementImpl <em>If Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.IfStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getIfStatement()
		 * @generated
		 */
		EClass IF_STATEMENT = eINSTANCE.getIfStatement();

		/**
		 * The meta object literal for the '<em><b>Else Statement</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STATEMENT__ELSE_STATEMENT = eINSTANCE.getIfStatement_ElseStatement();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STATEMENT__EXPRESSION = eINSTANCE.getIfStatement_Expression();

		/**
		 * The meta object literal for the '<em><b>Then Statement</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_STATEMENT__THEN_STATEMENT = eINSTANCE.getIfStatement_ThenStatement();

		/**
		 * The meta object literal for the '{@link DOM.impl.LabeledStatementImpl <em>Labeled Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.LabeledStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getLabeledStatement()
		 * @generated
		 */
		EClass LABELED_STATEMENT = eINSTANCE.getLabeledStatement();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABELED_STATEMENT__BODY = eINSTANCE.getLabeledStatement_Body();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABELED_STATEMENT__LABEL = eINSTANCE.getLabeledStatement_Label();

		/**
		 * The meta object literal for the '{@link DOM.impl.ReturnStatementImpl <em>Return Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ReturnStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getReturnStatement()
		 * @generated
		 */
		EClass RETURN_STATEMENT = eINSTANCE.getReturnStatement();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RETURN_STATEMENT__EXPRESSION = eINSTANCE.getReturnStatement_Expression();

		/**
		 * The meta object literal for the '{@link DOM.impl.SuperConstructorInvocationImpl <em>Super Constructor Invocation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.SuperConstructorInvocationImpl
		 * @see DOM.impl.DOMPackageImpl#getSuperConstructorInvocation()
		 * @generated
		 */
		EClass SUPER_CONSTRUCTOR_INVOCATION = eINSTANCE.getSuperConstructorInvocation();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS = eINSTANCE.getSuperConstructorInvocation_Arguments();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION = eINSTANCE.getSuperConstructorInvocation_Expression();

		/**
		 * The meta object literal for the '<em><b>Type Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS = eINSTANCE.getSuperConstructorInvocation_TypeArguments();

		/**
		 * The meta object literal for the '{@link DOM.impl.SwitchCaseImpl <em>Switch Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.SwitchCaseImpl
		 * @see DOM.impl.DOMPackageImpl#getSwitchCase()
		 * @generated
		 */
		EClass SWITCH_CASE = eINSTANCE.getSwitchCase();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_CASE__EXPRESSION = eINSTANCE.getSwitchCase_Expression();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWITCH_CASE__DEFAULT = eINSTANCE.getSwitchCase_Default();

		/**
		 * The meta object literal for the '{@link DOM.impl.SwitchStatementImpl <em>Switch Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.SwitchStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getSwitchStatement()
		 * @generated
		 */
		EClass SWITCH_STATEMENT = eINSTANCE.getSwitchStatement();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_STATEMENT__EXPRESSION = eINSTANCE.getSwitchStatement_Expression();

		/**
		 * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH_STATEMENT__STATEMENTS = eINSTANCE.getSwitchStatement_Statements();

		/**
		 * The meta object literal for the '{@link DOM.impl.SynchronizedStatementImpl <em>Synchronized Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.SynchronizedStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getSynchronizedStatement()
		 * @generated
		 */
		EClass SYNCHRONIZED_STATEMENT = eINSTANCE.getSynchronizedStatement();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNCHRONIZED_STATEMENT__BODY = eINSTANCE.getSynchronizedStatement_Body();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNCHRONIZED_STATEMENT__EXPRESSION = eINSTANCE.getSynchronizedStatement_Expression();

		/**
		 * The meta object literal for the '{@link DOM.impl.ThrowStatementImpl <em>Throw Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ThrowStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getThrowStatement()
		 * @generated
		 */
		EClass THROW_STATEMENT = eINSTANCE.getThrowStatement();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference THROW_STATEMENT__EXPRESSION = eINSTANCE.getThrowStatement_Expression();

		/**
		 * The meta object literal for the '{@link DOM.impl.TryStatementImpl <em>Try Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.TryStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getTryStatement()
		 * @generated
		 */
		EClass TRY_STATEMENT = eINSTANCE.getTryStatement();

		/**
		 * The meta object literal for the '<em><b>Catch Clauses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRY_STATEMENT__CATCH_CLAUSES = eINSTANCE.getTryStatement_CatchClauses();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRY_STATEMENT__BODY = eINSTANCE.getTryStatement_Body();

		/**
		 * The meta object literal for the '<em><b>Finally</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRY_STATEMENT__FINALLY = eINSTANCE.getTryStatement_Finally();

		/**
		 * The meta object literal for the '{@link DOM.impl.TypeDeclarationStatementImpl <em>Type Declaration Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.TypeDeclarationStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getTypeDeclarationStatement()
		 * @generated
		 */
		EClass TYPE_DECLARATION_STATEMENT = eINSTANCE.getTypeDeclarationStatement();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_DECLARATION_STATEMENT__DECLARATION = eINSTANCE.getTypeDeclarationStatement_Declaration();

		/**
		 * The meta object literal for the '{@link DOM.impl.VariableDeclarationStatementImpl <em>Variable Declaration Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.VariableDeclarationStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getVariableDeclarationStatement()
		 * @generated
		 */
		EClass VARIABLE_DECLARATION_STATEMENT = eINSTANCE.getVariableDeclarationStatement();

		/**
		 * The meta object literal for the '<em><b>Fragments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION_STATEMENT__FRAGMENTS = eINSTANCE.getVariableDeclarationStatement_Fragments();

		/**
		 * The meta object literal for the '<em><b>Modifiers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION_STATEMENT__MODIFIERS = eINSTANCE.getVariableDeclarationStatement_Modifiers();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION_STATEMENT__TYPE = eINSTANCE.getVariableDeclarationStatement_Type();

		/**
		 * The meta object literal for the '{@link DOM.impl.WhileStatementImpl <em>While Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.WhileStatementImpl
		 * @see DOM.impl.DOMPackageImpl#getWhileStatement()
		 * @generated
		 */
		EClass WHILE_STATEMENT = eINSTANCE.getWhileStatement();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHILE_STATEMENT__BODY = eINSTANCE.getWhileStatement_Body();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WHILE_STATEMENT__EXPRESSION = eINSTANCE.getWhileStatement_Expression();

		/**
		 * The meta object literal for the '{@link DOM.impl.ArrayTypeImpl <em>Array Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ArrayTypeImpl
		 * @see DOM.impl.DOMPackageImpl#getArrayType()
		 * @generated
		 */
		EClass ARRAY_TYPE = eINSTANCE.getArrayType();

		/**
		 * The meta object literal for the '<em><b>Component Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_TYPE__COMPONENT_TYPE = eINSTANCE.getArrayType_ComponentType();

		/**
		 * The meta object literal for the '<em><b>Dimensions</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRAY_TYPE__DIMENSIONS = eINSTANCE.getArrayType_Dimensions();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_TYPE__ELEMENT_TYPE = eINSTANCE.getArrayType_ElementType();

		/**
		 * The meta object literal for the '{@link DOM.impl.ParameterizedTypeImpl <em>Parameterized Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.ParameterizedTypeImpl
		 * @see DOM.impl.DOMPackageImpl#getParameterizedType()
		 * @generated
		 */
		EClass PARAMETERIZED_TYPE = eINSTANCE.getParameterizedType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETERIZED_TYPE__TYPE = eINSTANCE.getParameterizedType_Type();

		/**
		 * The meta object literal for the '<em><b>Type Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETERIZED_TYPE__TYPE_ARGUMENTS = eINSTANCE.getParameterizedType_TypeArguments();

		/**
		 * The meta object literal for the '{@link DOM.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.PrimitiveTypeImpl
		 * @see DOM.impl.DOMPackageImpl#getPrimitiveType()
		 * @generated
		 */
		EClass PRIMITIVE_TYPE = eINSTANCE.getPrimitiveType();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRIMITIVE_TYPE__CODE = eINSTANCE.getPrimitiveType_Code();

		/**
		 * The meta object literal for the '{@link DOM.impl.QualifiedTypeImpl <em>Qualified Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.QualifiedTypeImpl
		 * @see DOM.impl.DOMPackageImpl#getQualifiedType()
		 * @generated
		 */
		EClass QUALIFIED_TYPE = eINSTANCE.getQualifiedType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALIFIED_TYPE__NAME = eINSTANCE.getQualifiedType_Name();

		/**
		 * The meta object literal for the '<em><b>Qualifier</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALIFIED_TYPE__QUALIFIER = eINSTANCE.getQualifiedType_Qualifier();

		/**
		 * The meta object literal for the '{@link DOM.impl.SimpleTypeImpl <em>Simple Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.SimpleTypeImpl
		 * @see DOM.impl.DOMPackageImpl#getSimpleType()
		 * @generated
		 */
		EClass SIMPLE_TYPE = eINSTANCE.getSimpleType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMPLE_TYPE__NAME = eINSTANCE.getSimpleType_Name();

		/**
		 * The meta object literal for the '{@link DOM.impl.WildcardTypeImpl <em>Wildcard Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.WildcardTypeImpl
		 * @see DOM.impl.DOMPackageImpl#getWildcardType()
		 * @generated
		 */
		EClass WILDCARD_TYPE = eINSTANCE.getWildcardType();

		/**
		 * The meta object literal for the '<em><b>Bound</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WILDCARD_TYPE__BOUND = eINSTANCE.getWildcardType_Bound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WILDCARD_TYPE__UPPER_BOUND = eINSTANCE.getWildcardType_UpperBound();

		/**
		 * The meta object literal for the '{@link DOM.impl.SingleVariableDeclarationImpl <em>Single Variable Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.SingleVariableDeclarationImpl
		 * @see DOM.impl.DOMPackageImpl#getSingleVariableDeclaration()
		 * @generated
		 */
		EClass SINGLE_VARIABLE_DECLARATION = eINSTANCE.getSingleVariableDeclaration();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINGLE_VARIABLE_DECLARATION__TYPE = eINSTANCE.getSingleVariableDeclaration_Type();

		/**
		 * The meta object literal for the '<em><b>Varargs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SINGLE_VARIABLE_DECLARATION__VARARGS = eINSTANCE.getSingleVariableDeclaration_Varargs();

		/**
		 * The meta object literal for the '<em><b>Modifiers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINGLE_VARIABLE_DECLARATION__MODIFIERS = eINSTANCE.getSingleVariableDeclaration_Modifiers();

		/**
		 * The meta object literal for the '{@link DOM.impl.VariableDeclarationFragmentImpl <em>Variable Declaration Fragment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.VariableDeclarationFragmentImpl
		 * @see DOM.impl.DOMPackageImpl#getVariableDeclarationFragment()
		 * @generated
		 */
		EClass VARIABLE_DECLARATION_FRAGMENT = eINSTANCE.getVariableDeclarationFragment();

		/**
		 * The meta object literal for the '{@link DOM.impl.QualifiedNameImpl <em>Qualified Name</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.QualifiedNameImpl
		 * @see DOM.impl.DOMPackageImpl#getQualifiedName()
		 * @generated
		 */
		EClass QUALIFIED_NAME = eINSTANCE.getQualifiedName();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALIFIED_NAME__NAME = eINSTANCE.getQualifiedName_Name();

		/**
		 * The meta object literal for the '<em><b>Qualifier</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALIFIED_NAME__QUALIFIER = eINSTANCE.getQualifiedName_Qualifier();

		/**
		 * The meta object literal for the '{@link DOM.impl.SimpleNameImpl <em>Simple Name</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.SimpleNameImpl
		 * @see DOM.impl.DOMPackageImpl#getSimpleName()
		 * @generated
		 */
		EClass SIMPLE_NAME = eINSTANCE.getSimpleName();

		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMPLE_NAME__IDENTIFIER = eINSTANCE.getSimpleName_Identifier();

		/**
		 * The meta object literal for the '<em><b>Declaration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMPLE_NAME__DECLARATION = eINSTANCE.getSimpleName_Declaration();

		/**
		 * The meta object literal for the '{@link DOM.impl.MarkerAnnotationImpl <em>Marker Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.MarkerAnnotationImpl
		 * @see DOM.impl.DOMPackageImpl#getMarkerAnnotation()
		 * @generated
		 */
		EClass MARKER_ANNOTATION = eINSTANCE.getMarkerAnnotation();

		/**
		 * The meta object literal for the '{@link DOM.impl.NormalAnnotationImpl <em>Normal Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.NormalAnnotationImpl
		 * @see DOM.impl.DOMPackageImpl#getNormalAnnotation()
		 * @generated
		 */
		EClass NORMAL_ANNOTATION = eINSTANCE.getNormalAnnotation();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NORMAL_ANNOTATION__VALUES = eINSTANCE.getNormalAnnotation_Values();

		/**
		 * The meta object literal for the '{@link DOM.impl.SingleMemberAnnotationImpl <em>Single Member Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.impl.SingleMemberAnnotationImpl
		 * @see DOM.impl.DOMPackageImpl#getSingleMemberAnnotation()
		 * @generated
		 */
		EClass SINGLE_MEMBER_ANNOTATION = eINSTANCE.getSingleMemberAnnotation();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINGLE_MEMBER_ANNOTATION__VALUE = eINSTANCE.getSingleMemberAnnotation_Value();

		/**
		 * The meta object literal for the '{@link DOM.AssignmentOperatorKind <em>Assignment Operator Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.AssignmentOperatorKind
		 * @see DOM.impl.DOMPackageImpl#getAssignmentOperatorKind()
		 * @generated
		 */
		EEnum ASSIGNMENT_OPERATOR_KIND = eINSTANCE.getAssignmentOperatorKind();

		/**
		 * The meta object literal for the '{@link DOM.InfixExpressionOperatorKind <em>Infix Expression Operator Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.InfixExpressionOperatorKind
		 * @see DOM.impl.DOMPackageImpl#getInfixExpressionOperatorKind()
		 * @generated
		 */
		EEnum INFIX_EXPRESSION_OPERATOR_KIND = eINSTANCE.getInfixExpressionOperatorKind();

		/**
		 * The meta object literal for the '{@link DOM.PostfixExpressionOperatorKind <em>Postfix Expression Operator Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.PostfixExpressionOperatorKind
		 * @see DOM.impl.DOMPackageImpl#getPostfixExpressionOperatorKind()
		 * @generated
		 */
		EEnum POSTFIX_EXPRESSION_OPERATOR_KIND = eINSTANCE.getPostfixExpressionOperatorKind();

		/**
		 * The meta object literal for the '{@link DOM.PrefixExpressionOperatorKind <em>Prefix Expression Operator Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see DOM.PrefixExpressionOperatorKind
		 * @see DOM.impl.DOMPackageImpl#getPrefixExpressionOperatorKind()
		 * @generated
		 */
		EEnum PREFIX_EXPRESSION_OPERATOR_KIND = eINSTANCE.getPrefixExpressionOperatorKind();

	}

} //DOMPackage
