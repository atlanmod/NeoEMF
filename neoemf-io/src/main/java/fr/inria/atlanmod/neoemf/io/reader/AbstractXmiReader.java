/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io.reader;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Identifier;
import fr.inria.atlanmod.neoemf.io.structure.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.Reference;
import fr.inria.atlanmod.neoemf.io.structure.StructuralFeature;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.xml.sax.Attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;

/**
 * An abstract implementation of {@link Reader} that processes XMI files.
 */
public abstract class AbstractXmiReader extends AbstractReader {

    /**
     * The namespace prefix of XSI.
     */
    protected static final String XSI_NS = "xsi";

    /**
     * The namespace prefix of XMI.
     */
    protected static final String XMI_NS = "xmi";

    /**
     * The attribute key representing the identifier of an element.
     */
    private static final String XMI_ID = format(XMI_NS, "id");

    /**
     * The attribute key representing a reference to an identified element.
     *
     * @see #XMI_ID
     */
    private static final String XMI_IDREF = format(XMI_NS, "idref");

    /**
     * The attribute key representing the metaclass of an element.
     */
    private static final String XMI_XSI_TYPE = format("(" + XMI_NS + "|" + XSI_NS + ")", "type");

    /**
     * The attribute key representing the version of the parsed XMI file.
     */
    private static final String XMI_VERSION_ATTR = format(XMI_NS, "version");

    /**
     * The attribute key representing a link to another document.
     */
    private static final String PROXY = "href";

    /**
     * The attribute key representing a name of an element.
     */
    private static final String NAME = "name";

    /**
     * A regex pattern of an attribute containing one or several references (XPath reference). Multiple references must
     * be separated by a space.
     * <p>
     * Example of recognized strings : {@code "//@&lt;name1&gt;.&lt;index1&gt;/@&lt;name2&gt;"}
     */
    private static final Pattern PATTERN_WELL_FORMED_REF =
            Pattern.compile("(/{1,2}@\\w+(\\.\\d+)?[ ]?)+", Pattern.UNICODE_CASE);

    /**
     * A regex pattern of a prefixed value.
     * <p>
     * Example of recognized strings : {@code "&lt;prefix&gt;:&lt;name&gt;"}
     */
    private static final Pattern PATTERN_PREFIXED_VALUE =
            Pattern.compile("(\\w+):(\\w+)");

    private boolean ignoreElement = false;

    /**
     * Returns a list of {@code String} representing XPath references, or {@code null} if the {@code attribute} does not
     * match with {@link #PATTERN_WELL_FORMED_REF}.
     *
     * @return a list of {@code String} representing XPath references, or {@code null} if the {@code attribute} does not
     * match with {@link #PATTERN_WELL_FORMED_REF}
     *
     * @see #PATTERN_WELL_FORMED_REF
     */
    private static List<String> getReferences(String attribute) {
        List<String> references = null;

        if (!attribute.trim().isEmpty()) {
            references = Splitter.on(" ").omitEmptyStrings().trimResults().splitToList(attribute);

            boolean isReference = true;
            for (int i = 0, referencesSize = references.size(); i < referencesSize && isReference; i++) {
                String ref = references.get(i);
                isReference = PATTERN_WELL_FORMED_REF.matcher(ref).matches();
            }

            if (!isReference) {
                references = null;
            }
        }

        return references;
    }

    /**
     * Returns the prefix of the {@code prefixedValue}, or {@code null} if there is no prefix.
     *
     * @return the prefix of the {@code prefixedValue}, or {@code null} if there is no prefix
     */
    private static String getPrefix(String prefixedValue) {
        String prefix = null;

        if (nonNull(prefixedValue)) {
            List<String> splittedName = Splitter.on(":").omitEmptyStrings().trimResults().splitToList(prefixedValue);
            if (splittedName.size() > 1) {
                prefix = splittedName.get(0);
            }
        }

        return prefix;
    }

    /**
     * Processes a new element and send a notification to handlers.
     */
    protected void processStartElement(String uri, String localName, Attributes attributes) {
        Classifier element = new Classifier(Namespace.Registry.getInstance().getFromUri(uri), localName);

        int attrLength = attributes.getLength();

        List<StructuralFeature> structuralFeatures = new ArrayList<>(attrLength);

        // Processes features
        if (attrLength > 0) {
            for (int i = 0; i < attrLength; i++) {
                List<StructuralFeature> features = processFeatures(element,
                        getPrefix(attributes.getQName(i)),
                        attributes.getLocalName(i),
                        attributes.getValue(i));

                if (ignoreElement) {
                    // No need to go further
                    return;
                }

                if (nonNull(features) && !features.isEmpty()) {
                    structuralFeatures.addAll(features);
                }
            }
        }

        notifyStartElement(element);

        // Send attributes and references
        for (StructuralFeature feature : structuralFeatures) {
            if (feature.isAttribute()) {
                notifyAttribute((Attribute) feature);
            }
            else {
                notifyReference((Reference) feature);
            }
        }
    }

    /**
     * Processes a feature, which can be an attribute or a reference.
     *
     * @return a list of {@code StructuralFeature} that can be empty.
     *
     * @see #processAttributes(String, String)
     * @see #processReferences(String, List)
     */
    private List<StructuralFeature> processFeatures(Classifier classifier, String prefix, String localName, String value) {
        List<StructuralFeature> features = null;

        if (!processSpecialFeatures(classifier, prefix, localName, value)) {
            List<String> references = getReferences(value);
            if (nonNull(references)) {
                features = processReferences(localName, references);
            }
            else {
                features = processAttributes(localName, value);
            }
        }

        return features;
    }

    /**
     * Processes a special feature as 'xsi:type', 'xmi:id' or 'xmi:idref'.
     *
     * @return {@code true} if the given feature is a special feature
     */
    private boolean processSpecialFeatures(Classifier classifier, String prefix, String localName, String value) {
        boolean isSpecialFeature = false;

        // A special feature always has a prefix
        if (nonNull(prefix)) {
            final String prefixedValue = prefix + ':' + localName;

            if (prefixedValue.matches(XMI_XSI_TYPE)) { // xsi:type or xsi:type
                processMetaClass(classifier, value);
                isSpecialFeature = true;
            }
            else if (Objects.equals(XMI_ID, prefixedValue)) { // xmi:id
                classifier.setId(Identifier.original(value));
                isSpecialFeature = true;
            }
            else if (Objects.equals(XMI_IDREF, prefixedValue)) { // xmi:idref
                // It's not a feature of the current element, but a reference of the previous
                Reference reference = new Reference(classifier.getLocalName());
                reference.setIdReference(Identifier.original(value));
                notifyReference(reference);
                ignoreElement = true;
                isSpecialFeature = true;
            }
            else if (Objects.equals(XMI_VERSION_ATTR, prefixedValue)) { // xmi:version
                NeoLogger.debug("XMI version : " + value);
                isSpecialFeature = true;
            }
        }
        else if (Objects.equals(PROXY, localName)) {
            NeoLogger.warn("{0} is an external reference to {1}. This feature is not supported yet.",
                    classifier.getLocalName(), value);
            ignoreElement = true;
        }
        else if (Objects.equals(NAME, localName)) {
            classifier.setClassName(value);
            isSpecialFeature = true;
        }

        return isSpecialFeature;
    }

    /**
     * Processes an attribute.
     *
     * @return a singleton list of {@code StructuralFeature} containing the processed attribute.
     */
    private List<StructuralFeature> processAttributes(String localName, String value) {
        Attribute attribute = new Attribute(localName);
        attribute.setIndex(0);
        attribute.setValue(value);

        return Lists.newArrayList((StructuralFeature) attribute);
    }

    /**
     * Processes a list of {@code references} and returns a list of {@link Reference}.
     *
     * @return a list of {@code Reference} from the given {@code references}
     */
    private List<StructuralFeature> processReferences(String localName, List<String> references) {
        List<StructuralFeature> structuralFeatures = new ArrayList<>(references.size());

        int index = 0;
        for (String s : references) {
            Reference ref = new Reference(localName);
            ref.setIndex(index);
            ref.setIdReference(Identifier.generated(s));
            structuralFeatures.add(ref);
            index++;
        }

        return structuralFeatures;
    }

    /**
     * Processes a metaclass attribute from the {@code prefixedValue}, and defines is as the metaclass of the given
     * {@code element}.
     *
     * @see #PATTERN_PREFIXED_VALUE
     */
    private void processMetaClass(Classifier element, String prefixedValue) {
        Matcher m = PATTERN_PREFIXED_VALUE.matcher(prefixedValue);
        if (m.find()) {
            MetaClassifier metaClassifier = new MetaClassifier(Namespace.Registry.getInstance().getFromPrefix(m.group(1)), m.group(2));
            element.setMetaClassifier(metaClassifier);
        }
        else {
            throw new IllegalArgumentException("Malformed metaclass " + prefixedValue);
        }
    }

    /**
     * Processes characters.
     */
    protected void processCharacters(String characters) {
        notifyCharacters(characters);
    }

    /**
     * Processes the end of an element.
     */
    protected void processEndElement(String uri, String localName) {
        if (!ignoreElement) {
            notifyEndElement();
        }
        else {
            ignoreElement = false;
        }
    }
}
