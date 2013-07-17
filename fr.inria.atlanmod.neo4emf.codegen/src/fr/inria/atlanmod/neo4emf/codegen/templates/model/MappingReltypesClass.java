package fr.inria.atlanmod.neo4emf.codegen.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class MappingReltypesClass
{
  protected static String nl;
  public static synchronized MappingReltypesClass create(String lineSeparator)
  {
    nl = lineSeparator;
    MappingReltypesClass result = new MappingReltypesClass();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ".Reltypes;" + NL;
  protected final String TEXT_6 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * Neo4j <b>relationships mapping</b> for model persistence." + NL + " * It provides hashmaps to map relationships to the appropriate feature ID." + NL + " * <!-- end-user-doc -->" + NL + " * @see ";
  protected final String TEXT_7 = NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_8 = "NeoMappings " + NL + "{";
  protected final String TEXT_9 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_10 = " copyright = ";
  protected final String TEXT_11 = ";";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "\tprivate static ";
  protected final String TEXT_14 = "NeoMappings instance;" + NL + "\t" + NL + "\t" + NL + "\t/**" + NL + "\t * " + NL + "\t * @generated" + NL + "\t */" + NL + "\t " + NL + "\tpublic static ";
  protected final String TEXT_15 = "NeoMappings getInstance(){" + NL + "\t\tif (instance == null)" + NL + "\t\t\treturn new ";
  protected final String TEXT_16 = "NeoMappings ();" + NL + "\t\telse return instance;" + NL + "\t\t}" + NL + "\t\t" + NL + "\t/**" + NL + "\t *getter of the Map" + NL + "\t * @generated" + NL + "\t */" + NL + "\t\tpublic Map <Point,RelationshipType> getMap(){" + NL + "\t\treturn reference2relation;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * constructor of Relationship type mappings" + NL + "\t * @generated" + NL + "\t */" + NL + "private final Map <Point,RelationshipType> reference2relation;" + NL + "\t" + NL + "\t\t" + NL + "\t\tprivate ";
  protected final String TEXT_17 = "NeoMappings (){" + NL + "\t\t\treference2relation= new HashMap <Point,RelationshipType>();";
  protected final String TEXT_18 = NL + "\treference2relation.put(new Point(";
  protected final String TEXT_19 = ".";
  protected final String TEXT_20 = ",";
  protected final String TEXT_21 = ".";
  protected final String TEXT_22 = "),";
  protected final String TEXT_23 = "Reltypes.";
  protected final String TEXT_24 = ");" + NL + "\t\t\t\t\t";
  protected final String TEXT_25 = NL + "\t\t}" + NL + "" + NL + "} " + NL + "\t" + NL + "//";
  protected final String TEXT_26 = NL;

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
    genModel.addImport("java.awt.Point");
    genModel.addImport("java.util.Map");
    genModel.addImport("java.util.HashMap");
    genModel.addImport(genPackage.getReflectionPackageName()+"."+genPackage.getPackageInterfaceName());
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
    stringBuffer.append(namePrefix);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(namePrefix);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(namePrefix);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(namePrefix);
    stringBuffer.append(TEXT_17);
     for (GenClassifier genClassifier : genPackage.getOrderedGenClassifiers()) { 
	if (genClassifier instanceof GenClass) { GenClass cls = (GenClass)genClassifier; 
		for (GenFeature feat : cls.getAllGenFeatures()) {
    		if (feat.isReferenceType()){
    		
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cls.getClassifierID()
		);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cls.getFeatureID(feat));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(namePrefix);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cls.getFeatureID(feat));
    stringBuffer.append(TEXT_24);
    }
					}
				}
			}	
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getAdapterFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_26);
    return stringBuffer.toString();
  }
}
