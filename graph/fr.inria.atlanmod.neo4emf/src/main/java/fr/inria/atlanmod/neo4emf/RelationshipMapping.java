package fr.inria.atlanmod.neo4emf;

import org.neo4j.graphdb.RelationshipType;

public interface RelationshipMapping {

	public RelationshipType relationshipAt(int classID, int referenceID);
}
