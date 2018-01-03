/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 3.
 */

package fr.inria.atlanmod.neoemf.util;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link URI} wrapper that creates specific resource {@link URI}s from a {@link File} descriptor or an existing
 * {@link URI}. All methods are delegated to the internal {@link URI}.
 */
@ParametersAreNonnullByDefault
class FileBasedUri extends URI {

    /**
     * The {@link URI} scheme corresponding to a file.
     */
    @Nonnull
    static final String SCHEME = "file";

    /**
     * The base {@link URI}.
     */
    @Nonnull
    private final URI baseUri;

    /**
     * Constructs a new {@code FileBasedUri} from the given {@code baseUri}.
     *
     * @param baseUri the base {@link URI}
     */
    FileBasedUri(URI baseUri) {
        super(baseUri.hashCode());
        this.baseUri = baseUri;
    }

    @Override
    public boolean isRelative() {
        return baseUri.isRelative();
    }

    @Override
    public boolean isHierarchical() {
        return baseUri.isHierarchical();
    }

    @Override
    public boolean hasAuthority() {
        return baseUri.hasAuthority();
    }

    @Override
    public boolean hasOpaquePart() {
        return baseUri.hasOpaquePart();
    }

    @Override
    public boolean hasDevice() {
        return baseUri.hasDevice();
    }

    @Override
    public boolean hasPath() {
        return baseUri.hasPath();
    }

    @Override
    public boolean hasAbsolutePath() {
        return baseUri.hasAbsolutePath();
    }

    @Override
    public boolean hasRelativePath() {
        return baseUri.hasRelativePath();
    }

    @Override
    public boolean hasEmptyPath() {
        return baseUri.hasEmptyPath();
    }

    @Override
    public boolean hasQuery() {
        return baseUri.hasQuery();
    }

    @Override
    public boolean hasFragment() {
        return baseUri.hasFragment();
    }

    @Override
    public boolean isCurrentDocumentReference() {
        return baseUri.isCurrentDocumentReference();
    }

    @Override
    public boolean isEmpty() {
        return baseUri.isEmpty();
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isPlatform() {
        return baseUri.isPlatform();
    }

    @Override
    public boolean isPlatformResource() {
        return baseUri.isPlatformResource();
    }

    @Override
    public boolean isPlatformPlugin() {
        return baseUri.isPlatformPlugin();
    }

    @Override
    public boolean isArchive() {
        return baseUri.isArchive();
    }

    @Override
    public int hashCode() {
        return baseUri.hashCode();
    }

    @Override
    public String scheme() {
        return baseUri.scheme();
    }

    @Override
    public String opaquePart() {
        return baseUri.opaquePart();
    }

    @Override
    public String authority() {
        return baseUri.authority();
    }

    @Override
    public String userInfo() {
        return baseUri.userInfo();
    }

    @Override
    public String host() {
        return baseUri.host();
    }

    @Override
    public String port() {
        return baseUri.port();
    }

    @Override
    public String device() {
        return baseUri.device();
    }

    @Override
    public String[] segments() {
        return baseUri.segments();
    }

    @Override
    public List<String> segmentsList() {
        return baseUri.segmentsList();
    }

    @Override
    public int segmentCount() {
        return baseUri.segmentCount();
    }

    @Override
    public String segment(int i) {
        return baseUri.segment(i);
    }

    @Override
    public String lastSegment() {
        return baseUri.lastSegment();
    }

    @Override
    public String path() {
        return baseUri.path();
    }

    @Override
    public String devicePath() {
        return baseUri.devicePath();
    }

    @Override
    public String query() {
        return baseUri.query();
    }

    @Override
    public URI appendQuery(String query) {
        return baseUri.appendQuery(query);
    }

    @Override
    public URI trimQuery() {
        return baseUri.trimQuery();
    }

    @Override
    public String fragment() {
        return baseUri.fragment();
    }

    @Override
    public URI appendFragment(String fragment) {
        return baseUri.appendFragment(fragment);
    }

    @Override
    public URI trimFragment() {
        return baseUri.trimFragment();
    }

    @Override
    public URI resolve(URI base) {
        return baseUri.resolve(base);
    }

    @Override
    public URI resolve(URI base, boolean preserveRootParents) {
        return baseUri.resolve(base, preserveRootParents);
    }

    @Override
    public URI deresolve(URI base) {
        return baseUri.deresolve(base);
    }

    @Override
    public URI deresolve(URI base, boolean preserveRootParents, boolean anyRelPath, boolean shorterRelPath) {
        return baseUri.deresolve(base, preserveRootParents, anyRelPath, shorterRelPath);
    }

    @Override
    public String toFileString() {
        return URI.createHierarchicalURI(
                SCHEME,
                baseUri.authority(),
                baseUri.device(),
                baseUri.segments(),
                baseUri.query(),
                baseUri.fragment()
        ).toFileString();
    }

    @Override
    public String toPlatformString(boolean decode) {
        return baseUri.toPlatformString(decode);
    }

    @Override
    public URI appendSegment(String segment) {
        return baseUri.appendSegment(segment);
    }

    @Override
    public URI appendSegments(String[] segments) {
        return baseUri.appendSegments(segments);
    }

    @Override
    public URI trimSegments(int i) {
        return baseUri.trimSegments(i);
    }

    @Override
    public boolean hasTrailingPathSeparator() {
        return baseUri.hasTrailingPathSeparator();
    }

    @Override
    public String fileExtension() {
        return baseUri.fileExtension();
    }

    @Override
    public URI appendFileExtension(String fileExtension) {
        return baseUri.appendFileExtension(fileExtension);
    }

    @Override
    public URI trimFileExtension() {
        return baseUri.trimFileExtension();
    }

    @Override
    public boolean isPrefix() {
        return baseUri.isPrefix();
    }

    @Override
    public URI replacePrefix(URI oldPrefix, URI newPrefix) {
        return baseUri.replacePrefix(oldPrefix, newPrefix);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (nonNull(o) && URI.class.isInstance(o)) {
            return Objects.equals(this.toString(), o.toString());
        }

        return super.equals(o);
    }

    @Override
    public String toString() {
        return baseUri.toString();
    }
}
