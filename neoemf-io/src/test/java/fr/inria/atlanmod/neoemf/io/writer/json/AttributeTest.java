package fr.inria.atlanmod.neoemf.io.writer.json;

import fr.inria.atlanmod.neoemf.tests.sample.SampleFactory;
import fr.inria.atlanmod.neoemf.tests.sample.Value;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Consumer;

public class AttributeTest {
    // Get the factory
    private static final SampleFactory factory = SampleFactory.eINSTANCE;

    @Test
    void testSingleAttribute() {
        Consumer<Resource> populator = resource -> {
            Value val = factory.createValue();
            val.setValue(10);

            resource.getContents().addAll(Arrays.asList(
                    val
            ));
        };
        Helper.testMigration(populator, "AttributeTest/singleAttribute/");
    }

    @Test
    void testMultipleAttributes() {
        Consumer<Resource> populator = resource -> {
            Value val = factory.createValue();
            val.setValue(10);
            Value val2 = factory.createValue();
            val2.setValue(12);

            resource.getContents().addAll(Arrays.asList(
                    val, val2
            ));
        };
        Helper.testMigration(populator, "AttributeTest/multipleAttribute/");
    }
}
