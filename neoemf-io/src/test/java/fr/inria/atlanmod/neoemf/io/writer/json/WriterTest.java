package fr.inria.atlanmod.neoemf.io.writer.json;

import fr.inria.atlanmod.neoemf.tests.sample.Node;
import fr.inria.atlanmod.neoemf.tests.sample.SampleFactory;
import fr.inria.atlanmod.neoemf.tests.sample.SamplePackage;
import fr.inria.atlanmod.neoemf.tests.sample.Value;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import store.StoreFactory;
import store.StorePackage;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;

public class WriterTest {
    @BeforeAll
    static void initialize() {
        SamplePackage.eINSTANCE.eClass();
        StorePackage.eINSTANCE.eClass();
    }

    @Test
    void testSingleAttribute() throws IOException {
        Helper.testMigration("GeneratedInstances/singleAttribute/");
    }

    @Test
    void testMultipleAttributes() throws IOException {
        Helper.testMigration("GeneratedInstances/multipleAttributes/");
    }

    @Test
    void testMultiContainmentReference() throws IOException {
        Helper.testMigration("GeneratedInstances/multiContainmentReference/");
    }
}
