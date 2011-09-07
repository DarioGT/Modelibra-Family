package org.modelibra.modeler.gen.dblite.db;

import java.util.ArrayList;
import java.util.Collection;

import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.gen.GenBoxModelClass;
import org.modelibra.modeler.model.BoxModelKeysInfo;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.util.StringUtilities;

/**
 * 
 * @author Vincent Dussault
 * @author Dzenan Ridjanovic
 * @version 2007-03-02
 */
public class GenDbQueryFactoryClass extends GenBoxModelClass {

	protected String dbLiteRootPackage = Config.getConfig().getProperty(
			"gen.package.dbLite");

	protected String specificRootPackage = ""; // default

	public GenDbQueryFactoryClass(BoxModelKeysInfo tableInfo,
			String srcDirPath, String specificRootPackage) {
		super(tableInfo, srcDirPath);
		this.specificRootPackage = specificRootPackage;
	}

	private String capitalizeTableName() {
		return StringUtilities.withFirstCharUpperCase(table.getName());
	}

	public String getPackageName() {
		if (specificRootPackage != null
				&& !specificRootPackage.trim().equals("")) {
			return specificRootPackage + ".db."
					+ table.getName().trim().toLowerCase();
		} else {
			return "db." + table.getName().trim().toLowerCase();
		}
	}

	public String getClassName() {
		return capitalizeTableName() + "QueryFactory";
	}

	public String getExtendsName() {
		return null;
	}

	public Collection getConstructors() {
		return null;
	}

	public Collection getImports() {
		Collection<String> imports = new ArrayList<String>();
		imports.add(dbLiteRootPackage + ".db.DbAPI");
		imports.add("");
		imports.add(dbLiteRootPackage + ".db.DbUtil");
		return imports;
	}

	public Collection getMethods() {
		Collection<String> methods = new ArrayList<String>();
		String getInstanceMethod = "";
		getInstanceMethod += TAB + "public static " + capitalizeTableName()
				+ "Query getInstance(DbAPI db) {";
		getInstanceMethod += LINE;
		getInstanceMethod += TAB + TAB + "return (" + capitalizeTableName()
				+ "Query)DbUtil.getDbQueryAPIInstance(db, "
				+ capitalizeTableName() + "Query.class);";
		getInstanceMethod += LINE;
		getInstanceMethod += TAB + "}";
		methods.add(getInstanceMethod);
		return methods;
	}

	public Collection getFields() {
		return null;
	}

	public Collection getImplementsNames() {
		return null;
	}

	// overriden from GenBoxModelClass
	public boolean save() {
		String tableName = table.getName();
		boolean isSequence = false;
		if (!tableName.equalsIgnoreCase("DrdbSequence")) {
			return super.save();
		} else {
			for (int i = 0; i < table.getItems().size(); i++) {
				ItemModel column = table.getItem(i);
				String columnName = column.getName();
				if (columnName.equalsIgnoreCase("seqName")
						|| columnName.equalsIgnoreCase("seq")) {
					isSequence = true;
					break;
				}
			}
			if (!isSequence) {
				return super.save();
			}
		}
		return false;
	}

}
