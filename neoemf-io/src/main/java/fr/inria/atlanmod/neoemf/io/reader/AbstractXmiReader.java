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
import fr.inria.atlanmod.neoemf.io.structure.RawAttribute;
import fr.inria.atlanmod.neoemf.io.structure.RawClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawFeature;
import fr.inria.atlanmod.neoemf.io.structure.RawIdentifier;
import fr.inria.atlanmod.neoemf.io.structure.RawMetaClassifier;
import fr.inria.atlanmod.neoemf.io.structure.RawNamespace;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.xml.stream.events.Attribute;

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
    protected void readStartElement(String uri, String localName, Iterable<Attribute> attributes) {
        RawClassifier element = new RawClassifier(RawNamespace.Registry.getInstance().getFromUri(uri), localName);

        Collection<RawFeature> allFeatures = new ArrayList<>();

        // Processes features
        for (Attribute attribute : attributes) {
            Collection<RawFeature> localFeatures = processFeatures(
                    element,
                    attribute.getName().getPrefix(),
                    attribute.getName().getLocalPart(),
                    attribute.getValue());

            if (ignoreElement) {
                // No need to go further
                return;
            }

            if (!localFeatures.isEmpty()) {
                allFeatures.addAll(localFeatures);
            }
        }

        notifyStartElement(element);

        // Send attributes and references
        for (RawFeature feature : allFeatures) {
            if (feature.isAttribute()) {
                notifyAttribute((RawAttribute) feature);
            }
            else {
                notifyReference((RawReference) feature);
            }
        }
    }

    /**
     * Processes the end of an element.
     */
    protected void readEndElement() {
        if (!ignoreElement) {
            notifyEndElement();
        }
        else {
            ignoreElement = false;
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
     * @return a list of {@link RawFeature} that can be empty.
     *
     * @see #processAttribute(String, String)
     * @see #processReferences(String, Collection)
     */
    @Nonnull
    private Collection<RawFeature> processFeatures(RawClassifier classifier, String prefix, String localName, String value) {
        Collection<RawFeature> features;

        if (!processSpecialFeatures(classifier, prefix, localName, value)) {
            Collection<String> references = getReferences(value);
            if (!references.isEmpty()) {
                features = processReferences(localName, references);
            }
            else {
                features = processAttribute(localName, value);
            }
        }
        else {
            features = Collections.emptyList();
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
    private boolean processSpecialFeatures(RawClassifier classifier, String prefix, String localName, String value) {
        boolean isSpecialFeature = false;

        // A special feature always has a prefix
        if (nonNull(prefix) && !prefix.isEmpty()) {
            final String prefixedValue = prefix + ':' + localName;

            if (prefixedValue.matches(XmiConstants.XMI_XSI_TYPE)) { // xsi:type or xsi:type
                processMetaClass(classifier, value);
                isSpecialFeature = true;
            }
            else if (Objects.equals(XmiConstants.XMI_ID, prefixedValue)) { // xmi:id
                classifier.id(RawIdentifier.original(value));
                isSpecialFeature = true;
            }
            else if (Objects.equals(XmiConstants.XMI_IDREF, prefixedValue)) { // xmi:idref
                // It's not a feature of the current element, but a reference of the previous
                RawReference reference = new RawReference(classifier.localName());
                reference.idReference(RawIdentifier.original(value));
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
                    classifier.localName(), value);
            ignoreElement = true;
        }
        else if (Objects.equals(XmiConstants.NAME, localName)) {
            classifier.className(value);
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
    @Nonnull
    private Collection<String> getReferences(String attribute) {
        List<String> references;

        if (!attribute.trim().isEmpty()) {
            references = Splitter.on(" ").omitEmptyStrings().trimResults().splitToList(attribute);

            boolean isReference = true;
            for (int i = 0, referenceCount = references.size(); i < referenceCount && isReference; i++) {
                String ref = references.get(i);
                isReference = PATTERN_WELL_FORMED_REF.matcher(ref).matches();
            }

            if (!isReference) {
                references = Collections.emptyList();
            }
        }
        else {
            references = Collections.emptyList();
        }

        return references;
    }

    /**
     * Processes an attribute.
     *
     * @param localName the name of the attribute
     * @param value     the value of the attribute
     *
     * @return a singleton list of {@link RawFeature} containing the processed attribute.
     */
    @Nonnull
    private Collection<RawFeature> processAttribute(String localName, String value) {
        RawAttribute attribute = new RawAttribute(localName);
        attribute.index(0);
        attribute.value(value);

        return Collections.singleton(attribute);
    }

    /**
     * Processes a list of {@code references} and returns a list of {@link RawReference}.
     *
     * @param localName  the name of the reference
     * @param references the list that holds the identifier of referenced elements
     *
     * @return a list of {@link RawReference} from the given {@code references}
     */
    @Nonnull
    private Collection<RawFeature> processReferences(String localName, Collection<String> references) {
        Collection<RawFeature> features = new ArrayList<>();

        int index = 0;
        for (String rawReference : references) {
            RawReference ref = new RawReference(localName);
            ref.index(index);
            ref.idReference(RawIdentifier.generated(rawReference));
            features.add(ref);
            index++;
        }

        return features;
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
    private void processMetaClass(RawClassifier element, String prefixedValue) {
        Matcher m = PATTERN_PREFIXED_VALUE.matcher(prefixedValue);
        if (m.find()) {
            RawNamespace ns = RawNamespace.Registry.getInstance().getFromPrefix(m.group(1));
            String localName = m.group(2);

            RawMetaClassifier metaClassifier = new RawMetaClassifier(ns, localName);
            element.metaClassifier(metaClassifier);
        }
        else {
            throw new IllegalArgumentException("Malformed metaclass " + prefixedValue);
        }
    }
}
