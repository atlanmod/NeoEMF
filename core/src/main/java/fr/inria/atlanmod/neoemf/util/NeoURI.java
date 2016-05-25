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

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;

import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

public class NeoURI extends URI {

	protected static final String FILE_SCHEME = "file";

	private URI internalUri;
	
	protected NeoURI(int hashCode, URI internalUri) {
		super(hashCode);
		this.internalUri = internalUri;
	}

	public static URI createNeoURI(URI uri) {
		if(uri.scheme().equals(FILE_SCHEME)) {
			throw new IllegalArgumentException("Can not create NeoURI from file URI without a valid scheme");
		}
		if(PersistenceBackendFactoryRegistry.getFactories().containsKey(uri.scheme())) {
			return new NeoURI(uri.hashCode(), uri);
		} else {
			throw new IllegalArgumentException(MessageFormat.format("Unregistered URI scheme {0}", uri.toString()));
		}
	}
	
	public static URI createNeoURI(File file, String scheme) {
		URI returnValue;
		URI fileUri = URI.createFileURI(file.getAbsolutePath());
		if(scheme == null) {
			returnValue = NeoURI.createNeoURI(fileUri);
		} else {
			returnValue =createNeoURI(fileUri, scheme);
		}
		return returnValue;
	}
	
	public static URI createNeoURI(URI fileUri, String scheme) {
		URI returnValue;
		if(scheme == null) {
			returnValue = createNeoURI(fileUri);
		} else {
			URI uri = URI.createHierarchicalURI(
					scheme,
					fileUri.authority(),
					fileUri.device(),
					fileUri.segments(),
					fileUri.query(),
					fileUri.fragment()
			);
			returnValue = createNeoURI(uri);
		}
		return returnValue;
	}
	
	@Override
	public boolean isArchive() {
		return internalUri.isArchive();
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

	@Override
	public int hashCode() {
		return internalUri.hashCode();
	}
}
