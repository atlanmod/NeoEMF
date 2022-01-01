package fr.inria.atlanmod.neoemf.io.writer.json;


import fr.inria.atlanmod.neoemf.tests.sample.Node;
import fr.inria.atlanmod.neoemf.tests.sample.Comment;
import fr.inria.atlanmod.neoemf.tests.sample.SampleFactory;
import fr.inria.atlanmod.neoemf.tests.sample.Value;
import fr.inria.atlanmod.neoemf.tests.sample.impl.LocalNodeImpl;
import fr.inria.atlanmod.neoemf.tests.sample.impl.PhysicalNodeImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class AttributeTest {
    // Get the factory
    private final SampleFactory factory = SampleFactory.eINSTANCE;

    @Test
    void testSingleAttribute() {
        Consumer<Resource> populator = resource -> {
            Value val = factory.createValue();
            val.setValue(10);

            resource.getContents().addAll(List.of(
                    val
            ));
        };
        Helper.testMigration(populator, "AttributeTest/singleAttribute/");
    }

    @Test
    void testMultipleAttributes() {
        Value val = factory.createValue();
        val.setValue(10);
        Value val2 = factory.createValue();
        val2.setValue(12);
       // List<Value> values = List.of(val, val2);

        Comment comment = factory.createComment();
        comment.setContent("comment body");

        Consumer<Resource> populator = resource -> {
            resource.getContents().addAll(
                    List.of(val2, comment));
            };
        /*
        values.stream().map(v -> {
            Consumer<Resource> populator = resource -> {
                resource.getContents().add(v);
            };
            Helper.testMigration(populator, "AttributeTest/multipleAttribute/");

            return null;
        });*/
       Helper.testMigration(populator, "AttributeTest/multipleAttribute/");
    }

    @Test
    void testMultiValuedAttribute() {
        Consumer<Resource> populator = resource -> {
            Node node = factory.createPhysicalNode();
            node.setLabel("parentNode");
            Node node1 = factory.createPhysicalNode();
            node1.setLabel("childrenNode1");
            node1.setParent(node);
            Node node2 = factory.createPhysicalNode();
            node2.setLabel("childrenNode2");
            node2.setParent(node);

            node.getChildren().add(node1);
            node.getChildren().add(node2);

            resource.getContents().addAll(Arrays.asList(
                node, node1, node2
            ));
        };
        Helper.testMigration(populator, "AttributeTest/multiValuedAttribute/");
    }
}
