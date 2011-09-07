package org.modelibra.modeler.gen.dblite.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.modelibra.modeler.app.pref.Config;
import org.modelibra.modeler.gen.GenBoxModelClass;
import org.modelibra.modeler.model.BoxModel;
import org.modelibra.modeler.model.BoxModelKeysInfo;
import org.modelibra.modeler.model.ItemModel;
import org.modelibra.modeler.model.LineModel;
import org.modelibra.modeler.model.TypeModel;
import org.modelibra.modeler.util.StringUtilities;

/**
 * 
 * @author Vincent Dussault
 * @author Dzenan Ridjanovic
 * @version 2007-03-02
 */
public class GenDbQueryRecordAPIClass extends GenBoxModelClass {

	protected String dbLiteRootPackage = Config.getConfig().getProperty(
			"gen.package.dbLite");

	protected String specificRootPackage = "";

	protected static final int PRIVATE = 0;

	protected static final int PROTECTED = 1;

	protected static final int PUBLIC = 2;

	protected static final int GET_METHOD = 3;

	protected static final int SET_METHOD = 4;

	protected Collection imports = new ArrayList();

	protected String className;

	protected Collection fields = new ArrayList();

	protected Collection constructors = new ArrayList();

	protected Collection methods = new ArrayList();

	public GenDbQueryRecordAPIClass(BoxModelKeysInfo tableInfo,
			String srcDirPath, String specificRootPackage) {
		super(tableInfo, srcDirPath);
		this.specificRootPackage = specificRootPackage;
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

	public Collection<String> getImports() {
		Collection<String> imports = new ArrayList<String>();
		imports.add("java.io.Serializable");
		imports.add("");
		imports.add("java.util.Collection");
		imports.add("java.util.HashMap");
		imports.add("java.util.Map");
		imports.add("");
		imports.add(dbLiteRootPackage + ".db.DbAPI");
		imports.add(dbLiteRootPackage + ".db.DbException");
		imports.add(dbLiteRootPackage + ".db.DbQueryRecordAPI");

		String prefix = "";
		if (specificRootPackage != null
				&& !specificRootPackage.trim().equals("")) {
			prefix += specificRootPackage + ".";
		}

		if (tableInfo.isChild()) {
			imports.add("");

			// if they exist, a delete method for the foreign keys
			Collection fkColumns = tableInfo.getForeignKeyColumns();
			if (!fkColumns.isEmpty()) {
				Iterator xFkColumns = fkColumns.iterator();

				while (xFkColumns.hasNext()) {
					ArrayList fkColumn = (ArrayList) xFkColumns.next();
					String fkColumnName = (String) fkColumn.get(0);

					BoxModel parent = tableInfo.getBoxModelParent(fkColumnName);
					String parentRecordClassName = StringUtilities
							.withFirstCharUpperCase(parent.getName());

					String aImport = prefix + "db."
							+ parent.getName().trim().toLowerCase() + "."
							+ parentRecordClassName;
					if (!imports.contains(aImport)
							&& !aImport.equalsIgnoreCase(getPackageName() + "."
									+ table.getName())) {
						imports.add(aImport);
					} else {
						if (xFkColumns.hasNext()) {
							imports.add("");
						}
					}
				}
			}
		}

		for (Iterator iter = table.getLines1().iterator(); iter.hasNext();) {
			LineModel line = (LineModel) iter.next();
			if (!line.getDir12Max().equals("1")) {
				BoxModel relatedBox = line.getBoxModel2();
				String importRecord = prefix
						+ "db."
						+ relatedBox.getName().toLowerCase()
						+ "."
						+ StringUtilities.withFirstCharUpperCase(relatedBox
								.getName());
				String importQuery = prefix
						+ "db."
						+ relatedBox.getName().toLowerCase()
						+ "."
						+ StringUtilities.withFirstCharUpperCase(relatedBox
								.getName()
								+ "Query");
				String importQueryFactory = prefix
						+ "db."
						+ relatedBox.getName().toLowerCase()
						+ "."
						+ StringUtilities.withFirstCharUpperCase(relatedBox
								.getName()
								+ "QueryFactory");
				if (!imports.contains(importRecord)) {
					imports.add(importRecord);
					imports.add(importQuery);
					imports.add(importQueryFactory);
				}
			}
		}

		for (Iterator iter = table.getLines2().iterator(); iter.hasNext();) {
			LineModel line = (LineModel) iter.next();
			if (!line.getDir21Max().equals("1")) {
				BoxModel relatedBox = line.getBoxModel1();
				String importRecord = prefix
						+ "db."
						+ relatedBox.getName().toLowerCase()
						+ "."
						+ StringUtilities.withFirstCharUpperCase(relatedBox
								.getName());
				String importQuery = prefix
						+ "db."
						+ relatedBox.getName().toLowerCase()
						+ "."
						+ StringUtilities.withFirstCharUpperCase(relatedBox
								.getName()
								+ "Query");
				String importQueryFactory = prefix
						+ "db."
						+ relatedBox.getName().toLowerCase()
						+ "."
						+ StringUtilities.withFirstCharUpperCase(relatedBox
								.getName()
								+ "QueryFactory");
				if (!imports.contains(importRecord)) {
					imports.add(importRecord);
					imports.add(importQuery);
					imports.add(importQueryFactory);
				}
			}
		}

		return imports;
	}

	public String getClassName() {
		String className = capitalizeTableName();
		return className;
	}

	public String getExtendsName() {
		return null;
	}

	public Collection<String> getImplementsNames() {
		Collection<String> implementsNames = new ArrayList<String>();
		implementsNames.add("DbQueryRecordAPI");
		implementsNames.add("Serializable");
		return implementsNames;
	}

	private String capitalizeTableName() {
		return StringUtilities.withFirstCharUpperCase(table.getName());
	}

	public Collection<String> getConstructors() {
		Collection<String> constructors = new ArrayList<String>();

		ItemModel oidItem = table.getOidItem();
		TypeModel oidType = oidItem.getTypeModel();
		String oidObjectName = StringUtilities.withFirstCharLowerCase(oidItem
				.getName().trim());
		String oidJavaType = "Object"; // default
		if (oidType != null) {
			oidJavaType = oidType.getAlias().trim();
		}

		// default
		String constructor = "";
		constructor += TAB + "public " + capitalizeTableName() + "("
				+ oidJavaType + " " + oidObjectName + ", "
				+ capitalizeTableName() + "Query dbQuery){";
		constructor += LINE;
		constructor += TAB + TAB + "this." + oidObjectName + "="
				+ oidObjectName + ";";
		constructor += LINE;
		constructor += TAB + TAB + "this.dbQuery=dbQuery;";
		constructor += LINE;
		constructor += TAB + "}";
		constructors.add(constructor);

		// default
		String constructor2 = "";
		constructor2 += TAB + "public " + capitalizeTableName() + "("
				+ oidJavaType + " " + oidObjectName + ", DbAPI db){";
		constructor2 += LINE;
		constructor2 += TAB + TAB + "this." + oidObjectName + "="
				+ oidObjectName + ";";
		constructor2 += LINE;
		constructor2 += TAB + TAB + "this.dbQuery=" + capitalizeTableName()
				+ "QueryFactory.getInstance(db);";
		constructor2 += LINE;
		constructor2 += TAB + "}";
		constructors.add(constructor2);

		return constructors;
	}

	public Collection<String> getFields() {
		Collection<String> fields = new ArrayList<String>();
		// DbQuery
		String dbQueryField = TAB + "protected " + getClassName()
				+ "Query dbQuery;";
		fields.add(dbQueryField);
		fields.add("");

		// Columns
		Collection columns = tableInfo.getBoxModel().getItems();
		Iterator xColumns = columns.iterator();
		while (xColumns.hasNext()) {
			ItemModel column = (ItemModel) xColumns.next();
			String columnName = column.getName().trim();
			TypeModel columnType = column.getTypeModel();
			String javaType = "Object"; // Default
			if (columnType != null) {
				javaType = columnType.getAlias();
			}
			String columnField = buildField(columnName, javaType, PRIVATE);
			fields.add(columnField);

		}
		// Foreign key columns
		Collection fkColumns = tableInfo.getForeignKeyColumns();
		Iterator xFkColumns = fkColumns.iterator();
		while (xFkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) xFkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			String directionName = StringUtilities.withFirstCharUpperCase(
					(String) fkColumn.get(2)).trim();

			BoxModel parent = tableInfo.getBoxModelParent(fkColumnName);
			String parentRecordClassName = StringUtilities
					.withFirstCharUpperCase(parent.getName().trim());
			String parentRecordName = StringUtilities
					.withFirstCharLowerCase(parent.getName().trim());

			String fkColumnField = buildField(parentRecordName + directionName,
					parentRecordClassName, PRIVATE);
			fields.add(fkColumnField);
		}

		return fields;
	}

	public String buildField(String columnName, String javaType, int fieldScope) {
		char firstCharLowerCase = Character.toLowerCase(columnName.charAt(0));
		String fieldName = firstCharLowerCase + columnName.substring(1);
		String field = TAB;
		switch (fieldScope) {
		case PRIVATE:
			field += "private ";
			break;
		case PROTECTED:
			field += "protected ";
			break;
		case PUBLIC:
			field += "public ";
			break;
		}

		if (javaType.trim().equalsIgnoreCase("java.sql.Date")
				|| javaType.trim().equalsIgnoreCase("java.sql.Time")
				|| javaType.trim().equalsIgnoreCase("java.sql.Timestamp")) {
			javaType = "java.util.Date";
		}

		if (javaType.trim().equalsIgnoreCase("java.net.URL")
				|| javaType.trim().equalsIgnoreCase("org.dmlite.type.Email")) {
			javaType = "java.lang.String";
		}

		field += javaType + " ";
		field += fieldName;
		field += ";";
		return field;
	}

	public Collection<String> getMethods() {
		Collection<String> methods = new ArrayList<String>();

		// Columns
		Collection<ItemModel> columns = tableInfo.getBoxModel().getItems();
		Iterator xColumns = columns.iterator();
		while (xColumns.hasNext()) {
			ItemModel column = (ItemModel) xColumns.next();
			String columnName = column.getName().trim();
			TypeModel columnType = column.getTypeModel();
			String javaType = "Object"; // Default
			if (columnType != null) {
				javaType = columnType.getAlias();
			}
			String getMethod = buildMethod(columnName, javaType, GET_METHOD,
					PUBLIC);
			String setMethod = buildMethod(columnName, javaType, SET_METHOD,
					PUBLIC);
			methods.add(getMethod);
			if (!column.isOid()) {
				methods.add(setMethod);
			}
		}
		// Foreign key columns
		Collection fkColumns = tableInfo.getForeignKeyColumns();
		Iterator xFkColumns = fkColumns.iterator();
		while (xFkColumns.hasNext()) {
			ArrayList fkColumn = (ArrayList) xFkColumns.next();
			String fkColumnName = (String) fkColumn.get(0);
			String directionName = StringUtilities.withFirstCharUpperCase(
					(String) fkColumn.get(2)).trim();

			BoxModel parent = tableInfo.getBoxModelParent(fkColumnName);
			String parentRecordClassName = StringUtilities
					.withFirstCharUpperCase(parent.getName().trim());
			String parentRecordName = StringUtilities
					.withFirstCharLowerCase(parent.getName().trim());

			String getMethod = buildMethod(parentRecordName + directionName,
					parentRecordClassName, GET_METHOD, PUBLIC);
			String setMethod = buildMethod(parentRecordName + directionName,
					parentRecordClassName, SET_METHOD, PUBLIC);

			methods.add(getMethod);
			methods.add(setMethod);
		}

		methods.add(buildGetPrimaryKeyMethod());

		methods.addAll(buildGetManyMethods());

		return methods;
	}

	protected Collection<String> buildGetManyMethods() {
		Collection<String> getManyMethods = new ArrayList<String>();

		for (Iterator iter = table.getLines1().iterator(); iter.hasNext();) {
			LineModel line = (LineModel) iter.next();
			if (!line.isDir12Child()) {
				BoxModel relatedBox = line.getBoxModel2();

				String propertyName;
				String oppositePropertyName;
				if (line.isTwin() || line.isReflexive()) {
					propertyName = relatedBox.getName()
							+ StringUtilities.withFirstCharUpperCase(line
									.getDir21Name());
					oppositePropertyName = table.getName()
							+ StringUtilities.withFirstCharUpperCase(line
									.getDir21Name());
				} else {
					propertyName = relatedBox.getName();
					oppositePropertyName = table.getName();
				}

				String getManyMethod = "";
				getManyMethod += TAB + "public Collection getMany"
						+ StringUtilities.withFirstCharUpperCase(propertyName)
						+ "() throws DbException {";
				getManyMethod += LINE;
				getManyMethod += TAB
						+ TAB
						+ StringUtilities.withFirstCharUpperCase(relatedBox
								.getName())
						+ "Query "
						+ StringUtilities.withFirstCharLowerCase(propertyName)
						+ "Query = "
						+ StringUtilities.withFirstCharUpperCase(relatedBox
								.getName())
						+ "QueryFactory.getInstance(dbQuery.getDb());";
				getManyMethod += LINE;
				getManyMethod += TAB
						+ TAB
						+ "return "
						+ StringUtilities.withFirstCharLowerCase(propertyName)
						+ "Query.getCollectionFor"
						+ StringUtilities
								.withFirstCharUpperCase(oppositePropertyName)
						+ "(this);";
				getManyMethod += LINE;
				getManyMethod += TAB + "}";
				getManyMethods.add(getManyMethod);
			}
		}

		for (Iterator iter = table.getLines2().iterator(); iter.hasNext();) {
			LineModel line = (LineModel) iter.next();
			if (!line.isDir21Child()) {
				BoxModel relatedBox = line.getBoxModel1();

				String propertyName;
				String oppositePropertyName;
				if (line.isTwin() || line.isReflexive()) {
					propertyName = relatedBox.getName()
							+ StringUtilities.withFirstCharUpperCase(line
									.getDir12Name());
					oppositePropertyName = table.getName()
							+ StringUtilities.withFirstCharUpperCase(line
									.getDir12Name());
				} else {
					propertyName = relatedBox.getName();
					oppositePropertyName = table.getName();
				}

				String getManyMethod = "";
				getManyMethod += TAB + "public Collection getMany"
						+ StringUtilities.withFirstCharUpperCase(propertyName)
						+ "() throws DbException {";
				getManyMethod += LINE;
				getManyMethod += TAB
						+ TAB
						+ StringUtilities.withFirstCharUpperCase(relatedBox
								.getName())
						+ "Query "
						+ StringUtilities.withFirstCharLowerCase(propertyName)
						+ "Query = "
						+ StringUtilities.withFirstCharUpperCase(relatedBox
								.getName())
						+ "QueryFactory.getInstance(dbQuery.getDb());";
				getManyMethod += LINE;
				getManyMethod += TAB
						+ TAB
						+ "return "
						+ StringUtilities.withFirstCharLowerCase(propertyName)
						+ "Query.getCollectionFor"
						+ StringUtilities
								.withFirstCharUpperCase(oppositePropertyName)
						+ "(this);";
				getManyMethod += LINE;
				getManyMethod += TAB + "}";
				getManyMethods.add(getManyMethod);
			}
		}

		return getManyMethods;
	}

	protected String buildGetPrimaryKeyMethod() {
		String oidColumnName = StringUtilities.withFirstCharLowerCase(table
				.getOidItem().getName().trim());

		String getPkMethod = "";
		getPkMethod += TAB + "public Map getPrimaryKey(){";
		getPkMethod += LINE;
		getPkMethod += TAB + TAB + "Map primaryKey = new HashMap();";
		getPkMethod += LINE;
		getPkMethod += TAB + TAB + "primaryKey.put(\"" + oidColumnName + "\", "
				+ oidColumnName + ");";
		getPkMethod += LINE;
		getPkMethod += TAB + TAB + "return primaryKey;";
		getPkMethod += LINE;
		getPkMethod += TAB + "}";

		return getPkMethod;
	}

	protected String buildMethod(String columnName, String javaType,
			int methodType, int methodScope) {
		String method = "";

		// scope
		switch (methodScope) {
		case PRIVATE:
			method += TAB + "private ";
			break;
		case PROTECTED:
			method += TAB + "protected ";
			break;
		case PUBLIC:
			method += TAB + "public ";
			break;
		}

		if (javaType.trim().equalsIgnoreCase("java.sql.Date")
				|| javaType.trim().equalsIgnoreCase("java.sql.Time")
				|| javaType.trim().equalsIgnoreCase("java.sql.Timestamp")) {
			javaType = "java.util.Date";
		}

		if (javaType.trim().equalsIgnoreCase("java.net.URL")
				|| javaType.trim().equalsIgnoreCase("org.dmlite.type.Email")) {
			javaType = "java.lang.String";
		}

		String argName = StringUtilities.withFirstCharLowerCase(columnName);

		// name (1/2)
		switch (methodType) {
		case GET_METHOD:
			// type
			method += javaType + " ";

			method += "get";
			method += StringUtilities.withFirstCharUpperCase(columnName);
			method += "(){";
			method += LINE;
			method += TAB + TAB + "return " + argName + ";";
			method += LINE;
			method += TAB + "}";
			break;
		case SET_METHOD:
			// type
			method += "void ";

			method += "set";
			method += StringUtilities.withFirstCharUpperCase(columnName);
			method += "(" + javaType + " " + argName + "){";
			method += LINE;
			method += TAB + TAB + "this." + argName + "=" + argName + ";";
			method += LINE;
			method += TAB + "}";
			break;
		}

		return method;
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