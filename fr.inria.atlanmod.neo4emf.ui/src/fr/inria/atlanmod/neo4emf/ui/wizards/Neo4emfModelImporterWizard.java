package fr.inria.atlanmod.neo4emf.ui.wizards;

import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterDetailPage;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterPackagePage;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterWizard;

import fr.inria.atlanmod.neo4emf.ui.migrator.Neo4emfImporter;

public class Neo4emfModelImporterWizard extends ModelImporterWizard {
	public Neo4emfModelImporterWizard() {
	}

	@Override
	protected ModelConverter createModelConverter() {
		return new Neo4emfImporter();
	}

	@Override
	public void addPages() {
		ModelImporterDetailPage detailPage = new ModelImporterDetailPage(getModelImporter(), "EcoreModel");
		detailPage.setTitle("Ecore &Import");
		detailPage.setDescription(detailPage.showGenModel()
				? "Specify one or more '.ecore' or '.emof' URIs, try to load them, and choose a file name for the generator model"
				: "Specify one or more '.ecore' or '.emof' URIs and try to load them");
		addPage(detailPage);

		ModelImporterPackagePage packagePage = new ModelImporterPackagePage(getModelImporter(), "EcorePackages");
		packagePage.setShowReferencedGenModels(true);
		addPage(packagePage);
	}
}
