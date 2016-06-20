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

import fr.inria.atlanmod.neoemf.io.impl.AbstractInternalHandler;
import fr.inria.atlanmod.neoemf.io.input.xmi.util.TreePath;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class XmiHandler extends AbstractInternalHandler {

    private static final String XPATH_START_EXPR = "//@";
    private static final String XPATH_START_ELT = "/@";
    private static final String XPATH_INDEX_SEPARATOR = ".";

    /**
     * Pattern for detecting nodes which have no index in their path.
     * <p/>
     * Example : {@code .../@nodename/...} instead of {@code .../@nodename.0/...}
     */
    private static final Pattern PATTERN_NODE_WITHOUT_INDEX = Pattern.compile("((@\\w+)(?!\\.\\d+)(\\/|\\z))");

    private final TreePath paths;

    /**
     * The start of an XPath expression in this {@code XmiHandler}.
     * This variable is necessary to replace the malformed XPath reference in XMI files
     */
    private String expressionStart;

    public XmiHandler() {
        this.paths = new TreePath();
    }

    @Override
    public void handleStartDocument() throws Exception {
        super.handleStartDocument();
    }

    @Override
    public void handleStartElement(String prefix, String namespace, String localName, String reference) throws Exception {
        String path = paths.getPath(localName);

        // Increments the number of occurence for this path
        Integer count = paths.createOrIncrement(localName);

        if (expressionStart == null) {
            expressionStart = path + XPATH_INDEX_SEPARATOR + count + XPATH_START_ELT;
        }

        super.handleStartElement(prefix, namespace, localName, path + XPATH_INDEX_SEPARATOR + count);
    }

    @Override
    public void handleReference(String namespace, String localName, String reference) throws Exception {
        super.handleReference(namespace, localName, formatPath(reference));
    }

    @Override
    public void handleEndElement() throws Exception {
        paths.clearLast();

        super.handleEndElement();
    }

    @Override
    public void handleEndDocument() throws Exception {
        long size = paths.size();
        if (size > 1) {
            NeoLogger.warn("Some elements have not been cleaned ({0})", size);
        }

        super.handleEndDocument();
    }

    private String formatPath(String path) {
        // Replace the start of the given reference "//@" -> "/@<rootname>.<index>"
        String modifiedReference = path.replaceFirst(XPATH_START_EXPR, expressionStart);

        // Replace elements which has not index
        Matcher matcher = PATTERN_NODE_WITHOUT_INDEX.matcher(modifiedReference);
        while(matcher.find()) {
            modifiedReference = matcher.replaceAll("$2.0$3");
        }

        return modifiedReference;
    }
}
