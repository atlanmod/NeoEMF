/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.mock.beans.ClassifierMock;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static fr.inria.atlanmod.neoemf.NeoAssertions.assertThat;

/**
 *
 */
public class XmiStreamReaderWithIdTest extends AllXmiReaderTest {

    @Override
    @Before
    public void setUp() throws Exception {
        this.sample = getXmiWithId();

        super.setUp();
    }

    /**
     * Check that the namespaces are properly read.
     */
    @Test
    public void testNamespacesWithId() throws Exception {
        Namespace.Registry nsRegistry = Namespace.Registry.getInstance();
        Iterable<String> prefixes = nsRegistry.getPrefixes();
        assertThat(prefixes).containsExactlyInAnyOrder("uml", "xmi");

        ClassifierMock root = persistanceHandler.getElements().get(0);
        assertThat(root.getNamespace()).isNotNull();
        assertThat(root.getNamespace().getPrefix()).isEqualTo("uml");
    }

    /**
     * Check that the elements (and the 'xmi:id' attribute) and their children are properly read.
     */
    @Test
    public void testElementsAndChildrenWithId() throws Exception {
        assertThat(persistanceHandler.getElements()).isNotEmpty();

        ClassifierMock mock;
        ClassifierMock mockChild;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        assertValidElement(root, "Model", 5, "themodel"); // Assert that 'xmi' elements don't exist
        {
            //@Model/@packagedElement.0
            mock = ClassifierMock.getChildFrom(root, 0);
            assertValidElement(mock, "packagedElement", 4, "0x81_22");
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = ClassifierMock.getChildFrom(mock, 0, 4);
                assertValidElement(mockChild, "ownedAttribute", 0, "0x1f402_1");

                //@Model/@packagedElement.0/@packagedElement.3
                mock = ClassifierMock.getChildFrom(mock, 3);
                assertValidElement(mock, "packagedElement", 3, "COLLABORATION_0x1f402_12");
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = ClassifierMock.getChildFrom(mock, 0);
                    assertValidElement(mock, "ownedBehavior", 6, "INTERACTION_0x1f402_12");
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = ClassifierMock.getChildFrom(mock, 3, 0);
                        assertValidElement(mockChild, "operand", 5, "OPERAND1_0x1f402_12");

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = ClassifierMock.getChildFrom(mock, 4);
                        assertValidElement(mockChild, "message", 0, "MSG2_0x1f402_12");
                    }
                }
            }
            //@Model/@packagedElement.2
            mock = ClassifierMock.getChildFrom(root, 2);
            assertValidElement(mock, "packagedElement", 0, "RECOPEREVT1_0x81_22");
        }
    }

    /**
     * Check that the attributes are properly read.
     * Most references are recognized as attributes, until the next step...
     */
    @Test
    public void testAttributesWithId() throws Exception {
        ClassifierMock mock;
        ClassifierMock mockChild;

        List<Attribute> attributeList;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        attributeList = root.getAttributes();
        assertThat(attributeList).isEmpty();
        {
            //@Model/@packagedElement.0
            mock = ClassifierMock.getChildFrom(root, 0);
            attributeList = mock.getAttributes();
            assertThat(attributeList).isEmpty();
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = ClassifierMock.getChildFrom(mock, 0, 4);
                attributeList = mockChild.getAttributes();
                assertThat(attributeList).hasSize(1);
                assertValidAttribute(attributeList.get(0), "visibility", "private");

                //@Model/@packagedElement.0/@packagedElement.3
                mock = ClassifierMock.getChildFrom(mock, 3);
                attributeList = mock.getAttributes();
                assertThat(attributeList).isEmpty();
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = ClassifierMock.getChildFrom(mock, 0);
                    attributeList = mock.getAttributes();
                    assertThat(attributeList).isEmpty();
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = ClassifierMock.getChildFrom(mock, 3, 0);
                        assertThat(mockChild.getAttributes()).isEmpty();

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = ClassifierMock.getChildFrom(mock, 4);
                        attributeList = mockChild.getAttributes();
                        assertThat(attributeList).hasSize(4);
                        assertValidAttribute(attributeList.get(0), "messageSort", "synchCall");
                        assertValidAttribute(attributeList.get(1), "sendEvent", "MSGOCCSPECSEND2_0x1f402_12"); // Future reference
                        assertValidAttribute(attributeList.get(2), "receiveEvent", "MSGOCCSPECREC2_0x1f402_12"); // Future reference
                        assertValidAttribute(attributeList.get(3), "connector", "CONNECTOR1_2_0x1f402_12"); // Future reference
                    }
                }
            }
            //@Model/@packagedElement.2
            mock = ClassifierMock.getChildFrom(root, 2);
            attributeList = mock.getAttributes();
            assertThat(attributeList).hasSize(1);
            assertValidAttribute(attributeList.get(0), "operation", "0x1f582_2"); // Future reference
        }
    }

    /**
     * Check that the 'xmi:idref' references are properly read.
     * Most are not recognized as references yet
     */
    @Test
    public void testReferencesWithId() throws Exception {
        ClassifierMock mock;
        ClassifierMock mockChild;

        List<Reference> referenceList;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        assertThat(root.getReferences()).isEmpty();
        {
            //@Model/@packagedElement.0
            mock = ClassifierMock.getChildFrom(root, 0);
            assertThat(mock.getReferences()).isEmpty();
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = ClassifierMock.getChildFrom(mock, 0, 4);
                referenceList = mockChild.getReferences();
                assertThat(referenceList).hasSize(1);
                assertValidReference(referenceList.get(0), "type", UNKNOWN_INDEX, "0x1f582_4");

                //@Model/@packagedElement.0/@packagedElement.3
                mock = ClassifierMock.getChildFrom(mock, 3);
                assertThat(mock.getReferences()).isEmpty();
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = ClassifierMock.getChildFrom(mock, 0);
                    assertThat(mock.getReferences()).isEmpty();
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = ClassifierMock.getChildFrom(mock, 3, 0);
                        assertThat(mockChild.getReferences()).isEmpty();

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = ClassifierMock.getChildFrom(mock, 4);
                        assertThat(mockChild.getReferences()).isEmpty();
                    }
                }
            }

            //@Model/@packagedElement.2
            mock = ClassifierMock.getChildFrom(root, 2);
            assertThat(mock.getReferences()).isEmpty();
        }
    }

    /**
     * Check that the metaclasses ('xsi:type' or 'xmi:type') are properly read.
     */
    @Test
    public void testMetaClassesWithId() throws Exception {
        ClassifierMock mock;
        ClassifierMock mockChild;

        ClassifierMock root = persistanceHandler.getElements().get(0);
        Namespace ns = root.getNamespace();
        assertValidMetaClass(root.getMetaClassifier(), "Model", ns);
        {
            //@Model/@packagedElement.0
            mock = ClassifierMock.getChildFrom(root, 0);
            assertValidMetaClass(mock.getMetaClassifier(), "Package", ns);
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = ClassifierMock.getChildFrom(mock, 0, 4);
                assertValidMetaClass(mockChild.getMetaClassifier(), "Property", ns);

                //@Model/@packagedElement.0/@packagedElement.3
                mock = ClassifierMock.getChildFrom(mock, 3);
                assertValidMetaClass(mock.getMetaClassifier(), "Collaboration", ns);
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = ClassifierMock.getChildFrom(mock, 0);
                    assertValidMetaClass(mock.getMetaClassifier(), "Interaction", ns);
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = ClassifierMock.getChildFrom(mock, 3, 0);
                        assertValidMetaClass(mockChild.getMetaClassifier(), "InteractionOperand", ns);

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = ClassifierMock.getChildFrom(mock, 4);
                        assertValidMetaClass(mockChild.getMetaClassifier(), "Message", ns);
                    }
                }
            }
            //@Model/@packagedElement.2
            mock = ClassifierMock.getChildFrom(root, 2);
            assertValidMetaClass(mock.getMetaClassifier(), "ReceiveOperationEvent", ns);
        }
    }
}
