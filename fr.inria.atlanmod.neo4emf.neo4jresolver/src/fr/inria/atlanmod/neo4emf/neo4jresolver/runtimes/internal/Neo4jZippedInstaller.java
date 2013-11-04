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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.osgi.util.NLS;

import fr.inria.atlanmod.neo4emf.neo4jresolver.logger.Logger;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.AbstractNeo4jRuntimeInstaller;

/**
 * @author abelgomez
 *
 */
public class Neo4jZippedInstaller extends AbstractNeo4jRuntimeInstaller {

	protected static final int TIMEOUT = 10000;
	private String id;
	private String name;
	private String description;
	private String licenseText;
	private URL url;
	private String version;
	private List<String> jarFiles = new ArrayList<String>();
	private static final int ERROR = -1;
	private static final int UNDEFINED = -2;
	private volatile int size = UNDEFINED;
	
	public Neo4jZippedInstaller() {
		Thread checkSizeThread = new Thread() {
			@Override
			public void run() {
				try {
					URLConnection connection = getUrl().openConnection();
					connection.setConnectTimeout(1000);
					connection.setReadTimeout(1000);
					size = connection.getContentLength();
				} catch (IOException e) {
					size = ERROR;
				}
				notifyListeners();
			}
		};
		checkSizeThread.start();
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the licenseText
	 */
	public String getLicenseText() {
		return licenseText;
	}
	
	/**
	 * @param licenseText the licenseText to set
	 */
	public void setLicenseText(String licenseText) {
		this.licenseText = licenseText;
	}
	
	/**
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}
	
	/**
	 * @param url the url to set
	 */
	public void setUrl(URL url) {
		this.url = url;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return getDescriptionText();
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the jarFiles
	 */
	public List<String> getJarFiles() {
		return jarFiles;
	}
	
	public String getDescriptionText() {
		StringBuilder builder = new StringBuilder();
		builder.append(description);
		int size = getSize();
		builder.append("\n");
		builder.append("URL: ");
		builder.append(getUrl().toString());
		builder.append("\n");
		builder.append("Download size: ");
		if (size >= 0) {
			builder.append(FileUtils.byteCountToDisplaySize(size));
		} else if (size == UNDEFINED) {
			builder.append(NLS.bind("Connecting to {0} to get the size...", getUrl().toString()));
		} else if (size == ERROR){
			builder.append(NLS.bind("Unknown (unable to connect to {0})", getUrl().toString()));
		} 
		return builder.toString();
	}
	
	protected int getSize() {
		return size;
	}

	/* (non-Javadoc)
	 * @see fr.inria.atlanmod.neo4emf.neo4jresolver.installer.INeo4jInstallerHandler#install(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void performInstall(IProgressMonitor monitor, IPath dirPath) {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
	
		InputStream urlStream = null;
		try {
			URL url = getUrl();
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(TIMEOUT);
			connection.setReadTimeout(TIMEOUT);
			
			int length = connection.getContentLength();
			monitor.beginTask(
					NLS.bind("Reading from {1}", getVersion(), getUrl().toString()),
					length >= 0 ? length : IProgressMonitor.UNKNOWN);
	
			if (connection.getContentLengthLong() >= 0)
			urlStream = connection.getInputStream();
			ZipInputStream zipStream = new ZipInputStream(urlStream);
	        
			byte[] buffer = new byte[2048];
			ZipEntry entry;
	        while((entry = zipStream.getNextEntry()) != null) {
				if (monitor.isCanceled())
					break;
				String fullFilename = entry.getName();
				monitor.subTask(NLS.bind("Reading \"{0}\"", fullFilename));
				if (jarFiles.contains(fullFilename)) {
					String filename = FilenameUtils.getName(fullFilename);
					FileOutputStream output = null;
					try {
						output = new FileOutputStream(dirPath.append(filename).toOSString());
						int len = 0;
						while ((len = zipStream.read(buffer)) > 0) {
							output.write(buffer, 0, len);
						}
					} finally {
						IOUtils.closeQuietly(output);
					}
				}
				monitor.worked((int) entry.getCompressedSize());
	        }
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, NLS.bind("Unable to install Neo4J v.{0} from {1}", getVersion(), getUrl().toString()), e);
		} finally {
			IOUtils.closeQuietly(urlStream);
			monitor.done();
		}
	}

}
