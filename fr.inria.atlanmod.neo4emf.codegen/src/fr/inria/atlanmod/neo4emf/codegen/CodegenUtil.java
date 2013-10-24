package fr.inria.atlanmod.neo4emf.codegen;

import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;

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
}
