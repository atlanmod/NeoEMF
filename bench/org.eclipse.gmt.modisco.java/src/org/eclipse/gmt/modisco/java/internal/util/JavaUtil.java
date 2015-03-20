/**
 * *******************************************************************************
 * Copyright (c) 2009 Mia-Software.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sebastien Minguet (Mia-Software) - initial API and implementation
 *     Frederic Madiot (Mia-Software) - initial API and implementation
 *     Fabien Giquel (Mia-Software) - initial API and implementation
 *     Gabriel Barbier (Mia-Software) - initial API and implementation
 *     Erwan Breton (Sodifrance) - initial API and implementation
 *     Romain Dervaux (Mia-Software) - initial API and implementation
 * *******************************************************************************
 *
 * $Id$
 */
package org.eclipse.gmt.modisco.java.internal.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.infra.common.core.logging.MoDiscoLogger;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.ArrayType;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.Initializer;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.ParameterizedType;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeParameter;
import org.eclipse.gmt.modisco.java.UnresolvedItem;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.WildCardType;
import org.eclipse.gmt.modisco.java.plugin.JavaPlugin;

/**
 * An utility class for computing a qualified name for some model elements.
 * 
 */
public final class JavaUtil {

	private static final String TRACEID_GETQNAME = "org.eclipse.gmt.modisco.java/debug/JavaUtil/getQualifiedNames"; //$NON-NLS-1$
	private static final boolean TRACE_GETQNAME = JavaPlugin.getDefault()
			.isDebugging()
			&& new Boolean(Platform.getDebugOption(JavaUtil.TRACEID_GETQNAME));
	private static final String TRACEID_NE_BY_QN = "org.eclipse.gmt.modisco.java/debug/JavaUtil/getNamedElementByQualifiedName"; //$NON-NLS-1$
	private static final boolean TRACE_NE_BY_QN = JavaPlugin.getDefault()
			.isDebugging()
			&& new Boolean(Platform.getDebugOption(JavaUtil.TRACEID_NE_BY_QN));

	private JavaUtil() {
	}

	/**
	 * Convenience method for {@code getQualifiedName(object, true)}.
	 * 
	 * @see #getQualifiedName(ASTNode, boolean)
	 */
	public static String getQualifiedName(final ASTNode object) {
		return getQualifiedName(object, true);
	}

	/**
	 * Computes a qualified name for named elements (types, methods, parameters,
	 * ...).
	 * 
	 * @param object
	 *            a model element.
	 * @param removeGenerics
	 *            if we have to remove the generics.
	 * @return the qualified name or {@code null} if the object type is not a
	 *         named type.
	 */
	public static String getQualifiedName(final ASTNode object,
			final boolean removeGenerics) {
		StringBuilder buffer = new StringBuilder();
		if (object instanceof UnresolvedItem) {
			buffer.append(((UnresolvedItem) object).getName());
		} else if (object instanceof AnnotationTypeMemberDeclaration) {
			String containerQName = getQualifiedName((ASTNode) object
					.eContainer(), removeGenerics);
			buffer.append(containerQName);
			buffer.append("."); //$NON-NLS-1$
			buffer.append(((AnnotationTypeMemberDeclaration) object).getName());
			buffer.append("()"); //$NON-NLS-1$
		} else if (object instanceof EnumConstantDeclaration) {
			String containerQName = getQualifiedName((ASTNode) object
					.eContainer(), removeGenerics);
			buffer.append(containerQName);
			buffer.append("."); //$NON-NLS-1$
			buffer.append(((EnumConstantDeclaration) object).getName());
		} else if (object instanceof VariableDeclarationFragment) {
			if (object.eContainer() instanceof FieldDeclaration) {
				VariableDeclarationFragment singleVariableDeclaration = (VariableDeclarationFragment) object;
				String containerQN = getQualifiedName(
						(ASTNode) singleVariableDeclaration.eContainer()
								.eContainer(), removeGenerics);
				buffer.append(containerQN);
				buffer.append("."); //$NON-NLS-1$
				buffer.append(singleVariableDeclaration.getName());
			}
		} else if (object instanceof AbstractMethodDeclaration) {
			AbstractMethodDeclaration methodDeclaration = (AbstractMethodDeclaration) object;
			String containerQName = getQualifiedName((ASTNode) object
					.eContainer(), removeGenerics);
			buffer.append(containerQName);
			buffer.append('.');
			buffer.append(methodDeclaration.getName());
			buffer.append('(');
			for (int i = 0; i < methodDeclaration.getParameters().size(); i++) {
				if (i > 0) {
					buffer.append(","); //$NON-NLS-1$
				}
				SingleVariableDeclaration svd = methodDeclaration
						.getParameters().get(i);
				buffer.append(getQualifiedName(svd.getType(), true));
			}
			buffer.append(")"); //$NON-NLS-1$
		} else if (object instanceof ArrayType) {
			ArrayType arraytype = (ArrayType) object;
			buffer.append(arraytype.getName());
		} else if (object instanceof TypeParameter) {
			TypeParameter typeParameter = (TypeParameter) object;
			if (removeGenerics) {
				if (typeParameter.getBounds().size() > 0) {
					// get the erasure
					buffer.append(getQualifiedName(typeParameter.getBounds()
							.get(0), true));
				} else {
					buffer.append("java.lang.Object"); //$NON-NLS-1$
				}
			} else {
				buffer.append(typeParameter.getName());
			}
		} else if (object instanceof WildCardType) {
			WildCardType wildcardType = (WildCardType) object;
			buffer.append(wildcardType.getName());
		} else if (object instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) object;
			if (removeGenerics) {
				buffer.append(getQualifiedName(parameterizedType.getType(),
						removeGenerics));
			} else {
				buffer.append(parameterizedType.getName());
			}
		} else if (object instanceof AbstractTypeDeclaration) {
			AbstractTypeDeclaration typeDeclaration = (AbstractTypeDeclaration) object;
			if (typeDeclaration.eContainer() instanceof AbstractTypeDeclaration) {
				AbstractTypeDeclaration superTypeDeclaration = (AbstractTypeDeclaration) typeDeclaration
						.eContainer();
				buffer.append(getQualifiedName(superTypeDeclaration,
						removeGenerics));
				buffer.append('.');
			} else if (typeDeclaration.eContainer() instanceof Package) {
				Package packaje = (Package) typeDeclaration.eContainer();
				buffer.append(getQualifiedName(packaje, removeGenerics));
				buffer.append('.');
			} else if (typeDeclaration.eContainer() instanceof Model) {
				// No prefix if container is a Model instance
				assert true; // misc statement for checkstyle rule
			} else if (typeDeclaration.eContainer() != null) {
				MoDiscoLogger.logWarning(
						"Not managed type declaration: typeDeclaration.eContainer().getClass()= " //$NON-NLS-1$
						, JavaPlugin.getDefault());
			} else {
				MoDiscoLogger.logWarning("Type with null container" //$NON-NLS-1$
						, JavaPlugin.getDefault());
			}
			buffer.append(typeDeclaration.getName());
		} else if (object instanceof Package) {
			Package packaje = (Package) object;
			if (packaje.eContainer() instanceof Package) {
				Package superPackage = (Package) packaje.eContainer();
				buffer.append(getQualifiedName(superPackage, removeGenerics));
				buffer.append('.');
			}
			buffer.append(packaje.getName());
		} else if (object instanceof TypeAccess) {
			TypeAccess typeAccess = (TypeAccess) object;
			buffer
					.append(getQualifiedName(typeAccess.getType(),
							removeGenerics));
		} else if (object instanceof Model) {
			Exception e = new Exception(
					"getQualified name should note be called with a model as parameter"); //$NON-NLS-1$
			IStatus status = new Status(IStatus.ERROR, JavaPlugin.PLUGIN_ID, e
					.getMessage(), e);
			JavaPlugin.getDefault().getLog().log(status);
		} else if (object instanceof PrimitiveType) {
			PrimitiveType primitiveType = (PrimitiveType) object;
			buffer.append(primitiveType.getName());
		} else if (object instanceof AnonymousClassDeclaration) {
			// No qualified name for AnonymousClassDeclaration
			assert true; // misc statement for checkstyle rule
		} else if (object instanceof SingleVariableDeclaration) {
			SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration) object;
			if (!(singleVariableDeclaration.eContainer() instanceof CatchClause)) {
				buffer.append(getQualifiedName((ASTNode) singleVariableDeclaration
						.eContainer(), removeGenerics));
				buffer.append('.');
			}
			buffer.append(singleVariableDeclaration.getName());
		} else if (object != null) {
			Exception e = new Exception("Not managed type: " //$NON-NLS-1$
					+ object.getClass().getName());
			IStatus status = new Status(IStatus.ERROR, JavaPlugin.PLUGIN_ID, e
					.getMessage(), e);
			JavaPlugin.getDefault().getLog().log(status);
		} else {
			// May occur when parent model element is not complete (e.g.
			// unresolved type of parameter of method)
			// Exception e = new Exception("Null parameter");
			// IStatus status = new Status(IStatus.ERROR, JavaPlugin.PLUGIN_ID,
			// e
			// .getMessage(), e);
			// JavaPlugin.getDefault().getLog().log(status);
			buffer.append("<<null>>"); //$NON-NLS-1$
		}
		if (Platform.inDebugMode() && JavaUtil.TRACE_GETQNAME) {
			System.out.println("JavaUtil.getQualifiedName(EObject)= " //$NON-NLS-1$
					+ buffer.toString());
		}
		return buffer.toString();
	}

	private class CurrentElement {

		private EObject currentElement = null;
		private final List<EObject> element = new ArrayList<EObject>();
		private boolean hasChanged = false;
		private boolean hasChanged2 = true;
		private boolean init = true;

		public CurrentElement() {
			// TODO Auto-generated constructor stub
		}

		public void setElement(final EObject element) {
			this.element.add(element);
			if (this.currentElement == null) {
				this.currentElement = element;
			}
			this.hasChanged = false;
			this.hasChanged2 = true;
		}

		@SuppressWarnings("unused")
		public boolean hasChanged() {
			if (this.hasChanged) {
				this.hasChanged = false;
				return true;
			} else {
				return false;
			}
		}

		public boolean hasChanged2() {
			if (this.hasChanged2) {
				this.hasChanged2 = false;
				if (this.element.size() > 0) {
					this.currentElement = this.element.get(0);
					this.element.remove(0);
				}
				return true;
			} else {
				return false;
			}
		}

		public EObject getElement() {
			return this.currentElement;
		}

		public boolean isEmpty() {
			return this.currentElement == null;
		}

		public boolean nextIndex() {
			if (this.init) {
				this.init = false;
				return true;
			} else {
				this.hasChanged = false;
				this.hasChanged2 = true;
				return (this.element.size() > 0);
				// if (this.element.size() > 0) {
				// this.currentElement = this.element.get(0);
				// this.element.remove(0);
				// return true;
				// } else {
				// return false;
				// }
			}

		}

	}

	/**
	 * 
	 * @param model
	 * @param searchedQualifiedName
	 * @return the model element or null if not found
	 */
	public static NamedElement getNamedElementByQualifiedName(
			final Model model, final String searchedQualifiedName) {
		return getNamedElementByQualifiedName(model, searchedQualifiedName,
				new HashMap<String, NamedElement>());
	}

	/**
	 * 
	 * @param model
	 * @param searchedQualifiedName
	 * @param targets
	 *            is the discoverer target hash map. This parameter is helpful
	 *            to prevent discovery error caused by the visit of non valid
	 *            model element.
	 * @return the model element or null if not found
	 */
	public static NamedElement getNamedElementByQualifiedName(
			final Model model, final String searchedQualifiedName,
			final Map<String, NamedElement> targets) {
		if (JavaUtil.TRACE_NE_BY_QN) {
			System.out
					.println("Begin getNamedElementByQualifiedName(Model, String): " //$NON-NLS-1$
							+ searchedQualifiedName);
		}
		NamedElement resultNamedElement = null;
		JavaUtil javaUtil = new JavaUtil();
		CurrentElement currentElement = javaUtil.new CurrentElement();
		if (searchedQualifiedName == null) {
			MoDiscoLogger
					.logError(
							"JavaDiscovererUtils::getNamedElementByQualifiedName(): qualifiedName parameter is null", //$NON-NLS-1$
							JavaPlugin.getDefault());
		} else {
			Iterator<Type> orphanTypes = model.getOrphanTypes().iterator();
			while (orphanTypes.hasNext()) {
				Type orphanType = orphanTypes.next();
				if (searchedQualifiedName.equals(orphanType.getName())) {
					resultNamedElement = orphanType;
				}
			}
			boolean end = false;
			while (currentElement.nextIndex() && resultNamedElement == null) {
				while (resultNamedElement == null && !end
						&& currentElement.hasChanged2()) {
					try {
						if (currentElement.isEmpty()) {
							Iterator<Package> packages = model
									.getOwnedElements().iterator();
							while (packages.hasNext()
									&& resultNamedElement == null
							/* && !currentElement.hasChanged() */) {
								Package packaje = packages.next();
								String currentPackageName = JavaUtil
										.getQualifiedName(packaje, false);
								if (searchedQualifiedName
										.startsWith(currentPackageName)) {
									if (searchedQualifiedName
											.equals(currentPackageName)) {
										resultNamedElement = packaje;
									} else {
										currentElement.setElement(packaje);
									}
								}
							}
						} else {
							if (currentElement.getElement() instanceof Package) {
								Package packaje = (Package) currentElement
										.getElement();
								Iterator<Package> packages = packaje
										.getOwnedPackages().iterator();
								while (packages.hasNext()
										&& resultNamedElement == null
								/* && !currentElement.hasChanged() */) {
									Package subpackage = packages.next();
									String subPackageName = JavaUtil
											.getQualifiedName(subpackage, false);
									if (searchedQualifiedName
											.startsWith(subPackageName)) {
										if (searchedQualifiedName
												.equals(subPackageName)) {
											resultNamedElement = subpackage;
										} else {
											currentElement
													.setElement(subpackage);
										}
									}
								}
								Iterator<AbstractTypeDeclaration> abstractTypeDeclarations = packaje
										.getOwnedElements().iterator();
								while (abstractTypeDeclarations.hasNext()
										&& (resultNamedElement == null)
								/* && (!currentElement.hasChanged()) */) {
									AbstractTypeDeclaration atd = abstractTypeDeclarations
											.next();
									String atdName = JavaUtil.getQualifiedName(
											atd, false);
									if (searchedQualifiedName
											.startsWith(atdName)) {
										if (searchedQualifiedName
												.equals(atdName)) {
											resultNamedElement = atd;
										} else {
											currentElement.setElement(atd);
										}
									}
								}
							} else if (currentElement.getElement() instanceof AbstractTypeDeclaration) {
								AbstractTypeDeclaration abstractTypeDeclaration = (AbstractTypeDeclaration) currentElement
										.getElement();
								List<BodyDeclaration> bodyDeclarations = abstractTypeDeclaration
										.getBodyDeclarations();
								// for enums, we have to retrieve enums
								// constants
								if (abstractTypeDeclaration instanceof EnumDeclaration) {
									bodyDeclarations
											.addAll(((EnumDeclaration) abstractTypeDeclaration)
													.getEnumConstants());
								}
								Iterator<BodyDeclaration> it = bodyDeclarations
										.iterator();
								while (it.hasNext()
										&& resultNamedElement == null
								/* && !currentElement.hasChanged() */) {
									BodyDeclaration bodyDeclaration = it.next();
									if (bodyDeclaration instanceof AbstractTypeDeclaration) {
										AbstractTypeDeclaration subAbstractTypeDeclaration = (AbstractTypeDeclaration) bodyDeclaration;
										String subATDQN = JavaUtil
												.getQualifiedName(
														subAbstractTypeDeclaration,
														false);
										if (searchedQualifiedName
												.startsWith(subATDQN)) {
											if (searchedQualifiedName
													.equals(subATDQN)) {
												resultNamedElement = subAbstractTypeDeclaration;
											} else {
												currentElement
														.setElement(subAbstractTypeDeclaration);
											}
										}
									} else if (!targets
											.containsValue(bodyDeclaration)) {
										if (bodyDeclaration instanceof AbstractMethodDeclaration) {
											AbstractMethodDeclaration abstractMethodDeclaration = (AbstractMethodDeclaration) bodyDeclaration;
											String amdQN = JavaUtil
													.getQualifiedName(
															abstractMethodDeclaration,
															true);
											if (amdQN.contains("<<null>>")) { //$NON-NLS-1$
												IStatus status = new Status(
														IStatus.WARNING,
														JavaPlugin.PLUGIN_ID,
														"Failed to compute qualifiedName:" //$NON-NLS-1$
																+ amdQN
																+ " ; searchedQualifiedName= " //$NON-NLS-1$
																+ searchedQualifiedName,
														new Exception());
												JavaPlugin.getDefault()
														.getLog().log(status);
											}
											if (searchedQualifiedName
													.startsWith(amdQN)) {
												if (searchedQualifiedName
														.equals(amdQN)) {
													resultNamedElement = abstractMethodDeclaration;
												} else {
													currentElement
															.setElement(abstractMethodDeclaration);
												}
											}
										} else if (bodyDeclaration instanceof FieldDeclaration) {
											FieldDeclaration fieldDeclaration = (FieldDeclaration) bodyDeclaration;
											Iterator<VariableDeclarationFragment> variableDeclarationFragments = fieldDeclaration
													.getFragments().iterator();
											while (variableDeclarationFragments
													.hasNext()) {
												VariableDeclarationFragment variableDeclarationFragment = variableDeclarationFragments
														.next();
												String variableDeclarationFragmentQN = JavaUtil
														.getQualifiedName(
																variableDeclarationFragment,
																false);
												if (searchedQualifiedName
														.equals(variableDeclarationFragmentQN)) {
													resultNamedElement = variableDeclarationFragment;
												}
											}
										} else if (bodyDeclaration instanceof EnumConstantDeclaration) {
											EnumConstantDeclaration enumConstantDeclaration = (EnumConstantDeclaration) bodyDeclaration;
											String enumConstantQN = JavaUtil
													.getQualifiedName(
															enumConstantDeclaration,
															false);
											if (searchedQualifiedName
													.equals(enumConstantQN)) {
												resultNamedElement = enumConstantDeclaration;
											}
										} else if (bodyDeclaration instanceof AnnotationTypeMemberDeclaration) {
											AnnotationTypeMemberDeclaration annoMemberDeclaration = (AnnotationTypeMemberDeclaration) bodyDeclaration;
											String enumConstantQN = JavaUtil
													.getQualifiedName(
															annoMemberDeclaration,
															false);
											if (searchedQualifiedName
													.equals(enumConstantQN)) {
												resultNamedElement = annoMemberDeclaration;
											}
										} else if (bodyDeclaration instanceof Initializer) {
											// Nothing to do
											assert true; // misc statement for
															// checkstyle rule
										} else {
											throw new RuntimeException(
													"Unexpected type in an AbstractTypeDeclaration contaiment: " //$NON-NLS-1$
															+ bodyDeclaration
																	.getClass()
																	.getName());
										}
									}
								}
							} else {
								end = true;
							}
						}
					} catch (Exception e) {
						RuntimeException runtimeException = new RuntimeException(
								"getNamedElementByQualifiedName failed while reading:" //$NON-NLS-1$
										+ JavaUtil.getQualifiedName(
												(ASTNode) currentElement
														.getElement(), false),
								e);
						IStatus status = new Status(IStatus.ERROR,
								JavaPlugin.PLUGIN_ID, runtimeException
										.getMessage(), runtimeException);
						JavaPlugin.getDefault().getLog().log(status);
						throw runtimeException;
					}
				}
			}
			if (JavaUtil.TRACE_NE_BY_QN) {
				System.out
						.println("End getNamedElementByQualifiedName(Model, String): result=" //$NON-NLS-1$
								+ resultNamedElement);
			}
		}
		return resultNamedElement;
	}
}
