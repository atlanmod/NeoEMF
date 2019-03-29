/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb.document;

import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

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

    /**
     * Converts the specified {@code bean} in a {@link ClassDocument}.
     *
     * @param bean the bean
     *
     * @return the document
     */
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

    /**
     * Converts this document in a {@link SingleFeatureBean}.
     *
     * @return a bean
     */
    @Nonnull
    public ClassBean toBean() {
        return ClassBean.of(name, uri);
    }
}
