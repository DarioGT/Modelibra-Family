package org.modelibra.modeler.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.BoxModelKeysInfo;

/**
 * 
 * @author Vincent Dussault
 * @version 2003
 */
public abstract class GenBoxModelClass {

	protected static final String LINE = "\n";

	protected static final String TAB = "  ";

	protected static final String TAB2 = TAB + TAB;

	protected static final String TAB3 = TAB + TAB + TAB;

	protected static final String TAB4 = TAB + TAB + TAB + TAB;

	protected BoxModel table;

	protected BoxModelKeysInfo tableInfo;

	protected String srcDirPath;

	public GenBoxModelClass(BoxModelKeysInfo tableInfo, String srcDirPath) {
		this.tableInfo = tableInfo;
		this.table = tableInfo.getBoxModel();
		this.srcDirPath = srcDirPath;
	}

	public abstract String getPackageName();

	public abstract Collection getImports();

	public abstract String getClassName();

	public abstract String getExtendsName();

	public abstract Collection getImplementsNames();

	public abstract Collection getFields();

	public abstract Collection getConstructors();

	public abstract Collection getMethods();

	public String getPath() {
		String sep = System.getProperty("file.separator");

		String path = getPackageDirsPath();
		if (!path.endsWith(sep)) {
			path += sep;
		}

		path += getClassName() + ".java";
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
		String classContent = "";

		// package
		classContent += "package " + getPackageName() + ";";
		classContent += LINE;
		classContent += LINE;

		// imports
		Collection imports = getImports();
		if (imports != null) {
			Iterator xImport = imports.iterator();
			while (xImport.hasNext()) {
				String importName = (String) xImport.next();
				if (importName != "") {
					classContent += "import " + importName + ";";
				}
				classContent += LINE;
			}
			classContent += LINE;
		}

		// class name
		if (getExtendsName() != null && !getExtendsName().trim().equals("")) {
			classContent += "public class " + getClassName() + " extends "
					+ getExtendsName() + " ";
		} else {
			classContent += "public class " + getClassName() + " ";
		}

		// implements
		Collection implementsNames = getImplementsNames();
		if (implementsNames != null) {
			Iterator xImplements = implementsNames.iterator();
			if (xImplements.hasNext()) {
				classContent += "implements ";
			}
			while (xImplements.hasNext()) {
				String implementName = (String) xImplements.next();
				classContent += implementName;
				if (xImplements.hasNext()) {
					classContent += ",";
				}
				classContent += " ";
			}
		}

		classContent += "{";
		classContent += LINE;

		classContent += LINE;

		// fields
		Collection fields = getFields();
		if (fields != null) {
			Iterator xFields = fields.iterator();
			// if (xFields.hasNext()){
			// classContent += LINE;
			// classContent += LINE_COMMENT;
			// classContent += LINE;
			// classContent += "//Fields";
			// classContent += LINE;
			// classContent += LINE_COMMENT;
			// classContent += LINE;
			// }
			while (xFields.hasNext()) {
				String field = (String) xFields.next();
				classContent += field;
				classContent += LINE;
			}

			classContent += LINE;
		}

		// constuctors
		Collection constructors = getConstructors();
		if (constructors != null) {
			Iterator xConstructors = constructors.iterator();
			// if (xConstructors.hasNext()){
			// classContent += LINE;
			// classContent += LINE_COMMENT;
			// classContent += LINE;
			// classContent += "//Constructors";
			// classContent += LINE;
			// classContent += LINE_COMMENT;
			// classContent += LINE;
			// }
			while (xConstructors.hasNext()) {
				String constructor = (String) xConstructors.next();
				classContent += constructor;
				classContent += LINE;
				classContent += LINE;
			}
		}

		// methods
		Collection methods = getMethods();
		if (methods != null) {
			Iterator xMethods = methods.iterator();
			// if (xMethods.hasNext()){
			// classContent += LINE;
			// classContent += LINE_COMMENT;
			// classContent += LINE;
			// classContent += "//Methods";
			// classContent += LINE;
			// classContent += LINE_COMMENT;
			// classContent += LINE;
			// }
			while (xMethods.hasNext()) {
				String method = (String) xMethods.next();
				classContent += method;
				classContent += LINE;
				classContent += LINE;
			}
		}

		// end of class
		classContent += "}";

		return classContent;
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
			System.out.println("Error generating BoxModelClass ("
					+ getClass().getName() + ") : " + e.getMessage());
			return false;
		}
	}

}
