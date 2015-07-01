package fr.inria.atlanmod.atl_mr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;

public class HadoopURIConverterImpl extends ExtensibleURIConverterImpl {
	
	private Configuration conf;

	public HadoopURIConverterImpl(Configuration conf) {
		this.conf = conf;
	}

	@Override
	public InputStream createInputStream(URI uri, java.util.Map<?, ?> options) throws IOException {
		try {
			return super.createInputStream(uri, options);
		} catch (MalformedURLException e) {
			Path path = new Path(uri.toString());
			FileSystem fileSystem = FileSystem.get(path.toUri(), conf);
			return fileSystem.open(path);
		}
	}

	@Override
	public OutputStream createOutputStream(URI uri, java.util.Map<?, ?> options) throws IOException {
		try {
			return super.createOutputStream(uri, options);
		} catch (MalformedURLException e) {
			Path path = new Path(uri.toString());
			FileSystem fileSystem = FileSystem.get(path.toUri(), conf);
			return fileSystem.create(path);
		}
	}
}