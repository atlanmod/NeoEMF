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

import fr.inria.atlanmod.neoemf.io.persistence.PersistenceNotifier;
import fr.inria.atlanmod.neoemf.io.processor.EcoreProcessor;
import fr.inria.atlanmod.neoemf.io.processor.Processor;
import fr.inria.atlanmod.neoemf.io.processor.XPathProcessor;
import fr.inria.atlanmod.neoemf.io.structure.Attribute;
import fr.inria.atlanmod.neoemf.io.structure.Classifier;
import fr.inria.atlanmod.neoemf.io.structure.Identifier;
import fr.inria.atlanmod.neoemf.io.structure.MetaClassifier;
import fr.inria.atlanmod.neoemf.io.structure.Namespace;
import fr.inria.atlanmod.neoemf.io.structure.Reference;
import fr.inria.atlanmod.neoemf.io.structure.StructuralFeature;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;

/**
 * An abstract {@link Reader} that processes the raw structure of XMI files.
 */
public abstract class AbstractXmiReader extends AbstractReader {

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

    /**
     * Whether the current element has to be ignored.
     * <p>
     * Used when a special or unmanaged feature is encountered.
     */
    private boolean ignoreElement = false;

    @Override
    public Processor defaultProcessor() {
        Processor defaultProcessor;

        defaultProcessor = new PersistenceNotifier();
        defaultProcessor = new XPathProcessor(defaultProcessor);
        defaultProcessor = new EcoreProcessor(defaultProcessor);

        return defaultProcessor;
    }

    /**
     * Processes a new element and send a notification to handlers.
     *
     * @param uri        the URI of the element
     * @param localName  the name of the element
     * @param attributes the attributes of the element
     */
    protected void processStartElement(String uri, String localName, Iterable<javax.xml.stream.events.Attribute> attributes) {
        Classifier element = new Classifier(Namespace.Registry.getInstance().getFromUri(uri), localName);

        Collection<StructuralFeature> structuralFeatures = new ArrayList<>();

        // Processes features
        for (javax.xml.stream.events.Attribute attribute : attributes) {
            Collection<StructuralFeature> features = processFeatures(
                    element,
                    attribute.getName().getPrefix(),
                    attribute.getName().getLocalPart(),
                    attribute.getValue());

            if (ignoreElement) {
                // No need to go further
                return;
            }

            if (nonNull(features) && !features.isEmpty()) {
                structuralFeatures.addAll(features);
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
     * @param classifier the classifier representing the feature
     * @param prefix     the prefix of the feature
     * @param localName  the name of the feature
     * @param value      the value of the feature
     *
     * @return a list of {@link StructuralFeature} that can be empty.
     *
     * @see #processAttributes(String, String)
     * @see #processReferences(String, Collection)
     */
    private Collection<StructuralFeature> processFeatures(Classifier classifier, String prefix, String localName, String value) {
        Collection<StructuralFeature> features = null;

        if (!processSpecialFeatures(classifier, prefix, localName, value)) {
            Collection<String> references = getReferences(value);
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
     * @param classifier the classifier representing the feature
     * @param prefix     the prefix of the feature
     * @param localName  the name of the feature
     * @param value      the value of the feature
     *
     * @return {@code true} if the given feature is a special feature
     */
    private boolean processSpecialFeatures(Classifier classifier, String prefix, String localName, String value) {
        boolean isSpecialFeature = false;

        // A special feature always has a prefix
        if (nonNull(prefix) && !prefix.isEmpty()) {
            final String prefixedValue = prefix + ':' + localName;

            if (prefixedValue.matches(XmiConstants.XMI_XSI_TYPE)) { // xsi:type or xsi:type
                processMetaClass(classifier, value);
                isSpecialFeature = true;
            }
            else if (Objects.equals(XmiConstants.XMI_ID, prefixedValue)) { // xmi:id
                classifier.setId(Identifier.original(value));
                isSpecialFeature = true;
            }
            else if (Objects.equals(XmiConstants.XMI_IDREF, prefixedValue)) { // xmi:idref
                // It's not a feature of the current element, but a reference of the previous
                Reference reference = new Reference(classifier.getLocalName());
                reference.setIdReference(Identifier.original(value));
                notifyReference(reference);
                ignoreElement = true;
                isSpecialFeature = true;
            }
            else if (Objects.equals(XmiConstants.XMI_VERSION_ATTR, prefixedValue)) { // xmi:version
                NeoLogger.debug("XMI version : " + value);
                isSpecialFeature = true;
            }
        }
        else if (Objects.equals(XmiConstants.PROXY, localName)) {
            NeoLogger.warn("{0} is an external reference to {1}. This feature is not supported yet.",
                    classifier.getLocalName(), value);
            ignoreElement = true;
        }
        else if (Objects.equals(XmiConstants.NAME, localName)) {
            classifier.setClassName(value);
            isSpecialFeature = true;
        }

        return isSpecialFeature;
    }

    /**
     * Returns a list of {@link String} representing XPath references, or {@code null} if the {@code attribute} does not
     * match with {@link #PATTERN_WELL_FORMED_REF}.
     *
     * @param attribute the attribute to parse
     *
     * @return a list of {@link String} representing XPath references, or {@code null} if the {@code attribute} does not
     * match with {@link #PATTERN_WELL_FORMED_REF}
     *
     * @see #PATTERN_WELL_FORMED_REF
     */
    private Collection<String> getReferences(String attribute) {
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
     * Processes an attribute.
     *
     * @param localName the name of the attribute
     * @param value     the value of the attribute
     *
     * @return a singleton list of {@link StructuralFeature} containing the processed attribute.
     */
    private Collection<StructuralFeature> processAttributes(String localName, String value) {
        Attribute attribute = new Attribute(localName);
        attribute.setIndex(0);
        attribute.setValue(value);

        return Collections.singleton(attribute);
    }

    /**
     * Processes a list of {@code references} and returns a list of {@link Reference}.
     *
     * @param localName  the name of the reference
     * @param references the list that holds the identifier of referenced elements
     *
     * @return a list of {@link Reference} from the given {@code references}
     */
    private Collection<StructuralFeature> processReferences(String localName, Collection<String> references) {
        Collection<StructuralFeature> structuralFeatures = new ArrayList<>();

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
     * @param element       the element for which to define the metaclass
     * @param prefixedValue the value representing the metaclass
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
     * Processes the end of an element.
     *
     * @param uri       the URI of the element
     * @param localName the name of the element
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
