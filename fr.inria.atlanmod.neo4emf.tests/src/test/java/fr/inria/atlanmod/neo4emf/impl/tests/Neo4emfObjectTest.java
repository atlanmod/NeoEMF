package fr.inria.atlanmod.neo4emf.impl.tests;

/**
 * Created by sunye on 07/02/2014.
 */

import org.junit.Test;

import fr.inria.atlanmod.neo4emf.INeo4emfObject;
import fr.inria.atlanmod.neo4emf.impl.Neo4emfObject;


public class Neo4emfObjectTest {
    INeo4emfObject obj;

    @org.junit.Before
    public void setUp() throws Exception {
        obj = new Neo4emfObject();

    }

    @org.junit.After
    public void tearDown() throws Exception {

    }

    @Test
    public void testEmptyConstructor() throws Exception {
        assert obj.getNodeId() == -1;
        assert !obj.eIsProxy();
    }

    @Test
    public void testGetNodeId() throws Exception {
        obj.setNodeId(Long.MAX_VALUE);
        assert obj.getNodeId() == Long.MAX_VALUE;

        obj.setNodeId(Long.MIN_VALUE);
        assert obj.getNodeId() == Long.MIN_VALUE;

        obj.setNodeId(0);
        assert obj.getNodeId() == 0;
    }


    @org.junit.Test
    public void testGetPartitionId() throws Exception {

    }

    @org.junit.Test
    public void testSetPartitionId() throws Exception {

    }

    @org.junit.Test
    public void testCompareTo() throws Exception {

    }
    
    @Test
    public void testEquals() throws Exception {
    	assert obj.equals(obj);
    	assert !obj.equals(null);
    	assert !obj.equals(new Neo4emfObject());
    	
    }

    @org.junit.Test
    public void testEIsProxy() throws Exception {

    }

    @org.junit.Test
    public void testSetProxy() throws Exception {

    }

    @org.junit.Test
    public void testEGet() throws Exception {

    }

    @org.junit.Test
    public void testEGet1() throws Exception {

    }

    @org.junit.Test
    public void testEGet2() throws Exception {

    }

    @org.junit.Test
    public void testEGet3() throws Exception {

    }

    @org.junit.Test
    public void testEDynamicGet() throws Exception {

    }

    @org.junit.Test
    public void testSimpleGet() throws Exception {

    }

    @org.junit.Test
    public void testEDynamicSet() throws Exception {

    }

    @org.junit.Test
    public void testESet() throws Exception {

    }

    @org.junit.Test
    public void testESet1() throws Exception {

    }

    @org.junit.Test
    public void testEVirtualValue() throws Exception {

    }

    @org.junit.Test
    public void testIsLoaded() throws Exception {

    }
}
