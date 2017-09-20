package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.commons.BiConverter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.util.EObjects;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link BiConverter} that transforms the value of {@link FeatureMap.Entry} instances.
 */
@ParametersAreNonnullByDefault
public class FeatureMapConverter implements BiConverter<FeatureMap.Entry, EAttribute, String> {

    /**
     * The delimiter used to separate the feature from its value in a serialized {@link FeatureMap.Entry}.
     */
    @Nonnull
    private static final String FEATURE_MAP_DELIMITER = "#";

    /**
     * The store associated to this converter.
     */
    @Nonnull
    private final StoreAdapter store;

    /**
     * Constructs a new {@code FeatureMapConverter} for the given {@code store}.
     *
     * @param store the store associated to this converter
     */
    public FeatureMapConverter(StoreAdapter store) {
        this.store = store;
    }

    /**
     * Converts an instance of {@link FeatureMap.Entry} to a string literal representation.
     * <p>
     * The {@code entry} is serialized as {@code featureName#value}, where {@code value} is the string representation of
     * the {@link FeatureMap.Entry#getValue() value} for an attribute, or the string representation of the {@link Id}
     * for a reference.
     *
     * @param entry     the entry of the attribute
     * @param attribute the attribute to instantiate
     *
     * @return the string literal representation of the entry
     *
     * @see AttributeConverter#revert(String, EAttribute)
     * @see EcoreUtil#convertToString(EDataType, Object)
     */
    @Nonnull
    @Override
    public String convert(FeatureMap.Entry entry, @SuppressWarnings("unused") EAttribute attribute) {
        EStructuralFeature innerFeature = entry.getEStructuralFeature();
        String value;

        if (EObjects.isAttribute(innerFeature)) {
            EAttribute innerAttribute = EObjects.asAttribute(innerFeature);
            value = EcoreUtil.convertToString(innerAttribute.getEAttributeType(), entry.getValue());
        }
        else {
            PersistentEObject referencedObject = PersistentEObject.from(entry.getValue());
            store.updateInstanceOf(referencedObject);

            value = referencedObject.id().toString();
        }

        return innerFeature.getName() + FEATURE_MAP_DELIMITER + value;
    }

    /**
     * Creates an instance of {@link FeatureMap.Entry} from a string literal representation.
     * <p>
     * The {@code entry} must be serialized as {@code featureName#value}.
     *
     * @param entry     the string literal representation of the entry
     * @param attribute the attribute to instantiate
     *
     * @return the entry of the attribute
     *
     * @see AttributeConverter#convert(Object, EAttribute)
     * @see EcoreUtil#createFromString(EDataType, String)
     */
    @Nonnull
    @Override
    public FeatureMap.Entry revert(String entry, EAttribute attribute) {
        String[] splitValues = entry.split(FEATURE_MAP_DELIMITER, 2);

        EClass metaClass = attribute.getEContainingClass();
        EStructuralFeature innerFeature = metaClass.getEStructuralFeature(splitValues[0]);

        Object value;

        if (EObjects.isAttribute(innerFeature)) {
            EAttribute innerAttribute = EObjects.asAttribute(innerFeature);
            value = EcoreUtil.createFromString(innerAttribute.getEAttributeType(), splitValues[1]);
        }
        else {
            value = store.resolve(StringId.of(splitValues[1]));
        }

        return FeatureMapUtil.createEntry(innerFeature, value);
    }
}
