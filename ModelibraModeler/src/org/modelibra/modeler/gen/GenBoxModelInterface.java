package org.modelibra.modeler.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.BoxModelKeysInfo;

public abstract class GenBoxModelInterface {

	protected static final String TAB = "  ";

	protected static final String LINE = "\n";

	protected BoxModel table;

	protected BoxModelKeysInfo tableInfo;

	protected String srcDirPath;

	public GenBoxModelInterface(BoxModelKeysInfo tableInfo, String srcDirPath) {
		this.tableInfo = tableInfo;
		this.table = tableInfo.getBoxModel();
		this.srcDirPath = srcDirPath;
	}

	public abstract String getPackageName();

	public abstract Collection getImports();

	public abstract String getInterfaceName();

	public abstract String getExtendsName();

	public abstract Collection getMethods();

	public String getPath() {
		String sep = System.getProperty("file.separator");

		String path = getPackageDirsPath();
		if (!path.endsWith(sep)) {
			path += sep;
		}

		path += getInterfaceName() + ".java";
		return path;
	}

	public String getPackageDirsPath() {
		String sep = System.getProperty("file.separator");
		String path = srcDirPath + sep;
		StringTokenizer st = new StringTokenizer(getPackageName(), ".");
		while (st.hasMoreTokens()) {
			path += st.nextToken();
			path += sep;
		}
		return path;
	}

	// Overriden from Object
	public String toString() {
		String interfaceContent = "";

		// package
		interfaceContent += "package " + getPackageName() + ";";
		interfaceContent += LINE;
		interfaceContent += LINE;

		// imports
		Collection imports = getImports();
		if (imports != null) {
			Iterator xImport = imports.iterator();
			while (xImport.hasNext()) {
				String importName = (String) xImport.next();
				if (importName != "") {
					interfaceContent += "import " + importName + ";";
				}
				interfaceContent += LINE;
			}
			interfaceContent += LINE;
		}

		// interface name
		if (getExtendsName() != null && !getExtendsName().trim().equals("")) {
			interfaceContent += "public interface " + getInterfaceName()
					+ " extends " + getExtendsName() + " {";
		} else {
			interfaceContent += "public interface " + getInterfaceName() + " {";
		}
		interfaceContent += LINE;
		interfaceContent += LINE;

		// methods
		Collection methods = getMethods();
		if (methods != null) {
			Iterator xMethods = methods.iterator();
			// if (xMethods.hasNext()){
			// interfaceContent += LINE_COMMENT;
			// interfaceContent += LINE;
			// interfaceContent += "//Methods";
			// interfaceContent += LINE;
			// interfaceContent += LINE_COMMENT;
			// interfaceContent += LINE;
			// }
			while (xMethods.hasNext()) {
				String method = (String) xMethods.next();
				interfaceContent += method;
				interfaceContent += LINE;
			}
		}

		// end of interface
		interfaceContent += LINE;
		interfaceContent += "}";

		return interfaceContent;
	}

	public boolean save() {
		try {
			File dirs = new File(getPackageDirsPath());
			if (!dirs.exists()) {
				dirs.mkdirs();
			}
			if (dirs.isDirectory()) {
				FileWriter writer = new FileWriter(getPath());
				writer.write(this.toString());
				writer.close();
				return true;
			}
			return false;
		} catch (IOException e) {
			System.out.println("Error generating BoxModelInterface ("
					+ getClass().getName() + ") : " + e.getMessage());
			return false;
		}
	}

}