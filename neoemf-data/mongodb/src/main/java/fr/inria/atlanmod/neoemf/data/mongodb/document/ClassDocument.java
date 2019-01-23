package fr.inria.atlanmod.neoemf.data.mongodb.document;

import fr.inria.atlanmod.neoemf.data.bean.ClassBean;

import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Represents a meta-class.
 */
@ParametersAreNonnullByDefault
public class ClassDocument {

    /**
     * The field name of the name of a meta-class.
     */
    @Nonnull
    public static final String F_NAME = "name";

    /**
     * The field name of the URI of a meta-class.
     */
    @Nonnull
    public static final String F_URI = "uri";

    /**
     * The name of this meta-class.
     */
    @BsonProperty(F_NAME)
    private String name;

    /**
     * The URI of this meta-class.
     */
    @BsonProperty(F_URI)
    private String uri;

    @Nonnull
    public static ClassDocument fromBean(ClassBean bean) {
        ClassDocument m = new ClassDocument();
        m.setName(bean.name());
        m.setUri(bean.uri());
        return m;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Nonnull
    public ClassBean toBean() {
        return ClassBean.of(name, uri);
    }
}
