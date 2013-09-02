/**
 *
 * $Id$
 */
package DOM.impl;



import DOM.*;

import DOM.util.DOMAdapterFactory;

import fr.inria.atlanmod.neo4emf.change.impl.ChangeLog;
import fr.inria.atlanmod.neo4emf.change.impl.NewObject;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DOMFactoryImpl extends EFactoryImpl implements DOMFactory {

	
	/**
	 * AdapterFactory instance
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */

	protected DOMAdapterFactory adapterFactory = new DOMAdapterFactory();
	
	
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DOMFactory init() {
		try {
			DOMFactory theDOMFactory = (DOMFactory)EPackage.Registry.INSTANCE.getEFactory("org.amma.dsl.jdt.dom"); 
			if (theDOMFactory != null) {
				return theDOMFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DOMFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DOMFactoryImpl() {
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
			case DOMPackage.AST: return (EObject)createAST();
			case DOMPackage.ANONYMOUS_CLASS_DECLARATION: return (EObject)createAnonymousClassDeclaration();
			case DOMPackage.CATCH_CLAUSE: return (EObject)createCatchClause();
			case DOMPackage.COMPILATION_UNIT: return (EObject)createCompilationUnit();
			case DOMPackage.IMPORT_DECLARATION: return (EObject)createImportDeclaration();
			case DOMPackage.MEMBER_REF: return (EObject)createMemberRef();
			case DOMPackage.MEMBER_VALUE_PAIR: return (EObject)createMemberValuePair();
			case DOMPackage.METHOD_REF: return (EObject)createMethodRef();
			case DOMPackage.METHOD_REF_PARAMETER: return (EObject)createMethodRefParameter();
			case DOMPackage.MODIFIER: return (EObject)createModifier();
			case DOMPackage.PACKAGE_DECLARATION: return (EObject)createPackageDeclaration();
			case DOMPackage.TAG_ELEMENT: return (EObject)createTagElement();
			case DOMPackage.TEXT_ELEMENT: return (EObject)createTextElement();
			case DOMPackage.TYPE_PARAMETER: return (EObject)createTypeParameter();
			case DOMPackage.ANNOTATION_TYPE_MEMBER_DECLARATION: return (EObject)createAnnotationTypeMemberDeclaration();
			case DOMPackage.ENUM_CONSTANT_DECLARATION: return (EObject)createEnumConstantDeclaration();
			case DOMPackage.FIELD_DECLARATION: return (EObject)createFieldDeclaration();
			case DOMPackage.INITIALIZER: return (EObject)createInitializer();
			case DOMPackage.METHOD_DECLARATION: return (EObject)createMethodDeclaration();
			case DOMPackage.ANNOTATION_TYPE_DECLARATION: return (EObject)createAnnotationTypeDeclaration();
			case DOMPackage.ENUM_DECLARATION: return (EObject)createEnumDeclaration();
			case DOMPackage.TYPE_DECLARATION: return (EObject)createTypeDeclaration();
			case DOMPackage.BLOCK_COMMENT: return (EObject)createBlockComment();
			case DOMPackage.JAVADOC: return (EObject)createJavadoc();
			case DOMPackage.LINE_COMMENT: return (EObject)createLineComment();
			case DOMPackage.ARRAY_ACCESS: return (EObject)createArrayAccess();
			case DOMPackage.ARRAY_CREATION: return (EObject)createArrayCreation();
			case DOMPackage.ARRAY_INITIALIZER: return (EObject)createArrayInitializer();
			case DOMPackage.ASSIGNMENT: return (EObject)createAssignment();
			case DOMPackage.BOOLEAN_LITERAL: return (EObject)createBooleanLiteral();
			case DOMPackage.CAST_EXPRESSION: return (EObject)createCastExpression();
			case DOMPackage.CHARACTER_LITERAL: return (EObject)createCharacterLiteral();
			case DOMPackage.CLASS_INSTANCE_CREATION: return (EObject)createClassInstanceCreation();
			case DOMPackage.CONDITIONAL_EXPRESSION: return (EObject)createConditionalExpression();
			case DOMPackage.FIELD_ACCESS: return (EObject)createFieldAccess();
			case DOMPackage.INFIX_EXPRESSION: return (EObject)createInfixExpression();
			case DOMPackage.INSTANCEOF_EXPRESSION: return (EObject)createInstanceofExpression();
			case DOMPackage.METHOD_INVOCATION: return (EObject)createMethodInvocation();
			case DOMPackage.NULL_LITERAL: return (EObject)createNullLiteral();
			case DOMPackage.NUMBER_LITERAL: return (EObject)createNumberLiteral();
			case DOMPackage.PARENTHESIZED_EXPRESSION: return (EObject)createParenthesizedExpression();
			case DOMPackage.POSTFIX_EXPRESSION: return (EObject)createPostfixExpression();
			case DOMPackage.PREFIX_EXPRESSION: return (EObject)createPrefixExpression();
			case DOMPackage.STRING_LITERAL: return (EObject)createStringLiteral();
			case DOMPackage.SUPER_FIELD_ACCESS: return (EObject)createSuperFieldAccess();
			case DOMPackage.SUPER_METHOD_INVOCATION: return (EObject)createSuperMethodInvocation();
			case DOMPackage.THIS_EXPRESSION: return (EObject)createThisExpression();
			case DOMPackage.TYPE_LITERAL: return (EObject)createTypeLiteral();
			case DOMPackage.VARIABLE_DECLARATION_EXPRESSION: return (EObject)createVariableDeclarationExpression();
			case DOMPackage.ASSERT_STATEMENT: return (EObject)createAssertStatement();
			case DOMPackage.BLOCK: return (EObject)createBlock();
			case DOMPackage.BREAK_STATEMENT: return (EObject)createBreakStatement();
			case DOMPackage.CONSTRUCTOR_INVOCATION: return (EObject)createConstructorInvocation();
			case DOMPackage.CONTINUE_STATEMENT: return (EObject)createContinueStatement();
			case DOMPackage.DO_STATEMENT: return (EObject)createDoStatement();
			case DOMPackage.EMPTY_STATEMENT: return (EObject)createEmptyStatement();
			case DOMPackage.ENHANCED_FOR_STATEMENT: return (EObject)createEnhancedForStatement();
			case DOMPackage.EXPRESSION_STATEMENT: return (EObject)createExpressionStatement();
			case DOMPackage.FOR_STATEMENT: return (EObject)createForStatement();
			case DOMPackage.IF_STATEMENT: return (EObject)createIfStatement();
			case DOMPackage.LABELED_STATEMENT: return (EObject)createLabeledStatement();
			case DOMPackage.RETURN_STATEMENT: return (EObject)createReturnStatement();
			case DOMPackage.SUPER_CONSTRUCTOR_INVOCATION: return (EObject)createSuperConstructorInvocation();
			case DOMPackage.SWITCH_CASE: return (EObject)createSwitchCase();
			case DOMPackage.SWITCH_STATEMENT: return (EObject)createSwitchStatement();
			case DOMPackage.SYNCHRONIZED_STATEMENT: return (EObject)createSynchronizedStatement();
			case DOMPackage.THROW_STATEMENT: return (EObject)createThrowStatement();
			case DOMPackage.TRY_STATEMENT: return (EObject)createTryStatement();
			case DOMPackage.TYPE_DECLARATION_STATEMENT: return (EObject)createTypeDeclarationStatement();
			case DOMPackage.VARIABLE_DECLARATION_STATEMENT: return (EObject)createVariableDeclarationStatement();
			case DOMPackage.WHILE_STATEMENT: return (EObject)createWhileStatement();
			case DOMPackage.ARRAY_TYPE: return (EObject)createArrayType();
			case DOMPackage.PARAMETERIZED_TYPE: return (EObject)createParameterizedType();
			case DOMPackage.PRIMITIVE_TYPE: return (EObject)createPrimitiveType();
			case DOMPackage.QUALIFIED_TYPE: return (EObject)createQualifiedType();
			case DOMPackage.SIMPLE_TYPE: return (EObject)createSimpleType();
			case DOMPackage.WILDCARD_TYPE: return (EObject)createWildcardType();
			case DOMPackage.SINGLE_VARIABLE_DECLARATION: return (EObject)createSingleVariableDeclaration();
			case DOMPackage.VARIABLE_DECLARATION_FRAGMENT: return (EObject)createVariableDeclarationFragment();
			case DOMPackage.QUALIFIED_NAME: return (EObject)createQualifiedName();
			case DOMPackage.SIMPLE_NAME: return (EObject)createSimpleName();
			case DOMPackage.MARKER_ANNOTATION: return (EObject)createMarkerAnnotation();
			case DOMPackage.NORMAL_ANNOTATION: return (EObject)createNormalAnnotation();
			case DOMPackage.SINGLE_MEMBER_ANNOTATION: return (EObject)createSingleMemberAnnotation();
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
			case DOMPackage.ASSIGNMENT_OPERATOR_KIND:
				return createAssignmentOperatorKindFromString(eDataType, initialValue);
			case DOMPackage.INFIX_EXPRESSION_OPERATOR_KIND:
				return createInfixExpressionOperatorKindFromString(eDataType, initialValue);
			case DOMPackage.POSTFIX_EXPRESSION_OPERATOR_KIND:
				return createPostfixExpressionOperatorKindFromString(eDataType, initialValue);
			case DOMPackage.PREFIX_EXPRESSION_OPERATOR_KIND:
				return createPrefixExpressionOperatorKindFromString(eDataType, initialValue);
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
			case DOMPackage.ASSIGNMENT_OPERATOR_KIND:
				return convertAssignmentOperatorKindToString(eDataType, instanceValue);
			case DOMPackage.INFIX_EXPRESSION_OPERATOR_KIND:
				return convertInfixExpressionOperatorKindToString(eDataType, instanceValue);
			case DOMPackage.POSTFIX_EXPRESSION_OPERATOR_KIND:
				return convertPostfixExpressionOperatorKindToString(eDataType, instanceValue);
			case DOMPackage.PREFIX_EXPRESSION_OPERATOR_KIND:
				return convertPrefixExpressionOperatorKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AST createAST() {
		ASTImpl ast = new ASTImpl();
		ast.eAdapters().add(adapterFactory.createASTAdapter());
		ChangeLog.getInstance().add(new NewObject(ast));
		return ast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnonymousClassDeclaration createAnonymousClassDeclaration() {
		AnonymousClassDeclarationImpl anonymousClassDeclaration = new AnonymousClassDeclarationImpl();
		anonymousClassDeclaration.eAdapters().add(adapterFactory.createAnonymousClassDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(anonymousClassDeclaration));
		return anonymousClassDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CatchClause createCatchClause() {
		CatchClauseImpl catchClause = new CatchClauseImpl();
		catchClause.eAdapters().add(adapterFactory.createCatchClauseAdapter());
		ChangeLog.getInstance().add(new NewObject(catchClause));
		return catchClause;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompilationUnit createCompilationUnit() {
		CompilationUnitImpl compilationUnit = new CompilationUnitImpl();
		compilationUnit.eAdapters().add(adapterFactory.createCompilationUnitAdapter());
		ChangeLog.getInstance().add(new NewObject(compilationUnit));
		return compilationUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportDeclaration createImportDeclaration() {
		ImportDeclarationImpl importDeclaration = new ImportDeclarationImpl();
		importDeclaration.eAdapters().add(adapterFactory.createImportDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(importDeclaration));
		return importDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemberRef createMemberRef() {
		MemberRefImpl memberRef = new MemberRefImpl();
		memberRef.eAdapters().add(adapterFactory.createMemberRefAdapter());
		ChangeLog.getInstance().add(new NewObject(memberRef));
		return memberRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemberValuePair createMemberValuePair() {
		MemberValuePairImpl memberValuePair = new MemberValuePairImpl();
		memberValuePair.eAdapters().add(adapterFactory.createMemberValuePairAdapter());
		ChangeLog.getInstance().add(new NewObject(memberValuePair));
		return memberValuePair;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodRef createMethodRef() {
		MethodRefImpl methodRef = new MethodRefImpl();
		methodRef.eAdapters().add(adapterFactory.createMethodRefAdapter());
		ChangeLog.getInstance().add(new NewObject(methodRef));
		return methodRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodRefParameter createMethodRefParameter() {
		MethodRefParameterImpl methodRefParameter = new MethodRefParameterImpl();
		methodRefParameter.eAdapters().add(adapterFactory.createMethodRefParameterAdapter());
		ChangeLog.getInstance().add(new NewObject(methodRefParameter));
		return methodRefParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Modifier createModifier() {
		ModifierImpl modifier = new ModifierImpl();
		modifier.eAdapters().add(adapterFactory.createModifierAdapter());
		ChangeLog.getInstance().add(new NewObject(modifier));
		return modifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageDeclaration createPackageDeclaration() {
		PackageDeclarationImpl packageDeclaration = new PackageDeclarationImpl();
		packageDeclaration.eAdapters().add(adapterFactory.createPackageDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(packageDeclaration));
		return packageDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagElement createTagElement() {
		TagElementImpl tagElement = new TagElementImpl();
		tagElement.eAdapters().add(adapterFactory.createTagElementAdapter());
		ChangeLog.getInstance().add(new NewObject(tagElement));
		return tagElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextElement createTextElement() {
		TextElementImpl textElement = new TextElementImpl();
		textElement.eAdapters().add(adapterFactory.createTextElementAdapter());
		ChangeLog.getInstance().add(new NewObject(textElement));
		return textElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeParameter createTypeParameter() {
		TypeParameterImpl typeParameter = new TypeParameterImpl();
		typeParameter.eAdapters().add(adapterFactory.createTypeParameterAdapter());
		ChangeLog.getInstance().add(new NewObject(typeParameter));
		return typeParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationTypeMemberDeclaration createAnnotationTypeMemberDeclaration() {
		AnnotationTypeMemberDeclarationImpl annotationTypeMemberDeclaration = new AnnotationTypeMemberDeclarationImpl();
		annotationTypeMemberDeclaration.eAdapters().add(adapterFactory.createAnnotationTypeMemberDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(annotationTypeMemberDeclaration));
		return annotationTypeMemberDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumConstantDeclaration createEnumConstantDeclaration() {
		EnumConstantDeclarationImpl enumConstantDeclaration = new EnumConstantDeclarationImpl();
		enumConstantDeclaration.eAdapters().add(adapterFactory.createEnumConstantDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(enumConstantDeclaration));
		return enumConstantDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldDeclaration createFieldDeclaration() {
		FieldDeclarationImpl fieldDeclaration = new FieldDeclarationImpl();
		fieldDeclaration.eAdapters().add(adapterFactory.createFieldDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(fieldDeclaration));
		return fieldDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Initializer createInitializer() {
		InitializerImpl initializer = new InitializerImpl();
		initializer.eAdapters().add(adapterFactory.createInitializerAdapter());
		ChangeLog.getInstance().add(new NewObject(initializer));
		return initializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodDeclaration createMethodDeclaration() {
		MethodDeclarationImpl methodDeclaration = new MethodDeclarationImpl();
		methodDeclaration.eAdapters().add(adapterFactory.createMethodDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(methodDeclaration));
		return methodDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationTypeDeclaration createAnnotationTypeDeclaration() {
		AnnotationTypeDeclarationImpl annotationTypeDeclaration = new AnnotationTypeDeclarationImpl();
		annotationTypeDeclaration.eAdapters().add(adapterFactory.createAnnotationTypeDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(annotationTypeDeclaration));
		return annotationTypeDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumDeclaration createEnumDeclaration() {
		EnumDeclarationImpl enumDeclaration = new EnumDeclarationImpl();
		enumDeclaration.eAdapters().add(adapterFactory.createEnumDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(enumDeclaration));
		return enumDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclaration createTypeDeclaration() {
		TypeDeclarationImpl typeDeclaration = new TypeDeclarationImpl();
		typeDeclaration.eAdapters().add(adapterFactory.createTypeDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(typeDeclaration));
		return typeDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockComment createBlockComment() {
		BlockCommentImpl blockComment = new BlockCommentImpl();
		blockComment.eAdapters().add(adapterFactory.createBlockCommentAdapter());
		ChangeLog.getInstance().add(new NewObject(blockComment));
		return blockComment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Javadoc createJavadoc() {
		JavadocImpl javadoc = new JavadocImpl();
		javadoc.eAdapters().add(adapterFactory.createJavadocAdapter());
		ChangeLog.getInstance().add(new NewObject(javadoc));
		return javadoc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineComment createLineComment() {
		LineCommentImpl lineComment = new LineCommentImpl();
		lineComment.eAdapters().add(adapterFactory.createLineCommentAdapter());
		ChangeLog.getInstance().add(new NewObject(lineComment));
		return lineComment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayAccess createArrayAccess() {
		ArrayAccessImpl arrayAccess = new ArrayAccessImpl();
		arrayAccess.eAdapters().add(adapterFactory.createArrayAccessAdapter());
		ChangeLog.getInstance().add(new NewObject(arrayAccess));
		return arrayAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayCreation createArrayCreation() {
		ArrayCreationImpl arrayCreation = new ArrayCreationImpl();
		arrayCreation.eAdapters().add(adapterFactory.createArrayCreationAdapter());
		ChangeLog.getInstance().add(new NewObject(arrayCreation));
		return arrayCreation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayInitializer createArrayInitializer() {
		ArrayInitializerImpl arrayInitializer = new ArrayInitializerImpl();
		arrayInitializer.eAdapters().add(adapterFactory.createArrayInitializerAdapter());
		ChangeLog.getInstance().add(new NewObject(arrayInitializer));
		return arrayInitializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Assignment createAssignment() {
		AssignmentImpl assignment = new AssignmentImpl();
		assignment.eAdapters().add(adapterFactory.createAssignmentAdapter());
		ChangeLog.getInstance().add(new NewObject(assignment));
		return assignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanLiteral createBooleanLiteral() {
		BooleanLiteralImpl booleanLiteral = new BooleanLiteralImpl();
		booleanLiteral.eAdapters().add(adapterFactory.createBooleanLiteralAdapter());
		ChangeLog.getInstance().add(new NewObject(booleanLiteral));
		return booleanLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CastExpression createCastExpression() {
		CastExpressionImpl castExpression = new CastExpressionImpl();
		castExpression.eAdapters().add(adapterFactory.createCastExpressionAdapter());
		ChangeLog.getInstance().add(new NewObject(castExpression));
		return castExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacterLiteral createCharacterLiteral() {
		CharacterLiteralImpl characterLiteral = new CharacterLiteralImpl();
		characterLiteral.eAdapters().add(adapterFactory.createCharacterLiteralAdapter());
		ChangeLog.getInstance().add(new NewObject(characterLiteral));
		return characterLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassInstanceCreation createClassInstanceCreation() {
		ClassInstanceCreationImpl classInstanceCreation = new ClassInstanceCreationImpl();
		classInstanceCreation.eAdapters().add(adapterFactory.createClassInstanceCreationAdapter());
		ChangeLog.getInstance().add(new NewObject(classInstanceCreation));
		return classInstanceCreation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionalExpression createConditionalExpression() {
		ConditionalExpressionImpl conditionalExpression = new ConditionalExpressionImpl();
		conditionalExpression.eAdapters().add(adapterFactory.createConditionalExpressionAdapter());
		ChangeLog.getInstance().add(new NewObject(conditionalExpression));
		return conditionalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldAccess createFieldAccess() {
		FieldAccessImpl fieldAccess = new FieldAccessImpl();
		fieldAccess.eAdapters().add(adapterFactory.createFieldAccessAdapter());
		ChangeLog.getInstance().add(new NewObject(fieldAccess));
		return fieldAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfixExpression createInfixExpression() {
		InfixExpressionImpl infixExpression = new InfixExpressionImpl();
		infixExpression.eAdapters().add(adapterFactory.createInfixExpressionAdapter());
		ChangeLog.getInstance().add(new NewObject(infixExpression));
		return infixExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstanceofExpression createInstanceofExpression() {
		InstanceofExpressionImpl instanceofExpression = new InstanceofExpressionImpl();
		instanceofExpression.eAdapters().add(adapterFactory.createInstanceofExpressionAdapter());
		ChangeLog.getInstance().add(new NewObject(instanceofExpression));
		return instanceofExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodInvocation createMethodInvocation() {
		MethodInvocationImpl methodInvocation = new MethodInvocationImpl();
		methodInvocation.eAdapters().add(adapterFactory.createMethodInvocationAdapter());
		ChangeLog.getInstance().add(new NewObject(methodInvocation));
		return methodInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NullLiteral createNullLiteral() {
		NullLiteralImpl nullLiteral = new NullLiteralImpl();
		nullLiteral.eAdapters().add(adapterFactory.createNullLiteralAdapter());
		ChangeLog.getInstance().add(new NewObject(nullLiteral));
		return nullLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NumberLiteral createNumberLiteral() {
		NumberLiteralImpl numberLiteral = new NumberLiteralImpl();
		numberLiteral.eAdapters().add(adapterFactory.createNumberLiteralAdapter());
		ChangeLog.getInstance().add(new NewObject(numberLiteral));
		return numberLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParenthesizedExpression createParenthesizedExpression() {
		ParenthesizedExpressionImpl parenthesizedExpression = new ParenthesizedExpressionImpl();
		parenthesizedExpression.eAdapters().add(adapterFactory.createParenthesizedExpressionAdapter());
		ChangeLog.getInstance().add(new NewObject(parenthesizedExpression));
		return parenthesizedExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PostfixExpression createPostfixExpression() {
		PostfixExpressionImpl postfixExpression = new PostfixExpressionImpl();
		postfixExpression.eAdapters().add(adapterFactory.createPostfixExpressionAdapter());
		ChangeLog.getInstance().add(new NewObject(postfixExpression));
		return postfixExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrefixExpression createPrefixExpression() {
		PrefixExpressionImpl prefixExpression = new PrefixExpressionImpl();
		prefixExpression.eAdapters().add(adapterFactory.createPrefixExpressionAdapter());
		ChangeLog.getInstance().add(new NewObject(prefixExpression));
		return prefixExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringLiteral createStringLiteral() {
		StringLiteralImpl stringLiteral = new StringLiteralImpl();
		stringLiteral.eAdapters().add(adapterFactory.createStringLiteralAdapter());
		ChangeLog.getInstance().add(new NewObject(stringLiteral));
		return stringLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuperFieldAccess createSuperFieldAccess() {
		SuperFieldAccessImpl superFieldAccess = new SuperFieldAccessImpl();
		superFieldAccess.eAdapters().add(adapterFactory.createSuperFieldAccessAdapter());
		ChangeLog.getInstance().add(new NewObject(superFieldAccess));
		return superFieldAccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuperMethodInvocation createSuperMethodInvocation() {
		SuperMethodInvocationImpl superMethodInvocation = new SuperMethodInvocationImpl();
		superMethodInvocation.eAdapters().add(adapterFactory.createSuperMethodInvocationAdapter());
		ChangeLog.getInstance().add(new NewObject(superMethodInvocation));
		return superMethodInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThisExpression createThisExpression() {
		ThisExpressionImpl thisExpression = new ThisExpressionImpl();
		thisExpression.eAdapters().add(adapterFactory.createThisExpressionAdapter());
		ChangeLog.getInstance().add(new NewObject(thisExpression));
		return thisExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeLiteral createTypeLiteral() {
		TypeLiteralImpl typeLiteral = new TypeLiteralImpl();
		typeLiteral.eAdapters().add(adapterFactory.createTypeLiteralAdapter());
		ChangeLog.getInstance().add(new NewObject(typeLiteral));
		return typeLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclarationExpression createVariableDeclarationExpression() {
		VariableDeclarationExpressionImpl variableDeclarationExpression = new VariableDeclarationExpressionImpl();
		variableDeclarationExpression.eAdapters().add(adapterFactory.createVariableDeclarationExpressionAdapter());
		ChangeLog.getInstance().add(new NewObject(variableDeclarationExpression));
		return variableDeclarationExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssertStatement createAssertStatement() {
		AssertStatementImpl assertStatement = new AssertStatementImpl();
		assertStatement.eAdapters().add(adapterFactory.createAssertStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(assertStatement));
		return assertStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block createBlock() {
		BlockImpl block = new BlockImpl();
		block.eAdapters().add(adapterFactory.createBlockAdapter());
		ChangeLog.getInstance().add(new NewObject(block));
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BreakStatement createBreakStatement() {
		BreakStatementImpl breakStatement = new BreakStatementImpl();
		breakStatement.eAdapters().add(adapterFactory.createBreakStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(breakStatement));
		return breakStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstructorInvocation createConstructorInvocation() {
		ConstructorInvocationImpl constructorInvocation = new ConstructorInvocationImpl();
		constructorInvocation.eAdapters().add(adapterFactory.createConstructorInvocationAdapter());
		ChangeLog.getInstance().add(new NewObject(constructorInvocation));
		return constructorInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContinueStatement createContinueStatement() {
		ContinueStatementImpl continueStatement = new ContinueStatementImpl();
		continueStatement.eAdapters().add(adapterFactory.createContinueStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(continueStatement));
		return continueStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoStatement createDoStatement() {
		DoStatementImpl doStatement = new DoStatementImpl();
		doStatement.eAdapters().add(adapterFactory.createDoStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(doStatement));
		return doStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmptyStatement createEmptyStatement() {
		EmptyStatementImpl emptyStatement = new EmptyStatementImpl();
		emptyStatement.eAdapters().add(adapterFactory.createEmptyStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(emptyStatement));
		return emptyStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnhancedForStatement createEnhancedForStatement() {
		EnhancedForStatementImpl enhancedForStatement = new EnhancedForStatementImpl();
		enhancedForStatement.eAdapters().add(adapterFactory.createEnhancedForStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(enhancedForStatement));
		return enhancedForStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionStatement createExpressionStatement() {
		ExpressionStatementImpl expressionStatement = new ExpressionStatementImpl();
		expressionStatement.eAdapters().add(adapterFactory.createExpressionStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(expressionStatement));
		return expressionStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForStatement createForStatement() {
		ForStatementImpl forStatement = new ForStatementImpl();
		forStatement.eAdapters().add(adapterFactory.createForStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(forStatement));
		return forStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IfStatement createIfStatement() {
		IfStatementImpl ifStatement = new IfStatementImpl();
		ifStatement.eAdapters().add(adapterFactory.createIfStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(ifStatement));
		return ifStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabeledStatement createLabeledStatement() {
		LabeledStatementImpl labeledStatement = new LabeledStatementImpl();
		labeledStatement.eAdapters().add(adapterFactory.createLabeledStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(labeledStatement));
		return labeledStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReturnStatement createReturnStatement() {
		ReturnStatementImpl returnStatement = new ReturnStatementImpl();
		returnStatement.eAdapters().add(adapterFactory.createReturnStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(returnStatement));
		return returnStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SuperConstructorInvocation createSuperConstructorInvocation() {
		SuperConstructorInvocationImpl superConstructorInvocation = new SuperConstructorInvocationImpl();
		superConstructorInvocation.eAdapters().add(adapterFactory.createSuperConstructorInvocationAdapter());
		ChangeLog.getInstance().add(new NewObject(superConstructorInvocation));
		return superConstructorInvocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchCase createSwitchCase() {
		SwitchCaseImpl switchCase = new SwitchCaseImpl();
		switchCase.eAdapters().add(adapterFactory.createSwitchCaseAdapter());
		ChangeLog.getInstance().add(new NewObject(switchCase));
		return switchCase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SwitchStatement createSwitchStatement() {
		SwitchStatementImpl switchStatement = new SwitchStatementImpl();
		switchStatement.eAdapters().add(adapterFactory.createSwitchStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(switchStatement));
		return switchStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronizedStatement createSynchronizedStatement() {
		SynchronizedStatementImpl synchronizedStatement = new SynchronizedStatementImpl();
		synchronizedStatement.eAdapters().add(adapterFactory.createSynchronizedStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(synchronizedStatement));
		return synchronizedStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThrowStatement createThrowStatement() {
		ThrowStatementImpl throwStatement = new ThrowStatementImpl();
		throwStatement.eAdapters().add(adapterFactory.createThrowStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(throwStatement));
		return throwStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TryStatement createTryStatement() {
		TryStatementImpl tryStatement = new TryStatementImpl();
		tryStatement.eAdapters().add(adapterFactory.createTryStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(tryStatement));
		return tryStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclarationStatement createTypeDeclarationStatement() {
		TypeDeclarationStatementImpl typeDeclarationStatement = new TypeDeclarationStatementImpl();
		typeDeclarationStatement.eAdapters().add(adapterFactory.createTypeDeclarationStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(typeDeclarationStatement));
		return typeDeclarationStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclarationStatement createVariableDeclarationStatement() {
		VariableDeclarationStatementImpl variableDeclarationStatement = new VariableDeclarationStatementImpl();
		variableDeclarationStatement.eAdapters().add(adapterFactory.createVariableDeclarationStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(variableDeclarationStatement));
		return variableDeclarationStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WhileStatement createWhileStatement() {
		WhileStatementImpl whileStatement = new WhileStatementImpl();
		whileStatement.eAdapters().add(adapterFactory.createWhileStatementAdapter());
		ChangeLog.getInstance().add(new NewObject(whileStatement));
		return whileStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayType createArrayType() {
		ArrayTypeImpl arrayType = new ArrayTypeImpl();
		arrayType.eAdapters().add(adapterFactory.createArrayTypeAdapter());
		ChangeLog.getInstance().add(new NewObject(arrayType));
		return arrayType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterizedType createParameterizedType() {
		ParameterizedTypeImpl parameterizedType = new ParameterizedTypeImpl();
		parameterizedType.eAdapters().add(adapterFactory.createParameterizedTypeAdapter());
		ChangeLog.getInstance().add(new NewObject(parameterizedType));
		return parameterizedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType createPrimitiveType() {
		PrimitiveTypeImpl primitiveType = new PrimitiveTypeImpl();
		primitiveType.eAdapters().add(adapterFactory.createPrimitiveTypeAdapter());
		ChangeLog.getInstance().add(new NewObject(primitiveType));
		return primitiveType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualifiedType createQualifiedType() {
		QualifiedTypeImpl qualifiedType = new QualifiedTypeImpl();
		qualifiedType.eAdapters().add(adapterFactory.createQualifiedTypeAdapter());
		ChangeLog.getInstance().add(new NewObject(qualifiedType));
		return qualifiedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleType createSimpleType() {
		SimpleTypeImpl simpleType = new SimpleTypeImpl();
		simpleType.eAdapters().add(adapterFactory.createSimpleTypeAdapter());
		ChangeLog.getInstance().add(new NewObject(simpleType));
		return simpleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WildcardType createWildcardType() {
		WildcardTypeImpl wildcardType = new WildcardTypeImpl();
		wildcardType.eAdapters().add(adapterFactory.createWildcardTypeAdapter());
		ChangeLog.getInstance().add(new NewObject(wildcardType));
		return wildcardType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleVariableDeclaration createSingleVariableDeclaration() {
		SingleVariableDeclarationImpl singleVariableDeclaration = new SingleVariableDeclarationImpl();
		singleVariableDeclaration.eAdapters().add(adapterFactory.createSingleVariableDeclarationAdapter());
		ChangeLog.getInstance().add(new NewObject(singleVariableDeclaration));
		return singleVariableDeclaration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableDeclarationFragment createVariableDeclarationFragment() {
		VariableDeclarationFragmentImpl variableDeclarationFragment = new VariableDeclarationFragmentImpl();
		variableDeclarationFragment.eAdapters().add(adapterFactory.createVariableDeclarationFragmentAdapter());
		ChangeLog.getInstance().add(new NewObject(variableDeclarationFragment));
		return variableDeclarationFragment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualifiedName createQualifiedName() {
		QualifiedNameImpl qualifiedName = new QualifiedNameImpl();
		qualifiedName.eAdapters().add(adapterFactory.createQualifiedNameAdapter());
		ChangeLog.getInstance().add(new NewObject(qualifiedName));
		return qualifiedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleName createSimpleName() {
		SimpleNameImpl simpleName = new SimpleNameImpl();
		simpleName.eAdapters().add(adapterFactory.createSimpleNameAdapter());
		ChangeLog.getInstance().add(new NewObject(simpleName));
		return simpleName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MarkerAnnotation createMarkerAnnotation() {
		MarkerAnnotationImpl markerAnnotation = new MarkerAnnotationImpl();
		markerAnnotation.eAdapters().add(adapterFactory.createMarkerAnnotationAdapter());
		ChangeLog.getInstance().add(new NewObject(markerAnnotation));
		return markerAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NormalAnnotation createNormalAnnotation() {
		NormalAnnotationImpl normalAnnotation = new NormalAnnotationImpl();
		normalAnnotation.eAdapters().add(adapterFactory.createNormalAnnotationAdapter());
		ChangeLog.getInstance().add(new NewObject(normalAnnotation));
		return normalAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SingleMemberAnnotation createSingleMemberAnnotation() {
		SingleMemberAnnotationImpl singleMemberAnnotation = new SingleMemberAnnotationImpl();
		singleMemberAnnotation.eAdapters().add(adapterFactory.createSingleMemberAnnotationAdapter());
		ChangeLog.getInstance().add(new NewObject(singleMemberAnnotation));
		return singleMemberAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssignmentOperatorKind createAssignmentOperatorKindFromString(EDataType eDataType, String initialValue) {
		AssignmentOperatorKind result = AssignmentOperatorKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAssignmentOperatorKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InfixExpressionOperatorKind createInfixExpressionOperatorKindFromString(EDataType eDataType, String initialValue) {
		InfixExpressionOperatorKind result = InfixExpressionOperatorKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInfixExpressionOperatorKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PostfixExpressionOperatorKind createPostfixExpressionOperatorKindFromString(EDataType eDataType, String initialValue) {
		PostfixExpressionOperatorKind result = PostfixExpressionOperatorKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPostfixExpressionOperatorKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrefixExpressionOperatorKind createPrefixExpressionOperatorKindFromString(EDataType eDataType, String initialValue) {
		PrefixExpressionOperatorKind result = PrefixExpressionOperatorKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPrefixExpressionOperatorKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DOMPackage getDOMPackage() {
		return (DOMPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DOMPackage getPackage() {
		return DOMPackage.eINSTANCE;
	}

} //DOMFactoryImpl
