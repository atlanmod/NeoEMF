package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.sample.PrimaryObject;
import fr.inria.atlanmod.neoemf.tests.sample.TargetObject;

import org.eclipse.emf.ecore.util.FeatureMap;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the support of {@link FeatureMap}.
 */
public class FeatureMapTest extends AbstractBackendTest {

    /**
     * Checks that the {@link FeatureMap}s are correctly detected and created.
     */
    @Test
    public void detectionTest() {
        PersistentResource resource = newPersistentStore();

        PrimaryObject primary = EFACTORY.createPrimaryObject();
        resource.getContents().add(primary);

        FeatureMap featureMapAttributes = primary.getFeatureMapAttributeCollection();
        assertThat(featureMapAttributes).isInstanceOf(FeatureMap.class);

        FeatureMap featureMapReferences = primary.getFeatureMapReferenceCollection();
        assertThat(featureMapReferences).isInstanceOf(FeatureMap.class);
    }

    @Test
    @Ignore("Not supported yet")
    public void getSetWithAttributes() {
        String value0 = "Value0";
        String value1 = "Value1";
        String value2 = "Value2";
        String value3 = "Value3";

        PersistentResource resource = newPersistentStore();

        PrimaryObject primary = EFACTORY.createPrimaryObject();
        resource.getContents().add(primary);

        List<String> attributes1 = primary.getFeatureMapAttributeType1();
        attributes1.add(value0);
        attributes1.add(value1);

        List<String> attributes2 = primary.getFeatureMapAttributeType2();
        attributes2.add(value2);

        attributes1.add(value3);

        FeatureMap featureMapAttributes = primary.getFeatureMapAttributeCollection();
        assertThat(featureMapAttributes.getValue(0)).isEqualTo(value0);
        assertThat(featureMapAttributes.getValue(1)).isEqualTo(value1);
        assertThat(featureMapAttributes.getValue(2)).isEqualTo(value2);
        assertThat(featureMapAttributes.getValue(3)).isEqualTo(value3);
    }

    @Test
    @Ignore("Not supported yet")
    public void getSetWithReferences() {
        TargetObject target0 = EFACTORY.createTargetObject();
        target0.setName("Target0");

        TargetObject target1 = EFACTORY.createTargetObject();
        target1.setName("Target1");

        TargetObject target2 = EFACTORY.createTargetObject();
        target2.setName("Target2");

        TargetObject target3 = EFACTORY.createTargetObject();
        target2.setName("Target3");

        PersistentResource resource = newPersistentStore();

        PrimaryObject primary = EFACTORY.createPrimaryObject();
        resource.getContents().add(primary);

        List<TargetObject> references1 = primary.getFeatureMapReferenceType1();
        references1.add(target0);
        references1.add(target1);

        List<TargetObject> references2 = primary.getFeatureMapReferenceType2();
        references2.add(target2);

        references1.add(target3);

        FeatureMap featureMapAttributes = primary.getFeatureMapReferenceCollection();
        assertThat(featureMapAttributes.getValue(0)).isEqualTo(target0);
        assertThat(featureMapAttributes.getValue(1)).isEqualTo(target1);
        assertThat(featureMapAttributes.getValue(2)).isEqualTo(target2);
        assertThat(featureMapAttributes.getValue(3)).isEqualTo(target3);
    }
}
