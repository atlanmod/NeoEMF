package fr.inria.atlanmod.neo4emf.codegen.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

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
  protected final String TEXT_2 = "/**" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ".Reltypes;" + NL;
  protected final String TEXT_6 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * Neo4j <b>relationships</b> for model persistence." + NL + " * It provides an adapter <code>createXXX</code> method for each class of the model." + NL + " * <!-- end-user-doc -->" + NL + " * @see ";
  protected final String TEXT_7 = NL + " * @generated" + NL + " */" + NL + "public enum ";
  protected final String TEXT_8 = "Reltypes implements RelationshipType" + NL + "{";
  protected final String TEXT_9 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_10 = " copyright = ";
  protected final String TEXT_11 = ";";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "\t";
  protected final String TEXT_14 = NL + "    \t\t";
  protected final String TEXT_15 = "," + NL + "    \t\t";
  protected final String TEXT_16 = NL + "} " + NL + "" + NL + "//";
  protected final String TEXT_17 = NL;

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

    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel();
 String namePrefix = genPackage.getReflectionPackageName();
	    namePrefix= namePrefix.substring(0, 1).toUpperCase()+namePrefix.substring(1);
	     /* Trick to import java.util.* without warnings */Iterator.class.getName();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_5);
    genModel.addImport("org.neo4j.graphdb.RelationshipType");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(namePrefix);
    stringBuffer.append(TEXT_8);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
     for (GenClassifier genClassifier : genPackage.getOrderedGenClassifiers()) { 
	if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier; 
		for (GenFeature genFeature : genClass.getAllGenFeatures()) {
    		if (genFeature.isReferenceType()){
    		
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_15);
    		} 
       			}
       		}
       	}
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getAdapterFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_17);
    return stringBuffer.toString();
  }
}
