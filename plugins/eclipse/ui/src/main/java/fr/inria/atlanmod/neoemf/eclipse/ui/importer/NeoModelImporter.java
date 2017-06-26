/*
 * Copyright (c) 2008-2017 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eike Stepper - initial API and implementation
 *     Atlanmod INRIA LINA Mines Nantes - Adapted to NeoEMF models
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.importer;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.util.ConverterUtil;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.importer.ModelImporter;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

/**
 *
 */
public class NeoModelImporter extends ModelImporter {

    /**
     *
     */
    public static final String IMPORTER_ID = NeoModelImporter.class.getName();

    @Override
    public String getID() {
        return IMPORTER_ID;
    }

    @Override
    protected void handleOriginalGenModel() throws DiagnosticException {
        URI genModelURI = getOriginalGenModel().eResource().getURI();
        StringBuilder text = new StringBuilder();

        getOriginalGenModel().getForeignModel().stream()
                .filter(value -> value.endsWith(".ecore") || value.endsWith(".emof"))
                .map(URI::createURI)
                .forEach(v -> text.append(makeAbsolute(v, genModelURI).toString()).append(' '));

        if (text.length() == 0) {
            List<URI> locations = new UniqueEList<>();

            getOriginalGenModel().getGenPackages().stream()
                    .map(p -> p.getEcorePackage().eResource().getURI())
                    .filter(locations::add)
                    .map(u -> URI.createURI(u.toString()))
                    .forEach(u -> text.append(makeAbsolute(u, genModelURI).toString()).append(' '));
        }

        setModelLocation(text.toString().trim());
    }

    @Override
    protected Diagnostic doComputeEPackages(Monitor monitor) throws Exception {
        Diagnostic diagnostic = Diagnostic.OK_INSTANCE;

        List<URI> locationURIs = getModelLocationURIs();
        if (locationURIs.isEmpty()) {
            diagnostic = new BasicDiagnostic(Diagnostic.ERROR, IMPORTER_ID, 0, "Specify a valid Ecore model and try loading again", null);
        }
        else {
            monitor.beginTask("", 2);
            monitor.subTask(MessageFormat.format("Loading {0}", locationURIs));

            ResourceSet resourceSet = createResourceSet();

            locationURIs.forEach(u -> resourceSet.getResource(u, true));

            EcoreUtil.resolveAll(resourceSet);

            resourceSet.getResources().forEach(r -> getEPackages().addAll(EcoreUtil.getObjectsByType(r.getContents(), EcorePackage.Literals.EPACKAGE)));

            BasicDiagnostic diagnosticChain = new BasicDiagnostic(ConverterPlugin.ID, ConverterUtil.ACTION_MESSAGE_NONE, "Problems were detected while validating and converting the Ecore models", null);

            getEPackages().forEach(p -> Diagnostician.INSTANCE.validate(p, diagnosticChain));

            if (diagnosticChain.getSeverity() != Diagnostic.OK) {
                diagnostic = diagnosticChain;
            }
        }

        return diagnostic;
    }

    @Override
    public void addToResource(EPackage ePackage, ResourceSet resourceSet) {
        if (nonNull(ePackage.eResource()) && nonNull(getGenModel().eResource()) && !Objects.equals(ePackage.eResource().getURI().trimSegments(1), getGenModel().eResource().getURI().trimSegments(1))) {
            ePackage.eResource().getContents().remove(ePackage);
        }

        super.addToResource(ePackage, resourceSet);
    }

    @Override
    protected void adjustGenModel(Monitor monitor) {
        super.adjustGenModel(monitor);

        GenModel genModel = getGenModel();

        URI genModelUri = createFileURI(getGenModelPath().toString());

        getModelLocationURIs().stream()
                .map(u -> makeRelative(u, genModelUri).toString())
                .forEach(u -> genModel.getForeignModel().add(u));

        GenModels.adjust(genModel);
    }
}
