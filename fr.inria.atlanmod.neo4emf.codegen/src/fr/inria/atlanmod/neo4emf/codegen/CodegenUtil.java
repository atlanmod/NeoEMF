package fr.inria.atlanmod.neo4emf.codegen;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;

public class CodegenUtil {

	public static String  getDataClassExtends (GenClass cls){
		if (cls.getClassExtends().contains("Neo4emfObject"))
			return "";
		StringBuffer str = new StringBuffer(" extends Data");
		return str.append(cls.getClassExtends().substring(9, cls.getClassExtends().length()-4)).toString();
	}
}
