package fr.inria.atlanmod.neoemf.data.store.adapter;

import fr.inria.atlanmod.commons.BiConverter;
import fr.inria.atlanmod.commons.LazyObject;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

/**
 * A {@link BiConverter} that transforms the value of {@link EAttribute} instances.
 */
@ParametersAreNonnullByDefault
public class AttributeConverter implements BiConverter<Object, EAttribute, String> {

    /**
     * The converter or {@link FeatureMap.Entry} instances.
     */
    @Nonnull
    private final LazyObject<FeatureMapConverter> featureMapConverter;

    /**
     * Constructs a new {@code AttributeConverter} for the given {@code store}.
     *
     * @param store the store associated to this converter
     */
    public AttributeConverter(StoreAdapter store) {
        this.featureMapConverter = LazyObject.with(() -> new FeatureMapConverter(store));
    }

    /**
     * Converts an instance of the {@code attribute} to a string literal representation.
     *
     * @param value     the value of the attribute
     * @param attribute the attribute to instantiate
     *
     * @return the string literal representation of the value, or {@code null} if the {@code value} is {@code null}
     *
     * @see #revert(String, EAttribute)
     * @see EcoreUtil#convertToString(EDataType, Object)
     */
    @Override
    public String convert(@Nullable Object value, EAttribute attribute) {
        if (isNull(value)) {
            return null;
        }

        final EDataType dataType = attribute.getEAttributeType();

        if (FeatureMapUtil.isFeatureMapEntry(dataType)) {
            return featureMapConverter.get().convert(FeatureMap.Entry.class.cast(value), attribute);
        }

        return EcoreUtil.convertToString(dataType, value);
    }


    /**
     * Creates an instance of the {@code attribute} from a string literal representation.
     *
     * @param value     the string literal representation of the value
     * @param attribute the attribute to instantiate
     *
     * @return the value of the attribute, or {@code null} if the {@code value} is {@code null}
     *
     * @see #convert(Object, EAttribute)
     * @see EcoreUtil#createFromString(EDataType, String)
     */
    @Override
    public Object revert(@Nullable String value, EAttribute attribute) {
        if (isNull(value)) {
            return null;
        }

        final EDataType dataType = attribute.getEAttributeType();

        if (FeatureMapUtil.isFeatureMapEntry(dataType)) {
            return featureMapConverter.get().revert(value, attribute);
        }

        return EcoreUtil.createFromString(dataType, value);
    }
}
