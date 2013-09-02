/**
 *
 * $Id$
 */
package DOM.util;


import DOM.*;

import fr.inria.atlanmod.neo4emf.INeo4emfNotification;
import fr.inria.atlanmod.neo4emf.INeo4emfResource;

import fr.inria.atlanmod.neo4emf.change.impl.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see DOM.DOMPackage
 * @generated
 */
public class DOMAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DOMPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DOMAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DOMPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DOMSwitch<Adapter> modelSwitch =
		new DOMSwitch<Adapter>() {
			@Override
			public Adapter caseAST(AST object) {
				return createASTAdapter();
			}
			@Override
			public Adapter caseASTNode(ASTNode object) {
				return createASTNodeAdapter();
			}
			@Override
			public Adapter caseAnonymousClassDeclaration(AnonymousClassDeclaration object) {
				return createAnonymousClassDeclarationAdapter();
			}
			@Override
			public Adapter caseBodyDeclaration(BodyDeclaration object) {
				return createBodyDeclarationAdapter();
			}
			@Override
			public Adapter caseCatchClause(CatchClause object) {
				return createCatchClauseAdapter();
			}
			@Override
			public Adapter caseComment(Comment object) {
				return createCommentAdapter();
			}
			@Override
			public Adapter caseCompilationUnit(CompilationUnit object) {
				return createCompilationUnitAdapter();
			}
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter caseImportDeclaration(ImportDeclaration object) {
				return createImportDeclarationAdapter();
			}
			@Override
			public Adapter caseMemberRef(MemberRef object) {
				return createMemberRefAdapter();
			}
			@Override
			public Adapter caseMemberValuePair(MemberValuePair object) {
				return createMemberValuePairAdapter();
			}
			@Override
			public Adapter caseMethodRef(MethodRef object) {
				return createMethodRefAdapter();
			}
			@Override
			public Adapter caseMethodRefParameter(MethodRefParameter object) {
				return createMethodRefParameterAdapter();
			}
			@Override
			public Adapter caseExtendedModifier(ExtendedModifier object) {
				return createExtendedModifierAdapter();
			}
			@Override
			public Adapter caseModifier(Modifier object) {
				return createModifierAdapter();
			}
			@Override
			public Adapter casePackageDeclaration(PackageDeclaration object) {
				return createPackageDeclarationAdapter();
			}
			@Override
			public Adapter caseStatement(Statement object) {
				return createStatementAdapter();
			}
			@Override
			public Adapter caseTagElement(TagElement object) {
				return createTagElementAdapter();
			}
			@Override
			public Adapter caseTextElement(TextElement object) {
				return createTextElementAdapter();
			}
			@Override
			public Adapter caseType(Type object) {
				return createTypeAdapter();
			}
			@Override
			public Adapter caseTypeParameter(TypeParameter object) {
				return createTypeParameterAdapter();
			}
			@Override
			public Adapter caseVariableDeclaration(VariableDeclaration object) {
				return createVariableDeclarationAdapter();
			}
			@Override
			public Adapter caseAbstractTypeDeclaration(AbstractTypeDeclaration object) {
				return createAbstractTypeDeclarationAdapter();
			}
			@Override
			public Adapter caseAnnotationTypeMemberDeclaration(AnnotationTypeMemberDeclaration object) {
				return createAnnotationTypeMemberDeclarationAdapter();
			}
			@Override
			public Adapter caseEnumConstantDeclaration(EnumConstantDeclaration object) {
				return createEnumConstantDeclarationAdapter();
			}
			@Override
			public Adapter caseFieldDeclaration(FieldDeclaration object) {
				return createFieldDeclarationAdapter();
			}
			@Override
			public Adapter caseInitializer(Initializer object) {
				return createInitializerAdapter();
			}
			@Override
			public Adapter caseMethodDeclaration(MethodDeclaration object) {
				return createMethodDeclarationAdapter();
			}
			@Override
			public Adapter caseAnnotationTypeDeclaration(AnnotationTypeDeclaration object) {
				return createAnnotationTypeDeclarationAdapter();
			}
			@Override
			public Adapter caseEnumDeclaration(EnumDeclaration object) {
				return createEnumDeclarationAdapter();
			}
			@Override
			public Adapter caseTypeDeclaration(TypeDeclaration object) {
				return createTypeDeclarationAdapter();
			}
			@Override
			public Adapter caseBlockComment(BlockComment object) {
				return createBlockCommentAdapter();
			}
			@Override
			public Adapter caseJavadoc(Javadoc object) {
				return createJavadocAdapter();
			}
			@Override
			public Adapter caseLineComment(LineComment object) {
				return createLineCommentAdapter();
			}
			@Override
			public Adapter caseAnnotation(Annotation object) {
				return createAnnotationAdapter();
			}
			@Override
			public Adapter caseArrayAccess(ArrayAccess object) {
				return createArrayAccessAdapter();
			}
			@Override
			public Adapter caseArrayCreation(ArrayCreation object) {
				return createArrayCreationAdapter();
			}
			@Override
			public Adapter caseArrayInitializer(ArrayInitializer object) {
				return createArrayInitializerAdapter();
			}
			@Override
			public Adapter caseAssignment(Assignment object) {
				return createAssignmentAdapter();
			}
			@Override
			public Adapter caseBooleanLiteral(BooleanLiteral object) {
				return createBooleanLiteralAdapter();
			}
			@Override
			public Adapter caseCastExpression(CastExpression object) {
				return createCastExpressionAdapter();
			}
			@Override
			public Adapter caseCharacterLiteral(CharacterLiteral object) {
				return createCharacterLiteralAdapter();
			}
			@Override
			public Adapter caseClassInstanceCreation(ClassInstanceCreation object) {
				return createClassInstanceCreationAdapter();
			}
			@Override
			public Adapter caseConditionalExpression(ConditionalExpression object) {
				return createConditionalExpressionAdapter();
			}
			@Override
			public Adapter caseFieldAccess(FieldAccess object) {
				return createFieldAccessAdapter();
			}
			@Override
			public Adapter caseInfixExpression(InfixExpression object) {
				return createInfixExpressionAdapter();
			}
			@Override
			public Adapter caseInstanceofExpression(InstanceofExpression object) {
				return createInstanceofExpressionAdapter();
			}
			@Override
			public Adapter caseMethodInvocation(MethodInvocation object) {
				return createMethodInvocationAdapter();
			}
			@Override
			public Adapter caseName(Name object) {
				return createNameAdapter();
			}
			@Override
			public Adapter caseNullLiteral(NullLiteral object) {
				return createNullLiteralAdapter();
			}
			@Override
			public Adapter caseNumberLiteral(NumberLiteral object) {
				return createNumberLiteralAdapter();
			}
			@Override
			public Adapter caseParenthesizedExpression(ParenthesizedExpression object) {
				return createParenthesizedExpressionAdapter();
			}
			@Override
			public Adapter casePostfixExpression(PostfixExpression object) {
				return createPostfixExpressionAdapter();
			}
			@Override
			public Adapter casePrefixExpression(PrefixExpression object) {
				return createPrefixExpressionAdapter();
			}
			@Override
			public Adapter caseStringLiteral(StringLiteral object) {
				return createStringLiteralAdapter();
			}
			@Override
			public Adapter caseSuperFieldAccess(SuperFieldAccess object) {
				return createSuperFieldAccessAdapter();
			}
			@Override
			public Adapter caseSuperMethodInvocation(SuperMethodInvocation object) {
				return createSuperMethodInvocationAdapter();
			}
			@Override
			public Adapter caseThisExpression(ThisExpression object) {
				return createThisExpressionAdapter();
			}
			@Override
			public Adapter caseTypeLiteral(TypeLiteral object) {
				return createTypeLiteralAdapter();
			}
			@Override
			public Adapter caseVariableDeclarationExpression(VariableDeclarationExpression object) {
				return createVariableDeclarationExpressionAdapter();
			}
			@Override
			public Adapter caseAssertStatement(AssertStatement object) {
				return createAssertStatementAdapter();
			}
			@Override
			public Adapter caseBlock(Block object) {
				return createBlockAdapter();
			}
			@Override
			public Adapter caseBreakStatement(BreakStatement object) {
				return createBreakStatementAdapter();
			}
			@Override
			public Adapter caseConstructorInvocation(ConstructorInvocation object) {
				return createConstructorInvocationAdapter();
			}
			@Override
			public Adapter caseContinueStatement(ContinueStatement object) {
				return createContinueStatementAdapter();
			}
			@Override
			public Adapter caseDoStatement(DoStatement object) {
				return createDoStatementAdapter();
			}
			@Override
			public Adapter caseEmptyStatement(EmptyStatement object) {
				return createEmptyStatementAdapter();
			}
			@Override
			public Adapter caseEnhancedForStatement(EnhancedForStatement object) {
				return createEnhancedForStatementAdapter();
			}
			@Override
			public Adapter caseExpressionStatement(ExpressionStatement object) {
				return createExpressionStatementAdapter();
			}
			@Override
			public Adapter caseForStatement(ForStatement object) {
				return createForStatementAdapter();
			}
			@Override
			public Adapter caseIfStatement(IfStatement object) {
				return createIfStatementAdapter();
			}
			@Override
			public Adapter caseLabeledStatement(LabeledStatement object) {
				return createLabeledStatementAdapter();
			}
			@Override
			public Adapter caseReturnStatement(ReturnStatement object) {
				return createReturnStatementAdapter();
			}
			@Override
			public Adapter caseSuperConstructorInvocation(SuperConstructorInvocation object) {
				return createSuperConstructorInvocationAdapter();
			}
			@Override
			public Adapter caseSwitchCase(SwitchCase object) {
				return createSwitchCaseAdapter();
			}
			@Override
			public Adapter caseSwitchStatement(SwitchStatement object) {
				return createSwitchStatementAdapter();
			}
			@Override
			public Adapter caseSynchronizedStatement(SynchronizedStatement object) {
				return createSynchronizedStatementAdapter();
			}
			@Override
			public Adapter caseThrowStatement(ThrowStatement object) {
				return createThrowStatementAdapter();
			}
			@Override
			public Adapter caseTryStatement(TryStatement object) {
				return createTryStatementAdapter();
			}
			@Override
			public Adapter caseTypeDeclarationStatement(TypeDeclarationStatement object) {
				return createTypeDeclarationStatementAdapter();
			}
			@Override
			public Adapter caseVariableDeclarationStatement(VariableDeclarationStatement object) {
				return createVariableDeclarationStatementAdapter();
			}
			@Override
			public Adapter caseWhileStatement(WhileStatement object) {
				return createWhileStatementAdapter();
			}
			@Override
			public Adapter caseArrayType(ArrayType object) {
				return createArrayTypeAdapter();
			}
			@Override
			public Adapter caseParameterizedType(ParameterizedType object) {
				return createParameterizedTypeAdapter();
			}
			@Override
			public Adapter casePrimitiveType(PrimitiveType object) {
				return createPrimitiveTypeAdapter();
			}
			@Override
			public Adapter caseQualifiedType(QualifiedType object) {
				return createQualifiedTypeAdapter();
			}
			@Override
			public Adapter caseSimpleType(SimpleType object) {
				return createSimpleTypeAdapter();
			}
			@Override
			public Adapter caseWildcardType(WildcardType object) {
				return createWildcardTypeAdapter();
			}
			@Override
			public Adapter caseSingleVariableDeclaration(SingleVariableDeclaration object) {
				return createSingleVariableDeclarationAdapter();
			}
			@Override
			public Adapter caseVariableDeclarationFragment(VariableDeclarationFragment object) {
				return createVariableDeclarationFragmentAdapter();
			}
			@Override
			public Adapter caseQualifiedName(QualifiedName object) {
				return createQualifiedNameAdapter();
			}
			@Override
			public Adapter caseSimpleName(SimpleName object) {
				return createSimpleNameAdapter();
			}
			@Override
			public Adapter caseMarkerAnnotation(MarkerAnnotation object) {
				return createMarkerAnnotationAdapter();
			}
			@Override
			public Adapter caseNormalAnnotation(NormalAnnotation object) {
				return createNormalAnnotationAdapter();
			}
			@Override
			public Adapter caseSingleMemberAnnotation(SingleMemberAnnotation object) {
				return createSingleMemberAnnotationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link DOM.AST <em>AST</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.AST
	 * @generated
	 */
	public Adapter createASTAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ASTNode <em>AST Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ASTNode
	 * @generated
	 */
	public Adapter createASTNodeAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.AnonymousClassDeclaration <em>Anonymous Class Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.AnonymousClassDeclaration
	 * @generated
	 */
	public Adapter createAnonymousClassDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.BodyDeclaration <em>Body Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.BodyDeclaration
	 * @generated
	 */
	public Adapter createBodyDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.CatchClause <em>Catch Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.CatchClause
	 * @generated
	 */
	public Adapter createCatchClauseAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.Comment
	 * @generated
	 */
	public Adapter createCommentAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.CompilationUnit <em>Compilation Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.CompilationUnit
	 * @generated
	 */
	public Adapter createCompilationUnitAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ImportDeclaration <em>Import Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ImportDeclaration
	 * @generated
	 */
	public Adapter createImportDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.MemberRef <em>Member Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.MemberRef
	 * @generated
	 */
	public Adapter createMemberRefAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.MemberValuePair <em>Member Value Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.MemberValuePair
	 * @generated
	 */
	public Adapter createMemberValuePairAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.MethodRef <em>Method Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.MethodRef
	 * @generated
	 */
	public Adapter createMethodRefAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.MethodRefParameter <em>Method Ref Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.MethodRefParameter
	 * @generated
	 */
	public Adapter createMethodRefParameterAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ExtendedModifier <em>Extended Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ExtendedModifier
	 * @generated
	 */
	public Adapter createExtendedModifierAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.Modifier <em>Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.Modifier
	 * @generated
	 */
	public Adapter createModifierAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.PackageDeclaration <em>Package Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.PackageDeclaration
	 * @generated
	 */
	public Adapter createPackageDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.Statement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.Statement
	 * @generated
	 */
	public Adapter createStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.TagElement <em>Tag Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.TagElement
	 * @generated
	 */
	public Adapter createTagElementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.TextElement <em>Text Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.TextElement
	 * @generated
	 */
	public Adapter createTextElementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.Type
	 * @generated
	 */
	public Adapter createTypeAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.TypeParameter <em>Type Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.TypeParameter
	 * @generated
	 */
	public Adapter createTypeParameterAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.VariableDeclaration
	 * @generated
	 */
	public Adapter createVariableDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.AbstractTypeDeclaration <em>Abstract Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.AbstractTypeDeclaration
	 * @generated
	 */
	public Adapter createAbstractTypeDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.AnnotationTypeMemberDeclaration <em>Annotation Type Member Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.AnnotationTypeMemberDeclaration
	 * @generated
	 */
	public Adapter createAnnotationTypeMemberDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.EnumConstantDeclaration <em>Enum Constant Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.EnumConstantDeclaration
	 * @generated
	 */
	public Adapter createEnumConstantDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.FieldDeclaration <em>Field Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.FieldDeclaration
	 * @generated
	 */
	public Adapter createFieldDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.Initializer <em>Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.Initializer
	 * @generated
	 */
	public Adapter createInitializerAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.MethodDeclaration <em>Method Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.MethodDeclaration
	 * @generated
	 */
	public Adapter createMethodDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.AnnotationTypeDeclaration <em>Annotation Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.AnnotationTypeDeclaration
	 * @generated
	 */
	public Adapter createAnnotationTypeDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.EnumDeclaration <em>Enum Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.EnumDeclaration
	 * @generated
	 */
	public Adapter createEnumDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.TypeDeclaration <em>Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.TypeDeclaration
	 * @generated
	 */
	public Adapter createTypeDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.BlockComment <em>Block Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.BlockComment
	 * @generated
	 */
	public Adapter createBlockCommentAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.Javadoc <em>Javadoc</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.Javadoc
	 * @generated
	 */
	public Adapter createJavadocAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.LineComment <em>Line Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.LineComment
	 * @generated
	 */
	public Adapter createLineCommentAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.Annotation
	 * @generated
	 */
	public Adapter createAnnotationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ArrayAccess <em>Array Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ArrayAccess
	 * @generated
	 */
	public Adapter createArrayAccessAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ArrayCreation <em>Array Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ArrayCreation
	 * @generated
	 */
	public Adapter createArrayCreationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ArrayInitializer <em>Array Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ArrayInitializer
	 * @generated
	 */
	public Adapter createArrayInitializerAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.Assignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.Assignment
	 * @generated
	 */
	public Adapter createAssignmentAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.BooleanLiteral
	 * @generated
	 */
	public Adapter createBooleanLiteralAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.CastExpression <em>Cast Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.CastExpression
	 * @generated
	 */
	public Adapter createCastExpressionAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.CharacterLiteral <em>Character Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.CharacterLiteral
	 * @generated
	 */
	public Adapter createCharacterLiteralAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ClassInstanceCreation <em>Class Instance Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ClassInstanceCreation
	 * @generated
	 */
	public Adapter createClassInstanceCreationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ConditionalExpression <em>Conditional Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ConditionalExpression
	 * @generated
	 */
	public Adapter createConditionalExpressionAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.FieldAccess <em>Field Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.FieldAccess
	 * @generated
	 */
	public Adapter createFieldAccessAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.InfixExpression <em>Infix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.InfixExpression
	 * @generated
	 */
	public Adapter createInfixExpressionAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.InstanceofExpression <em>Instanceof Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.InstanceofExpression
	 * @generated
	 */
	public Adapter createInstanceofExpressionAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.MethodInvocation <em>Method Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.MethodInvocation
	 * @generated
	 */
	public Adapter createMethodInvocationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.Name <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.Name
	 * @generated
	 */
	public Adapter createNameAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.NullLiteral <em>Null Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.NullLiteral
	 * @generated
	 */
	public Adapter createNullLiteralAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.NumberLiteral <em>Number Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.NumberLiteral
	 * @generated
	 */
	public Adapter createNumberLiteralAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ParenthesizedExpression <em>Parenthesized Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ParenthesizedExpression
	 * @generated
	 */
	public Adapter createParenthesizedExpressionAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.PostfixExpression <em>Postfix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.PostfixExpression
	 * @generated
	 */
	public Adapter createPostfixExpressionAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.PrefixExpression <em>Prefix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.PrefixExpression
	 * @generated
	 */
	public Adapter createPrefixExpressionAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.StringLiteral
	 * @generated
	 */
	public Adapter createStringLiteralAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.SuperFieldAccess <em>Super Field Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.SuperFieldAccess
	 * @generated
	 */
	public Adapter createSuperFieldAccessAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.SuperMethodInvocation <em>Super Method Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.SuperMethodInvocation
	 * @generated
	 */
	public Adapter createSuperMethodInvocationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ThisExpression <em>This Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ThisExpression
	 * @generated
	 */
	public Adapter createThisExpressionAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.TypeLiteral <em>Type Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.TypeLiteral
	 * @generated
	 */
	public Adapter createTypeLiteralAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.VariableDeclarationExpression <em>Variable Declaration Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.VariableDeclarationExpression
	 * @generated
	 */
	public Adapter createVariableDeclarationExpressionAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.AssertStatement <em>Assert Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.AssertStatement
	 * @generated
	 */
	public Adapter createAssertStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.Block
	 * @generated
	 */
	public Adapter createBlockAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.BreakStatement <em>Break Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.BreakStatement
	 * @generated
	 */
	public Adapter createBreakStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ConstructorInvocation <em>Constructor Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ConstructorInvocation
	 * @generated
	 */
	public Adapter createConstructorInvocationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ContinueStatement <em>Continue Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ContinueStatement
	 * @generated
	 */
	public Adapter createContinueStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.DoStatement <em>Do Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.DoStatement
	 * @generated
	 */
	public Adapter createDoStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.EmptyStatement <em>Empty Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.EmptyStatement
	 * @generated
	 */
	public Adapter createEmptyStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.EnhancedForStatement <em>Enhanced For Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.EnhancedForStatement
	 * @generated
	 */
	public Adapter createEnhancedForStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ExpressionStatement <em>Expression Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ExpressionStatement
	 * @generated
	 */
	public Adapter createExpressionStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ForStatement <em>For Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ForStatement
	 * @generated
	 */
	public Adapter createForStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.IfStatement <em>If Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.IfStatement
	 * @generated
	 */
	public Adapter createIfStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.LabeledStatement <em>Labeled Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.LabeledStatement
	 * @generated
	 */
	public Adapter createLabeledStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ReturnStatement <em>Return Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ReturnStatement
	 * @generated
	 */
	public Adapter createReturnStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.SuperConstructorInvocation <em>Super Constructor Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.SuperConstructorInvocation
	 * @generated
	 */
	public Adapter createSuperConstructorInvocationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.SwitchCase <em>Switch Case</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.SwitchCase
	 * @generated
	 */
	public Adapter createSwitchCaseAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.SwitchStatement <em>Switch Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.SwitchStatement
	 * @generated
	 */
	public Adapter createSwitchStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.SynchronizedStatement <em>Synchronized Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.SynchronizedStatement
	 * @generated
	 */
	public Adapter createSynchronizedStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ThrowStatement <em>Throw Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ThrowStatement
	 * @generated
	 */
	public Adapter createThrowStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.TryStatement <em>Try Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.TryStatement
	 * @generated
	 */
	public Adapter createTryStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.TypeDeclarationStatement <em>Type Declaration Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.TypeDeclarationStatement
	 * @generated
	 */
	public Adapter createTypeDeclarationStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.VariableDeclarationStatement <em>Variable Declaration Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.VariableDeclarationStatement
	 * @generated
	 */
	public Adapter createVariableDeclarationStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.WhileStatement <em>While Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.WhileStatement
	 * @generated
	 */
	public Adapter createWhileStatementAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ArrayType <em>Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ArrayType
	 * @generated
	 */
	public Adapter createArrayTypeAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.ParameterizedType <em>Parameterized Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.ParameterizedType
	 * @generated
	 */
	public Adapter createParameterizedTypeAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.PrimitiveType
	 * @generated
	 */
	public Adapter createPrimitiveTypeAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.QualifiedType <em>Qualified Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.QualifiedType
	 * @generated
	 */
	public Adapter createQualifiedTypeAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.SimpleType <em>Simple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.SimpleType
	 * @generated
	 */
	public Adapter createSimpleTypeAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.WildcardType <em>Wildcard Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.WildcardType
	 * @generated
	 */
	public Adapter createWildcardTypeAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.SingleVariableDeclaration <em>Single Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.SingleVariableDeclaration
	 * @generated
	 */
	public Adapter createSingleVariableDeclarationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.VariableDeclarationFragment <em>Variable Declaration Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.VariableDeclarationFragment
	 * @generated
	 */
	public Adapter createVariableDeclarationFragmentAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.QualifiedName <em>Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.QualifiedName
	 * @generated
	 */
	public Adapter createQualifiedNameAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.SimpleName <em>Simple Name</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.SimpleName
	 * @generated
	 */
	public Adapter createSimpleNameAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.MarkerAnnotation <em>Marker Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.MarkerAnnotation
	 * @generated
	 */
	public Adapter createMarkerAnnotationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.NormalAnnotation <em>Normal Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.NormalAnnotation
	 * @generated
	 */
	public Adapter createNormalAnnotationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for an object of class '{@link DOM.SingleMemberAnnotation <em>Single Member Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see DOM.SingleMemberAnnotation
	 * @generated
	 */
	public Adapter createSingleMemberAnnotationAdapter() {
		return new AdapterImpl(){
		@Override
		public void notifyChanged(Notification msg){			
				if (msg.getEventType()==INeo4emfNotification.GET){
					EObject eObject = (EObject)msg.getNotifier();
					((INeo4emfResource)eObject.eResource()).notifyGet(eObject,(EStructuralFeature)msg.getFeature());
				}
				ChangeLog.getInstance().addNewEntry(msg);
			}
		};
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}
 	
}	
//DOMAdapterFactory