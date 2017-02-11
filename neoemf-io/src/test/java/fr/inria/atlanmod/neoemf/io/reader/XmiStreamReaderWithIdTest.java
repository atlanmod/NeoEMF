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

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.Tags;
import fr.inria.atlanmod.neoemf.io.mock.beans.ElementMock;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class XmiStreamReaderWithIdTest extends AbstractXmiReaderTest {

    @Override
    @Before
    public void readResource() throws IOException {
        this.sample = getXmiWithId();
        super.readResource();
    }

    /**
     * Check that the namespaces are properly read.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testNamespacesWithId() {
        Namespace.Registry nsRegistry = Namespace.Registry.getInstance();
        Iterable<String> prefixes = nsRegistry.getPrefixes();
        assertThat(prefixes).containsExactlyInAnyOrder("uml", "xmi");

        ElementMock root = persistanceHandler.getElements().get(0);
        assertThat(root.ns()).isNotNull();
        assertThat(root.ns().prefix()).isEqualTo("uml");
    }

    /**
     * Check that the elements (and the 'xmi:id' attribute) and their children are properly read.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testElementsAndChildrenWithId() {
        assertThat(persistanceHandler.getElements()).isNotEmpty();

        ElementMock mock;
        ElementMock mockChild;

        ElementMock root = persistanceHandler.getElements().get(0);
        assertValidElement(root, "Model", 5, "themodel"); // Assert that 'xmi' elements don't exist
        {
            //@Model/@packagedElement.0
            mock = ElementMock.childFrom(root, 0);
            assertValidElement(mock, "packagedElement", 4, "0x81_22");
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = ElementMock.childFrom(mock, 0, 4);
                assertValidElement(mockChild, "ownedAttribute", 0, "0x1f402_1");

                //@Model/@packagedElement.0/@packagedElement.3
                mock = ElementMock.childFrom(mock, 3);
                assertValidElement(mock, "packagedElement", 3, "COLLABORATION_0x1f402_12");
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = ElementMock.childFrom(mock, 0);
                    assertValidElement(mock, "ownedBehavior", 6, "INTERACTION_0x1f402_12");
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = ElementMock.childFrom(mock, 3, 0);
                        assertValidElement(mockChild, "operand", 5, "OPERAND1_0x1f402_12");

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = ElementMock.childFrom(mock, 4);
                        assertValidElement(mockChild, "message", 0, "MSG2_0x1f402_12");
                    }
                }
            }
            //@Model/@packagedElement.2
            mock = ElementMock.childFrom(root, 2);
            assertValidElement(mock, "packagedElement", 0, "RECOPEREVT1_0x81_22");
        }
    }

    /**
     * Check that the attributes are properly read.
     * Most references are recognized as attributes, until the next step...
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testAttributesWithId() {
        ElementMock mock;
        ElementMock mockChild;

        List<RawAttribute> attributeList;

        ElementMock root = persistanceHandler.getElements().get(0);
        attributeList = root.attributes();
        assertThat(attributeList).isEmpty();
        {
            //@Model/@packagedElement.0
            mock = ElementMock.childFrom(root, 0);
            attributeList = mock.attributes();
            assertThat(attributeList).isEmpty();
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = ElementMock.childFrom(mock, 0, 4);
                attributeList = mockChild.attributes();
                assertThat(attributeList).hasSize(1);
                assertValidAttribute(attributeList.get(0), "visibility", "private");

                //@Model/@packagedElement.0/@packagedElement.3
                mock = ElementMock.childFrom(mock, 3);
                attributeList = mock.attributes();
                assertThat(attributeList).isEmpty();
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = ElementMock.childFrom(mock, 0);
                    attributeList = mock.attributes();
                    assertThat(attributeList).isEmpty();
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = ElementMock.childFrom(mock, 3, 0);
                        assertThat(mockChild.attributes()).isEmpty();

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = ElementMock.childFrom(mock, 4);
                        attributeList = mockChild.attributes();
                        assertThat(attributeList).hasSize(4);
                        assertValidAttribute(attributeList.get(0), "messageSort", "synchCall");
                        assertValidAttribute(attributeList.get(1), "sendEvent", "MSGOCCSPECSEND2_0x1f402_12"); // Future reference
                        assertValidAttribute(attributeList.get(2), "receiveEvent", "MSGOCCSPECREC2_0x1f402_12"); // Future reference
                        assertValidAttribute(attributeList.get(3), "connector", "CONNECTOR1_2_0x1f402_12"); // Future reference
                    }
                }
            }
            //@Model/@packagedElement.2
            mock = ElementMock.childFrom(root, 2);
            attributeList = mock.attributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "operation", "0x1f582_2"); // Future reference
        }
    }

    /**
     * Check that the 'xmi:idref' references are properly read.
     * Most are not recognized as references yet
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testReferencesWithId() {
        ElementMock mock;
        ElementMock mockChild;

        List<RawReference> referenceList;

        ElementMock root = persistanceHandler.getElements().get(0);
        assertThat(root.references()).isEmpty();
        {
            //@Model/@packagedElement.0
            mock = ElementMock.childFrom(root, 0);
            assertThat(mock.references()).isEmpty();
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = ElementMock.childFrom(mock, 0, 4);
                referenceList = mockChild.references();
                assertThat(referenceList).hasSize(1);
                assertValidReference(referenceList.get(0), "type", UNKNOWN_INDEX, "0x1f582_4");

                //@Model/@packagedElement.0/@packagedElement.3
                mock = ElementMock.childFrom(mock, 3);
                assertThat(mock.references()).isEmpty();
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = ElementMock.childFrom(mock, 0);
                    assertThat(mock.references()).isEmpty();
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = ElementMock.childFrom(mock, 3, 0);
                        assertThat(mockChild.references()).isEmpty();

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = ElementMock.childFrom(mock, 4);
                        assertThat(mockChild.references()).isEmpty();
                    }
                }
            }

            //@Model/@packagedElement.2
            mock = ElementMock.childFrom(root, 2);
            assertThat(mock.references()).isEmpty();
        }
    }

    /**
     * Check that the metaclasses ('xsi:type' or 'xmi:type') are properly read.
     */
    @Test
    @Category(Tags.IOTests.class)
    public void testMetaClassesWithId() {
        ElementMock mock;
        ElementMock mockChild;

        ElementMock root = persistanceHandler.getElements().get(0);
        Namespace ns = root.ns();
        assertValidMetaClass(root.metaClass(), "Model", ns);
        {
            //@Model/@packagedElement.0
            mock = ElementMock.childFrom(root, 0);
            assertValidMetaClass(mock.metaClass(), "Package", ns);
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = ElementMock.childFrom(mock, 0, 4);
                assertValidMetaClass(mockChild.metaClass(), "Property", ns);

                //@Model/@packagedElement.0/@packagedElement.3
                mock = ElementMock.childFrom(mock, 3);
                assertValidMetaClass(mock.metaClass(), "Collaboration", ns);
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = ElementMock.childFrom(mock, 0);
                    assertValidMetaClass(mock.metaClass(), "Interaction", ns);
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = ElementMock.childFrom(mock, 3, 0);
                        assertValidMetaClass(mockChild.metaClass(), "InteractionOperand", ns);

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = ElementMock.childFrom(mock, 4);
                        assertValidMetaClass(mockChild.metaClass(), "Message", ns);
                    }
                }
            }
            //@Model/@packagedElement.2
            mock = ElementMock.childFrom(root, 2);
            assertValidMetaClass(mock.metaClass(), "ReceiveOperationEvent", ns);
        }
    }
}
