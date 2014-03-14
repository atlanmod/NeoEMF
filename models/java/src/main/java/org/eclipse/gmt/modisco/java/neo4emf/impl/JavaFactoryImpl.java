/**
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 * Descritpion ! To come
 * @author Amine BENELALLAM
**/
package org.eclipse.gmt.modisco.java.neo4emf.impl;
 


import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
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
import org.eclipse.gmt.modisco.java.BooleanLiteral;
import org.eclipse.gmt.modisco.java.BreakStatement;
import org.eclipse.gmt.modisco.java.CastExpression;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.CharacterLiteral;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.ClassFile;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
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
import org.eclipse.gmt.modisco.java.TypeAccess;
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
import org.eclipse.gmt.modisco.java.VariableDeclarationExpression;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.WhileStatement;
import org.eclipse.gmt.modisco.java.WildCardType;
import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaFactory;
import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;
import org.eclipse.gmt.modisco.java.neo4emf.util.JavaAdapterFactory;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JavaFactoryImpl extends EFactoryImpl implements JavaFactory {

	
	/**
	 * AdapterFactory instance
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	protected JavaAdapterFactory adapterFactory = new JavaAdapterFactory();
	
	
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JavaFactory init() {
		try {
			JavaFactory theJavaFactory = (JavaFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/MoDisco/Java/0.2.incubation/java-Neo4EMF"); 
			if (theJavaFactory != null) {
				return theJavaFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new JavaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case JavaPackage.ANNOTATION: return (EObject)createAnnotation();
			case JavaPackage.ARCHIVE: return (EObject)createArchive();
			case JavaPackage.ASSERT_STATEMENT: return (EObject)createAssertStatement();
			case JavaPackage.ANNOTATION_MEMBER_VALUE_PAIR: return (EObject)createAnnotationMemberValuePair();
			case JavaPackage.ANNOTATION_TYPE_DECLARATION: return (EObject)createAnnotationTypeDeclaration();
			case JavaPackage.ANNOTATION_TYPE_MEMBER_DECLARATION: return (EObject)createAnnotationTypeMemberDeclaration();
			case JavaPackage.ANONYMOUS_CLASS_DECLARATION: return (EObject)createAnonymousClassDeclaration();
			case JavaPackage.ARRAY_ACCESS: return (EObject)createArrayAccess();
			case JavaPackage.ARRAY_CREATION: return (EObject)createArrayCreation();
			case JavaPackage.ARRAY_INITIALIZER: return (EObject)createArrayInitializer();
			case JavaPackage.ARRAY_LENGTH_ACCESS: return (EObject)createArrayLengthAccess();
			case JavaPackage.ARRAY_TYPE: return (EObject)createArrayType();
			case JavaPackage.ASSIGNMENT: return (EObject)createAssignment();
			case JavaPackage.BOOLEAN_LITERAL: return (EObject)createBooleanLiteral();
			case JavaPackage.BLOCK_COMMENT: return (EObject)createBlockComment();
			case JavaPackage.BLOCK: return (EObject)createBlock();
			case JavaPackage.BREAK_STATEMENT: return (EObject)createBreakStatement();
			case JavaPackage.CAST_EXPRESSION: return (EObject)createCastExpression();
			case JavaPackage.CATCH_CLAUSE: return (EObject)createCatchClause();
			case JavaPackage.CHARACTER_LITERAL: return (EObject)createCharacterLiteral();
			case JavaPackage.CLASS_FILE: return (EObject)createClassFile();
			case JavaPackage.CLASS_INSTANCE_CREATION: return (EObject)createClassInstanceCreation();
			case JavaPackage.CONSTRUCTOR_DECLARATION: return (EObject)createConstructorDeclaration();
			case JavaPackage.CONDITIONAL_EXPRESSION: return (EObject)createConditionalExpression();
			case JavaPackage.CONSTRUCTOR_INVOCATION: return (EObject)createConstructorInvocation();
			case JavaPackage.CLASS_DECLARATION: return (EObject)createClassDeclaration();
			case JavaPackage.COMPILATION_UNIT: return (EObject)createCompilationUnit();
			case JavaPackage.CONTINUE_STATEMENT: return (EObject)createContinueStatement();
			case JavaPackage.DO_STATEMENT: return (EObject)createDoStatement();
			case JavaPackage.EMPTY_STATEMENT: return (EObject)createEmptyStatement();
			case JavaPackage.ENHANCED_FOR_STATEMENT: return (EObject)createEnhancedForStatement();
			case JavaPackage.ENUM_CONSTANT_DECLARATION: return (EObject)createEnumConstantDeclaration();
			case JavaPackage.ENUM_DECLARATION: return (EObject)createEnumDeclaration();
			case JavaPackage.EXPRESSION_STATEMENT: return (EObject)createExpressionStatement();
			case JavaPackage.FIELD_ACCESS: return (EObject)createFieldAccess();
			case JavaPackage.FIELD_DECLARATION: return (EObject)createFieldDeclaration();
			case JavaPackage.FOR_STATEMENT: return (EObject)createForStatement();
			case JavaPackage.IF_STATEMENT: return (EObject)createIfStatement();
			case JavaPackage.IMPORT_DECLARATION: return (EObject)createImportDeclaration();
			case JavaPackage.INFIX_EXPRESSION: return (EObject)createInfixExpression();
			case JavaPackage.INITIALIZER: return (EObject)createInitializer();
			case JavaPackage.INSTANCEOF_EXPRESSION: return (EObject)createInstanceofExpression();
			case JavaPackage.INTERFACE_DECLARATION: return (EObject)createInterfaceDeclaration();
			case JavaPackage.JAVADOC: return (EObject)createJavadoc();
			case JavaPackage.LABELED_STATEMENT: return (EObject)createLabeledStatement();
			case JavaPackage.LINE_COMMENT: return (EObject)createLineComment();
			case JavaPackage.MANIFEST: return (EObject)createManifest();
			case JavaPackage.MANIFEST_ATTRIBUTE: return (EObject)createManifestAttribute();
			case JavaPackage.MANIFEST_ENTRY: return (EObject)createManifestEntry();
			case JavaPackage.MEMBER_REF: return (EObject)createMemberRef();
			case JavaPackage.METHOD_DECLARATION: return (EObject)createMethodDeclaration();
			case JavaPackage.METHOD_INVOCATION: return (EObject)createMethodInvocation();
			case JavaPackage.METHOD_REF: return (EObject)createMethodRef();
			case JavaPackage.METHOD_REF_PARAMETER: return (EObject)createMethodRefParameter();
			case JavaPackage.MODEL: return (EObject)createModel();
			case JavaPackage.MODIFIER: return (EObject)createModifier();
			case JavaPackage.NUMBER_LITERAL: return (EObject)createNumberLiteral();
			case JavaPackage.NULL_LITERAL: return (EObject)createNullLiteral();
			case JavaPackage.PACKAGE: return (EObject)createPackage();
			case JavaPackage.PACKAGE_ACCESS: return (EObject)createPackageAccess();
			case JavaPackage.PARAMETERIZED_TYPE: return (EObject)createParameterizedType();
			case JavaPackage.PARENTHESIZED_EXPRESSION: return (EObject)createParenthesizedExpression();
			case JavaPackage.POSTFIX_EXPRESSION: return (EObject)createPostfixExpression();
			case JavaPackage.PREFIX_EXPRESSION: return (EObject)createPrefixExpression();
			case JavaPackage.PRIMITIVE_TYPE: return (EObject)createPrimitiveType();
			case JavaPackage.PRIMITIVE_TYPE_BOOLEAN: return (EObject)createPrimitiveTypeBoolean();
			case JavaPackage.PRIMITIVE_TYPE_BYTE: return (EObject)createPrimitiveTypeByte();
			case JavaPackage.PRIMITIVE_TYPE_CHAR: return (EObject)createPrimitiveTypeChar();
			case JavaPackage.PRIMITIVE_TYPE_DOUBLE: return (EObject)createPrimitiveTypeDouble();
			case JavaPackage.PRIMITIVE_TYPE_SHORT: return (EObject)createPrimitiveTypeShort();
			case JavaPackage.PRIMITIVE_TYPE_FLOAT: return (EObject)createPrimitiveTypeFloat();
			case JavaPackage.PRIMITIVE_TYPE_INT: return (EObject)createPrimitiveTypeInt();
			case JavaPackage.PRIMITIVE_TYPE_LONG: return (EObject)createPrimitiveTypeLong();
			case JavaPackage.PRIMITIVE_TYPE_VOID: return (EObject)createPrimitiveTypeVoid();
			case JavaPackage.RETURN_STATEMENT: return (EObject)createReturnStatement();
			case JavaPackage.SINGLE_VARIABLE_ACCESS: return (EObject)createSingleVariableAccess();
			case JavaPackage.SINGLE_VARIABLE_DECLARATION: return (EObject)createSingleVariableDeclaration();
			case JavaPackage.STRING_LITERAL: return (EObject)createStringLiteral();
			case JavaPackage.SUPER_CONSTRUCTOR_INVOCATION: return (EObject)createSuperConstructorInvocation();
			case JavaPackage.SUPER_FIELD_ACCESS: return (EObject)createSuperFieldAccess();
			case JavaPackage.SUPER_METHOD_INVOCATION: return (EObject)createSuperMethodInvocation();
			case JavaPackage.SWITCH_CASE: return (EObject)createSwitchCase();
			case JavaPackage.SWITCH_STATEMENT: return (EObject)createSwitchStatement();
			case JavaPackage.SYNCHRONIZED_STATEMENT: return (EObject)createSynchronizedStatement();
			case JavaPackage.TAG_ELEMENT: return (EObject)createTagElement();
			case JavaPackage.TEXT_ELEMENT: return (EObject)createTextElement();
			case JavaPackage.THIS_EXPRESSION: return (EObject)createThisExpression();
			case JavaPackage.THROW_STATEMENT: return (EObject)createThrowStatement();
			case JavaPackage.TRY_STATEMENT: return (EObject)createTryStatement();
			case JavaPackage.TYPE_ACCESS: return (EObject)createTypeAccess();
			case JavaPackage.TYPE_DECLARATION_STATEMENT: return (EObject)createTypeDeclarationStatement();
			case JavaPackage.TYPE_LITERAL: return (EObject)createTypeLiteral();
			case JavaPackage.TYPE_PARAMETER: return (EObject)createTypeParameter();
			case JavaPackage.UNRESOLVED_ITEM: return (EObject)createUnresolvedItem();
			case JavaPackage.UNRESOLVED_ITEM_ACCESS: return (EObject)createUnresolvedItemAccess();
			case JavaPackage.UNRESOLVED_ANNOTATION_DECLARATION: return (EObject)createUnresolvedAnnotationDeclaration();
			case JavaPackage.UNRESOLVED_ANNOTATION_TYPE_MEMBER_DECLARATION: return (EObject)createUnresolvedAnnotationTypeMemberDeclaration();
			case JavaPackage.UNRESOLVED_CLASS_DECLARATION: return (EObject)createUnresolvedClassDeclaration();
			case JavaPackage.UNRESOLVED_ENUM_DECLARATION: return (EObject)createUnresolvedEnumDeclaration();
			case JavaPackage.UNRESOLVED_INTERFACE_DECLARATION: return (EObject)createUnresolvedInterfaceDeclaration();
			case JavaPackage.UNRESOLVED_LABELED_STATEMENT: return (EObject)createUnresolvedLabeledStatement();
			case JavaPackage.UNRESOLVED_METHOD_DECLARATION: return (EObject)createUnresolvedMethodDeclaration();
			case JavaPackage.UNRESOLVED_SINGLE_VARIABLE_DECLARATION: return (EObject)createUnresolvedSingleVariableDeclaration();
			case JavaPackage.UNRESOLVED_TYPE: return (EObject)createUnresolvedType();
			case JavaPackage.UNRESOLVED_TYPE_DECLARATION: return (EObject)createUnresolvedTypeDeclaration();
			case JavaPackage.UNRESOLVED_VARIABLE_DECLARATION_FRAGMENT: return (EObject)createUnresolvedVariableDeclarationFragment();
			case JavaPackage.VARIABLE_DECLARATION_EXPRESSION: return (EObject)createVariableDeclarationExpression();
			case JavaPackage.VARIABLE_DECLARATION_FRAGMENT: return (EObject)createVariableDeclarationFragment();
			case JavaPackage.VARIABLE_DECLARATION_STATEMENT: return (EObject)createVariableDeclarationStatement();
			case JavaPackage.WILD_CARD_TYPE: return (EObject)createWildCardType();
			case JavaPackage.WHILE_STATEMENT: return (EObject)createWhileStatement();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case JavaPackage.ASSIGNMENT_KIND:
				return createAssignmentKindFromString(eDataType, initialValue);
			case JavaPackage.INFIX_EXPRESSION_KIND:
				return createInfixExpressionKindFromString(eDataType, initialValue);
			case JavaPackage.INHERITANCE_KIND:
				return createInheritanceKindFromString(eDataType, initialValue);
			case JavaPackage.POSTFIX_EXPRESSION_KIND:
				return createPostfixExpressionKindFromString(eDataType, initialValue);
			case JavaPackage.PREFIX_EXPRESSION_KIND:
				return createPrefixExpressionKindFromString(eDataType, initialValue);
			case JavaPackage.VISIBILITY_KIND:
				return createVisibilityKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case JavaPackage.ASSIGNMENT_KIND:
				return convertAssignmentKindToString(eDataType, instanceValue);
			case JavaPackage.INFIX_EXPRESSION_KIND:
				return convertInfixExpressionKindToString(eDataType, instanceValue);
			case JavaPackage.INHERITANCE_KIND:
				return convertInheritanceKindToString(eDataType, instanceValue);
			case JavaPackage.POSTFIX_EXPRESSION_KIND:
				return convertPostfixExpressionKindToString(eDataType, instanceValue);
			case JavaPackage.PREFIX_EXPRESSION_KIND:
				return convertPrefixExpressionKindToString(eDataType, instanceValue);
			case JavaPackage.VISIBILITY_KIND:
				return convertVisibilityKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Annotation createAnnotation() {
		AnnotationImpl annotation = new AnnotationImpl();
		Adapter adapter = adapterFactory.createAnnotationAdapter();
		if (adapter != null) {
			annotation.eAdapters().add(adapter);
		}
		return annotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Archive createArchive() {
		ArchiveImpl archive = new ArchiveImpl();
		Adapter adapter = adapterFactory.createArchiveAdapter();
		if (adapter != null) {
			archive.eAdapters().add(adapter);
		}
		return archive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssertStatement createAssertStatement() {
		AssertStatementImpl assertStatement = new AssertStatementImpl();
		Adapter adapter = adapterFactory.createAssertStatementAdapter();
		if (adapter != null) {
			assertStatement.eAdapters().add(adapter);
		}
		return assertStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationMemberValuePair createAnnotationMemberValuePair() {
		AnnotationMemberValuePairImpl annotationMemberValuePair = new AnnotationMemberValuePairImpl();
		Adapter adapter = adapterFactory.createAnnotationMemberValuePairAdapter();
		if (adapter != null) {
			annotationMemberValuePair.eAdapters().add(adapter);
		}
		return annotationMemberValuePair;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationTypeDeclaration createAnnotationTypeDeclaration() {
		AnnotationTypeDeclarationImpl annotationTypeDeclaration = new AnnotationTypeDeclarationImpl();
		Adapter adapter = adapterFactory.createAnnotationTypeDeclarationAdapter();
		if (adapter != null) {
			annotationTypeDeclaration.eAdapters().add(adapter);
		}
		return annotationTypeDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationTypeMemberDeclaration createAnnotationTypeMemberDeclaration() {
		AnnotationTypeMemberDeclarationImpl annotationTypeMemberDeclaration = new AnnotationTypeMemberDeclarationImpl();
		Adapter adapter = adapterFactory.createAnnotationTypeMemberDeclarationAdapter();
		if (adapter != null) {
			annotationTypeMemberDeclaration.eAdapters().add(adapter);
		}
		return annotationTypeMemberDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnonymousClassDeclaration createAnonymousClassDeclaration() {
		AnonymousClassDeclarationImpl anonymousClassDeclaration = new AnonymousClassDeclarationImpl();
		Adapter adapter = adapterFactory.createAnonymousClassDeclarationAdapter();
		if (adapter != null) {
			anonymousClassDeclaration.eAdapters().add(adapter);
		}
		return anonymousClassDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayAccess createArrayAccess() {
		ArrayAccessImpl arrayAccess = new ArrayAccessImpl();
		Adapter adapter = adapterFactory.createArrayAccessAdapter();
		if (adapter != null) {
			arrayAccess.eAdapters().add(adapter);
		}
		return arrayAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayCreation createArrayCreation() {
		ArrayCreationImpl arrayCreation = new ArrayCreationImpl();
		Adapter adapter = adapterFactory.createArrayCreationAdapter();
		if (adapter != null) {
			arrayCreation.eAdapters().add(adapter);
		}
		return arrayCreation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayInitializer createArrayInitializer() {
		ArrayInitializerImpl arrayInitializer = new ArrayInitializerImpl();
		Adapter adapter = adapterFactory.createArrayInitializerAdapter();
		if (adapter != null) {
			arrayInitializer.eAdapters().add(adapter);
		}
		return arrayInitializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayLengthAccess createArrayLengthAccess() {
		ArrayLengthAccessImpl arrayLengthAccess = new ArrayLengthAccessImpl();
		Adapter adapter = adapterFactory.createArrayLengthAccessAdapter();
		if (adapter != null) {
			arrayLengthAccess.eAdapters().add(adapter);
		}
		return arrayLengthAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayType createArrayType() {
		ArrayTypeImpl arrayType = new ArrayTypeImpl();
		Adapter adapter = adapterFactory.createArrayTypeAdapter();
		if (adapter != null) {
			arrayType.eAdapters().add(adapter);
		}
		return arrayType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Assignment createAssignment() {
		AssignmentImpl assignment = new AssignmentImpl();
		Adapter adapter = adapterFactory.createAssignmentAdapter();
		if (adapter != null) {
			assignment.eAdapters().add(adapter);
		}
		return assignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanLiteral createBooleanLiteral() {
		BooleanLiteralImpl booleanLiteral = new BooleanLiteralImpl();
		Adapter adapter = adapterFactory.createBooleanLiteralAdapter();
		if (adapter != null) {
			booleanLiteral.eAdapters().add(adapter);
		}
		return booleanLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockComment createBlockComment() {
		BlockCommentImpl blockComment = new BlockCommentImpl();
		Adapter adapter = adapterFactory.createBlockCommentAdapter();
		if (adapter != null) {
			blockComment.eAdapters().add(adapter);
		}
		return blockComment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block createBlock() {
		BlockImpl block = new BlockImpl();
		Adapter adapter = adapterFactory.createBlockAdapter();
		if (adapter != null) {
			block.eAdapters().add(adapter);
		}
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BreakStatement createBreakStatement() {
		BreakStatementImpl breakStatement = new BreakStatementImpl();
		Adapter adapter = adapterFactory.createBreakStatementAdapter();
		if (adapter != null) {
			breakStatement.eAdapters().add(adapter);
		}
		return breakStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CastExpression createCastExpression() {
		CastExpressionImpl castExpression = new CastExpressionImpl();
		Adapter adapter = adapterFactory.createCastExpressionAdapter();
		if (adapter != null) {
			castExpression.eAdapters().add(adapter);
		}
		return castExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CatchClause createCatchClause() {
		CatchClauseImpl catchClause = new CatchClauseImpl();
		Adapter adapter = adapterFactory.createCatchClauseAdapter();
		if (adapter != null) {
			catchClause.eAdapters().add(adapter);
		}
		return catchClause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacterLiteral createCharacterLiteral() {
		CharacterLiteralImpl characterLiteral = new CharacterLiteralImpl();
		Adapter adapter = adapterFactory.createCharacterLiteralAdapter();
		if (adapter != null) {
			characterLiteral.eAdapters().add(adapter);
		}
		return characterLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassFile createClassFile() {
		ClassFileImpl classFile = new ClassFileImpl();
		Adapter adapter = adapterFactory.createClassFileAdapter();
		if (adapter != null) {
			classFile.eAdapters().add(adapter);
		}
		return classFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassInstanceCreation createClassInstanceCreation() {
		ClassInstanceCreationImpl classInstanceCreation = new ClassInstanceCreationImpl();
		Adapter adapter = adapterFactory.createClassInstanceCreationAdapter();
		if (adapter != null) {
			classInstanceCreation.eAdapters().add(adapter);
		}
		return classInstanceCreation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstructorDeclaration createConstructorDeclaration() {
		ConstructorDeclarationImpl constructorDeclaration = new ConstructorDeclarationImpl();
		Adapter adapter = adapterFactory.createConstructorDeclarationAdapter();
		if (adapter != null) {
			constructorDeclaration.eAdapters().add(adapter);
		}
		return constructorDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionalExpression createConditionalExpression() {
		ConditionalExpressionImpl conditionalExpression = new ConditionalExpressionImpl();
		Adapter adapter = adapterFactory.createConditionalExpressionAdapter();
		if (adapter != null) {
			conditionalExpression.eAdapters().add(adapter);
		}
		return conditionalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstructorInvocation createConstructorInvocation() {
		ConstructorInvocationImpl constructorInvocation = new ConstructorInvocationImpl();
		Adapter adapter = adapterFactory.createConstructorInvocationAdapter();
		if (adapter != null) {
			constructorInvocation.eAdapters().add(adapter);
		}
		return constructorInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassDeclaration createClassDeclaration() {
		ClassDeclarationImpl classDeclaration = new ClassDeclarationImpl();
		Adapter adapter = adapterFactory.createClassDeclarationAdapter();
		if (adapter != null) {
			classDeclaration.eAdapters().add(adapter);
		}
		return classDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit createCompilationUnit() {
		CompilationUnitImpl compilationUnit = new CompilationUnitImpl();
		Adapter adapter = adapterFactory.createCompilationUnitAdapter();
		if (adapter != null) {
			compilationUnit.eAdapters().add(adapter);
		}
		return compilationUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContinueStatement createContinueStatement() {
		ContinueStatementImpl continueStatement = new ContinueStatementImpl();
		Adapter adapter = adapterFactory.createContinueStatementAdapter();
		if (adapter != null) {
			continueStatement.eAdapters().add(adapter);
		}
		return continueStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoStatement createDoStatement() {
		DoStatementImpl doStatement = new DoStatementImpl();
		Adapter adapter = adapterFactory.createDoStatementAdapter();
		if (adapter != null) {
			doStatement.eAdapters().add(adapter);
		}
		return doStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmptyStatement createEmptyStatement() {
		EmptyStatementImpl emptyStatement = new EmptyStatementImpl();
		Adapter adapter = adapterFactory.createEmptyStatementAdapter();
		if (adapter != null) {
			emptyStatement.eAdapters().add(adapter);
		}
		return emptyStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnhancedForStatement createEnhancedForStatement() {
		EnhancedForStatementImpl enhancedForStatement = new EnhancedForStatementImpl();
		Adapter adapter = adapterFactory.createEnhancedForStatementAdapter();
		if (adapter != null) {
			enhancedForStatement.eAdapters().add(adapter);
		}
		return enhancedForStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumConstantDeclaration createEnumConstantDeclaration() {
		EnumConstantDeclarationImpl enumConstantDeclaration = new EnumConstantDeclarationImpl();
		Adapter adapter = adapterFactory.createEnumConstantDeclarationAdapter();
		if (adapter != null) {
			enumConstantDeclaration.eAdapters().add(adapter);
		}
		return enumConstantDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumDeclaration createEnumDeclaration() {
		EnumDeclarationImpl enumDeclaration = new EnumDeclarationImpl();
		Adapter adapter = adapterFactory.createEnumDeclarationAdapter();
		if (adapter != null) {
			enumDeclaration.eAdapters().add(adapter);
		}
		return enumDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionStatement createExpressionStatement() {
		ExpressionStatementImpl expressionStatement = new ExpressionStatementImpl();
		Adapter adapter = adapterFactory.createExpressionStatementAdapter();
		if (adapter != null) {
			expressionStatement.eAdapters().add(adapter);
		}
		return expressionStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldAccess createFieldAccess() {
		FieldAccessImpl fieldAccess = new FieldAccessImpl();
		Adapter adapter = adapterFactory.createFieldAccessAdapter();
		if (adapter != null) {
			fieldAccess.eAdapters().add(adapter);
		}
		return fieldAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldDeclaration createFieldDeclaration() {
		FieldDeclarationImpl fieldDeclaration = new FieldDeclarationImpl();
		Adapter adapter = adapterFactory.createFieldDeclarationAdapter();
		if (adapter != null) {
			fieldDeclaration.eAdapters().add(adapter);
		}
		return fieldDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForStatement createForStatement() {
		ForStatementImpl forStatement = new ForStatementImpl();
		Adapter adapter = adapterFactory.createForStatementAdapter();
		if (adapter != null) {
			forStatement.eAdapters().add(adapter);
		}
		return forStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IfStatement createIfStatement() {
		IfStatementImpl ifStatement = new IfStatementImpl();
		Adapter adapter = adapterFactory.createIfStatementAdapter();
		if (adapter != null) {
			ifStatement.eAdapters().add(adapter);
		}
		return ifStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportDeclaration createImportDeclaration() {
		ImportDeclarationImpl importDeclaration = new ImportDeclarationImpl();
		Adapter adapter = adapterFactory.createImportDeclarationAdapter();
		if (adapter != null) {
			importDeclaration.eAdapters().add(adapter);
		}
		return importDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfixExpression createInfixExpression() {
		InfixExpressionImpl infixExpression = new InfixExpressionImpl();
		Adapter adapter = adapterFactory.createInfixExpressionAdapter();
		if (adapter != null) {
			infixExpression.eAdapters().add(adapter);
		}
		return infixExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Initializer createInitializer() {
		InitializerImpl initializer = new InitializerImpl();
		Adapter adapter = adapterFactory.createInitializerAdapter();
		if (adapter != null) {
			initializer.eAdapters().add(adapter);
		}
		return initializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanceofExpression createInstanceofExpression() {
		InstanceofExpressionImpl instanceofExpression = new InstanceofExpressionImpl();
		Adapter adapter = adapterFactory.createInstanceofExpressionAdapter();
		if (adapter != null) {
			instanceofExpression.eAdapters().add(adapter);
		}
		return instanceofExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InterfaceDeclaration createInterfaceDeclaration() {
		InterfaceDeclarationImpl interfaceDeclaration = new InterfaceDeclarationImpl();
		Adapter adapter = adapterFactory.createInterfaceDeclarationAdapter();
		if (adapter != null) {
			interfaceDeclaration.eAdapters().add(adapter);
		}
		return interfaceDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Javadoc createJavadoc() {
		JavadocImpl javadoc = new JavadocImpl();
		Adapter adapter = adapterFactory.createJavadocAdapter();
		if (adapter != null) {
			javadoc.eAdapters().add(adapter);
		}
		return javadoc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabeledStatement createLabeledStatement() {
		LabeledStatementImpl labeledStatement = new LabeledStatementImpl();
		Adapter adapter = adapterFactory.createLabeledStatementAdapter();
		if (adapter != null) {
			labeledStatement.eAdapters().add(adapter);
		}
		return labeledStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineComment createLineComment() {
		LineCommentImpl lineComment = new LineCommentImpl();
		Adapter adapter = adapterFactory.createLineCommentAdapter();
		if (adapter != null) {
			lineComment.eAdapters().add(adapter);
		}
		return lineComment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Manifest createManifest() {
		ManifestImpl manifest = new ManifestImpl();
		Adapter adapter = adapterFactory.createManifestAdapter();
		if (adapter != null) {
			manifest.eAdapters().add(adapter);
		}
		return manifest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManifestAttribute createManifestAttribute() {
		ManifestAttributeImpl manifestAttribute = new ManifestAttributeImpl();
		Adapter adapter = adapterFactory.createManifestAttributeAdapter();
		if (adapter != null) {
			manifestAttribute.eAdapters().add(adapter);
		}
		return manifestAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ManifestEntry createManifestEntry() {
		ManifestEntryImpl manifestEntry = new ManifestEntryImpl();
		Adapter adapter = adapterFactory.createManifestEntryAdapter();
		if (adapter != null) {
			manifestEntry.eAdapters().add(adapter);
		}
		return manifestEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemberRef createMemberRef() {
		MemberRefImpl memberRef = new MemberRefImpl();
		Adapter adapter = adapterFactory.createMemberRefAdapter();
		if (adapter != null) {
			memberRef.eAdapters().add(adapter);
		}
		return memberRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodDeclaration createMethodDeclaration() {
		MethodDeclarationImpl methodDeclaration = new MethodDeclarationImpl();
		Adapter adapter = adapterFactory.createMethodDeclarationAdapter();
		if (adapter != null) {
			methodDeclaration.eAdapters().add(adapter);
		}
		return methodDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodInvocation createMethodInvocation() {
		MethodInvocationImpl methodInvocation = new MethodInvocationImpl();
		Adapter adapter = adapterFactory.createMethodInvocationAdapter();
		if (adapter != null) {
			methodInvocation.eAdapters().add(adapter);
		}
		return methodInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodRef createMethodRef() {
		MethodRefImpl methodRef = new MethodRefImpl();
		Adapter adapter = adapterFactory.createMethodRefAdapter();
		if (adapter != null) {
			methodRef.eAdapters().add(adapter);
		}
		return methodRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodRefParameter createMethodRefParameter() {
		MethodRefParameterImpl methodRefParameter = new MethodRefParameterImpl();
		Adapter adapter = adapterFactory.createMethodRefParameterAdapter();
		if (adapter != null) {
			methodRefParameter.eAdapters().add(adapter);
		}
		return methodRefParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Model createModel() {
		ModelImpl model = new ModelImpl();
		Adapter adapter = adapterFactory.createModelAdapter();
		if (adapter != null) {
			model.eAdapters().add(adapter);
		}
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Modifier createModifier() {
		ModifierImpl modifier = new ModifierImpl();
		Adapter adapter = adapterFactory.createModifierAdapter();
		if (adapter != null) {
			modifier.eAdapters().add(adapter);
		}
		return modifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumberLiteral createNumberLiteral() {
		NumberLiteralImpl numberLiteral = new NumberLiteralImpl();
		Adapter adapter = adapterFactory.createNumberLiteralAdapter();
		if (adapter != null) {
			numberLiteral.eAdapters().add(adapter);
		}
		return numberLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NullLiteral createNullLiteral() {
		NullLiteralImpl nullLiteral = new NullLiteralImpl();
		Adapter adapter = adapterFactory.createNullLiteralAdapter();
		if (adapter != null) {
			nullLiteral.eAdapters().add(adapter);
		}
		return nullLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.eclipse.gmt.modisco.java.Package createPackage() {
		PackageImpl package_ = new PackageImpl();
		Adapter adapter = adapterFactory.createPackageAdapter();
		if (adapter != null) {
			package_.eAdapters().add(adapter);
		}
		return package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageAccess createPackageAccess() {
		PackageAccessImpl packageAccess = new PackageAccessImpl();
		Adapter adapter = adapterFactory.createPackageAccessAdapter();
		if (adapter != null) {
			packageAccess.eAdapters().add(adapter);
		}
		return packageAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterizedType createParameterizedType() {
		ParameterizedTypeImpl parameterizedType = new ParameterizedTypeImpl();
		Adapter adapter = adapterFactory.createParameterizedTypeAdapter();
		if (adapter != null) {
			parameterizedType.eAdapters().add(adapter);
		}
		return parameterizedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParenthesizedExpression createParenthesizedExpression() {
		ParenthesizedExpressionImpl parenthesizedExpression = new ParenthesizedExpressionImpl();
		Adapter adapter = adapterFactory.createParenthesizedExpressionAdapter();
		if (adapter != null) {
			parenthesizedExpression.eAdapters().add(adapter);
		}
		return parenthesizedExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PostfixExpression createPostfixExpression() {
		PostfixExpressionImpl postfixExpression = new PostfixExpressionImpl();
		Adapter adapter = adapterFactory.createPostfixExpressionAdapter();
		if (adapter != null) {
			postfixExpression.eAdapters().add(adapter);
		}
		return postfixExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrefixExpression createPrefixExpression() {
		PrefixExpressionImpl prefixExpression = new PrefixExpressionImpl();
		Adapter adapter = adapterFactory.createPrefixExpressionAdapter();
		if (adapter != null) {
			prefixExpression.eAdapters().add(adapter);
		}
		return prefixExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType createPrimitiveType() {
		PrimitiveTypeImpl primitiveType = new PrimitiveTypeImpl();
		Adapter adapter = adapterFactory.createPrimitiveTypeAdapter();
		if (adapter != null) {
			primitiveType.eAdapters().add(adapter);
		}
		return primitiveType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveTypeBoolean createPrimitiveTypeBoolean() {
		PrimitiveTypeBooleanImpl primitiveTypeBoolean = new PrimitiveTypeBooleanImpl();
		Adapter adapter = adapterFactory.createPrimitiveTypeBooleanAdapter();
		if (adapter != null) {
			primitiveTypeBoolean.eAdapters().add(adapter);
		}
		return primitiveTypeBoolean;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveTypeByte createPrimitiveTypeByte() {
		PrimitiveTypeByteImpl primitiveTypeByte = new PrimitiveTypeByteImpl();
		Adapter adapter = adapterFactory.createPrimitiveTypeByteAdapter();
		if (adapter != null) {
			primitiveTypeByte.eAdapters().add(adapter);
		}
		return primitiveTypeByte;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveTypeChar createPrimitiveTypeChar() {
		PrimitiveTypeCharImpl primitiveTypeChar = new PrimitiveTypeCharImpl();
		Adapter adapter = adapterFactory.createPrimitiveTypeCharAdapter();
		if (adapter != null) {
			primitiveTypeChar.eAdapters().add(adapter);
		}
		return primitiveTypeChar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveTypeDouble createPrimitiveTypeDouble() {
		PrimitiveTypeDoubleImpl primitiveTypeDouble = new PrimitiveTypeDoubleImpl();
		Adapter adapter = adapterFactory.createPrimitiveTypeDoubleAdapter();
		if (adapter != null) {
			primitiveTypeDouble.eAdapters().add(adapter);
		}
		return primitiveTypeDouble;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveTypeShort createPrimitiveTypeShort() {
		PrimitiveTypeShortImpl primitiveTypeShort = new PrimitiveTypeShortImpl();
		Adapter adapter = adapterFactory.createPrimitiveTypeShortAdapter();
		if (adapter != null) {
			primitiveTypeShort.eAdapters().add(adapter);
		}
		return primitiveTypeShort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveTypeFloat createPrimitiveTypeFloat() {
		PrimitiveTypeFloatImpl primitiveTypeFloat = new PrimitiveTypeFloatImpl();
		Adapter adapter = adapterFactory.createPrimitiveTypeFloatAdapter();
		if (adapter != null) {
			primitiveTypeFloat.eAdapters().add(adapter);
		}
		return primitiveTypeFloat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveTypeInt createPrimitiveTypeInt() {
		PrimitiveTypeIntImpl primitiveTypeInt = new PrimitiveTypeIntImpl();
		Adapter adapter = adapterFactory.createPrimitiveTypeIntAdapter();
		if (adapter != null) {
			primitiveTypeInt.eAdapters().add(adapter);
		}
		return primitiveTypeInt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveTypeLong createPrimitiveTypeLong() {
		PrimitiveTypeLongImpl primitiveTypeLong = new PrimitiveTypeLongImpl();
		Adapter adapter = adapterFactory.createPrimitiveTypeLongAdapter();
		if (adapter != null) {
			primitiveTypeLong.eAdapters().add(adapter);
		}
		return primitiveTypeLong;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveTypeVoid createPrimitiveTypeVoid() {
		PrimitiveTypeVoidImpl primitiveTypeVoid = new PrimitiveTypeVoidImpl();
		Adapter adapter = adapterFactory.createPrimitiveTypeVoidAdapter();
		if (adapter != null) {
			primitiveTypeVoid.eAdapters().add(adapter);
		}
		return primitiveTypeVoid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReturnStatement createReturnStatement() {
		ReturnStatementImpl returnStatement = new ReturnStatementImpl();
		Adapter adapter = adapterFactory.createReturnStatementAdapter();
		if (adapter != null) {
			returnStatement.eAdapters().add(adapter);
		}
		return returnStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleVariableAccess createSingleVariableAccess() {
		SingleVariableAccessImpl singleVariableAccess = new SingleVariableAccessImpl();
		Adapter adapter = adapterFactory.createSingleVariableAccessAdapter();
		if (adapter != null) {
			singleVariableAccess.eAdapters().add(adapter);
		}
		return singleVariableAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleVariableDeclaration createSingleVariableDeclaration() {
		SingleVariableDeclarationImpl singleVariableDeclaration = new SingleVariableDeclarationImpl();
		Adapter adapter = adapterFactory.createSingleVariableDeclarationAdapter();
		if (adapter != null) {
			singleVariableDeclaration.eAdapters().add(adapter);
		}
		return singleVariableDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringLiteral createStringLiteral() {
		StringLiteralImpl stringLiteral = new StringLiteralImpl();
		Adapter adapter = adapterFactory.createStringLiteralAdapter();
		if (adapter != null) {
			stringLiteral.eAdapters().add(adapter);
		}
		return stringLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuperConstructorInvocation createSuperConstructorInvocation() {
		SuperConstructorInvocationImpl superConstructorInvocation = new SuperConstructorInvocationImpl();
		Adapter adapter = adapterFactory.createSuperConstructorInvocationAdapter();
		if (adapter != null) {
			superConstructorInvocation.eAdapters().add(adapter);
		}
		return superConstructorInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuperFieldAccess createSuperFieldAccess() {
		SuperFieldAccessImpl superFieldAccess = new SuperFieldAccessImpl();
		Adapter adapter = adapterFactory.createSuperFieldAccessAdapter();
		if (adapter != null) {
			superFieldAccess.eAdapters().add(adapter);
		}
		return superFieldAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuperMethodInvocation createSuperMethodInvocation() {
		SuperMethodInvocationImpl superMethodInvocation = new SuperMethodInvocationImpl();
		Adapter adapter = adapterFactory.createSuperMethodInvocationAdapter();
		if (adapter != null) {
			superMethodInvocation.eAdapters().add(adapter);
		}
		return superMethodInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchCase createSwitchCase() {
		SwitchCaseImpl switchCase = new SwitchCaseImpl();
		Adapter adapter = adapterFactory.createSwitchCaseAdapter();
		if (adapter != null) {
			switchCase.eAdapters().add(adapter);
		}
		return switchCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchStatement createSwitchStatement() {
		SwitchStatementImpl switchStatement = new SwitchStatementImpl();
		Adapter adapter = adapterFactory.createSwitchStatementAdapter();
		if (adapter != null) {
			switchStatement.eAdapters().add(adapter);
		}
		return switchStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronizedStatement createSynchronizedStatement() {
		SynchronizedStatementImpl synchronizedStatement = new SynchronizedStatementImpl();
		Adapter adapter = adapterFactory.createSynchronizedStatementAdapter();
		if (adapter != null) {
			synchronizedStatement.eAdapters().add(adapter);
		}
		return synchronizedStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagElement createTagElement() {
		TagElementImpl tagElement = new TagElementImpl();
		Adapter adapter = adapterFactory.createTagElementAdapter();
		if (adapter != null) {
			tagElement.eAdapters().add(adapter);
		}
		return tagElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextElement createTextElement() {
		TextElementImpl textElement = new TextElementImpl();
		Adapter adapter = adapterFactory.createTextElementAdapter();
		if (adapter != null) {
			textElement.eAdapters().add(adapter);
		}
		return textElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThisExpression createThisExpression() {
		ThisExpressionImpl thisExpression = new ThisExpressionImpl();
		Adapter adapter = adapterFactory.createThisExpressionAdapter();
		if (adapter != null) {
			thisExpression.eAdapters().add(adapter);
		}
		return thisExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThrowStatement createThrowStatement() {
		ThrowStatementImpl throwStatement = new ThrowStatementImpl();
		Adapter adapter = adapterFactory.createThrowStatementAdapter();
		if (adapter != null) {
			throwStatement.eAdapters().add(adapter);
		}
		return throwStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TryStatement createTryStatement() {
		TryStatementImpl tryStatement = new TryStatementImpl();
		Adapter adapter = adapterFactory.createTryStatementAdapter();
		if (adapter != null) {
			tryStatement.eAdapters().add(adapter);
		}
		return tryStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess createTypeAccess() {
		TypeAccessImpl typeAccess = new TypeAccessImpl();
		Adapter adapter = adapterFactory.createTypeAccessAdapter();
		if (adapter != null) {
			typeAccess.eAdapters().add(adapter);
		}
		return typeAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclarationStatement createTypeDeclarationStatement() {
		TypeDeclarationStatementImpl typeDeclarationStatement = new TypeDeclarationStatementImpl();
		Adapter adapter = adapterFactory.createTypeDeclarationStatementAdapter();
		if (adapter != null) {
			typeDeclarationStatement.eAdapters().add(adapter);
		}
		return typeDeclarationStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeLiteral createTypeLiteral() {
		TypeLiteralImpl typeLiteral = new TypeLiteralImpl();
		Adapter adapter = adapterFactory.createTypeLiteralAdapter();
		if (adapter != null) {
			typeLiteral.eAdapters().add(adapter);
		}
		return typeLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeParameter createTypeParameter() {
		TypeParameterImpl typeParameter = new TypeParameterImpl();
		Adapter adapter = adapterFactory.createTypeParameterAdapter();
		if (adapter != null) {
			typeParameter.eAdapters().add(adapter);
		}
		return typeParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedItem createUnresolvedItem() {
		UnresolvedItemImpl unresolvedItem = new UnresolvedItemImpl();
		Adapter adapter = adapterFactory.createUnresolvedItemAdapter();
		if (adapter != null) {
			unresolvedItem.eAdapters().add(adapter);
		}
		return unresolvedItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedItemAccess createUnresolvedItemAccess() {
		UnresolvedItemAccessImpl unresolvedItemAccess = new UnresolvedItemAccessImpl();
		Adapter adapter = adapterFactory.createUnresolvedItemAccessAdapter();
		if (adapter != null) {
			unresolvedItemAccess.eAdapters().add(adapter);
		}
		return unresolvedItemAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedAnnotationDeclaration createUnresolvedAnnotationDeclaration() {
		UnresolvedAnnotationDeclarationImpl unresolvedAnnotationDeclaration = new UnresolvedAnnotationDeclarationImpl();
		Adapter adapter = adapterFactory.createUnresolvedAnnotationDeclarationAdapter();
		if (adapter != null) {
			unresolvedAnnotationDeclaration.eAdapters().add(adapter);
		}
		return unresolvedAnnotationDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedAnnotationTypeMemberDeclaration createUnresolvedAnnotationTypeMemberDeclaration() {
		UnresolvedAnnotationTypeMemberDeclarationImpl unresolvedAnnotationTypeMemberDeclaration = new UnresolvedAnnotationTypeMemberDeclarationImpl();
		Adapter adapter = adapterFactory.createUnresolvedAnnotationTypeMemberDeclarationAdapter();
		if (adapter != null) {
			unresolvedAnnotationTypeMemberDeclaration.eAdapters().add(adapter);
		}
		return unresolvedAnnotationTypeMemberDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedClassDeclaration createUnresolvedClassDeclaration() {
		UnresolvedClassDeclarationImpl unresolvedClassDeclaration = new UnresolvedClassDeclarationImpl();
		Adapter adapter = adapterFactory.createUnresolvedClassDeclarationAdapter();
		if (adapter != null) {
			unresolvedClassDeclaration.eAdapters().add(adapter);
		}
		return unresolvedClassDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedEnumDeclaration createUnresolvedEnumDeclaration() {
		UnresolvedEnumDeclarationImpl unresolvedEnumDeclaration = new UnresolvedEnumDeclarationImpl();
		Adapter adapter = adapterFactory.createUnresolvedEnumDeclarationAdapter();
		if (adapter != null) {
			unresolvedEnumDeclaration.eAdapters().add(adapter);
		}
		return unresolvedEnumDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedInterfaceDeclaration createUnresolvedInterfaceDeclaration() {
		UnresolvedInterfaceDeclarationImpl unresolvedInterfaceDeclaration = new UnresolvedInterfaceDeclarationImpl();
		Adapter adapter = adapterFactory.createUnresolvedInterfaceDeclarationAdapter();
		if (adapter != null) {
			unresolvedInterfaceDeclaration.eAdapters().add(adapter);
		}
		return unresolvedInterfaceDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedLabeledStatement createUnresolvedLabeledStatement() {
		UnresolvedLabeledStatementImpl unresolvedLabeledStatement = new UnresolvedLabeledStatementImpl();
		Adapter adapter = adapterFactory.createUnresolvedLabeledStatementAdapter();
		if (adapter != null) {
			unresolvedLabeledStatement.eAdapters().add(adapter);
		}
		return unresolvedLabeledStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedMethodDeclaration createUnresolvedMethodDeclaration() {
		UnresolvedMethodDeclarationImpl unresolvedMethodDeclaration = new UnresolvedMethodDeclarationImpl();
		Adapter adapter = adapterFactory.createUnresolvedMethodDeclarationAdapter();
		if (adapter != null) {
			unresolvedMethodDeclaration.eAdapters().add(adapter);
		}
		return unresolvedMethodDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedSingleVariableDeclaration createUnresolvedSingleVariableDeclaration() {
		UnresolvedSingleVariableDeclarationImpl unresolvedSingleVariableDeclaration = new UnresolvedSingleVariableDeclarationImpl();
		Adapter adapter = adapterFactory.createUnresolvedSingleVariableDeclarationAdapter();
		if (adapter != null) {
			unresolvedSingleVariableDeclaration.eAdapters().add(adapter);
		}
		return unresolvedSingleVariableDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedType createUnresolvedType() {
		UnresolvedTypeImpl unresolvedType = new UnresolvedTypeImpl();
		Adapter adapter = adapterFactory.createUnresolvedTypeAdapter();
		if (adapter != null) {
			unresolvedType.eAdapters().add(adapter);
		}
		return unresolvedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedTypeDeclaration createUnresolvedTypeDeclaration() {
		UnresolvedTypeDeclarationImpl unresolvedTypeDeclaration = new UnresolvedTypeDeclarationImpl();
		Adapter adapter = adapterFactory.createUnresolvedTypeDeclarationAdapter();
		if (adapter != null) {
			unresolvedTypeDeclaration.eAdapters().add(adapter);
		}
		return unresolvedTypeDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnresolvedVariableDeclarationFragment createUnresolvedVariableDeclarationFragment() {
		UnresolvedVariableDeclarationFragmentImpl unresolvedVariableDeclarationFragment = new UnresolvedVariableDeclarationFragmentImpl();
		Adapter adapter = adapterFactory.createUnresolvedVariableDeclarationFragmentAdapter();
		if (adapter != null) {
			unresolvedVariableDeclarationFragment.eAdapters().add(adapter);
		}
		return unresolvedVariableDeclarationFragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclarationExpression createVariableDeclarationExpression() {
		VariableDeclarationExpressionImpl variableDeclarationExpression = new VariableDeclarationExpressionImpl();
		Adapter adapter = adapterFactory.createVariableDeclarationExpressionAdapter();
		if (adapter != null) {
			variableDeclarationExpression.eAdapters().add(adapter);
		}
		return variableDeclarationExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclarationFragment createVariableDeclarationFragment() {
		VariableDeclarationFragmentImpl variableDeclarationFragment = new VariableDeclarationFragmentImpl();
		Adapter adapter = adapterFactory.createVariableDeclarationFragmentAdapter();
		if (adapter != null) {
			variableDeclarationFragment.eAdapters().add(adapter);
		}
		return variableDeclarationFragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclarationStatement createVariableDeclarationStatement() {
		VariableDeclarationStatementImpl variableDeclarationStatement = new VariableDeclarationStatementImpl();
		Adapter adapter = adapterFactory.createVariableDeclarationStatementAdapter();
		if (adapter != null) {
			variableDeclarationStatement.eAdapters().add(adapter);
		}
		return variableDeclarationStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WildCardType createWildCardType() {
		WildCardTypeImpl wildCardType = new WildCardTypeImpl();
		Adapter adapter = adapterFactory.createWildCardTypeAdapter();
		if (adapter != null) {
			wildCardType.eAdapters().add(adapter);
		}
		return wildCardType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WhileStatement createWhileStatement() {
		WhileStatementImpl whileStatement = new WhileStatementImpl();
		Adapter adapter = adapterFactory.createWhileStatementAdapter();
		if (adapter != null) {
			whileStatement.eAdapters().add(adapter);
		}
		return whileStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentKind createAssignmentKindFromString(EDataType eDataType, String initialValue) {
		AssignmentKind result = AssignmentKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAssignmentKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfixExpressionKind createInfixExpressionKindFromString(EDataType eDataType, String initialValue) {
		InfixExpressionKind result = InfixExpressionKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInfixExpressionKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InheritanceKind createInheritanceKindFromString(EDataType eDataType, String initialValue) {
		InheritanceKind result = InheritanceKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInheritanceKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PostfixExpressionKind createPostfixExpressionKindFromString(EDataType eDataType, String initialValue) {
		PostfixExpressionKind result = PostfixExpressionKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPostfixExpressionKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrefixExpressionKind createPrefixExpressionKindFromString(EDataType eDataType, String initialValue) {
		PrefixExpressionKind result = PrefixExpressionKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPrefixExpressionKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisibilityKind createVisibilityKindFromString(EDataType eDataType, String initialValue) {
		VisibilityKind result = VisibilityKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertVisibilityKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaPackage getJavaPackage() {
		return (JavaPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static JavaPackage getPackage() {
		return JavaPackage.eINSTANCE;
	}

} //JavaFactoryImpl
