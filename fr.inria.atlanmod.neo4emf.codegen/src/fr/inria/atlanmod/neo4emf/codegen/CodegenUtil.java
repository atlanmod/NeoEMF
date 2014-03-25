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
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;


public class CodegenUtil {

	public static String  getDataClassExtends (GenClass cls){
		if (cls.getClassExtends().contains("Neo4emfObject"))
			return "";
		StringBuffer str = new StringBuffer(" extends Data");
		return str.append(cls.getClassExtends().substring(9, cls.getClassExtends().length()-4)).toString();
	}

	
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
	
	public static String getListConstructorAndNeoImport(GenModel model, String listConstructor) {
		String listType = listConstructor.substring(0,listConstructor.indexOf('<'));
		model.getImportedName("fr.inria.atlanmod.neo4emf.Neo"+listType);
		return "Neo"+listConstructor;
	}
}
