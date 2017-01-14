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

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.util.List;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A {@link URI} wrapper that creates specific resource {@link URI}s from a {@link File} descriptor or an existing
 * {@link URI}. All methods are delegated to the internal {@link URI}.
 * <p>
 * The created {@code URI} are used to register {@link fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory} in
 * {@link PersistenceBackendFactoryRegistry} and configure the {@code protocol to factory} map of an existing
 * {@link org.eclipse.emf.ecore.resource.ResourceSet} with a
 * {@link fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory}.
 *
 * @see PersistenceBackendFactoryRegistry
 * @see org.eclipse.emf.ecore.resource.ResourceSet#getResourceFactoryRegistry()
 * @see org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getProtocolToFactoryMap()
 */
public class PersistenceURI extends URI {

    /**
     * The {@code URI} scheme corresponding to a file.
     */
    @Nonnull
    protected static final String FILE_SCHEME = "file";

    /**
     * The base {@code URI}.
     */
    @Nonnull
    private final URI internalUri;

    /**
     * Constructs a new {@code PersistenceURI} from the given {@code internalURI}.
     *
     * @param internalUri the base {@code URI}
     *
     * @note This constructor is protected to avoid wrong {@link URI} instantiations. Use {@link #createURI(URI)},
     * {@link #createFileURI(File, String)}, or {@link #createFileURI(URI, String)} instead.
     */
    protected PersistenceURI(@Nonnull URI internalUri) {
        super(internalUri.hashCode());
        this.internalUri = internalUri;
    }

    /**
     * Creates a new {@code PersistenceURI} from the given {@code uri}.
     * <p>
     * This method checks that the scheme of the provided {@code uri} can be used to create a new {@link
     * PersistenceURI}. Its scheme must be registered in the {@link PersistenceBackendFactoryRegistry}.
     *
     * @param uri the base {@code URI}
     *
     * @return the created {@code URI}
     *
     * @throws NullPointerException     if the {@code uri} is {@code null}
     * @throws IllegalArgumentException if the scheme of the provided {@code uri} is not registered in the {@link
     *                                  PersistenceBackendFactoryRegistry} or if it is {@link #FILE_SCHEME}
     * @see #createFileURI(File, String)
     * @see #createFileURI(URI, String)
     */
    @Nonnull
    public static URI createURI(@Nonnull URI uri) {
        checkNotNull(uri);
        checkArgument(!Objects.equals(uri.scheme(), FILE_SCHEME),
                "Can not create PersistenceURI from file URI without a valid scheme");
        checkArgument(PersistenceBackendFactoryRegistry.isRegistered(uri.scheme()),
                "Unregistered URI scheme %s", uri.toString());

        return new PersistenceURI(uri);
    }

    /**
     * Creates a new {@code PersistenceURI} from the given {@code file} descriptor.
     *
     * @param file   the {@link File} to build a {@code URI} from
     * @param scheme the scheme to identify the {@link fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory} to use
     *
     * @return the created {@code URI}
     *
     * @throws NullPointerException if the {@code file} is {@code null}
     */
    @Nonnull
    public static URI createFileURI(@Nonnull File file, @Nullable String scheme) {
        checkNotNull(file);
        URI fileUri = createFileURI(file.getAbsolutePath());

        if (isNull(scheme)) {
            return createURI(fileUri);
        }
        else {
            return createFileURI(fileUri, scheme);
        }
    }

    /**
     * Creates a new {@code PersistenceURI} from the given {@code uri} by checking the referenced file exists on the
     * file system.
     *
     * @param uri    the base {@code URI}
     * @param scheme the scheme to identify the {@link fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory} to use
     *
     * @return the created {@code URI}
     *
     * @throws NullPointerException if the {@code uri} is {@code null}
     */
    @Nonnull
    public static URI createFileURI(@Nonnull URI uri, @Nullable String scheme) {
        checkNotNull(uri);
        if (isNull(scheme)) {
            return createURI(uri);
        }
        else {
            return createURI(createHierarchicalURI(scheme, uri.authority(), uri.device(), uri.segments(), uri.query(), uri.fragment()));
        }
    }

    @Override
    public boolean isRelative() {
        return internalUri.isRelative();
    }

    @Override
    public boolean isHierarchical() {
        return internalUri.isHierarchical();
    }

    @Override
    public boolean hasAuthority() {
        return internalUri.hasAuthority();
    }

    @Override
    public boolean hasOpaquePart() {
        return internalUri.hasOpaquePart();
    }

    @Override
    public boolean hasDevice() {
        return internalUri.hasDevice();
    }

    @Override
    public boolean hasPath() {
        return internalUri.hasPath();
    }

    @Override
    public boolean hasAbsolutePath() {
        return internalUri.hasAbsolutePath();
    }

    @Override
    public boolean hasRelativePath() {
        return internalUri.hasRelativePath();
    }

    @Override
    public boolean hasEmptyPath() {
        return internalUri.hasEmptyPath();
    }

    @Override
    public boolean hasQuery() {
        return internalUri.hasQuery();
    }

    @Override
    public boolean hasFragment() {
        return internalUri.hasFragment();
    }

    @Override
    public boolean isCurrentDocumentReference() {
        return internalUri.isCurrentDocumentReference();
    }

    @Override
    public boolean isEmpty() {
        return internalUri.isEmpty();
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isPlatform() {
        return internalUri.isPlatform();
    }

    @Override
    public boolean isPlatformResource() {
        return internalUri.isPlatformResource();
    }

    @Override
    public boolean isPlatformPlugin() {
        return internalUri.isPlatformPlugin();
    }

    @Override
    public boolean isArchive() {
        return internalUri.isArchive();
    }

    @Override
    public int hashCode() {
        return internalUri.hashCode();
    }

    @Override
    public String scheme() {
        return internalUri.scheme();
    }

    @Override
    public String opaquePart() {
        return internalUri.opaquePart();
    }

    @Override
    public String authority() {
        return internalUri.authority();
    }

    @Override
    public String userInfo() {
        return internalUri.userInfo();
    }

    @Override
    public String host() {
        return internalUri.host();
    }

    @Override
    public String port() {
        return internalUri.port();
    }

    @Override
    public String device() {
        return internalUri.device();
    }

    @Override
    public String[] segments() {
        return internalUri.segments();
    }

    @Override
    public List<String> segmentsList() {
        return internalUri.segmentsList();
    }

    @Override
    public int segmentCount() {
        return internalUri.segmentCount();
    }

    @Override
    public String segment(int i) {
        return internalUri.segment(i);
    }

    @Override
    public String lastSegment() {
        return internalUri.lastSegment();
    }

    @Override
    public String path() {
        return internalUri.path();
    }

    @Override
    public String devicePath() {
        return internalUri.devicePath();
    }

    @Override
    public String query() {
        return internalUri.query();
    }

    @Override
    public URI appendQuery(String query) {
        return internalUri.appendQuery(query);
    }

    @Override
    public URI trimQuery() {
        return internalUri.trimQuery();
    }

    @Override
    public String fragment() {
        return internalUri.fragment();
    }

    @Override
    public URI appendFragment(String fragment) {
        return internalUri.appendFragment(fragment);
    }

    @Override
    public URI trimFragment() {
        return internalUri.trimFragment();
    }

    @Override
    public URI resolve(URI base) {
        return internalUri.resolve(base);
    }

    @Override
    public URI resolve(URI base, boolean preserveRootParents) {
        return internalUri.resolve(base, preserveRootParents);
    }

    @Override
    public URI deresolve(URI base) {
        return internalUri.deresolve(base);
    }

    @Override
    public URI deresolve(URI base, boolean preserveRootParents, boolean anyRelPath, boolean shorterRelPath) {
        return internalUri.deresolve(base, preserveRootParents, anyRelPath, shorterRelPath);
    }

    @Override
    public String toFileString() {
        URI uri = URI.createHierarchicalURI(
                FILE_SCHEME,
                internalUri.authority(),
                internalUri.device(),
                internalUri.segments(),
                internalUri.query(),
                internalUri.fragment());
        return uri.toFileString();
    }

    @Override
    public String toPlatformString(boolean decode) {
        return internalUri.toPlatformString(decode);
    }

    @Override
    public URI appendSegment(String segment) {
        return internalUri.appendSegment(segment);
    }

    @Override
    public URI appendSegments(String[] segments) {
        return internalUri.appendSegments(segments);
    }

    @Override
    public URI trimSegments(int i) {
        return internalUri.trimSegments(i);
    }

    @Override
    public boolean hasTrailingPathSeparator() {
        return internalUri.hasTrailingPathSeparator();
    }

    @Override
    public String fileExtension() {
        return internalUri.fileExtension();
    }

    @Override
    public URI appendFileExtension(String fileExtension) {
        return internalUri.appendFileExtension(fileExtension);
    }

    @Override
    public URI trimFileExtension() {
        return internalUri.trimFileExtension();
    }

    @Override
    public boolean isPrefix() {
        return internalUri.isPrefix();
    }

    @Override
    public URI replacePrefix(URI oldPrefix, URI newPrefix) {
        return internalUri.replacePrefix(oldPrefix, newPrefix);
    }

    @Override
    public String toString() {
        return internalUri.toString();
    }
}
