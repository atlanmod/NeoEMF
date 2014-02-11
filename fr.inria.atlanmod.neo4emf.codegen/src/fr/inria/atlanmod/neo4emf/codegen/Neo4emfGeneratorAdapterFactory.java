/**
 * Copyright (c) 2006, 2013 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package fr.inria.atlanmod.neo4emf.codegen;



import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;

public class Neo4emfGeneratorAdapterFactory extends GenModelGeneratorAdapterFactory
{
  private static final boolean DISABLE = "false".equals(System.getProperty("fr.inria.atlanmod.neo4emf.codegen"));

  @Override
  public Adapter createGenPackageAdapter() { 
	  	if (genPackageGeneratorAdapter == null && !DISABLE){
	  		genPackageGeneratorAdapter = new GenReltypesGeneratorAdapter(this);
	  	}
	  	return genPackageGeneratorAdapter;
//	  return null;
  }
  
  @Override
  public Adapter createGenEnumAdapter() { return null; }

  @Override
  public Adapter createGenModelAdapter()
  {
//	  if (genModelGeneratorAdapter== null && !DISABLE)
//		{
//		genPackageGeneratorAdapter =  new GenReltypesGeneratorAdapter(this);
//		}
//	return genModelGeneratorAdapter; 
	  return null;
  }
  
  @Override
  public Adapter createGenClassAdapter() {
	  return null;
	  }
  
}
