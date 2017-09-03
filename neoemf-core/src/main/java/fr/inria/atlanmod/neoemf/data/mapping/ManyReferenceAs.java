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
 * multi-valued references. This behavior is specified by the {@link #manyReferenceConverter()} method.
 *
 * @param <M> the type of the references after mapping
 */
@ParametersAreNonnullByDefault
public interface ManyReferenceAs<M> extends ManyValueMapper, ManyReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(ManyFeatureBean key) {
        Converter<Id, M> func = manyReferenceConverter();

        return this.<M>valueOf(key)
                .map(func::doBackward);
    }

    @Nonnull
    @Override
    default List<Id> allReferencesOf(SingleFeatureBean key) {
        Converter<Id, M> func = manyReferenceConverter();

        return this.<M>allValuesOf(key).stream()
                .map(func::doBackward)
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        Converter<Id, M> func = manyReferenceConverter();

        return this.<M>valueFor(key, func.doForward(reference))
                .map(func::doBackward);
    }

    @Override
    default boolean hasAnyReference(SingleFeatureBean key) {
        return this.<M>hasAnyValue(key);
    }

    @Override
    default void addReference(ManyFeatureBean key, Id reference) {
        Converter<Id, M> func = manyReferenceConverter();

        this.<M>addValue(key, func.doForward(reference));
    }

    @Override
    default void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        Converter<Id, M> func = manyReferenceConverter();

        this.<M>addAllValues(key, collection.stream().map(func::doForward).collect(Collectors.toList()));
    }

    @Override
    default int appendReference(SingleFeatureBean key, Id reference) {
        Converter<Id, M> func = manyReferenceConverter();

        return this.<M>appendValue(key, func.doForward(reference));
    }

    @Override
    default int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        Converter<Id, M> func = manyReferenceConverter();

        return this.<M>appendAllValues(key, collection.stream().map(func::doForward).collect(Collectors.toList()));
    }

    @Nonnull
    @Override
    default Optional<Id> removeReference(ManyFeatureBean key) {
        Converter<Id, M> func = manyReferenceConverter();

        return this.<M>removeValue(key)
                .map(func::doBackward);
    }

    @Override
    default void removeAllReferences(SingleFeatureBean key) {
        this.<M>removeAllValues(key);
    }

    @Nonnull
    @Override
    default Optional<Id> moveReference(ManyFeatureBean source, ManyFeatureBean target) {
        Converter<Id, M> func = manyReferenceConverter();

        return this.<M>moveValue(source, target)
                .map(func::doBackward);
    }

    @Override
    default boolean containsReference(SingleFeatureBean key, @Nullable Id reference) {
        Converter<Id, M> func = manyReferenceConverter();

        return this.<M>containsValue(key, isNull(reference) ? null : func.doForward(reference));
    }

    @Nonnull
    @Override
    default Optional<Integer> indexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        Converter<Id, M> func = manyReferenceConverter();

        return this.<M>indexOfValue(key, isNull(reference) ? null : func.doForward(reference));
    }

    @Nonnull
    @Override
    default Optional<Integer> lastIndexOfReference(SingleFeatureBean key, @Nullable Id reference) {
        Converter<Id, M> func = manyReferenceConverter();

        return this.<M>lastIndexOfValue(key, isNull(reference) ? null : func.doForward(reference));
    }

    @Nonnull
    @Override
    default Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        return this.<M>sizeOfValue(key);
    }

    @Nonnull
    Converter<Id, M> manyReferenceConverter();
}
