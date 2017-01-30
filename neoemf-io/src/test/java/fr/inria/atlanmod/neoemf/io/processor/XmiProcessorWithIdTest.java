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

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.mock.beans.ElementMock;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.Reference;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class XmiProcessorWithIdTest extends AbstractXmiProcessorTest {

    @Override
    @Before
    public void readResource() throws IOException {
        registerEPackageFromEcore("uml", "http://schema.omg.org/spec/UML/2.1");
        this.sample = getXmiWithId();
        super.readResource();
    }

    /**
     * Check that the elements are properly processed.
     * <p>
     * All elements must have an id and a class name.
     */
    @Test
    public void testElementsAndChildrenWithId() {
        assertThat(persistanceHandler.getElements()).isNotEmpty();

        ElementMock mock;
        ElementMock mockChild;

        ElementMock root = persistanceHandler.getElements().get(0);
        assertValidElement(root, "Model", 5, "themodel", "jbk", true); // Assert that 'xmi' elements don't exist
        {
            //@Model/@packagedElement.0
            mock = ElementMock.childFrom(root, 0);
            assertValidElement(mock, "packagedElement", 4, "0x81_22", "jbk", false);
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = ElementMock.childFrom(mock, 0, 4);
                assertValidElement(mockChild, "ownedAttribute", 0, "0x1f402_1", "attribute1", false);

                //@Model/@packagedElement.0/@packagedElement.3
                mock = ElementMock.childFrom(mock, 3);
                assertValidElement(mock, "packagedElement", 3, "COLLABORATION_0x1f402_12", "machine", false);
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = ElementMock.childFrom(mock, 0);
                    assertValidElement(mock, "ownedBehavior", 6, "INTERACTION_0x1f402_12", "machine", false);
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = ElementMock.childFrom(mock, 3, 0);
                        assertValidElement(mockChild, "operand", 5, "OPERAND1_0x1f402_12", null, false);

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = ElementMock.childFrom(mock, 4);
                        assertValidElement(mockChild, "message", 0, "MSG2_0x1f402_12", "answer", false);
                    }
                }
            }
            //@Model/@packagedElement.2
            mock = ElementMock.childFrom(root, 2);
            assertValidElement(mock, "packagedElement", 0, "RECOPEREVT1_0x81_22", "answer", false);
        }
    }

    /**
     * Check that the attributes are properly processed.
     */
    @Test
    public void testAttributesWithId() {
        ElementMock mock;
        ElementMock mockChild;

        List<Attribute> attributeList;

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
                assertValidAttribute(attributeList.get(0), "visibility", 0, "private");

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
                        assertThat(attributeList).hasSize(1);
                        assertValidAttribute(attributeList.get(0), "messageSort", 0, "synchCall");
                    }
                }
            }
            //@Model/@packagedElement.2
            mock = ElementMock.childFrom(root, 2);
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
    public void testReferencesWithId() {
        ElementMock mock;
        ElementMock mockChild;

        List<Reference> referenceList;

        ElementMock root = persistanceHandler.getElements().get(0);
        referenceList = root.references();
        assertThat(referenceList).hasSize(5); // Now contains containment
        assertValidReference(referenceList.get(0), "packagedElement", UNKNOWN_INDEX, "0x81_22", true, true);
        assertValidReference(referenceList.get(2), "packagedElement", UNKNOWN_INDEX, "RECOPEREVT1_0x81_22", true, true);
        {
            //@Model/@packagedElement.0
            mock = ElementMock.childFrom(root, 0);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(4); // Now contains containment
            assertValidReference(referenceList.get(1), "packagedElement", UNKNOWN_INDEX, "0x1f582_4", true, true);
            {
                //@Model/@packagedElement.0/@packagedElement.0/@ownedAttribute
                mockChild = ElementMock.childFrom(mock, 0, 4);
                referenceList = mockChild.references();
                assertThat(referenceList).hasSize(1);
                assertValidReference(referenceList.get(0), "type", UNKNOWN_INDEX, "0x1f582_4", false, false);

                //@Model/@packagedElement.0/@packagedElement.3
                mock = ElementMock.childFrom(mock, 3);
                referenceList = mock.references();
                assertThat(referenceList).hasSize(3); // Now contains containment
                assertValidReference(referenceList.get(0), "ownedBehavior", UNKNOWN_INDEX, "INTERACTION_0x1f402_12", true, true);
                {
                    //@Model/@packagedElement.0/@packagedElement.3/ownedBehavior.0
                    mock = ElementMock.childFrom(mock, 0);
                    referenceList = mock.references();
                    assertThat(referenceList).hasSize(6); // Now contains containment
                    assertValidReference(referenceList.get(4), "message", UNKNOWN_INDEX, "MSG2_0x1f402_12", true, true);
                    {
                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@fragment/@operand
                        mockChild = ElementMock.childFrom(mock, 3, 0);
                        referenceList = mockChild.references();
                        assertThat(referenceList).hasSize(5); // Now contains containment
                        assertValidReference(referenceList.get(2), "fragment", UNKNOWN_INDEX, "BEHEXECSPEC2_0x1f402_12", true, true);

                        //@Model/@packagedElement.0/@packagedElement.3/@ownedBehavior.0/@message.0
                        mockChild = ElementMock.childFrom(mock, 4);
                        referenceList = mockChild.references();
                        assertThat(referenceList).hasSize(3);
                        assertValidReference(referenceList.get(0), "sendEvent", 0, "MSGOCCSPECSEND2_0x1f402_12", false, false); // New reference
                        assertValidReference(referenceList.get(1), "receiveEvent", 0, "MSGOCCSPECREC2_0x1f402_12", false, false); // New reference
                        assertValidReference(referenceList.get(2), "connector", 0, "CONNECTOR1_2_0x1f402_12", false, false); // New reference
                    }
                }
            }

            //@Model/@packagedElement.2
            mock = ElementMock.childFrom(root, 2);
            referenceList = mock.references();
            assertThat(referenceList).hasSize(1);
            assertValidReference(referenceList.get(0), "operation", 0, "0x1f582_2", false, false); // New reference
        }
    }

    /**
     * Check that the metaclasses ({@code xsi:type} or {@code xmi:type}) are properly processed.
     */
    @Test
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
