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
import fr.inria.atlanmod.neoemf.io.beans.Identifier;
import fr.inria.atlanmod.neoemf.io.beans.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.beans.Namespace;
import fr.inria.atlanmod.neoemf.io.beans.Reference;
import fr.inria.atlanmod.neoemf.io.beans.StructuralFeature;
import fr.inria.atlanmod.neoemf.io.input.impl.AbstractReader;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An abstract implementation of a {@link fr.inria.atlanmod.neoemf.io.input.Reader reader} able to process {@code
 * XMI} files.
 */
public abstract class AbstractXmiReader extends AbstractReader {

    protected static final String XSI_NS = "xsi";

    protected static final String XMI_NS = "xmi";

    /**
     * The attribute key representing the identifier of an element.
     */
    private static final String XMI_ID = format(XMI_NS, "id");

    /**
     * The attribute key representing the identifier of an element.
     */
    private static final String XMI_IDREF = format(XMI_NS, "idref");

    /**
     * The attribute key representing the metaclass of an element.
     */
    private static final String XMI_XSI_TYPE = format("(" + XMI_NS + "|" + XSI_NS + ")", "type");

    /**
     * The attribute key representing the version of the parsed XMI file.
     */
    private static final String XMI_VERSION_ATTR = format(XMI_NS , "version");

    /**
     * A regex pattern of an attribute containing one or several references (XPath reference).
     * <p/>
     * Example of recognized strings : {@code "//@&lt;name1&gt;.&lt;index1&gt;/@&lt;name2&gt;"}
     * <p/>
     * Multiple references must be seperated by a space
     */
    private static final Pattern PATTERN_WELL_FORMED_REF =
            Pattern.compile("(/{1,2}@\\w+(\\.\\d+)?[ ]?)+", Pattern.UNICODE_CASE);

    /**
     * A regex pattern of a prefixed value.
     * <p/>
     * Example of recognized strings : {@code "&lt;prefix&gt;:&lt;name&gt;"}
     */
    private static final Pattern PATTERN_PREFIXED_VALUE =
            Pattern.compile("(\\w+):(\\w+)");

    private boolean isIdRef = false;

    /**
     * Processes a new element and send a notification to handlers.
     */
    protected void processElement(String uri, String localName, Attributes attributes) throws Exception {
        Classifier element = new Classifier(Namespace.Registry.getInstance().getFromUri(uri), localName);

        int attrLength = attributes.getLength();

        List<StructuralFeature> structuralFeatures = new ArrayList<>(attrLength);

        // Processes attributes / Check "xmi:id" and "xsi:type"
        if (attrLength > 0) {
            for (int i = 0; i < attrLength; i++) {
                List<StructuralFeature> features = processAttribute(element,
                        getPrefix(attributes.getQName(i)),
                        attributes.getLocalName(i),
                        attributes.getValue(i));

                if (isIdRef) {
                    //No need to go further, the reference has been notified
                    return;
                }

                if (features != null && !features.isEmpty()) {
                    structuralFeatures.addAll(features);
                }
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

    /**
     * Processes an attribute

     * @return a list of {@link StructuralFeature structural features} that can be empty.
     */
    private List<StructuralFeature> processAttribute(Classifier element, String prefix, String locaName, String value) throws Exception {
        if (prefix != null) {
            final String prefixedValue = prefix + ':' + locaName;

            // xsi:type
            if (prefixedValue.matches(XMI_XSI_TYPE)) {
                processMetaClass(element, value);
                return null;
            }

            // xmi:id
            if (XMI_ID.equals(prefixedValue)) {
                element.setId(Identifier.original(value));
                return null;
            }

            if (XMI_IDREF.equals(prefixedValue)) {
                //It's not a feature of the current element, but a reference of the previous
                Reference reference = new Reference(locaName);
                reference.setValue(value);
                notifyReference(reference);
                isIdRef = true;
                return null;
            }

            if (XMI_VERSION_ATTR.equals(prefixedValue)) {
                // Do nothing
                return null;
            }
        }

        List<String> references = getReferences(value);
        if (references != null) {
            return processReferences(locaName, references);
        }
        else {
            StructuralFeature structuralFeature = new Attribute(locaName);
            structuralFeature.setIndex(0);
            structuralFeature.setValue(value);

            return Lists.newArrayList(structuralFeature);
        }
    }

    /**
     * Processes a list of {@code references} and returns a list of {@link Reference}.

     * @return a list of {@link Reference} from the given {@code references}
     */
    private List<StructuralFeature> processReferences(String localName, List<String> references) throws Exception {
        List<StructuralFeature> structuralFeatures = new ArrayList<>(references.size());

        int index = 0;
        for (String s : references) {
            Reference ref = new Reference(localName);
            ref.setIndex(index);
            ref.setValue(s);
            structuralFeatures.add(ref);
            index++;
        }

        return structuralFeatures;
    }

    /**
     * Processes a metaclass attribute from the given {@code prefixedValue}, and defines is as the metaclass of the
     * given {@code element}.
     *
     * @see #PATTERN_PREFIXED_VALUE
     */
    private void processMetaClass(Classifier element, String prefixedValue) throws Exception {
        Matcher m = PATTERN_PREFIXED_VALUE.matcher(prefixedValue);
        if (m.find()) {
            MetaClassifier metaClassifier = new MetaClassifier(Namespace.Registry.getInstance().getFromPrefix(m.group(1)), m.group(2));
            element.setMetaClassifier(metaClassifier);
        } else {
            throw new IllegalArgumentException("Malformed metaclass " + prefixedValue);
        }
    }

    protected void processCharacters(String characters) throws Exception {
        notifyCharacters(characters);
    }

    protected void processEndElement(String uri, String localName) throws Exception {
        if (!isIdRef) {
            notifyEndElement();
        } else {
            isIdRef = false;
        }
    }

    /**
     * Returns a list of {@code String} representing XPath references, or {@code null} if the given {@code attribute}
     * does not match with {@link #PATTERN_WELL_FORMED_REF}.
     *
     * @return a list of {@code String} representing XPath references, or {@code null} if the given {@code attribute}
     * does not match with {@link #PATTERN_WELL_FORMED_REF}
     *
     * @see #PATTERN_WELL_FORMED_REF
     */
    private static List<String> getReferences(String attribute) {
        if (attribute.trim().isEmpty()) {
            return null;
        }

        List<String> references = Splitter.on(" ").omitEmptyStrings().trimResults().splitToList(attribute);

        boolean isReference = true;
        for (int i = 0, referencesSize = references.size(); i < referencesSize && isReference; i++) {
            String ref = references.get(i);
            isReference = PATTERN_WELL_FORMED_REF.matcher(ref).matches();
        }

        return isReference ? references : null;
    }

    /**
     * Returns the prefix of the given {@code prefixedValue}, or {@code null} if there is no prefix.
     * @return the prefix of the given {@code prefixedValue}, or {@code null} if there is no prefix
     */
    private static String getPrefix(String prefixedValue) {
        String prefix = null;

        if (prefixedValue == null) {
            return null;
        }

        List<String> splittedName = Splitter.on(":").omitEmptyStrings().trimResults().splitToList(prefixedValue);
        if (splittedName.size() > 1) {
            prefix = splittedName.get(0);
        }

        return prefix;
    }
}
