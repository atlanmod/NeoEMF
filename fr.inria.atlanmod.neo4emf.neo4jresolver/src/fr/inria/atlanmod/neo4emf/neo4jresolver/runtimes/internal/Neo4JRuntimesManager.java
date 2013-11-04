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
package fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import fr.inria.atlanmod.neo4emf.neo4jresolver.Neo4jResolverPlugin;
import fr.inria.atlanmod.neo4emf.neo4jresolver.collections.ObservableArrayList;
import fr.inria.atlanmod.neo4emf.neo4jresolver.logger.Logger;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.AbstractNeo4jRuntimeInstaller;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntimesManager;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.JarFilter;
import fr.inria.atlanmod.neo4emf.neo4jresolver.wizards.ImportNeo4jRuntimeWizard;

/**
 * @author abelgomez
 *
 */
public class Neo4JRuntimesManager implements INeo4jRuntimesManager {

	/**
	 * 
	 */
	private static final String EXPORT_PACKAGE = "Export-Package";
	/**
	 * 
	 */
	private static final String BUNDLE_CLASS_PATH = "Bundle-ClassPath";
	/**
	 * 
	 */
	private static final String BUNDLE_VERSION = "Bundle-Version";
	/**
	 * 
	 */
	private static final String BUNDLE_SYMBOLIC_NAME = "Bundle-SymbolicName";
	/**
	 * 
	 */
	private static final String BUNDLE_NAME = "Bundle-Name";
	/**
	 * 
	 */
	private static final String BUNDLE_MANIFEST_VERSION = "Bundle-ManifestVersion";
	private static final String MANIFEST_MF = "MANIFEST.MF";
	private static final String META_INF = "META-INF";
	
	private static final String RUNTIMES_DIR = "runtimes";
	private static final String INSTALLERS_EXTENSION_POINT_ID = "fr.inria.atlanmod.neo4emf.neo4jresolver.neo4jInstallers";
	private static final String ATT_ID = "id";
	private static final String ATT_NAME = "name";
	private static final String ATT_VERSION = "version";
	private static final String ATT_DESCRIPTION = "description";
	private static final String ATT_URL = "url";
	private static final String ATT_PATH = "path";
	private static final String ATT_HANDLER = "handler";
	private static final String CHILD_LICENSE = "License";
	private static final String CHILD_ZIPENTRY= "ZipEntry";
	
	public static final Neo4JRuntimesManager INSTANCE;
	
	private static List<INeo4jRuntime> installedRuntimes;
	private static List<AbstractNeo4jRuntimeInstaller> installers;

	private static IPath dataAreaPath;
	
	static {
		INSTANCE = new Neo4JRuntimesManager();
	}
	
	public List<INeo4jRuntime> getRuntimes() {
		if (installedRuntimes == null) {
			load();
		}
		return installedRuntimes;
	}
	
	/* (non-Javadoc)
	 * @see fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal.INeo4jRuntimesManager#getRuntime(java.lang.String)
	 */
	@Override
	public INeo4jRuntime getRuntime(String id) {
		for (INeo4jRuntime runtime : getRuntimes()) {
			if (runtime.getId() != null && runtime.getId().equals(id)) {
				return runtime;
			}
		} return null;
	}
	
	/* (non-Javadoc)
	 * @see fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal.INeo4jRuntimesManager#launchInstallRuntimesWizard()
	 */
	@Override
	public void launchInstallRuntimesWizard() {
		final Display display = Display.getCurrent() != null ? Display.getCurrent() : Display.getDefault();
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				Shell shell = display.getActiveShell();
				ImportNeo4jRuntimeWizard wizard = new ImportNeo4jRuntimeWizard();
				WizardDialog dialog = new WizardDialog(shell, wizard);
				dialog.setTitle(wizard.getWindowTitle());
				dialog.setBlockOnOpen(true);
				dialog.open();
			}
		});
	}
	
	public List<AbstractNeo4jRuntimeInstaller> getInstallers() {
		if (installers == null) {
			installers = new ArrayList<AbstractNeo4jRuntimeInstaller>();
			IConfigurationElement[] elements = Platform.getExtensionRegistry().getConfigurationElementsFor(INSTALLERS_EXTENSION_POINT_ID);
			for (IConfigurationElement element : elements) {
				try {
					AbstractNeo4jRuntimeInstaller installer = null; 
					if (element.getAttribute(ATT_HANDLER) != null) {
						installer = (AbstractNeo4jRuntimeInstaller) element.createExecutableExtension(ATT_HANDLER);
					} else {
						Neo4jZippedInstaller zippedInstaller = new Neo4jZippedInstaller();
						zippedInstaller.setId(element.getAttribute(ATT_ID));
						zippedInstaller.setName(element.getAttribute(ATT_NAME));
						zippedInstaller.setVersion(element.getAttribute(ATT_VERSION));
						zippedInstaller.setDescription(element.getAttribute(ATT_DESCRIPTION));
						zippedInstaller.setId(element.getAttribute(ATT_ID));
						zippedInstaller.setUrl(new URL(element.getAttribute(ATT_URL)));
						for (IConfigurationElement childElement : element.getChildren(CHILD_LICENSE)) {
							zippedInstaller.setLicenseText(childElement.getValue());
						}
						for (IConfigurationElement childElement : element.getChildren(CHILD_ZIPENTRY)) {
							zippedInstaller.getJarFiles().add(childElement.getAttribute(ATT_PATH));
						}
						installer = zippedInstaller;
					}
					installers.add(installer);
				} catch (CoreException | MalformedURLException | InvalidRegistryObjectException e) {
					Logger.log(Logger.SEVERITY_ERROR, e);
				}
			}
		} 
		return installers;
	}
	
	private void load() {
		if (installedRuntimes == null) {
			 installedRuntimes = new ObservableArrayList<INeo4jRuntime>();	
		} else {
			installedRuntimes.clear();
		}
		File runtimeDataDir = getRuntimeDataArea().toFile();
		if (runtimeDataDir.isDirectory()) {
			for (File versionDir : runtimeDataDir.listFiles()) {
				if (versionDir.isDirectory()) {
					for (File idDir : versionDir.listFiles()) {
						INeo4jRuntime runtimeFromLocation = getRuntimeFromLocation(new Path(idDir.getAbsolutePath()));
						if (runtimeFromLocation != null) {
							installedRuntimes.add(runtimeFromLocation);
						}
					}
				}
			}
		}
	}
	
	public IPath getRuntimeDataArea() {
		if (dataAreaPath == null) {
			dataAreaPath = Neo4jResolverPlugin.getDefault().getStateLocation().append(RUNTIMES_DIR);
		}
		return dataAreaPath;
	}
	
	public INeo4jRuntime getRuntimeFromLocation(IPath path) {
		INeo4jRuntime runtime =  null;
		File manifestMF = path.append(META_INF).append(MANIFEST_MF).toFile();
		if (manifestMF.exists()) {
			FileInputStream stream = null;
			try {
				stream = new FileInputStream(manifestMF);
				Manifest manifest = new Manifest(stream);
				String id = manifest.getMainAttributes().getValue(BUNDLE_SYMBOLIC_NAME);
				String version = manifest.getMainAttributes().getValue(BUNDLE_VERSION);
				runtime = new Neo4jRuntime(version, id, path);
			} catch (Exception e) {
				Logger.log(Logger.SEVERITY_ERROR, e);
			} finally {
				IOUtils.closeQuietly(stream);
			}
		}
		return runtime;
	}
	
	public void initializeRuntimeMetadata(AbstractNeo4jRuntimeInstaller installer, IPath path) {
		File dirFile = path.toFile();
		File manifestFile = path.append(META_INF).append(MANIFEST_MF).toFile();
		FileOutputStream outputStream = null;
		try {
			FileUtils.forceMkdir(manifestFile.getParentFile());
			outputStream = new FileOutputStream(manifestFile);
			Manifest manifest = new Manifest();
			Attributes atts = manifest.getMainAttributes();
			atts.put(Attributes.Name.MANIFEST_VERSION, "1.0");
			atts.putValue(BUNDLE_MANIFEST_VERSION, "2");
			atts.putValue(BUNDLE_NAME, installer.getName());
			atts.putValue(BUNDLE_SYMBOLIC_NAME, installer.getId());
			atts.putValue(BUNDLE_VERSION, installer.getVersion());
			atts.putValue(BUNDLE_CLASS_PATH, buildJarFilesList(dirFile));
			atts.putValue(EXPORT_PACKAGE, buildPackagesList(dirFile));
			manifest.write(outputStream);
			load();
		} catch (Exception e) {
			Logger.log(Logger.SEVERITY_ERROR, e);
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
	}
	
	private String buildJarFilesList(File directory) {
		final String SEPARATOR = ", ";
		StringBuilder builder = new StringBuilder();
		if (directory != null && directory.isDirectory()) {
			for (File file : directory.listFiles(new JarFilter())) {
				builder.append(file.getName());
				builder.append(SEPARATOR);
			}
			if (builder.length() >= SEPARATOR.length()) {
				builder.setLength(builder.length() - SEPARATOR.length());
			}
			return builder.toString();
		} else {
			return null;
		}
	}
	
	private String buildPackagesList(File directory) {
		Set<String> packages = new HashSet<>();
		final String SEPARATOR = ", ";
		StringBuilder builder = new StringBuilder();
		if (directory != null && directory.isDirectory()) {
			for (File file : directory.listFiles(new JarFilter())) {
				ZipInputStream zipStream = null;
				try {
					zipStream = new ZipInputStream(new FileInputStream(file));
					ZipEntry entry;
			        while((entry = zipStream.getNextEntry()) != null) {
			        	if (FilenameUtils.getExtension(entry.getName()).equals("class")) {
			        		packages.add(FilenameUtils.getPathNoEndSeparator(
			        					entry.getName()).replace('/', '.'));		        		
			        	}
			        }
				} catch (IOException e) {
					Logger.log(Logger.SEVERITY_ERROR, e);
				} finally {
					IOUtils.closeQuietly(zipStream);
				}
			}
			for (String pkg : packages) {
				builder.append(pkg);
				builder.append(SEPARATOR);
			}
			if (builder.length() >= SEPARATOR.length()) {
				builder.setLength(builder.length() - SEPARATOR.length());
			}
			return builder.toString();
		} else {
			return null;
		}
	}
	
}
