/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.reader;

import fr.inria.atlanmod.neoemf.io.processor.AbstractProcessor;
import fr.inria.atlanmod.neoemf.io.processor.EcoreMapper;
import fr.inria.atlanmod.neoemf.io.processor.XPathResolver;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyValue;
import fr.inria.atlanmod.neoemf.io.util.XmiConstants;

import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.primitive.Strings;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * An {@link AbstractStreamReader} that processes the raw structure of XMI files.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractXmiStreamReader extends AbstractStreamReader {

    @Nonnull
    @Override
    protected List<AbstractProcessor> createProcessors() {
        return Arrays.asList(new EcoreMapper(), new XPathResolver());
    }

    @Override
    protected final boolean isSpecialAttribute(@Nullable String prefix, String name, String value) throws IOException {
        if (Objects.equals(XmiConstants.HREF, name)) { // A link to an external resource
            Log.warn("External features are not supported yet");

            ignoreCurrentElement();
            return true;
        }

        if (nonNull(Strings.emptyToNull(prefix))) {
            final String prefixedValue = XmiConstants.format(prefix, name);

            if (Objects.equals(XmiConstants.XMI_IDREF, prefixedValue)) { // A reference of the previous element
                ProxyReference reference = new ProxyReference()
                        .setName(getCurrentElement().getName())
                        .setOwner(getPreviousId())
                        .setValue(ProxyValue.raw(value));

                notifyReference(reference);

                ignoreCurrentElement();
                return true;
            }

            if (Objects.equals(XmiConstants.XMI_ID, prefixedValue)) { // The identifier of the current element
                getCurrentElement().setId(ProxyValue.raw(value));

                return true;
            }

            if (Objects.equals(XmiConstants.XMI_VERSION_ATTR, prefixedValue)) { // The version of the read XMI file
                return true;
            }

            if (prefixedValue.matches(XmiConstants.XMI_XSI_TYPE)) { // The meta-class of the current element
                readMetaClass(value);

                return true;
            }
        }

        return false;
    }
}
