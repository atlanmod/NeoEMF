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

import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.ClassifierElement;
import fr.inria.atlanmod.neoemf.io.beans.Feature;
import fr.inria.atlanmod.neoemf.io.beans.NamedElement;
import fr.inria.atlanmod.neoemf.io.beans.Namespace;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.io.input.impl.AbstractReader;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public abstract class AbstractXmiReader extends AbstractReader {

    private static final String ID_ATTR = "xmi:id";

    private static final String TYPE_ATTR = "xsi:type";

    private static final Pattern PATTERN_WELL_FORMED_REF =
            Pattern.compile("(/{1,2}@\\w+(\\.\\d+)?[ ]?)+", Pattern.UNICODE_CASE);

    private static final Pattern PATTERN_PREFIXED_VALUE =
            Pattern.compile("(\\w+):(\\w+)");

    protected void processElement(String uri, String localName, Attributes attributes) throws Exception {

        ClassifierElement element = new ClassifierElement();
        element.setNamespace(Namespace.Registry.getInstance().getFromUri(uri));
        element.setLocalName(localName);

        List<Feature> features = new ArrayList<>();

        // Processes attributes / Check "xmi:id" and "xsi:type"
        int attrLength = attributes.getLength();
        if (attrLength > 0) {
            for (int i = 0; i < attrLength; i++) {
                features.addAll(processAttribute(
                        element,
                        getPrefix(attributes.getQName(i)),
                        attributes.getLocalName(i),
                        attributes.getValue(i)));
            }
        }

        notifyStartElement(element);

        // Send attributes and references
        for (Feature f : features) {
            if (f.isAttribute()) {
                notifyAttribute((Attribute) f);
            } else {
                notifyReference((Reference) f);
            }
        }
    }

    private static String getPrefix(String qName) {
        String prefix = null;

        if (qName == null) {
            return null;
        }

        List<String> splittedName = Splitter.on(":").omitEmptyStrings().trimResults().splitToList(qName);
        if (splittedName.size() > 1) {
            prefix = splittedName.get(0);
        }

        return prefix;
    }

    private List<Feature> processAttribute(ClassifierElement element, String prefix, String locaName, String value) throws Exception {
        if (prefix != null) {
            // xsi:type
            if (TYPE_ATTR.equals(prefix + ":" + locaName)) {
                processMetaClass(element, value);
                return Collections.emptyList();
            }

            // xmi:id
            if (ID_ATTR.equals(prefix + ":" + locaName)) {
                element.setId(value);
                return Collections.emptyList();
            }
        }

        Namespace ns = Namespace.Registry.getInstance().getFromPrefix(prefix);

        Iterable<String> references = getReferences(value);
        if (references != null) {
            return processReferences(ns, locaName, references);
        }
        else {
            List<Feature> features = new ArrayList<>();

            Feature feature = new Attribute();
            feature.setNamespace(ns);
            feature.setLocalName(locaName);
            feature.setIndex(0);
            feature.setValue(value);
            features.add(feature);

            return features;
        }
    }

    private List<Feature> processReferences(Namespace ns, String name, Iterable<String> references) throws Exception {
        List<Feature> features = new ArrayList<>();

        int index = 0;
        for (String ref : references) {
            Feature feature = new Reference();
            feature.setNamespace(ns);
            feature.setLocalName(name);
            feature.setIndex(index);
            feature.setValue(ref);
            features.add(feature);
            index++;
        }

        return features;
    }

    private void processMetaClass(ClassifierElement element, String prefixedValue) throws Exception {
        Matcher m = PATTERN_PREFIXED_VALUE.matcher(prefixedValue);
        if (m.find()) {
            NamedElement metaClass = new NamedElement();
            metaClass.setNamespace(Namespace.Registry.getInstance().getFromPrefix(m.group(1)));
            metaClass.setLocalName(m.group(2));
            element.setMetaclass(metaClass);
        } else {
            throw new IllegalArgumentException();
        }
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

    protected void processCharacters(String characters) throws Exception {
        notifyCharacters(characters);
    }
}
