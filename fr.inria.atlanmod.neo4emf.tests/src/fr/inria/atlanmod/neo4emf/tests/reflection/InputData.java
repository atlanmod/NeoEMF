package fr.inria.atlanmod.neo4emf.tests.reflection;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import mgraph.MgraphFactory;
import mgraph.MgraphPackage;
import mteach.MteachFactory;
import mteach.MteachPackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.INeo4emfResourceFactory;

public class InputData {
	
	/**
	 * Generate the whole testing data set
	 * @param token avoid resource access issues (especially for lock releases)
	 * @return the list containing all the data
	 * @note If new metmodels are needed for testing, ensure their data method
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
		Object[][] parameters = new Object[1][4];
		parameters[0][0] = MgraphPackage.eINSTANCE;
		parameters[0][1] = MgraphFactory.eINSTANCE;
		@SuppressWarnings("rawtypes") Map mapping = mgraph.reltypes.ReltypesMappings.getInstance().getMap();
		parameters[0][2] = mapping;
		ResourceSet rSet = new ResourceSetImpl();
		URI uri = URI.createURI("neo4emf:/./data/output/R"+token+"Graph");
		rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("neo4emf", INeo4emfResourceFactory.eINSTANCE.setRelationshipsMap(mapping));
		parameters[0][3] = (INeo4emfResource) rSet.createResource(uri);
		return Arrays.asList(parameters);
	}
	
	/**
	 * Returns the test data for MTeach metamodel.
	 * @param token avoid resource access issues (especially for lock releases)
	 * @return
	 */
	public static List<Object[]> dataTeach(String token) {
		Object[][] parameters = new Object[1][4];
		parameters[0][0] = MteachPackage.eINSTANCE;
		parameters[0][1] = MteachFactory.eINSTANCE;
		@SuppressWarnings("rawtypes") Map mapping = mteach.reltypes.ReltypesMappings.getInstance().getMap();
		parameters[0][2] = mapping;
		ResourceSet rSet = new ResourceSetImpl();
		URI uri = URI.createURI("neo4emf:/./data/output/R"+token+"Teach");
		rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("neo4emf", INeo4emfResourceFactory.eINSTANCE.setRelationshipsMap(mapping));
		parameters[0][3] = (INeo4emfResource) rSet.createResource(uri);
		return Arrays.asList(parameters);
	}
	
}
