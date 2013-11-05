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
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
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
	
			urlStream = connection.getInputStream();
			ZipInputStream zipStream = new ZipInputStream(urlStream);
			byte[] buffer = new byte[1024*8];
			long start = System.currentTimeMillis();
			int total = length;
			int totalRead = 0;
			ZipEntry entry;
			float kBps = -1;
	        while((entry = zipStream.getNextEntry()) != null) {
	        	if (monitor.isCanceled())
	        		break;
				String fullFilename = entry.getName();
				IPath fullFilenamePath = new Path(fullFilename);
				int secsRemaining = (int) ((total-totalRead)/1024/kBps);
				String textRemaining = secsToText(secsRemaining);
				monitor.subTask(NLS.bind("{0} remaining. Reading {1}", 
						textRemaining.length() > 0 ? textRemaining : "unknown time", 
						StringUtils.abbreviateMiddle(fullFilenamePath.removeFirstSegments(1).toString(), "...", 45)));
				int entrySize = (int) entry.getCompressedSize();
				OutputStream output = null;
					try {
						int len = 0;
						int read = 0;
						String action = null;
						if (jarFiles.contains(fullFilename)) {
							action = "Copying";
							String filename = FilenameUtils.getName(fullFilename);
							output = new FileOutputStream(dirPath.append(filename).toOSString());
						} else {
							action = "Skipping";
							output = new NullOutputStream();
						}
						int secs = (int) ((System.currentTimeMillis() - start) / 1000);
						kBps = (float) totalRead/1024/secs;

						while ((len = zipStream.read(buffer)) > 0) {
							if (monitor.isCanceled())
								break;
							read += len;
							monitor.subTask(NLS.bind("{0} remaining. {1} {2} at {3}KB/s ({4}KB / {5}KB)", 
									new Object[] { 
									String.format("%s", textRemaining.length() > 0 ? textRemaining : "unknown time"), 
									action, 
									StringUtils.abbreviateMiddle(fullFilenamePath.removeFirstSegments(1).toString(), "...", 45), 
									String.format("%,.1f", kBps), 
									String.format("%,.1f", (float) read/1024), 
									String.format("%,.1f", (float) entry.getSize()/1024) }));
							output.write(buffer, 0, len);
						}
						totalRead += entrySize;
						monitor.worked(entrySize);
					} finally {
						IOUtils.closeQuietly(output);
					}
	        }
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, NLS.bind("Unable to install Neo4J v.{0} from {1}", getVersion(), getUrl().toString()), e);
		} finally {
			IOUtils.closeQuietly(urlStream);
			monitor.done();
		}
	}

	/**
	 * @param secsRemaining
	 * @return
	 */
	private String secsToText(int secsRemaining) {
		if (secsRemaining < 0) return "";
		int mins = secsRemaining / 60;
		int secs = secsRemaining % 60;
		StringBuilder builder = new StringBuilder();
		if (mins > 0)
			builder.append(NLS.bind("{0}min ", mins));
		if (mins > 0 && secs > 0)
			builder.append("and ");
		if (secs > 0)
			builder.append(NLS.bind("{0}s", secs));
		return builder.toString();
	}

}
