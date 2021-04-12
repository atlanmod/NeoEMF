/**
 */
package org.eclipse.gmt.modisco.java.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeQualifiedExpression;
import org.eclipse.gmt.modisco.java.AbstractVariablesContainer;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnnotationTypeDeclaration;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.Archive;
import org.eclipse.gmt.modisco.java.ArrayAccess;
import org.eclipse.gmt.modisco.java.ArrayCreation;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.ArrayLengthAccess;
import org.eclipse.gmt.modisco.java.ArrayType;
import org.eclipse.gmt.modisco.java.AssertStatement;
import org.eclipse.gmt.modisco.java.Assignment;
import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.BlockComment;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.BooleanLiteral;
import org.eclipse.gmt.modisco.java.BreakStatement;
import org.eclipse.gmt.modisco.java.CastExpression;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.CharacterLiteral;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ConditionalExpression;
import org.eclipse.gmt.modisco.java.ConstructorDeclaration;
import org.eclipse.gmt.modisco.java.ConstructorInvocation;
import org.eclipse.gmt.modisco.java.ContinueStatement;
import org.eclipse.gmt.modisco.java.DoStatement;
import org.eclipse.gmt.modisco.java.EmptyStatement;
import org.eclipse.gmt.modisco.java.EnhancedForStatement;
import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.FieldAccess;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.ForStatement;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.InfixExpression;
import org.eclipse.gmt.modisco.java.InfixExpressionKind;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.Initializer;
import org.eclipse.gmt.modisco.java.InstanceofExpression;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.JavaFactory;
import org.eclipse.gmt.modisco.java.JavaPackage;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.LabeledStatement;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.Manifest;
import org.eclipse.gmt.modisco.java.ManifestAttribute;
import org.eclipse.gmt.modisco.java.ManifestEntry;
import org.eclipse.gmt.modisco.java.MemberRef;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.MethodRef;
import org.eclipse.gmt.modisco.java.MethodRefParameter;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.NamespaceAccess;
import org.eclipse.gmt.modisco.java.NullLiteral;
import org.eclipse.gmt.modisco.java.NumberLiteral;
import org.eclipse.gmt.modisco.java.PackageAccess;
import org.eclipse.gmt.modisco.java.ParameterizedType;
import org.eclipse.gmt.modisco.java.ParenthesizedExpression;
import org.eclipse.gmt.modisco.java.PostfixExpression;
import org.eclipse.gmt.modisco.java.PostfixExpressionKind;
import org.eclipse.gmt.modisco.java.PrefixExpression;
import org.eclipse.gmt.modisco.java.PrefixExpressionKind;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.PrimitiveTypeBoolean;
import org.eclipse.gmt.modisco.java.PrimitiveTypeByte;
import org.eclipse.gmt.modisco.java.PrimitiveTypeChar;
import org.eclipse.gmt.modisco.java.PrimitiveTypeDouble;
import org.eclipse.gmt.modisco.java.PrimitiveTypeFloat;
import org.eclipse.gmt.modisco.java.PrimitiveTypeInt;
import org.eclipse.gmt.modisco.java.PrimitiveTypeLong;
import org.eclipse.gmt.modisco.java.PrimitiveTypeShort;
import org.eclipse.gmt.modisco.java.PrimitiveTypeVoid;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.StringLiteral;
import org.eclipse.gmt.modisco.java.SuperConstructorInvocation;
import org.eclipse.gmt.modisco.java.SuperFieldAccess;
import org.eclipse.gmt.modisco.java.SuperMethodInvocation;
import org.eclipse.gmt.modisco.java.SwitchCase;
import org.eclipse.gmt.modisco.java.SwitchStatement;
import org.eclipse.gmt.modisco.java.SynchronizedStatement;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.ThisExpression;
import org.eclipse.gmt.modisco.java.ThrowStatement;
import org.eclipse.gmt.modisco.java.TryStatement;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeDeclaration;
import org.eclipse.gmt.modisco.java.TypeDeclarationStatement;
import org.eclipse.gmt.modisco.java.TypeLiteral;
import org.eclipse.gmt.modisco.java.TypeParameter;
import org.eclipse.gmt.modisco.java.UnresolvedAnnotationDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedAnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedClassDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedEnumDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedInterfaceDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedItem;
import org.eclipse.gmt.modisco.java.UnresolvedItemAccess;
import org.eclipse.gmt.modisco.java.UnresolvedLabeledStatement;
import org.eclipse.gmt.modisco.java.UnresolvedMethodDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedSingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedType;
import org.eclipse.gmt.modisco.java.UnresolvedTypeDeclaration;
import org.eclipse.gmt.modisco.java.UnresolvedVariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclaration;
import org.eclipse.gmt.modisco.java.VariableDeclarationExpression;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.WhileStatement;
import org.eclipse.gmt.modisco.java.WildCardType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JavaPackageImpl extends EPackageImpl implements JavaPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractMethodDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractMethodInvocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractTypeDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractTypeQualifiedExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractVariablesContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass archiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assertStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass astNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationMemberValuePairEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationTypeDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationTypeMemberDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anonymousClassDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayCreationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayInitializerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayLengthAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assignmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bodyDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockCommentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass breakStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass castExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass catchClauseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass characterLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classFileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classInstanceCreationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constructorDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionalExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constructorInvocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compilationUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass continueStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass emptyStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enhancedForStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumConstantDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass forStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ifStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass importDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infixExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass initializerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instanceofExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interfaceDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javadocEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labeledStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lineCommentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manifestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manifestAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass manifestEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass memberRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodInvocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodRefParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namespaceAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numberLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nullLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterizedTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parenthesizedExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass postfixExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass prefixExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeBooleanEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeByteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeCharEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeDoubleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeShortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeFloatEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeIntEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeLongEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeVoidEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass returnStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleVariableAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleVariableDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass statementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass superConstructorInvocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass superFieldAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass superMethodInvocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass switchCaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass switchStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass synchronizedStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass thisExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass throwStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tryStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeDeclarationStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeLiteralEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedItemAccessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedAnnotationDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedAnnotationTypeMemberDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedClassDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedEnumDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedInterfaceDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedLabeledStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedMethodDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedSingleVariableDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedTypeDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unresolvedVariableDeclarationFragmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableDeclarationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableDeclarationExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableDeclarationFragmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableDeclarationStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wildCardTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass whileStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum assignmentKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum infixExpressionKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum inheritanceKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum postfixExpressionKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum prefixExpressionKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum visibilityKindEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.gmt.modisco.java.JavaPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private JavaPackageImpl() {
		super(eNS_URI, JavaFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link JavaPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static JavaPackage init() {
		if (isInited) return (JavaPackage)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredJavaPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		JavaPackageImpl theJavaPackage = registeredJavaPackage instanceof JavaPackageImpl ? (JavaPackageImpl)registeredJavaPackage : new JavaPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theJavaPackage.createPackageContents();

		// Initialize created meta-data
		theJavaPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theJavaPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(JavaPackage.eNS_URI, theJavaPackage);
		return theJavaPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractMethodDeclaration() {
		return abstractMethodDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_Body() {
		return (EReference)abstractMethodDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_Parameters() {
		return (EReference)abstractMethodDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_ThrownExceptions() {
		return (EReference)abstractMethodDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_TypeParameters() {
		return (EReference)abstractMethodDeclarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_UsagesInDocComments() {
		return (EReference)abstractMethodDeclarationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_Usages() {
		return (EReference)abstractMethodDeclarationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractMethodInvocation() {
		return abstractMethodInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodInvocation_Method() {
		return (EReference)abstractMethodInvocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodInvocation_Arguments() {
		return (EReference)abstractMethodInvocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodInvocation_TypeArguments() {
		return (EReference)abstractMethodInvocationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractTypeDeclaration() {
		return abstractTypeDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeDeclaration_BodyDeclarations() {
		return (EReference)abstractTypeDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeDeclaration_CommentsBeforeBody() {
		return (EReference)abstractTypeDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeDeclaration_CommentsAfterBody() {
		return (EReference)abstractTypeDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeDeclaration_Package() {
		return (EReference)abstractTypeDeclarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeDeclaration_SuperInterfaces() {
		return (EReference)abstractTypeDeclarationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractTypeQualifiedExpression() {
		return abstractTypeQualifiedExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeQualifiedExpression_Qualifier() {
		return (EReference)abstractTypeQualifiedExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractVariablesContainer() {
		return abstractVariablesContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractVariablesContainer_Type() {
		return (EReference)abstractVariablesContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractVariablesContainer_Fragments() {
		return (EReference)abstractVariablesContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotation() {
		return annotationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotation_Type() {
		return (EReference)annotationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotation_Values() {
		return (EReference)annotationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchive() {
		return archiveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchive_OriginalFilePath() {
		return (EAttribute)archiveEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchive_ClassFiles() {
		return (EReference)archiveEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchive_Manifest() {
		return (EReference)archiveEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssertStatement() {
		return assertStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssertStatement_Message() {
		return (EReference)assertStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssertStatement_Expression() {
		return (EReference)assertStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getASTNode() {
		return astNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASTNode_Comments() {
		return (EReference)astNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASTNode_OriginalCompilationUnit() {
		return (EReference)astNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASTNode_OriginalClassFile() {
		return (EReference)astNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotationMemberValuePair() {
		return annotationMemberValuePairEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotationMemberValuePair_Member() {
		return (EReference)annotationMemberValuePairEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotationMemberValuePair_Value() {
		return (EReference)annotationMemberValuePairEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotationTypeDeclaration() {
		return annotationTypeDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotationTypeMemberDeclaration() {
		return annotationTypeMemberDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotationTypeMemberDeclaration_Default() {
		return (EReference)annotationTypeMemberDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotationTypeMemberDeclaration_Type() {
		return (EReference)annotationTypeMemberDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotationTypeMemberDeclaration_Usages() {
		return (EReference)annotationTypeMemberDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnonymousClassDeclaration() {
		return anonymousClassDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnonymousClassDeclaration_BodyDeclarations() {
		return (EReference)anonymousClassDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnonymousClassDeclaration_ClassInstanceCreation() {
		return (EReference)anonymousClassDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayAccess() {
		return arrayAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayAccess_Array() {
		return (EReference)arrayAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayAccess_Index() {
		return (EReference)arrayAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayCreation() {
		return arrayCreationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayCreation_Dimensions() {
		return (EReference)arrayCreationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayCreation_Initializer() {
		return (EReference)arrayCreationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayCreation_Type() {
		return (EReference)arrayCreationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayInitializer() {
		return arrayInitializerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayInitializer_Expressions() {
		return (EReference)arrayInitializerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayLengthAccess() {
		return arrayLengthAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayLengthAccess_Array() {
		return (EReference)arrayLengthAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayType() {
		return arrayTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayType_Dimensions() {
		return (EAttribute)arrayTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayType_ElementType() {
		return (EReference)arrayTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssignment() {
		return assignmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssignment_LeftHandSide() {
		return (EReference)assignmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Operator() {
		return (EAttribute)assignmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssignment_RightHandSide() {
		return (EReference)assignmentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBodyDeclaration() {
		return bodyDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBodyDeclaration_AbstractTypeDeclaration() {
		return (EReference)bodyDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBodyDeclaration_Annotations() {
		return (EReference)bodyDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBodyDeclaration_AnonymousClassDeclarationOwner() {
		return (EReference)bodyDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBodyDeclaration_Modifier() {
		return (EReference)bodyDeclarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanLiteral() {
		return booleanLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanLiteral_Value() {
		return (EAttribute)booleanLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlockComment() {
		return blockCommentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlock() {
		return blockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBlock_Statements() {
		return (EReference)blockEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBreakStatement() {
		return breakStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakStatement_Label() {
		return (EReference)breakStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCastExpression() {
		return castExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCastExpression_Expression() {
		return (EReference)castExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCastExpression_Type() {
		return (EReference)castExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCatchClause() {
		return catchClauseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCatchClause_Exception() {
		return (EReference)catchClauseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCatchClause_Body() {
		return (EReference)catchClauseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCharacterLiteral() {
		return characterLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterLiteral_EscapedValue() {
		return (EAttribute)characterLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassFile() {
		return classFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassFile_OriginalFilePath() {
		return (EAttribute)classFileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFile_Type() {
		return (EReference)classFileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFile_AttachedSource() {
		return (EReference)classFileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFile_Package() {
		return (EReference)classFileEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassInstanceCreation() {
		return classInstanceCreationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassInstanceCreation_AnonymousClassDeclaration() {
		return (EReference)classInstanceCreationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassInstanceCreation_Expression() {
		return (EReference)classInstanceCreationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassInstanceCreation_Type() {
		return (EReference)classInstanceCreationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstructorDeclaration() {
		return constructorDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionalExpression() {
		return conditionalExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalExpression_ElseExpression() {
		return (EReference)conditionalExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalExpression_Expression() {
		return (EReference)conditionalExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalExpression_ThenExpression() {
		return (EReference)conditionalExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstructorInvocation() {
		return constructorInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassDeclaration() {
		return classDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassDeclaration_SuperClass() {
		return (EReference)classDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComment() {
		return commentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Content() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_EnclosedByParent() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_PrefixOfParent() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompilationUnit() {
		return compilationUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompilationUnit_OriginalFilePath() {
		return (EAttribute)compilationUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationUnit_CommentList() {
		return (EReference)compilationUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationUnit_Imports() {
		return (EReference)compilationUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationUnit_Package() {
		return (EReference)compilationUnitEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationUnit_Types() {
		return (EReference)compilationUnitEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContinueStatement() {
		return continueStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContinueStatement_Label() {
		return (EReference)continueStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDoStatement() {
		return doStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDoStatement_Expression() {
		return (EReference)doStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDoStatement_Body() {
		return (EReference)doStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEmptyStatement() {
		return emptyStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnhancedForStatement() {
		return enhancedForStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnhancedForStatement_Body() {
		return (EReference)enhancedForStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnhancedForStatement_Expression() {
		return (EReference)enhancedForStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnhancedForStatement_Parameter() {
		return (EReference)enhancedForStatementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumConstantDeclaration() {
		return enumConstantDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumConstantDeclaration_AnonymousClassDeclaration() {
		return (EReference)enumConstantDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumConstantDeclaration_Arguments() {
		return (EReference)enumConstantDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumDeclaration() {
		return enumDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumDeclaration_EnumConstants() {
		return (EReference)enumDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpression() {
		return expressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpressionStatement() {
		return expressionStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExpressionStatement_Expression() {
		return (EReference)expressionStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFieldAccess() {
		return fieldAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFieldAccess_Field() {
		return (EReference)fieldAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFieldAccess_Expression() {
		return (EReference)fieldAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFieldDeclaration() {
		return fieldDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getForStatement() {
		return forStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForStatement_Expression() {
		return (EReference)forStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForStatement_Updaters() {
		return (EReference)forStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForStatement_Initializers() {
		return (EReference)forStatementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForStatement_Body() {
		return (EReference)forStatementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIfStatement() {
		return ifStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfStatement_Expression() {
		return (EReference)ifStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfStatement_ThenStatement() {
		return (EReference)ifStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfStatement_ElseStatement() {
		return (EReference)ifStatementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImportDeclaration() {
		return importDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImportDeclaration_Static() {
		return (EAttribute)importDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImportDeclaration_ImportedElement() {
		return (EReference)importDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfixExpression() {
		return infixExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfixExpression_Operator() {
		return (EAttribute)infixExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfixExpression_RightOperand() {
		return (EReference)infixExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfixExpression_LeftOperand() {
		return (EReference)infixExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfixExpression_ExtendedOperands() {
		return (EReference)infixExpressionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInitializer() {
		return initializerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInitializer_Body() {
		return (EReference)initializerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstanceofExpression() {
		return instanceofExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanceofExpression_RightOperand() {
		return (EReference)instanceofExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanceofExpression_LeftOperand() {
		return (EReference)instanceofExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterfaceDeclaration() {
		return interfaceDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavadoc() {
		return javadocEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJavadoc_Tags() {
		return (EReference)javadocEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabeledStatement() {
		return labeledStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLabeledStatement_Body() {
		return (EReference)labeledStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLabeledStatement_UsagesInBreakStatements() {
		return (EReference)labeledStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLabeledStatement_UsagesInContinueStatements() {
		return (EReference)labeledStatementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLineComment() {
		return lineCommentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManifest() {
		return manifestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getManifest_MainAttributes() {
		return (EReference)manifestEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getManifest_EntryAttributes() {
		return (EReference)manifestEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManifestAttribute() {
		return manifestAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManifestAttribute_Key() {
		return (EAttribute)manifestAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManifestAttribute_Value() {
		return (EAttribute)manifestAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManifestEntry() {
		return manifestEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManifestEntry_Name() {
		return (EAttribute)manifestEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getManifestEntry_Attributes() {
		return (EReference)manifestEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMemberRef() {
		return memberRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberRef_Member() {
		return (EReference)memberRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberRef_Qualifier() {
		return (EReference)memberRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodDeclaration() {
		return methodDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodDeclaration_ExtraArrayDimensions() {
		return (EAttribute)methodDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodDeclaration_ReturnType() {
		return (EReference)methodDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodDeclaration_RedefinedMethodDeclaration() {
		return (EReference)methodDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodDeclaration_Redefinitions() {
		return (EReference)methodDeclarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodInvocation() {
		return methodInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodInvocation_Expression() {
		return (EReference)methodInvocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodRef() {
		return methodRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodRef_Method() {
		return (EReference)methodRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodRef_Qualifier() {
		return (EReference)methodRefEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodRef_Parameters() {
		return (EReference)methodRefEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodRefParameter() {
		return methodRefParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodRefParameter_Name() {
		return (EAttribute)methodRefParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodRefParameter_Varargs() {
		return (EAttribute)methodRefParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodRefParameter_Type() {
		return (EReference)methodRefParameterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModel() {
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModel_Name() {
		return (EAttribute)modelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_OwnedElements() {
		return (EReference)modelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_OrphanTypes() {
		return (EReference)modelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_UnresolvedItems() {
		return (EReference)modelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_CompilationUnits() {
		return (EReference)modelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_ClassFiles() {
		return (EReference)modelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Archives() {
		return (EReference)modelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModifier() {
		return modifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Visibility() {
		return (EAttribute)modifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Inheritance() {
		return (EAttribute)modifierEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Static() {
		return (EAttribute)modifierEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Transient() {
		return (EAttribute)modifierEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Volatile() {
		return (EAttribute)modifierEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Native() {
		return (EAttribute)modifierEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Strictfp() {
		return (EAttribute)modifierEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Synchronized() {
		return (EAttribute)modifierEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModifier_BodyDeclaration() {
		return (EReference)modifierEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModifier_SingleVariableDeclaration() {
		return (EReference)modifierEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModifier_VariableDeclarationStatement() {
		return (EReference)modifierEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModifier_VariableDeclarationExpression() {
		return (EReference)modifierEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Proxy() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamedElement_UsagesInImports() {
		return (EReference)namedElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamespaceAccess() {
		return namespaceAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumberLiteral() {
		return numberLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumberLiteral_TokenValue() {
		return (EAttribute)numberLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNullLiteral() {
		return nullLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackage() {
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_OwnedElements() {
		return (EReference)packageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_Model() {
		return (EReference)packageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_OwnedPackages() {
		return (EReference)packageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_Package() {
		return (EReference)packageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_UsagesInPackageAccess() {
		return (EReference)packageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageAccess() {
		return packageAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageAccess_Package() {
		return (EReference)packageAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageAccess_Qualifier() {
		return (EReference)packageAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterizedType() {
		return parameterizedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterizedType_Type() {
		return (EReference)parameterizedTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterizedType_TypeArguments() {
		return (EReference)parameterizedTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParenthesizedExpression() {
		return parenthesizedExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParenthesizedExpression_Expression() {
		return (EReference)parenthesizedExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPostfixExpression() {
		return postfixExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPostfixExpression_Operator() {
		return (EAttribute)postfixExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPostfixExpression_Operand() {
		return (EReference)postfixExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrefixExpression() {
		return prefixExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrefixExpression_Operator() {
		return (EAttribute)prefixExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPrefixExpression_Operand() {
		return (EReference)prefixExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveType() {
		return primitiveTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeBoolean() {
		return primitiveTypeBooleanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeByte() {
		return primitiveTypeByteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeChar() {
		return primitiveTypeCharEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeDouble() {
		return primitiveTypeDoubleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeShort() {
		return primitiveTypeShortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeFloat() {
		return primitiveTypeFloatEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeInt() {
		return primitiveTypeIntEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeLong() {
		return primitiveTypeLongEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeVoid() {
		return primitiveTypeVoidEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReturnStatement() {
		return returnStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReturnStatement_Expression() {
		return (EReference)returnStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleVariableAccess() {
		return singleVariableAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableAccess_Variable() {
		return (EReference)singleVariableAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableAccess_Qualifier() {
		return (EReference)singleVariableAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleVariableDeclaration() {
		return singleVariableDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_Modifier() {
		return (EReference)singleVariableDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSingleVariableDeclaration_Varargs() {
		return (EAttribute)singleVariableDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_Type() {
		return (EReference)singleVariableDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_Annotations() {
		return (EReference)singleVariableDeclarationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_MethodDeclaration() {
		return (EReference)singleVariableDeclarationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_CatchClause() {
		return (EReference)singleVariableDeclarationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_EnhancedForStatement() {
		return (EReference)singleVariableDeclarationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStatement() {
		return statementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringLiteral() {
		return stringLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringLiteral_EscapedValue() {
		return (EAttribute)stringLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSuperConstructorInvocation() {
		return superConstructorInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperConstructorInvocation_Expression() {
		return (EReference)superConstructorInvocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSuperFieldAccess() {
		return superFieldAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperFieldAccess_Field() {
		return (EReference)superFieldAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSuperMethodInvocation() {
		return superMethodInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwitchCase() {
		return switchCaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwitchCase_Default() {
		return (EAttribute)switchCaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchCase_Expression() {
		return (EReference)switchCaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwitchStatement() {
		return switchStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchStatement_Expression() {
		return (EReference)switchStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchStatement_Statements() {
		return (EReference)switchStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSynchronizedStatement() {
		return synchronizedStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSynchronizedStatement_Body() {
		return (EReference)synchronizedStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSynchronizedStatement_Expression() {
		return (EReference)synchronizedStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTagElement() {
		return tagElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagElement_TagName() {
		return (EAttribute)tagElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTagElement_Fragments() {
		return (EReference)tagElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextElement() {
		return textElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTextElement_Text() {
		return (EAttribute)textElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThisExpression() {
		return thisExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThrowStatement() {
		return throwStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getThrowStatement_Expression() {
		return (EReference)throwStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTryStatement() {
		return tryStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTryStatement_Body() {
		return (EReference)tryStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTryStatement_Finally() {
		return (EReference)tryStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTryStatement_CatchClauses() {
		return (EReference)tryStatementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getType() {
		return typeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getType_UsagesInTypeAccess() {
		return (EReference)typeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeAccess() {
		return typeAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeAccess_Type() {
		return (EReference)typeAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeAccess_Qualifier() {
		return (EReference)typeAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeDeclaration() {
		return typeDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclaration_TypeParameters() {
		return (EReference)typeDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeDeclarationStatement() {
		return typeDeclarationStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationStatement_Declaration() {
		return (EReference)typeDeclarationStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeLiteral() {
		return typeLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeLiteral_Type() {
		return (EReference)typeLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeParameter() {
		return typeParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeParameter_Bounds() {
		return (EReference)typeParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedItem() {
		return unresolvedItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedItemAccess() {
		return unresolvedItemAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnresolvedItemAccess_Element() {
		return (EReference)unresolvedItemAccessEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnresolvedItemAccess_Qualifier() {
		return (EReference)unresolvedItemAccessEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedAnnotationDeclaration() {
		return unresolvedAnnotationDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedAnnotationTypeMemberDeclaration() {
		return unresolvedAnnotationTypeMemberDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedClassDeclaration() {
		return unresolvedClassDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedEnumDeclaration() {
		return unresolvedEnumDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedInterfaceDeclaration() {
		return unresolvedInterfaceDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedLabeledStatement() {
		return unresolvedLabeledStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedMethodDeclaration() {
		return unresolvedMethodDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedSingleVariableDeclaration() {
		return unresolvedSingleVariableDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedType() {
		return unresolvedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedTypeDeclaration() {
		return unresolvedTypeDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedVariableDeclarationFragment() {
		return unresolvedVariableDeclarationFragmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableDeclaration() {
		return variableDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableDeclaration_ExtraArrayDimensions() {
		return (EAttribute)variableDeclarationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclaration_Initializer() {
		return (EReference)variableDeclarationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclaration_UsageInVariableAccess() {
		return (EReference)variableDeclarationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableDeclarationExpression() {
		return variableDeclarationExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclarationExpression_Modifier() {
		return (EReference)variableDeclarationExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclarationExpression_Annotations() {
		return (EReference)variableDeclarationExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableDeclarationFragment() {
		return variableDeclarationFragmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclarationFragment_VariablesContainer() {
		return (EReference)variableDeclarationFragmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableDeclarationStatement() {
		return variableDeclarationStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableDeclarationStatement_ExtraArrayDimensions() {
		return (EAttribute)variableDeclarationStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclarationStatement_Modifier() {
		return (EReference)variableDeclarationStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclarationStatement_Annotations() {
		return (EReference)variableDeclarationStatementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWildCardType() {
		return wildCardTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWildCardType_UpperBound() {
		return (EAttribute)wildCardTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWildCardType_Bound() {
		return (EReference)wildCardTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWhileStatement() {
		return whileStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWhileStatement_Expression() {
		return (EReference)whileStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWhileStatement_Body() {
		return (EReference)whileStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAssignmentKind() {
		return assignmentKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getInfixExpressionKind() {
		return infixExpressionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getInheritanceKind() {
		return inheritanceKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPostfixExpressionKind() {
		return postfixExpressionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPrefixExpressionKind() {
		return prefixExpressionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getVisibilityKind() {
		return visibilityKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaFactory getJavaFactory() {
		return (JavaFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		abstractMethodDeclarationEClass = createEClass(ABSTRACT_METHOD_DECLARATION);
		createEReference(abstractMethodDeclarationEClass, ABSTRACT_METHOD_DECLARATION__BODY);
		createEReference(abstractMethodDeclarationEClass, ABSTRACT_METHOD_DECLARATION__PARAMETERS);
		createEReference(abstractMethodDeclarationEClass, ABSTRACT_METHOD_DECLARATION__THROWN_EXCEPTIONS);
		createEReference(abstractMethodDeclarationEClass, ABSTRACT_METHOD_DECLARATION__TYPE_PARAMETERS);
		createEReference(abstractMethodDeclarationEClass, ABSTRACT_METHOD_DECLARATION__USAGES_IN_DOC_COMMENTS);
		createEReference(abstractMethodDeclarationEClass, ABSTRACT_METHOD_DECLARATION__USAGES);

		abstractMethodInvocationEClass = createEClass(ABSTRACT_METHOD_INVOCATION);
		createEReference(abstractMethodInvocationEClass, ABSTRACT_METHOD_INVOCATION__METHOD);
		createEReference(abstractMethodInvocationEClass, ABSTRACT_METHOD_INVOCATION__ARGUMENTS);
		createEReference(abstractMethodInvocationEClass, ABSTRACT_METHOD_INVOCATION__TYPE_ARGUMENTS);

		abstractTypeDeclarationEClass = createEClass(ABSTRACT_TYPE_DECLARATION);
		createEReference(abstractTypeDeclarationEClass, ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS);
		createEReference(abstractTypeDeclarationEClass, ABSTRACT_TYPE_DECLARATION__COMMENTS_BEFORE_BODY);
		createEReference(abstractTypeDeclarationEClass, ABSTRACT_TYPE_DECLARATION__COMMENTS_AFTER_BODY);
		createEReference(abstractTypeDeclarationEClass, ABSTRACT_TYPE_DECLARATION__PACKAGE);
		createEReference(abstractTypeDeclarationEClass, ABSTRACT_TYPE_DECLARATION__SUPER_INTERFACES);

		abstractTypeQualifiedExpressionEClass = createEClass(ABSTRACT_TYPE_QUALIFIED_EXPRESSION);
		createEReference(abstractTypeQualifiedExpressionEClass, ABSTRACT_TYPE_QUALIFIED_EXPRESSION__QUALIFIER);

		abstractVariablesContainerEClass = createEClass(ABSTRACT_VARIABLES_CONTAINER);
		createEReference(abstractVariablesContainerEClass, ABSTRACT_VARIABLES_CONTAINER__TYPE);
		createEReference(abstractVariablesContainerEClass, ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS);

		annotationEClass = createEClass(ANNOTATION);
		createEReference(annotationEClass, ANNOTATION__TYPE);
		createEReference(annotationEClass, ANNOTATION__VALUES);

		archiveEClass = createEClass(ARCHIVE);
		createEAttribute(archiveEClass, ARCHIVE__ORIGINAL_FILE_PATH);
		createEReference(archiveEClass, ARCHIVE__CLASS_FILES);
		createEReference(archiveEClass, ARCHIVE__MANIFEST);

		assertStatementEClass = createEClass(ASSERT_STATEMENT);
		createEReference(assertStatementEClass, ASSERT_STATEMENT__MESSAGE);
		createEReference(assertStatementEClass, ASSERT_STATEMENT__EXPRESSION);

		astNodeEClass = createEClass(AST_NODE);
		createEReference(astNodeEClass, AST_NODE__COMMENTS);
		createEReference(astNodeEClass, AST_NODE__ORIGINAL_COMPILATION_UNIT);
		createEReference(astNodeEClass, AST_NODE__ORIGINAL_CLASS_FILE);

		annotationMemberValuePairEClass = createEClass(ANNOTATION_MEMBER_VALUE_PAIR);
		createEReference(annotationMemberValuePairEClass, ANNOTATION_MEMBER_VALUE_PAIR__MEMBER);
		createEReference(annotationMemberValuePairEClass, ANNOTATION_MEMBER_VALUE_PAIR__VALUE);

		annotationTypeDeclarationEClass = createEClass(ANNOTATION_TYPE_DECLARATION);

		annotationTypeMemberDeclarationEClass = createEClass(ANNOTATION_TYPE_MEMBER_DECLARATION);
		createEReference(annotationTypeMemberDeclarationEClass, ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT);
		createEReference(annotationTypeMemberDeclarationEClass, ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE);
		createEReference(annotationTypeMemberDeclarationEClass, ANNOTATION_TYPE_MEMBER_DECLARATION__USAGES);

		anonymousClassDeclarationEClass = createEClass(ANONYMOUS_CLASS_DECLARATION);
		createEReference(anonymousClassDeclarationEClass, ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS);
		createEReference(anonymousClassDeclarationEClass, ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION);

		arrayAccessEClass = createEClass(ARRAY_ACCESS);
		createEReference(arrayAccessEClass, ARRAY_ACCESS__ARRAY);
		createEReference(arrayAccessEClass, ARRAY_ACCESS__INDEX);

		arrayCreationEClass = createEClass(ARRAY_CREATION);
		createEReference(arrayCreationEClass, ARRAY_CREATION__DIMENSIONS);
		createEReference(arrayCreationEClass, ARRAY_CREATION__INITIALIZER);
		createEReference(arrayCreationEClass, ARRAY_CREATION__TYPE);

		arrayInitializerEClass = createEClass(ARRAY_INITIALIZER);
		createEReference(arrayInitializerEClass, ARRAY_INITIALIZER__EXPRESSIONS);

		arrayLengthAccessEClass = createEClass(ARRAY_LENGTH_ACCESS);
		createEReference(arrayLengthAccessEClass, ARRAY_LENGTH_ACCESS__ARRAY);

		arrayTypeEClass = createEClass(ARRAY_TYPE);
		createEAttribute(arrayTypeEClass, ARRAY_TYPE__DIMENSIONS);
		createEReference(arrayTypeEClass, ARRAY_TYPE__ELEMENT_TYPE);

		assignmentEClass = createEClass(ASSIGNMENT);
		createEReference(assignmentEClass, ASSIGNMENT__LEFT_HAND_SIDE);
		createEAttribute(assignmentEClass, ASSIGNMENT__OPERATOR);
		createEReference(assignmentEClass, ASSIGNMENT__RIGHT_HAND_SIDE);

		bodyDeclarationEClass = createEClass(BODY_DECLARATION);
		createEReference(bodyDeclarationEClass, BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION);
		createEReference(bodyDeclarationEClass, BODY_DECLARATION__ANNOTATIONS);
		createEReference(bodyDeclarationEClass, BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER);
		createEReference(bodyDeclarationEClass, BODY_DECLARATION__MODIFIER);

		booleanLiteralEClass = createEClass(BOOLEAN_LITERAL);
		createEAttribute(booleanLiteralEClass, BOOLEAN_LITERAL__VALUE);

		blockCommentEClass = createEClass(BLOCK_COMMENT);

		blockEClass = createEClass(BLOCK);
		createEReference(blockEClass, BLOCK__STATEMENTS);

		breakStatementEClass = createEClass(BREAK_STATEMENT);
		createEReference(breakStatementEClass, BREAK_STATEMENT__LABEL);

		castExpressionEClass = createEClass(CAST_EXPRESSION);
		createEReference(castExpressionEClass, CAST_EXPRESSION__EXPRESSION);
		createEReference(castExpressionEClass, CAST_EXPRESSION__TYPE);

		catchClauseEClass = createEClass(CATCH_CLAUSE);
		createEReference(catchClauseEClass, CATCH_CLAUSE__EXCEPTION);
		createEReference(catchClauseEClass, CATCH_CLAUSE__BODY);

		characterLiteralEClass = createEClass(CHARACTER_LITERAL);
		createEAttribute(characterLiteralEClass, CHARACTER_LITERAL__ESCAPED_VALUE);

		classFileEClass = createEClass(CLASS_FILE);
		createEAttribute(classFileEClass, CLASS_FILE__ORIGINAL_FILE_PATH);
		createEReference(classFileEClass, CLASS_FILE__TYPE);
		createEReference(classFileEClass, CLASS_FILE__ATTACHED_SOURCE);
		createEReference(classFileEClass, CLASS_FILE__PACKAGE);

		classInstanceCreationEClass = createEClass(CLASS_INSTANCE_CREATION);
		createEReference(classInstanceCreationEClass, CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION);
		createEReference(classInstanceCreationEClass, CLASS_INSTANCE_CREATION__EXPRESSION);
		createEReference(classInstanceCreationEClass, CLASS_INSTANCE_CREATION__TYPE);

		constructorDeclarationEClass = createEClass(CONSTRUCTOR_DECLARATION);

		conditionalExpressionEClass = createEClass(CONDITIONAL_EXPRESSION);
		createEReference(conditionalExpressionEClass, CONDITIONAL_EXPRESSION__ELSE_EXPRESSION);
		createEReference(conditionalExpressionEClass, CONDITIONAL_EXPRESSION__EXPRESSION);
		createEReference(conditionalExpressionEClass, CONDITIONAL_EXPRESSION__THEN_EXPRESSION);

		constructorInvocationEClass = createEClass(CONSTRUCTOR_INVOCATION);

		classDeclarationEClass = createEClass(CLASS_DECLARATION);
		createEReference(classDeclarationEClass, CLASS_DECLARATION__SUPER_CLASS);

		commentEClass = createEClass(COMMENT);
		createEAttribute(commentEClass, COMMENT__CONTENT);
		createEAttribute(commentEClass, COMMENT__ENCLOSED_BY_PARENT);
		createEAttribute(commentEClass, COMMENT__PREFIX_OF_PARENT);

		compilationUnitEClass = createEClass(COMPILATION_UNIT);
		createEAttribute(compilationUnitEClass, COMPILATION_UNIT__ORIGINAL_FILE_PATH);
		createEReference(compilationUnitEClass, COMPILATION_UNIT__COMMENT_LIST);
		createEReference(compilationUnitEClass, COMPILATION_UNIT__IMPORTS);
		createEReference(compilationUnitEClass, COMPILATION_UNIT__PACKAGE);
		createEReference(compilationUnitEClass, COMPILATION_UNIT__TYPES);

		continueStatementEClass = createEClass(CONTINUE_STATEMENT);
		createEReference(continueStatementEClass, CONTINUE_STATEMENT__LABEL);

		doStatementEClass = createEClass(DO_STATEMENT);
		createEReference(doStatementEClass, DO_STATEMENT__EXPRESSION);
		createEReference(doStatementEClass, DO_STATEMENT__BODY);

		emptyStatementEClass = createEClass(EMPTY_STATEMENT);

		enhancedForStatementEClass = createEClass(ENHANCED_FOR_STATEMENT);
		createEReference(enhancedForStatementEClass, ENHANCED_FOR_STATEMENT__BODY);
		createEReference(enhancedForStatementEClass, ENHANCED_FOR_STATEMENT__EXPRESSION);
		createEReference(enhancedForStatementEClass, ENHANCED_FOR_STATEMENT__PARAMETER);

		enumConstantDeclarationEClass = createEClass(ENUM_CONSTANT_DECLARATION);
		createEReference(enumConstantDeclarationEClass, ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION);
		createEReference(enumConstantDeclarationEClass, ENUM_CONSTANT_DECLARATION__ARGUMENTS);

		enumDeclarationEClass = createEClass(ENUM_DECLARATION);
		createEReference(enumDeclarationEClass, ENUM_DECLARATION__ENUM_CONSTANTS);

		expressionEClass = createEClass(EXPRESSION);

		expressionStatementEClass = createEClass(EXPRESSION_STATEMENT);
		createEReference(expressionStatementEClass, EXPRESSION_STATEMENT__EXPRESSION);

		fieldAccessEClass = createEClass(FIELD_ACCESS);
		createEReference(fieldAccessEClass, FIELD_ACCESS__FIELD);
		createEReference(fieldAccessEClass, FIELD_ACCESS__EXPRESSION);

		fieldDeclarationEClass = createEClass(FIELD_DECLARATION);

		forStatementEClass = createEClass(FOR_STATEMENT);
		createEReference(forStatementEClass, FOR_STATEMENT__EXPRESSION);
		createEReference(forStatementEClass, FOR_STATEMENT__UPDATERS);
		createEReference(forStatementEClass, FOR_STATEMENT__INITIALIZERS);
		createEReference(forStatementEClass, FOR_STATEMENT__BODY);

		ifStatementEClass = createEClass(IF_STATEMENT);
		createEReference(ifStatementEClass, IF_STATEMENT__EXPRESSION);
		createEReference(ifStatementEClass, IF_STATEMENT__THEN_STATEMENT);
		createEReference(ifStatementEClass, IF_STATEMENT__ELSE_STATEMENT);

		importDeclarationEClass = createEClass(IMPORT_DECLARATION);
		createEAttribute(importDeclarationEClass, IMPORT_DECLARATION__STATIC);
		createEReference(importDeclarationEClass, IMPORT_DECLARATION__IMPORTED_ELEMENT);

		infixExpressionEClass = createEClass(INFIX_EXPRESSION);
		createEAttribute(infixExpressionEClass, INFIX_EXPRESSION__OPERATOR);
		createEReference(infixExpressionEClass, INFIX_EXPRESSION__RIGHT_OPERAND);
		createEReference(infixExpressionEClass, INFIX_EXPRESSION__LEFT_OPERAND);
		createEReference(infixExpressionEClass, INFIX_EXPRESSION__EXTENDED_OPERANDS);

		initializerEClass = createEClass(INITIALIZER);
		createEReference(initializerEClass, INITIALIZER__BODY);

		instanceofExpressionEClass = createEClass(INSTANCEOF_EXPRESSION);
		createEReference(instanceofExpressionEClass, INSTANCEOF_EXPRESSION__RIGHT_OPERAND);
		createEReference(instanceofExpressionEClass, INSTANCEOF_EXPRESSION__LEFT_OPERAND);

		interfaceDeclarationEClass = createEClass(INTERFACE_DECLARATION);

		javadocEClass = createEClass(JAVADOC);
		createEReference(javadocEClass, JAVADOC__TAGS);

		labeledStatementEClass = createEClass(LABELED_STATEMENT);
		createEReference(labeledStatementEClass, LABELED_STATEMENT__BODY);
		createEReference(labeledStatementEClass, LABELED_STATEMENT__USAGES_IN_BREAK_STATEMENTS);
		createEReference(labeledStatementEClass, LABELED_STATEMENT__USAGES_IN_CONTINUE_STATEMENTS);

		lineCommentEClass = createEClass(LINE_COMMENT);

		manifestEClass = createEClass(MANIFEST);
		createEReference(manifestEClass, MANIFEST__MAIN_ATTRIBUTES);
		createEReference(manifestEClass, MANIFEST__ENTRY_ATTRIBUTES);

		manifestAttributeEClass = createEClass(MANIFEST_ATTRIBUTE);
		createEAttribute(manifestAttributeEClass, MANIFEST_ATTRIBUTE__KEY);
		createEAttribute(manifestAttributeEClass, MANIFEST_ATTRIBUTE__VALUE);

		manifestEntryEClass = createEClass(MANIFEST_ENTRY);
		createEAttribute(manifestEntryEClass, MANIFEST_ENTRY__NAME);
		createEReference(manifestEntryEClass, MANIFEST_ENTRY__ATTRIBUTES);

		memberRefEClass = createEClass(MEMBER_REF);
		createEReference(memberRefEClass, MEMBER_REF__MEMBER);
		createEReference(memberRefEClass, MEMBER_REF__QUALIFIER);

		methodDeclarationEClass = createEClass(METHOD_DECLARATION);
		createEAttribute(methodDeclarationEClass, METHOD_DECLARATION__EXTRA_ARRAY_DIMENSIONS);
		createEReference(methodDeclarationEClass, METHOD_DECLARATION__RETURN_TYPE);
		createEReference(methodDeclarationEClass, METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION);
		createEReference(methodDeclarationEClass, METHOD_DECLARATION__REDEFINITIONS);

		methodInvocationEClass = createEClass(METHOD_INVOCATION);
		createEReference(methodInvocationEClass, METHOD_INVOCATION__EXPRESSION);

		methodRefEClass = createEClass(METHOD_REF);
		createEReference(methodRefEClass, METHOD_REF__METHOD);
		createEReference(methodRefEClass, METHOD_REF__QUALIFIER);
		createEReference(methodRefEClass, METHOD_REF__PARAMETERS);

		methodRefParameterEClass = createEClass(METHOD_REF_PARAMETER);
		createEAttribute(methodRefParameterEClass, METHOD_REF_PARAMETER__NAME);
		createEAttribute(methodRefParameterEClass, METHOD_REF_PARAMETER__VARARGS);
		createEReference(methodRefParameterEClass, METHOD_REF_PARAMETER__TYPE);

		modelEClass = createEClass(MODEL);
		createEAttribute(modelEClass, MODEL__NAME);
		createEReference(modelEClass, MODEL__OWNED_ELEMENTS);
		createEReference(modelEClass, MODEL__ORPHAN_TYPES);
		createEReference(modelEClass, MODEL__UNRESOLVED_ITEMS);
		createEReference(modelEClass, MODEL__COMPILATION_UNITS);
		createEReference(modelEClass, MODEL__CLASS_FILES);
		createEReference(modelEClass, MODEL__ARCHIVES);

		modifierEClass = createEClass(MODIFIER);
		createEAttribute(modifierEClass, MODIFIER__VISIBILITY);
		createEAttribute(modifierEClass, MODIFIER__INHERITANCE);
		createEAttribute(modifierEClass, MODIFIER__STATIC);
		createEAttribute(modifierEClass, MODIFIER__TRANSIENT);
		createEAttribute(modifierEClass, MODIFIER__VOLATILE);
		createEAttribute(modifierEClass, MODIFIER__NATIVE);
		createEAttribute(modifierEClass, MODIFIER__STRICTFP);
		createEAttribute(modifierEClass, MODIFIER__SYNCHRONIZED);
		createEReference(modifierEClass, MODIFIER__BODY_DECLARATION);
		createEReference(modifierEClass, MODIFIER__SINGLE_VARIABLE_DECLARATION);
		createEReference(modifierEClass, MODIFIER__VARIABLE_DECLARATION_STATEMENT);
		createEReference(modifierEClass, MODIFIER__VARIABLE_DECLARATION_EXPRESSION);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__PROXY);
		createEReference(namedElementEClass, NAMED_ELEMENT__USAGES_IN_IMPORTS);

		namespaceAccessEClass = createEClass(NAMESPACE_ACCESS);

		numberLiteralEClass = createEClass(NUMBER_LITERAL);
		createEAttribute(numberLiteralEClass, NUMBER_LITERAL__TOKEN_VALUE);

		nullLiteralEClass = createEClass(NULL_LITERAL);

		packageEClass = createEClass(PACKAGE);
		createEReference(packageEClass, PACKAGE__OWNED_ELEMENTS);
		createEReference(packageEClass, PACKAGE__MODEL);
		createEReference(packageEClass, PACKAGE__OWNED_PACKAGES);
		createEReference(packageEClass, PACKAGE__PACKAGE);
		createEReference(packageEClass, PACKAGE__USAGES_IN_PACKAGE_ACCESS);

		packageAccessEClass = createEClass(PACKAGE_ACCESS);
		createEReference(packageAccessEClass, PACKAGE_ACCESS__PACKAGE);
		createEReference(packageAccessEClass, PACKAGE_ACCESS__QUALIFIER);

		parameterizedTypeEClass = createEClass(PARAMETERIZED_TYPE);
		createEReference(parameterizedTypeEClass, PARAMETERIZED_TYPE__TYPE);
		createEReference(parameterizedTypeEClass, PARAMETERIZED_TYPE__TYPE_ARGUMENTS);

		parenthesizedExpressionEClass = createEClass(PARENTHESIZED_EXPRESSION);
		createEReference(parenthesizedExpressionEClass, PARENTHESIZED_EXPRESSION__EXPRESSION);

		postfixExpressionEClass = createEClass(POSTFIX_EXPRESSION);
		createEAttribute(postfixExpressionEClass, POSTFIX_EXPRESSION__OPERATOR);
		createEReference(postfixExpressionEClass, POSTFIX_EXPRESSION__OPERAND);

		prefixExpressionEClass = createEClass(PREFIX_EXPRESSION);
		createEAttribute(prefixExpressionEClass, PREFIX_EXPRESSION__OPERATOR);
		createEReference(prefixExpressionEClass, PREFIX_EXPRESSION__OPERAND);

		primitiveTypeEClass = createEClass(PRIMITIVE_TYPE);

		primitiveTypeBooleanEClass = createEClass(PRIMITIVE_TYPE_BOOLEAN);

		primitiveTypeByteEClass = createEClass(PRIMITIVE_TYPE_BYTE);

		primitiveTypeCharEClass = createEClass(PRIMITIVE_TYPE_CHAR);

		primitiveTypeDoubleEClass = createEClass(PRIMITIVE_TYPE_DOUBLE);

		primitiveTypeShortEClass = createEClass(PRIMITIVE_TYPE_SHORT);

		primitiveTypeFloatEClass = createEClass(PRIMITIVE_TYPE_FLOAT);

		primitiveTypeIntEClass = createEClass(PRIMITIVE_TYPE_INT);

		primitiveTypeLongEClass = createEClass(PRIMITIVE_TYPE_LONG);

		primitiveTypeVoidEClass = createEClass(PRIMITIVE_TYPE_VOID);

		returnStatementEClass = createEClass(RETURN_STATEMENT);
		createEReference(returnStatementEClass, RETURN_STATEMENT__EXPRESSION);

		singleVariableAccessEClass = createEClass(SINGLE_VARIABLE_ACCESS);
		createEReference(singleVariableAccessEClass, SINGLE_VARIABLE_ACCESS__VARIABLE);
		createEReference(singleVariableAccessEClass, SINGLE_VARIABLE_ACCESS__QUALIFIER);

		singleVariableDeclarationEClass = createEClass(SINGLE_VARIABLE_DECLARATION);
		createEReference(singleVariableDeclarationEClass, SINGLE_VARIABLE_DECLARATION__MODIFIER);
		createEAttribute(singleVariableDeclarationEClass, SINGLE_VARIABLE_DECLARATION__VARARGS);
		createEReference(singleVariableDeclarationEClass, SINGLE_VARIABLE_DECLARATION__TYPE);
		createEReference(singleVariableDeclarationEClass, SINGLE_VARIABLE_DECLARATION__ANNOTATIONS);
		createEReference(singleVariableDeclarationEClass, SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION);
		createEReference(singleVariableDeclarationEClass, SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE);
		createEReference(singleVariableDeclarationEClass, SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT);

		statementEClass = createEClass(STATEMENT);

		stringLiteralEClass = createEClass(STRING_LITERAL);
		createEAttribute(stringLiteralEClass, STRING_LITERAL__ESCAPED_VALUE);

		superConstructorInvocationEClass = createEClass(SUPER_CONSTRUCTOR_INVOCATION);
		createEReference(superConstructorInvocationEClass, SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION);

		superFieldAccessEClass = createEClass(SUPER_FIELD_ACCESS);
		createEReference(superFieldAccessEClass, SUPER_FIELD_ACCESS__FIELD);

		superMethodInvocationEClass = createEClass(SUPER_METHOD_INVOCATION);

		switchCaseEClass = createEClass(SWITCH_CASE);
		createEAttribute(switchCaseEClass, SWITCH_CASE__DEFAULT);
		createEReference(switchCaseEClass, SWITCH_CASE__EXPRESSION);

		switchStatementEClass = createEClass(SWITCH_STATEMENT);
		createEReference(switchStatementEClass, SWITCH_STATEMENT__EXPRESSION);
		createEReference(switchStatementEClass, SWITCH_STATEMENT__STATEMENTS);

		synchronizedStatementEClass = createEClass(SYNCHRONIZED_STATEMENT);
		createEReference(synchronizedStatementEClass, SYNCHRONIZED_STATEMENT__BODY);
		createEReference(synchronizedStatementEClass, SYNCHRONIZED_STATEMENT__EXPRESSION);

		tagElementEClass = createEClass(TAG_ELEMENT);
		createEAttribute(tagElementEClass, TAG_ELEMENT__TAG_NAME);
		createEReference(tagElementEClass, TAG_ELEMENT__FRAGMENTS);

		textElementEClass = createEClass(TEXT_ELEMENT);
		createEAttribute(textElementEClass, TEXT_ELEMENT__TEXT);

		thisExpressionEClass = createEClass(THIS_EXPRESSION);

		throwStatementEClass = createEClass(THROW_STATEMENT);
		createEReference(throwStatementEClass, THROW_STATEMENT__EXPRESSION);

		tryStatementEClass = createEClass(TRY_STATEMENT);
		createEReference(tryStatementEClass, TRY_STATEMENT__BODY);
		createEReference(tryStatementEClass, TRY_STATEMENT__FINALLY);
		createEReference(tryStatementEClass, TRY_STATEMENT__CATCH_CLAUSES);

		typeEClass = createEClass(TYPE);
		createEReference(typeEClass, TYPE__USAGES_IN_TYPE_ACCESS);

		typeAccessEClass = createEClass(TYPE_ACCESS);
		createEReference(typeAccessEClass, TYPE_ACCESS__TYPE);
		createEReference(typeAccessEClass, TYPE_ACCESS__QUALIFIER);

		typeDeclarationEClass = createEClass(TYPE_DECLARATION);
		createEReference(typeDeclarationEClass, TYPE_DECLARATION__TYPE_PARAMETERS);

		typeDeclarationStatementEClass = createEClass(TYPE_DECLARATION_STATEMENT);
		createEReference(typeDeclarationStatementEClass, TYPE_DECLARATION_STATEMENT__DECLARATION);

		typeLiteralEClass = createEClass(TYPE_LITERAL);
		createEReference(typeLiteralEClass, TYPE_LITERAL__TYPE);

		typeParameterEClass = createEClass(TYPE_PARAMETER);
		createEReference(typeParameterEClass, TYPE_PARAMETER__BOUNDS);

		unresolvedItemEClass = createEClass(UNRESOLVED_ITEM);

		unresolvedItemAccessEClass = createEClass(UNRESOLVED_ITEM_ACCESS);
		createEReference(unresolvedItemAccessEClass, UNRESOLVED_ITEM_ACCESS__ELEMENT);
		createEReference(unresolvedItemAccessEClass, UNRESOLVED_ITEM_ACCESS__QUALIFIER);

		unresolvedAnnotationDeclarationEClass = createEClass(UNRESOLVED_ANNOTATION_DECLARATION);

		unresolvedAnnotationTypeMemberDeclarationEClass = createEClass(UNRESOLVED_ANNOTATION_TYPE_MEMBER_DECLARATION);

		unresolvedClassDeclarationEClass = createEClass(UNRESOLVED_CLASS_DECLARATION);

		unresolvedEnumDeclarationEClass = createEClass(UNRESOLVED_ENUM_DECLARATION);

		unresolvedInterfaceDeclarationEClass = createEClass(UNRESOLVED_INTERFACE_DECLARATION);

		unresolvedLabeledStatementEClass = createEClass(UNRESOLVED_LABELED_STATEMENT);

		unresolvedMethodDeclarationEClass = createEClass(UNRESOLVED_METHOD_DECLARATION);

		unresolvedSingleVariableDeclarationEClass = createEClass(UNRESOLVED_SINGLE_VARIABLE_DECLARATION);

		unresolvedTypeEClass = createEClass(UNRESOLVED_TYPE);

		unresolvedTypeDeclarationEClass = createEClass(UNRESOLVED_TYPE_DECLARATION);

		unresolvedVariableDeclarationFragmentEClass = createEClass(UNRESOLVED_VARIABLE_DECLARATION_FRAGMENT);

		variableDeclarationEClass = createEClass(VARIABLE_DECLARATION);
		createEAttribute(variableDeclarationEClass, VARIABLE_DECLARATION__EXTRA_ARRAY_DIMENSIONS);
		createEReference(variableDeclarationEClass, VARIABLE_DECLARATION__INITIALIZER);
		createEReference(variableDeclarationEClass, VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS);

		variableDeclarationExpressionEClass = createEClass(VARIABLE_DECLARATION_EXPRESSION);
		createEReference(variableDeclarationExpressionEClass, VARIABLE_DECLARATION_EXPRESSION__MODIFIER);
		createEReference(variableDeclarationExpressionEClass, VARIABLE_DECLARATION_EXPRESSION__ANNOTATIONS);

		variableDeclarationFragmentEClass = createEClass(VARIABLE_DECLARATION_FRAGMENT);
		createEReference(variableDeclarationFragmentEClass, VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER);

		variableDeclarationStatementEClass = createEClass(VARIABLE_DECLARATION_STATEMENT);
		createEAttribute(variableDeclarationStatementEClass, VARIABLE_DECLARATION_STATEMENT__EXTRA_ARRAY_DIMENSIONS);
		createEReference(variableDeclarationStatementEClass, VARIABLE_DECLARATION_STATEMENT__MODIFIER);
		createEReference(variableDeclarationStatementEClass, VARIABLE_DECLARATION_STATEMENT__ANNOTATIONS);

		wildCardTypeEClass = createEClass(WILD_CARD_TYPE);
		createEAttribute(wildCardTypeEClass, WILD_CARD_TYPE__UPPER_BOUND);
		createEReference(wildCardTypeEClass, WILD_CARD_TYPE__BOUND);

		whileStatementEClass = createEClass(WHILE_STATEMENT);
		createEReference(whileStatementEClass, WHILE_STATEMENT__EXPRESSION);
		createEReference(whileStatementEClass, WHILE_STATEMENT__BODY);

		// Create enums
		assignmentKindEEnum = createEEnum(ASSIGNMENT_KIND);
		infixExpressionKindEEnum = createEEnum(INFIX_EXPRESSION_KIND);
		inheritanceKindEEnum = createEEnum(INHERITANCE_KIND);
		postfixExpressionKindEEnum = createEEnum(POSTFIX_EXPRESSION_KIND);
		prefixExpressionKindEEnum = createEEnum(PREFIX_EXPRESSION_KIND);
		visibilityKindEEnum = createEEnum(VISIBILITY_KIND);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		abstractMethodDeclarationEClass.getESuperTypes().add(this.getBodyDeclaration());
		abstractMethodInvocationEClass.getESuperTypes().add(this.getASTNode());
		abstractTypeDeclarationEClass.getESuperTypes().add(this.getBodyDeclaration());
		abstractTypeDeclarationEClass.getESuperTypes().add(this.getType());
		abstractTypeQualifiedExpressionEClass.getESuperTypes().add(this.getExpression());
		abstractVariablesContainerEClass.getESuperTypes().add(this.getASTNode());
		annotationEClass.getESuperTypes().add(this.getExpression());
		archiveEClass.getESuperTypes().add(this.getNamedElement());
		assertStatementEClass.getESuperTypes().add(this.getStatement());
		annotationMemberValuePairEClass.getESuperTypes().add(this.getNamedElement());
		annotationTypeDeclarationEClass.getESuperTypes().add(this.getAbstractTypeDeclaration());
		annotationTypeMemberDeclarationEClass.getESuperTypes().add(this.getBodyDeclaration());
		anonymousClassDeclarationEClass.getESuperTypes().add(this.getASTNode());
		arrayAccessEClass.getESuperTypes().add(this.getExpression());
		arrayCreationEClass.getESuperTypes().add(this.getExpression());
		arrayInitializerEClass.getESuperTypes().add(this.getExpression());
		arrayLengthAccessEClass.getESuperTypes().add(this.getExpression());
		arrayTypeEClass.getESuperTypes().add(this.getType());
		assignmentEClass.getESuperTypes().add(this.getExpression());
		bodyDeclarationEClass.getESuperTypes().add(this.getNamedElement());
		booleanLiteralEClass.getESuperTypes().add(this.getExpression());
		blockCommentEClass.getESuperTypes().add(this.getComment());
		blockEClass.getESuperTypes().add(this.getStatement());
		breakStatementEClass.getESuperTypes().add(this.getStatement());
		castExpressionEClass.getESuperTypes().add(this.getExpression());
		catchClauseEClass.getESuperTypes().add(this.getStatement());
		characterLiteralEClass.getESuperTypes().add(this.getExpression());
		classFileEClass.getESuperTypes().add(this.getNamedElement());
		classInstanceCreationEClass.getESuperTypes().add(this.getExpression());
		classInstanceCreationEClass.getESuperTypes().add(this.getAbstractMethodInvocation());
		constructorDeclarationEClass.getESuperTypes().add(this.getAbstractMethodDeclaration());
		conditionalExpressionEClass.getESuperTypes().add(this.getExpression());
		constructorInvocationEClass.getESuperTypes().add(this.getStatement());
		constructorInvocationEClass.getESuperTypes().add(this.getAbstractMethodInvocation());
		classDeclarationEClass.getESuperTypes().add(this.getTypeDeclaration());
		commentEClass.getESuperTypes().add(this.getASTNode());
		compilationUnitEClass.getESuperTypes().add(this.getNamedElement());
		continueStatementEClass.getESuperTypes().add(this.getStatement());
		doStatementEClass.getESuperTypes().add(this.getStatement());
		emptyStatementEClass.getESuperTypes().add(this.getStatement());
		enhancedForStatementEClass.getESuperTypes().add(this.getStatement());
		enumConstantDeclarationEClass.getESuperTypes().add(this.getBodyDeclaration());
		enumConstantDeclarationEClass.getESuperTypes().add(this.getVariableDeclaration());
		enumDeclarationEClass.getESuperTypes().add(this.getAbstractTypeDeclaration());
		expressionEClass.getESuperTypes().add(this.getASTNode());
		expressionStatementEClass.getESuperTypes().add(this.getStatement());
		fieldAccessEClass.getESuperTypes().add(this.getExpression());
		fieldDeclarationEClass.getESuperTypes().add(this.getBodyDeclaration());
		fieldDeclarationEClass.getESuperTypes().add(this.getAbstractVariablesContainer());
		forStatementEClass.getESuperTypes().add(this.getStatement());
		ifStatementEClass.getESuperTypes().add(this.getStatement());
		importDeclarationEClass.getESuperTypes().add(this.getASTNode());
		infixExpressionEClass.getESuperTypes().add(this.getExpression());
		initializerEClass.getESuperTypes().add(this.getBodyDeclaration());
		instanceofExpressionEClass.getESuperTypes().add(this.getExpression());
		interfaceDeclarationEClass.getESuperTypes().add(this.getTypeDeclaration());
		javadocEClass.getESuperTypes().add(this.getComment());
		labeledStatementEClass.getESuperTypes().add(this.getNamedElement());
		labeledStatementEClass.getESuperTypes().add(this.getStatement());
		lineCommentEClass.getESuperTypes().add(this.getComment());
		memberRefEClass.getESuperTypes().add(this.getASTNode());
		methodDeclarationEClass.getESuperTypes().add(this.getAbstractMethodDeclaration());
		methodInvocationEClass.getESuperTypes().add(this.getExpression());
		methodInvocationEClass.getESuperTypes().add(this.getAbstractMethodInvocation());
		methodRefEClass.getESuperTypes().add(this.getASTNode());
		methodRefParameterEClass.getESuperTypes().add(this.getASTNode());
		modifierEClass.getESuperTypes().add(this.getASTNode());
		namedElementEClass.getESuperTypes().add(this.getASTNode());
		namespaceAccessEClass.getESuperTypes().add(this.getASTNode());
		numberLiteralEClass.getESuperTypes().add(this.getExpression());
		nullLiteralEClass.getESuperTypes().add(this.getExpression());
		packageEClass.getESuperTypes().add(this.getNamedElement());
		packageAccessEClass.getESuperTypes().add(this.getNamespaceAccess());
		parameterizedTypeEClass.getESuperTypes().add(this.getType());
		parenthesizedExpressionEClass.getESuperTypes().add(this.getExpression());
		postfixExpressionEClass.getESuperTypes().add(this.getExpression());
		prefixExpressionEClass.getESuperTypes().add(this.getExpression());
		primitiveTypeEClass.getESuperTypes().add(this.getType());
		primitiveTypeBooleanEClass.getESuperTypes().add(this.getPrimitiveType());
		primitiveTypeByteEClass.getESuperTypes().add(this.getPrimitiveType());
		primitiveTypeCharEClass.getESuperTypes().add(this.getPrimitiveType());
		primitiveTypeDoubleEClass.getESuperTypes().add(this.getPrimitiveType());
		primitiveTypeShortEClass.getESuperTypes().add(this.getPrimitiveType());
		primitiveTypeFloatEClass.getESuperTypes().add(this.getPrimitiveType());
		primitiveTypeIntEClass.getESuperTypes().add(this.getPrimitiveType());
		primitiveTypeLongEClass.getESuperTypes().add(this.getPrimitiveType());
		primitiveTypeVoidEClass.getESuperTypes().add(this.getPrimitiveType());
		returnStatementEClass.getESuperTypes().add(this.getStatement());
		singleVariableAccessEClass.getESuperTypes().add(this.getExpression());
		singleVariableDeclarationEClass.getESuperTypes().add(this.getVariableDeclaration());
		statementEClass.getESuperTypes().add(this.getASTNode());
		stringLiteralEClass.getESuperTypes().add(this.getExpression());
		superConstructorInvocationEClass.getESuperTypes().add(this.getStatement());
		superConstructorInvocationEClass.getESuperTypes().add(this.getAbstractMethodInvocation());
		superFieldAccessEClass.getESuperTypes().add(this.getAbstractTypeQualifiedExpression());
		superMethodInvocationEClass.getESuperTypes().add(this.getAbstractTypeQualifiedExpression());
		superMethodInvocationEClass.getESuperTypes().add(this.getAbstractMethodInvocation());
		switchCaseEClass.getESuperTypes().add(this.getStatement());
		switchStatementEClass.getESuperTypes().add(this.getStatement());
		synchronizedStatementEClass.getESuperTypes().add(this.getStatement());
		tagElementEClass.getESuperTypes().add(this.getASTNode());
		textElementEClass.getESuperTypes().add(this.getASTNode());
		thisExpressionEClass.getESuperTypes().add(this.getAbstractTypeQualifiedExpression());
		throwStatementEClass.getESuperTypes().add(this.getStatement());
		tryStatementEClass.getESuperTypes().add(this.getStatement());
		typeEClass.getESuperTypes().add(this.getNamedElement());
		typeAccessEClass.getESuperTypes().add(this.getExpression());
		typeAccessEClass.getESuperTypes().add(this.getNamespaceAccess());
		typeDeclarationEClass.getESuperTypes().add(this.getAbstractTypeDeclaration());
		typeDeclarationStatementEClass.getESuperTypes().add(this.getStatement());
		typeLiteralEClass.getESuperTypes().add(this.getExpression());
		typeParameterEClass.getESuperTypes().add(this.getType());
		unresolvedItemEClass.getESuperTypes().add(this.getNamedElement());
		unresolvedItemAccessEClass.getESuperTypes().add(this.getExpression());
		unresolvedItemAccessEClass.getESuperTypes().add(this.getNamespaceAccess());
		unresolvedAnnotationDeclarationEClass.getESuperTypes().add(this.getAnnotationTypeDeclaration());
		unresolvedAnnotationDeclarationEClass.getESuperTypes().add(this.getUnresolvedItem());
		unresolvedAnnotationTypeMemberDeclarationEClass.getESuperTypes().add(this.getAnnotationTypeMemberDeclaration());
		unresolvedAnnotationTypeMemberDeclarationEClass.getESuperTypes().add(this.getUnresolvedItem());
		unresolvedClassDeclarationEClass.getESuperTypes().add(this.getClassDeclaration());
		unresolvedClassDeclarationEClass.getESuperTypes().add(this.getUnresolvedItem());
		unresolvedEnumDeclarationEClass.getESuperTypes().add(this.getEnumDeclaration());
		unresolvedEnumDeclarationEClass.getESuperTypes().add(this.getUnresolvedItem());
		unresolvedInterfaceDeclarationEClass.getESuperTypes().add(this.getInterfaceDeclaration());
		unresolvedInterfaceDeclarationEClass.getESuperTypes().add(this.getUnresolvedItem());
		unresolvedLabeledStatementEClass.getESuperTypes().add(this.getLabeledStatement());
		unresolvedLabeledStatementEClass.getESuperTypes().add(this.getUnresolvedItem());
		unresolvedMethodDeclarationEClass.getESuperTypes().add(this.getMethodDeclaration());
		unresolvedMethodDeclarationEClass.getESuperTypes().add(this.getUnresolvedItem());
		unresolvedSingleVariableDeclarationEClass.getESuperTypes().add(this.getSingleVariableDeclaration());
		unresolvedSingleVariableDeclarationEClass.getESuperTypes().add(this.getUnresolvedItem());
		unresolvedTypeEClass.getESuperTypes().add(this.getType());
		unresolvedTypeEClass.getESuperTypes().add(this.getUnresolvedItem());
		unresolvedTypeDeclarationEClass.getESuperTypes().add(this.getAbstractTypeDeclaration());
		unresolvedTypeDeclarationEClass.getESuperTypes().add(this.getUnresolvedItem());
		unresolvedVariableDeclarationFragmentEClass.getESuperTypes().add(this.getVariableDeclarationFragment());
		unresolvedVariableDeclarationFragmentEClass.getESuperTypes().add(this.getUnresolvedItem());
		variableDeclarationEClass.getESuperTypes().add(this.getNamedElement());
		variableDeclarationExpressionEClass.getESuperTypes().add(this.getExpression());
		variableDeclarationExpressionEClass.getESuperTypes().add(this.getAbstractVariablesContainer());
		variableDeclarationFragmentEClass.getESuperTypes().add(this.getVariableDeclaration());
		variableDeclarationStatementEClass.getESuperTypes().add(this.getStatement());
		variableDeclarationStatementEClass.getESuperTypes().add(this.getAbstractVariablesContainer());
		wildCardTypeEClass.getESuperTypes().add(this.getType());
		whileStatementEClass.getESuperTypes().add(this.getStatement());

		// Initialize classes, features, and operations; add parameters
		initEClass(abstractMethodDeclarationEClass, AbstractMethodDeclaration.class, "AbstractMethodDeclaration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractMethodDeclaration_Body(), this.getBlock(), null, "body", null, 0, 1, AbstractMethodDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAbstractMethodDeclaration_Parameters(), this.getSingleVariableDeclaration(), this.getSingleVariableDeclaration_MethodDeclaration(), "parameters", null, 0, -1, AbstractMethodDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractMethodDeclaration_ThrownExceptions(), this.getTypeAccess(), null, "thrownExceptions", null, 0, -1, AbstractMethodDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractMethodDeclaration_TypeParameters(), this.getTypeParameter(), null, "typeParameters", null, 0, -1, AbstractMethodDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractMethodDeclaration_UsagesInDocComments(), this.getMethodRef(), this.getMethodRef_Method(), "usagesInDocComments", null, 0, -1, AbstractMethodDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAbstractMethodDeclaration_Usages(), this.getAbstractMethodInvocation(), this.getAbstractMethodInvocation_Method(), "usages", null, 0, -1, AbstractMethodDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(abstractMethodInvocationEClass, AbstractMethodInvocation.class, "AbstractMethodInvocation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractMethodInvocation_Method(), this.getAbstractMethodDeclaration(), this.getAbstractMethodDeclaration_Usages(), "method", null, 1, 1, AbstractMethodInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAbstractMethodInvocation_Arguments(), this.getExpression(), null, "arguments", null, 0, -1, AbstractMethodInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractMethodInvocation_TypeArguments(), this.getTypeAccess(), null, "typeArguments", null, 0, -1, AbstractMethodInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractTypeDeclarationEClass, AbstractTypeDeclaration.class, "AbstractTypeDeclaration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractTypeDeclaration_BodyDeclarations(), this.getBodyDeclaration(), this.getBodyDeclaration_AbstractTypeDeclaration(), "bodyDeclarations", null, 0, -1, AbstractTypeDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractTypeDeclaration_CommentsBeforeBody(), this.getComment(), null, "commentsBeforeBody", null, 0, -1, AbstractTypeDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractTypeDeclaration_CommentsAfterBody(), this.getComment(), null, "commentsAfterBody", null, 0, -1, AbstractTypeDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAbstractTypeDeclaration_Package(), this.getPackage(), this.getPackage_OwnedElements(), "package", null, 0, 1, AbstractTypeDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAbstractTypeDeclaration_SuperInterfaces(), this.getTypeAccess(), null, "superInterfaces", null, 0, -1, AbstractTypeDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(abstractTypeQualifiedExpressionEClass, AbstractTypeQualifiedExpression.class, "AbstractTypeQualifiedExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractTypeQualifiedExpression_Qualifier(), this.getTypeAccess(), null, "qualifier", null, 0, 1, AbstractTypeQualifiedExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(abstractVariablesContainerEClass, AbstractVariablesContainer.class, "AbstractVariablesContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractVariablesContainer_Type(), this.getTypeAccess(), null, "type", null, 0, 1, AbstractVariablesContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAbstractVariablesContainer_Fragments(), this.getVariableDeclarationFragment(), this.getVariableDeclarationFragment_VariablesContainer(), "fragments", null, 0, -1, AbstractVariablesContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(annotationEClass, Annotation.class, "Annotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnnotation_Type(), this.getTypeAccess(), null, "type", null, 1, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAnnotation_Values(), this.getAnnotationMemberValuePair(), null, "values", null, 0, -1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(archiveEClass, Archive.class, "Archive", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArchive_OriginalFilePath(), theEcorePackage.getEString(), "originalFilePath", null, 1, 1, Archive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getArchive_ClassFiles(), this.getClassFile(), null, "classFiles", null, 0, -1, Archive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getArchive_Manifest(), this.getManifest(), null, "manifest", null, 0, 1, Archive.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(assertStatementEClass, AssertStatement.class, "AssertStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssertStatement_Message(), this.getExpression(), null, "message", null, 0, 1, AssertStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAssertStatement_Expression(), this.getExpression(), null, "expression", null, 1, 1, AssertStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(astNodeEClass, ASTNode.class, "ASTNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getASTNode_Comments(), this.getComment(), null, "comments", null, 0, -1, ASTNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASTNode_OriginalCompilationUnit(), this.getCompilationUnit(), null, "originalCompilationUnit", null, 0, 1, ASTNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getASTNode_OriginalClassFile(), this.getClassFile(), null, "originalClassFile", null, 0, 1, ASTNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(annotationMemberValuePairEClass, AnnotationMemberValuePair.class, "AnnotationMemberValuePair", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnnotationMemberValuePair_Member(), this.getAnnotationTypeMemberDeclaration(), this.getAnnotationTypeMemberDeclaration_Usages(), "member", null, 0, 1, AnnotationMemberValuePair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAnnotationMemberValuePair_Value(), this.getExpression(), null, "value", null, 1, 1, AnnotationMemberValuePair.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(annotationTypeDeclarationEClass, AnnotationTypeDeclaration.class, "AnnotationTypeDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(annotationTypeMemberDeclarationEClass, AnnotationTypeMemberDeclaration.class, "AnnotationTypeMemberDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnnotationTypeMemberDeclaration_Default(), this.getExpression(), null, "default", null, 0, 1, AnnotationTypeMemberDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAnnotationTypeMemberDeclaration_Type(), this.getTypeAccess(), null, "type", null, 1, 1, AnnotationTypeMemberDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAnnotationTypeMemberDeclaration_Usages(), this.getAnnotationMemberValuePair(), this.getAnnotationMemberValuePair_Member(), "usages", null, 0, -1, AnnotationTypeMemberDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(anonymousClassDeclarationEClass, AnonymousClassDeclaration.class, "AnonymousClassDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAnonymousClassDeclaration_BodyDeclarations(), this.getBodyDeclaration(), this.getBodyDeclaration_AnonymousClassDeclarationOwner(), "bodyDeclarations", null, 0, -1, AnonymousClassDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAnonymousClassDeclaration_ClassInstanceCreation(), this.getClassInstanceCreation(), this.getClassInstanceCreation_AnonymousClassDeclaration(), "classInstanceCreation", null, 0, 1, AnonymousClassDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(arrayAccessEClass, ArrayAccess.class, "ArrayAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArrayAccess_Array(), this.getExpression(), null, "array", null, 1, 1, ArrayAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getArrayAccess_Index(), this.getExpression(), null, "index", null, 1, 1, ArrayAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(arrayCreationEClass, ArrayCreation.class, "ArrayCreation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArrayCreation_Dimensions(), this.getExpression(), null, "dimensions", null, 0, -1, ArrayCreation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArrayCreation_Initializer(), this.getArrayInitializer(), null, "initializer", null, 0, 1, ArrayCreation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getArrayCreation_Type(), this.getTypeAccess(), null, "type", null, 1, 1, ArrayCreation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(arrayInitializerEClass, ArrayInitializer.class, "ArrayInitializer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArrayInitializer_Expressions(), this.getExpression(), null, "expressions", null, 0, -1, ArrayInitializer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arrayLengthAccessEClass, ArrayLengthAccess.class, "ArrayLengthAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArrayLengthAccess_Array(), this.getExpression(), null, "array", null, 1, 1, ArrayLengthAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(arrayTypeEClass, ArrayType.class, "ArrayType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArrayType_Dimensions(), theEcorePackage.getEInt(), "dimensions", null, 1, 1, ArrayType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getArrayType_ElementType(), this.getTypeAccess(), null, "elementType", null, 1, 1, ArrayType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(assignmentEClass, Assignment.class, "Assignment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssignment_LeftHandSide(), this.getExpression(), null, "leftHandSide", null, 1, 1, Assignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getAssignment_Operator(), this.getAssignmentKind(), "operator", null, 1, 1, Assignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAssignment_RightHandSide(), this.getExpression(), null, "rightHandSide", null, 1, 1, Assignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(bodyDeclarationEClass, BodyDeclaration.class, "BodyDeclaration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBodyDeclaration_AbstractTypeDeclaration(), this.getAbstractTypeDeclaration(), this.getAbstractTypeDeclaration_BodyDeclarations(), "abstractTypeDeclaration", null, 0, 1, BodyDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getBodyDeclaration_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, BodyDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBodyDeclaration_AnonymousClassDeclarationOwner(), this.getAnonymousClassDeclaration(), this.getAnonymousClassDeclaration_BodyDeclarations(), "anonymousClassDeclarationOwner", null, 0, 1, BodyDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getBodyDeclaration_Modifier(), this.getModifier(), this.getModifier_BodyDeclaration(), "modifier", null, 0, 1, BodyDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(booleanLiteralEClass, BooleanLiteral.class, "BooleanLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanLiteral_Value(), theEcorePackage.getEBoolean(), "value", null, 1, 1, BooleanLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(blockCommentEClass, BlockComment.class, "BlockComment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(blockEClass, Block.class, "Block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBlock_Statements(), this.getStatement(), null, "statements", null, 0, -1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(breakStatementEClass, BreakStatement.class, "BreakStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBreakStatement_Label(), this.getLabeledStatement(), this.getLabeledStatement_UsagesInBreakStatements(), "label", null, 0, 1, BreakStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(castExpressionEClass, CastExpression.class, "CastExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCastExpression_Expression(), this.getExpression(), null, "expression", null, 1, 1, CastExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCastExpression_Type(), this.getTypeAccess(), null, "type", null, 1, 1, CastExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(catchClauseEClass, CatchClause.class, "CatchClause", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCatchClause_Exception(), this.getSingleVariableDeclaration(), this.getSingleVariableDeclaration_CatchClause(), "exception", null, 1, 1, CatchClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCatchClause_Body(), this.getBlock(), null, "body", null, 1, 1, CatchClause.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(characterLiteralEClass, CharacterLiteral.class, "CharacterLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCharacterLiteral_EscapedValue(), theEcorePackage.getEString(), "escapedValue", null, 1, 1, CharacterLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(classFileEClass, ClassFile.class, "ClassFile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getClassFile_OriginalFilePath(), theEcorePackage.getEString(), "originalFilePath", null, 1, 1, ClassFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getClassFile_Type(), this.getAbstractTypeDeclaration(), null, "type", null, 0, 1, ClassFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getClassFile_AttachedSource(), this.getCompilationUnit(), null, "attachedSource", null, 0, 1, ClassFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getClassFile_Package(), this.getPackage(), null, "package", null, 0, 1, ClassFile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(classInstanceCreationEClass, ClassInstanceCreation.class, "ClassInstanceCreation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassInstanceCreation_AnonymousClassDeclaration(), this.getAnonymousClassDeclaration(), this.getAnonymousClassDeclaration_ClassInstanceCreation(), "anonymousClassDeclaration", null, 0, 1, ClassInstanceCreation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getClassInstanceCreation_Expression(), this.getExpression(), null, "expression", null, 0, 1, ClassInstanceCreation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getClassInstanceCreation_Type(), this.getTypeAccess(), null, "type", null, 1, 1, ClassInstanceCreation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(constructorDeclarationEClass, ConstructorDeclaration.class, "ConstructorDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(conditionalExpressionEClass, ConditionalExpression.class, "ConditionalExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConditionalExpression_ElseExpression(), this.getExpression(), null, "elseExpression", null, 1, 1, ConditionalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getConditionalExpression_Expression(), this.getExpression(), null, "expression", null, 1, 1, ConditionalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getConditionalExpression_ThenExpression(), this.getExpression(), null, "thenExpression", null, 1, 1, ConditionalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(constructorInvocationEClass, ConstructorInvocation.class, "ConstructorInvocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(classDeclarationEClass, ClassDeclaration.class, "ClassDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassDeclaration_SuperClass(), this.getTypeAccess(), null, "superClass", null, 0, 1, ClassDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(commentEClass, Comment.class, "Comment", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComment_Content(), theEcorePackage.getEString(), "content", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getComment_EnclosedByParent(), theEcorePackage.getEBoolean(), "enclosedByParent", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getComment_PrefixOfParent(), theEcorePackage.getEBoolean(), "prefixOfParent", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(compilationUnitEClass, CompilationUnit.class, "CompilationUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCompilationUnit_OriginalFilePath(), theEcorePackage.getEString(), "originalFilePath", null, 1, 1, CompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCompilationUnit_CommentList(), this.getComment(), null, "commentList", null, 0, -1, CompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompilationUnit_Imports(), this.getImportDeclaration(), null, "imports", null, 0, -1, CompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompilationUnit_Package(), this.getPackage(), null, "package", null, 0, 1, CompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCompilationUnit_Types(), this.getAbstractTypeDeclaration(), null, "types", null, 0, -1, CompilationUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(continueStatementEClass, ContinueStatement.class, "ContinueStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContinueStatement_Label(), this.getLabeledStatement(), this.getLabeledStatement_UsagesInContinueStatements(), "label", null, 0, 1, ContinueStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(doStatementEClass, DoStatement.class, "DoStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDoStatement_Expression(), this.getExpression(), null, "expression", null, 1, 1, DoStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getDoStatement_Body(), this.getStatement(), null, "body", null, 1, 1, DoStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(emptyStatementEClass, EmptyStatement.class, "EmptyStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(enhancedForStatementEClass, EnhancedForStatement.class, "EnhancedForStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnhancedForStatement_Body(), this.getStatement(), null, "body", null, 1, 1, EnhancedForStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getEnhancedForStatement_Expression(), this.getExpression(), null, "expression", null, 1, 1, EnhancedForStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getEnhancedForStatement_Parameter(), this.getSingleVariableDeclaration(), this.getSingleVariableDeclaration_EnhancedForStatement(), "parameter", null, 1, 1, EnhancedForStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(enumConstantDeclarationEClass, EnumConstantDeclaration.class, "EnumConstantDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnumConstantDeclaration_AnonymousClassDeclaration(), this.getAnonymousClassDeclaration(), null, "anonymousClassDeclaration", null, 0, 1, EnumConstantDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getEnumConstantDeclaration_Arguments(), this.getExpression(), null, "arguments", null, 0, -1, EnumConstantDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(enumDeclarationEClass, EnumDeclaration.class, "EnumDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEnumDeclaration_EnumConstants(), this.getEnumConstantDeclaration(), null, "enumConstants", null, 0, -1, EnumDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(expressionEClass, Expression.class, "Expression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(expressionStatementEClass, ExpressionStatement.class, "ExpressionStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExpressionStatement_Expression(), this.getExpression(), null, "expression", null, 1, 1, ExpressionStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(fieldAccessEClass, FieldAccess.class, "FieldAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFieldAccess_Field(), this.getSingleVariableAccess(), null, "field", null, 1, 1, FieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getFieldAccess_Expression(), this.getExpression(), null, "expression", null, 1, 1, FieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(fieldDeclarationEClass, FieldDeclaration.class, "FieldDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(forStatementEClass, ForStatement.class, "ForStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getForStatement_Expression(), this.getExpression(), null, "expression", null, 0, 1, ForStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getForStatement_Updaters(), this.getExpression(), null, "updaters", null, 0, -1, ForStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getForStatement_Initializers(), this.getExpression(), null, "initializers", null, 0, -1, ForStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getForStatement_Body(), this.getStatement(), null, "body", null, 1, 1, ForStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(ifStatementEClass, IfStatement.class, "IfStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIfStatement_Expression(), this.getExpression(), null, "expression", null, 1, 1, IfStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIfStatement_ThenStatement(), this.getStatement(), null, "thenStatement", null, 1, 1, IfStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getIfStatement_ElseStatement(), this.getStatement(), null, "elseStatement", null, 0, 1, IfStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(importDeclarationEClass, ImportDeclaration.class, "ImportDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImportDeclaration_Static(), theEcorePackage.getEBoolean(), "static", null, 0, 1, ImportDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getImportDeclaration_ImportedElement(), this.getNamedElement(), this.getNamedElement_UsagesInImports(), "importedElement", null, 1, 1, ImportDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(infixExpressionEClass, InfixExpression.class, "InfixExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInfixExpression_Operator(), this.getInfixExpressionKind(), "operator", null, 1, 1, InfixExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInfixExpression_RightOperand(), this.getExpression(), null, "rightOperand", null, 1, 1, InfixExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInfixExpression_LeftOperand(), this.getExpression(), null, "leftOperand", null, 1, 1, InfixExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInfixExpression_ExtendedOperands(), this.getExpression(), null, "extendedOperands", null, 0, -1, InfixExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(initializerEClass, Initializer.class, "Initializer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInitializer_Body(), this.getBlock(), null, "body", null, 1, 1, Initializer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(instanceofExpressionEClass, InstanceofExpression.class, "InstanceofExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstanceofExpression_RightOperand(), this.getTypeAccess(), null, "rightOperand", null, 1, 1, InstanceofExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInstanceofExpression_LeftOperand(), this.getExpression(), null, "leftOperand", null, 1, 1, InstanceofExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(interfaceDeclarationEClass, InterfaceDeclaration.class, "InterfaceDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(javadocEClass, Javadoc.class, "Javadoc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getJavadoc_Tags(), this.getTagElement(), null, "tags", null, 0, -1, Javadoc.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labeledStatementEClass, LabeledStatement.class, "LabeledStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLabeledStatement_Body(), this.getStatement(), null, "body", null, 1, 1, LabeledStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getLabeledStatement_UsagesInBreakStatements(), this.getBreakStatement(), this.getBreakStatement_Label(), "usagesInBreakStatements", null, 0, -1, LabeledStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getLabeledStatement_UsagesInContinueStatements(), this.getContinueStatement(), this.getContinueStatement_Label(), "usagesInContinueStatements", null, 0, -1, LabeledStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(lineCommentEClass, LineComment.class, "LineComment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(manifestEClass, Manifest.class, "Manifest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getManifest_MainAttributes(), this.getManifestAttribute(), null, "mainAttributes", null, 0, -1, Manifest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getManifest_EntryAttributes(), this.getManifestEntry(), null, "entryAttributes", null, 0, -1, Manifest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(manifestAttributeEClass, ManifestAttribute.class, "ManifestAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManifestAttribute_Key(), theEcorePackage.getEString(), "key", null, 1, 1, ManifestAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getManifestAttribute_Value(), theEcorePackage.getEString(), "value", null, 1, 1, ManifestAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(manifestEntryEClass, ManifestEntry.class, "ManifestEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getManifestEntry_Name(), theEcorePackage.getEString(), "name", null, 0, 1, ManifestEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getManifestEntry_Attributes(), this.getManifestAttribute(), null, "attributes", null, 0, -1, ManifestEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(memberRefEClass, MemberRef.class, "MemberRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMemberRef_Member(), this.getNamedElement(), null, "member", null, 1, 1, MemberRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMemberRef_Qualifier(), this.getTypeAccess(), null, "qualifier", null, 0, 1, MemberRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(methodDeclarationEClass, MethodDeclaration.class, "MethodDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethodDeclaration_ExtraArrayDimensions(), theEcorePackage.getEInt(), "extraArrayDimensions", null, 1, 1, MethodDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMethodDeclaration_ReturnType(), this.getTypeAccess(), null, "returnType", null, 0, 1, MethodDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMethodDeclaration_RedefinedMethodDeclaration(), this.getMethodDeclaration(), this.getMethodDeclaration_Redefinitions(), "redefinedMethodDeclaration", null, 0, 1, MethodDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMethodDeclaration_Redefinitions(), this.getMethodDeclaration(), this.getMethodDeclaration_RedefinedMethodDeclaration(), "redefinitions", null, 0, -1, MethodDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(methodInvocationEClass, MethodInvocation.class, "MethodInvocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMethodInvocation_Expression(), this.getExpression(), null, "expression", null, 0, 1, MethodInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(methodRefEClass, MethodRef.class, "MethodRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMethodRef_Method(), this.getAbstractMethodDeclaration(), this.getAbstractMethodDeclaration_UsagesInDocComments(), "method", null, 1, 1, MethodRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMethodRef_Qualifier(), this.getTypeAccess(), null, "qualifier", null, 0, 1, MethodRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMethodRef_Parameters(), this.getMethodRefParameter(), null, "parameters", null, 0, -1, MethodRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(methodRefParameterEClass, MethodRefParameter.class, "MethodRefParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethodRefParameter_Name(), theEcorePackage.getEString(), "name", null, 0, 1, MethodRefParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getMethodRefParameter_Varargs(), theEcorePackage.getEBoolean(), "varargs", null, 1, 1, MethodRefParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMethodRefParameter_Type(), this.getTypeAccess(), null, "type", null, 1, 1, MethodRefParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModel_Name(), theEcorePackage.getEString(), "name", null, 0, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModel_OwnedElements(), this.getPackage(), this.getPackage_Model(), "ownedElements", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModel_OrphanTypes(), this.getType(), null, "orphanTypes", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModel_UnresolvedItems(), this.getUnresolvedItem(), null, "unresolvedItems", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModel_CompilationUnits(), this.getCompilationUnit(), null, "compilationUnits", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModel_ClassFiles(), this.getClassFile(), null, "classFiles", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModel_Archives(), this.getArchive(), null, "archives", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(modifierEClass, Modifier.class, "Modifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModifier_Visibility(), this.getVisibilityKind(), "visibility", null, 1, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getModifier_Inheritance(), this.getInheritanceKind(), "inheritance", null, 1, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getModifier_Static(), theEcorePackage.getEBoolean(), "static", null, 1, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getModifier_Transient(), theEcorePackage.getEBoolean(), "transient", null, 1, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getModifier_Volatile(), theEcorePackage.getEBoolean(), "volatile", null, 1, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getModifier_Native(), theEcorePackage.getEBoolean(), "native", null, 1, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getModifier_Strictfp(), theEcorePackage.getEBoolean(), "strictfp", null, 1, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getModifier_Synchronized(), theEcorePackage.getEBoolean(), "synchronized", null, 1, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModifier_BodyDeclaration(), this.getBodyDeclaration(), this.getBodyDeclaration_Modifier(), "bodyDeclaration", null, 0, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModifier_SingleVariableDeclaration(), this.getSingleVariableDeclaration(), this.getSingleVariableDeclaration_Modifier(), "singleVariableDeclaration", null, 0, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModifier_VariableDeclarationStatement(), this.getVariableDeclarationStatement(), this.getVariableDeclarationStatement_Modifier(), "variableDeclarationStatement", null, 0, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getModifier_VariableDeclarationExpression(), this.getVariableDeclarationExpression(), this.getVariableDeclarationExpression_Modifier(), "variableDeclarationExpression", null, 0, 1, Modifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), theEcorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getNamedElement_Proxy(), theEcorePackage.getEBoolean(), "proxy", null, 1, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getNamedElement_UsagesInImports(), this.getImportDeclaration(), this.getImportDeclaration_ImportedElement(), "usagesInImports", null, 0, -1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(namespaceAccessEClass, NamespaceAccess.class, "NamespaceAccess", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(numberLiteralEClass, NumberLiteral.class, "NumberLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNumberLiteral_TokenValue(), theEcorePackage.getEString(), "tokenValue", null, 1, 1, NumberLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(nullLiteralEClass, NullLiteral.class, "NullLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(packageEClass, org.eclipse.gmt.modisco.java.Package.class, "Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackage_OwnedElements(), this.getAbstractTypeDeclaration(), this.getAbstractTypeDeclaration_Package(), "ownedElements", null, 0, -1, org.eclipse.gmt.modisco.java.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPackage_Model(), this.getModel(), this.getModel_OwnedElements(), "model", null, 0, 1, org.eclipse.gmt.modisco.java.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPackage_OwnedPackages(), this.getPackage(), this.getPackage_Package(), "ownedPackages", null, 0, -1, org.eclipse.gmt.modisco.java.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPackage_Package(), this.getPackage(), this.getPackage_OwnedPackages(), "package", null, 0, 1, org.eclipse.gmt.modisco.java.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPackage_UsagesInPackageAccess(), this.getPackageAccess(), this.getPackageAccess_Package(), "usagesInPackageAccess", null, 0, -1, org.eclipse.gmt.modisco.java.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(packageAccessEClass, PackageAccess.class, "PackageAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackageAccess_Package(), this.getPackage(), this.getPackage_UsagesInPackageAccess(), "package", null, 1, 1, PackageAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPackageAccess_Qualifier(), this.getPackageAccess(), null, "qualifier", null, 0, 1, PackageAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(parameterizedTypeEClass, ParameterizedType.class, "ParameterizedType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterizedType_Type(), this.getTypeAccess(), null, "type", null, 1, 1, ParameterizedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getParameterizedType_TypeArguments(), this.getTypeAccess(), null, "typeArguments", null, 0, -1, ParameterizedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parenthesizedExpressionEClass, ParenthesizedExpression.class, "ParenthesizedExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParenthesizedExpression_Expression(), this.getExpression(), null, "expression", null, 1, 1, ParenthesizedExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(postfixExpressionEClass, PostfixExpression.class, "PostfixExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPostfixExpression_Operator(), this.getPostfixExpressionKind(), "operator", null, 1, 1, PostfixExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPostfixExpression_Operand(), this.getExpression(), null, "operand", null, 1, 1, PostfixExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(prefixExpressionEClass, PrefixExpression.class, "PrefixExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrefixExpression_Operator(), this.getPrefixExpressionKind(), "operator", null, 1, 1, PrefixExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPrefixExpression_Operand(), this.getExpression(), null, "operand", null, 1, 1, PrefixExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(primitiveTypeEClass, PrimitiveType.class, "PrimitiveType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveTypeBooleanEClass, PrimitiveTypeBoolean.class, "PrimitiveTypeBoolean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveTypeByteEClass, PrimitiveTypeByte.class, "PrimitiveTypeByte", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveTypeCharEClass, PrimitiveTypeChar.class, "PrimitiveTypeChar", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveTypeDoubleEClass, PrimitiveTypeDouble.class, "PrimitiveTypeDouble", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveTypeShortEClass, PrimitiveTypeShort.class, "PrimitiveTypeShort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveTypeFloatEClass, PrimitiveTypeFloat.class, "PrimitiveTypeFloat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveTypeIntEClass, PrimitiveTypeInt.class, "PrimitiveTypeInt", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveTypeLongEClass, PrimitiveTypeLong.class, "PrimitiveTypeLong", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primitiveTypeVoidEClass, PrimitiveTypeVoid.class, "PrimitiveTypeVoid", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(returnStatementEClass, ReturnStatement.class, "ReturnStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReturnStatement_Expression(), this.getExpression(), null, "expression", null, 0, 1, ReturnStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(singleVariableAccessEClass, SingleVariableAccess.class, "SingleVariableAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSingleVariableAccess_Variable(), this.getVariableDeclaration(), this.getVariableDeclaration_UsageInVariableAccess(), "variable", null, 1, 1, SingleVariableAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSingleVariableAccess_Qualifier(), this.getExpression(), null, "qualifier", null, 0, 1, SingleVariableAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(singleVariableDeclarationEClass, SingleVariableDeclaration.class, "SingleVariableDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSingleVariableDeclaration_Modifier(), this.getModifier(), this.getModifier_SingleVariableDeclaration(), "modifier", null, 0, 1, SingleVariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getSingleVariableDeclaration_Varargs(), theEcorePackage.getEBoolean(), "varargs", null, 1, 1, SingleVariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSingleVariableDeclaration_Type(), this.getTypeAccess(), null, "type", null, 1, 1, SingleVariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSingleVariableDeclaration_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, SingleVariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSingleVariableDeclaration_MethodDeclaration(), this.getAbstractMethodDeclaration(), this.getAbstractMethodDeclaration_Parameters(), "methodDeclaration", null, 0, 1, SingleVariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSingleVariableDeclaration_CatchClause(), this.getCatchClause(), this.getCatchClause_Exception(), "catchClause", null, 0, 1, SingleVariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSingleVariableDeclaration_EnhancedForStatement(), this.getEnhancedForStatement(), this.getEnhancedForStatement_Parameter(), "enhancedForStatement", null, 0, 1, SingleVariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(statementEClass, Statement.class, "Statement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringLiteralEClass, StringLiteral.class, "StringLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringLiteral_EscapedValue(), theEcorePackage.getEString(), "escapedValue", null, 1, 1, StringLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(superConstructorInvocationEClass, SuperConstructorInvocation.class, "SuperConstructorInvocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSuperConstructorInvocation_Expression(), this.getExpression(), null, "expression", null, 0, 1, SuperConstructorInvocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(superFieldAccessEClass, SuperFieldAccess.class, "SuperFieldAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSuperFieldAccess_Field(), this.getSingleVariableAccess(), null, "field", null, 1, 1, SuperFieldAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(superMethodInvocationEClass, SuperMethodInvocation.class, "SuperMethodInvocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(switchCaseEClass, SwitchCase.class, "SwitchCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSwitchCase_Default(), theEcorePackage.getEBoolean(), "default", null, 1, 1, SwitchCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSwitchCase_Expression(), this.getExpression(), null, "expression", null, 0, 1, SwitchCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(switchStatementEClass, SwitchStatement.class, "SwitchStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSwitchStatement_Expression(), this.getExpression(), null, "expression", null, 1, 1, SwitchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSwitchStatement_Statements(), this.getStatement(), null, "statements", null, 0, -1, SwitchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(synchronizedStatementEClass, SynchronizedStatement.class, "SynchronizedStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSynchronizedStatement_Body(), this.getBlock(), null, "body", null, 1, 1, SynchronizedStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSynchronizedStatement_Expression(), this.getExpression(), null, "expression", null, 1, 1, SynchronizedStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(tagElementEClass, TagElement.class, "TagElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTagElement_TagName(), theEcorePackage.getEString(), "tagName", null, 0, 1, TagElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTagElement_Fragments(), this.getASTNode(), null, "fragments", null, 0, -1, TagElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textElementEClass, TextElement.class, "TextElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextElement_Text(), theEcorePackage.getEString(), "text", null, 1, 1, TextElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(thisExpressionEClass, ThisExpression.class, "ThisExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(throwStatementEClass, ThrowStatement.class, "ThrowStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getThrowStatement_Expression(), this.getExpression(), null, "expression", null, 1, 1, ThrowStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(tryStatementEClass, TryStatement.class, "TryStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTryStatement_Body(), this.getBlock(), null, "body", null, 1, 1, TryStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTryStatement_Finally(), this.getBlock(), null, "finally", null, 0, 1, TryStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTryStatement_CatchClauses(), this.getCatchClause(), null, "catchClauses", null, 0, -1, TryStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeEClass, Type.class, "Type", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getType_UsagesInTypeAccess(), this.getTypeAccess(), this.getTypeAccess_Type(), "usagesInTypeAccess", null, 0, -1, Type.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(typeAccessEClass, TypeAccess.class, "TypeAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeAccess_Type(), this.getType(), this.getType_UsagesInTypeAccess(), "type", null, 1, 1, TypeAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTypeAccess_Qualifier(), this.getNamespaceAccess(), null, "qualifier", null, 0, 1, TypeAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(typeDeclarationEClass, TypeDeclaration.class, "TypeDeclaration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeDeclaration_TypeParameters(), this.getTypeParameter(), null, "typeParameters", null, 0, -1, TypeDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typeDeclarationStatementEClass, TypeDeclarationStatement.class, "TypeDeclarationStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeDeclarationStatement_Declaration(), this.getAbstractTypeDeclaration(), null, "declaration", null, 1, 1, TypeDeclarationStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(typeLiteralEClass, TypeLiteral.class, "TypeLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeLiteral_Type(), this.getTypeAccess(), null, "type", null, 1, 1, TypeLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(typeParameterEClass, TypeParameter.class, "TypeParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTypeParameter_Bounds(), this.getTypeAccess(), null, "bounds", null, 0, -1, TypeParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unresolvedItemEClass, UnresolvedItem.class, "UnresolvedItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedItemAccessEClass, UnresolvedItemAccess.class, "UnresolvedItemAccess", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnresolvedItemAccess_Element(), this.getUnresolvedItem(), null, "element", null, 0, 1, UnresolvedItemAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getUnresolvedItemAccess_Qualifier(), this.getASTNode(), null, "qualifier", null, 0, 1, UnresolvedItemAccess.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(unresolvedAnnotationDeclarationEClass, UnresolvedAnnotationDeclaration.class, "UnresolvedAnnotationDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedAnnotationTypeMemberDeclarationEClass, UnresolvedAnnotationTypeMemberDeclaration.class, "UnresolvedAnnotationTypeMemberDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedClassDeclarationEClass, UnresolvedClassDeclaration.class, "UnresolvedClassDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedEnumDeclarationEClass, UnresolvedEnumDeclaration.class, "UnresolvedEnumDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedInterfaceDeclarationEClass, UnresolvedInterfaceDeclaration.class, "UnresolvedInterfaceDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedLabeledStatementEClass, UnresolvedLabeledStatement.class, "UnresolvedLabeledStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedMethodDeclarationEClass, UnresolvedMethodDeclaration.class, "UnresolvedMethodDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedSingleVariableDeclarationEClass, UnresolvedSingleVariableDeclaration.class, "UnresolvedSingleVariableDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedTypeEClass, UnresolvedType.class, "UnresolvedType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedTypeDeclarationEClass, UnresolvedTypeDeclaration.class, "UnresolvedTypeDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unresolvedVariableDeclarationFragmentEClass, UnresolvedVariableDeclarationFragment.class, "UnresolvedVariableDeclarationFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(variableDeclarationEClass, VariableDeclaration.class, "VariableDeclaration", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableDeclaration_ExtraArrayDimensions(), theEcorePackage.getEInt(), "extraArrayDimensions", null, 1, 1, VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getVariableDeclaration_Initializer(), this.getExpression(), null, "initializer", null, 0, 1, VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getVariableDeclaration_UsageInVariableAccess(), this.getSingleVariableAccess(), this.getSingleVariableAccess_Variable(), "usageInVariableAccess", null, 0, -1, VariableDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(variableDeclarationExpressionEClass, VariableDeclarationExpression.class, "VariableDeclarationExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVariableDeclarationExpression_Modifier(), this.getModifier(), this.getModifier_VariableDeclarationExpression(), "modifier", null, 0, 1, VariableDeclarationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getVariableDeclarationExpression_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, VariableDeclarationExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(variableDeclarationFragmentEClass, VariableDeclarationFragment.class, "VariableDeclarationFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVariableDeclarationFragment_VariablesContainer(), this.getAbstractVariablesContainer(), this.getAbstractVariablesContainer_Fragments(), "variablesContainer", null, 0, 1, VariableDeclarationFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(variableDeclarationStatementEClass, VariableDeclarationStatement.class, "VariableDeclarationStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVariableDeclarationStatement_ExtraArrayDimensions(), theEcorePackage.getEInt(), "extraArrayDimensions", null, 1, 1, VariableDeclarationStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getVariableDeclarationStatement_Modifier(), this.getModifier(), this.getModifier_VariableDeclarationStatement(), "modifier", null, 0, 1, VariableDeclarationStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getVariableDeclarationStatement_Annotations(), this.getAnnotation(), null, "annotations", null, 0, -1, VariableDeclarationStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wildCardTypeEClass, WildCardType.class, "WildCardType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWildCardType_UpperBound(), theEcorePackage.getEBoolean(), "upperBound", null, 1, 1, WildCardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getWildCardType_Bound(), this.getTypeAccess(), null, "bound", null, 0, 1, WildCardType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(whileStatementEClass, WhileStatement.class, "WhileStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWhileStatement_Expression(), this.getExpression(), null, "expression", null, 1, 1, WhileStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getWhileStatement_Body(), this.getStatement(), null, "body", null, 1, 1, WhileStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(assignmentKindEEnum, AssignmentKind.class, "AssignmentKind");
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.ASSIGN);
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.PLUS_ASSIGN);
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.MINUS_ASSIGN);
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.TIMES_ASSIGN);
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.DIVIDE_ASSIGN);
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.BIT_AND_ASSIGN);
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.BIT_OR_ASSIGN);
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.BIT_XOR_ASSIGN);
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.REMAINDER_ASSIGN);
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.LEFT_SHIFT_ASSIGN);
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.RIGHT_SHIFT_SIGNED_ASSIGN);
		addEEnumLiteral(assignmentKindEEnum, AssignmentKind.RIGHT_SHIFT_UNSIGNED_ASSIGN);

		initEEnum(infixExpressionKindEEnum, InfixExpressionKind.class, "InfixExpressionKind");
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.TIMES);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.DIVIDE);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.REMAINDER);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.PLUS);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.MINUS);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.LEFT_SHIFT);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.RIGHT_SHIFT_SIGNED);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.RIGHT_SHIFT_UNSIGNED);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.LESS);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.GREATER);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.LESS_EQUALS);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.GREATER_EQUALS);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.EQUALS);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.NOT_EQUALS);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.XOR);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.AND);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.OR);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.CONDITIONAL_AND);
		addEEnumLiteral(infixExpressionKindEEnum, InfixExpressionKind.CONDITIONAL_OR);

		initEEnum(inheritanceKindEEnum, InheritanceKind.class, "InheritanceKind");
		addEEnumLiteral(inheritanceKindEEnum, InheritanceKind.NONE);
		addEEnumLiteral(inheritanceKindEEnum, InheritanceKind.ABSTRACT);
		addEEnumLiteral(inheritanceKindEEnum, InheritanceKind.FINAL);

		initEEnum(postfixExpressionKindEEnum, PostfixExpressionKind.class, "PostfixExpressionKind");
		addEEnumLiteral(postfixExpressionKindEEnum, PostfixExpressionKind.INCREMENT);
		addEEnumLiteral(postfixExpressionKindEEnum, PostfixExpressionKind.DECREMENT);

		initEEnum(prefixExpressionKindEEnum, PrefixExpressionKind.class, "PrefixExpressionKind");
		addEEnumLiteral(prefixExpressionKindEEnum, PrefixExpressionKind.INCREMENT);
		addEEnumLiteral(prefixExpressionKindEEnum, PrefixExpressionKind.DECREMENT);
		addEEnumLiteral(prefixExpressionKindEEnum, PrefixExpressionKind.PLUS);
		addEEnumLiteral(prefixExpressionKindEEnum, PrefixExpressionKind.MINUS);
		addEEnumLiteral(prefixExpressionKindEEnum, PrefixExpressionKind.COMPLEMENT);
		addEEnumLiteral(prefixExpressionKindEEnum, PrefixExpressionKind.NOT);

		initEEnum(visibilityKindEEnum, VisibilityKind.class, "VisibilityKind");
		addEEnumLiteral(visibilityKindEEnum, VisibilityKind.NONE);
		addEEnumLiteral(visibilityKindEEnum, VisibilityKind.PUBLIC);
		addEEnumLiteral(visibilityKindEEnum, VisibilityKind.PRIVATE);
		addEEnumLiteral(visibilityKindEEnum, VisibilityKind.PROTECTED);

		// Create resource
		createResource(eNS_URI);
	}

} //JavaPackageImpl
