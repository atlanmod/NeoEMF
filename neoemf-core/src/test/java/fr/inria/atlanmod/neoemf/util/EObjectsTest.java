package fr.inria.atlanmod.neoemf.util;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

public class EObjectsTest {

    @Test
    public void testIsAttribute() throws Exception {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EObjects.isAttribute(attribute)).isTrue();
        assertThat(EObjects.isAttribute(reference)).isFalse();
    }

    @Test
    public void testIsReference() throws Exception {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EObjects.isReference(reference)).isTrue();
        assertThat(EObjects.isReference(attribute)).isFalse();
    }

    @Test
    public void testAsAttribute() throws Exception {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EObjects.asAttribute(attribute)).isInstanceOf(EAttribute.class);

        assertThat(catchThrowable(() ->
                EObjects.asAttribute(reference)
        )).isExactlyInstanceOf(ClassCastException.class);
    }

    @Test
    public void testAsReference() throws Exception {
        EStructuralFeature attribute = mock(EAttribute.class);
        EStructuralFeature reference = mock(EReference.class);

        assertThat(EObjects.asReference(reference)).isInstanceOf(EReference.class);

        assertThat(catchThrowable(() ->
                EObjects.asReference(attribute))
        ).isExactlyInstanceOf(ClassCastException.class);
    }
}