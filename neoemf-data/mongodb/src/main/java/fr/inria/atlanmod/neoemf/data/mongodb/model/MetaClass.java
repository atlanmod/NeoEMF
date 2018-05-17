package fr.inria.atlanmod.neoemf.data.mongodb.model;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Represents a meta-class.
 */
@ParametersAreNonnullByDefault
public class MetaClass {

    private String name;

    private String uri;

    @VisibleForReflection
    public MetaClass() {
    }

    public MetaClass(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    @Nonnull
    public static MetaClass fromBean(ClassBean bean) {
        return new MetaClass(bean.name(), bean.uri());
    }

    public String getName() {
        return name;
    }

    @VisibleForReflection
    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    @VisibleForReflection
    public void setUri(String uri) {
        this.uri = uri;
    }

    @Nonnull
    public ClassBean toBean() {
        return ClassBean.of(name, uri);
    }
}
