package fr.inria.atlanmod.neo4emf.neo4jresolver.cp.container.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import fr.inria.atlanmod.neo4emf.neo4jresolver.cp.container.INeo4jClasspathContainer;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.JarFilter;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal.Neo4JRuntimesManager;

/**
 * 
 * @author abelgomez
 *
 */
public class Neo4jClasspathContainer implements IClasspathContainer, INeo4jClasspathContainer {

	private String description = "Neo4J Runtime";
	private IPath path;
	private String version;

	public Neo4jClasspathContainer(IPath path, IJavaProject project) {
		this.path = path;
		this.version = path.lastSegment();
		this.description = "Neo4J Runtime [" + this.version + "]";
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getClasspathEntries()
	 */
	public IClasspathEntry[] getClasspathEntries() {
		ArrayList<IClasspathEntry> entryList = new ArrayList<IClasspathEntry>();
		File neo4jDir = null;
		// Get runtimes
		List<INeo4jRuntime> runtimes = Neo4JRuntimesManager.INSTANCE.getRuntimes();
		if (!runtimes.isEmpty()) {
			// If we have runtimes now proceed... 
			for (INeo4jRuntime runtime : runtimes) {
				if (runtime.getVersion().equals(version)) {
					// We found a matching version
					neo4jDir = runtime.getPath().toFile();
				} 
			}
			// If we found a JAR directory, create the entries
			if (neo4jDir != null && neo4jDir.isDirectory()) {
				File[] libs = neo4jDir.listFiles(new JarFilter());
				for (File lib : libs) {
					entryList.add(JavaCore.newLibraryEntry(new Path(lib.getAbsolutePath()), null, null));
				}
			}
		}
		IClasspathEntry[] entryArray = new IClasspathEntry[entryList.size()];
		return (IClasspathEntry[]) entryList.toArray(entryArray);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getKind()
	 */
	public int getKind() {
		return IClasspathContainer.K_APPLICATION;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getPath()
	 */
	public IPath getPath() {
		return path;
	}

}
