package fr.inria.atlanmod.neo4emf.codegen.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import fr.inria.atlanmod.neo4emf.codegen.CodegenUtil;

public class ReltypesClass
{
  protected static String nl;
  public static synchronized ReltypesClass create(String lineSeparator)
  {
    nl = lineSeparator;
    ReltypesClass result = new ReltypesClass();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "/**" + NL + "<<<<<<< HEAD" + NL + " *" + NL + " * ";
  protected final String TEXT_4 = "Id";
  protected final String TEXT_5 = NL + " */" + NL + "=======" + NL + " * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes" + NL + " * All rights reserved. This program and the accompanying materials" + NL + " * are made available under the terms of the Eclipse Public License v1.0" + NL + " * which accompanies this distribution, and is available at" + NL + " * http://www.eclipse.org/legal/epl-v10.html" + NL + " * " + NL + " * Contributors:" + NL + " *    Atlanmod INRIA LINA Mines Nantes - initial API and implementation" + NL + " * Descritpion ! To come" + NL + " * @author Amine BENELALLAM" + NL + " **/" + NL + ">>>>>>> e211675... Templates refactoring." + NL + "package ";
  protected final String TEXT_6 = ".reltypes;" + NL;
  protected final String TEXT_7 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * Neo4j <b>relationships</b> for model persistence." + NL + " * It provides an adapter <code>createXXX</code> method for each class of the model." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public enum Reltypes implements RelationshipType" + NL + "{";
  protected final String TEXT_8 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_9 = " copyright = ";
  protected final String TEXT_10 = ";";
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = NL + "\t";
  protected final String TEXT_13 = NL + "    \t\t";
  protected final String TEXT_14 = "," + NL + "    \t\t";
  protected final String TEXT_15 = NL + "} " + NL + "" + NL + "//Reltypes Class";
  protected final String TEXT_16 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: amine ben
 *   IBM - Initial API and implementation
 */

    stringBuffer.append(TEXT_1);
     GenPackage genPck = (GenPackage) argument; GenModel genModel= genPck.getGenModel();
 /* Trick to import java.util.* without warnings */Iterator.class.getName();
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genModel.getModelPluginID());
    stringBuffer.append(TEXT_6);
    genModel.addImport("org.neo4j.graphdb.RelationshipType");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_7);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
     for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()){
	for (GenClassifier genClassifier : genPackage.getOrderedGenClassifiers()) { 
		if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier; 
			for (GenFeature genFeature : CodegenUtil.getEAllGenFeatures(genClass)) {
    			 if(genFeature.isReferenceType()){
    		
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_14);
    		} 
       			}
       		}
       	}
    }
    stringBuffer.append(TEXT_15);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}
