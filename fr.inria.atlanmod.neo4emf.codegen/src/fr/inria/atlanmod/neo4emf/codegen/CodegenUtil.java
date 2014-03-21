/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neo4emf.codegen;

import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;


public class CodegenUtil {

	/**
	 * Getter to the attribute inner class name.
	 * @param genClass a GenClass instance.
	 * @return the inner class name.
	 */
	public static String getAttributeClassName(GenClass genClass) {
		return "Data"+genClass.getInterfaceName();
	}
	
	/**
	 * Getter to the reference inner class name.
	 * @param genClass a GenClass instance.
	 * @return the inner class name.
	 */
	public static String getReferenceClassName(GenClass genClass) {
		return genClass.getInterfaceName()+"References";
	}
	
	/**
	 * Creates and returns a string containing "extends", followed by the name of the super
	 * classof the attribute class, or an empty string.
	 * 
	 * @param cls a GenClass instance, the context
	 * @return the "extends <superclass" string
	 */
	public static String  getAttributeClassExtends (GenClass cls){
		boolean hasSuperClass = !cls.getEcoreClass().getESuperTypes().isEmpty();
		
		if (hasSuperClass) {
			return " extends "+ getAttributeClassName(cls.getBaseGenClass());
		} else {
			return "";
		}
	}

	/**
	 * Creates and returns a string containing "extends", followed by the name of the super
	 * class of the reference class, or an empty string.
	 * 
	 * @param cls a GenClass instance, the context
	 * @return the "extends <superclass" string
	 */
	public static String  getReferenceClassExtends (GenClass cls){
		boolean hasSuperClass = !cls.getEcoreClass().getESuperTypes().isEmpty();
		
		if (hasSuperClass) {
			return " extends "+ getAttributeClassName(cls.getBaseGenClass());
		} else {
			return "";
		}
	}
	
	/**
	 * Returns the AttributeClass getter method name.
	 * @return
	 */
	public static String getAttributeClassGetter() {
		return "getData()";
	}
	
	
	/**
	 * TODO Comment this code.
	 * FIXME This method may have a side effect: we are adding elements to an existing collection,
	 * the one returned from getAllGenFeatures(). 
	 * 
	 * @param genCls
	 * @return
	 */
	public static List<GenFeature> getEAllGenFeatures(final GenClass genCls){
		List<GenFeature> result = genCls.getAllGenFeatures();
		List<GenFeature> declaredFeatures = genCls.getDeclaredFieldGenFeatures();
		for (GenFeature feat : declaredFeatures){
			if ( ! result.contains(feat)){
				result.add(feat);
			}
		}
		return result;
	}
}
