package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.commons.Converter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

/**
 * A {@link ManyReferenceMapper} that provides a default behavior to use {@link M} instead of {@link Id} for
 * multi-valued references.
 *
 * @param <M> the type of the references after mapping
 */
@ParametersAreNonnullByDefault
public interface ManyReferenceAs<M> extends ManyValueMapper, ManyReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(ManyFeatureBean key) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>valueOf(key)
                .map(converter::revert);
    }

    @Nonnull
    @Override
    default List<Id> allReferencesOf(SingleFeatureBean key) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>allValuesOf(key).stream()
                .map(converter::revert)
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>valueFor(key, converter.convert(reference))
                .map(converter::revert);
    }

    @Override
    default void addReference(ManyFeatureBean key, Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        this.<M>addValue(key, converter.convert(reference));
    }

    @Override
    default void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        Converter<Id, M> converter = manyReferenceConverter();

        this.<M>addAllValues(key, collection.stream().map(converter::convert).collect(Collectors.toList()));
    }

    @Override
    default int appendReference(SingleFeatureBean key, Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>appendValue(key, converter.convert(reference));
    }

    @Override
    default int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>appendAllValues(key, collection.stream().map(converter::convert).collect(Collectors.toList()));
    }

    @Nonnull
    @Override
    default Optional<Id> removeReference(ManyFeatureBean key) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>removeValue(key)
                .map(converter::revert);
    }

    @Override
    default void removeAllReferences(SingleFeatureBean key) {
        this.<M>removeAllValues(key);
    }

    @Nonnull
    @Override
    default Optional<Id> moveReference(ManyFeatureBean source, ManyFeatureBean target) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>moveValue(source, target)
                .map(converter::revert);
    }

    @Override
    default boolean containsReference(SingleFeatureBean key, @Nullable Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>containsValue(key, isNull(reference) ? null : converter.convert(reference));
    }

    @Nonnull
    @Override
    default Optional<Integer> indexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>indexOfValue(key, isNull(reference) ? null : converter.convert(reference));
    }

    @Nonnull
    @Override
    default Optional<Integer> lastIndexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>lastIndexOfValue(key, isNull(reference) ? null : converter.convert(reference));
    }

    @Nonnull
    @Override
    default Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return this.<M>sizeOfValue(key);
    }

    /**
     * Returns the converter used to transform a multi-valued reference to the desired type.
     *
     * @return the converter
     */
    @Nonnull
    Converter<Id, M> manyReferenceConverter();
}
