package fr.inria.atlanmod.neo4emf.codegen.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import fr.inria.atlanmod.neo4emf.codegen.CodegenUtil;

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
  protected final String TEXT_5 = ".reltypes;" + NL;
  protected final String TEXT_6 = NL + NL + "\t/**" + NL + " \t* <!-- begin-user-doc -->" + NL + " \t* Neo4j <b>relationships mapping</b> for model persistence." + NL + " \t* It provides hashmaps to map relationships to the appropriate feature ID." + NL + " \t* <!-- end-user-doc -->" + NL + " \t* @generated" + NL + " \t*/" + NL + "public class ReltypesMappings {";
  protected final String TEXT_7 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_8 = " copyright = ";
  protected final String TEXT_9 = ";";
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = NL + "\tprivate static ReltypesMappings instance;" + NL + "\t" + NL + "\t" + NL + "\t/**" + NL + "\t * " + NL + "\t * @generated" + NL + "\t */" + NL + "\t " + NL + "\tpublic static ReltypesMappings getInstance(){" + NL + "\t\tif (instance == null)" + NL + "\t\t\treturn new ReltypesMappings ();" + NL + "\t\telse return instance;" + NL + "\t\t}" + NL + "\t\t" + NL + "\t/**" + NL + "\t *getter of the Map" + NL + "\t * @generated" + NL + "\t */" + NL + "\t\tpublic Map<String,Map<Point,RelationshipType>> getMap(){" + NL + "\t\treturn reference2relation;" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * constructor of Relationship type mappings" + NL + "\t * @generated" + NL + "\t */" + NL + "\t\tprivate final Map<String,Map<Point,RelationshipType>> reference2relation;" + NL + "\t" + NL + "\t\tprivate ReltypesMappings (){" + NL + "\t\t\t" + NL + "\t\t\treference2relation= new HashMap<String,Map<Point,RelationshipType>>();\t\t";
  protected final String TEXT_12 = NL + "\t\t" + NL + "\t\t\t" + NL + "\t\t";
  protected final String TEXT_13 = NL + "\t\t\t\t\tMap<Point,RelationshipType> map";
  protected final String TEXT_14 = " = new HashMap<Point,RelationshipType>();" + NL + "\t\t\t\t";
  protected final String TEXT_15 = NL + "\t\t\tmap";
  protected final String TEXT_16 = ".put(new Point(";
  protected final String TEXT_17 = ".";
  protected final String TEXT_18 = ",";
  protected final String TEXT_19 = ".";
  protected final String TEXT_20 = "),Reltypes.";
  protected final String TEXT_21 = ");" + NL + "\t\t\t\t\t";
  protected final String TEXT_22 = NL + "\t\t\treference2relation.put(";
  protected final String TEXT_23 = ".eNS_URI,map";
  protected final String TEXT_24 = ");" + NL + "\t\t";
  protected final String TEXT_25 = NL + "\t\t}" + NL + "\t\t" + NL + "\t/**" + NL + "\t* Getting a Relationship from an eRef belonging to " + NL + "\t* an {@link EObject} eObject" + NL + "\t*@param eObject {@link EObject}" + NL + "\t*@param eRef {@link EReference}" + NL + "\t*@generated" + NL + "\t*/" + NL + "\t//public RelationshipType getReltype(EObject eObject, EReference eRef) {" + NL + "\t\t//\tString key = eObject.eClass().getEPackage().getNsURI();\t\t" + NL + "\t\t\t//return getMap().get(key).get(new Point(eObject.eClass().getClassifierID(), eRef.getFeatureID()));" + NL + "\t\t//}" + NL + "} " + NL + "\t" + NL + "//Reltypes Class";
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
 * Contributors: Amine BENELALLAM
 *   IBM - Initial API and implementation
 */

     GenPackage genPck = (GenPackage) argument; GenModel genModel= genPck.getGenModel();
 
	     /* Trick to import java.util.* without warnings */Iterator.class.getName();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPck.getInterfacePackageName());
    stringBuffer.append(TEXT_5);
    genModel.addImport("org.neo4j.graphdb.RelationshipType");
    genModel.addImport("fr.inria.atlanmod.neo4emf.Point");
    genModel.addImport("java.util.Map");
    genModel.addImport("java.util.HashMap");
    genModel.markImportLocation(stringBuffer);
     for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers())
	 genModel.addImport(genPackage.getReflectionPackageName()+"."+genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_6);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
     for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()){ int i=0;
    stringBuffer.append(TEXT_12);
    for (GenClassifier genClassifier : genPackage.getOrderedGenClassifiers()) { 
			if (genClassifier instanceof GenClass) {
				i++; if (i == 1){
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_14);
    } 
				GenClass cls = (GenClass)genClassifier; 
				for (GenFeature feat : CodegenUtil.getEAllGenFeatures(cls)) {
    				if(feat.isReferenceType()){
    				
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cls.getClassifierID()
		);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cls.getFeatureID(feat));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cls.getFeatureID(feat));
    stringBuffer.append(TEXT_21);
    		}
						}
					}
				}
				if (i > 0){
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_24);
    }
		}
    stringBuffer.append(TEXT_25);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_26);
    return stringBuffer.toString();
  }
}
