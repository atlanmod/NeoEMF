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
        registerPackageFromEcore("uml", "http://schema.omg.org/spec/UML/2.1");
        this.sample = getXmiWithId();
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
        DummyElement mock;
        DummyElement mockChild;

        assertValidElement(root, "themodel", "Model", "jbk", 5); // Assert that 'xmi' elements don't exist
        {
            //@Model/@packagedElement.0
            mock = DummyElement.from(root, 0);
            assertValidElement(mock, "0x81_22", "packagedElement", "jbk", 4);
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = DummyElement.from(mock, 0, 4);
                assertValidElement(mockChild, "0x1f402_1", "ownedAttribute", "attribute1", 0);

                //@Model/@packagedElement.0/@packagedElement.3
                mock = DummyElement.from(mock, 3);
                assertValidElement(mock, "COLLABORATION_0x1f402_12", "packagedElement", "machine", 3);
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = DummyElement.from(mock, 0);
                    assertValidElement(mock, "INTERACTION_0x1f402_12", "ownedBehavior", "machine", 6);
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = DummyElement.from(mock, 3, 0);
                        assertValidElement(mockChild, "OPERAND1_0x1f402_12", "operand", null, 5);

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = DummyElement.from(mock, 4);
                        assertValidElement(mockChild, "MSG2_0x1f402_12", "message", "answer", 0);
                    }
                }
            }
            //@Model/@packagedElement.2
            mock = DummyElement.from(root, 2);
            assertValidElement(mock, "RECOPEREVT1_0x81_22", "packagedElement", "answer", 0);
        }
    }

    /**
     * Check that the attributes are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testAttributes() {
        DummyElement mock;
        DummyElement mockChild;

        List<BasicAttribute> attributeList;

        attributeList = root.attributes();
        assertThat(attributeList).isEmpty();
        {
            //@Model/@packagedElement.0
            mock = DummyElement.from(root, 0);
            attributeList = mock.attributes();
            assertThat(attributeList).isEmpty();
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = DummyElement.from(mock, 0, 4);
                attributeList = mockChild.attributes();
                assertThat(attributeList).hasSize(1);
                assertValidAttribute(attributeList.get(0), "visibility", "private", 0);

                //@Model/@packagedElement.0/@packagedElement.3
                mock = DummyElement.from(mock, 3);
                attributeList = mock.attributes();
                assertThat(attributeList).isEmpty();
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = DummyElement.from(mock, 0);
                    attributeList = mock.attributes();
                    assertThat(attributeList).isEmpty();
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = DummyElement.from(mock, 3, 0);
                        assertThat(mockChild.attributes()).isEmpty();

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = DummyElement.from(mock, 4);
                        attributeList = mockChild.attributes();
                        assertThat(attributeList).hasSize(1);
                        assertValidAttribute(attributeList.get(0), "messageSort", "synchCall", 0);
                    }
                }
            }
            //@Model/@packagedElement.2
            mock = DummyElement.from(root, 2);
            attributeList = mock.attributes();
            assertThat(attributeList).isEmpty();
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
        DummyElement mock;
        DummyElement mockChild;

        List<BasicReference> referenceList;

        referenceList = root.references();
        assertThat(referenceList).hasSize(5); // Now contains containment
        assertValidReference(referenceList.get(0), "packagedElement", "0x81_22", UNKNOWN_INDEX, true, true);
        assertValidReference(referenceList.get(2), "packagedElement", "RECOPEREVT1_0x81_22", UNKNOWN_INDEX, true, true);
        {
            //@Model/@packagedElement.0
            mock = DummyElement.from(root, 0);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(4); // Now contains containment
            assertValidReference(referenceList.get(1), "packagedElement", "0x1f582_4", UNKNOWN_INDEX, true, true);
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = DummyElement.from(mock, 0, 4);
                referenceList = mockChild.references();
                assertThat(referenceList).hasSize(1);
                assertValidReference(referenceList.get(0), "type", "0x1f582_4", UNKNOWN_INDEX, false, false);

                //@Model/@packagedElement.0/@packagedElement.3
                mock = DummyElement.from(mock, 3);
                referenceList = mock.references();
                assertThat(referenceList).hasSize(3); // Now contains containment
                assertValidReference(referenceList.get(0), "ownedBehavior", "INTERACTION_0x1f402_12", UNKNOWN_INDEX, true, true);
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = DummyElement.from(mock, 0);
                    referenceList = mock.references();
                    assertThat(referenceList).hasSize(6); // Now contains containment
                    assertValidReference(referenceList.get(4), "message", "MSG2_0x1f402_12", UNKNOWN_INDEX, true, true);
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = DummyElement.from(mock, 3, 0);
                        referenceList = mockChild.references();
                        assertThat(referenceList).hasSize(5); // Now contains containment
                        assertValidReference(referenceList.get(2), "fragment", "BEHEXECSPEC2_0x1f402_12", UNKNOWN_INDEX, true, true);

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = DummyElement.from(mock, 4);
                        referenceList = mockChild.references();
                        assertThat(referenceList).hasSize(3);
                        assertValidReference(referenceList.get(0), "sendEvent", "MSGOCCSPECSEND2_0x1f402_12", 0, false, false); // New reference
                        assertValidReference(referenceList.get(1), "receiveEvent", "MSGOCCSPECREC2_0x1f402_12", 0, false, false); // New reference
                        assertValidReference(referenceList.get(2), "connector", "CONNECTOR1_2_0x1f402_12", 0, false, false); // New reference
                    }
                }
            }

            //@Model/@packagedElement.2
            mock = DummyElement.from(root, 2);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(1);
            assertValidReference(referenceList.get(0), "operation", "0x1f582_2", 0, false, false); // New reference
        }
    }

    /**
     * Check that the metaclasses ({@code xsi:type} or {@code xmi:type}) are properly processed.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testMetaClasses() {
        DummyElement mock;
        DummyElement mockChild;

        BasicNamespace ns = root.ns();
        assertValidMetaClass(root.metaClass(), "Model", ns);
        {
            //@Model/@packagedElement.0
            mock = DummyElement.from(root, 0);
            assertValidMetaClass(mock.metaClass(), "Package", ns);
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = DummyElement.from(mock, 0, 4);
                assertValidMetaClass(mockChild.metaClass(), "Property", ns);

                //@Model/@packagedElement.0/@packagedElement.3
                mock = DummyElement.from(mock, 3);
                assertValidMetaClass(mock.metaClass(), "Collaboration", ns);
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = DummyElement.from(mock, 0);
                    assertValidMetaClass(mock.metaClass(), "Interaction", ns);
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = DummyElement.from(mock, 3, 0);
                        assertValidMetaClass(mockChild.metaClass(), "InteractionOperand", ns);

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = DummyElement.from(mock, 4);
                        assertValidMetaClass(mockChild.metaClass(), "Message", ns);
                    }
                }
            }
            //@Model/@packagedElement.2
            mock = DummyElement.from(root, 2);
            assertValidMetaClass(mock.metaClass(), "ReceiveOperationEvent", ns);
        }
    }
}
