/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util;


import org.eclipse.emf.common.util.URI;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link URI} wrapper that delegates all methods to an internal {@link URI}.
 */
@ParametersAreNonnullByDefault
class UriDecorator extends URI {

    /**
     * The base {@link URI}.
     */
    @Nonnull
    protected final URI base;

    /**
     * Constructs a new {@code UriDecorator} on the specified {@code base}.
     *
     * @param base the base {@link URI}
     */
    protected UriDecorator(URI base) {
        super(base.hashCode());
        this.base = base;
    }

    @Override
    public boolean isRelative() {
        return base.isRelative();
    }

    @Override
    public boolean isHierarchical() {
        return base.isHierarchical();
    }

    @Override
    public boolean hasAuthority() {
        return base.hasAuthority();
    }

    @Override
    public boolean hasOpaquePart() {
        return base.hasOpaquePart();
    }

    @Override
    public boolean hasDevice() {
        return base.hasDevice();
    }

    @Override
    public boolean hasPath() {
        return base.hasPath();
    }

    @Override
    public boolean hasAbsolutePath() {
        return base.hasAbsolutePath();
    }

    @Override
    public boolean hasRelativePath() {
        return base.hasRelativePath();
    }

    @Override
    public boolean hasEmptyPath() {
        return base.hasEmptyPath();
    }

    @Override
    public boolean hasQuery() {
        return base.hasQuery();
    }

    @Override
    public boolean hasFragment() {
        return base.hasFragment();
    }

    @Override
    public boolean isCurrentDocumentReference() {
        return base.isCurrentDocumentReference();
    }

    @Override
    public boolean isEmpty() {
        return base.isEmpty();
    }

    @Override
    public boolean isFile() {
        return base.isFile();
    }

    @Override
    public boolean isPlatform() {
        return base.isPlatform();
    }

    @Override
    public boolean isPlatformResource() {
        return base.isPlatformResource();
    }

    @Override
    public boolean isPlatformPlugin() {
        return base.isPlatformPlugin();
    }

    @Override
    public boolean isArchive() {
        return base.isArchive();
    }

    @Override
    public int hashCode() {
        return base.hashCode();
    }

    @Override
    public String scheme() {
        return base.scheme();
    }

    @Override
    public String opaquePart() {
        return base.opaquePart();
    }

    @Override
    public String authority() {
        return base.authority();
    }

    @Override
    public String userInfo() {
        return base.userInfo();
    }

    @Override
    public String host() {
        return base.host();
    }

    @Override
    public String port() {
        return base.port();
    }

    @Override
    public String device() {
        return base.device();
    }

    @Override
    public String[] segments() {
        return base.segments();
    }

    @Override
    public List<String> segmentsList() {
        return base.segmentsList();
    }

    @Override
    public int segmentCount() {
        return base.segmentCount();
    }

    @Override
    public String segment(int i) {
        return base.segment(i);
    }

    @Override
    public String lastSegment() {
        return base.lastSegment();
    }

    @Override
    public String path() {
        return base.path();
    }

    @Override
    public String devicePath() {
        return base.devicePath();
    }

    @Override
    public String query() {
        return base.query();
    }

    @Override
    public URI appendQuery(String query) {
        return base.appendQuery(query);
    }

    @Override
    public URI trimQuery() {
        return base.trimQuery();
    }

    @Override
    public String fragment() {
        return base.fragment();
    }

    @Override
    public URI appendFragment(String fragment) {
        return base.appendFragment(fragment);
    }

    @Override
    public URI trimFragment() {
        return base.trimFragment();
    }

    @Override
    public URI resolve(URI base) {
        return this.base.resolve(base);
    }

    @Override
    public URI resolve(URI base, boolean preserveRootParents) {
        return this.base.resolve(base, preserveRootParents);
    }

    @Override
    public URI deresolve(URI base) {
        return this.base.deresolve(base);
    }

    @Override
    public URI deresolve(URI base, boolean preserveRootParents, boolean anyRelPath, boolean shorterRelPath) {
        return this.base.deresolve(base, preserveRootParents, anyRelPath, shorterRelPath);
    }

    @Override
    public String toFileString() {
        return base.toFileString();
    }

    @Override
    public String toPlatformString(boolean decode) {
        return base.toPlatformString(decode);
    }

    @Override
    public URI appendSegment(String segment) {
        return base.appendSegment(segment);
    }

    @Override
    public URI appendSegments(String[] segments) {
        return base.appendSegments(segments);
    }

    @Override
    public URI trimSegments(int i) {
        return base.trimSegments(i);
    }

    @Override
    public boolean hasTrailingPathSeparator() {
        return base.hasTrailingPathSeparator();
    }

    @Override
    public String fileExtension() {
        return base.fileExtension();
    }

    @Override
    public URI appendFileExtension(String fileExtension) {
        return base.appendFileExtension(fileExtension);
    }

    @Override
    public URI trimFileExtension() {
        return base.trimFileExtension();
    }

    @Override
    public boolean isPrefix() {
        return base.isPrefix();
    }

    @Override
    public URI replacePrefix(URI oldPrefix, URI newPrefix) {
        return base.replacePrefix(oldPrefix, newPrefix);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        return base.equals(o);
    }

    @Override
    public String toString() {
        return base.toString();
    }
}
