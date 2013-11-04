/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neo4emf.neo4jresolver.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;

import fr.inria.atlanmod.neo4emf.neo4jresolver.logger.Logger;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.AbstractNeo4jRuntimeInstaller;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal.Neo4JRuntimesManager;

/**
 * @author abelgomez
 *
 */
public class ImportNeo4jRuntimeWizard extends Wizard {

	private InstallRuntimesWizardData data = new InstallRuntimesWizardData(); 

	public ImportNeo4jRuntimeWizard() {
		setNeedsProgressMonitor(true);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) {
					if (monitor == null) monitor = new NullProgressMonitor();
					try {
						List<AbstractNeo4jRuntimeInstaller> installers = data.getInstallersToInstall();
						monitor.beginTask("Installing", installers.size());
						for (AbstractNeo4jRuntimeInstaller installer : installers) {
							monitor.setTaskName(NLS.bind("Installing {0}", installer.getName()));
							IPath runtimeDataAreaPath = Neo4JRuntimesManager.INSTANCE.getRuntimeDataArea();
							IPath runtimePath = runtimeDataAreaPath.append(installer.getVersion()).append(installer.getId());
							FileUtils.forceMkdir(runtimePath.toFile());
							installer.install(new SubProgressMonitor(monitor, 1), runtimePath);
							if (monitor.isCanceled()) return;
							Neo4JRuntimesManager.INSTANCE.initializeRuntimeMetadata(installer, runtimePath);
						}
					} catch (IOException e) {
						Logger.log(Logger.SEVERITY_ERROR, "Unable to create directory for runtime", e);
					} finally {
						monitor.done();
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			Logger.log(Logger.SEVERITY_ERROR, e);
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();
		AvailableNeo4jRuntimesWizardPage availableRuntimesPage = new AvailableNeo4jRuntimesWizardPage(data);
		ConfirmInstallWizardPage confirmPage = new ConfirmInstallWizardPage(data);
		addPage(availableRuntimesPage);
		addPage(confirmPage);
	}
}
