package fr.inria.atlanmod.neo4emf.tests.reflection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mgraph.MgraphFactory;
import mgraph.MgraphPackage;
import mteach.MteachFactory;
import mteach.MteachPackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.INeo4emfResourceFactory;
import fr.inria.atlanmod.neo4emf.drivers.NESession;

public class InputData {
	
	/**
	 * Generate the whole testing data set
	 * @param token avoid resource access issues (especially for lock releases)
	 * @return the list containing all the data
	 * @note If new metamodels are needed for testing, ensure their data method
	 * is added to this one.
	 */
	public static List<Object[]> allData(String token) {
		List<Object[]> data = new ArrayList<Object[]>(dataGraph(token));
		data.addAll(dataTeach(token));
		return data;
	}
	
	/**
	 * Returns the test data for MGraph metamodel.
	 * @param token avoid resource access issues (especially for lock releases)
	 * @return
	 */
	public static List<Object[]> dataGraph(String token) {
		Object[][] parameters = new Object[1][3];
		parameters[0][0] = MgraphPackage.eINSTANCE;
		parameters[0][1] = MgraphFactory.eINSTANCE;
		URI uri = URI.createURI("neo4emf:./data/output/R"+token+"Graph");
		
		NESession session = new NESession(MgraphPackage.eINSTANCE);
		INeo4emfResource resource = session.createResource(uri,1000000);
		parameters[0][2] = resource;
		return Arrays.asList(parameters);
	}
	
	/**
	 * Returns the test data for MTeach metamodel.
	 * @param token avoid resource access issues (especially for lock releases)
	 * @return
	 */
	public static List<Object[]> dataTeach(String token) {
		Object[][] parameters = new Object[1][3];
		parameters[0][0] = MteachPackage.eINSTANCE;
		parameters[0][1] = MteachFactory.eINSTANCE;
		
		URI uri = URI.createURI("neo4emf:./data/output/R"+token+"Teach");
		
		NESession session = new NESession(MteachPackage.eINSTANCE);
		INeo4emfResource resource = session.createResource(uri, 10);

		parameters[0][2] = resource;
		return Arrays.asList(parameters);
	}
	
}
