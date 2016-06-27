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
import com.google.common.collect.Lists;

import fr.inria.atlanmod.neoemf.io.beans.Attribute;
import fr.inria.atlanmod.neoemf.io.beans.Classifier;
import fr.inria.atlanmod.neoemf.io.beans.NamedElement;
import fr.inria.atlanmod.neoemf.io.beans.Namespace;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.io.beans.StructuralFeature;
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
        Classifier element = new Classifier();
        element.setNamespace(Namespace.Registry.getInstance().getFromUri(uri));
        element.setLocalName(localName);

        int attrLength = attributes.getLength();

        List<StructuralFeature> structuralFeatures = new ArrayList<>(attrLength);

        // Processes attributes / Check "xmi:id" and "xsi:type"
        if (attrLength > 0) {
            for (int i = 0; i < attrLength; i++) {
                structuralFeatures.addAll(processAttribute(
                        element,
                        getPrefix(attributes.getQName(i)),
                        attributes.getLocalName(i),
                        attributes.getValue(i)));
            }
        }

        notifyStartElement(element);

        // Send attributes and references
        for (StructuralFeature feature : structuralFeatures) {
            if (feature.isAttribute()) {
                notifyAttribute((Attribute) feature);
            } else {
                notifyReference((Reference) feature);
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

    private List<StructuralFeature> processAttribute(Classifier element, String prefix, String locaName, String value) throws Exception {
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

        List<String> references = getReferences(value);
        if (references != null) {
            return processReferences(ns, locaName, references);
        }
        else {
            StructuralFeature structuralFeature = new Attribute();
            structuralFeature.setNamespace(ns);
            structuralFeature.setLocalName(locaName);
            structuralFeature.setIndex(0);
            structuralFeature.setValue(value);

            return Lists.newArrayList(structuralFeature);
        }
    }

    private List<StructuralFeature> processReferences(Namespace ns, String name, List<String> references) throws Exception {
        List<StructuralFeature> structuralFeatures = new ArrayList<>(references.size());

        int index = 0;
        for (String ref : references) {
            StructuralFeature structuralFeature = new Reference();
            structuralFeature.setNamespace(ns);
            structuralFeature.setLocalName(name);
            structuralFeature.setIndex(index);
            structuralFeature.setValue(ref);
            structuralFeatures.add(structuralFeature);
            index++;
        }

        return structuralFeatures;
    }

    private void processMetaClass(Classifier element, String prefixedValue) throws Exception {
        Matcher m = PATTERN_PREFIXED_VALUE.matcher(prefixedValue);
        if (m.find()) {
            NamedElement metaClass = new NamedElement();
            metaClass.setNamespace(Namespace.Registry.getInstance().getFromPrefix(m.group(1)));
            metaClass.setLocalName(m.group(2));
            element.setMetaclass(metaClass);
        } else {
            throw new IllegalArgumentException("Malformed metaclass " + prefixedValue);
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
