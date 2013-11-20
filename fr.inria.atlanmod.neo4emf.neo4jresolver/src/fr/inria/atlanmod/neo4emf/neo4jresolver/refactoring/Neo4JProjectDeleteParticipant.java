package fr.inria.atlanmod.neo4emf.neo4jresolver.refactoring;


public class Neo4JProjectDeleteParticipant extends Neo4JProjectAbstractParticipant {

	public Neo4JProjectDeleteParticipant() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#getName()
	 */
	@Override
	public String getName() {
		return "Neo4J Runtime Delete Resource";
	}

}
