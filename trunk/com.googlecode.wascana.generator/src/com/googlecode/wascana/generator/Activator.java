package com.googlecode.wascana.generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.googlecode.wascana.generator";

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> clazz) {
		BundleContext context = plugin.getBundle().getBundleContext();
		ServiceReference ref = context.getServiceReference(clazz.getName());
		return (ref != null) ? (T)context.getService(ref) : null;
	}

	public static String getFileContents(IPath path) throws IOException {
		URL url = FileLocator.find(plugin.getBundle(), path, null);
		InputStream in = (InputStream)url.getContent();
		StringWriter writer = new StringWriter();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		for (String str = reader.readLine(); str != null; str = reader.readLine()) {
			writer.write(str);
			writer.write('\n');
		}
		return writer.toString();
	}
}
