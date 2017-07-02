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

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.common.annotation.Builder;
import fr.inria.atlanmod.common.annotation.VisibleForTesting;
import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An abstract {@link UriBuilder} that manages the assembly and the construction of {@link URI}s.
 */
@Builder("builder")
@ParametersAreNonnullByDefault
public abstract class AbstractUriBuilder implements UriBuilder {

    /**
     * The {@link URI} scheme corresponding to a file.
     */
    @Nonnull
    private static final String FILE_SCHEME = "file";

    /**
     * The scheme to identify the {@link BackendFactory} to use.
     */
    private String scheme;

    /**
     * Constructs a new default {@code AbstractUriBuilder}.
     */
    protected AbstractUriBuilder() {
    }

    /**
     * Constructs a new {@code AbstractUriBuilder} with the given {@code scheme}.
     *
     * @param scheme the scheme to identify the {@link BackendFactory} to use
     */
    protected AbstractUriBuilder(String scheme) {
        this.scheme = checkNotNull(scheme, "Cannot create URI without a valid scheme");
    }

    @Nonnull
    @VisibleForTesting
    @SuppressWarnings("JavaDoc")
    public static UriBuilder builder(String scheme) {
        return new AbstractUriBuilder(scheme) {

            @Override
            protected boolean supportsFile() {
                return true;
            }

            @Override
            protected boolean supportsServer() {
                return true;
            }
        };
    }

    /**
     * Gets the scheme to identify the {@link BackendFactory} to use.
     *
     * @return the {@link URI} scheme
     */
    @Nonnull
    private String scheme() {
        if (isNull(scheme)) {
            scheme = Bindings.schemeOf(getClass());
        }

        return scheme;
    }

    /**
     * Checks that the {@link BackendFactory} associated to the created {@link URI} supports file-based storage.
     *
     * @return {@code true} if file-based {@link URI}s are supported
     */
    protected abstract boolean supportsFile();

    /**
     * Checks that the {@link BackendFactory} associated to the created {@link URI} supports server-based storage.
     *
     * @return {@code true} if server-based {@link URI}s are supported
     */
    protected abstract boolean supportsServer();

    @Nonnull
    @Override
    public URI fromUri(URI uri) {
        checkNotNull(scheme(), "Cannot create URI without a valid scheme");
        checkNotNull(uri);

        if (!supportsFile()) {
            throw new UnsupportedOperationException(String.format("%s does not support file-based URI", getClass().getSimpleName()));
        }

        if (Objects.equals(scheme(), uri.scheme())) {
            checkArgument(BackendFactoryRegistry.getInstance().isRegistered(uri.scheme()),
                    "Unregistered scheme (%s)", uri.scheme());

            return new FileUri(uri);
        }

        if (Objects.equals(FILE_SCHEME, uri.scheme())) {
            return fromFile(new File(uri.toFileString()));
        }

        throw new IllegalArgumentException(String.format("Cannot create URI with the scheme %s", uri.scheme()));
    }

    @Nonnull
    @Override
    public URI fromFile(String filePath) {
        checkNotNull(filePath);

        return fromFile(new File(filePath));
    }

    @Nonnull
    @Override
    public URI fromFile(File file) {
        checkNotNull(file);

        return fromFile(URI.createFileURI(file.getAbsolutePath()));
    }

    @Nonnull
    @Override
    public URI fromServer(String host, int port, URI model) {
        return fromServer(host, port, model.segments());
    }

    @Nonnull
    @Override
    public URI fromServer(String host, int port, String... segments) {
        checkNotNull(scheme(), "Cannot create URI without a valid scheme");
        checkNotNull(host);
        checkNotNull(segments);
        checkArgument(port >= 0);

        if (!supportsServer()) {
            throw new UnsupportedOperationException(String.format("%s does not support server-based URIs", getClass().getSimpleName()));
        }

        return URI.createHierarchicalURI(scheme(),
                host + ':' + port,
                null,
                segments,
                null,
                null);
    }

    /**
     * Creates a new {@code URI} from the given {@code uri} by checking the referenced file exists on the file system.
     *
     * @param uri the base filed-based {@link URI}
     *
     * @return a new URI
     *
     * @throws UnsupportedOperationException if this URI builder does not support this method
     * @throws NullPointerException          if the {@code uri} is {@code null}
     */
    @Nonnull
    private URI fromFile(URI uri) {
        checkNotNull(scheme(), "Cannot create URI without a valid scheme");
        checkArgument(uri.isFile(), "Expecting a file-based URI: {0}", uri.toString());

        return fromUri(URI.createHierarchicalURI(scheme(),
                uri.authority(),
                uri.device(),
                uri.segments(),
                uri.query(),
                uri.fragment()));
    }

    /**
     * A {@link URI} wrapper that creates specific resource {@link URI}s from a {@link File} descriptor or an existing
     * {@link URI}. All methods are delegated to the internal {@link URI}.
     */
    @ParametersAreNonnullByDefault
    private static class FileUri extends URI {

        /**
         * The base {@link URI}.
         */
        @Nonnull
        private final URI baseUri;

        /**
         * Constructs a new {@code FileUri} from the given {@code baseUri}.
         *
         * @param baseUri the base {@link URI}
         */
        private FileUri(URI baseUri) {
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
                    FILE_SCHEME,
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
            if (URI.class.isInstance(o)) {
                return Objects.equals(this.toString(), o.toString());
            }

            return super.equals(o);
        }

        @Override
        public String toString() {
            return baseUri.toString();
        }
    }
}
