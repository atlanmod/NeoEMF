/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.input.xmi;

import com.google.common.base.Splitter;

import fr.inria.atlanmod.neoemf.io.input.impl.AbstractReader;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.xml.sax.Attributes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public abstract class AbstractXmiReader extends AbstractReader {

    private static final String XSI_NS = "xsi";
    private static final String XMI_NS = "xmi";

    private static final String TYPE_ATTR = XSI_NS + ":type";

    private static final String ID_ATTR = XMI_NS + ":id";

    private static final Pattern PATTERN_WELL_FORMED_REF =
            Pattern.compile("(/{1,2}@\\w+(\\.\\d+)?[ ]?)+", Pattern.UNICODE_CASE);

    private static final Pattern PATTERN_PREFIXED_VALUE =
            Pattern.compile("(\\w+):(\\w+)");

    protected void processElement(String uri, String name) throws Exception {

        // TODO If element has the `xmi:id` attribute, send it as the identifier of the element

        notifyStartElement(uri, name, null);
    }

    protected void processAttribute(String qName, String nsUri, String name, String value) throws Exception {
        if (isMetaClass(qName)) {
            try {
                processMetaClass(value);
                return;
            } catch (IllegalArgumentException e) {
                NeoLogger.warn(e);
            }
        }

        Iterable<String> references = getReferences(value);
        if (references != null) {
            processReferences(nsUri, name, references);
        } else {
            notifyAttribute(nsUri, name, 0, value);
        }
    }

    private void processReferences(String nsUri, String name, Iterable<String> references) throws Exception {
        int index = 0;
        for (String ref : references) {
            notifyReference(nsUri, name, index, ref);
            index++;
        }
    }

    private void processMetaClass(String prefixedValue) throws Exception {
        String nsUsi;
        String name;

        Matcher m = PATTERN_PREFIXED_VALUE.matcher(prefixedValue);
        if (m.find()) {
            nsUsi = getNsUri(m.group(1));
            name = m.group(2);
        } else {
            throw new IllegalArgumentException();
        }

        notifyMetaClass(nsUsi, name);
    }

    private boolean isMetaClass(String value) {
        return TYPE_ATTR.equals(value);
    }

    private boolean hasIdentifier(Attributes attributes) {
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.getQName(i).equals(ID_ATTR)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getReferences(String value) {
        if (value.trim().isEmpty()) {
            return null;
        }

        List<String> references = Splitter.on(" ").omitEmptyStrings().trimResults().splitToList(value);

        boolean isReference = true;
        for (int i = 0, referencesSize = references.size(); i < referencesSize && isReference; i++) {
            String ref = references.get(i);
            isReference = PATTERN_WELL_FORMED_REF.matcher(ref).matches();
        }

        return isReference ? references : null;
    }
}
