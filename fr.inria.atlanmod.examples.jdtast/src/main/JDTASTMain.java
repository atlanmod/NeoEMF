package main;

import java.io.IOException;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import Core.CorePackage;
import Core.impl.IJavaModelImpl;
import DOM.BodyDeclaration;
import DOM.DOMPackage;
import DOM.ExtendedModifier;
import DOM.MethodDeclaration;
import DOM.Modifier;
import DOM.TypeDeclaration;
import DOM.impl.TypeDeclarationImpl;
import PrimitiveTypes.PrimitiveTypesPackage;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.INeo4emfResourceFactory;

public class JDTASTMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		INeo4emfResource resource = INeo4emfResourceFactory.eINSTANCE.createResource("./target/jtdast_set0_NEODB/", reltypes.ReltypesMappings.getInstance().getMap());
		
		EPackage.Registry.INSTANCE.put("org.amma.dsl.jdt.dom", DOMPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put("org.amma.dsl.jdt.core", CorePackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put("org.amma.dsl.jdt.primitiveTypes", PrimitiveTypesPackage.eINSTANCE);
		resource.load(null);
		INeo4emfObject root = (INeo4emfObject) resource.getContents().get(0);
		root.eGet(root.eClass().getEStructuralFeature(0));
		 ((IJavaModelImpl)root).getExternalPackageFragmentRoots();
		 System.out.println(root instanceof INeo4emfObject);
		 System.out.println(root instanceof EObject);
		System.out.println("size of instances is : "+ resource.getAllInstances(CorePackage.Literals.ITYPE).size());		
		 EList <INeo4emfObject> iTypeInstances = resource.getAllInstances(DOMPackage.Literals.TYPE_DECLARATION);
		 System.out.println("size of iTypeInstances is :" + iTypeInstances.size());
		 EList <INeo4emfObject> iBodyInstances = resource.getAllInstances(DOMPackage.Literals.BODY_DECLARATION);
		 EList<TypeDeclaration> result = new BasicEList<TypeDeclaration>();
		 boolean pass=false; 
		 	for (INeo4emfObject obj : iTypeInstances ){
		 		if (pass == true) pass = false;
		 		//((TypeDeclarationImpl)obj).getInterface();
		 		//((TypeDeclarationImpl)obj).getName().getFullyQualifiedName(); 
		 		EList<BodyDeclaration> bodyDecList = ((TypeDeclarationImpl)obj).getBodyDeclarations();
		 		filterMethodDeclaration(bodyDecList);		 		
		 		for (BodyDeclaration body : bodyDecList){
		 			if (pass == true) break;
		 			//((MethodDeclaration)body).getExtraDimensions();
		 			EList<ExtendedModifier> modifiers = ((MethodDeclaration)body).getModifiers();
		 					filterModifiers(modifiers);
		 			for (ExtendedModifier mod : modifiers){
		 				if (((Modifier)mod).getPublic()==true && ((Modifier)mod).getStatic()==true){
		 						result.add((TypeDeclaration)obj);
		 						pass = true;
		 						break;
		 				}
		 					
		 			}
		 				
		 		}
		 	}
		 
//		resource.load(null);
//		//((PhysicalElement)resource.getContents().get(0)).setPath("/root/");
//		System.out.println(((PhysicalElementImpl)resource.getContents().get(0)));
//		resource.save();
		System.out.println("size of result is :" + result.size());
		resource.shutdown();
	}



	private static void filterModifiers(EList<ExtendedModifier> modifiers) {
		EList<ExtendedModifier> result = new BasicEList<ExtendedModifier>();
		for (ExtendedModifier mod : modifiers)
			if ((mod instanceof Modifier))
				result.add(mod);
			modifiers.clear();
			modifiers.addAll(result);
	}

	private static void filterMethodDeclaration(
			EList<BodyDeclaration> bodyDecList) {
		EList<BodyDeclaration> result = new BasicEList<BodyDeclaration>();
		for (BodyDeclaration body : bodyDecList)
			if ((body instanceof MethodDeclaration))
				result.add(body);
			bodyDecList.clear();
			bodyDecList.addAll(result);
		
	}

}
