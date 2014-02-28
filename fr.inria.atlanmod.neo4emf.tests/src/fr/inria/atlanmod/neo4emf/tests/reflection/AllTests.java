package fr.inria.atlanmod.neo4emf.tests.reflection;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ StructuralTest.class, ChangelogTest.class })
public class AllTests extends TestCase {

}
