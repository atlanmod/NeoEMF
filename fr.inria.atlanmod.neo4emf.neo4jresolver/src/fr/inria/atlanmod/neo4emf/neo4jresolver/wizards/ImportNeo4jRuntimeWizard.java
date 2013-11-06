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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;

import fr.inria.atlanmod.neo4emf.neo4jresolver.logger.Logger;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.AbstractNeo4jRuntimeInstaller;

/**
 * @author abelgomez
 *
 */
public class ImportNeo4jRuntimeWizard extends Wizard {

	private InstallRuntimesWizardData data = new InstallRuntimesWizardData();
	private boolean result;

	public ImportNeo4jRuntimeWizard() {
		setNeedsProgressMonitor(true);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		result = true;
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor) {
					if (monitor == null) monitor = new NullProgressMonitor();
					try {
						List<AbstractNeo4jRuntimeInstaller> installers = data.getInstallersToInstall();
						monitor.beginTask("Installing", installers.size());
						
						for (AbstractNeo4jRuntimeInstaller installer : installers) {
							monitor.setTaskName(NLS.bind("Installing {0}", installer.getName()));
							try {
								installer.install(new SubProgressMonitor(monitor, 1));
							} catch (IOException e) {
								Logger.log(Logger.SEVERITY_ERROR, NLS.bind("Unable to install Neo4J v.{0}", installer.getVersion()), e);
							}
							if (monitor.isCanceled()) {
								result = false;
								return;
							}
						}
					} finally {
						monitor.done();
					}
				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			Logger.log(Logger.SEVERITY_ERROR, e);
		}
		return result;
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
