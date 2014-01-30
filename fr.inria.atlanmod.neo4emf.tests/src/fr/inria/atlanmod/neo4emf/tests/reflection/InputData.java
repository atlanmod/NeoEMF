package fr.inria.atlanmod.neo4emf.tests.reflection;

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
	 * @return the list containing all the data
	 * @note If new metmodels are needed for testing, ensure their data method
	 * is added to this one.
	 */
	public static List<Object[]> allData() {
		List<Object[]> data = new ArrayList<Object[]>(dataGraph());
		data.addAll(dataTeach());
		return data;
	}
	
	/**
	 * Returns the test data for MGraph metamodel.
	 * @return
	 */
	public static List<Object[]> dataGraph() {
		Object[][] parameters = new Object[1][4];
		parameters[0][0] = MgraphPackage.eINSTANCE;
		parameters[0][1] = MgraphFactory.eINSTANCE;
		@SuppressWarnings("rawtypes") Map mapping = mgraph.reltypes.ReltypesMappings.getInstance().getMap();
		parameters[0][2] = mapping;
		ResourceSet rSet = new ResourceSetImpl();
		URI uri = URI.createURI("neo4emf:/./RStructuralGraph");
		rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("neo4emf", INeo4emfResourceFactory.eINSTANCE.setRelationshipsMap(mapping));
		// Create the resource
		parameters[0][3] = (INeo4emfResource) rSet.createResource(uri);
		return Arrays.asList(parameters);
	}
	
	/**
	 * Returns the test data for MTeach metamodel.
	 * @return
	 */
	public static List<Object[]> dataTeach() {
		Object[][] parameters = new Object[1][4];
		parameters[0][0] = MteachPackage.eINSTANCE;
		parameters[0][1] = MteachFactory.eINSTANCE;
		@SuppressWarnings("rawtypes") Map mapping = mteach.reltypes.ReltypesMappings.getInstance().getMap();
		parameters[0][2] = mapping;
		ResourceSet rSet = new ResourceSetImpl();
		URI uri = URI.createURI("neo4emf:/./RStructuralTeach");
		rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("neo4emf", INeo4emfResourceFactory.eINSTANCE.setRelationshipsMap(mapping));
		parameters[0][3] = (INeo4emfResource) rSet.createResource(uri);
		return Arrays.asList(parameters);
	}
	
}
