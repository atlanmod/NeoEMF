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
package fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntimeListener.Event;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal.Neo4JRuntimesManager;

/**
 * @author abelgomez
 *
 */
public abstract class AbstractNeo4jRuntimeInstaller {

	private List<INeo4jRuntimeListener> listeners = new ArrayList<INeo4jRuntimeListener>();
	
	public abstract String getId();
	
	public abstract String getName();

	public abstract String getVersion();

	public abstract String getDescription();

	public abstract String getLicenseText();

	protected abstract void performInstall(IProgressMonitor monitor, IPath runtimePath) throws IOException;
	
	public void install(IProgressMonitor monitor) throws IOException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		try {
			monitor.beginTask("Installing", 1);
			IPath runtimeDataAreaPath = Neo4JRuntimesManager.INSTANCE.getRuntimeDataArea();
			IPath runtimePath = runtimeDataAreaPath.append(getVersion()).append(getId());
			FileUtils.forceMkdir(runtimePath.toFile());
			performInstall(new SubProgressMonitor(monitor, 1), runtimePath);
			Neo4JRuntimesManager.INSTANCE.initializeRuntimeMetadata(this, runtimePath);
			Neo4JRuntimesManager.INSTANCE.configureAsProjectPlugin(this, runtimePath);
		} finally {
			monitor.done();
		}
	}
	
	public void addChangeListener(INeo4jRuntimeListener listener) {
		listeners.add(listener);
	}
	
	public void removeChangeListener(INeo4jRuntimeListener listener) {
		listeners.remove(listener);
	}
	
	protected void notifyListeners() {
		for (INeo4jRuntimeListener listener : listeners) {
			listener.notifyChange(new Event(this));
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		listeners.clear();
		listeners = null;
		super.finalize();
	}
}
