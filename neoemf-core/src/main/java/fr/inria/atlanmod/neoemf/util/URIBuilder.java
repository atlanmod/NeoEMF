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

import fr.inria.atlanmod.neoemf.annotations.VisibleForTesting;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkState;
import static java.util.Objects.isNull;

/**
 * A builder of {@link URI} used to register {@link BackendFactory} in the {@link BackendFactoryRegistry} and configure
 * the {@code protocol to factory} map of an existing {@link ResourceSet} with a {@link PersistentResourceFactory}.
 *
 * @see BackendFactoryRegistry
 * @see ResourceSet#getResourceFactoryRegistry()
 * @see Registry#getProtocolToFactoryMap()
 */
@ParametersAreNonnullByDefault
public class URIBuilder {

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
     * Constructs a new {@code URIBuilder}.
     */
    protected URIBuilder() {
    }

    /**
     * Creates a new {@code URIBuilder}.
     *
     * @return a new builder
     */
    @Nonnull
    @VisibleForTesting
    public static URIBuilder newBuilder() {
        return new URIBuilder();
    }

    /**
     * Formats the {@link URI} scheme from the given {@code factory}.
     *
     * @param factory the factory associated to the {@link URI}
     *
     * @return the formatted {@link URI} scheme as {@code "neo-{factory.name()}"}
     */
    @Nonnull
    protected static String formatScheme(BackendFactory factory) {
        return String.format("neo-%s", checkNotNull(factory).name());
    }

    /**
     * Defines the scheme to identify the {@link BackendFactory} to use.
     *
     * @param scheme the scheme
     *
     * @return this builder (for chaining)
     *
     * @throws NullPointerException  if the {@code scheme} is {@code null}
     * @throws IllegalStateException if the scheme has already been defined
     */
    @Nonnull
    @VisibleForTesting
    public final URIBuilder withScheme(String scheme) {
        checkNotNull(scheme, "Cannot create URI without a valid scheme");
        checkState(isNull(this.scheme), "The scheme has already been defined as %s", scheme);

        this.scheme = scheme;

        return this;
    }

    /**
     * Creates a new {@code URI} from the given {@code uri}.
     * <p>
     * This method checks that the scheme of the provided {@code uri} can be used to create a new {@link
     * URIBuilder}. Its scheme must be registered in the {@link BackendFactoryRegistry}.
     *
     * @param uri the base {@link URI}
     *
     * @return a new URI
     *
     * @throws UnsupportedOperationException if this URI builder does not support this method
     * @throws NullPointerException          if the {@code uri} is {@code null}
     * @throws IllegalArgumentException      if the scheme of the provided {@code uri} is not registered in the {@link
     *                                       BackendFactoryRegistry} or if it is {@link #FILE_SCHEME}
     */
    @Nonnull
    public URI fromUri(URI uri) {
        checkNotNull(scheme, "Cannot create URI without a valid scheme");
        checkNotNull(uri);

        if (Objects.equals(scheme, uri.scheme())) {
            checkArgument(BackendFactoryRegistry.isRegistered(uri.scheme()), "Unregistered scheme %s", uri);
            return new FileBasedUri(uri);
        }

        if (Objects.equals(FILE_SCHEME, uri.scheme())) {
            return fromFile(new File(uri.toFileString()));
        }

        throw new IllegalArgumentException(String.format("Cannot create URI with the scheme %s", uri.scheme()));
    }

    /**
     * Creates a new {@code URI} from the given {@code file} descriptor.
     *
     * @param file the {@link File} to build a {@link URI} from
     *
     * @return a new URI
     *
     * @throws UnsupportedOperationException if this URI builder does not support this method
     * @throws NullPointerException          if the {@code file} is {@code null}
     */
    @Nonnull
    public URI fromFile(File file) {
        checkNotNull(scheme, "Cannot create URI without a valid scheme");
        checkNotNull(file);

        return fromFile(URI.createFileURI(file.getAbsolutePath()));
    }

    /**
     * Creates a new {@code URI} from the given {@code uri} by checking the referenced file exists on the file system.
     *
     * @param uri the base {@link URI}
     *
     * @return a new URI
     *
     * @throws UnsupportedOperationException if this URI builder does not support this method
     * @throws NullPointerException          if the {@code uri} is {@code null}
     */
    @Nonnull
    private URI fromFile(URI uri) {
        return fromUri(URI.createHierarchicalURI(scheme,
                uri.authority(),
                uri.device(),
                uri.segments(),
                uri.query(),
                uri.fragment()));
    }

    /**
     * Creates a new {@code URI} from the {@code host}, {@code port}, and {@code model} by creating a hierarchical
     * {@link URI} that references the distant model resource.
     *
     * @param host  the address of the server (use {@code "localhost"} if the server is running locally)
     * @param port  the port of the server
     * @param model a {@link URI} identifying the model in the database
     *
     * @return a new URI
     *
     * @throws UnsupportedOperationException if this URI builder does not support this method
     * @throws NullPointerException          if any of the parameters is {@code null}
     * @throws IllegalArgumentException      if {@code port < 0}
     */
    @Nonnull
    public URI fromServer(String host, int port, URI model) {
        checkNotNull(scheme, "Cannot create URI without a valid scheme");
        checkNotNull(host);
        checkNotNull(model);
        checkArgument(port >= 0);

        return URI.createHierarchicalURI(scheme,
                String.format("%s:%d", host, port),
                null,
                model.segments(),
                null,
                null);
    }

    /**
     * A {@link URI} wrapper that creates specific resource {@link URI}s from a {@link File} descriptor or an existing
     * {@link URI}. All methods are delegated to the internal {@link URI}.
     */
    @ParametersAreNonnullByDefault
    private static class FileBasedUri extends URI {

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
        private FileBasedUri(URI baseUri) {
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
