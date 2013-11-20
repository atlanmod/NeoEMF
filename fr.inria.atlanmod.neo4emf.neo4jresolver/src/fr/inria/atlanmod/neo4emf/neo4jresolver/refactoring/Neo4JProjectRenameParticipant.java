package fr.inria.atlanmod.neo4emf.neo4jresolver.refactoring;


public class Neo4JProjectRenameParticipant extends Neo4JProjectAbstractParticipant {

	public Neo4JProjectRenameParticipant() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#getName()
	 */
	@Override
	public String getName() {
		return "Neo4J Runtime Rename Resource";
	}

}
