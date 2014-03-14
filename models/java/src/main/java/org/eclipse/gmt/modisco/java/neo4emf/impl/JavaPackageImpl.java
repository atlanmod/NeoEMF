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

import fr.inria.atlanmod.neo4emf.RelationshipMapping;

import java.io.IOException;

import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaFactory;
import org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage;

import org.eclipse.gmt.modisco.java.neo4emf.reltypes.Reltypes;

import org.neo4j.graphdb.RelationshipType;

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
	protected String packageFilename = "java.ecore";

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
	 * @see org.eclipse.gmt.modisco.java.neo4emf.meta.JavaPackage#eNS_URI
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
	 * @generated
	 */
	public static JavaPackage init() {
		if (isInited) return (JavaPackage)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI);

		// Obtain or create and register package
		JavaPackageImpl theJavaPackage = (JavaPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof JavaPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new JavaPackageImpl());

		isInited = true;

		// Load packages
		theJavaPackage.loadPackage();

		// Fix loaded packages
		theJavaPackage.fixPackageContents();

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
		if (abstractMethodDeclarationEClass == null) {
			abstractMethodDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(0);
		}
		return abstractMethodDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_Body() {
        return (EReference)getAbstractMethodDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_Parameters() {
        return (EReference)getAbstractMethodDeclaration().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_ThrownExceptions() {
        return (EReference)getAbstractMethodDeclaration().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_TypeParameters() {
        return (EReference)getAbstractMethodDeclaration().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_UsagesInDocComments() {
        return (EReference)getAbstractMethodDeclaration().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodDeclaration_Usages() {
        return (EReference)getAbstractMethodDeclaration().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractMethodInvocation() {
		if (abstractMethodInvocationEClass == null) {
			abstractMethodInvocationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(1);
		}
		return abstractMethodInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodInvocation_Method() {
        return (EReference)getAbstractMethodInvocation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodInvocation_Arguments() {
        return (EReference)getAbstractMethodInvocation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractMethodInvocation_TypeArguments() {
        return (EReference)getAbstractMethodInvocation().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractTypeDeclaration() {
		if (abstractTypeDeclarationEClass == null) {
			abstractTypeDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(2);
		}
		return abstractTypeDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeDeclaration_BodyDeclarations() {
        return (EReference)getAbstractTypeDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeDeclaration_CommentsBeforeBody() {
        return (EReference)getAbstractTypeDeclaration().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeDeclaration_CommentsAfterBody() {
        return (EReference)getAbstractTypeDeclaration().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeDeclaration_Package() {
        return (EReference)getAbstractTypeDeclaration().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeDeclaration_SuperInterfaces() {
        return (EReference)getAbstractTypeDeclaration().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractTypeQualifiedExpression() {
		if (abstractTypeQualifiedExpressionEClass == null) {
			abstractTypeQualifiedExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(3);
		}
		return abstractTypeQualifiedExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractTypeQualifiedExpression_Qualifier() {
        return (EReference)getAbstractTypeQualifiedExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAbstractVariablesContainer() {
		if (abstractVariablesContainerEClass == null) {
			abstractVariablesContainerEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(4);
		}
		return abstractVariablesContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractVariablesContainer_Type() {
        return (EReference)getAbstractVariablesContainer().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAbstractVariablesContainer_Fragments() {
        return (EReference)getAbstractVariablesContainer().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotation() {
		if (annotationEClass == null) {
			annotationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(5);
		}
		return annotationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotation_Type() {
        return (EReference)getAnnotation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotation_Values() {
        return (EReference)getAnnotation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArchive() {
		if (archiveEClass == null) {
			archiveEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(6);
		}
		return archiveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArchive_OriginalFilePath() {
        return (EAttribute)getArchive().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchive_ClassFiles() {
        return (EReference)getArchive().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArchive_Manifest() {
        return (EReference)getArchive().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssertStatement() {
		if (assertStatementEClass == null) {
			assertStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(7);
		}
		return assertStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssertStatement_Message() {
        return (EReference)getAssertStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssertStatement_Expression() {
        return (EReference)getAssertStatement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getASTNode() {
		if (astNodeEClass == null) {
			astNodeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(8);
		}
		return astNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASTNode_Comments() {
        return (EReference)getASTNode().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASTNode_OriginalCompilationUnit() {
        return (EReference)getASTNode().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASTNode_OriginalClassFile() {
        return (EReference)getASTNode().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotationMemberValuePair() {
		if (annotationMemberValuePairEClass == null) {
			annotationMemberValuePairEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(9);
		}
		return annotationMemberValuePairEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotationMemberValuePair_Member() {
        return (EReference)getAnnotationMemberValuePair().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotationMemberValuePair_Value() {
        return (EReference)getAnnotationMemberValuePair().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotationTypeDeclaration() {
		if (annotationTypeDeclarationEClass == null) {
			annotationTypeDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(10);
		}
		return annotationTypeDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotationTypeMemberDeclaration() {
		if (annotationTypeMemberDeclarationEClass == null) {
			annotationTypeMemberDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(11);
		}
		return annotationTypeMemberDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotationTypeMemberDeclaration_Default() {
        return (EReference)getAnnotationTypeMemberDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotationTypeMemberDeclaration_Type() {
        return (EReference)getAnnotationTypeMemberDeclaration().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotationTypeMemberDeclaration_Usages() {
        return (EReference)getAnnotationTypeMemberDeclaration().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnonymousClassDeclaration() {
		if (anonymousClassDeclarationEClass == null) {
			anonymousClassDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(12);
		}
		return anonymousClassDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnonymousClassDeclaration_BodyDeclarations() {
        return (EReference)getAnonymousClassDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnonymousClassDeclaration_ClassInstanceCreation() {
        return (EReference)getAnonymousClassDeclaration().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayAccess() {
		if (arrayAccessEClass == null) {
			arrayAccessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(13);
		}
		return arrayAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayAccess_Array() {
        return (EReference)getArrayAccess().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayAccess_Index() {
        return (EReference)getArrayAccess().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayCreation() {
		if (arrayCreationEClass == null) {
			arrayCreationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(14);
		}
		return arrayCreationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayCreation_Dimensions() {
        return (EReference)getArrayCreation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayCreation_Initializer() {
        return (EReference)getArrayCreation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayCreation_Type() {
        return (EReference)getArrayCreation().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayInitializer() {
		if (arrayInitializerEClass == null) {
			arrayInitializerEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(15);
		}
		return arrayInitializerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayInitializer_Expressions() {
        return (EReference)getArrayInitializer().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayLengthAccess() {
		if (arrayLengthAccessEClass == null) {
			arrayLengthAccessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(16);
		}
		return arrayLengthAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayLengthAccess_Array() {
        return (EReference)getArrayLengthAccess().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayType() {
		if (arrayTypeEClass == null) {
			arrayTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(17);
		}
		return arrayTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayType_Dimensions() {
        return (EAttribute)getArrayType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayType_ElementType() {
        return (EReference)getArrayType().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssignment() {
		if (assignmentEClass == null) {
			assignmentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(18);
		}
		return assignmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssignment_LeftHandSide() {
        return (EReference)getAssignment().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssignment_Operator() {
        return (EAttribute)getAssignment().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssignment_RightHandSide() {
        return (EReference)getAssignment().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBodyDeclaration() {
		if (bodyDeclarationEClass == null) {
			bodyDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(20);
		}
		return bodyDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBodyDeclaration_AbstractTypeDeclaration() {
        return (EReference)getBodyDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBodyDeclaration_Annotations() {
        return (EReference)getBodyDeclaration().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBodyDeclaration_AnonymousClassDeclarationOwner() {
        return (EReference)getBodyDeclaration().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBodyDeclaration_Modifier() {
        return (EReference)getBodyDeclaration().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanLiteral() {
		if (booleanLiteralEClass == null) {
			booleanLiteralEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(21);
		}
		return booleanLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanLiteral_Value() {
        return (EAttribute)getBooleanLiteral().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlockComment() {
		if (blockCommentEClass == null) {
			blockCommentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(22);
		}
		return blockCommentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlock() {
		if (blockEClass == null) {
			blockEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(23);
		}
		return blockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBlock_Statements() {
        return (EReference)getBlock().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBreakStatement() {
		if (breakStatementEClass == null) {
			breakStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(24);
		}
		return breakStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBreakStatement_Label() {
        return (EReference)getBreakStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCastExpression() {
		if (castExpressionEClass == null) {
			castExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(25);
		}
		return castExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCastExpression_Expression() {
        return (EReference)getCastExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCastExpression_Type() {
        return (EReference)getCastExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCatchClause() {
		if (catchClauseEClass == null) {
			catchClauseEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(26);
		}
		return catchClauseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCatchClause_Exception() {
        return (EReference)getCatchClause().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCatchClause_Body() {
        return (EReference)getCatchClause().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCharacterLiteral() {
		if (characterLiteralEClass == null) {
			characterLiteralEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(27);
		}
		return characterLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterLiteral_EscapedValue() {
        return (EAttribute)getCharacterLiteral().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassFile() {
		if (classFileEClass == null) {
			classFileEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(28);
		}
		return classFileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassFile_OriginalFilePath() {
        return (EAttribute)getClassFile().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFile_Type() {
        return (EReference)getClassFile().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFile_AttachedSource() {
        return (EReference)getClassFile().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassFile_Package() {
        return (EReference)getClassFile().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassInstanceCreation() {
		if (classInstanceCreationEClass == null) {
			classInstanceCreationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(29);
		}
		return classInstanceCreationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassInstanceCreation_AnonymousClassDeclaration() {
        return (EReference)getClassInstanceCreation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassInstanceCreation_Expression() {
        return (EReference)getClassInstanceCreation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassInstanceCreation_Type() {
        return (EReference)getClassInstanceCreation().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstructorDeclaration() {
		if (constructorDeclarationEClass == null) {
			constructorDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(30);
		}
		return constructorDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionalExpression() {
		if (conditionalExpressionEClass == null) {
			conditionalExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(31);
		}
		return conditionalExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalExpression_ElseExpression() {
        return (EReference)getConditionalExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalExpression_Expression() {
        return (EReference)getConditionalExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalExpression_ThenExpression() {
        return (EReference)getConditionalExpression().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstructorInvocation() {
		if (constructorInvocationEClass == null) {
			constructorInvocationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(32);
		}
		return constructorInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassDeclaration() {
		if (classDeclarationEClass == null) {
			classDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(33);
		}
		return classDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassDeclaration_SuperClass() {
        return (EReference)getClassDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComment() {
		if (commentEClass == null) {
			commentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(34);
		}
		return commentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Content() {
        return (EAttribute)getComment().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_EnclosedByParent() {
        return (EAttribute)getComment().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_PrefixOfParent() {
        return (EAttribute)getComment().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompilationUnit() {
		if (compilationUnitEClass == null) {
			compilationUnitEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(35);
		}
		return compilationUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCompilationUnit_OriginalFilePath() {
        return (EAttribute)getCompilationUnit().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationUnit_CommentList() {
        return (EReference)getCompilationUnit().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationUnit_Imports() {
        return (EReference)getCompilationUnit().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationUnit_Package() {
        return (EReference)getCompilationUnit().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompilationUnit_Types() {
        return (EReference)getCompilationUnit().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContinueStatement() {
		if (continueStatementEClass == null) {
			continueStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(36);
		}
		return continueStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContinueStatement_Label() {
        return (EReference)getContinueStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDoStatement() {
		if (doStatementEClass == null) {
			doStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(37);
		}
		return doStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDoStatement_Expression() {
        return (EReference)getDoStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDoStatement_Body() {
        return (EReference)getDoStatement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEmptyStatement() {
		if (emptyStatementEClass == null) {
			emptyStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(38);
		}
		return emptyStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnhancedForStatement() {
		if (enhancedForStatementEClass == null) {
			enhancedForStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(39);
		}
		return enhancedForStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnhancedForStatement_Body() {
        return (EReference)getEnhancedForStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnhancedForStatement_Expression() {
        return (EReference)getEnhancedForStatement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnhancedForStatement_Parameter() {
        return (EReference)getEnhancedForStatement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumConstantDeclaration() {
		if (enumConstantDeclarationEClass == null) {
			enumConstantDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(40);
		}
		return enumConstantDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumConstantDeclaration_AnonymousClassDeclaration() {
        return (EReference)getEnumConstantDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumConstantDeclaration_Arguments() {
        return (EReference)getEnumConstantDeclaration().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumDeclaration() {
		if (enumDeclarationEClass == null) {
			enumDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(41);
		}
		return enumDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEnumDeclaration_EnumConstants() {
        return (EReference)getEnumDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpression() {
		if (expressionEClass == null) {
			expressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(42);
		}
		return expressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpressionStatement() {
		if (expressionStatementEClass == null) {
			expressionStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(43);
		}
		return expressionStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExpressionStatement_Expression() {
        return (EReference)getExpressionStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFieldAccess() {
		if (fieldAccessEClass == null) {
			fieldAccessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(44);
		}
		return fieldAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFieldAccess_Field() {
        return (EReference)getFieldAccess().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFieldAccess_Expression() {
        return (EReference)getFieldAccess().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFieldDeclaration() {
		if (fieldDeclarationEClass == null) {
			fieldDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(45);
		}
		return fieldDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getForStatement() {
		if (forStatementEClass == null) {
			forStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(46);
		}
		return forStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForStatement_Expression() {
        return (EReference)getForStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForStatement_Updaters() {
        return (EReference)getForStatement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForStatement_Initializers() {
        return (EReference)getForStatement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getForStatement_Body() {
        return (EReference)getForStatement().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIfStatement() {
		if (ifStatementEClass == null) {
			ifStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(47);
		}
		return ifStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfStatement_Expression() {
        return (EReference)getIfStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfStatement_ThenStatement() {
        return (EReference)getIfStatement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIfStatement_ElseStatement() {
        return (EReference)getIfStatement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImportDeclaration() {
		if (importDeclarationEClass == null) {
			importDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(48);
		}
		return importDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImportDeclaration_Static() {
        return (EAttribute)getImportDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImportDeclaration_ImportedElement() {
        return (EReference)getImportDeclaration().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInfixExpression() {
		if (infixExpressionEClass == null) {
			infixExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(49);
		}
		return infixExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInfixExpression_Operator() {
        return (EAttribute)getInfixExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfixExpression_RightOperand() {
        return (EReference)getInfixExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfixExpression_LeftOperand() {
        return (EReference)getInfixExpression().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInfixExpression_ExtendedOperands() {
        return (EReference)getInfixExpression().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInitializer() {
		if (initializerEClass == null) {
			initializerEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(52);
		}
		return initializerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInitializer_Body() {
        return (EReference)getInitializer().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstanceofExpression() {
		if (instanceofExpressionEClass == null) {
			instanceofExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(53);
		}
		return instanceofExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanceofExpression_RightOperand() {
        return (EReference)getInstanceofExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstanceofExpression_LeftOperand() {
        return (EReference)getInstanceofExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterfaceDeclaration() {
		if (interfaceDeclarationEClass == null) {
			interfaceDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(54);
		}
		return interfaceDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavadoc() {
		if (javadocEClass == null) {
			javadocEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(55);
		}
		return javadocEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJavadoc_Tags() {
        return (EReference)getJavadoc().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLabeledStatement() {
		if (labeledStatementEClass == null) {
			labeledStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(56);
		}
		return labeledStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLabeledStatement_Body() {
        return (EReference)getLabeledStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLabeledStatement_UsagesInBreakStatements() {
        return (EReference)getLabeledStatement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLabeledStatement_UsagesInContinueStatements() {
        return (EReference)getLabeledStatement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLineComment() {
		if (lineCommentEClass == null) {
			lineCommentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(57);
		}
		return lineCommentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManifest() {
		if (manifestEClass == null) {
			manifestEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(58);
		}
		return manifestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getManifest_MainAttributes() {
        return (EReference)getManifest().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getManifest_EntryAttributes() {
        return (EReference)getManifest().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManifestAttribute() {
		if (manifestAttributeEClass == null) {
			manifestAttributeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(59);
		}
		return manifestAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManifestAttribute_Key() {
        return (EAttribute)getManifestAttribute().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManifestAttribute_Value() {
        return (EAttribute)getManifestAttribute().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getManifestEntry() {
		if (manifestEntryEClass == null) {
			manifestEntryEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(60);
		}
		return manifestEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getManifestEntry_Name() {
        return (EAttribute)getManifestEntry().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getManifestEntry_Attributes() {
        return (EReference)getManifestEntry().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMemberRef() {
		if (memberRefEClass == null) {
			memberRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(61);
		}
		return memberRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberRef_Member() {
        return (EReference)getMemberRef().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMemberRef_Qualifier() {
        return (EReference)getMemberRef().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodDeclaration() {
		if (methodDeclarationEClass == null) {
			methodDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(62);
		}
		return methodDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodDeclaration_ExtraArrayDimensions() {
        return (EAttribute)getMethodDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodDeclaration_ReturnType() {
        return (EReference)getMethodDeclaration().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodDeclaration_RedefinedMethodDeclaration() {
        return (EReference)getMethodDeclaration().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodDeclaration_Redefinitions() {
        return (EReference)getMethodDeclaration().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodInvocation() {
		if (methodInvocationEClass == null) {
			methodInvocationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(63);
		}
		return methodInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodInvocation_Expression() {
        return (EReference)getMethodInvocation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodRef() {
		if (methodRefEClass == null) {
			methodRefEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(64);
		}
		return methodRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodRef_Method() {
        return (EReference)getMethodRef().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodRef_Qualifier() {
        return (EReference)getMethodRef().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodRef_Parameters() {
        return (EReference)getMethodRef().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodRefParameter() {
		if (methodRefParameterEClass == null) {
			methodRefParameterEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(65);
		}
		return methodRefParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodRefParameter_Name() {
        return (EAttribute)getMethodRefParameter().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodRefParameter_Varargs() {
        return (EAttribute)getMethodRefParameter().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodRefParameter_Type() {
        return (EReference)getMethodRefParameter().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModel() {
		if (modelEClass == null) {
			modelEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(66);
		}
		return modelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModel_Name() {
        return (EAttribute)getModel().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_OwnedElements() {
        return (EReference)getModel().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_OrphanTypes() {
        return (EReference)getModel().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_UnresolvedItems() {
        return (EReference)getModel().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_CompilationUnits() {
        return (EReference)getModel().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_ClassFiles() {
        return (EReference)getModel().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModel_Archives() {
        return (EReference)getModel().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModifier() {
		if (modifierEClass == null) {
			modifierEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(67);
		}
		return modifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Visibility() {
        return (EAttribute)getModifier().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Inheritance() {
        return (EAttribute)getModifier().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Static() {
        return (EAttribute)getModifier().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Transient() {
        return (EAttribute)getModifier().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Volatile() {
        return (EAttribute)getModifier().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Native() {
        return (EAttribute)getModifier().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Strictfp() {
        return (EAttribute)getModifier().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getModifier_Synchronized() {
        return (EAttribute)getModifier().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModifier_BodyDeclaration() {
        return (EReference)getModifier().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModifier_SingleVariableDeclaration() {
        return (EReference)getModifier().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModifier_VariableDeclarationStatement() {
        return (EReference)getModifier().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModifier_VariableDeclarationExpression() {
        return (EReference)getModifier().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		if (namedElementEClass == null) {
			namedElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(68);
		}
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
        return (EAttribute)getNamedElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Proxy() {
        return (EAttribute)getNamedElement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamedElement_UsagesInImports() {
        return (EReference)getNamedElement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamespaceAccess() {
		if (namespaceAccessEClass == null) {
			namespaceAccessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(69);
		}
		return namespaceAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumberLiteral() {
		if (numberLiteralEClass == null) {
			numberLiteralEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(70);
		}
		return numberLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumberLiteral_TokenValue() {
        return (EAttribute)getNumberLiteral().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNullLiteral() {
		if (nullLiteralEClass == null) {
			nullLiteralEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(71);
		}
		return nullLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackage() {
		if (packageEClass == null) {
			packageEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(72);
		}
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_OwnedElements() {
        return (EReference)getPackage().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_Model() {
        return (EReference)getPackage().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_OwnedPackages() {
        return (EReference)getPackage().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_Package() {
        return (EReference)getPackage().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_UsagesInPackageAccess() {
        return (EReference)getPackage().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageAccess() {
		if (packageAccessEClass == null) {
			packageAccessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(73);
		}
		return packageAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageAccess_Package() {
        return (EReference)getPackageAccess().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageAccess_Qualifier() {
        return (EReference)getPackageAccess().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterizedType() {
		if (parameterizedTypeEClass == null) {
			parameterizedTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(74);
		}
		return parameterizedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterizedType_Type() {
        return (EReference)getParameterizedType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterizedType_TypeArguments() {
        return (EReference)getParameterizedType().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParenthesizedExpression() {
		if (parenthesizedExpressionEClass == null) {
			parenthesizedExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(75);
		}
		return parenthesizedExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParenthesizedExpression_Expression() {
        return (EReference)getParenthesizedExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPostfixExpression() {
		if (postfixExpressionEClass == null) {
			postfixExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(76);
		}
		return postfixExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPostfixExpression_Operator() {
        return (EAttribute)getPostfixExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPostfixExpression_Operand() {
        return (EReference)getPostfixExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrefixExpression() {
		if (prefixExpressionEClass == null) {
			prefixExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(78);
		}
		return prefixExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPrefixExpression_Operator() {
        return (EAttribute)getPrefixExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPrefixExpression_Operand() {
        return (EReference)getPrefixExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveType() {
		if (primitiveTypeEClass == null) {
			primitiveTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(80);
		}
		return primitiveTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeBoolean() {
		if (primitiveTypeBooleanEClass == null) {
			primitiveTypeBooleanEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(81);
		}
		return primitiveTypeBooleanEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeByte() {
		if (primitiveTypeByteEClass == null) {
			primitiveTypeByteEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(82);
		}
		return primitiveTypeByteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeChar() {
		if (primitiveTypeCharEClass == null) {
			primitiveTypeCharEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(83);
		}
		return primitiveTypeCharEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeDouble() {
		if (primitiveTypeDoubleEClass == null) {
			primitiveTypeDoubleEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(84);
		}
		return primitiveTypeDoubleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeShort() {
		if (primitiveTypeShortEClass == null) {
			primitiveTypeShortEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(85);
		}
		return primitiveTypeShortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeFloat() {
		if (primitiveTypeFloatEClass == null) {
			primitiveTypeFloatEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(86);
		}
		return primitiveTypeFloatEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeInt() {
		if (primitiveTypeIntEClass == null) {
			primitiveTypeIntEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(87);
		}
		return primitiveTypeIntEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeLong() {
		if (primitiveTypeLongEClass == null) {
			primitiveTypeLongEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(88);
		}
		return primitiveTypeLongEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimitiveTypeVoid() {
		if (primitiveTypeVoidEClass == null) {
			primitiveTypeVoidEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(89);
		}
		return primitiveTypeVoidEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReturnStatement() {
		if (returnStatementEClass == null) {
			returnStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(90);
		}
		return returnStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReturnStatement_Expression() {
        return (EReference)getReturnStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleVariableAccess() {
		if (singleVariableAccessEClass == null) {
			singleVariableAccessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(91);
		}
		return singleVariableAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableAccess_Variable() {
        return (EReference)getSingleVariableAccess().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableAccess_Qualifier() {
        return (EReference)getSingleVariableAccess().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleVariableDeclaration() {
		if (singleVariableDeclarationEClass == null) {
			singleVariableDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(92);
		}
		return singleVariableDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_Modifier() {
        return (EReference)getSingleVariableDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSingleVariableDeclaration_Varargs() {
        return (EAttribute)getSingleVariableDeclaration().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_Type() {
        return (EReference)getSingleVariableDeclaration().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_Annotations() {
        return (EReference)getSingleVariableDeclaration().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_MethodDeclaration() {
        return (EReference)getSingleVariableDeclaration().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_CatchClause() {
        return (EReference)getSingleVariableDeclaration().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleVariableDeclaration_EnhancedForStatement() {
        return (EReference)getSingleVariableDeclaration().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStatement() {
		if (statementEClass == null) {
			statementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(93);
		}
		return statementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringLiteral() {
		if (stringLiteralEClass == null) {
			stringLiteralEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(94);
		}
		return stringLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringLiteral_EscapedValue() {
        return (EAttribute)getStringLiteral().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSuperConstructorInvocation() {
		if (superConstructorInvocationEClass == null) {
			superConstructorInvocationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(95);
		}
		return superConstructorInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperConstructorInvocation_Expression() {
        return (EReference)getSuperConstructorInvocation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSuperFieldAccess() {
		if (superFieldAccessEClass == null) {
			superFieldAccessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(96);
		}
		return superFieldAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSuperFieldAccess_Field() {
        return (EReference)getSuperFieldAccess().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSuperMethodInvocation() {
		if (superMethodInvocationEClass == null) {
			superMethodInvocationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(97);
		}
		return superMethodInvocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwitchCase() {
		if (switchCaseEClass == null) {
			switchCaseEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(98);
		}
		return switchCaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSwitchCase_Default() {
        return (EAttribute)getSwitchCase().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchCase_Expression() {
        return (EReference)getSwitchCase().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSwitchStatement() {
		if (switchStatementEClass == null) {
			switchStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(99);
		}
		return switchStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchStatement_Expression() {
        return (EReference)getSwitchStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSwitchStatement_Statements() {
        return (EReference)getSwitchStatement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSynchronizedStatement() {
		if (synchronizedStatementEClass == null) {
			synchronizedStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(100);
		}
		return synchronizedStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSynchronizedStatement_Body() {
        return (EReference)getSynchronizedStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSynchronizedStatement_Expression() {
        return (EReference)getSynchronizedStatement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTagElement() {
		if (tagElementEClass == null) {
			tagElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(101);
		}
		return tagElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTagElement_TagName() {
        return (EAttribute)getTagElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTagElement_Fragments() {
        return (EReference)getTagElement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTextElement() {
		if (textElementEClass == null) {
			textElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(102);
		}
		return textElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTextElement_Text() {
        return (EAttribute)getTextElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThisExpression() {
		if (thisExpressionEClass == null) {
			thisExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(103);
		}
		return thisExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThrowStatement() {
		if (throwStatementEClass == null) {
			throwStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(104);
		}
		return throwStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getThrowStatement_Expression() {
        return (EReference)getThrowStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTryStatement() {
		if (tryStatementEClass == null) {
			tryStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(105);
		}
		return tryStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTryStatement_Body() {
        return (EReference)getTryStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTryStatement_Finally() {
        return (EReference)getTryStatement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTryStatement_CatchClauses() {
        return (EReference)getTryStatement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getType() {
		if (typeEClass == null) {
			typeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(106);
		}
		return typeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getType_UsagesInTypeAccess() {
        return (EReference)getType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeAccess() {
		if (typeAccessEClass == null) {
			typeAccessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(107);
		}
		return typeAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeAccess_Type() {
        return (EReference)getTypeAccess().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeAccess_Qualifier() {
        return (EReference)getTypeAccess().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeDeclaration() {
		if (typeDeclarationEClass == null) {
			typeDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(108);
		}
		return typeDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclaration_TypeParameters() {
        return (EReference)getTypeDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeDeclarationStatement() {
		if (typeDeclarationStatementEClass == null) {
			typeDeclarationStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(109);
		}
		return typeDeclarationStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeDeclarationStatement_Declaration() {
        return (EReference)getTypeDeclarationStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeLiteral() {
		if (typeLiteralEClass == null) {
			typeLiteralEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(110);
		}
		return typeLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeLiteral_Type() {
        return (EReference)getTypeLiteral().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTypeParameter() {
		if (typeParameterEClass == null) {
			typeParameterEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(111);
		}
		return typeParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTypeParameter_Bounds() {
        return (EReference)getTypeParameter().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedItem() {
		if (unresolvedItemEClass == null) {
			unresolvedItemEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(112);
		}
		return unresolvedItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedItemAccess() {
		if (unresolvedItemAccessEClass == null) {
			unresolvedItemAccessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(113);
		}
		return unresolvedItemAccessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnresolvedItemAccess_Element() {
        return (EReference)getUnresolvedItemAccess().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnresolvedItemAccess_Qualifier() {
        return (EReference)getUnresolvedItemAccess().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedAnnotationDeclaration() {
		if (unresolvedAnnotationDeclarationEClass == null) {
			unresolvedAnnotationDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(114);
		}
		return unresolvedAnnotationDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedAnnotationTypeMemberDeclaration() {
		if (unresolvedAnnotationTypeMemberDeclarationEClass == null) {
			unresolvedAnnotationTypeMemberDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(115);
		}
		return unresolvedAnnotationTypeMemberDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedClassDeclaration() {
		if (unresolvedClassDeclarationEClass == null) {
			unresolvedClassDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(116);
		}
		return unresolvedClassDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedEnumDeclaration() {
		if (unresolvedEnumDeclarationEClass == null) {
			unresolvedEnumDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(117);
		}
		return unresolvedEnumDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedInterfaceDeclaration() {
		if (unresolvedInterfaceDeclarationEClass == null) {
			unresolvedInterfaceDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(118);
		}
		return unresolvedInterfaceDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedLabeledStatement() {
		if (unresolvedLabeledStatementEClass == null) {
			unresolvedLabeledStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(119);
		}
		return unresolvedLabeledStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedMethodDeclaration() {
		if (unresolvedMethodDeclarationEClass == null) {
			unresolvedMethodDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(120);
		}
		return unresolvedMethodDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedSingleVariableDeclaration() {
		if (unresolvedSingleVariableDeclarationEClass == null) {
			unresolvedSingleVariableDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(121);
		}
		return unresolvedSingleVariableDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedType() {
		if (unresolvedTypeEClass == null) {
			unresolvedTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(122);
		}
		return unresolvedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedTypeDeclaration() {
		if (unresolvedTypeDeclarationEClass == null) {
			unresolvedTypeDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(123);
		}
		return unresolvedTypeDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnresolvedVariableDeclarationFragment() {
		if (unresolvedVariableDeclarationFragmentEClass == null) {
			unresolvedVariableDeclarationFragmentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(124);
		}
		return unresolvedVariableDeclarationFragmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableDeclaration() {
		if (variableDeclarationEClass == null) {
			variableDeclarationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(125);
		}
		return variableDeclarationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableDeclaration_ExtraArrayDimensions() {
        return (EAttribute)getVariableDeclaration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclaration_Initializer() {
        return (EReference)getVariableDeclaration().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclaration_UsageInVariableAccess() {
        return (EReference)getVariableDeclaration().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableDeclarationExpression() {
		if (variableDeclarationExpressionEClass == null) {
			variableDeclarationExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(126);
		}
		return variableDeclarationExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclarationExpression_Modifier() {
        return (EReference)getVariableDeclarationExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclarationExpression_Annotations() {
        return (EReference)getVariableDeclarationExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableDeclarationFragment() {
		if (variableDeclarationFragmentEClass == null) {
			variableDeclarationFragmentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(127);
		}
		return variableDeclarationFragmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclarationFragment_VariablesContainer() {
        return (EReference)getVariableDeclarationFragment().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVariableDeclarationStatement() {
		if (variableDeclarationStatementEClass == null) {
			variableDeclarationStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(128);
		}
		return variableDeclarationStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVariableDeclarationStatement_ExtraArrayDimensions() {
        return (EAttribute)getVariableDeclarationStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclarationStatement_Modifier() {
        return (EReference)getVariableDeclarationStatement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVariableDeclarationStatement_Annotations() {
        return (EReference)getVariableDeclarationStatement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWildCardType() {
		if (wildCardTypeEClass == null) {
			wildCardTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(130);
		}
		return wildCardTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWildCardType_UpperBound() {
        return (EAttribute)getWildCardType().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWildCardType_Bound() {
        return (EReference)getWildCardType().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWhileStatement() {
		if (whileStatementEClass == null) {
			whileStatementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(131);
		}
		return whileStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWhileStatement_Expression() {
        return (EReference)getWhileStatement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWhileStatement_Body() {
        return (EReference)getWhileStatement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAssignmentKind() {
		if (assignmentKindEEnum == null) {
			assignmentKindEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(19);
		}
		return assignmentKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getInfixExpressionKind() {
		if (infixExpressionKindEEnum == null) {
			infixExpressionKindEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(50);
		}
		return infixExpressionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getInheritanceKind() {
		if (inheritanceKindEEnum == null) {
			inheritanceKindEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(51);
		}
		return inheritanceKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPostfixExpressionKind() {
		if (postfixExpressionKindEEnum == null) {
			postfixExpressionKindEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(77);
		}
		return postfixExpressionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPrefixExpressionKind() {
		if (prefixExpressionKindEEnum == null) {
			prefixExpressionKindEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(79);
		}
		return prefixExpressionKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getVisibilityKind() {
		if (visibilityKindEEnum == null) {
			visibilityKindEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(JavaPackage.eNS_URI).getEClassifiers().get(129);
		}
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
	private boolean isLoaded = false;

	/**
	 * Laods the package and any sub-packages from their serialized form.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void loadPackage() {
		if (isLoaded) return;
		isLoaded = true;

		URL url = getClass().getResource(packageFilename);
		if (url == null) {
			throw new RuntimeException("Missing serialized package: " + packageFilename);
		}
		URI uri = URI.createURI(url.toString());
		Resource resource = new EcoreResourceFactoryImpl().createResource(uri);
		try {
			resource.load(null);
		}
		catch (IOException exception) {
			throw new WrappedException(exception);
		}
		initializeFromLoadedEPackage(this, (EPackage)resource.getContents().get(0));
		createResource(eNS_URI);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isFixed = false;

	/**
	 * Fixes up the loaded package, to make it appear as if it had been programmatically built.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fixPackageContents() {
		if (isFixed) return;
		isFixed = true;
		fixEClassifiers();
	}

	/**
	 * Sets the instance class on the given classifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void fixInstanceClass(EClassifier eClassifier) {
		if (eClassifier.getInstanceClassName() == null) {
			eClassifier.setInstanceClassName("org.eclipse.gmt.modisco.java." + eClassifier.getName());
			setGeneratedClassName(eClassifier);
		}
	}

/* Neo4EMF Inserted Code -- Begin*/

private RelationshipMapping relationshipMapping = new JavaPackageRelationshipMapping();

@Override
public RelationshipMapping getRelationshipMapping() {
	return relationshipMapping;
}

class JavaPackageRelationshipMapping implements RelationshipMapping {

	private RelationshipType[][] mapping = new RelationshipType[132][];

	public JavaPackageRelationshipMapping() {
	
		mapping[AST_NODE] = new RelationshipType[3];
		mapping[AST_NODE][AST_NODE__COMMENTS] = Reltypes.AST_NODE__COMMENTS;
		mapping[AST_NODE][AST_NODE__ORIGINAL_COMPILATION_UNIT] = Reltypes.AST_NODE__ORIGINAL_COMPILATION_UNIT;
		mapping[AST_NODE][AST_NODE__ORIGINAL_CLASS_FILE] = Reltypes.AST_NODE__ORIGINAL_CLASS_FILE;
		mapping[NAMED_ELEMENT] = new RelationshipType[6];
		mapping[NAMED_ELEMENT][NAMED_ELEMENT__USAGES_IN_IMPORTS] = Reltypes.NAMED_ELEMENT__USAGES_IN_IMPORTS;
		mapping[BODY_DECLARATION] = new RelationshipType[10];
		mapping[BODY_DECLARATION][BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION] = Reltypes.BODY_DECLARATION__ABSTRACT_TYPE_DECLARATION;
		mapping[BODY_DECLARATION][BODY_DECLARATION__ANNOTATIONS] = Reltypes.BODY_DECLARATION__ANNOTATIONS;
		mapping[BODY_DECLARATION][BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER] = Reltypes.BODY_DECLARATION__ANONYMOUS_CLASS_DECLARATION_OWNER;
		mapping[BODY_DECLARATION][BODY_DECLARATION__MODIFIER] = Reltypes.BODY_DECLARATION__MODIFIER;
		mapping[ABSTRACT_METHOD_DECLARATION] = new RelationshipType[16];
		mapping[ABSTRACT_METHOD_DECLARATION][ABSTRACT_METHOD_DECLARATION__BODY] = Reltypes.ABSTRACT_METHOD_DECLARATION__BODY;
		mapping[ABSTRACT_METHOD_DECLARATION][ABSTRACT_METHOD_DECLARATION__PARAMETERS] = Reltypes.ABSTRACT_METHOD_DECLARATION__PARAMETERS;
		mapping[ABSTRACT_METHOD_DECLARATION][ABSTRACT_METHOD_DECLARATION__THROWN_EXCEPTIONS] = Reltypes.ABSTRACT_METHOD_DECLARATION__THROWN_EXCEPTIONS;
		mapping[ABSTRACT_METHOD_DECLARATION][ABSTRACT_METHOD_DECLARATION__TYPE_PARAMETERS] = Reltypes.ABSTRACT_METHOD_DECLARATION__TYPE_PARAMETERS;
		mapping[ABSTRACT_METHOD_DECLARATION][ABSTRACT_METHOD_DECLARATION__USAGES_IN_DOC_COMMENTS] = Reltypes.ABSTRACT_METHOD_DECLARATION__USAGES_IN_DOC_COMMENTS;
		mapping[ABSTRACT_METHOD_DECLARATION][ABSTRACT_METHOD_DECLARATION__USAGES] = Reltypes.ABSTRACT_METHOD_DECLARATION__USAGES;
		mapping[ABSTRACT_METHOD_INVOCATION] = new RelationshipType[6];
		mapping[ABSTRACT_METHOD_INVOCATION][ABSTRACT_METHOD_INVOCATION__METHOD] = Reltypes.ABSTRACT_METHOD_INVOCATION__METHOD;
		mapping[ABSTRACT_METHOD_INVOCATION][ABSTRACT_METHOD_INVOCATION__ARGUMENTS] = Reltypes.ABSTRACT_METHOD_INVOCATION__ARGUMENTS;
		mapping[ABSTRACT_METHOD_INVOCATION][ABSTRACT_METHOD_INVOCATION__TYPE_ARGUMENTS] = Reltypes.ABSTRACT_METHOD_INVOCATION__TYPE_ARGUMENTS;
		mapping[ABSTRACT_TYPE_DECLARATION] = new RelationshipType[16];
		mapping[ABSTRACT_TYPE_DECLARATION][ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS] = Reltypes.ABSTRACT_TYPE_DECLARATION__USAGES_IN_TYPE_ACCESS;
		mapping[ABSTRACT_TYPE_DECLARATION][ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS] = Reltypes.ABSTRACT_TYPE_DECLARATION__BODY_DECLARATIONS;
		mapping[ABSTRACT_TYPE_DECLARATION][ABSTRACT_TYPE_DECLARATION__COMMENTS_BEFORE_BODY] = Reltypes.ABSTRACT_TYPE_DECLARATION__COMMENTS_BEFORE_BODY;
		mapping[ABSTRACT_TYPE_DECLARATION][ABSTRACT_TYPE_DECLARATION__COMMENTS_AFTER_BODY] = Reltypes.ABSTRACT_TYPE_DECLARATION__COMMENTS_AFTER_BODY;
		mapping[ABSTRACT_TYPE_DECLARATION][ABSTRACT_TYPE_DECLARATION__PACKAGE] = Reltypes.ABSTRACT_TYPE_DECLARATION__PACKAGE;
		mapping[ABSTRACT_TYPE_DECLARATION][ABSTRACT_TYPE_DECLARATION__SUPER_INTERFACES] = Reltypes.ABSTRACT_TYPE_DECLARATION__SUPER_INTERFACES;
		mapping[EXPRESSION] = new RelationshipType[3];
		mapping[ABSTRACT_TYPE_QUALIFIED_EXPRESSION] = new RelationshipType[4];
		mapping[ABSTRACT_TYPE_QUALIFIED_EXPRESSION][ABSTRACT_TYPE_QUALIFIED_EXPRESSION__QUALIFIER] = Reltypes.ABSTRACT_TYPE_QUALIFIED_EXPRESSION__QUALIFIER;
		mapping[ABSTRACT_VARIABLES_CONTAINER] = new RelationshipType[5];
		mapping[ABSTRACT_VARIABLES_CONTAINER][ABSTRACT_VARIABLES_CONTAINER__TYPE] = Reltypes.ABSTRACT_VARIABLES_CONTAINER__TYPE;
		mapping[ABSTRACT_VARIABLES_CONTAINER][ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS] = Reltypes.ABSTRACT_VARIABLES_CONTAINER__FRAGMENTS;
		mapping[ANNOTATION] = new RelationshipType[5];
		mapping[ANNOTATION][ANNOTATION__TYPE] = Reltypes.ANNOTATION__TYPE;
		mapping[ANNOTATION][ANNOTATION__VALUES] = Reltypes.ANNOTATION__VALUES;
		mapping[ARCHIVE] = new RelationshipType[9];
		mapping[ARCHIVE][ARCHIVE__CLASS_FILES] = Reltypes.ARCHIVE__CLASS_FILES;
		mapping[ARCHIVE][ARCHIVE__MANIFEST] = Reltypes.ARCHIVE__MANIFEST;
		mapping[STATEMENT] = new RelationshipType[3];
		mapping[ASSERT_STATEMENT] = new RelationshipType[5];
		mapping[ASSERT_STATEMENT][ASSERT_STATEMENT__MESSAGE] = Reltypes.ASSERT_STATEMENT__MESSAGE;
		mapping[ASSERT_STATEMENT][ASSERT_STATEMENT__EXPRESSION] = Reltypes.ASSERT_STATEMENT__EXPRESSION;
		mapping[ANNOTATION_MEMBER_VALUE_PAIR] = new RelationshipType[8];
		mapping[ANNOTATION_MEMBER_VALUE_PAIR][ANNOTATION_MEMBER_VALUE_PAIR__MEMBER] = Reltypes.ANNOTATION_MEMBER_VALUE_PAIR__MEMBER;
		mapping[ANNOTATION_MEMBER_VALUE_PAIR][ANNOTATION_MEMBER_VALUE_PAIR__VALUE] = Reltypes.ANNOTATION_MEMBER_VALUE_PAIR__VALUE;
		mapping[ANNOTATION_TYPE_DECLARATION] = new RelationshipType[16];
		mapping[ANNOTATION_TYPE_MEMBER_DECLARATION] = new RelationshipType[13];
		mapping[ANNOTATION_TYPE_MEMBER_DECLARATION][ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT] = Reltypes.ANNOTATION_TYPE_MEMBER_DECLARATION__DEFAULT;
		mapping[ANNOTATION_TYPE_MEMBER_DECLARATION][ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE] = Reltypes.ANNOTATION_TYPE_MEMBER_DECLARATION__TYPE;
		mapping[ANNOTATION_TYPE_MEMBER_DECLARATION][ANNOTATION_TYPE_MEMBER_DECLARATION__USAGES] = Reltypes.ANNOTATION_TYPE_MEMBER_DECLARATION__USAGES;
		mapping[ANONYMOUS_CLASS_DECLARATION] = new RelationshipType[5];
		mapping[ANONYMOUS_CLASS_DECLARATION][ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS] = Reltypes.ANONYMOUS_CLASS_DECLARATION__BODY_DECLARATIONS;
		mapping[ANONYMOUS_CLASS_DECLARATION][ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION] = Reltypes.ANONYMOUS_CLASS_DECLARATION__CLASS_INSTANCE_CREATION;
		mapping[ARRAY_ACCESS] = new RelationshipType[5];
		mapping[ARRAY_ACCESS][ARRAY_ACCESS__ARRAY] = Reltypes.ARRAY_ACCESS__ARRAY;
		mapping[ARRAY_ACCESS][ARRAY_ACCESS__INDEX] = Reltypes.ARRAY_ACCESS__INDEX;
		mapping[ARRAY_CREATION] = new RelationshipType[6];
		mapping[ARRAY_CREATION][ARRAY_CREATION__DIMENSIONS] = Reltypes.ARRAY_CREATION__DIMENSIONS;
		mapping[ARRAY_CREATION][ARRAY_CREATION__INITIALIZER] = Reltypes.ARRAY_CREATION__INITIALIZER;
		mapping[ARRAY_CREATION][ARRAY_CREATION__TYPE] = Reltypes.ARRAY_CREATION__TYPE;
		mapping[ARRAY_INITIALIZER] = new RelationshipType[4];
		mapping[ARRAY_INITIALIZER][ARRAY_INITIALIZER__EXPRESSIONS] = Reltypes.ARRAY_INITIALIZER__EXPRESSIONS;
		mapping[ARRAY_LENGTH_ACCESS] = new RelationshipType[4];
		mapping[ARRAY_LENGTH_ACCESS][ARRAY_LENGTH_ACCESS__ARRAY] = Reltypes.ARRAY_LENGTH_ACCESS__ARRAY;
		mapping[TYPE] = new RelationshipType[7];
		mapping[TYPE][TYPE__USAGES_IN_TYPE_ACCESS] = Reltypes.TYPE__USAGES_IN_TYPE_ACCESS;
		mapping[ARRAY_TYPE] = new RelationshipType[9];
		mapping[ARRAY_TYPE][ARRAY_TYPE__ELEMENT_TYPE] = Reltypes.ARRAY_TYPE__ELEMENT_TYPE;
		mapping[ASSIGNMENT] = new RelationshipType[6];
		mapping[ASSIGNMENT][ASSIGNMENT__LEFT_HAND_SIDE] = Reltypes.ASSIGNMENT__LEFT_HAND_SIDE;
		mapping[ASSIGNMENT][ASSIGNMENT__RIGHT_HAND_SIDE] = Reltypes.ASSIGNMENT__RIGHT_HAND_SIDE;
		mapping[BOOLEAN_LITERAL] = new RelationshipType[4];
		mapping[COMMENT] = new RelationshipType[6];
		mapping[BLOCK_COMMENT] = new RelationshipType[6];
		mapping[BLOCK] = new RelationshipType[4];
		mapping[BLOCK][BLOCK__STATEMENTS] = Reltypes.BLOCK__STATEMENTS;
		mapping[BREAK_STATEMENT] = new RelationshipType[4];
		mapping[BREAK_STATEMENT][BREAK_STATEMENT__LABEL] = Reltypes.BREAK_STATEMENT__LABEL;
		mapping[CAST_EXPRESSION] = new RelationshipType[5];
		mapping[CAST_EXPRESSION][CAST_EXPRESSION__EXPRESSION] = Reltypes.CAST_EXPRESSION__EXPRESSION;
		mapping[CAST_EXPRESSION][CAST_EXPRESSION__TYPE] = Reltypes.CAST_EXPRESSION__TYPE;
		mapping[CATCH_CLAUSE] = new RelationshipType[5];
		mapping[CATCH_CLAUSE][CATCH_CLAUSE__EXCEPTION] = Reltypes.CATCH_CLAUSE__EXCEPTION;
		mapping[CATCH_CLAUSE][CATCH_CLAUSE__BODY] = Reltypes.CATCH_CLAUSE__BODY;
		mapping[CHARACTER_LITERAL] = new RelationshipType[4];
		mapping[CLASS_FILE] = new RelationshipType[10];
		mapping[CLASS_FILE][CLASS_FILE__TYPE] = Reltypes.CLASS_FILE__TYPE;
		mapping[CLASS_FILE][CLASS_FILE__ATTACHED_SOURCE] = Reltypes.CLASS_FILE__ATTACHED_SOURCE;
		mapping[CLASS_FILE][CLASS_FILE__PACKAGE] = Reltypes.CLASS_FILE__PACKAGE;
		mapping[CLASS_INSTANCE_CREATION] = new RelationshipType[9];
		mapping[CLASS_INSTANCE_CREATION][CLASS_INSTANCE_CREATION__METHOD] = Reltypes.CLASS_INSTANCE_CREATION__METHOD;
		mapping[CLASS_INSTANCE_CREATION][CLASS_INSTANCE_CREATION__ARGUMENTS] = Reltypes.CLASS_INSTANCE_CREATION__ARGUMENTS;
		mapping[CLASS_INSTANCE_CREATION][CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS] = Reltypes.CLASS_INSTANCE_CREATION__TYPE_ARGUMENTS;
		mapping[CLASS_INSTANCE_CREATION][CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION] = Reltypes.CLASS_INSTANCE_CREATION__ANONYMOUS_CLASS_DECLARATION;
		mapping[CLASS_INSTANCE_CREATION][CLASS_INSTANCE_CREATION__EXPRESSION] = Reltypes.CLASS_INSTANCE_CREATION__EXPRESSION;
		mapping[CLASS_INSTANCE_CREATION][CLASS_INSTANCE_CREATION__TYPE] = Reltypes.CLASS_INSTANCE_CREATION__TYPE;
		mapping[CONSTRUCTOR_DECLARATION] = new RelationshipType[16];
		mapping[CONDITIONAL_EXPRESSION] = new RelationshipType[6];
		mapping[CONDITIONAL_EXPRESSION][CONDITIONAL_EXPRESSION__ELSE_EXPRESSION] = Reltypes.CONDITIONAL_EXPRESSION__ELSE_EXPRESSION;
		mapping[CONDITIONAL_EXPRESSION][CONDITIONAL_EXPRESSION__EXPRESSION] = Reltypes.CONDITIONAL_EXPRESSION__EXPRESSION;
		mapping[CONDITIONAL_EXPRESSION][CONDITIONAL_EXPRESSION__THEN_EXPRESSION] = Reltypes.CONDITIONAL_EXPRESSION__THEN_EXPRESSION;
		mapping[CONSTRUCTOR_INVOCATION] = new RelationshipType[6];
		mapping[CONSTRUCTOR_INVOCATION][CONSTRUCTOR_INVOCATION__METHOD] = Reltypes.CONSTRUCTOR_INVOCATION__METHOD;
		mapping[CONSTRUCTOR_INVOCATION][CONSTRUCTOR_INVOCATION__ARGUMENTS] = Reltypes.CONSTRUCTOR_INVOCATION__ARGUMENTS;
		mapping[CONSTRUCTOR_INVOCATION][CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS] = Reltypes.CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS;
		mapping[TYPE_DECLARATION] = new RelationshipType[17];
		mapping[TYPE_DECLARATION][TYPE_DECLARATION__TYPE_PARAMETERS] = Reltypes.TYPE_DECLARATION__TYPE_PARAMETERS;
		mapping[CLASS_DECLARATION] = new RelationshipType[18];
		mapping[CLASS_DECLARATION][CLASS_DECLARATION__SUPER_CLASS] = Reltypes.CLASS_DECLARATION__SUPER_CLASS;
		mapping[COMPILATION_UNIT] = new RelationshipType[11];
		mapping[COMPILATION_UNIT][COMPILATION_UNIT__COMMENT_LIST] = Reltypes.COMPILATION_UNIT__COMMENT_LIST;
		mapping[COMPILATION_UNIT][COMPILATION_UNIT__IMPORTS] = Reltypes.COMPILATION_UNIT__IMPORTS;
		mapping[COMPILATION_UNIT][COMPILATION_UNIT__PACKAGE] = Reltypes.COMPILATION_UNIT__PACKAGE;
		mapping[COMPILATION_UNIT][COMPILATION_UNIT__TYPES] = Reltypes.COMPILATION_UNIT__TYPES;
		mapping[CONTINUE_STATEMENT] = new RelationshipType[4];
		mapping[CONTINUE_STATEMENT][CONTINUE_STATEMENT__LABEL] = Reltypes.CONTINUE_STATEMENT__LABEL;
		mapping[DO_STATEMENT] = new RelationshipType[5];
		mapping[DO_STATEMENT][DO_STATEMENT__EXPRESSION] = Reltypes.DO_STATEMENT__EXPRESSION;
		mapping[DO_STATEMENT][DO_STATEMENT__BODY] = Reltypes.DO_STATEMENT__BODY;
		mapping[EMPTY_STATEMENT] = new RelationshipType[3];
		mapping[ENHANCED_FOR_STATEMENT] = new RelationshipType[6];
		mapping[ENHANCED_FOR_STATEMENT][ENHANCED_FOR_STATEMENT__BODY] = Reltypes.ENHANCED_FOR_STATEMENT__BODY;
		mapping[ENHANCED_FOR_STATEMENT][ENHANCED_FOR_STATEMENT__EXPRESSION] = Reltypes.ENHANCED_FOR_STATEMENT__EXPRESSION;
		mapping[ENHANCED_FOR_STATEMENT][ENHANCED_FOR_STATEMENT__PARAMETER] = Reltypes.ENHANCED_FOR_STATEMENT__PARAMETER;
		mapping[ENUM_CONSTANT_DECLARATION] = new RelationshipType[15];
		mapping[ENUM_CONSTANT_DECLARATION][ENUM_CONSTANT_DECLARATION__INITIALIZER] = Reltypes.ENUM_CONSTANT_DECLARATION__INITIALIZER;
		mapping[ENUM_CONSTANT_DECLARATION][ENUM_CONSTANT_DECLARATION__USAGE_IN_VARIABLE_ACCESS] = Reltypes.ENUM_CONSTANT_DECLARATION__USAGE_IN_VARIABLE_ACCESS;
		mapping[ENUM_CONSTANT_DECLARATION][ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION] = Reltypes.ENUM_CONSTANT_DECLARATION__ANONYMOUS_CLASS_DECLARATION;
		mapping[ENUM_CONSTANT_DECLARATION][ENUM_CONSTANT_DECLARATION__ARGUMENTS] = Reltypes.ENUM_CONSTANT_DECLARATION__ARGUMENTS;
		mapping[ENUM_DECLARATION] = new RelationshipType[17];
		mapping[ENUM_DECLARATION][ENUM_DECLARATION__ENUM_CONSTANTS] = Reltypes.ENUM_DECLARATION__ENUM_CONSTANTS;
		mapping[EXPRESSION_STATEMENT] = new RelationshipType[4];
		mapping[EXPRESSION_STATEMENT][EXPRESSION_STATEMENT__EXPRESSION] = Reltypes.EXPRESSION_STATEMENT__EXPRESSION;
		mapping[FIELD_ACCESS] = new RelationshipType[5];
		mapping[FIELD_ACCESS][FIELD_ACCESS__FIELD] = Reltypes.FIELD_ACCESS__FIELD;
		mapping[FIELD_ACCESS][FIELD_ACCESS__EXPRESSION] = Reltypes.FIELD_ACCESS__EXPRESSION;
		mapping[FIELD_DECLARATION] = new RelationshipType[12];
		mapping[FIELD_DECLARATION][FIELD_DECLARATION__TYPE] = Reltypes.FIELD_DECLARATION__TYPE;
		mapping[FIELD_DECLARATION][FIELD_DECLARATION__FRAGMENTS] = Reltypes.FIELD_DECLARATION__FRAGMENTS;
		mapping[FOR_STATEMENT] = new RelationshipType[7];
		mapping[FOR_STATEMENT][FOR_STATEMENT__EXPRESSION] = Reltypes.FOR_STATEMENT__EXPRESSION;
		mapping[FOR_STATEMENT][FOR_STATEMENT__UPDATERS] = Reltypes.FOR_STATEMENT__UPDATERS;
		mapping[FOR_STATEMENT][FOR_STATEMENT__INITIALIZERS] = Reltypes.FOR_STATEMENT__INITIALIZERS;
		mapping[FOR_STATEMENT][FOR_STATEMENT__BODY] = Reltypes.FOR_STATEMENT__BODY;
		mapping[IF_STATEMENT] = new RelationshipType[6];
		mapping[IF_STATEMENT][IF_STATEMENT__EXPRESSION] = Reltypes.IF_STATEMENT__EXPRESSION;
		mapping[IF_STATEMENT][IF_STATEMENT__THEN_STATEMENT] = Reltypes.IF_STATEMENT__THEN_STATEMENT;
		mapping[IF_STATEMENT][IF_STATEMENT__ELSE_STATEMENT] = Reltypes.IF_STATEMENT__ELSE_STATEMENT;
		mapping[IMPORT_DECLARATION] = new RelationshipType[5];
		mapping[IMPORT_DECLARATION][IMPORT_DECLARATION__IMPORTED_ELEMENT] = Reltypes.IMPORT_DECLARATION__IMPORTED_ELEMENT;
		mapping[INFIX_EXPRESSION] = new RelationshipType[7];
		mapping[INFIX_EXPRESSION][INFIX_EXPRESSION__RIGHT_OPERAND] = Reltypes.INFIX_EXPRESSION__RIGHT_OPERAND;
		mapping[INFIX_EXPRESSION][INFIX_EXPRESSION__LEFT_OPERAND] = Reltypes.INFIX_EXPRESSION__LEFT_OPERAND;
		mapping[INFIX_EXPRESSION][INFIX_EXPRESSION__EXTENDED_OPERANDS] = Reltypes.INFIX_EXPRESSION__EXTENDED_OPERANDS;
		mapping[INITIALIZER] = new RelationshipType[11];
		mapping[INITIALIZER][INITIALIZER__BODY] = Reltypes.INITIALIZER__BODY;
		mapping[INSTANCEOF_EXPRESSION] = new RelationshipType[5];
		mapping[INSTANCEOF_EXPRESSION][INSTANCEOF_EXPRESSION__RIGHT_OPERAND] = Reltypes.INSTANCEOF_EXPRESSION__RIGHT_OPERAND;
		mapping[INSTANCEOF_EXPRESSION][INSTANCEOF_EXPRESSION__LEFT_OPERAND] = Reltypes.INSTANCEOF_EXPRESSION__LEFT_OPERAND;
		mapping[INTERFACE_DECLARATION] = new RelationshipType[17];
		mapping[JAVADOC] = new RelationshipType[7];
		mapping[JAVADOC][JAVADOC__TAGS] = Reltypes.JAVADOC__TAGS;
		mapping[LABELED_STATEMENT] = new RelationshipType[9];
		mapping[LABELED_STATEMENT][LABELED_STATEMENT__BODY] = Reltypes.LABELED_STATEMENT__BODY;
		mapping[LABELED_STATEMENT][LABELED_STATEMENT__USAGES_IN_BREAK_STATEMENTS] = Reltypes.LABELED_STATEMENT__USAGES_IN_BREAK_STATEMENTS;
		mapping[LABELED_STATEMENT][LABELED_STATEMENT__USAGES_IN_CONTINUE_STATEMENTS] = Reltypes.LABELED_STATEMENT__USAGES_IN_CONTINUE_STATEMENTS;
		mapping[LINE_COMMENT] = new RelationshipType[6];
		mapping[MANIFEST] = new RelationshipType[2];
		mapping[MANIFEST][MANIFEST__MAIN_ATTRIBUTES] = Reltypes.MANIFEST__MAIN_ATTRIBUTES;
		mapping[MANIFEST][MANIFEST__ENTRY_ATTRIBUTES] = Reltypes.MANIFEST__ENTRY_ATTRIBUTES;
		mapping[MANIFEST_ATTRIBUTE] = new RelationshipType[2];
		mapping[MANIFEST_ENTRY] = new RelationshipType[2];
		mapping[MANIFEST_ENTRY][MANIFEST_ENTRY__ATTRIBUTES] = Reltypes.MANIFEST_ENTRY__ATTRIBUTES;
		mapping[MEMBER_REF] = new RelationshipType[5];
		mapping[MEMBER_REF][MEMBER_REF__MEMBER] = Reltypes.MEMBER_REF__MEMBER;
		mapping[MEMBER_REF][MEMBER_REF__QUALIFIER] = Reltypes.MEMBER_REF__QUALIFIER;
		mapping[METHOD_DECLARATION] = new RelationshipType[20];
		mapping[METHOD_DECLARATION][METHOD_DECLARATION__RETURN_TYPE] = Reltypes.METHOD_DECLARATION__RETURN_TYPE;
		mapping[METHOD_DECLARATION][METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION] = Reltypes.METHOD_DECLARATION__REDEFINED_METHOD_DECLARATION;
		mapping[METHOD_DECLARATION][METHOD_DECLARATION__REDEFINITIONS] = Reltypes.METHOD_DECLARATION__REDEFINITIONS;
		mapping[METHOD_INVOCATION] = new RelationshipType[7];
		mapping[METHOD_INVOCATION][METHOD_INVOCATION__METHOD] = Reltypes.METHOD_INVOCATION__METHOD;
		mapping[METHOD_INVOCATION][METHOD_INVOCATION__ARGUMENTS] = Reltypes.METHOD_INVOCATION__ARGUMENTS;
		mapping[METHOD_INVOCATION][METHOD_INVOCATION__TYPE_ARGUMENTS] = Reltypes.METHOD_INVOCATION__TYPE_ARGUMENTS;
		mapping[METHOD_INVOCATION][METHOD_INVOCATION__EXPRESSION] = Reltypes.METHOD_INVOCATION__EXPRESSION;
		mapping[METHOD_REF] = new RelationshipType[6];
		mapping[METHOD_REF][METHOD_REF__METHOD] = Reltypes.METHOD_REF__METHOD;
		mapping[METHOD_REF][METHOD_REF__QUALIFIER] = Reltypes.METHOD_REF__QUALIFIER;
		mapping[METHOD_REF][METHOD_REF__PARAMETERS] = Reltypes.METHOD_REF__PARAMETERS;
		mapping[METHOD_REF_PARAMETER] = new RelationshipType[6];
		mapping[METHOD_REF_PARAMETER][METHOD_REF_PARAMETER__TYPE] = Reltypes.METHOD_REF_PARAMETER__TYPE;
		mapping[MODEL] = new RelationshipType[7];
		mapping[MODEL][MODEL__OWNED_ELEMENTS] = Reltypes.MODEL__OWNED_ELEMENTS;
		mapping[MODEL][MODEL__ORPHAN_TYPES] = Reltypes.MODEL__ORPHAN_TYPES;
		mapping[MODEL][MODEL__UNRESOLVED_ITEMS] = Reltypes.MODEL__UNRESOLVED_ITEMS;
		mapping[MODEL][MODEL__COMPILATION_UNITS] = Reltypes.MODEL__COMPILATION_UNITS;
		mapping[MODEL][MODEL__CLASS_FILES] = Reltypes.MODEL__CLASS_FILES;
		mapping[MODEL][MODEL__ARCHIVES] = Reltypes.MODEL__ARCHIVES;
		mapping[MODIFIER] = new RelationshipType[15];
		mapping[MODIFIER][MODIFIER__BODY_DECLARATION] = Reltypes.MODIFIER__BODY_DECLARATION;
		mapping[MODIFIER][MODIFIER__SINGLE_VARIABLE_DECLARATION] = Reltypes.MODIFIER__SINGLE_VARIABLE_DECLARATION;
		mapping[MODIFIER][MODIFIER__VARIABLE_DECLARATION_STATEMENT] = Reltypes.MODIFIER__VARIABLE_DECLARATION_STATEMENT;
		mapping[MODIFIER][MODIFIER__VARIABLE_DECLARATION_EXPRESSION] = Reltypes.MODIFIER__VARIABLE_DECLARATION_EXPRESSION;
		mapping[NAMESPACE_ACCESS] = new RelationshipType[3];
		mapping[NUMBER_LITERAL] = new RelationshipType[4];
		mapping[NULL_LITERAL] = new RelationshipType[3];
		mapping[PACKAGE] = new RelationshipType[11];
		mapping[PACKAGE][PACKAGE__OWNED_ELEMENTS] = Reltypes.PACKAGE__OWNED_ELEMENTS;
		mapping[PACKAGE][PACKAGE__MODEL] = Reltypes.PACKAGE__MODEL;
		mapping[PACKAGE][PACKAGE__OWNED_PACKAGES] = Reltypes.PACKAGE__OWNED_PACKAGES;
		mapping[PACKAGE][PACKAGE__PACKAGE] = Reltypes.PACKAGE__PACKAGE;
		mapping[PACKAGE][PACKAGE__USAGES_IN_PACKAGE_ACCESS] = Reltypes.PACKAGE__USAGES_IN_PACKAGE_ACCESS;
		mapping[PACKAGE_ACCESS] = new RelationshipType[5];
		mapping[PACKAGE_ACCESS][PACKAGE_ACCESS__PACKAGE] = Reltypes.PACKAGE_ACCESS__PACKAGE;
		mapping[PACKAGE_ACCESS][PACKAGE_ACCESS__QUALIFIER] = Reltypes.PACKAGE_ACCESS__QUALIFIER;
		mapping[PARAMETERIZED_TYPE] = new RelationshipType[9];
		mapping[PARAMETERIZED_TYPE][PARAMETERIZED_TYPE__TYPE] = Reltypes.PARAMETERIZED_TYPE__TYPE;
		mapping[PARAMETERIZED_TYPE][PARAMETERIZED_TYPE__TYPE_ARGUMENTS] = Reltypes.PARAMETERIZED_TYPE__TYPE_ARGUMENTS;
		mapping[PARENTHESIZED_EXPRESSION] = new RelationshipType[4];
		mapping[PARENTHESIZED_EXPRESSION][PARENTHESIZED_EXPRESSION__EXPRESSION] = Reltypes.PARENTHESIZED_EXPRESSION__EXPRESSION;
		mapping[POSTFIX_EXPRESSION] = new RelationshipType[5];
		mapping[POSTFIX_EXPRESSION][POSTFIX_EXPRESSION__OPERAND] = Reltypes.POSTFIX_EXPRESSION__OPERAND;
		mapping[PREFIX_EXPRESSION] = new RelationshipType[5];
		mapping[PREFIX_EXPRESSION][PREFIX_EXPRESSION__OPERAND] = Reltypes.PREFIX_EXPRESSION__OPERAND;
		mapping[PRIMITIVE_TYPE] = new RelationshipType[7];
		mapping[PRIMITIVE_TYPE_BOOLEAN] = new RelationshipType[7];
		mapping[PRIMITIVE_TYPE_BYTE] = new RelationshipType[7];
		mapping[PRIMITIVE_TYPE_CHAR] = new RelationshipType[7];
		mapping[PRIMITIVE_TYPE_DOUBLE] = new RelationshipType[7];
		mapping[PRIMITIVE_TYPE_SHORT] = new RelationshipType[7];
		mapping[PRIMITIVE_TYPE_FLOAT] = new RelationshipType[7];
		mapping[PRIMITIVE_TYPE_INT] = new RelationshipType[7];
		mapping[PRIMITIVE_TYPE_LONG] = new RelationshipType[7];
		mapping[PRIMITIVE_TYPE_VOID] = new RelationshipType[7];
		mapping[RETURN_STATEMENT] = new RelationshipType[4];
		mapping[RETURN_STATEMENT][RETURN_STATEMENT__EXPRESSION] = Reltypes.RETURN_STATEMENT__EXPRESSION;
		mapping[SINGLE_VARIABLE_ACCESS] = new RelationshipType[5];
		mapping[SINGLE_VARIABLE_ACCESS][SINGLE_VARIABLE_ACCESS__VARIABLE] = Reltypes.SINGLE_VARIABLE_ACCESS__VARIABLE;
		mapping[SINGLE_VARIABLE_ACCESS][SINGLE_VARIABLE_ACCESS__QUALIFIER] = Reltypes.SINGLE_VARIABLE_ACCESS__QUALIFIER;
		mapping[VARIABLE_DECLARATION] = new RelationshipType[9];
		mapping[VARIABLE_DECLARATION][VARIABLE_DECLARATION__INITIALIZER] = Reltypes.VARIABLE_DECLARATION__INITIALIZER;
		mapping[VARIABLE_DECLARATION][VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS] = Reltypes.VARIABLE_DECLARATION__USAGE_IN_VARIABLE_ACCESS;
		mapping[SINGLE_VARIABLE_DECLARATION] = new RelationshipType[16];
		mapping[SINGLE_VARIABLE_DECLARATION][SINGLE_VARIABLE_DECLARATION__MODIFIER] = Reltypes.SINGLE_VARIABLE_DECLARATION__MODIFIER;
		mapping[SINGLE_VARIABLE_DECLARATION][SINGLE_VARIABLE_DECLARATION__TYPE] = Reltypes.SINGLE_VARIABLE_DECLARATION__TYPE;
		mapping[SINGLE_VARIABLE_DECLARATION][SINGLE_VARIABLE_DECLARATION__ANNOTATIONS] = Reltypes.SINGLE_VARIABLE_DECLARATION__ANNOTATIONS;
		mapping[SINGLE_VARIABLE_DECLARATION][SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION] = Reltypes.SINGLE_VARIABLE_DECLARATION__METHOD_DECLARATION;
		mapping[SINGLE_VARIABLE_DECLARATION][SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE] = Reltypes.SINGLE_VARIABLE_DECLARATION__CATCH_CLAUSE;
		mapping[SINGLE_VARIABLE_DECLARATION][SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT] = Reltypes.SINGLE_VARIABLE_DECLARATION__ENHANCED_FOR_STATEMENT;
		mapping[STRING_LITERAL] = new RelationshipType[4];
		mapping[SUPER_CONSTRUCTOR_INVOCATION] = new RelationshipType[7];
		mapping[SUPER_CONSTRUCTOR_INVOCATION][SUPER_CONSTRUCTOR_INVOCATION__METHOD] = Reltypes.SUPER_CONSTRUCTOR_INVOCATION__METHOD;
		mapping[SUPER_CONSTRUCTOR_INVOCATION][SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS] = Reltypes.SUPER_CONSTRUCTOR_INVOCATION__ARGUMENTS;
		mapping[SUPER_CONSTRUCTOR_INVOCATION][SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS] = Reltypes.SUPER_CONSTRUCTOR_INVOCATION__TYPE_ARGUMENTS;
		mapping[SUPER_CONSTRUCTOR_INVOCATION][SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION] = Reltypes.SUPER_CONSTRUCTOR_INVOCATION__EXPRESSION;
		mapping[SUPER_FIELD_ACCESS] = new RelationshipType[5];
		mapping[SUPER_FIELD_ACCESS][SUPER_FIELD_ACCESS__FIELD] = Reltypes.SUPER_FIELD_ACCESS__FIELD;
		mapping[SUPER_METHOD_INVOCATION] = new RelationshipType[7];
		mapping[SUPER_METHOD_INVOCATION][SUPER_METHOD_INVOCATION__METHOD] = Reltypes.SUPER_METHOD_INVOCATION__METHOD;
		mapping[SUPER_METHOD_INVOCATION][SUPER_METHOD_INVOCATION__ARGUMENTS] = Reltypes.SUPER_METHOD_INVOCATION__ARGUMENTS;
		mapping[SUPER_METHOD_INVOCATION][SUPER_METHOD_INVOCATION__TYPE_ARGUMENTS] = Reltypes.SUPER_METHOD_INVOCATION__TYPE_ARGUMENTS;
		mapping[SWITCH_CASE] = new RelationshipType[5];
		mapping[SWITCH_CASE][SWITCH_CASE__EXPRESSION] = Reltypes.SWITCH_CASE__EXPRESSION;
		mapping[SWITCH_STATEMENT] = new RelationshipType[5];
		mapping[SWITCH_STATEMENT][SWITCH_STATEMENT__EXPRESSION] = Reltypes.SWITCH_STATEMENT__EXPRESSION;
		mapping[SWITCH_STATEMENT][SWITCH_STATEMENT__STATEMENTS] = Reltypes.SWITCH_STATEMENT__STATEMENTS;
		mapping[SYNCHRONIZED_STATEMENT] = new RelationshipType[5];
		mapping[SYNCHRONIZED_STATEMENT][SYNCHRONIZED_STATEMENT__BODY] = Reltypes.SYNCHRONIZED_STATEMENT__BODY;
		mapping[SYNCHRONIZED_STATEMENT][SYNCHRONIZED_STATEMENT__EXPRESSION] = Reltypes.SYNCHRONIZED_STATEMENT__EXPRESSION;
		mapping[TAG_ELEMENT] = new RelationshipType[5];
		mapping[TAG_ELEMENT][TAG_ELEMENT__FRAGMENTS] = Reltypes.TAG_ELEMENT__FRAGMENTS;
		mapping[TEXT_ELEMENT] = new RelationshipType[4];
		mapping[THIS_EXPRESSION] = new RelationshipType[4];
		mapping[THROW_STATEMENT] = new RelationshipType[4];
		mapping[THROW_STATEMENT][THROW_STATEMENT__EXPRESSION] = Reltypes.THROW_STATEMENT__EXPRESSION;
		mapping[TRY_STATEMENT] = new RelationshipType[6];
		mapping[TRY_STATEMENT][TRY_STATEMENT__BODY] = Reltypes.TRY_STATEMENT__BODY;
		mapping[TRY_STATEMENT][TRY_STATEMENT__FINALLY] = Reltypes.TRY_STATEMENT__FINALLY;
		mapping[TRY_STATEMENT][TRY_STATEMENT__CATCH_CLAUSES] = Reltypes.TRY_STATEMENT__CATCH_CLAUSES;
		mapping[TYPE_ACCESS] = new RelationshipType[5];
		mapping[TYPE_ACCESS][TYPE_ACCESS__TYPE] = Reltypes.TYPE_ACCESS__TYPE;
		mapping[TYPE_ACCESS][TYPE_ACCESS__QUALIFIER] = Reltypes.TYPE_ACCESS__QUALIFIER;
		mapping[TYPE_DECLARATION_STATEMENT] = new RelationshipType[4];
		mapping[TYPE_DECLARATION_STATEMENT][TYPE_DECLARATION_STATEMENT__DECLARATION] = Reltypes.TYPE_DECLARATION_STATEMENT__DECLARATION;
		mapping[TYPE_LITERAL] = new RelationshipType[4];
		mapping[TYPE_LITERAL][TYPE_LITERAL__TYPE] = Reltypes.TYPE_LITERAL__TYPE;
		mapping[TYPE_PARAMETER] = new RelationshipType[8];
		mapping[TYPE_PARAMETER][TYPE_PARAMETER__BOUNDS] = Reltypes.TYPE_PARAMETER__BOUNDS;
		mapping[UNRESOLVED_ITEM] = new RelationshipType[6];
		mapping[UNRESOLVED_ITEM_ACCESS] = new RelationshipType[5];
		mapping[UNRESOLVED_ITEM_ACCESS][UNRESOLVED_ITEM_ACCESS__ELEMENT] = Reltypes.UNRESOLVED_ITEM_ACCESS__ELEMENT;
		mapping[UNRESOLVED_ITEM_ACCESS][UNRESOLVED_ITEM_ACCESS__QUALIFIER] = Reltypes.UNRESOLVED_ITEM_ACCESS__QUALIFIER;
		mapping[UNRESOLVED_ANNOTATION_DECLARATION] = new RelationshipType[16];
		mapping[UNRESOLVED_ANNOTATION_TYPE_MEMBER_DECLARATION] = new RelationshipType[13];
		mapping[UNRESOLVED_CLASS_DECLARATION] = new RelationshipType[18];
		mapping[UNRESOLVED_ENUM_DECLARATION] = new RelationshipType[17];
		mapping[UNRESOLVED_INTERFACE_DECLARATION] = new RelationshipType[17];
		mapping[UNRESOLVED_LABELED_STATEMENT] = new RelationshipType[9];
		mapping[UNRESOLVED_METHOD_DECLARATION] = new RelationshipType[20];
		mapping[UNRESOLVED_SINGLE_VARIABLE_DECLARATION] = new RelationshipType[16];
		mapping[UNRESOLVED_TYPE] = new RelationshipType[7];
		mapping[UNRESOLVED_TYPE_DECLARATION] = new RelationshipType[16];
		mapping[VARIABLE_DECLARATION_FRAGMENT] = new RelationshipType[10];
		mapping[VARIABLE_DECLARATION_FRAGMENT][VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER] = Reltypes.VARIABLE_DECLARATION_FRAGMENT__VARIABLES_CONTAINER;
		mapping[UNRESOLVED_VARIABLE_DECLARATION_FRAGMENT] = new RelationshipType[10];
		mapping[VARIABLE_DECLARATION_EXPRESSION] = new RelationshipType[7];
		mapping[VARIABLE_DECLARATION_EXPRESSION][VARIABLE_DECLARATION_EXPRESSION__TYPE] = Reltypes.VARIABLE_DECLARATION_EXPRESSION__TYPE;
		mapping[VARIABLE_DECLARATION_EXPRESSION][VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS] = Reltypes.VARIABLE_DECLARATION_EXPRESSION__FRAGMENTS;
		mapping[VARIABLE_DECLARATION_EXPRESSION][VARIABLE_DECLARATION_EXPRESSION__MODIFIER] = Reltypes.VARIABLE_DECLARATION_EXPRESSION__MODIFIER;
		mapping[VARIABLE_DECLARATION_EXPRESSION][VARIABLE_DECLARATION_EXPRESSION__ANNOTATIONS] = Reltypes.VARIABLE_DECLARATION_EXPRESSION__ANNOTATIONS;
		mapping[VARIABLE_DECLARATION_STATEMENT] = new RelationshipType[8];
		mapping[VARIABLE_DECLARATION_STATEMENT][VARIABLE_DECLARATION_STATEMENT__TYPE] = Reltypes.VARIABLE_DECLARATION_STATEMENT__TYPE;
		mapping[VARIABLE_DECLARATION_STATEMENT][VARIABLE_DECLARATION_STATEMENT__FRAGMENTS] = Reltypes.VARIABLE_DECLARATION_STATEMENT__FRAGMENTS;
		mapping[VARIABLE_DECLARATION_STATEMENT][VARIABLE_DECLARATION_STATEMENT__MODIFIER] = Reltypes.VARIABLE_DECLARATION_STATEMENT__MODIFIER;
		mapping[VARIABLE_DECLARATION_STATEMENT][VARIABLE_DECLARATION_STATEMENT__ANNOTATIONS] = Reltypes.VARIABLE_DECLARATION_STATEMENT__ANNOTATIONS;
		mapping[WILD_CARD_TYPE] = new RelationshipType[9];
		mapping[WILD_CARD_TYPE][WILD_CARD_TYPE__BOUND] = Reltypes.WILD_CARD_TYPE__BOUND;
		mapping[WHILE_STATEMENT] = new RelationshipType[5];
		mapping[WHILE_STATEMENT][WHILE_STATEMENT__EXPRESSION] = Reltypes.WHILE_STATEMENT__EXPRESSION;
		mapping[WHILE_STATEMENT][WHILE_STATEMENT__BODY] = Reltypes.WHILE_STATEMENT__BODY;
	} // JavaPackageRelationshipMapping()
	
	public RelationshipType relationshipAt(int classID, int referenceID) {
		assert classID >= 0 && classID < mapping.length : "Invalid Class ID";
		assert referenceID >= 0 && referenceID < mapping[classID].length: "Invalid Reference ID";
		
		return mapping[classID][referenceID];
	}
}// class JavaPackageRelationshipMapping
/* Neo4EMF Inserted Code -- End*/
} //JavaPackageImpl
