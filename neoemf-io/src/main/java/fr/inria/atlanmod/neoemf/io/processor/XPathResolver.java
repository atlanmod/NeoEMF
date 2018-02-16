/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdProvider;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.neoemf.io.util.XPathConstants.END_EXPR;
import static fr.inria.atlanmod.neoemf.io.util.XPathConstants.INDEX_SEPARATOR;
import static fr.inria.atlanmod.neoemf.io.util.XPathConstants.START_ELT;
import static fr.inria.atlanmod.neoemf.io.util.XPathConstants.START_EXPR;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A {@link Processor} that analyses XPath references and transforms them in {@link fr.inria.atlanmod.neoemf.core.Id}.
 */
@ParametersAreNonnullByDefault
public class XPathResolver extends AbstractProcessor<Handler> {

    /**
     * Pattern for detecting nodes which have no index in their path.
     * <p>
     * <b>Example:</b> {@code .../@name/...} instead of {@code .../@name.0/...}
     */
    @Nonnull
    private static final Pattern PATTERN_NO_INDEX = Pattern.compile("((@\\w+)(?!\\.\\d+)(/|\\z))", Pattern.UNICODE_CASE);

    /**
     * The default {@link IdProvider} to use for generating identifiers.
     */
    @Nonnull
    private final IdProvider idProvider = Id.getProvider();

    /**
     * The XPath structure.
     */
    private XPathTree paths;

    /**
     * The start of an XPath expression in this {@link XPathResolver}.
     * <p>
     * This variable is necessary to replace the malformed XPath reference in XMI files
     */
    private String expressionStart;

    /**
     * Defines whether the processed document already contains identifiers. In this case, XPath processing is
     * unnecessary and this processor simply notifies the underlying processor.
     */
    private Boolean ignore;

    /**
     * Constructs a new {@code XPathResolver} with the given {@code handler}.
     *
     * @param handler the handler to notify
     */
    public XPathResolver(Handler handler) {
        super(handler);
    }

    @Override
    public void onInitialize() {
        paths = new XPathTree();

        notifyInitialize();
    }

    @Override
    public void onStartElement(BasicElement element) {
        // If the first element has an identifier, we assume that the file is ID-based.
        if (isNull(ignore)) {
            ignore = nonNull(element.rawId());
        }

        resolve(element);

        notifyStartElement(element);
    }

    @Override
    public void onReference(BasicReference reference) {
        resolve(reference);

        notifyReference(reference);
    }

    @Override
    public void onEndElement() {
        if (!ignore) {
            // Removes children of the last element
            paths.clearLast();
        }

        notifyEndElement();
    }

    @Override
    public void onComplete() {
        notifyComplete();
    }

    /**
     * Resolves the {@link BasicElement#id() identifier} of the specified {@code element}.
     *
     * @param element the element to resolve
     */
    private void resolve(BasicElement element) {
        if (nonNull(element.id())) {
            return;
        }

        Id id;
        String rawId = element.rawId();

        // An element has no identifier if it's not resolved
        if (ignore || nonNull(rawId)) {
            checkNotNull(rawId, "raw id must be set");
            id = getOrGenerateId(rawId);
        }
        else {
            // Increments the number of occurrence for this path
            // Processes the raw identifier from the path of the element in XML tree
            // Defines the raw identifier as '<path>.<index>'
            final String path = paths.path(element.name()) + INDEX_SEPARATOR + paths.createOrIncrement(element.name());

            // Defines the XPath start of all elements from the root element
            if (isNull(expressionStart)) {
                expressionStart = path + START_ELT;
            }

            id = generateId(path);
        }

        element.id(id).rawId(null);
    }

    /**
     * Resolves the {@link BasicReference#value identifier} of the referenced element.
     *
     * @param reference the reference to resolve
     */
    private void resolve(BasicReference reference) {
        if (nonNull(reference.value())) {
            return;
        }

        Id referencedId;
        String rawValue = reference.rawValue();
        checkNotNull(rawValue, "raw value must be set");

        if (ignore || !rawValue.startsWith(START_EXPR) && !rawValue.startsWith(START_ELT)) {
            referencedId = getOrGenerateId(rawValue);
        }
        else {
            // Replace the start of the given reference "//@" -> "/@<rootname>.<index>"
            String path = rawValue.replaceFirst(START_EXPR, expressionStart);

            // Replace elements which has not index : all elements must have an index (default = 0)
            Matcher matcher = PATTERN_NO_INDEX.matcher(path);
            while (matcher.find()) {
                path = matcher.replaceAll("$2.0$3");
            }

            // Remove the latest '/' character if present
            if (path.endsWith(END_EXPR)) {
                path = path.substring(0, path.length() - END_EXPR.length());
            }

            referencedId = generateId(path);
        }

        reference.value(referencedId);
    }

    /**
     * Retrieves the {@link Id} associated to the given {@code baseValue}.
     *
     * @param baseValue the string value of the identifier
     *
     * @return the identifier
     */
    @Nonnull
    private Id getOrGenerateId(String baseValue) {
        try {
            return idProvider.fromHexString(baseValue);
        }
        catch (IllegalArgumentException e) {
            return generateId(baseValue);
        }
    }

    /**
     * Generates a new {@link Id} from a {@code baseValue}.
     *
     * @param baseValue the base value of the generated {@link Id}
     *
     * @return a new instance of {@link Id}
     */
    @Nonnull
    private Id generateId(String baseValue) {
        return idProvider.generate(baseValue);
    }
}
