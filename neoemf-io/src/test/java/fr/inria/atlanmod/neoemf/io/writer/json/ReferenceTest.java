package fr.inria.atlanmod.neoemf.io.writer.json;

import fr.inria.atlanmod.neoemf.tests.sample.SamplePackage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import store.StorePackage;

import java.io.IOException;

public class ReferenceTest {
	@BeforeAll
	static void initialize() {
		SamplePackage.eINSTANCE.eClass();
		StorePackage.eINSTANCE.eClass();
	}

	@Test
	void testSingleReference() throws IOException {
		Helper.testMigration("ReferenceTest/singleReference/");
	}

	@Test
	void testSingleReference2() throws IOException {
		Helper.testMigration("ReferenceTest/complexReferences/");
	}
}
