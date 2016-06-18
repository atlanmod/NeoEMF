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

import com.google.common.base.Predicate;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Sets;

import fr.inria.atlanmod.neoemf.io.impl.AbstractInternalHandler;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class XmiHandler extends AbstractInternalHandler {

    private static final String XPATH_START_EXPR = "//@";
    private static final String XPATH_START_ELT = "/@";
    private static final String XPATH_INDEX_SEPARATOR = ".";
    private static final Pattern NODE_WITHOUT_INDEX = Pattern.compile("((@\\w+)(?!\\.\\d+)(\\/|\\z))");

    private final Cache<String, Integer> xPathCountCache;
    private final Deque<String> tags;

    /**
     * The start of an XPath expression in this {@code XmiHandler}.
     * This variable is necessary to replace the malformed XPath reference in XMI files
     */
    private String expressionStart;

    public XmiHandler() {
        this.xPathCountCache = CacheBuilder.newBuilder().build();
        this.tags = new ArrayDeque<>();
    }

    @Override
    public void handleStartDocument() throws Exception {
        super.handleStartDocument();
    }

    @Override
    public void handleStartElement(String prefix, String namespace, String localName, String reference) throws Exception {
        String xPath = getXPath(localName);

        // Increments the number of occurence for this path
        Integer count = xPathCountCache.getIfPresent(xPath);
        if (count == null) {
            count = 0;
        } else {
            count += 1;
        }
        xPathCountCache.put(xPath, count);

        if (expressionStart == null) {
            expressionStart = xPath + XPATH_INDEX_SEPARATOR + count + XPATH_START_ELT;
        }

        tags.addLast(localName);

        super.handleStartElement(prefix, namespace, localName, xPath + XPATH_INDEX_SEPARATOR + count);
    }

    @Override
    public void handleReference(String namespace, String localName, String reference) throws Exception {
        super.handleReference(namespace, localName, formatXPath(reference));
    }

    @Override
    public void handleEndElement() throws Exception {
        new CacheCleaner(getXPath(tags.getLast())).start();
        tags.removeLast();

        super.handleEndElement();
    }

    @Override
    public void handleEndDocument() throws Exception {
        // TODO Notify threads that this process is over and that they can stop.
        xPathCountCache.invalidateAll();

        super.handleEndDocument();
    }

    private String getXPath(String localName) {
        StringBuilder str = new StringBuilder(XPATH_START_ELT);
        boolean first = true;
        for (String tag : tags) {
            if (first) {
                str.append(tag);
            }
            else {
                str.append(XPATH_START_ELT).append(tag);
            }
            String xPathWithoutIndex = str.toString();
            str.append(XPATH_INDEX_SEPARATOR).append(xPathCountCache.getIfPresent(xPathWithoutIndex));
            first = false;
        }
        if (localName != null) {
            str.append(first ? "" : XPATH_START_ELT).append(localName);
        }
        return str.toString();
    }

    private String formatXPath(String xPath) {
        // Replace the start of the given reference "//@" -> "/@<rootname>.<index>"
        String modifiedReference = xPath.replaceFirst(XPATH_START_EXPR, expressionStart);

        // Replace elements which has not index
        Matcher matcher = NODE_WITHOUT_INDEX.matcher(modifiedReference);
        while(matcher.find()) {
            modifiedReference = matcher.replaceAll("$2.0$3");
        }

        return modifiedReference;
    }

    private class CacheCleaner extends Thread {

        private final String xPath;

        public CacheCleaner(String xPath) {
            this.xPath = xPath;
        }

        @Override
        public void run() {
            xPathCountCache.invalidateAll(Sets.filter(xPathCountCache.asMap().keySet(), new InvalideKeyPredicate()));
        }

        private class InvalideKeyPredicate implements Predicate<String> {

            @Override
            public boolean apply(String input) {
                return input.startsWith(xPath);
            }
        }
    }
}
