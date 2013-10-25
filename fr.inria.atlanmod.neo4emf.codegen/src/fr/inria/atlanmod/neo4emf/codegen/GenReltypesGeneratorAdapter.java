package fr.inria.atlanmod.neo4emf.codegen;

import java.util.List;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;

public class GenReltypesGeneratorAdapter extends GenBaseGeneratorAdapter {
	protected static final int REL_TYPES_ID = 0;
	protected static final int MAP_REL_TYPES_ID = 1;

	protected static final JETEmitterDescriptor[] JET_EMITTER_DESCRIPTORS = {
			new JETEmitterDescriptor("model/ReltypesClass.javajet", "fr.inria.atlanmod.neo4emf.codegen.templates.model.ReltypesClass"),
			new JETEmitterDescriptor("model/MappingReltypesClass.javajet", "fr.inria.atlanmod.neo4emf.codegen.templates.model.MappingReltypesClass") };

	protected JETEmitterDescriptor[] getJETEmitterDescriptors() {
		return JET_EMITTER_DESCRIPTORS;
	}

	public GenReltypesGeneratorAdapter() {
		super();
	}

	public GenReltypesGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	@Override
	public boolean canGenerate(Object object, Object projectType) {
		// return MODEL_PROJECT_TYPE.equals(projectType)
		// ? ( hasReferences((GenPackage) object) ? super.canGenerate(object,
		// projectType) : false ): false;
		return MODEL_PROJECT_TYPE.equals(projectType) ? super.canGenerate(object, projectType) : false;
	}

	private boolean hasReferences(GenPackage object) {
		for (GenClass genClass : object.getOrderedGenClasses()) {
			for (GenFeature feat : genClass.getAllGenFeatures())
				if (feat.isReferenceType())
					return true;
		}
		return false;
	}

	@Override
	protected Diagnostic generateModel(Object object, Monitor monitor) {
		GenPackage genPackage = (GenPackage) object;
		GenModel genModel = genPackage.getGenModel();

		// if (hasReferences(genPackage)){
		monitor.beginTask("", 2);
		monitor.subTask(message);
		ensureProjectExists(genModel.getModelDirectory(), genModel, MODEL_PROJECT_TYPE, genModel.isUpdateClasspath(), createMonitor(monitor, 1));

		generateJava(genModel.getModelDirectory(),
				genModel.getModelPluginID() + ".reltypes", "Reltypes",
				getJETEmitter(getJETEmitterDescriptors(), REL_TYPES_ID),
				null,
				createMonitor(monitor, 1));

		generateJava(genModel.getModelDirectory(),
				genModel.getModelPluginID() + ".reltypes", "ReltypesMappings",
				getJETEmitter(getJETEmitterDescriptors(), MAP_REL_TYPES_ID),
				null,
				createMonitor(monitor, 1));
		// }
		return Diagnostic.OK_INSTANCE;
	}

	@Override
	protected void addBaseTemplatePathEntries(List<String> templatePath) {
		templatePath.add(Neo4emfGeneratorPlugin.INSTANCE.getBaseURL().toString() + "templates");
		super.addBaseTemplatePathEntries(templatePath);
	}

	@Override
	protected void addClasspathEntries(JETEmitter jetEmitter) throws JETException {
		super.addClasspathEntries(jetEmitter);
		jetEmitter.addVariable("NEO4EMF_GENERATOR", Neo4emfGeneratorPlugin.ID);
	}
}