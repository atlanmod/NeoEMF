package fr.inria.atlanmod.neo4emf.neo4jresolver.refactoring;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.DeleteParticipant;
import org.eclipse.osgi.util.NLS;

import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.INeo4jRuntime;
import fr.inria.atlanmod.neo4emf.neo4jresolver.runtimes.internal.Neo4JRuntimesManager;


public class Neo4JProjectDeleteParticipant extends DeleteParticipant {

	IResource resource = null;

	public Neo4JProjectDeleteParticipant() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#getName()
	 */
	@Override
	public String getName() {
		return "Neo4J Runtime Delete Resource";
	}

	@Override
	protected boolean initialize(Object element) {
		resource = (IResource) element;
		if (element instanceof IResource) {
			IProject project = resource.getProject();
			for (INeo4jRuntime runtime : Neo4JRuntimesManager.INSTANCE.getRuntimes()) {
				if (project.getLocation().equals(runtime.getPath())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public RefactoringStatus checkConditions(IProgressMonitor pm, CheckConditionsContext context) throws OperationCanceledException {
		return RefactoringStatus.createWarningStatus(NLS.bind("{0} is linked to a Neo4J Runtime Project", resource.getName()));
	}

	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		return null;
	}

}
