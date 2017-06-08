/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.Tags;
import fr.inria.atlanmod.neoemf.io.mock.DummyElement;
import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicNamespace;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;
import fr.inria.atlanmod.neoemf.io.util.IOResources;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class XmiReaderWithIdTest extends AbstractInputTest {

    @Override
    @Before
    public void readResource() throws IOException {
        IOResources.registerPackage("uml", "http://schema.omg.org/spec/UML/2.1");
        this.sample = IOResources.xmiWithId();
        super.readResource();
    }

    /**
     * Check that the namespaces are properly read.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testNamespaces() {
        BasicNamespace.Registry nsRegistry = BasicNamespace.Registry.getInstance();
        Iterable<String> prefixes = nsRegistry.getPrefixes();
        assertThat(prefixes).containsExactlyInAnyOrder("uml", "xmi");

        assertThat(root.ns()).isNotNull();
        assertThat(root.ns().prefix()).isEqualTo("uml");
    }

    /**
     * Check that the elements are properly processed.
     * <p>
     * All elements must have an id and a class name.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testElementsAndChildren() {
        DummyElement o;
        DummyElement child;

        assertValidElement(root, "themodel", "Model", "jbk", 5); // Assert that 'xmi' elements don't exist
        {
            //@Model/@packagedElement.0
            o = childFrom(root, 0);
            assertValidElement(o, "0x81_22", "packagedElement", "jbk", 4);
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                child = childFrom(o, 0, 4);
                assertValidElement(child, "0x1f402_1", "ownedAttribute", "attribute1", 0);

                //@Model/@packagedElement.0/@packagedElement.3
                o = childFrom(o, 3);
                assertValidElement(o, "COLLABORATION_0x1f402_12", "packagedElement", "machine", 3);
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    o = childFrom(o, 0);
                    assertValidElement(o, "INTERACTION_0x1f402_12", "ownedBehavior", "machine", 6);
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        child = childFrom(o, 3, 0);
                        assertValidElement(child, "OPERAND1_0x1f402_12", "operand", null, 5);

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        child = childFrom(o, 4);
                        assertValidElement(child, "MSG2_0x1f402_12", "message", "answer", 0);
                    }
                }
            }
            //@Model/@packagedElement.2
            o = childFrom(root, 2);
            assertValidElement(o, "RECOPEREVT1_0x81_22", "packagedElement", "answer", 0);
        }
    }

    /**
     * Check that the attributes are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testAttributes() {
        DummyElement o;
        DummyElement child;

        List<BasicAttribute> attributes;

        attributes = root.attributes();
        assertThat(attributes).isEmpty();
        {
            //@Model/@packagedElement.0
            o = childFrom(root, 0);
            attributes = o.attributes();
            assertThat(attributes).isEmpty();
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                child = childFrom(o, 0, 4);
                attributes = child.attributes();
                assertThat(attributes).hasSize(1);
                assertValidAttribute(attributes.get(0), "visibility", "private", 0);

                //@Model/@packagedElement.0/@packagedElement.3
                o = childFrom(o, 3);
                attributes = o.attributes();
                assertThat(attributes).isEmpty();
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    o = childFrom(o, 0);
                    attributes = o.attributes();
                    assertThat(attributes).isEmpty();
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        child = childFrom(o, 3, 0);
                        assertThat(child.attributes()).isEmpty();

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        child = childFrom(o, 4);
                        attributes = child.attributes();
                        assertThat(attributes).hasSize(1);
                        assertValidAttribute(attributes.get(0), "messageSort", "synchCall", 0);
                    }
                }
            }
            //@Model/@packagedElement.2
            o = childFrom(root, 2);
            attributes = o.attributes();
            assertThat(attributes).isEmpty();
        }
    }

    /**
     * Check that the {@code xmi:idref} references are properly processed.
     * <p>
     * Containments and inverse references must have been created.
     * References previously detected as attributes, are now well placed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testReferences() {
        DummyElement o;
        DummyElement child;

        List<BasicReference> references;

        references = root.references();
        assertThat(references).hasSize(5); // Now contains containment
        assertValidReference(references.get(0), "packagedElement", "0x81_22", -1, true, true);
        assertValidReference(references.get(2), "packagedElement", "RECOPEREVT1_0x81_22", -1, true, true);
        {
            //@Model/@packagedElement.0
            o = childFrom(root, 0);
            references = o.references();
            assertThat(references).hasSize(4); // Now contains containment
            assertValidReference(references.get(1), "packagedElement", "0x1f582_4", -1, true, true);
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                child = childFrom(o, 0, 4);
                references = child.references();
                assertThat(references).hasSize(1);
                assertValidReference(references.get(0), "type", "0x1f582_4", -1, false, false);

                //@Model/@packagedElement.0/@packagedElement.3
                o = childFrom(o, 3);
                references = o.references();
                assertThat(references).hasSize(3); // Now contains containment
                assertValidReference(references.get(0), "ownedBehavior", "INTERACTION_0x1f402_12", -1, true, true);
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    o = childFrom(o, 0);
                    references = o.references();
                    assertThat(references).hasSize(6); // Now contains containment
                    assertValidReference(references.get(4), "message", "MSG2_0x1f402_12", -1, true, true);
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        child = childFrom(o, 3, 0);
                        references = child.references();
                        assertThat(references).hasSize(5); // Now contains containment
                        assertValidReference(references.get(2), "fragment", "BEHEXECSPEC2_0x1f402_12", -1, true, true);

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        child = childFrom(o, 4);
                        references = child.references();
                        assertThat(references).hasSize(3);
                        assertValidReference(references.get(0), "sendEvent", "MSGOCCSPECSEND2_0x1f402_12", 0, false, false); // New reference
                        assertValidReference(references.get(1), "receiveEvent", "MSGOCCSPECREC2_0x1f402_12", 0, false, false); // New reference
                        assertValidReference(references.get(2), "connector", "CONNECTOR1_2_0x1f402_12", 0, false, false); // New reference
                    }
                }
            }

            //@Model/@packagedElement.2
            o = childFrom(root, 2);
            references = o.references();
            assertThat(references).hasSize(1);
            assertValidReference(references.get(0), "operation", "0x1f582_2", 0, false, false); // New reference
        }
    }

    /**
     * Check that the metaclasses ({@code xsi:type} or {@code xmi:type}) are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testMetaClasses() {
        DummyElement o;
        DummyElement child;

        BasicNamespace ns = root.ns();
        assertValidMetaClass(root.metaClass(), "Model", ns);
        {
            //@Model/@packagedElement.0
            o = childFrom(root, 0);
            assertValidMetaClass(o.metaClass(), "Package", ns);
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                child = childFrom(o, 0, 4);
                assertValidMetaClass(child.metaClass(), "Property", ns);

                //@Model/@packagedElement.0/@packagedElement.3
                o = childFrom(o, 3);
                assertValidMetaClass(o.metaClass(), "Collaboration", ns);
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    o = childFrom(o, 0);
                    assertValidMetaClass(o.metaClass(), "Interaction", ns);
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        child = childFrom(o, 3, 0);
                        assertValidMetaClass(child.metaClass(), "InteractionOperand", ns);

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        child = childFrom(o, 4);
                        assertValidMetaClass(child.metaClass(), "Message", ns);
                    }
                }
            }
            //@Model/@packagedElement.2
            o = childFrom(root, 2);
            assertValidMetaClass(o.metaClass(), "ReceiveOperationEvent", ns);
        }
    }
}
